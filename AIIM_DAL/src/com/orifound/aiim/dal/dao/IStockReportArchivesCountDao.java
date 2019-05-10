package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点- 库房盘点档案数量信息表的DAO接口定义
 *
 */
public interface IStockReportArchivesCountDao {

	/**
	 * Dao接口定义：添加库房盘点档案数量信息
	 * @param stockReportArchivesCounty 要添加的库房盘点档案数量信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StockReportArchivesCount stockReportArchivesCounty, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的库房盘点档案数量信息
	 * @param stockReportArchivesCountys 返回查找成功的库房盘点档案数量信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(int stocktakingID,List<StockReportArchivesCount> stockReportArchivesCounts, ErrInfo pErrInfo);
	
	/**
	 * 执行库房档案数量信息盘点操作<br>插入所有系统中不在架的档案情况表
	 * @param stocktakingID 盘点工作编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);

	
}

