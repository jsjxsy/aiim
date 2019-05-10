/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ģ��������Ľӿڶ���
 *
 */
public interface IOfficialTemplateManageService {

	/**
	 * ���һ���µĹ���ģ��
	 * @param officialTemplate ����ӵĹ���ģ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ���ģ��
	 * @param officialTemplate Ҫɾ���Ĺ���ģ�壬��Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ���ģ��
	 * @param officialTemplate �޸ĺ�Ĺ���ģ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialTemplate(OfficialTemplate officialTemplate, ErrInfo pErrInfo);

	/**
	 * �������еĹ���ģ����Ϣ
	 * @param officialTemplates ���ز��ҳɹ��Ĺ���ģ�弯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialTemplates(OfficialDocType officialDocType,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ���ģ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialTemplate ���ز��ҳɹ��Ĺ���ģ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialTemplateByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo);
	
	/**
	 * ����Ψһ��ʶ���ҹ���ģ����Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialTemplate ���ز��ҳɹ��Ĺ���ģ����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialTemplateByName(OfficialDocType officialDocType,String title,List<OfficialTemplate> officialTemplates, ErrInfo pErrInfo);

}
