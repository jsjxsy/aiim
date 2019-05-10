package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 系统消息表的实体类
 */
public class SystemMessage
{
    /**
     * 构造函数
     */
    public SystemMessage()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 消息编号
	* @param toUserID 消息接收人编号
	* @param sendTime 消息发送时间
	* @param msgTypeID 消息类型编号
	* @param msgTitle 消息标题
	* @param msgContent 消息内容
	* @param readFlag 阅读标志
	* @param archivesTypeID 档案分类编号
	* @param nBXH 档案内部序号
	*/
	public SystemMessage(int iD,int toUserID,Date sendTime,int msgTypeID,String msgTitle,String msgContent,boolean readFlag,int archivesTypeID,int nBXH)
	{
		// Table Name: SystemMessage
		// Columns List,Can Used in SELECT SQL: ID,ToUserID,SendTime,MsgTypeID,MsgTitle,MsgContent,ReadFlag,ArchivesTypeID,NBXH
		// Columns List,Can Used in INSERT SQL: :ID,:ToUserID,:SendTime,:MsgTypeID,:MsgTitle,:MsgContent,:ReadFlag,:ArchivesTypeID,:NBXH
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ToUserID=:ToUserID,SendTime=:SendTime,MsgTypeID=:MsgTypeID,MsgTitle=:MsgTitle,MsgContent=:MsgContent,ReadFlag=:ReadFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH

		this.iD = iD;
		this.toUserID = toUserID;
		this.sendTime = sendTime;
		this.msgTypeID = msgTypeID;
		this.msgTitle = msgTitle;
		this.msgContent = msgContent;
		this.readFlag = readFlag;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 消息编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：消息编号
	 * @return 消息编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：消息编号
	 * @param iD 消息编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 消息接收人编号
	 */
	private int toUserID=0;

	/**
	 * 获取属性值：消息接收人编号
	 * @return 消息接收人编号
	 */
	public int getToUserID()
	{
		return toUserID;
	}

	/**
	 * 设置属性值：消息接收人编号
	 * @param toUserID 消息接收人编号
	 */
	public void setToUserID(int toUserID)
	{
		this.toUserID = toUserID;
	}

	/**
	 * 消息发送时间
	 */
	private Date sendTime;

	/**
	 * 获取属性值：消息发送时间
	 * @return 消息发送时间
	 */
	public Date getSendTime()
	{
		return sendTime;
	}

	/**
	 * 设置属性值：消息发送时间
	 * @param sendTime 消息发送时间
	 */
	public void setSendTime(Date sendTime)
	{
		this.sendTime = sendTime;
	}

	/**
	 * 消息类型编号
	 */
	private int msgTypeID=0;

	/**
	 * 获取属性值：消息类型编号
	 * @return 消息类型编号
	 */
	public int getMsgTypeID()
	{
		return msgTypeID;
	}

	/**
	 * 设置属性值：消息类型编号
	 * @param msgTypeID 消息类型编号
	 */
	public void setMsgTypeID(int msgTypeID)
	{
		this.msgTypeID = msgTypeID;
	}

	/**
	 * 消息标题
	 */
	private String msgTitle=null;

	/**
	 * 获取属性值：消息标题
	 * @return 消息标题
	 */
	public String getMsgTitle()
	{
		return msgTitle;
	}

	/**
	 * 设置属性值：消息标题
	 * @param msgTitle 消息标题
	 */
	public void setMsgTitle(String msgTitle)
	{
		this.msgTitle = msgTitle;
	}

	/**
	 * 消息内容
	 */
	private String msgContent=null;

	/**
	 * 获取属性值：消息内容
	 * @return 消息内容
	 */
	public String getMsgContent()
	{
		return msgContent;
	}

	/**
	 * 设置属性值：消息内容
	 * @param msgContent 消息内容
	 */
	public void setMsgContent(String msgContent)
	{
		this.msgContent = msgContent;
	}

	/**
	 * 阅读标志
	 */
	private boolean readFlag=false;

	/**
	 * 获取属性值：阅读标志
	 * @return 阅读标志
	 */
	public boolean getReadFlag()
	{
		return readFlag;
	}

	/**
	 * 设置属性值：阅读标志
	 * @param readFlag 阅读标志
	 */
	public void setReadFlag(boolean readFlag)
	{
		this.readFlag = readFlag;
	}

	/**
	 * 档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 档案内部序号
	 */
	private int nBXH=0;

	/**
	 * 获取属性值：档案内部序号
	 * @return 档案内部序号
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * 设置属性值：档案内部序号
	 * @param nBXH 档案内部序号
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public SystemMessage clone()
	{
		SystemMessage item = new SystemMessage(iD,toUserID,sendTime,msgTypeID,msgTitle,msgContent,readFlag,archivesTypeID,nBXH);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param systemMessage 指定的对象源
	*/
	public void cloneFrom(SystemMessage systemMessage)
	{
		this.iD = systemMessage.getID();
		this.toUserID = systemMessage.getToUserID();
		this.sendTime = systemMessage.getSendTime();
		this.msgTypeID = systemMessage.getMsgTypeID();
		this.msgTitle = systemMessage.getMsgTitle();
		this.msgContent = systemMessage.getMsgContent();
		this.readFlag = systemMessage.getReadFlag();
		this.archivesTypeID = systemMessage.getArchivesTypeID();
		this.nBXH = systemMessage.getNBXH();
		this.keyInCol = systemMessage.getKeyInCol();
		this.tag = systemMessage.getTag();
	}


    
}



