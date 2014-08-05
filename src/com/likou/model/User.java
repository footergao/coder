package com.likou.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("User")
public class User implements Serializable{
	
	private static final long serialVersionUID = 3878817383983701841L;
	
	public static final int USER_STATUS_ENABLE	= 0x0000;//可用
	public static final int USER_STATUS_DISABLE	= 0x0001;//禁用
	public static final int USER_STATUS_DELETE	= 0x0002;//删除
	
	public static final short USER_ISDIRECTOR_NO  = 0;//不是公司负责人
	public static final short USER_ISDIRECTOR_YES = 1;//是公司负责人
	
	public int userId;//用户标识
	public int status;//状态
	public String loginName;//登录名
	public String userName;//用户姓名
	public String password;//密码
	public Integer organizationId;//组织标识
	public Integer provinceId;
	public Integer city;//城市标识
	public int gender;//性别
	public String email;//电子邮件
	public String mobilePhone;//手机号码
	public int failNum;//错误密码次数
	public String creatorId;//创建人标识
	public String creatorName;//创建人姓名
	public int createdTimestamp;//创建时间
	public int lastModified;//最后修改时间
	
	public int companyId;//公司标识
	public short isDirector;//是否负责人
	
	//冗余
	public String cityName;//所属城市名称（冗余）
	public String organizationName;//组织名称（冗余）
	public int roleId;//角色标识
	public String roleName;//角色名
	public String companyName;//公司名称
	public Integer pid;//组织的父级
	public int puid;//上级用户标识
	public String pusername;//上级用户名
	
}
