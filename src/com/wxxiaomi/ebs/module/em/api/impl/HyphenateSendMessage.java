package com.wxxiaomi.ebs.module.em.api.impl;

import com.wxxiaomi.ebs.module.em.api.HyphenateRestAPI;
import com.wxxiaomi.ebs.module.em.api.SendMessageAPI;
import com.wxxiaomi.ebs.module.em.comm.constant.HTTPMethod;
import com.wxxiaomi.ebs.module.em.comm.helper.HeaderHelper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.BodyWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.HeaderWrapper;

public class HyphenateSendMessage extends HyphenateRestAPI implements SendMessageAPI {
    private static final String ROOT_URI = "/messages";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object sendMessage(Object payload) {
        String  url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }
}
