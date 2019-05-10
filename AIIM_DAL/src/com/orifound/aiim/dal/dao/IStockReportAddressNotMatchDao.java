package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵�-�ϼ�λ�ò�ƥ����Ϣ���DAO�ӿڶ���
 *
 */
public interface IStockReportAddressNotMatchDao {

	/**
	 * Dao�ӿڶ��壺����ϼ�λ�ò�ƥ����Ϣ
	 * @param stockReportAddressNotMatch Ҫ��ӵ��ϼ�λ�ò�ƥ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺����ָ�����ϼ�λ�ò�ƥ����Ϣ
	 * @param stockReportAddressNotMatch Ҫ���µ��ϼ�λ�ò�ƥ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е��ϼ�λ�ò�ƥ����Ϣ
	 * @param stockReportAddressNotMatchs ���ز��ҳɹ����ϼ�λ�ò�ƥ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(int stocktakingID,List<StockReportAddressNotMatch> stockReportAddressNotMatchs, ErrInfo pErrInfo);
	
	/**
	 * ִ���ϼ�λ�ò�ƥ����Ϣ����̵�<br>���������ϼ�λ�ò�ƥ����Ϣ����Ӧ�ı���
	 * @param stocktakingID �̵㹤�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean executeStocktakingByStocktakingID(int stocktakingID,ErrInfo pErrInfo);

}
