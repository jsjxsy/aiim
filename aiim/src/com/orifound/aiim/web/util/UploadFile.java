package com.orifound.aiim.web.util;

import javax.activation.DataHandler;

/**
 * 
 *
 */
public class UploadFile {

	private String title;// �ϴ��ļ���

	private DataHandler file;// �ļ�

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