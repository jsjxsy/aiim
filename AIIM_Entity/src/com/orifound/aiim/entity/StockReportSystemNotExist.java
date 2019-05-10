package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �̵㱨��-ϵͳ�в��ڼܵĵ���
 */
public class StockReportSystemNotExist
{
    /**
     * ���캯��
     */
    public StockReportSystemNotExist()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param stocktakingID �̵㹤��������
	* @param archivesBarcode ��������
	* @param archivesBoxBarcode ������
	* @param storeAddressFullName �ϼ�λ����������
	* @param storeStatus �ݲ�״̬
	*/
	public StockReportSystemNotExist(int stocktakingID,String archivesBarcode,String archivesBoxBarcode,String storeAddressFullName,EnumStoreStatus storeStatus)
	{
		// Table Name: StocktakingReport_SystemNotExists
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesBoxBarcode,StoreAddressFullName,StoreStatus
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreAddressFullName,:StoreStatus
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName,StoreStatus=:StoreStatus

		this.stocktakingID = stocktakingID;
		this.archivesBarcode = archivesBarcode;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeAddressFullName = storeAddressFullName;
		this.storeStatus = storeStatus;
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
	 * �̵㹤��������
	 */
	private int stocktakingID=0;

	/**
	 * ��ȡ����ֵ���̵㹤��������
	 * @return �̵㹤��������
	 */
	public int getStocktakingID()
	{
		return stocktakingID;
	}

	/**
	 * ��������ֵ���̵㹤��������
	 * @param stocktakingID �̵㹤��������
	 */
	public void setStocktakingID(int stocktakingID)
	{
		this.stocktakingID = stocktakingID;
	}

	/**
	 * ��������
	 */
	private String archivesBarcode=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getArchivesBarcode()
	{
		return archivesBarcode;
	}

	/**
	 * ��������ֵ����������
	 * @param archivesBarcode ��������
	 */
	public void setArchivesBarcode(String archivesBarcode)
	{
		this.archivesBarcode = archivesBarcode;
	}

	/**
	 * ������
	 */
	private String archivesBoxBarcode=null;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public String getArchivesBoxBarcode()
	{
		return archivesBoxBarcode;
	}

	/**
	 * ��������ֵ��������
	 * @param archivesBoxBarcode ������
	 */
	public void setArchivesBoxBarcode(String archivesBoxBarcode)
	{
		this.archivesBoxBarcode = archivesBoxBarcode;
	}

	/**
	 * �ϼ�λ����������
	 */
	private String storeAddressFullName=null;

	/**
	 * ��ȡ����ֵ���ϼ�λ����������
	 * @return �ϼ�λ����������
	 */
	public String getStoreAddressFullName()
	{
		return storeAddressFullName;
	}

	/**
	 * ��������ֵ���ϼ�λ����������
	 * @param storeAddressFullName �ϼ�λ����������
	 */
	public void setStoreAddressFullName(String storeAddressFullName)
	{
		this.storeAddressFullName = storeAddressFullName;
	}

	/**
	 * �ݲ�״̬
	 */
	private EnumStoreStatus storeStatus = EnumStoreStatus.NONE;
	
	/**
	 * ��ȡ����ֵ���ݲ�״̬
	 * @return �ݲ�״̬
	 */
	public EnumStoreStatus getStoreStatus() {
		return storeStatus;
	}

	/**
	 * ��������ֵ���ݲ�״̬
	 * @param storeStatus �ݲ�״̬
	 */	
	public void setStoreStatus(EnumStoreStatus storeStatus) {
		this.storeStatus = storeStatus;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StockReportSystemNotExist clone()
	{
		StockReportSystemNotExist item = new StockReportSystemNotExist(stocktakingID,archivesBarcode,archivesBoxBarcode,storeAddressFullName,storeStatus);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stockReportSystemNotExists ָ���Ķ���Դ
	*/
	public void cloneFrom(StockReportSystemNotExist stockReportSystemNotExists)
	{
		this.stocktakingID = stockReportSystemNotExists.getStocktakingID();
		this.archivesBarcode = stockReportSystemNotExists.getArchivesBarcode();
		this.archivesBoxBarcode = stockReportSystemNotExists.getArchivesBoxBarcode();
		this.storeAddressFullName = stockReportSystemNotExists.getStoreAddressFullName();
		this.storeStatus = stockReportSystemNotExists.getStoreStatus();
		this.keyInCol = stockReportSystemNotExists.getKeyInCol();
		this.tag = stockReportSystemNotExists.getTag();
	}

 
    
}



