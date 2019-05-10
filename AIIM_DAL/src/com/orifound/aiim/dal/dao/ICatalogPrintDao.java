/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * Ŀ¼��ӡdao�ӿڶ���
 *
 */
public interface ICatalogPrintDao {
	
	/**
	 * ����Ŀ¼��ӡ ��ѯ������Ϣ
	 * @param depaermentName	��ӡ����Ŀ¼�Ĳ�������DXBM(�����γɲ���)��YWZD(ҵ��ָ����)��DAGL(����������)
	 * @param paperTransferBatNo	�ƽ����κ�
	 * @param archivesType 			��������
	 * @param archivesInfos ���ص�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
	 * ����Ŀ¼ ��ӡ��ѯ
	 * @param boxBarcode 	������
	 * @param storeStatus   �ݲ�״̬
	 * @param archivesType 	��������
	 * @param archivesInfos ���ص�����Ϣ����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForBoxCatalog(String boxBarcode, int storeStatus, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
 	 * ��Ƥ��ӡ��ѯ	�����ڲ���Ų��ҵ�����Ϣ
 	 * @param pNBXH ָ�����ڲ����
 	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
 	 * @param archivesInfo ���ز��ҳɹ��ĵ�����Ϣ
 	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
 	 * @return ����ɹ�����true�����򷵻�false
 	 */
 	boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);
}
