/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataSource���DAO�ӿڶ���
 *
 */
public interface IDataSourceDao
{

	/**
	 * Dao�ӿڶ��壺�������Դ
	 * @param dataSource Ҫ��ӵ�����Դ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ��������Դ
	 * @param dataSource Ҫɾ��������Դ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ��������Դ
	 * @param dataSource Ҫ���µ�����Դ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�����Դ
	 * @param dataSources ���ز��ҳɹ�������Դ���ϣ�������Դ�����Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(Map<Integer,DataSource> dataSources, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ��������Դ
	 * @param pID ָ����Ψһ��ʶ
	 * @param dataSource ���ز��ҳɹ�������Դ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, DataSource dataSource, ErrInfo pErrInfo);

}
