package com.likou.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;  
import android.app.Application;
public class ExitApplication  extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();
	private static ExitApplication instance;
	//单例模式中获取唯一的ExitApplication 实例
	private ExitApplication()
    {
	}
	//单例实现返回MyApplication实例  
    public static ExitApplication getInstance(){  
        if(null == instance){  
            instance = new ExitApplication();  
        }  
        return instance;  
    }  
    
    //Activity加入到List中  
    public void addActivity(Activity activity){  
        activityList.add(activity);  
    }  
      
    //遍历每个Activity退出  
    public void exit(){  
        for(Activity activity:activityList){  
            activity.finish();  
        }  
        System.exit(0);  
    }  
}  