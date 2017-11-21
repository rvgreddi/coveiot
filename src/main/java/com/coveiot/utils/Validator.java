package com.coveiot.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 
 * @author VENUGOPAL
 *
 */

public class Validator {

	public static boolean dateValidate(LocalDateTime tm) {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plus = tm.plusMinutes(CoveiotConstants.URI_EXPIRE_IN_MINUTES);
		return (now.isAfter(tm) && tm.isBefore(plus));
	}

	public static boolean weatherDateValidate(Timestamp ts) {
		LocalDateTime tm = ts.toLocalDateTime();
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime plus = tm.plusSeconds(CoveiotConstants.URI_EXPIRE_IN_SECONDS);
		return (now.isAfter(tm) && tm.isBefore(plus));
	}
}
