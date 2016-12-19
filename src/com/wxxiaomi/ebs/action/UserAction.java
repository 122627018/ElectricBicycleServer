package com.wxxiaomi.ebs.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.action.base.BaseAction;
import com.wxxiaomi.ebs.dao.bean.Photo;
import com.wxxiaomi.ebs.dao.bean.constant.Result;
import com.wxxiaomi.ebs.service.TopicService;
import com.wxxiaomi.ebs.service.UserService;
import com.wxxiaomi.ebs.util.MyUtils;

@Controller
@Scope("prototype")
public class UserAction extends BaseAction{

	@Resource UserService service;
	@Resource TopicService topicService;
	

	
	public String emnamelist;
	public int userid;
	
	public int album_id;
	public String imgs;
	
	public String long_token;
	public String phoneId;
	
	
	
	
	public String userinfo;
	
	
	
	public String friends;
	public String updateuserfriends(){
		try{
		System.out.println("updateuserfriends");
		System.out.println("freinds:"+friends);
		List<String> emnames = new ArrayList<String>();
		List<Date> times = new ArrayList<Date>();
		if(friends!=null){
			String[] split = friends.split("#");
			for(String itme : split){
				String[] split2 = itme.split("=");
				emnames.add(split2[0]);
				if(split2.length>1){
					times.add(MyUtils.StrToDate(split2[1]));
				}
				
			}
		}
		adapterResult(service.updateUserFriends(emnames, times));
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return "updateuserfriends";
	}
	
	public String nickname;
	public String description;
	public String emname;
	public String city;
	public String cover;
	public String avatar;
	public int sex = 1;
	public String create_time;
	
	public String updateuserinfo(){
		adapterResult(service.updateUserInfo(userid,nickname,avatar,emname,description,city,cover,sex,create_time));
		return "updateuserinfo";
	}
	
	public String longToken(){
		try{
		adapterResult(service.LongToken(long_token, phoneId));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "longToken";
	}


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
	
	public String username;
	public String password;
	public String uniqueNum;
	public String login(){	
		/**
		 * 登陆操作：
		 * 从数据库获取user
		 * 生成Token，放在header
		 * 返回
		 */
		Result login = service.Login(username, password,uniqueNum);
		adapterResult(login);
		return "login";
	}
	
	public String register(){
		Result resgiter = service.Register(username, password);
		adapterResult(resgiter);
		return "register";
	}
	
	public String infosbyems(){
		System.out.println("getinfosbyems");
		String[] split = emnamelist.split("<>");
		List<String> asList = Arrays.asList(split);
		adapterResult(service.getUserInfosByEms(asList));
		return "infosbyems";
	}
	
	public String name;
	public String userinfobyname(){
		adapterResult(service.getUserInfosByName(name));
		return "userinfobyname";
	}
	
	public String optionlog(){
		System.out.println("userid:"+userid);
		adapterResult(service.UserOptionLog(Integer.valueOf(userid)));
		return "optionlog";
	}

	






}
