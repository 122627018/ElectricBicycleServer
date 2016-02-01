package com.wxxiaomi.ebs.engine.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wxxiaomi.ebs.bean.format.NearByPerson.UserLocat;
import com.wxxiaomi.ebs.util.ConnectionUtil;
import com.wxxiaomi.ebs.util.GeoHashUtil;

public class MapManager {
	
	

	/**
	 * 取出附近的人
	 * @param userid
	 * @param geo
	 * @return
	 */
	public static List<UserLocat> getNearByPerson(int userid,String geo){
		List<UserLocat> result = new ArrayList<UserLocat>();
		try {
			geo = geo.substring(0, 5);
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from locat where userid!=? and geo LIKE ?");
			ps.setInt(1, userid);
			ps.setString(2, "%"+geo+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				UserLocat userLocat = new UserLocat();
				userLocat.setLocat(GeoHashUtil.decode(rs.getString(3)));
				userLocat.setUser(UserManager.selectUserById(rs.getInt(2)));
				result.add(userLocat);
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean savaLocation(int userid,String geo) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select userid from locat where userid=?");
//			PreparedStatement ps = conn
//					.prepareStatement("insert into locat values(null,?,?,?)");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ps = conn.prepareStatement("update locat set geo=?,time=? where userid=?");
				ps.setString(1, geo);
				ps.setString(2, "2016.1.1");
				ps.setInt(3, userid);
				if (ps.executeUpdate() > 0) {
					flag = true;
				}
				
			}else{
				ps = conn.prepareStatement("insert into locat values(null,?,?,?)");
				ps.setInt(1, userid);
				ps.setString(2, geo);
				ps.setString(3, "2015.1.1");
				if (ps.executeUpdate() > 0) {
					flag = true;
				}
			}
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
}
