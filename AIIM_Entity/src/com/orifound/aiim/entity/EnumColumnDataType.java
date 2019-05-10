package com.orifound.aiim.entity;

/**
 * 数据项字段的数据类型枚举<br>
 * 字符串、整数、日期时间、布尔值、文本、实数
 * 
 */
public enum EnumColumnDataType
{

	字符串,	
	整数,
	日期时间,
	布尔值,
	文本,
	实数;
	

	/**
	 * 根据枚举值获取枚举成员<br>
	 * 目前支持SQL Server平台，如果将来要支持其他数据库平台可扩展修改该函数中的判断语句
	 * @param ColumnDataTypeText 字段数据类型文本串
	 * @return 对应该枚举值的枚举成员对象
	 */
	public static EnumColumnDataType getEnumElement(String ColumnDataTypeText)
	{
		EnumColumnDataType dataType=EnumColumnDataType.字符串;
		ColumnDataTypeText=ColumnDataTypeText.toLowerCase();
		
		if (ColumnDataTypeText.equals("varchar"))
		{
			dataType=EnumColumnDataType.字符串;
		}
		else if (ColumnDataTypeText.equals("int")) 
		{
			dataType=EnumColumnDataType.整数;
		}
		else if (ColumnDataTypeText.equals("datetime")) 
		{
			dataType=EnumColumnDataType.日期时间;
		}
		else if (ColumnDataTypeText.equals("bit")) 
		{
			dataType=EnumColumnDataType.布尔值;
		}
		else if (ColumnDataTypeText.equals("text")) 
		{
			dataType=EnumColumnDataType.文本;
		}
		else if (ColumnDataTypeText.equals("float")) 
		{
			dataType=EnumColumnDataType.实数;
		}

		return dataType;
	}
}

