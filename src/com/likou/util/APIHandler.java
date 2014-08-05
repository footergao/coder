package com.likou.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class APIHandler {
	public static String getXML(String httpUrl) {
		StringBuffer buffer = new StringBuffer();
		System.setProperty("sun.net.client.defaultConnectTimeout", "8000");
		System.setProperty("sun.net.client.defaultReadTimeout", "8000");
		try {
			URL newUrl = new URL(httpUrl);
			HttpURLConnection hConnect = (HttpURLConnection) newUrl
					.openConnection();
			hConnect.setConnectTimeout(10000);// 10s内连不上就断开
			InputStream inputStream = hConnect.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));
			
			int ch;
			for (int length = 0; (ch = rd.read()) > -1
					&& (200500 <= 0 || length < 200500); length++)
				buffer.append((char) ch);
			rd.close();
			hConnect.disconnect();
			return buffer.toString().trim();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public static String getXML2(String httpUrl) {
		StringBuffer buffer = new StringBuffer();
		System.setProperty("sun.net.client.defaultConnectTimeout", "8000");
		System.setProperty("sun.net.client.defaultReadTimeout", "8000");
		try {
			URL newUrl = new URL(httpUrl);
			HttpURLConnection hConnect = (HttpURLConnection) newUrl
					.openConnection();
			hConnect.setConnectTimeout(10000);// 10s内连不上就断开
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					hConnect.getInputStream(), "UTF-8"));
			
			int ch;
			for (int length = 0; (ch = rd.read()) > -1
					&& (200500 <= 0 || length < 200500); length++)
				buffer.append((char) ch);
			rd.close();
			hConnect.disconnect();
			return buffer.toString().trim();
		} catch (Exception e) {
			return httpUrl;
		}
	}
	public static Bitmap getBitMap(String url) throws Exception{
		Bitmap bm = null;
		URL myFileUrl = null;
		try {
		myFileUrl = new URL(url);

		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}

		HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
		conn.setDoInput(true);
		conn.connect();
		InputStream in = conn.getInputStream();
		bm = BitmapFactory.decodeStream(in);
		in.close();

		return bm;

		}
	
}