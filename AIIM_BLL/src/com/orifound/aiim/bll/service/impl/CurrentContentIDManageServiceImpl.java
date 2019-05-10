/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.ICurrentContentIDManageService;
import com.orifound.aiim.dal.dao.ICurrentContentIDDao;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 当前案卷号信息管理服务实现类
 *
 */
public class CurrentContentIDManageServiceImpl implements ICurrentContentIDManageService
{
	
	/**
	 * 构造函数
	 */
	public CurrentContentIDManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public CurrentContentIDManageServiceImpl(ICurrentContentIDDao currentContentIDDao)
	{
		this.currentContentIDDao = currentContentIDDao;
	}
	
	/**
	 * 当前案卷号信息表的数据访问对象
	 */
	private ICurrentContentIDDao currentContentIDDao = null;

	/**
	 * 获取属性值：当前案卷号信息表的数据访问对象
	 * @return 当前案卷号信息表的数据访问对象
	 */
	public ICurrentContentIDDao getCurrentContentIDDao()
	{
		return currentContentIDDao;
	}

	/**
	 * 设置属性值：当前案卷号信息表的数据访问对象
	 * @param currentContentIDDao 当前案卷号信息表的数据访问对象
	 */
	public void setCurrentContentIDDao(ICurrentContentIDDao currentContentIDDao)
	{
		this.currentContentIDDao = currentContentIDDao;
	}
	
	/**
	 * 检查当前案卷号信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForCurrentContentID(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (currentContentIDDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("当前案卷号信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.ICurrentContentIDManageService#saveCurrentContentID(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//检查当前案卷号信息的完整性
			if (pFlag)
			{
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "当前案卷号信息不完整: ");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (currentContentIDDao.save(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存当前案卷号信息失败: ");
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * 检查当前案卷号信息的完整性
	 * @param currentContentID 当前案卷号信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查当前案卷号信息是否为空
			pErrPos = 1;
			if (currentContentID==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("当前案卷号信息非法为空。");
			}

			//检查档案全宗编号是否为空
			if (pFlag)
			{
				if (currentContentID.getArchivesIDPrefix()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案全宗编号非法为空。");
				}
				else 
				{
					if (currentContentID.getArchivesIDPrefix().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案全宗编号非法为空。");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.ICurrentContentIDManageService#updateCurrentContentID(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//检查当前案卷号信息的完整性
			if (pFlag)
			{
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "当前案卷号信息不完整: ");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (currentContentIDDao.update(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新当前案卷号信息失败: ");
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.bll.service.ICurrentContentIDManageService#findCurrentContentIDByID(java.lang.String, int, com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCurrentContentIDByPrefix(String archivesIDPrefix,  CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案全宗编号是否为空
			if (pFlag)
			{
				if (archivesIDPrefix==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案全宗编号非法为空。");
				}
				else 
				{
					if (archivesIDPrefix.length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案全宗编号非法为空。");
					}
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (currentContentIDDao.findByPrefix(archivesIDPrefix, currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取当前案卷号信息失败: ");
				}
			}
			
			if (pFlag) {
				if (currentContentID.getContentID() == 0) {
					currentContentID.setArchivesIDPrefix(archivesIDPrefix);
					currentContentID.setContentID(1);
					if (currentContentIDDao.save(currentContentID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "新增案卷号信息失败: ");
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
				if (pErrInfo.getException() != null)
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

	@Override
	public boolean findAll(List<CurrentContentID> currentContentIDs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCurrentContentID(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (currentContentIDDao.findAll(currentContentIDs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取案卷号信息失败: ");
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
				if (pErrInfo.getException() != null)
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

}
