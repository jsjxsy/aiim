package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 盘点报告-上架位置不匹配的档案
 */
public class StockReportAddressNotMatch
{
    /**
     * 构造函数
     */
    public StockReportAddressNotMatch()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param stocktakingID 盘点工作任务编号
	* @param archivesBoxBarcode 盒条码
	* @param paperAddressID 实物档案上架位置编号
	* @param paperAddressFullName 实物档案上架位置完整名称
	* @param systemAddressID 系统登记的上架位置编号
	* @param systemAddressFullName 系统中登记的上架位置完整名称
	*/
	public StockReportAddressNotMatch(int stocktakingID,String archivesBoxBarcode,int paperAddressID,String paperAddressFullName,int systemAddressID,String systemAddressFullName)
	{
		// Table Name: StocktakingReport_AddressNotMatch
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,PaperAddressID,PaperAddressFullName,SystemAddressID,SystemAddressFullName
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:PaperAddressID,:PaperAddressFullName,:SystemAddressID,:SystemAddressFullName
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,PaperAddressID=:PaperAddressID,PaperAddressFullName=:PaperAddressFullName,SystemAddressID=:SystemAddressID,SystemAddressFullName=:SystemAddressFullName

		this.stocktakingID = stocktakingID;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.paperAddressID = paperAddressID;
		this.paperAddressFullName = paperAddressFullName;
		this.systemAddressID = systemAddressID;
		this.systemAddressFullName = systemAddressFullName;
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
	 * 实物档案上架位置编号
	 */
	private int paperAddressID=0;

	/**
	 * 获取属性值：实物档案上架位置编号
	 * @return 实物档案上架位置编号
	 */
	public int getPaperAddressID()
	{
		return paperAddressID;
	}

	/**
	 * 设置属性值：实物档案上架位置编号
	 * @param paperAddressID 实物档案上架位置编号
	 */
	public void setPaperAddressID(int paperAddressID)
	{
		this.paperAddressID = paperAddressID;
	}

	/**
	 * 实物档案上架位置完整名称
	 */
	private String paperAddressFullName=null;

	/**
	 * 获取属性值：实物档案上架位置完整名称
	 * @return 实物档案上架位置完整名称
	 */
	public String getPaperAddressFullName()
	{
		return paperAddressFullName;
	}

	/**
	 * 设置属性值：实物档案上架位置完整名称
	 * @param paperAddressFullName 实物档案上架位置完整名称
	 */
	public void setPaperAddressFullName(String paperAddressFullName)
	{
		this.paperAddressFullName = paperAddressFullName;
	}

	/**
	 * 系统登记的上架位置编号
	 */
	private int systemAddressID=0;

	/**
	 * 获取属性值：系统登记的上架位置编号
	 * @return 系统登记的上架位置编号
	 */
	public int getSystemAddressID()
	{
		return systemAddressID;
	}

	/**
	 * 设置属性值：系统登记的上架位置编号
	 * @param systemAddressID 系统登记的上架位置编号
	 */
	public void setSystemAddressID(int systemAddressID)
	{
		this.systemAddressID = systemAddressID;
	}

	/**
	 * 系统中登记的上架位置完整名称
	 */
	private String systemAddressFullName=null;

	/**
	 * 获取属性值：系统中登记的上架位置完整名称
	 * @return 系统中登记的上架位置完整名称
	 */
	public String getSystemAddressFullName()
	{
		return systemAddressFullName;
	}

	/**
	 * 设置属性值：系统中登记的上架位置完整名称
	 * @param systemAddressFullName 系统中登记的上架位置完整名称
	 */
	public void setSystemAddressFullName(String systemAddressFullName)
	{
		this.systemAddressFullName = systemAddressFullName;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StockReportAddressNotMatch clone()
	{
		StockReportAddressNotMatch item = new StockReportAddressNotMatch(stocktakingID,archivesBoxBarcode,paperAddressID,paperAddressFullName,systemAddressID,systemAddressFullName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stockReportAddressNotMatch 指定的对象源
	*/
	public void cloneFrom(StockReportAddressNotMatch stockReportAddressNotMatch)
	{
		this.stocktakingID = stockReportAddressNotMatch.getStocktakingID();
		this.archivesBoxBarcode = stockReportAddressNotMatch.getArchivesBoxBarcode();
		this.paperAddressID = stockReportAddressNotMatch.getPaperAddressID();
		this.paperAddressFullName = stockReportAddressNotMatch.getPaperAddressFullName();
		this.systemAddressID = stockReportAddressNotMatch.getSystemAddressID();
		this.systemAddressFullName = stockReportAddressNotMatch.getSystemAddressFullName();
		this.keyInCol = stockReportAddressNotMatch.getKeyInCol();
		this.tag = stockReportAddressNotMatch.getTag();
	}


    
}



