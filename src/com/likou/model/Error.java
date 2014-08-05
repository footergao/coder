package com.likou.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * api 出错返回实体类
 * 
 */
@XStreamAlias("Error")
public class Error implements Serializable {

	private static final long serialVersionUID = -1209615847886642479L;

	public static final int ERROR_PARTNER_CODE = -1;// 参数partner错误
	public static final int ERROR_INPUTCHARSET_CODE = -2;// 参数partner错误
	public static final int ERROR_SIGN_CODE = -3;// sign错误
	public static final int ERROR_USER_CODE = -4;// 用户名不存在或密码错误
	public static final int ERROR_EXCEPTION_CODE = -5;// 方法捕获到异常

	public int errorId; // 错误代码
}
