package com.likou.model;

import java.io.Serializable;

public class VersionInfo implements Serializable{

	private static final long serialVersionUID = -5747805927251078889L;
	
	public int versionInfoId;// 客户端版本信息标识
	public String apkVersion;// 版本号
	public String apkVersionName;// 版本号名字
	public String apkSize;// 版本大小
	public String apkPath;// 客户端下载地址

}
