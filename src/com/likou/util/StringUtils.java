package com.likou.util;

public class StringUtils {

	public static String replaceHttpLink(String url) {
		return url.replace("http://", "").replace("/", "+");
	}

}
