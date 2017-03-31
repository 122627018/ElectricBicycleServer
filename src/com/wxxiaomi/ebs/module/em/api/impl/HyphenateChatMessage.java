package com.wxxiaomi.ebs.module.em.api.impl;

import com.wxxiaomi.ebs.module.em.api.ChatMessageAPI;
import com.wxxiaomi.ebs.module.em.api.HyphenateRestAPI;
import com.wxxiaomi.ebs.module.em.comm.constant.HTTPMethod;
import com.wxxiaomi.ebs.module.em.comm.helper.HeaderHelper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.HeaderWrapper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.QueryWrapper;

public class HyphenateChatMessage extends HyphenateRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "/chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor).addQueryLang(query);

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
