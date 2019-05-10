package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherDocsType;

public interface ITeacherDocsTypeDao {

	/**
	 * 查询所有教职工材料类型
	 * @param teacherDocsTypes
	 * @param pErrInfo
	 * @return
	 */
	boolean findAll(List<TeacherDocsType> teacherDocsTypes, ErrInfo pErrInfo);

}
