package com.wxxiaomi.ebs.dao.inter.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wxxiaomi.ebs.dao.bean.Topic;
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

}
