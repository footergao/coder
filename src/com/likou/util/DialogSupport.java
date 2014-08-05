package com.likou.util;

import com.likou.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;


/**
 * 通用对话框支持类
 * 
 */
public class DialogSupport
{
	/**
	 * 弹出alert，有确定按钮，点击确定按钮关闭改alert框
	 * 
	 * @param context
	 * @param title
	 * @param message
	 */
	public static void alert(Context context, String title, String message)
	{
		alert(context, title, message, null);
	}

	/**
	 * 弹出alert，有确定按钮，点击确定按钮响应onClickOkListener
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param onClickOkListener
	 */
	public static void alert(Context context, String title, String message,
		DialogInterface.OnClickListener onClickOkListener)
	{
		BitmapDrawable drawable = null;
		try
		{
			drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_launcher);
			AlertDialog dialog = new AlertDialog.Builder(context).setIcon(drawable).setTitle(title)
				.setMessage(message).setPositiveButton(R.string.button_ok, onClickOkListener)
				.create();
			dialog.show();
		} catch (OutOfMemoryError e)
		{
			Log.e("error", e.getMessage(), e);
			new AlertDialog.Builder(context).setMessage(message)
				.setPositiveButton(R.string.button_ok, onClickOkListener).create().show();
		}
	}

	/**
	 * 弹出alert，有确定按钮，点击确定按钮关闭改alert框
	 * 
	 * @param activity
	 * @param title
	 * @param message
	 * @param finishActivity
	 *            如果是true就会返回到前一个activity
	 */
	public static void alert(final Activity activity, String title, String message,
		boolean finishActivity)
	{
		DialogInterface.OnClickListener l = null;
		if (finishActivity)
		{
			l = new DialogInterface.OnClickListener()
			{
				public void onClick(DialogInterface dialog, int whichButton)
				{
					activity.finish();
				}
			};
		}
		alert(activity, title, message, l);
	}

	/**
	 * 弹出confim对话框
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param onClickOkListener
	 *            “确定”按钮的响应事件
	 */
	public static void confirm(Context context, String title, String message,
		DialogInterface.OnClickListener onClickOkListener)
	{
		confirm(context, title, message, onClickOkListener, null);
	}

	/**
	 * 弹出confim对话框
	 * 
	 * @param context
	 * @param title
	 * @param message
	 * @param onClickOkListener
	 *            “确定”按钮的响应事件
	 * @param onClickCancelListener
	 *            “取消”按钮的响应事件
	 */
	public static void confirm(Context context, String title, String message,
		DialogInterface.OnClickListener onClickOkListener,
		DialogInterface.OnClickListener onClickCancelListener)
	{
		BitmapDrawable drawable = null;
		try
		{
			drawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.ic_launcher);
			AlertDialog dialog = new AlertDialog.Builder(context).setIcon(drawable).setTitle(title)
				.setMessage(message).setPositiveButton(R.string.button_ok, onClickOkListener)
				.setNegativeButton(R.string.button_cancel, onClickCancelListener).create();
			dialog.show();
		} catch (OutOfMemoryError e)
		{
			Log.e("error", e.getMessage(), e);
			new AlertDialog.Builder(context).setMessage(message)
				.setPositiveButton(R.string.button_ok, onClickOkListener)
				.setNegativeButton(R.string.button_cancel, onClickCancelListener).create().show();
		}
	}

}
