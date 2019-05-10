/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ICertificateStudentManageService;
import com.orifound.aiim.dal.dao.ICertificateStudentDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * ��֤ѧ����Ϣ�������ʵ����
 *
 */
public class CertificateStudentManageServiceImpl implements ICertificateStudentManageService {

	/**
	 * ע���֤ѧ����Ϣ���DAO
	 */
	@Autowired
	private ICertificateStudentDao certificateStudentDao;
	
	/**
	 * ���캯��
	 */
	public CertificateStudentManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public CertificateStudentManageServiceImpl(ICertificateStudentDao certificateStudentDao) {
		this.certificateStudentDao = certificateStudentDao;
	}
	
	/**
	 * ���ѧ����Ϣ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForCertificateStudent(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (certificateStudentDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ѧ����Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#deleteCertificateStudent(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#findCertificateStudentByID(int, com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCertificateStudentByID(String pID,
			CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (StringTool.checkNull(pID) == false) {
					pFlag = false;
					pErrInfo.getContent().append("Ψһ��ʶ�Ƿ�Ϊ�ա�");
				}
			}
			
			//����Ψһ��ʶ���ҳ�֤ѧ����Ϣ
			if (pFlag) {
				if (certificateStudentDao.findByID(pID, certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ψһ��ʶ���ҳ�֤ѧ����Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#findCertificateStudents(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCertificateStudents(
			List<CertificateStudent> certificateStudents, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#saveCertificateStudent(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveCertificateStudent(
			CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ѧ����Ϣ
			if (pFlag) {
				if (certificateStudent==null || StringTool.checkNull(certificateStudent.getXH())==false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ����Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			//��ӳ�֤ѧ����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.save(certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ӳ�֤ѧ����Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#updateCertificateStudent(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ѧ����Ϣ
			if (pFlag) {
				if (certificateStudent==null || StringTool.checkNull(certificateStudent.getXH())==false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ����Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			//����ָ���ĳ�֤ѧ����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.update(certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ָ���ĳ�֤ѧ����Ϣ ʧ�ܣ�");
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
	public boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}

			//�������е�ѧԺ��Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.findAllCollege(colleges, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�ѧԺ��Ϣ ʧ�ܣ�");
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
	public boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.findAllMajor(majors, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�רҵ��Ϣ ʧ�ܣ�");
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