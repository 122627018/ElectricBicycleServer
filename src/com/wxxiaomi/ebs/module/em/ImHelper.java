package com.wxxiaomi.ebs.module.em;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.wxxiaomi.ebs.module.em.api.IMUserAPI;
import com.wxxiaomi.ebs.module.em.api.SendMessageAPI;
import com.wxxiaomi.ebs.module.em.comm.ClientContext;
import com.wxxiaomi.ebs.module.em.comm.HyphenateRestAPIFactory;
import com.wxxiaomi.ebs.module.em.comm.body.CommandMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.IMUserBody;
import com.wxxiaomi.ebs.module.em.comm.constant.MsgTargetType;
import com.wxxiaomi.ebs.module.em.comm.utils.ResponseUtils;
import com.wxxiaomi.ebs.module.em.comm.wrapper.ResponseWrapper;

public class ImHelper {
	private boolean isInit = false;
	static ImHelper INSTANCE;
	HyphenateRestAPIFactory factory;
	 IMUserAPI user;
//     ChatMessageAPI chat = (ChatMessageAPI) factory.newInstance(HyphenateRestAPIFactory.MESSAGE_CLASS);
//     FileAPI file = (FileAPI) factory.newInstance(HyphenateRestAPIFactory.FILE_CLASS);
     SendMessageAPI message;
//     ChatGroupAPI chatgroup = (ChatGroupAPI) factory.newInstance(HyphenateRestAPIFactory.CHATGROUP_CLASS);
//     ChatRoomAPI chatroom = (ChatRoomAPI) factory.newInstance(HyphenateRestAPIFactory.CHATROOM_CLASS);
     
     private ImHelper(){};
      
     public static ImHelper getInstance(){
    	 if(INSTANCE==null){
    		 synchronized (ImHelper.class) {
    			 INSTANCE = new ImHelper();
			}
    	 }
//    	 System.out.println("INSTANCE.isInit:"+INSTANCE.isInit);
    	 if(!INSTANCE.isInit){
//    		 System.out.println(" INSTANCE.init();");
    		 INSTANCE.isInit = true;
    		 INSTANCE.init();
    		 
    	 }
//    	 System.out.println("INSTANCE.isInit:"+INSTANCE.isInit);
    	 return INSTANCE;
     }
     
     public void init(){
    	 System.out.println("ImHelper初始化了");
    	 try{
    	 factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
    	 message = (SendMessageAPI) factory.newInstance(HyphenateRestAPIFactory.SEND_MESSAGE_CLASS);
    	 user = (IMUserAPI) factory.newInstance(HyphenateRestAPIFactory.USER_CLASS);
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }
     }
     
     public boolean sendCommandMsg(CommandMessageBody cmdMsg){
    	 System.out.println("sendCommandMsg");
    	 try{
    		 System.out.println(message==null);
    		 ResponseWrapper response = (ResponseWrapper) INSTANCE.message.sendMessage(cmdMsg);
			return response.getResponseStatus()==200;
    	 }catch (Exception e) {
			// TODO: handle exception
    		 e.printStackTrace();
    		 return false;
		}
     }
     
     public boolean registerUser(IMUserBody usr){
    	 ResponseWrapper response = (ResponseWrapper)user.createUser(usr);
    	 return response.getResponseStatus()==200;
     }
     
//     public void demo(){
//    	 CommandMessageBody cmdMsg = new CommandMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "admin", null, "I.m the command message from server");
//    	 System.out.println("asddsa");
//    	 sendCommandMsg(cmdMsg);
//     }
     
}
