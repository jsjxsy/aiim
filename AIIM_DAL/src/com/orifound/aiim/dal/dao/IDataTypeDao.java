/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataType���DAO�ӿڶ���
 *
 */
public interface IDataTypeDao
{

	/**
	 * Dao�ӿڶ��壺����������ֶ�����
	 * @param dataType Ҫ��ӵ��������ֶ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean save(DataType dataType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����������ֶ�����
	 * @param dataType Ҫɾ�����������ֶ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean delete(DataType dataType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ�����������ֶ�����
	 * @param dataType Ҫ���µ��������ֶ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean update(DataType dataType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е��������ֶ�����
	 * @param dataTypes ���ز��ҳɹ����������ֶ����ͼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findAll(List<DataType> dataTypes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ�����������ֶ�����
	 * @param pID ָ����Ψһ��ʶ
	 * @param dataType ���ز��ҳɹ����������ֶ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findByID(int pID, DataType dataType, ErrInfo pErrInfo);

}
