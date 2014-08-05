package com.likou.activity.main;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import com.likou.R;
import com.likou.activity.common.BaseFragmentActivity;
import com.likou.application.Config;
import com.likou.model.VersionInfo;

public class LoginActivity extends BaseFragmentActivity {

	// private static final String TAG = makeLogTag(WelcomeActivity.class);

	private static final String ACTION_VERSION = "/version/getVersionInfo";
	private static final int API_VERSION = 0x10;

	private Button bt_register;
	private VersionInfo mVersion;
	private int currentVersion;
	private long enqueue;
	private DownloadManager dm;

	@Override
	protected int centerLayoutId() {
		// TODO Auto-generated method stub
		return R.layout.login_layout;
	}

	@Override
	protected void initCenter(View centerView) {
		// TODO Auto-generated method stub


		setTitleTextRes("登录ulay");
		
		
		showRightView(true, "忘记密码了？", new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startToActivity(RegisterActivity1.class, null);
			}
		});
		

		
		
		bt_register = (Button) findViewById(R.id.bt_register);
		
		
	
	}
	
	@Override
	protected void initData() {
		// TODO Auto-generated method stub
		// 版本更新
				versionUpdate();

				registerReceiver(new DownloadReceiver(), new IntentFilter(
						DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		
	}

	@Override
	protected void initAction() {
		// TODO Auto-generated method stub

		bt_register.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startToActivity(RegisterActivity2.class, null);
			}
		});

	}
			
	/**
	 * 版本更新
	 */
	private void versionUpdate() {
		currentVersion = mApplication.getVersion();
		// httpRequest(getVersionUrl(), API_VERSION);
	}

	/**
	 * 取得网址url
	 * 
	 * @return
	 */
	private String getVersionUrl() {
		return new StringBuffer(Config.WEBSERVICE_URL).append(ACTION_VERSION)
				.toString();
	}

	@Override
	protected void httpResponse(String json, int action) {
		super.httpResponse(json, action);
		switch (action) {
		case API_VERSION:
			versionHandler(json);
			break;
		default:
			break;
		}
	}

	private void versionHandler(String json) {
		mVersion = gson.fromJson(json, VersionInfo.class);
		if (mVersion != null) {
			if (Integer.valueOf(mVersion.apkVersion) > currentVersion) {
				showUpdateDialog();
			}
		}
	}

	/**
	 * 显示更新对话框s
	 */
	private void showUpdateDialog() {
		AlertDialog.Builder updateDialog = new AlertDialog.Builder(this);
		updateDialog.setTitle(R.string.updateTitle);
		updateDialog.setMessage(R.string.updateMessage);
		updateDialog.setNegativeButton(R.string.updateOk,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						downloadAPK();
					}
				});
		updateDialog.setPositiveButton(R.string.updateCancel, null);
		updateDialog.show();
	}

	/**
	 * 下载APK
	 */
	@SuppressLint("NewApi")
	protected void downloadAPK() {
		dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
		Request request = new Request(Uri.parse(mVersion.apkPath));
		enqueue = dm.enqueue(request);
	}


	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if ((System.currentTimeMillis() - mExitTime) > 2000) {
				Toast.makeText(this, "在按一次退出", Toast.LENGTH_SHORT).show();
				mExitTime = System.currentTimeMillis();
			} else {
				finish();
			}
			return true;
		}
		// 拦截MENU按钮点击事件，让他无任何操作
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 显示下载视图
	 */
	public void showDownload() {
		Intent i = new Intent();
		i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
		startActivity(i);
	}

	public class DownloadReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
				Query query = new Query();
				query.setFilterById(enqueue);
				Cursor c = dm.query(query);
				if (c.moveToFirst()) {
					int columnIndex = c
							.getColumnIndex(DownloadManager.COLUMN_STATUS);
					if (DownloadManager.STATUS_SUCCESSFUL == c
							.getInt(columnIndex)) {
						// 显示下载
						showDownload();
					}
				}
			}
		}

	}
}
