package com.likou.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.likou.R;


import android.app.Activity;
import android.util.Log;

public class ModulePropertiesUtil
{
	private static final String TAG = ModulePropertiesUtil.class.getName();
	private static Properties moduleProperties = new Properties();

	/**
	 * 初始化配置文件
	 * @param activity
	 */
	public static void initPropertie(Activity activity)
	{
		try
		{
			InputStream in = activity.getAssets().open("module.properties"); 
			moduleProperties.load(in);
		} catch (IOException e)
		{
			Log.e(TAG, e.toString(), e);
		}
	}

	public static Set<String> allModules()
	{
		Set<String> modules = new HashSet<String>();
		Set<Object> moduleKeys = moduleProperties.keySet();
		for (Object keyObject : moduleKeys)
		{
			String key = ((String) keyObject).trim();
			if (key != null && key.endsWith(".name"))
			{
				String module = key.substring(0, key.lastIndexOf(".name"));
				modules.add(module);
			}
		}

		return modules;
	}

	/**
	 * 图片的resId找不到则抛异常
	 * 
	 * @param module
	 * @return
	 * @throws Exception
	 *             没有相应的图片
	 */
	public static int moduleImageId(String module) throws Exception
	{
		String name = findByModuleSuffix(module, ".image");
		if (name == null || name.trim().equals(""))
		{
			Log.e(TAG, "not found in config file");
		} else
		{
			Field field = R.drawable.class.getField(name);
			if (field != null)
			{
				int id = field.getInt(null);
				return id;
			}
		}
		throw new Exception("没有相应的图片");
	}

	public static String moduleActionName(String module)
	{
		return findByModuleSuffix(module, ".action");
	}

	public static String moduleName(String module)
	{
		return findByModuleSuffix(module, ".name");
	}

	public static String moduleUri(String module)
	{
		return findByModuleSuffix(module, ".uri");
	}

	public static String modulePosition(String module)
	{
		return findByModuleSuffix(module, ".position");
	}

	public static String moduleIcon(String module)
	{
		return findByModuleSuffix(module, ".image");
	}

	private static String findByModuleSuffix(String module, String suffix)
	{
		String result = null;
		if (module != null)
		{
			result = (String) moduleProperties.get(module.trim() + suffix);
		}
		return result;
	}
}
