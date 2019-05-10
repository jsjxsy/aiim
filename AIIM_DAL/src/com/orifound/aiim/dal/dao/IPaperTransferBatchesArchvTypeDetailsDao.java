package com.orifound.aiim.dal.dao;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;

/**
 * 纸质档案移交批次档案分类明细情况表的DAO接口定义
 *
 */
public interface IPaperTransferBatchesArchvTypeDetailsDao {

	/**
	 * Dao接口定义：更新指定的批次分类详细信息的档案总数量
	 * @param pPaperTransferBatchesArchvTypeDetail 要更新的PaperTransferBatchesArchvTypeDetail
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新指定的批次分类详细信息的档案总数量
	 * @param pPaperTransferBatchesArchvTypeDetail 要更新的PaperTransferBatchesArchvTypeDetail
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateWhithSubBat(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据档案分类编号和批次号查找
	 * @param paperTransferBatchesArchvTypeDetail 返回的纸质档案移交批次档案分类明细情况表 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesTypeAndBatNO(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：插入新的纸质档案移交批次档案分类明细情况
	 * @param paperTransferBatchesArchvTypeDetail 返回的纸质档案移交批次档案分类明细情况表 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据批次号查找纸质档案移交批次档案分类明细情况
	 * @param paperTransferBatch 批次信息 批次号必须有值
	 * @param paperTransferBatchesArchvTypeDetail 返回的纸质档案移交批次档案分类明细情况表 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCurrentPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);

	/**
	 * 更新指定批次指定档案类别的档号生成状态
	 * @param paperTransferBatNo 批次信息 批次号
	 * @param archivesTypeID 档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, ErrInfo pErrInfo);

	/**
	 * 查找批次分类信息中是否还有未生成档号的分类
	 * @param paperTransferBatNo 批次信息 批次号
	 * @param archivesTypeID 档案分类
	 * @param paperTransferBatchesArchvTypeDetails 返回的批次分类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);
}

