package com.wxxiaomi.ebs.dao.inter;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.FootPrint;
import com.wxxiaomi.ebs.dao.bean.Locat;
import com.wxxiaomi.ebs.dao.bean.format.FootPrintDetail;

public interface LocatDao {

	List<Locat> getNear(int userid, String geo);
//	int savaLocation(int userid, String geo);
//	int collectLocat(int userid, String geo,String locat_tag);
	int savaLocat(Locat locat);
	Locat getNearlyPersonLocat(int userid);
	
	FootPrint getUserNearlyFoot(int userid);
	
	int savaFootPrint(FootPrint foot);
	List<FootPrintDetail> footPrintList(int userid);
	
}
