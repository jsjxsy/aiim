package com.orifound.aiim.dal.dao;

import java.util.List;


import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ʵ�ﵵ�����ó�ȥ��ϸ���DAO�ӿڶ���
 *
 */
public interface IArchivesUseOutInfoDao {

	/**
	 * Dao�ӿڶ��壺���ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param archivesUseOutInfo Ҫ��ӵ�ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	
	/**
	 * Dao�ӿڶ��壺�黹ʵ�ﵵ��
	 * @param archivesUseOutInfo Ҫ��ӵ�ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param archivesUseOutInfo Ҫɾ����ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param archivesUseOutInfo Ҫ���µ�ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺������������ʵ�ﵵ�����ó�ȥ��ϸ<br>�����Ǽ���Ϣ����������Ϣ
	 * @param archivesUseOutInfos ���ز��ҳɹ���ʵ�ﵵ�����ó�ȥ��ϸ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByQueryCondition(String querySQL,DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������õǼǱ�Ų�ѯ����ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param archivesUseOutInfos ���ز��ҳɹ���ʵ�ﵵ�����ó�ȥ��ϸ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo);
	

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUseOutInfo ���ز��ҳɹ���ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺���ݵ������������ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUseOutInfo ���ز��ҳɹ���ʵ�ﵵ�����ó�ȥ��ϸ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByArchivesBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo);

}
