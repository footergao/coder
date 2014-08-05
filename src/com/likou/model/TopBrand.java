package com.likou.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * APP品牌城 实体类 继承Shop类
 * 
 * @author 董雪梅
 * @create 2014-3-5 上午10:12:35
 * @version 1.0
 */
@XStreamAlias("TopBrand")
public class TopBrand extends Shop {

	private static final long serialVersionUID = -1705852499348046150L;
	/* 在品牌城中的序号 */
	public short topOrderNo;
	/* 最后操作人标识 */
	public long lastOperatorId;
	/* 最后操作人姓名 */
	public String lastOperatorName;
	/* 最后操作时戳 */
	public int lastOperatedTimestamp;

}
