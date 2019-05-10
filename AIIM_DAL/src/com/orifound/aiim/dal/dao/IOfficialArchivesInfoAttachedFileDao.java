/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ���ԭ����Ϣ���DAO�ӿڶ���
 *
 */
public interface IOfficialArchivesInfoAttachedFileDao {

	/**
	 * Dao�ӿڶ��壺��ӹ��ĵ�����Ϣ
	 * @param officialArchivesInfoAttachedFile Ҫ��ӵĹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialArchivesType officialArchivesType,OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ�����Ϣ
	 * @param officialArchivesInfoAttachedFile Ҫɾ���Ĺ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialArchivesType officialArchivesType,int officialArchivesInfoAttachedFileID, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ�����Ϣ
	 * @param officialArchivesInfoAttachedFile Ҫ���µĹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesType officialArchivesType,OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ��ĵ�����Ϣ
	 * @paramofficialArchivesType
	 * @param officialArchivesInfoAttachedFiles ���ز��ҳɹ��Ĺ��ĵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(OfficialArchivesType officialArchivesType,int pNBXH,List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ��ĵ�����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesInfoAttachedFile ���ز��ҳɹ��Ĺ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo);
	/**
	 * 
	 * @param officialArchivesType
	 * @param officialArchivesInfoAttachedFile
	 * @param pErrInfo
	 * @return
	 */
	boolean findOfficialArchivesInfoAttachedFileByName(OfficialArchivesType officialArchivesType, OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo);

}
