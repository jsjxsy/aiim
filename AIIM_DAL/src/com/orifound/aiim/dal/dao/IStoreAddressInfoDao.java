package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ϼ�λ����Ϣ���DAO�ӿڶ���
 *
 */
public interface IStoreAddressInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӵ����ϼ�λ����Ϣ
	 * @param storeAddressInfo Ҫ��ӵĵ����ϼ�λ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ����ϼ�λ����Ϣ
	 * @param storeAddressInfo Ҫɾ���ĵ����ϼ�λ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ����ϼ�λ����Ϣ
	 * @param storeAddressInfo Ҫ���µĵ����ϼ�λ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�����豸��������ҵ����ϼ�λ����Ϣ����
	 * @param storeAddressInfos ���ز��ҳɹ��ĵ����ϼ�λ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByStoreAddressID(int storeAddressID,List<StoreAddressInfo> storeAddressInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݺ���������ҵ����ϼ�λ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param storeAddressInfo ���ز��ҳɹ��ĵ����ϼ�λ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBoxBarcode(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);
	
	/**
	 * �жϺ����������
	 * @param archivesBoxBarcode ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ���ڷ���true,���򷵻�false
	 */
	boolean checkBoxBarcodeExist(String archivesBoxBarcode,ErrInfo pErrInfo);

}

