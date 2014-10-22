package com.crazy.singleweb.entity;

import com.crazy.singleweb.util.StringUtil;

public class Menu extends BaseEntity {

	private int id;
	private String name;
	private String displayName;
	private String linkUrl;
	private String picUrl;
	private int type;
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"id\":").append(id);
		sb.append(",\"name\":\"").append(StringUtil.fixJsonStr(name))
				.append("\"");
		sb.append(",\"type\":").append(type).append("");
		sb.append(",\"displayName\":\"")
				.append(StringUtil.fixJsonStr(displayName)).append("\"");
		sb.append(",\"linkUrl\":\"").append(StringUtil.fixJsonStr(linkUrl))
				.append("\"");
		sb.append(",\"picUrl\":\"").append(StringUtil.fixJsonStr(picUrl))
				.append("\"");
		sb.append(",\"remark\":\"").append(StringUtil.fixJsonStr(remark))
				.append("\"");
		return sb.toString();
	}

}
