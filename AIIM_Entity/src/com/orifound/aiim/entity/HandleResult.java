package com.orifound.aiim.entity;

import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������ض���
 *
 */
public class HandleResult{


	/**
	 * �����Ƿ�ɹ�
	 */
	private boolean success = true;
	
	/**
	 * ������Ϣ����ϸ����
	 */
	private StringBuilder content = new StringBuilder();
	
	/**
	 * ���ش�����Ϣ
	 */
	private String errorContent = "";
	
	/**
	 * ǩ����
	 */
	private String signer;
	
	/**
	 * �����ļ����ص�ַ
	 */
	private String decryptFileAddress;
	
	public HandleResult() {}
	
	public HandleResult(ErrInfo pErrInfo, boolean pFlag) {
		content.append(pErrInfo.toString());
		success = pFlag;
		errorContent = content.toString();
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public StringBuilder getContent() {
		return content;
	}

	public void setContent(StringBuilder content) {
		this.content = content;
	}

	public String getSigner() {
		return signer;
	}

	public void setSigner(String signer) {
		this.signer = signer;
	}

	public String getDecryptFileAddress() {
		return decryptFileAddress;
	}

	public void setDecryptFileAddress(String decryptFileAddress) {
		this.decryptFileAddress = decryptFileAddress;
	}

	public String getErrorContent() {
		return errorContent;
	}

	public void setErrorContent(String errorContent) {
		this.errorContent = errorContent;
	}

	@Override
	public String toString() {
		return errorContent;
	}
}