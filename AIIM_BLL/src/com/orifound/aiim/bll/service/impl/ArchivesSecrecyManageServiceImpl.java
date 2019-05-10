/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案密级管理服务实现类
 *
 */
public class ArchivesSecrecyManageServiceImpl implements IArchivesSecrecyManageService
{
	
	/**
	 * 构造函数
	 */
	public ArchivesSecrecyManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesSecrecyManageServiceImpl(IArchivesSecrecyDao archivesSecrecyDao)
	{
		this.archivesSecrecyDao = archivesSecrecyDao;
	}

	/**
	 * 档案密级字典表的数据访问对象
	 */
	private IArchivesSecrecyDao archivesSecrecyDao = null;

	/**
	 * 获取属性值：档案密级字典表的数据访问对象
	 * @return 档案密级字典表的数据访问对象
	 */
	public IArchivesSecrecyDao getArchivesSecrecyDao()
	{
		return archivesSecrecyDao;
	}

	/**
	 * 设置属性值：档案密级字典表的数据访问对象
	 * @param archivesSecrecyDao 档案密级字典表的数据访问对象
	 */
	public void setArchivesSecrecyDao(IArchivesSecrecyDao archivesSecrecyDao)
	{
		this.archivesSecrecyDao = archivesSecrecyDao;
	}
	
	/**
	 * 检查档案密级的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesSecrecy(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesSecrecyDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案密级的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#saveArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#deleteArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#updateArchivesSecrecy(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#findArchivesSecrecys(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesSecrecys(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesSecrecy(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (archivesSecrecyDao.findAll(archivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有档案密级信息失败: ");
				}
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

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesSecrecyManageService#findArchivesSecrecyByID(int, com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesSecrecyByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesSecrecy(pErrInfo) == false) {
				pFlag = false;
			}
			
			//判断档案密级信息是否为空
			if (pFlag) {
				if (archivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案密级信息非法为空。");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (archivesSecrecyDao.findByOpenSecrecy(archivesSecrecy, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询公开密级的档案密级数据字典信息 失败：");
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
