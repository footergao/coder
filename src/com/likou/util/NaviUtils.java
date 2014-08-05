package com.likou.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.baidu.mapapi.navi.BaiduMapAppNotSupportNaviException;
import com.baidu.mapapi.navi.BaiduMapNavigation;
import com.baidu.mapapi.navi.NaviPara;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class NaviUtils {
	
	/**
	 * 开始导航
	 * 
	 * @param view
	 */
	public static void startNavi(final Activity activity, GeoPoint startPoint, GeoPoint endPoint) {
		// 构建 导航参数
		NaviPara para = new NaviPara();
		para.startPoint = startPoint;
		para.startName = "从这里开始";
		para.endPoint = endPoint;
		para.endName = "到这里结束";

		try {
			
			BaiduMapNavigation.openBaiduMapNavi(para, activity);
			
		} catch (BaiduMapAppNotSupportNaviException e) {
			e.printStackTrace();
			AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setMessage("您尚未安装百度地图app或app版本过低，点击确认安装？");
			builder.setTitle("提示");
			builder.setPositiveButton("确认",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
							BaiduMapNavigation
									.GetLatestBaiduMapApp(activity);
						}
					});

			builder.setNegativeButton("取消",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					});
			builder.create().show();
		}
	}

}
