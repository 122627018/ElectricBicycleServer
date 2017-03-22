package com.wxxiaomi.ebs.module.em.engine.common;

public class ImException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMError error;
	public ImException(IMError error){
		this.error = error;
	}
	
	public String getDisplayErr(){
		return error.getErrorMsg();
	}
	
	public IMError getErrDetail(){
		return error;
	}
}
