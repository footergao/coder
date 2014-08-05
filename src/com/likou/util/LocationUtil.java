package com.likou.util;

public class LocationUtil {

	public static double distance(double lat1, double lon1, double lat2,
			double lon2) {

		double theta = lon1 - lon2;

		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))

		+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))

		* Math.cos(deg2rad(theta));

		dist = Math.acos(dist);

		dist = rad2deg(dist);

		double miles = dist * 60 * 1.1515;

		return miles;

	}

	private static String getStrDistance(int dis) {
		if (dis >= 1000) {
			return String.valueOf(FloatUtil.IntForFloat(dis, 1000, 2)) + "千米";
		} else {

			return String.valueOf(dis) + "米";
		}
	}

	// 获取定位坐标与客户坐标的距离
	// x,y 定位坐标,cX,cY客户坐标
	public static int getIntDis(double Y, double X, double cY, double cX) {
		if (X == 0 || Y == 0)
			return -1;
		if (cX == 0 || cY == 0)
			return -2;
		String valueStr = String.valueOf(1609.344 * distance(Y, X, cY, cX));
		return Integer.parseInt(valueStr.trim().split("\\.")[0].trim());
	}

	public static String getstrDis(int dis) {
		String str = "";
		switch (dis) {
		case -1:
			str = "0米(定位中...)";
			break;
		case -2:
			str = "0米(首次未定位)";
			break;
		default:
			str = getStrDistance(dis);
		}
		return str;
	}

	public static double deg2rad(double degree) {

		return degree / 180 * Math.PI;

	}

	public static double rad2deg(double radian) {

		return radian * 180 / Math.PI;

	}
}
