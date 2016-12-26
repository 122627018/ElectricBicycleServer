package com.wxxiaomi.ebs.dao.bean;

public class FootPrint {

	private int id;
	private Locat locat;
	private int userid;
	private String content;
	private String picture;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Locat getLocat() {
		return locat;
	}
	public void setLocat(Locat locat) {
		this.locat = locat;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	@Override
	public String toString() {
		return "FootPrint [id=" + id + ", locat=" + locat + ", userid="
				+ userid + ", content=" + content + ", picture=" + picture
				+ "]";
	}
	
	
}
