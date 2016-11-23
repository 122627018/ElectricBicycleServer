package com.wxxiaomi.ebs.bean;

import java.util.Date;


public class Topic {
	private int id;
	private UserCommonInfo userCommonInfo;
	private String content;
	private Date time;
	private String pics;
	private String title;
	private int hot;
	private int ccount;
	private String locat;
	private String locat_tag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public UserCommonInfo getUserCommonInfo() {
		return userCommonInfo;
	}
	public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
		this.userCommonInfo = userCommonInfo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public int getCcount() {
		return ccount;
	}
	public void setCcount(int ccount) {
		this.ccount = ccount;
	}
	public String getLocat() {
		return locat;
	}
	public void setLocat(String locat) {
		this.locat = locat;
	}
	public String getLocat_tag() {
		return locat_tag;
	}
	public void setLocat_tag(String locat_tag) {
		this.locat_tag = locat_tag;
	}
	public Topic() {
		super();
	}
	@Override
	public String toString() {
		return "Topic [id=" + id + ", userCommonInfo=" + userCommonInfo
				+ ", content=" + content + ", time=" + time + ", pics=" + pics
				+ ", title=" + title + ", hot=" + hot + ", ccount=" + ccount
				+ ", locat=" + locat + ", locat_tag=" + locat_tag + "]";
	}
	
	
	
	

}
