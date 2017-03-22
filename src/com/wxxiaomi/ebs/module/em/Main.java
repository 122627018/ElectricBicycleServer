package com.wxxiaomi.ebs.module.em;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minidev.json.JSONArray;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wxxiaomi.ebs.module.em.api.ChatGroupAPI;
import com.wxxiaomi.ebs.module.em.api.ChatMessageAPI;
import com.wxxiaomi.ebs.module.em.api.ChatRoomAPI;
import com.wxxiaomi.ebs.module.em.api.FileAPI;
import com.wxxiaomi.ebs.module.em.api.IMUserAPI;
import com.wxxiaomi.ebs.module.em.api.SendMessageAPI;
import com.wxxiaomi.ebs.module.em.comm.ClientContext;
import com.wxxiaomi.ebs.module.em.comm.HyphenateRestAPIFactory;
import com.wxxiaomi.ebs.module.em.comm.body.CommandMessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.IMUserBody;
import com.wxxiaomi.ebs.module.em.comm.body.MessageBody;
import com.wxxiaomi.ebs.module.em.comm.body.TextMessageBody;
import com.wxxiaomi.ebs.module.em.comm.constant.MsgTargetType;
import com.wxxiaomi.ebs.module.em.comm.utils.ResponseUtils;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.ResponseWrapper;

 
public class Main {

    public static void main(String[] args) throws Exception {

        HyphenateRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

        IMUserAPI user = (IMUserAPI) factory.newInstance(HyphenateRestAPIFactory.USER_CLASS);
        ChatMessageAPI chat = (ChatMessageAPI) factory.newInstance(HyphenateRestAPIFactory.MESSAGE_CLASS);
        FileAPI file = (FileAPI) factory.newInstance(HyphenateRestAPIFactory.FILE_CLASS);
        SendMessageAPI message = (SendMessageAPI) factory.newInstance(HyphenateRestAPIFactory.SEND_MESSAGE_CLASS);
        ChatGroupAPI chatgroup = (ChatGroupAPI) factory.newInstance(HyphenateRestAPIFactory.CHATGROUP_CLASS);
        ChatRoomAPI chatroom = (ChatRoomAPI) factory.newInstance(HyphenateRestAPIFactory.CHATROOM_CLASS);
//        Object contacts = user.getContacts("122627018");
//        ResponseWrapper fileResponse = (ResponseWrapper) file.uploadFile(new File("d:/01.jpg"));
//        String uuid = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("uuid").asText();
//        String shareSecret = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("share-secret").asText();
//        InputStream in = (InputStream) ((ResponseWrapper) file.downloadFile(uuid, shareSecret, false)).getResponseBody();
//        FileOutputStream fos = new FileOutputStream("d:/logo1.png");
//        byte[] buffer = new byte[1024];
//        int len1 = 0;
//        while ((len1 = in.read(buffer)) != -1) {
//            fos.write(buffer, 0, len1);
//        }
//        fos.close();

//         Create a IM user
//		BodyWrapper userBody = new IMUserBody("20170202020202", "123456", "HelloWorld");
//		user.createUser(userBody);

		// Create multiple IM users
//		List<IMUserBody> users = new List<IMUserBody>();
//		users.add(new IMUserBody("User002", "123456", null));
//		users.add(new IMUserBody("User003", "123456", null));
//		BodyWrapper usersBody = new IMUsersBody(users);
//		user.createUsers(usersBody);

		// Get a IM user
//		Object users = user.getUsersByUsername("User001");

		// Get a not-existing user
//		user.getUsersByUsername("FakeUser001");

		// Get users with pagination
//		user.getUsersWithPagination(null, null);

        // Get users with pagination
//        Object usersBatch = user.getUsersWithPagination(1L, "");
//        try{
//        CommandMessageBody cmdMsg = new CommandMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "admin", null, "I.m the command message from server");
//        Map<String,String> map = new HashMap<>();
        
//        Object contacts = ;
        
        
        
//        ResponseWrapper res = (ResponseWrapper)user.getContacts("hgtasd654");
//        
//        System.out.println("mig_res.getResponseStatus():"+res.getResponseStatus());
//        JsonNode json = ResponseUtils.ResponseBodyToJsonNode(res);
//        System.out.println(json);
//        JsonNode jsonNode = json.get("data");
//        ObjectMapper objectMappe = new ObjectMapper();
//        String[] readValue = objectMappe.readValue(jsonNode.toString(), String[].class);
////        System.out.println(readValue);
//        for (int i = 0; i < readValue.length; i++) {
//            System.out.println(readValue[i]);
//        }
        
        
        
        
//        JSON.par
//        JsonFactory
//        jsonNode.
//        System.out.println(jsonNode.toString());
//        for(int i=0;i<jsonNode.size();i++){
//        	System.out.println(jsonNode.get(0).path("").get);
//        }
//        jsonNode.path(arg0)
//        Iterator<String> fieldNames = jsonNode.fieldNames();
//        while(fieldNames.hasNext()){   
//        	System.out.println(fieldNames.next());
////        	String next = fieldNames.next().;
////        	System.out.println(next + ": " + jsonNode.path(next).toString()); 
//        }
//        System.out.println();
//        JSONArray.toJSONString(json.ArrayList<String>)
//        JSONArray
//        json.get("data").
//        System.out.println("data"+json.get("data"));
        
//        MessageBody msg = new TextMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "1226270sha1", null,"你猜");
//        message.sendMessage(msg);
//        }catch (Exception e) {
//			// TODO: handle exception
//        	e.printStackTrace();
        
//        message.
//        MessageBody msg = new 
        
        
//        user.addContact("122627018", "siwnal654135s");
        ResponseWrapper res = (ResponseWrapper) user.addContact("122627018", "siwnal654135s");
      
      System.out.println("mig_res.getResponseStatus():"+res.getResponseStatus());
      JsonNode json = ResponseUtils.ResponseBodyToJsonNode(res);
      System.out.println(json);
    }

}
