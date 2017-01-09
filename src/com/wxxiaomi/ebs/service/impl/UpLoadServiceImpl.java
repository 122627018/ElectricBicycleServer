package com.wxxiaomi.ebs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.wxxiaomi.ebs.service.UpLoadService;

@Service

public class UpLoadServiceImpl implements UpLoadService {

	@Override
	public String uploadImage(File file, String fileName, String description) {
		return "";
//		OutputStream os = null;
//		InputStream is = null;
//		try {
//			String root = ServletActionContext.getServletContext().getRealPath(
//					"/upload");
//			is = new FileInputStream(file);
//			String savaName = fileName.substring(0, fileName.indexOf("."))
//					+ System.currentTimeMillis()
//					+ fileName.substring(fileName.indexOf("."),
//							fileName.length());
//			os = new FileOutputStream(new File(root, savaName));
//			byte[] buffer = new byte[1024];
//			@SuppressWarnings("unused")
//			int length = 0;
//			while (-1 != (length = is.read(buffer, 0, buffer.length))) {
//				os.write(buffer);
//			}
//			String savaPath = "/upload/" + savaName;
//			os.close();
//			is.close();
//			return savaPath;
//		}catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}

}
