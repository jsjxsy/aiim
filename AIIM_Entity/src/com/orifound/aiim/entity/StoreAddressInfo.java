package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * �����ϼ�λ����Ϣ���ʵ����
 */
public class StoreAddressInfo
{
    /**
     * ���캯��
     */
    public StoreAddressInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param storeAddressID �ϼ�λ�õĿⷿ�豸���
	* @param archivesBoxBarcode ������
	* @param storeAddressFullName �ϼ�λ����������
	* @param putTime �ϼ�ʱ��
	*/
	public StoreAddressInfo(int storeAddressID,String archivesBoxBarcode,String storeAddressFullName,Date putTime)
	{
		// Table Name: StoreAddressInfo
		// Columns List,Can Used in SELECT SQL: StoreAddressID,ArchivesBoxBarcode,StoreAddressFullName,PutTime
		// Columns List,Can Used in INSERT SQL: :StoreAddressID,:ArchivesBoxBarcode,:StoreAddressFullName,:PutTime
		// Columns List,Can Used in UPDATE SQL: StoreAddressID=:StoreAddressID,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName,PutTime=:PutTime

		this.storeAddressID = storeAddressID;
		this.archivesBoxBarcode = archivesBoxBarcode;
		this.storeAddressFullName = storeAddressFullName;
		this.putTime = putTime;
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
	 * �ϼ�λ�õĿⷿ�豸���
	 */
	private int storeAddressID=0;

	/**
	 * ��ȡ����ֵ���ϼ�λ�õĿⷿ�豸���
	 * @return �ϼ�λ�õĿⷿ�豸���
	 */
	public int getStoreAddressID()
	{
		return storeAddressID;
	}

	/**
	 * ��������ֵ���ϼ�λ�õĿⷿ�豸���
	 * @param storeAddressID �ϼ�λ�õĿⷿ�豸���
	 */
	public void setStoreAddressID(int storeAddressID)
	{
		this.storeAddressID = storeAddressID;
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
	 * �ϼ�ʱ��
	 */
	private Date putTime;

	/**
	 * ��ȡ����ֵ���ϼ�ʱ��
	 * @return �ϼ�ʱ��
	 */
	public Date getPutTime()
	{
		return putTime;
	}

	/**
	 * ��������ֵ���ϼ�ʱ��
	 * @param putTime �ϼ�ʱ��
	 */
	public void setPutTime(Date putTime)
	{
		this.putTime = putTime;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StoreAddressInfo clone()
	{
		StoreAddressInfo item = new StoreAddressInfo(storeAddressID,archivesBoxBarcode,storeAddressFullName,putTime);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param storeAddressInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(StoreAddressInfo storeAddressInfo)
	{
		this.storeAddressID = storeAddressInfo.getStoreAddressID();
		this.archivesBoxBarcode = storeAddressInfo.getArchivesBoxBarcode();
		this.storeAddressFullName = storeAddressInfo.getStoreAddressFullName();
		this.putTime = storeAddressInfo.getPutTime();
		this.keyInCol = storeAddressInfo.getKeyInCol();
		this.tag = storeAddressInfo.getTag();
	}



    
}



