package com.wxxiaomi.ebs.dao.inter;

import java.util.List;

import com.wxxiaomi.ebs.dao.bean.Comment;

public interface CommentDao {
	
	Comment getCommentById(int comment_id);

	List<Comment> getTopicComments(int topicId);
	
	int InsetComment(Comment comment);
	
	int deleteComment(int comment_id,int userid);
	
	List<Comment> getSomeOneReplys(int userid);
	
	List<Comment> getUserTopicComments(int userid);
	
	List<Comment> getUserDoReplys(int userid);
}
