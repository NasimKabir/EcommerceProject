package com.nasim.jwt;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtUtils {
	public static Date getExpirationTimeHourly(Long expirationHour) {
		Date now = new Date();
		Long expiredInMiliS = TimeUnit.HOURS.toMillis(expirationHour);
		return new Date(expiredInMiliS + now.getTime());
	}
}
