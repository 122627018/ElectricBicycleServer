package com.wxxiaomi.ebs.module.em;


import com.wxxiaomi.ebs.module.em.api.ChatGroupAPI;
import com.wxxiaomi.ebs.module.em.api.ChatMessageAPI;
import com.wxxiaomi.ebs.module.em.api.ChatRoomAPI;
import com.wxxiaomi.ebs.module.em.api.FileAPI;
import com.wxxiaomi.ebs.module.em.api.IMUserAPI;
import com.wxxiaomi.ebs.module.em.api.SendMessageAPI;
import com.wxxiaomi.ebs.module.em.comm.ClientContext;
import com.wxxiaomi.ebs.module.em.comm.HyphenateRestAPIFactory;
import com.wxxiaomi.ebs.module.em.comm.body.CommandMessageBody;
import com.wxxiaomi.ebs.module.em.comm.constant.MsgTargetType;

 
public class Main {

    public static void main(String[] args) throws Exception {

        HyphenateRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();

        IMUserAPI user = (IMUserAPI) factory.newInstance(HyphenateRestAPIFactory.USER_CLASS);
        ChatMessageAPI chat = (ChatMessageAPI) factory.newInstance(HyphenateRestAPIFactory.MESSAGE_CLASS);
        FileAPI file = (FileAPI) factory.newInstance(HyphenateRestAPIFactory.FILE_CLASS);
        SendMessageAPI message = (SendMessageAPI) factory.newInstance(HyphenateRestAPIFactory.SEND_MESSAGE_CLASS);
        ChatGroupAPI chatgroup = (ChatGroupAPI) factory.newInstance(HyphenateRestAPIFactory.CHATGROUP_CLASS);
        ChatRoomAPI chatroom = (ChatRoomAPI) factory.newInstance(HyphenateRestAPIFactory.CHATROOM_CLASS);

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

        // Create a IM user
//		BodyWrapper userBody = new IMUserBody("User001", "123456", "HelloWorld");
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
        
        CommandMessageBody cmdMsg = new CommandMessageBody(MsgTargetType.USERS, new String[]{"122627018"}, "admin", null, "I.m the command message from server");
        message.sendMessage(cmdMsg);
    }

}
