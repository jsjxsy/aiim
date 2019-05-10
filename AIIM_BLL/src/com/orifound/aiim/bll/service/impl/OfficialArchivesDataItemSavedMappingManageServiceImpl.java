package com.orifound.aiim.bll.service.impl;

import java.util.Map;


import com.orifound.aiim.bll.service.IOfficialArchivesDataItemSavedMappingManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesDataItemSavedMappingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;

public class OfficialArchivesDataItemSavedMappingManageServiceImpl  implements
		IOfficialArchivesDataItemSavedMappingManageService {
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesDataItemSavedMappingManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialArchivesDataItemSavedMappingManageServiceImpl(IOfficialArchivesDataItemSavedMappingDao officialArchivesDataItemSavedMappingDao) {
		this.officialArchivesDataItemSavedMappingDao = officialArchivesDataItemSavedMappingDao;
	}
	
	/**
	 * ���OfficialArchivesDataItemSavedMapping��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForOfficialArchivesDataItemSavedMapping(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialArchivesDataItemSavedMappingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("OfficialArchivesDataItemSavedMapping��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	/**
	 * ���ĵ������������Ϣӳ�������ݷ��ʶ���
	 */
	private IOfficialArchivesDataItemSavedMappingDao officialArchivesDataItemSavedMappingDao = null;

	/**
	 * ��ȡ����ֵ�����ĵ������������Ϣӳ�������ݷ��ʶ���
	 * @return ���ĵ������������Ϣӳ�������ݷ��ʶ���
	 */
	public IOfficialArchivesDataItemSavedMappingDao getOfficialArchivesDataItemSavedMappingDao() {
		return officialArchivesDataItemSavedMappingDao;
	}

	/**
	 * ��������ֵ�����ĵ������������Ϣӳ�������ݷ��ʶ���
	 * @param officialArchivesDataItemSavedMappingDao ���ĵ������������Ϣӳ�������ݷ��ʶ���
	 */
	public void setOfficialArchivesDataItemSavedMappingDao(IOfficialArchivesDataItemSavedMappingDao officialArchivesDataItemSavedMappingDao) {
		this.officialArchivesDataItemSavedMappingDao = officialArchivesDataItemSavedMappingDao;
	}
	
	@Override
	public boolean deleteOfficialArchivesDataItemSavedMapping(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findOfficialArchivesDataItemSavedMappingByID(
			int pID,
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ��ѯ���еĹ��ĵ����������ӳ��
	 */
	public boolean findByArchivesTypeSavedMappingID(Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
		    //����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesDataItemSavedMapping(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialArchivesDataItemSavedMappingManageService��DAO����ע��ʧ��!");
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(officialArchivesDataItemSavedMappingDao.findByArchivesTypeSavedMappingID(archivesTypeSavedMappingID, officialArchivesDataItemSavedMappings, pErrInfo)==false){
					pFlag=false;
					pErrInfo.getContent().insert(0,"��ȡָ�����ĵ�������ӳ���ϵ��Ӧ������������Ĺ鵵��ϵӳ����Ϣʧ�ܣ�");
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
	public boolean saveOfficialArchivesDataItemSavedMapping(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOfficialArchivesDataItemSavedMapping(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
	
		return false;
	}

}
