/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ȫ�ڹ������Ľӿڶ���
 *
 */
public interface IArchivesFondsManageService
{
	
	/**
	 * �������еĵ���ȫ����Ϣ
	 * @param archivesFondss ���ز��ҳɹ��ĵ���ȫ�ڼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesFondss(List<ArchivesFonds> archivesFondss,ErrInfo pErrInfo);
	
	/**
	 * ����ȫ�ںŲ��ҵ���ȫ����Ϣ
	 * @param pID ָ����ȫ�����ֱ��
	 * @param archivesFonds ���ز��ҳɹ��ĵ���ȫ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesFondsByID(int pID,ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	/**
	 * ���һ���µĵ���ȫ��
	 * @param archivesFonds ����ӵĵ���ȫ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesFonds(ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���ĵ���ȫ��
	 * @param archivesFonds Ҫɾ���ĵ���ȫ�ڣ���ȫ�ں��ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesFonds(ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	/**
	 * �޸�ָ���ĵ���ȫ��
	 * @param archivesFonds �޸ĺ�ĵ���ȫ����Ϣ����ȫ�ں��ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesFonds(ArchivesFonds archivesFonds,ErrInfo pErrInfo);
	
	
	
	
}
