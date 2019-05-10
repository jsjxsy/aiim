/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * 系统消息的查询条件对象类
 *
 */
public class SystemMessageQueryCondition {

	/**
	 * 构造函数
	 */
	public SystemMessageQueryCondition() {
		
	}
	
	/**
	 * 消息发送起始时间
	 */
	private Date sendTimeBegin = null;

	/**
	 * 设置属性值：消息发送起始时间
	 * @param SendTimeBegin 消息发送起始时间
	 */
	public void setSendTimeBegin(Date sendTimeBegin) {
		this.sendTimeBegin = sendTimeBegin;
	}

	/**
	 * 获取属性值：消息发送起始时间
	 * @return 消息发送起始时间
	 */
	public Date getSendTimeBegin() {
		return sendTimeBegin;
	}
	
	/**
	 * 消息发送结束时间
	 */
	private Date sendTimeEnd = null;

	/**
	 * 设置属性值：消息发送结束时间
	 * @param sendTimeEnd 消息发送结束时间
	 */
	public void setSendTimeEnd(Date sendTimeEnd) {
		this.sendTimeEnd = sendTimeEnd;
	}

	/**
	 * 获取属性值：消息发送结束时间
	 * @return 消息发送结束时间
	 */
	public Date getSendTimeEnd() {
		return sendTimeEnd;
	}

	/**
	 * 消息标题
	 */
	private String msgTitle = "";

	/**
	 * 设置属性值：消息标题
	 * @param msgTitle 消息标题
	 */
	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	/**
	 * 获取属性值：消息标题
	 * @return 消息标题
	 */
	public String getMsgTitle() {
		return msgTitle;
	}

	/**
	 * 消息内容
	 */
	private String msgContent = "";

	/**
	 * 设置属性值：消息内容
	 * @param msgContent 消息内容
	 */
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	/**
	 * 获取属性值：消息内容
	 * @return 消息内容
	 */
	public String getMsgContent() {
		return msgContent;
	}

	

	
}
