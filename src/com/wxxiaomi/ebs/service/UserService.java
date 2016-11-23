package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.UserCommonInfo;

public interface UserService {

	User Login(String username,String password);
	
	List<UserCommonInfo> getUserListByEMUsername(List<String> list);
	
	boolean checkPhoneExists(String phone);
	
	User registerUser(String username, String password);
	
	 UserCommonInfo getUserCommonInfoById(int userid);
	 
	 boolean improveUserInfo(int userid, String emname, String name,
				String description);
	 
	 List<UserCommonInfo> getUserInfoByName(String name);
	 
	 List<UserCommonInfo> getUserInfoByEmname(String emname);
	 
	 boolean updateUserHead(int userid,String path);
}
