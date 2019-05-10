package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 盘点报告-系统中不在架的档案
 */
public class StockReportSystemNotExist
{
    /**
     * 构造函数
     */
    public StockReportSystemNotExist()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param stocktakingID 盘点工作任务编号
	* @param archivesBarcode 档案条码
	* @param archivesBoxBarcode 盒条码
	* @param storeAddressFullName 上架位置完整名称
	* @param storeStatus 馆藏状态
	*/
	public StockReportSystemNotExist(int stocktakingID,String archivesBarcode,String archivesBoxBarcode,String storeAddressFullName,EnumStoreStatus storeStatus)
	{
		// Table Name: StocktakingReport_SystemNotExists
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesBoxBarcode,StoreAddressFullName,StoreStatus
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreAddressFullName,:StoreStatus
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName,StoreStatus=:StoreStatus

		this.stocktakingID = stocktakingID;
		this.archivesBarcode = archivesBarcode;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeAddressFullName = storeAddressFullName;
		this.storeStatus = storeStatus;
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
	 * 盘点工作任务编号
	 */
	private int stocktakingID=0;

	/**
	 * 获取属性值：盘点工作任务编号
	 * @return 盘点工作任务编号
	 */
	public int getStocktakingID()
	{
		return stocktakingID;
	}

	/**
	 * 设置属性值：盘点工作任务编号
	 * @param stocktakingID 盘点工作任务编号
	 */
	public void setStocktakingID(int stocktakingID)
	{
		this.stocktakingID = stocktakingID;
	}

	/**
	 * 档案条码
	 */
	private String archivesBarcode=null;

	/**
	 * 获取属性值：档案条码
	 * @return 档案条码
	 */
	public String getArchivesBarcode()
	{
		return archivesBarcode;
	}

	/**
	 * 设置属性值：档案条码
	 * @param archivesBarcode 档案条码
	 */
	public void setArchivesBarcode(String archivesBarcode)
	{
		this.archivesBarcode = archivesBarcode;
	}

	/**
	 * 盒条码
	 */
	private String archivesBoxBarcode=null;

	/**
	 * 获取属性值：盒条码
	 * @return 盒条码
	 */
	public String getArchivesBoxBarcode()
	{
		return archivesBoxBarcode;
	}

	/**
	 * 设置属性值：盒条码
	 * @param archivesBoxBarcode 盒条码
	 */
	public void setArchivesBoxBarcode(String archivesBoxBarcode)
	{
		this.archivesBoxBarcode = archivesBoxBarcode;
	}

	/**
	 * 上架位置完整名称
	 */
	private String storeAddressFullName=null;

	/**
	 * 获取属性值：上架位置完整名称
	 * @return 上架位置完整名称
	 */
	public String getStoreAddressFullName()
	{
		return storeAddressFullName;
	}

	/**
	 * 设置属性值：上架位置完整名称
	 * @param storeAddressFullName 上架位置完整名称
	 */
	public void setStoreAddressFullName(String storeAddressFullName)
	{
		this.storeAddressFullName = storeAddressFullName;
	}

	/**
	 * 馆藏状态
	 */
	private EnumStoreStatus storeStatus = EnumStoreStatus.NONE;
	
	/**
	 * 获取属性值：馆藏状态
	 * @return 馆藏状态
	 */
	public EnumStoreStatus getStoreStatus() {
		return storeStatus;
	}

	/**
	 * 设置属性值：馆藏状态
	 * @param storeStatus 馆藏状态
	 */	
	public void setStoreStatus(EnumStoreStatus storeStatus) {
		this.storeStatus = storeStatus;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StockReportSystemNotExist clone()
	{
		StockReportSystemNotExist item = new StockReportSystemNotExist(stocktakingID,archivesBarcode,archivesBoxBarcode,storeAddressFullName,storeStatus);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stockReportSystemNotExists 指定的对象源
	*/
	public void cloneFrom(StockReportSystemNotExist stockReportSystemNotExists)
	{
		this.stocktakingID = stockReportSystemNotExists.getStocktakingID();
		this.archivesBarcode = stockReportSystemNotExists.getArchivesBarcode();
		this.archivesBoxBarcode = stockReportSystemNotExists.getArchivesBoxBarcode();
		this.storeAddressFullName = stockReportSystemNotExists.getStoreAddressFullName();
		this.storeStatus = stockReportSystemNotExists.getStoreStatus();
		this.keyInCol = stockReportSystemNotExists.getKeyInCol();
		this.tag = stockReportSystemNotExists.getTag();
	}

 
    
}



