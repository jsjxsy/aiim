package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �̵㱨��-ϵͳ�в��ڼܵ�����Ϣ���DAO�ӿڶ���
 *
 */
public interface IStockReportSystemNotExistDao {

	/**
	 * Dao�ӿڶ��壺���ϵͳ�в��ڼܵ�����Ϣ
	 * @param stockReportSystemNotExist Ҫ��ӵ�ϵͳ�в��ڼܵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ϵͳ�в��ڼܵ�����Ϣ
	 * @param stockReportSystemNotExist Ҫɾ����ϵͳ�в��ڼܵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ϵͳ�в��ڼܵ�����Ϣ
	 * @param stockReportSystemNotExist Ҫ���µ�ϵͳ�в��ڼܵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ϵͳ�в��ڼܵ�����Ϣ
	 * stocktakingID �ⷿ�̵���
	 * @param stockReportSystemNotExists ���ز��ҳɹ���ϵͳ�в��ڼܵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(int stocktakingID,List<StockReportSystemNotExist> stockReportSystemNotExists, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ϵͳ�в��ڼܵ�����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param stockReportSystemNotExist ���ز��ҳɹ���ϵͳ�в��ڼܵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo);

	/**
	 * ִ��ϵͳ�в��ڼܵ�������̵����<br>��������ϵͳ�в��ڼܵĵ��������
	 * @param stocktakingID �̵㹤�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);
}
