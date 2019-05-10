/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesBoxLabel;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案移交管理服务的接口定义
 *
 */
public interface IPaperTransferManageService {

	/**
	 * 档案形成部门统计接收审核通过、未通过的数量和提交送审通过未通过数量<br>
	 * 统计接收审核未通过的数量和提交送审通过未通过数量，对于没有移交实物档案和提交送审的档案分类不予统计和返回结果<br>
	 * 注意：返回结果不是树状结构，但可利用工具类转换为页面所需的树状结构
	 * @param enumPaperCheckType 指定接收审核类型，业务指导室接收审核或档案管理室接收审核
	 * @param userID 著录用户编号
	 * @param archivesTypeEx 返回统计结果中有数据的最底层档案分类，以档案分类编号作为集合的键
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean statPaperCheckBackCountAndInputCheckReslut(int [] userIDs,Map<Integer, ArchivesTypeEx> archivesTypeExs,ErrInfo pErrInfo);
	
	/**
	 * 查找指定分类和工作流状态下的档案信息
	 * @param userIDs 操作人员的编号数组(防止有代工的情况)
	 * @param archivesType 指定档案分类
	 * @param enumWorkFlowStatus 指定工作流状态
	 * @param archivesInfos 查询成功后返回的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs,ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,DataPageInfo dataPageInfo,String userType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * 统计当前批次中档案总数量<br/>
	 * 适用功能：形成部门显示当前移交清单的档案信息总数量<br/><br/>
	 *  逻辑：
	 * 1、查找用户当前的移交批次 <br/>
	 * 2、统计总数量<br/>
	 * @param userIDs 操作人员的编号
	 * @param beginTime 监测开始时间
	 * @param endTime 监测结束时间
	 * @param archivesInfosSum 返回的档案总数量
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean staArchivesInfosSumByCurrentTransferBat(int userID ,IntegerEx archivesInfosSum,boolean insideFlag,ErrInfo pErrInfo);
	
	/**
	 * 添加档案信息至指定用户的实物档案移交清单中<br><br>
	 * 适用功能：适用于档案形成部门的档案移交业务   <br/><br>
	 * 逻辑：
	 * 1、查找用户当前的移交批次没有则创建 <br/>
	 * 2、将档案信息复制到批次详细表中<br/>
	 * 3、更新批次中此分类的档案数量<br/>
	 * 4、更新档案信息工作流状态<br/>
	 * @param userInfo 著录用户信息
	 * @param archivesType 档案所属分类
	 * @param archivesInfos 要添加至移交清单的档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addToPaperTransferBatchForOutside(UserInfo userInfo,ArchivesType archivesType,List<ArchivesInfo> archivesInfos,boolean insideFlag,ErrInfo pErrInfo);
	
	/**
	 * 添加指定的移交批次（档案形成部门的档案移交批次）至业务指导室的指定用户的实物档案移交清单中<br>
	 * 适用于业务指导室向档案管理室进行档案移交的业务
	 * @param userID 指定用户的编号
	 * @param paperTansferSubBatches 指定要将哪些子批次（档案形成部门的档案移交批次）的档案添加至移交清单中
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addToPaperTransferBatchForIntside(UserInfo userInfo, String [] paperTransferBatchNos,ErrInfo pErrInfo);
	
	/**
	 * 将档案信息至指定用户的实物档案移交清单中移除<br><br>
	 * 适用功能：适用于档案形成部门的档案移交业务   <br/><br>
	 * 逻辑：
	 * 1、删除批次中的指定的档案信息记录 <br/>
	 * 2、更新档案信息工作流状态 <br/>
	 * 3、更新用户当前批次中档案所在的分类下档案总数量 <br/>
	 * @param nBXH 要移除的档案的内部序号
	 * @param archivesType 指定要移除的档案的档案类型
	 * @param transferBatNo 指定要移除的档案所在的批次
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean removeArchiveSInfoFromQDList(int nBXH, ArchivesType archivesType, String transferBatNo,int userID,EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);
	
	/**
	 * 档案形成部门确认要移交指定批次的实物档案<br>
	 * 适用功能：档案形成部门确认要移交指定批次的实物档案   <br><br>
	 * 逻辑：
	 * 1、设置批次信息确认移交状态并更新批次中档案总数量<br/>
	 * 2、更新档案信息工作流状态(先查询批次中的档案分类，再根据分类设置档案工作流状态) <br/>
	 * @param paperTransferBatNo 实物档案移交批次编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean confirmTransferPaperForOutside(String paperTransferBatNo,UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * 业务指导室确认要移交指定批次的实物档案<br>
	 * 适用功能：业务指导室确认要移交指定批次的实物档案   <br><br>
	 * 逻辑：
	 * 1、设置批次信息确认移交状态并更新批次中档案总数量<br/>
	 * 2、更新档案信息工作流状态(先查询批次中的档案分类，再根据分类设置档案工作流状态) <br/>
	 * @param paperTransferBatNo 实物档案移交批次编号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean confirmTransferPaperForInside(String paperTransferBatNo,UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * 查找批次下的分类信息   在知道档案移交批次的情况下
	 * @param paperTransferBatchesDetails 返回查找成功后的档案移交明细清单
	 * @param paperTransferBatchesArchvTypeDetails 返回的批次分类信息情况的集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);
	
	/**
	 * 查找指定部门移交的处于指定处理状态的馆外移交批次信息<br>
	 * 适用于业务指导室对各档案形成部门的馆外移交接收管理，指定单个部门进行查找
	 * @param departmentID 指定部门的编号
	 * @param enumPaperTransferBatchesDealStatus 指定移交批次的处理状态
	 * @param paperTransferBatches 返回查找成功后的档案移交批次信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchesForOutside(int departmentID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * 查找指定部门移交的满足指定条件的馆外移交批次信息<br>
	 * 适用于业务指导室对各档案形成部门的馆外移交接收管理，指定单个部门进行查找
	 * @param departmentID 指定部门的编号
	 * @param enumPaperTransferBatchesDealStatus 指定移交批次的处理状态
	 * @param paperTransferBatchesQueryCondition 查询条件
	 * @param paperTransferBatches 返回查找成功后的档案移交批次信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchesForOutside(int departmentID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition,DataPageInfo dataPageInfo,List<PaperTransferBatch> paperTransferBatches, ErrInfo pErrInfo);
	
	/**
	 * 查找指定用户移交的处于指定处理状态的馆内移交批次信息<br>
	 * 适用于档案管理室对业务指导室的馆内移交接收管理
	 * @param userID 指定用户的编号
	 * @param enumPaperTransferBatchesDealStatus 指定移交批次的处理状态
	 * @param paperTransferBatches 返回查找成功后的档案移交批次信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchesForInside(int userID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * 查找指定用户移交的处于指定处理状态并满足指定条件的馆内移交批次信息（分页获取）<br>
	 * 适用于档案管理室对业务指导室的馆内移交接收管理
	 * @param userID 指定用户的编号
	 * @param enumPaperTransferBatchesDealStatus 指定移交批次的处理状态
	 * @param paperTransferBatchesQueryCondition 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param paperTransferBatches 返回查找成功后的档案移交批次信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchesForInside(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, DataPageInfo dataPageInfo, List<PaperTransferBatch> paperTransferBatches, boolean insideFlag,ErrInfo pErrInfo);
	
	/**
	 * 查找指定批次号的档案移交批次信息 以便登记使用
	 * @param paperTransferBatNo 指定的档案移交批次
	 * @param paperTransferBatch 查找成功后返回的档案移交批次信息，可直接访问其PaperTransferBatchesArchvTypeDetails属性值获取该移交批次下的各档案分类的档案数量情况
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchByBatchNo(String paperTransferBatNo,PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);
	
	/**
	 * 查找指定用户当前移交批次的分类信息
	 * @param userID 操作人员的编号
	 * @param paperTransferBatchesDetails 返回查找成功后的档案移交明细清单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCurrentPaperTransferBatchesArchvTypeDetails(int userID,PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * 查找指定移交批次和档案分类下的档案明细清单信息
	 * @param paperTransferBatNo 指定的移交批次
	 * @param archivesType 指定的档案分类
	 * @param paperTransferBatchesDetails 返回查找成功后的档案移交明细清单
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findPaperTransferBatchesDetails(String paperTransferBatNo,ArchivesType archivesType,List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo);
	
	/**
	 * 实物档案接收登记
	 * @param paperTransferBatch 要登记的档案移交批次信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean registerPaperReceive(UserInfo userInfo ,String batNo,List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);
	
	/**
	 * 为指定移交批次中指定档案分类下的档案生成档号<br>
	 * 注意：只有在指定移交批次中指定档案分类下的档案全部接收审核完成后才能够为接收审核通过的档案批量生成档号
	 * @param paperTransferBatNo 实物档案移交批次编号
	 * @param archivesType 指定的档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean generateArchivesID(String paperTransferBatNo,ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * 设置批次审核完成状态
	 * @param paperTransferBatNo 指定要设置的批次
	 * @param archivesType 指定档案的档案类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean checkFinish(String paperTransferBatNo, ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * 接收审核通过<br>
	 * 对指定档案设置接收审核通过的标志
	 * @param userID 审核人员的编号
	 * @param archivesType 指定档案所属的档案分类
	 * @param archivesInfo 指定的档案，其NBXH属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean paperCheckPass(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,String batNo,EnumCheckResult enumCheckResult,ErrInfo pErrInfo);
	
	/**
	 * 接收审核打回<br>
	 * 对指定档案设置接收审核打回的标志
	 * @param userID 审核人员的编号
	 * @param archivesType 指定档案所属的档案分类
	 * @param archivesInfo 指定的档案，其NBXH属性必须赋值
	 * @param backReason 打回原因
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean paperCheckBack(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,String backReason,String batNo,EnumCheckResult enumCheckResult,ErrInfo pErrInfo);

	/**
	 * 统计档案管理室接收审核未通过的数量<br>
	 * 统计出各档案分类下接收审核未通过的档案数量情况，对于没有移交实物档案的档案分类不予统计和返回结果<br>
	 * 注意：返回结果不是树状结构，但可利用工具类转换为页面所需的树状结构
	 * @param enumPaperCheckType 指定接收审核类型，业务指导室接收审核或档案管理室接收审核
	 * @param userID 著录用户编号
	 * @param archivesTypeEx 返回统计结果中有数据的最底层档案分类，以档案分类编号作为集合的键，可通过该对象的PaperCheckBackArchivesInfoCount属性来访问统计结果
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean statPaperCheckBackCount(int [] userIDs,String userType,Map<Integer, ArchivesTypeEx> archivesTypeExs,ErrInfo pErrInfo);
	
	/**
	 * 查找指定档案盒的标签<br>
	 * 计算出指定档案盒的标签内容，即盒内档号起止范围
	 * @param archivesBoxBarcode 指定档案盒条码
	 * @param archivesBoxLabel 返回查找成功后的档案盒标签信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesBoxLabel(String archivesBoxBarcode,ArchivesBoxLabel archivesBoxLabel,ErrInfo pErrInfo);
	
	/**
	 * 粘贴档案条码：将档案信息与档案条码进行关联<br>
	 * @param archivesFondsID 档号
	 * @param archivesType 指定的档案分类
	 * @param barcode 档案条形码
	 * @param archivesInfo 返回关联成功后的档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean pasteArchivesBarcode(String archivesID,ArchivesType archivesType,String barcode,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * 档案装盒：将档案条码与档案盒条码进行关联<br>
	 * 适用于一次关联多个档案条码
	 * @param archivesInfos 要装盒的档案（集合），其成员对象的档案条码属性必须赋值
	 * @param archivesBoxBarcode 盒条码，将档案装入该指定的档案盒中
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean archivesBoxing(List<String> barcodes,String archivesBoxBarcode, ErrInfo pErrInfo);

	/**
	 * 查找制定批次中未关联条码的档案的第一卷
	 * @param batNo
	 * @param archivesTypeID
	 * @param paperTransferBatchesDetail
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
}