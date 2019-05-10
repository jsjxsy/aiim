package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 当前档案利用申请的批次号信息表的实体类
 */
public class CurrentRequestID
{
    /**
     * 构造函数
     */
    public CurrentRequestID()
    {
        // TODO: 构造函数内部处理
    }
    
	/**
	* 带字段参数的构造函数
	* @param requestDate 档案利用申请日期
	* @param requestID 最大批次流水号
	*/
	public CurrentRequestID(Date requestDate,int requestID)
	{
		// Columns List,Can Used in SELECT SQL: RequestDate,RequestID
		// Columns List,Can Used in INSERT SQL: pRequestDate,pRequestID
		// Columns List,Can Used in UPDATE SQL: RequestDate=pRequestDate,RequestID=pRequestID

		this.requestDate = requestDate;
		this.requestID = requestID;
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
	 * 档案利用申请日期
	 */
	private Date requestDate;

	/**
	 * 获取属性值：档案利用申请日期
	 * @return 档案利用申请日期
	 */
	public Date getRequestDate()
	{
		return requestDate;
	}

	/**
	 * 设置属性值：档案利用申请日期
	 * @param requestDate 档案利用申请日期
	 */
	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}

	/**
	 * 最大批次流水号
	 */
	private int requestID=0;

	/**
	 * 获取属性值：最大批次流水号
	 * @return 最大批次流水号
	 */
	public int getRequestID()
	{
		return requestID;
	}

	/**
	 * 设置属性值：最大批次流水号
	 * @param requestID 最大批次流水号
	 */
	public void setRequestID(int requestID)
	{
		this.requestID = requestID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CurrentRequestID clone()
	{
		CurrentRequestID item = new CurrentRequestID(requestDate,requestID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


    
}



