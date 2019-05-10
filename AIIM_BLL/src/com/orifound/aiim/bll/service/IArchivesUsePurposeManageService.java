package com.orifound.aiim.bll.service;

import java.util.List;
import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������Ŀ��ҵ���߼��ӿ�
 * @author Administrator
 *
 */
public interface IArchivesUsePurposeManageService {
	
	/**
	 * �������е�ArchivesUsePurose
	 * @param archivesUsePurposes ���ز��ҳɹ���ArchivesUsePurose����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllArchivesUsePurpose(List<ArchivesUsePurpose> archivesUsePurposes, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ArchivesUsePurose
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePurpose ���ز��ҳɹ���ArchivesUsePurose
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID( ArchivesUsePurpose archivesUsePurpose, ErrInfo pErrInfo);

}
