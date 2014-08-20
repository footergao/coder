package com.likou.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import com.likou.R;
import com.likou.activity.common.BaseFragmentActivity;



public class Welcome extends Activity {


	private AlphaAnimation start_anima;
	View view;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		view = View.inflate(this, R.layout.activity_main, null);
		setContentView(view);

		initView();
	}
	
	

	private void initView() {
		// 渐变展示启动屏
		start_anima = new AlphaAnimation(0.3f, 1.0f);
		start_anima.setDuration(2000);
		view.startAnimation(start_anima);
		start_anima.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				redirectTo();
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationStart(Animation animation) {
				
			}
		});

	}

	/**
	 * 转向
	 */
	protected void redirectTo() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
