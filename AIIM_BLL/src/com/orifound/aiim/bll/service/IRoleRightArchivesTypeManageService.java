/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesType;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫ����������Ȩ�������ӿ�
 *
 */
public interface IRoleRightArchivesTypeManageService
{
	/**
	 * Ϊָ����ɫ��ӵ�������Ȩ��<br>
	 * ������һ�����һ����������Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesType ��ɫ�ĵ�������Ȩ�ޣ��䵵���������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean saveRightArchivesTypeForRole(UserRole userRole,UserRightArchivesType userRightArchivesType,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ����ɫ��ӵ�������Ȩ��
	 * ������һ����Ӷ����������Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesTypes ��ɫ�ĵ�������Ȩ�޼��ϣ����Ա����ĵ����������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean saveRightArchivesTypesForRole(UserRole userRole,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ����ɫ�Ƴ���������Ȩ��<br>
	 * ������һ���Ƴ������������Ȩ��
	 * @param userRole �û���ɫ��Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesTypes ��ɫ�ĵ�������Ȩ�޼��ϣ����Ա����ĵ����������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteRightArchivesTypesForRole(UserRole userRole,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * ��ȡ��ɫ�ĵ�������Ȩ��
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param archivestypes ���ضԸý�ɫֱ����Ȩ�ĵ������༯�ϣ�����״�ṹ�����Ե�����������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightArchivesTypeByRolesID(int[] pRoleID,LinkedHashMap<Integer,ArchivesType> archivestypes,ErrInfo pErrInfo);
	
}
