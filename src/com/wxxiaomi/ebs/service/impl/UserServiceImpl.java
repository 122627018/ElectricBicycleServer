package com.wxxiaomi.ebs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxxiaomi.ebs.dao.bean.Option;
import com.wxxiaomi.ebs.dao.bean.User;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.dao.bean.constant.OptionType;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.dao.bean.format.OptionDetail;
import com.wxxiaomi.ebs.dao.bean.format.UserInfo;
import com.wxxiaomi.ebs.dao.inter.CommentDao;
import com.wxxiaomi.ebs.dao.inter.OptionDao;
import com.wxxiaomi.ebs.dao.inter.TopicDao;
import com.wxxiaomi.ebs.dao.inter.UserDao;
import com.wxxiaomi.ebs.module.jwt.Jwt;
import com.wxxiaomi.ebs.module.jwt.TokenState;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.util.MyUtils;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDao userDao;
	@Resource
	OptionDao optionDao;
	@Resource
	TopicDao topicDao;
	@Resource
	CommentDao commentDao;

	@Override
	public Result Login(String username, String password, String uniqueNum) {
		Result result;
		try {
			User user = userDao.getUser(username, password);
			if (user != null) {
				System.out.println("user!=null");
				Map<String, Object> payload = new HashMap<String, Object>();
				Date date = new Date();
				payload.put("uid", user.getUserCommonInfo().getId());// 用户id
				payload.put("iat", date.getTime());// 生成时间:当前
				payload.put("ext", date.getTime() + 60 * 60);// 过期时间2小时(60*60*2000
																// 2小时)
				String token = Jwt.createToken(payload);
				Map<String, Object> longMap = new HashMap<String, Object>();
				longMap.put("uid", user.getUserCommonInfo().getId());// 用户id
				longMap.put("iat", date.getTime());// 生成时间:当前
				longMap.put("ext", date.getTime() + 1000 * 60 * 60 * 24 * 15);// 过期时间15天
				longMap.put("phoneNum", uniqueNum);
				String long_token = Jwt.createToken(longMap);
				System.out.println("user:" + user.toString());
				result = new Result(200, "", user);
				result.putHeader("token", token);
				result.putHeader("long_token", long_token);
			} else {
				result = new Result(300, "账号或者密码错误", "");

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			result = new Result(300, "账号或者密码错误", "");
		}
		return result;

	}

	@Override
	public Result Register(String username, String passwrod) {
		// userDao.insertUser(user)
		return null;
	}

	@Override
	public Result getUserInfosByEms(List<String> ems) {
		List<UserCommonInfo> userInfosByEms = userDao.getUserInfosByEms(ems);
		return new Result(200, "", userInfosByEms);
	}

	@Override
	public Result getUserInfosByName(int userid,String name) {
		List<UserCommonInfo> userInfosByNames = userDao.getUserInfosByNames(userid,name);
		System.out.println("size:"+userInfosByNames.size());
		return new Result(200, "", userInfosByNames);
	}

	@Override
	public Result UserOptionLog(int userid) {
		// System.out.println("UserOptionLog");
		// List<Option> options = optionDao.getUserOptions(userid);
		// // List<Option> commentOption = new ArrayList<Option>();
		// List<Option> topicOption = new ArrayList<Option>();
		// for(Option option : options){
		// int type = option.getType();
		// switch (type) {
		// case OptionType.FOOT_PRINT:
		// break;
		// case OptionType.PHOTO_PUBLISH: //更新相册
		// break;
		// case OptionType.TOPIC_COMMENT://话题评论
		// case OptionType.TOPIC_PUBLISH://话题发布
		// topicOption.add(option);
		// break;
		// }
		// }
		// List<OptionDetail> optionDetail =
		// topicDao.getOptionDetail(topicOption);
		// //再经过commentdao的洗礼
		// optionDetail = commentDao.getOptionDetail(optionDetail);
		// optionDetail = userDao.getOptionDetail(optionDetail);

		try {
			List<OptionDetail> optionDetail = getOptionDetail(userid);
			return new Result(200, "", optionDetail);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(404, "出错啦", "");
		}

	}

	public List<OptionDetail> getOptionDetail(int userid) {
		List<Option> options = optionDao.getUserOptions(userid);
		List<Option> topicOption = new ArrayList<Option>();
		List<Option> locatOption = new ArrayList<Option>();
		for (Option option : options) {
			int type = option.getType();
			switch (type) {
			case OptionType.FOOT_PRINT:
				break;
			case OptionType.PHOTO_PUBLISH: // 更新相册
				break;
			case OptionType.TOPIC_COMMENT:// 话题评论
			case OptionType.TOPIC_PUBLISH:// 话题发布
				topicOption.add(option);
				break;
			}
		}
//		if(locatOption.size()!=0){
//			List<OptionDetail> os = new  ArrayList<OptionDetail>();
//			OptionDetail o= new OptionDetail();
//		}
		List<OptionDetail> optionDetail = topicDao.getOptionDetail(topicOption);
		// 再经过commentdao的洗礼
		optionDetail = commentDao.getOptionDetail(optionDetail);
		optionDetail = userDao.getOptionDetail(optionDetail);
//		optionDetail.get(1).setType(OptionType.FOOT_PRINT);
//		optionDetail.get(2).setType(OptionType.FOOT_PRINT);
//		optionDetail.get(3).setType(OptionType.FOOT_PRINT);
//		optionDetail.get(4).setType(OptionType.FOOT_PRINT);
		return optionDetail;
	}

	@Override
	public Result LongToken(String ltoken, String phoneId) {
		System.out.println("LongToken,ltoken:" + ltoken);
		Result result = null;
		// 先验证longToken的正确性，再返回短token
		Map<String, Object> resultMap = Jwt.validToken(ltoken);
		TokenState tokenState = TokenState.getTokenState((String) resultMap
				.get("state"));
		switch (tokenState) {
		case VALID:
			// 通过
			@SuppressWarnings("unchecked")
			HashMap<String, String> dataobj = ((HashMap<String, String>) resultMap
					.get("data"));
			System.out.println(dataobj);
			String userid = String.valueOf(dataobj.get("uid"));

			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("uid", userid);// 用户id
			payload.put("iat", date.getTime());// 生成时间:当前
			payload.put("ext", date.getTime() + 2000 * 60 * 60);// 过期时间2小时
			String stoken = Jwt.createToken(payload);

			Map<String, Object> longMap = new HashMap<String, Object>();
			longMap.put("uid", userid);// 用户id
			longMap.put("iat", date.getTime());// 生成时间:当前
			longMap.put("ext", date.getTime() + 1000 * 60 * 60 * 24 * 15);// 过期时间15小时
			longMap.put("phoneNum", dataobj.get("phoneNum"));
			String long_token = Jwt.createToken(longMap);

			result = new Result(200, "", userid);
			result.putHeader("long_token", long_token);
			result.putHeader("token", stoken);
			break;
		case EXPIRED:
			// 无效
		case INVALID:
			// 过期
			result = new Result(402, "登陆凭证过期，请重新登陆", "");
			break;

		default:
			break;
		}
		return result;
	}

	@Override
	public Result updateUserInfo(int userid, String nickname, String avatar,
			String emname, String description, String city, String cover,
			int sex, String create_time) {
		try {

			UserCommonInfo info = new UserCommonInfo(
					userid,
					nickname,
					avatar,
					emname,
					new Date(),
					create_time != null ? MyUtils.StrToDate(create_time) : null,
					description, city, sex, cover);
			userDao.updateUser(info);
			return new Result(200, "", "success");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new Result(404, "出错", "success");
	}

	@Override
	public Result updateUserFriends(List<String> emnames, List<Date> times) {
		// TODO Auto-generated method stub
		List<UserCommonInfo> updateUserFriends = userDao.updateUserFriends(
				emnames, times);
		return new Result(200, "", updateUserFriends);
	}

	@Override
	public Result updateUserCover(int userid, String coverPath) {
		// TODO Auto-generated method stub
		userDao.updateUserCover(userid, coverPath);
		return new Result(200, "", coverPath);
	}

	@Override
	public Result getUserInfoById(int taget_userid) {
		UserCommonInfo userInfoById = userDao.getUserInfoById(taget_userid);
		return new Result(200, "", userInfoById);
	}

	@Override
	public Result getUserInfoAndOptionById(int taget_userid) {
		try {
			System.out.println("taget_userid:" + taget_userid);
			UserCommonInfo userInfoById = userDao.getUserInfoById(taget_userid);
			System.out.println("UserCommonInfo:"+userInfoById.toString());
			List<OptionDetail> optionDetail = getOptionDetail(taget_userid);
			UserInfo userInfo = new UserInfo(userInfoById, optionDetail);
			System.out.println("userInfo:" + userInfo.toString());
			return new Result(200, "", userInfo);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new Result(404, "出错啦", "");
		}
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<UserCommonInfo> getUserListByEMUsername(List<String> list) {
	// String queryString = "from UserCommonInfo u where u.emname in(:list)";
	// Query queryObject = factory.getCurrentSession()
	// .createQuery(queryString);
	// queryObject.setParameterList("list", list);
	// return (List<UserCommonInfo>) queryObject.list();
	// }

	// @Override
	// public boolean checkPhoneExists(String phone) {
	// // String queryString = "from UserCommonInfo u where u.phone=?";
	// return false;
	//
	// }
	//
	// @Override
	// public User registerUser(String username, String password) {
	// try {
	// // 新建user
	// User user = new User();
	// user.setUsername(username);
	// user.setPassword(password);
	//
	// // 设置CommonInfo
	// UserCommonInfo info = new UserCommonInfo();
	// info.setName(username);
	// info.setHead("default.jpg");
	//
	// // 申请IM注册
	// ObjectNode createNewIMUserSingle = EasemobIMUsers
	// .createNewIMUserSingle(username, password);
	// int statusCode = Integer.valueOf(createNewIMUserSingle.get(
	// "statusCode").toString());
	//
	// if (statusCode == 200) {
	// info.setEmname(username);
	// factory.getCurrentSession().persist(info);
	// user.setUserCommonInfo(info);
	// factory.getCurrentSession().persist(user);
	// } else {
	// throw new Exception("IM注册失败，错误码：" + statusCode + "(错误信息):"
	// + createNewIMUserSingle.get("error"));
	// }
	//
	// return user;
	// } catch (Exception e) {
	// return null;
	// }
	// }
	//
	// @Override
	// public UserCommonInfo getUserCommonInfoById(int userid) {
	// UserCommonInfo result = (UserCommonInfo) factory.getCurrentSession()
	// .get(UserCommonInfo.class, userid);
	// return result;
	// }
	//
	// @Override
	// public boolean improveUserInfo(int userid, String emname, String name,
	// String description) {
	// try {
	// UserCommonInfo user = new UserCommonInfo();
	// user.setId(userid);
	// user.setEmname(emname);
	// user.setHead("");
	// user.setName(name);
	// factory.getCurrentSession().update(user);
	// return true;
	// } catch (Exception e) {
	// return false;
	// }
	// }
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<UserCommonInfo> getUserInfoByName(String name) {
	// String queryString = "from UserCommonInfo u where u.name=?";
	// Query queryObject = factory.getCurrentSession()
	// .createQuery(queryString);
	// queryObject.setParameter(0, name);
	// return queryObject.list();
	// }
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<UserCommonInfo> getUserInfoByEmname(String emname) {
	// String queryString = "from UserCommonInfo u where u.emname=?";
	// Query queryObject = factory.getCurrentSession()
	// .createQuery(queryString);
	// queryObject.setParameter(0, emname);
	// return queryObject.list();
	// }
	//
	// @Override
	// public boolean updateUserHead(int userid, String path) {
	// try {
	// String queryString = "update UserCommonInfo u set u.head=? where u.id=?";
	// Query queryObject = factory.getCurrentSession().createQuery(
	// queryString);
	// queryObject.setParameter(0, path);
	// queryObject.setParameter(1, userid);
	// queryObject.executeUpdate();
	// return true;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return false;
	// }
	// }
	//
	//
	// @Override
	// public boolean insertUserPhoto(List<Photo> photos) {
	// for(Photo item : photos){
	// factory.getCurrentSession().persist(item);
	// }
	//
	// return true;
	// }
	//
	// @Override
	// public List<String> getUserPhoto(int album_id, int size) {
	// String queryString = "select url from Photo p where p.album_id=?";
	// Query queryObject = factory.getCurrentSession()
	// .createQuery(queryString);
	// queryObject.setParameter(0, album_id);
	// return queryObject.list();
	// }

	// @Override
	// public boolean insertUserPhoto(int userid, int album_id, String[] imgUrl,
	// String create_time) {
	// //更新user的相片数量
	// //向数据库插入photo
	// return false;
	// }

}
