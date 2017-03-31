package com.wxxiaomi.ebs.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxxiaomi.ebs.dao.bean.FootPrint;
import com.wxxiaomi.ebs.dao.bean.Locat;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.dao.bean.format.FootPrintDetail;
import com.wxxiaomi.ebs.dao.bean.format.FootPrintGet;
import com.wxxiaomi.ebs.dao.inter.LocatDao;
import com.wxxiaomi.ebs.dao.inter.UserDao;
import com.wxxiaomi.ebs.service.MapService;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.support.AppCode;
import com.wxxiaomi.ebs.util.GeoHashUtil;
import com.wxxiaomi.ebs.util.MyUtils;

@Service
public class MapServiceImpl implements MapService {

	@Resource
	UserDao userDao;
	@Resource
	LocatDao locatDao;

	/**
	 * 取出附近的人
	 * 
	 * @param userid
	 * @param geo
	 * @return
	 */
	@Override
	public Result getNearByPerson(int userid, String geo) {
		// locatDao.savaLocation(userid, geo);
		savaLocat(userid, geo);
		List<Locat> near = locatDao.getNear(userid, geo);
		if (near != null) {
			for (Locat item : near) {
				String geoo = item.getGeo();
				double[] decode = GeoHashUtil.decode(geoo);
				item.setPoint(decode);
			}
		}
		return new Result(200, "", near);
	}

	@Override
	public Result savaLocation(int userid, String geo) {
		// Locat locat = new Locat();
		// int flag = locatDao.savaLocation(userid, geo);
		// if (flag == 0) {
		//
		// }
		savaLocat(userid, geo);
		return new Result(200, "", "success");
	}

	public void savaLocat(int userid, String geo) {
		// Locat nearlyPersonLocat = locatDao.getNearlyPersonLocat(userid);
		// if(nearlyPersonLocat!=null){
		//
		// }
		Locat locat = new Locat();
		locat.setGeo(geo);
		UserCommonInfo info = new UserCommonInfo();
		info.setId(userid);
		locat.setTime(new Date());
		locat.setUserCommonInfo(info);
		locatDao.savaLocat(locat);
	}

	@Override
	public Result collectLocat(int userid, String geo, String locat_tag,
			String content, String picture) {
		// 在某个范围之内，用户在N天之内不能重复记录这个区域
		// 2公里-1个月
		// 20公里-10天
		// 100公里-5天
		// 500公里-1天
		// 在N(1)个小时之内只能记录一次(客户端判断,服务器也需要判断)

		// 先从数据库取出最近一次地点记录(地点和时间)
		// 进行规则比较,如果不通过,返回错误码
		try {
			Result result = null;
//			Locat nearlyLocat = locatDao.getNearlyPersonLocat(userid);
			FootPrint userNearlyFoot = locatDao.getUserNearlyFoot(userid);
			if (userNearlyFoot != null) {
				double faraway = GeoHashUtil.demo(userNearlyFoot.getLocat().getGeo(), geo);
				int day = MyUtils.daysOfTwo(userNearlyFoot.getLocat().getTime(), new Date());
				if (faraway >= 0 && faraway < 2) {
					if (day < 30) {
						System.out.println("2公里范围内，一个月时间内记录过足迹");
//						 nearlyLocat.setGeo(geo);
//						userNearlyFoot.getLocat().setTime(time)
						result = new Result(AppCode.ERROR_LBS_PUBLIC_REPLY, "您在最近这段时间内已在此记录足迹，无须重复记录", "");
						return result;
					}
				} else if (faraway >= 2 && faraway < 20) {
					if (day < 10) {
						System.out.println("20公里范围内，10天时间内记录过足迹");
						result = new Result(AppCode.ERROR_LBS_PUBLIC_REPLY, "您在最近这段时间内已在此记录足迹，无须重复记录", "");
						return result;
					}
				} else if (faraway >= 20 && faraway < 100) {
					if (day < 5) {
						System.out.println("100公里范围内，5天时间内记录过足迹");
						result = new Result(AppCode.ERROR_LBS_PUBLIC_REPLY, "您在最近这段时间内已在此记录足迹，无须重复记录", "");
						return result;
					}
				} else if (faraway >= 100 && faraway < 500) {
					if (day < 2) {
						System.out.println("500公里范围内，2天时间内记录过足迹");
						result = new Result(AppCode.ERROR_LBS_PUBLIC_REPLY, "您在最近这段时间内已在此记录足迹，无须重复记录", "");
						return result;
					}
				} 
			}
			UserCommonInfo info = new UserCommonInfo();
			info.setId(userid);
			Locat locat = new Locat();
			locat.setUserCommonInfo(info);
			locat.setGeo(geo);
			locat.setTime(new Date());
			locatDao.savaLocat(locat);
			FootPrint foot = new FootPrint();
			foot.setContent(content);
			foot.setPicture(picture);
			foot.setUserid(userid);
			foot.setLocat(locat);
			locatDao.savaFootPrint(foot);
			result = new Result(200, "", "success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(404, "未知错误", "");
		}
	}

	@Override
	public Result listFootPrint(int target_id) {
		List<FootPrintDetail> footPrintList = locatDao.footPrintList(target_id);
		System.out.println("footPrintList.size:"+footPrintList.size());
		UserCommonInfo userInfoById = userDao.getUserInfoById(target_id);
		FootPrintGet get = new FootPrintGet(footPrintList,userInfoById);
		return new Result(200, "", get);
	}

}
