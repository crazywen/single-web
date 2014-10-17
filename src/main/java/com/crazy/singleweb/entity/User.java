package com.crazy.singleweb.entity;

/**
 * 用户实体类
 * 
 * @author liujiawen
 * 
 */
public class User {

	private int id;
	private String name;
	private String account;
	private String pwd;
	private int type;
	private String email;
	private String phone;
	private String address;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", account=" + account
				+ ", pwd=" + pwd + ", type=" + type + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", remark="
				+ remark + "]";
	}

}
