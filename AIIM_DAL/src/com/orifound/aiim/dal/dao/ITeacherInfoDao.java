package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherInfo;

public interface ITeacherInfoDao {

	boolean add(List<TeacherInfo> teacherInfos, ErrInfo pErrInfo);

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
	 * �����ڲ���Ų�ѯ��ְ��������Ϣ
	 * @param teacherInfo ���صĽ�ְ����Ϣ ҲҪ��Ϊ��ѯ����NBXH������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTeacherInfoByNBXH(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	/**
	 * ���½�ְ��������Ϣ
	 * @param teacherInfo ���صĽ�ְ����Ϣ ҲҪ��Ϊ��ѯ����NBXH������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	boolean add(TeacherInfo teacherInfo, ErrInfo pErrInfo);
}
