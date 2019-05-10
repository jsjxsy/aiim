package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherDocsType;

public interface ITeacherDocsTypeDao {

	/**
	 * ��ѯ���н�ְ����������
	 * @param teacherDocsTypes
	 * @param pErrInfo
	 * @return
	 */
	boolean findAll(List<TeacherDocsType> teacherDocsTypes, ErrInfo pErrInfo);

}
