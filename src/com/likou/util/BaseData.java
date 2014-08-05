package com.likou.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.likou.model.User;

import android.graphics.Bitmap;


public class BaseData {
	 public static int topbar_Height =0;
	  public static int mobile_Height = 0;
	  public static int mobile_Width = 0;
	  public static final int TopBarPer=16;//设置topbar按屏幕百分比
	  
	  public static String UserName;//姓名
	  public static String LoginUserName;
	  public static String LoginUserPwd;
	  
	  public static final String CHARSET = "utf-8"; //字符集
	  
	  public static final int TASK_POST = 1;
	  public static final int TASK_GET = 2;
	  public static final int TASK_IMG = 3;
	  public static final int TASK_GET_LIST = 4;
	  
	  public static final String partner = "2088701017765457";
	  
	  public static ArrayList<ArrayList<String>> PlanList = new ArrayList<ArrayList<String>>();
	  public static int PlanIndex = 0;
	  
	  public static String encryptKey = "c42zbged";//DES.encryptDES(sign,encryptKey);
	  
	  private static User currentUser;
	  
	  public static String returnValue = "";
	  public static Map returnMap = new HashMap<String,String>();
	  public static Map returnImgMap = new HashMap<String,Bitmap>();

	  
	  public static void setUser(User currentuser)
	  {
		  currentUser = currentuser;
	  }
	  
	  public static User getUser()
	  {
		  if(currentUser != null)
			  return currentUser;
		  else
			  return null;
	  }
	  
	  //服务器返回结果
	  public static void setReturnValue(String value)
	  {
		  returnValue = value;
	  }
	  
	  public static String getReturnValue()
	  {
		  return returnValue; 
	  }
	  
	  public static void setReturnMap(Map value)
	  {
		  returnMap = value;
	  }
	  
	  public static Map getReturnMap()
	  {
		  return returnMap; 
	  }
	  
	  public static void setReturnImgMap(Map value)
	  {
		  returnImgMap = value;
	  }
	  
	  public static Map getReturnImgMap()
	  {
		  return returnImgMap; 
	  }

}
