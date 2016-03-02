package com.wxxiaomi.ebs.em.api;

public interface IMUserAPI {

	/**
	 * 注册IM用户[单个] <br>
	 * POST
	 * 
	 * @param payload
	 *            <code>{"username":"${用户名}","password":"${密码}", "nickname":"${昵称值}"}</code>
	 * @return
	 */
	Object createNewIMUserSingle(Object payload);
	
	/**
	 * 查看某个IM用户的好友信息 <br>
	 * GET
	 * 
	 * @param userName
	 *            用戶名或用戶ID
	 * @return
	 */
	Object getFriends(String userName);
}
