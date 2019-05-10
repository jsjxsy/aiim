/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 实物档案移交批次的处理状态枚举
 *
 */
public enum EnumPaperTransferBatchesDealStatus {
	
//	0：未确认移交
//	1：确认移交
//	2：接收登记完成
//	3：接收审核完成
	
	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE(-1),

	/**
	 * 未确认移交
	 */
	未确认移交(0),
	
	/**
	 * 确认移交
	 */
	确认移交(1),
	
	/**
	 * 接收登记完成
	 */
	接收登记完成(2),
	
	/**
	 *接收审核完成
	 */
	接收审核完成(3),
	
	/**
	 *接收审核完成
	 */
	添加到移交清单(4);
	/**
	 * 构造函数
	 * @param enumValue 枚举值
	 */
	 private EnumPaperTransferBatchesDealStatus(int enumValue)
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
	public static EnumPaperTransferBatchesDealStatus getEnumElement(int enumValue)
	{
		EnumPaperTransferBatchesDealStatus pEnumElement = EnumPaperTransferBatchesDealStatus.NONE;

		for (EnumPaperTransferBatchesDealStatus item : EnumPaperTransferBatchesDealStatus.values())
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
