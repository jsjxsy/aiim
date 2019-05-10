package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesCertificateRegisterManageService;
import com.orifound.aiim.dal.dao.IArchivesCertificateRegisterDao;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DateQuerycondition;
import com.orifound.aiim.entity.ErrInfo;

public class ArchivesCertificateRegisterManageServiceImpl implements IArchivesCertificateRegisterManageService {
	/**
	 * 构造函数
	 */
	public ArchivesCertificateRegisterManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesCertificateRegisterManageServiceImpl(IArchivesCertificateRegisterDao archivesCertificateRegisterDao) {
		this.archivesCertificateRegisterDao = archivesCertificateRegisterDao;
	}
   
	/**
	 * 档案出证登记表的数据访问对象
	 */
	private IArchivesCertificateRegisterDao archivesCertificateRegisterDao = null;

	/**
	 * 获取属性值：档案出证登记表的数据访问对象
	 * @return 档案出证登记表的数据访问对象
	 */
	public IArchivesCertificateRegisterDao getArchivesCertificateRegisterDao() {
		return archivesCertificateRegisterDao;
	}

	/**
	 * 设置属性值：档案出证登记表的数据访问对象
	 * @param archivesCertificateRegisterDao 档案出证登记表的数据访问对象
	 */
	public void setArchivesCertificateRegisterDao(IArchivesCertificateRegisterDao archivesCertificateRegisterDao) {
		this.archivesCertificateRegisterDao = archivesCertificateRegisterDao;
	}
	
	/**
	 * 检查档案出证登记的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForarchivesCertificateRegisterDao(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesCertificateRegisterDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案出证登记的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	public boolean deleteArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findArchivesCertificateRegisterByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findArchivesCertificateRegisters(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArchivesCertificateRegister(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findArchivesCertificateRegistersByCondition(DateQuerycondition dateQuerycondition,
			List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForarchivesCertificateRegisterDao(pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案出证登记dao注入失败：");
				}
			}
			
			if (pFlag) {
				if (dateQuerycondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案出证登记日期查询条件非法为空！");
				}
			}
			
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateRegisterDao.findByCondition(dateQuerycondition, pArchivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询符合条件的档案出证登记信息集合失败：");
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
	public boolean findArchivesCertificateRegistersByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition, DataPageInfo dataPageInfo,
			List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForarchivesCertificateRegisterDao(pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案出证登记dao注入失败：");
				}
			}
			
			if (pFlag) {
				if (dateQuerycondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案出证登记日期查询条件非法为空！");
				}
			}
			
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateRegisterDao.findByCondition(pManagerUserID,dateQuerycondition, dataPageInfo,pArchivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询符合条件的档案出证登记信息集合失败：");
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
