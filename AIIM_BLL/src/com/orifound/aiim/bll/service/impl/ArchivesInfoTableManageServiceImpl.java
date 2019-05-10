/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesInfoTableManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoTableDao;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案信息相关表管理服务实现类
 *
 */
public class ArchivesInfoTableManageServiceImpl implements IArchivesInfoTableManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesInfoTableManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesInfoTableManageServiceImpl(IArchivesInfoTableDao archivesInfoTableDao)
	{
		this.archivesInfoTableDao = archivesInfoTableDao;
	}
	
	/**
	 * DD_ArchivesInfoTable表的数据访问对象
	 */
	private IArchivesInfoTableDao archivesInfoTableDao = null;

	/**
	 * 获取属性值：DD_ArchivesInfoTable表的数据访问对象
	 * @return DD_ArchivesInfoTable表的数据访问对象
	 */
	public IArchivesInfoTableDao getArchivesInfoTableDao()
	{
		return archivesInfoTableDao;
	}

	/**
	 * 设置属性值：DD_ArchivesInfoTable表的数据访问对象
	 * @param archivesInfoTableDao DD_ArchivesInfoTable表的数据访问对象
	 */
	public void setArchivesInfoTableDao(IArchivesInfoTableDao archivesInfoTableDao)
	{
		this.archivesInfoTableDao = archivesInfoTableDao;
	}
	
	
	/**
	 * 检查档案信息相关表定义字典表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesInfoTable(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesInfoTableDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息相关表定义字典表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#findArchivesInfoTableByID(int, com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoTableByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#findArchivesTypeTables(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypeTables(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoTable(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类编号是否非法为空
			if (pFlag)
			{
				pErrPos = 2;
				if (pArchivesTypeID==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案编号非法为0");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfoTableDao.findByArchivesTypeID(pArchivesTypeID, archivesInfoTables, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取档案分类相关信息表失败: ");
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#saveArchivesInfoTable(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#setCreatedFlag(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#deleteArchivesTypeTables(int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesTypeTables(int pArchivesTypeID, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoTableManageService#updateArchivesInfoTable(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
