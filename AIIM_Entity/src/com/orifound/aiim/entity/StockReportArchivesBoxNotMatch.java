package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 盘点报告-档案装盒不匹配
 */
public class StockReportArchivesBoxNotMatch
{
    /**
     * 构造函数
     */
    public StockReportArchivesBoxNotMatch()
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
	* @param paperBoxBarcode 实物所在的盒条码
	* @param systemBoxBarcode 系统中关联的盒条码
	*/
	public StockReportArchivesBoxNotMatch(int stocktakingID,String archivesBarcode,int archivesTypeID,int nBXH,String archivesID,String title,String paperBoxBarcode,String systemBoxBarcode)
	{
		// Table Name: StocktakingReport_ArchivesBoxNotMatch
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,PaperBoxBarcode,SystemBoxBarcode
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:PaperBoxBarcode,:SystemBoxBarcode
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,PaperBoxBarcode=:PaperBoxBarcode,SystemBoxBarcode=:SystemBoxBarcode

		this.stocktakingID = stocktakingID;
		this.archivesBarcode = archivesBarcode;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.paperBoxBarcode = paperBoxBarcode;
		this.systemBoxBarcode = systemBoxBarcode;
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
	 * 实物所在的盒条码
	 */
	private String paperBoxBarcode=null;

	/**
	 * 获取属性值：实物所在的盒条码
	 * @return 实物所在的盒条码
	 */
	public String getPaperBoxBarcode()
	{
		return paperBoxBarcode;
	}

	/**
	 * 设置属性值：实物所在的盒条码
	 * @param paperBoxBarcode 实物所在的盒条码
	 */
	public void setPaperBoxBarcode(String paperBoxBarcode)
	{
		this.paperBoxBarcode = paperBoxBarcode;
	}

	/**
	 * 系统中关联的盒条码
	 */
	private String systemBoxBarcode=null;

	/**
	 * 获取属性值：系统中关联的盒条码
	 * @return 系统中关联的盒条码
	 */
	public String getSystemBoxBarcode()
	{
		return systemBoxBarcode;
	}

	/**
	 * 设置属性值：系统中关联的盒条码
	 * @param systemBoxBarcode 系统中关联的盒条码
	 */
	public void setSystemBoxBarcode(String systemBoxBarcode)
	{
		this.systemBoxBarcode = systemBoxBarcode;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StockReportArchivesBoxNotMatch clone()
	{
		StockReportArchivesBoxNotMatch item = new StockReportArchivesBoxNotMatch(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,paperBoxBarcode,systemBoxBarcode);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stockReportArchivesBoxNotMatch 指定的对象源
	*/
	public void cloneFrom(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch)
	{
		this.stocktakingID = stockReportArchivesBoxNotMatch.getStocktakingID();
		this.archivesBarcode = stockReportArchivesBoxNotMatch.getArchivesBarcode();
		this.archivesTypeID = stockReportArchivesBoxNotMatch.getArchivesTypeID();
		this.nBXH = stockReportArchivesBoxNotMatch.getNBXH();
		this.archivesID = stockReportArchivesBoxNotMatch.getArchivesID();
		this.title = stockReportArchivesBoxNotMatch.getTitle();
		this.paperBoxBarcode = stockReportArchivesBoxNotMatch.getPaperBoxBarcode();
		this.systemBoxBarcode = stockReportArchivesBoxNotMatch.getSystemBoxBarcode();
		this.keyInCol = stockReportArchivesBoxNotMatch.getKeyInCol();
		this.tag = stockReportArchivesBoxNotMatch.getTag();
	}



    
}



