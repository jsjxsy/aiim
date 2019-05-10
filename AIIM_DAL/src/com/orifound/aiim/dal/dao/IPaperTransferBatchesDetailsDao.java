package com.orifound.aiim.dal.dao;

import java.util.List;
import java.util.Map;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;

/**
 * ֽ�ʵ����ƽ���ϸ�嵥���DAO�ӿڶ���
 *
 */
public interface IPaperTransferBatchesDetailsDao {

	/**
	 * Dao�ӿڶ��壺����ָ����PaperTransferBatchesDetail
	 * @param pPaperTransferBatchesDetail Ҫ���µ�PaperTransferBatchesDetail
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(PaperTransferBatchesDetail pPaperTransferBatchesDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��������ӵ��ƽ��嵥��
	 * @param archivesType ����������Ϣ
	 * @param archivesInfos Ҫ����ĵ�����Ϣ
	 * @param paperTransferBatch ��ǰ������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addPaperTransferBatchesDetails(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);
	
	/**
	 * ͳ�Ƶ�ǰ�����е���������
	 * @param paperTransferBatch Ҫͳ�Ƶ����α��
	 * @param paperTransferBatchesDetails ���ظ����൵��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean staArchivesInfosSumByTransferBat (IntegerEx archivesInfosSum, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);

	/**
	 * ����ָ����������ָ������ĵ�����Ϣ
	 * @param paperTransferBatNo ���κ�
	 * @param archivesTypeID ��������
	 * @param paperTransferBatchesDetails ���ص�������ϸ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBatNoAndArchivesType(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo);

	/**
	 * ͳ�Ƶ�ǰ�����е���������
	 * @param nBXH Ҫɾ����������Ϣ��ϸ���Ӧ�ĵ����ڲ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(String paperTransferBatNo, int archivesTypeID,int nBXH, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�����γɲ����û�ȷ�Ϻ����õ���������״̬   �˷����������������еĵ�����Ϣ����������д�빤������¼��
	 * @param archivesType ��������
	 * @param userID �������û�
	 * @param paperTransferBatNo �������ڵ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean setFlagForWorkFlow(ArchivesType archivesType, PaperTransferBatch paperTransferBatch, int userID, EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����������õ�������˽��
	 * @param archivesTypeID �������� ���
	 * @param batNo �������ڵ����ε����κ�
	 * @param enumCheckResult ����������˽��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateReceiveCheckResult(int archivesTypeID, String batNo,int NBXH, EnumCheckResult enumCheckResult,ErrInfo pErrInfo);
	
	/**
	 * �������κŲ��ҵ�ָ������ָ�������µ���������ȫ�ں�
	 * @param batNo ���κ�
	 * @param archivesType ����������Ϣ
	 * @param ArchivesFondIDs ���ص�����ȫ�ڵ�ȫ�ں�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesFondsByBatNoAndArchivesType(String batNo, ArchivesType archivesType,List<String> ArchivesFondIDs,ErrInfo pErrInfo);

	/**
	 * �������κŲ���ָ������ָ�������ȫ�ڵĵ����ڲ���ż���
	 * @param paperTransferBatNo ���κ�
	 * @param archivesType ����������Ϣ
	 * @param ArchivesFondID ȫ�ڵ�ȫ�ں�
	 * @param NBXHS ���ص��ڲ���ż���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findNBXHSByArchivesFonds(String paperTransferBatNo, ArchivesType archivesType, String archivesFondID,List<Integer> NBXHS, ErrInfo pErrInfo);

	/**
	 * ��ָ������ָ�������ȫ�ڵĵ�����Ϣ���ɵ���
	 * @param paperTransferBatNo ���κ�
	 * @param archivesType ����������Ϣ
	 * @param ArchivesFondID ȫ�ڵ�ȫ�ں�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesID(String paperTransferBatNo, ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * ���γɲ��ŵ��ƽ����μ��뵽ҵ��ָ���ҵ��ƽ�������
	 * @param paperTransferBatchNos Ҫ�����ƽ����ε������κ�
	 * @param paperTransferBatch �µ����ε���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addToPaperTransferBatchaddToPaperTransferBatch(String[] paperTransferBatchNos, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);

	/**
	 * ҵ��ָ����ͳ�������¸���������ĵ�������
	 * @param paperTransferBatch
	 * @param paperTransferBatchesArchvTypeDetails
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean staArchivesInfosSumByTransferBatArchvType(PaperTransferBatch paperTransferBatch, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,ErrInfo pErrInfo);
	
	/**
	 * ҵ��ָ���Ҽ����ƽ��嵥��<br/>  
	 * �����µ����������еĵ�����Ϣ�Ĺ�����״̬
	 * @param paperTransferBatch ����������������Ϣ
	 * @param archivesType ���������ķ�����Ϣ
	 * @param enumWorkFlowStatus Ҫ���µ�״̬
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean setArchivesFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ҵ��ָ�����û�ȷ�Ϻ����õ���������״̬   �˷����������������еĵ�����Ϣ����������д�빤������¼��
	 * @param archivesType ��������
	 * @param userID �������û�
	 * @param paperTransferBatNo �������ڵ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean setFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * ��ѯû�����ɵ��ŵĵ����ĵ���ǰ׺
	 * @param paperTransferBatNo
	 * @param archivesType
	 * @param archivesIDPrefixs
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesIDPrefixWhitOutArchivesID(String paperTransferBatNo, ArchivesType archivesType, Map<Integer, String> archivesIDPrefixs, ErrInfo pErrInfo);

	/**
	 * �����ƶ�������δ��������ĵ����ĵ�һ��
	 * @param batNo
	 * @param archivesTypeID
	 * @param paperTransferBatchesDetail
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID, final ArchivesInfo archivesInfo, ErrInfo pErrInfo);
}

