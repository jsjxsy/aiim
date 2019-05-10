/**
 * 
 */
package com.orifound.aiim.bll.service;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * Ŀ¼��ӡģ����������������ӿ�
 *
 */
public interface ICatalogDataItemManageService
{
	/**
	 * ����ָ��ʵ�嵵�������µĸ���Ŀ¼��ӡģ����������������<br>
	 * ������������ʵ����൵�������ĵ������಻����<br>
	 * ���ҳɹ���Ŀ¼��ӡģ��������ϣ����ֶ�����Ϊ�ؼ��֣�ֱ�ӹҽ��ڵ�����������µ�Ŀ¼��ӡģ�弯����
	 * @parma archivesType ָ���ĵ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCatalogDataItems(ArchivesType archivesType, ErrInfo pErrInfo);
	
	/**
	 * ����ָ���ĵ��������µĸ���Ŀ¼��ӡģ����������������<br>
	 * �����ڹ��ĵ������࣬������ʵ����൵��������<br>
	 * ���ҳɹ���Ŀ¼��ӡģ��������ϣ����ֶ�����Ϊ�ؼ��֣�ֱ�ӹҽ��ڵ�����������µ�Ŀ¼��ӡģ�弯����
	 * @parma officialArchivesType ָ���Ĺ��ĵ����������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCatalogDataItemsForOfficial(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

}
