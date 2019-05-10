/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����Դ�������Ľӿڶ���
 *
 */
public interface IDataSourceManageService
{

	/**
	 * ���һ���µ�����Դ
	 * @param dataSource ����ӵ�����Դ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveDataSource(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ��������Դ
	 * @param dataSource Ҫɾ��������Դ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteDataSource(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * �޸�ָ��������Դ
	 * @param dataSource �޸ĺ������Դ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateDataSource(DataSource dataSource, ErrInfo pErrInfo);

	/**
	 * �������е�����Դ��Ϣ
	 * @param dataSources ���ز��ҳɹ�������Դ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDataSources(Map<Integer, DataSource> dataSources, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ��������Դ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param dataSource ���ز��ҳɹ�������Դ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDataSourceByID(int pID, DataSource dataSource, ErrInfo pErrInfo);

}
