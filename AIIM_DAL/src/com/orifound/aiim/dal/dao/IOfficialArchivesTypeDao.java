/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���ĵ��������ֵ���DAO�ӿڶ���
 *
 */
public interface IOfficialArchivesTypeDao
{

	/**
	 * Dao�ӿڶ��壺��ӹ��ĵ�������
	 * @param officialArchivesType Ҫ��ӵĹ��ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ��ĵ�������
	 * @param officialArchivesType Ҫɾ���Ĺ��ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ��ĵ�������
	 * @param officialArchivesType Ҫ���µĹ��ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ��ĵ�������
	 * @param officialArchivesTypes ���ز��ҳɹ��Ĺ��ĵ������༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(LinkedHashMap<Integer,OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ��ĵ�������
	 * @param pOfficialArchivesTypeID ָ���Ĺ��ĵ���������
	 * @param officialArchivesType ���ز��ҳɹ��Ĺ��ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo);

}
