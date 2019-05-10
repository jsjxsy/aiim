package com.orifound.aiim.entity;

/**
 * 人事档案导入类型
 * @author Administrator
 *
 */
public enum EnumImportType {

//	1：表示批量导入教职工档案基本信息
	//
//		2：表示批量导入博士后档案基本信息
	//
//		3：表示批量导入博士生档案基本信息
	//
//		4：表示批量导入硕士生档案基本信息
	//
//		5：表示批量导入本科生档案基本信息
	//
//		6：表示批量更新博士生毕业去向信息
	//
//		7：表示批量更新硕士生毕业去向信息
	//
//		8：表示批量更新本科生毕业去向信息
	
	NONE(-1),
	
	教职工档案基本信息(1),
	
	博士后档案基本信息(2),
	
	博士生档案基本信息(3),
	
	硕士生档案基本信息(4),
	
	本科生档案基本信息(5),
	
	博士生毕业去向信息(6),
	
	硕士生毕业去向信息(7),
	
	本科生毕业去向信息(8);
	


	
	/**
	 * 枚举值
	 */
	int enumValue;
	
	/**
	 * 获取属性值：枚举值
	 * 
	 * @return 枚举值
	 */
	public int getEnumValue()
	{
		return enumValue;
	}
	
	/**
	 * 构造函数
	 * 
	 * @param enumValue
	 *            枚举值
	 */
	private EnumImportType(int enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * 根据枚举值获取枚举成员
	 * 
	 * @param enumValue
	 *            枚举值
	 * @return 对应该枚举值的枚举成员对象
	 */
	public static EnumImportType getEnumElement(int enumValue)
	{
		EnumImportType pEnumElement = EnumImportType.NONE;

		for (EnumImportType item : EnumImportType.values())
		{
			if (item.getEnumValue() == enumValue)
			{
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
	
}
