/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 存毁鉴定明细情况表的DAO接口定义
 *
 */
public interface IAppraisalKeepDestroyDetailDao {
	
	/**
	 * 添加一个新的存毁鉴定明细情况
	 * @param appraisalKeepDestroyDetail 新添加的存毁鉴定明细情况信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：批量添加存毁鉴定明细情况
	 * @param userInfo 经办人信息
	 * @param archivesType 档案类型
	 * @param batchArchives 批量档案信息 键：内部序号、值Map集合：是否销毁AppraisalDeletedFlag、新保存期限ID(NewRetentionPeriodID)
	 * @param opinion 公共参数 传递参数：	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的存毁鉴定明细情况
	 * @param appraisalKeepDestoryDetail 要删除的存毁鉴定明细情况
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的存毁鉴定明细情况
	 * @param appraisalKeepDestoryDetail 要更新的存毁鉴定明细情况
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的存毁鉴定明细情况
	 * @param appraisalKeepDestoryDetails 返回查找成功的存毁鉴定明细情况集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<AppraisalKeepDestroyDetail> appraisalKeepDestoryDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找存毁鉴定明细情况
	 * @param pID 指定的唯一标识
	 * @param appraisalKeepDestoryDetail 返回查找成功的存毁鉴定明细情况
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：分页查询存毁鉴定登记信息
	 * @param archivesTypeIds 指定档案类型下的所有档案类型id
	 * @param params 参数列表 包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
	 * 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd
	 * @param dataPageInfo 数据页信息对象类 
	 * @param appraisalKeepDestroyDetails 返回存毁鉴定登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails, ErrInfo pErrInfo);
}
