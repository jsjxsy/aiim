/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.EnumMap;

import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ�����Ϣ��ر��DAO�ӿڶ���
 *
 */
public interface IOfficialArchivesInfoTableDao
{

	/**
	 * Dao�ӿڶ��壺��ӹ��ĵ�����Ϣ��ر�
	 * @param officialArchivesInfoTable Ҫ��ӵĹ��ĵ�����Ϣ��ر�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ�����Ϣ��ر�
	 * @param officialArchivesInfoTable Ҫɾ���Ĺ��ĵ�����Ϣ��ر�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ�����Ϣ��ر�
	 * @param officialArchivesInfoTable Ҫ���µĹ��ĵ�����Ϣ��ر�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݹ��ĵ��������Ų��Ҷ�Ӧ�ĵ�����Ϣ��ر�
	 * @param pArchivesTypeID ָ���Ĺ��ĵ���������
	 * @param archivesInfoTables ���ز��ҳɹ��ĵ�����Ϣ��ر��ϣ��Ա�����ö����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeID(int pArchivesTypeID, EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ��ĵ�����Ϣ��ر�
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesInfoTable ���ز��ҳɹ��Ĺ��ĵ�����Ϣ��ر�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

}
