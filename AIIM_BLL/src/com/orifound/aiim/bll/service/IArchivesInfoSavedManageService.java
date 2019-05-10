/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;


/**
 *  档案归档信息的管理服务接口定义
 */
public interface IArchivesInfoSavedManageService {
	/**
	 * 鉴定管理：分类查询档案信息（分页获取）
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfoQueryConditions 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean queryClassifiedForAppraisal(ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 鉴定管理->存毁鉴定登记：查询指定档案类型的过期档案信息（分页获取）
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param formationDepartmentID 档案形成部门编号
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean queryClassifiedForSaveDestroyAppraisal(ArchivesType archivesType,int formationDepartmentID,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 鉴定管理->存毁鉴定登记：记录鉴定详情、批量更新档案信息的保存期限
	 * @param userInfo 经办人信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param saveArchives 需要继续保存的档案Map集合 键：Integer数组：档案内部序号、档案类型id 	值：保存期限id
	 * @param destoryArchives 需要销毁的档案List集合 Integer数组：档案内部序号、档案类型id
	 * @param batchArchives 批量插入存毁鉴定明细表参数
	 * @param opinion 公共鉴定数据项	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateBatchRetentionPeriod(UserInfo userInfo, ArchivesType archivesType, Map<Integer[], Integer> saveArchives, List<Integer[]> destoryArchives, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo);

	/**
	 * 归档档案信息 将档案从working表复制到saved表
	 * @param nbxh 要归档的档案内部序号
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
    boolean saveArchivesInfos(int nbxh, ArchivesType archivesType, ErrInfo pErrInfo);
    
    /**
	 * 鉴定管理->开放鉴定：查询指定档案类型的档案信息（分页获取）
	 * @param userInfo 用户信息 用于查询此用户公开鉴定的档案
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfoQueryConditions 查询条件
	 * @param isPublic 是否开放：1开放、0未开放
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */	
	boolean queryClassifiedForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isPublic, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	/**
	 * 鉴定管理->开放鉴定	记录鉴定详情、批量更新档案开放信息
	 * @param userInfo 		经办人信息
	 * @param archivesType 	档案类型
	 * @param archivesNBXHs 操作的档案内部序号集合
	 * @param isPublic 		是否开放:0未开放、1开放
	 * @param opinion 		公共鉴定数据项	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean updateBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, Map<String, String> opinion, ErrInfo pErrInfo);
	
	
	/**
	 * 鉴定管理->公开鉴定：查询指定档案类型的档案信息（分页获取）
	 * @param userInfo 用户信息 用于查询此用户公开鉴定的档案
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfoQueryConditions 查询条件
	 * @param isOpen 是否公开：1公开、0未公开
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */	
	boolean queryClassifiedForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isOpen, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	/**
	 * 鉴定管理->公开鉴定	记录鉴定详情、批量更新档案密级信息
	 * @param userInfo 		经办人信息
	 * @param archivesType 	档案类型
	 * @param archivesNBXHs 操作的档案内部序号集合
	 * @param isPublic 		是否开放:0未公开、1公开
	 * @param opinion 		公共鉴定数据项	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean updateBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, Map<String, String> opinion, ErrInfo pErrInfo);
	

	/**
	 * 鉴定管理->划控鉴定：查询指定档案类型的档案信息（分页获取）
	 * @param userInfo 经办人信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfoQueryConditions 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */	
	boolean queryClassifiedForControlAreaAppraisal(UserInfo userInfo, ArchivesType archivesType,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	
    /**
     * 更新档案条码
     * @param archivesID 档号
	 * @param archivesType 档案所属类别
     * @param barcode  待更新的档案条码
     * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
     */
	public boolean updateArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo);

	/**
	 * 根据条码查询档案信息
	 * @param archivesType 档案所属类别
	 * @param barcode 档案条码
	 * @param archivesInfo 查询返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * 根据档案的档号查询档案信息
	 * @param archivesID 档号
	 * @param archivesType 档案所属类别
	 * @param archivesInfo 查询返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findByArchivesID(String archivesID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * 根据内部序号查找档案信息
	 * @param pNBXH 指定的内部序号
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 返回查找成功的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * 删除档案信息
	 * @param archivesType 要删除的档案信息所在的档案分类
	 * @param nbxhs  要删除的档案信息内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo);

	/**
	 * 打回归档信息
	 * @param nBXHS 要打回的档案的内部序号
	 * @param archivesType 档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean savedCallBack(int[] nBXHS, UserInfo userInfo,ArchivesType archivesType, ErrInfo pErrInfo);
	
	/**
	 * 根据内部序号查找档案信息
	 * @param pNBXH 指定的内部序号
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfos 返回查找成功的卷内文件信息集合，以卷内文件序号升序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
}
