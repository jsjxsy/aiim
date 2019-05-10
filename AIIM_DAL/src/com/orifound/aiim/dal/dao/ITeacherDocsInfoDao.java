package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherDocsInfo;

public interface ITeacherDocsInfoDao {

	/**
	 * ��Ӿ��ڲ���
	 * @param teacherDocsInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean add(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	boolean add(List<String> gzhList, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);
	
	/**
	 * ����ָ����ְ�������ľ��ڲ���
	 * @param nbxh
	 * @param teacherDocsInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findByNBXH(int nbxh, List<TeacherDocsInfo> teacherDocsInfos,ErrInfo pErrInfo);

	/**
	 * ɾ�����ڲ�����Ϣ
	 * @param docIds
	 * @param pErrInfo
	 * @return
	 */
	boolean delDocs(int[] docIds, ErrInfo pErrInfo);

	boolean findByID(int id, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	boolean update(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);
	
	/**
	 * �����ڲ���Ų��ҽ�ְ��
	 * @param nbxh �ڲ����
	 * @param pTeacherDocsInfos 
	 * @param pErrInfo
	 * @return
	 */
	boolean findByNBXHForPrint(int nbxh, List<TeacherDocsInfo> pTeacherDocsInfos, ErrInfo pErrInfo);

	

}
