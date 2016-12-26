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
	
	public int target_id;
	public String listfootprint(){
		System.out.println("listfootprint(),userid:"+userid);
		adapterResult(mapService.listFootPrint(target_id));
		return "listfootprint";
	}

	
	public String content;
	public String picture;
//	public String geo;
	public double lat;
	public double lng;
	public String locat_tag;
	public String publishfootprint(){
		System.out.println("publishfootprint");
		String geo = GeoHashUtil.encode(lat, lng);
		adapterResult(mapService.collectLocat(userid, geo, locat_tag, content, picture));
		return "publishfootprint";
	}

}
