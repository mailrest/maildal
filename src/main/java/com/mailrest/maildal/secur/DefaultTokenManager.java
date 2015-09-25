/*
 *      Copyright (C) 2015 Noorq, Inc.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.mailrest.maildal.secur;

import java.security.InvalidKeyException;
import java.security.SignatureException;
import java.util.List;

import net.oauth.jsontoken.Checker;
import net.oauth.jsontoken.JsonToken;
import net.oauth.jsontoken.JsonTokenParser;
import net.oauth.jsontoken.crypto.HmacSHA256Signer;
import net.oauth.jsontoken.crypto.HmacSHA256Verifier;
import net.oauth.jsontoken.crypto.SignatureAlgorithm;
import net.oauth.jsontoken.crypto.Verifier;
import net.oauth.jsontoken.discovery.VerifierProvider;
import net.oauth.jsontoken.discovery.VerifierProviders;

import org.joda.time.Instant;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public final class DefaultTokenManager<T extends JsonSerializable<T>> implements TokenManager<T> {

	public static final String ISSUER = "MR";
	public static final String KEY_ID = "dev";
	public static final String SIGNING_KEY = "123456";

	private static final HmacSHA256Signer signer;
	private static final Verifier verifier;
	private static final VerifierProvider locator;
	private static final VerifierProviders locators = new VerifierProviders();
	
	static {

		byte[] key = SIGNING_KEY.getBytes();

		try {
			signer = new HmacSHA256Signer(ISSUER, KEY_ID, key);
			verifier = new HmacSHA256Verifier(key);

			locator = new VerifierProvider() {

				@Override
				public List<Verifier> findVerifier(String id, String key) {
					return Lists.newArrayList(verifier);
				}
			};

			locators.setVerifierProvider(SignatureAlgorithm.HS256, locator);

		} catch (InvalidKeyException e) {
			throw new RuntimeException(e);
		}

	}
	
	private final T instance;
	private final Checker checker;
	private final JsonTokenParser parser;	
	
	public DefaultTokenManager(T instance) {
		
		this.instance = instance;
		
		this.checker = new Checker() {

			@Override
			public void check(JsonObject payload) throws SignatureException {
				String error = instance.verifyJson(payload);
				if (error != null) {
					throw new InvalidTokenException("json validation failed: " + error);
				}
			}

		};
		
		this.parser = new JsonTokenParser(locators, checker);
		
	}
	
	@Override
	public String toJwt(T tokenObj, int durationMinutes) {
		
		if (tokenObj == null) {
			throw new IllegalArgumentException("empty tokenObj");
		}

		String error = tokenObj.verifyObj();
		if (error != null) {
			throw new IllegalArgumentException("token validation failed: " + error);
		}

		long time = System.currentTimeMillis();

		JsonToken token = new JsonToken(signer);
		// token.setAudience(AUDIENCE);
		token.setIssuedAt(new Instant(time));
		token.setExpiration(new Instant(time + 1000L * 60L * durationMinutes));

		JsonObject payload = token.getPayloadAsJsonObject();
		tokenObj.writeJson(payload);

		try {
			return token.serializeAndSign();
		} catch (SignatureException e) {
			throw new InvalidTokenException(e);
		}
	}

	@Override
	public T fromJwt(String jwt) {
		
		if (jwt == null || jwt.isEmpty()) {
			throw new IllegalArgumentException("empty token");
		}

		JsonToken jsonToken;
		try {
			jsonToken = parser.verifyAndDeserialize(jwt);
		} catch (IllegalStateException | JsonParseException | SignatureException e) {
			throw new InvalidTokenException("wrong token format", e);
		}

		JsonObject payload = jsonToken.getPayloadAsJsonObject();

		// System.out.println(payload);

		String issuer = payload.getAsJsonPrimitive("iss").getAsString();
		if (!ISSUER.equals(issuer)) {
			throw new InvalidTokenException("invalid issuer");
		}

		Instant currentTime = new Instant(System.currentTimeMillis());

		Instant issuedTime = jsonToken.getIssuedAt();
		Instant expiresTime = jsonToken.getExpiration();

		if (issuedTime == null) {
			throw new InvalidTokenException("empty issuedTime");
		}

		if (expiresTime == null) {
			throw new InvalidTokenException("empty expiresTime");
		}

		if (currentTime.compareTo(issuedTime) >= 0 && currentTime.compareTo(expiresTime) <= 0) {
			
			T tokenObj = instance.newInstance();
			tokenObj.readJson(payload);
			
			String error = tokenObj.verifyObj();
			if (error != null) {
				throw new InvalidTokenException("token validation failed: " + error);
			}
			
			return tokenObj;
			
		} else {
			throw new InvalidTokenException("expired token");
		}
		
	}

}
