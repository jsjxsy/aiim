package com.orifound.aiim.dal.dao;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;

/**
 * ֽ�ʵ����ƽ����ε���������ϸ������DAO�ӿڶ���
 *
 */
public interface IPaperTransferBatchesArchvTypeDetailsDao {

	/**
	 * Dao�ӿڶ��壺����ָ�������η�����ϸ��Ϣ�ĵ���������
	 * @param pPaperTransferBatchesArchvTypeDetail Ҫ���µ�PaperTransferBatchesArchvTypeDetail
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ�������η�����ϸ��Ϣ�ĵ���������
	 * @param pPaperTransferBatchesArchvTypeDetail Ҫ���µ�PaperTransferBatchesArchvTypeDetail
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateWhithSubBat(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݵ��������ź����κŲ���
	 * @param paperTransferBatchesArchvTypeDetail ���ص�ֽ�ʵ����ƽ����ε���������ϸ����� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesTypeAndBatNO(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�����µ�ֽ�ʵ����ƽ����ε���������ϸ���
	 * @param paperTransferBatchesArchvTypeDetail ���ص�ֽ�ʵ����ƽ����ε���������ϸ����� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������κŲ���ֽ�ʵ����ƽ����ε���������ϸ���
	 * @param paperTransferBatch ������Ϣ ���κű�����ֵ
	 * @param paperTransferBatchesArchvTypeDetail ���ص�ֽ�ʵ����ƽ����ε���������ϸ����� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCurrentPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);

	/**
	 * ����ָ������ָ���������ĵ�������״̬
	 * @param paperTransferBatNo ������Ϣ ���κ�
	 * @param archivesTypeID ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, ErrInfo pErrInfo);

	/**
	 * �������η�����Ϣ���Ƿ���δ���ɵ��ŵķ���
	 * @param paperTransferBatNo ������Ϣ ���κ�
	 * @param archivesTypeID ��������
	 * @param paperTransferBatchesArchvTypeDetails ���ص����η�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);
}

