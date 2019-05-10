package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ϵͳ��Ϣ���ʵ����
 */
public class SystemMessage
{
    /**
     * ���캯��
     */
    public SystemMessage()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ϣ���
	* @param toUserID ��Ϣ�����˱��
	* @param sendTime ��Ϣ����ʱ��
	* @param msgTypeID ��Ϣ���ͱ��
	* @param msgTitle ��Ϣ����
	* @param msgContent ��Ϣ����
	* @param readFlag �Ķ���־
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * ��Ϣ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����Ϣ���
	 * @return ��Ϣ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����Ϣ���
	 * @param iD ��Ϣ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��Ϣ�����˱��
	 */
	private int toUserID=0;

	/**
	 * ��ȡ����ֵ����Ϣ�����˱��
	 * @return ��Ϣ�����˱��
	 */
	public int getToUserID()
	{
		return toUserID;
	}

	/**
	 * ��������ֵ����Ϣ�����˱��
	 * @param toUserID ��Ϣ�����˱��
	 */
	public void setToUserID(int toUserID)
	{
		this.toUserID = toUserID;
	}

	/**
	 * ��Ϣ����ʱ��
	 */
	private Date sendTime;

	/**
	 * ��ȡ����ֵ����Ϣ����ʱ��
	 * @return ��Ϣ����ʱ��
	 */
	public Date getSendTime()
	{
		return sendTime;
	}

	/**
	 * ��������ֵ����Ϣ����ʱ��
	 * @param sendTime ��Ϣ����ʱ��
	 */
	public void setSendTime(Date sendTime)
	{
		this.sendTime = sendTime;
	}

	/**
	 * ��Ϣ���ͱ��
	 */
	private int msgTypeID=0;

	/**
	 * ��ȡ����ֵ����Ϣ���ͱ��
	 * @return ��Ϣ���ͱ��
	 */
	public int getMsgTypeID()
	{
		return msgTypeID;
	}

	/**
	 * ��������ֵ����Ϣ���ͱ��
	 * @param msgTypeID ��Ϣ���ͱ��
	 */
	public void setMsgTypeID(int msgTypeID)
	{
		this.msgTypeID = msgTypeID;
	}

	/**
	 * ��Ϣ����
	 */
	private String msgTitle=null;

	/**
	 * ��ȡ����ֵ����Ϣ����
	 * @return ��Ϣ����
	 */
	public String getMsgTitle()
	{
		return msgTitle;
	}

	/**
	 * ��������ֵ����Ϣ����
	 * @param msgTitle ��Ϣ����
	 */
	public void setMsgTitle(String msgTitle)
	{
		this.msgTitle = msgTitle;
	}

	/**
	 * ��Ϣ����
	 */
	private String msgContent=null;

	/**
	 * ��ȡ����ֵ����Ϣ����
	 * @return ��Ϣ����
	 */
	public String getMsgContent()
	{
		return msgContent;
	}

	/**
	 * ��������ֵ����Ϣ����
	 * @param msgContent ��Ϣ����
	 */
	public void setMsgContent(String msgContent)
	{
		this.msgContent = msgContent;
	}

	/**
	 * �Ķ���־
	 */
	private boolean readFlag=false;

	/**
	 * ��ȡ����ֵ���Ķ���־
	 * @return �Ķ���־
	 */
	public boolean getReadFlag()
	{
		return readFlag;
	}

	/**
	 * ��������ֵ���Ķ���־
	 * @param readFlag �Ķ���־
	 */
	public void setReadFlag(boolean readFlag)
	{
		this.readFlag = readFlag;
	}

	/**
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * �����ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ�������ڲ����
	 * @return �����ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ�������ڲ����
	 * @param nBXH �����ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public SystemMessage clone()
	{
		SystemMessage item = new SystemMessage(iD,toUserID,sendTime,msgTypeID,msgTitle,msgContent,readFlag,archivesTypeID,nBXH);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param systemMessage ָ���Ķ���Դ
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



