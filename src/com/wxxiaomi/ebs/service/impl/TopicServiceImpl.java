package com.wxxiaomi.ebs.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxxiaomi.ebs.dao.bean.Comment;
import com.wxxiaomi.ebs.dao.bean.Topic;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.dao.inter.TopicDao;
import com.wxxiaomi.ebs.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService {

	@Resource
	TopicDao topicDao;
	@Override
	public Result getTopics(int start) {
		List<Topic> topics = topicDao.getTopics(start);
		return new Result(200,"",topics);
	}



	@Override
	public Result getTopicByUserid(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getTopicById(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getTopicComent(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result getUserReply(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getUserTopicComment(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getUserDoReply(int useid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getCommentById(int comment_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getTopics(Set<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result getComments(Set<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Result publishTopic(int userid, String content, String pics,
			String[] locat_points, String locat_tag) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Result deleteTopic(int topicId) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Result publishComment(int topicId, String content1, int from_uid,
			String from_nick, String from_head, int to_uid, String to_unick) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Result deleteComment(int userid, int comment_id) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	@Resource
//	SessionFactory factory;
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Topic> getTopics(int start) {
//		// TODO Auto-generated method stub
//		String queryString = "from Topic order by id desc";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setFirstResult(start);
//		queryObject.setMaxResults(5);
//		// queryObje
//		return (List<Topic>) queryObject.list();
//	}
//
//	@Override
//	public int publishTopic(Topic topic) {
//		// TODO Auto-generated method stub
//		try {
//			factory.getCurrentSession().save(topic);
//			return topic.getId();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return 0;
//		}
//
//	}
//
//	@Override
//	public boolean deleteTopic(int topicId) {
//		// TODO Auto-generated method stub
//		try {
//			Topic topic = (Topic) factory.getCurrentSession().get(Topic.class,
//					topicId);
//			if (topic != null) {
//				factory.getCurrentSession().delete(topic);
//			}
//			return true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return false;
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Topic> getTopicByUserid(int userid) {
//		String queryString = "from Topic t where t.userCommonInfo.id=? order by t.id desc";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, userid);
//		return queryObject.list();
//	}
//
//	@Override
//	public Topic getTopicById(int topicId) {
//		return (Topic) factory.getCurrentSession().get(Topic.class, topicId);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Comment> getTopicComent(int topicId) {
//		String queryString = "from Comment c where c.topic_id=? order by c.id desc";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, topicId);
//		return queryObject.list();
//	}
//
//	@Override
//	public int publishComment(Comment comment) {
//		try {
//			factory.getCurrentSession().save(comment);
//			return comment.getId();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return 0;
//		}
//
//	}
//
//
//	@Override
//	public boolean deleteComment(int userid, int comment_id) {
//		try {
//			Comment comment = (Comment) factory.getCurrentSession().get(Topic.class,
//					comment_id);
//			if (comment != null&&comment.getFrom_uid()==userid) {
//				factory.getCurrentSession().delete(comment);
//			}else{
//				return false;
//			}
//			return true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return false;
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Comment> getUserReply(int userid) {
//		String queryString = "from Comment c where c.to_uid=? order by c.id desc";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, userid);
//		return queryObject.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Comment> getUserTopicComment(int userid) {
//		String queryString = "from Comment c where c.to_uid=? order by c.id desc";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, userid);
//		return queryObject.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Comment> getUserDoReply(int userid) {
//		String queryString = "from Comment c where c.from_uid=? order by c.id desc";
//		Query queryObject = factory.getCurrentSession()
//				.createQuery(queryString);
//		queryObject.setParameter(0, userid);
//		return queryObject.list();
//	}
//
//	@Override
//	public Comment getCommentById(int comment_id) {
//		// TODO Auto-generated method stub
//		return (Comment) factory.getCurrentSession().get(Comment.class, comment_id);
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Topic> getTopics(Set<Integer> ids) {
//		// TODO Auto-generated method stub
//		Object[] array = ids.toArray();
//		return factory.getCurrentSession().createQuery("from Topic t where t.id in(:ids)")
//		.setParameterList("ids", array)
//		.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Comment> getComments(Set<Integer> ids) {
//		Object[] array = ids.toArray();
//		return factory.getCurrentSession().createQuery("from Comment t where t.id in(:ids)")
//		.setParameterList("ids", array)
//		.list();
//	}

}
