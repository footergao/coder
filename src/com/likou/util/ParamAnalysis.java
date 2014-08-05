package com.likou.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import android.util.Log;

public class ParamAnalysis {
	@SuppressWarnings("unchecked")
	public static String getSignOfParams(Map params, String key) {
		String sign = "";
		try {
			sign = DES.encryptDES(getContent(params), key).replace(" ", "")
					.replace("+", "");

			// 取得后8位，作为真正的sign
			if (!sign.equals("")) {
				int length = sign.length();
				sign = sign.substring(length - 8, length);
			}
			Log.v("PARAM", sign);
			return sign;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sign;
	}

	// 拼参数(用于生成sign)
	private static String getContent(Map params) {
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			if (params.get(key) != null) {
				String value = params.get(key).toString();
				if (i == keys.size() - 1) {
					prestr = prestr + key + "=" + value;
				} else {
					prestr = prestr + key + "=" + value + "&";
				}
			} else {
				if (i == keys.size() - 1) {
					prestr = prestr + key + "=";
				} else {
					prestr = prestr + key + "=&";
				}
			}
		}
		return prestr;
	}

	// 拼参数，并将参数encode(用于生成url)
	public static String getUrlOfParams(Map params)
			throws UnsupportedEncodingException {
		List keys = new ArrayList(params.keySet());
		Collections.sort(keys);

		String prestrUrl = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = (String) keys.get(i);
			if (params.get(key) != null) {
				String value = params.get(key).toString();
				if (i == keys.size() - 1) {
					prestrUrl = prestrUrl + key + "="
							+ EncodeUrl(value).replaceAll("\\+", "%20");
				} else {
					prestrUrl = prestrUrl + key + "="
							+ EncodeUrl(value).replaceAll("\\+", "%20") + "&";
				}
			} else {
				if (i == keys.size() - 1) {
					prestrUrl = prestrUrl + key + "=";
				} else {
					prestrUrl = prestrUrl + key + "=&";
				}
			}
		}
		return prestrUrl;
	}

	private static String EncodeUrl(String url)
			throws UnsupportedEncodingException {
		url = URLEncoder.encode(url, BaseData.CHARSET);
		url = url.replace("\\+", "%2B");
		url = url.replace("\\/", "%2F");
		url = url.replace("\\?", "%3F");
		url = url.replace("\\%", "%25");
		url = url.replace("\\#", "%23");
		url = url.replace("\\&", "%26");
		url = url.replace("\\=", "%3D");
		return url;
	}

}
