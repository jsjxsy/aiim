package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IReportPrintSettingManageService;
import com.orifound.aiim.dal.dao.IReportPrintSettingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportPrintSetting;

public class ReportPrintSettingManageServiceImpl implements IReportPrintSettingManageService{
	
	/**
	 * ���캯��
	 */
	public ReportPrintSettingManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ReportPrintSettingManageServiceImpl(IReportPrintSettingDao reportPrintSettingDao) {
		this.reportPrintSettingDao = reportPrintSettingDao;
	}
	 
	/**
	 * TableName������ݷ��ʶ���
	 */
	private IReportPrintSettingDao reportPrintSettingDao = null;

	/**
	 * ��ȡ����ֵ��TableName������ݷ��ʶ���
	 * @return TableName������ݷ��ʶ���
	 */
	public IReportPrintSettingDao getReportPrintSettingDao() {
		return reportPrintSettingDao;
	}

	/**
	 * ��������ֵ��TableName������ݷ��ʶ���
	 * @param reportPrintSettingDao TableName������ݷ��ʶ���
	 */
	public void setReportPrintSettingDao(IReportPrintSettingDao reportPrintSettingDao) {
		this.reportPrintSettingDao = reportPrintSettingDao;
	}
	 /**
	 * ���ReportPrintSetting��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForReportPrintSetting(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (reportPrintSettingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ReportPrintSetting��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	@Override
	public boolean deleteReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findReportPrintSettingByID(int pID, ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findReportPrintSettings(List<ReportPrintSetting> pReportPrintSettings, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForReportPrintSetting(pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ӡ���õ�DAO����������ע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportPrintSettingDao.findAll(pReportPrintSettings, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�����ӡ���ñ�ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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

	@Override
	public boolean saveReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
