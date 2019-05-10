package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 库房盘点的设备位置与档案盒详细
 */
public class StocktakingAddressBoxDetail
{
    /**
     * 构造函数
     */
    public StocktakingAddressBoxDetail()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param stocktakingID 盘点工作任务编号
	* @param storeAddressBarcode 档案层条码
	* @param archivesBoxBarcode 档案盒条码
	*/
	public StocktakingAddressBoxDetail(int stocktakingID,String storeAddressBarcode,String archivesBoxBarcode)
	{
		// Table Name: StocktakingAddressBoxDetails
		// Columns List,Can Used in SELECT SQL: StocktakingID,StoreAddressBarcode,ArchivesBoxBarcode
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:StoreAddressBarcode,:ArchivesBoxBarcode
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,StoreAddressBarcode=:StoreAddressBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode

		this.stocktakingID = stocktakingID;
		this.storeAddressBarcode = storeAddressBarcode;
		this.archivesBoxBarcode = archivesBoxBarcode;
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
	 * 档案层条码
	 */
	private String storeAddressBarcode=null;

	/**
	 * 获取属性值：档案层条码
	 * @return 档案层条码
	 */
	public String getStoreAddressBarcode()
	{
		return storeAddressBarcode;
	}

	/**
	 * 设置属性值：档案层条码
	 * @param storeAddressBarcode 档案层条码
	 */
	public void setStoreAddressBarcode(String storeAddressBarcode)
	{
		this.storeAddressBarcode = storeAddressBarcode;
	}

	/**
	 * 档案盒条码
	 */
	private String archivesBoxBarcode=null;

	/**
	 * 获取属性值：档案盒条码
	 * @return 档案盒条码
	 */
	public String getArchivesBoxBarcode()
	{
		return archivesBoxBarcode;
	}

	/**
	 * 设置属性值：档案盒条码
	 * @param archivesBoxBarcode 档案盒条码
	 */
	public void setArchivesBoxBarcode(String archivesBoxBarcode)
	{
		this.archivesBoxBarcode = archivesBoxBarcode;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StocktakingAddressBoxDetail clone()
	{
		StocktakingAddressBoxDetail item = new StocktakingAddressBoxDetail(stocktakingID,storeAddressBarcode,archivesBoxBarcode);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stocktakingAddressBoxDetails 指定的对象源
	*/
	public void cloneFrom(StocktakingAddressBoxDetail stocktakingAddressBoxDetails)
	{
		this.stocktakingID = stocktakingAddressBoxDetails.getStocktakingID();
		this.storeAddressBarcode = stocktakingAddressBoxDetails.getStoreAddressBarcode();
		this.archivesBoxBarcode = stocktakingAddressBoxDetails.getArchivesBoxBarcode();
		this.keyInCol = stocktakingAddressBoxDetails.getKeyInCol();
		this.tag = stocktakingAddressBoxDetails.getTag();
	}



    
}



