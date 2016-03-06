package com.wxxiaomi.ebs.bean.format;

import java.util.List;

import com.wxxiaomi.ebs.bean.User.UserCommonInfo;


public class NearByPerson {
	private List<UserLocat> userLocatList;

	
	public static class UserLocat{
		private UserCommonInfo userCommonInfo;
		private double[] locat;
		
		public UserCommonInfo getUserCommonInfo() {
			return userCommonInfo;
		}
		public void setUserCommonInfo(UserCommonInfo userCommonInfo) {
			this.userCommonInfo = userCommonInfo;
		}
		public double[] getLocat() {
			return locat;
		}
		public void setLocat(double[] locat) {
			this.locat = locat;
		}
	}


	public List<UserLocat> getUserLocatList() {
		return userLocatList;
	}


	public void setUserLocatList(List<UserLocat> userLocatList) {
		this.userLocatList = userLocatList;
	}


	public NearByPerson(List<UserLocat> userLocatList) {
		super();
		this.userLocatList = userLocatList;
	}


	public NearByPerson() {
		super();
	}
	
	
}
