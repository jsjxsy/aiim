package com.orifound.aiim.entity;

import java.util.*;
//ע���������Զ�����  ����Ļ����ϼ���һ��archivesUseRegister
/**
 * ʵ�ﵵ�����ó�ȥ��ϸ���
 */
public class ArchivesUseOutInfo
{
    /**
     * ���캯��
     */
    public ArchivesUseOutInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �������ñ��
	* @param useRegID �赵�ǼǱ��
	* @param borrowFlag ���ı�־
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param archivesBarcode ��������
	* @param title ����
	* @param pageSum ����ҳ��
	* @param shouldReturnDate ��ٹ黹����
	* @param archivesUseRegister ���ӣ����õǼ���Ϣ
	*/
	public ArchivesUseOutInfo(int iD,int useRegID,boolean borrowFlag,int archivesTypeID,int nBXH,String archivesID,String archivesBarcode,String title,int pageSum,Date shouldReturnDate)
	{
		// Table Name: ArchivesUseOutInfo
		// Columns List,Can Used in SELECT SQL: ID,UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate
		// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UseRegID=:UseRegID,BorrowFlag=:BorrowFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,ArchivesBarcode=:ArchivesBarcode,Title=:Title,PageSum=:PageSum,ShouldReturnDate=:ShouldReturnDate

		this.iD = iD;
		this.useRegID = useRegID;
		this.borrowFlag = borrowFlag;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.archivesBarcode = archivesBarcode;
		this.title = title;
		this.pageSum = pageSum;
		this.shouldReturnDate = shouldReturnDate;
	}

	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �������ñ��
	* @param useRegID �赵�ǼǱ��
	* @param borrowFlag ���ı�־
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param archivesBarcode ��������
	* @param title ����
	* @param pageSum ����ҳ��
	* @param shouldReturnDate ��ٹ黹����
	* @param returnDate �黹����
	*/
	public ArchivesUseOutInfo(int iD,int useRegID,boolean borrowFlag,int archivesTypeID,int nBXH,String archivesID,String archivesBarcode,String title,int pageSum,Date shouldReturnDate,Date returnDate)
	{
		// Table Name: ArchivesUseReturnInfo
		// Columns List,Can Used in SELECT SQL: ID,UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate,ReturnDate
		// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate,:ReturnDate
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UseRegID=:UseRegID,BorrowFlag=:BorrowFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,ArchivesBarcode=:ArchivesBarcode,Title=:Title,PageSum=:PageSum,ShouldReturnDate=:ShouldReturnDate,ReturnDate=:ReturnDate

		this.iD = iD;
		this.useRegID = useRegID;
		this.borrowFlag = borrowFlag;
		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.archivesBarcode = archivesBarcode;
		this.title = title;
		this.pageSum = pageSum;
		this.shouldReturnDate = shouldReturnDate;
		this.returnDate = returnDate;
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
	 * �������ñ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ���������ñ��
	 * @return �������ñ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ���������ñ��
	 * @param iD �������ñ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �赵�ǼǱ��
	 */
	private int useRegID=0;

	/**
	 * ��ȡ����ֵ���赵�ǼǱ��
	 * @return �赵�ǼǱ��
	 */
	public int getUseRegID()
	{
		return useRegID;
	}

	/**
	 * ��������ֵ���赵�ǼǱ��
	 * @param useRegID �赵�ǼǱ��
	 */
	public void setUseRegID(int useRegID)
	{
		this.useRegID = useRegID;
	}

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
	 * �����ڲ����
	 */
	private int nBXH=0;

	/**
	 * ��ȡ����ֵ�������ڲ����
	 * @return �����ڲ����
	 */
	public int getNBXH()
	{
		return nBXH;
	}

	/**
	 * ��������ֵ�������ڲ����
	 * @param nBXH �����ڲ����
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
	 * ����ҳ��
	 */
	private int pageSum=0;

	/**
	 * ��ȡ����ֵ������ҳ��
	 * @return ����ҳ��
	 */
	public int getPageSum()
	{
		return pageSum;
	}

	/**
	 * ��������ֵ������ҳ��
	 * @param pageSum ����ҳ��
	 */
	public void setPageSum(int pageSum)
	{
		this.pageSum = pageSum;
	}

	/**
	 * ��ٹ黹����
	 */
	private Date shouldReturnDate;

	/**
	 * ��ȡ����ֵ����ٹ黹����
	 * @return ��ٹ黹����
	 */
	public Date getShouldReturnDate()
	{
		return shouldReturnDate;
	}

	/**
	 * ��������ֵ����ٹ黹����
	 * @param shouldReturnDate ��ٹ黹����
	 */
	public void setShouldReturnDate(Date shouldReturnDate)
	{
		this.shouldReturnDate = shouldReturnDate;
	}
	
	
	/**
	 * �黹����
	 */
	private Date returnDate;

	/**
	 * ��ȡ����ֵ���黹����
	 * @return �黹����
	 */
	public Date getReturnDate()
	{
		return returnDate;
	}

	/**
	 * ��������ֵ���黹����
	 * @param returnDate �黹����
	 */
	public void setReturnDate(Date returnDate)
	{
		this.returnDate = returnDate;
	}

	
	
	/**
	 * ʵ�ﵵ�����õǼ�
	 */
	private ArchivesUseRegister archivesUseRegister;
		
	public ArchivesUseRegister getArchivesUseRegister() {
		return archivesUseRegister;
	}

	public void setArchivesUseRegister(ArchivesUseRegister archivesUseRegister) {
		this.archivesUseRegister = archivesUseRegister;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesUseOutInfo clone()
	{
		ArchivesUseOutInfo item = new ArchivesUseOutInfo(iD,useRegID,borrowFlag,archivesTypeID,nBXH,archivesID,archivesBarcode,title,pageSum,shouldReturnDate,returnDate);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		item.setArchivesUseRegister(archivesUseRegister);

		return item;
	}
	


	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUseOutInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUseOutInfo archivesUseOutInfo)
	{
		this.iD = archivesUseOutInfo.getID();
		this.useRegID = archivesUseOutInfo.getUseRegID();
		this.borrowFlag = archivesUseOutInfo.getBorrowFlag();
		this.archivesTypeID = archivesUseOutInfo.getArchivesTypeID();
		this.nBXH = archivesUseOutInfo.getNBXH();
		this.archivesID = archivesUseOutInfo.getArchivesID();
		this.archivesBarcode = archivesUseOutInfo.getArchivesBarcode();
		this.title = archivesUseOutInfo.getTitle();
		this.pageSum = archivesUseOutInfo.getPageSum();
		this.shouldReturnDate = archivesUseOutInfo.getShouldReturnDate();
		this.keyInCol = archivesUseOutInfo.getKeyInCol();
		this.tag = archivesUseOutInfo.getTag();
		this.returnDate = archivesUseOutInfo.returnDate;
		this.archivesUseRegister = archivesUseOutInfo.getArchivesUseRegister();
	}


    
}



