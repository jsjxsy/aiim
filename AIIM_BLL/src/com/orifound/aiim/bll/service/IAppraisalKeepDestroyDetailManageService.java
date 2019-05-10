/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 存毁鉴定明细情况管理服务的接口定义
 *
 */
public interface IAppraisalKeepDestroyDetailManageService {

	/**
	 * 添加一个新的存毁鉴定明细情况
	 * @param appraisalKeepDestroyDetail 新添加的存毁鉴定明细情况信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveAppraisalKeepDestroyDetail(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * 批量添加存毁鉴定明细情况
	 * @param userInfo 经办人信息
	 * @param archivesType 档案类型
	 * @param batchArchives 批量档案信息 键：内部序号、值Map集合：是否销毁AppraisalDeletedFlag、新保存期限ID(NewRetentionPeriodID)
	 * @param opinion 公共参数 传递参数：	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo);
	
	/**
	 * 删除指定的存毁鉴定明细情况
	 * @param appraisalKeepDestroyDetail 要删除的存毁鉴定明细情况，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteAppraisalKeepDestroyDetail(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * 修改指定的存毁鉴定明细情况
	 * @param appraisalKeepDestroyDetail 修改后的存毁鉴定明细情况信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateAppraisalKeepDestroyDetail(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * 查找所有的存毁鉴定明细情况信息
	 * @param appraisalKeepDestroyDetails 返回查找成功的存毁鉴定明细情况集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAppraisalKeepDestroyDetails(List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找存毁鉴定明细情况信息
	 * @param pID 指定的唯一标识
	 * @param appraisalKeepDestroyDetail 返回查找成功的存毁鉴定明细情况信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAppraisalKeepDestroyDetailByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo);

	/**
	 * 分页查询存毁鉴定登记信息
	 * @param params 参数列表 包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
	 * 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd
	 * @param dataPageInfo 数据页信息对象类 
	 * @param appraisalKeepDestroyDetails 返回存毁鉴定登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails, ErrInfo pErrInfo);

}
