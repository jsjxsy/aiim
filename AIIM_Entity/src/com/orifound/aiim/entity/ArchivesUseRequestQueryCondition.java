/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * �����������뵥��ѯ����������
 *
 */
public class ArchivesUseRequestQueryCondition {
	
	/**
	 * �����������뵥���
	 */
	private String iD=null;

	/**
	 * ��ȡ����ֵ�������������뵥���
	 * @return �����������뵥���
	 */
	public String getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�������������뵥���
	 * @param iD �����������뵥���
	 */
	public void setID(String iD)
	{
		this.iD = iD;
	}

	/**
	 * ��ʼ����ʱ��
	 */
	private Date requestTimeBegin;

	/**
	 * ��ȡ����ֵ����ʼ����ʱ��
	 * @return ��ʼ����ʱ��
	 */
	public Date getRequestTimeBegin()
	{
		return requestTimeBegin;
	}

	/**
	 * ��������ֵ����ʼ����ʱ��
	 * @param requestTimeBegin ��ʼ����ʱ��
	 */
	public void setRequestTimeBegin(Date requestTimeBegin)
	{
		this.requestTimeBegin = requestTimeBegin;
	}
	
	/**
	 * ��ֹ����ʱ��
	 */
	private Date requestTimeEnd;

	/**
	 * ��ȡ����ֵ����ֹ����ʱ��
	 * @return ��ֹ����ʱ��
	 */
	public Date getRequestTimeEnd() {
		return requestTimeEnd;
	}

	/**
	 * ��������ֵ����ֹ����ʱ��
	 * @param requestTimeEnd ��ֹ����ʱ��
	 */
	public void setRequestTimeEnd(Date requestTimeEnd) {
		this.requestTimeEnd = requestTimeEnd;
	}
	
	/**
	 * ��λ
	 */
	private String userDepartment=null;	

	/**
	 * ��ȡ����ֵ����λ
	 * @return ��λ
	 */
	public String getUserDepartment()
	{
		return userDepartment;
	}

	/**
	 * ��������ֵ����λ
	 * @param userDepartment ��λ
	 */
	public void setUserDepartment(String userDepartment)
	{
		this.userDepartment = userDepartment;
	}

	/**
	 * ��ʵ����
	 */
	private String userRealName=null;

	/**
	 * ��ȡ����ֵ����ʵ����
	 * @return ��ʵ����
	 */
	public String getUserRealName()
	{
		return userRealName;
	}

	/**
	 * ��������ֵ����ʵ����
	 * @param userRealName ��ʵ����
	 */
	public void setUserRealName(String userRealName)
	{
		this.userRealName = userRealName;
	}
	
	/**
	 * ֤������
	 */
	private String iDCardNo=null;

	/**
	 * ��ȡ����ֵ��֤������
	 * @return ֤������
	 */
	public String getIDCardNo()
	{
		return iDCardNo;
	}

	/**
	 * ��������ֵ��֤������
	 * @param iDCardNo ֤������
	 */
	public void setIDCardNo(String iDCardNo)
	{
		this.iDCardNo = iDCardNo;
	}
}
