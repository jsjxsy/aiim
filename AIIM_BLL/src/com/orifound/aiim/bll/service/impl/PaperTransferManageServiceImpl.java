package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.ICurrentContentIDManageService;
import com.orifound.aiim.bll.service.IPaperTransferManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.dal.dao.ICurrentTransferBatNoDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesArchvTypeDetailsDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesDetailsDao;
import com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao;
import com.orifound.aiim.entity.ArchivesBoxLabel;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.CurrentContentID;
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
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案移交管理服务实现类
 *
 */
public class PaperTransferManageServiceImpl implements IPaperTransferManageService {
	
	/**
	 * 档案归档工作表中的档案信息管理服务对象
	 */
	@Autowired
	private IArchivesInfoWorkingDao archivesInfoWorkingDao = null;
	
	/**
	 *  纸质档案移交批次表的数据访问对象
	 */
	@Autowired
	private IPaperTransferBatchesDao paperTransferBatchesDao = null;

	/**
	 * 批次详细表表的数据访问对象
	 */
	@Autowired
	private IPaperTransferBatchesDetailsDao paperTransferBatchesDetailsDao = null;

	/**
	 * 当前移交批次信息 表的数据访问对象
	 */
	@Autowired
	private ICurrentTransferBatNoDao currentTransferBatNoDao = null;

	/**
	 * 纸质档案移交批次档案分类明细情况表的DAO接口定义
	 */
	@Autowired
	private IPaperTransferBatchesArchvTypeDetailsDao paperTransferBatchesArchvTypeDetailsDao; 
	
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService;
	
	/**
	 * 当前案卷号管理服务接口
	 */
	@Autowired
	private ICurrentContentIDManageService currentContentIDManageService;
	
	/**
	 * 当前案卷号管理服务接口
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;

	/**
	 * 库房档案信息DAO
	 */
	@Autowired
	private IStoreroomArchivesInfoDao storeroomArchivesInfoDao;
	
	@Override
	public boolean statPaperCheckBackCountAndInputCheckReslut(int[] userIDs, Map<Integer, ArchivesTypeEx> archivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		IntegerEx count = new IntegerEx();
		try {
			//检查依赖注入
			pErrPos = 1;
		
			pErrPos = 1;
			if (archivesInfoWorkingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案归档工作表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}
			}

			if (pFlag) {
				if (archivesTypeExs == null) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息未赋值");
				}
			}
			
