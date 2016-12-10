package com.wxxiaomi.ebs.support.exception;


	/**
	 * 用户已经存在异常
	 * @author Administrator
	 *
	 */
	public class UserExistsException extends Exception{
		private static final long serialVersionUID = 1L;

		public UserExistsException(String msg)  
		    {  
		        super(msg);  
		    }  
		
	}
