/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ�������Ĺ鵵ӳ���ϵ����DAO�ӿڶ���
 *
 */
public interface IOfficialArchivesTypeSavedMappingDao {

	/**
	 * Dao�ӿڶ��壺��ӹ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMapping Ҫ��ӵĹ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMapping Ҫɾ���Ĺ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMapping Ҫ���µĹ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMappings ���ز��ҳɹ��Ĺ��ĵ�������Ĺ鵵ӳ���ϵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesTypeSavedMapping ���ز��ҳɹ��Ĺ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);
	
	/**
	 * ���ĵ������ͱ�Ų�ѯ
	 * @param officialArchivesTypeID ���ĵ������ͱ��
	 * @param officialArchivesTypeSavedMappings �������ͼ���ID
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return	����ɹ�����true�����򷵻�false
	 */
	boolean findByOfficialArchivesTypeID(int officialArchivesTypeID, Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings, ErrInfo pErrInfo);
}

