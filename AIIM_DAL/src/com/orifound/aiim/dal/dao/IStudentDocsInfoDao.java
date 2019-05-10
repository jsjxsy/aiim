package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentInfo;

public interface IStudentDocsInfoDao {

	/**
	 *  �����ڲ���Ų���ѧ�����������ļ�
	 * @param nbxh ѧ�������ڲ����
	 * @param studentInfo ���ص�ѧ�������;����ļ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDocByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * ���¾����ļ�����״̬
	 * @param ids
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateExistsFlag(List<Integer> ids, ErrInfo pErrInfo);
	
}
