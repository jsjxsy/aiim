/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.StudentGradeInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ѧ���ɼ���Ϣ���DAO�ӿڶ���
 *
 */
public interface IStudentGradeInfoDao {

	/**
	 * Dao�ӿڶ��壺���ѧ���ɼ���Ϣ
	 * @param studentGradeInfo Ҫ��ӵ�ѧ���ɼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ѧ���ɼ���Ϣ
	 * @param studentGradeInfo Ҫɾ����ѧ���ɼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ѧ���ɼ���Ϣ
	 * @param studentGradeInfo Ҫ���µ�ѧ���ɼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ѧ���ɼ���Ϣ
	 * @param studentGradeInfos ���ز��ҳɹ���ѧ���ɼ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ѧ���ɼ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param studentGradeInfo ���ز��ҳɹ���ѧ���ɼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);
	
	/**
	 * ����ѧ�����пγ̳ɼ���Ϣ
	 * @param XH	ѧ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveStudentAllGrade(String XH, ErrInfo pErrInfo);
	
	/**
	 * ��ѯָ��ѧ�������пγ̳ɼ���Ϣ
	 * @param XH	ѧ��
	 * @param studentGradeInfos	���ز��ҳɹ���ѧ���ɼ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentAllGrade(String XH, List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo);
	
	/**
	 * ��������ѧ�����пγ̳ɼ���Ϣ
	 * @param XH	ѧ��
	 * @param studentGradeInfos	�������µ�ѧ���ɼ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStudentAllGrade(String XH, List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo);

	/**
	 * ��ѯ��ѡ�γ���Ϣ(�����Ѿ�ѡ��Ĺ�ѡ��)
	 * @param name �γ�����
	 * @param XH ����ѧ���Ѿ�ѡ��Ĺ�ѡ��
	 *  @param ElectivesFlag ����ѡ�޿α�־
	 * @param courses	���ز��ҳɹ��Ŀγ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllElectivesCourseByName(String name, String XH, String ElectivesFlag, List<Course> courses, ErrInfo pErrInfo);
	
	/**
	 * �������빫ѡ�γ�
	 * @param XH ѧ��
	 * @param courseNames �γ���������
	 * @param electivesCourses ���ز���ɹ��Ĺ�ѡ�γ̼�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveElectivesCourse(String XH, String[] courseNames, List<StudentGradeInfo> electivesCourses, ErrInfo pErrInfo);
}
