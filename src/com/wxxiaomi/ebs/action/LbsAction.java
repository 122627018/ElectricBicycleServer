package com.wxxiaomi.ebs.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.bean.Locat;
import com.wxxiaomi.ebs.bean.format.NearByPerson;
import com.wxxiaomi.ebs.service.MapService;
import com.wxxiaomi.ebs.util.GeoHashUtil;

@Controller
public class LbsAction {

	@Resource
	MapService mapService;

	Object infos;
	public String state;
	public String error = "";

	public int userid;
	public double latitude;
	public double longitude;

	/**
	 * 获取附近的人
	 * 
	 * @return
	 */
	public String near() {
		try {
			List<Locat> result = new ArrayList<Locat>();
			System.out.println("getnear request-->latitude=" + latitude
					+ ",longitude=" + longitude + ",userid=" + userid);
			String geoResult = GeoHashUtil.encode(latitude, longitude);
			mapService.savaLocation(userid, geoResult);
			List<Locat> newList = mapService.getNearByPerson(userid, geoResult);
			if (newList != null) {
				result.addAll(newList);
				for (Locat item : result) {
					String geo = item.getGeo();
					double[] decode = GeoHashUtil.decode(geo);
					item.setPoint(decode);

				}
			}
			infos = new NearByPerson(result);
			state = "200";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "near";
	}

	public Object getInfos() {
		return infos;
	}

	public String getState() {
		return state;
	}

	public String getError() {
		return error;
	}

}
