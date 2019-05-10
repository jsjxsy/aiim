package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����������뵥��Ϣ���DAO�ӿڶ���
 *
 */
public interface IArchivesUseRequestDao {

	/**
	 * Dao�ӿڶ��壺��ӵ����������뵥��Ϣ
	 * @param archivesUseRequest Ҫ��ӵĵ����������뵥��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ����������뵥��Ϣ
	 * @param archivesUseRequest Ҫɾ���ĵ����������뵥��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

	

	/**
	 * Dao�ӿڶ��壺���ݲ�ѯ�����������ߵ����������뵥��Ϣ
	 * @param archivesUseRequests ���ز��ҳɹ��ĵ����������뵥��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByQueryCondition(String querySQL,DataPageInfo dataPageInfo , List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ����������뵥��Ϣ
	 * @param archivesUseRequest ���ز��ҳɹ��ĵ����������뵥��Ϣ ,ID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

}
