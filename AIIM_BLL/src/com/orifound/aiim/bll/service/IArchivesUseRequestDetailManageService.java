package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * �����������뵥��ϸҵ���߼��ӿ�
 * @author Administrator
 *
 */
public interface IArchivesUseRequestDetailManageService {
	/**
	 * ��ӵ����������뵥��ϸ
	 * @param archivesUseRequestDetail Ҫ��ӵĵ����������뵥��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addArchivesUseRequestDetail(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ����������뵥��ϸ
	 * @param archivesUseRequestDetail Ҫɾ���ĵ����������뵥��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesUseRequestDetail(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);


	/**
	 * ��������δ�����ĵ����������뵥��ϸ
	 * @param archivesUseRequestDetails ���ز��ҳɹ��ĵ����������뵥��ϸ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllNotExamineArchivesUseRequestDetail(DataPageInfo dataPageInfo, List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);
	
	/**
	 * ���ҵ�һ��δ�����ĵ����������뵥��ϸ
	 * @param recordsNum ��¼����
	 * @param archivesUseRequestDetail �����������뵥��ϸ
	 * @param pErrInfo ���ش�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOneNotExamineArchivesUseRequestDetail(IntegerEx recordsNum, ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	
	/**
	 * ����Ψһ��ʶ���ҵ����������뵥��ϸ
	 * @param archivesUseRequestDetail ���ز��ҳɹ��ĵ����������뵥��ϸ ,ID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRequestDetailByID( ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	/**
	 * ����Ψһ��ʶ,�����û��ύ�����ߵ�����������
	 * @param archivesUseRequestDetail ���ز��ҳɹ��ĵ����������뵥��ϸ <br>
	 * ID,checkResult,backReason,checkTime,checkUserID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkArchivesUseRequestDetail( ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	/**
	 * �������뵥��Ų������е����������뵥��ϸ
	 * @param archivesUseRequestDetail ���ز��ҳɹ��ĵ����������뵥��ϸ <br>
	 * ID,checkResult,backReason,checkTime,checkUserID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRequestDetailsByRequestID(String requestID,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);
	
	
}
