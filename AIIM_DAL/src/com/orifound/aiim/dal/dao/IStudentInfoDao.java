package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentInfo;

/**
 * ѧ����Ϣ��DAO�ӿ�
 * @author Administrator
 *
 */
public interface IStudentInfoDao {

	/**
	 * �������ѧ��
	 * @param studentInfos Ҫ��ӵ�ѧ����ȥ��importDataitemsMappings
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * �������е�ѧ����Ϣ����ҳ��ʾ
	 * @param xyName ѧԺ����
	 * @param dataPageInfo ��ҳ����
	 * @param studentInfos ���ص�ѧ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean find(String xyName, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * �����ڲ���Ų���ѧ��������Ϣ
	 * @param nbxh
	 * @param studentInfo
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * ���µ���������״̬
	 * @param nbxh Ҫ���µĵ����ڲ����
	 * @param enumWorkFlowStatus ����������״̬
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateWorkFlowStatus(int nbxh, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);
	
	/**
	 * ��ѧ����Ϣ��ӵ��ⷿ������Ϣ����
	 * @param nbxh Ҫ��ӵĵ����ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addStudentInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo); 

	/**
	 * �ҳ�ѧ�����ڵ�ѧԺ
	 * @param collegeNames
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCollege(List<String> collegeNames, ErrInfo pErrInfo);

	/**
	 * ����ѧ��������ת����Ϣ
	 * @param studentInfo Ҫ���µ�ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateMoveOutInfo(StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * ����ת������Ų���ѧ����Ϣ
	 * @param moveOutId ת�������
	 * @param studentInfos ��ѯ���ص�ѧ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentInfoByMoveOutId(int moveOutId, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * ����ת�������µ���������״̬
	 * @param moveOutRegID ת������� 
	 * @param enumWorkFlowStatus ������״̬
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateWorkFlowStatusByMoveOutRegID(int moveOutRegID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * ��ѯѧ��������Ϣ
	 * @param studentInfo Ҫ��ѯ��ѧ��������Ϣ������
	 * @param enumWorkFlowStatus ָ��Ҫ��ѯ��Щ״̬��ѧ��������Ϣ
	 * @param studentInfos ���ص�ѧ��������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentInfos(StudentInfo studentInfo,List<EnumWorkFlowStatus> enumWorkFlowStatus,List<StudentInfo> studentInfos, ErrInfo pErrInfo);
}
