package com.orifound.aiim.web.util;

import javax.activation.DataHandler;

/**
 * 
 *
 */
public class UploadFile {

	private String title;// 上传文件名

	private DataHandler file;// 文件

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public DataHandler getFile() {
		return file;
	}

	public void setFile(DataHandler file) {
		this.file = file;
	}
}