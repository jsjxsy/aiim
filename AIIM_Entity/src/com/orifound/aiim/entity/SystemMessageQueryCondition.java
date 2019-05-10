/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * ϵͳ��Ϣ�Ĳ�ѯ����������
 *
 */
public class SystemMessageQueryCondition {

	/**
	 * ���캯��
	 */
	public SystemMessageQueryCondition() {
		
	}
	
	/**
	 * ��Ϣ������ʼʱ��
	 */
	private Date sendTimeBegin = null;

	/**
	 * ��������ֵ����Ϣ������ʼʱ��
	 * @param SendTimeBegin ��Ϣ������ʼʱ��
	 */
	public void setSendTimeBegin(Date sendTimeBegin) {
		this.sendTimeBegin = sendTimeBegin;
	}

	/**
	 * ��ȡ����ֵ����Ϣ������ʼʱ��
	 * @return ��Ϣ������ʼʱ��
	 */
	public Date getSendTimeBegin() {
		return sendTimeBegin;
	}
	
	/**
	 * ��Ϣ���ͽ���ʱ��
	 */
	private Date sendTimeEnd = null;

	/**
	 * ��������ֵ����Ϣ���ͽ���ʱ��
	 * @param sendTimeEnd ��Ϣ���ͽ���ʱ��
	 */
	public void setSendTimeEnd(Date sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}

	/**
	 * ��ȡ����ֵ����Ϣ���ͽ���ʱ��
	 * @return ��Ϣ���ͽ���ʱ��
	 */
	public Date getSendTimeEnd() {
		return sendTimeEnd;
	}

	/**
	 * ��Ϣ����
	 */
	private String msgTitle = "";

	/**
	 * ��������ֵ����Ϣ����
	 * @param msgTitle ��Ϣ����
	 */
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	/**
	 * ��ȡ����ֵ����Ϣ����
	 * @return ��Ϣ����
	 */
	public String getMsgTitle() {
		return msgTitle;
	}

	/**
	 * ��Ϣ����
	 */
	private String msgContent = "";

	/**
	 * ��������ֵ����Ϣ����
	 * @param msgContent ��Ϣ����
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	/**
	 * ��ȡ����ֵ����Ϣ����
	 * @return ��Ϣ����
	 */
	public String getMsgContent() {
		return msgContent;
	}

	

	
}
