package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������Ŀ�ı��DAO�ӿڶ���
 *
 */
public interface IArchivesUsePurposeDao {
	
	/**
	 * Dao�ӿڶ��壺�������е�ArchivesUsePurose
	 * @param archivesUsePurposes ���ز��ҳɹ���ArchivesUsePurose����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesUsePurpose> pArchivesUsePurposes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ArchivesUsePurose
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePurpose ���ز��ҳɹ���ArchivesUsePurose
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(ArchivesUsePurpose pArchivesUsePurpose, ErrInfo pErrInfo);

}
