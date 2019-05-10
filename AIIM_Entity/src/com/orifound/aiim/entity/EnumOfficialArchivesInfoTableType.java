package com.orifound.aiim.entity;

/**
 * 公文档案主表信息的类型枚举
 * 
 */
public enum EnumOfficialArchivesInfoTableType
{
	
//	1：公文档案登记表
//	2：公文档案原文信息表

	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	// 定义其他枚举成员
	
	/**
	 * 公文档案登记表
	 */
	公文档案登记表(1),
	
	/**
	 * 公文档案原文信息表
	 */
	公文档案原文信息表(2);

	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumOfficialArchivesInfoTableType(int enumValue)
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
	public static EnumOfficialArchivesInfoTableType getEnumElement(int enumValue)
	{
		EnumOfficialArchivesInfoTableType pEnumElement = EnumOfficialArchivesInfoTableType.NONE;

		for (EnumOfficialArchivesInfoTableType item : EnumOfficialArchivesInfoTableType.values())
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
