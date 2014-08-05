package com.likou.util;

import java.io.File;
import java.util.Hashtable;

import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Toast;

public class SystemInfo {
	static {
		// 不存在则创建目录
		String sdCardPath = getExternalStoragePath();
		if (sdCardPath != null) {
			File dir = new File(sdCardPath);
			if (!dir.exists())
				dir.mkdir();
		}
	}

//	/**
//	 * 登录用户信息
//	 */
//	public static UserEntity user;
//	/**
//	 * 站点配置信息
//	 */
//	public static ConfigEntity config;
	/**
	 * 简单存储文件对象名称
	 */
	public static final String SETTING_INFOS = "Setting_Info";
	/**
	 * 登录验证规则
	 */
	public static int VALIDATERULE = -1;
	/**
	 * 模块分配权限信息列表
	 */
	public static Hashtable<String, String> authorities;
	/**
	 * 是否播放语音
	 */
	public static boolean MESSAGEVOICE = true;

	/**
	 * 获取安装程序保存的SD卡目录(默认项目在SD卡中保存文件目录为 SDCard/MobilePlatform)
	 */
	public static String getExternalStoragePath() {
		try {
			// 获取SdCard状态
			String state = android.os.Environment.getExternalStorageState();
			// 判断SdCard是否存在并且是可用的
			if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
				if (android.os.Environment.getExternalStorageDirectory()
						.canWrite()) {
					return android.os.Environment.getExternalStorageDirectory()
							.getPath() + File.separator + "MobilePlatform";
				} else {
					// Toast.makeText(null, "SD卡不可写！",
					// Toast.LENGTH_SHORT).show();
					// showToast("SD卡不可写！");
				}
			} else {
				// showToast("SD卡没安装！");
			}
		} catch (Exception ex) {

		}
		return null;
	}

	/**
	 * 获取安装程序保存的SD卡根目录
	 */
	public static String getSdCardPath() {
		try {
			// 获取SdCard状态
			String state = android.os.Environment.getExternalStorageState();
			// 判断SdCard是否存在并且是可用的
			if (android.os.Environment.MEDIA_MOUNTED.equals(state)) {
				if (android.os.Environment.getExternalStorageDirectory()
						.canWrite()) {
					return android.os.Environment.getExternalStorageDirectory()
							.getPath();
				}
			}
		} catch (Exception ex) {

		}
		return null;
	}

	/**
	 * 获取手机荧屏类型
	 * 
	 * @param activity
	 * @return 0->480,1->320,2->240
	 */
	public static int getScreenMode(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		String tmp = "当前手机分辨率为：" + Integer.toString(dm.widthPixels) + ":"
				+ Integer.toString(dm.heightPixels);
		// System.out.println(tmp);
		if (dm.widthPixels > 400)
			return 0;
		if (dm.widthPixels > 300)
			return 1;
		return 2;
	}

	/**
	 * 在荧屏中央显示提示消息
	 * 
	 * @param activity
	 * @param text
	 */
	public static void showToast(Context context,String text) {
		// new Thread()
		// {
		// public void run()
		// {
		// Looper.prepare();
		Toast t = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, t.getXOffset() / 2, t.getYOffset() / 2);
		t.show();
		// Looper.loop();
		// Looper.myLooper().quit();
		// }
		// }.start();
	}

	public static void showToast(Context context,int textId) {
		Toast t = Toast.makeText(context, textId, Toast.LENGTH_SHORT);
		t.setGravity(Gravity.CENTER, t.getXOffset() / 2, t.getYOffset() / 2);
		t.show();
	}

	/**
	 * 获取手机号码或者序列号
	 */
	public static String getPhoneSeqId(Context content, boolean withStarFlag) {
		TelephonyManager tm = (TelephonyManager) content
				.getSystemService(Context.TELEPHONY_SERVICE);
		if (tm.getLine1Number() != null && !tm.getLine1Number().equals("")) {
			return tm.getLine1Number() + (withStarFlag ? "(*)" : "");// 手机号码
		} else if (tm.getSubscriberId() != null
				&& !tm.getSubscriberId().equals("")) {
			return tm.getSubscriberId();// 序列号
		} else {
			return tm.getDeviceId();// the unique device ID
		}
	}

	/**
	 * 检查空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkString(String str) {
		return str != null && !"".equals(str.trim())
				&& !"null".equals(str.trim());
	}
}
