package com.wxxiaomi.ebs.dao.bean.constant;

import java.util.HashMap;
import java.util.Map;

public class Result {
	public int state;
	public String error;
	public Object infos;
	public Map<String,String> headers;
	public Result(int state, String error, Object infos) {
		super();
		this.state = state;
		this.error = error;
		this.infos = infos;
	}
	public void putHeader(String key,String value){
		if(headers==null){
			headers = new HashMap<String,String>();
		}
		headers.put(key, value);
	}
	
	public boolean isHeadEmpty(){
		if(headers==null){
			return true;
		}else{
			if(headers.size() ==0){
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "Result [state=" + state + ", error=" + error + ", infos="
				+ infos + ", headers=" + headers + "]";
	}
	
	
	
}
