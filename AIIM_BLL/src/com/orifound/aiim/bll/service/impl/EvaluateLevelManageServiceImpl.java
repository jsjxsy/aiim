/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateLevelManageService;
import com.orifound.aiim.dal.dao.IEvaluateLevelDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateLevel;

/**
 * ������������������
 * @author tyb
 *
 */
public class EvaluateLevelManageServiceImpl implements IEvaluateLevelManageService {
	/**
	 * �������ֵȼ�������ݷ��ʶ���
	 */
	@Autowired
	private IEvaluateLevelDao evaluateLevelDao = null;

	/**
	 * ���캯��
	 */
	public EvaluateLevelManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public EvaluateLevelManageServiceImpl(IEvaluateLevelDao evaluateLevelDao) {
		this.evaluateLevelDao = evaluateLevelDao;
	}
	
	
	/**
	 * ��鿼�����ֵȼ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForEvaluateLevel(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (evaluateLevelDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�������ֵȼ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#deleteEvaluateLevel(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteEvaluateLevel(EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#findEvaluateLevelByID(int, com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateLevelByID(int pID, EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#findEvaluateLevels(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateLevels(List<EvaluateLevel> evaluateLevels, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateLevel(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� �������ֵȼ����� �Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateLevels == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�������ֵȼ����ϷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//����DAO ��ѯ�������ֵȼ�����
				if (evaluateLevelDao.findAll(evaluateLevels, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�������ֵȼ����� ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#saveEvaluateLevel(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveEvaluateLevel(EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateLevelManageService#updateEvaluateLevel(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateEvaluateLevel(EvaluateLevel evaluateLevel,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
