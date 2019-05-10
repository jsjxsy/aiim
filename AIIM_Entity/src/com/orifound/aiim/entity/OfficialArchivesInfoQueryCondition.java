package com.orifound.aiim.entity;

public class OfficialArchivesInfoQueryCondition {
	/**
	 * 构造函数
	 * 
	 * @param dataItem
	 *            当前查询条件对应的数据项
	 */
	public OfficialArchivesInfoQueryCondition(ArchivesTypeDataItem dataItem)
	{
		this.dataItem = dataItem;
	}

	/**
	 * 构造函数
	 * 
	 * @param dataItem
	 *            当前查询条件对应的数据项
	 * @param operatorType
	 *            条件查询的操作符
	 * @param value
	 *            用于检索的条件值
	 * @param isAND
	 *            是否为与运算(AND)
	 */
	public OfficialArchivesInfoQueryCondition(ArchivesTypeDataItem dataItem, EnumOperatorType operatorType, String value, boolean isAND)
	{
		this(dataItem);
		this.operatorType = operatorType;
		this.isAND = isAND;
		setValue(value);
	}

	/**
	 * 当前查询条件对应的数据项
	 */
	private ArchivesTypeDataItem dataItem = null;

	/**
	 * 设置属性值：当前查询条件对应的数据项
	 * 
	 * @param dataItem
	 *            当前查询条件对应的数据项
	 */
	public void setDataItem(ArchivesTypeDataItem dataItem)
	{
		this.dataItem = dataItem;
	}

	/**
	 * 获取属性值：当前查询条件对应的数据项
	 * 
	 * @return 当前查询条件对应的数据项
	 */
	public ArchivesTypeDataItem getDataItem()
	{
		return dataItem;
	}

	/**
	 * 条件查询的操作符，缺省是包含操作符
	 */
	private EnumOperatorType operatorType = EnumOperatorType.包含;

	/**
	 * 设置属性值：条件查询的操作符
	 * 
	 * @param operatorType
	 *            条件查询的操作符
	 */
	public void setOperatorType(EnumOperatorType operatorType)
	{
		this.operatorType = operatorType;
	}

	/**
	 * 获取属性值：条件查询的操作符
	 * 
	 * @return 条件查询的操作符
	 */
	public EnumOperatorType getOperatorType()
	{
		return operatorType;
	}

	/**
	 * 是否为与运算(AND)
	 */
	private boolean isAND = true;

	/**
	 * 设置属性值：是否为与运算(AND)
	 * 
	 * @param isAND
	 *            是否为与运算(AND)
	 */
	public void setIsAND(boolean isAND)
	{
		this.isAND = isAND;
	}

	/**
	 * 获取属性值：是否为与运算(AND)
	 * 
	 * @return 是否为与运算(AND)
	 */
	public boolean getIsAND()
	{
		return isAND;
	}

	/**
	 * 用于检索的条件值
	 */
	private String value = "";

	/**
	 * 设置属性值：用于检索的条件值
	 * 
	 * @param value
	 *            用于检索的条件值
	 */
	public void setValue(String value)
	{
		this.value = value;
		if (minValue.length()==0)
		{
			this.minValue=value;
		}
		if (maxValue.length()==0)
		{
			this.maxValue=value;
		}
	}

	/**
	 * 获取属性值：用于检索的条件值
	 * 
	 * @return 用于检索的条件值
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * 用于范围检索的最小值
	 */
	private String minValue = "";

	/**
	 * 设置属性值：用于范围检索的最小值
	 * 
	 * @param minValue
	 *            用于范围检索的最小值
	 */
	public void setMinValue(String minValue)
	{
		this.minValue = minValue;
	}

	/**
	 * 获取属性值：用于范围检索的最小值
	 * 
	 * @return 用于范围检索的最小值
	 */
	public String getMinValue()
	{
		return minValue;
	}

	/**
	 * 用于范围检索的最大值
	 */
	private String maxValue = "";

	/**
	 * 设置属性值：用于范围检索的最大值
	 * 
	 * @param maxValue
	 *            用于范围检索的最大值
	 */
	public void setMaxValue(String maxValue)
	{
		this.maxValue = maxValue;
	}

	/**
	 * 获取属性值：用于范围检索的最大值
	 * 
	 * @return 用于范围检索的最大值
	 */
	public String getMaxValue()
	{
		return maxValue;
	}

	/**
	 * 分离出范围查询条件值
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	@Deprecated
	public boolean splitRangeValue(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查数据项是否可查询
			if (dataItem.getInputQueryFlag() == false && dataItem.getUseQueryFlag() == false)
			{
				pFlag = false;
				pErrInfo.getContent().append("数据项" + dataItem.getDisplayText() + "不可用于查询检索。");
			}

			// 检查条件值是否有值
			if (pFlag)
			{
				pErrPos = 1;
				value = value.trim();
				if (value.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("条件值非法为空。");
				}
			}

			// 如果是范围查询的数据项，则分离出查询范围的最小/最大值，如果不是范围查询的数据项，则无需分离解析
			if (pFlag)
			{
				if (dataItem.getRangeQueryFlag())
				{

					// 检查是否有分隔符-或－
					value = value.replace("－", "-");
					if (value.indexOf("-") != -1)
					{
						String[] pValues = value.split("-");
						if (pValues.length > 2)
						{
							pFlag = false;
							pErrInfo.getContent().append("范围查询的条件值中非法出现多个'-'符号。");
						}

						// 赋予范围查询的最小和最大值
						if (pFlag)
						{
							minValue = pValues[0];
							maxValue = pValues[1];
						}
					}
					else
					{
						// 如果没有范围，那么直接优化为精确查询
						operatorType = EnumOperatorType.等于;
					}
				}
			}
		}
		catch (Exception e)
		{
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

}
