//package com.wxxiaomi.ebs.service.impl;
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.hibernate.Query;
//import org.hibernate.SessionFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.wxxiaomi.ebs.dao.bean.Option;
//import com.wxxiaomi.ebs.dao.bean.Option2;
//import com.wxxiaomi.ebs.dao.bean.constant.Result;
//import com.wxxiaomi.ebs.dao.inter.OptionDao;
//import com.wxxiaomi.ebs.service.OptionService;
//
//@Service
//public class OptionServiceImpl  {
//
//	@Resource
//	OptionDao optionDao;
//	
//	
//	
//	
//	
//	
//	
//	
//
////	@Override
////	public boolean insertOption(Option option) {
////		factory.getCurrentSession().persist(option);
////		return true;
////	}
////
////	@SuppressWarnings("unchecked")
////	@Override
////	public List<Option> getUserOptions(int userid) {
////		String queryString = "from Option o where o.user_id=?";
////		Query queryObject = factory.getCurrentSession()
////				.createQuery(queryString);
////		queryObject.setParameter(0, userid);
////		List<Option> options= queryObject.list();
////		return options;
////	}
////
////	@Override
////	public List<Option2> getUserOptions2(int userid) {
////		// TODO Auto-generated method stub
////		return null;
////	}
//}
