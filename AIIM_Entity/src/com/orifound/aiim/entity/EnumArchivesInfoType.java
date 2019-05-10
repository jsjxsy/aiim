/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 档案信息的归档类型枚举<br>
 * 文件级档案、案卷级档案、卷内文件
 */
public enum EnumArchivesInfoType {
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),
	
	/**
	 * 文件级档案信息
	 */
	文件级档案(0),
	
	/**
	 * 案卷级档案信息
	 */
	案卷级档案(1),
	
	/**
	 * 卷内文件信息
	 */
	卷内文件(2);
	
	private EnumArchivesInfoType(int enumValue){
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
	public static EnumArchivesInfoType getEnumElement(int enumValue) {
		EnumArchivesInfoType enumArchivesInfoType=EnumArchivesInfoType.NONE;
		
		for (EnumArchivesInfoType item : EnumArchivesInfoType.values()) {
			if (item.getEnumValue()==enumValue)
			{
				enumArchivesInfoType=item;
				break;
			}
		}
		
		return enumArchivesInfoType;
	}
}
