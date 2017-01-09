package com.wxxiaomi.ebs.module.em.api;

import com.wxxiaomi.ebs.module.em.comm.ClientContext;


public abstract class HyphenateRestAPI implements RestAPI {
	
	private ClientContext context;
	
	private RestAPIInvoker invoker;

	public abstract String getResourceRootURI();
	
	public ClientContext getContext() {
		return context;
	}

	public void setContext(ClientContext context) {
		this.context = context;
	}

	public RestAPIInvoker getInvoker() {
		return invoker;
	}

	public void setInvoker(RestAPIInvoker invoker) {
		this.invoker = invoker;
	}
}
