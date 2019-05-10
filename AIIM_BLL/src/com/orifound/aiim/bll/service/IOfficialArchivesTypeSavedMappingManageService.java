/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ�������Ĺ鵵ӳ���ϵ��������Ľӿڶ���
 *
 */
public interface IOfficialArchivesTypeSavedMappingManageService {

	/**
	 * ���һ���µĹ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMapping ����ӵĹ��ĵ�������Ĺ鵵ӳ���ϵ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialArchivesTypeSavedMapping(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMapping Ҫɾ���Ĺ��ĵ�������Ĺ鵵ӳ���ϵ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesTypeSavedMapping(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ��ĵ�������Ĺ鵵ӳ���ϵ��
	 * @param officialArchivesTypeSavedMapping �޸ĺ�Ĺ��ĵ�������Ĺ鵵ӳ���ϵ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialArchivesTypeSavedMapping(OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping, ErrInfo pErrInfo);

	/**
	 * �������еĹ��ĵ�������Ĺ鵵ӳ���ϵ����Ϣ
	 * @param officialArchivesTypeSavedMappings ���ز��ҳɹ��Ĺ��ĵ�������Ĺ鵵ӳ���ϵ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesTypeSavedMappings(Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ��ĵ�������Ĺ鵵ӳ���ϵ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesTypeSavedMapping ���ز��ҳɹ��Ĺ��ĵ�������Ĺ鵵ӳ���ϵ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesTypeSavedMappingByID(int pID, OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping,
			ErrInfo pErrInfo);
	/**
	 * ���ݹ��ĵ������ͱ�Ų�ѯ���������ͼ���
	 * @param officialArchivesTypeID ���ĵ������ͱ��
	 * @param archivesTypes �������ͼ���
	 * @param pErrInfo 
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesTypesByOfficialArchivesTypeID(int officialArchivesTypeID,Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings,ErrInfo pErrInfo );

}
