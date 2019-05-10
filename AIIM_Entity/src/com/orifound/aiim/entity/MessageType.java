package com.orifound.aiim.entity;

    
/**
 * ��Ϣ�����ֵ���ʵ����
 */
public class MessageType
{
    /**
     * ���캯��
     */
    public MessageType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param msgTypeID ��Ϣ���ͱ��
	* @param dealURI �����URI��ַ
	* @param remark ��ע
	*/
	public MessageType(int msgTypeID,String dealURI,String remark)
	{
		// Table Name: DD_MessageType
		// Columns List,Can Used in SELECT SQL: MsgTypeID,DealURI,Remark
		// Columns List,Can Used in INSERT SQL: :MsgTypeID,:DealURI,:Remark
		// Columns List,Can Used in UPDATE SQL: MsgTypeID=:MsgTypeID,DealURI=:DealURI,Remark=:Remark

		this.msgTypeID = msgTypeID;
		this.dealURI = dealURI;
		this.remark = remark;
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
	 * �����URI��ַ
	 */
	private String dealURI=null;

	/**
	 * ��ȡ����ֵ�������URI��ַ
	 * @return �����URI��ַ
	 */
	public String getDealURI()
	{
		return dealURI;
	}

	/**
	 * ��������ֵ�������URI��ַ
	 * @param dealURI �����URI��ַ
	 */
	public void setDealURI(String dealURI)
	{
		this.dealURI = dealURI;
	}

	/**
	 * ��ע
	 */
	private String remark=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * @param remark ��ע
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public MessageType clone()
	{
		MessageType item = new MessageType(msgTypeID,dealURI,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param messageType ָ���Ķ���Դ
	*/
	public void cloneFrom(MessageType messageType)
	{
		this.msgTypeID = messageType.getMsgTypeID();
		this.dealURI = messageType.getDealURI();
		this.remark = messageType.getRemark();
		this.keyInCol = messageType.getKeyInCol();
		this.tag = messageType.getTag();
	}

    
}



