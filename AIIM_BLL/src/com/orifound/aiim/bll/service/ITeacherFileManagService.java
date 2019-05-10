package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.TeacherDocsInfo;
import com.orifound.aiim.entity.TeacherDocsType;
import com.orifound.aiim.entity.TeacherInfo;

public interface ITeacherFileManagService {

	/**
	 * �����ְ��������Ϣ
	 * @param excel Ҫ�����Excel�ļ�����
	 * @param importType ��������
	 * @param archivesType ������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean importTeacherInfo(Excel excel, int importType, ArchivesType archivesType, IntegerEx sum, ErrInfo pErrInfo);

	/**
	 * ��������ѯ��ְ��������Ϣ
	 * @param teacherInfo ��װ��ѯ����  ���й��ʺź�����������ֵ
	 * @param archivesType �����������
	 * @param dataPageInfo ��ҳ����
	 * @param teacherInfos ���صĵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTeacherInfos(TeacherInfo teacherInfo,ArchivesType archivesType, DataPageInfo dataPageInfo,List<TeacherInfo> teacherInfos, ErrInfo pErrInfo);


	/**
	 * ��ѯ���д��鵵�Ľ�ְ��������Ϣ
	 * @param archivesType �����������
	 * @param dataPageInfo ��ҳ����
	 * @param teacherInfos ���صĵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTeacherInfosForArchiving(ArchivesType archivesType, DataPageInfo dataPageInfo,List<TeacherInfo> teacherInfos, ErrInfo pErrInfo);


	/**
	 * �����ڲ���Ų�ѯ��ְ��������Ϣ
	 * @param teacherInfo ���صĽ�ְ����Ϣ ҲҪ��Ϊ��ѯ����NBXH������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTeacherInfoByNBXH(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	/**
	 * ��ѯ���еĵ������Ϸ�����Ϣ
	 * @param teacherDocsTypes
	 * @param pErrInfo
	 * @return
	 */
	boolean findTeacherDocsType(List<TeacherDocsType> teacherDocsTypes,ErrInfo pErrInfo);

	/**
	 * ���ְ��������Ϣ����Ӿ����ļ�
	 * @param nbxh
	 * @param teacherDocsInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean addDoc(int nbxh, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	/**
	 * �����ڲ���Ų��Ҿ��ڲ�����Ϣ
	 * @param nbxh
	 * @param teacherDocsInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findTeacherDocsInfoByNBXH(int nbxh, List<TeacherDocsInfo> teacherDocsInfos, ErrInfo pErrInfo);

	/**
	 * ɾ�����ڲ�����Ϣ
	 * @param docIds
	 * @param pErrInfo
	 * @return
	 */
	boolean delDoc(int[] docIds, ErrInfo pErrInfo);

	boolean addTeacherInfo(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	boolean updateTeacherInfo(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	boolean findTeacherDocById(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	boolean updateTeacherDocInfo(TeacherDocsInfo teacherDocsInfo,ErrInfo pErrInfo);
	
	boolean setMoveOut(int nbxh, ErrInfo pErrInfo);
	/**
	 * ����ְ����Ϣ��ӵ��ⷿ������Ϣ����
	 * @param nbxh Ҫ��ӵĵ����ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addTeacherInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo);
	
	/**
	 * ���½�ְ�������Ĺ�����״̬
	 * @param nbxh Ҫ��ӵĵ����ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateTeacherInfoStatus(int nbxh,int workFlowStatus , ErrInfo pErrInfo);
	
	/**
	 * �����ڲ���Ų��Ҿ��ڲ�����Ϣ��ӡ
	 * @param nbxh
	 * @param teacherDocsInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findTeacherDocsInfoByNBXHForPrint(int nbxh, List<TeacherDocsInfo> teacherDocsInfos, ErrInfo pErrInfo);

	boolean batAddTeacherDocsInfo(List<String>  gzhList,TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

}
