package com.wxxiaomi.ebs.engine;

import java.util.HashMap;
import java.util.Map;

import com.wxxiaomi.ebs.bean.User1;
import com.wxxiaomi.ebs.bean.format.Format_Login;
import com.wxxiaomi.ebs.engine.manager.UserManager;

public class UserEngine {
	
	private static<T> Map<String,Object> getResponseMap(int state,String error
			,T infos){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("state", state);
		map.put("error", error);
		map.put("infos", infos);
		return map;
	}

	public static Map<String,Object> Login(String username,String password){
		User1 user1 = UserManager.getUser(username, password);
		if(user1!=null){
			return getResponseMap(200, "", new Format_Login(user1));
		}else{
			return getResponseMap(404, "帐户名或密码错误",  null);
		}
		
	}
}
