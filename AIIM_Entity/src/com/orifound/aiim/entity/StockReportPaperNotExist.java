package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �̵㱨��-ʵ�ﵵ�����ڼ�
 */
public class StockReportPaperNotExist
{
    /**
     * ���캯��
     */
    public StockReportPaperNotExist()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param stocktakingID �̵㹤��������
	* @param archivesBarcode ��������
	* @param archivesTypeID ����������
	* @param nBXH �ڲ����
	* @param archivesID ����
	* @param title ����
	* @param archivesBoxBarcode ������
	* @param storeAddressFullName �ϼ�λ����������
	*/
	public StockReportPaperNotExist(int stocktakingID,String archivesBarcode,int archivesTypeID,int nBXH,String archivesID,String title,String archivesBoxBarcode,String storeAddressFullName)
	{
		// Table Name: StocktakingReport_PaperNotExists
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBoxBarcode,StoreAddressFullName
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBoxBarcode,:StoreAddressFullName
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName

		this.stocktakingID = stocktakingID;
		this.archivesBarcode = archivesBarcode;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeAddressFullName = storeAddressFullName;
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
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * �ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ���ڲ����
	 * @return �ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ���ڲ����
	 * @param nBXH �ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		this.nBXH = nBXH;
	}

	/**
	 * ����
	 */
	private String archivesID=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * ��������ֵ������
	 * @param archivesID ����
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * ����
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ������
	 * @param title ����
	 */
	public void setTitle(String title)
	{
		this.title = title;
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
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StockReportPaperNotExist clone()
	{
		StockReportPaperNotExist item = new StockReportPaperNotExist(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,archivesBoxBarcode,storeAddressFullName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stockReportPaperNotExists ָ���Ķ���Դ
	*/
	public void cloneFrom(StockReportPaperNotExist stockReportPaperNotExists)
	{
		this.stocktakingID = stockReportPaperNotExists.getStocktakingID();
		this.archivesBarcode = stockReportPaperNotExists.getArchivesBarcode();
		this.archivesTypeID = stockReportPaperNotExists.getArchivesTypeID();
		this.nBXH = stockReportPaperNotExists.getNBXH();
		this.archivesID = stockReportPaperNotExists.getArchivesID();
		this.title = stockReportPaperNotExists.getTitle();
		this.archivesBoxBarcode = stockReportPaperNotExists.getArchivesBoxBarcode();
		this.storeAddressFullName = stockReportPaperNotExists.getStoreAddressFullName();
		this.keyInCol = stockReportPaperNotExists.getKeyInCol();
		this.tag = stockReportPaperNotExists.getTag();
	}



    
}



