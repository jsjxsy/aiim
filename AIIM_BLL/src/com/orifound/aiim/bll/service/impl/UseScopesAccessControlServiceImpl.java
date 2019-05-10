/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.commons.acservice.IUseScopesAccessControlService;

/**
 * 划控访问控制服务实现类
 *
 */
public class UseScopesAccessControlServiceImpl implements IUseScopesAccessControlService
{
	
	/**
	 * 构造函数
	 */
	public UseScopesAccessControlServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public UseScopesAccessControlServiceImpl(IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao)
	{
		this.appraisalUseScopesDetailDao = appraisalUseScopesDetailDao;
	}

	/**
	 * 划控鉴定的档案明细信息的数据访问对象
	 */
	private IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao = null;

	/**
	 * 获取属性值：划控鉴定的档案明细信息的数据访问对象
	 * @return 划控鉴定的档案明细信息的数据访问对象
	 */
	public IAppraisalUseScopesDetailDao getAppraisalUseScopesDetailDao()
	{
		return appraisalUseScopesDetailDao;
	}

	/**
	 * 设置属性值：划控鉴定的档案明细信息的数据访问对象
	 * @param AppraisalUseScopesDetailDao 划控鉴定的档案明细信息的数据访问对象
	 */
	public void setAppraisalUseScopesDetailDao(IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao)
	{
		this.appraisalUseScopesDetailDao = appraisalUseScopesDetailDao;
	}
	
	/**
	 * 检查划控鉴定的档案明细信息的DAO依赖注入（DAO Depandency Injection）
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
			if (appraisalUseScopesDetailDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("划控鉴定的档案明细信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.commons.acservice.IUseScopesAccessControlService#checkArchivesInfoUseScopesACL(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean checkArchivesInfoUseScopesACL(Object archivesType, Object archivesInfoIdentity, Object user) throws Exception
	{
		boolean checkPass=false; //ACL检查通过
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo=new ErrInfo();
		
		int pArchivesTypeID=0;
		int pNBXH=0;
		int pUserID=0;
		IntegerEx pACLCount=new IntegerEx();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//转换参数类型
			if (pFlag)
			{
				pArchivesTypeID=Integer.parseInt(String.valueOf(archivesType));
				pNBXH=Integer.parseInt(String.valueOf(archivesInfoIdentity));
				pUserID=Integer.parseInt(String.valueOf(user));
			}

			//调用DAO接口进行查询
			if (pFlag)
			{
				pErrPos = 2;
				if (appraisalUseScopesDetailDao.findCountArchivesInfoNotInUseScopesACL(pUserID, pArchivesTypeID, pNBXH, pACLCount, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询划控ACL失败: ");
				}
			}
			
			//赋予返回值
			if (pFlag)
			{
				//通过检查指定的档案是否存在于用户无权访问的划控档案列表中，如果存在则无权访问，如果不存在则可以访问
				//如果不存在，表示指定的档案还未被进行划控，或者已经划控给访问用户使用
				if (pACLCount.getValue()==0)
				{
					checkPass=true;
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
				
				//抛出异常
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
		}

		return checkPass;
	}

	@Override
	public int findCountArchivesInfoInUseScopesACL(Object archivesType, Object archivesInfoIdentity, Object user) throws Exception
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo=new ErrInfo();
		
		int pArchivesTypeID=0;
		int pNBXH=0;
		int pUserID=0;
		IntegerEx pACLCount=new IntegerEx();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//转换参数类型
			if (pFlag)
			{
				pArchivesTypeID=Integer.parseInt(String.valueOf(archivesType));
				pNBXH=Integer.parseInt(String.valueOf(archivesInfoIdentity));
				pUserID=Integer.parseInt(String.valueOf(user));
			}

			//调用DAO接口进行查询
			if (pFlag)
			{
				pErrPos = 2;
				if (appraisalUseScopesDetailDao.findCountArchivesInfoInUseScopesACL(pUserID, pArchivesTypeID, pNBXH, pACLCount, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询指定用户的划控ACL失败: ");
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
				
				//抛出异常
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
		}

		return pACLCount.getValue();
	}

}
