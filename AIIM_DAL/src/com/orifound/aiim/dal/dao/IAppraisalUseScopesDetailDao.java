/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 划控鉴定的档案明细信息DAO接口
 *
 */
public interface IAppraisalUseScopesDetailDao
{

	/**
	 * 查找指定的档案在指定用户无权访问的划控档案列表中出现的次数
	 * @param pUserID 访问用户的编号
	 * @param pArchivesTypeID 档案分类编号
	 * @param pNBXH 档案的内部序号
	 * @param pACLCount 返回查找成功的总数量
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCountArchivesInfoNotInUseScopesACL(int pUserID,int pArchivesTypeID,int pNBXH,IntegerEx pACLCount,ErrInfo pErrInfo);

	/**
	 * 查找指定的档案在指定用户的划控档案列表中出现的次数
	 * @param pUserID 访问用户的编号
	 * @param pArchivesTypeID 档案分类编号
	 * @param pNBXH 档案的内部序号
	 * @param pACLCount 返回查找成功的总数量
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCountArchivesInfoInUseScopesACL(int pUserID,int pArchivesTypeID,int pNBXH,IntegerEx pACLCount,ErrInfo pErrInfo);
	
	/**
	 * 根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息
	 * @param archivesTypeID		档案类型id
	 * @param NBXH		内部序号
	 * @param appraisalUseScopesDetail	返回查找成功的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	/**
	 * 添加 档案划分控制鉴定明细情况信息
	 * @param appraisalUseScopesDetail 新添加的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	/**
	 * 更新 档案划分控制鉴定明细情况信息
	 * @param appraisalUseScopesDetail 新添加的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	
	/**
	 * 新增 档案划控鉴定角色
	 * @param appraisalUseScopesDetailId 档案划分控制鉴定明细情况表的实体Id
	 * @param roleIds 划控角色id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveRoles(int appraisalUseScopesDetailId, List<Integer> roleIds, ErrInfo pErrInfo);
	
	/**
	 * 删除 档案划控鉴定角色
	 * @param appraisalUseScopesDetailId 档案划分控制鉴定明细情况表的实体Id
	 * @param roleIds 划控角色id集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteRoles(int appraisalUseScopesDetailId, List<Integer> roleIds, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：分页查询公开/开放鉴定登记信息
	 * @param archivesTypeIds 指定档案类型下的所有档案类型id
	 * @param params 参数列表 包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
	 * 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd
	 * @param dataPageInfo 数据页信息对象类 
	 * @param appraisalPublicDetails 返回开放鉴定登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalUseScopesDetail> appraisalUseScopesDetails, ErrInfo pErrInfo);

	/**
	 * 鉴定管理：划控鉴定信息 根据id获取授权的所有角色名称
	 * @param pId 		唯一标识符
	 * @param roleNames 返回角色名称集合
	 * @param pErrInfo  返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findRoleNamesById(int pId, List<String> roleNames, ErrInfo pErrInfo);
	
	/**
	 * 删除档案划分控制鉴定明细情况信息
	 * @param appraisalUseScopesDetail 新添加的档案划分控制鉴定明细情况表的实体类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
}
