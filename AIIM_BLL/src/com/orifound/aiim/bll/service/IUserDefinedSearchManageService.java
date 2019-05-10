package com.orifound.aiim.bll.service;

	import java.util.List;

	import com.orifound.aiim.entity.UserDefinedSearch;
	import com.orifound.aiim.entity.ErrInfo;

	/**
	 * �û��Զ��������������Ľӿڶ���
	 *
	 */
	public interface IUserDefinedSearchManageService {

		/**
		 * ���һ���µ��û��Զ�������
		 * @param userDefinedSearch ����ӵ��û��Զ���������Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean addUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

		/**
		 * ɾ��ָ�����û��Զ�������
		 * @param userDefinedSearch Ҫɾ�����û��Զ�����������Ψһ��ʶ�ֶα��븳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean deleteUserDefinedSearch(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);
	

		/**
		 * ��ѯָ���û��������Զ���������Ϣ
		 * @param userDefinedSearchs ���ز��ҳɹ����û��Զ�����������
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo);

		/**
		 * ����Ψһ��ʶ�����û��Զ���������Ϣ
		 * @param pID ָ����Ψһ��ʶ
		 * @param userDefinedSearch ���ز��ҳɹ����û��Զ���������Ϣ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);
		
		/**
		 * ����Զ���������ѯ�����Ƿ��Ѿ�����<br>
		 * �������ƴ���ʱ�ͽ��ö��󷵻أ��粻��������ID��0
		 * @param userDefinedSearch �û��Զ���������ѯ����name���Ա��踳ֵ
		 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
		 * @return ����ɹ�����true�����򷵻�false
		 */
		boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo);

	}

