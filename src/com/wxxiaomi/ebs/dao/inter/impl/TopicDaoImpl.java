package com.wxxiaomi.ebs.dao.inter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.dao.bean.Option;
import com.wxxiaomi.ebs.dao.bean.Topic;
import com.wxxiaomi.ebs.dao.bean.format.OptionDetail;
import com.wxxiaomi.ebs.dao.inter.TopicDao;

@Repository
@Transactional
public class TopicDaoImpl implements TopicDao {

	@Resource
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopics(int start) {
		String queryString = "from Topic order by id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setFirstResult(start);
		queryObject.setMaxResults(5);
		// queryObje
		return (List<Topic>) queryObject.list();
	}

	@Override
	public int InsetTopic(Topic topic) {
		// TODO Auto-generated method stub
		try {
			factory.getCurrentSession().save(topic);
			return topic.getId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteTopic(int topicId) {
		try {
			Topic topic = (Topic) factory.getCurrentSession().get(Topic.class,
					topicId);
			if (topic != null) {
				factory.getCurrentSession().delete(topic);
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Topic> getTopicsByUid(int userid) {
		String queryString = "from Topic t where t.userCommonInfo.id=? order by t.id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		return queryObject.list();
	}

	@Override
	public Topic getTopicById(int topicId) {
		return (Topic) factory.getCurrentSession().get(Topic.class, topicId);
	}

	@Override
	public List<OptionDetail> getOptionDetail(List<Option> topicOption) {
		List<OptionDetail> results = new ArrayList<OptionDetail>();
		Map<Integer,List<Option>> demo = new HashMap<Integer, List<Option>>();
		for(Option item : topicOption){
			if(demo.containsKey(item.getParent_id())){
				System.out.println("需要查询的topicid:"+item.getParent_id());
				demo.get(item.getParent_id()).add(item);
			}else{
				List<Option> list = new ArrayList<Option>();
				list.add(item);
				demo.put(item.getParent_id(), list);
			}
		}
		System.out.println("demo.keySet().size:"+demo.keySet().size());
		if(demo.keySet().size()!=0){
			String queryString = "from Topic t where t.id in(:list)";
			Query queryObject = factory.getCurrentSession()
					.createQuery(queryString);
			queryObject.setParameterList("list", demo.keySet());
			@SuppressWarnings("unchecked")
			List<Topic> topics = queryObject.list();
			for(Topic item : topics){
//				Option option = demo.get(item.getId());
				List<Option> list = demo.get(item.getId());
				for(Option option: list){
					OptionDetail o = new OptionDetail(option.getId(), option.getUser_id()
							, option.getCreate_time(),  item.getPicss()[0], item.getContent()
							, item.getCcount(), item.getHot(), item.getLocat_tag(),item.getId()
							,option.getObj_id(),option.getType());
					results.add(o);
				}
			}
		}
		return results;
	}

}
