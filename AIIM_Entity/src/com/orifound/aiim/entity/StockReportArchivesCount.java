package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 盘点报告-档案数量
 */
public class StockReportArchivesCount
{
    /**
     * 构造函数
     */
    public StockReportArchivesCount()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param stocktakingID 盘点工作任务编号
	* @param archivesTypeID 档案分类编号
	* @param paperVolumeCount 盘点的实物档案总卷数
	* @param paperPieceCount 盘点的实物档案总件数
	* @param systemVolumeCount 系统在架的档案总卷数
	* @param systemPieceCount 系统在架的档案总件数
	*/
	public StockReportArchivesCount(int stocktakingID,int archivesTypeID,int paperVolumeCount,int paperPieceCount,int systemVolumeCount,int systemPieceCount)
	{
		// Table Name: StocktakingReport_ArchivesCount
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesTypeID,PaperVolumeCount,PaperPieceCount,SystemVolumeCount,SystemPieceCount
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesTypeID,:PaperVolumeCount,:PaperPieceCount,:SystemVolumeCount,:SystemPieceCount
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesTypeID=:ArchivesTypeID,PaperVolumeCount=:PaperVolumeCount,PaperPieceCount=:PaperPieceCount,SystemVolumeCount=:SystemVolumeCount,SystemPieceCount=:SystemPieceCount

		this.stocktakingID = stocktakingID;
		this.archivesTypeID = archivesTypeID;
		this.paperVolumeCount = paperVolumeCount;
		this.paperPieceCount = paperPieceCount;
		this.systemVolumeCount = systemVolumeCount;
		this.systemPieceCount = systemPieceCount;
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
	 * 盘点的实物档案总卷数
	 */
	private int paperVolumeCount=0;

	/**
	 * 获取属性值：盘点的实物档案总卷数
	 * @return 盘点的实物档案总卷数
	 */
	public int getPaperVolumeCount()
	{
		return paperVolumeCount;
	}

	/**
	 * 设置属性值：盘点的实物档案总卷数
	 * @param paperVolumeCount 盘点的实物档案总卷数
	 */
	public void setPaperVolumeCount(int paperVolumeCount)
	{
		this.paperVolumeCount = paperVolumeCount;
	}

	/**
	 * 盘点的实物档案总件数
	 */
	private int paperPieceCount=0;

	/**
	 * 获取属性值：盘点的实物档案总件数
	 * @return 盘点的实物档案总件数
	 */
	public int getPaperPieceCount()
	{
		return paperPieceCount;
	}

	/**
	 * 设置属性值：盘点的实物档案总件数
	 * @param paperPieceCount 盘点的实物档案总件数
	 */
	public void setPaperPieceCount(int paperPieceCount)
	{
		this.paperPieceCount = paperPieceCount;
	}

	/**
	 * 系统在架的档案总卷数
	 */
	private int systemVolumeCount=0;

	/**
	 * 获取属性值：系统在架的档案总卷数
	 * @return 系统在架的档案总卷数
	 */
	public int getSystemVolumeCount()
	{
		return systemVolumeCount;
	}

	/**
	 * 设置属性值：系统在架的档案总卷数
	 * @param systemVolumeCount 系统在架的档案总卷数
	 */
	public void setSystemVolumeCount(int systemVolumeCount)
	{
		this.systemVolumeCount = systemVolumeCount;
	}

	/**
	 * 系统在架的档案总件数
	 */
	private int systemPieceCount=0;

	/**
	 * 获取属性值：系统在架的档案总件数
	 * @return 系统在架的档案总件数
	 */
	public int getSystemPieceCount()
	{
		return systemPieceCount;
	}

	/**
	 * 设置属性值：系统在架的档案总件数
	 * @param systemPieceCount 系统在架的档案总件数
	 */
	public void setSystemPieceCount(int systemPieceCount)
	{
		this.systemPieceCount = systemPieceCount;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StockReportArchivesCount clone()
	{
		StockReportArchivesCount item = new StockReportArchivesCount(stocktakingID,archivesTypeID,paperVolumeCount,paperPieceCount,systemVolumeCount,systemPieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param stockReportArchivesCount 指定的对象源
	*/
	public void cloneFrom(StockReportArchivesCount stockReportArchivesCount)
	{
		this.stocktakingID = stockReportArchivesCount.getStocktakingID();
		this.archivesTypeID = stockReportArchivesCount.getArchivesTypeID();
		this.paperVolumeCount = stockReportArchivesCount.getPaperVolumeCount();
		this.paperPieceCount = stockReportArchivesCount.getPaperPieceCount();
		this.systemVolumeCount = stockReportArchivesCount.getSystemVolumeCount();
		this.systemPieceCount = stockReportArchivesCount.getSystemPieceCount();
		this.keyInCol = stockReportArchivesCount.getKeyInCol();
		this.tag = stockReportArchivesCount.getTag();
	}


    
}



