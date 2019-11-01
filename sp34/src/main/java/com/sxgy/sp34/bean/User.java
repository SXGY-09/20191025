package com.sxgy.sp34.bean;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	public User() {}
	private int id;
	private String loginName;
	private String username;
	private String password;
	public User(int id, String loginName, String username, String password) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.username = username;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + "]";
	}
}
