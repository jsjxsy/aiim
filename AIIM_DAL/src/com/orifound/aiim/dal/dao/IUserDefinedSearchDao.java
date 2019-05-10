package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserDefinedSearch;

/**
 * �û��Զ���������ѯ���ݷ��ʽӿ�
 * @author Administrator
 *
 */
public interface IUserDefinedSearchDao {	

	/**
	 * Dao�ӿڶ��壺����û��Զ���������ѯ����
	 * @param pUserDefinedSeach Ҫ��ӵ�UserDefinedSeach
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addUserDefinedSearchs( UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ�����û��Զ���������ѯ����
	 * @param pUserDefinedSeach Ҫɾ����UserDefinedSeach
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);



	/**
	 * Dao�ӿڶ��壺����ָ���û��������û��Զ���������ѯ����
	 * @param userDefinedSearchs ���ز��ҳɹ����û��Զ���������ѯ���󼯺�
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ�����û��Զ���������ѯ����
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserDefinedSeach ���ز��ҳɹ���UserDefinedSeach
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

	
	/**
	 * Dao�ӿڶ��壺����Զ���������ѯ�����Ƿ��Ѿ�����
	 * �������ƴ���ʱ�ͽ��ö��󷵻أ��粻��������ID��0
	 * @param pID ָ����Ψһ��ʶ
	 * @param pUserDefinedSeach ���ز��ҳɹ���UserDefinedSeach
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);
}
