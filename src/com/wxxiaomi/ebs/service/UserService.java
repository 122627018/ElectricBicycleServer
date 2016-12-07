package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.bean.Option;
import com.wxxiaomi.ebs.bean.Photo;
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
	 
//	 /**
//	  * 客户端更新相册
//	  * @param userid
//	  * @param album_id
//	  * @param imgUrl
//	  * @param create_time
//	  * @return
//	  */
//	 boolean insertUserPhoto(int userid,int album_id,String[] imgUrl,String create_time);
	 
	 boolean insertUserPhoto(List<Photo> photos);
	 
	 /**
	  * 取得某个用户的照片
	  * @param album_id
	  * @param size
	  * @return
	  */
	 List<String> getUserPhoto(int album_id,int size);
	 
	 
	 
	 
}
