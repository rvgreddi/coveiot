package com.coveiot.utils;

import java.time.LocalDateTime;

/**
 * 
 * @author VENUGOPAL
 *
 */

public class StringUtils {

	public static String constructRedirectURI(Long id) {
		StringBuilder builder = new StringBuilder();
		builder.append(CoveiotConstants.LOGIN_BASE_URI);
		builder.append(String.valueOf(id));
		builder.append("?tm=");
		builder.append(LocalDateTime.now());

		return builder.toString();

	}
}
