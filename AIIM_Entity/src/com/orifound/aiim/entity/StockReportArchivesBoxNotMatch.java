package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �̵㱨��-����װ�в�ƥ��
 */
public class StockReportArchivesBoxNotMatch
{
    /**
     * ���캯��
     */
    public StockReportArchivesBoxNotMatch()
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
	* @param paperBoxBarcode ʵ�����ڵĺ�����
	* @param systemBoxBarcode ϵͳ�й����ĺ�����
	*/
	public StockReportArchivesBoxNotMatch(int stocktakingID,String archivesBarcode,int archivesTypeID,int nBXH,String archivesID,String title,String paperBoxBarcode,String systemBoxBarcode)
	{
		// Table Name: StocktakingReport_ArchivesBoxNotMatch
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,PaperBoxBarcode,SystemBoxBarcode
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:PaperBoxBarcode,:SystemBoxBarcode
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,PaperBoxBarcode=:PaperBoxBarcode,SystemBoxBarcode=:SystemBoxBarcode

		this.stocktakingID = stocktakingID;
		this.archivesBarcode = archivesBarcode;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.paperBoxBarcode = paperBoxBarcode;
		this.systemBoxBarcode = systemBoxBarcode;
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
	 * ʵ�����ڵĺ�����
	 */
	private String paperBoxBarcode=null;

	/**
	 * ��ȡ����ֵ��ʵ�����ڵĺ�����
	 * @return ʵ�����ڵĺ�����
	 */
	public String getPaperBoxBarcode()
	{
		return paperBoxBarcode;
	}

	/**
	 * ��������ֵ��ʵ�����ڵĺ�����
	 * @param paperBoxBarcode ʵ�����ڵĺ�����
	 */
	public void setPaperBoxBarcode(String paperBoxBarcode)
	{
		this.paperBoxBarcode = paperBoxBarcode;
	}

	/**
	 * ϵͳ�й����ĺ�����
	 */
	private String systemBoxBarcode=null;

	/**
	 * ��ȡ����ֵ��ϵͳ�й����ĺ�����
	 * @return ϵͳ�й����ĺ�����
	 */
	public String getSystemBoxBarcode()
	{
		return systemBoxBarcode;
	}

	/**
	 * ��������ֵ��ϵͳ�й����ĺ�����
	 * @param systemBoxBarcode ϵͳ�й����ĺ�����
	 */
	public void setSystemBoxBarcode(String systemBoxBarcode)
	{
		this.systemBoxBarcode = systemBoxBarcode;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StockReportArchivesBoxNotMatch clone()
	{
		StockReportArchivesBoxNotMatch item = new StockReportArchivesBoxNotMatch(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,paperBoxBarcode,systemBoxBarcode);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stockReportArchivesBoxNotMatch ָ���Ķ���Դ
	*/
	public void cloneFrom(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch)
	{
		this.stocktakingID = stockReportArchivesBoxNotMatch.getStocktakingID();
		this.archivesBarcode = stockReportArchivesBoxNotMatch.getArchivesBarcode();
		this.archivesTypeID = stockReportArchivesBoxNotMatch.getArchivesTypeID();
		this.nBXH = stockReportArchivesBoxNotMatch.getNBXH();
		this.archivesID = stockReportArchivesBoxNotMatch.getArchivesID();
		this.title = stockReportArchivesBoxNotMatch.getTitle();
		this.paperBoxBarcode = stockReportArchivesBoxNotMatch.getPaperBoxBarcode();
		this.systemBoxBarcode = stockReportArchivesBoxNotMatch.getSystemBoxBarcode();
		this.keyInCol = stockReportArchivesBoxNotMatch.getKeyInCol();
		this.tag = stockReportArchivesBoxNotMatch.getTag();
	}



    
}



