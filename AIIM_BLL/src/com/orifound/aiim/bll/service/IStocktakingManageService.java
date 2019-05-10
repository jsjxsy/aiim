package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;

/**
 * �ⷿ�̵�ҵ���߼��ӿ�
 * @author Administrator
 *
 */
public interface IStocktakingManageService {
	
	/**
	 * ��ӿⷿ�̵���豸λ���뵵������ϸ
	 * @param stocktakingAddressBoxDetail Ҫ��ӵĿⷿ�̵���豸λ���뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addStocktakingAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo);
	
	/**
	 * ��ӿⷿ�̵�ĵ������뵵������ϸ
	 * @param stocktakingArchivesDetail Ҫ��ӵĿⷿ�̵�ĵ������뵵������ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addStocktakingArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo);
	
	/**
	 * ִ�пⷿ�̵㹤��
	 * @param stocktakingID
	 * @param pErrInfo
	 * @return
	 */
	boolean executeStocktaking(int stocktakingID,ErrInfo pErrInfo);
	
	/**
	 * ��ѯ���пⷿ�����������
	 * @param stocktakingID �̵㹤�����
	 * @param stockReportArchivesCounts ���ش���ɹ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean  findStockReportArchivesCount(int stocktakingID, List<StockReportArchivesCount> stockReportArchivesCounts ,ErrInfo pErrInfo);


	/**
	 * ��ѯ����ϵͳ�в��ڼܵ������
	 * @param stocktakingID �̵㹤�����
	 * @param stockReportSystemNotExists ���ڷ��ؽ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStockReportSystemNotExist(int stocktakingID, List<StockReportSystemNotExist> stockReportSystemNotExists ,ErrInfo pErrInfo);
	

	/**
	 * ��ѯ����ʵ�ﵵ�����ڼܵ������
	 * @param stocktakingID �̵㹤�����
	 * @param stockReportPaperNotExists ���ڷ��ؽ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean  findStockReportPaperNotExist(int stocktakingID, List<StockReportPaperNotExist> stockReportPaperNotExists ,ErrInfo pErrInfo);
	
	
	/**
	 * ��ѯ����λ�ò�ƥ�����
	 * @param stocktakingID �̵㹤�����
	 * @param stockReportAddressNotMatchs ���ڷ��ؽ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStockReportAddressNotMatch(int stocktakingID, List<StockReportAddressNotMatch> stockReportAddressNotMatchs ,ErrInfo pErrInfo) ;
	
	/**
	 * ��ѯ����װ�в�ƥ�䵵�����
	 * @param stocktakingID �̵㹤�����
	 * @param stockReportArchivesBoxNotMatchs ���ڷ��ؽ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStockReportArchivesBoxNotMatch(int stocktakingID, List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs ,ErrInfo pErrInfo);
	
}
