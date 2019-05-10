package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherDocsInfo;

public interface ITeacherDocsInfoDao {

	/**
	 * 添加卷内材料
	 * @param teacherDocsInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean add(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	boolean add(List<String> gzhList, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);
	
	/**
	 * 查找指定教职工档案的卷内材料
	 * @param nbxh
	 * @param teacherDocsInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findByNBXH(int nbxh, List<TeacherDocsInfo> teacherDocsInfos,ErrInfo pErrInfo);

	/**
	 * 删除卷内材料信息
	 * @param docIds
	 * @param pErrInfo
	 * @return
	 */
	boolean delDocs(int[] docIds, ErrInfo pErrInfo);

	boolean findByID(int id, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	boolean update(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);
	
	/**
	 * 根据内部序号查找教职工
	 * @param nbxh 内部序号
	 * @param pTeacherDocsInfos 
	 * @param pErrInfo
	 * @return
	 */
	boolean findByNBXHForPrint(int nbxh, List<TeacherDocsInfo> pTeacherDocsInfos, ErrInfo pErrInfo);

	

}
