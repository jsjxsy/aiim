package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �ⷿ�̵㹤����Ϣ
 */
public class StocktakingInfo
{
    /**
     * ���캯��
     */
    public StocktakingInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �̵㹤�����
	* @param storeroomID �ⷿ���
	* @param stocktakingDate �̵�����
	* @param stocktakedFlag ���̵��ʶ
	*/
	public StocktakingInfo(int iD,int storeroomID,Date stocktakingDate,boolean stocktakedFlag)
	{
		// Table Name: StocktakingInfo
		// Columns List,Can Used in SELECT SQL: ID,StoreroomID,StocktakingDate,StocktakedFlag
		// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:StocktakingDate,:StocktakedFlag
		// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,StocktakingDate=:StocktakingDate,StocktakedFlag=:StocktakedFlag

		this.iD = iD;
		this.storeroomID = storeroomID;
		this.stocktakingDate = stocktakingDate;
		this.stocktakedFlag = stocktakedFlag;
	}

	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �̵㹤�����
	* @param storeroomID �ⷿ���
	* @param storeroomName �ⷿ����//���Ӳ�����StocktakingInfo��ṹ��û�У������������
	* @param stocktakingDate �̵�����
	* @param stocktakedFlag ���̵��ʶ
	*/
	public StocktakingInfo(int iD,int storeroomID,String storeroomName,Date stocktakingDate,boolean stocktakedFlag)
	{
		// Table Name: StocktakingInfo
		// Columns List,Can Used in SELECT SQL: ID,StoreroomID,StocktakingDate,StocktakedFlag
		// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:StocktakingDate,:StocktakedFlag
		// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,StocktakingDate=:StocktakingDate,StocktakedFlag=:StocktakedFlag

		this.iD = iD;
		this.storeroomID = storeroomID;
		this.storeroomName = storeroomName;
		this.stocktakingDate = stocktakingDate;
		this.stocktakedFlag = stocktakedFlag;
	}
	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
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
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * �̵㹤�����
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���̵㹤�����
	 * @return �̵㹤�����
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���̵㹤�����
	 * @param iD �̵㹤�����
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �ⷿ���
	 */
	private int storeroomID=0;

	/**
	 * ��ȡ����ֵ���ⷿ���
	 * @return �ⷿ���
	 */
	public int getStoreroomID()
	{
		return storeroomID;
	}

	/**
	 * ��������ֵ���ⷿ���
	 * @param storeroomID �ⷿ���
	 */
	public void setStoreroomID(int storeroomID)
	{
		this.storeroomID = storeroomID;
	}
	
	/**
	 * �ⷿ����
	 */
	private String storeroomName = null;
	
	/**
	 * ��ȡ����ֵ���ⷿ����
	 * @return
	 */
	public String getStoreroomName() {
		return storeroomName;
	}

	/**
	 * ��������ֵ���ⷿ����
	 * @param storeroomName
	 */
	public void setStoreroomName(String storeroomName) {
		this.storeroomName = storeroomName;
	}
	/**
	 * �̵�����
	 */
	private Date stocktakingDate;

	/**
	 * ��ȡ����ֵ���̵�����
	 * @return �̵�����
	 */
	public Date getStocktakingDate()
	{
		return stocktakingDate;
	}

	/**
	 * ��������ֵ���̵�����
	 * @param stocktakingDate �̵�����
	 */
	public void setStocktakingDate(Date stocktakingDate)
	{
		this.stocktakingDate = stocktakingDate;
	}

	/**
	 * ���̵��ʶ
	 */
	private boolean stocktakedFlag=false;

	/**
	 * ��ȡ����ֵ�����̵��ʶ
	 * @return 
	 */
	public boolean getStocktakedFlag()
	{
		return stocktakedFlag;
	}

	/**
	 * ��������ֵ�����̵��ʶ
	 * @param stocktakedFlag 
	 */
	public void setStocktakedFlag(boolean stocktakedFlag)
	{
		this.stocktakedFlag = stocktakedFlag;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StocktakingInfo clone()
	{
		StocktakingInfo item = new StocktakingInfo(iD, storeroomID, storeroomName, stocktakingDate, stocktakedFlag);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stocktakingInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(StocktakingInfo stocktakingInfo)
	{
		this.iD = stocktakingInfo.getID();
		this.storeroomID = stocktakingInfo.getStoreroomID();
		this.storeroomName = stocktakingInfo.getStoreroomName();
		this.stocktakingDate = stocktakingInfo.getStocktakingDate();
		this.stocktakedFlag = stocktakingInfo.getStocktakedFlag();
		this.keyInCol = stocktakingInfo.getKeyInCol();
		this.tag = stocktakingInfo.getTag();
	}

    
}



