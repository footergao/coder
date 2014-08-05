package com.likou.activity.common;

import static com.likou.util.LogUtils.LOGE;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.baidu.location.BDLocation;
import com.google.gson.Gson;
import com.likou.R;
import com.likou.application.Config;
import com.likou.application.LikouApplication;
import com.likou.db.DatabaseHelper;
import com.likou.model.Error;
import com.likou.model.Member;
import com.likou.model.WuDiModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

public abstract class BaseFragmentActivity extends FragmentActivity {

	private static final String TAG = BaseFragmentActivity.class
			.getSimpleName();

	protected static int PAGE_SIZE = 15;
	protected static int PRODUCT_NUM = 3;

	protected int current_page = 1;

	protected BDLocation mLocation;
	protected LikouApplication mApplication;
	protected DatabaseHelper mDBHelper;
	protected StringRequest sRequest;
	protected int totalCount;
	protected int totalPageCount;
	protected int screenHeight;
	protected int screenWidth;
	protected ProgressDialog mProgressDialog;
	protected SharedPreferences mPreferences;
	protected Member mMember;
	protected WuDiModel mModel;
	protected Error mError;
	protected Gson gson;

	private AsyncHttpClient mHttp;

	protected View centerView;

	private TextView tvTitle;
	private String title;

	protected Button bt_top_left;

	protected Button bt_top_right;

	protected Button bt_top_center;

	protected RelativeLayout template_top;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		setContentView(R.layout.template_layout_defination);
		//
		WindowManager manage = getWindowManager();
		Display display = manage.getDefaultDisplay();
		screenHeight = display.getHeight();
		screenWidth = display.getWidth();
		//
		init();
		// 标题党
		LinearLayout center = (LinearLayout) findViewById(R.id.template_center);
		LayoutInflater lInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		centerView = lInflater.inflate(centerLayoutId(), null, true);
		center.addView(centerView);
		initCenter(center);
		tvTitle.setText(title);
		initData();
		initAction();
		//
		mApplication = (LikouApplication) getApplication();
		mLocation = mApplication.getBDLocation();
		mDBHelper = mApplication.getDBHelper();
		mPreferences = getSharedPreferences(Config.APP_NAME, MODE_PRIVATE);
		mMember = mApplication.getMember();
		mHttp = new AsyncHttpClient();
		gson = new Gson();
	}

	private void init() {
		// TODO Auto-generated method stub
		bt_top_left = (Button) findViewById(R.id.top_btn_left);
		bt_top_right = (Button) findViewById(R.id.top_btn_right);
		bt_top_center = (Button) findViewById(R.id.top_btn_center);

		template_top = (RelativeLayout) findViewById(R.id.template_top);
	}

	protected void httpRequest(String url, final int action) {
		if (url != null) {
			LOGE(TAG, url);
			mHttp.get(url, new AsyncHttpResponseHandler() {

				@Override
				public void onSuccess(int statusCode, String content) {
					httpResponse1(content, action);

					LOGE(TAG, content);
				}

				@Override
				public void onFailure(int statusCode, Throwable error,
						String content) {
					httpError(error, action);
				}

			});
		}
	}

	protected void httpError(Throwable error, int action) {
		LOGE(TAG, error.getMessage());
	}

	private void httpResponse1(String json, int action) {
		LOGE(TAG, json);
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
		}
		Error error = null;
		if (json.contains("error")) {
			error = gson.fromJson(json, Error.class);
		}
		if (error != null) {
			switch (error.errorId) {
			case -1:
				showToast(R.string.error_1);
				break;
			case -2:
				showToast(R.string.error_2);
				break;
			case -3:
				showToast(R.string.error_3);
				break;
			case -4:
				showToast(R.string.error_4);
				break;
			case -5:
				showToast(R.string.error_5);
				break;
			case -6:
				showToast(R.string.error_6);
				break;
			case -7:
				showToast(R.string.error_7);
				break;
			default:
				break;
			}
		} else {
			httpResponse(json, action);
		}
	}

	protected void httpResponse(String json, int action) {

	}

	public ProgressDialog getDefaultDialog(int message) {
		ProgressDialog dialog = new ProgressDialog(this);
		dialog.setMessage(getString(message));
		return dialog;
	}

	protected void showToast(int messageId) {
		Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show();
	}

	protected void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	protected int getTotalPageCount() {
		if (totalCount % PAGE_SIZE == 0) {
			return totalCount / PAGE_SIZE;
		} else {
			return totalCount / PAGE_SIZE + 1;
		}
	}

	/**
	 * 设置界面布局文件ID
	 */
	protected abstract int centerLayoutId();

	/**
	 * 初始化界面控件
	 */
	protected abstract void initCenter(View centerView);

	/**
	 * 初始化界面数据
	 */
	protected abstract void initData();

	/**
	 * 初始化界面事件注册
	 */
	protected abstract void initAction();

	protected final void showRightView(boolean isShow, String text,
			OnClickListener listener) {
		bt_top_right.setVisibility(isShow ? View.VISIBLE : View.GONE);
		bt_top_right.setText(text);
		if (listener != null)
			bt_top_right.setOnClickListener(listener);
	}

	protected final void showLeftView(boolean isShow, OnClickListener listener) {
		bt_top_left.setVisibility(isShow ? View.VISIBLE : View.GONE);
		if (listener != null)
			bt_top_left.setOnClickListener(listener);
	}

	protected final void showTopMenu(boolean isShow) {
		template_top.setVisibility(isShow ? View.VISIBLE : View.GONE);
	}

	/**
	 * 打开新的Activity对象
	 */
	protected void startToActivity(Class cls, Bundle b) {
		Intent it = new Intent(this, cls);
		if (b != null)
			it.putExtras(b);
		startActivityForResult(it, 1);
	}

	/**
	 * 打开新的Activity对象
	 */
	protected void startToActivity(Class cls, Bundle b, int resquetCode) {
		Intent it = new Intent(this, cls);
		if (b != null)
			it.putExtras(b);
		startActivityForResult(it, resquetCode);
	}
	
	protected final void setTitleTextRes(String title)
	{
		this.title = title;
		if (screenHeight >= 1280) tvTitle.setTextSize(30);
	}

}
