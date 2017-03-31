package com.wxxiaomi.ebs.filter;

import java.security.interfaces.RSAPrivateKey;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.util.TextUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.wxxiaomi.ebs.common.ErrorMsg;
import com.wxxiaomi.ebs.security.AESUtil;
import com.wxxiaomi.ebs.security.RSAEncrypt;
import com.wxxiaomi.ebs.security.RSAUtils;

public class DecodeFilter extends MethodFilterInterceptor {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected String doIntercept(ActionInvocation invoker) throws Exception {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		if ("POST".equals(request.getMethod())) {
			System.out.println("被DecodeFilter检查了s");
			try {
				String data = request.getParameter("data");
				String key = request.getHeader("key");
				System.out.println("key:"+key);
				System.out.println("data:"+data);
				if(TextUtils.isEmpty(key)){
					//非法客户端，未经授权的客户端
					request.setAttribute("error_code", ErrorMsg.ERROR_ILLICIT_CLIENT);
					System.out.println("头部密钥为空!");
					return "error";
				}
				RSAPrivateKey rsa_key = RSAEncrypt
						.loadPrivateKeyByStr(RSAEncrypt
								.loadPrivateKeyByResource());
				String kk = RSAUtils.decryptByPrivateKey(key, rsa_key);
				String decrypt = AESUtil.decrypt(data, kk);
				System.out.println("客户端传过来的内容：" + decrypt);
				String[] split = decrypt.split("&");
				for(String item : split){
					String[] split2 = item.split("=");
					System.out.println(split2[0]+":"+split2[1]);
					invoker.getStack().setValue(split2[0], split2[1]);
				}
				return invoker.invoke();
			} catch (Exception e) {
				// TODO: 解密失败
				request.setAttribute("error_code", ErrorMsg.ERROR_OVERDUE_CLIENT);
				e.printStackTrace();
				return "error";
			}
		}
		return invoker.invoke();
	}

}
