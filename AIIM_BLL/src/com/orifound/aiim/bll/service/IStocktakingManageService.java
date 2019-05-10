package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;

/**
 * 库房盘点业务逻辑接口
 * @author Administrator
 *
 */
public interface IStocktakingManageService {
	
	/**
	 * 添加库房盘点的设备位置与档案盒详细
	 * @param stocktakingAddressBoxDetail 要添加的库房盘点的设备位置与档案盒详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addStocktakingAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * 添加库房盘点的档案盒与档案卷详细
	 * @param stocktakingArchivesDetail 要添加的库房盘点的档案盒与档案卷详细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addStocktakingArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	/**
	 * 执行库房盘点工作
	 * @param stocktakingID
	 * @param pErrInfo
	 * @return
	 */
	boolean executeStocktaking(int stocktakingID,ErrInfo pErrInfo);
	
	/**
	 * 查询所有库房档案数量情况
	 * @param stocktakingID 盘点工作编号
	 * @param stockReportArchivesCounts 返回处理成功的数量信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean  findStockReportArchivesCount(int stocktakingID, List<StockReportArchivesCount> stockReportArchivesCounts ,ErrInfo pErrInfo);


	/**
	 * 查询所有系统中不在架档案情况
	 * @param stocktakingID 盘点工作编号
	 * @param stockReportSystemNotExists 用于返回结果集
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStockReportSystemNotExist(int stocktakingID, List<StockReportSystemNotExist> stockReportSystemNotExists ,ErrInfo pErrInfo);
	

	/**
	 * 查询所有实物档案不在架档案情况
	 * @param stocktakingID 盘点工作编号
	 * @param stockReportPaperNotExists 用于返回结果集
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean  findStockReportPaperNotExist(int stocktakingID, List<StockReportPaperNotExist> stockReportPaperNotExists ,ErrInfo pErrInfo);
	
	
	/**
	 * 查询所有位置不匹配情况
	 * @param stocktakingID 盘点工作编号
	 * @param stockReportAddressNotMatchs 用于返回结果集
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStockReportAddressNotMatch(int stocktakingID, List<StockReportAddressNotMatch> stockReportAddressNotMatchs ,ErrInfo pErrInfo) ;
	
	/**
	 * 查询所有装盒不匹配档案情况
	 * @param stocktakingID 盘点工作编号
	 * @param stockReportArchivesBoxNotMatchs 用于返回结果集
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStockReportArchivesBoxNotMatch(int stocktakingID, List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs ,ErrInfo pErrInfo);
	
}
