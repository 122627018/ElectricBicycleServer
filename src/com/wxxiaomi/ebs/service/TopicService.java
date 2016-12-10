package com.wxxiaomi.ebs.service;

import java.util.List;
import java.util.Set;

import com.wxxiaomi.ebs.dao.bean.Comment;
import com.wxxiaomi.ebs.dao.bean.Topic;
import com.wxxiaomi.ebs.dao.bean.constant.Result;


public interface TopicService {
	
	
	
	/**
	 * 获取最近10条话题
	 * @return
	 */
	Result getTopics(int start);
	
	/**
	 * 发布一则话题
	 * @param topic
	 */
	Result publishTopic(int userid,String content,String pics,String[] locat_points,String locat_tag);
	
	/**
	 * 删除一则话题
	 * @param topicId
	 */
	Result deleteTopic(int topicId);
	
	/**
	 * 获取一个用户发布的话题
	 * @param userid
	 * @return
	 */
	Result getTopicByUserid(int userid);
	
	/**
	 * 根据id获取一则comment
	 * @param topicId
	 * @return
	 */
	Result getTopicById(int  topicId);
	
	Result getTopicComent(int topicId);
	
	/**
	 * 发表一则评论
	 * @return
	 */
	Result publishComment(int topicId, String content1, int from_uid,
			String from_nick, String from_head, int to_uid, String to_unick);
	
	/**
	 * 回复一则评论
	 * @param comment_id
	 * @return
	 */
//	boolean replyComment(int comment_id);
	
	/**
	 * 删除一则评论
	 * @param userid
	 * @param comment_id
	 * @return
	 */
	Result deleteComment(int userid,int comment_id);
	
	/**
	 * 获取回复某个用户的所有回复
	 * @param userid
	 * @return
	 */
	Result getUserReply(int userid);
	
	/**
	 * 获取某个用户所得到的所有评论
	 * @param userid
	 * @return
	 */
	Result getUserTopicComment(int userid);
	
	/**
	 * 获取某个用户发出去的所有评论(包括回复)
	 * @param useid
	 * @return
	 */
	Result getUserDoReply(int useid);
	
	Result getCommentById(int comment_id);

	
	Result getTopics(Set<Integer> ids);
	Result getComments(Set<Integer> ids);
}
