package com.orifound.aiim.entity;

/**
 * 编辑风格的枚举定义<br>
 * 编辑风格：表示数据项在用户界面上进行编辑时的交互类型
 */
public enum EnumEditStyle
{

//	1：文本框
//	2：文本域
//	3：下拉列表框
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	/**
	 * 文本框，单行编辑风格
	 */
	文本框(1),
	
	/**
	 * 文本域，多行编辑风格
	 */
	文本域(2),
	
	/**
	 * 下拉列表框，多选一列表选择
	 */
	下拉列表框(3);

	/**
	 * 构造函数
	 * 
	 * @param enumValue
	 *            枚举值
	 */
	private EnumEditStyle(int enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * 枚举值
	 */
	private int enumValue;

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
	 * 根据枚举值获取枚举成员
	 * 
	 * @param enumValue
	 *            枚举值
	 * @return 对应该枚举值的枚举成员对象
	 */
	public static EnumEditStyle getEnumElement(int enumValue)
	{
		EnumEditStyle pEnumElement = EnumEditStyle.NONE;

		for (EnumEditStyle item : EnumEditStyle.values())
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