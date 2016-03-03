package cn.cjtblog.util;

public class StringUtil {
	public static boolean isEmptyOrNull(String str) {

		return str != null && str.isEmpty() ? false : true;
	}
}
