/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService;
import com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesType;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色档案分类授权管理服务实现类
 *
 */
public class RoleRightArchivesTypeManageServiceImpl implements IRoleRightArchivesTypeManageService
{
	/**
	 * 构造函数
	 */
	public RoleRightArchivesTypeManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public RoleRightArchivesTypeManageServiceImpl(IRoleRightArchivesTypeDao roleRightArchivesTypeDao)
	{
		this.roleRightArchivesTypeDao = roleRightArchivesTypeDao;
	}
	
	/**
	 * 角色档案分类授权表的数据访问对象
	 */
	private IRoleRightArchivesTypeDao roleRightArchivesTypeDao = null;

	/**
	 * 获取属性值：角色档案分类授权表的数据访问对象
	 * @return 角色档案分类授权表的数据访问对象
	 */
	public IRoleRightArchivesTypeDao getRoleRightArchivesTypeDao()
	{
		return roleRightArchivesTypeDao;
	}

	/**
	 * 设置属性值：角色档案分类授权表的数据访问对象
	 * @param roleRightArchivesTypeDao 角色档案分类授权表的数据访问对象
	 */
	public void setRoleRightArchivesTypeDao(IRoleRightArchivesTypeDao roleRightArchivesTypeDao)
	{
		this.roleRightArchivesTypeDao = roleRightArchivesTypeDao;
	}
	
	/**
	 * 检查角色档案分类授权信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForRoleRightArchivesType(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (roleRightArchivesTypeDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("角色档案分类授权信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#saveRightArchivesTypeForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypeForRole(UserRole userRole, UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#saveRightArchivesTypesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesTypesForRole(UserRole userRole, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#deleteRightArchivesTypesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesTypesForRole(UserRole userRole, List<UserRightArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesTypeManageService#findRightArchivesTypeByRolesID(int[], java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesTypeByRolesID(int[] pRoleID, LinkedHashMap<Integer, ArchivesType> archivestypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForRoleRightArchivesType(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查角色编号是否非法
			if (pFlag)
			{
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (roleRightArchivesTypeDao.findByRoleID(pRoleID, archivestypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定角色（"+pRoleID+"）的档案分类授权信息失败: ");
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

}
