/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseManageService;
import com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService;
import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesUseRegisterDao;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.CurrentRequestID;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumStoreStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案利用管理服务实现类
 *
 */
public class ArchivesUseManageServiceImpl implements IArchivesUseManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesUseManageServiceImpl() {

	}
	
	/**
	 * 档案利用登记表的数据访问层
	 */
	private IArchivesUseRegisterDao archivesUseRegisterDao = null;

	public IArchivesUseRegisterDao getArchivesUseRegisterDao() {
		return archivesUseRegisterDao;
	}

	public void setArchivesUseRegisterDao(IArchivesUseRegisterDao archivesUseRegisterDao) {
		this.archivesUseRegisterDao = archivesUseRegisterDao;
	}
	
	/**
	 * 库房档案信息管理服务类
	 */
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService = null;
	
	public IStoreroomArchivesInfoManageService getStoreroomArchivesInfoManageService() {
		return storeroomArchivesInfoManageService;
	}

	public void setStoreroomArchivesInfoManageService(IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService) {
		this.storeroomArchivesInfoManageService = storeroomArchivesInfoManageService;
	}

	/**
	 * 实物档案利用出去明细管理服务类
	 */
	private IArchivesUseOutInfoManageService archivesUseOutInfoManageService = null;
	
	
	public IArchivesUseOutInfoManageService getArchivesUseOutInfoManageService() {
		return archivesUseOutInfoManageService;
	}

	public void setArchivesUseOutInfoManageService(IArchivesUseOutInfoManageService archivesUseOutInfoManageService) {
		this.archivesUseOutInfoManageService = archivesUseOutInfoManageService;
	}

	/**
	 * 检查业务服务类和数据访问类是否注入成功（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesUseRegisterDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案利用登记表的数据访问注入失败！请检查是否有进行依赖注入或赋值。");
			}
			
			if (archivesUseOutInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("实物档案利用出去明细管理服务类注入失败！请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#checkArchivesUseRequestDetail(com.orifound.aiim.entity.ArchivesUseRequestDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean checkArchivesUseRequestDetail(
			ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findExpiringArchivesUseInfos(com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findExpiringArchivesUseRegister(int dayNum,DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();	
		
		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesUseRegisterDao.findAllExpiringRegister(dayNum, dataPageInfo, archivesUseRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有将要到期利用登记信息失败: ");
					
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

	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseExpiredUserInfos(com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseExpiredUseRegister(DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesUseRegisterDao.findAllExpiredRegister(dataPageInfo, archivesUseRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有过期利用登记信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseInfoByID(int, com.orifound.aiim.entity.ArchivesUseInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfoByID(int archivesUseInfoID,
			ArchivesUseOutInfo archivesUseInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseInfos(com.orifound.aiim.entity.ArchivesUseInfoQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfos(
			ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition,
			DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseInfos,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseInfosByPersonID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfosByPersonID(int archivesUsePersonID,
			List<ArchivesUseOutInfo> archivesUseInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUsePersonInfoByPersonID(int, com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUsePersonInfoByPersonID(int archivesUsePersonID,
			ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRegister(java.lang.String, com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRegister(	ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesUseRegisterDao.findByID(archivesUseRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据利用编号查询档案利用登记信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRegisters(com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRegisters(
			ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition,
			DataPageInfo dataPageInfo,
			List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String querySQL = "";
		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//根据查询条件对象构造SQL查询条件
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseRegisterQueryCondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("利用查询条件未初始化！");
				}else{
					querySQL = getQuerySQL(archivesUseRegisterQueryCondition);
				}				
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesUseRegisterDao.findByConditions(querySQL, dataPageInfo, archivesUseRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据利用条件查询档案利用登记信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRequestDetails(com.orifound.aiim.entity.EnumCheckResult, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRequestDetails(
			EnumCheckResult enumCheckResult, DataPageInfo dataPageInfo,
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRequests(com.orifound.aiim.entity.ArchivesUseRequestQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRequests(
			ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition,
			DataPageInfo dataPageInfo,
			List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#generateArchivesUseRequestBatchNo(com.orifound.aiim.entity.CurrentRequestID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean generateArchivesUseRequestBatchNo(
			CurrentRequestID currentRequestID, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#generateFetchPaperList(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean generateFetchPaperList(
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#generateFetchPaperList(com.orifound.aiim.entity.ArchivesUseRequest, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean generateFetchPaperList(
			ArchivesUseRequest archivesUseRequest,
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#registerArchivesUse(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addArchivesUseRegister(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseRegisterDao.add(archivesUseRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加档案利用登记信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#renewArchivesByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesUseInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean renewArchivesByBarcode(int daysNum,ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口，获取此条形码所对应的档案
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoManageService.findArchivesUseOutInfoByArchivesBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据档案条形码查询利用出去明细失败: ");
				}
			}
			
			if(pFlag){
				pErrPos = 3;
				//根据
				archivesUseRegister.setID(archivesUseOutInfo.getUseRegID());
				if (archivesUseRegisterDao.findByID(archivesUseRegister, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据利用登记编号查找利用登记信息失败：");
				}else{
					archivesUseOutInfo.setArchivesUseRegister(archivesUseRegister);
				}
			}
			
			//验证档案是否可以续借
			if (pFlag) {
				pErrPos = 3;
				if(archivesUseOutInfo.getNBXH()==0){
					pFlag = false;
					pErrInfo.getContent().insert(0, "没有找到条形码'"+archivesUseOutInfo.getArchivesBarcode()+"'所指定的借档信息！");
				}else if(archivesUseOutInfo.getBorrowFlag() == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "题名为《"+archivesUseOutInfo.getTitle()+"》的档案的利用方式为查档，查档利用时不允许续借！");
				}
			}
			
			//调用业务逻辑，更新归还时间,完成续借业务
			if(pFlag){
				pErrPos = 4;	
				//构造新的归还时间
				Date date =new Date((archivesUseOutInfo.getShouldReturnDate().getTime()+(long)daysNum*24*60*60*1000));
				archivesUseOutInfo.setShouldReturnDate(date);//设置新的归还日期
				if(archivesUseOutInfoManageService.updateShouldReturnDate(archivesUseOutInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"续借：更新档案归还时间失败：");
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
System.out.println(pErrInfo.toString());
			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#returnArchivesByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesUseInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean returnArchivesByBarcode(	ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口，获取此条形码所对应的档案
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoManageService.findArchivesUseOutInfoByArchivesBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据档案条形码查询利用出去明细信息失败: ");
				}
			}
			
				
			//调用DAO接口，获取登记信息
			if(pFlag){
				pErrPos = 3;
				//根据
				archivesUseRegister.setID(archivesUseOutInfo.getUseRegID());
				if (archivesUseRegisterDao.findByID(archivesUseRegister, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据利用登记编号查找利用登记信息失败：");
				}else{
					//将获取的登记信息添加到利用明细信息中去
					archivesUseOutInfo.setArchivesUseRegister(archivesUseRegister);
				}
			}

			//调用业务逻辑，更新库房状态，将利用出去表中的记录转移到利用归还表中,完成归还业务
			if(pFlag){
				pErrPos = 4;
				if(archivesUseOutInfoManageService.returnArchivesByBarcode(archivesUseOutInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"归还档案失败：");
				}else{
					//更新库房档案信息，将指定条形码档案的馆藏状态置为可被利用状态
					StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo();
					storeroomArchivesInfo.setArchivesBarcode(archivesUseOutInfo.getArchivesBarcode());
					storeroomArchivesInfo.setStoreStatus(EnumStoreStatus.可被利用);
					storeroomArchivesInfoManageService.updateStoreStatusByArchivesBarcode(storeroomArchivesInfo, pErrInfo);
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
System.out.println(pErrInfo.toString());
			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#submitArchivesUseRequest(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ArchivesUseRequest, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean submitArchivesUseRequest(UserInfo userInfo,
			ArchivesUseRequest archivesUseRequest,
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#unregisterArchivesUse(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean unregisterArchivesUse(
			ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#unregisterArchivesUse(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean unregisterArchivesUse(
			List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/***
	 * 根据利用查询条件构造SQL
	 * @param archivesUseRegisterQueryCondition 档案利用查询条件
	 * @return querySQL 返回构造的SQL语句，以and开始 
	 */
	private String getQuerySQL(ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition){
			String querySQL = "";	
		
			//借档/查档标识
			if(archivesUseRegisterQueryCondition.getBorrowFlag()==true){
				querySQL += " AND BorrowFlag= 1";
			}else{
				querySQL += " AND BorrowFlag= 0";
			}
			
			//证件号查询
			if (archivesUseRegisterQueryCondition.getIDCardNo()!=null && !"".equals(archivesUseRegisterQueryCondition.getIDCardNo().trim()) ) {
				querySQL += " AND IDCardNo LIKE '" + archivesUseRegisterQueryCondition.getIDCardNo().trim() + "%'";
			}

			//利用起始时间
			if(archivesUseRegisterQueryCondition.getUseDateBegin()!=null ){
				querySQL += " AND UseDate >= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRegisterQueryCondition.getUseDateBegin() )   + "'";
			}
			
			//利用结束时间
			if(archivesUseRegisterQueryCondition.getUseDateEnd()!=null ){
				querySQL += " AND UseDate <= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRegisterQueryCondition.getUseDateEnd() )   + "'";
			}
			
			//所属部门
			if(archivesUseRegisterQueryCondition.getUserDepartment()!=null && !"".equals(archivesUseRegisterQueryCondition.getUserDepartment().trim())){
				querySQL += " AND Department LIKE '%" + archivesUseRegisterQueryCondition.getUserDepartment().trim() + "%'";
			}
			
			//姓名
			if(archivesUseRegisterQueryCondition.getUserRealName()!=null && !"".equals(archivesUseRegisterQueryCondition.getUserRealName().trim())){
				querySQL += " AND Name LIKE '%" + archivesUseRegisterQueryCondition.getUserRealName().trim() + "%'";
			}
		return querySQL;
	}

}
