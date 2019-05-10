package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * �����������뵥��ϸ���DAO�ӿڶ���
 *
 */
public interface IArchivesUseRequestDetailDao {

	/**
	 * Dao�ӿڶ��壺��ӵ����������뵥��ϸ
	 * @param archivesUseRequestDetail Ҫ��ӵĵ����������뵥��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ����������뵥��ϸ
	 * @param archivesUseRequestDetail Ҫɾ���ĵ����������뵥��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������������ָ���ĵ����������뵥��ϸ
	 * @param archivesUseRequestDetail Ҫ���µĵ����������뵥��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateCheckResult(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺��������δ�����ĵ����������뵥��ϸ
	 * @param archivesUseRequestDetails ���ز��ҳɹ��ĵ����������뵥��ϸ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllNotExamine(DataPageInfo dataPageInfo,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);

	/**
	 *  Dao�ӿڶ��壺���ҵ�һ��δ�����ĵ����������뵥��ϸ
	 * @param recordsNum ��¼����
	 * @param archivesUseRequestDetail �����������뵥��ϸ
	 * @param pErrInfo ���ش�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOneNotExamine(IntegerEx recordsNum, ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ����������뵥��ϸ
	 * @param archivesUseRequestDetail ���ز��ҳɹ��ĵ����������뵥��ϸ ,ID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID( ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������뵥��Ų������е����������뵥��ϸ
	 * @param requestID
	 * @param archivesUseRequestDetails
	 * @param pErrInfo
	 * @return
	 */
	boolean findByRequestID(String requestID ,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo);

}
