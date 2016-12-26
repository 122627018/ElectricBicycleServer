package com.wxxiaomi.ebs.util;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyUtils {

	public static String getCodePar(String par){
		try {
			return new String(par.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return par;
		}
	}
	
	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {
	  
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	   Date date = null;
	   try {
	    date = format.parse(str);
	   } catch (Exception e) {
	    e.printStackTrace();
	   }
	   return date;
	}
	
	/**
	 * 计算俩个时间相差的天数
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int daysOfTwo(Date fDate, Date oDate) {

	       Calendar aCalendar = Calendar.getInstance();

	       aCalendar.setTime(fDate);

	       int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

	       aCalendar.setTime(oDate);

	       int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

	       return day2 - day1;

	    }
}
