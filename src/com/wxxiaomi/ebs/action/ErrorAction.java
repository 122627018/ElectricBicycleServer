package com.wxxiaomi.ebs.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.wxxiaomi.ebs.action.base.BaseAction;
import com.wxxiaomi.ebs.common.ErrorMsg;

@Controller
@Scope("prototype")
public class ErrorAction extends BaseAction{

	HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);
//	public int error_code;
	private ErrorMsg msg;
	public String show(){
		msg = (ErrorMsg) request.getAttribute("error_code");
//		switch (error) {
//		case ERROR_ILLICIT_CLIENT:
//			System.out.println("成功拉,errorcode:"+error.getErrorCode()+",msg:"+error.getErrorMsg());
//			break;
//
//		default:
//			break;
//		}
//		System.out.println("从request中取得的code:"+);
//		System.out.println("error_code:"+error_code);
//		infos = "";
		error = msg.getErrorMsg();
		state = msg.getErrorCode();
		return "result";
	}
}
