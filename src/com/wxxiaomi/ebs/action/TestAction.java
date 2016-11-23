package com.wxxiaomi.ebs.action;


import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.bean.Topic;

@Controller
public class TestAction {

	
	private String message;
	private String par;
	private Topic topic;
	
	
	public String getMessage() {
		return message;
	}


	


	public void setPar(String par) {
		this.par = par;
	}





	public String msg(){
		System.out.println("msg");
//		System.out.println("par="+par);
		try {
			System.out.println("topic="+topic.toString());
//			String string = new String(par.getBytes("ISO-8859-1"),"UTF-8");
//			System.out.println("string="+par);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message = "测试json";
		return "result";
	}





	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
}
