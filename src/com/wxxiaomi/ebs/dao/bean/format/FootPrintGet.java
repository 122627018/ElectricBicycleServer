package com.wxxiaomi.ebs.dao.bean.format;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;

public class FootPrintGet {

	private  List<FootPrintDetail> footPrints;
	private UserCommonInfo userInfo;
	public FootPrintGet(List<FootPrintDetail> footPrints,
			UserCommonInfo userInfo) {
		super();
		this.footPrints = footPrints;
		this.userInfo = userInfo;
	}
	public List<FootPrintDetail> getFootPrints() {
		return footPrints;
	}
	public void setFootPrints(List<FootPrintDetail> footPrints) {
		this.footPrints = footPrints;
	}
	public UserCommonInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserCommonInfo userInfo) {
		this.userInfo = userInfo;
	}
	public FootPrintGet() {
		super();
	}
	
	
}
