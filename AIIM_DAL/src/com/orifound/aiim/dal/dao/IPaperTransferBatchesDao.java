package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.UserInfo;

/**
 * �ƽ�������Ϣ���DAO�ӿڶ���
 *
 */
public interface IPaperTransferBatchesDao {

	/**
	 * Dao�ӿڶ��壺���PaperTansferBatch
	 * @param pPaperTansferBatch Ҫ��ӵ�PaperTansferBatch 
	 * ������batNo���ƽ����κţ��� batNoCreateUserID���������κŵ��û���
	 * transferType���ƽ����ͣ���insideFlag�������ƽ���־��������ֵ
	 * 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����PaperTansferBatch
	 * @param pPaperTansferBatch Ҫɾ����PaperTansferBatch
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����PaperTansferBatch
	 * @param pPaperTansferBatch Ҫ���µ�PaperTansferBatch
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�PaperTansferBatch
	 * @param pPaperTansferBatchs ���ز��ҳɹ���PaperTansferBatch����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(int [] deptIDs,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> pPaperTransferBatches, boolean insideFlag,ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����PaperTansferBatch
	 * @param batNO ָ����Ψһ��ʶ ���κ�
	 * @param pPaperTansferBatch ���ز��ҳɹ���PaperTansferBatch
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByBatNO(String batNO, PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�����û���ȷ���ƽ���־����������Ϣ
	 * @param paperTransferBatch ���ز��ҳɹ���������Ϣ  �������ε��û���� ��ȷ���ƽ���־���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchByConfirmFlag(PaperTransferBatch paperTransferBatch,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�û�ȷ���ƽ�
	 * @param paperTransferBatNo Ҫȷ���ƽ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateConfirmFlag(String paperTransferBatNo,UserInfo userInfo,int srchivesSum, ErrInfo pErrInfo);

	
	/**
	 * ��Ч����->��������ѯ ���������ҽ������
	 * @param counts  counts[0]δ��������counts[1]�ܽ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return boolean ����ɹ�����true�����򷵻�false
	 */
	boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ����PaperTansferBatch �����ڽ��յǼ�ʱ�������ν�������Ϣ
	 * @param pPaperTansferBatch Ҫ���µ�PaperTansferBatch
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateForReceive(PaperTransferBatch pPaperTransferBatch,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����PaperTansferBatch�������ɱ�־
	 * @param paperTransferBatNo Ҫ���µ����ε����κ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateBatState(String paperTransferBatNo, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������������ƶ�����ָ��״̬���ƽ�������Ϣ
	 * @param paperTransferBatNo Ҫ���µ����ε����κ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByCondition(int[] deptIDs, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches,DataPageInfo dataPageInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺������������ָ���û�ָ��״̬�Ľ���������Ϣ
	 * @param paperTransferBatNo Ҫ���µ����ε����κ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByCondition(EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,int[] userIDs, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches,DataPageInfo dataPageInfo,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�����û����Ҹ��û��ƽ���������Ϣ
	 * @param userID �ƽ��û�ID
	 * @param enumPaperTransferBatchesDealStatus ָ������״̬
	 * @param paperTransferBatches ���ص�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchsByTransferUser(int userID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches, boolean insideFlag,ErrInfo pErrInfo);

	/**
	 * ���������е�����Ϣ������
	 * @param batNo Ҫ���µ����κ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateTransferTotal(String batNo, ErrInfo pErrInfo);
}
