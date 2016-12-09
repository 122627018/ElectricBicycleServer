package com.wxxiaomi.ebs.action;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import com.sun.jmx.snmp.Timestamp;
import com.wxxiaomi.ebs.bean.Comment;
import com.wxxiaomi.ebs.bean.Option;
import com.wxxiaomi.ebs.bean.Photo;
import com.wxxiaomi.ebs.bean.Topic;
import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.UserCommonInfo;
import com.wxxiaomi.ebs.bean.constant.OptionType;
import com.wxxiaomi.ebs.bean.format.Format_InitUserData;
import com.wxxiaomi.ebs.bean.format.Format_Login;
import com.wxxiaomi.ebs.service.OptionService;
import com.wxxiaomi.ebs.service.TopicService;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.util.DateJsonValueProcessor;
import com.wxxiaomi.ebs.util.JsonDateValueProcessor;
import com.wxxiaomi.ebs.util.jwt.Jwt;

@Controller
public class UserAction{

	@Resource UserService service;
//	@Resource OptLogsService optService;
	@Resource TopicService topicService;
	@Resource OptionService optionService;
	
	public String username;
	public String password;
	Object infos;
	public String state;
	public String error = "";
	public String name;
	
	public String emnamelist;
	private String description;
	private String emname;
	public int userid;
	
	public int album_id;
	public String imgs;
	
	public String insertUserPhoto(){
		String[] split = imgs.split("#");
		for(String img : split){
			Photo photo = new Photo();
			photo.setAlbum_id(album_id);
			photo.setCreate_time(new Date());
			photo.setUrl(img);
		}
		state = "200";
		infos = "success";
		return "insertUserPhoto";
	}
	
	
	
	

	public String login(){	
		User user = service.Login(username, password);
		if(user!=null){
			System.out.println(user.toString());
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, Object> payload = new HashMap<String, Object>();
			Date date = new Date();
			payload.put("uid", "291969452");// 用户id
			payload.put("iat", date.getTime());// 生成时间:当前
			payload.put("ext", date.getTime() + 2000 * 60 * 60);// 过期时间2小时
			String token = Jwt.createToken(payload);
			response.setHeader("token", token);
			state = "200";
			infos = new Format_Login(user);
		}else{
			state = "5000";
			error = "账号或者密码出错";
		}
		return "login";
	}
	
	public String register(){
		System.out.println("register");
		User user = service.registerUser(username, password);
		if(user!=null){
			infos = new Format_Login(user);
			state = "200";
		}else{
			System.out.println("用户已存在");
			state = "5000";
			error = "用户已存在";
		}
		System.out.println(infos.toString());
		return "register";
	}
	
	public String improveuserinfo(){
		System.out.println("improveuserinfo");
//		service.improveUserInfo(username, password);
		boolean result = service.improveUserInfo(userid, emname, name, description);
		infos = result;
		return "inituserinfo";
	}
	
	public String infosbyems(){
		System.out.println("getinfosbyems");
		String[] split = emnamelist.split("<>");
		List<String> asList = Arrays.asList(split);
		List<UserCommonInfo> users = service.getUserListByEMUsername(asList);
		infos = new Format_InitUserData(users);
		state = "200";
		return "infosbyems";
	}
	
	public String userinfobyname(){
		System.out.println("getuserinfobyname");
		List<UserCommonInfo> users = service.getUserInfoByName(name);
		System.out.println("users.size():"+users.size());
		state="200";
		error = "";
		infos = new Format_InitUserData(users);
		return "userinfobyname";
	}
	
	public String optionlog(){
		long startTime=System.currentTimeMillis();
//		System.out.println("optionlog");
//		List<OptionLogs> userLogs = optService.getUserLogs(userid);
//		infos = userLogs;
//		System.out.println(userLogs.size());
//		state = "200";
//		return "optionlog";
//		Map<Integer,Option> commentMap = new HashMap<Integer,Option>();
//		Map<Integer,Option> topicMap = new HashMap<Integer,Option>();
		List<Option> options = optionService.getUserOptions(userid);
		
		for(Option option : options){
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
			int type = option.getObj_type();
			switch (type) {
			case OptionType.FOOT_PRINT:
				break;
			case OptionType.PHOTO_PUBLISH: //更新相册
				break;
			case OptionType.TOPIC_COMMENT://话题评论
				//map.put(option.getParent_id(), option);
				//取出评论
				Comment comment = topicService.getCommentById(option.getObj_id());
				//取出话题
				Topic topic = topicService.getTopicById(option.getParent_id());
				System.out.println(topic.toString());
				option.setJson_obj(JSONObject.fromObject(comment,jsonConfig).toString());
				option.setJson_parent(JSONObject.fromObject(topic,jsonConfig).toString());
				
//				commentMap.put(option.getObj_id(), option);
//				topicMap.put(option.getParent_id(), option);
				break;
			case OptionType.TOPIC_PUBLISH://话题发布
				Topic t = topicService.getTopicById(option.getObj_id());
				option.setJson_obj(JSONObject.fromObject(t,jsonConfig).toString());
//				topicMap.put(option.getObj_id(), option);
				break;
			}
		}
//		Set<Integer> commentKeySet = commentMap.keySet();
//		Set<Integer> topicKeySet = topicMap.keySet();
//		List<Topic> topics = topicService.getTopics(topicKeySet);
//		List<Comment> comments = topicService.getComments(commentKeySet);
//		for(Topic t: topics){
//			commentMap.get(t.getId());
//		}
//		for(Comment c:comments){
//			
//		}
		state = "200";
		infos = options;
		long endTime=System.currentTimeMillis();
		 float excTime=(float)(endTime-startTime)/1000;
	       System.out.println("获取用户动态所耗费的执行时间："+excTime+"s");
		return "optionlog";
	}
	

	public String getState() {
		return state;
	}


	public String getError() {
		return error;
	}


	public Object getInfos() {
		return infos;
	}





}
