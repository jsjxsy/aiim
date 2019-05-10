/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;


import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_ArchivesInfoTable���DAO�ӿڶ���
 *
 */
public interface IArchivesInfoTableDao
{

	/**
	 * Dao�ӿڶ��壺��ӵ�����Ϣ��ر�
	 * @param archivesInfoTable Ҫ��ӵĵ�����Ϣ��ر�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ����������������������¼
	 * @param pArchivesTypeID ָ���ĵ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteByArchivesTypeID(int pArchivesTypeID,ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ñ�����־
	 * @param archivesInfoTable ָ���ĵ�����Ϣ��ر�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݵ��������Ų��Ҷ�Ӧ�ĵ�����Ϣ��ر�
	 * @param pArchivesTypeID ָ���ĵ���������
	 * @param archivesInfoTables ���ز��ҳɹ��ĵ�����Ϣ��ر��ϣ��Ա�����ö����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeID(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ�����Ϣ��ر�
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesInfoTable ���ز��ҳɹ��ĵ�����Ϣ��ر�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

}
