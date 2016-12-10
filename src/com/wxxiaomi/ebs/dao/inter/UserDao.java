package com.wxxiaomi.ebs.dao.inter;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.User;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;

public interface UserDao {

	User getUser(String username,String password);
	
	int insertUser(User user);
	
	List<UserCommonInfo> getUserInfosByEms(List<String> names);
	
	UserCommonInfo getUserInfoByEm(String emname);
	
	UserCommonInfo getUserInfoById(int userid);
	
//	UserCommonInfo getUserInfoByName(String name);
	
	int updateUser(UserCommonInfo userInfo);
	
	int updateUserHead(int userid,String imgPath);

	List<UserCommonInfo> getUserInfosByNames(String name);
}
