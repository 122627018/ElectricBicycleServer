package com.wxxiaomi.ebs.bean;

public class Bicycle {

	private int id;
	private int typeid;
	private int isbund;
	private int userid;
	private int version;
	private String name;
	private String size;
	private String batterysize;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getIsbund() {
		return isbund;
	}
	public void setIsbund(int isbund) {
		this.isbund = isbund;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getBatterysize() {
		return batterysize;
	}
	public void setBatterysize(String batterysize) {
		this.batterysize = batterysize;
	}
	public Bicycle(int typeid, int isbund, int userid, int version,
			String name, String size, String batterysize) {
		super();
		this.typeid = typeid;
		this.isbund = isbund;
		this.userid = userid;
		this.version = version;
		this.name = name;
		this.size = size;
		this.batterysize = batterysize;
	}
	
	
}
