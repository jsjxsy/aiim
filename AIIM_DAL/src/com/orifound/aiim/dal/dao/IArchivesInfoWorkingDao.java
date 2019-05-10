/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;


import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.EnumWorkingUserIDType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 档案信息工作表的DAO接口定义
 * @param <EnumCheckResult>
 *
 */
public interface IArchivesInfoWorkingDao
{

	/**
	 * Dao接口定义：添加档案信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param archivesInfo 要添加的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

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

	/**
	 * 查找指定工作流状态的所有档案信息（分页获取）
	 * @param enumArchivesInfoType 指定档案信息类型（文件或案卷）
	 * @param userID 著录人员的编号数组
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param enumWorkFlowStatus 指定工作流状态
	 * @param archivesInfoQueryConditions 指定查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 查询成功后返回的档案信息，以内部序号降序排列
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean find(EnumArchivesInfoType enumArchivesInfoType, int[] userID,ArchivesType archivesType,EnumWorkFlowStatus enumWorkFlowStatus,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
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
	 * Dao接口定义：更新指定档案的工作流状态和用户编号信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param pNBXH 指定的档案内部序号
	 * @param enumWorkingUserIDType 用户编号类型
	 * @param userID 用户编号
	 * @param enumWorkFlowStatus 工作流状态
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateWorkFlowUserIDByNBXH(ArchivesType archivesType, int[] pNBXHs,EnumWorkingUserIDType enumWorkingUserIDType,int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定案卷的所有卷内文件的工作流状态和用户编号信息
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXH 指定的案卷内部序号
	 * @param enumWorkingUserIDType 用户编号类型
	 * @param userID 用户编号
	 * @param enumWorkFlowStatus 工作流状态
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateWorkFlowUserIDForChild(ArchivesType archivesType, int[] parentNBXHs,EnumWorkingUserIDType enumWorkingUserIDType, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：批量更新档案信息的案卷标志
	 * @param archivesType 所属档案分类，其档案相关信息表属性必须有值
	 * @param parentNBXHs 指定的案卷内部序号数组
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean batchUpdateParentNBXH(ArchivesType archivesType, final List<ArchivesInfo> archivesInfos,final int userID,final EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);
	
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
	 * Dao接口定义：统计档案著录审核通过未通过的档案数量
	 * @param userIDs 著录人员的编号数组(防止有代工的情况)
	 * @param ArchivesType 所属档案分类
	 * @param enumWorkFlowStatus 工作流程
	 * @param pErrInfo 返回处理失败的错误信息
	 * @param count 返回统计结果
	 * @return 处理成功返回true，否则返回false
	 */
	boolean statArchivesInfoCountByWorkFlowStatus(int [] userIDs, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,String userType,IntegerEx count, ErrInfo pErrInfo);

	/**
	 * Dao接口定义 : 查找指定分类和工作流状态下的档案信息
	 * @param userIDs 著录人员的编号数组(防止有代工的情况)
	 * @param archivesType 指定档案分类
	 * @param enumWorkFlowStatus 指定工作流状态
	 * @param archivesInfos 查询成功后返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs,ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo,String userType,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义 : 查找指定分类和工作流状态下的档案信息
	 * @param deptIDs 著录人员所负责的部门的编号数组
	 * @param archivesType 指定档案分类
	 * @param enumWorkFlowStatus 指定工作流状态
	 * @param archivesInfos 查询成功后返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfosByEnumWorkFlowStatus(ArchivesType archivesType,int[] deptIDs, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	/**
	 * 更新档案打回原因
	 * @param nbxh 打回档案的内部序号
	 * @param backReason 打回原因
	 * @param archivesType 档案分类信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesBackReason(int nbxh, ArchivesType archivesType,String backReason ,ErrInfo pErrInfo);
	
	 /**
  	 * 修改指定的档案信息并设置档案信息修复标志<br>
  	 * 值针对案卷设置修复标志
  	 * @param archivesType 所属档案分类，其档案分类编号属性必须赋值）
  	 * @param NBXH 要设置修复标记的档案内部序号
  	 * @param pErrInfo 返回处理失败的错误信息
  	 * @return 处理成功返回true，否则返回false
  	 */
     boolean updateArchivesInfoFixedFlag(ArchivesType archivesType,int NBXH,ErrInfo pErrInfo);
     
     /**
      * 更新档案案卷号和案卷号文本
      * @param contentID 案卷号
      * @param contentIDText 案卷号文本
      * @param NBXH 要更新的档案的内部序号
      * @param archivesType 所属档案分类
      * @param pErrInfo 返回处理失败的错误信息
  	  * @return 处理成功返回true，否则返回false
      */
     boolean updateContentIDText(int contentID,String contentIDText,int NBXH,ArchivesType archivesType,ErrInfo pErrInfo);
     
    /**
     * 给卷内文件生成档号
     * @param parentNBXH 所在案卷内部序号
     * @param archivesType 所属档案分类
     * @param pErrInfo 返回处理失败的错误信息
  	  * @return 处理成功返回true，否则返回false
     */
     boolean updateSubArchivesID(int parentNBXH,ArchivesType archivesType,ErrInfo pErrInfo);
}
