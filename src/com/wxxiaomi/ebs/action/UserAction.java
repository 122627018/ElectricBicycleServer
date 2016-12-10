package com.wxxiaomi.ebs.action;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.action.base.BaseAction;
import com.wxxiaomi.ebs.dao.bean.Photo;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.service.TopicService;
import com.wxxiaomi.ebs.service.UserService;

@Controller
public class UserAction extends BaseAction{

	@Resource UserService service;
	@Resource TopicService topicService;
	
	public String username;
	public String password;
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
		state = 200;
		infos = "success";
		return "insertUserPhoto";
	}
	
	

	public String login(){	
		/**
		 * 登陆操作：
		 * 从数据库获取user
		 * 生成Token，放在header
		 * 返回
		 */
		Result login = service.Login(username, password);
		adapterResult(login);
		return "login";
	}
	
	public String register(){
//		System.out.println("register");
//		User user = service.registerUser(username, password);
//		if(user!=null){
//			infos = new Format_Login(user);
//			state = 200;
//		}else{
//			System.out.println("用户已存在");
//			state = 5000;
//			error = "用户已存在";
//		}
//		System.out.println(infos.toString());
		
		Result resgiter = service.Register(username, password);
		adapterResult(resgiter);
		return "register";
	}
	
	public String improveuserinfo(){
		System.out.println("improveuserinfo");
//		service.improveUserInfo(username, password);
//		boolean result = service.improveUserInfo(userid, emname, name, description);
//		infos = result;
		return "inituserinfo";
	}
	
	public String infosbyems(){
		System.out.println("getinfosbyems");
		String[] split = emnamelist.split("<>");
		List<String> asList = Arrays.asList(split);
//		List<UserCommonInfo> users = service.getUserListByEMUsername(asList);
//		infos = new Format_InitUserData(users);
//		state = 200;
		adapterResult(service.getUserInfosByEms(asList));
		return "infosbyems";
	}
	
	public String userinfobyname(){
//		System.out.println("getuserinfobyname");
//		List<UserCommonInfo> users = service.getUserInfoByName(name);
//		System.out.println("users.size():"+users.size());
//		state=200;
//		error = "";
//		infos = new Format_InitUserData(users);
		adapterResult(service.getUserInfosByName(name));
		return "userinfobyname";
	}
	
	public String optionlog(){
//		long startTime=System.currentTimeMillis();
//		System.out.println("optionlog");
//		List<OptionLogs> userLogs = optService.getUserLogs(userid);
//		infos = userLogs;
//		System.out.println(userLogs.size());
//		state = "200";
//		return "optionlog";
//		Map<Integer,Option> commentMap = new HashMap<Integer,Option>();
//		Map<Integer,Option> topicMap = new HashMap<Integer,Option>();
//		List<Option> options = optionService.getUserOptions(userid);
//		
//		for(Option option : options){
//			JsonConfig jsonConfig = new JsonConfig();  
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
//			int type = option.getObj_type();
//			switch (type) {
//			case OptionType.FOOT_PRINT:
//				break;
//			case OptionType.PHOTO_PUBLISH: //更新相册
//				break;
//			case OptionType.TOPIC_COMMENT://话题评论
//				//map.put(option.getParent_id(), option);
//				//取出评论
//				Comment comment = topicService.getCommentById(option.getObj_id());
//				//取出话题
//				Topic topic = topicService.getTopicById(option.getParent_id());
//				System.out.println(topic.toString());
//				option.setJson_obj(JSONObject.fromObject(comment,jsonConfig).toString());
//				option.setJson_parent(JSONObject.fromObject(topic,jsonConfig).toString());
//				
////				commentMap.put(option.getObj_id(), option);
////				topicMap.put(option.getParent_id(), option);
//				break;
//			case OptionType.TOPIC_PUBLISH://话题发布
//				Topic t = topicService.getTopicById(option.getObj_id());
//				option.setJson_obj(JSONObject.fromObject(t,jsonConfig).toString());
////				topicMap.put(option.getObj_id(), option);
//				break;
//			}
//		}
////		Set<Integer> commentKeySet = commentMap.keySet();
////		Set<Integer> topicKeySet = topicMap.keySet();
////		List<Topic> topics = topicService.getTopics(topicKeySet);
////		List<Comment> comments = topicService.getComments(commentKeySet);
////		for(Topic t: topics){
////			commentMap.get(t.getId());
////		}
////		for(Comment c:comments){
////			
////		}
//		state = 200;
//		infos = options;
//		long endTime=System.currentTimeMillis();
//		 float excTime=(float)(endTime-startTime)/1000;
//	       System.out.println("获取用户动态所耗费的执行时间："+excTime+"s");
		adapterResult(service.UserOptionLog(userid));
		return "optionlog";
	}
	






}
