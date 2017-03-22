package com.wxxiaomi.ebs.common;

public interface CODEClient {

	//非法客户端，未经授权的客户端(没有携带密钥)
	public static final int ERROR_ILLICIT_CLIENT = 300;
	
	//密钥错误，过期，非法的客户端(解密出错)
	public static final int ERROR_OVERDUE_CLIENT = 301;
	
	//没有携带用户标识
	public static final int ERROR_TOKEN_NULL = 302;
	
	//token的解密出错
	public static final int ERROR_TOKEN_PARSE = 303;
	
	//未知错误
	public static final int ERROR_UNKNOW = 299;
	
	//用户短期标识过期
	public static final int ERROR_TOKEN_EXPIRED = 304;
}
