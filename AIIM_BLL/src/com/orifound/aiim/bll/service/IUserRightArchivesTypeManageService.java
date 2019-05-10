/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesType;

/**
 * �û�����������Ȩ�������ӿ�
 *
 */
public interface IUserRightArchivesTypeManageService
{
	/**
	 * Ϊָ���û���ӵ�������Ȩ��<br>
	 * ������һ�����һ����������Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesType �û��ĵ�������Ȩ�ޣ��䵵���������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRightArchivesTypeForUser(UserInfo userInfo,UserRightArchivesType userRightArchivesType,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û���ӵ�������Ȩ��<br>
	 * ������һ����Ӷ����������Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesTypes �û��ĵ�������Ȩ�޼��ϣ����Ա����ĵ����������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveRightArchivesTypesForUser(UserInfo userInfo,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * Ϊָ���û��Ƴ���������Ȩ��<br>
	 * ������һ���Ƴ������������Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesTypes �û��ĵ�������Ȩ�޼��ϣ����Ա����ĵ����������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteRightArchivesTypesForUser(UserInfo userInfo,List<UserRightArchivesType> userRightArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * ��ȡָ���û��ĵ�������Ȩ��
	 * @param pUserID ָ�����û����
	 * @param archivestypes ���ضԸ��û�ֱ����Ȩ�ĵ������༯�ϣ�����״�ṹ�����Ե�����������Ϊ���Ϲؼ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findRightArchivesTypeByUserID(int pUserID,LinkedHashMap<Integer,ArchivesType> archivestypes,ErrInfo pErrInfo);
	
	
	/**
	 * Ϊָ���û��Ƴ���������Ȩ��<br>
	 * ������һ���Ƴ������������Ȩ��
	 * @param userInfo �û���Ϣ�������ֶα��븳ֵ
	 * @param userRightArchivesType �û��ĵ�������Ȩ�ޣ����Ա����ĵ����������ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteRightArchivesTypeForUser(UserInfo userInfo, UserRightArchivesType userRightArchivesTypes, ErrInfo pErrInfo);
	
	
	/**
	 * Ϊָ���û��Ƴ����еĵ�������Ȩ��<br>
	 * @param UserID �û����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteRightArchivesTypeByUserID(int UserID, ErrInfo pErrInfo);
	
	
}
