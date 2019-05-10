package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.StocktakingInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵㹤����Ϣ���DAO�ӿڶ���
 *
 */
public interface IStocktakingInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӿⷿ�̵㹤����Ϣ
	 * @param stocktakingInfo Ҫ��ӵĿⷿ�̵㹤����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀⷿ�̵㹤����Ϣ
	 * @param stocktakingInfo Ҫɾ���Ŀⷿ�̵㹤����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀⷿ�̵�״̬
	 * @param stocktakingInfo Ҫ���µĿⷿ�̵㹤����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿⷿ�̵㹤����Ϣ
	 * @param stocktakingInfos ���ز��ҳɹ��Ŀⷿ�̵㹤����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<StocktakingInfo> stocktakingInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿⷿ�̵㹤����Ϣ
	 * @param stocktakingInfo ���ز��ҳɹ��Ŀⷿ�̵㹤����Ϣ,ID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID( StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

}
