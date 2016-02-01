package com.wxxiaomi.ebs.engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wxxiaomi.ebs.bean.format.NearByPerson;
import com.wxxiaomi.ebs.bean.format.NearByPerson.UserLocat;
import com.wxxiaomi.ebs.engine.manager.MapManager;
import com.wxxiaomi.ebs.util.GeoHashUtil;

public class MapEngine {

	private static<T> Map<String,Object> getResponseMap(int state,String error
			,T infos){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("state", state);
		map.put("error", error);
		map.put("infos", infos);
		return map;
	}
	
	/**
	 * 获取一个用户附近的人
	 * @param userid     用户id
	 * @param latitude   经度
	 * @param longitude  纬度
	 * @return 
	 */
	public static Map<String, Object> getNearByPerson(int userid, double latitude,
			double longitude) {
		String geoResult = GeoHashUtil.encode(latitude, longitude);
		//取出附近的人
		List<UserLocat> nearByPerson = MapManager.getNearByPerson(userid, geoResult);
		System.out.println("nearByPerson.size="+nearByPerson.size());
		//存入自己的位置
		 MapManager.savaLocation(userid,geoResult);
		 return getResponseMap(200, "", new NearByPerson(nearByPerson));
	}

}
