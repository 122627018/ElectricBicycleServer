package com.wxxiaomi.ebs.module.em.engine;

import java.io.IOException;

import com.wxxiaomi.ebs.module.em.engine.common.ImException;



public interface IMUserDao {

	/**
	 * 获取用户联系人
	 * @param username
	 * @return
	 * @throws ImException 
	 */
	String[] getUserContacts(String username) throws ImException;
	
	/**
	 * 获取用户的黑名单
	 * @param username
	 * @return
	 * @throws IOException 
	 * @throws ImException 
	 */
	String[] getUserBlackList(String username) throws ImException;
	
	boolean createUser(String username,String pwd) throws ImException;
	
//	boolean addContact
	
}
