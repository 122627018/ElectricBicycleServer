package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.Locat;


public interface MapService {

	List<Locat> getNearByPerson(int userid, String geo);
	
	boolean savaLocation(int userid, String geo);
	
	List<Locat> createNearByPeople(double latitude,
			double longitude);
}
