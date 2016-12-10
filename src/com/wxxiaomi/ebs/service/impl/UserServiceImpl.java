package com.wxxiaomi.ebs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxxiaomi.ebs.dao.bean.User;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.dao.inter.UserDao;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.util.jwt.Jwt;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	
	@Override
	public Result Login(String username, String password) {
		User user = userDao.getUser(username, password);
		Result result;
		if(user!=null){
			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("uid", user.getUserCommonInfo().id);// 用户id
			payload.put("iat", date.getTime());// 生成时间:当前
			payload.put("ext", date.getTime() + 2000 * 60 * 60);// 过期时间2小时
			String token = Jwt.createToken(payload);
			result = new Result(200,"",user);
			result.putHeader("token", token);
			result.putHeader("long_token", "asdsadasd");
		}else{
			result = new Result(300, "账号或者密码错误", "");
		}
		
		return result;

	}


	@Override
	public Result Register(String username, String passwrod) {
//		userDao.insertUser(user)
		return null;
	}


	@Override
	public Result getUserInfosByEms(List<String> ems) {
		List<UserCommonInfo> userInfosByEms = userDao.getUserInfosByEms(ems);
		return new Result(200, "", userInfosByEms);
	}


	@Override
	public Result getUserInfosByName(String name) {
		return new Result(200, "", userDao.getUserInfosByNames(name));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//	@SuppressWarnings("unchecked")
//	@Override
//	public List<UserCommonInfo> getUserListByEMUsername(List<String> list) {
//		String queryString = "from UserCommonInfo u where u.emname in(:list)";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameterList("list", list);
//		return (List<UserCommonInfo>) queryObject.list();
//	}

//	@Override
//	public boolean checkPhoneExists(String phone) {
//		// String queryString = "from UserCommonInfo u where u.phone=?";
//		return false;
//
//	}
//
//	@Override
//	public User registerUser(String username, String password) {
//		try {
//			// 新建user
//			User user = new User();
//			user.setUsername(username);
//			user.setPassword(password);
//
//			// 设置CommonInfo
//			UserCommonInfo info = new UserCommonInfo();
//			info.setName(username);
//			info.setHead("default.jpg");
//
//			// 申请IM注册
//			ObjectNode createNewIMUserSingle = EasemobIMUsers
//					.createNewIMUserSingle(username, password);
//			int statusCode = Integer.valueOf(createNewIMUserSingle.get(
//					"statusCode").toString());
//
//			if (statusCode == 200) {
//				info.setEmname(username);
//				factory.getCurrentSession().persist(info);
//				user.setUserCommonInfo(info);
//				factory.getCurrentSession().persist(user);
//			} else {
//				throw new Exception("IM注册失败，错误码：" + statusCode + "(错误信息):"
//						+ createNewIMUserSingle.get("error"));
//			}
//
//			return user;
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	@Override
//	public UserCommonInfo getUserCommonInfoById(int userid) {
//		UserCommonInfo result = (UserCommonInfo) factory.getCurrentSession()
//				.get(UserCommonInfo.class, userid);
//		return result;
//	}
//
//	@Override
//	public boolean improveUserInfo(int userid, String emname, String name,
//			String description) {
//		try {
//			UserCommonInfo user = new UserCommonInfo();
//			user.setId(userid);
//			user.setEmname(emname);
//			user.setHead("");
//			user.setName(name);
//			factory.getCurrentSession().update(user);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<UserCommonInfo> getUserInfoByName(String name) {
//		String queryString = "from UserCommonInfo u where u.name=?";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, name);
//		return queryObject.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<UserCommonInfo> getUserInfoByEmname(String emname) {
//		String queryString = "from UserCommonInfo u where u.emname=?";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, emname);
//		return queryObject.list();
//	}
//
//	@Override
//	public boolean updateUserHead(int userid, String path) {
//		try {
//			String queryString = "update UserCommonInfo u set u.head=? where u.id=?";
//			Query queryObject = factory.getCurrentSession().createQuery(
//					queryString);
//			queryObject.setParameter(0, path);
//			queryObject.setParameter(1, userid);
//			queryObject.executeUpdate();
//			return true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
//
//	
//	@Override
//	public boolean insertUserPhoto(List<Photo> photos) {
//		for(Photo item : photos){
//			factory.getCurrentSession().persist(item);
//		}
//		
//		return true;
//	}
//
//	@Override
//	public List<String> getUserPhoto(int album_id, int size) {
//		String queryString = "select url from Photo p where p.album_id=?";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, album_id);
//		return queryObject.list();
//	}

	

	

//	@Override
//	public boolean insertUserPhoto(int userid, int album_id, String[] imgUrl,
//			String create_time) {
//		//更新user的相片数量
//		//向数据库插入photo
//		return false;
//	}

}
