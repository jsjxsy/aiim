/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������ֿ��Ƽ�����ϸ������ʵ����������Ľӿڶ���
 *
 */
public interface IAppraisalUseScopesDetailManageService {

	/**
	 * ��ӻ��߸��� �������ֿ��Ƽ�����ϸ�����Ϣ
	 * @param appraisalUseScopesDetail ����ӵĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOrUpdateAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���ĵ������ֿ��Ƽ�����ϸ������ʵ����
	 * @param appraisalUseScopesDetail Ҫɾ���ĵ������ֿ��Ƽ�����ϸ������ʵ���࣬��Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ������ֿ��Ƽ�����ϸ������ʵ����
	 * @param appraisalUseScopesDetail �޸ĺ�ĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateAppraisalUseScopesDetail(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);

	/**
	 * �������еĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param appraisalUseScopesDetails ���ز��ҳɹ��ĵ������ֿ��Ƽ�����ϸ������ʵ���༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalUseScopesDetails(List<AppraisalUseScopesDetail> appraisalUseScopesDetails,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param appraisalUseScopesDetail ���ز��ҳɹ��ĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalUseScopesDetailByID(int pID, AppraisalUseScopesDetail appraisalUseScopesDetail,
			ErrInfo pErrInfo);

	/**
	 * ���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param archivesTypeID ��������id
	 * @param NBXH		�����ڲ����
	 * @param appraisalUseScopesDetail	���ز��ҳɹ��ĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAppraisalUseScopesDetailByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);

	/**
	 * ��������->���ؼ����Ǽ���Ϣ  ��ҳ��ѯ
	 * @param archivesTypeIds ָ�����������µ����е�������id
	 * @param params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param appraisalPublicDetails ���ؿ��ż����Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalUseScopesDetail> appraisalUseScopesDetails, ErrInfo pErrInfo);

	/**
	 * �����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ����
	 * @param pId 		Ψһ��ʶ��
	 * @param roleNames ���ؽ�ɫ���Ƽ���
	 * @param pErrInfo  ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRoleNamesById(int pId, List<String> roleNames, ErrInfo pErrInfo);
}
