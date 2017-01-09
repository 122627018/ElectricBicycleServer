package com.wxxiaomi.ebs.action;


import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.wxxiaomi.ebs.module.em.ImHelper;
import com.wxxiaomi.ebs.module.em.api.SendMessageAPI;
import com.wxxiaomi.ebs.module.em.comm.ClientContext;
import com.wxxiaomi.ebs.module.em.comm.HyphenateRestAPIFactory;
import com.wxxiaomi.ebs.module.em.comm.body.CommandMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.IMUserBody;
import com.wxxiaomi.ebs.module.em.comm.constant.MsgTargetType;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;


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
		System.out.println("1");
//		System.out.println(Thread.currentThread().getName());
//		Map<String,String> pars = new HashMap<String,String>();
//		ImHelper.getInstance().demo();
//		 CommandMessageBody cmdMsg = new CommandMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "admin", null, "I.m the command message from server");
		try{
		System.out.println("JsonNodeFactory.instance:"+JsonNodeFactory.instance);
//		JsonNodeFactory instance = JsonNodeFactory.instance;
//		HyphenateRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
		System.out.println("3");
//		SendMessageAPI message = (SendMessageAPI) factory.newInstance(HyphenateRestAPIFactory.SEND_MESSAGE_CLASS);
		System.out.println("4");
			 CommandMessageBody cmdMsg = new CommandMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "admin", null, "I.m the command message from server");
//			 message.sendMessage(cmdMsg);
			 ImHelper.getInstance().sendCommandMsg(cmdMsg);
		System.out.println("2");
//		BodyWrapper userBody = new IMUserBody("User001", "123456", "HelloWorld");
		System.out.println("3");
		state = "401";
		error = "token过期";
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "result";
	}





	
	
}
