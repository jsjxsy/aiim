package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * ��ǰ�ƽ�������Ϣ���ʵ����
 */
public class CurrentTransferBatNo
{
    /**
     * ���캯��
     */
    public CurrentTransferBatNo()
    {
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param batNoCreateDate �ƽ����δ�������
	* @param currentNo ��ǰ���
	* @param batNoCreateUserID �������κŵ��û�
	* @param currentBatNo ��ǰ�ƽ����κ�
	*/
	public CurrentTransferBatNo(Date batNoCreateDate,int currentNo,int batNoCreateUserID,String currentBatNo)
	{
		// Columns List,Can Used in SELECT SQL: BatNoCreateDate,CurrentNo,BatNoCreateUserID,CurrentBatNo
		// Columns List,Can Used in INSERT SQL: pBatNoCreateDate,pCurrentNo,pBatNoCreateUserID,pCurrentBatNo
		// Columns List,Can Used in UPDATE SQL: BatNoCreateDate=pBatNoCreateDate,CurrentNo=pCurrentNo,BatNoCreateUserID=pBatNoCreateUserID,CurrentBatNo=pCurrentBatNo

		this.batNoCreateDate = batNoCreateDate;
		this.currentNo = currentNo;
		this.batNoCreateUserID = batNoCreateUserID;
		this.currentBatNo = currentBatNo;
	}

	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}
	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}
	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * �ƽ����δ�������
	 */
	private Date batNoCreateDate;

	/**
	 * ��ȡ����ֵ���ƽ����δ�������
	 */
	public Date getBatNoCreateDate()
	{
		return batNoCreateDate;
	}
	/**
	 * ��������ֵ���ƽ����δ�������
	 */
	public void setBatNoCreateDate(Date batNoCreateDate)
	{
		this.batNoCreateDate = batNoCreateDate;
	}

	/**
	 * ��ǰ���
	 */
	private int currentNo=0;

	/**
	 * ��ȡ����ֵ����ǰ���
	 */
	public int getCurrentNo()
	{
		return currentNo;
	}
	/**
	 * ��������ֵ����ǰ���
	 */
	public void setCurrentNo(int currentNo)
	{
		this.currentNo = currentNo;
	}

	/**
	 * �������κŵ��û�
	 */
	private int batNoCreateUserID=0;

	/**
	 * ��ȡ����ֵ���������κŵ��û�
	 */
	public int getBatNoCreateUserID()
	{
		return batNoCreateUserID;
	}
	/**
	 * ��������ֵ���������κŵ��û�
	 */
	public void setBatNoCreateUserID(int batNoCreateUserID)
	{
		this.batNoCreateUserID = batNoCreateUserID;
	}

	/**
	 * ��ǰ�ƽ����κ�
	 */
	private String currentBatNo=null;

	/**
	 * ��ȡ����ֵ����ǰ�ƽ����κ�
	 */
	public String getCurrentBatNo()
	{
		return currentBatNo;
	}
	/**
	 * ��������ֵ����ǰ�ƽ����κ�
	 */
	public void setCurrentBatNo(String currentBatNo)
	{
		this.currentBatNo = currentBatNo;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CurrentTransferBatNo clone()
	{
		CurrentTransferBatNo item = new CurrentTransferBatNo(batNoCreateDate,currentNo,batNoCreateUserID,currentBatNo);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


    
}



