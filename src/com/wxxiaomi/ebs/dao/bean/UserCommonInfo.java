package com.wxxiaomi.ebs.dao.bean;

import java.util.Date;

public class UserCommonInfo {
	private int id;
	private String nickname;
	private String avatar;
	private String emname;
	private int album_id;
	private Date update_time;
	private Date create_time;
	private String description;
	private String city;
	private int sex;
	private String cover;
	
	
	
	
	public UserCommonInfo() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getEmname() {
		return emname;
	}
	public void setEmname(String emname) {
		this.emname = emname;
	}
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public UserCommonInfo(int id, String nickname, String avatar,
			String emname, Date update_time, Date create_time,
			String description, String city, int sex, String cover) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.avatar = avatar;
		this.emname = emname;
		this.album_id = 0;
		this.update_time = update_time;
		this.create_time = create_time;
		this.description = description;
		this.city = city;
		this.sex = sex;
		this.cover = cover;
	}
	
	
	
	
	


	
	
}