/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IDutyManageService;
import com.orifound.aiim.dal.dao.IDutyDao;
import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 职务信息数据字典服务实现类
 * @author tyb
 *
 */
public class DutyManageServiceImpl implements IDutyManageService {
	
	/**
	 * 注入职务信息数据字典表的DAO
	 */
	private IDutyDao dutyDao;
	
	/**
	 * 构造函数
	 */
	public DutyManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public DutyManageServiceImpl(IDutyDao dutyDao) {
		this.dutyDao = dutyDao;
	}
	
	/**
	 * 检查职务信息数据字典表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForDuty(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (dutyDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("职务信息数据字典表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDutyManageService#deleteDuty(com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteDuty(Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDutyManageService#findDutyByID(int, com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDutyByID(int pID, Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDutyManageService#findDutys(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findDutys(List<Duty> dutys, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForDuty(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (dutyDao.findAll(dutys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的职务信息数据字典集合 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDutyManageService#saveDuty(com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveDuty(Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IDutyManageService#updateDuty(com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateDuty(Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public IDutyDao getDutyDao() {
		return dutyDao;
	}

	public void setDutyDao(IDutyDao dutyDao) {
		this.dutyDao = dutyDao;
	}

}
