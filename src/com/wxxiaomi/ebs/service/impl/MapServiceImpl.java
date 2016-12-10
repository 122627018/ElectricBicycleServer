package com.wxxiaomi.ebs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.dao.bean.Locat;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.service.MapService;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.util.GeoHashUtil;

@Service
@Transactional
public class MapServiceImpl implements MapService {

	@Resource
	UserService userService;
	@Resource
	SessionFactory factory;

	/**
	 * 取出附近的人
	 * 
	 * @param userid
	 * @param geo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Locat> getNearByPerson(int userid, String geo) {
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
	public boolean savaLocation(int userid, String geo) {
		
//		String queryString1 = "from Locat l  where l.userCommonInfo.id=?";
//		Query query = factory.getCurrentSession()
//				.createQuery(queryString1);
//		query.setParameter(0, userid);
//		if(query.list().size()!=0){
//			String queryString = "update Locat l set geo=? where l.userCommonInfo.id=?";
//			Query queryObject = factory.getCurrentSession()
//					.createQuery(queryString);
//			queryObject.setParameter(0, geo);
//			queryObject.setParameter(1, userid);
//			queryObject.executeUpdate();
//		}else{
//			Locat locat = new Locat();
//			locat.setGeo(geo);
//			locat.setUserCommonInfo(userService.getUserCommonInfoById(userid));
//			factory.getCurrentSession().save(locat);
//			
//		}
		
		return true;
	}

	@Override
	public List<Locat> createNearByPeople(double latitude, double longitude) {
//		List<Locat> result = new ArrayList<Locat>();
//		try {
//			int[] list = { 20, 21, 22, 23, 24 };
//			for (int i = 0; i < list.length; i++) {
//				latitude += 0.0005;
//				longitude += 0.0005;
//				String encode = GeoHashUtil.encode(latitude, longitude);
//				UserCommonInfo userCommonInfoById = userService
//						.getUserCommonInfoById(list[i]);
//				double[] locate = { latitude, longitude };
//				boolean savaLocation = savaLocation(list[i], encode);
//				if (savaLocation) {
//					result.add(new Locat(userCommonInfoById, locate));
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//		return result;
		return null;

	};
}
