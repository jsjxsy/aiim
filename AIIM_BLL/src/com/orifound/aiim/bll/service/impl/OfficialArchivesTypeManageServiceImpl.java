/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.LinkedHashMap;

import com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案分类信息管理服务
 *
 */
public class OfficialArchivesTypeManageServiceImpl implements IOfficialArchivesTypeManageService
{
	
	/**
	 * 构造函数
	 */
	public OfficialArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialArchivesTypeManageServiceImpl(IOfficialArchivesTypeDao officialArchivesTypeDao)
	{
		this.officialArchivesTypeDao = officialArchivesTypeDao;
	}
	
	/**
	 * 公文档案分类信息表的数据访问对象
	 */
	private IOfficialArchivesTypeDao officialArchivesTypeDao = null;

	/**
	 * 获取属性值：公文档案分类信息表的数据访问对象
	 * @return 公文档案分类信息表的数据访问对象
	 */
	public IOfficialArchivesTypeDao getOfficialArchivesTypeDao()
	{
		return officialArchivesTypeDao;
	}

	/**
	 * 设置属性值：公文档案分类信息表的数据访问对象
	 * @param officialArchivesTypeDao 公文档案分类信息表的数据访问对象
	 */
	public void setOfficialArchivesTypeDao(IOfficialArchivesTypeDao officialArchivesTypeDao)
	{
		this.officialArchivesTypeDao = officialArchivesTypeDao;
	}
	
	/**
	 * 检查公文档案分类字典表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialArchivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文档案分类字典表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#saveOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类名称是否为空
			if (pFlag)
			{
				if (officialArchivesType.getName()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类名称非法为空。");
				}
				else 
				{
					if (officialArchivesType.getName().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类名称非法为空。");
					}
				}
			}
			
			//检查档案分类编码是否为空
			if (pFlag)
			{
				if (officialArchivesType.getCode()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类代码非法为空。");
				}
				else 
				{
					if (officialArchivesType.getCode().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类代码非法为空。");
					}
				}
			}
			
			//检查档案分类原文存储路径是否为空
			if (pFlag)
			{
				if (officialArchivesType.getAttachedFileSavePath()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类原文存储路径非法为空。");
				}
				else 
				{
					if (officialArchivesType.getAttachedFileSavePath().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类原文存储路径非法为空。");
					}
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (officialArchivesTypeDao.save(officialArchivesType, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文档案分类信息保存失败: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#deleteOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#updateOfficialArchivesType(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#findOfficialArchivesTypes(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesTypes(LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (officialArchivesTypeDao.findAll(officialArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取公文档案分类信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService#findOfficialArchivesTypeByID(int, com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findOfficialArchivesTypeByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
