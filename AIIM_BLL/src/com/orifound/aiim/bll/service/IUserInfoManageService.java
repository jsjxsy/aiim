/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * �û���Ϣ�������Ľӿڶ���
 *
 */
public interface IUserInfoManageService {

	/**
	 * ��¼ϵͳ
	 * @param userInfo ��ǰ��¼���û����û������������Ա��븳ֵ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean login(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * ������¼ϵͳ
	 * @param userInfo ���ص�¼�ɹ��������û�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean loginWithAnonymous(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * �˳�ϵͳ
	 * @param userInfo ��ǰ��¼���û�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean logout(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * ��ȡ�����û�
	 * @param userInfo ��ȡ�ɹ����ص������û�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAnonymousUser(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * �����û���Ų����û���Ϣ
	 * @param pUserID ֤������
	 * @param userInfo ���ز��ҳɹ����û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserByUserID(Integer pUserID,UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * ����֤����������û���Ϣ
	 * @param pIDCardNo ֤������
	 * @param userInfo ���ز��ҳɹ����û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserByIDCardNo(String pIDCardNo,UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * �����û���ʵ���������û���Ϣ
	 * @param pIDCardNo �û���ʵ����
	 * @param userInfo ���ز��ҳɹ����û���Ϣ���ϣ��п�����ͬ�������û����ڶ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserByRealName(String realName,List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * ����ϵͳ�������û���Ϣ
	 * @param userInfos ���ز��ҳɹ�������ϵͳ�û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUsers(List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * ����ָ�������µ������û���Ϣ
	 * @param departmentID ���ű��
	 * @param userInfos ����ָ�������µ������û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUsersByDepartmentID(int departmentID,List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * ���һ��ϵͳ�û�
	 * 
	 * @param userInfo Ҫ��ӵ��û�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserInfo(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ����ϵͳ�û�
	 * 
	 * @param userInfo Ҫɾ�����û������û�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserInfo(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * ����ָ����ϵͳ�û���Ϣ
	 * 
	 * @param userInfo Ҫ���µ��û���Ϣ�����û�����ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateUserInfo(UserInfo userInfo,ErrInfo pErrInfo);
	
	/**
	 * ���ָ���û���ָ������<br>
	 * ������һ�����һ���û�
	 * @param userInfo �û���Ϣ
	 * @param departmentInfo ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addUserToDeparment(UserInfo userInfo,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ���ָ���û���ָ������<br>
	 * ������һ����Ӷ���û�
	 * @param userInfos �û���Ϣ����
	 * @param departmentInfo ������Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addUsersToDeparment(List<UserInfo> userInfos,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ָ���û�������Ĳ���<br>
	 * ������һ��ָ��һ������
	 * @param userInfo �û���Ϣ�����û�����ֶα��븳ֵ
	 * @param departmentInfo ������Ϣ���䲿�ű���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserChargeDepartment(UserInfo userInfo,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ָ���û�������Ĳ���<br>
	 * ������һ��ָ���������
	 * @param userInfo �û���Ϣ�����û�����ֶα��븳ֵ
	 * @param departmentInfos ������Ϣ���ϣ����Ա����Ĳ��ű���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveUserChargeDepartments(UserInfo userInfo,List<DepartmentInfo> departmentInfos,ErrInfo pErrInfo);
	
	/**
	 * ȡ���û��벿��֮��ĸ����ϵ<br>
	 * ������һ��ȡ��һ������
	 * @param userInfo �û���Ϣ�����û�����ֶα��븳ֵ
	 * @param departmentInfo ������Ϣ���䲿�ű���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserChargeDepartment(UserInfo userInfo,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ȡ���û��벿��֮��ĸ����ϵ<br>
	 * ������һ��ȡ���������
	 * @param userInfo �û���Ϣ�����û�����ֶα��븳ֵ
	 * @param departmentInfos ������Ϣ���ϣ����Ա����Ĳ��ű���ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteUserChargeDepartments(UserInfo userInfo,List<DepartmentInfo> departmentInfos,ErrInfo pErrInfo);
	
	/**
	 * ��ѯ��������˼���
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo);

	
	/**
	 * ��ѯ���з�����������Ա
	 * @param userInfoQueryCondition ��ѯ����
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos,ErrInfo pErrInfo);


	/**
	 * ����ϵͳ��ɫ�����û���Ϣ
	 * @param esnumSystemRole �û���ɫ
	 * @param userInfos ���ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserBySystemRole(EnumSystemRole esnumSystemRole, List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * 
	 * @param pID
	 * @param userInfos ���ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserChargeUserInfosByUserID(int pID,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	
	/**
	 * ��������δ������û���Ϣ
	 * @param userInfos ���ص��û���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAllUserUnchargeUserInfosByUserID(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo);
	
	/**
	 * ��ѯ���з��������Ĵ�����Ա
	 * @param userInfoQueryCondition ��ѯ����
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserProxyInfosByCondition(Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos,ErrInfo pErrInfo);
	
	/**
	 * �޸�����
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 boolean modifyPassword(UserInfo userInfo, ErrInfo pErrInfo);
	
	/**
	 * ����û����Ƿ�Ψһ
	 * @param userInfos ���ڲ��ҵ��û���Ϣ
	 * @param int ����û����Ѿ����ڷ���true����෵��false
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
    boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);
	
	/**
	 * ���֤�����Ƿ�Ψһ
	 * @param userInfos ���ڲ��ҵ��û���Ϣ
	 * @param exists ���֤�����Ѿ����ڷ���true����෵��false
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo);

	/**
	 * ��ѯδ����ָ����ɫ���û�
	 * @param pRoleID ��ɫ���
	 * @param userInfoQueryCondition ��ѯ����
	 * @param userInfos ������������˼��� 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUseresNotInRoleID(int pRoleID, Map<String,Object> userInfoQueryCondition, DataPageInfo dataPageInfo,List<UserInfo> userInfos , ErrInfo pErrInfo);
}
