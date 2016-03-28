package com.wxxiaomi.ebs.engine.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wxxiaomi.ebs.bean.User.UserCommonInfo;
import com.wxxiaomi.ebs.bean.format.NearByPerson.UserLocat;
import com.wxxiaomi.ebs.exception.UnKnownErrorException;
import com.wxxiaomi.ebs.util.ConnectionUtil;
import com.wxxiaomi.ebs.util.GeoHashUtil;

public class MapManager {

	/**
	 * 取出附近的人
	 * 
	 * @param userid
	 * @param geo
	 * @return
	 */
	public static List<UserLocat> getNearByPerson(int userid, String geo) {
		List<UserLocat> result = new ArrayList<UserLocat>();
		try {
			geo = geo.substring(0, 5);
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select * from bicycle_locat where userid!=? and geo LIKE ?");
			ps.setInt(1, userid);
			ps.setString(2, "%" + geo + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				UserLocat userLocat = new UserLocat();
				userLocat.setLocat(GeoHashUtil.decode(rs.getString(3)));
				try {
					userLocat.setUserCommonInfo(UserDao
							.getUserCommonInfoById(rs.getInt(2)));
				} catch (UnKnownErrorException e) {
					e.printStackTrace();
					continue;
				}
				result.add(userLocat);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static boolean savaLocation(int userid, String geo) {
		boolean flag = false;
		try {
			Connection conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select userid from bicycle_locat where userid=?");
			// PreparedStatement ps = conn
			// .prepareStatement("insert into locat values(null,?,?,?)");
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ps = conn
						.prepareStatement("update bicycle_locat set geo=?,time=? where userid=?");
				ps.setString(1, geo);
				ps.setString(2, "2016.1.1");
				ps.setInt(3, userid);
				if (ps.executeUpdate() > 0) {
					flag = true;
				}

			} else {
				ps = conn
						.prepareStatement("insert into bicycle_locat values(null,?,?,?)");
				ps.setInt(1, userid);
				ps.setString(2, geo);
				ps.setString(3, "2015.1.1");
				if (ps.executeUpdate() > 0) {
					flag = true;
				}
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public static List<UserLocat> createNearByPeople(double latitude,
			double longitude) {
		List<UserLocat> result = new ArrayList<UserLocat>();
		try {
				int[] list = {70,71,72,73,74};
				for(int i = 0;i<list.length;i++){
					latitude += 0.0005;
					longitude += 0.0005;
					String encode = GeoHashUtil.encode(latitude, longitude);
					UserCommonInfo userCommonInfoById = UserDao.getUserCommonInfoById(list[i]);
					double[] locate = {latitude,longitude};
					boolean savaLocation = savaLocation(list[i],encode);
					if(savaLocation){
						result.add(new UserLocat(userCommonInfoById, locate));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (UnKnownErrorException e) {
				e.printStackTrace();
			}
		return result;

	};
}
