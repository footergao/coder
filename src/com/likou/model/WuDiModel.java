package com.likou.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("WuDiModel")
public class WuDiModel implements Serializable {

	private static final long serialVersionUID = 912021868994431680L;

	private ArrayList<?> wuDiList;
	private int lastTime;// 当前时间
	private int CurrentPage;// 当前页
	private int PageSize;// 页数
	private int TotalCount;// 总条数
	private int TotalPageCount;// 总页数

	public ArrayList<?> getWuDiList() {
		return wuDiList;
	}

	public void setWuDiList(ArrayList<?> wuDiList) {
		this.wuDiList = wuDiList;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public int getCurrentPage() {
		return CurrentPage;
	}

	public void setCurrentPage(int currentPage) {
		CurrentPage = currentPage;
	}

	public int getPageSize() {
		return PageSize;
	}

	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}

	public int getTotalCount() {
		return TotalCount;
	}

	public void setTotalCount(int totalCount) {
		TotalCount = totalCount;
	}

	public int getTotalPageCount() {
		return TotalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		TotalPageCount = totalPageCount;
	}

}
