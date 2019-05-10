/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案划分控制鉴定明细情况表的实体类管理服务的接口定义
 *
 */
public interface IAppraisalUseScopesDetailManageService {

	/**
	 * 添加或者更新 档案划分控制鉴定明细情况信息
	 * @param appraisalUseScopesDetail 新添加的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveOrUpdateAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	/**
	 * 删除指定的档案划分控制鉴定明细情况表的实体类
	 * @param appraisalUseScopesDetail 要删除的档案划分控制鉴定明细情况表的实体类，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案划分控制鉴定明细情况表的实体类
	 * @param appraisalUseScopesDetail 修改后的档案划分控制鉴定明细情况表的实体类信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案划分控制鉴定明细情况表的实体类信息
	 * @param appraisalUseScopesDetails 返回查找成功的档案划分控制鉴定明细情况表的实体类集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAppraisalUseScopesDetails(List<AppraisalUseScopesDetail> appraisalUseScopesDetails,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案划分控制鉴定明细情况表的实体类信息
	 * @param pID 指定的唯一标识
	 * @param appraisalUseScopesDetail 返回查找成功的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAppraisalUseScopesDetailByID(int pID, AppraisalUseScopesDetail appraisalUseScopesDetail,
			ErrInfo pErrInfo);

	/**
	 * 根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息
	 * @param archivesTypeID 档案类型id
	 * @param NBXH		档案内部序号
	 * @param appraisalUseScopesDetail	返回查找成功的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAppraisalUseScopesDetailByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);

	/**
	 * 鉴定管理->划控鉴定登记信息  分页查询
	 * @param archivesTypeIds 指定档案类型下的所有档案类型id
	 * @param params 参数列表 包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
	 * 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd
	 * @param dataPageInfo 数据页信息对象类 
	 * @param appraisalPublicDetails 返回开放鉴定登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalUseScopesDetail> appraisalUseScopesDetails, ErrInfo pErrInfo);

	/**
	 * 鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称
	 * @param pId 		唯一标识符
	 * @param roleNames 返回角色名称集合
	 * @param pErrInfo  返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRoleNamesById(int pId, List<String> roleNames, ErrInfo pErrInfo);
}
