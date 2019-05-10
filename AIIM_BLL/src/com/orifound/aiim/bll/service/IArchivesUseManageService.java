/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesUseExpiredUserInfo;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.CurrentRequestID;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * �������ù������Ľӿڶ���
 *
 */
public interface IArchivesUseManageService {

	/**
	 * �����µĵ����������뵥���<br>
	 * �����������뵥��Ÿ�ʽΪ��8λ���ڣ���ǰ���ڣ�+5λ��ˮ�ţ����� 2010032400001 
	 * @param currentRequestID ���������ɵĵ����������뵥���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean generateArchivesUseRequestBatchNo(CurrentRequestID currentRequestID,ErrInfo pErrInfo);
	
	/**
	 * �ύ�����������뵥
	 * @param userInfo ��������Ϣ
	 * @param archivesUseRequest �����������뵥��Ϣ
	 * @param archivesUseRequestDetails ��������������ϸ�嵥
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean submitArchivesUseRequest(UserInfo userInfo,ArchivesUseRequest archivesUseRequest,List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���ĵ��������嵥���ɵ���
	 * @param archivesUseRequestDetails ָ���ĵ��������嵥�����ҳɹ��󷵻ص�����Ϣ��ͨ��������Է��ʣ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean generateFetchPaperList(List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���ĵ����������뵥���ɵ���
	 * @param archivesUseRequestDetails ָ���ĵ����������뵥
	 * @param archivesUseRequestDetails ���ز��ҳɹ���ĵ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean generateFetchPaperList(ArchivesUseRequest archivesUseRequest,List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * ��ѯ�����������뵥
	 * @param archivesUseRequestQueryCondition ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesUseRequests ���ز��ҳɹ��ĵ����������뵥
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRequests(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition,DataPageInfo dataPageInfo,List<ArchivesUseRequest> archivesUseRequests,ErrInfo pErrInfo);
	
	/**
	 * �������õǼ�<br>
	 * �����ڽ赵�Ͳ鵵���õǼ�
	 * @param archivesUseRegister Ҫ�Ǽ�������Ϣ���������������˼�ʵ�ﵵ�������嵥��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addArchivesUseRegister(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * �����������õǼ���Ϣ<br>
	 * �����ڽ赵�Ͳ鵵���õǼǵĳ���
	 * @param archivesUseRegister ָ��Ҫ�����ĵ������õǼ���Ϣ����赵�ǼǱ�����Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean unregisterArchivesUse(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * ��������������õǼ���Ϣ<br>
	 * �����ڽ赵�Ͳ鵵���õǼǵĳ���
	 * @param archivesUseRegisters ָ��Ҫ�����ĵ������õǼ���Ϣ���ϣ����Ա�Ľ赵�ǼǱ�����Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean unregisterArchivesUse(List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo);
	
	/**
	 * ��ѯ�������õǼ���Ϣ<br>
	 * �����ڽ赵�Ͳ鵵���õǼ���Ϣ�Ĳ�ѯ
	 * @param archivesUseRegisterQueryCondition ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesUseRegisters ���ز��ҳɹ��ĵ������õǼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRegisters(ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition,DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo);
	
	/**
	 * ���ݵ������õǼǱ�Ų�ѯ�������õǼ���Ϣ<br>
	 * �����ڽ赵�Ͳ鵵���õǼ���Ϣ�Ĳ�ѯ
	 * @param archivesUseRegister ���ز��ҳɹ��ĵ������õǼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRegister(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * ����ָ������״̬�ĵ�������������ϸ��Ϣ
	 * @param enumCheckResult ָ������״̬
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesUseRequestDetails ���ز��ҳɹ��ĵ�������������ϸ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRequestDetails(EnumCheckResult enumCheckResult,DataPageInfo dataPageInfo,List<ArchivesUseRequestDetail> archivesUseRequestDetails,ErrInfo pErrInfo);
	
	/**
	 * ��ָ���ĵ������������������
	 * @param archivesUseRequestDetail ������������������Ϣ�����������ĵ�������������Ϣ�Լ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkArchivesUseRequestDetail(ArchivesUseRequestDetail archivesUseRequestDetail,ErrInfo pErrInfo);
	
	/**
	 * ���ݵ���������ϸ��Ų�ѯ����������Ϣ
	 * @param ArchivesUseOutInfoID ����������ϸ���
	 * @param ArchivesUseOutInfo ���ز�ѯ�ɹ��ĵ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseOutInfoByID(int ArchivesUseOutInfoID,ArchivesUseOutInfo archivesUseOutInfo,ErrInfo pErrInfo);
	
	/**
	 * ��ѯ����ָ�������ĵ���������Ϣ
	 * @param archivesUseInfoQueryCondition ��ѯ����
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesUseOutInfos ���ز�ѯ�ɹ��ĵ���������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseOutInfos(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition,DataPageInfo dataPageInfo,List<ArchivesUseOutInfo> archivesUseOutInfos,ErrInfo pErrInfo);
	
	/**
	 * ����ָ�������ʵ�ﵵ��
	 * @param barcode �������룬�����ǰ������������ļ�������
	 * @param archivesUseOutInfo ��������ɹ���ĵ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean renewArchivesByBarcode(int daysNum,ArchivesUseOutInfo archivesUseOutInfo,ErrInfo pErrInfo);
	
	/**
	 * �黹ָ�������ʵ�ﵵ��
	 * @param archivesUseOutInfo ���ع黹�ɹ���ĵ���������Ϣ ��barcode���Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo,ErrInfo pErrInfo);
	
	/**
	 * ����Ԥ��<br>
	 * �Ѽ�������黹���޵ĵ��������˵Ǽ���Ϣ��ѯ����
	 * @param dayNum ��ǰԤ��������
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesUseOutInfos ���ز��ҳɹ���ĵ���������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findExpiringArchivesUseRegister(int dayNum,DataPageInfo dataPageInfo,List<ArchivesUseRegister> ArchivesUseRegister,ErrInfo pErrInfo);
	
	/**
	 * ������Ҫ���ڴ߻��ĵ��������˵Ǽ���Ϣ
	 * @param dataPageInfo ���ݷ�ҳ��ʾ��Ϣ
	 * @param archivesUseRegisters ���ز��ҳɹ�����Ҫ���ڴ߻��ĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseExpiredUseRegister(DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo);
	
	/**
	 * ���ݵ��������˱�Ų��ҵ�����������Ϣ<br>
	 * �����ڲ鿴���ڴ߻���ϸ��Ϣ��ҵ���ܣ�����Ҫ��ʾ��������ϸ��Ϣ
	 * @param archivesUsePersonID ���������˱��
	 * @param ArchivesUsePersonInfo ���ز��ҳɹ��ĵ�����������ϸ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUsePersonInfoByPersonID(int archivesUsePersonID,ArchivesUsePersonInfo archivesUsePersonInfo,ErrInfo pErrInfo);
	
	/**
	 * ���ݵ��������˱�Ų��ҹ���δ���ĵ����嵥<br>
	 * �����ڲ鿴���ڴ߻���ϸ��Ϣ��ҵ����
	 * @param archivesUseExpiredUserInfo ָ���������ù��ڵ��û���Ϣ������ʵ�����Ͳ����������Ա��븳ֵ
	 * @param archivesUseOutInfos ���ز��ҳɹ��Ĺ���δ�������嵥
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseOutInfosByPersonID(int archivesUsePersonID,List<ArchivesUseOutInfo> archivesUseOutInfos,ErrInfo pErrInfo);
	
	
}
