package com.wxxiaomi.ebs.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionUtil {
	
	private static String url= "jdbc:mysql://localhost:3306/bicycle";
	private static String user = "root";
	private static String password = "root";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws Exception{
		return  DriverManager.getConnection(url, user, password);
	}
	
}
