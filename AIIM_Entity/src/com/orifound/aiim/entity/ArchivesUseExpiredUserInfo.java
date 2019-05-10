/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * �������ù��ڵ��û���Ϣ������<br>
 * �洢��Щ�е�������δ������������Ϣ����δ��������������Ϣ
 *
 */
public class ArchivesUseExpiredUserInfo {

	/**
	 * ��������ʵ����
	 */
	private String userRealName=null;

	/**
	 * ��ȡ����ֵ����������ʵ����
	 * @return ��������ʵ����
	 */
	public String getUserRealName()
	{
		return userRealName;
	}

	/**
	 * ��������ֵ����������ʵ����
	 * @param userRealName ��������ʵ����
	 */
	public void setUserRealName(String userRealName)
	{
		this.userRealName = userRealName;
	}
	
	/**
	 * ������������λ����
	 */
	private String userDepartment=null;

	/**
	 * ��ȡ����ֵ��������������λ����
	 * @return ������������λ����
	 */
	public String getUserDepartment()
	{
		return userDepartment;
	}

	/**
	 * ��������ֵ��������������λ����
	 * @param userDepartment ������������λ����
	 */
	public void setUserDepartment(String userDepartment)
	{
		this.userDepartment = userDepartment;
	}

	/**
	 * ����δ���ĵ�������
	 */
	private int expiredArchivesCount = 0;

	/**
	 * ��������ֵ������δ���ĵ�������
	 * @param expiredArchivesCount ����δ���ĵ�������
	 */
	public void setExpiredArchivesCount(int expiredArchivesCount) {
		this.expiredArchivesCount = expiredArchivesCount;
	}

	/**
	 * ��ȡ����ֵ������δ���ĵ�������
	 * @return ����δ���ĵ�������
	 */
	public int getExpiredArchivesCount() {
		return expiredArchivesCount;
	}

	
}
