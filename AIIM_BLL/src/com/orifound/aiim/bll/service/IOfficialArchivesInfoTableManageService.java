/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.EnumMap;
import java.util.List;

import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ�����ر���Ϣ�������Ľӿڶ���
 *
 */
public interface IOfficialArchivesInfoTableManageService
{

	/**
	 * ���һ���µĹ��ĵ�����ر���Ϣ
	 * @param officialArchivesInfoTable ����ӵĹ��ĵ�����ر���Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ��ĵ�����ر���Ϣ
	 * @param officialArchivesInfoTable Ҫɾ���Ĺ��ĵ�����ر���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ��ĵ�����ر���Ϣ
	 * @param officialArchivesInfoTable �޸ĺ�Ĺ��ĵ�����ر���Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialArchivesInfoTable(OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

	/**
	 * ����ָ�����ĵ��������ŵĹ��ĵ�����ر���Ϣ��Ϣ
	 * @param pArchivesTypeID ָ���ĵ���������
	 * @param officialArchivesInfoTables ���ز��ҳɹ��Ĺ��ĵ�����ر���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfoTables(int pArchivesTypeID, EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ��ĵ�����ر���Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesInfoTable ���ز��ҳɹ��Ĺ��ĵ�����ر���Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialArchivesInfoTableByID(int pID, OfficialArchivesInfoTable officialArchivesInfoTable, ErrInfo pErrInfo);

}
