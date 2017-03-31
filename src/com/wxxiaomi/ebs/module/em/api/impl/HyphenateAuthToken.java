package com.wxxiaomi.ebs.module.em.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxxiaomi.ebs.module.em.api.AuthTokenAPI;
import com.wxxiaomi.ebs.module.em.api.HyphenateRestAPI;
import com.wxxiaomi.ebs.module.em.api.RestAPIInvoker;
import com.wxxiaomi.ebs.module.em.comm.body.AuthTokenBody;
import com.wxxiaomi.ebs.module.em.comm.constant.HTTPMethod;
import com.wxxiaomi.ebs.module.em.comm.helper.HeaderHelper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.HeaderWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.ResponseWrapper;

public class HyphenateAuthToken extends HyphenateRestAPI implements AuthTokenAPI{
	
	public static final String ROOT_URI = "/token";
	
	private static final Logger log = LoggerFactory.getLogger(HyphenateAuthToken.class);
	
	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	public Object getAuthToken(String clientId, String clientSecret) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = new AuthTokenBody(clientId, clientSecret);
		HeaderWrapper header = HeaderHelper.getDefaultHeader();
		RestAPIInvoker invoker2 = getInvoker();
		System.out.println(invoker2);
		System.out.println("url:"+url);
		ResponseWrapper sendRequest = invoker2.sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);;
		
		for(String item:sendRequest.getMessages()){
			System.out.println(item);
		}
//		System.out.println(sendRequest.get);
		return sendRequest;
	}
}
