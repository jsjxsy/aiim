package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ʵ�ﵵ�����ó�ȥ��ϸ�������Ľӿڶ���
 *
 */
public interface IArchivesUseOutInfoManageService {

	/**
	 * ���һ���µ�ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param EntityName ����ӵ�Entity��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ����ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param EntityName Ҫɾ����ʵ�ﵵ�����ó�ȥ��ϸ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * ���µ���Ӧ�黹ʱ��
	 * @param EntityName �޸ĺ��ʵ�ﵵ�����ó�ȥ��ϸ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	/**
	 * 
	 * @param EntityNames ���ز��ҳɹ���Entity����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */

	/**
	 * ����������ѯʵ�ﵵ�����ó�ȥ��ϸ��Ϣ
	 * @param archivesUseInfoQueryCondition ��ѯ��������
	 * @param dataPageInfo ��ҳ��Ϣ
	 * @param archivesUseOutInfos ���ز��ҳɹ���Entity����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return
	 */
	boolean findArchivesUseOutInfosByQueryCondition(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition, DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ����ʵ�ﵵ�����ó�ȥ��ϸ��Ϣ
	 * @param archivesUseOutInfo ���ز��ҳɹ��ĵ������ó�ȥ��ϸ��Ϣ,�䵵�������루ArchivesBarcode�����븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseOutInfoByID( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	
	/**
	 * Dao�ӿڶ��壺���ݵ������������ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param archivesUseOutInfo ���ز��ҳɹ���ʵ�ﵵ�����ó�ȥ��ϸ,�䵵�������루ArchivesBarcode�����븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseOutInfoByArchivesBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	/**
	 * �������õǼǱ�Ų�������ʵ�ﵵ�����ó�ȥ��ϸ��Ϣ
	 * @param EntityNames ���ز��ҳɹ���Entity����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesUseOutInfosByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);
	
	
	/**
	 * Dao�ӿڶ��壺���ݵ���������黹����
	 * @param archivesUseOutInfo ���ز��ҳɹ���ʵ�ﵵ�����ó�ȥ��ϸ,�䵵�������루ArchivesBarcode�����븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean returnArchivesByBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	
//	boolean findArchivesUseOutInfos(
//			ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition,
//			DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseInfos,
//			ErrInfo pErrInfo);
	
	
}
