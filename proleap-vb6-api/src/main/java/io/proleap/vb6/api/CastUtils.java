/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.api;

import java.util.Date;

public class CastUtils {

	public static Boolean toBool(final Integer integer) {
		return integer != 0;
	}

	public static Boolean toBool(final Long lng) {
		return lng != 0;
	}

	public static Boolean toBool(final Object obj) {
		return null;
	}

	public static Byte toByte(final Object obj) {
		return null;
	}

	public static Byte toByte(final String str) {
		return Byte.parseByte(str);
	}

	public static Date toDate(final Integer integer) {
		return null;
	}

	public static Date toDate(final Object obj) {
		return null;
	}

	public static Integer toInt(final Boolean bool) {
		return bool ? 1 : 0;
	}

	public static Integer toInt(final Double dbl) {
		return dbl.intValue();
	}

	public static Integer toInt(final Long lng) {
		return lng.intValue();
	}

	public static Integer toInt(final Object obj) {
		return null;
	}

	public static Integer toInt(final String str) {
		return Integer.parseInt(str);
	}

	public static Long toLong(final Boolean bool) {
		return bool ? 1l : 0l;
	}

	public static Long toLong(final Double dbl) {
		return Math.round(dbl);
	}

	public static Long toLong(final Object obj) {
		return null;
	}

	public static Long toLong(final String str) {
		return Long.parseLong(str);
	}
}
