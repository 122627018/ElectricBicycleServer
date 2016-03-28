package com.wxxiaomi.ebs.engine.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wxxiaomi.ebs.bean.User;
import com.wxxiaomi.ebs.bean.User.UserCommonInfo;
import com.wxxiaomi.ebs.exception.PsdErrorException;
import com.wxxiaomi.ebs.exception.UnKnownErrorException;
import com.wxxiaomi.ebs.exception.UserExistsException;
import com.wxxiaomi.ebs.util.ConnectionUtil;

/**
 * 操作数据库的引擎 错误码：321---链接数据库出错 322---未知错误
 * 
 * @author Administrator
 * 
 */
public class UserDao {
	
//	/**
//	 * 创建5个新用户
//	 * @return
//	 */
//	public static List<Integer> createManyCount(){
//		Connection conn = null;
//		List<Integer> result = new ArrayList<Integer>();
//		try {
//			conn = ConnectionUtil.getConnection();
//			PreparedStatement ps = conn
//					.prepareStatement("select * from bicycle_user_common where emname in "+temp);
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//		return result;
//	}
	

	/**
	 * 根据em的用户名---list--获取或有公共信息列表
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public static List<UserCommonInfo> getUserListByEMUsername(List<String> list)
			throws SQLException {
		List<UserCommonInfo> result = new ArrayList<UserCommonInfo>();
		Connection conn = null;
		try {
			if(list.size() == 0){
				return result;
			}
			String temp = "(";
			for (int i = 0; i < list.size(); i++) {
				if (i == 0) {
					temp += list.get(i);
				} else {
					temp += "," + list.get(i);
				}
			}
			temp += ")";
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from bicycle_user_common where emname in "+temp);
			
//			System.out.println("temp=" + temp);
//			ps.setString(1, temp);
//			ps.set
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserCommonInfo userCommonInfo = new UserCommonInfo(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4));
				result.add(userCommonInfo);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

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
				.prepareStatement("select id from bicycle_user_personal where username=?");
		ps.setString(1, phone);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			throw new UserExistsException("用户已存在");
		}
	}

	/**
	 * 注册一个用户
	 * @param username
	 * @param password
	 * @param name
	 * @return
	 * @throws SQLException
	 * @throws UnKnownErrorException
	 */
	public static User registerUser(String username, String password)
			throws SQLException, UnKnownErrorException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("insert into bicycle_user_personal values(null,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			if (ps.executeUpdate() > 0) {
				ps = conn.prepareStatement("select max(id) from bicycle_user_personal");
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					User user = new User(rs.getInt(1), username, password, null);
					return user;
				}
			}
			throw new UnKnownErrorException("操作数据库失败");
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
	}

	/**
	 * 从服务器数据库获取user资料
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws PsdErrorException
	 *             密码错误异常
	 * @throws UnKnownErrorException
	 *             自定义错误异常->获取公共信息失败
	 * @throws SQLException
	 *             获取数据库链接异常
	 */
	public static User Login(String username, String password)
			throws SQLException, PsdErrorException, UnKnownErrorException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from bicycle_user_personal where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User user = new User(rs.getInt(1), username, password,
						getUserCommonInfoById(rs.getInt(1)));
				return user;
			} else {
				throw new PsdErrorException("用户名或者密码错误");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (UnKnownErrorException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}

	/**
	 * 根据id获取用户公共信息
	 * 
	 * @param userid
	 * @return
	 * @throws SQLException
	 * @throws UnKnownErrorException
	 */
	public static UserCommonInfo getUserCommonInfoById(int userid)
			throws SQLException, UnKnownErrorException {
		Connection conn = null;
		try {
			conn = ConnectionUtil.getConnection();

			PreparedStatement ps = conn
					.prepareStatement("select * from bicycle_user_common where userid=?");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// user = new User(rs.getInt(1), rs.getString(2),
				// rs.getString(3));
				return new UserCommonInfo(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4));
			} else {
				throw new UnKnownErrorException("数据库执行查询失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}

	}

	public static UserCommonInfo improveUserInfo(int userid, String emname, String name,
			String description) throws SQLException {
		Connection conn = null;
		UserCommonInfo info = null;
		try {
			conn = ConnectionUtil.getConnection();

			PreparedStatement ps = conn
					.prepareStatement("insert into bicycle_user_common values(?,?,'demo',?)");
			ps.setInt(1, userid);
			ps.setString(2, name);
			ps.setString(3, emname);
			if (ps.executeUpdate() > 0) {
				info = new UserCommonInfo(userid, name, "", emname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.close();
		}
		return info;
	}

	// public static User selectUserById(int userid) {
	// User user = null;
	// try {
	// Connection conn = ConnectionUtil.getConnection();
	// PreparedStatement ps = conn
	// .prepareStatement("select id,name,head from bicycle_user where id=?");
	// ps.setInt(1, userid);
	// ResultSet rs = ps.executeQuery();
	// if (rs.next()) {
	// user = new User(rs.getInt(1), rs.getString(2), rs.getString(3));
	// }
	// conn.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return user;
	// }
}
