package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
/**
 * ���������������뵥��Ϣҵ���߼��ӿ�
 * @author Administrator
 *
 */
public interface IArchivesUseRequestManageService {
	/**
	 * ��ӵ����������뵥��Ϣ
	 * @param archivesUseRequest Ҫ��ӵĵ����������뵥��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ�������ߵ����������뵥��Ϣ
	 * @param archivesUseRequest Ҫɾ���ĵ����������뵥��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);


	/**
	 * ����������ѯ���ߵ����������뵥��Ϣ
	 * @param archivesUseRequests ���ز��ҳɹ��ĵ����������뵥��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRequestsByCondition(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition, DataPageInfo dataPageInfo, List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ�������ߵ����������뵥��Ϣ
	 * @param archivesUseRequest ���ز��ҳɹ��ĵ����������뵥��Ϣ ,ID���븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseRequestByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo);
}
