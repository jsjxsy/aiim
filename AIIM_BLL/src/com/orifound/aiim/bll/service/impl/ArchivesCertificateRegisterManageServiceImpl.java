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
	 * ���캯��
	 */
	public ArchivesCertificateRegisterManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesCertificateRegisterManageServiceImpl(IArchivesCertificateRegisterDao archivesCertificateRegisterDao) {
		this.archivesCertificateRegisterDao = archivesCertificateRegisterDao;
	}
   
	/**
	 * ������֤�ǼǱ�����ݷ��ʶ���
	 */
	private IArchivesCertificateRegisterDao archivesCertificateRegisterDao = null;

	/**
	 * ��ȡ����ֵ��������֤�ǼǱ�����ݷ��ʶ���
	 * @return ������֤�ǼǱ�����ݷ��ʶ���
	 */
	public IArchivesCertificateRegisterDao getArchivesCertificateRegisterDao() {
		return archivesCertificateRegisterDao;
	}

	/**
	 * ��������ֵ��������֤�ǼǱ�����ݷ��ʶ���
	 * @param archivesCertificateRegisterDao ������֤�ǼǱ�����ݷ��ʶ���
	 */
	public void setArchivesCertificateRegisterDao(IArchivesCertificateRegisterDao archivesCertificateRegisterDao) {
		this.archivesCertificateRegisterDao = archivesCertificateRegisterDao;
	}
	
	/**
	 * ��鵵����֤�Ǽǵ�DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForarchivesCertificateRegisterDao(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesCertificateRegisterDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������֤�Ǽǵ�DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForarchivesCertificateRegisterDao(pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������֤�Ǽ�daoע��ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (dateQuerycondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("������֤�Ǽ����ڲ�ѯ�����Ƿ�Ϊ�գ�");
				}
			}
			
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateRegisterDao.findByCondition(dateQuerycondition, pArchivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���������ĵ�����֤�Ǽ���Ϣ����ʧ�ܣ�");
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
	public boolean findArchivesCertificateRegistersByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition, DataPageInfo dataPageInfo,
			List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForarchivesCertificateRegisterDao(pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������֤�Ǽ�daoע��ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (dateQuerycondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("������֤�Ǽ����ڲ�ѯ�����Ƿ�Ϊ�գ�");
				}
			}
			
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateRegisterDao.findByCondition(pManagerUserID,dateQuerycondition, dataPageInfo,pArchivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���������ĵ�����֤�Ǽ���Ϣ����ʧ�ܣ�");
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
