package com.likou.util;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class GpsAction {

	private Context context;
	public BDLocation location;
	private ILocation il;

	
	public GpsAction(Context context,ILocation il) {
		this.context = context;
		this.il = il;
		this.getLocation();
	}

	public LocationClient mLocationClient = null;

	public void getLocation() {
		
		mLocationClient = new LocationClient(context);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		option.setAddrType("all");// 返回的定位结果包含地址信息
		option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
		option.disableCache(true);// 禁止启用缓存定位
		mLocationClient.setLocOption(option);

		Log.v("gl", "开启定位");
		mLocationClient.start();

		
		mLocationClient.registerLocationListener(new BDLocationListener() {

			@Override
			public void onReceivePoi(BDLocation arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onReceiveLocation(BDLocation location) {
				
				if (location == null)
					return;
				StringBuffer sb = new StringBuffer(256);
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nradius : ");
				sb.append(location.getRadius());
				if (location.getLocType() == BDLocation.TypeGpsLocation) {
					sb.append("\nspeed : ");
					sb.append(location.getSpeed());
					sb.append("\nsatellite : ");
					sb.append(location.getSatelliteNumber());
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
					sb.append("\naddr : ");
					sb.append(location.getAddrStr());
				}

				if (location.getLongitude() == 4.9E-324
						|| location.getLatitude() == 4.9E-324) {
					Toast.makeText(context, "定位失败，请检查网络或GPS", Toast.LENGTH_SHORT)
							.show();
					location.setLongitude(0);
					location.setLatitude(0);
					il.Result(location);
				} else {
					//Toast.makeText(context, "定位方式" + sb.toString(),
					//		Toast.LENGTH_SHORT).show();
					Log.v("gl", "~~~~~~~~~~~" + location.getLatitude());

					il.Result(location);
				}

			}
		});
		}
}
