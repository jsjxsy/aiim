/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_ArchivesFonds���DAO�ӿڶ���
 *
 */
public interface IArchivesFondsDao
{
	/**
	 * Dao�ӿڶ��壺��ӵ���ȫ��
	 * @param pArchivesFonds Ҫ��ӵĵ���ȫ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ���ȫ��
	 * @param pArchivesFonds Ҫɾ���ĵ���ȫ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ���ȫ��
	 * @param pArchivesFonds Ҫ���µĵ���ȫ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ���ȫ��
	 * @param pArchivesFondss ���ز��ҳɹ��ĵ���ȫ�ڼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesFonds> pArchivesFondss, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ȫ�ںŲ��ҵ���ȫ��
	 * @param pID ָ����ȫ�����ֱ��
	 * @param pArchivesFonds ���ز��ҳɹ��ĵ���ȫ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID,ArchivesFonds pArchivesFonds, ErrInfo pErrInfo);
	
}

