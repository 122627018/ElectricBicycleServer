package com.wxxiaomi.ebs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.bean.Option;
import com.wxxiaomi.ebs.service.OptionService;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {

	@Resource
	SessionFactory factory;
	@Override
	public boolean insertOption(Option option) {
		factory.getCurrentSession().persist(option);
		return true;
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
