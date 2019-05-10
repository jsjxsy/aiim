package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 盘点报告-实物档案不在架
 */
public class StockReportPaperNotExist
{
    /**
     * 构造函数
     */
    public StockReportPaperNotExist()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param stocktakingID 盘点工作任务编号
	* @param archivesBarcode 档案条码
	* @param archivesTypeID 档案分类编号
	* @param nBXH 内部序号
	* @param archivesID 档号
	* @param title 题名
	* @param archivesBoxBarcode 盒条码
	* @param storeAddressFullName 上架位置完整名称
	*/
	public StockReportPaperNotExist(int stocktakingID,String archivesBarcode,int archivesTypeID,int nBXH,String archivesID,String title,String archivesBoxBarcode,String storeAddressFullName)
	{
		// Table Name: StocktakingReport_PaperNotExists
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBoxBarcode,StoreAddressFullName
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBoxBarcode,:StoreAddressFullName
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName

		this.stocktakingID = stocktakingID;
		this.archivesBarcode = archivesBarcode;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeAddressFullName = storeAddressFullName;
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
	 * 内部序号
	 */
	private int nBXH=0;

	/**
	 * 获取属性值：内部序号
	 * @return 内部序号
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * 设置属性值：内部序号
	 * @param nBXH 内部序号
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * 档号
	 */
	private String archivesID=null;

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * 题名
	 */
	private String title=null;

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		this.title = title;
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
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StockReportPaperNotExist clone()
	{
		StockReportPaperNotExist item = new StockReportPaperNotExist(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,archivesBoxBarcode,storeAddressFullName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stockReportPaperNotExists 指定的对象源
	*/
	public void cloneFrom(StockReportPaperNotExist stockReportPaperNotExists)
	{
		this.stocktakingID = stockReportPaperNotExists.getStocktakingID();
		this.archivesBarcode = stockReportPaperNotExists.getArchivesBarcode();
		this.archivesTypeID = stockReportPaperNotExists.getArchivesTypeID();
		this.nBXH = stockReportPaperNotExists.getNBXH();
		this.archivesID = stockReportPaperNotExists.getArchivesID();
		this.title = stockReportPaperNotExists.getTitle();
		this.archivesBoxBarcode = stockReportPaperNotExists.getArchivesBoxBarcode();
		this.storeAddressFullName = stockReportPaperNotExists.getStoreAddressFullName();
		this.keyInCol = stockReportPaperNotExists.getKeyInCol();
		this.tag = stockReportPaperNotExists.getTag();
	}



    
}



