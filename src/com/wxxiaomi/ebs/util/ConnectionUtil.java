package com.wxxiaomi.ebs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	private static String url= "jdbc:mysql://localhost:3306/bicycle?characterEncoding=UTF-8";
	private static String user = "root";
	private static String password = "root";
//	private static String url= "jdbc:mysql://qdm175613403.my3w.com/qdm175613403_db";
//	private static String user = "qdm175613403";
//	private static String password = "woshiwanga";
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return  DriverManager.getConnection(url, user, password);
	}
	
}
