/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.Map;

import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ���������Ĺ鵵��ϵӳ��������Ľӿڶ���
 *
 */
public interface IOfficialArchivesDataItemSavedMappingManageService {

	/**
	 * ���һ���µĹ��ĵ���������Ĺ鵵��ϵӳ��
	 * @param officialArchivesDataItemSavedMapping ����ӵĹ��ĵ���������Ĺ鵵��ϵӳ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialArchivesDataItemSavedMapping(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ��ĵ���������Ĺ鵵��ϵӳ��
	 * @param officialArchivesDataItemSavedMapping Ҫɾ���Ĺ��ĵ���������Ĺ鵵��ϵӳ�䣬��Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesDataItemSavedMapping(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ��ĵ���������Ĺ鵵��ϵӳ��
	 * @param officialArchivesDataItemSavedMapping �޸ĺ�Ĺ��ĵ���������Ĺ鵵��ϵӳ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialArchivesDataItemSavedMapping(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping, ErrInfo pErrInfo);

	/**
	 * ����ָ�����ĵ�������ӳ���ϵ��Ӧ������������Ĺ鵵��ϵӳ����Ϣ
	 * @param officialArchivesDataItemSavedMappings ���ز��ҳɹ��Ĺ��ĵ���������Ĺ鵵��ϵӳ�伯�ϣ��Թ��ĵ�����������������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeSavedMappingID(Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ��ĵ���������Ĺ鵵��ϵӳ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesDataItemSavedMapping ���ز��ҳɹ��Ĺ��ĵ���������Ĺ鵵��ϵӳ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesDataItemSavedMappingByID(int pID, OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo);

}
