package com.wxxiaomi.ebs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxxiaomi.ebs.dao.bean.Locat;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.dao.inter.LocatDao;
import com.wxxiaomi.ebs.service.MapService;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.util.GeoHashUtil;

@Service
public class MapServiceImpl implements MapService {

	@Resource
	UserService userService;
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
		locatDao.savaLocation(userid, geo);
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

		int flag = locatDao.savaLocation(userid, geo);
		if (flag == 0) {
		}
		return new Result(200, "", "success");
	}

	@Override
	public Result collectLocat(int userid, String geo, String locat_tag) {
		// 在某个范围之内，用户在N天之内不能重复记录这个区域
		// 2公里-1个月
		// 20公里-10天
		// 100公里-5天
		// 500公里-1天
		// 在N(1)个小时之内只能记录一次(客户端判断,服务器也需要判断)

		// 先从数据库取出最近一次地点记录(地点和时间)
		//进行规则比较,如果不通过,返回错误码
		return null;
	}

}
