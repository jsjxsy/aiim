/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService;
import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.IArchivesInfoSavedDao;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoWorkingDaoImpl;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案归档信息的管理服务实现类
 *
 */
public class ArchivesInfoSavedManageServiceImpl implements IArchivesInfoSavedManageService {
	
	/**
	 *  注入档案信息归档表的DAO
	 */
	@Autowired
	private IArchivesInfoSavedDao archivesInfoSavedDao;
	
	/**
	 * 注入存毁鉴定明细情况管理服务对象
	 */
	@Autowired
	private IAppraisalKeepDestroyDetailManageService appraisalKeepDestroyDetailManageService;
	
	/**
	 * 注入档案公开/开放鉴定明细情况表的实体类管理服务对象
	 */
	@Autowired
	private IAppraisalPublicDetailManageService appraisalPublicDetailManageService;
	
	/**
	 * 
	 */
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService;
	
	/**
	 * 
	 */
	@Autowired
	private IArchivesInfoWorkingDao archivesInfoWorkingDao;
	/**
	 * 构造函数
	 */
	public ArchivesInfoSavedManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesInfoSavedManageServiceImpl(IArchivesInfoSavedDao archivesInfoSavedDao) {
		this.archivesInfoSavedDao = archivesInfoSavedDao;
	}
	
