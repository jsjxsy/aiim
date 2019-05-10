package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.MoveOutInfo;
import com.orifound.aiim.entity.StudentInfo;

/**
 * ѧ���������������
 * @author Administrator
 *
 */
public interface IStudentFileManageService {

	/**
	 * ����ѧ��������Ϣ
	 * @param excel Ҫ�����Excel�ļ�����
	 * @param importType ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean importStudentInfo(Excel excel, int importType,ArchivesType archivesType,IntegerEx sum, ErrInfo pErrInfo);

	/**
	 * �������е�ѧ����Ϣ����ҳ��ʾ
	 * @param xyName ѧԺ����
	 * @param dataPageInfo ��ҳ����
	 * @param studentInfos ���ص�ѧ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean find(String xyName,EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * �����ڲ���Ų��Ҿ����ļ�
	 * @param nbxh ѧ�������ڲ����
	 * @param studentInfo ���ص�ѧ����Ϣ�����;����ļ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDocByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * ��ѧ��������Ϣ��Ӿ����ļ�
	 * @param ids �����ļ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addDocs(int nbxh ,List<Integer> ids, ErrInfo pErrInfo);

	/**
	 * ת���Ǽ�
	 * @param excel
	 * @param importType ��������
	 * @param sum
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean moveOutRegister(int userId,Excel excel, int importType, ArchivesType archivesType, IntegerEx sum, ErrInfo pErrInfo);

	/**
	 * �ҳ�ѧ�����ڵ�ѧԺ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCollege(List<String> collegeNames, ErrInfo pErrInfo);

	/**
	 * ��ѯָ��״̬��ָ��ת����ʽ��ת������Ϣ
	 * @param moveOutWay ת����ʽ
	 * @param moveOutFlag ת��״̬
	 * @param moveOutInfos ���ص�ת������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMoveOutInfo(int moveOutWay, boolean moveOutFlag,ArchivesType archivesType,DataPageInfo dataPageInfo,int minNum ,int maxNum, List<MoveOutInfo> moveOutInfos, ErrInfo pErrInfo);

	/**
	 * ����ID����ת������Ϣ
	 * @param id ת�������
	 * @param moveOutInfo ���ص�ת������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findMoveOutInfoById(int id, MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * ����ת����ת����ʽ
	 * @param id ת�������
	 * @param moveOutWay ת����ʽ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateMoveOutWay(int id, int moveOutWay, ErrInfo pErrInfo);

	/**
	 * ����ת�������к�
	 * @param id ת�������
	 * @param sN ת�������к�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateSN(int id, String sN, ErrInfo pErrInfo);

	/**
	 * ��ѯѧ��������Ϣ
	 * @param studentInfo Ҫ��ѯ��ѧ��������Ϣ������
	 * @param enumWorkFlowStatus ָ��Ҫ��ѯ��Щ״̬��ѧ��������Ϣ
	 * @param studentInfos ���ص�ѧ��������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentInfos(StudentInfo studentInfo,List<EnumWorkFlowStatus> enumWorkFlowStatus, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * ���õ���������״̬
	 * @param nbxh
	 * @param enumElement
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStudentInfos(int nbxh, EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);

	/**
	 * ����ת������ѯ��ݵ���Ϣ
	 * @param ids ת�����������
	 * @param emsInfos ���صĿ�ݵ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean getEMSinfos(int[] ids, List<EMS> emsInfos, ErrInfo pErrInfo);
}
