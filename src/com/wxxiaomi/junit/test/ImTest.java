package com.wxxiaomi.junit.test;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wxxiaomi.ebs.module.em.engine.IMUserDao;
import com.wxxiaomi.ebs.module.em.engine.common.ImException;

public class ImTest {

	static IMUserDao imUserDao;

	@BeforeClass
	public static void setUp() {
		try {
			ApplicationContext act = new ClassPathXmlApplicationContext(
					"beans.xml");
			imUserDao = (IMUserDao) act.getBean("imUserDaoImpl");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Test
	public void testGetImUserBlackList() {
		String[] userBlackList;
		try {
			userBlackList = imUserDao.getUserBlackList("21654889");
			System.out.println(userBlackList.length);
		} catch (ImException e) {
			// TODO Auto-generated catch block
			System.out.println("出错");
			 e.printStackTrace();
		}

	}

	@Test
	public void testCreateImUser() {
		boolean createUser;
		try {
			createUser = imUserDao.createUser("201702260912", "987987987");
			if (createUser) {
				System.out.println("创建成功");
			}
		} catch (ImException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("出错拉");
			System.out.println("e.getDisplayErr():"+e.getDisplayErr());
		}

	}

	@Test
	public void testGetImUserContacts() {
		try {
			String[] userContacts;
			userContacts = imUserDao.getUserContacts("122627018");
			for (int i = 0; i < userContacts.length; i++) {
				System.out.println(userContacts[i]);
			}
		} catch (ImException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
