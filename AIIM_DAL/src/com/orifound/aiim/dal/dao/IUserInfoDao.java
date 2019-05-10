/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.*;

/**
 * UserInfo���DAO�ӿڶ���
 *
 */
public interface IUserInfoDao {

	/**
	 * DAO�ӿڶ��壺���һ�����û�
	 * @param userInfo Ҫ��ӵ��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺ɾ��ָ���û�
	 * @param userInfo Ҫɾ�����û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserInfo userInfo,ErrInfo pErrInfo);
	
	
	/**
	 * DAO�ӿڶ��壺����ָ���û�
	 * @param userInfo Ҫ���µ��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(UserInfo userInfo,ErrInfo pErrInfo);
	

	/**
	 * DAO�ӿڶ��壺���������û�
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<UserInfo> userInfos, ErrInfo pErrInfo);

	/**
	 * DAO�ӿڶ��壺�����û�������ָ���û�
	 * @param userName �û���
	 * @param userInfo ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByUserName(String userName,UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺�����û���ʵ��������ָ���û�
	 * @param realName �û���
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByRealName(String realName,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺����֤������ָ���û�
	 * @param userName �û���
	 * @param userInfo ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByIDCard(int iDCardTypeID,String iDCardNo,UserInfo userInfo, ErrInfo pErrInfo);
	 

	/**
	 * DAO�ӿڶ��壺���ݲ��ű�Ų���ָ���û���
	 * @param departmentID ���ű��
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByDepartmentID(int departmentID,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺����ϵͳ�������û�
	 * @param userInfo ���ҳɹ��󷵻ص������û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAnonymous(UserInfo userInfo, ErrInfo pErrInfo);
	
	
	/**
	 * DAO�ӿڶ��壺��ѯָ��ϵͳ�����ɫ����Ա��Ϣ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param systemRole ָ����ϵͳ�����ɫ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findBusinessGuids(List<UserInfo> userInfos, EnumSystemRole systemRole, ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺��ѯҵ��ָ����ָ�������е�����ְ��Ա
	 * @param businessGuidIds ҵ��ָ������Աid����
	 * @param userIds ������ְ��ԱId����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo);
	
	/**
	 * DAO�ӿڶ��壺��ѯ���е��������ŵ���Ա����ȥ�ݳ������ݳ���
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllArchivesManagers(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * Dao�ӿڶ��壺��ѯ��������˼���
	 * @param userInfos ���ص��û���Ϣ���� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo);

	
	/**
	 * ��ѯ���з�����������Ա
	 * @dataPageInfo ��ҳ����
	 * @param userInfoQueryCondition ��ѯ����
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param pID
	 * @param userInfo ���ص��û���Ϣ 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserInfoByUserID(int pID,UserInfo userInfo, ErrInfo pErrInfo);

	
	/**
	 * ��ѯ���о��е��������ҽ�ɫ����Ա��Ϣ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesinfoManagers(List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * ب���û�ID��ѯ���б�������û���Ϣ
	 * @param pID
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserChargeUserInfoByUserID(int pID,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	
	/**
	 * ��ѯ���б�������û���Ϣ
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllUserUnchargeUserInfo(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 *���ݲ�ѯ������ѯ��������Ϣ 
	 * @param userInfoQueryCondition ��ѯ����
	 * @param dataPageInfo ��ҳ����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserProxyInfosByCondition(Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo); 
	
	/**
	 * �޸�����
	 * @param userInfos ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updatePassword(UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * ����û����Ƿ�Ψһ
	 * @param userInfos ���ڲ��ҵ��û���Ϣ
	 * @param exists ����û����Ѿ����ڸ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
    boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);
	
	/**
	 * ���֤�����Ƿ�Ψһ
	 * @param userInfos ���ڲ��ҵ��û���Ϣ
	 * @param exists ���֤�����Ѿ����ڸ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);
	
	/**
	 * ��ѯδ����ָ����ɫ���û�
	 * @param pRoleID ��ɫ���
	 * @param dataPageInfo ��ҳ����
	 * @param userInfoQueryCondition ��ѯ����
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUseresNotInRoleID(int pRoleID,Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo);
	

}