/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ��������ֵ��������Ľӿڶ���
 *
 */
public interface IOfficialArchivesTypeManageService
{

	/**
	 * ���һ���µĹ��ĵ��������ֵ��
	 * @param officialArchivesType ����ӵĹ��ĵ��������ֵ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ��ĵ��������ֵ��
	 * @param officialArchivesType Ҫɾ���Ĺ��ĵ��������ֵ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ��ĵ��������ֵ��
	 * @param officialArchivesType �޸ĺ�Ĺ��ĵ��������ֵ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialArchivesType(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * �������еĹ��ĵ��������ֵ����Ϣ
	 * @param officialArchivesTypes ���ز��ҳɹ��Ĺ��ĵ��������ֵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesTypes(LinkedHashMap<Integer,OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ��ĵ��������ֵ����Ϣ
	 * @param pOfficialArchivesTypeID ָ���Ĺ��ĵ���������
	 * @param officialArchivesType ���ز��ҳɹ��Ĺ��ĵ��������ֵ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesTypeByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

}
