/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataSourceItem���DAO�ӿڶ���
 *
 */
public interface IDataSourceItemDao
{

	/**
	 * Dao�ӿڶ��壺�������Դ��������
	 * @param dataSourceItem Ҫ��ӵ�����Դ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ��������Դ��������
	 * @param dataSourceItem Ҫɾ��������Դ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ��������Դ��������
	 * @param dataSourceItem Ҫ���µ�����Դ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺��������Դ��Ų�������Դ��������
	 * @param pDataSourceID ָ��������Դ���
	 * @param dataSourceItems ���ز��ҳɹ�������Դ��������ϣ�������Դ������������Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByDataSourceID(int pDataSourceID, LinkedHashMap<Integer,DataSourceItem> dataSourceItems, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ��������Դ��������
	 * @param pID ָ����Ψһ��ʶ
	 * @param dataSourceItem ���ز��ҳɹ�������Դ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo);

}
