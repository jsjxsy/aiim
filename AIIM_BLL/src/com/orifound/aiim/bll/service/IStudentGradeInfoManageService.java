/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.StudentGradeInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ѧ���ɼ���Ϣ��������Ľӿڶ���
 *
 */
public interface IStudentGradeInfoManageService {

	/**
	 * ���һ���µ�ѧ���ɼ���Ϣ��
	 * @param studentGradeInfo ����ӵ�ѧ���ɼ���Ϣ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveStudentGradeInfo(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����ѧ���ɼ���Ϣ��
	 * @param studentGradeInfo Ҫɾ����ѧ���ɼ���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteStudentGradeInfo(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ����ѧ���ɼ���Ϣ��
	 * @param studentGradeInfo �޸ĺ��ѧ���ɼ���Ϣ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStudentGradeInfo(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * �������е�ѧ���ɼ���Ϣ����Ϣ
	 * @param studentGradeInfos ���ز��ҳɹ���ѧ���ɼ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentGradeInfos(List<StudentGradeInfo> studentGradeInfos,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ѧ���ɼ���Ϣ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param studentGradeInfo ���ز��ҳɹ���ѧ���ɼ���Ϣ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentGradeInfoByID(int pID, StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo);
	
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