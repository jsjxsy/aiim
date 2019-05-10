/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService;
import com.orifound.aiim.dal.dao.IRoleRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色档案密级授权管理服务实现类
 *
 */
public class RoleRightArchivesSecrecyManageServiceImpl implements IRoleRightArchivesSecrecyManageService
{
	/**
	 * 构造函数
	 */
	public RoleRightArchivesSecrecyManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public RoleRightArchivesSecrecyManageServiceImpl(IRoleRightArchivesSecrecyDao roleRightArchivesSecrecyDao)
	{
		this.roleRightArchivesSecrecyDao = roleRightArchivesSecrecyDao;
	}
	
	/**
	 * 角色档案密级授权表的数据访问对象
	 */
	private IRoleRightArchivesSecrecyDao roleRightArchivesSecrecyDao = null;

	/**
	 * 获取属性值：角色档案密级授权表的数据访问对象
	 * @return 角色档案密级授权表的数据访问对象
	 */
	public IRoleRightArchivesSecrecyDao getRoleRightArchivesSecrecyDao()
	{
		return roleRightArchivesSecrecyDao;
	}

	/**
	 * 设置属性值：角色档案密级授权表的数据访问对象
	 * @param roleRightArchivesSecrecyDao 角色档案密级授权表的数据访问对象
	 */
	public void setRoleRightArchivesSecrecyDao(IRoleRightArchivesSecrecyDao roleRightArchivesSecrecyDao)
	{
		this.roleRightArchivesSecrecyDao = roleRightArchivesSecrecyDao;
	}
	
	/**
	 * 检查角色档案密级授权信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForRoleRightArchivesSecrecy(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (roleRightArchivesSecrecyDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("角色档案密级授权信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService#saveRightArchivesSecrecysForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveRightArchivesSecrecysForRole(UserRole userRole, List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService#deleteRightArchivesSecrecysForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteRightArchivesSecrecysForRole(UserRole userRole, List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IRoleRightArchivesSecrecyManageService#findRightArchivesSecrecysByRolesID(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findRightArchivesSecrecysByRolesID(int[] pRoleID, Map<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForRoleRightArchivesSecrecy(pErrInfo) == false)
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
				if (roleRightArchivesSecrecyDao.findByRoleID(pRoleID, archivesSecrecys, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取角色（编号: "+pRoleID+"）的档案密级授权信息失败: ");
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
