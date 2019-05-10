package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.bll.service.IAttachedFileUseRequestPassInfoManageService;
import com.orifound.aiim.dal.dao.IAttachedFileUseRequestPassInfoDao;
import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 原文利用申请通过信息业务逻辑实现类
 * @author Administrator
 *
 */
public class AttachedFileUseRequestPassInfoManageServiceImpl implements IAttachedFileUseRequestPassInfoManageService {
	/**
	 * 构造函数
	 */
	public AttachedFileUseRequestPassInfoManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public AttachedFileUseRequestPassInfoManageServiceImpl(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao) {
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}
	
	/**
	 * 原文利用申请通过信息表DAO接口
	 */
	private IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao = null;	

	public IAttachedFileUseRequestPassInfoDao getAttachedFileUseRequestPassInfoDao() {
		return attachedFileUseRequestPassInfoDao;
	}

	public void setAttachedFileUseRequestPassInfoDao(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao) {
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
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

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (attachedFileUseRequestPassInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("原文利用申请通过信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	public boolean addAttachedFileUseRequestPassInfo(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
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
				if (attachedFileUseRequestPassInfoDao.add(attachedFileUseRequestPassInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加原文申请详细信息失败: ");
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
	public boolean deleteAttachedFileUseRequestPassInfo(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAttachedFileUseRequestPassInfoByID(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findCountByRequestPassInfo(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pCount, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
