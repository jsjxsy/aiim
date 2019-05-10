package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点-实物档案不在架信息表的DAO接口定义
 *
 */
public interface IStockReportPaperNotExistDao {

	/**
	 * Dao接口定义：添加实物档案不在架信息
	 * @param stockReportPaperNotExist 要添加的实物档案不在架信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的实物档案不在架信息
	 * @param stockReportPaperNotExist 要删除的实物档案不在架信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的实物档案不在架信息
	 * @param stockReportPaperNotExist 要更新的实物档案不在架信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的实物档案不在架信息
	 * @param stockReportPaperNotExists 返回查找成功的实物档案不在架信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(int stocktakingID,List<StockReportPaperNotExist> stockReportPaperNotExists, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找实物档案不在架信息
	 * @param pID 指定的唯一标识
	 * @param stockReportPaperNotExist 返回查找成功的实物档案不在架信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);
	
	/**
	 * 执行实物档案不在架档案情况盘点<br>插入所有实物档案不在架的档案情况表
	 * @param stocktakingID 盘点工作编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);

}

