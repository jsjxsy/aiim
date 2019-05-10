package com.orifound.aiim.dal.dao;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;

/**
 * �ⷿ�̵�Dao
 * @author Administrator
 *
 */
public interface IStocktakingDao {
	
	/**
	 * Dao�ӿڶ��壺��ӿⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetail Ҫ��ӵĿⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���¿ⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetail Ҫ��ӵĿⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�жϵ������������Ƿ��Ѿ�����
	 * @param stocktakingAddressBoxDetail Ҫ��ӵĿⷿ�̵���豸λ���뵵������ϸ,archivesBoxBarcode���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return �����򷵻�true���������򷵻�false
	 */
	boolean checkAddressBoxDetailExist(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��ӿⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetail Ҫ��ӵĿⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���¿ⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetail Ҫ��ӵĿⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�жϵ������������Ƿ����
	 * @param stocktakingArchivesDetail Ҫ��ӵĿⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return �����򷵻�true���������򷵻�false
	 */
	boolean checkArchivesDetailExist(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	
	
	
}
