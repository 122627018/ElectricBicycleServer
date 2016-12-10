package com.wxxiaomi.ebs.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.wxxiaomi.ebs.service.UserService;

@Controller
public class UpLoadAction {

	@Resource
	UserService userService;
	// 这里用List来存放上传过来的文件，file同样指的是临时文件夹中的临时文件，而不是真正上传过来的文件
	// private List<File> file;
	private File file;
	// 这个List存放的是文件的名字，和List<File>中的文件相对应
	// private List<String> fileFileName;
	private String fileName;

	// private List<String> fileContentType;

	private int userid;

	/**
	 * 头像上传 逻辑： 1.取得用户id和上传的头像 
	 * 2.存储头像到本地并取得对应的地址 
	 * 3.调用service保存id对应用户的头像地址
	 * 4.返回保存的头像地址
	 * 
	 * @return
	 */
	public String head() {
//		System.out.println("收到头像上传请求:fileName=" + fileName + ",userid="
//				+ userid);
//		if (fileName == null) {
//			fileName = "demodemo.jpg";
//		}
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
//
//			String savaPath = "/upload/" + savaName;
//			System.out.println("最终返回的存储路径是：" + savaName);
//			if (userService.updateUserHead(userid, savaPath)) {
//				state = "200";
//				infos = savaPath;
//			} else {
//				System.out.println("头像上传失败");
//				state = "404";
//				error = "上传失败";
//			}
//
//			os.close();
//			is.close();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		return "head";
	}

	private String state;
	private String error;
	private Object infos;

	public String getState() {
		return state;
	}

	public String getError() {
		return error;
	}

	public Object getInfos() {
		return infos;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}
