package com.likou.activity.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.likou.R;
import com.likou.activity.main.Welcome;

/**
 * 
 * @author xujm
 * 
 */
public abstract class BaseActivity extends Activity {
	protected int screenHeight;
	protected int screenWidth;
	protected Button btLeft;
	protected Button btRight;
	protected TextView btCenter, tv_back;
	private int titleTextResId;
	protected View centerView;
	private LinearLayout btm_layout, back_home_layout;
	private LinearLayout loadingLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		ActivityCollector.registry(this);
		setContentView(R.layout.base_layout);
		WindowManager manage = getWindowManager();
		Display display = manage.getDefaultDisplay();
		screenHeight = display.getHeight();
		screenWidth = display.getWidth();
		init();
		setButtonAction();
		LinearLayout center = (LinearLayout) findViewById(R.id.base_center);
		LayoutInflater lInflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		centerView = lInflater.inflate(centerLayoutId(), null, true);
		center.addView(centerView);
		initCenter(center);
		if (titleTextResId != 0) {
			btCenter.setText(titleTextResId);
		}
		initData();
		initAction();
	}


	/**
	 * 设置每个继承的Activity子类的标题
	 * 
	 * @param resId
	 */
	protected final void setTitleTextRes(int resId) {
		this.titleTextResId = resId;
	}

	/**
	 * 是否显示菜单右方标签
	 * 
	 * @param isShow
	 *            true或false,是否显示有方标签,默认false不显示
	 * @param listener
	 *            标签的点击事件
	 */
	protected final void showRightBut(boolean isShow, OnClickListener listener,
			int res) {
		btRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
		btRight.setBackgroundDrawable(getResources().getDrawable(res));
		if (listener != null)
			btRight.setOnClickListener(listener);
	}

	protected final void showRightBut(boolean isShow, OnClickListener listener) {
		btRight.setVisibility(isShow ? View.VISIBLE : View.GONE);
		if (listener != null)
			btRight.setOnClickListener(listener);
	}

	/**
	 * 左侧图片更改
	 * 
	 * @param res
	 */
	protected final void changeLeftBut(int res) {
		btLeft.setBackgroundDrawable(getResources().getDrawable(res));
	}

	/**
	 * 是否显示下方菜单栏
	 * 
	 * @param isShow
	 *            true或false,是否显示下方菜单栏,默认true显示
	 */

	protected final void showBottomMenu(boolean isShow, int flag) {
		btm_layout.setVisibility(isShow ? View.VISIBLE : View.GONE);
		if (flag == 1) {
			tv_back.setText("进入商家");
			back_home_layout.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					// finish();
				}
			});
		} else {
			back_home_layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent();
					i.setClass(BaseActivity.this, Welcome.class);
					i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}
			});
		}

	}

	/**
	 * 控件初始化
	 */
	private void init() {
		btCenter = (TextView) findViewById(R.id.top_btn_center);
		btLeft = (Button) findViewById(R.id.top_btn_left);
		btRight = (Button) findViewById(R.id.top_btn_right);
		btm_layout = (LinearLayout) findViewById(R.id.bottom_but);
		tv_back = (TextView) findViewById(R.id.tv_back);// 底部返回
		back_home_layout = (LinearLayout) findViewById(R.id.back_home_layout);
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

	/**
	 * 设置默认按钮事件
	 */
	private void setButtonAction() {
		btLeft.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onBackPressed();
			}
		});
		btRight.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

			}
		});

	}

//	/**
//	 * 窗口关闭
//	 */
//	protected void close() {
//		ActivityCollector.deRegistry(this);
//	}

	/**
	 * 获取窗体宽度
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * 获取窗体高度
	 */
	public int getScreenHeight() {
		return screenHeight;
	}
}
