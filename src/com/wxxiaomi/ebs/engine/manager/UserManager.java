package com.wxxiaomi.ebs.engine.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wxxiaomi.ebs.bean.User1;
import com.wxxiaomi.ebs.util.ConnectionUtil;


public class UserManager {

	public static User1 registerUser(String username,String password,String name){
		User1 user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("insert into bicycle_user values(null,?,?,null,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, name);
			if (ps.executeUpdate() > 0) {
				ps = conn.prepareStatement("select max(id) from bicycle_user");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					user = new User1(rs.getInt(1), username, password, "",name);
				}
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public static User1 getUser(String username,String password){
		User1 user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from bicycle_user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User1(rs.getInt(1), rs.getString(2), rs.getString(3), "");
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public static User1 selectUserById(int userid){
		User1 user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select id,name,head from bicycle_user where id=?");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User1(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
