package com.wxxiaomi.ebs.em.engine;


import java.net.URL;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wxxiaomi.ebs.em.comm.Constants;
import com.wxxiaomi.ebs.em.comm.HTTPMethod;
import com.wxxiaomi.ebs.em.comm.Roles;
import com.wxxiaomi.ebs.em.util.HTTPClientUtils;
import com.wxxiaomi.ebs.em.vo.ClientSecretCredential;
import com.wxxiaomi.ebs.em.vo.Credential;
import com.wxxiaomi.ebs.em.vo.EndPoints;

public class EasemobIMUsers {

	// 通过app的client_id和client_secret来获取app管理员token
	
	private static Credential credential = new ClientSecretCredential(
			Constants.APP_CLIENT_ID, Constants.APP_CLIENT_SECRET,
			Roles.USER_ROLE_APPADMIN);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

	/**
	 * 注册IM用户[单个]
	 * 
	 * 给指定Constants.APPKEY创建一个新的用户
	 * 
	 * @param dataNode
	 * @return
	 */
	public static ObjectNode createNewIMUserSingle(String username,
			String password) {
		ObjectNode dataNode = JsonNodeFactory.instance.objectNode();
		dataNode.put("username", username);
		dataNode.put("password", password);
		ObjectNode objectNode = factory.objectNode();
		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+",
				Constants.APPKEY)) {
//			System.out.println("Bad format of Constants.APPKEY: "
//					+ Constants.APPKEY);
			objectNode.put("message", "Bad format of Constants.APPKEY");
			return objectNode;
		}
		objectNode.removeAll();
		if (null != dataNode && !dataNode.has("username")) {
//			System.out
//					.println("Property that named username must be provided .");
			objectNode.put("message",
					"Property that named username must be provided .");
			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
//			System.out
//					.println("Property that named password must be provided .");
			objectNode.put("message",
					"Property that named password must be provided .");
			return objectNode;
		}
		try {
			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL,
					credential, dataNode, HTTPMethod.METHOD_POST);
		} catch (Exception e) {
//			e.printStackTrace();
//			throw new 
		}
		return objectNode;
	}
	
	
	/**
	 * IM用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
//	public static ObjectNode imUserLogin(String username, String password) {
//
//		ObjectNode objectNode = factory.objectNode();
//
//		// check appKey format
//		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
////			LOGGER.error("Bad format of Appkey: " + Constants.APPKEY);
//
//			objectNode.put("message", "Bad format of Appkey");
//
//			return objectNode;
//		}
//		if (StringUtils.isEmpty(username)) {
////			LOGGER.error("Your username must be provided，the value is username of imuser.");
//
//			objectNode.put("message", "Your username must be provided，the value is username of imuser.");
//
//			return objectNode;
//		}
//		if (StringUtils.isEmpty(password)) {
////			LOGGER.error("Your password must be provided，the value is username of imuser.");
//
//			objectNode.put("message", "Your password must be provided，the value is username of imuser.");
//
//			return objectNode;
//		}
//
//		try {
//			ObjectNode dataNode = factory.objectNode();
//			dataNode.put("grant_type", "password");
//			dataNode.put("username", username);
//			dataNode.put("password", password);
//
//			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.TOKEN_APP_URL, null, dataNode,
//					HTTPMethod.METHOD_POST);
//
//		} catch (Exception e) {
//			throw new RuntimeException("Some errors occurred while fetching a token by username and password .");
//		}
//
//		return objectNode;
//	}
	
	
	/**
	 * 获取用户所有好友
	 * 
	 * @param ownerUserName
	 * 
	 * @return
	 */
	public static ObjectNode getFriends(String ownerUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check Constants.APPKEY format
		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", Constants.APPKEY)) {
//			LOGGER.error("Bad format of Constants.APPKEY: " + Constants.APPKEY);

			objectNode.put("message", "Bad format of Constants.APPKEY");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
//			LOGGER.error("Your userName must be provided，the value is username of imuser.");

			objectNode.put("message", "Your userName must be provided，the value is username of imuser.");

			return objectNode;
		}

		try {
			
			URL addFriendSingleUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users/"
					+ ownerUserName + "/contacts/users");

			ObjectNode body = factory.objectNode();
			objectNode = HTTPClientUtils.sendHTTPRequest(addFriendSingleUrl, credential, body, HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

}
