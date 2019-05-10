package com.orifound.aiim.entity;

/**
 * �ⷿ������Ϣ���ʵ����
 */
public class StoreroomArchivesInfo
{
    /**
     * ���캯��
     */
    public StoreroomArchivesInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeID ����������
	* @param nBXH �����ڲ����
	* @param archivesID ����
	* @param title ����
	* @param archivesBarcode ��������
	* @param archivesBoxBarcode ������
	* @param storeStatus �ݲ�״̬
	*/
	public StoreroomArchivesInfo(int archivesTypeID,int nBXH,String archivesID,String title,String archivesBarcode,String archivesBoxBarcode,EnumStoreStatus storeStatus)
	{
		// Table Name: StoreroomArchivesInfo
		// Columns List,Can Used in SELECT SQL: ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBarcode,ArchivesBoxBarcode,StoreStatus
		// Columns List,Can Used in INSERT SQL: :ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreStatus
		// Columns List,Can Used in UPDATE SQL: ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBarcode=:ArchivesBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreStatus=:StoreStatus

		this.archivesTypeID = archivesTypeID;
		this.nBXH = nBXH;
		this.archivesID = archivesID;
		this.title = title;
		this.archivesBarcode = archivesBarcode;
		this.archivesBoxBarcode = archivesBoxBarcode;
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
	 * ��ȡ����ֵ���ݲ�״̬
	 * @return �ݲ�״̬
	 */
	public void setStoreStatus(EnumStoreStatus storeStatus) {
		this.storeStatus = storeStatus;
	}
	

//	/**
//	 * �ݲ�״̬
//	 */
//	private int storeStatus=0;
//
//	/**
//	 * ��ȡ����ֵ���ݲ�״̬
//	 * @return �ݲ�״̬
//	 */
//	public int getStoreStatus()
//	{
//		return storeStatus;
//	}
//
//	/**
//	 * ��������ֵ���ݲ�״̬
//	 * @param storeStatus �ݲ�״̬
//	 */
//	public void setStoreStatus(int storeStatus)
//	{
//		this.storeStatus = storeStatus;
//	}

	

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StoreroomArchivesInfo clone()
	{
		StoreroomArchivesInfo item = new StoreroomArchivesInfo(archivesTypeID,nBXH,archivesID,title,archivesBarcode,archivesBoxBarcode,storeStatus);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param storeroomArchivesInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(StoreroomArchivesInfo storeroomArchivesInfo)
	{
		this.archivesTypeID = storeroomArchivesInfo.getArchivesTypeID();
		this.nBXH = storeroomArchivesInfo.getNBXH();
		this.archivesID = storeroomArchivesInfo.getArchivesID();
		this.title = storeroomArchivesInfo.getTitle();
		this.archivesBarcode = storeroomArchivesInfo.getArchivesBarcode();
		this.archivesBoxBarcode = storeroomArchivesInfo.getArchivesBoxBarcode();
		this.storeStatus = storeroomArchivesInfo.getStoreStatus();
		this.keyInCol = storeroomArchivesInfo.getKeyInCol();
		this.tag = storeroomArchivesInfo.getTag();
	}


    
}



