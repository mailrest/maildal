package com.mailrest.maildal.repository;

import static com.noorq.casser.core.Query.eq;

import java.util.Optional;

import scala.concurrent.Future;

import com.datastax.driver.core.ResultSet;
import com.mailrest.maildal.gen.CookieId;
import com.mailrest.maildal.model.Cookie;
import com.noorq.casser.core.Casser;
import com.noorq.casser.support.Fun;

public interface CookieRepository extends AbstractRepository {

	static final Cookie cookie = Casser.dsl(Cookie.class);
	
	static final int ONE_MONTH = 30 * 3600 * 24;
	
	default Future<Optional<Fun.Tuple1<String>>> lookupCookie(String cookieId) {
		
		return session()
				.select(cookie::accountId)
				.where(cookie::cookieId, eq(cookieId))
				.single()
				.future();
	}
	
	default Future<Fun.Tuple2<ResultSet, String>> createCookie(
			String accountId) {
		
		String cookieId = CookieId.next();
		
		return session()
			.insert()
			.value(cookie::cookieId, cookieId)
			.value(cookie::accountId, accountId)
			.usingTtl(ONE_MONTH)
			.future(cookieId);
		
	}
	
	
}
