/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��ټ�����ϸ������DAO�ӿڶ���
 *
 */
public interface IAppraisalKeepDestroyDetailDao {
	
	/**
	 * ���һ���µĴ�ټ�����ϸ���
	 * @param appraisalKeepDestroyDetail ����ӵĴ�ټ�����ϸ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺������Ӵ�ټ�����ϸ���
	 * @param userInfo ��������Ϣ
	 * @param archivesType ��������
	 * @param batchArchives ����������Ϣ �����ڲ���š�ֵMap���ϣ��Ƿ�����AppraisalDeletedFlag���±�������ID(NewRetentionPeriodID)
	 * @param opinion �������� ���ݲ�����	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĵ�ټ�����ϸ���
	 * @param appraisalKeepDestoryDetail Ҫɾ���Ĵ�ټ�����ϸ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĵ�ټ�����ϸ���
	 * @param appraisalKeepDestoryDetail Ҫ���µĴ�ټ�����ϸ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĴ�ټ�����ϸ���
	 * @param appraisalKeepDestoryDetails ���ز��ҳɹ��Ĵ�ټ�����ϸ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<AppraisalKeepDestroyDetail> appraisalKeepDestoryDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���Ҵ�ټ�����ϸ���
	 * @param pID ָ����Ψһ��ʶ
	 * @param appraisalKeepDestoryDetail ���ز��ҳɹ��Ĵ�ټ�����ϸ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺��ҳ��ѯ��ټ����Ǽ���Ϣ
	 * @param archivesTypeIds ָ�����������µ����е�������id
	 * @param params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param appraisalKeepDestroyDetails ���ش�ټ����Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails, ErrInfo pErrInfo);
}
