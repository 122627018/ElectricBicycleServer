package com.wxxiaomi.ebs.action;


import org.springframework.stereotype.Controller;


@Controller
public class TestAction {

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



	public String msg(){
		System.out.println("msg");
		state = "401";
		error = "token过期";
		
		return "result";
	}





	
	
}
