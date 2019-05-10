/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * ��֤ѧ����Ϣ�������Ľӿڶ���
 *
 */
public interface ICertificateStudentManageService {

	/**
	 * ���һ���µĳ�֤ѧ����Ϣ
	 * @param certificateStudent ����ӵĳ�֤ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĳ�֤ѧ����Ϣ
	 * @param certificateStudent Ҫɾ���ĳ�֤ѧ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĳ�֤ѧ����Ϣ
	 * @param certificateStudent �޸ĺ�ĳ�֤ѧ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param archivesCertificateInfo �����ĳ�֤��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * �������еĳ�֤ѧ����Ϣ
	 * @param certificateStudents ���ز��ҳɹ��ĳ�֤ѧ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCertificateStudents(List<CertificateStudent> certificateStudents,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҳ�֤ѧ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param certificateStudent ���ز��ҳɹ��ĳ�֤ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCertificateStudentByID(String pID, CertificateStudent certificateStudent,
			ErrInfo pErrInfo);

	
	/**
	 * �������е�רҵ��Ϣ
	 * @param majors ���ز��ҳɹ���רҵ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo);
	
	/**
	 * �������е�ѧԺ��Ϣ
	 * @param colleges ���ز��ҳɹ���ѧԺ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo);
}