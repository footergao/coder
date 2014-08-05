package com.likou.model;

import java.io.Serializable;
import java.util.Date;

import com.j256.ormlite.field.DatabaseField;

public class HttpCache implements Serializable, Comparable<HttpCache> {

	private static final long serialVersionUID = -7001114998767147508L;
	@DatabaseField(id = true)
	public String url;
	@DatabaseField
	public String content;
	@DatabaseField
	public long createTime = new Date().getTime();

	@Override
	public int compareTo(HttpCache another) {
		return (int) (createTime - another.createTime);
	}

}
