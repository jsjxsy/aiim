package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 档案利用申请单明细业务逻辑接口
 * @author Administrator
 *
 */
public interface IArchivesUseRequestDetailManageService {
	/**
	 * 添加档案利用申请单明细
	 * @param archivesUseRequestDetail 要添加的档案利用申请单明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addArchivesUseRequestDetail(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案利用申请单明细
	 * @param archivesUseRequestDetail 要删除的档案利用申请单明细
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesUseRequestDetail(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);


	/**
	 * 查找所有未审批的档案利用申请单明细
	 * @param archivesUseRequestDetails 返回查找成功的档案利用申请单明细集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllNotExamineArchivesUseRequestDetail(DataPageInfo dataPageInfo, List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);
	
	/**
	 * 查找第一个未审批的档案利用申请单明细
	 * @param recordsNum 记录总数
	 * @param archivesUseRequestDetail 档案利用申请单明细
	 * @param pErrInfo 返回错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findOneNotExamineArchivesUseRequestDetail(IntegerEx recordsNum, ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	
	/**
	 * 根据唯一标识查找档案利用申请单明细
	 * @param archivesUseRequestDetail 返回查找成功的档案利用申请单明细 ,ID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRequestDetailByID( ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	/**
	 * 根据唯一标识,审批用户提交的在线档案利用申请
	 * @param archivesUseRequestDetail 返回查找成功的档案利用申请单明细 <br>
	 * ID,checkResult,backReason,checkTime,checkUserID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkArchivesUseRequestDetail( ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	/**
	 * 根据申请单编号查找所有档案利用申请单明细
	 * @param archivesUseRequestDetail 返回查找成功的档案利用申请单明细 <br>
	 * ID,checkResult,backReason,checkTime,checkUserID必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRequestDetailsByRequestID(String requestID,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);
	
	
}
