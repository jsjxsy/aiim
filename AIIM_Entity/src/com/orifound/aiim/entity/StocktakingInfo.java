package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 库房盘点工作信息
 */
public class StocktakingInfo
{
    /**
     * 构造函数
     */
    public StocktakingInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 盘点工作编号
	* @param storeroomID 库房编号
	* @param stocktakingDate 盘点日期
	* @param stocktakedFlag 已盘点标识
	*/
	public StocktakingInfo(int iD,int storeroomID,Date stocktakingDate,boolean stocktakedFlag)
	{
		// Table Name: StocktakingInfo
		// Columns List,Can Used in SELECT SQL: ID,StoreroomID,StocktakingDate,StocktakedFlag
		// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:StocktakingDate,:StocktakedFlag
		// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,StocktakingDate=:StocktakingDate,StocktakedFlag=:StocktakedFlag

		this.iD = iD;
		this.storeroomID = storeroomID;
		this.stocktakingDate = stocktakingDate;
		this.stocktakedFlag = stocktakedFlag;
	}

	/**
	* 带字段参数的构造函数
	* @param iD 盘点工作编号
	* @param storeroomID 库房编号
	* @param storeroomName 库房名称//符加参数（StocktakingInfo表结构中没有，需关联其它表）
	* @param stocktakingDate 盘点日期
	* @param stocktakedFlag 已盘点标识
	*/
	public StocktakingInfo(int iD,int storeroomID,String storeroomName,Date stocktakingDate,boolean stocktakedFlag)
	{
		// Table Name: StocktakingInfo
		// Columns List,Can Used in SELECT SQL: ID,StoreroomID,StocktakingDate,StocktakedFlag
		// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:StocktakingDate,:StocktakedFlag
		// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,StocktakingDate=:StocktakingDate,StocktakedFlag=:StocktakedFlag

		this.iD = iD;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.stocktakingDate = stocktakingDate;
		this.stocktakedFlag = stocktakedFlag;
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
	 * 盘点工作编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：盘点工作编号
	 * @return 盘点工作编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：盘点工作编号
	 * @param iD 盘点工作编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 库房编号
	 */
	private int storeroomID=0;

	/**
	 * 获取属性值：库房编号
	 * @return 库房编号
	 */
	public int getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * 设置属性值：库房编号
	 * @param storeroomID 库房编号
	 */
	public void setStoreroomID(int storeroomID)
	{
		this.storeroomID = storeroomID;
	}
	
	/**
	 * 库房名称
	 */
	private String storeroomName = null;
	
	/**
	 * 获取属性值：库房名称
	 * @return
	 */
	public String getStoreroomName() {
		return storeroomName;
	}

	/**
	 * 设置属性值：库房名称
	 * @param storeroomName
	 */
	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}
	/**
	 * 盘点日期
	 */
	private Date stocktakingDate;

	/**
	 * 获取属性值：盘点日期
	 * @return 盘点日期
	 */
	public Date getStocktakingDate()
	{
		return stocktakingDate;
	}

	/**
	 * 设置属性值：盘点日期
	 * @param stocktakingDate 盘点日期
	 */
	public void setStocktakingDate(Date stocktakingDate)
	{
		this.stocktakingDate = stocktakingDate;
	}

	/**
	 * 已盘点标识
	 */
	private boolean stocktakedFlag=false;

	/**
	 * 获取属性值：已盘点标识
	 * @return 
	 */
	public boolean getStocktakedFlag()
	{
		return stocktakedFlag;
	}

	/**
	 * 设置属性值：已盘点标识
	 * @param stocktakedFlag 
	 */
	public void setStocktakedFlag(boolean stocktakedFlag)
	{
		this.stocktakedFlag = stocktakedFlag;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StocktakingInfo clone()
	{
		StocktakingInfo item = new StocktakingInfo(iD, storeroomID, storeroomName, stocktakingDate, stocktakedFlag);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stocktakingInfo 指定的对象源
	*/
	public void cloneFrom(StocktakingInfo stocktakingInfo)
	{
		this.iD = stocktakingInfo.getID();
		this.storeroomID = stocktakingInfo.getStoreroomID();
		this.storeroomName = stocktakingInfo.getStoreroomName();
		this.stocktakingDate = stocktakingInfo.getStocktakingDate();
		this.stocktakedFlag = stocktakingInfo.getStocktakedFlag();
		this.keyInCol = stocktakingInfo.getKeyInCol();
		this.tag = stocktakingInfo.getTag();
	}

    
}



