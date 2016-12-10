package com.wxxiaomi.ebs.dao.inter;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.Locat;

public interface LocatDao {

	List<Locat> getNear(int userid, String geo);
	int savaLocation(int userid, String geo);
	
}
