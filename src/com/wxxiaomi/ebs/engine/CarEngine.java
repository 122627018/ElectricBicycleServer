package com.wxxiaomi.ebs.engine;

import java.util.HashMap;
import java.util.Map;

import com.wxxiaomi.ebs.engine.manager.CarDao;

public class CarEngine {
	
	
	private static<T> Map<String,Object> getResponseMap(int state,String error
			,T infos){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("state", state);
		map.put("error", error);
		map.put("infos", infos);
		return map;
	}
	
	public static Map<String,Object> bundBicycle(int userid,int carid){
		if(CarDao.bundBicycle(userid, carid)){
			return getResponseMap(200,"",null);
		}else{
			return getResponseMap(404,"绑定失败",null);
		}
	}
	
	public static Map<String,Object> getCarInfos(int id){
		return getResponseMap(200,"",CarDao.getCarInfo(id));
	}
}
