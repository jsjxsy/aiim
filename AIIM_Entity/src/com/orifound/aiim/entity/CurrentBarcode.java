package com.orifound.aiim.entity;

    
/**
 * 当前条形码信息表的实体类
 */
public class CurrentBarcode
{
    /**
     * 构造函数
     */
    public CurrentBarcode()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param barcodeType 条形码类型
	* @param currentBarcodeNo 当前条码流水序号
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
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 条形码类型
	 */
	private EnumBarcodeType barcodeType=EnumBarcodeType.NONE;

	/**
	 * 获取属性值：条形码类型
	 * @return 条形码类型
	 */
	public EnumBarcodeType getBarcodeType()
	{
		return barcodeType;
	}

	/**
	 * 设置属性值：条形码类型
	 * @param barcodeType 条形码类型
	 */
	public void setBarcodeType(EnumBarcodeType barcodeType)
	{
		this.barcodeType = barcodeType;
	}

	/**
	 * 当前条码流水序号
	 */
	private int currentBarcodeNo=0;

	/**
	 * 获取属性值：当前条码流水序号
	 * @return 当前条码流水序号
	 */
	public int getCurrentBarcodeNo()
	{
		return currentBarcodeNo;
	}

	/**
	 * 设置属性值：当前条码流水序号
	 * @param currentBarcodeNo 当前条码流水序号
	 */
	public void setCurrentBarcodeNo(int currentBarcodeNo)
	{
		this.currentBarcodeNo = currentBarcodeNo;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CurrentBarcode clone()
	{
		CurrentBarcode item = new CurrentBarcode(barcodeType,currentBarcodeNo);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}
	
	
	/**
	* 从指定对象克隆，复制所有属性值
	* @param currentBarcode 指定的对象源
	*/
	public void cloneFrom(CurrentBarcode fromCurrentBarcode){
		this.barcodeType = fromCurrentBarcode.getBarcodeType();
		this.currentBarcodeNo = fromCurrentBarcode.getCurrentBarcodeNo();
		this.keyInCol = fromCurrentBarcode.getKeyInCol();
		this.tag = fromCurrentBarcode.getTag();		
	}



    
}



