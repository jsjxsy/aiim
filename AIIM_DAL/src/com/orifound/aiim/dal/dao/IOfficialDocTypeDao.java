/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������ֵ���DAO�ӿڶ���
 *
 */
public interface IOfficialDocTypeDao
{

	/**
	 * Dao�ӿڶ��壺��ӹ�������
	 * @param officialDocType Ҫ��ӵĹ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĺ�������
	 * @param officialDocType Ҫɾ���Ĺ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĺ�������
	 * @param officialDocType Ҫ���µĹ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĹ�������
	 * @param officialDocTypes ���ز��ҳɹ��Ĺ������ּ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(LinkedHashMap<Integer,OfficialDocType> officialDocTypes, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҹ�������
	 * @param pDocTypeID ָ���Ĺ������ֱ��
	 * @param officialDocType ���ز��ҳɹ��Ĺ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pDocTypeID, OfficialDocType officialDocType, ErrInfo pErrInfo);

}
