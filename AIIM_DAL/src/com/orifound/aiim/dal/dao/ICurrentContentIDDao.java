/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ǰ�������Ϣ���DAO�ӿڶ���
 *
 */
public interface ICurrentContentIDDao
{

	/**
	 * Dao�ӿڶ��壺��ӵ�ǰ�������Ϣ
	 * @param currentContentID Ҫ��ӵĵ�ǰ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�ǰ�������Ϣ�ۼ�1
	 * @param currentContentID Ҫ���µĵ�ǰ�������Ϣ���䵵��ȫ�ڱ�ź͵������������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݵ���ȫ�ڱ�ź͵��������Ų��ҵ�ǰ�������Ϣ
	 * @param archivesIDPrefix 
	 * @param currentContentID ���ز��ҳɹ��ĵ�ǰ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByPrefix(String archivesIDPrefix,CurrentContentID currentContentID, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���ݵ���ȫ�ڱ�ź͵��������Ų��ҵ�ǰ�������Ϣ
	 * @param currentContentIDs ���ز��ҳɹ��ĵ�ǰ�������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<CurrentContentID> currentContentIDs, ErrInfo pErrInfo);

}
