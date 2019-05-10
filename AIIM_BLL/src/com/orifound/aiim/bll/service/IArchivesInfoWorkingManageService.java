/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案归档工作表中的档案信息管理服务的接口定义<br>
 * 注意：该接口中定义的方法仅对档案归档工作表数据（ArchivesInfoWorking_TypeCode）进行查询，<br>
 * 不会访问档案归档信息表（ArchivesInfoSaved_TypeCode）中的数据
 *
 */
public interface IArchivesInfoWorkingManageService
{

	/**
	 * 查找指定分类和工作流状态下满足条件的档案（分页获取）
	 * @param userID 著录人员的编号数组
	 * @param archivesType 指定档案分类
	 * @param enumArchivesInfoType 指定档案信息类型（文件或案卷）
	 * @param enumWorkFlowStatus 指定工作流状态
	 * @param archivesInfoQueryConditions 指定查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 查询成功后返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfos(int[] userID,ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType, EnumWorkFlowStatus enumWorkFlowStatus,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	/**
	 * 根据内部序号查找档案归档工作表中的档案信息
	 * @param pNBXH 指定的内部序号
	 * @param archivesType 所属档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfo 返回查找成功的档案归档工作表中的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfoByNBXH(int pNBXH, ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 档案归档过程中的插卷处理：将指定的多个文件信息插入指定案卷中作为其卷内文件<br>
	 * 注意，如果对目标案卷现已存在的卷内文件的次序进行了调整，那么需要对其卷内文件序号属性进行更新赋值。
	 * @param archivesType 指定文件所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 要进行插卷的多个文件，其内部序号属性必须赋值
	 * @param archivesInfo 要插入的目标案卷，插卷成功后会同步更新该对象的ChildArchivesInfos属性
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean appendArchivesInfos(ArchivesType archivesType,List<ArchivesInfo> archivesInfos,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 档案归档过程中的拆卷处理：将指定的案卷拆开，其卷内文件全部变为独立的文件信息
	 * @param archivesType 指定案卷所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 要进行拆卷的案卷集合，其成员对象的内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean brokeArchivesInfo(ArchivesType archivesType,List<ArchivesInfo> archivesInfos,int userID, ErrInfo pErrInfo);

	/**
	 * 删除指定的单个档案信息，包括其原文电子文件信息<br>
	 * 这里的档案可以是文件级或案卷级归档的档案基本信息，也可以是卷内文件信息<br>
	 * 如果是案卷档案，其卷文件文件也会一并删除
	 * @param archivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param enumArchivesInfoType 指定档案的归档类型（文件级、案卷级或卷内文件）
	 * @param archivesInfo 要删除的档案，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesInfo(ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 删除指定的多个档案信息，包括其原文电子文件信息<br>
	 * 这里的档案可以是文件级或案卷级归档的档案基本信息，也可以是卷内文件信息<br>
	 * 如果是案卷档案，其卷文件文件也会一并删除
	 * @param archivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param enumArchivesInfoType 指定档案的归档类型（文件级、案卷级或卷内文件）
	 * @param archivesInfos 要删除的档案，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesInfos(ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 查找指定用户待著录审核的档案（分页获取）
	 * @param deptIDs 著录审核人员所负责的部门编号数组
	 * @param archivesType 指定档案分类，其档案分类编号属性必须赋值
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 查询成功后返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findInputCheckNeedArchivesInfos(int[] deptIDs,ArchivesType archivesType,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);

	/**
	 * 根据内部序号查找指定案卷的卷内文件信息
	 * @param pNBXH 指定案卷的内部序号
	 * @param archivesType 指定案卷所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 返回查找成功后的卷内文件集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findChildArchivesInfosByNBXH(int pNBXH,ArchivesType archivesType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 归档过程中的组卷处理：将指定的多个文件信息合并为一份案卷
	 * @param userID 组卷人员的编号
	 * @param archivesType 指定文件所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 要进行组卷的多个文件，其内部序号属性必须赋值
	 * @param archivesInfo 组卷后的案卷基本信息，组卷成功后会同步更新该对象的ChildArchivesInfos属性
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean combineArchivesInfos(int userID,ArchivesType archivesType,List<ArchivesInfo> archivesInfos,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * 归档过程中的合卷处理：将指定的多个案卷信息合并为一份案卷
	 * @param archivesType 指定案卷所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 要进行合卷的多个案卷，其内部序号属性必须赋值
	 * @param archivesInfo 合卷后的案卷基本信息，合卷成功后会同步更新该对象下的ChildArchivesInfos属性值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean mergeArchivesInfos(ArchivesType archivesType,List<ArchivesInfo> archivesInfos,ArchivesInfo archivesInfo, ErrInfo pErrInfo);

	/**
	 * 添加一份新的档案信息<br>
	 * 这里的档案可以是文件级或案卷级归档的档案基本信息，也可以是卷内文件信息
	 * @param userID 著录人员的编号
	 * @param archivesType 所属档案分类，其档案分类编号属性必须赋值
	 * @param enumArchivesInfoType 指定档案信息类型（文件、案卷或卷内文件）
	 * @param archivesInfo 要添加的档案
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesInfo(int userID, ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);

	/**
	 * 修改指定的档案信息<br>
	 * 这里的档案可以是文件级或案卷级归档的档案基本信息，也可以是卷内文件信息<br>
	 * 修改档案信息时仅能够修改档案的元数据属性字段值，对于档案工作流状态以及著录人员等信息不作修改
	 * @param userID 著录人员的编号
	 * @param archivesType 所属档案分类，其档案分类编号属性必须赋值
	 * @param enumArchivesInfoType 指定档案信息类型（文件、案卷或卷内文件）
	 * @param archivesInfo 要添加的档案
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesInfo(ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);

	/**
	 * 档案归档过程中的拆分处理：将指定的卷内文件从所属案卷中拆分出去形成独立的文件
	 * @param archivesType 指定案卷所属的档案分类，其档案分类编号属性必须赋值
	 * @param parentArchivesInfo 所属案卷，其内部序号属性必须赋值
	 * @param childArchivesInfos 要进行拆分的卷内文件集合，其成员的NBXH属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean separateArchivesInfos(ArchivesType archivesType,ArchivesInfo parentArchivesInfo,List<ArchivesInfo> childArchivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 档案归档过程中的分卷处理：将指定的卷内文件从所属案卷中分离出去形成新的案卷
	 * @param archivesType 指定案卷所属的档案分类，其档案分类编号属性必须赋值
	 * @param parentArchivesInfo 原来所属案卷，其内部序号属性必须赋值
	 * @param childArchivesInfos 要进行分离的卷内文件集合，其成员的NBXH属性必须赋值
	 * @param desArchivesInfo 分离后新形成的目标案卷
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean splitArchivesInfos(ArchivesType archivesType,ArchivesInfo parentArchivesInfo,List<ArchivesInfo> childArchivesInfos,ArchivesInfo desArchivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 著录信息提交送审：将指定的多份档案信息批量提交至业务指导室进行著录审核
	 * @param userID 著录人员的编号
	 * @param archivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 要提交送审的档案集合，其成员对象的内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean submitToInputCheck(int userID,ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType,List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);

	/**
	 * 统计指定用户（业务指导室专职档案人员）需要进行著录审核的各种档案分类的档案数量<br>
	 * 对于没有提交送审的档案分类不予统计和返回结果<br>
	 * 注意：返回结果不是树状结构，但可利用工具类转换为页面所需的树状结构
	 * @param deptID 用户负责的部门编号数组
	 * @param archivesTypeEx 返回指定用户需要进行著录审核的档案分类集合，以档案分类编号作为集合的键，各分类等待进行著录审核的档案数量可通过该对象的InputCheckNeedArchivesInfoCount属性来访问统计结果
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean statInputCheckNeedCount(int[] deptID,Map<Integer, ArchivesTypeEx> archivesTypeEx, ErrInfo pErrInfo);
	
	/**
	 * 著录审核通过<br>
	 * 对指定档案设置著录审核通过的标志
	 * @param userID 审核人员的编号
	 * @param archivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfo 指定的档案，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean inputCheckPass(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 著录审核打回<br>
	 * 对指定档案设置著录审核打回的标志
	 * @param userID 审核人员的编号
	 * @param archivesType 指定档案所属的档案分类，其档案分类编号属性必须赋值
	 * @param archivesInfo 指定的档案，其内部序号属性必须赋值
	 * @param backReason 打回原因
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean inputCheckBack(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,String backReason,ErrInfo pErrInfo);
	
	/**
	 * 统计著录审核结果<br>
	 * 统计出各档案分类下著录审核通过和未通过的档案数量情况，对于没有提交送审的档案分类不予统计和返回结果<br>
	 * 注意：返回结果不是树状结构，但可利用工具类转换为页面所需的树状结构
	 * @param userID 著录用户编号
	 * @param archivesTypeEx 返回统计结果中有数据的最底层档案分类，以档案分类编号作为集合的键，可通过该对象的InputCheckPassArchivesInfoCount、InputCheckBackArchivesInfoCount属性来访问统计结果
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean statInputCheckResult(int[] userID,Map<Integer, ArchivesTypeEx> archivesTypeEx,ErrInfo pErrInfo);
	
	/**
	 * 查找指定著录用户和档案分类下著录审核通过的档案信息
	 * @param userID 著录用户编号
	 * @param archivesType 档案所属分类，其档案分类编号属性必须赋值
	 * @param dataPageInfo 数据分页显示信息
	 * @param archivesInfos 返回查找成功的档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findInputCheckPassArchivesInfos(int[] userID,ArchivesType archivesType,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 查找指定著录用户和档案分类下著录审核未通过的档案信息
	 * @param userID 著录用户编号
	 * @param archivesType 档案所属分类，其档案分类编号属性必须赋值
	 * @param archivesInfos 返回查找成功的档案信息集合
	 * @param dataPageInfo 数据分页显示信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findInputCheckBackArchivesInfos(int[] userID,ArchivesType archivesType,DataPageInfo dataPageInfo,List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 修复著录审核打回的档案信息<br>
	 * 更新档案信息、设置档案工作流状态为提交送审完成、设置修复标志
	 * @param archivesType 档案所属分类，其档案分类编号属性必须赋值
	 * @param archivesInfo 要修复的档案信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean fixArchivesInfoForInputCheckBack(ArchivesType archivesType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	
	/**
	 * 修复接收审核打回的档案信息<br>
	 * 更新档案信息、设置档案工作流状态为业务指导室著录审核通过、设置修复标志
	 * @param archivesType 档案所属分类，其档案分类编号属性必须赋值
	 * @param archivesInfo 要修复的档案信息，其内部序号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean fixArchivesInfoForPaperCheckBack(ArchivesType archivesType,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 设置工作流状态标志：保存档案归档过程中的工作状态，标志档案归档过程中的指定环节已经处理完成<br>
	 * 业务逻辑：更新档案信息表中的档案工作流状态和工作人员信息，并保存档案归档过程信息
	 * 
	 * @param enumArchivesInfoType 档案信息分类，如文件、案卷
	 * @param pArchivesTypeID 档案分类编号
	 * @param pNBXH 内部序号
	 * @param userID 用户编号
	 * @param enumWorkFlowStatus 工作流状态
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
      boolean setFlagForWorkFlow(EnumArchivesInfoType enumArchivesInfoType, int pArchivesTypeID,  int[] pNBXHs, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

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
      * 删除档案信息
      * @param archivesType 档案所属档案分类
      * @param nBXH 要删除的档案的内部序号
      * @param pErrInfo 返回处理失败的错误信息
  	  * @return 处理成功返回true，否则返回false
      */
	boolean deleteParentAndChild(ArchivesType archivesType, int nBXH, ErrInfo pErrInfo);

	/**
	 * 将文件级档案插入到案卷中
	 * @param userID 操作用户的ID号
	 * @param archivesType 档案所属档案分类
	 * @param parentNBXH 要插入文件的案卷内部序号
	 * @param nBXHS 要插入的文件内部序号
	 * @param pErrInfo 返回处理失败的错误信息
  	 * @return 处理成功返回true，否则返回false
	 */
	boolean insertFileToArchives(int userID, ArchivesType archivesType, int parentNBXH, int[] nBXHS, ErrInfo pErrInfo);
}
