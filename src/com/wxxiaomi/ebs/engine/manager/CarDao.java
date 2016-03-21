package com.wxxiaomi.ebs.engine.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wxxiaomi.ebs.bean.Bicycle;
import com.wxxiaomi.ebs.util.ConnectionUtil;

public class CarDao {
	
	public static boolean bundBicycle(int userid,int carid){
		boolean flag = false;
		Connection conn;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("update bicycle_bicycle set isbund=1, userid=? where id=?");
			ps.setInt(1, userid);
			ps.setInt(2, carid);
			if(ps.executeUpdate() > 0){
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public static Bicycle getCarInfo(int id) {
		Bicycle result = null;
		
		Connection conn;
		try {
			conn = ConnectionUtil.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("select typeid,isbund,userid,version,name,size,batterysize from bicycle_bicycle,bicycle_bicycle_type where bicycle_bicycle.Id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = new Bicycle(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getInt(4), rs.getString(5), rs.getString(6),
						rs.getString(7));
				result.setId(id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
