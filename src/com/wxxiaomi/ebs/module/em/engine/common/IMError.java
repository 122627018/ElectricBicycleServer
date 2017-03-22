package com.wxxiaomi.ebs.module.em.engine.common;


public enum IMError {
	
	INVALID_GRANT(400,"\"invalid_grant\"","invalid username or password",20,"用户名或者密码输入错误"),
	ORGANIZATION_APPLICATION_NOT_FOUND(400,"\"organization_application_not_found\"","Could not find application for easemob-demo/aachatdemoui from URI: easemob-demo/aachatdemoui/users",21,"找不到aachatdemoui对应的APP，可能是URL写错了"),
	ILLEGAL_ARGUMENT(400,"\"illegal_argument\"","Entity user requires a property named username",22,"创建用户请求体未提供username或者psd/参数错误"),
	JSON_PARSE(400,"\"json_parse\"",23,"发送请求时请求体不符合标准的JSON格式，服务器无法正确解析"),
	DUPLICATE_UNIQUE_PROPERTY_EXISTS(400,"\"duplicate_unique_property_exists\"",25,"用户名已存在，dddd这个用户名在该APP下已经存在"),
	UNAUTHORIZED(400,"\"unauthorized\"",26,"APP的用户注册模式为授权注册，但是注册用户时请求头没带token"),
	AUTH_BAD_ACCESS_TOKEN(400,"\"auth_bad_access_token\"","token方面出现问题",27,"无效token，符合token的格式，但是该token不是接受请求的系统生成的，系统无法识别该token/发送请求时使用的token错误。注意：不是token过期"),
	SERVICE_RESOURCE_NOT_FOUND(400,"\"service_resource_not_found\"",28,"URL指定的资源不存在"),
	REQUEST_ENTITY_TOO_LARGE(400,"\"Request Entity Too Large\"",29,"请求体过大，比如超过了5kb，拆成几个更小的请求体重试即可"),
	REACH_LIMIT(400,"\"reach_limit\"",30,"超过接口每秒调用次数，加大调用间隔或者联系商务调整限流大小"),
	NO_FULL_TEXT_INDEX(400,"\"no_full_text_index\"",31,"username不支持全文索引，不可以对该字段进行contains操作"),
	UNSUPPORTED_SERVICE_OPERATION(400,"\"unsupported_service_operation\"",32,"请求方式不被发送请求的URL支持"),
	WEB_APPLICATION(400,"\"web_application\"",33,"错误的请求，给一个未提供的API发送了请求");
	
	
	private int code; 
	private String msg; 
	private String errorKey;
	private String error_description;
	private int httpCode;
	IMError(int httpCode,String errorKey,String error_description,int code, String msg) {  
        this.code = code;  
        this.msg = msg;
        this.errorKey = errorKey;
        this.error_description = error_description;
    }
	
	IMError(int httpCode,String errorKey,int code, String msg) {  
        this.code = code;  
        this.msg = msg;
        this.errorKey = errorKey;
        this.error_description = "";
    }
	
	public String getErrorMsg(){
		return msg;
	}
	
	public int getErrorCode(){
		return code;
	}
	
	public String getKey(){
		return errorKey;
	}
	
	public String getErrDescription(){
		return error_description;
	}
	
	public int getHttpCode(){
		return httpCode;
	}
	
	 public static IMError getIMError(String errorKey){
		 IMError[] errors=IMError.values();
		 IMError er=null;
	    	for (IMError error : errors) {
				if(error.getKey().equals(errorKey)){
					er=error;
					break;
				}
			}
	    	return er;
	    }
}
