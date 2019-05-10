/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_ArchivesType���DAO�ӿڶ���
 *
 */
public interface IArchivesTypeDao {

	/**
	 * DAO�ӿڶ��壺���һ���µĵ�������
	 * @param archivesType Ҫ��ӵĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺ɾ��ָ���ĵ�������
	 * @param archivesType Ҫɾ���ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺����ָ���ĵ�������
	 * @param archivesType Ҫ���µĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺�������е�һ���������ࣨһ����Ŀ��
	 * @param archivesTypes ���ز��ҳɹ��ĵ������༯��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findForLevel1(LinkedHashMap<Integer,ArchivesType> archivesTypes,ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺����ָ�����������������һ����������
	 * @param archivesTypeID ����������
	 * @param childArchivesTypes ���ز��ҳɹ�����һ���������༯�ϣ��Ե�����������Ϊ�ؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findForChild(int archivesTypeID, LinkedHashMap<Integer, ArchivesType> childArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺���ݵ��������Ų���ָ���ĵ���������Ϣ
	 * @param pID ָ���ĵ���������
	 * @param archivesType Ҫɾ���ĵ�������
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, ArchivesType archivesType,ErrInfo pErrInfo);
	
}
