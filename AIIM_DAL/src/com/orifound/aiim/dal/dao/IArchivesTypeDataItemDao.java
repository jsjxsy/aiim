/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������������ֵ��DDR_ArchivesType_DataItem����DAO�ӿڶ���
 *
 */
public interface IArchivesTypeDataItemDao
{

	/**
	 * Dao�ӿڶ��壺��ӵ�������������
	 * @param archivesTypeDataItem Ҫ��ӵĵ�������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�������������
	 * @param archivesTypeDataItem Ҫɾ���ĵ�������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�������������
	 * @param archivesTypeDataItem Ҫ���µĵ�������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���������ඨ���������<br>
	 * ������ʵ����൵���͹��ĵ������඼����
	 * @param officialArchivesFlag ���ĵ�����־
	 * @parma pArchivesTypeID ָ���ĵ���������
	 * @param archivesTypeDataItems ���ز��ҳɹ��ĵ�������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID, LinkedHashMap<String,ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ�������������
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesTypeDataItem ���ز��ҳɹ��ĵ�������������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

}
