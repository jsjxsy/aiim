/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataItem;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataItem���DAO�ӿڶ���
 *
 */
public interface IDataItemDao
{

	/**
	 * Dao�ӿڶ��壺���������
	 * @param dataItem Ҫ��ӵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(DataItem dataItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����������
	 * @param dataItem Ҫɾ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(DataItem dataItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����������
	 * @param dataItem Ҫ���µ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(DataItem dataItem, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�������
	 * @param dataItems ���ز��ҳɹ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<DataItem> dataItems, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����������
	 * @param pID ָ����Ψһ��ʶ
	 * @param dataItem ���ز��ҳɹ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, DataItem dataItem, ErrInfo pErrInfo);

}
