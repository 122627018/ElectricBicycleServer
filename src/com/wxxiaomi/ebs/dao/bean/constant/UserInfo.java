package com.wxxiaomi.ebs.dao.bean.constant;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.dao.bean.format.OptionDetail;

public class UserInfo  {

	private UserCommonInfo userCommonInfo;
	private List<OptionDetail> options;
	public UserInfo() {
		super();
	}
	
	public UserCommonInfo getUserCommonInfo() {
		return userCommonInfo;
	}

	public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
		this.userCommonInfo = userCommonInfo;
	}

	public List<OptionDetail> getOptions() {
		return options;
	}

	public void setOptions(List<OptionDetail> options) {
		this.options = options;
	}

	public UserInfo(UserCommonInfo userCommonInfo, List<OptionDetail> options) {
		super();
		this.userCommonInfo = userCommonInfo;
		this.options = options;
	}
	@Override
	public String toString() {
		return "UserInfo [userCommonInfo=" + userCommonInfo.toString() + ", options="
				+ options.size() + "]";
	}
	
	
}
