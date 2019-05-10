/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * AppraisalPublicDetails(档案开放/公开鉴定表)表的DAO接口定义
 *
 */
public interface IAppraisalPublicDetailDao {

	/**
	 * Dao接口定义：添加档案开放/公开鉴定信息
	 * @param appraisalPublicDetail 要添加的档案开放/公开鉴定信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定的档案开放/公开鉴定信息
	 * @param appraisalPublicDetail 要删除的档案开放/公开鉴定信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案开放/公开鉴定信息
	 * @param appraisalPublicDetail 要更新的档案开放/公开鉴定信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的档案开放/公开鉴定信息
	 * @param appraisalPublicDetails 返回查找成功的档案开放/公开鉴定信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找档案开放/公开鉴定信息
	 * @param pID 指定的唯一标识
	 * @param PublicFlag 开放标志
	 * @param appraisalPublicDetail 返回查找成功的档案开放/公开鉴定信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * 鉴定管理->开放鉴定	批量添加档案开放鉴定信息
	 * @param userInfo 		经办人信息
	 * @param archivesType 	档案类型
	 * @param archivesNBXHs 操作的档案内部序号集合
	 * @param opinion 		公共鉴定数据项	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param isPublic 		是否开放:0未开放、1开放
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean saveBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, ErrInfo pErrInfo);

	/**
	 * 鉴定管理->取消开放/公开鉴定		删除指定档案类型和内部序号的档案开放鉴定信息
	 * @param archivesType 档案类型
	 * @param NBXH 			档案内部序号
	 * @param publicFlag 	开放标志
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo);
	
	/**
	 * 鉴定管理->公开鉴定	批量添加档案开放鉴定信息
	 * @param userInfo 		经办人信息
	 * @param archivesType 	档案类型
	 * @param archivesNBXHs 操作的档案内部序号集合
	 * @param opinion 		公共鉴定数据项	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param isOpen 		是否公开:0未公开、1公开
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean saveBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, ErrInfo pErrInfo);

	
	/**
	 * Dao接口定义：分页查询公开/开放鉴定登记信息
	 * @param archivesTypeIds 指定档案类型下的所有档案类型id
	 * @param params 参数列表 包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
	 * 								鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd、开放标志PublicFlag
	 * @param dataPageInfo 数据页信息对象类 
	 * @param appraisalPublicDetails 返回开放鉴定登记信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo);

}
