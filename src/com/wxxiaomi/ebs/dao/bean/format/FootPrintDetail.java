package com.wxxiaomi.ebs.dao.bean.format;

import java.util.Arrays;
import java.util.Date;

import com.wxxiaomi.ebs.util.GeoHashUtil;

public class FootPrintDetail {

	private int userid;
	private int foot_id;
	private int locat_id;
	private double[] points;
	private String content;
	private String picture;
	private Date create_time;
	private String geo;
	
	
	
	
	public FootPrintDetail(int userid, int foot_id, int locat_id,
			String content, String picture, Date create_time, String geo) {
		super();
		this.userid = userid;
		this.foot_id = foot_id;
		this.locat_id = locat_id;
		this.content = content;
		this.picture = picture;
		this.create_time = create_time;
		this.geo = geo;
		this.points = GeoHashUtil.decode(geo);
	}
	@Override
	public String toString() {
		return "FootPrintDetail [userid=" + userid + ", foot_id=" + foot_id
				+ ", locat_id=" + locat_id + ", points="
				+ Arrays.toString(points) + ", content=" + content
				+ ", picture=" + picture + ", create_time=" + create_time
				+ ", geo=" + geo + "]";
	}
	public String getGeo() {
		return geo;
	}
	public void setGeo(String geo) {
		points = GeoHashUtil.decode(geo);
		this.geo = geo;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getFoot_id() {
		return foot_id;
	}
	public void setFoot_id(int foot_id) {
		this.foot_id = foot_id;
	}
	public int getLocat_id() {
		return locat_id;
	}
	public void setLocat_id(int locat_id) {
		this.locat_id = locat_id;
	}
	public double[] getPoints() {
		return points;
	}
	public void setPoints(double[] points) {
		this.points = points;
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
	
	
}
