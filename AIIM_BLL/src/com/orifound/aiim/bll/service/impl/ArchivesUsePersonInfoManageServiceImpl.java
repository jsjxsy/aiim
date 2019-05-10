/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用人信息管理服务实现类
 * @author Administrator
 *
 */
public class ArchivesUsePersonInfoManageServiceImpl implements IArchivesUsePersonInfoManageService {

	/**
	 * 构造函数
	 */
	public ArchivesUsePersonInfoManageServiceImpl() {

	}
	
	/**
	 * 档案利用人信息表的数据访问对象
	 */
	private IArchivesUsePersonInfoDao archivesUsePersonInfoDao = null;

	public IArchivesUsePersonInfoDao getArchivesUsePersonInfoDao() {
		return archivesUsePersonInfoDao;
	}

	public void setArchivesUsePersonInfoDao(IArchivesUsePersonInfoDao archivesUsePersonInfoDao) {
		this.archivesUsePersonInfoDao = archivesUsePersonInfoDao;
	}
	
	/**
	 * 检查档案利用人信息表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesUsePersonInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案利用人信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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

	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#add(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
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

			//检查是否指定了档案分类编号
			if (pFlag)
			{
				if (archivesUsePersonInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案利用人未初始化。");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesUsePersonInfoDao.add(archivesUsePersonInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加档案利用人信息失败。 ");
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
		}
		return pFlag;	
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#delete(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#findByID(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
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

			//检查是否指定了档案分类编号
			if (pFlag)
			{
				if (archivesUsePersonInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案利用人未初始化。");
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesUsePersonInfoDao.findByID(archivesUsePersonInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据用户编号查询档案利用人信息失败。 ");
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
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#findByName(java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByIDCardNo(String IDCardNo, List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo) {
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
				if (archivesUsePersonInfoDao.findByIDCardNo(IDCardNo, archivesUsePersonInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据用户证件号码查询档案利用人信息失败。 ");
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
		}
		return pFlag;
	}

	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#update(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService#findByName(java.lang.String, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByName(String name, List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo) {
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

			//检查是否指定了档案分类编号
//			if (pFlag)
//			{
//				if (archivesUsePersonInfo==null)
//				{
//					pFlag = false;
//					pErrInfo.getContent().append("档案利用人未初始化。");
//				}
//			}
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesUsePersonInfoDao.findByName(name, archivesUsePersonInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据用户姓名查询档案利用人信息失败。 ");
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
		}
		return pFlag;
	}
}
