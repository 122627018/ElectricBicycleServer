package com.wxxiaomi.junit.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonParser;
import com.wxxiaomi.ebs.bean.Comment;
import com.wxxiaomi.ebs.bean.Locat;
import com.wxxiaomi.ebs.bean.Topic;
import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.UserCommonInfo;
import com.wxxiaomi.ebs.service.MapService;
import com.wxxiaomi.ebs.service.TopicService;
import com.wxxiaomi.ebs.service.UpLoadService;
import com.wxxiaomi.ebs.service.UserService;

public class JunitTest {

	static UserService service;
	static MapService mapService;
	static TopicService topicService;
	static UpLoadService upLoadService;

	@BeforeClass
	public static void setUp() {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext(
					"beans.xml");
			service = (UserService) act.getBean("userServiceImpl");
			mapService = (MapService) act.getBean("mapServiceImpl");
			topicService = (TopicService) act.getBean("topicServiceImpl");
			upLoadService = (UpLoadService) act.getBean("upLoadServiceImpl");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void login() {
		User user = service.Login("122627018", "98798798");
		System.out.println(user.toString());
	}

	@Test
	public void getUserListByEMUsername() {
		List<String> names = new ArrayList<String>();
		names.add("03221716");
		names.add("03230925");
		names.add("122627018");
		List<UserCommonInfo> result = service.getUserListByEMUsername(names);
		for (UserCommonInfo item : result) {
			System.out.println(item.toString());
		}
	}

	@Test
	public void testRegisterUser() {
		User user = service.registerUser("2016102811494", "987987987s");
		System.out.println(user.toString());
	}

	@Test
	public void testGetUserCommonInfo() {
		UserCommonInfo userCommonInfoById = service.getUserCommonInfoById(68);
		System.out.println(userCommonInfoById.toString());
	}

	@Test
	public void testGetUserInfoByName() {
		List<UserCommonInfo> userInfoByName = service
				.getUserInfoByName("201610281024");
		System.out.println(userInfoByName.size());
	}

	@Test
	public void testGetNear() {
		List<Locat> list = mapService.getNearByPerson(70, "s0000006gthy");
		if (list != null) {
			System.out.println("list.size()=" + list.size());
			for (Locat locat : list) {
				System.out.println(locat.toString());
			}
		}

	}

	@Test
	public void testSavaLocat() {
		mapService.savaLocation(77, "s0000006gthy");
	}

	@Test
	public void testCreateNear() {
		mapService.createNearByPeople(23.5457440000, 116.3705530000);
	}

	@Test
	public void testUpdateHead() {
		boolean result = service.updateUserHead(25,
				"/upload/filename_demo1478075801540.jpg");
		System.out.println("result=" + result);
		UserCommonInfo userCommonInfoById = service.getUserCommonInfoById(25);
		System.out.println("id:25->" + userCommonInfoById.toString());
	}

	@Test
	public void testUploadImage() {
		File file = new File("F:/IMG_20160719_130452.jpg");
		String uploadImage = upLoadService.uploadImage(file, "2016-11-8",
				"一张图片");
		System.out.println("uploadImage:" + uploadImage);
	}

	@Test
	public void testPublishTopic() {
		for (int i = 1; i < 12; i++) {
			Topic p = new Topic();
			p.setContent("今天好像这里发生了火灾" + i);
			p.setTime(new Date());
			p.setPics("/upload/demodemo1478183543431.jpg#/upload/demodemo1478576267463.jpg#/upload/demodemo1478576352769.jpg");
			UserCommonInfo info = new UserCommonInfo();
			info.setId(10+i);
			p.setUserCommonInfo(info);
			topicService.publishTopic(p);
		}
	}

	@Test
	public void testGetTopic() {
		List<Topic> topics = topicService.getTopics(5);
		for (Topic item : topics) {
			System.out.println(item.toString());
		}
	}

	@Test
	public void testGetTopicByUserid() {
		List<Topic> topics = topicService.getTopicByUserid(25);
		for (Topic item : topics) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	public void testPublishComment(){
		Comment c = new Comment();
		c.setFrom_uid(23);
		c.setFrom_head("default.jpg");
		c.setFrom_nick("201610290938");
		c.setTo_uid(25);
		c.setTo_unick("122627018");
		c.setContent("我是第1条评论");
		c.setTopic_id(68);
		topicService.publishComment(c);
		for(int i=0;i<10;i++){
			Comment comment = new Comment();
			
			if(i%2==0){
				comment.setId(0);
				comment.setFrom_uid(23);
				comment.setFrom_head("default.jpg");
				comment.setFrom_nick("201610290938");
				comment.setTo_uid(25);
				comment.setTo_unick("122627018");
				comment.setContent("我是第"+i+"条评论");
				comment.setTopic_id(68);
			}else{
				comment.setId(0);
				comment.setFrom_uid(25);
				comment.setTo_uid(23);
				comment.setTo_unick("201610290938");
				comment.setFrom_head("/upload/demodemo1479116012698.jpg");
				comment.setFrom_nick("122627018");
				comment.setContent("我是第"+i+"条评论");
				comment.setTopic_id(68);
			}
			topicService.publishComment(comment);
		}
	}
	
	@Test
	public void testGetTopicComent(){
		List<Comment> list = topicService.getTopicComent(68);
		for(Comment item : list){
			System.out.println(item.toString());
		}
		
	}
	
	
	@Test
	public void testGetUserReply(){
		List<Comment> list = topicService.getUserReply(25);
		for(Comment item : list){
			System.out.println(item.toString());
		}
	}
}
