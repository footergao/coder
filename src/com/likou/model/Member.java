package com.likou.model;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 会员 实体类.
 * @author 董雪梅
 * @create 2014-3-4  下午04:56:46
 * @version 1.0
 */
@XStreamAlias("Member")
public class Member implements Serializable{
	
	private static final long serialVersionUID = -6443756786208050366L;
	
	/* 会员状态-正常 */
	public static final short STATE_NORMAL = 0;
	/* 会员状态-注销 */
	public static final short STATE_DISABLED = 1;
	
	/* 会员类型-商家会员 */
	public static final short MEMBERTYPE_SHOP = 1;
	/* 会员类型-普通会员 */
	public static final short MEMBERTYPE_COMMON = 2;
	
	/* 创建人类型-后台管理员 */
	public static final short CREATORTYPE_MANAGER = 1;
	/* 创建人类型-系统（注册的情况下） */
	public static final short CREATORTYPE_SYSTEM = 2;
	
	/* 最后修改人类型-后台管理员 */
	public static final short LASTMODIFIERTYPE_MANAGER = 1;
	/* 最后修改人类型-会员自己 */
	public static final short LASTMODIFIERTYPE_MEMBER = 2;
	
	/* 会员标识 */
	public int memberId;
   /* 会员状态，取值见常量 */
	public short state;
	/* 会员类型，取值见常量 */
	public short memberType;
	/* 会员等级，预留 */
	public Short memberLevel;
	/* 会员名 */
	public String memberName;
	/* 手机号 */
	public String telephone;
	/* 会员密码，md5加密 */
	public String memberPwd;
	/* 门店标识，当会员是商家会员时，此属性有值 */
	public Integer shopId;
	/* 创建时戳 */
	public int createdTimestamp;
	/* 创建人类型，取值见常量 */
	public short creatorType;
	/* 创建人标识 */
	public Long creatorId;
	/* 创建人姓名 */
	public String creatorName;
	/* 最后修改时戳 */
	public long lastModified;
	/* 最后修改人类型，取值见常量*/
	public Short lastModifierType;
	/* 最后修改人标识 */
	public int lastModifierId;
	/* 最后修改人姓名 */
	public String lastModifierName;
	//状态码
	public int stat;
	//验证码
	public String code;
	
	public String pwd;
	
}
