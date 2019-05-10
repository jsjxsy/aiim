/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��������/���ż�����ϸ������ʵ����������Ľӿڶ���
 *
 */
public interface IAppraisalPublicDetailManageService {

	/**
	 * ���һ���µĵ�������/���ż�����ϸ������ʵ����
	 * @param appraisalPublicDetail ����ӵĵ�������/���ż�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveAppraisalPublicDetail(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ�������/���ż�����ϸ������ʵ����
	 * @param appraisalPublicDetail Ҫɾ���ĵ�������/���ż�����ϸ������ʵ���࣬��Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteAppraisalPublicDetail(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�������/���ż�����ϸ������ʵ����
	 * @param appraisalPublicDetail �޸ĺ�ĵ�������/���ż�����ϸ������ʵ������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateAppraisalPublicDetail(AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

	/**
	 * �������еĵ�������/���ż�����ϸ������ʵ������Ϣ
	 * @param appraisalPublicDetails ���ز��ҳɹ��ĵ�������/���ż�����ϸ������ʵ���༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalPublicDetails(List<AppraisalPublicDetail> appraisalPublicDetails,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ�������/���ż�����ϸ������ʵ������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param PublicFlag ���ű�־
	 * @param appraisalPublicDetail ���ز��ҳɹ��ĵ�������/���ż�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalPublicDetailByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo);

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
	boolean saveBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, String isPublic, ErrInfo pErrInfo);

	/**
	 * ��������->��������	������ӵ�������������Ϣ
	 * @param userInfo 		��������Ϣ
	 * @param archivesType 	��������
	 * @param archivesNBXHs �����ĵ����ڲ���ż���
	 * @param opinion 		��������������	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param isPublic 		�Ƿ񿪷�:0δ������1����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean saveBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, String isOpen, ErrInfo pErrInfo);

	/**
	 * ��������->����/���ż����Ǽ���Ϣ  ��ҳ��ѯ����/���ż����Ǽ���Ϣ
	 * @param archivesTypeIds ָ�����������µ����е�������id
	 * @param params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd�����ű�־PublicFlag
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param appraisalPublicDetails ���ؿ��ż����Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo);

	/**
	 * ��������->ȡ������/��������		ɾ��ָ���������ͺ��ڲ���ŵĵ������ż�����Ϣ
	 * @param archivesType ��������
	 * @param NBXH 			�����ڲ����
	 * @param publicFlag 	���ű�־
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean deleteAppraisalPublicDetail(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo);
}
