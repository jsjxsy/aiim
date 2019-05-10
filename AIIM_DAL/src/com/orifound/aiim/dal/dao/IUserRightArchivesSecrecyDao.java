/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �û������ܼ���Ȩ���DAO�ӿڶ���
 *
 */
public interface IUserRightArchivesSecrecyDao
{

	/**
	 * Dao�ӿڶ��壺����û������ܼ���Ȩ
	 * @param userRightArchivesSecrecy Ҫ��ӵ��û������ܼ���Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û������ܼ���Ȩ
	 * @param userRightArchivesSecrecy Ҫɾ�����û������ܼ���Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���û��ĵ����ܼ���Ȩ
	 * @param pUserID ָ�����û����
	 * @param userRightArchivesSecrecys ���ز��ҳɹ����û������ܼ���Ȩ���ϣ����ܼ������Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID,Map<Integer,ArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺����ָ���û��ĵ����ܼ���Ȩ
	 * @param pUserID ָ�����û����
	 * @param userRightArchivesSecrecys ���ز��ҳɹ����û������ܼ���Ȩ���ϣ����ܼ������Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserID(int pUserID, List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���û������еĵ����ܼ�Ȩ��
	 * @param pUserID ָ�����û����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteByUserID(int pUserID, ErrInfo pErrInfo);
}
