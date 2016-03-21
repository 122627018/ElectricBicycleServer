package com.wxxiaomi.ebs.bean;

public class User {

	private int id;
	private String username;
	private String password;
	private UserCommonInfo userCommonInfo;
	
	
	
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



	public static class UserCommonInfo{
		private int userid;
		private String name;
		private String head;
		private String emname;
		
		public String getEmname() {
			return emname;
		}
		public void setEmname(String emname) {
			this.emname = emname;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getHead() {
			return head;
		}
		public void setHead(String head) {
			this.head = head;
		}
		public int getUserid() {
			return userid;
		}
		public void setUserid(int userid) {
			this.userid = userid;
		}
		public UserCommonInfo(int userid, String name, String head,String emname) {
			super();
			this.userid = userid;
			this.name = name;
			this.head = head;
			this.emname = emname;
		}
		
		
		
	}
	
	
	
	
	
	
}
