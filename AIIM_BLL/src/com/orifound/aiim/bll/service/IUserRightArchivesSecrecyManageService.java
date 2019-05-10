/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

/**
 * �û������ܼ���Ȩ�������ӿ�
 *
 */
public interface IUserRightArchivesSecrecyManageService
{
	/**
	 * Ϊָ���û���ӵ����ܼ�Ȩ��<br>
	 * ������һ����Ӷ�������ܼ�Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesSecrecies �û��ĵ����ܼ�Ȩ�޼��ϣ����Ա����ĵ����ܼ�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRightArchivesSecrecysForUser(UserInfo userInfo,List<UserRightArchivesSecrecy> userRightArchivesSecrecies,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û��Ƴ������ܼ�Ȩ��<br>
	 * ������һ���Ƴ���������ܼ�Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesSecrecies �û��ĵ����ܼ�Ȩ�޼��ϣ����Ա����ĵ����ܼ�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRightArchivesSecrecysForUser(UserInfo userInfo,List<UserRightArchivesSecrecy> userRightArchivesSecrecies,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ���û��ĵ����ܼ�Ȩ��
	 * @param pUserID ָ�����û����
	 * @param archivesSecrecys ���ضԸ��û�ֱ����Ȩ�ĵ����ܼ���Ϣ���ϣ����ܼ������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRightArchivesSecrecysByUserID(int pUserID,Map<Integer,ArchivesSecrecy> archivesSecrecys,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ���û��ĵ����ܼ�Ȩ�޼���
	 * @param pUserID  ָ�����û����
	 * @param userRightArchivesSecrecys ���ضԸ��û�ֱ����Ȩ�ĵ����ܼ���Ϣ���ϣ����ܼ������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findRightArchivesSecrecysByUserID(int pUserID,List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ���û������еĵ����ܼ�Ȩ��
	 * @param pUserID  ָ�����û����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteUserRightArchivesSecrecyByUserID(int pUserID, ErrInfo pErrInfo);
}
