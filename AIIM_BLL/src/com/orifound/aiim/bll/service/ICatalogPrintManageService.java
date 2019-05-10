package com.orifound.aiim.bll.service;

import java.util.LinkedHashMap;
import java.util.List;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;

/**
 * Ŀ¼��ӡ���������
 *
 */
public interface ICatalogPrintManageService {
	/**
	 * ����Ŀ¼��ӡģ�����Ͳ�ѯ��ӡ��ʾ���������
	 * @param catalogType Ŀ¼��ӡģ������
	 * @param catalogDataItems ������ʾ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDataItemByCatalogType(ArchivesType archivesType, EnumCatalogType catalogType, LinkedHashMap<String, CatalogDataItem> catalogDataItems, ErrInfo pErrInfo);
	
	/**
	 * ����Ŀ¼	��ӡ��ѯ������Ϣ
	 * @param depaermentName	��ӡ����Ŀ¼�Ĳ�������DXBM(�����γɲ���)��YWZD(ҵ��ָ����)��DAGL(����������)
	 * @param paperTransferBatNo	�ƽ����κ�
	 * @param archivesType 	��������
	 * @param archivesInfos ���ص�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
	 * �ƽ��嵥 	��ӡ��ѯ
	 * @param paperTransferBatNo ָ�����ƽ�����
	 * @param archivesType ָ���ĵ�������
	 * @param paperTransferBatchesDetails ���ز��ҳɹ���ĵ����ƽ���ϸ�嵥
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForTransferList(String paperTransferBatNo,ArchivesType archivesType,List<PaperTransferBatchesDetail> paperTransferBatchesDetails,List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo);
	
	/**
	 * ����Ŀ¼ 	��ӡ��ѯ
	 * @param boxBarcode 	������
	 * @param archivesType 	��������
	 * @param archivesInfos ���ص�����Ϣ����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForBoxCatalog(String boxBarcode, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	/**
	 * ����Ŀ¼ ��ӡ��ѯ
	 * @param archivesType 	��������
	 * @param archivesInfo	������Ϣ(�����Ƿ�鵵��Ϣ���Ƿ��е���)
	 * @param archivesInfos ���ص�����Ϣ����
	 * @param pErrInfo 		���ش���ʧ�ܵĴ�����Ϣ
	 * @return 				����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForFileCatalog(ArchivesType archivesType, ArchivesInfo archivesInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
	//����
	boolean findArchivesinfoForFileRetrieval();
	
	/**
 	 * ��Ƥ��ӡ��ѯ	�����ڲ���Ų��ҵ�����Ϣ
 	 * @param pNBXH ָ�����ڲ����
 	 * @param archivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
 	 * @param archivesInfo ���ز��ҳɹ��ĵ�����Ϣ
 	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
 	 * @return ����ɹ�����true�����򷵻�false
 	 */
 	boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo);
	
	/**
	 * ����Ŀ¼ ��ӡ��ѯ
	 * @param officialArchivesTypeID 	��������id
	 * @param archivesInfos 			���ص�����Ϣ����
	 * @param pErrInfo 					���ش���ʧ�ܵĴ�����Ϣ
	 * @return 							����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoForofficialArchivesInfoCatalog(OfficialArchivesType officialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);
}
