package com.wxxiaomi.ebs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.wxxiaomi.ebs.dao.bean.Comment;
import com.wxxiaomi.ebs.dao.bean.Option;
import com.wxxiaomi.ebs.dao.bean.Topic;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.dao.bean.constant.OptionType;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.dao.inter.CommentDao;
import com.wxxiaomi.ebs.dao.inter.OptionDao;
import com.wxxiaomi.ebs.dao.inter.TopicDao;
import com.wxxiaomi.ebs.module.em.ImHelper;
import com.wxxiaomi.ebs.module.em.Person;
import com.wxxiaomi.ebs.module.em.api.SendMessageAPI;
import com.wxxiaomi.ebs.module.em.comm.ClientContext;
import com.wxxiaomi.ebs.module.em.comm.HyphenateRestAPIFactory;
import com.wxxiaomi.ebs.module.em.comm.body.CommandMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.IMUserBody;
import com.wxxiaomi.ebs.module.em.comm.constant.MsgTargetType;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.service.TopicService;
import com.wxxiaomi.ebs.util.GeoHashUtil;

@Service
public class TopicServiceImpl implements TopicService {

	@Resource
	TopicDao topicDao;
	@Resource
	OptionDao optionDao;
	@Resource
	CommentDao commentDao;
	
	@Override
	public Result getTopics(int start) {
		List<Topic> topics = topicDao.getTopics(start);
		return new Result(200,"",topics);
	}



	@Override
	public Result getTopicByUserid(int userid) {
		List<Topic> topics = topicDao.getTopicsByUid(userid);
		return new Result(200,"",topics);
	}

	@Override
	public Result getTopicById(int topicId) {
		Topic topic = topicDao.getTopicById(topicId);
		return new Result(200,"",topic);
	}

	@Override
	public Result getTopicComent(int topicId) {
		List<Comment> topicComments = commentDao.getTopicComments(topicId);
		return new Result(200,"",topicComments);
	}


	@Override
	public Result getUserReply(int userid) {
		List<Comment> topicComments = commentDao.getSomeOneReplys(userid);
		return new Result(200,"",topicComments);
	}

	@Override
	public Result getUserTopicComment(int userid) {
		List<Comment> topicComments = commentDao.getUserTopicComments(userid);
		return new Result(200,"",topicComments);
	}

	@Override
	public Result getUserDoReply(int useid) {
		List<Comment> topicComments = commentDao.getUserDoReplys(useid);
		return new Result(200,"",topicComments);
	}

	@Override
	public Result getCommentById(int comment_id) {
		Comment comment = commentDao.getCommentById(comment_id);
		return new Result(200,"",comment);
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
		UserCommonInfo userInfo = new UserCommonInfo();
		userInfo.setId(userid);
		Topic t = new Topic();
		t.setContent(content);
		System.out.println("pics:"+pics);
		t.setPics(pics);
		t.setTime(new Date());
		t.setCcount(0);
		String encode = GeoHashUtil.encode(Double.valueOf(locat_points[0]), Double.valueOf(locat_points[1]));
		t.setLocat(encode);
		t.setLocat_tag(locat_tag);
		t.setHot(0);
		t.setUserCommonInfo(userInfo);
		t.setTitle("");
		topicDao.InsetTopic(t);
		
		
		//记得记录
		Option o = new Option();
		o.setType(OptionType.TOPIC_PUBLISH);
		o.setParent_id(t.getId());
		o.setUser_id(userid);
		o.setCreate_time(new Date());
		optionDao.insertOption(o);
		return new Result(200,"","success");
	}



	@Override
	public Result deleteTopic(int topicId) {
		topicDao.deleteTopic(topicId);
		return new Result(200,"","success");
	}



	@Override
	public Result publishComment(int topicId, String content1, int from_uid,
			String from_nick, String from_head, int to_uid, String to_unick) {
		Comment comment = new Comment(0, topicId, content1, from_uid,
				from_nick, from_head, to_uid, to_unick);
		commentDao.InsetComment(comment);
		
		Option o = new Option();
		o.setUser_id(from_uid);
		o.setObj_id(comment.getId());
		o.setParent_id(topicId);
		o.setCreate_time(new Date());
		o.setType(OptionType.TOPIC_COMMENT);
		optionDao.insertOption(o);
		System.out.println("1");
		System.out.println("JsonNodeFactory.instance:"+JsonNodeFactory.instance);
		System.out.println("2");
//			System.out.println(Thread.currentThread().getName());
//			Map<String,String> pars = new HashMap<String,String>();
//			ImHelper.getInstance().demo();
		try{
//		HyphenateRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
//		System.out.println("3");
//		SendMessageAPI message = (SendMessageAPI) factory.newInstance(HyphenateRestAPIFactory.SEND_MESSAGE_CLASS);
		System.out.println("4");
			 CommandMessageBody cmdMsg = new CommandMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "admin", null, "I.m the command message from server");
			 ImHelper.getInstance().sendCommandMsg(cmdMsg);
//			
//			JsonNodeFactory instance = JsonNodeFactory.instance;
//			
//			System.out.println("2");
//			BodyWrapper userBody = new IMUserBody("User001", "123456", "HelloWorld");
			 System.out.println("5");
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
//			Person p = new Person();
		
		
		return new Result(200,"","");
	}



	@Override
	public Result deleteComment(int userid, int comment_id) {
		commentDao.deleteComment(comment_id, userid);
		return new Result(200,"","success");
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
