/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.UserRightArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û�����������Ȩ���DAO�ӿڶ���
 *
 */
public interface IUserRightArchivesTypeDao
{

	/**
	 * Dao�ӿڶ��壺����û�����������Ȩ
	 * @param userRightArchivesType Ҫ��ӵ��û�����������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û�����������Ȩ
	 * @param userRightArchivesType Ҫɾ�����û�����������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ�����û�����������Ȩ
	 * @param userRightArchivesType Ҫ���µ��û�����������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���û��ĵ���������Ȩ
	 * @param pUserID ָ�����û����
	 * @param userRightArchivesType ���ز��ҳɹ����û�����������Ȩ���ϣ��Ե�����������Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID, LinkedHashMap<Integer,ArchivesType> userRightArchivesTypes, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���û��µ�����userRightArchivesTypes��Ϣ
	 * @param pUserID ָ�����û����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteByUserID(int pUserID, ErrInfo pErrInfo);
}
