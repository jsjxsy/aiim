package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ϼ�λ����Ϣ�������Ľӿڶ���
 *
 */
public interface IStoreAddressInfoManageService {

	/**
	 * ���һ���µĵ����ϼ�λ����Ϣ
	 * @param  storeAddressInfo����ӵĵ����ϼ�λ����Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ����ϼ�λ����Ϣ
	 * @param  Ҫɾ���ĵ����ϼ�λ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ����ϼ�λ����Ϣ
	 * @param  �޸ĺ�ĵ����ϼ�λ����Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * �������еĵ����ϼ�λ����Ϣ��Ϣ
	 * @param s ���ز��ҳɹ��ĵ����ϼ�λ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStoreAddressInfos(List<StoreAddressInfo> storeAddressInfo, ErrInfo pErrInfo);

	/**
	 * ���ݺ���������ҵ����ϼ�λ����Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param  ���ز��ҳɹ��ĵ����ϼ�λ����Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStoreAddressInfoByBoxBarcode( StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo);

}

