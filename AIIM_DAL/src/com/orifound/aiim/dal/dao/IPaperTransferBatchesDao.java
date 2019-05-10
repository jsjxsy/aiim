package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.UserInfo;

/**
 * 移交批次信息表的DAO接口定义
 *
 */
public interface IPaperTransferBatchesDao {

	/**
	 * Dao接口定义：添加PaperTansferBatch
	 * @param pPaperTansferBatch 要添加的PaperTansferBatch 
	 * 其属性batNo（移交批次号）、 batNoCreateUserID（创建批次号的用户）
	 * transferType（移交类型）、insideFlag（馆内移交标志）必须有值
	 * 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的PaperTansferBatch
	 * @param pPaperTansferBatch 要删除的PaperTansferBatch
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的PaperTansferBatch
	 * @param pPaperTansferBatch 要更新的PaperTansferBatch
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的PaperTansferBatch
	 * @param pPaperTansferBatchs 返回查找成功的PaperTansferBatch集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(int [] deptIDs,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> pPaperTransferBatches, boolean insideFlag,ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找PaperTansferBatch
	 * @param batNO 指定的唯一标识 批次号
	 * @param pPaperTansferBatch 返回查找成功的PaperTansferBatch
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByBatNO(String batNO, PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据用户和确认移交标志查找批次信息
	 * @param paperTransferBatch 返回查找成功的批次信息  创建批次的用户编号 和确认移交标志必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchByConfirmFlag(PaperTransferBatch paperTransferBatch,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：用户确认移交
	 * @param paperTransferBatNo 要确认移交的批次
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateConfirmFlag(String paperTransferBatNo,UserInfo userInfo,int srchivesSum, ErrInfo pErrInfo);

	
	/**
	 * 绩效管理->入库情况查询 档案管理室接收情况
	 * @param counts  counts[0]未接收数、counts[1]总接收数
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return boolean 处理成功返回true，否则返回false
	 */
	boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新指定的PaperTansferBatch 适用于接收登记时更新批次接收人信息
	 * @param pPaperTansferBatch 要更新的PaperTansferBatch
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateForReceive(PaperTransferBatch pPaperTransferBatch,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的PaperTansferBatch的审核完成标志
	 * @param paperTransferBatNo 要更新的批次的批次号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateBatState(String paperTransferBatNo, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据条件查找制定部门指定状态的移交批次信息
	 * @param paperTransferBatNo 要更新的批次的批次号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByCondition(int[] deptIDs, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches,DataPageInfo dataPageInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据条件查找指定用户指定状态的接收批次信息
	 * @param paperTransferBatNo 要更新的批次的批次号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByCondition(EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,int[] userIDs, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches,DataPageInfo dataPageInfo,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据用户查找该用户移交的批次信息
	 * @param userID 移交用户ID
	 * @param enumPaperTransferBatchesDealStatus 指定批次状态
	 * @param paperTransferBatches 返回的批次信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchsByTransferUser(int userID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches, boolean insideFlag,ErrInfo pErrInfo);

	/**
	 * 更新批次中档案信息总数量
	 * @param batNo 要更新的批次号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateTransferTotal(String batNo, ErrInfo pErrInfo);
}
