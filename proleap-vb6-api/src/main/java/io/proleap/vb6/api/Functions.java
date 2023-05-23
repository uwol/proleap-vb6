/*
 * Copyright (C) 2017, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.vb6.api;

import java.util.Date;

import io.proleap.vb6.api.primitives.FileNumber;

public class Functions {

	public static Double Abs(final Double obj) {
		return obj == null ? null : Math.abs(obj);
	}

	public static Integer Abs(final Integer obj) {
		return obj == null ? null : Math.abs(obj);
	}

	public static Long Abs(final Long obj) {
		return obj == null ? null : Math.abs(obj);
	}

	public static void AppActivate(final Object obj) {

	}

	public static void AppActivate(final Object obj1, final Object obj2) {

	}

	public static void Beep() {

	}

	public static Boolean CBool(final Object obj) {
		return obj instanceof Boolean ? (Boolean) obj : null;
	}

	public static Byte CByte(final Object obj) {
		return obj instanceof Byte ? (Byte) obj : null;
	}

	public static Date CDate(final Object obj) {
		return obj instanceof Date ? (Date) obj : null;
	}

	public static Double CDbl(final Object obj) {
		return obj == null ? null : (Double) obj;
	}

	public static void ChDir(final Object obj) {

	}

	public static void ChDrive(final Object obj) {

	}

	public static String Chr(final Object obj) {
		return obj == null ? null : (String) obj;
	}

	public static Integer CInt(final Object obj) {
		return obj == null ? null : (Integer) obj;
	}

	public static Long CLng(final Object obj) {
		return obj == null ? null : (Long) obj;
	}

	public static void Close() {

	}

	public static void Close(final FileNumber fileNumber) {
	}

	public static Object CreateObject(final String str) {
		return null;
	}

	public static String CStr(final Object obj) {
		return obj == null ? null : (String) obj;
	}

	public static String CStr(final String str) {
		return str;
	}

	public static java.util.Date CVDate(final Object obj) {
		return null;
	}

	public static Date DateAdd(final String str, final Number amount, final Date date) {
		return null;
	}

	public static void DefByte(final String[] letterRange) {

	}

	public static void DefCur(final String[] letterRange) {

	}

	public static void DefDate(final String[] letterRange) {

	}

	public static void DefDbl(final String[] letterRange) {

	}

	public static void DefDec(final String[] letterRange) {

	}

	public static void DefInt(final String[] letterRange) {

	}

	public static void DefLng(final String[] letterRange) {

	}

	public static void DefObj(final String[] letterRange) {

	}

	public static void DefSng(final String[] letterRange) {

	}

	public static void DefStr(final String[] letterRange) {

	}

	public static void DefType(final String[] letterRange) {

	}

	public static void DefVar(final String[] letterRange) {

	}

	public static void DeleteSetting(final Object... obj) {

	}

	public static Object Environ(final Object obj) {
		return null;
	}

	public static void ExitProcess(final Object obj) {

	}

	public static String Format(final Object obj) {
		return "";
	}

	public static String Format(final Object obj, final String str) {
		return "";
	}

	public static Integer FreeFile(final Object obj) {
		return null;
	}

	public static Object IIf(final boolean condition, final Object obj1, final Object obj2) {
		return null;
	}

	public static Object InputBox(final String message, final String message2, final Object input) {
		return null;
	}

	public static Long InStr(final Object obj1, final Object obj2) {
		return null;
	}

	public static Long InStr(final Object obj1, final Object obj2, final Object obj3) {
		return null;
	}

	public static Long InStr(final Object obj1, final Object obj2, final Object obj3, final Object obj4) {
		return null;
	}

	public static int Int(final Object obj1) {
		return 1;
	}

	public static boolean IsArray(final Object obj1) {
		return true;
	}

	public static boolean IsDate(final Object obj1) {
		return true;
	}

	public static boolean IsEmpty(final Object obj1) {
		return true;
	}

	public static boolean IsMissing(final Object obj) {
		return false;
	}

	public static boolean IsNull(final Object obj1) {
		return true;
	}

	public static boolean IsNumeric(final Object obj1) {
		return true;
	}

	public static boolean IsObject(final Object obj1) {
		return true;
	}

	public static Integer LBound(final Object obj) {
		return null;
	}

	public static Integer LBound(final Object obj, final Integer position) {
		return null;
	}

	public static String Left(final Object obj, final int position) {
		return "";
	}

	public static String Left(final Object obj, final long position) {
		return "";
	}

	public static long Len(final Object obj) {
		return 0;
	}

	public static void MessageBeep(final Object obj) {

	}

	public static String Mid(final Object obj, final int start) {
		return "";
	}

	public static String Mid(final Object obj, final int start, final int length) {
		return "";
	}

	public static String Mid(final Object obj, final int start, final long length) {
		return "";
	}

	public static String Mid(final Object obj, final long start) {
		return "";
	}

	public static String Mid(final Object obj, final long start, final long length) {
		return "";
	}

	public static void Open(final Object filename, final FileNumber fileNumber) {
	}

	public static void print(final FileNumber fileNumber, final Object... obj) {
	}

	public static String Replace(final Object obj, final Object pattern, final Object replacement) {
		return "";
	}

	public static String Replace(final Object obj, final Object pattern, final Object replacement, final Object obj4) {
		return "";
	}

	public static String Right(final Object obj, final int position) {
		return "";
	}

	public static void Round(final Object object) {

	}

	public static void Round(final Object obj1, final Object obj2) {

	}

	public static void SaveSetting(final Object... obj) {

	}

	public static void setDate(final Object object) {

	}

	public static Object Shell(final Object obj) {
		return null;
	}

	public static Object Shell(final Object obj, final Object number) {
		return null;
	}

	public static String Space(final Object obj) {
		return null;
	}

	public static Boolean StrComp(final String str1, final String str2, final Boolean binaryCompare) {
		return null;
	}

	public static String String(final Integer amount, final String str) {
		return null;
	}

	public static String SuperTrim(final Object obj) {
		return "";
	}

	public static String Trim(final Object obj) {
		return "";
	}

	public static String TypeName(final Object obj) {
		return "";
	}

	public static Integer UBound(final Object obj) {
		return null;
	}

	public static Integer UBound(final Object obj, final Object obj2) {
		return null;
	}

	public static String UCase(final Object obj) {
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static Class VarType(final Object obj) {
		return null;
	}

	public static void Write(final FileNumber fileNumber, final Object... obj) {
	}
}
