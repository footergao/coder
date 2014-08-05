package com.likou.model;

import java.io.Serializable;
import java.util.List;

import com.j256.ormlite.field.DatabaseField;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @version 1.0
 */
@XStreamAlias("Shop")
public class Shop implements Serializable, Comparable<Shop> {

	private static final long serialVersionUID = 3407393130785282756L;

	public static final short SHOP_STATE_NEW = 0;// 新建
	public static final short SHOP_STATE_DELETED = 9;// 删除

	/* 操作员类型-网站管理员 */
	public static final short OPERATORTYPE_WEBMANAGER = 1;
	/* 操作员类型-商家 */
	public static final short OPERATORTYPE_SHOPMANAGER = 2;

	/* 商家等级-认证商家 */
	public static final short SHOPLEVEL_AUTHENTICATION = 1;
	/* 商家等级-钻石认证商家 */
	public static final short SHOPLEVEL_DIAMONDAUTH = 2;
	/* 商家等级-皇冠认证商家 */
	public static final short SHOPLEVEL_CROWNAUTH = 3;

	/* 门店标识 */
	@DatabaseField(id = true)
	public int shopId;
	/* 状态，取值见常量 */
	public short state;
	/* 广场标识 */
	public int buildingId;
	/* 广场名称，数据库表不存储，页面显示使用 */
	public String buildingName;
	/* 风格标识 */
	public int styleId;
	/* 风格名称，数据库表不存储，页面显示使用 */
	public String styleName;
	/* 门店名称 */
	@DatabaseField
	public String shopName;
	/* 门店等级，取值见常量 */
	@DatabaseField
	public int shopLevel;
	/* 品牌名称 */
	public String brandName;
	/* 品牌LOGO/店铺主图 */
	@DatabaseField
	public String brandLogo;
	/* 门店地址 */
	public String shopAddress;
	/* 门店电话 */
	public String tel;
	/* X坐标 */
	public double coordinateX;
	/* Y坐标 */
	public double coordinateY;
	/* 收藏数 */
	public int collectNum;
	/* 在广场内的序号，>0表示为广场推荐门店，序号越大，排名越前 */
	public int buildingOrderNo;
	/* 创建时戳 */
	public int createdTimestamp;
	/* 创建人类型，取值见常量 操作员类型 */
	public short creatorType;
	/* 创建人标识 */
	public int creatorId;
	/* 创建人姓名 */
	public String creatorName;
	/* 最后修改时戳 */
	public int lastModified;
	/* 最后修改人类型，取值见常量 操作员类型 */
	public short lastModifierType;
	/* 最后修改人标识 */
	public int lastModifierId;
	/* 最后修改人姓名 */
	public String lastModifierName;

	@DatabaseField
	public long collectTime;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + shopId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (shopId != other.shopId)
			return false;
		return true;
	}

	@Override
	public int compareTo(Shop another) {
		return (int) (another.collectTime - collectTime);
	}

}