		   for (ArchivesTypeEx archivesTypeEx : archivesTypeExs.values()) {
			   //调用业DAO统计著录审核通过
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.业务指导室著录审核通过,"UserID1", count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "统计著录审核通过的数量失败");
					}else{
						archivesTypeEx.setInputCheckPassArchivesInfoCount(count.getValue());
					}
			   }
			  
			 //调用业DAO统计著录审核通过
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.业务指导室著录审核打回,"UserID1",count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "统计著录审核未通过的数量失败");
					}else{
						archivesTypeEx.setInputCheckBackArchivesInfoCount(count.getValue());
					}
			   }
			   
			   //调用DAO统计移交未通过的数量
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.业务指导室接收审核打回,"UserID1",count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "统计接收审核未通过的数量失败");
					}else{
						archivesTypeEx.setPaperCheckBackArchivesInfoCount(count.getValue());
					}
			   }
		    }
				
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo,String userType,List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查依赖注入
			pErrPos = 1;
			if (archivesInfoWorkingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案归档工作表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else if(userIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}
			}

			if (pFlag) {
				if (archivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息未赋值");
				}
			}
			
			if (pFlag) {
				if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息编号未赋值");
				}
			}
			
			if (pFlag) {
				if (enumWorkFlowStatus == EnumWorkFlowStatus.NONE) {
					pFlag = false;
					pErrInfo.getContent().append("请指定工作流程");
				}
			}
			
			if (pFlag) {
				if (dataPageInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("分页对象非法为空");
				}
			}
			
			if (pFlag) {
				//调用DAO
				if(archivesInfoWorkingDao.findArchivesInfosByEnumWorkFlowStatus(userIDs, archivesType, enumWorkFlowStatus, dataPageInfo,userType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找档案信息失败");
				}
			}
				
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean staArchivesInfosSumByCurrentTransferBat(int userID, IntegerEx archivesInfosSum,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		try {
			//检查依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (userID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userID);
				}
			}
	
			if (pFlag) {
				if (archivesInfosSum == null) {
					pFlag = false;
					pErrInfo.getContent().append("统计结果对象非法为空");
				}
			}
			
			
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,insideFlag,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找当前用户是否有未确认移交的批次失败");
				}
			}
			
			if(pFlag){
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					archivesInfosSum.setValue(0);
				}else{
					//调用DAO
					if(paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBat(archivesInfosSum,paperTransferBatch,  pErrInfo)== false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "统计当前批次档案信息数量失败");
					}
				}
			}	
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean addToPaperTransferBatchForOutside(UserInfo userInfo, ArchivesType archivesType, List<ArchivesInfo> archivesInfos,boolean insideFlag , ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		paperTransferBatch.setInsideFlag(insideFlag);//设置馆内移交标志为false
		
		int NBXHS [] = null;
		PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
		
		try {
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}

			//检查用户编号
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0。");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userInfo.getUserID());
					paperTransferBatch.setTransferDepartmentID(userInfo.getDepartmentID());
				}
			}
			
			//检查分类信息
			if (pFlag) {
				if (archivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空，请检查是否赋值。");
				}else if(archivesType.getID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息编号非法为0，请检查是否赋值。");
				}
			}
			
			//检查档案信息集合
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("要加入清单的档案信息集合非法为空，请检查是否初始化。");
				}else if(archivesInfos.size() <= 0){
					pFlag = false;
					pErrInfo.getContent().append("请选择要加入清单的档案信息。");
				}else{
					NBXHS = new int [archivesInfos.size()];
					for (int i = 0; i < archivesInfos.size(); i++) {
						NBXHS[i] = archivesInfos.get(i).getNBXH();
					}
				}
			}

			//调用DAO查找当前用户是否有未确认移交的批次   返回批次信息 
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,insideFlag,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找当前用户是否有未确认移交的批次失败");
				}
			}
			
			//如果没找到，则创建
			if (pFlag) {
				pErrPos = 3;
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					if (this.getTransferBatNo(userInfo.getUserID(), paperTransferBatch,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "得到批次号失败");
					}
					
					//添加一条新的批次信息
					if (pFlag) {
						if (paperTransferBatchesDao.add(paperTransferBatch, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "添加批次信息失败");
						}
					}
				}
			}

			//将档案加入到批次
			if (pFlag) {
				pErrPos = 4;
				if(paperTransferBatchesDetailsDao.addPaperTransferBatchesDetails(archivesType,archivesInfos,paperTransferBatch,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "加入移交清单失败");
				}
			}
			
			//更新档案分类下档案数量情况
			if (pFlag) {
				pErrPos = 5;
				paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
				paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
				paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatch.getBatNo());
				paperTransferBatchesArchvTypeDetail.setTransferTotal(archivesInfos.size());
				if (insideFlag) {
					List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
					pPaperTransferBatchesArchvTypeDetails.add(paperTransferBatchesArchvTypeDetail);
					if(this.updatePaperTransferBatchesArchvTypeDetailForInside(paperTransferBatch, pPaperTransferBatchesArchvTypeDetails, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新档案信息分类明细情况表失败");
					}
				}else{
					if(this.updatePaperTransferBatchesArchvTypeDetailForOutside(paperTransferBatchesArchvTypeDetail,pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新档案信息分类明细情况表失败");
					}
				}
			}
			
			//更新档案工作流状态
			if (pFlag) {
				pErrPos = 6;
				if(archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), NBXHS, userInfo.getUserID(), EnumWorkFlowStatus.添加至馆外移交清单, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案工作流状态失败: ");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean addToPaperTransferBatchForIntside(UserInfo userInfo, String[] paperTransferBatchNos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		paperTransferBatch.setInsideFlag(true);//设置馆内移交标志
		
		List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		
		try {
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}

			//检查用户编号
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0。");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userInfo.getUserID());
					paperTransferBatch.setTransferDepartmentID(userInfo.getDepartmentID());
				}
			}
			
			//批次号数组是否为空或赋值
			if (pFlag) {
				if (paperTransferBatchNos == null) {
					pFlag = false;
					pErrInfo.getContent().append("要加入清单的批次信息集合非法为空，请检查是否初始化。");
				}else if(paperTransferBatchNos.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("请选择要加入清单的批次。");
				}
			}

			//调用DAO查找当前用户是否有未确认移交的批次   返回批次信息 
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,true,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找当前用户是否有未确认移交的批次失败");
				}
			}
			
			//如果没找到，则创建
			if (pFlag) {
				pErrPos = 3;
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					if (this.getTransferBatNo(userInfo.getUserID(), paperTransferBatch,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "得到批次号失败");
					}
					
					//添加一条新的批次信息
					if (pFlag) {
						if (paperTransferBatchesDao.add(paperTransferBatch, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "添加批次信息失败");
						}
					}
				}
			}

			//将形成部门的移交批次加入到业务指导室的移交批次中   写的是批次和批次关联的表
			if (pFlag) {
				pErrPos = 4;
				if(paperTransferBatchesDetailsDao.addToPaperTransferBatchaddToPaperTransferBatch(paperTransferBatchNos,paperTransferBatch,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "加入移交清单失败");
				}
			}
			
			//更新批次的状态
			if (pFlag) {
				pErrPos = 5;
				for (String pPaperTransferBatchNo : paperTransferBatchNos) {
					if(paperTransferBatchesDao.updateBatState(pPaperTransferBatchNo, EnumPaperTransferBatchesDealStatus.添加到移交清单, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新批次状态失败");
					}
				}
			}
			
			//更新档案分类下档案数量情况   直接去分类统计？
			if (pFlag) {
				pErrPos = 6;
				paperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
				if(this.updatePaperTransferBatchesArchvTypeDetailForInside(paperTransferBatch,paperTransferBatchesArchvTypeDetails,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新档案信息分类明细情况表失败");
				}
			}
			
			//关联更新档案工作流状态并写入工作流记录表
			if (pFlag) {
				pErrPos = 7;
				if(this.setFlagForWorkFlow(paperTransferBatch,paperTransferBatchesArchvTypeDetails, EnumWorkFlowStatus.添加至馆内移交清单, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案工作流状态失败: ");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean removeArchiveSInfoFromQDList(int nBXH, ArchivesType archivesType, String transferBatNo, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
		paperTransferBatchesArchvTypeDetail.setTransferBatNo(transferBatNo);
		paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
		
		try {
			pErrPos = 1;
			//删除档案清单明细
			if (pFlag) {
				if(paperTransferBatchesDetailsDao.delete(transferBatNo,archivesType.getID(), nBXH, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除纸质档案移交批次档案分类明细情况失败");
				}
			}
			
			//修改working表中档案的工作流状态
			if (pFlag) {
				if(archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int[]{nBXH},userID, enumWorkFlowStatus, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流状态失败");
				}
			}
			
			//查找该批次分类的详细信息
			if (pFlag) {
				if(paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找纸质档案移交批次档案分类明细情况失败");
				}
			}
			
			//更新各分类档案总数量  总数量由统计为统计的结果
			if (pFlag) {
				if(paperTransferBatchesArchvTypeDetailsDao.update(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新纸质档案移交批次档案分类明细情况失败");
				}
			}	
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean confirmTransferPaperForOutside(String paperTransferBatNo, UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		IntegerEx archivesInfosSum = new IntegerEx();
		
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new LinkedHashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		try {
			//检查依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}

			if (pFlag) {
				pErrPos = 2;
				paperTransferBatch.setBatNo(paperTransferBatNo);
				if (paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBat(archivesInfosSum,paperTransferBatch,  pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "统计批次中档案总数量失败");
				}
			}
			
			if (pFlag) {
				//调用DAO设置批次信息的确认移交状态
				pErrPos = 3;
				if(paperTransferBatchesDao.updateConfirmFlag(paperTransferBatNo, userInfo, archivesInfosSum.getValue(), pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "确认移交失败");
				}
			}
			
			//查找档案类型
			if (pFlag) {
				pErrPos = 4;
				if (paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获得批次档案分类详细信息失败");
				}
			}
			
			//根据档案类型设置档案工作流状态
			if (pFlag) {
				Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails.values()) {
					if (archivesTypes.containsKey(paperTransferBatchesArchvTypeDetail.getArchivesTypeID())) {
						if (paperTransferBatchesDetailsDao.setFlagForWorkFlow(archivesTypes.get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID()), paperTransferBatch, userInfo.getUserID(), EnumWorkFlowStatus.确认进行实物档案的馆外移交, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "设置工作流失败");
						}
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean confirmTransferPaperForInside(String paperTransferBatNo, UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		IntegerEx archivesInfosSum = new IntegerEx();
		
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new LinkedHashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		try {
			//检查依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}

			//统计批次中档案总数量，以更新送出总数量属性
			if (pFlag) {
				pErrPos = 2;
				paperTransferBatch.setBatNo(paperTransferBatNo);
				if (paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBat(archivesInfosSum, paperTransferBatch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "统计批次中档案总数量失败");
				}
			}
			
			if (pFlag) {
				//调用DAO设置批次信息的确认移交状态
				pErrPos = 3;
				if(paperTransferBatchesDao.updateConfirmFlag(paperTransferBatNo, userInfo, archivesInfosSum.getValue(), pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "确认移交失败");
				}
			}
			
			//查找档案类型
			if (pFlag) {
				pErrPos = 4;
				if (paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获得批次档案分类详细信息失败");
				}
			}
			
			//根据档案类型设置档案工作流状态
			if (pFlag) {
				Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails.values()) {
					if (archivesTypes.containsKey(paperTransferBatchesArchvTypeDetail.getArchivesTypeID())) {
						if (paperTransferBatchesDetailsDao.setFlagForWorkFlow( paperTransferBatch, archivesTypes.get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID()),userInfo.getUserID(), EnumWorkFlowStatus.确认进行实物档案的馆内移交, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "设置工作流失败");
						}
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean findPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch,Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo)== false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean findPaperTransferBatchesForOutside(int departmentID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> pPaperTransferBatches,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("移交批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (paperTransferBatchesDao.findAll(new int []{departmentID}, enumPaperTransferBatchesDealStatus, pPaperTransferBatches,insideFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找移交的批次信息失败");
				}
			}	
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean findPaperTransferBatchesForOutside(int departmentID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition,DataPageInfo dataPageInfo, List<PaperTransferBatch> paperTransferBatches, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("移交批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (paperTransferBatchesDao.findByCondition(new int []{departmentID},  enumPaperTransferBatchesDealStatus,  paperTransferBatchesQueryCondition,  paperTransferBatches, dataPageInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找移交的批次信息失败");
				}
			}	
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean findPaperTransferBatchesForInside(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches, boolean insideFlag,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//调用DAO获取指定用户移交的批次
			if (pFlag) {
				pErrPos = 2;
				if(paperTransferBatchesDao.findPaperTransferBatchsByTransferUser(userID,enumPaperTransferBatchesDealStatus,paperTransferBatches, insideFlag, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据移交用户查找移交批次失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean findPaperTransferBatchesForInside(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, DataPageInfo dataPageInfo, List<PaperTransferBatch> paperTransferBatches,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				if (paperTransferBatchesDao.findByCondition(enumPaperTransferBatchesDealStatus, new int[]{userID}, paperTransferBatchesQueryCondition, paperTransferBatches, dataPageInfo,insideFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找移交的批次信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean findPaperTransferBatchByBatchNo(String paperTransferBatNo, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//查找批次信息.
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findByBatNO(paperTransferBatNo, paperTransferBatch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找批次详情信息失败");
				}
			}
			
			//查找批次分类详细信息
			if (pFlag) {
                Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new LinkedHashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找批次分类详情信息失败");
				}else{
					paperTransferBatch.setPaperTransferBatchesArchvTypeDetails(paperTransferBatchesArchvTypeDetails);
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean findCurrentPaperTransferBatchesArchvTypeDetails(int userID, PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		//PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		try {
			//检查依赖注入
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("批次信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (userID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userID);
				}
			}
	
			if (pFlag) {
				if (paperTransferBatchesArchvTypeDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("统计结果对象非法为空");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				
				paperTransferBatch.setBatNoStatus(EnumPaperTransferBatchesDealStatus.未确认移交.getEnumValue());
				
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,insideFlag,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找当前用户是否有未确认移交的批次失败");
				}
			}
			
			if(pFlag){
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					pFlag = false;
					pErrInfo.getContent().insert(0, "当前用户没有未确认移交的批次");
				}
			}	
			
			if (pFlag) {
				//调用DAO
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "得到当前批次的分类信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean findPaperTransferBatchesDetails(String paperTransferBatNo, ArchivesType archivesType, List<PaperTransferBatchesDetail> paperTransferBatchesDetails, List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				if(paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(paperTransferBatNo,archivesType.getID(),paperTransferBatchesDetails,enumCheckResults,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().append("查询批次档案详细信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean registerPaperReceive(UserInfo userInfo, String batNo, List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		PaperTransferBatch paperTransferBatch = null;
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();

			//1、调用find方法找出批次下的分类详细信息
			if (pFlag) {
				paperTransferBatch = new PaperTransferBatch();
				paperTransferBatch.setBatNo(batNo);
				
				paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询批次分类详细信息失败");
				}
			}
			
			//2、匹配
			if (pFlag) {
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails.values()) {
					for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail1 : pPaperTransferBatchesArchvTypeDetails) {
						if(paperTransferBatchesArchvTypeDetail1.getArchivesTypeID() == paperTransferBatchesArchvTypeDetail.getArchivesTypeID()){
							if(paperTransferBatchesArchvTypeDetail1.getTransferTotal() != paperTransferBatchesArchvTypeDetail.getTransferTotal()){
								pFlag = false;
								pErrInfo.getContent().insert(0,"分类（"+archivesTypes.get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID()).getFullName()+"）档案数量不匹配");
							}
						}
					}
				}
			}
			
			//3、更新批次为登记完成状态
			if (pFlag) {
				paperTransferBatch.setReceiveUserID(userInfo.getUserID());
				paperTransferBatch.setReceiveDepartmentID(userInfo.getDepartmentID());
				paperTransferBatch.setReceiveTime(new Date());
				if (paperTransferBatchesDao.updateForReceive(paperTransferBatch, EnumPaperTransferBatchesDealStatus.接收登记完成, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新批次为登记完成状态失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean generateArchivesID(String paperTransferBatNo, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
		CurrentContentID currentContentID = null;
		List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		List<PaperTransferBatchesDetail> paperTransferBatchesDetails = null;
		
		try {
			//开始处理 1...
			pErrPos = 1;
			//判断接收审核是否完成
			if (pFlag) {
				paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
				paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
				paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatNo);
				if (paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(paperTransferBatchesArchvTypeDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询批次分类详情信息失败");
				}else{
					if(paperTransferBatchesArchvTypeDetail.getArchivesIDMaked()){
						pFlag = false;
						pErrInfo.getContent().insert(0, "该分类已经生成档号");
					}
				}
			}
			
			//查找此批次分类是否还有未审核的档案
			if (pFlag) {
				pErrPos = 2;
				paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
				List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
				enumCheckResults.add(EnumCheckResult.尚未审核);
				if (paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(paperTransferBatNo, archivesType.getID(), paperTransferBatchesDetails,enumCheckResults , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查未审核的批次详细信息失败");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (paperTransferBatchesDetails.size()>0) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "该分类中还有未审核的档案，不能生成档号");
				}
			}
			
			//生成档号
			if (pFlag) {
				pErrPos = 4;
				//用分类档案规则取出档号的前缀 关联working表和批次详细表查询
				Map<Integer,String> archivesIDPrefixs = new HashMap<Integer, String>();
				if (paperTransferBatchesDetailsDao.findArchivesIDPrefixWhitOutArchivesID(paperTransferBatNo, archivesType, archivesIDPrefixs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找档号前缀失败");
				}
				
				//判断有没有值
				if (pFlag) {
					pErrPos = 5;
					if (archivesIDPrefixs.size()>0) {
						for (Iterator<Integer> keys = archivesIDPrefixs.keySet().iterator();keys.hasNext();) {
							int NBXH = keys.next();
							String archivesIDPrefix = archivesIDPrefixs.get(NBXH);
							//用档号的前缀取出案卷号最大值
							currentContentID = new CurrentContentID();
							pErrPos = 6;
							if (currentContentIDManageService.findCurrentContentIDByPrefix(archivesIDPrefix, currentContentID, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "根据档号前缀查找案卷号最大值失败");
							}
							
							//更新档案的案卷号和案卷号文本
							if (pFlag) {
								pErrPos = 7;
								int contentIDTextLength = archivesType.getContentIDFormatLength();//取出该分类案卷号文本的长度
						    	StringBuffer contentIDTextBase = new StringBuffer();
						    	for(int i=0;i<contentIDTextLength;i++){
						    		contentIDTextBase.append("0");
						    	}
						    	int contentID = currentContentID.getContentID();
						    	
						    	String contentIDText = "";
					        	contentIDText = new Integer(contentID).toString();
					        	
					        	if(contentIDText.length() < contentIDTextLength){
					        		contentIDText =  (contentIDTextBase.replace((contentIDTextLength-contentIDText.length()), contentIDTextLength, contentIDText)).toString();
					        	}
					        	
								if (archivesInfoWorkingDao.updateContentIDText(currentContentID.getContentID(), contentIDText, NBXH, archivesType, pErrInfo) == false) {
					        		pFlag = false;
									pErrInfo.getContent().insert(0, "更新案卷号文本信息失败");
								}
							}
							
							//更新案卷号的最大值
							if (pFlag) {
								pErrPos = 8;
								currentContentID.setContentID(currentContentID.getContentID()+1);
								if (currentContentIDManageService.updateCurrentContentID(currentContentID, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "更新案卷号最大值失败");
								}
							}
						}
					}
				}
			}
			
			//更新数据库working表和批次详细表的档号信息
			if (pFlag) {
				pErrPos = 9;
				if (paperTransferBatchesDetailsDao.updateArchivesID(paperTransferBatNo, archivesType,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新档号失败");
				}
			}

			//更新此批次此分类的档案已生成状态
			if (pFlag) {
				pErrPos = 10;
				if (paperTransferBatchesArchvTypeDetailsDao.updateArchivesIDMaked( paperTransferBatNo,  archivesType.getID(),  pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新此批次此分类的档案已生成状态失败");
				}
			}
			
			//查找批次中是否有未生成档号的档案类别信息  如果没有则设置此批次的状态为审核通过
			if (pFlag) {
				pErrPos = 11;
				paperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
				if (paperTransferBatchesArchvTypeDetailsDao.findByArchivesIDMaked( paperTransferBatNo,  archivesType.getID(), paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新此批次此分类的档案已生成状态失败");
				}
			}
			
			//设置批次通过标志
			if (pFlag) {
				pErrPos = 12;
				if (paperTransferBatchesArchvTypeDetails.size() == 0) {
					if (this.checkFinish(paperTransferBatNo,archivesType,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "设置批次接收审核通过状态失败");
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean checkFinish(String paperTransferBatNo, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			pErrPos = 1;
			if (paperTransferBatchesDao.updateBatState(paperTransferBatNo, EnumPaperTransferBatchesDealStatus.接收审核完成, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"设置批次审核完成状态失败");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean paperCheckPass(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, String batNo, EnumCheckResult enumCheckResult, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//标志批次详细信息审核状态为接收审核通过
			if (pFlag) {
				if (paperTransferBatchesDetailsDao.updateReceiveCheckResult(archivesType.getID(), batNo, archivesInfo.getNBXH(), enumCheckResult, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流为接受审核完成失败");
				}
			}
			
			//更新分类信息档案数量
			if (pFlag) {
				PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
				paperTransferBatchesArchvTypeDetail.setTransferBatNo(batNo);
				paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
				if (paperTransferBatchesArchvTypeDetailsDao.updateWhithSubBat(paperTransferBatchesArchvTypeDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新分类信息下档案数量失败");
				}
			}
			
			//设置档案信息工作流为著录审核
			if (pFlag) {
				EnumWorkFlowStatus enumWorkFlowStatus = null;
				if (enumCheckResult == EnumCheckResult.业务指导室审核通过) {
					enumWorkFlowStatus = EnumWorkFlowStatus.业务指导室接收审核通过;
					if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()}, userID, enumWorkFlowStatus, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作流为接受审核完成失败");
					}
					
				} else if (enumCheckResult == EnumCheckResult.档案管理室审核通过) {
					enumWorkFlowStatus = EnumWorkFlowStatus.档案管理室接收审核通过;
					if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()}, userID, enumWorkFlowStatus, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作流为接受审核完成失败");
					}
					
					//查找这个批次中是否还有未审核的档案
					List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
					List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
					enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
					if(paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(batNo, archivesType.getID(), paperTransferBatchesDetails, enumCheckResults, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"查询指定档案管理是未审核的档案信息失败");
					}
					
					//设置批次的状态
					if (pFlag) {
						if (paperTransferBatchesDetails.size() == 0) {
							if (this.checkFinish(batNo,archivesType,pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "设置批次接收审核通过状态失败");
							}
						}
					}					
					
					//将档案信息归档到save表
					if (pFlag) {
						if (archivesInfoSavedManageService.saveArchivesInfos(archivesInfo.getNBXH(), archivesType, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "归档失败");
						}
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	@Override
	public boolean paperCheckBack(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, String backReason, String batNo, EnumCheckResult enumCheckResult, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//标志批次详细信息审核状态为接收审核通过
			if (pFlag) {
				if (paperTransferBatchesDetailsDao.updateReceiveCheckResult(archivesType.getID(), batNo, archivesInfo.getNBXH(), enumCheckResult, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流为接受审核打回失败");
				}
			}
			
			//设置档案信息工作流为著录审核
			if (pFlag) {
				EnumWorkFlowStatus enumWorkFlowStatus = null;
				if (enumCheckResult == EnumCheckResult.业务指导室审核打回) {
					enumWorkFlowStatus = EnumWorkFlowStatus.业务指导室接收审核打回;
				} else if (enumCheckResult == EnumCheckResult.档案管理室审核打回) {
					//查找这个批次中是否还有未审核的档案
					List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
					enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
					List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
					if(paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(batNo, archivesType.getID(), paperTransferBatchesDetails, enumCheckResults, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"查询指定档案管理是未审核的档案信息失败");
					}
					
					//设置批次接收审核完成
					if (pFlag) {
						if (paperTransferBatchesDetails.size() == 0) {
							if (this.checkFinish(batNo,archivesType,pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "设置批次接收审核完成状态失败");
							}
						}
					}
					
					//将档案信息从saved表复制到working表
					/*if (pFlag) {
						if (archivesInfoSavedDao.checkOutArchivesInfo(archivesType,new int []{archivesInfo.getNBXH()},pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "将档案信息从saved表复制到working表失败");
						}
					}*/
					
					//删除saved表的档案信息
				/*	if (pFlag) {
						if (archivesInfoSavedManageService.deleteByNBXH(archivesType,new int []{archivesInfo.getNBXH()},pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "删除档案信息失败");
						}
					}*/
					
					enumWorkFlowStatus = EnumWorkFlowStatus.档案管理室接收审核打回;
				}
				
				//更新分类信息档案数量
				if (pFlag) {
					PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
					paperTransferBatchesArchvTypeDetail.setTransferBatNo(batNo);
					paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
					if (paperTransferBatchesArchvTypeDetailsDao.updateWhithSubBat(paperTransferBatchesArchvTypeDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新分类信息下档案数量失败");
					}
				}
				
				//更新分类信息档案数量
				if (pFlag) {
					if (paperTransferBatchesDao.updateTransferTotal(batNo,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新批次档案总数量失败");
					}
				}
				
				//写打回原因
				if (pFlag) {	
					if (archivesInfoWorkingDao.updateArchivesBackReason(archivesInfo.getNBXH(),archivesType,backReason,pErrInfo)== false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"更新档案信息打回原因失败");
					}
				}
				
				//设置档案工作流状态
				if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()},userID,enumWorkFlowStatus, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	
	
	
	/**
	 * 档案形成部门加入移交清单      更新档案分类下档案数量情况
	 * @param paperTransferBatchesArchvTypeDetail
	 * @param pErrInfo
	 * @return
	 */
	private boolean updatePaperTransferBatchesArchvTypeDetailForOutside(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail,ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//查找
			
			if (pFlag) {
				if(paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(pPaperTransferBatchesArchvTypeDetail, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找纸质档案移交批次档案分类明细情况失败");
				}
			}
				
			if (pFlag) {
				//插入
				if (pPaperTransferBatchesArchvTypeDetail.getID() == 0) {
					if(paperTransferBatchesArchvTypeDetailsDao.add(pPaperTransferBatchesArchvTypeDetail, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"插入纸质档案移交批次档案分类明细情况失败");
					}
				}else{
					//更新
//					pPaperTransferBatchesArchvTypeDetail.setTransferTotal(pPaperTransferBatchesArchvTypeDetail.getTransferTotal()+paperTransferBatchesArchvTypeDetail.getTransferTotal());
//					pPaperTransferBatchesArchvTypeDetail.setReceiveTotal(pPaperTransferBatchesArchvTypeDetail.getReceiveTotal()+paperTransferBatchesArchvTypeDetail.getReceiveTotal());
					
					if(paperTransferBatchesArchvTypeDetailsDao.update(pPaperTransferBatchesArchvTypeDetail, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"更新纸质档案移交批次档案分类明细情况失败");
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 业务指导室加入移交清单更新档案批次分类详细信息  并返回分类详细信息
	 * @param paperTransferBatchNos
	 * @param paperTransferBatch
	 * @param paperTransferBatchesArchvTypeDetails
	 * @param pErrInfo
	 * @return
	 */
	private boolean updatePaperTransferBatchesArchvTypeDetailForInside(PaperTransferBatch paperTransferBatch,List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			//调用dao统计当前批次中所有的档案分类信息档案总数量
			if (pFlag) {
				pErrPos = 2;
				if(paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBatArchvType(paperTransferBatch, pPaperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "统计批次分类档案总数量失败");
				}
			}

			//循环批次分类详细信息，判断是否有记录，有的更新，没有则插入
			if (pFlag) {
				pErrPos = 3;
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : pPaperTransferBatchesArchvTypeDetails) {
					paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatch.getBatNo());
		
						if(paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找纸质档案移交批次档案分类明细情况失败");
						}
					
						
					if (pFlag) {
						//插入
						if (paperTransferBatchesArchvTypeDetail.getID() == 0) {
							if(paperTransferBatchesArchvTypeDetailsDao.add(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
								pFlag = false;
								pErrInfo.getContent().insert(0,"插入纸质档案移交批次档案分类明细情况失败");
							}
						}else{
							//更新
							if(paperTransferBatchesArchvTypeDetailsDao.updateWhithSubBat(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
								pFlag = false;
								pErrInfo.getContent().insert(0,"更新纸质档案移交批次档案分类明细情况失败");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0 ) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	/**
	 * 得到新的移交批次号   对档案形成部门和业务指导室均适用
	 * @param userID 创建批次的当前用户编号
	 * @param paperTransferBatch 传出的批次信息
	 * @return 处理成功返回 true 失败返回 false
	 */
	private boolean getTransferBatNo(int userID,PaperTransferBatch paperTransferBatch,ErrInfo pErrInfo){

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		IntegerEx currentNo = new IntegerEx();
		
		try {
			pErrPos = 1;
			//1、调用DAO找出当前的最大值得到批次号 序号
			if (pFlag) {
				if (currentTransferBatNoDao.findCurrentNo(currentNo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找当前批次序号最大值失败");
				}
			}
			
			//2、调用DAO更新数据库 update
			if (pFlag) {
				pErrPos = 2;
				if(currentTransferBatNoDao.update(userID,currentNo,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新当前批次序号最大值失败");
				}
			}

			//3、调用DAO查找批次号 findCurrentTransferBatNo
			if (pFlag) {
				pErrPos = 3;
				if(currentTransferBatNoDao.findCurrentTransferBatNo(userID,paperTransferBatch,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找档案批次号失败");
				}
			}

			//4、如果找不到
			if (pFlag) {
				pErrPos = 4;
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					this.getTransferBatNo(userID, paperTransferBatch, pErrInfo);
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	



	/**
	 * 业务指导室加入移交清单后设置档案工作流状态
	 * @param paperTransferBatch
	 * @param paperTransferBatchesArchvTypeDetails
	 * @param enumWorkFlowStatus
	 * @param pErrInfo
	 * @return
	 */
	private boolean setFlagForWorkFlow(PaperTransferBatch paperTransferBatch, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ArchivesType archivesType = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//循环分类设置工作流状态
			if (pFlag) {
				pErrPos = 2;
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails) {
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID());
					if(paperTransferBatchesDetailsDao.setArchivesFlagForWorkFlow(paperTransferBatch,archivesType,enumWorkFlowStatus,pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作刘失败");
					}
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean statPaperCheckBackCount(int[] userIDs, String userType,Map<Integer, ArchivesTypeEx> archivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		IntegerEx count = new IntegerEx();
		try {
			//检查依赖注入
			pErrPos = 1;
		
			pErrPos = 1;
			if (archivesInfoWorkingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案归档工作表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}
			}

			if (pFlag) {
				if (archivesTypeExs == null) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息未赋值");
				}
			}
			
		   for (ArchivesTypeEx archivesTypeEx : archivesTypeExs.values()) {
			   //调用DAO统计移交未通过的数量
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.档案管理室接收审核打回,userType,count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "统计接收审核未通过的数量失败");
					}else{
						archivesTypeEx.setPaperCheckBackArchivesInfoCount(count.getValue());
					}
			   }
		    }
				
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean archivesBoxing(List<String> barcodes,String archivesBoxBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
	
		try {
			//开始处理 1...
			pErrPos = 1;
			
	
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				//调用库房DAO类
				if(storeroomArchivesInfoDao.updateArchivesBoxBarcode(barcodes, archivesBoxBarcode, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新库房档案信息盒条码失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
	
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
	
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
	
			//销毁局部变量
			throwable = null;
		}
	
		return pFlag;
	}

	@Override
	public boolean findArchivesBoxLabel(String archivesBoxBarcode, ArchivesBoxLabel archivesBoxLabel, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<String> archivesIDs = new ArrayList<String>();
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//调用DAO得到盒内标签起止范围
//			if (pFlag) {
//				pErrPos = 2;
//				if(storeroomArchivesInfoDao.findArchivesIDByBoxBarcode(archivesBoxBarcode, archivesIDs, pErrInfo) == false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0, "查找盒内档号失败");
//				}
//			}
			
			if (pFlag) {
				archivesBoxLabel.setMinArchivesID(archivesIDs.get(0));
				for (String archivesID : archivesIDs) {
					archivesBoxLabel.setMaxArchivesID(archivesID);
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean pasteArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedManageService.findByArchivesID(archivesID, archivesType,archivesInfo,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询档案信息失败");
				}
			}
			
			if (pFlag) {
				if (archivesInfo.getNBXH() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("没有此卷档案");
				}
			}
			
			StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo();
			if (pFlag) {
				if (storeroomArchivesInfoDao.find(archivesInfo.getNBXH(), archivesType.getID(),storeroomArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询库房档案信息失败");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				if (pFlag) {
					pErrPos = 2;
					if(archivesInfoSavedManageService.updateArchivesBarcode(archivesID, archivesType,barcode,pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新档案条码失败");
					}
				}
				
				if (storeroomArchivesInfo.getNBXH() == 0) {
					if (pFlag) {
						pErrPos = 3;
						if (storeroomArchivesInfoDao.add(barcode, archivesType, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "新增库房档案信息失败");
						}
					}
				}else{
					if (pFlag) {
						pErrPos = 3;
						if (storeroomArchivesInfoDao.updateBarCode(barcode,storeroomArchivesInfo.getNBXH(), archivesType, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "更新库房档案信息条码失败");
						}
					}
				}
			}
				
			if (pFlag) {
				archivesInfo.setBarcode(barcode);
			}
//			if (pFlag) {
//				pErrPos = 4;
//				if(archivesInfoSavedManageService.findByBarcode(archivesType,barcode,archivesInfo,pErrInfo) == false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0, "查询档案信息失败");
//				}
//			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDetailsDao.findTopArchivesByBatNoWhitOutBarcode(batNo,archivesTypeID,archivesInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询档案信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}	
}
