/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.LinkedHashMap;

import com.orifound.aiim.bll.service.IOfficialDocTypeManageService;
import com.orifound.aiim.dal.dao.IOfficialDocTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;

/**
 * 公文文种信息管理服务实现类
 *
 */
public class OfficialDocTypeManageServiceImpl implements IOfficialDocTypeManageService
{
	/**
	 * 构造函数
	 */
	public OfficialDocTypeManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialDocTypeManageServiceImpl(IOfficialDocTypeDao officialDocTypeDao)
	{
		this.officialDocTypeDao = officialDocTypeDao;
	}
	
	/**
	 * 公文文种字典表的数据访问对象
	 */
	private IOfficialDocTypeDao officialDocTypeDao = null;

	/**
	 * 获取属性值：公文文种字典表的数据访问对象
	 * @return 公文文种字典表的数据访问对象
	 */
	public IOfficialDocTypeDao getOfficialDocTypeDao()
	{
		return officialDocTypeDao;
	}

	/**
	 * 设置属性值：公文文种字典表的数据访问对象
	 * @param officialDocTypeDao 公文文种字典表的数据访问对象
	 */
	public void setOfficialDocTypeDao(IOfficialDocTypeDao officialDocTypeDao)
	{
		this.officialDocTypeDao = officialDocTypeDao;
	}
	
	/**
	 * 检查公文文种信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForOfficialDocType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialDocTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文文种信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#saveOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#deleteOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#updateOfficialDocType(com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#findOfficialDocTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialDocTypes(LinkedHashMap<Integer,OfficialDocType> officialDocTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialDocType(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (officialDocTypeDao.findAll(officialDocTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取公文文种信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialDocTypeManageService#findOfficialDocTypeByID(int, com.orifound.aiim.entity.OfficialDocType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialDocTypeByID(int pID, OfficialDocType officialDocType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
