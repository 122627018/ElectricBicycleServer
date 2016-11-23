package com.wxxiaomi.ebs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.UserCommonInfo;
import com.wxxiaomi.ebs.em.engine.EasemobIMUsers;
import com.wxxiaomi.ebs.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	SessionFactory factory;

	@Override
	public User Login(String username, String password) {
		String queryString = "from User u where u.username=? and u.password=?";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, username);
		queryObject.setParameter(1, password);
		if (queryObject.list().size() > 0) {
			return (User) queryObject.list().get(0);
		} else
			return null;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCommonInfo> getUserListByEMUsername(List<String> list) {
		String queryString = "from UserCommonInfo u where u.emname in(:list)";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameterList("list", list);
		return (List<UserCommonInfo>) queryObject.list();
	}

	@Override
	public boolean checkPhoneExists(String phone) {
		// String queryString = "from UserCommonInfo u where u.phone=?";
		return false;

	}

	@Override
	public User registerUser(String username, String password) {
		try {
			// 新建user
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);

			// 设置CommonInfo
			UserCommonInfo info = new UserCommonInfo();
			info.setName(username);
			info.setHead("default.jpg");

			// 申请IM注册
			ObjectNode createNewIMUserSingle = EasemobIMUsers
					.createNewIMUserSingle(username, password);
			int statusCode = Integer.valueOf(createNewIMUserSingle.get(
					"statusCode").toString());

			if (statusCode == 200) {
				info.setEmname(username);
				factory.getCurrentSession().persist(info);
				user.setUserCommonInfo(info);
				factory.getCurrentSession().persist(user);
			} else {
				throw new Exception("IM注册失败，错误码：" + statusCode + "(错误信息):"
						+ createNewIMUserSingle.get("error"));
			}

			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserCommonInfo getUserCommonInfoById(int userid) {
		UserCommonInfo result = (UserCommonInfo) factory.getCurrentSession()
				.get(UserCommonInfo.class, userid);
		return result;
	}

	@Override
	public boolean improveUserInfo(int userid, String emname, String name,
			String description) {
		try {
			UserCommonInfo user = new UserCommonInfo();
			user.setId(userid);
			user.setEmname(emname);
			user.setHead("");
			user.setName(name);
			factory.getCurrentSession().update(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCommonInfo> getUserInfoByName(String name) {
		String queryString = "from UserCommonInfo u where u.name=?";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, name);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserCommonInfo> getUserInfoByEmname(String emname) {
		String queryString = "from UserCommonInfo u where u.emname=?";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, emname);
		return queryObject.list();
	}

	@Override
	public boolean updateUserHead(int userid, String path) {
		try {
			String queryString = "update UserCommonInfo u set u.head=? where u.id=?";
			Query queryObject = factory.getCurrentSession().createQuery(
					queryString);
			queryObject.setParameter(0, path);
			queryObject.setParameter(1, userid);
			queryObject.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
