package com.wxxiaomi.ebs.bean;

public class UserCommonInfo {
	public String name;
	public String head;
	public String emname;
	public int id;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserCommonInfo() {
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

	public String getEmname() {
		return emname;
	}

	public void setEmname(String emname) {
		this.emname = emname;
	}

	@Override
	public String toString() {
		return "UserCommonInfo [name=" + name + ", head=" + head + ", emname="
				+ emname + ", id=" + id + "]";
	}

	
	
}