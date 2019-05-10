package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;

/**
 * 纸质档案移交明细清单表的DAO接口定义
 *
 */
public interface IPaperTransferBatchesDetailsDao {

	/**
	 * Dao接口定义：更新指定的PaperTransferBatchesDetail
	 * @param pPaperTransferBatchesDetail 要更新的PaperTransferBatchesDetail
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(PaperTransferBatchesDetail pPaperTransferBatchesDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：将档案添加到移交清单中
	 * @param archivesType 档案分类信息
	 * @param archivesInfos 要加入的档案信息
	 * @param paperTransferBatch 当前的批次
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addPaperTransferBatchesDetails(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);
	
	/**
	 * 统计当前批次中档案总数量
	 * @param paperTransferBatch 要统计的批次编号
	 * @param paperTransferBatchesDetails 返回各分类档案总数量
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean staArchivesInfosSumByTransferBat (IntegerEx archivesInfosSum, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);

	/**
	 * 查找指定大批次中指定分类的档案信息
	 * @param paperTransferBatNo 批次号
	 * @param archivesTypeID 档案分类
	 * @param paperTransferBatchesDetails 返回的批次详细档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBatNoAndArchivesType(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo);

	/**
	 * 统计当前批次中档案总数量
	 * @param nBXH 要删除的批次信息明细表对应的档案内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(String paperTransferBatNo, int archivesTypeID,int nBXH, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：档案形成部门用户确认后设置档案工作流状态   此方法设置批次中所有的档案信息工作流并且写入工作流记录表
	 * @param archivesType 档案类型
	 * @param userID 操作的用户
	 * @param paperTransferBatNo 档案所在的批次
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean setFlagForWorkFlow(ArchivesType archivesType, PaperTransferBatch paperTransferBatch, int userID, EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：接收审核设置档案的审核结果
	 * @param archivesTypeID 档案类型 编号
	 * @param batNo 档案所在的批次的批次号
	 * @param enumCheckResult 档案接收审核结果
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateReceiveCheckResult(int archivesTypeID, String batNo,int NBXH, EnumCheckResult enumCheckResult,ErrInfo pErrInfo);
	
	/**
	 * 根据批次号查找当指定批次指定分类下档案的所有全宗号
	 * @param batNo 批次号
	 * @param archivesType 档案分类信息
	 * @param ArchivesFondIDs 返回的所有全宗的全宗号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesFondsByBatNoAndArchivesType(String batNo, ArchivesType archivesType,List<String> ArchivesFondIDs,ErrInfo pErrInfo);

	/**
	 * 根据批次号查找指定批次指定分类和全宗的档案内部序号集合
	 * @param paperTransferBatNo 批次号
	 * @param archivesType 档案分类信息
	 * @param ArchivesFondID 全宗的全宗号
	 * @param NBXHS 返回的内部序号集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findNBXHSByArchivesFonds(String paperTransferBatNo, ArchivesType archivesType, String archivesFondID,List<Integer> NBXHS, ErrInfo pErrInfo);

	/**
	 * 给指定批次指定分类和全宗的档案信息生成档号
	 * @param paperTransferBatNo 批次号
	 * @param archivesType 档案分类信息
	 * @param ArchivesFondID 全宗的全宗号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesID(String paperTransferBatNo, ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * 将形成部门的移交批次加入到业务指导室的移交批次中
	 * @param paperTransferBatchNos 要加入移交批次的子批次号
	 * @param paperTransferBatch 新的批次的信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addToPaperTransferBatchaddToPaperTransferBatch(String[] paperTransferBatchNos, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);

	/**
	 * 业务指导室统计批次下各档案分类的档案数量
	 * @param paperTransferBatch
	 * @param paperTransferBatchesArchvTypeDetails
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean staArchivesInfosSumByTransferBatArchvType(PaperTransferBatch paperTransferBatch, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,ErrInfo pErrInfo);
	
	/**
	 * 业务指导室加入移交清单后<br/>  
	 * 更新新的批次中所有的档案信息的工作流状态
	 * @param paperTransferBatch 档案所属的批次信息
	 * @param archivesType 档案所属的分类信息
	 * @param enumWorkFlowStatus 要更新的状态
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean setArchivesFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：业务指导室用户确认后设置档案工作流状态   此方法设置批次中所有的档案信息工作流并且写入工作流记录表
	 * @param archivesType 档案类型
	 * @param userID 操作的用户
	 * @param paperTransferBatNo 档案所在的批次
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean setFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * 查询没有生成档号的档案的档号前缀
	 * @param paperTransferBatNo
	 * @param archivesType
	 * @param archivesIDPrefixs
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesIDPrefixWhitOutArchivesID(String paperTransferBatNo, ArchivesType archivesType, Map<Integer, String> archivesIDPrefixs, ErrInfo pErrInfo);

	/**
	 * 查找制定批次中未关联条码的档案的第一卷
	 * @param batNo
	 * @param archivesTypeID
	 * @param paperTransferBatchesDetail
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID, final ArchivesInfo archivesInfo, ErrInfo pErrInfo);
}

