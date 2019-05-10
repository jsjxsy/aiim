package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 盘点报告-系统中不在架档案信息表的DAO接口定义
 *
 */
public interface IStockReportSystemNotExistDao {

	/**
	 * Dao接口定义：添加系统中不在架档案信息
	 * @param stockReportSystemNotExist 要添加的系统中不在架档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的系统中不在架档案信息
	 * @param stockReportSystemNotExist 要删除的系统中不在架档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的系统中不在架档案信息
	 * @param stockReportSystemNotExist 要更新的系统中不在架档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的系统中不在架档案信息
	 * stocktakingID 库房盘点编号
	 * @param stockReportSystemNotExists 返回查找成功的系统中不在架档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(int stocktakingID,List<StockReportSystemNotExist> stockReportSystemNotExists, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找系统中不在架档案信息
	 * @param pID 指定的唯一标识
	 * @param stockReportSystemNotExist 返回查找成功的系统中不在架档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * 执行系统中不在架档案情况盘点操作<br>插入所有系统中不在架的档案情况表
	 * @param stocktakingID 盘点工作编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);
}
