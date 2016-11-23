package com.wxxiaomi.ebs.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.bean.Comment;
import com.wxxiaomi.ebs.bean.Topic;
import com.wxxiaomi.ebs.bean.UserCommonInfo;
import com.wxxiaomi.ebs.service.TopicService;

@Controller
public class TopicAjaxAction {

	@Resource
	TopicService service;
	private int start = 0;
	private String callback;
	private int topicId;
	
	//以下参数是提交topic的
	private int userid;
	private String content;
	private String time;
	private String pics;
	
	private int topic_id;
  

	private  String content1;
    private int from_uid;
    private String from_nick ;
    private String  from_head ;
    private int to_uid ;
    private String  to_unick;
 
	
	
	public String publishComment(){
		System.out.println( "publishComment [service=" + service + ", topic_id=" + topic_id
				+ ", content1=" + content1 + ", from_uid=" + from_uid
				+ ", from_nick=" + from_nick + ", from_head=" + from_head
				+ ", to_uid=" + to_uid + ", to_unick=" + to_unick + "]");
		Comment comment = new Comment(0, topic_id, content1, from_uid, from_nick, from_head, to_uid, to_unick);
		service.publishComment(comment);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=utf-8");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "200");
		params.put("error", "");
		params.put("infos", "success");
		JSONObject array = JSONObject.fromObject(params);
		if (callback != null) {
			try {
				String result = callback + "(" + array.toString() + ")";
				System.out.println("返回的json="+result);
				response.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public String getComment(){
		System.out.println("getComment->topicId="+topicId+",callback="+callback);
		List<Comment> list = service.getTopicComent(topicId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=utf-8");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "200");
		params.put("error", "");
		params.put("infos", list);
		JSONObject array = JSONObject.fromObject(params);
		System.out.println("返回的array="+array.toString());
		if (callback != null) {
			try {
				String result = callback + "(" + array.toString() + ")";
				System.out.println("返回的json="+result);
				response.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String getMyTopic(){
		System.out.println("getMyTopic->userid="+userid+",callback="+callback);
		List<Topic> list = service.getTopicByUserid(userid);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=utf-8");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "200");
		params.put("error", "");
		params.put("infos", list);
		JSONObject array = JSONObject.fromObject(params);
		System.out.println("返回的array="+array.toString());
		if (callback != null) {
			try {
				String result = callback + "(" + array.toString() + ")";
				System.out.println("返回的json="+result);
				response.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String submitTopic(){
		System.out.println("submitTopic->userid="+userid+"-content="+content+"-timeing="+time+"-pics="+pics);
		UserCommonInfo userInfo = new UserCommonInfo();
		userInfo.setId(userid);
		Topic t = new Topic();
		t.setContent(content);
		t.setPics(pics);
//		t.setTime(time);
		t.setUserCommonInfo(userInfo);
		service.publishTopic(t);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=utf-8");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "200");
		params.put("error", "");
		params.put("infos", "success");
		JSONObject array = JSONObject.fromObject(params);
		if (callback != null) {
			try {
				String result = callback + "(" + array.toString() + ")";
				response.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getTopic() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=UTF-8");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "200");
		params.put("error", "");
		params.put("infos", service.getTopicById(topicId));
		JSONObject array = JSONObject.fromObject(params);
		if (callback != null) {
			try {
				String result = callback + "(" + array.toString() + ")";
				response.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String list() {
		System.out.println("list->start=" + start);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/text;charset=UTF-8");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("state", "200");
		params.put("error", "");
		params.put("infos", service.getTopics(start));
		// 声明JSONArray对象并输入JSON字符串
		JSONObject array = JSONObject.fromObject(params);
		if (callback != null) {
			try {
				String result = callback + "(" + array.toString() + ")";
				response.getWriter().write(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public void setService(TopicService service) {
		this.service = service;
	}

	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
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
	
	
	
	

}
