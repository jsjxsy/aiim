/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.dal.dao.IDepartmentInfoDao;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 部门信息管理服务实现类
 *
 */
public class DepartmentInfoManageServiceImpl implements
		IDepartmentInfoManageService
{
	
	/**
	 * 构造函数
	 */
	public DepartmentInfoManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public DepartmentInfoManageServiceImpl(IDepartmentInfoDao departmentInfoDao)
	{
		this.departmentInfoDao = departmentInfoDao;
	}
	
	/**
	 * 部门信息表的数据访问对象
	 */
	private IDepartmentInfoDao departmentInfoDao = null;

	/**
	 * 获取属性值：部门信息表的数据访问对象
	 * @return 部门信息表的数据访问对象
	 */
	public IDepartmentInfoDao getDepartmentInfoDao()
	{
		return departmentInfoDao;
	}

	/**
	 * 设置属性值：部门信息表的数据访问对象
	 * @param departmentInfoDao 部门信息表的数据访问对象
	 */
	public void setDepartmentInfoDao(IDepartmentInfoDao departmentInfoDao)
	{
		this.departmentInfoDao = departmentInfoDao;
	}
	
	/**
	 * 检查部门信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForDepartmentInfo(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (departmentInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("部门信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#deleteDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDepartmentInfo(DepartmentInfo departmentInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#findDepartmentInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDepartmentInfos(List<DepartmentInfo> departmentInfos,
			ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDepartmentInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查部门信息集合是否为空
			if (pFlag) {
				if (departmentInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数:部门信息集合非法为空。");
				}
			}

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (departmentInfoDao.findAll(departmentInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取部门信息失败: ");
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
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#findDepartmentInfoByID(int, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDepartmentInfoByID(int departmentID,
			DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#saveDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDepartmentInfo(DepartmentInfo departmentInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDepartmentInfoManageService#updateDepartmentInfo(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDepartmentInfo(DepartmentInfo departmentInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findFormationDepartments(
			List<DepartmentInfo> formationDepartments, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDepartmentInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (findDepartmentInfos(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "鉴定管理->存毁鉴定登记：查找系统中所有档案形成部门信息 失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
