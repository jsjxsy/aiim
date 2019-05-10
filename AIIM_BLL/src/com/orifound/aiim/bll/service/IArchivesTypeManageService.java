/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����������Ϣ�������Ľӿڶ���
 *
 */
public interface IArchivesTypeManageService
{

	/**
	 * ���һ���µĵ���������Ϣ
	 * @param archivesType ����ӵĵ���������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesType(ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���ĵ���������Ϣ
	 * @param archivesType Ҫɾ���ĵ���������Ϣ���䵵���������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesType(ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���ĵ���������Ϣ
	 * @param archivesType �޸ĺ�ĵ���������Ϣ��Ϣ���䵵���������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateArchivesType(ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * �������еĵ���������Ϣ<br>
	 * ���ص����в�νṹ�ĵ���������Ϣ��һ����Ŀ��������Ŀ�ȶ�������ˣ�����ͨ��childArchivesTypes���Է���
	 * @param archivesTypes ���ز��ҳɹ��Ķ��㵵��������Ϣ���ϣ�һ����Ŀ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesTypes(LinkedHashMap<Integer,ArchivesType> archivesTypes, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ���������Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesType ���ز��ҳɹ��ĵ���������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesTypeByID(int pID, ArchivesType archivesType, ErrInfo pErrInfo);
	
}
