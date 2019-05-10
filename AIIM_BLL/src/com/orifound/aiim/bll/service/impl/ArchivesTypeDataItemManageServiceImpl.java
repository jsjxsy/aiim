/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService;
import com.orifound.aiim.dal.dao.IArchivesTypeDataItemDao;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类数据项管理服务实现类
 *
 */
public class ArchivesTypeDataItemManageServiceImpl implements IArchivesTypeDataItemManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesTypeDataItemManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesTypeDataItemManageServiceImpl(IArchivesTypeDataItemDao archivesTypeDataItemDao)
	{
		this.archivesTypeDataItemDao = archivesTypeDataItemDao;
	}
	
	/**
	 * 档案分类数据项字典表的数据访问对象
	 */
	private IArchivesTypeDataItemDao archivesTypeDataItemDao = null;

	/**
	 * 获取属性值：档案分类数据项字典表的数据访问对象
	 * @return 档案分类数据项字典表的数据访问对象
	 */
	public IArchivesTypeDataItemDao getArchivesTypeDataItemDao()
	{
		return archivesTypeDataItemDao;
	}

	/**
	 * 设置属性值：档案分类数据项字典表的数据访问对象
	 * @param archivesTypeDataItemDao 档案分类数据项字典表的数据访问对象
	 */
	public void setArchivesTypeDataItemDao(IArchivesTypeDataItemDao archivesTypeDataItemDao)
	{
		this.archivesTypeDataItemDao = archivesTypeDataItemDao;
	}
	
	/**
	 * 检查档案分类数据项的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesTypeDataItem(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesTypeDataItemDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类数据项的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#saveArchivesTypeDataItem(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#deleteArchivesTypeDataItem(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#updateArchivesTypeDataItem(com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#findByArchivesTypeID(Boolean, int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID, LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesTypeDataItem(pErrInfo) == false)
			{
				pFlag = false;
			}

			//检查是否指定了档案分类编号
			if (pFlag)
			{
				if (pArchivesTypeID==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类编号非法为0");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypeDataItemDao.findByArchivesTypeID(officialArchivesFlag,pArchivesTypeID, archivesTypeDataItems, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取档案分类（编号："+pArchivesTypeID+"）下定义的数据项信息失败: ");
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

		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService#findArchivesTypeDataItemByID(int, com.orifound.aiim.entity.ArchivesTypeDataItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypeDataItemByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
