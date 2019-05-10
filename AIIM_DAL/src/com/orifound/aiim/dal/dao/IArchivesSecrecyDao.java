/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ܼ��ֵ���DAO�ӿڶ���
 *
 */
public interface IArchivesSecrecyDao
{

	/**
	 * Dao�ӿڶ��壺��ӵ����ܼ�
	 * @param archivesSecrecy Ҫ��ӵĵ����ܼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ����ܼ�
	 * @param archivesSecrecy Ҫɾ���ĵ����ܼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ����ܼ�
	 * @param archivesSecrecy Ҫ���µĵ����ܼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ����ܼ�
	 * @param archivesSecrecys ���ز��ҳɹ��ĵ����ܼ����ϣ����ܼ������Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(LinkedHashMap<Integer,ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ����ܼ�
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesSecrecy ���ز��ҳɹ��ĵ����ܼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺��ѯ�����ܼ��ĵ����ܼ������ֵ���Ϣ
	 * @param archivesSecrecy Ҫ���µĵ����ܼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);
}
