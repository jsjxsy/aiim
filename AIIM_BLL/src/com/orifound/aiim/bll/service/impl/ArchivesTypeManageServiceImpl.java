/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类信息管理服务实现类
 *
 */
public class ArchivesTypeManageServiceImpl implements IArchivesTypeManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesTypeManageServiceImpl(IArchivesTypeDao archivesTypeDao)
	{
		this.archivesTypeDao = archivesTypeDao;
	}
	
	/**
	 * DD_ArchivesType表的数据访问对象
	 */
	private IArchivesTypeDao archivesTypeDao = null;

	/**
	 * 获取属性值：DD_ArchivesType表的数据访问对象
	 * @return DD_ArchivesType表的数据访问对象
	 */
	public IArchivesTypeDao getArchivesTypeDao()
	{
		return archivesTypeDao;
	}

	/**
	 * 设置属性值：DD_ArchivesType表的数据访问对象
	 * @param archivesTypeDao DD_ArchivesType表的数据访问对象
	 */
	public void setArchivesTypeDao(IArchivesTypeDao archivesTypeDao)
	{
		this.archivesTypeDao = archivesTypeDao;
	}
	
	
	/**
	 * 检查档案分类信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#saveArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesType(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#deleteArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesType(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#updateArchivesType(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesType(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#findArchivesTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypes(LinkedHashMap<Integer, ArchivesType> archivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口，先获取一级类目信息
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypeDao.findForLevel1(archivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取顶层档案分类（一级类目）信息失败: ");
				}
			}
			
			//调用DAO接口，递归获取每个一级类目的下级分类
			if (pFlag)
			{
				for (ArchivesType item : archivesTypes.values())
				{
					pErrPos = 3;
					LinkedHashMap<Integer, ArchivesType> childArchivesTypes = new LinkedHashMap<Integer, ArchivesType>();
					if (archivesTypeDao.findForChild(item.getID(), childArchivesTypes, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取 "+item.getFullCode()+" 的下级档案分类失败： ");
						break;
					}
					
					//查找成功后挂接到当前一级类目下
					if (pFlag)
					{
						pErrPos = 4;
						if (childArchivesTypes.size()>0)
						{
							item.setChildArchivesTypes(childArchivesTypes);
						}
					}
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
	 * @see com.orifound.aiim.bll.service.IArchivesTypeManageService#findArchivesTypeByID(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesTypeByID(int pID, ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
