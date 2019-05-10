package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StocktakingArchivesDetail;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵�ĵ������뵵������ϸ���DAO�ӿڶ���
 *
 */
public interface IStocktakingArchivesDetailDao {

	/**
	 * Dao�ӿڶ��壺��ӿⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetail Ҫ��ӵĿⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetail Ҫɾ���Ŀⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetail Ҫ���µĿⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetails ���ز��ҳɹ��Ŀⷿ�̵�ĵ������뵵������ϸ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StocktakingArchivesDetail> stocktakingArchivesDetails, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿⷿ�̵�ĵ������뵵������ϸ
	 * @param pID ָ����Ψһ��ʶ
	 * @param stocktakingArchivesDetail ���ز��ҳɹ��Ŀⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);

}
