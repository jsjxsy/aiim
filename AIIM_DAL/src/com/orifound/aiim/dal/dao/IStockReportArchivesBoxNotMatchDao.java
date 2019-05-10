package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点- 档案装盒不匹配信息表的DAO接口定义
 *
 */
public interface IStockReportArchivesBoxNotMatchDao {

	/**
	 * Dao接口定义：添加档案装盒不匹配信息
	 * @param stockReportArchivesBoxNotMatch 要添加的档案装盒不匹配信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo);


	/**
	 * Dao接口定义：更新指定的档案装盒不匹配信息
	 * @param stockReportArchivesBoxNotMatch 要更新的档案装盒不匹配信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案装盒不匹配信息
	 * int stocktakingID 库房盘点编号
	 * @param stockReportArchivesBoxNotMatchs 返回查找成功的档案装盒不匹配信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(int stocktakingID,List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs, ErrInfo pErrInfo);
	
	/**
	 * 执行档案装盒不匹配信息情况盘点<br>插入所有装盒不匹配信息到对应的表中
	 * @param stocktakingID 盘点工作编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);


}

