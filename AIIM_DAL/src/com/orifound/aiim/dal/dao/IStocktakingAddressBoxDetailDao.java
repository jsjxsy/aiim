package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵���豸λ���뵵������ϸ���DAO�ӿڶ���
 *
 */
public interface IStocktakingAddressBoxDetailDao {

	/**
	 * Dao�ӿڶ��壺��ӿⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetail Ҫ��ӵĿⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetail Ҫɾ���Ŀⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetail Ҫ���µĿⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetails ���ز��ҳɹ��Ŀⷿ�̵���豸λ���뵵������ϸ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StocktakingAddressBoxDetail> stocktakingAddressBoxDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿⷿ�̵���豸λ���뵵������ϸ
	 * @param pID ָ����Ψһ��ʶ
	 * @param stocktakingAddressBoxDetail ���ز��ҳɹ��Ŀⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);

}
