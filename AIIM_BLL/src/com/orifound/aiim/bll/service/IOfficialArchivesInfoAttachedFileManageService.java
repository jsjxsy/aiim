/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ���ԭ����Ϣ��������Ľӿڶ���
 *
 */
public interface IOfficialArchivesInfoAttachedFileManageService {

	/**
	 * ���һ���µĹ��ĵ���ԭ����Ϣ��
	 * @param officialArchivesInfoAttachedFile ����ӵĹ��ĵ���ԭ����Ϣ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ��ĵ���ԭ����Ϣ��
	 * @param officialArchivesInfoAttachedFile Ҫɾ���Ĺ��ĵ���ԭ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesInfoAttachedFile(OfficialArchivesType officialArchivesType,
			int officialArchivesInfoAttachedFileID,
			ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ��ĵ���ԭ����Ϣ��
	 * @param officialArchivesInfoAttachedFile �޸ĺ�Ĺ��ĵ���ԭ����Ϣ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialArchivesInfoAttachedFile(OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * �������еĹ��ĵ���ԭ����Ϣ����Ϣ
	 * @param officialArchivesInfoAttachedFiles ���ز��ҳɹ��Ĺ��ĵ���ԭ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfoAttachedFiles(OfficialArchivesType officialArchivesType,int pNBXH,List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles,
			ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ��ĵ���ԭ����Ϣ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesInfoAttachedFile ���ز��ҳɹ��Ĺ��ĵ���ԭ����Ϣ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfoAttachedFileByID(OfficialArchivesType officialArchivesType, OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo);

}
