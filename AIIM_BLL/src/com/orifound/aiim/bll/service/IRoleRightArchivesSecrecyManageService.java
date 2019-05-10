/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫ�����ܼ���Ȩ�������ӿ�
 *
 */
public interface IRoleRightArchivesSecrecyManageService
{
	/**
	 * Ϊָ����ɫ��ӵ����ܼ�Ȩ��<br>
	 * ������һ����Ӷ���ܼ�Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param roleRightArchivesSecrecies ��ɫ�ĵ����ܼ�Ȩ�޼��ϣ����Ա����ĵ����ܼ�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean saveRightArchivesSecrecysForRole(UserRole userRole,List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies,ErrInfo pErrInfo);

	/**
	 * Ϊָ����ɫ�Ƴ������ܼ�Ȩ��<br>
	 * ������һ���Ƴ�����ܼ�Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param roleRightArchivesSecrecies ��ɫ�ĵ����ܼ�Ȩ�޼��ϣ����Ա����ĵ����ܼ�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteRightArchivesSecrecysForRole(UserRole userRole,List<RoleRightArchivesSecrecy> roleRightArchivesSecrecies,ErrInfo pErrInfo);
	
	/**
	 * ��ȡ��ɫ�ĵ����ܼ�Ȩ��
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param archivesSecrecys ���ضԸý�ɫֱ����Ȩ�ĵ����ܼ���Ϣ���ϣ����ܼ������Ϊ���ϵĹؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightArchivesSecrecysByRolesID(int[] pRoleID,Map<Integer,ArchivesSecrecy> archivesSecrecys,ErrInfo pErrInfo);
	
}
