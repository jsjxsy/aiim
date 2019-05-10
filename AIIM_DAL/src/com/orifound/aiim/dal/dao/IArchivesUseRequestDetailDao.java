package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 档案利用申请单明细表的DAO接口定义
 *
 */
public interface IArchivesUseRequestDetailDao {

	/**
	 * Dao接口定义：添加档案利用申请单明细
	 * @param archivesUseRequestDetail 要添加的档案利用申请单明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案利用申请单明细
	 * @param archivesUseRequestDetail 要删除的档案利用申请单明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新审批结果到指定的档案利用申请单明细
	 * @param archivesUseRequestDetail 要更新的档案利用申请单明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateCheckResult(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有未审批的档案利用申请单明细
	 * @param archivesUseRequestDetails 返回查找成功的档案利用申请单明细集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllNotExamine(DataPageInfo dataPageInfo,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);

	/**
	 *  Dao接口定义：查找第一条未审批的档案利用申请单明细
	 * @param recordsNum 记录总数
	 * @param archivesUseRequestDetail 档案利用申请单明细
	 * @param pErrInfo 返回错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOneNotExamine(IntegerEx recordsNum, ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案利用申请单明细
	 * @param archivesUseRequestDetail 返回查找成功的档案利用申请单明细 ,ID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID( ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据申请单编号查找所有档案利用申请单明细
	 * @param requestID
	 * @param archivesUseRequestDetails
	 * @param pErrInfo
	 * @return
	 */
	boolean findByRequestID(String requestID ,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);

}
