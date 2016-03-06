package com.wxxiaomi.ebs.bean.format;


import com.wxxiaomi.ebs.bean.User;

public class Format_Login {

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
