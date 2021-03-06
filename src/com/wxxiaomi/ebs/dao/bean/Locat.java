package com.wxxiaomi.ebs.dao.bean;

import java.util.Arrays;
import java.util.Date;

public class Locat {
	
	private int id;
	private Date time;
	private UserCommonInfo userCommonInfo;
	private double[] point;
	private String geo;
	
	
	
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public UserCommonInfo getUserCommonInfo() {
		return userCommonInfo;
	}
	public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
		this.userCommonInfo = userCommonInfo;
	}
	public double[] getPoint() {
		return point;
	}
	public void setPoint(double[] point) {
		this.point = point;
	}
	
	
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		this.geo = geo;
	}
	public Locat(UserCommonInfo userCommonInfo, double[] point) {
		super();
		this.userCommonInfo = userCommonInfo;
		this.point = point;
	}
	public Locat() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Locat [id=" + id + ", time=" + time + ", userCommonInfo="
				+ userCommonInfo.toString() + ", locat=" + Arrays.toString(point)
				+ ", geo=" + geo + "]";
	}
	
	
}
