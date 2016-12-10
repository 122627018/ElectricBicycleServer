package com.wxxiaomi.ebs.dao.bean.format;


import java.io.Serializable;

import com.wxxiaomi.ebs.dao.bean.User;


public class Format_Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User userInfo;
//	private List<UserCommonInfo> friendList;

	public Format_Login(User userInfo) {
		super();
		this.userInfo = userInfo;
//		this.friendList = friendList;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
//
//	public List<UserCommonInfo> getFriendList() {
//		return friendList;
//	}
//
//	public void setFriendList(List<UserCommonInfo> friendList) {
//		this.friendList = friendList;
//	}
	
	

}
