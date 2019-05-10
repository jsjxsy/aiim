package com.orifound.aiim.entity;

    
/**
 * ��ǰ��������Ϣ���ʵ����
 */
public class CurrentBarcode
{
    /**
     * ���캯��
     */
    public CurrentBarcode()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param barcodeType ����������
	* @param currentBarcodeNo ��ǰ������ˮ���
	*/
	public CurrentBarcode(EnumBarcodeType barcodeType,int currentBarcodeNo)
	{
		// Columns List,Can Used in SELECT SQL: BarcodeType,CurrentBarcodeNo
		// Columns List,Can Used in INSERT SQL: pBarcodeType,pCurrentBarcodeNo
		// Columns List,Can Used in UPDATE SQL: BarcodeType=pBarcodeType,CurrentBarcodeNo=pCurrentBarcodeNo

		this.barcodeType = barcodeType;
		this.currentBarcodeNo = currentBarcodeNo;
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
	private EnumBarcodeType barcodeType=EnumBarcodeType.NONE;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public EnumBarcodeType getBarcodeType()
	{
		return barcodeType;
	}

	/**
	 * ��������ֵ������������
	 * @param barcodeType ����������
	 */
	public void setBarcodeType(EnumBarcodeType barcodeType)
	{
		this.barcodeType = barcodeType;
	}

	/**
	 * ��ǰ������ˮ���
	 */
	private int currentBarcodeNo=0;

	/**
	 * ��ȡ����ֵ����ǰ������ˮ���
	 * @return ��ǰ������ˮ���
	 */
	public int getCurrentBarcodeNo()
	{
		return currentBarcodeNo;
	}

	/**
	 * ��������ֵ����ǰ������ˮ���
	 * @param currentBarcodeNo ��ǰ������ˮ���
	 */
	public void setCurrentBarcodeNo(int currentBarcodeNo)
	{
		this.currentBarcodeNo = currentBarcodeNo;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CurrentBarcode clone()
	{
		CurrentBarcode item = new CurrentBarcode(barcodeType,currentBarcodeNo);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}
	
	
	/**
	* ��ָ�������¡��������������ֵ
	* @param currentBarcode ָ���Ķ���Դ
	*/
	public void cloneFrom(CurrentBarcode fromCurrentBarcode){
		this.barcodeType = fromCurrentBarcode.getBarcodeType();
		this.currentBarcodeNo = fromCurrentBarcode.getCurrentBarcodeNo();
		this.keyInCol = fromCurrentBarcode.getKeyInCol();
		this.tag = fromCurrentBarcode.getTag();		
	}



    
}



