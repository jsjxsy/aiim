/**
 * 
 */
package com.orifound.aiim.dal.dao;


import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.ErrInfo;

/**
 * CurrentBarcode���DAO�ӿڶ���
 *
 */
public interface ICurrentBarcodeDao {


	/**
	 * Dao�ӿڶ��壺����ָ������������Ϣ
	 * @param pEntity Ҫ���µ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(CurrentBarcode currentBarcode, ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺����ָ�����͵���������Ϣ
	 * @param pEntity Ҫ���µ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(CurrentBarcode currentBarcode, ErrInfo pErrInfo);
	
	
	/**
	 * Dao�ӿڶ��壺�����������Ͳ�����������Ϣ
	 * @param barCodeType ָ����Ψһ��ʶ
	 * @param currentBarcode ���ز��ҳɹ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBarcodeType(CurrentBarcode currentBarcode, ErrInfo pErrInfo);

}
