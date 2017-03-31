package com.wxxiaomi.ebs.common;

public enum ErrorMsg {
	ERROR_ILLICIT_CLIENT(300,"未经授权的客户端"),
	
	ERROR_OVERDUE_CLIENT(301,"非法的客户端"),
	
	//没有携带用户登录标识
	ERROR_TOKEN_NULL(302,"没有携带用户登录标识"),
	
	//token的解密出错
	ERROR_TOKEN_PARSE(303,"登录标志出错"),
	
	ERROR_UNKNOW(299,"未知错误"),
	
	ERROR_TOKEN_EXPIRED(304,"用户短期标识过期")
	
	;
	
	
	private int code; 
	private String msg; 
	ErrorMsg(int code,String msg) {  
        this.code = code;  
        this.msg = msg;
    }
	
	public String getErrorMsg(){
		return msg;
	}
	
	public int getErrorCode(){
		return code;
	}
}
