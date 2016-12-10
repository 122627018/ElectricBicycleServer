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
		String queryString = "from Locat l where l.userCommonInfo.id!=? and geo LIKE ?";
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

}
