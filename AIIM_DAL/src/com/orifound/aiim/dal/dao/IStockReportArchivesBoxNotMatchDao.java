package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵�- ����װ�в�ƥ����Ϣ���DAO�ӿڶ���
 *
 */
public interface IStockReportArchivesBoxNotMatchDao {

	/**
	 * Dao�ӿڶ��壺��ӵ���װ�в�ƥ����Ϣ
	 * @param stockReportArchivesBoxNotMatch Ҫ��ӵĵ���װ�в�ƥ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo);


	/**
	 * Dao�ӿڶ��壺����ָ���ĵ���װ�в�ƥ����Ϣ
	 * @param stockReportArchivesBoxNotMatch Ҫ���µĵ���װ�в�ƥ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ���װ�в�ƥ����Ϣ
	 * int stocktakingID �ⷿ�̵���
	 * @param stockReportArchivesBoxNotMatchs ���ز��ҳɹ��ĵ���װ�в�ƥ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(int stocktakingID,List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs, ErrInfo pErrInfo);
	
	/**
	 * ִ�е���װ�в�ƥ����Ϣ����̵�<br>��������װ�в�ƥ����Ϣ����Ӧ�ı���
	 * @param stocktakingID �̵㹤�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);


}

