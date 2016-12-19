package com.wxxiaomi.junit.test;




import java.sql.Timestamp;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wxxiaomi.ebs.dao.bean.Comment;
import com.wxxiaomi.ebs.dao.bean.Topic;
import com.wxxiaomi.ebs.dao.bean.UserCommonInfo;
import com.wxxiaomi.ebs.dao.inter.CommentDao;
import com.wxxiaomi.ebs.dao.inter.TopicDao;
import com.wxxiaomi.ebs.dao.inter.UserDao;


public class JunitTest {

//	static UserService service;
//	static MapService mapService;
	static TopicDao topicDaoImpl;
//	static UpLoadService upLoadService;
//
//	static OptionService optionService;
	
	static CommentDao commentDao;
	static UserDao userDao;
	@BeforeClass
	public static void setUp() {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext(
					"beans.xml");
			userDao = (UserDao)act.getBean("userDaoImpl");
//			service = (UserService) act.getBean("userServiceImpl");
//			mapService = (MapService) act.getBean("mapServiceImpl");
			topicDaoImpl = (TopicDao) act.getBean("topicDaoImpl");
//			upLoadService = (UpLoadService) act.getBean("upLoadServiceImpl");
//			optionService = (OptionService)act.getBean("optionServiceImpl");
			commentDao = (CommentDao)act.getBean("commentDaoImpl");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	@Test
	public void testUpdateUserInfo(){
		try{
		UserCommonInfo info = new UserCommonInfo();
		info.setId(26);
		info.setNickname("啦啦啦啦");
		userDao.updateUser(info);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
//	@Test
//	public void testUpdateUserInfo(){
//		UserCommonInfo info = new UserCommonInfo();
//		info.name = "demo";
//		info.head = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1821181857,3823959247&fm=111&gp=0.jpg";
//		info.id = 1;
//		info.emname = "201610281024";
//		userDao.updateUser(info);
//	}
	
	@Test
	public void testGetComment(){
//		commentDao = new CommentDaoImpl();
		Comment comment = commentDao.getCommentById(25);
		System.out.println(comment.toString());
	}

//	@Test
//	public void login() {
//		User user = service.Login("122627018", "98798798");
//		System.out.println(user.toString());
//	}
//
//	@Test
//	public void getUserListByEMUsername() {
//		List<String> names = new ArrayList<String>();
//		names.add("03221716");
//		names.add("03230925");
//		names.add("122627018");
//		List<UserCommonInfo> result = service.getUserListByEMUsername(names);
//		for (UserCommonInfo item : result) {
//			System.out.println(item.toString());
//		}
//	}
//
//	@Test
//	public void testRegisterUser() {
//		User user = service.registerUser("2016102811494", "987987987s");
//		System.out.println(user.toString());
//	}
//
//	@Test
//	public void testGetUserCommonInfo() {
//		UserCommonInfo userCommonInfoById = service.getUserCommonInfoById(68);
//		System.out.println(userCommonInfoById.toString());
//	}
//
//	@Test
//	public void testGetUserInfoByName() {
//		List<UserCommonInfo> userInfoByName = service
//				.getUserInfoByName("201610281024");
//		System.out.println(userInfoByName.size());
//	}
//
//	@Test
//	public void testGetNear() {
//		List<Locat> list = mapService.getNearByPerson(70, "s0000006gthy");
//		if (list != null) {
//			System.out.println("list.size()=" + list.size());
//			for (Locat locat : list) {
//				System.out.println(locat.toString());
//			}
//		}
//
//	}
//
//	@Test
//	public void testSavaLocat() {
//		mapService.savaLocation(77, "s0000006gthy");
//	}
//
//	@Test
//	public void testCreateNear() {
//		mapService.createNearByPeople(23.5457440000, 116.3705530000);
//	}
//
//	@Test
//	public void testUpdateHead() {
//		boolean result = service.updateUserHead(25,
//				"/upload/filename_demo1478075801540.jpg");
//		System.out.println("result=" + result);
//		UserCommonInfo userCommonInfoById = service.getUserCommonInfoById(25);
//		System.out.println("id:25->" + userCommonInfoById.toString());
//	}
//
//	@Test
//	public void testUploadImage() {
//		File file = new File("F:/IMG_20160719_130452.jpg");
//		String uploadImage = upLoadService.uploadImage(file, "2016-11-8",
//				"一张图片");
//		System.out.println("uploadImage:" + uploadImage);
//	}
//
//	@Test
//	public void testPublishTopic() {
//		for (int i = 1; i < 12; i++) {
//			Topic p = new Topic();
//			p.setContent("今天好像这里发生了火灾" + i);
//			p.setTime(new Date());
//			p.setPics("http://mttext.oss-cn-shanghai.aliyuncs.com/25fba402-b5de-4c87-a24c-1e77ce4e4e80.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/d8c6ce7a-00b0-4ff2-849f-0d4c1e5bfc9b.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/7a1cf774-efd6-49ae-b1a2-84747a88fef2.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/5fe2666c-2264-405c-8943-85e8586dc411.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/ab733356-0066-4b91-a078-ca26cd382d95.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/60e7d603-a338-43f5-ac50-b36657b1b04b.jpg");
//			UserCommonInfo info = new UserCommonInfo();
//			info.setId(10+i);
//			p.setUserCommonInfo(info);
//			topicService.publishTopic(p);
//		}
//	}
//	
//	@Test
//	public void testPublishTopic2() {
//		
//			UserCommonInfo userInfo = new UserCommonInfo();
//			userInfo.setId(25);
//			Topic t = new Topic();
//			t.setContent("asdasdasdsa");
//			t.setPics("http://mttext.oss-cn-shanghai.aliyuncs.com/25fba402-b5de-4c87-a24c-1e77ce4e4e80.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/d8c6ce7a-00b0-4ff2-849f-0d4c1e5bfc9b.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/7a1cf774-efd6-49ae-b1a2-84747a88fef2.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/5fe2666c-2264-405c-8943-85e8586dc411.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/ab733356-0066-4b91-a078-ca26cd382d95.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/60e7d603-a338-43f5-ac50-b36657b1b04b.jpg");
//			t.setTime(new Date());
//			t.setCcount(0);
//			t.setLocat("ws4wpf4wk48t");
//			t.setLocat_tag("科技馆");
//			t.setHot(0);
//			t.setUserCommonInfo(userInfo);
//			t.setTitle("");
//			topicDaoImpl.InsetTopic(t);
//	}
//
//	@Test
//	public void testGetTopic() {
//		List<Topic> topics = topicService.getTopics(5);
//		for (Topic item : topics) {
//			System.out.println(item.toString());
//		}
//	}
//
//	@Test
//	public void testGetTopicByUserid() {
//		List<Topic> topics = topicService.getTopicByUserid(25);
//		for (Topic item : topics) {
//			System.out.println(item.toString());
//		}
//	}
//	
//	@Test
//	public void testPublishComment(){
//		Comment c = new Comment();
//		c.setFrom_uid(23);
//		c.setFrom_head("default.jpg");
//		c.setFrom_nick("201610290938");
//		c.setTo_uid(25);
//		c.setTo_unick("122627018");
//		c.setContent("我是第1条评论");
//		c.setTopic_id(68);
//		topicService.publishComment(c);
//		for(int i=0;i<10;i++){
//			Comment comment = new Comment();
//			
//			if(i%2==0){
//				comment.setId(0);
//				comment.setFrom_uid(23);
//				comment.setFrom_head("default.jpg");
//				comment.setFrom_nick("201610290938");
//				comment.setTo_uid(25);
//				comment.setTo_unick("122627018");
//				comment.setContent("我是第"+i+"条评论");
//				comment.setTopic_id(68);
//			}else{
//				comment.setId(0);
//				comment.setFrom_uid(25);
//				comment.setTo_uid(23);
//				comment.setTo_unick("201610290938");
//				comment.setFrom_head("/upload/demodemo1479116012698.jpg");
//				comment.setFrom_nick("122627018");
//				comment.setContent("我是第"+i+"条评论");
//				comment.setTopic_id(68);
//			}
//			topicService.publishComment(comment);
//		}
//	}
//	
//	@Test
//	public void testGetTopicComent(){
//		List<Comment> list = topicService.getTopicComent(68);
//		for(Comment item : list){
//			System.out.println(item.toString());
//		}
//		
//	}
//	
//	
//	@Test
//	public void testGetUserReply(){
//		List<Comment> list = topicService.getUserReply(25);
//		for(Comment item : list){
//			System.out.println(item.toString());
//		}
//	}
//	
//	@Test
//	public void testInsertOption(){
//		OptionLogs log = new OptionLogs();
//		log.setContent("考虑考虑");
//		log.setCreate_time(new Date());
//		log.setLocat("ws4wpf4wk48t");
//		log.setLocat_tag("科技馆");
//		log.setObj_id(117);
//		log.setObj_type(OptionType.TOPIC_PUBLISH);
//		log.setPictures("http://mttext.oss-cn-shanghai.aliyuncs.com/49d4fc4f-d93a-449c-956c-6e5e1eb19891.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/4aa8f07c-4c0e-49cc-9201-c93c6f58b9ef.jpg#http://mttext.oss-cn-shanghai.aliyuncs.com/0754de10-2e40-4e74-9969-2ed394495562.jpg");
//		log.setTitle("发布了一条话题");
//		log.setUserid(25);
//		log.setFoor_note("");
////		optionService.insertOption(log);
//	}
//	
//	@Test
//	public void testInsertUserPhoto(){
//		Photo p = new Photo();
//		p.setAlbum_id(1);
//		p.setCreate_time(new Date());
//		p.setUrl("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3706555796,1550133346&fm=58");
//		List<Photo> list = new ArrayList<Photo>();
//		list.add(p);
//		service.insertUserPhoto(list);
//	}
//	
//	@Test
//	public void testGetUserPhoto(){
//		List<String> userPhoto = service.getUserPhoto(1, 0);
//		for(String img : userPhoto){
//			System.out.println(img);
//		}
//		}
//	@Test
//	public void getOption(){
////		List<Option> userOptions = service.getUserOptions(25);
////		System.out.println(userOptions.size());
//	}
//	
//	@Test
//	public void testGetOption(){
//		long startTime=System.currentTimeMillis();
////		System.out.println("optionlog");
////		List<OptionLogs> userLogs = optService.getUserLogs(userid);
////		infos = userLogs;
////		System.out.println(userLogs.size());
////		state = "200";
////		return "optionlog";
////		Map<Integer,Option> commentMap = new HashMap<Integer,Option>();
////		Map<Integer,Option> topicMap = new HashMap<Integer,Option>();
//		List<Option> options = optionService.getUserOptions(25);
//		
//		for(Option option : options){
//			JsonConfig jsonConfig = new JsonConfig();  
//			jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());  
//			int type = option.getObj_type();
//			switch (type) {
//			case OptionType.FOOT_PRINT:
//				break;
//			case OptionType.PHOTO_PUBLISH: //更新相册
//				break;
//			case OptionType.TOPIC_COMMENT://话题评论
//				//map.put(option.getParent_id(), option);
//				//取出评论
//				Comment comment = topicService.getCommentById(option.getObj_id());
//				//取出话题
//				Topic topic = topicService.getTopicById(option.getParent_id());
//				System.out.println(topic.toString());
//				option.setJson_obj(JSONObject.fromObject(comment,jsonConfig).toString());
//				option.setJson_parent(JSONObject.fromObject(topic,jsonConfig).toString());
//				
////				commentMap.put(option.getObj_id(), option);
////				topicMap.put(option.getParent_id(), option);
//				break;
//			case OptionType.TOPIC_PUBLISH://话题发布
//				Topic t = topicService.getTopicById(option.getObj_id());
//				option.setJson_obj(JSONObject.fromObject(t,jsonConfig).toString());
////				topicMap.put(option.getObj_id(), option);
//				break;
//			}
//		}
////		Set<Integer> commentKeySet = commentMap.keySet();
////		Set<Integer> topicKeySet = topicMap.keySet();
////		List<Topic> topics = topicService.getTopics(topicKeySet);
////		List<Comment> comments = topicService.getComments(commentKeySet);
////		for(Topic t: topics){
////			commentMap.get(t.getId());
////		}
////		for(Comment c:comments){
////			
////		}
//		long endTime=System.currentTimeMillis();
//		 float excTime=(float)(endTime-startTime)/1000;
//	       System.out.println("获取用户动态所耗费的执行时间："+excTime+"s");
//	}
//		
}
