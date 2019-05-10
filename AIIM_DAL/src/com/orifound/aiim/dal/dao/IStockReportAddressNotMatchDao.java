package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房盘点-上架位置不匹配信息表的DAO接口定义
 *
 */
public interface IStockReportAddressNotMatchDao {

	/**
	 * Dao接口定义：添加上架位置不匹配信息
	 * @param stockReportAddressNotMatch 要添加的上架位置不匹配信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：更新指定的上架位置不匹配信息
	 * @param stockReportAddressNotMatch 要更新的上架位置不匹配信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的上架位置不匹配信息
	 * @param stockReportAddressNotMatchs 返回查找成功的上架位置不匹配信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(int stocktakingID,List<StockReportAddressNotMatch> stockReportAddressNotMatchs, ErrInfo pErrInfo);
	
	/**
	 * 执行上架位置不匹配信息情况盘点<br>插入所有上架位置不匹配信息到对应的表中
	 * @param stocktakingID 盘点工作编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);

}
