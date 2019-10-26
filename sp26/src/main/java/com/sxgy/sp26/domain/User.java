package com.sxgy.sp26.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private String username;
	private MultipartFile headPortrait;
	public User() {
		super();
	}
	public User(String username, MultipartFile headPortrait) {
		super();
		this.username = username;
		this.headPortrait = headPortrait;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public MultipartFile getHeadPortrait() {
		return headPortrait;
	}
	public void setHeadPortrait(MultipartFile headPortrait) {
		this.headPortrait = headPortrait;
	}
	
}
