/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * AppraisalPublicDetails(��������/����������)���DAO�ӿڶ���
 *
 */
public interface IAppraisalPublicDetailDao {

	/**
	 * Dao�ӿڶ��壺��ӵ�������/����������Ϣ
	 * @param appraisalPublicDetail Ҫ��ӵĵ�������/����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�������/����������Ϣ
	 * @param appraisalPublicDetail Ҫɾ���ĵ�������/����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�������/����������Ϣ
	 * @param appraisalPublicDetail Ҫ���µĵ�������/����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ�������/����������Ϣ
	 * @param appraisalPublicDetails ���ز��ҳɹ��ĵ�������/����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ�������/����������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param PublicFlag ���ű�־
	 * @param appraisalPublicDetail ���ز��ҳɹ��ĵ�������/����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * ��������->���ż���	������ӵ������ż�����Ϣ
	 * @param userInfo 		��������Ϣ
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param opinion 		��������������	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param isPublic 		�Ƿ񿪷�:0δ���š�1����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean saveBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, ErrInfo pErrInfo);

	/**
	 * ��������->ȡ������/��������		ɾ��ָ���������ͺ��ڲ���ŵĵ������ż�����Ϣ
	 * @param archivesType ��������
	 * @param NBXH 			�����ڲ����
	 * @param publicFlag 	���ű�־
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo);
	
	/**
	 * ��������->��������	������ӵ������ż�����Ϣ
	 * @param userInfo 		��������Ϣ
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param opinion 		��������������	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param isOpen 		�Ƿ񹫿�:0δ������1����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean saveBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺��ҳ��ѯ����/���ż����Ǽ���Ϣ
	 * @param archivesTypeIds ָ�����������µ����е�������id
	 * @param params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd�����ű�־PublicFlag
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param appraisalPublicDetails ���ؿ��ż����Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo);

}
