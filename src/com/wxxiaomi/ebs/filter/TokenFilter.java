package com.wxxiaomi.ebs.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wxxiaomi.ebs.ConstantValue;
import com.wxxiaomi.ebs.module.jwt.Jwt;
import com.wxxiaomi.ebs.module.jwt.TokenState;

public class TokenFilter extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {
		if(!ConstantValue.isTokenOpen){
			return invoker.invoke();
		}
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			String token = request.getHeader("token");
			if (token != null) {
				Map<String, Object> resultMap = Jwt.validToken(token);
				System.out.println("校验结果是:"
						+ getResult((String) resultMap.get("state")));
				System.out.println(TokenState.getTokenState((String) resultMap
						.get("state")));
				System.out.println(TokenState.VALID.toString());
				if (TokenState.getTokenState((String) resultMap.get("state"))
						.equals(TokenState.VALID)) {
					@SuppressWarnings("unchecked")
					HashMap<String, String> dataobj = ((HashMap<String, String>) resultMap
							.get("data"));
					System.out.println(dataobj);
					String uid = dataobj.get("uid");
					if (uid == null) {
						System.out.println("token中取不到uid");
						return "demo";
					}
					System.out.println("从token中取出的uid是：" + uid);
					request.setAttribute("userid", uid);
					return invoker.invoke();
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "demo";
	}

	public String getResult(String state) {
		switch (TokenState.getTokenState(state)) {
		case VALID:
			// To do somethings
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
