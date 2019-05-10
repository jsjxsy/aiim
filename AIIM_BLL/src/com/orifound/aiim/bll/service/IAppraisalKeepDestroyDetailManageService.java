/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��ټ�����ϸ����������Ľӿڶ���
 *
 */
public interface IAppraisalKeepDestroyDetailManageService {

	/**
	 * ���һ���µĴ�ټ�����ϸ���
	 * @param appraisalKeepDestroyDetail ����ӵĴ�ټ�����ϸ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveAppraisalKeepDestroyDetail(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * ������Ӵ�ټ�����ϸ���
	 * @param userInfo ��������Ϣ
	 * @param archivesType ��������
	 * @param batchArchives ����������Ϣ �����ڲ���š�ֵMap���ϣ��Ƿ�����AppraisalDeletedFlag���±�������ID(NewRetentionPeriodID)
	 * @param opinion �������� ���ݲ�����	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���Ĵ�ټ�����ϸ���
	 * @param appraisalKeepDestroyDetail Ҫɾ���Ĵ�ټ�����ϸ�������Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteAppraisalKeepDestroyDetail(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĵ�ټ�����ϸ���
	 * @param appraisalKeepDestroyDetail �޸ĺ�Ĵ�ټ�����ϸ�����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateAppraisalKeepDestroyDetail(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * �������еĴ�ټ�����ϸ�����Ϣ
	 * @param appraisalKeepDestroyDetails ���ز��ҳɹ��Ĵ�ټ�����ϸ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalKeepDestroyDetails(List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���Ҵ�ټ�����ϸ�����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param appraisalKeepDestroyDetail ���ز��ҳɹ��Ĵ�ټ�����ϸ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalKeepDestroyDetailByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo);

	/**
	 * ��ҳ��ѯ��ټ����Ǽ���Ϣ
	 * @param params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param appraisalKeepDestroyDetails ���ش�ټ����Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails, ErrInfo pErrInfo);

}
