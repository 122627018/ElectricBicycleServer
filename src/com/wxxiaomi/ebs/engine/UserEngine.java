package com.wxxiaomi.ebs.engine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.User.UserCommonInfo;
import com.wxxiaomi.ebs.bean.format.Format_InitUserData;
import com.wxxiaomi.ebs.bean.format.Format_Login;
import com.wxxiaomi.ebs.em.engine.EasemobIMUsers;
import com.wxxiaomi.ebs.engine.manager.UserDao;
import com.wxxiaomi.ebs.exception.PsdErrorException;
import com.wxxiaomi.ebs.exception.UnKnownErrorException;
import com.wxxiaomi.ebs.exception.UserExistsException;

public class UserEngine {

	/**
	 * 
	 * @param state
	 *            状态码
	 * @param error
	 *            错误信息
	 * @param infos
	 *            实体
	 * @return
	 */
	private static <T> Map<String, Object> getResponseMap(int state,
			String error, T infos) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);
		map.put("error", error);
		map.put("infos", infos);
		return map;
	}

	public static List<String> jsonToArray(JsonNode jsonNode) {
		List<String> result = new ArrayList<String>();

		for (int i = 0; i < jsonNode.size(); i++) {
			result.add(jsonNode.get(i).toString());
		}
		return result;
	}

	/**
	 * 初始化用户信息
	 * @param username
	 * @param password
	 * @return
	 */
	public static Map<String, Object> initUserInfo(String username,
			String password) {
		try {
			ObjectNode friends = EasemobIMUsers.getFriends(username);
			int stateCode = Integer.valueOf(friends.get("statusCode")
					.toString());
			if (stateCode == 200) {
				JsonNode jsonNode = friends.get("data");
				List<String> list = jsonToArray(jsonNode);

				List<UserCommonInfo> userListByEMUsername = UserDao
						.getUserListByEMUsername(list);
				return getResponseMap(200, "", new Format_InitUserData(userListByEMUsername));

			} else {
				// 获取好友失败
				System.out.println("登陆过程获取好友列表失败");
				return doEMException(friends);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return getResponseMap(321, "连接服务器异常", null);
		}

	}

	/**
	 * 登陆操作
	 * 
	 * @param username
	 * @param password
	 * @param isFirst
	 * @return
	 */
	public static Map<String, Object> Login(String username, String password) {
		User user1;
		try {
			user1 = UserDao.Login(username, password);
			return getResponseMap(200, "", user1);
			// if ("y".equals(isFirst)) {
			// System.out.println("进入em获取好友中");
			// ObjectNode friends = EasemobIMUsers.getFriends(user1
			// .getUsername());
			// // System.out.println("friends.toString()=" +
			// friends.toString());
			// int stateCode = Integer.valueOf(friends.get("statusCode")
			// .toString());
			// if (stateCode == 200) {
			// JsonNode jsonNode = friends.get("data");
			// List<String> list = jsonToArray(jsonNode);
			// // List<UserCommonInfo> userListByEMUsername = UserDao
			// // .getUserListByEMUsername(list);
			// return getResponseMap(200, "", new Format_Login(user1));
			// }else{
			// //获取好友失败
			// System.out.println("登陆过程获取好友列表失败");
			// return doEMException(friends);
			// }
			// } else {
			// return getResponseMap(200, "", new Format_Login(user1, null));
			// }

		} catch (SQLException e) {
			e.printStackTrace();
			return getResponseMap(321, "链接服务器数据库出错", null);
		} catch (PsdErrorException e) {
			// e.printStackTrace();
			return getResponseMap(333, "帐户名或密码错误", null);
		} catch (UnKnownErrorException e) {
			e.printStackTrace();
			return getResponseMap(334, e.getMessage(), null);
		}
	}

	/**
	 * 注册一个用户
	 * 
	 * @param username
	 * @param password
	 * @param name
	 * @return
	 */
	public static Map<String, Object> Register(String username,
			String password, String name) {
		try {
			ObjectNode createNewIMUserSingle = EasemobIMUsers
					.createNewIMUserSingle(username, password);
			// System.out.println("createNewIMUserSingle.get(statusCode)="+createNewIMUserSingle.get("statusCode"));
			int statusCode = Integer.valueOf(createNewIMUserSingle.get(
					"statusCode").toString());
			System.out.println("注册过程中环信返回的状态码是：" + statusCode);
			if (statusCode == 200) {
				// 环信用户注册成功
				User user = UserDao.registerUser(username, password, name);
				return getResponseMap(200, "", new Format_Login(user));
			} else {
				return doEMException(createNewIMUserSingle);
			}

		} catch (SQLException e) {
			return getResponseMap(321, "服务器连接数据库失败", null);
		} catch (UnKnownErrorException e) {
			return getResponseMap(322, "未知错误", null);
		}
	}

	/**
	 * 处理环信的异常
	 * 
	 * @param createNewIMUserSingle
	 * @return
	 */
	private static Map<String, Object> doEMException(
			ObjectNode createNewIMUserSingle) {

		return null;
	}

	/**
	 * 检查此电话是否被注册过
	 * 
	 * @param phone
	 * @return
	 */
	public static Map<String, Object> checkPhone(String phone) {
		try {
			UserDao.checkPhoneExists(phone);
			return getResponseMap(200, "", "");
		} catch (UserExistsException e) {
			return getResponseMap(320, "此手机号已经被注册", "");
		} catch (SQLException e) {
			e.printStackTrace();
			return getResponseMap(321, "服务器连接数据库发生错误", "");
		}
		// return getResponseMap(404, "为知错误", "");
	}
}
