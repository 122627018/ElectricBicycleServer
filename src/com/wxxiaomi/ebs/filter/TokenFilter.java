package com.wxxiaomi.ebs.filter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.opensymphony.xwork2.ognl.OgnlValueStack;
import com.opensymphony.xwork2.util.ValueStack;
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
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
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
						return "demo";
					} else {
						System.out.println("从token中取出的uid是：" + uid);
						// String fuid = request.getParameter("userid");
						// request.getParameterMap().remove("userid");
						// request.setAttribute("userid_demo", 20);
						// System.out.println("从request域里面取出的userid:"+fuid);
						// String finduid =
						// invoker.getStack().findString("userid");
						// System.out.println("从invoker.getStack()取得userid："+finduid);
						invoker.getStack().setValue("userid", uid);
						// invoker.getStack().set("userid", 20);
						// String finduid2 =
						// invoker.getStack().findString("userid");
						// System.out.println("finduid:"+finduid+",finduid2:"+finduid2);
						//
						// invoker.getStack().set("userid_demo", uid);
						// ValueStack valueStack =
						// ActionContext.getContext().getValueStack();
						// valueStack.set("userid_demo", uid);

						// OgnlValueStack
						// stack=(OgnlValueStack)request.getAttribute("struts.valueStack");
						// // stack.push(o)
						// stack.set("userid_demo", uid);
						return invoker.invoke();
					}

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
