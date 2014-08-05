package com.likou.more;

import java.io.File;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.likou.R;
import com.likou.activity.common.BaseActivity;

/**
 * 更多
 *
 */
public class MoreActivity extends BaseActivity{
	LinearLayout ll_more_version,ll_more_feedback,ll_more_help,ll_more_aboutus,ll_more_servicephone;
	
	Button btn_right;
	private StringBuffer httpUrl;
	private double coordinateSLX=0.0,coordinateSLY=0.0;
	@Override
	protected int centerLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.lk_item_more;
	} 

	@Override
	protected void initCenter(View centerView) {
		// TODO Auto-generated method stub
		ll_more_version = (LinearLayout)findViewById(R.id.ll_more_version);
		ll_more_feedback = (LinearLayout)findViewById(R.id.ll_more_feedback);
		ll_more_help = (LinearLayout)findViewById(R.id.ll_more_help);
		ll_more_aboutus = (LinearLayout)findViewById(R.id.ll_more_aboutus);
		ll_more_servicephone = (LinearLayout)findViewById(R.id.ll_more_servicephone);
		
		
		btn_right = (Button)findViewById(R.id.top_btn_right);
		btn_right.setBackgroundColor(00000000);
	}

	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initAction() {
		// TODO Auto-generated method stub
		//版本更新
		ll_more_version.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				queryEditionInfo();
			}
		});
		
		//意见反馈
		ll_more_feedback.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
//				open(AddFeedBackActivity.class);
			}
		});
		
		//使用帮助
		ll_more_help.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				open(HelpActivity.class);
			}
		});
		
		//关于我们
		ll_more_aboutus.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MoreActivity.this, AboutUsActivity.class));	
			}
		});
		
		//客服电话
		ll_more_servicephone.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				queryPhone();
			}
		});
	}
//	/**
//	 * 获取新版本
//	 */
//	private void queryEditionInfo(){
//		try {
//			String oldVersion = getPackageManager().getPackageInfo(
//					getApplicationInfo().packageName, 0).versionName;
//			System.out.println("oldVersion====="+oldVersion);
//
//			try {
//				Map<String, String> params = new HashMap<String, String>();
//				params.put("versionInfoId", "1"); 
//				params.put("partner", BaseData.partner);
//		    	params.put("input_charset", BaseData.CHARSET); 
////				params.put("pwd", BaseData.getUser().getPassword());
//				String sign = ParamAnalysis.getSignOfParams(params, BaseData.encryptKey);
//				params.put("sign", sign);
//				String url =  ParamAnalysis.getUrlOfParams(params);
//				httpUrl = new StringBuffer(Connection.HTTP_PATH)
//				.append("api/vi/version_view.action?")
//				.append(url);
//				
//				Log.v("xxx",httpUrl.toString());
//				String xml = APIHandler.getXML(httpUrl.toString());
//				Log.v("xxx",xml);
//				try {
//					VersionInfo version = (VersionInfo) xmlUtil.fromXml(xml, VersionInfo.class);
//					if(version != null)
//					{	
//					String apkVersion=version.getApkVersion();//版本号
//					final String apkVersionName=version.getApkVersionName();//版本号名字
//					String apkSize=version.getApkSize();//版本大小
//					String apkPath=version.getApkPath();//客户端下载地址
//					if (apkVersion == null || "".equals(apkVersion) || "null".equals(apkVersion)
//							|| oldVersion.equals(apkVersion))//无更新
//						{
//						SystemInfo.showToast(MoreActivity.this, "当前已是最新版本");
//						}else{
//							Vector<DocumentEntity> entities = new Vector<DocumentEntity>();
//							DocumentEntity entity = new DocumentEntity(apkVersionName, null,
//									apkPath, Long.parseLong(apkSize));
//							entities.add(entity);
//							// 启动文件下载
//							FileTransmitter fileTransmitter = new FileTransmitter(
//									MoreActivity.this, entities, 1024 * 100, true);
//							fileTransmitter.setOnDownloadFinishedListener(new OnDownloadFinishedListener()
//							{
//								@Override
//								public void onFinished(String downloadDir)
//								{
//									File file = new File(downloadDir + File.separator
//											+ apkVersionName);
//									open(file);
//								}
//							});	 
//							
//						}
//				}
//					
//				} catch (Exception e) {				
//					//toast(e.getMessage());
//					e.printStackTrace();
//				}
//				} catch (Exception e) {				
//					//toast(e.getMessage());
//					e.printStackTrace();
//				}
//				
//
//		} catch (NameNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	/**
//	 * 打开SD卡中文件
//	 */
//	public void open(File file)
//	{
//		if (file == null || !file.exists()) return;
//		Intent intent = new Intent();
//		intent.setAction(android.content.Intent.ACTION_VIEW);
//		Uri uri = Uri.fromFile(file);
//		intent.setDataAndType(uri, "application/vnd.android.package-archive");
//		startActivity(intent);
//	}
//	/**
//	 * 客服电话
//	 */
//	private void queryPhone(){
//		try {
//			Map<String, String> params = new HashMap<String, String>();
//			//http://localhost:8080/api/so/systemOption_servicetel.action?
//			//creatorId=*&pwd=*&coordinateX=*&coordinateY=*& flag=*&partner=*&input_charset=*&sign=*
//
//
//	    	params.put("input_charset", BaseData.CHARSET); 
//			params.put("partner", BaseData.partner);
//			params.put("creatorId",String.valueOf(BaseData.getUser().getUserId()));
//			params.put("pwd", BaseData.getUser().getPassword());
//			params.put("coordinateX",String.valueOf(coordinateSLX));
//	        params.put("coordinateY",String.valueOf(coordinateSLY));
//	        params.put("flag","1");
//
//			String sign = ParamAnalysis.getSignOfParams(params, BaseData.encryptKey);
//			params.put("sign", sign);
//			String url =  ParamAnalysis.getUrlOfParams(params);
//			httpUrl = new StringBuffer(Connection.HTTP_PATH)
//			.append("api/so/systemOption_servicetel.action?")
//			.append(url);
//			
//			Log.v("xxx",httpUrl.toString());
//			String xml = APIHandler.getXML(httpUrl.toString());
//			Log.v("xxx",xml);
//			try {
//				SystemOption phoneModel = (SystemOption) xmlUtil.fromXml(xml, SystemOption.class);
//				if(phoneModel != null)
//				{
//					String phoneNum=phoneModel.getContent();
//					Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phoneNum));
//					startActivity(intent);
//				}
//				
//			} catch (Exception e) {				
//				//toast(e.getMessage());
//				e.printStackTrace();
//			}
//			} catch (Exception e) {				
//				//toast(e.getMessage());
//				e.printStackTrace();
//			}
//	}


}
