package com.orifound.aiim.bll.service.impl;

import java.util.List;


import com.orifound.aiim.bll.service.IOfficialArchivesInfoAttachedFileManageService;
import com.orifound.aiim.dal.dao.IOfficialArchivesInfoAttachedFileDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.OfficialArchivesType;

public class OfficialArchivesInfoAttachedFileManageServiceImpl 
		 implements
		IOfficialArchivesInfoAttachedFileManageService {
	/**
	 * ���ĵ���ԭ����Ϣ������ݷ��ʶ���
	 */
	private IOfficialArchivesInfoAttachedFileDao officialArchivesInfoAttachedFileDao = null;

	/**
	 * ��ȡ����ֵ�����ĵ���ԭ����Ϣ������ݷ��ʶ���
	 * @return ���ĵ���ԭ����Ϣ������ݷ��ʶ���
	 */
	public IOfficialArchivesInfoAttachedFileDao getOfficialArchivesInfoAttachedFileDao() {
		return officialArchivesInfoAttachedFileDao;
	}

	/**
	 * ��������ֵ�����ĵ���ԭ����Ϣ������ݷ��ʶ���
	 * @param officialArchivesInfoAttachedFileDao ���ĵ���ԭ����Ϣ������ݷ��ʶ���
	 */
	public void setOfficialArchivesInfoAttachedFileDao(IOfficialArchivesInfoAttachedFileDao officialArchivesInfoAttachedFileDao) {
		this.officialArchivesInfoAttachedFileDao = officialArchivesInfoAttachedFileDao;
	}
	/**
	 * ���캯��
	 */
	public OfficialArchivesInfoAttachedFileManageServiceImpl() {	

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialArchivesInfoAttachedFileManageServiceImpl(IOfficialArchivesInfoAttachedFileDao officialArchivesInfoAttachedFileDao) {
		this.officialArchivesInfoAttachedFileDao = officialArchivesInfoAttachedFileDao;
	}
	@Override
	public boolean deleteOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,
			int officialArchivesInfoAttachedFileID,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��鵵��������Ϣ
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}

			if (pFlag) {
				if (officialArchivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ����Ϣ��ŷǷ�Ϊ0");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.delete(officialArchivesType, officialArchivesInfoAttachedFileID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��ԭ�ĵ��ӵ�����Ϣʧ��");
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
	public boolean findOfficialArchivesInfoAttachedFileByID(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��鵵��������Ϣ
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(officialArchivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ�Ա�ŷǷ�Ϊ0");
			}
			
			//����dao����ԭ�ĵ����ļ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.findByID(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��IDԭ�ĵ��ӵ�����Ϣʧ��");
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
	public boolean findOfficialArchivesInfoAttachedFiles(OfficialArchivesType officialArchivesType,
			int pNBXH,
			List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//��鵵��������Ϣ
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			if (pFlag) {
				if (pNBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ�������������ڲ���ŷǷ�Ϊ0");
				}
			}
			
			if (pFlag) {
				if (officialArchivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫ���ص�ԭ�ĵ��ӵ�����Ϣ���϶���û�и�ֵ");
				}
			}
			
			//����DAO����ԭ�ĵ��ӵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.findAll(officialArchivesType, pNBXH, officialArchivesInfoAttachedFiles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ԭ�ĵ��ӵ�����Ϣʧ��");
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
	public boolean saveOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��鵵��������Ϣ
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(officialArchivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ�������ڲ���ŷǷ�Ϊ0");
			}
			
			//����dao����ԭ����Ϣ�Ƿ����
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileDao.findOfficialArchivesInfoAttachedFileByName(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ļ���ԭ�ĵ��ӵ�����Ϣʧ��");
				}
			}

			//����dao����ԭ��
			if (pFlag) {
				pErrPos = 3;
				if(officialArchivesInfoAttachedFile.getID() == 0){
					if (officialArchivesInfoAttachedFileDao.save(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���ԭ�ĵ��ӵ�����Ϣʧ��");
					}
				}else{
					pFlag = false;
					pErrInfo.getContent().append("���ļ��Ѵ��ڣ������ϴ�����ɾ��ԭ���ļ�");
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
	public boolean updateOfficialArchivesInfoAttachedFile(
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
