/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案信息归档表的DAO接口定义
 *
 */
public interface IArchivesInfoSavedDao {
	 
	/**
	 * Dao接口定义：添加档案信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 要添加的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesType archivesType,int NBXH, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的档案信息<br>
	 * 仅删除指定内部序号的档案信息记录，不会涉及到其卷内文件信息记录
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 要删除的档案信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：删除指定的档案信息<br>
	 * 不仅删除指定内部序号的档案信息记录，而且其卷内文件信息记录也一并删除（如有的话）
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 要删除的档案信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteParentAndChild(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的档案信息<br>
	 * 修改档案信息时仅能够修改档案的元数据属性字段值，对于档案工作流状态以及著录人员等信息不作修改
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 要更新的档案信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**[已费除：用queryClassified（...,archivesInfoQueryConditions_SQL,...）取代它]
	 * 分类查询档案信息（分页获取）
	 * @param archivesTypes 指定的底层档案分类集合，注：当点击最末节点时其中只有一个分类
	 * @param archivesInfoQueryConditions 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */		
//	public boolean queryClassified(UserInfo userInfo,  List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 分类查询档案信息（分页获取）
	 * @param archivesTypes 指定的底层档案分类集合，<br>
	 * 注：当点击最末节点时其中只有一个分类,当点击中间档案分类节点时有多多个分类
	 * @param archivesInfoQueryConditions_SQL 查询条件，字符串
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */		
	public boolean queryClassified(UserInfo userInfo,  List<ArchivesType> archivesTypes,String archivesInfoQueryConditions_SQL,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据内部序号查找档案信息
	 * @param pNBXH 指定的内部序号
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 返回查找成功的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：根据内部序号查找档案信息
	 * @param pNBXH 指定的内部序号
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfos 返回查找成功的卷内文件信息集合，以卷内文件序号升序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找指定案卷的最大卷内文件序号值
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXH 指定的案卷内部序号
	 * @param maxSubContentID 返回查找成功的最大卷内文件序号值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMaxSubContentID(ArchivesType archivesType, int parentNBXH,IntegerEx maxSubContentID, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：更新指定案卷的卷内文件数量和总页数
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXH 指定的案卷内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateSubContentCountAndPageSum(ArchivesType archivesType, int parentNBXH,ErrInfo pErrInfo);
		
	/**
	 * Dao接口定义：批量更新档案信息的案卷标志
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXHs 指定的案卷内部序号数组
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean batchUpdateParentFlag(ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：批量删除档案信息<br>
	 * 仅删除指定内部序号的档案信息记录，不会涉及到其卷内文件信息记录
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXHs 指定的案卷内部序号数组
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean batchDelArchives(ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：批量更新文件级档案的案卷的内部序号
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXHs 指定的案卷内部序号数组
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean batchUpdateParentNBXH(int parentNBXH, ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 根据查询条件统计出指定分类的档案数量
	 * @param userInfo 用户信息
	 * @param archivesType 档案分类信息
	 * @param querySQL  SQL查询条件
	 * @param countNum  返回查询结果
	 * @param pErrInfo  返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean queryCountByClassified(UserInfo userInfo,ArchivesType archivesType,StringBuilder querySQL,IntegerEx countNum,ErrInfo pErrInfo);

	/**
	 * 鉴定管理->存毁鉴定：查询指定档案类型的过期档案信息（分页获取）
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param FormationDepartmentID 档案形成部门编号
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查询成功后的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean queryClassifiedForSaveDestroyAppraisal(ArchivesType archivesType,int FormationDepartmentID,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：批量更新档案信息的保存期限
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param saveArchives 需要继续保存的档案Map集合 键：Integer数组：档案内部序号、档案类型id 	值：保存期限id
	 * @param destoryArchives 需要销毁的档案List集合 Integer数组：档案内部序号、档案类型id
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateBatchRetentionPeriod(ArchivesType archivesType, Map<Integer[], Integer> saveArchives, List<Integer[]> destoryArchives, ErrInfo pErrInfo);
	
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
	 * 鉴定管理->开放鉴定	批量更新档案开放信息
	 * @param userInfo 		用于查询此用户开放鉴定的档案
	 * @param archivesType 	档案类型
	 * @param archivesNBXHs 操作的档案内部序号集合
	 * @param isPublic 		是否开放:0未开放、1开放
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean updateBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, ErrInfo pErrInfo);
	
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
	 * 鉴定管理->公开鉴定	批量更新档案密级信息
	 * @param userInfo 		经办人信息
	 * @param archivesType 	档案类型
	 * @param archivesNBXHs 操作的档案内部序号集合
	 * @param isPublic 		是否开放:0未公开、1公开
	 * @param pErrInfo 		返回处理失败的错误信息
	 * @return 				处理成功返回true，否则返回false
	 */
	boolean updateBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, ErrInfo pErrInfo);
	
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
	 * 根据条码查询档案信息
	 * @param archivesType
	 * @param barcode
	 * @param archivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * 更新档案条码
	 * @param archivesID
	 * @param archivesType
	 * @param barcode
	 * @param pErrInfo
	 * @return
	 */
	boolean updateArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo);

	/**
	 * 根据档号查询档案信息
	 * @param archivesFondsID
	 * @param archivesType
	 * @param archivesInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findByArchivesID(String archivesID, ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * 根据内部序号删除档案信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param nbxhs  要删除的档案信息内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo);

	/**
	 * 将档案信息从saved表复制到working表
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param nbxhs 要复制的档案信息内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkOutArchivesInfo(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo);
}
