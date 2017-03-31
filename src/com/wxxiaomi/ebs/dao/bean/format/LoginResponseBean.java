package com.wxxiaomi.ebs.dao.bean.format;

import java.io.Serializable;
import java.util.List;

import com.wxxiaomi.ebs.dao.bean.User;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;

public class LoginResponseBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private List<UserCommonInfo> friendList;
	private List<UserCommonInfo> blackList;
	public LoginResponseBean(User user, List<UserCommonInfo> friendList,
			List<UserCommonInfo> blackList) {
		super();
		this.user = user;
		this.friendList = friendList;
		this.blackList = blackList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<UserCommonInfo> getFriendList() {
		return friendList;
	}
	public void setFriendList(List<UserCommonInfo> friendList) {
		this.friendList = friendList;
	}
	public List<UserCommonInfo> getBlackList() {
		return blackList;
	}
	public void setBlackList(List<UserCommonInfo> blackList) {
		this.blackList = blackList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
