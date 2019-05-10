/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������������Ϣ���������ӿڶ���
 * @author Administrator
 *
 */
public interface IArchivesUsePersonInfoManageService {
	/**
	 * ��ӵ�����������Ϣ
	 * @param archivesUsePersonInfo Ҫ��ӵĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 *ɾ��ָ���ĵ�����������Ϣ
	 * @param archivesUsePersonInfo Ҫɾ���ĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * ����ָ���ĵ�����������Ϣ
	 * @param archivesUsePersonInfo Ҫ���µĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * �������еĵ�����������Ϣ
	 * @param archivesUsePersonInfo ���ز��ҳɹ��ĵ�����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ�����������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePersonInfo ���ز��ҳɹ��ĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);
	
	/**
	 * ����Ψ���ֲ��ҵ�����������Ϣ��<br>
	 * ����Ϊ��ʱ��ѯ������������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePersonInfos ���ز��ҳɹ��ĵ�����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByIDCardNo(String IDCardNo,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);

	/**
	 * ����Ψ���ֲ��ҵ�����������Ϣ��<br>
	 * ����Ϊ��ʱ��ѯ������������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePersonInfos ���ز��ҳɹ��ĵ�����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByName(String name,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);
}
