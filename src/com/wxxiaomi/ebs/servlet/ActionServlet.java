package com.wxxiaomi.ebs.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wxxiaomi.ebs.engine.CarEngine;
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
			outMap.putAll(UserEngine.Login(username, password));
		}else if("checkphone".equals(action)){
			
			String phone  = request.getParameter("phone");
			System.out.println("phone="+phone);
			outMap.putAll(UserEngine.checkPhone(phone));
		}else if("register".equals(action)){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("username="+username+"--password="+password);
			outMap.putAll(UserEngine.Register(username, password));
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
		}else if("getbicycleinfo".equals(action)){
			int id = Integer.valueOf(request.getParameter("bicycleid"));
			outMap.putAll(CarEngine.getCarInfos(id));
		}else if("bundbicycle".equals(action)){
			int userid = Integer.valueOf(request.getParameter("userid"));
			int carid = Integer.valueOf(request.getParameter("cardid"));
			System.out.println("bundbicycle->userid="+userid+"--carid="+carid);
			outMap.putAll(CarEngine.bundBicycle(userid, carid));
		}else if("improveuserinfo".equals(action)){
			String emname = request.getParameter("username");
			String name = request.getParameter("name");
			int userid = Integer.valueOf(request.getParameter("userid"));
			String description = request.getParameter("description");
			outMap.putAll(UserEngine.improveUserInfo(userid, emname, name, description));
		}
		JsonUtil.renderJson(response, outMap);
	}

}
