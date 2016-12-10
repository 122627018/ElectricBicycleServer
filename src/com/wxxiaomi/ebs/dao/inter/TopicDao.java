package com.wxxiaomi.ebs.dao.inter;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.Topic;

public interface TopicDao {

	List<Topic> getTopics(int start);
	
	int InsetTopic(Topic topic);
	
	int deleteTopic(int topicId);
	
	List<Topic> getTopicsByUid(int userid);
	
	Topic getTopicById(int topicId);
	
}
