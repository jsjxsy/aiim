/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������������������Ľӿڶ���
 *
 */
public interface IArchivesTypeDataItemManageService
{

	/**
	 * ���һ���µĵ�������������
	 * @param archivesTypeDataItem ����ӵĵ���������������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ�������������
	 * @param archivesTypeDataItem Ҫɾ���ĵ��������������Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�������������
	 * @param archivesTypeDataItem �޸ĺ�ĵ���������������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesTypeDataItem(ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

	/**
	 * ����ָ���������ඨ���������<br>
	 * ������ʵ����൵���͹��ĵ������඼����
	 * @param officialArchivesFlag ���ĵ�����־
	 * @parma pArchivesTypeID ָ���ĵ���������
	 * @param archivesTypeDataItems ���ز��ҳɹ��ĵ�������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID, LinkedHashMap<String,ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ���������������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesTypeDataItem ���ز��ҳɹ��ĵ���������������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesTypeDataItemByID(int pID, ArchivesTypeDataItem archivesTypeDataItem, ErrInfo pErrInfo);

}
