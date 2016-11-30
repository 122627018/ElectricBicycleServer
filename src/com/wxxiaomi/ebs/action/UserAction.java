package com.wxxiaomi.ebs.action;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.UserCommonInfo;
import com.wxxiaomi.ebs.bean.format.Format_InitUserData;
import com.wxxiaomi.ebs.bean.format.Format_Login;
import com.wxxiaomi.ebs.service.UserService;

@Controller
public class UserAction {

	@Resource UserService service;
	
	public String username;
	public String password;
	Object infos;
	public String state;
	public String error;
	public String name;
	
	public String emnamelist;

	private String description;

	private String emname;

	private int userid;
	
	
	public String getState() {
		return state;
	}


	public String getError() {
		return error;
	}


	public Object getInfos() {
		return infos;
	}

	public String login(){	
		User user = service.Login(username, password);
		if(user!=null){
			System.out.println(user.toString());
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
	
//	public String userinfobyemname(){
//		System.out.println("getuserinfobyemname");
//		List<UserCommonInfo> users = service.getUserInfoByEmname(emname);
//		infos = new Format_InitUserData(users);
//		return "getuserinfobyname";
//	}
}
