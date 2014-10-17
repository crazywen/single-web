package com.crazy.singleweb.util;

public class DynamicParam {

	private Object ext;
	/** 是否降序 */
	private boolean isDesc;

	/** 分页类 */
	private PageInfo pi;

	public Object getExt() {
		return ext;
	}

	public void setExt(Object ext) {
		this.ext = ext;
	}

	public boolean isDesc() {
		return isDesc;
	}

	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}

	public PageInfo getPi() {
		return pi;
	}

	public void setPi(PageInfo pi) {
		this.pi = pi;
	}

}
