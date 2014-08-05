package com.likou.application;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.likou.db.DatabaseHelper;
import com.likou.model.Member;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class LikouApplication extends Application {

	public static final String TAG = LikouApplication.class.getSimpleName();

	private String TopAdvertisementUrl;
	private String BottomAdvertisementUrl;

	private DatabaseHelper mDBHelper;
	private static LikouApplication sInstance;
	public BDLocation bdLocation;
	private BMapManager mBMapManager;
	public boolean m_bKeyRight = true;
	public LocationClient mLocClient;
	private Member mMember;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;
		initImageLoader();
		initEngineManager(this);
		getLocationClient(this);
	}

	public DatabaseHelper getDBHelper() {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
		}
		return mDBHelper;
	}

	public BDLocation getBDLocation() {
		if (bdLocation == null) {
			bdLocation = new BDLocation();
			bdLocation.setLatitude(32.000);
			bdLocation.setLongitude(128.000);
		}
		return bdLocation;
	}
	
	public void setMember(Member member) {
		this.mMember = member;
	}

	public Member getMember() {
		return mMember;
	}

	public LocationClient getLocationClient(Context context) {
		if (mLocClient == null) {
			// 定位初始化
			mLocClient = new LocationClient(getApplicationContext());
			mLocClient.registerLocationListener(new BDLocationListener() {

				@Override
				public void onReceivePoi(BDLocation arg0) {
					if (arg0 == null)
						return;
					bdLocation = arg0;
				}

				@Override
				public void onReceiveLocation(BDLocation arg0) {
					if (arg0 == null)
						return;
					bdLocation = arg0;
				}
			});
			LocationClientOption option = new LocationClientOption();
			option.setAddrType("all");// 返回的定位结果包含地址信息
			option.setOpenGps(true);// 打开gps
			option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
			option.disableCache(true);// 禁止启用缓存定位
			mLocClient.setLocOption(option);
			mLocClient.start();
		}
		return mLocClient;
	}

	/**
	 * 取得当前版本信息
	 * @return
	 */
	public int getVersion() {
		PackageManager pm = this.getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(this.getPackageName(), 0);
			return info.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 初始化地图引擎
	 * 
	 * @param context
	 */
	private void initEngineManager(final Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int iError) {
				// 非零值表示key验证未通过
				if (iError != 0) {
					// // 授权Key错误：
					// Toast.makeText(
					// context,
					// "请在 DemoApplication.java文件输入正确的授权Key,并检查您的网络连接是否正常！error: "
					// + iError, Toast.LENGTH_LONG).show();
					LikouApplication.getInstance().m_bKeyRight = false;
				} else {
					LikouApplication.getInstance().m_bKeyRight = true;
					// Toast.makeText(
					// LikouApplication.getInstance()
					// .getApplicationContext(), "key认证成功",
					// Toast.LENGTH_LONG).show();
				}

			}

			@Override
			public void onGetNetworkState(int iError) {
				if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
					// Toast.makeText(context, "您的网络出错啦！", Toast.LENGTH_LONG)
					// .show();
				} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
					// Toast.makeText(context, "输入正确的检索条件！", Toast.LENGTH_LONG)
					// .show();
				}

			}
		})) {
			Toast.makeText(context, "BMapManager  初始化错误!", Toast.LENGTH_LONG)
					.show();
		}
	}

	/**
	 * 
	 * @return
	 */
	public static LikouApplication getInstance() {
		return sInstance;
	}

	/**
	 * 初始化图片缓存
	 */
	private void initImageLoader() {
		// 初始化图片加载库
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.cacheOnDisc(true)
				// 图片存本地
				.cacheInMemory(true).displayer(new FadeInBitmapDisplayer(50))
				.bitmapConfig(Bitmap.Config.RGB_565)
				.imageScaleType(ImageScaleType.EXACTLY) // default
				.build();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.memoryCache(new UsingFreqLimitedMemoryCache(16 * 1024 * 1024))
				.defaultDisplayImageOptions(defaultOptions).build();
		ImageLoader.getInstance().init(config);
	}

	public String getTopAdvertisementUrl() {
		return TopAdvertisementUrl;
	}

	public void setTopAdvertisementUrl(String topAdvertisementUrl) {
		TopAdvertisementUrl = topAdvertisementUrl;
	}

	public String getBottomAdvertisementUrl() {
		return BottomAdvertisementUrl;
	}

	public void setBottomAdvertisementUrl(String bottomAdvertisementUrl) {
		BottomAdvertisementUrl = bottomAdvertisementUrl;
	}

}
