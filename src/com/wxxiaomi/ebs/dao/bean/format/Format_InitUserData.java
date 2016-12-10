package com.wxxiaomi.ebs.dao.bean.format;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;



public class Format_InitUserData {

	private List<UserCommonInfo> friendList;

	public List<UserCommonInfo> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<UserCommonInfo> friendList) {
		this.friendList = friendList;
	}

	public Format_InitUserData(List<UserCommonInfo> friendList) {
		super();
		this.friendList = friendList;
	}
	
	
	
	
}
