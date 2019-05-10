package com.orifound.aiim.entity;

/**
 * 数据源固有类别的枚举<br>
 * 档案密级,保管期限,档案全宗,档案形成部门,公文文种等等
 * 
 */
public enum EnumDataSourceInherentType
{

//	1：表示档案密级
//	2：表示保管期限
//	3：表示部门信息
//	4：表示公文文种，公文的种类，如通知、命令等等
//	5：表示档案全宗
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	/**
	 * 档案的机密级别数据源
	 */
	档案密级(1),
	
	/**
	 * 档案的保管期限数据源
	 */
	保管期限(2),
	
	/**
	 * 档案形成部门数据源
	 */
	档案形成部门(3),
	
	/**
	 * 公文文种数据源，公文的种类，如通知、命令等
	 */
	公文文种(4),
	
	/**
	 * 档案全宗数据源
	 */
	档案全宗(5);
	
	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	private EnumDataSourceInherentType(int enumValue)
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
	public static EnumDataSourceInherentType getEnumElement(int enumValue)
	{
		EnumDataSourceInherentType pEnumElement = EnumDataSourceInherentType.NONE;

		for (EnumDataSourceInherentType item : EnumDataSourceInherentType.values())
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
