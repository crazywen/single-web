package com.crazy.singleweb.entity;

import java.util.Date;

import com.crazy.singleweb.util.DateUtil;
import com.crazy.singleweb.util.StringUtil;
import com.crazy.singleweb.util.DateUtil.DateTimeType;

public class Entity extends BaseEntity {
	private int id;
	private String name;
	private Date createTime;
	private int type;
	private String url;
	private String linkUrl;
	private String tag;

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toJSON() {
		StringBuilder sb = new StringBuilder();
		sb.append("{\"id\":").append(id);
		sb.append(",\"name\":\"").append(StringUtil.fixJsonStr(name))
				.append("\"");
		sb.append(",\"type\":").append(type).append("");
		sb.append(",\"createTime\":\"")
				.append(DateUtil.format(createTime, DateTimeType.DateTime))
				.append("\"");
		sb.append(",\"linkUrl\":\"").append(StringUtil.fixJsonStr(linkUrl))
				.append("\"");
		sb.append(",\"url\":\"").append(StringUtil.fixJsonStr(url))
				.append("\"");
		sb.append(",\"tag\":\"").append(StringUtil.fixJsonStr(tag))
				.append("\"");
		sb.append("}");
		return sb.toString();
	}

}
