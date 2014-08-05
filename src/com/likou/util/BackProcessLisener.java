package com.likou.util;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/**
 *  响应返回事件，比如点击“取消”按钮时
 * @author liujia
 *
 */
public class BackProcessLisener implements OnClickListener
{
	private Activity	activity;

	public BackProcessLisener(Activity activity)
	{
		this.activity = activity;
	}

	public void onClick(View v)
	{
		activity.onBackPressed();
	}

}
