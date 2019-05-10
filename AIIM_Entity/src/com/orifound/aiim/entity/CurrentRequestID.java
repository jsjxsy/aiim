package com.orifound.aiim.entity;

import java.util.Date;
    
/**
 * ��ǰ����������������κ���Ϣ���ʵ����
 */
public class CurrentRequestID
{
    /**
     * ���캯��
     */
    public CurrentRequestID()
    {
        // TODO: ���캯���ڲ�����
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param requestDate ����������������
	* @param requestID ���������ˮ��
	*/
	public CurrentRequestID(Date requestDate,int requestID)
	{
		// Columns List,Can Used in SELECT SQL: RequestDate,RequestID
		// Columns List,Can Used in INSERT SQL: pRequestDate,pRequestID
		// Columns List,Can Used in UPDATE SQL: RequestDate=pRequestDate,RequestID=pRequestID

		this.requestDate = requestDate;
		this.requestID = requestID;
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
	 * ����������������
	 */
	private Date requestDate;

	/**
	 * ��ȡ����ֵ������������������
	 * @return ����������������
	 */
	public Date getRequestDate()
	{
		return requestDate;
	}

	/**
	 * ��������ֵ������������������
	 * @param requestDate ����������������
	 */
	public void setRequestDate(Date requestDate)
	{
		this.requestDate = requestDate;
	}

	/**
	 * ���������ˮ��
	 */
	private int requestID=0;

	/**
	 * ��ȡ����ֵ�����������ˮ��
	 * @return ���������ˮ��
	 */
	public int getRequestID()
	{
		return requestID;
	}

	/**
	 * ��������ֵ�����������ˮ��
	 * @param requestID ���������ˮ��
	 */
	public void setRequestID(int requestID)
	{
		this.requestID = requestID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CurrentRequestID clone()
	{
		CurrentRequestID item = new CurrentRequestID(requestDate,requestID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}


    
}



