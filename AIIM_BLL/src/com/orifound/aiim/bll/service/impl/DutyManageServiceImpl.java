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
 * ְ����Ϣ�����ֵ����ʵ����
 * @author tyb
 *
 */
public class DutyManageServiceImpl implements IDutyManageService {
	
	/**
	 * ע��ְ����Ϣ�����ֵ���DAO
	 */
	private IDutyDao dutyDao;
	
	/**
	 * ���캯��
	 */
	public DutyManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public DutyManageServiceImpl(IDutyDao dutyDao) {
		this.dutyDao = dutyDao;
	}
	
	/**
	 * ���ְ����Ϣ�����ֵ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForDuty(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (dutyDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ְ����Ϣ�����ֵ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForDuty(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (dutyDao.findAll(dutys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�ְ����Ϣ�����ֵ伯�� ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
