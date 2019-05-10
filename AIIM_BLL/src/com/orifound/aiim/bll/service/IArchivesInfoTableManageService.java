/**
 * 
 */
package com.orifound.aiim.bll.service;


import java.util.*;

import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������Ϣ��ر�������Ľӿڶ���
 *
 */
public interface IArchivesInfoTableManageService
{
	/**
	 * ����Ψһ��ʶ���ҵ�����Ϣ��ر���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesInfoTable ���ز��ҳɹ��ĵ�����Ϣ��ر���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfoTableByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * ����ָ�����������������ر���Ϣ
	 * @param pArchivesTypeID ָ���ĵ���������
	 * @param archivesInfoTable ���ز��ҳɹ��ĵ�����Ϣ��ر���Ϣ���ϣ��Ա�����ö����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesTypeTables(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo);
	
	/**
	 * ���һ���µĵ�����Ϣ��ر��¼
	 * @param archivesInfoTable ����ӵĵ�����Ϣ��ر���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * ���ñ�����־<br>
	 * �������������ݿ��ṹ������Ϻ����
	 * @param archivesInfoTable ָ���ĵ�����Ϣ��ر�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ��������������ر����¼
	 * @param pArchivesTypeID ָ���ĵ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesTypeTables(int pArchivesTypeID, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�����Ϣ��ر��¼
	 * @param archivesInfoTable �޸ĺ�ĵ�����Ϣ��ر���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesInfoTable(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo);

}
