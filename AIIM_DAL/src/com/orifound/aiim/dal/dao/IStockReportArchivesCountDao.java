package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵�- �ⷿ�̵㵵��������Ϣ���DAO�ӿڶ���
 *
 */
public interface IStockReportArchivesCountDao {

	/**
	 * Dao�ӿڶ��壺��ӿⷿ�̵㵵��������Ϣ
	 * @param stockReportArchivesCounty Ҫ��ӵĿⷿ�̵㵵��������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StockReportArchivesCount stockReportArchivesCounty, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĿⷿ�̵㵵��������Ϣ
	 * @param stockReportArchivesCountys ���ز��ҳɹ��Ŀⷿ�̵㵵��������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(int stocktakingID,List<StockReportArchivesCount> stockReportArchivesCounts, ErrInfo pErrInfo);
	
	/**
	 * ִ�пⷿ����������Ϣ�̵����<br>��������ϵͳ�в��ڼܵĵ��������
	 * @param stocktakingID �̵㹤�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);

	
}

