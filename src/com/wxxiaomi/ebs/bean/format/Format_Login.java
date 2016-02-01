package com.wxxiaomi.ebs.bean.format;

import com.wxxiaomi.ebs.bean.User1;

public class Format_Login {

	private User1 userInfo;

	public Format_Login(User1 userInfo) {
		super();
		this.userInfo = userInfo;
	}

	public User1 getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User1 userInfo) {
		this.userInfo = userInfo;
	}

}
