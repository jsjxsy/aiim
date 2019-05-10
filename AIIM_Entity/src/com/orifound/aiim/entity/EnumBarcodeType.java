/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 条形码类型枚举类<br>
 * 条形码类型：1表示库房设备条形码，2表示盒条码，3表示档案条码（文件级或案卷级档案条码）。
 */
public enum EnumBarcodeType {

	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	/**
	 * 库房设备条形码
	 */
	库房设备条形码(1),
	
	/**
	 * 档案盒条码
	 */
	档案盒条形码(2),
	
	/**
	 * 档案条形码（文件级或案卷级档案条码）
	 */
	档案条形码(3);

	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumBarcodeType(int enumValue) {
		this.enumValue = enumValue;
	}

	/**
	 * 枚举值
	 */
	private int enumValue;

	/**
	 * 获取属性值：枚举值
	 * @return 枚举值
	 */
	public int getEnumValue() {
		return enumValue;
	}

	/**
	 * 根据枚举值获取枚举成员
	 * @param enumValue 枚举值
	 * @return 对应该枚举值的枚举成员对象
	 */
	public static EnumBarcodeType getEnumElement(int enumValue) {
		EnumBarcodeType pEnumElement = EnumBarcodeType.NONE;

		for (EnumBarcodeType item : EnumBarcodeType.values()) {
			if (item.getEnumValue() == enumValue) {
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}
