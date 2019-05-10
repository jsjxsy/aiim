/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ɫ�����ܼ���Ȩ���DAO�ӿڶ���
 *
 */
public interface IRoleRightArchivesSecrecyDao
{

	/**
	 * Dao�ӿڶ��壺��ӽ�ɫ�����ܼ���Ȩ
	 * @param roleRightArchivesSecrecy Ҫ��ӵĽ�ɫ�����ܼ���Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(RoleRightArchivesSecrecy roleRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ľ�ɫ�����ܼ���Ȩ
	 * @param roleRightArchivesSecrecy Ҫɾ���Ľ�ɫ�����ܼ���Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(RoleRightArchivesSecrecy roleRightArchivesSecrecy, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ɫ�ĵ����ܼ���Ȩ
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param roleRightArchivesSecrecys ���ز��ҳɹ��Ľ�ɫ�����ܼ���Ȩ���ϣ����ܼ������Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRoleID(int[] pRoleID,Map<Integer,ArchivesSecrecy> roleRightArchivesSecrecys, ErrInfo pErrInfo);


}
