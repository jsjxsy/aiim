package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * �̵㱨��-��������
 */
public class StockReportArchivesCount
{
    /**
     * ���캯��
     */
    public StockReportArchivesCount()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param stocktakingID �̵㹤��������
	* @param archivesTypeID ����������
	* @param paperVolumeCount �̵��ʵ�ﵵ���ܾ���
	* @param paperPieceCount �̵��ʵ�ﵵ���ܼ���
	* @param systemVolumeCount ϵͳ�ڼܵĵ����ܾ���
	* @param systemPieceCount ϵͳ�ڼܵĵ����ܼ���
	*/
	public StockReportArchivesCount(int stocktakingID,int archivesTypeID,int paperVolumeCount,int paperPieceCount,int systemVolumeCount,int systemPieceCount)
	{
		// Table Name: StocktakingReport_ArchivesCount
		// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesTypeID,PaperVolumeCount,PaperPieceCount,SystemVolumeCount,SystemPieceCount
		// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesTypeID,:PaperVolumeCount,:PaperPieceCount,:SystemVolumeCount,:SystemPieceCount
		// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesTypeID=:ArchivesTypeID,PaperVolumeCount=:PaperVolumeCount,PaperPieceCount=:PaperPieceCount,SystemVolumeCount=:SystemVolumeCount,SystemPieceCount=:SystemPieceCount

		this.stocktakingID = stocktakingID;
		this.archivesTypeID = archivesTypeID;
		this.paperVolumeCount = paperVolumeCount;
		this.paperPieceCount = paperPieceCount;
		this.systemVolumeCount = systemVolumeCount;
		this.systemPieceCount = systemPieceCount;
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
	 * �̵��ʵ�ﵵ���ܾ���
	 */
	private int paperVolumeCount=0;

	/**
	 * ��ȡ����ֵ���̵��ʵ�ﵵ���ܾ���
	 * @return �̵��ʵ�ﵵ���ܾ���
	 */
	public int getPaperVolumeCount()
	{
		return paperVolumeCount;
	}

	/**
	 * ��������ֵ���̵��ʵ�ﵵ���ܾ���
	 * @param paperVolumeCount �̵��ʵ�ﵵ���ܾ���
	 */
	public void setPaperVolumeCount(int paperVolumeCount)
	{
		this.paperVolumeCount = paperVolumeCount;
	}

	/**
	 * �̵��ʵ�ﵵ���ܼ���
	 */
	private int paperPieceCount=0;

	/**
	 * ��ȡ����ֵ���̵��ʵ�ﵵ���ܼ���
	 * @return �̵��ʵ�ﵵ���ܼ���
	 */
	public int getPaperPieceCount()
	{
		return paperPieceCount;
	}

	/**
	 * ��������ֵ���̵��ʵ�ﵵ���ܼ���
	 * @param paperPieceCount �̵��ʵ�ﵵ���ܼ���
	 */
	public void setPaperPieceCount(int paperPieceCount)
	{
		this.paperPieceCount = paperPieceCount;
	}

	/**
	 * ϵͳ�ڼܵĵ����ܾ���
	 */
	private int systemVolumeCount=0;

	/**
	 * ��ȡ����ֵ��ϵͳ�ڼܵĵ����ܾ���
	 * @return ϵͳ�ڼܵĵ����ܾ���
	 */
	public int getSystemVolumeCount()
	{
		return systemVolumeCount;
	}

	/**
	 * ��������ֵ��ϵͳ�ڼܵĵ����ܾ���
	 * @param systemVolumeCount ϵͳ�ڼܵĵ����ܾ���
	 */
	public void setSystemVolumeCount(int systemVolumeCount)
	{
		this.systemVolumeCount = systemVolumeCount;
	}

	/**
	 * ϵͳ�ڼܵĵ����ܼ���
	 */
	private int systemPieceCount=0;

	/**
	 * ��ȡ����ֵ��ϵͳ�ڼܵĵ����ܼ���
	 * @return ϵͳ�ڼܵĵ����ܼ���
	 */
	public int getSystemPieceCount()
	{
		return systemPieceCount;
	}

	/**
	 * ��������ֵ��ϵͳ�ڼܵĵ����ܼ���
	 * @param systemPieceCount ϵͳ�ڼܵĵ����ܼ���
	 */
	public void setSystemPieceCount(int systemPieceCount)
	{
		this.systemPieceCount = systemPieceCount;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StockReportArchivesCount clone()
	{
		StockReportArchivesCount item = new StockReportArchivesCount(stocktakingID,archivesTypeID,paperVolumeCount,paperPieceCount,systemVolumeCount,systemPieceCount);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param stockReportArchivesCount ָ���Ķ���Դ
	*/
	public void cloneFrom(StockReportArchivesCount stockReportArchivesCount)
	{
		this.stocktakingID = stockReportArchivesCount.getStocktakingID();
		this.archivesTypeID = stockReportArchivesCount.getArchivesTypeID();
		this.paperVolumeCount = stockReportArchivesCount.getPaperVolumeCount();
		this.paperPieceCount = stockReportArchivesCount.getPaperPieceCount();
		this.systemVolumeCount = stockReportArchivesCount.getSystemVolumeCount();
		this.systemPieceCount = stockReportArchivesCount.getSystemPieceCount();
		this.keyInCol = stockReportArchivesCount.getKeyInCol();
		this.tag = stockReportArchivesCount.getTag();
	}


    
}



