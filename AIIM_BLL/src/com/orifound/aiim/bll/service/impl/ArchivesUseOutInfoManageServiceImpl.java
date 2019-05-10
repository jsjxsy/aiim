/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 实物档案利用出去明细管理服务的实现类
 * @author Administrator
 *
 */
public class ArchivesUseOutInfoManageServiceImpl implements IArchivesUseOutInfoManageService {
	/**
	 * 构造函数
	 */
	public ArchivesUseOutInfoManageServiceImpl() {

	}
	
	
	/**
	 * 实物档案利用出去明细表的数据访问对象
	 */
	private IArchivesUseOutInfoDao archivesUseOutInfoDao = null;

	/**
	 * 获取属性值：实物档案利用出去明细表的数据访问对象
	 * @return 实物档案利用出去明细表的数据访问对象
	 */
	public IArchivesUseOutInfoDao getArchivesUseOutInfoDao() {
		return archivesUseOutInfoDao;
	}

	/**
	 * 设置属性值：TableName表的数据访问对象
	 * @param ArchivesUseOutInfoDao TableName表的数据访问对象
	 */
	public void setArchivesUseOutInfoDao(IArchivesUseOutInfoDao archivesUseOutInfoDao) {
		this.archivesUseOutInfoDao = archivesUseOutInfoDao;
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
		
		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesUseOutInfoDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("实物档案利用出去明细表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#addArchivesUseOutInfo(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
				if (archivesUseOutInfoDao.add(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "实物档案利用出去明细失败： ");
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
	public boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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

			//调用DAO接口:归还实物档案
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoDao.returnArchivesByBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "实物档案利用出去明细失败： ");
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
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#deleteArchivesUseOutInfo(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#findArchivesUseOutInfoByID(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfoByID(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public boolean findArchivesUseOutInfoByArchivesBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
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
			
			if(archivesUseOutInfo!=null && "".equals(archivesUseOutInfo.getArchivesBarcode())){
				pFlag = false;
				pErrInfo.getContent().append("实物档案利用出去明细对象中的档案条形码未赋值。");
			}
			

			//调用DAO接口,根据档案条形码查询利用出去明细信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoDao.findByArchivesBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO:根据档案条形码查询所有利用出去明细信息失败: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean findArchivesUseOutInfosByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
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
				if (archivesUseOutInfoDao.findByRegisterID(registerID, archivesUseOutInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO根据利用登记编号查询所有利用出去明细列表失败: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#findArchivesUseOutInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfosByQueryCondition(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition, DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
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
			
			//调用DAO接口
			if (pFlag) {
				pErrPos = 2;
				//将查询条件对象转化成SQL条件
				dataPageInfo.setPageSize(15);
				querySQL = getQuerySQL(archivesUseInfoQueryCondition);
System.out.println("querySQL:"+querySQL);
				if (archivesUseOutInfoDao.findByQueryCondition(querySQL, dataPageInfo, archivesUseOutInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO根据利用登记编号查询所有利用出去明细列表失败: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#updateArchivesUseOutInfo(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
			
			if("".equals(archivesUseOutInfo.getArchivesBarcode())){
				pFlag = false;
				pErrInfo.getContent().append("档案条形码非法为空");
			} 

			//调用DAO接口
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoDao.updateShouldReturnDate(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据档案条形码更新档案归还时间失败: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	

	/***
	 * 根据利用查询条件构造SQL
	 * @param archivesUseRegisterQueryCondition 档案利用查询条件
	 * @return querySQL 返回构造的SQL语句，以and开始 
	 */
	private String getQuerySQL(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition){
			String querySQL = "";	
		//	AND Name LIKE '%涛%' 
		//  AND IDCardNo LIKE '33%'
		//	AND ArchivesID LIKE 'G01%' 
		//	AND Title LIKE '%T%' 
		//	AND Department LIKE '%测%' 
		//	AND A.BorrowFlag=1 
		//	AND PurposeID=4 
		//	AND UseDate>'2010-5-5' 
		//	AND UseDate<'2010-8-5'		
			System.out.println("useWay"+archivesUseInfoQueryCondition.getUseWayID());
			//全部/借档/查档判断
			if(archivesUseInfoQueryCondition.getUseWayID()==1){//借档
				querySQL += " AND C.BorrowFlag = 1 ";
			}else if(archivesUseInfoQueryCondition.getUseWayID()==2){//查档
				querySQL += " AND C.BorrowFlag = 0 ";
			}
			System.out.println("useWay"+archivesUseInfoQueryCondition.getUseWayID());
			
			//证件号查询
			if (archivesUseInfoQueryCondition.getIDCardNo()!=null && !"".equals(archivesUseInfoQueryCondition.getIDCardNo().trim()) ) {
				querySQL += " AND IDCardNo LIKE '" + archivesUseInfoQueryCondition.getIDCardNo().trim() + "%'";
			}

			//利用起始时间
			if(archivesUseInfoQueryCondition.getUseDateBegin()!=null ){
				querySQL += " AND UseDate >= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseInfoQueryCondition.getUseDateBegin() )   + "'";
			}
			
			//利用结束时间
			if(archivesUseInfoQueryCondition.getUseDateEnd()!=null ){
				querySQL += " AND UseDate <= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseInfoQueryCondition.getUseDateEnd() )   + "'";
			}
			
			//所属部门
			if(archivesUseInfoQueryCondition.getUserDepartment()!=null && !"".equals(archivesUseInfoQueryCondition.getUserDepartment().trim())){
				querySQL += " AND Department LIKE '%" + archivesUseInfoQueryCondition.getUserDepartment().trim() + "%'";
			}
			
			//姓名
			if(archivesUseInfoQueryCondition.getUserRealName()!=null && !"".equals(archivesUseInfoQueryCondition.getUserRealName().trim())){
				querySQL += " AND Name LIKE '%" + archivesUseInfoQueryCondition.getUserRealName().trim() + "%'";
			}
			
			//档号
			if(archivesUseInfoQueryCondition.getArchivesID()!=null && !"".equals(archivesUseInfoQueryCondition.getArchivesID().trim())){
				querySQL += " AND ArchivesID LIKE '" + archivesUseInfoQueryCondition.getArchivesID().trim() + "%'";
			}
			
			//题名
			if(archivesUseInfoQueryCondition.getTitle()!=null && !"".equals(archivesUseInfoQueryCondition.getTitle().trim())){
				querySQL += " AND Title LIKE '%" + archivesUseInfoQueryCondition.getTitle().trim() + "%'";
			}
			
			//利用目的
			if(archivesUseInfoQueryCondition.getPurposeID()!=0){
				querySQL += " AND PurposeID = " + archivesUseInfoQueryCondition.getPurposeID();
			}
			
		return querySQL;
	}


}
