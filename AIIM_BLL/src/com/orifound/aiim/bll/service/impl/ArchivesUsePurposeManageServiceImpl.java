package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUsePurposeManageService;
import com.orifound.aiim.dal.dao.IArchivesUsePurposeDao;
import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用目的业务逻辑的实现类
 * @author Administrator
 *
 */
public class ArchivesUsePurposeManageServiceImpl implements IArchivesUsePurposeManageService {

	/**
	 * 档案利用目的表的数据访问对象
	 */
	private IArchivesUsePurposeDao archivesUsePurposeDao = null;

	/**
	 * 获取属性值：TableName表的数据访问对象
	 * @return TableName表的数据访问对象
	 */
	public IArchivesUsePurposeDao getArchivesUsePurposeDao() {
		return archivesUsePurposeDao;
	}

	/**
	 * 设置属性值：TableName表的数据访问对象
	 * @param entityDao TableName表的数据访问对象
	 */
	public void setArchivesUsePurposeDao(IArchivesUsePurposeDao archivesUsePurposeDao) {
		this.archivesUsePurposeDao = archivesUsePurposeDao;
	}
	
	
	/**
	 * 检查业务服务类和数据访问类是否注入成功（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {
	
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesUsePurposeDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案利用目的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	
	@Override
	public boolean findAllArchivesUsePurpose(List<ArchivesUsePurpose> archivesUsePurposes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesUsePurposeDao.findAll(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有档案利用目的失败: ");
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
	public boolean findByID(ArchivesUsePurpose archivesUsePurpose, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesUsePurposeDao.findByID( archivesUsePurpose, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "通过ID查找利用目的失败: ");
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

}
