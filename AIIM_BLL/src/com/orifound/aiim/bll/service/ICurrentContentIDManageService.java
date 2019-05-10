/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ǰ����Ź������Ľӿڶ���
 *
 */
public interface ICurrentContentIDManageService
{

	/**
	 * ���һ���µĵ�ǰ�����
	 * @param currentContentID ����ӵĵ�ǰ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ�ǰ����ż������ۼ�1
	 * @param currentContentID ָ���ĵ�ǰ�������Ϣ���䵵��ȫ�ڱ�ź͵������������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * ���ݵ���ȫ�ڱ�ź͵��������Ų��ҵ�ǰ�������Ϣ
	 * @param pArchivesFondsID ָ���ĵ���ȫ�ڱ��
	 * @param pArchivesTypeID ָ���ĵ���������
	 * @param currentContentID ���ز��ҳɹ��ĵ�ǰ�������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCurrentContentIDByPrefix(String archivesIDPrefix, CurrentContentID currentContentID, ErrInfo pErrInfo);

	/**
	 * ��������ȫ�ڵİ������Ϣ
	 * @param currentContentIDs ���ز��ҳɹ��İ������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<CurrentContentID> currentContentIDs, ErrInfo pErrInfo);
}
