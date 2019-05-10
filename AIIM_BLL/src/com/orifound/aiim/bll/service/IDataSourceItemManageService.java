/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����Դ��������������Ľӿڶ���
 *
 */
public interface IDataSourceItemManageService
{

	/**
	 * ���һ���µ�����Դ��������
	 * @param dataSourceItem ����ӵ�����Դ����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ��������Դ��������
	 * @param dataSourceItem Ҫɾ��������Դ���������Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * �޸�ָ��������Դ��������
	 * @param dataSourceItem �޸ĺ������Դ����������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateDataSourceItem(DataSourceItem dataSourceItem, ErrInfo pErrInfo);

	/**
	 * ����ָ������Դ��������������Ϣ
	 * @param dataSourceItems ���ز��ҳɹ�������Դ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDataSourceItemsByDataSourceID(Integer pDataSourceID, LinkedHashMap<Integer,DataSourceItem> dataSourceItems, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ��������Դ����������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param dataSourceItem ���ز��ҳɹ�������Դ����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDataSourceItemByID(int pID, DataSourceItem dataSourceItem, ErrInfo pErrInfo);

}
