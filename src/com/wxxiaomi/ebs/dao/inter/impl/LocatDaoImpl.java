package com.wxxiaomi.ebs.dao.inter.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.dao.bean.Locat;
import com.wxxiaomi.ebs.dao.inter.LocatDao;

@Repository
@Transactional
public class LocatDaoImpl implements LocatDao{

	@Resource
	SessionFactory factory;
	@SuppressWarnings("unchecked")
	@Override
	public List<Locat> getNear(int userid, String geo) {
		geo = geo.substring(0, 5);
		String queryString = "from Locat l where l.userCommonInfo.id!=? and geo LIKE ? " +
				"and not exists (select 1 from Locat b where b.userCommonInfo.id = l.userCommonInfo.id and b.id > l.id)";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		queryObject.setParameter(1, "%" + geo + "%");
		if (queryObject.list().size() > 0) {
			return queryObject.list();
		} else {
			return null;
		}
	}

	@Override
	public int savaLocation(int userid, String geo) {
		String queryString1 = "from Locat l  where l.userCommonInfo.id=?";
		Query query = factory.getCurrentSession()
				.createQuery(queryString1);
		query.setParameter(0, userid);
		if(query.list().size()!=0){
			String queryString = "update Locat l set geo=? where l.userCommonInfo.id=?";
			Query queryObject = factory.getCurrentSession()
					.createQuery(queryString);
			queryObject.setParameter(0, geo);
			queryObject.setParameter(1, userid);
			queryObject.executeUpdate();
			return 1;
		}
		return 0;
	}

	@Override
	public int collectLocat(int userid, String geo, String locat_tag) {
		//在某个范围之内，用户在N天之内不能重复记录这个区域
		//2公里-1个月
		//20公里-10天
		//100公里-5天
		//500公里-1天
		//在N(1)个小时之内只能记录一次(客户端判断,服务器也需要判断)
		
		//先从数据库取出最近一次地点记录(地点和时间)
		
		
		return 0;
	}

}
