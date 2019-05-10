/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * �������õǼ���Ϣ��ѯ����������
 *
 */
public class ArchivesUseRegisterQueryCondition {
	
	/**
	 * ���ı�־
	 */
	private boolean borrowFlag=false;

	/**
	 * ��ȡ����ֵ�����ı�־
	 * @return ���ı�־
	 */
	public boolean getBorrowFlag()
	{
		return borrowFlag;
	}

	/**
	 * ��������ֵ�����ı�־
	 * @param borrowFlag ���ı�־
	 */
	public void setBorrowFlag(boolean borrowFlag)
	{
		this.borrowFlag = borrowFlag;
	}
	
	/**
	 * ��ʼ��������
	 */
	private Date useDateBegin = null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public Date getUseDateBegin()
	{
		return useDateBegin;
	}

	/**
	 * ��������ֵ����������
	 * @param useDate ��������
	 */
	public void setUseDateBegin(Date useDateBegin)
	{
		this.useDateBegin = useDateBegin;
	}
	
	/**
	 * ��ֹ��������
	 */
	private Date useDateEnd = null;

	/**
	 * ��ȡ����ֵ����ֹ��������
	 * @return ��ֹ��������
	 */
	public Date getUseDateEnd()
	{
		return useDateEnd;
	}

	/**
	 * ��������ֵ����ֹ��������
	 * @param useDate ��ֹ��������
	 */
	public void setUseDateEnd(Date useDateEnd)
	{
		this.useDateEnd = useDateEnd;
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
	
	
}
