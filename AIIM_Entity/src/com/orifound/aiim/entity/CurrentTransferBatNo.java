package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * 当前移交批次信息表的实体类
 */
public class CurrentTransferBatNo
{
    /**
     * 构造函数
     */
    public CurrentTransferBatNo()
    {
    }
    
	/**
	* 带字段参数的构造函数
	* @param batNoCreateDate 移交批次创建日期
	* @param currentNo 当前序号
	* @param batNoCreateUserID 创建批次号的用户
	* @param currentBatNo 当前移交批次号
	*/
	public CurrentTransferBatNo(Date batNoCreateDate,int currentNo,int batNoCreateUserID,String currentBatNo)
	{
		// Columns List,Can Used in SELECT SQL: BatNoCreateDate,CurrentNo,BatNoCreateUserID,CurrentBatNo
		// Columns List,Can Used in INSERT SQL: pBatNoCreateDate,pCurrentNo,pBatNoCreateUserID,pCurrentBatNo
		// Columns List,Can Used in UPDATE SQL: BatNoCreateDate=pBatNoCreateDate,CurrentNo=pCurrentNo,BatNoCreateUserID=pBatNoCreateUserID,CurrentBatNo=pCurrentBatNo

		this.batNoCreateDate = batNoCreateDate;
		this.currentNo = currentNo;
		this.batNoCreateUserID = batNoCreateUserID;
		this.currentBatNo = currentBatNo;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}
	/**
	 * 设置属性值：成员对象在集合中的关键字
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
	 */
	public Object getTag()
	{
		return tag;
	}
	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 移交批次创建日期
	 */
	private Date batNoCreateDate;

	/**
	 * 获取属性值：移交批次创建日期
	 */
	public Date getBatNoCreateDate()
	{
		return batNoCreateDate;
	}
	/**
	 * 设置属性值：移交批次创建日期
	 */
	public void setBatNoCreateDate(Date batNoCreateDate)
	{
		this.batNoCreateDate = batNoCreateDate;
	}

	/**
	 * 当前序号
	 */
	private int currentNo=0;

	/**
	 * 获取属性值：当前序号
	 */
	public int getCurrentNo()
	{
		return currentNo;
	}
	/**
	 * 设置属性值：当前序号
	 */
	public void setCurrentNo(int currentNo)
	{
		this.currentNo = currentNo;
	}

	/**
	 * 创建批次号的用户
	 */
	private int batNoCreateUserID=0;

	/**
	 * 获取属性值：创建批次号的用户
	 */
	public int getBatNoCreateUserID()
	{
		return batNoCreateUserID;
	}
	/**
	 * 设置属性值：创建批次号的用户
	 */
	public void setBatNoCreateUserID(int batNoCreateUserID)
	{
		this.batNoCreateUserID = batNoCreateUserID;
	}

	/**
	 * 当前移交批次号
	 */
	private String currentBatNo=null;

	/**
	 * 获取属性值：当前移交批次号
	 */
	public String getCurrentBatNo()
	{
		return currentBatNo;
	}
	/**
	 * 设置属性值：当前移交批次号
	 */
	public void setCurrentBatNo(String currentBatNo)
	{
		this.currentBatNo = currentBatNo;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CurrentTransferBatNo clone()
	{
		CurrentTransferBatNo item = new CurrentTransferBatNo(batNoCreateDate,currentNo,batNoCreateUserID,currentBatNo);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


    
}



