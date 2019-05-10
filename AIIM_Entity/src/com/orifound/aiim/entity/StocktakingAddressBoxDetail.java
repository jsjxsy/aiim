package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �ⷿ�̵���豸λ���뵵������ϸ
 */
public class StocktakingAddressBoxDetail
{
    /**
     * ���캯��
     */
    public StocktakingAddressBoxDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param stocktakingID �̵㹤��������
	* @param storeAddressBarcode ����������
	* @param archivesBoxBarcode ����������
	*/
	public StocktakingAddressBoxDetail(int stocktakingID,String storeAddressBarcode,String archivesBoxBarcode)
	{
		// Table Name: StocktakingAddressBoxDetails
		// Columns List,Can Used in SELECT SQL: StocktakingID,StoreAddressBarcode,ArchivesBoxBarcode
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:StoreAddressBarcode,:ArchivesBoxBarcode
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,StoreAddressBarcode=:StoreAddressBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode

		this.stocktakingID = stocktakingID;
		this.storeAddressBarcode = storeAddressBarcode;
		this.archivesBoxBarcode = archivesBoxBarcode;
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
	 * ����������
	 */
	private String storeAddressBarcode=null;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public String getStoreAddressBarcode()
	{
		return storeAddressBarcode;
	}

	/**
	 * ��������ֵ������������
	 * @param storeAddressBarcode ����������
	 */
	public void setStoreAddressBarcode(String storeAddressBarcode)
	{
		this.storeAddressBarcode = storeAddressBarcode;
	}

	/**
	 * ����������
	 */
	private String archivesBoxBarcode=null;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public String getArchivesBoxBarcode()
	{
		return archivesBoxBarcode;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesBoxBarcode ����������
	 */
	public void setArchivesBoxBarcode(String archivesBoxBarcode)
	{
		this.archivesBoxBarcode = archivesBoxBarcode;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StocktakingAddressBoxDetail clone()
	{
		StocktakingAddressBoxDetail item = new StocktakingAddressBoxDetail(stocktakingID,storeAddressBarcode,archivesBoxBarcode);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stocktakingAddressBoxDetails ָ���Ķ���Դ
	*/
	public void cloneFrom(StocktakingAddressBoxDetail stocktakingAddressBoxDetails)
	{
		this.stocktakingID = stocktakingAddressBoxDetails.getStocktakingID();
		this.storeAddressBarcode = stocktakingAddressBoxDetails.getStoreAddressBarcode();
		this.archivesBoxBarcode = stocktakingAddressBoxDetails.getArchivesBoxBarcode();
		this.keyInCol = stocktakingAddressBoxDetails.getKeyInCol();
		this.tag = stocktakingAddressBoxDetails.getTag();
	}



    
}



