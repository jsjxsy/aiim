/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * ��֤ѧ����Ϣ���DAO�ӿڶ���
 *
 */
public interface ICertificateStudentDao {

	/**
	 * Dao�ӿڶ��壺��ӳ�֤ѧ����Ϣ
	 * @param certificateStudent Ҫ��ӵĳ�֤ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĳ�֤ѧ����Ϣ
	 * @param certificateStudent Ҫɾ���ĳ�֤ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĳ�֤ѧ����Ϣ
	 * @param certificateStudent Ҫ���µĳ�֤ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĳ�֤ѧ����Ϣ
	 * @param certificateStudents ���ز��ҳɹ��ĳ�֤ѧ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<CertificateStudent> certificateStudents, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҳ�֤ѧ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param certificateStudent ���ز��ҳɹ��ĳ�֤ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(String pID, CertificateStudent certificateStudent, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������е�רҵ��Ϣ
	 * @param majors ���ز��ҳɹ���רҵ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������е�ѧԺ��Ϣ
	 * @param colleges ���ز��ҳɹ���ѧԺ��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo);
}