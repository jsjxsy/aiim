package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.bll.service.IOfficialArchivesTypeSavedMappingManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesTypeSavedMappingDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;

public class OfficialArchivesTypeSavedMappingManageServiceImpl implements IOfficialArchivesTypeSavedMappingManageService{
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesTypeSavedMappingManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialArchivesTypeSavedMappingManageServiceImpl(IOfficialArchivesTypeSavedMappingDao officialArchivesTypeSavedMappingDao) {
		this.officialArchivesTypeSavedMappingDao = officialArchivesTypeSavedMappingDao;
	}
	
	/**
	 * ���ĵ����������Ϣӳ�������ݷ��ʶ���
	 */
	private IOfficialArchivesTypeSavedMappingDao officialArchivesTypeSavedMappingDao = null;

	/**
	 * ��ȡ����ֵ�����ĵ����������Ϣӳ�������ݷ��ʶ���
	 * @return ���ĵ����������Ϣӳ�������ݷ��ʶ���
	 */
	public IOfficialArchivesTypeSavedMappingDao getOfficialArchivesTypeSavedMappingDao() {
		return officialArchivesTypeSavedMappingDao;
	}
	
	
	/**
	 * ��������ֵ�����ĵ����������Ϣӳ�������ݷ��ʶ���
	 * @param officialArchivesTypeSavedMappingDao ���ĵ����������Ϣӳ�������ݷ��ʶ���
	 */
	public void setOfficialArchivesTypeSavedMappingDao(IOfficialArchivesTypeSavedMappingDao officialArchivesTypeSavedMappingDao) {
		this.officialArchivesTypeSavedMappingDao = officialArchivesTypeSavedMappingDao;
	}
	
	/**
	 * ���officialArchivesTypeSavedMappingDao��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForOfficialArchivesTypeSaveMapping(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialArchivesTypeSavedMappingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("officialArchivesTypeSavedMappingDao��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	@Override
	public boolean deleteOfficialArchivesTypeSavedMapping(
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findOfficialArchivesTypeSavedMappingByID(int pID,
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public 	boolean findOfficialArchivesTypeSavedMappings(Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveOfficialArchivesTypeSavedMapping(
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOfficialArchivesTypeSavedMapping(
			OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ���ݹ��ĵ������ͱ�Ų�ѯ���������ͼ���
	 */
	public boolean findArchivesTypesByOfficialArchivesTypeID(int officialArchivesTypeID, Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (this.checkDaoInjectionForOfficialArchivesTypeSaveMapping(pErrInfo)==false) {
				pFlag = false;
				pErrInfo.getContent().append("officialArchivesTypeSavedMappingDao��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(officialArchivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("�������ͱ�ŷǷ�Ϊ��.");
				}
			}
			
			//��ȡ���ĵ������͹鵵ӳ���ϵ
			if (pFlag) {
				pErrPos = 3;
				if(officialArchivesTypeSavedMappingDao.findByOfficialArchivesTypeID(officialArchivesTypeID, officialArchivesTypeSavedMappings, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("��ȡ���ĵ������͹鵵ӳ���ϵʧ�ܣ� ");
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

}
