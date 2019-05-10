/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.RoleRightArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��ɫ����������Ȩ���DAO�ӿڶ���
 *
 */
public interface IRoleRightArchivesTypeDao
{

	/**
	 * Dao�ӿڶ��壺��ӽ�ɫ����������Ȩ
	 * @param roleRightArchivesType Ҫ��ӵĽ�ɫ����������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ľ�ɫ����������Ȩ
	 * @param roleRightArchivesType Ҫɾ���Ľ�ɫ����������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ľ�ɫ����������Ȩ
	 * @param roleRightArchivesType Ҫ���µĽ�ɫ����������Ȩ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ����ɫ�ĵ���������Ȩ
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param userRightArchivesType ���ز��ҳɹ��Ľ�ɫ����������Ȩ���ϣ��Ե�����������Ϊ���ϼ�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRoleID(int[] pRoleID, LinkedHashMap<Integer,ArchivesType> roleRightArchivesTypes, ErrInfo pErrInfo);

}
