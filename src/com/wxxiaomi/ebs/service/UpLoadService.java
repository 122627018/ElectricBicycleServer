package com.wxxiaomi.ebs.service;

import java.io.File;

public interface UpLoadService {

	/**
	 * 上传一张图片
	 * @param file
	 * @return 返回地址
	 */
	String uploadImage(File file,String filename,String description);
}
