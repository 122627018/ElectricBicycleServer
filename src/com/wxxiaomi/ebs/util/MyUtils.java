package com.wxxiaomi.ebs.util;

import java.io.UnsupportedEncodingException;

public class MyUtils {

	public static String getCodePar(String par){
		try {
			return new String(par.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return par;
		}
	}
}