	/**
	 * 检查档案归档信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForarchivesInfoSaved(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesInfoSavedDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案归档信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoSavedManageService#queryClassifiedForAppraisal(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean queryClassifiedForAppraisal(ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息未初始化。");
				}
			}
			
			//检查查询条件并进行相关修复处理
			if (pFlag)
			{
				if (CommonUtil.checkArchivesInfoUseQueryConditions(archivesInfoQueryConditions, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "检查查询条件信息失败: ");
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (pFlag) {
					
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean queryClassifiedForSaveDestroyAppraisal(
			ArchivesType archivesType, int FormationDepartmentID,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.queryClassifiedForSaveDestroyAppraisal(archivesType, FormationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 鉴定管理->存毁鉴定：查询指定档案类型的过期档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean updateBatchRetentionPeriod(UserInfo userInfo, ArchivesType archivesType, Map<Integer[], Integer> saveArchives, List<Integer[]> destoryArchives, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				//批量添加存毁鉴定明细情况
				if (appraisalKeepDestroyDetailManageService.saveBatch(userInfo , archivesType, batchArchives, opinion, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 批量添加存毁鉴定明细情况 失败：");
				}
			}
			

			
			if (pFlag) {
				//鉴定管理->存毁鉴定登记：批量更新档案信息的保存期限
				pErrPos = 2;
				if (archivesInfoSavedDao.updateBatchRetentionPeriod(archivesType, saveArchives, destoryArchives, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 鉴定管理->存毁鉴定登记：批量更新档案信息的保存期限 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean saveArchivesInfos(int NBXH, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//先将档案信息从working表中删除
			if (pFlag) {
				if (this.deleteByNBXH(archivesType, new int[]{NBXH}, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除档案信息失败");
				}
			}
			
			//给卷内文件生成档号
			if (pFlag) {
				if (archivesInfoWorkingDao.updateSubArchivesID(NBXH, archivesType, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "给卷内文件生成档号失败: ");
				}
			}
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.save(archivesType, NBXH, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "归档失败: ");
				}
			}
			
			//将档案信息从working表中删除
			if (pFlag) {
				if (archivesInfoWorkingManageService.deleteParentAndChild(archivesType, NBXH, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除档案信息失败");
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
	public boolean queryClassifiedForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isOpen,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测用户是否为空
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息未初始化。");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//检测公开标志是否为空
			if (pFlag) {
				if (StringTool.checkNull(isOpen) == false) {
					pFlag = false;
					pErrInfo.getContent().append("开放标志非法为空。");
				}
			}
			
			//鉴定管理->公开鉴定：查询指定档案类型的档案信息
			if (pFlag) {
				pErrPos = 4;
				if (archivesInfoSavedDao.queryClassifiedForOpenAppraisal(userInfo, archivesType, archivesInfoQueryConditions,isOpen, dataPageInfo, archivesInfos, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service鉴定管理->开放鉴定：查询指定档案类型的档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean queryClassifiedForPublicAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			String isPublic, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测用户是否为空
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息未初始化。");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//检测开放标志是否为空
			if (pFlag) {
				if (StringTool.checkNull(isPublic) == false) {
					pFlag = false;
					pErrInfo.getContent().append("开放标志非法为空。");
				}
			}
			
			//鉴定管理->开放鉴定：查询指定档案类型的档案信息
			if (pFlag) {
				pErrPos = 4;
				if (archivesInfoSavedDao.queryClassifiedForPublicAppraisal(userInfo, archivesType, archivesInfoQueryConditions,isPublic, dataPageInfo, archivesInfos, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service鉴定管理->开放鉴定：查询指定档案类型的档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean updateBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测用户是否为空
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//检测开放标志是否为空
			if (pFlag) {
				if (StringTool.checkNull(isPublic) == false) {
					pFlag = false;
					pErrInfo.getContent().append("开放标志非法为空。");
				}
			}
			
			//鉴定管理->开放鉴定 批量添加档案开放鉴定信息
			if (pFlag) {
				if (appraisalPublicDetailManageService.saveBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, opinion, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定 批量添加档案开放鉴定信息 失败：");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				//鉴定管理->开放鉴定 批量更新档案开放信息
				if (archivesInfoSavedDao.updateBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 鉴定管理->开放鉴定 批量更新档案开放信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.findByBarcode( archivesType,  barcode,  archivesInfo,  pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据条码查询档案信息失败");
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
	public boolean updateArchivesBarcode(String archivesFondsID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.updateArchivesBarcode(archivesFondsID, archivesType, barcode, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据条码查询档案信息失败");
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
	public boolean updateBatchForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType, List<Integer> archivesNBXHs,
			String isPublic,Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测用户是否为空
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//检测开放标志是否为空
			if (pFlag) {
				if (StringTool.checkNull(isPublic) == false) {
					pFlag = false;
					pErrInfo.getContent().append("公开标志非法为空。");
				}
			}
			
			if (pFlag) {
				//鉴定管理->公开鉴定 批量添加档案公开鉴定信息
				if (appraisalPublicDetailManageService.saveBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, opinion, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 鉴定管理->公开鉴定 批量添加档案公开鉴定信息 失败：");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				//鉴定管理->公开鉴定 批量更新档案密级信息
				if (archivesInfoSavedDao.updateBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service 鉴定管理->公开鉴定 批量更新档案密级信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean queryClassifiedForControlAreaAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测用户是否为空
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息未初始化。");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				//鉴定管理->划控鉴定：查询指定档案类型的档案信息
				if (archivesInfoSavedDao.queryClassifiedForControlAreaAppraisal(userInfo, archivesType, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "鉴定管理->划控鉴定：查询指定档案类型的档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean findByArchivesID(String archivesID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.findByArchivesID(archivesID,archivesType, archivesInfo, pErrInfo) == false){
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

	@Override
	public boolean findByNBXH(int pNBXH, ArchivesType archivesType,
			ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测内部序号是否为空
			if (pFlag) {
				if (pNBXH<=0) {
					pFlag = false;
					pErrInfo.getContent().append("内部序号非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//根据内部序号查找档案信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.findByNBXH(pNBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据内部序号查找档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测内部序号是否为空
			if (pFlag) {
				if (nbxhs.length == 0) {
					pFlag = false;
					pErrInfo.getContent().append("内部序号非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//根据内部序号查找档案信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.deleteByNBXH(archivesType,nbxhs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除档案信息失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	public boolean savedCallBack(int[] nBXHS,UserInfo userInfo, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
	
			//将档案信息从saved表复制到working表
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.checkOutArchivesInfo(archivesType,nBXHS,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "将档案信息从saved表复制到working表失败: ");
				}
			}
			
			//删除saved表的档案信息
			if (pFlag) {
				pErrPos = 3;
				if (this.deleteByNBXH(archivesType,nBXHS,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除档案信息失败: ");
				}
			}

			
			//设置档案工作流状态
			if (pFlag) {
				pErrPos = 4;
				if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(),nBXHS ,userInfo.getUserID(),EnumWorkFlowStatus.归档信息打回修改, pErrInfo) == false) {
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

	@Override
	public boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测档案信息集合是否为空
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息集合非法为空。");
				}
			}
			
			//检测内部序号为空
			if (pFlag) {
				if (pNBXH<=0) {
					pFlag = false;
					pErrInfo.getContent().append("内部序号非法为空。");
				}
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//根据内部序号查找档案信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.findChildrenByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据内部序号查找档案信息 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
