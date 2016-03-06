package com.wxxiaomi.ebs.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wxxiaomi.ebs.engine.MapEngine;
import com.wxxiaomi.ebs.engine.UserEngine;
import com.wxxiaomi.ebs.util.JsonUtil;

public class ActionServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		Map<String, Object> outMap = new HashMap<String, Object>();
		if ("login".equals(action)) {
			//登录操作
			System.out.println("action=login");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
//			String isFirst = request.getParameter("isfirst");
//			System.out.println("isFirst="+isFirst);
			outMap.putAll(UserEngine.Login(username, password));
		}else if("checkphone".equals(action)){
			String phone  = request.getParameter("phone");
			outMap.putAll(UserEngine.checkPhone(phone));
		}else if("register".equals(action)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			System.out.println("username="+username+"--password="+password+"--name="+name);
			outMap.putAll(UserEngine.Register(username, password,name));
		}else if("getnearby".equals(action)){//请求附近的人
			System.out.println("getnearby");
			double latitude = Double.valueOf(request.getParameter("latitude"));
			double longitude = Double.valueOf(request.getParameter("longitude"));
			int userid = Integer.valueOf(request.getParameter("userid"));
			//取得经纬度之后,需要通过把经纬度GeoHash编码，存到数据库中
			outMap.putAll(MapEngine.getNearByPerson(userid,latitude,longitude));
		}else if("inituserinfo".equals(action)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			outMap.putAll(UserEngine.initUserInfo(username, password));
		}
		JsonUtil.renderJson(response, outMap);
	}

}
