package com.wxxiaomi.ebs.engine.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wxxiaomi.ebs.bean.User1;
import com.wxxiaomi.ebs.exception.UnKnownErrorException;
import com.wxxiaomi.ebs.exception.UserExistsException;
import com.wxxiaomi.ebs.util.ConnectionUtil;

public class UserManager {

	/**
	 * 检查手机号是否已经被注册过了
	 * 
	 * @param phone
	 * @throws UserExistsException
	 * @throws SQLException
	 * @throws Exception
	 */
	public static void checkPhoneExists(String phone)
			throws UserExistsException, SQLException {
		Connection conn = ConnectionUtil.getConnection();
		PreparedStatement ps = conn
				.prepareStatement("select id from bicycle_user username=?");
		ps.setString(1, phone);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			throw new UserExistsException("");
		}
	}

	public static User1 registerUser(String username, String password,
			String name) throws SQLException, UnKnownErrorException {
		User1 user = null;
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("insert into bicycle_user values(null,?,?,null,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, name);
			if (ps.executeUpdate() > 0) {
				ps = conn.prepareStatement("select max(id) from bicycle_user");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					user = new User1(rs.getInt(1), username, password, "", name);
				}
			}else{
				//插入失败
				throw new UnKnownErrorException("未知错误");
			}
		} catch (SQLException e) {
			throw e;
		}finally{
			conn.close();
		}
		return user;
	}

	public static User1 getUser(String username, String password) {
		User1 user = null;
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from bicycle_user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new User1(rs.getInt(1), rs.getString(2),
						rs.getString(3), "");
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public static User1 selectUserById(int userid) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
