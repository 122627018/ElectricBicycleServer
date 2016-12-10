package com.wxxiaomi.ebs.support.exception;

public class ApiException extends Exception{
	
	private String message;
	private int code;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApiException(int code,String message) {
		super();
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
	

}
