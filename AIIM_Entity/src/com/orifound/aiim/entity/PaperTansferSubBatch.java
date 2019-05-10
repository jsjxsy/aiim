package com.orifound.aiim.entity;

   
/**
 * ֽ�ʵ����ƽ���������Ϣ���ʵ����
 */
public class PaperTansferSubBatch
{
    /**
     * ���캯��
     */
    public PaperTansferSubBatch()
    {
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param subBatNo �����κ�
	*/
	public PaperTansferSubBatch(String subBatNo)
	{
		this.subBatNo = subBatNo;
	}
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param batNo �ƽ����κ�
	* @param subBatNo �����κ�
	*/
	public PaperTansferSubBatch(int iD,String batNo,String subBatNo)
	{
		// Columns List,Can Used in SELECT SQL: ID,BatNo,SubBatNo
		// Columns List,Can Used in INSERT SQL: pID,pBatNo,pSubBatNo
		// Columns List,Can Used in UPDATE SQL: ID=pID,BatNo=pBatNo,SubBatNo=pSubBatNo

		this.iD = iD;
		this.batNo = batNo;
		this.subBatNo = subBatNo;
	}

	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}
	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
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
	 */
	public Object getTag()
	{
		return tag;
	}
	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 */
	public int getID()
	{
		return iD;
	}
	/**
	 * ��������ֵ�����
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �ƽ����κ�
	 */
	private String batNo=null;

	/**
	 * ��ȡ����ֵ���ƽ����κ�
	 */
	public String getBatNo()
	{
		return batNo;
	}
	/**
	 * ��������ֵ���ƽ����κ�
	 */
	public void setBatNo(String batNo)
	{
		this.batNo = batNo;
	}

	/**
	 * �����κ�
	 */
	private String subBatNo=null;

	/**
	 * ��ȡ����ֵ�������κ�
	 */
	public String getSubBatNo()
	{
		return subBatNo;
	}
	/**
	 * ��������ֵ�������κ�
	 */
	public void setSubBatNo(String subBatNo)
	{
		this.subBatNo = subBatNo;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public PaperTansferSubBatch clone()
	{
		PaperTansferSubBatch item = new PaperTansferSubBatch(iD,batNo,subBatNo);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


    
}



