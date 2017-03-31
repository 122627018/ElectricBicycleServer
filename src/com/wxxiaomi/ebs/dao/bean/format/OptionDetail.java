package com.wxxiaomi.ebs.dao.bean.format;

import java.util.Date;

public class OptionDetail {
//attch,option_tag,reply不会封装到json中
	private int id;
	private int userid;
	private String nickname;
	private String avatar;
	private Date create_time;
	//xxx 评论了/发表了/
	private String option_tag; 
	
	
	private String picture;
	private String content;
	//脚view
//	private String foot;
	private int reply_count;
	private int like_count;
	private String locat_tag="";
	//如果是回复的操作，那么这里显示回复的内存
	private String reply;
	//类型
	private int type;
	//目标id
	private int obj_id;
	//附加数据，存放地址信息或者图片地址
	private String attch;
	private int pnt_id;
	
	public int getPnt_id() {
		return pnt_id;
	}
	public void setPnt_id(int pnt_id) {
		this.pnt_id = pnt_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getReply_count() {
		return reply_count;
	}
	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public String getLocat_tag() {
		return locat_tag;
	}
	public void setLocat_tag(String locat_tag) {
		this.locat_tag = locat_tag;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getOption_tag() {
		return option_tag;
	}
	public void setOption_tag(String option_tag) {
		this.option_tag = option_tag;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getObj_id() {
		return obj_id;
	}
	public void setObj_id(int obj_id) {
		this.obj_id = obj_id;
	}
	public String getAttch() {
		return attch;
	}
	public void setAttch(String attch) {
		this.attch = attch;
	}
	public OptionDetail(int id, int userid,
			Date create_time, String picture,
			String content, int reply_count, int like_count, String locat_tag,
			 int pnt_id,int obj_id,int type) {
		super();
		this.id = id;
		this.userid = userid;
//		this.nickname = nickname;
//		this.avatar = avatar;
		this.create_time = create_time;
//		this.option_tag = option_tag;
		this.picture = picture;
		this.content = content;
		this.reply_count = reply_count;
		this.like_count = like_count;
		this.locat_tag = locat_tag;
//		this.reply = reply;
		this.type = type;
		this.pnt_id = pnt_id;
		this.obj_id = obj_id;
//		this.attch = attch;
	}
	@Override
	public String toString() {
		return "OptionDetail [id=" + id + ", userid=" + userid + ", nickname="
				+ nickname + ", avatar=" + avatar + ", create_time="
				+ create_time + ", option_tag=" + option_tag + ", picture="
				+ picture + ", content=" + content + ", reply_count="
				+ reply_count + ", like_count=" + like_count + ", locat_tag="
				+ locat_tag + ", reply=" + reply + ", type=" + type
				+ ", obj_id=" + obj_id + ", attch=" + attch + "]";
	}
	
	
}
