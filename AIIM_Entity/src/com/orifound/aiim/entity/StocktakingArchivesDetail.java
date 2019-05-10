package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �ⷿ�̵�ĵ������뵵������ϸ
 */
public class StocktakingArchivesDetail
{
    /**
     * ���캯��
     */
    public StocktakingArchivesDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param stocktakingID �̵㹤��������
	* @param archivesBoxBarcode ����������
	* @param archivesBarcode ��������
	*/
	public StocktakingArchivesDetail(int stocktakingID,String archivesBoxBarcode,String archivesBarcode)
	{
		// Table Name: StocktakingArchivesDetails
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,ArchivesBarcode
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:ArchivesBarcode
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,ArchivesBarcode=:ArchivesBarcode

		this.stocktakingID = stocktakingID;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.archivesBarcode = archivesBarcode;
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
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StocktakingArchivesDetail clone()
	{
		StocktakingArchivesDetail item = new StocktakingArchivesDetail(stocktakingID,archivesBoxBarcode,archivesBarcode);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stocktakingArchivesDetails ָ���Ķ���Դ
	*/
	public void cloneFrom(StocktakingArchivesDetail stocktakingArchivesDetails)
	{
		this.stocktakingID = stocktakingArchivesDetails.getStocktakingID();
		this.archivesBoxBarcode = stocktakingArchivesDetails.getArchivesBoxBarcode();
		this.archivesBarcode = stocktakingArchivesDetails.getArchivesBarcode();
		this.keyInCol = stocktakingArchivesDetails.getKeyInCol();
		this.tag = stocktakingArchivesDetails.getTag();
	}



    
}



