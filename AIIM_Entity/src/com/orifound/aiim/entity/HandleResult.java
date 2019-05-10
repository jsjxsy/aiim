package com.orifound.aiim.entity;

import com.orifound.aiim.entity.ErrInfo;

/**
 * 处理结果返回对象
 *
 */
public class HandleResult{


	/**
	 * 处理是否成功
	 */
	private boolean success = true;
	
	/**
	 * 错误信息的详细内容
	 */
	private StringBuilder content = new StringBuilder();
	
	/**
	 * 返回错误信息
	 */
	private String errorContent = "";
	
	/**
	 * 签名人
	 */
	private String signer;
	
	/**
	 * 解密文件下载地址
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