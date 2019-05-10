/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * Ŀ¼���������DAO�ӿڶ���
 *
 */
public interface ICatalogDataItemDao
{
	/**
	 * Dao�ӿڶ��壺����ָ������������ָ��Ŀ¼��ӡģ����������������<br>
	 * ������ʵ����൵���͹��ĵ������඼����
	 * @param officialArchivesFlag ���ĵ�����־
	 * @parma pArchivesTypeID ָ���ĵ���������
	 * @param catalogType ָ����Ŀ¼���
	 * @param catalogDataItems ���ز��ҳɹ���Ŀ¼��ӡģ���������弯�ϣ����ֶ�����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeID(Boolean officialArchivesFlag,int pArchivesTypeID,Integer catalogType,LinkedHashMap<String,CatalogDataItem> catalogDataItems, ErrInfo pErrInfo);

}
