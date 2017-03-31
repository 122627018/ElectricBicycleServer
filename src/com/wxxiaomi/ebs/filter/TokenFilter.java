package com.wxxiaomi.ebs.filter;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wxxiaomi.ebs.ConstantValue;
import com.wxxiaomi.ebs.common.ErrorMsg;
import com.wxxiaomi.ebs.module.jwt.Jwt;
import com.wxxiaomi.ebs.module.jwt.TokenState;

public class TokenFilter extends MethodFilterInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		System.out.println("被token拦截器拦下了");
		if (!ConstantValue.isTokenOpen) {
			return invoker.invoke();
		}
		try {
			String token = request.getHeader("token");
			if (token != null) {
				Map<String, Object> resultMap = Jwt.validToken(token);
				System.out.println("token:" + token + "\n校验结果是:"
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
					String uid = String.valueOf(dataobj.get("uid"));
					if (uid == null) {
						System.out.println("token中取不到uid");
						request.setAttribute("error_code", ErrorMsg.ERROR_TOKEN_PARSE);
						return "error";
					} else {
						System.out.println("从token中取出的uid是：" + uid);
						invoker.getStack().setValue("userid", uid);
						return invoker.invoke();
					}

				}else if(TokenState.getTokenState((String) resultMap.get("state"))
						.equals(TokenState.EXPIRED)){
					//token过期
					request.setAttribute("error_code", ErrorMsg.ERROR_TOKEN_EXPIRED);
					return "error";
				}

			}else{
//				request.setAttribute("error_code", CODEClient.ERROR_TOKEN_NULL);
				request.setAttribute("error_code", ErrorMsg.ERROR_ILLICIT_CLIENT);
				return "error";
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error_code", ErrorMsg.ERROR_TOKEN_PARSE);
			return "error";
		}
//		System.out.println("asdasd");
//		request.
//		request.setAttribute("error_code", 30);
//		request.se
//		invoker.getStack().setValue("error_code", 20);
		request.setAttribute("error_code", ErrorMsg.ERROR_UNKNOW);
		return "error";
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
