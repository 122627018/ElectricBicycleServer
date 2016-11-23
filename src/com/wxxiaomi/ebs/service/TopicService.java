package com.wxxiaomi.ebs.service;

import java.util.List;

import com.wxxiaomi.ebs.bean.Comment;
import com.wxxiaomi.ebs.bean.Topic;

public interface TopicService {
	
	
	
	/**
	 * 获取最近10条话题
	 * @return
	 */
	List<Topic> getTopics(int start);
	
	/**
	 * 发布一则话题
	 * @param topic
	 */
	boolean publishTopic(Topic topic);
	
	/**
	 * 删除一则话题
	 * @param topicId
	 */
	boolean deleteTopic(int topicId);
	
	/**
	 * 获取一个用户发布的话题
	 * @param userid
	 * @return
	 */
	List<Topic> getTopicByUserid(int userid);
	
	/**
	 * 根据id获取一则comment
	 * @param topicId
	 * @return
	 */
	Topic getTopicById(int  topicId);
	
	List<Comment> getTopicComent(int topicId);
	
	/**
	 * 发表一则评论
	 * @return
	 */
	boolean publishComment(Comment comment);
	
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
	boolean deleteComment(int userid,int comment_id);
	
	/**
	 * 获取回复某个用户的所有评论
	 * @param userid
	 * @return
	 */
	List<Comment> getUserReply(int userid);

}
