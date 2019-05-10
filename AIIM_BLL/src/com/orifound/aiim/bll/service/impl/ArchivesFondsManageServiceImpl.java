/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IArchivesFondsManageService;
import com.orifound.aiim.dal.dao.IArchivesFondsDao;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 全宗管理服务实现类
 *
 */
public class ArchivesFondsManageServiceImpl implements IArchivesFondsManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesFondsManageServiceImpl()
	{
	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesFondsManageServiceImpl(IArchivesFondsDao archivesFondsDao)
	{
		this.archivesFondsDao = archivesFondsDao;
	}
	
	/**
	 * DD_ArchivesFonds表的数据访问对象
	 */
	private IArchivesFondsDao archivesFondsDao = null;

	/**
	 * 获取属性值：DD_ArchivesFonds表的数据访问对象
	 * @return DD_ArchivesFonds表的数据访问对象
	 */
	public IArchivesFondsDao getArchivesFondsDao()
	{
		return archivesFondsDao;
	}

	/**
	 * 设置属性值：DD_ArchivesFonds表的数据访问对象
	 * @param archivesFondsDao DD_ArchivesFonds表的数据访问对象
	 */
	public void setArchivesFondsDao(IArchivesFondsDao archivesFondsDao)
	{
		this.archivesFondsDao = archivesFondsDao;
	}
	
	/**
	 * 检查档案全宗信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesFonds(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesFondsDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案全宗信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#findArchivesFondss(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesFondss(List<ArchivesFonds> archivesFondss, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (archivesFondsDao.findAll(archivesFondss, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询所有全宗信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#findArchivesFondsByID(java.lang.String, com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesFondsByID(int pID, ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			//检查ID是否赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (pID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗数字编号非法为空。");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (archivesFondsDao.findByID(pID, archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询指定全宗("+pID+")信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#saveArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesFonds(ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			// 检查全宗编号是否有赋值
			if (pFlag)
			{
				pErrPos = 1;
				if (archivesFonds.getCode() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗号非法为空。");
				}
				else
				{
					if (archivesFonds.getCode().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("全宗号非法为空。");
					}
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos =2;
				if (archivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗名称非法为空。");
				}
				else
				{
					if (archivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("全宗名称非法为空。");
					}
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (archivesFondsDao.save(archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("保存全宗信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#deleteArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesFonds(ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			//检查全宗编号是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗数字编号非法为空。");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (archivesFondsDao.delete(archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除全宗信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.ISystemManageService#updateArchivesFonds(com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesFonds(ArchivesFonds archivesFonds, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO依赖注入
			pErrPos = 1;
			if (checkDaoInjectionForArchivesFonds(pErrInfo)==false)
			{
				pFlag=false;
			}
			
			// 检查全宗编号是否有赋值
			{
				pErrPos = 2;
				if (archivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗数字编号非法为空。");
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos =2;
				if (archivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗名称非法为空。");
				}
				else
				{
					if (archivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("全宗名称非法为空。");
					}
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (archivesFondsDao.update(archivesFonds, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新全宗信息失败: ");
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

}
