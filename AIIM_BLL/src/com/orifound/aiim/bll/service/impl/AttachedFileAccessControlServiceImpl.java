/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.dal.dao.IAttachedFileUseRequestPassInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.commons.acservice.IAttachedFileAccessControlService;

/**
 * 档案原文电子文件访问控制服务实现类
 *
 */
public class AttachedFileAccessControlServiceImpl implements IAttachedFileAccessControlService
{
	/**
	 * 构造函数
	 */
	public AttachedFileAccessControlServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public AttachedFileAccessControlServiceImpl(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao)
	{
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}

	/**
	 * 档案原文申请通过信息表的数据访问对象
	 */
	private IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao = null;

	/**
	 * 获取属性值：档案原文申请通过信息表的数据访问对象
	 * @return 档案原文申请通过信息表的数据访问对象
	 */
	public IAttachedFileUseRequestPassInfoDao getAttachedFileUseRequestPassInfoDao()
	{
		return attachedFileUseRequestPassInfoDao;
	}

	/**
	 * 设置属性值：档案原文申请通过信息表的数据访问对象
	 * @param attachedFileUseRequestPassInfoDao 档案原文申请通过信息表的数据访问对象
	 */
	public void setAttachedFileUseRequestPassInfoDao(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao)
	{
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}
	
	/**
	 * 检查档案原文申请通过信息表的DAO依赖注入（DAO Depandency Injection）
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
			if (attachedFileUseRequestPassInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案原文申请通过信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.commons.acservice.IAttachedFileAccessControlService#checkArchivesAttachedFileACL(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean checkArchivesAttachedFileACL(Object archivesType, Object archivesInfoIdentity, Object user) throws Exception
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
				if (attachedFileUseRequestPassInfoDao.findCountByRequestPassInfo(pUserID, pArchivesTypeID, pNBXH, pACLCount, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询原文申请ACL失败: ");
				}
			}
			
			//赋予返回值
			if (pFlag)
			{
				//如果存在该用户申请指定档案原文通过的有效信息，则表示可以访问原文信息
				if (pACLCount.getValue()>0)
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

}
