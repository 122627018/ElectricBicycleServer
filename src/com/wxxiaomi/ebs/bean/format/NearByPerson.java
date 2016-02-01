package com.wxxiaomi.ebs.bean.format;

import java.util.List;

import com.wxxiaomi.ebs.bean.User1;


public class NearByPerson {
	private List<UserLocat> userLocatList;

	
	public static class UserLocat{
		private User1 user;
		private double[] locat;
		public User1 getUser() {
			return user;
		}
		public void setUser(User1 user) {
			this.user = user;
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
