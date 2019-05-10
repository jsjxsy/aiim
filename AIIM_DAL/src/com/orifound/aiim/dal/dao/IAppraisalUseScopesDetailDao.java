/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ���ؼ����ĵ�����ϸ��ϢDAO�ӿ�
 *
 */
public interface IAppraisalUseScopesDetailDao
{

	/**
	 * ����ָ���ĵ�����ָ���û���Ȩ���ʵĻ��ص����б��г��ֵĴ���
	 * @param pUserID �����û��ı��
	 * @param pArchivesTypeID ����������
	 * @param pNBXH �������ڲ����
	 * @param pACLCount ���ز��ҳɹ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCountArchivesInfoNotInUseScopesACL(int pUserID,int pArchivesTypeID,int pNBXH,IntegerEx pACLCount,ErrInfo pErrInfo);

	/**
	 * ����ָ���ĵ�����ָ���û��Ļ��ص����б��г��ֵĴ���
	 * @param pUserID �����û��ı��
	 * @param pArchivesTypeID ����������
	 * @param pNBXH �������ڲ����
	 * @param pACLCount ���ز��ҳɹ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCountArchivesInfoInUseScopesACL(int pUserID,int pArchivesTypeID,int pNBXH,IntegerEx pACLCount,ErrInfo pErrInfo);
	
	/**
	 * ���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param archivesTypeID		��������id
	 * @param NBXH		�ڲ����
	 * @param appraisalUseScopesDetail	���ز��ҳɹ��ĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	/**
	 * ��� �������ֿ��Ƽ�����ϸ�����Ϣ
	 * @param appraisalUseScopesDetail ����ӵĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	/**
	 * ���� �������ֿ��Ƽ�����ϸ�����Ϣ
	 * @param appraisalUseScopesDetail ����ӵĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
	
	
	/**
	 * ���� �������ؼ�����ɫ
	 * @param appraisalUseScopesDetailId �������ֿ��Ƽ�����ϸ������ʵ��Id
	 * @param roleIds ���ؽ�ɫid����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRoles(int appraisalUseScopesDetailId, List<Integer> roleIds, ErrInfo pErrInfo);
	
	/**
	 * ɾ�� �������ؼ�����ɫ
	 * @param appraisalUseScopesDetailId �������ֿ��Ƽ�����ϸ������ʵ��Id
	 * @param roleIds ���ؽ�ɫid����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRoles(int appraisalUseScopesDetailId, List<Integer> roleIds, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��ҳ��ѯ����/���ż����Ǽ���Ϣ
	 * @param archivesTypeIds ָ�����������µ����е�������id
	 * @param params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd
	 * @param dataPageInfo ����ҳ��Ϣ������ 
	 * @param appraisalPublicDetails ���ؿ��ż����Ǽ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalUseScopesDetail> appraisalUseScopesDetails, ErrInfo pErrInfo);

	/**
	 * �����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ����
	 * @param pId 		Ψһ��ʶ��
	 * @param roleNames ���ؽ�ɫ���Ƽ���
	 * @param pErrInfo  ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRoleNamesById(int pId, List<String> roleNames, ErrInfo pErrInfo);
	
	/**
	 * ɾ���������ֿ��Ƽ�����ϸ�����Ϣ
	 * @param appraisalUseScopesDetail ����ӵĵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo);
}
