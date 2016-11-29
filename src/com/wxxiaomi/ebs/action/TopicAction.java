package com.wxxiaomi.ebs.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.bean.Comment;
import com.wxxiaomi.ebs.bean.Topic;
import com.wxxiaomi.ebs.bean.UserCommonInfo;
import com.wxxiaomi.ebs.service.TopicService;

@Controller
public class TopicAction {

	@Resource
	TopicService service;
	private int start = 0;

	// 以下参数是提交topic的
	private int userid;
	private String content;
	private String pics;
	private String locat;
	private String locat_tag;


	private String content1;
	private int from_uid;
	private String from_nick;
	private String from_head;
	private int to_uid;
	private String to_unick;
	private int topicId;
	
	public String one(){
		System.out.println("getOneTopic->topicid:"+topicId);
		Topic topic = service.getTopicById(topicId);
		infos = topic;
		state = "200";
		return "one";
	}
	
	public String topicDelete(){
		boolean deleteTopic = service.deleteTopic(topicId);
		if(deleteTopic){
			state = "200";
			infos = "success";
		}else{
			state = "404";
			System.out.println("删除失败");
		}
		return "topicDelete";
	}

	public String commentList() {
		System.out.println("getComment->topicId=" + topicId);
		List<Comment> list = service.getTopicComent(topicId);
		state = "200";
		infos = list;
		return "commentList";
	}

	public String publishComment() {
		System.out.println("publishComment [service=" + service + ", topic_id="
				+ topicId + ", content1=" + content1 + ", from_uid=" + from_uid
				+ ", from_nick=" + from_nick + ", from_head=" + from_head
				+ ", to_uid=" + to_uid + ", to_unick=" + to_unick + "]");
		Comment comment = new Comment(0, topicId, content1, from_uid,
				from_nick, from_head, to_uid, to_unick);
		service.publishComment(comment);
		state = "200";
		infos = comment;
		return "publishComment";
	}

	public String list() {
		state = "200";
		infos = service.getTopics(start);
		return "list";
	}

	public String myTopic() {
		System.out.println(" myTopic()，userid:"+userid);
		try{
		List<Topic> list = service.getTopicByUserid(userid);
		System.out.println("list==null?"+list==null);
		//System.out.println(list.size());
		state = "200";
		infos = list;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "getMyTopic";
	}

	public String submitTopic() {

//		JSONObject fromObject = JSONObject.fromObject(topic);
//		Topic tt = (Topic)JSONObject.toBean(fromObject,Topic.class);
//		System.out.println("tt:"+tt.toString());
		UserCommonInfo userInfo = new UserCommonInfo();
		userInfo.setId(userid);
		Topic t = new Topic();
		t.setContent(content);
		System.out.println("pics:"+pics);
		t.setPics(pics);
		t.setTime(new Date());
		t.setCcount(0);
		t.setLocat(locat);
		t.setLocat_tag(locat_tag);
		t.setHot(0);
		t.setUserCommonInfo(userInfo);
		t.setTitle("");
		service.publishTopic(t);
		infos = "success";
		return "submitTopic";
	}

	private String state = "404";
	private String error = "";
	private Object infos;

	public String getState() {
		return state;
	}

	public String getError() {
		return error;
	}

	public Object getInfos() {
		return infos;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public void setFrom_uid(int from_uid) {
		this.from_uid = from_uid;
	}

	public void setFrom_nick(String from_nick) {
		this.from_nick = from_nick;
	}

	public void setFrom_head(String from_head) {
		this.from_head = from_head;
	}

	public void setTo_uid(int to_uid) {
		this.to_uid = to_uid;
	}

	public void setTo_unick(String to_unick) {
		this.to_unick = to_unick;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public void setService(TopicService service) {
		this.service = service;
	}

	public void setLocat(String locat) {
		this.locat = locat;
	}

	public void setLocat_tag(String locat_tag) {
		this.locat_tag = locat_tag;
	}


}
