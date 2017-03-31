package com.wxxiaomi.ebs.module.em.api.impl;

import java.io.File;

import com.wxxiaomi.ebs.module.em.api.FileAPI;
import com.wxxiaomi.ebs.module.em.api.HyphenateRestAPI;
import com.wxxiaomi.ebs.module.em.comm.helper.HeaderHelper;
import com.wxxiaomi.ebs.module.em.comm.wrapper.HeaderWrapper;

public class HyphenateFile extends HyphenateRestAPI implements FileAPI {
    private static final String ROOT_URI = "/chatfiles";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object uploadFile(Object file) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getUploadHeaderWithToken();

        return getInvoker().uploadFile(url, header, (File) file);
    }

    public Object downloadFile(String fileUUID, String shareSecret, Boolean isThumbnail) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + fileUUID;
        HeaderWrapper header = HeaderHelper.getDownloadHeaderWithToken(shareSecret, isThumbnail);

        return getInvoker().downloadFile(url, header);
    }
}
