package com.wxxiaomi.ebs.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wxxiaomi.ebs.util.jwt.Jwt;
import com.wxxiaomi.ebs.util.jwt.TokenState;

public class TokenFilter extends MethodFilterInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {
		System.out.println("doIntercept");
		HttpServletRequest request =(HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);    
		String token = request.getHeader("token");
		if(token!=null){
			Map<String, Object> resultMap = Jwt.validToken(token);
			System.out.println("校验结果是:" + getResult((String)resultMap.get("state")) );
			if(TokenState.getTokenState((String)resultMap.get("state"))==TokenState.VALID){
				HashMap<String,String> dataobj =  (HashMap<String,String>) resultMap.get("data");
				String uid = dataobj.get("uid");
				System.out.println("从token中取出的uid是：" +uid);
				request.setAttribute("userid", uid);
				return invoker.invoke();
			}
			
		}
		return "demo";
	}

	
	public String getResult(String state) {
		switch (TokenState.getTokenState(state)) {
		case VALID:
			//To do somethings
			state = "有效token";
			break;
		case EXPIRED:
			state = "过期token";
			break;
		case INVALID:
			state = "无效的token";
			break;
		}
		return state;
	}

}
