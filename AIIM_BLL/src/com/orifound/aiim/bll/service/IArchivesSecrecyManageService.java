/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����ܼ��������Ľӿڶ���
 *
 */
public interface IArchivesSecrecyManageService
{

	/**
	 * ���һ���µĵ����ܼ�
	 * @param archivesSecrecy ����ӵĵ����ܼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ����ܼ�
	 * @param archivesSecrecy Ҫɾ���ĵ����ܼ�����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ����ܼ�
	 * @param archivesSecrecy �޸ĺ�ĵ����ܼ���Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * �������еĵ����ܼ���Ϣ
	 * @param archivesSecrecys ���ز��ҳɹ��ĵ����ܼ����ϣ����ܼ������Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesSecrecys(LinkedHashMap<Integer,ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ����ܼ���Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesSecrecy ���ز��ҳɹ��ĵ����ܼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesSecrecyByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);

	/**
	 * ��ѯ�����ܼ��ĵ����ܼ������ֵ���Ϣ
	 * @param archivesSecrecy Ҫ���µĵ����ܼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo);
}
