package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 库房盘点的档案盒与档案卷详细
 */
public class StocktakingArchivesDetail
{
    /**
     * 构造函数
     */
    public StocktakingArchivesDetail()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param stocktakingID 盘点工作任务编号
	* @param archivesBoxBarcode 档案盒条码
	* @param archivesBarcode 档案条码
	*/
	public StocktakingArchivesDetail(int stocktakingID,String archivesBoxBarcode,String archivesBarcode)
	{
		// Table Name: StocktakingArchivesDetails
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,ArchivesBarcode
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:ArchivesBarcode
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,ArchivesBarcode=:ArchivesBarcode

		this.stocktakingID = stocktakingID;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.archivesBarcode = archivesBarcode;
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
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StocktakingArchivesDetail clone()
	{
		StocktakingArchivesDetail item = new StocktakingArchivesDetail(stocktakingID,archivesBoxBarcode,archivesBarcode);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stocktakingArchivesDetails 指定的对象源
	*/
	public void cloneFrom(StocktakingArchivesDetail stocktakingArchivesDetails)
	{
		this.stocktakingID = stocktakingArchivesDetails.getStocktakingID();
		this.archivesBoxBarcode = stocktakingArchivesDetails.getArchivesBoxBarcode();
		this.archivesBarcode = stocktakingArchivesDetails.getArchivesBarcode();
		this.keyInCol = stocktakingArchivesDetails.getKeyInCol();
		this.tag = stocktakingArchivesDetails.getTag();
	}



    
}



