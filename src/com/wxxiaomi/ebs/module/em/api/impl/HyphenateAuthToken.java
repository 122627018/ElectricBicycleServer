package com.wxxiaomi.ebs.module.em.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wxxiaomi.ebs.module.em.api.AuthTokenAPI;
import com.wxxiaomi.ebs.module.em.api.HyphenateRestAPI;
import com.wxxiaomi.ebs.module.em.comm.body.AuthTokenBody;
import com.wxxiaomi.ebs.module.em.comm.constant.HTTPMethod;
import com.wxxiaomi.ebs.module.em.comm.helper.HeaderHelper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.HeaderWrapper;

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
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}
}
