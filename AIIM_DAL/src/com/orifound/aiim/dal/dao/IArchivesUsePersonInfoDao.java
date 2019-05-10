package com.orifound.aiim.dal.dao;

import java.util.List;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;



/**
 * ������������Ϣ���DAO�ӿڶ���
 *
 */
public interface IArchivesUsePersonInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӵ�����������Ϣ
	 * @param archivesUsePersonInfo Ҫ��ӵĵ�����������Ϣ <br>��ӳɹ����IDֵ�����ö���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���ĵ�����������Ϣ
	 * @param archivesUsePersonInfo Ҫɾ���ĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���ĵ�����������Ϣ
	 * @param archivesUsePersonInfo Ҫ���µĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĵ�����������Ϣ
	 * @param archivesUsePersonInfo ���ز��ҳɹ��ĵ�����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҵ�����������Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePersonInfo ���ز��ҳɹ��ĵ�����������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����Ψ���ֲ��ҵ�����������Ϣ��
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePersonInfos ���ز��ҳɹ��ĵ�����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByIDCardNo(String IDCardNo,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψ���ֲ��ҵ�����������Ϣ��
	 * @param pID ָ����Ψһ��ʶ
	 * @param archivesUsePersonInfos ���ز��ҳɹ��ĵ�����������Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByName(String name,List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo);
}

