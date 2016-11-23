package com.wxxiaomi.ebs.bean.format;

import java.util.List;

import com.wxxiaomi.ebs.bean.Locat;



public class NearByPerson {
	private List<Locat> userLocatList;


	public List<Locat> getUserLocatList() {
		return userLocatList;
	}


	public void setUserLocatList(List<Locat> userLocatList) {
		this.userLocatList = userLocatList;
	}


	public NearByPerson(List<Locat> userLocatList) {
		super();
		this.userLocatList = userLocatList;
	}


	public NearByPerson() {
		super();
	}
	
	
}
