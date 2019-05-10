/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesBoxLabel;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����ƽ��������Ľӿڶ���
 *
 */
public interface IPaperTransferManageService {

	/**
	 * �����γɲ���ͳ�ƽ������ͨ����δͨ�����������ύ����ͨ��δͨ������<br>
	 * ͳ�ƽ������δͨ�����������ύ����ͨ��δͨ������������û���ƽ�ʵ�ﵵ�����ύ����ĵ������಻��ͳ�ƺͷ��ؽ��<br>
	 * ע�⣺���ؽ��������״�ṹ���������ù�����ת��Ϊҳ���������״�ṹ
	 * @param enumPaperCheckType ָ������������ͣ�ҵ��ָ���ҽ�����˻򵵰������ҽ������
	 * @param userID ��¼�û����
	 * @param archivesTypeEx ����ͳ�ƽ���������ݵ���ײ㵵�����࣬�Ե�����������Ϊ���ϵļ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean statPaperCheckBackCountAndInputCheckReslut(int [] userIDs,Map<Integer, ArchivesTypeEx> archivesTypeExs,ErrInfo pErrInfo);
	
	/**
	 * ����ָ������͹�����״̬�µĵ�����Ϣ
	 * @param userIDs ������Ա�ı������(��ֹ�д��������)
	 * @param archivesType ָ����������
	 * @param enumWorkFlowStatus ָ��������״̬
	 * @param archivesInfos ��ѯ�ɹ��󷵻صĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs,ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,DataPageInfo dataPageInfo,String userType, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo);
	
	/**
	 * ͳ�Ƶ�ǰ�����е���������<br/>
	 * ���ù��ܣ��γɲ�����ʾ��ǰ�ƽ��嵥�ĵ�����Ϣ������<br/><br/>
	 *  �߼���
	 * 1�������û���ǰ���ƽ����� <br/>
	 * 2��ͳ��������<br/>
	 * @param userIDs ������Ա�ı��
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime ������ʱ��
	 * @param archivesInfosSum ���صĵ���������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean staArchivesInfosSumByCurrentTransferBat(int userID ,IntegerEx archivesInfosSum,boolean insideFlag,ErrInfo pErrInfo);
	
	/**
	 * ��ӵ�����Ϣ��ָ���û���ʵ�ﵵ���ƽ��嵥��<br><br>
	 * ���ù��ܣ������ڵ����γɲ��ŵĵ����ƽ�ҵ��   <br/><br>
	 * �߼���
	 * 1�������û���ǰ���ƽ�����û���򴴽� <br/>
	 * 2����������Ϣ���Ƶ�������ϸ����<br/>
	 * 3�����������д˷���ĵ�������<br/>
	 * 4�����µ�����Ϣ������״̬<br/>
	 * @param userInfo ��¼�û���Ϣ
	 * @param archivesType ������������
	 * @param archivesInfos Ҫ������ƽ��嵥�ĵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addToPaperTransferBatchForOutside(UserInfo userInfo,ArchivesType archivesType,List<ArchivesInfo> archivesInfos,boolean insideFlag,ErrInfo pErrInfo);
	
	/**
	 * ���ָ�����ƽ����Σ������γɲ��ŵĵ����ƽ����Σ���ҵ��ָ���ҵ�ָ���û���ʵ�ﵵ���ƽ��嵥��<br>
	 * ������ҵ��ָ�����򵵰������ҽ��е����ƽ���ҵ��
	 * @param userID ָ���û��ı��
	 * @param paperTansferSubBatches ָ��Ҫ����Щ�����Σ������γɲ��ŵĵ����ƽ����Σ��ĵ���������ƽ��嵥��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addToPaperTransferBatchForIntside(UserInfo userInfo, String [] paperTransferBatchNos,ErrInfo pErrInfo);
	
	/**
	 * ��������Ϣ��ָ���û���ʵ�ﵵ���ƽ��嵥���Ƴ�<br><br>
	 * ���ù��ܣ������ڵ����γɲ��ŵĵ����ƽ�ҵ��   <br/><br>
	 * �߼���
	 * 1��ɾ�������е�ָ���ĵ�����Ϣ��¼ <br/>
	 * 2�����µ�����Ϣ������״̬ <br/>
	 * 3�������û���ǰ�����е������ڵķ����µ��������� <br/>
	 * @param nBXH Ҫ�Ƴ��ĵ������ڲ����
	 * @param archivesType ָ��Ҫ�Ƴ��ĵ����ĵ�������
	 * @param transferBatNo ָ��Ҫ�Ƴ��ĵ������ڵ�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean removeArchiveSInfoFromQDList(int nBXH, ArchivesType archivesType, String transferBatNo,int userID,EnumWorkFlowStatus enumWorkFlowStatus,ErrInfo pErrInfo);
	
	/**
	 * �����γɲ���ȷ��Ҫ�ƽ�ָ�����ε�ʵ�ﵵ��<br>
	 * ���ù��ܣ������γɲ���ȷ��Ҫ�ƽ�ָ�����ε�ʵ�ﵵ��   <br><br>
	 * �߼���
	 * 1������������Ϣȷ���ƽ�״̬�����������е���������<br/>
	 * 2�����µ�����Ϣ������״̬(�Ȳ�ѯ�����еĵ������࣬�ٸ��ݷ������õ���������״̬) <br/>
	 * @param paperTransferBatNo ʵ�ﵵ���ƽ����α��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean confirmTransferPaperForOutside(String paperTransferBatNo,UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * ҵ��ָ����ȷ��Ҫ�ƽ�ָ�����ε�ʵ�ﵵ��<br>
	 * ���ù��ܣ�ҵ��ָ����ȷ��Ҫ�ƽ�ָ�����ε�ʵ�ﵵ��   <br><br>
	 * �߼���
	 * 1������������Ϣȷ���ƽ�״̬�����������е���������<br/>
	 * 2�����µ�����Ϣ������״̬(�Ȳ�ѯ�����еĵ������࣬�ٸ��ݷ������õ���������״̬) <br/>
	 * @param paperTransferBatNo ʵ�ﵵ���ƽ����α��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean confirmTransferPaperForInside(String paperTransferBatNo,UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * ���������µķ�����Ϣ   ��֪�������ƽ����ε������
	 * @param paperTransferBatchesDetails ���ز��ҳɹ���ĵ����ƽ���ϸ�嵥
	 * @param paperTransferBatchesArchvTypeDetails ���ص����η�����Ϣ����ļ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);
	
	/**
	 * ����ָ�������ƽ��Ĵ���ָ������״̬�Ĺ����ƽ�������Ϣ<br>
	 * ������ҵ��ָ���ҶԸ������γɲ��ŵĹ����ƽ����չ���ָ���������Ž��в���
	 * @param departmentID ָ�����ŵı��
	 * @param enumPaperTransferBatchesDealStatus ָ���ƽ����εĴ���״̬
	 * @param paperTransferBatches ���ز��ҳɹ���ĵ����ƽ�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchesForOutside(int departmentID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * ����ָ�������ƽ�������ָ�������Ĺ����ƽ�������Ϣ<br>
	 * ������ҵ��ָ���ҶԸ������γɲ��ŵĹ����ƽ����չ���ָ���������Ž��в���
	 * @param departmentID ָ�����ŵı��
	 * @param enumPaperTransferBatchesDealStatus ָ���ƽ����εĴ���״̬
	 * @param paperTransferBatchesQueryCondition ��ѯ����
	 * @param paperTransferBatches ���ز��ҳɹ���ĵ����ƽ�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchesForOutside(int departmentID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition,DataPageInfo dataPageInfo,List<PaperTransferBatch> paperTransferBatches, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���û��ƽ��Ĵ���ָ������״̬�Ĺ����ƽ�������Ϣ<br>
	 * �����ڵ��������Ҷ�ҵ��ָ���ҵĹ����ƽ����չ���
	 * @param userID ָ���û��ı��
	 * @param enumPaperTransferBatchesDealStatus ָ���ƽ����εĴ���״̬
	 * @param paperTransferBatches ���ز��ҳɹ���ĵ����ƽ�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchesForInside(int userID,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���û��ƽ��Ĵ���ָ������״̬������ָ�������Ĺ����ƽ�������Ϣ����ҳ��ȡ��<br>
	 * �����ڵ��������Ҷ�ҵ��ָ���ҵĹ����ƽ����չ���
	 * @param userID ָ���û��ı��
	 * @param enumPaperTransferBatchesDealStatus ָ���ƽ����εĴ���״̬
	 * @param paperTransferBatchesQueryCondition ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param paperTransferBatches ���ز��ҳɹ���ĵ����ƽ�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchesForInside(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, DataPageInfo dataPageInfo, List<PaperTransferBatch> paperTransferBatches, boolean insideFlag,ErrInfo pErrInfo);
	
	/**
	 * ����ָ�����κŵĵ����ƽ�������Ϣ �Ա�Ǽ�ʹ��
	 * @param paperTransferBatNo ָ���ĵ����ƽ�����
	 * @param paperTransferBatch ���ҳɹ��󷵻صĵ����ƽ�������Ϣ����ֱ�ӷ�����PaperTransferBatchesArchvTypeDetails����ֵ��ȡ���ƽ������µĸ���������ĵ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchByBatchNo(String paperTransferBatNo,PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���û���ǰ�ƽ����εķ�����Ϣ
	 * @param userID ������Ա�ı��
	 * @param paperTransferBatchesDetails ���ز��ҳɹ���ĵ����ƽ���ϸ�嵥
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCurrentPaperTransferBatchesArchvTypeDetails(int userID,PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,boolean insideFlag, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���ƽ����κ͵��������µĵ�����ϸ�嵥��Ϣ
	 * @param paperTransferBatNo ָ�����ƽ�����
	 * @param archivesType ָ���ĵ�������
	 * @param paperTransferBatchesDetails ���ز��ҳɹ���ĵ����ƽ���ϸ�嵥
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPaperTransferBatchesDetails(String paperTransferBatNo,ArchivesType archivesType,List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo);
	
	/**
	 * ʵ�ﵵ�����յǼ�
	 * @param paperTransferBatch Ҫ�Ǽǵĵ����ƽ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean registerPaperReceive(UserInfo userInfo ,String batNo,List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���ƽ�������ָ�����������µĵ������ɵ���<br>
	 * ע�⣺ֻ����ָ���ƽ�������ָ�����������µĵ���ȫ�����������ɺ���ܹ�Ϊ�������ͨ���ĵ����������ɵ���
	 * @param paperTransferBatNo ʵ�ﵵ���ƽ����α��
	 * @param archivesType ָ���ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean generateArchivesID(String paperTransferBatNo,ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * ��������������״̬
	 * @param paperTransferBatNo ָ��Ҫ���õ�����
	 * @param archivesType ָ�������ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkFinish(String paperTransferBatNo, ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * �������ͨ��<br>
	 * ��ָ���������ý������ͨ���ı�־
	 * @param userID �����Ա�ı��
	 * @param archivesType ָ�����������ĵ�������
	 * @param archivesInfo ָ���ĵ�������NBXH���Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean paperCheckPass(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,String batNo,EnumCheckResult enumCheckResult,ErrInfo pErrInfo);
	
	/**
	 * ������˴��<br>
	 * ��ָ���������ý�����˴�صı�־
	 * @param userID �����Ա�ı��
	 * @param archivesType ָ�����������ĵ�������
	 * @param archivesInfo ָ���ĵ�������NBXH���Ա��븳ֵ
	 * @param backReason ���ԭ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean paperCheckBack(int userID,ArchivesType archivesType,ArchivesInfo archivesInfo,String backReason,String batNo,EnumCheckResult enumCheckResult,ErrInfo pErrInfo);

	/**
	 * ͳ�Ƶ��������ҽ������δͨ��������<br>
	 * ͳ�Ƴ������������½������δͨ���ĵ����������������û���ƽ�ʵ�ﵵ���ĵ������಻��ͳ�ƺͷ��ؽ��<br>
	 * ע�⣺���ؽ��������״�ṹ���������ù�����ת��Ϊҳ���������״�ṹ
	 * @param enumPaperCheckType ָ������������ͣ�ҵ��ָ���ҽ�����˻򵵰������ҽ������
	 * @param userID ��¼�û����
	 * @param archivesTypeEx ����ͳ�ƽ���������ݵ���ײ㵵�����࣬�Ե�����������Ϊ���ϵļ�����ͨ���ö����PaperCheckBackArchivesInfoCount����������ͳ�ƽ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean statPaperCheckBackCount(int [] userIDs,String userType,Map<Integer, ArchivesTypeEx> archivesTypeExs,ErrInfo pErrInfo);
	
	/**
	 * ����ָ�������еı�ǩ<br>
	 * �����ָ�������еı�ǩ���ݣ������ڵ�����ֹ��Χ
	 * @param archivesBoxBarcode ָ������������
	 * @param archivesBoxLabel ���ز��ҳɹ���ĵ����б�ǩ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesBoxLabel(String archivesBoxBarcode,ArchivesBoxLabel archivesBoxLabel,ErrInfo pErrInfo);
	
	/**
	 * ճ���������룺��������Ϣ�뵵��������й���<br>
	 * @param archivesFondsID ����
	 * @param archivesType ָ���ĵ�������
	 * @param barcode ����������
	 * @param archivesInfo ���ع����ɹ���ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean pasteArchivesBarcode(String archivesID,ArchivesType archivesType,String barcode,ArchivesInfo archivesInfo,ErrInfo pErrInfo);
	
	/**
	 * ����װ�У������������뵵����������й���<br>
	 * ������һ�ι��������������
	 * @param archivesInfos Ҫװ�еĵ��������ϣ������Ա����ĵ����������Ա��븳ֵ
	 * @param archivesBoxBarcode �����룬������װ���ָ���ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean archivesBoxing(List<String> barcodes,String archivesBoxBarcode, ErrInfo pErrInfo);

	/**
	 * �����ƶ�������δ��������ĵ����ĵ�һ��
	 * @param batNo
	 * @param archivesTypeID
	 * @param paperTransferBatchesDetail
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID,ArchivesInfo archivesInfo, ErrInfo pErrInfo);
}