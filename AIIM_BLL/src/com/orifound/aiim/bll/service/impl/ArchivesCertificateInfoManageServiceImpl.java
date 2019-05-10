/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������֤��ϸ����������ʵ����
 *
 */
public class ArchivesCertificateInfoManageServiceImpl implements IArchivesCertificateInfoManageService {
	/**
	 * ע�뵵����֤��ϢDAO
	 */
	@Autowired
	private IArchivesCertificateInfoDao archivesCertificateInfoDao;
	
	/**
	 * ���캯��
	 */
	public ArchivesCertificateInfoManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesCertificateInfoManageServiceImpl(IArchivesCertificateInfoDao archivesCertificateInfoDao) {
		this.archivesCertificateInfoDao = archivesCertificateInfoDao;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#deleteArchivesCertificateInfo(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesCertificateInfo(
			ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#findArchivesCertificateInfoByID(int, com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesCertificateInfoByID(int pID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				if (pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ψһ��ʶ�Ƿ�Ϊ�գ�");
				}
			}

			//����Ψһ��ʶ���ҵ�����֤��Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findByID(pID, archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ψһ��ʶ���ҵ�����֤��Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#findArchivesCertificateInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesCertificateInfos(
			List<ArchivesCertificateInfo> archivesCertificateInfos,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#findCertificateType(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCertificateTypes(List<CertificateType> certificateTypes,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//��ѯ���е�����֤����
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findCertificateTypes(certificateTypes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���е�����֤���� ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#saveArchivesCertificateInfo(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesCertificateInfo(ArchivesCertificateRegister certificateRegister, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo) {
		System.out.println("saveArchivesCertificateInfo..........");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⵵���Ǽ���Ϣ
			if (pFlag) {
				if (certificateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("�����Ǽ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�����˱�� 
			if (pFlag) {
				if (certificateRegister.getPersonID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����˱�ŷǷ�Ϊ�ա�");
				}
			}
			
			//Ӧ�ɽ��
			if (pFlag) {
				if (certificateRegister.getShouldCharge() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ӧ�ɽ��Ƿ�Ϊ�ա�");
				}
			}
			//�����˱��
			if (pFlag) {
				if (certificateRegister.getManagerUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����˱�ŷǷ�Ϊ�ա�");
				}
			}
			//��⵵����֤��ϸ
			if (pFlag) {
				if (archivesCertificateInfos==null || archivesCertificateInfos.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("������֤��ϸ�Ƿ�Ϊ�ա�");
				}
			}

			//��֤�շѵǼ�
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.save(certificateRegister, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��֤�շѵǼ� ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService#updateArchivesCertificateInfo(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesCertificateInfo(
			ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⵵����֤��Ϣ
			if (pFlag) {
				if (archivesCertificateInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("���µ�����֤��Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			//����ָ���ĵ�����֤��Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.update(archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ָ���ĵ�����֤��Ϣ ʧ�ܣ�");
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
	
	/**
	 * ���Entity��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesCertificateInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������֤��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	public boolean findArchivesCertificateInfoByCertificateRegID(int pCertificateRegID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pCertificateRegID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("��֤�ǼǱ�ŷǷ�Ϊ�գ�");
			}
			//��ѯ���е�����֤����
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findByCertificateRegID(pCertificateRegID, archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���е�����֤���� ʧ�ܣ�");
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

	

	@Override
	public boolean findArchivesCertificateRegistersByQuery(
			Map<String, String> queryString,
			List<ArchivesCertificateRegister> archivesCertificateRegisters,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⵵����֤�ǼǼ���
			if (pFlag) {
				if (archivesCertificateRegisters == null) {
					pFlag = false;
					pErrInfo.getContent().append("������֤�ǼǼ��ϷǷ�Ϊ�գ�");
				}
			}

			//����������ѯ������֤�Ǽ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findArchivesCertificateRegistersByQuery(queryString, archivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����������ѯ������֤�Ǽ���Ϣ ʧ�ܣ�");
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

	@Override
	public boolean findArchivesCertificateInfosByRegisterId(
			int certificateRegID,
			List<ArchivesCertificateInfo> archivesCertificateInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����֤�Ǽ�
			if (pFlag) {
				if (certificateRegID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("��֤�Ǽ�id�Ƿ�Ϊ�ա�");
				}
			}
			
			//��ⷵ�س�֤��Ϣ����
			if (pFlag) {
				if (archivesCertificateInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("��֤��Ϣ���ϷǷ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.findByRegisterId(certificateRegID, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݳ�֤�Ǽ�id���ҵ�����֤��Ϣ ʧ�ܣ�");
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

	@Override
	public boolean updateArchivesCertificateInfoXH(
			ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���Ψһ��ʶ
			if (pFlag) {
				if (archivesCertificateInfo.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ψһ��ʶ�Ƿ�Ϊ�ա�");
				}
			}
			
			//���ѧ��
			if (pFlag) {
				if (StringTool.checkNull(archivesCertificateInfo.getXH()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ�ŷǷ�Ϊ�ա�");
				}
			}

			//����ָ���ĵ�����֤��Ϣѧ��
			if (pFlag) {
				pErrPos = 2;
				if (archivesCertificateInfoDao.updateXH(archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ָ���ĵ�����֤��Ϣѧ�� ʧ�ܣ�");
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

}
