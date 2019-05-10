/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.*;

import com.orifound.aiim.bll.service.ICheckRuleManageService;
import com.orifound.aiim.dal.dao.ICheckRuleDao;
import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 校验规则管理服务实现类
 *
 */
public class CheckRuleManageServiceImpl implements ICheckRuleManageService
{
	
	/**
	 * 构造函数
	 */
	public CheckRuleManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public CheckRuleManageServiceImpl(ICheckRuleDao checkRuleDao)
	{
		this.checkRuleDao = checkRuleDao;
	}

	/**
	 * 校验规则字典表的数据访问对象
	 */
	private ICheckRuleDao checkRuleDao = null;

	/**
	 * 获取属性值：校验规则字典表的数据访问对象
	 * @return 校验规则字典表的数据访问对象
	 */
	public ICheckRuleDao getCheckRuleDao()
	{
		return checkRuleDao;
	}

	/**
	 * 设置属性值：校验规则字典表的数据访问对象
	 * @param checkRuleDao 校验规则字典表的数据访问对象
	 */
	public void setCheckRuleDao(ICheckRuleDao checkRuleDao)
	{
		this.checkRuleDao = checkRuleDao;
	}
	
	/**
	 * 检查校验规则的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForCheckRule(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (checkRuleDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("校验规则的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#saveCheckRule(com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveCheckRule(CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#deleteCheckRule(com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteCheckRule(CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#updateCheckRule(com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCheckRule(CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#findCheckRules(java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCheckRules(Map<Integer, CheckRule> checkRules, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCheckRule(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag)
			{
				if (checkRuleDao.findAll(checkRules, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有校验规则信息失败: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICheckRuleManageService#findCheckRuleByID(int, com.orifound.aiim.entity.CheckRule, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCheckRuleByID(int pID, CheckRule checkRule, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
