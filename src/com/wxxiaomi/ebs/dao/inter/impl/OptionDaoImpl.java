package com.wxxiaomi.ebs.dao.inter.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.dao.bean.Option;
import com.wxxiaomi.ebs.dao.inter.OptionDao;

@Repository
@Transactional
public class OptionDaoImpl implements OptionDao{

	@Resource
	SessionFactory factory;
	
	@Override
	public int insertOption(Option option) {
		factory.getCurrentSession().persist(option);
		return 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Option> getUserOptions(int userid) {
		String queryString = "from Option o where o.user_id=?";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		List<Option> options= queryObject.list();
		return options;
	}

}
