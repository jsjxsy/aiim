package com.orifound.aiim.bll.service.impl;

import java.util.Map;

import com.orifound.aiim.bll.service.ISystemFeatureManageService;
import com.orifound.aiim.dal.dao.ISystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

public class SystemFeatureManageServiceImpl implements ISystemFeatureManageService {
	/**
	 * 构造函数
	 */
	public SystemFeatureManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public SystemFeatureManageServiceImpl(ISystemFeatureDao systemFeatureMDao) {
		this.systemFeatureDao = systemFeatureMDao;
	}
	
	/**
	 * SystemFeature表的数据访问对象
	 */
	private ISystemFeatureDao systemFeatureDao = null;

	/**
	 * 获取属性值：SystemFeature表的数据访问对象
	 * @return SystemFeature表的数据访问对象
	 */
	public ISystemFeatureDao getSystemFeatureDao() {
		return systemFeatureDao;
	}

	/**
	 * 设置属性值：SystemFeature表的数据访问对象
	 * @param ystemFeatureDao SystemFeature表的数据访问对象
	 */
	public void setSystemFeatureDao(ISystemFeatureDao systemFeatureDao) {
		this.systemFeatureDao = systemFeatureDao;
	}
	
	/**
	 * 检查SystemFeature的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForSystemFeature(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (systemFeatureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("SystemFeature的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
		}

		return pFlag;
	}
	@Override
	public boolean deleteSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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

	@Override
	public boolean findSystemFeatureByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findSystemFeatures(Map<String,SystemFeature> pSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForSystemFeature(pErrInfo) == false)
			{
				pFlag = false;
			}
			

			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (systemFeatureDao.findAllSystemFeature(pSystemFeatures, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有系统功能授权失败: ");
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

	@Override
	public boolean saveSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSystemFeature(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
