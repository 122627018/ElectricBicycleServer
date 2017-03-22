package com.wxxiaomi.ebs.module.em.engine.impl;

import java.io.IOException;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxxiaomi.ebs.module.em.api.IMUserAPI;
import com.wxxiaomi.ebs.module.em.comm.ClientContext;
import com.wxxiaomi.ebs.module.em.comm.HyphenateRestAPIFactory;
import com.wxxiaomi.ebs.module.em.comm.body.IMUserBody;
import com.wxxiaomi.ebs.module.em.comm.utils.ResponseUtils;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.ResponseWrapper;
import com.wxxiaomi.ebs.module.em.engine.IMUserDao;
import com.wxxiaomi.ebs.module.em.engine.common.IMError;
import com.wxxiaomi.ebs.module.em.engine.common.ImException;

@Repository
public class ImUserDaoImpl implements IMUserDao {
	HyphenateRestAPIFactory factory = ClientContext.getInstance()
			.init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
	IMUserAPI user = (IMUserAPI) factory
			.newInstance(HyphenateRestAPIFactory.USER_CLASS);

	@Override
	public String[] getUserContacts(String username) throws ImException {
		// TODO Auto-generated method stub
		try {
			ResponseWrapper res = (ResponseWrapper) user.getContacts(username);
			JsonNode json = ResponseUtils.ResponseBodyToJsonNode(res);
			if (res.getResponseStatus() == 200) {
				ObjectMapper objectMappe = new ObjectMapper();
				String[] readValue = objectMappe.readValue(json.get("data").toString(),
						String[].class);
				return readValue;
			}else{
				throw new ImException(IMError.getIMError(json.get("error").toString()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 服务器网络错误
			System.out.println("获取好友过程中网络错误");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String[] getUserBlackList(String username) throws ImException {
		try {
			ResponseWrapper res = (ResponseWrapper) user.getBlacklist(username);
//			System.out.println("me_res.getResponseStatus():"+res.getResponseStatus());
			if (res.getResponseStatus() == 200) {
				JsonNode json = ResponseUtils.ResponseBodyToJsonNode(res);
				ObjectMapper objectMappe = new ObjectMapper();
//				System.out.println(json);
				String[] readValue = objectMappe.readValue(json.get("data").toString(),
						String[].class);
				return readValue;
			}else{
				JsonNode json = ResponseUtils.ResponseBodyToJsonNode(res);;
				throw new ImException(IMError.getIMError(json.get("error").toString()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// 服务器网络错误
			System.out.println("获取用户黑名单过程中网络错误");
//			e.printStackTrace();
			
		} catch (ImException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw e;
		}
		return null;
	}

	@Override
	public boolean createUser(String username, String pwd) throws ImException {
		try {
			BodyWrapper userBody = new IMUserBody(username, pwd, "HelloWorld");
			ResponseWrapper res = (ResponseWrapper) user.createUser(userBody);
			JsonNode json;
				json = ResponseUtils.ResponseBodyToJsonNode(res);
			if (res.getResponseStatus() == 200) {
				return true;
			}else{
				throw new ImException(IMError.getIMError(json.get("error").toString()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
