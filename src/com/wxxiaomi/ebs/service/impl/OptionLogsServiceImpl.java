package com.wxxiaomi.ebs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.bean.OptionLogs;
import com.wxxiaomi.ebs.service.OptLogsService;

@Service
@Transactional
public class OptionLogsServiceImpl implements OptLogsService {

	@Resource
	SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<OptionLogs> getUserLogs(int userid) {
		System.out.println("userid:"+userid);
		String queryString = "from OptionLogs o where o.userid=? order by id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		return queryObject.list();
	}

	@Override
	public boolean starOption(int opt_id) {
		try {
			String queryString = "update OptionLogs o set o.start=o.start+1 where o.id=?";
			Query queryObject = factory.getCurrentSession().createQuery(
					queryString);
			queryObject.setParameter(0, opt_id);
			queryObject.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean insertOption(OptionLogs opt) {
		try {
			factory.getCurrentSession().save(opt);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
