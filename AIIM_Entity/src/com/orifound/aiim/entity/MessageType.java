package com.orifound.aiim.entity;

    
/**
 * 消息类型字典表的实体类
 */
public class MessageType
{
    /**
     * 构造函数
     */
    public MessageType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param msgTypeID 消息类型编号
	* @param dealURI 处理的URI地址
	* @param remark 备注
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
	 * 处理的URI地址
	 */
	private String dealURI=null;

	/**
	 * 获取属性值：处理的URI地址
	 * @return 处理的URI地址
	 */
	public String getDealURI()
	{
		return dealURI;
	}

	/**
	 * 设置属性值：处理的URI地址
	 * @param dealURI 处理的URI地址
	 */
	public void setDealURI(String dealURI)
	{
		this.dealURI = dealURI;
	}

	/**
	 * 备注
	 */
	private String remark=null;

	/**
	 * 获取属性值：备注
	 * @return 备注
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * 设置属性值：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public MessageType clone()
	{
		MessageType item = new MessageType(msgTypeID,dealURI,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param messageType 指定的对象源
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



