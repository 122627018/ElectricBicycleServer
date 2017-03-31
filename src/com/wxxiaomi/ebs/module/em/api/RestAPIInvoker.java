package com.wxxiaomi.ebs.module.em.api;

import java.io.File;

import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.HeaderWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.QueryWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.ResponseWrapper;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header);
}
