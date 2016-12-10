package com.wxxiaomi.ebs.action.base;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.wxxiaomi.ebs.dao.bean.constant.Result;

public class BaseAction{

	public Object infos;
	public int state;
	public String error = "";
	public int getState() {
		return state;
	}


	public String getError() {
		return error;
	}


	public Object getInfos() {
		return infos;
	}
	
	public void adapterResult(Result result){
		infos = result.infos;
		state = result.state;
		error = result.error;
		if(!result.isHeadEmpty()){
			HttpServletResponse response = ServletActionContext.getResponse();
			Map<String, String> headers = result.headers;
			for(Entry<String, String> item : headers.entrySet()){
				response.setHeader(item.getKey(), item.getValue());
			}
			
		}
		
	}


	
	
}
