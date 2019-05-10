/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����������Ϣ�������Ľӿڶ���
 *
 */
public interface IOfficialDocTypeManageService
{

	/**
	 * ���һ���µĹ���������Ϣ
	 * @param officialDocType ����ӵĹ���������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ĺ���������Ϣ
	 * @param officialDocType Ҫɾ���Ĺ���������Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ĺ���������Ϣ
	 * @param officialDocType �޸ĺ�Ĺ���������Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateOfficialDocType(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * �������еĹ���������Ϣ��Ϣ
	 * @param officialDocTypes ���ز��ҳɹ��Ĺ���������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialDocTypes(LinkedHashMap<Integer,OfficialDocType> officialDocTypes, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҹ���������Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param officialDocType ���ز��ҳɹ��Ĺ���������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findOfficialDocTypeByID(int pID, OfficialDocType officialDocType, ErrInfo pErrInfo);

}
