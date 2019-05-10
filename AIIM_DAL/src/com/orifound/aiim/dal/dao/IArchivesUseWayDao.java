package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesUseWay;
import com.orifound.aiim.entity.ErrInfo;

/**
 * TableName���DAO�ӿڶ���
 *
 */
public interface IArchivesUseWayDao {

	/**
	 * Dao�ӿڶ��壺�������е�ArchivesUseWay
	 * @param archivesUseWays ���ز��ҳɹ���ArchivesUseWay����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesUseWay> pArchivesUseWays, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ArchivesUseWay
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUseWay ���ز��ҳɹ���ArchivesUseWay
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesUseWay pArchivesUseWay, ErrInfo pErrInfo);

}
