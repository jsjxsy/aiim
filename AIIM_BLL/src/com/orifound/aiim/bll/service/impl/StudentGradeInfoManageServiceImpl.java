/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IStudentGradeInfoManageService;
import com.orifound.aiim.dal.dao.IStudentGradeInfoDao;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentGradeInfo;

/**
 * ѧ���ɼ���Ϣ��������ʵ����
 *
 */
public class StudentGradeInfoManageServiceImpl implements IStudentGradeInfoManageService {
	
	/**
	 * ע��ѧ���ɼ���Ϣ���DAO
	 */
	@Autowired
	private IStudentGradeInfoDao studentGradeInfoDao;
	
	/**
	 * ���캯��
	 */
	public StudentGradeInfoManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public StudentGradeInfoManageServiceImpl(IStudentGradeInfoDao studentGradeInfoDao) {
		this.studentGradeInfoDao = studentGradeInfoDao;
	}
	
	/**
	 * ���ѧ���ɼ���Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForStudentGradeInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (studentGradeInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ѧ���ɼ���Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#deleteStudentGradeInfo(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteStudentGradeInfo(StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#findStudentGradeInfoByID(int, com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStudentGradeInfoByID(int pID,
			StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#findStudentGradeInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStudentGradeInfos(
			List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#saveStudentGradeInfo(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveStudentGradeInfo(StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#updateStudentGradeInfo(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStudentGradeInfo(StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findStudentAllGrade(String XH,
			List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ѧ���Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ�ŷǷ�Ϊ�ա�");
				}
			}

			//��ѯָ��ѧ�������пγ̳ɼ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.findStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯָ��ѧ�������пγ̳ɼ���Ϣ ʧ�ܣ�");
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
	public boolean saveStudentAllGrade(String XH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ѧ���Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//����ѧ�����пγ̳ɼ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.saveStudentAllGrade(XH, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ѧ�����пγ̳ɼ���Ϣ ʧ��:");
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
	public boolean updateStudentAllGrade(String XH,
			List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ѧ���Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ�ŷǷ�Ϊ�ա�");
				}
			}

			//��������ѧ�����пγ̳ɼ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.updateStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��������ѧ�����пγ̳ɼ���Ϣ ʧ�ܣ�");
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
	public boolean findAllElectivesCourseByName(String name, String XH, String ElectivesFlag, List<Course> courses, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⹫��ѡ�޿α�־
			if (pFlag) {
				if (StringTool.checkNull(ElectivesFlag) == false) {
					pFlag = false;
					pErrInfo.getContent().append("����ѡ�޿α�־�Ƿ�Ϊ�ա�");
				}
			}

			//��ѯ��ѡ�γ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.findAllElectivesCourseByName(name, XH, ElectivesFlag, courses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ѡ�γ���Ϣ ʧ�ܣ�");
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
	public boolean saveElectivesCourse(String XH, String[] courseNames, List<StudentGradeInfo> electivesCourses, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
		
			//���ѧ��
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ѧ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//��⹫ѡ�γ���������
			if (pFlag) {
				if (courseNames==null || courseNames.length<=0) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ�γ���������Ƿ�Ϊ�ա�");
				}
			}

			//�������빫ѡ�γ�
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.saveElectivesCourse(XH, courseNames, electivesCourses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������빫ѡ�γ� ʧ�ܣ�");
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