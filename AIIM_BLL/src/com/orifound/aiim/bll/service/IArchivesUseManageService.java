/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesUseExpiredUserInfo;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.CurrentRequestID;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案利用管理服务的接口定义
 *
 */
public interface IArchivesUseManageService {

	/**
	 * 生成新的档案利用申请单编号<br>
	 * 档案利用申请单编号格式为：8位日期（当前日期）+5位流水号，例如 2010032400001 
	 * @param currentRequestID 返回新生成的档案利用申请单编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean generateArchivesUseRequestBatchNo(CurrentRequestID currentRequestID,ErrInfo pErrInfo);
	
	/**
	 * 提交档案利用申请单
	 * @param userInfo 利用人信息
	 * @param archivesUseRequest 档案利用申请单信息
	 * @param archivesUseRequestDetails 档案利用申请明细清单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean submitArchivesUseRequest(UserInfo userInfo,ArchivesUseRequest archivesUseRequest,List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * 为指定的档案利用清单生成调卷单
	 * @param archivesUseRequestDetails 指定的档案利用清单，查找成功后返回调卷单信息（通过相关属性访问）
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean generateFetchPaperList(List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * 为指定的档案利用申请单生成调卷单
	 * @param archivesUseRequestDetails 指定的档案利用申请单
	 * @param archivesUseRequestDetails 返回查找成功后的调卷单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean generateFetchPaperList(ArchivesUseRequest archivesUseRequest,List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * 查询档案利用申请单
	 * @param archivesUseRequestQueryCondition 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesUseRequests 返回查找成功的档案利用申请单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRequests(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition,DataPageInfo dataPageInfo,List<ArchivesUseRequest> archivesUseRequests,ErrInfo pErrInfo);
	
	/**
	 * 档案利用登记<br>
	 * 适用于借档和查档利用登记
	 * @param archivesUseRegister 要登记利用信息，包括档案利用人及实物档案利用清单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addArchivesUseRegister(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * 撤销档案利用登记信息<br>
	 * 适用于借档和查档利用登记的撤销
	 * @param archivesUseRegister 指定要撤销的档案利用登记信息，其借档登记编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean unregisterArchivesUse(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * 撤销多个档案利用登记信息<br>
	 * 适用于借档和查档利用登记的撤销
	 * @param archivesUseRegisters 指定要撤销的档案利用登记信息集合，其成员的借档登记编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean unregisterArchivesUse(List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo);
	
	/**
	 * 查询档案利用登记信息<br>
	 * 适用于借档和查档利用登记信息的查询
	 * @param archivesUseRegisterQueryCondition 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesUseRegisters 返回查找成功的档案利用登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRegisters(ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition,DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo);
	
	/**
	 * 根据档案利用登记编号查询档案利用登记信息<br>
	 * 适用于借档和查档利用登记信息的查询
	 * @param archivesUseRegister 返回查找成功的档案利用登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRegister(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * 查找指定审批状态的档案利用申请明细信息
	 * @param enumCheckResult 指定审批状态
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesUseRequestDetails 返回查找成功的档案利用申请明细信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseRequestDetails(EnumCheckResult enumCheckResult,DataPageInfo dataPageInfo,List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * 对指定的档案利用申请进行审批
	 * @param archivesUseRequestDetail 档案利用申请审批信息，包括审批的档案利用申请信息以及审批结果
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkArchivesUseRequestDetail(ArchivesUseRequestDetail archivesUseRequestDetail,ErrInfo pErrInfo);
	
	/**
	 * 根据档案利用明细编号查询档案利用信息
	 * @param ArchivesUseOutInfoID 档案利用明细编号
	 * @param ArchivesUseOutInfo 返回查询成功的档案利用信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseOutInfoByID(int ArchivesUseOutInfoID,ArchivesUseOutInfo archivesUseOutInfo,ErrInfo pErrInfo);
	
	/**
	 * 查询满足指定条件的档案利用信息
	 * @param archivesUseInfoQueryCondition 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesUseOutInfos 返回查询成功的档案利用信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseOutInfos(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition,DataPageInfo dataPageInfo,List<ArchivesUseOutInfo> archivesUseOutInfos,ErrInfo pErrInfo);
	
	/**
	 * 续借指定条码的实物档案
	 * @param barcode 档案条码，可以是案卷条码或卷内文件的条码
	 * @param archivesUseOutInfo 返回续借成功后的档案利用信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean renewArchivesByBarcode(int daysNum,ArchivesUseOutInfo archivesUseOutInfo,ErrInfo pErrInfo);
	
	/**
	 * 归还指定条码的实物档案
	 * @param archivesUseOutInfo 返回归还成功后的档案利用信息 ；barcode属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo,ErrInfo pErrInfo);
	
	/**
	 * 到期预警<br>
	 * 把即将到达归还期限的档案利用人登记信息查询出来
	 * @param dayNum 提前预警的天数
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesUseOutInfos 返回查找成功后的档案利用信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findExpiringArchivesUseRegister(int dayNum,DataPageInfo dataPageInfo,List<ArchivesUseRegister> ArchivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * 查找需要过期催还的档案利用人登记信息
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesUseRegisters 返回查找成功的需要过期催还的档案利用人信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseExpiredUseRegister(DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo);
	
	/**
	 * 根据档案利用人编号查找档案利用人信息<br>
	 * 适用于查看过期催还详细信息的业务功能，其中要显示利用人详细信息
	 * @param archivesUsePersonID 档案利用人编号
	 * @param ArchivesUsePersonInfo 返回查找成功的档案利用人详细信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUsePersonInfoByPersonID(int archivesUsePersonID,ArchivesUsePersonInfo archivesUsePersonInfo,ErrInfo pErrInfo);
	
	/**
	 * 根据档案利用人编号查找过期未还的档案清单<br>
	 * 适用于查看过期催还详细信息的业务功能
	 * @param archivesUseExpiredUserInfo 指定档案利用过期的用户信息，其真实姓名和部门名称属性必须赋值
	 * @param archivesUseOutInfos 返回查找成功的过期未还档案清单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesUseOutInfosByPersonID(int archivesUsePersonID,List<ArchivesUseOutInfo> archivesUseOutInfos,ErrInfo pErrInfo);
	
	
}
