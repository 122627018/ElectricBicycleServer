package com.wxxiaomi.ebs.dao.bean;

import java.util.Date;

public class Option {

	private int id;
	private int user_id;
	private int obj_type;
	private int obj_id;
	private int parent_type;
	private int parent_id;
	private int type;
	private Date create_time;
	private String attch;
	
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getObj_type() {
		return obj_type;
	}
	public void setObj_type(int obj_type) {
		this.obj_type = obj_type;
	}
	public int getObj_id() {
		return obj_id;
	}
	public void setObj_id(int obj_id) {
		this.obj_id = obj_id;
	}
	public int getParent_type() {
		return parent_type;
	}
	public void setParent_type(int parent_type) {
		this.parent_type = parent_type;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getAttch() {
		return attch;
	}
	public void setAttch(String attch) {
		this.attch = attch;
	}
	
	
	
	
	
}
