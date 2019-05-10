/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.EnumMap;

import com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesInfoTableDao;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;

/**
 * 公文档案信息相关表的管理服务实现类
 *
 */
public class OfficialArchivesInfoTableManageServiceImpl implements IOfficialArchivesInfoTableManageService
{
	/**
	 * 构造函数
	 */
	public OfficialArchivesInfoTableManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialArchivesInfoTableManageServiceImpl(IOfficialArchivesInfoTableDao officialArchivesInfoTableDao)
	{
		this.officialArchivesInfoTableDao = officialArchivesInfoTableDao;
	}
	
	/**
	 * 公文档案信息相关表的数据访问对象
	 */
	private IOfficialArchivesInfoTableDao officialArchivesInfoTableDao = null;

	/**
	 * 获取属性值：公文档案信息相关表的数据访问对象
	 * @return 公文档案信息相关表的数据访问对象
	 */
	public IOfficialArchivesInfoTableDao getOfficialArchivesInfoTableDao()
	{
		return officialArchivesInfoTableDao;
	}

	/**
	 * 设置属性值：公文档案信息相关表的数据访问对象
	 * @param officialArchivesInfoTableDao 公文档案信息相关表的数据访问对象
	 */
	public void setOfficialArchivesInfoTableDao(IOfficialArchivesInfoTableDao officialArchivesInfoTableDao)
	{
		this.officialArchivesInfoTableDao = officialArchivesInfoTableDao;
	}
	
	/**
	 * 检查公文档案信息相关表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionFor(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialArchivesInfoTableDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文档案信息相关表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#saveOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#deleteOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#updateOfficialArchivesInfoTable(com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#findOfficialArchivesInfoTables(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesInfoTables(int pArchivesTypeID, EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables,
			ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionFor(pErrInfo) == false)
			{
				pFlag = false;
			}

			//检查档案分类编号是否为0
			if (pFlag)
			{
				pErrPos = 2;
				if (pArchivesTypeID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类编号非法为0");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (officialArchivesInfoTableDao.findByArchivesTypeID(pArchivesTypeID, officialArchivesInfoTables, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定公文档案分类的相关表信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService#findOfficialArchivesInfoTableByID(int, com.orifound.aiim.entity.OfficialArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesInfoTableByID(int pID, OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
