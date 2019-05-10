/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ�����Ϣ���DAO�ӿڶ���
 *
 */
public interface IOfficialArchivesInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӹ��ĵ�����Ϣ
	 * @param officialArchivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param officialArchivesInfo Ҫ��ӵĹ��ĵ�����Ϣ
	 * @param officialArchivesInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ�����Ϣ
	 * @param officialArchivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param officialArchivesInfo Ҫɾ���Ĺ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ�����Ϣ
	 * @param officialArchivesType �����������࣬�䵵�������Ϣ�����Ա�����ֵ
	 * @param officialArchivesInfo Ҫɾ���Ĺ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteParentAndChild(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ�����Ϣ
	 * @param officialArchivesInfo Ҫ���µĹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ�����Ϣ
	 * @param officialArchivesInfo Ҫ���µĹ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesType officialArchiesType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺�������еĹ��ĵ�����Ϣ
	 * @param officialArchivesInfos ���ز��ҳɹ��Ĺ��ĵ�����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(OfficialArchivesType offcialArchivesType,List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ��ĵ�����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialArchivesInfo ���ز��ҳɹ��Ĺ��ĵ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	
	/**
	 * ����Ψһ��ʶ���ҹ��ĵ����ǼǱ� ��Ϣ
	 * @param pNBXH ָ����Ψһ��ʶ
	 * @param offcialArchivesType  ָ���ļ������Ĺ��ĵ������࣬�乫�ĵ������������Ա��븳ֵ
	 * @param officialArchivesInfo ���ز��ҳɹ��Ĺ��ĵ����ǼǱ� ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findByNBXH(int pNBXH, OfficialArchivesType offcialArchivesType, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo);
	/**
	 * 
	 * @param enumOfficialArchivesInfoTableType
	 * @param officialArchivesType
	 * @param officialArchivesInfoQueryConditions
	 * @param dataPageInfo
	 * @param archivesInfos
	 * @param pErrInfo
	 * @return
	 */
	public boolean find(EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType, OfficialArchivesType officialArchivesType,
			List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo, List<OfficialArchivesInfo> archivesInfos, ErrInfo pErrInfo);
	
   /**
    * 
    * @param officialArchivesType
    * @param officialArchivesInfos
    * @param pErrInfo
    * @return
    */
	public boolean batchDelOfficialArchives(OfficialArchivesType officialArchivesType,final List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo);
	
	
}

