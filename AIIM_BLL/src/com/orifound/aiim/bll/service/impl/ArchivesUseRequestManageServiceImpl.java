package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseRequestManageService;
import com.orifound.aiim.dal.dao.IArchivesUseRequestDao;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案在线利用申请单信息业务逻辑实现类
 * @author Administrator
 *
 */
public class ArchivesUseRequestManageServiceImpl implements IArchivesUseRequestManageService {
	
	/**
	 * 构造函数
	 */
	public ArchivesUseRequestManageServiceImpl() {

	}
	
	/**
	 * 档案利用申请单信息表的数据访问对象
	 */
	private IArchivesUseRequestDao archivesUseRequestDao = null;

	/**
	 * 获取属性值：TableName表的数据访问对象
	 * @return TableName表的数据访问对象
	 */ 
	public IArchivesUseRequestDao getArchivesUseRequestDao() {
		return archivesUseRequestDao;
	}
	
	/**
	 * 设置属性值：TableName表的数据访问对象
	 * @param archivesUseRequestDao TableName表的数据访问对象
	 */
	public void setArchivesUseRequestDao(IArchivesUseRequestDao archivesUseRequestDao) {
		this.archivesUseRequestDao = archivesUseRequestDao;
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
			if (archivesUseRequestDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案利用申请单信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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

	

	@Override
	public boolean addArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
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
				if (archivesUseRequestDao.add(archivesUseRequest, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加档案利用申请单信息失败: ");
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
	public boolean deleteArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	@Override
	public boolean findArchivesUseRequestsByCondition(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition, DataPageInfo dataPageInfo,
			List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo) {
		System.out.println("safadfadf");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				String querySQL = getQuerySQL(archivesUseRequestQueryCondition);
				System.out.println("querySQL:"+querySQL);
				if (archivesUseRequestDao.findByQueryCondition(querySQL, dataPageInfo, archivesUseRequests, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据查询条件查找档案利用申请单信息失败: ");
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
System.out.println("error:"+pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findArchivesUseRequestByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	/***
	 * 根据利用查询条件构造SQL
	 * @param archivesUseRequestQueryCondition 档案利用查询条件
	 * @return querySQL 返回构造的SQL语句，以and开始 
	 */
	private String getQuerySQL(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition){
			String querySQL = "";	
		
			//申请单编号
			if(archivesUseRequestQueryCondition.getID()!=null && !"".equals(archivesUseRequestQueryCondition.getID().trim())){
				querySQL += " AND ID LIKE '" + archivesUseRequestQueryCondition.getID().trim() + "%'";
			}
			
			//证件号查询
			if (archivesUseRequestQueryCondition.getIDCardNo()!=null && !"".equals(archivesUseRequestQueryCondition.getIDCardNo().trim()) ) {
				querySQL += " AND IDCardNo LIKE '" + archivesUseRequestQueryCondition.getIDCardNo().trim() + "%'";
			}

			//利用起始时间
			if(archivesUseRequestQueryCondition.getRequestTimeBegin()!=null ){
				querySQL += " AND RequestTime >= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRequestQueryCondition.getRequestTimeBegin())   + "'";
			}
			
			//利用结束时间
			if(archivesUseRequestQueryCondition.getRequestTimeEnd()!=null ){
				querySQL += " AND RequestTime <= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRequestQueryCondition.getRequestTimeEnd() )   + "'";
			}
			
			//所属部门
			if(archivesUseRequestQueryCondition.getUserDepartment()!=null && !"".equals(archivesUseRequestQueryCondition.getUserDepartment().trim())){
				querySQL += " AND UserDepartment LIKE '%" + archivesUseRequestQueryCondition.getUserDepartment().trim() + "%'";
			}
			
			//姓名
			if(archivesUseRequestQueryCondition.getUserRealName()!=null && !"".equals(archivesUseRequestQueryCondition.getUserRealName().trim())){
				querySQL += " AND RealName LIKE '%" + archivesUseRequestQueryCondition.getUserRealName().trim() + "%'";
			}
		return querySQL;
	}


}
