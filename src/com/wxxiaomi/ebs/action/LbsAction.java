package com.wxxiaomi.ebs.action;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.action.base.BaseAction;
import com.wxxiaomi.ebs.service.MapService;
import com.wxxiaomi.ebs.util.GeoHashUtil;

@Controller
public class LbsAction extends BaseAction{
   
	@Resource
	MapService mapService;


	public int userid;
	public double latitude;
	public double longitude;

	/**
	 * 获取附近的人
	 * 
	 * @return
	 */
	public String near() {
		System.out.println("near(),userid:"+userid);
		String geoResult = GeoHashUtil.encode(latitude, longitude);
		adapterResult(mapService.getNearByPerson(userid, geoResult));
		return "near";
	}


}
