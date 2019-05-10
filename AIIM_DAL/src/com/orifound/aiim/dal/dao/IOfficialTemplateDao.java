/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ģ����DAO�ӿڶ���
 *
 */
public interface IOfficialTemplateDao {

	/**
	 * Dao�ӿڶ��壺���ģ��
	 * @param officialTemplate Ҫ��ӵ�ģ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ����ģ��
	 * @param officialTemplate Ҫɾ����ģ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ģ��
	 * @param officialTemplate Ҫ���µ�ģ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������е�ģ��
	 * @param officialTemplates ���ز��ҳɹ���ģ�弯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(OfficialDocType officialDocType,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ģ��
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialTemplate ���ز��ҳɹ���ģ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ����ģ��
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialTemplate ���ز��ҳɹ���ģ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByName(OfficialDocType officialDocType,String title,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);
}
