package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵�-ʵ�ﵵ�����ڼ���Ϣ���DAO�ӿڶ���
 *
 */
public interface IStockReportPaperNotExistDao {

	/**
	 * Dao�ӿڶ��壺���ʵ�ﵵ�����ڼ���Ϣ
	 * @param stockReportPaperNotExist Ҫ��ӵ�ʵ�ﵵ�����ڼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ʵ�ﵵ�����ڼ���Ϣ
	 * @param stockReportPaperNotExist Ҫɾ����ʵ�ﵵ�����ڼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ʵ�ﵵ�����ڼ���Ϣ
	 * @param stockReportPaperNotExist Ҫ���µ�ʵ�ﵵ�����ڼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ʵ�ﵵ�����ڼ���Ϣ
	 * @param stockReportPaperNotExists ���ز��ҳɹ���ʵ�ﵵ�����ڼ���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(int stocktakingID,List<StockReportPaperNotExist> stockReportPaperNotExists, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ʵ�ﵵ�����ڼ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param stockReportPaperNotExist ���ز��ҳɹ���ʵ�ﵵ�����ڼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo);
	
	/**
	 * ִ��ʵ�ﵵ�����ڼܵ�������̵�<br>��������ʵ�ﵵ�����ڼܵĵ��������
	 * @param stocktakingID �̵㹤�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);

}

