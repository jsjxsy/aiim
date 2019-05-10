/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IEvaluateItemManageService;
import com.orifound.aiim.dal.dao.IEvaluateItemDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateItem;

/**
 * ��Ч������Ŀ�ֵ�� ������
 * @author tyb
 *
 */
public class EvaluateItemManageServiceImpl implements IEvaluateItemManageService {
	/**
	 * ע�� �����������ݷ��ʶ���
	 */
	@Autowired
	private IEvaluateItemDao evaluateItemDao = null;
	
	/**
	 * ���캯��
	 */
	public EvaluateItemManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public EvaluateItemManageServiceImpl(IEvaluateItemDao evaluateItemDao) {
		this.evaluateItemDao = evaluateItemDao;
	}
	
	/**
	 * ��鿼�����DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForEvaluateItem(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (evaluateItemDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#deleteEvaluateItem(com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteEvaluateItem(EvaluateItem evaluateItem,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#findEvaluateItemByID(int, com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateItemByID(int pID, EvaluateItem evaluateItem,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#findEvaluateItems(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findEvaluateItems(List<EvaluateItem> evaluateItems, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForEvaluateItem(pErrInfo) == false) {
				pFlag = false;
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��鴫����� �������
				if (evaluateItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ϷǷ�Ϊ�ա�");
				}
			}
			
			//����DAO
			if (pFlag) {
				pErrPos = 3;
				//DoSomething
				if (evaluateItemDao.findAll(evaluateItems, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������еĿ�����Ŀ�ֵ���Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#saveEvaluateItem(com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveEvaluateItem(EvaluateItem evaluateItem, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IEvaluateItemManageService#updateEvaluateItem(com.orifound.aiim.entity.EvaluateItem, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateEvaluateItem(EvaluateItem evaluateItem,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
