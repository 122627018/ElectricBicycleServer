package com.wxxiaomi.ebs.dao.inter.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.dao.bean.FootPrint;
import com.wxxiaomi.ebs.dao.bean.Locat;
import com.wxxiaomi.ebs.dao.bean.format.FootPrintDetail;
import com.wxxiaomi.ebs.dao.inter.LocatDao;

@Repository
@Transactional
public class LocatDaoImpl implements LocatDao {

	@Resource
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Locat> getNear(int userid, String geo) {
		List<Locat> result = new ArrayList<Locat>();
		geo = geo.substring(0, 5);
		//一个用户获取一条
		String queryString = "from Locat l where l.userCommonInfo.id!=? and geo LIKE ? "
				+ "and not exists (select 1 from Locat b where b.userCommonInfo.id = l.userCommonInfo.id and b.id > l.id)";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		queryObject.setParameter(1, "%" + geo + "%");
		result.addAll(queryObject.list());
		return result;
	}

//	@Override
//	public int savaLocation(int userid, String geo) {
//		String queryString1 = "from Locat l  where l.userCommonInfo.id=?";
//		Query query = factory.getCurrentSession().createQuery(queryString1);
//		query.setParameter(0, userid);
//		if (query.list().size() != 0) {
//			String queryString = "update Locat l set geo=? where l.userCommonInfo.id=?";
//			Query queryObject = factory.getCurrentSession().createQuery(
//					queryString);
//			queryObject.setParameter(0, geo);
//			queryObject.setParameter(1, userid);
//			queryObject.executeUpdate();
//			return 1;
//		}
//		return 0;
//	}
//
//	@Override
//	public int collectLocat(int userid, String geo, String locat_tag) {
//		// 在某个范围之内，用户在N天之内不能重复记录这个区域
//		// 2公里-1个月
//		// 20公里-10天
//		// 100公里-5天
//		// 500公里-1天
//		// 在N(1)个小时之内只能记录一次(客户端判断,服务器也需要判断)
//
//		// 先从数据库取出最近一次地点记录(地点和时间)
//
//		return 0;
//	}

	@Override
	public int savaFootPrint(FootPrint foot) {
		// 在某个范围之内，用户在N天之内不能重复记录这个区域
		// 2公里-1个月
		// 20公里-10天
		// 100公里-5天
		// 500公里-1天
		// 在N(1)个小时之内只能记录一次(客户端判断,服务器也需要判断)

		// 先从数据库取出最近一次地点记录(地点和时间)
//		factory.getCurrentSession().persist(foot.getLocat());
		factory.getCurrentSession().persist(foot);
		return foot.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FootPrintDetail> footPrintList(int userid) {
//		String queryString1 = "select f.id as foot_id,l.id as locat_id,f.userid,l.geo,l.time as create_time,f.content,f.picture from FootPrint f,Locat l where f.userid=? and f.locat.id=l.id";
		String queryString1 = "select new com.wxxiaomi.ebs.dao.bean.format.FootPrintDetail(f.userid,f.id,l.id,f.content,f.picture,l.time,l.geo) from FootPrint f,Locat l where f.userid=? and f.locat.id=l.id";
		Query query = factory.getCurrentSession().createQuery(queryString1);
		query.setParameter(0, userid);
		return (List<FootPrintDetail>)query.list();
	}

	@Override
	public Locat getNearlyPersonLocat(int userid) {
		// TODO Auto-generated method stub
		String queryString = "from Locat l where l.userCommonInfo.id=?"
				+ "and not exists (select 1 from Locat b where b.userCommonInfo.id = l.userCommonInfo.id and b.id > l.id)";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		if(queryObject.list().size()>0){
			return (Locat)queryObject.list().get(0);
		}
		return null;
	}

	@Override
	public int savaLocat(Locat locat) {
//		String queryString1 = "from Locat l  where l.userCommonInfo.id=?";
//		Query query = factory.getCurrentSession().createQuery(queryString1);
//		query.setParameter(0, userid);
//		if (query.list().size() != 0) {
//			String queryString = "update Locat l set geo=? where l.userCommonInfo.id=?";
//			Query queryObject = factory.getCurrentSession().createQuery(
//					queryString);
//			queryObject.setParameter(0, geo);
//			queryObject.setParameter(1, userid);
//			queryObject.executeUpdate();
//			return 1;
//		}
		factory.getCurrentSession().persist(locat);
		return locat.getId();
//		return 0;
	}

	@Override
	public FootPrint getUserNearlyFoot(int userid) {
		String queryString = "from FootPrint l where l.userid=? "
				+ "and not exists (select 1 from FootPrint b where b.userid = l.userid and b.id > l.id)";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		if (queryObject.list().size() > 0) {
			return (FootPrint)queryObject.list().get(0);
		} else {
			return null;
		}
	}

}
