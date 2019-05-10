/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.Map;

import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ���������Ĺ鵵��ϵӳ����DAO�ӿڶ���
 *
 */
public interface IOfficialArchivesDataItemSavedMappingDao {

	/**
	 * Dao�ӿڶ��壺��ӹ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesDataItemSavedMapping Ҫ��ӵĹ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesDataItemSavedMapping Ҫɾ���Ĺ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesDataItemSavedMapping Ҫ���µĹ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param archivesTypeSavedMappingID ���ĵ�������鵵ӳ���ϵ���
	 * @param officialArchivesDataItemSavedMappings ���ز��ҳɹ��Ĺ��ĵ���������Ĺ鵵ӳ���ϵ���ϣ��Թ��ĵ���������������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeSavedMappingID (Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesDataItemSavedMapping ���ز��ҳɹ��Ĺ��ĵ���������Ĺ鵵ӳ���ϵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

}
