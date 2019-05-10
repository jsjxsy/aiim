/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 二元表达式操作符枚举类型<br>
 * 等于,包含,介于
 * 
 */
public enum EnumOperatorType {
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),
	
	/**
	 * 等于操作<br>
	 * 适用于精确查询
	 */
	等于(0),
	
	/**
	 * 包含操作<br>
	 * 适用于模糊查询
	 */
	包含(1),
	
	/**
	 * 介于操作<br>
	 * 适用于范围查询
	 */
	介于(2);
	
	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumOperatorType(int enumValue){
		this.enumValue=enumValue;
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
	public static EnumOperatorType getEnumElement(int enumValue) {
		EnumOperatorType pEnumElement=EnumOperatorType.NONE;
		
		for (EnumOperatorType item : EnumOperatorType.values()) {
			if (item.getEnumValue()==enumValue)
			{
				pEnumElement=item;
				break;
			}
		}
		
		return pEnumElement;
	}
}

