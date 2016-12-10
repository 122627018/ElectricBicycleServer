package com.wxxiaomi.ebs.dao.bean;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String username;
	private String password;
	private UserCommonInfo userCommonInfo;
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", userCommonInfo=" + userCommonInfo + "]";
	}



	public User() {
		super();
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public UserCommonInfo getUserCommonInfo() {
		return userCommonInfo;
	}



	public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
		this.userCommonInfo = userCommonInfo;
	}



	public User(UserCommonInfo userCommonInfo) {
		super();
		this.userCommonInfo = userCommonInfo;
	}



	public User(int id, String username, String password,
			UserCommonInfo userCommonInfo) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.userCommonInfo = userCommonInfo;
	}



	
	
	
	
	
}
