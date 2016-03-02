package com.wxxiaomi.ebs.engine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.wxxiaomi.ebs.bean.User1;
import com.wxxiaomi.ebs.bean.format.Format_Login;
import com.wxxiaomi.ebs.engine.manager.UserManager;
import com.wxxiaomi.ebs.exception.UnKnownErrorException;
import com.wxxiaomi.ebs.exception.UserExistsException;

public class UserEngine {
	
	/**
	 * 
	 * @param state  状态码
	 * @param error  错误信息
	 * @param infos  实体
	 * @return
	 */
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

	/**
	 * 注册一个用户
	 * @param username
	 * @param password
	 * @param name
	 * @return
	 */
	public static Map<String,Object> Register(
			String username, String password, String name) {
		try {
			User1 user = UserManager.registerUser(username, password, name);
			return getResponseMap(200, "", new Format_Login(user));
		} catch (SQLException e) {
			return getResponseMap(321, "服务器连接数据库失败",  null);
		} catch (UnKnownErrorException e) {
			return getResponseMap(322, "位置错误",  null);
		}
	}

	/**
	 * 检查此电话是否被注册过
	 * @param phone
	 * @return
	 */
	public static Map<String,Object> checkPhone(
			String phone) {
		try {
			UserManager.checkPhoneExists(phone);
			return getResponseMap(200, "", "");
		} catch (UserExistsException e) {
			return getResponseMap(320, "用户已存在", "");
		} catch (SQLException e) {
			return getResponseMap(321, "服务器连接数据库发生错误", "");
		}
//		return getResponseMap(404, "为知错误", "");
	}
}
