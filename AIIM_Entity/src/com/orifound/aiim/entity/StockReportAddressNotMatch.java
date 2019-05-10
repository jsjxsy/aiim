package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �̵㱨��-�ϼ�λ�ò�ƥ��ĵ���
 */
public class StockReportAddressNotMatch
{
    /**
     * ���캯��
     */
    public StockReportAddressNotMatch()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param stocktakingID �̵㹤��������
	* @param archivesBoxBarcode ������
	* @param paperAddressID ʵ�ﵵ���ϼ�λ�ñ��
	* @param paperAddressFullName ʵ�ﵵ���ϼ�λ����������
	* @param systemAddressID ϵͳ�Ǽǵ��ϼ�λ�ñ��
	* @param systemAddressFullName ϵͳ�еǼǵ��ϼ�λ����������
	*/
	public StockReportAddressNotMatch(int stocktakingID,String archivesBoxBarcode,int paperAddressID,String paperAddressFullName,int systemAddressID,String systemAddressFullName)
	{
		// Table Name: StocktakingReport_AddressNotMatch
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,PaperAddressID,PaperAddressFullName,SystemAddressID,SystemAddressFullName
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:PaperAddressID,:PaperAddressFullName,:SystemAddressID,:SystemAddressFullName
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,PaperAddressID=:PaperAddressID,PaperAddressFullName=:PaperAddressFullName,SystemAddressID=:SystemAddressID,SystemAddressFullName=:SystemAddressFullName

		this.stocktakingID = stocktakingID;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.paperAddressID = paperAddressID;
		this.paperAddressFullName = paperAddressFullName;
		this.systemAddressID = systemAddressID;
		this.systemAddressFullName = systemAddressFullName;
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
	 * ʵ�ﵵ���ϼ�λ�ñ��
	 */
	private int paperAddressID=0;

	/**
	 * ��ȡ����ֵ��ʵ�ﵵ���ϼ�λ�ñ��
	 * @return ʵ�ﵵ���ϼ�λ�ñ��
	 */
	public int getPaperAddressID()
	{
		return paperAddressID;
	}

	/**
	 * ��������ֵ��ʵ�ﵵ���ϼ�λ�ñ��
	 * @param paperAddressID ʵ�ﵵ���ϼ�λ�ñ��
	 */
	public void setPaperAddressID(int paperAddressID)
	{
		this.paperAddressID = paperAddressID;
	}

	/**
	 * ʵ�ﵵ���ϼ�λ����������
	 */
	private String paperAddressFullName=null;

	/**
	 * ��ȡ����ֵ��ʵ�ﵵ���ϼ�λ����������
	 * @return ʵ�ﵵ���ϼ�λ����������
	 */
	public String getPaperAddressFullName()
	{
		return paperAddressFullName;
	}

	/**
	 * ��������ֵ��ʵ�ﵵ���ϼ�λ����������
	 * @param paperAddressFullName ʵ�ﵵ���ϼ�λ����������
	 */
	public void setPaperAddressFullName(String paperAddressFullName)
	{
		this.paperAddressFullName = paperAddressFullName;
	}

	/**
	 * ϵͳ�Ǽǵ��ϼ�λ�ñ��
	 */
	private int systemAddressID=0;

	/**
	 * ��ȡ����ֵ��ϵͳ�Ǽǵ��ϼ�λ�ñ��
	 * @return ϵͳ�Ǽǵ��ϼ�λ�ñ��
	 */
	public int getSystemAddressID()
	{
		return systemAddressID;
	}

	/**
	 * ��������ֵ��ϵͳ�Ǽǵ��ϼ�λ�ñ��
	 * @param systemAddressID ϵͳ�Ǽǵ��ϼ�λ�ñ��
	 */
	public void setSystemAddressID(int systemAddressID)
	{
		this.systemAddressID = systemAddressID;
	}

	/**
	 * ϵͳ�еǼǵ��ϼ�λ����������
	 */
	private String systemAddressFullName=null;

	/**
	 * ��ȡ����ֵ��ϵͳ�еǼǵ��ϼ�λ����������
	 * @return ϵͳ�еǼǵ��ϼ�λ����������
	 */
	public String getSystemAddressFullName()
	{
		return systemAddressFullName;
	}

	/**
	 * ��������ֵ��ϵͳ�еǼǵ��ϼ�λ����������
	 * @param systemAddressFullName ϵͳ�еǼǵ��ϼ�λ����������
	 */
	public void setSystemAddressFullName(String systemAddressFullName)
	{
		this.systemAddressFullName = systemAddressFullName;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StockReportAddressNotMatch clone()
	{
		StockReportAddressNotMatch item = new StockReportAddressNotMatch(stocktakingID,archivesBoxBarcode,paperAddressID,paperAddressFullName,systemAddressID,systemAddressFullName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stockReportAddressNotMatch ָ���Ķ���Դ
	*/
	public void cloneFrom(StockReportAddressNotMatch stockReportAddressNotMatch)
	{
		this.stocktakingID = stockReportAddressNotMatch.getStocktakingID();
		this.archivesBoxBarcode = stockReportAddressNotMatch.getArchivesBoxBarcode();
		this.paperAddressID = stockReportAddressNotMatch.getPaperAddressID();
		this.paperAddressFullName = stockReportAddressNotMatch.getPaperAddressFullName();
		this.systemAddressID = stockReportAddressNotMatch.getSystemAddressID();
		this.systemAddressFullName = stockReportAddressNotMatch.getSystemAddressFullName();
		this.keyInCol = stockReportAddressNotMatch.getKeyInCol();
		this.tag = stockReportAddressNotMatch.getTag();
	}


    
}



