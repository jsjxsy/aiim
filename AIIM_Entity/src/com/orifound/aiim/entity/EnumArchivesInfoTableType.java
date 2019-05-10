package com.orifound.aiim.entity;

/**
 * 档案信息表类型的枚举定义
 * 
 */
public enum EnumArchivesInfoTableType
{

//	1：档案归档工作表
//	2：档案归档过程表
//	3：档案归档信息表
//	4：档案原文信息表
//	5：档案全文信息表
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	/**
	 * 档案归档工作表，ArchivesInfoWorking_TypeCode
	 */
	档案归档工作表(1),
	
	/**
	 * 档案归档过程表，ArchivesInfoWorkProcedure_TypeCode
	 */
	档案归档过程表(2),
	
	/**
	 * 档案归档信息表，ArchivesInfoSaved_TypeCode
	 */
	档案归档信息表(3),
	
	/**
	 * 档案原文信息表，ArchivesInfoAttachedFile_TypeCode
	 */
	档案原文信息表(4),
	
	/**
	 * 档案全文信息表，ArchivesInfoAttachedFileFullText_TypeCode
	 */
	档案全文信息表(5);
	

	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumArchivesInfoTableType(int enumValue)
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
	public static EnumArchivesInfoTableType getEnumElement(int enumValue)
	{
		EnumArchivesInfoTableType pEnumElement = EnumArchivesInfoTableType.NONE;

		for (EnumArchivesInfoTableType item : EnumArchivesInfoTableType.values())
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
