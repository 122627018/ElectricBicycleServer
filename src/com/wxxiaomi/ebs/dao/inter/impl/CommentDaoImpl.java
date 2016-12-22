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

import com.wxxiaomi.ebs.dao.bean.Comment;
import com.wxxiaomi.ebs.dao.bean.format.OptionDetail;
import com.wxxiaomi.ebs.dao.inter.CommentDao;

@Repository
@Transactional
public class CommentDaoImpl implements CommentDao{

	@Resource
	SessionFactory factory;
	
	@Override
	public Comment getCommentById(int comment_id) {
		return (Comment) factory.getCurrentSession().get(Comment.class, comment_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getTopicComments(int topicId) {
		String queryString = "from Comment c where c.topic_id=? order by c.id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, topicId);
		return queryObject.list();
	}

	@Override
	public int InsetComment(Comment comment) {
		try {
			factory.getCurrentSession().save(comment);
			return comment.getId();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteComment(int comment_id, int userid) {
		try {
			Comment comment = (Comment) factory.getCurrentSession().get(Comment.class,
					comment_id);
			if (comment != null&&comment.getFrom_uid()==userid) {
				factory.getCurrentSession().delete(comment);
			}else{
				return 0;
			}
			return 1;
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getSomeOneReplys(int userid) {
		String queryString = "from Comment c where c.to_uid=? order by c.id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getUserTopicComments(int userid) {
		String queryString = "from Comment c where c.to_uid=? order by c.id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getUserDoReplys(int userid) {
		String queryString = "from Comment c where c.from_uid=? order by c.id desc";
		Query queryObject = factory.getCurrentSession()
				.createQuery(queryString);
		queryObject.setParameter(0, userid);
		return queryObject.list();
	}

	@Override
	public List<OptionDetail> getOptionDetail(List<OptionDetail> options) {
		Map<Integer,List<OptionDetail>> demo = new HashMap<Integer, List<OptionDetail>>();
//		List<Integer> ids = new ArrayList<Integer>();
//		Map<Integer,OptionDetail> temp = new HashMap<Integer,OptionDetail>();
		for(OptionDetail item : options){
//			System.out.println("item.getObj_id:"+item.getObj_id());
			if(item.getObj_id()!=0){
				
				if(demo.containsKey(item.getObj_id())){
					demo.get(item.getObj_id()).add(item);
				}else{
					List<OptionDetail> list = new ArrayList<OptionDetail>();
					list.add(item);
					demo.put(item.getObj_id(), list);
				}
			}
		
		}
		if(demo.size()!=0){
			String queryString = "from Comment c where c.id in(:list)";
			Query queryObject = factory.getCurrentSession()
					.createQuery(queryString);
			queryObject.setParameterList("list", demo.keySet());
			@SuppressWarnings("unchecked")
			List<Comment> comments = queryObject.list();
			for(Comment item : comments){
				List<OptionDetail> list = demo.get(item.getId());
				for(OptionDetail o:list){
					o.setReply(item.getContent());
				}
			}
		}
		
		return options;
		
	}

}
