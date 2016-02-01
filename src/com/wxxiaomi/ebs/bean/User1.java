package com.wxxiaomi.ebs.bean;

public class User1 {

	private int id;
	private String username;
	private String password;
	private String head;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public User1(int id, String username, String password, String head) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.head = head;
	}
	public User1(int id, String name, String head) {
		super();
		this.id = id;
		this.head = head;
		this.name = name;
	}
	
	
}
