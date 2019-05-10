package com.orifound.aiim.entity;

/**
 * 人事档案枚举类型
 * 
 */
public enum EnumPersonalArchivesType
{

//		0：表示不是人事档案
//		1：表示教职工档案
//		2：表示博士后档案
//		3：表示博士生档案
//		4：表示硕士生档案
//		5：表示本科生档案
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	// 表示不是人事档案
	非人事档案(0),
	
	/**
	 * 表示教职工档案
	 */
	教职工档案(1),
	
	/**
	 * 表示博士后档案
	 */
	博士后档案(2),
	
	/**
	 * 表示博士生档案
	 */
	博士生档案(3),
	
	/**
	 * 表示硕士生档案
	 */
	硕士生档案(4),
	
	/**
	 * 表示本科生档案
	 */
	本科生档案(5),


	/**
	 * 表示学生人事档案分类
	 */
	学生人事档案分类(10),
	
	/**
	 * 表示教职工人事档案分类
	 */
	教职工人事档案分类(11);
	
	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumPersonalArchivesType(int enumValue)
	{
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
	public int getEnumValue()
	{
		return enumValue;
	}

	/**
	 * 根据枚举值获取枚举成员
	 * @param enumValue 枚举值
	 * @return 对应该枚举值的枚举成员对象
	 */
	public static EnumPersonalArchivesType getEnumElement(int enumValue)
	{
		EnumPersonalArchivesType pEnumElement = EnumPersonalArchivesType.NONE;

		for (EnumPersonalArchivesType item : EnumPersonalArchivesType.values())
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
