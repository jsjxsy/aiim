package com.orifound.aiim.web.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IStudentGradeInfoManageService;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentGradeInfo;

/**
 * ������֤����DWR
 *
 */
public class ArchivesCertificateManageDWR {
	
	/**
	 * ע�� ����������Ϣ������������
	 */
	@Autowired
	private IArchivesUsePersonInfoManageService archivesUsePersonInfoManageService;
	
	/**
	 * ע��ѧ���ɼ���Ϣ�����������
	 */
	@Autowired
	private IStudentGradeInfoManageService studentGradeInfoManageService;
	
	/**
	 * ע��ϵͳ���ù���������
	 */
	@Autowired
	private IConfigManageService configManageService;
	
	/**
	 * ����������֤������ ��ѯ������������Ϣ����
	 * @param archivesUsePersonInfo	��ѯ��������������ʵ��
	 * @param request	web������� 
	 * @return List<ArchivesUsePersonInfo>
	 */
	public List<ArchivesUsePersonInfo> findArchivesUsePersonInfoByQuery(ArchivesUsePersonInfo archivesUsePersonInfo ,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<ArchivesUsePersonInfo> archivesUsePersonInfos = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				archivesUsePersonInfos = new ArrayList<ArchivesUsePersonInfo>();
				
					//���֤������=='none' 	����������ѯ
				if ("none".equals(archivesUsePersonInfo.getIDCardNo())) {
					if (archivesUsePersonInfoManageService.findByName(archivesUsePersonInfo.getName(), archivesUsePersonInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����Ψ���ֲ��ҵ�����������Ϣ�� ʧ�ܣ�");
					}
					//���֤������!='none' 	����֤�������ѯ
				} else {
					if (archivesUsePersonInfoManageService.findByIDCardNo(archivesUsePersonInfo.getIDCardNo(), archivesUsePersonInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����֤��������ҵ�����������Ϣ�� ʧ�ܣ�");
					}
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
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
		return archivesUsePersonInfos;
	}
	
	/**
	 * ���ݿγ����Ƽ�����ѡ�γ�
	 * @param XH ѧ��
	 * @param courseName �γ�����
	 * @return List<Course>
	 */
	public List<Course> findElectivesCourse(String XH, String courseName, HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<Course> electivesCourses = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ѯ��ѡ�γ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				electivesCourses = new ArrayList<Course>();
				if (studentGradeInfoManageService.findAllElectivesCourseByName(courseName, XH, "1", electivesCourses, pErrInfo) == false) {
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
		return electivesCourses;
	}
	
	/**
	 * �������빫ѡ�γ�
	 * @param XH	ѧ��
	 * @param courseNames ��ѡ�γ�����������
	 * @return �����Ƿ�ɹ�
	 */
	public List<StudentGradeInfo> saveElectivesCourse(String XH, String[] courseNames) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<StudentGradeInfo> studentGradeInfos = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				studentGradeInfos = new ArrayList<StudentGradeInfo>();
				if (studentGradeInfoManageService.saveElectivesCourse(XH, courseNames, studentGradeInfos, pErrInfo) == false) {
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
		return studentGradeInfos;
	}
	
	/**
	 * ��ȡ��֤�����ļ��ϴ��ļ���������·��
	 * @return
	 */
	public String getUploadPath(HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		String uploadPath = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ȡָ��������
			if (pFlag) {
				pErrPos = 2;
				List<Config> pConfigs = new ArrayList<Config>();
				if (configManageService.findConfigByConfigType("CertificateFilePath", pConfigs , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ�������� ʧ�ܣ�");
				}
				
				//��ȡ��֤�����ļ��ϴ��ļ���������·��
				if (pConfigs.size() >= 1) {
					uploadPath = pConfigs.get(0).getLocalPath()+"\\"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"\\";
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
		return uploadPath;
	}
	
	/**
	 * ���Entity��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (archivesUsePersonInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"����������Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (studentGradeInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"ѧ���ɼ���Ϣ���BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (configManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"ϵͳ���ù����BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
}