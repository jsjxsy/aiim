package com.orifound.aiim.entity;

public class OfficialArchivesInfoQueryCondition {
	/**
	 * ���캯��
	 * 
	 * @param dataItem
	 *            ��ǰ��ѯ������Ӧ��������
	 */
	public OfficialArchivesInfoQueryCondition(ArchivesTypeDataItem dataItem)
	{
		this.dataItem = dataItem;
	}

	/**
	 * ���캯��
	 * 
	 * @param dataItem
	 *            ��ǰ��ѯ������Ӧ��������
	 * @param operatorType
	 *            ������ѯ�Ĳ�����
	 * @param value
	 *            ���ڼ���������ֵ
	 * @param isAND
	 *            �Ƿ�Ϊ������(AND)
	 */
	public OfficialArchivesInfoQueryCondition(ArchivesTypeDataItem dataItem, EnumOperatorType operatorType, String value, boolean isAND)
	{
		this(dataItem);
		this.operatorType = operatorType;
		this.isAND = isAND;
		setValue(value);
	}

	/**
	 * ��ǰ��ѯ������Ӧ��������
	 */
	private ArchivesTypeDataItem dataItem = null;

	/**
	 * ��������ֵ����ǰ��ѯ������Ӧ��������
	 * 
	 * @param dataItem
	 *            ��ǰ��ѯ������Ӧ��������
	 */
	public void setDataItem(ArchivesTypeDataItem dataItem)
	{
		this.dataItem = dataItem;
	}

	/**
	 * ��ȡ����ֵ����ǰ��ѯ������Ӧ��������
	 * 
	 * @return ��ǰ��ѯ������Ӧ��������
	 */
	public ArchivesTypeDataItem getDataItem()
	{
		return dataItem;
	}

	/**
	 * ������ѯ�Ĳ�������ȱʡ�ǰ���������
	 */
	private EnumOperatorType operatorType = EnumOperatorType.����;

	/**
	 * ��������ֵ��������ѯ�Ĳ�����
	 * 
	 * @param operatorType
	 *            ������ѯ�Ĳ�����
	 */
	public void setOperatorType(EnumOperatorType operatorType)
	{
		this.operatorType = operatorType;
	}

	/**
	 * ��ȡ����ֵ��������ѯ�Ĳ�����
	 * 
	 * @return ������ѯ�Ĳ�����
	 */
	public EnumOperatorType getOperatorType()
	{
		return operatorType;
	}

	/**
	 * �Ƿ�Ϊ������(AND)
	 */
	private boolean isAND = true;

	/**
	 * ��������ֵ���Ƿ�Ϊ������(AND)
	 * 
	 * @param isAND
	 *            �Ƿ�Ϊ������(AND)
	 */
	public void setIsAND(boolean isAND)
	{
		this.isAND = isAND;
	}

	/**
	 * ��ȡ����ֵ���Ƿ�Ϊ������(AND)
	 * 
	 * @return �Ƿ�Ϊ������(AND)
	 */
	public boolean getIsAND()
	{
		return isAND;
	}

	/**
	 * ���ڼ���������ֵ
	 */
	private String value = "";

	/**
	 * ��������ֵ�����ڼ���������ֵ
	 * 
	 * @param value
	 *            ���ڼ���������ֵ
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
	 * ��ȡ����ֵ�����ڼ���������ֵ
	 * 
	 * @return ���ڼ���������ֵ
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * ���ڷ�Χ��������Сֵ
	 */
	private String minValue = "";

	/**
	 * ��������ֵ�����ڷ�Χ��������Сֵ
	 * 
	 * @param minValue
	 *            ���ڷ�Χ��������Сֵ
	 */
	public void setMinValue(String minValue)
	{
		this.minValue = minValue;
	}

	/**
	 * ��ȡ����ֵ�����ڷ�Χ��������Сֵ
	 * 
	 * @return ���ڷ�Χ��������Сֵ
	 */
	public String getMinValue()
	{
		return minValue;
	}

	/**
	 * ���ڷ�Χ���������ֵ
	 */
	private String maxValue = "";

	/**
	 * ��������ֵ�����ڷ�Χ���������ֵ
	 * 
	 * @param maxValue
	 *            ���ڷ�Χ���������ֵ
	 */
	public void setMaxValue(String maxValue)
	{
		this.maxValue = maxValue;
	}

	/**
	 * ��ȡ����ֵ�����ڷ�Χ���������ֵ
	 * 
	 * @return ���ڷ�Χ���������ֵ
	 */
	public String getMaxValue()
	{
		return maxValue;
	}

	/**
	 * �������Χ��ѯ����ֵ
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	@Deprecated
	public boolean splitRangeValue(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ����������Ƿ�ɲ�ѯ
			if (dataItem.getInputQueryFlag() == false && dataItem.getUseQueryFlag() == false)
			{
				pFlag = false;
				pErrInfo.getContent().append("������" + dataItem.getDisplayText() + "�������ڲ�ѯ������");
			}

			// �������ֵ�Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				value = value.trim();
				if (value.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ֵ�Ƿ�Ϊ�ա�");
				}
			}

			// ����Ƿ�Χ��ѯ���������������ѯ��Χ����С/���ֵ��������Ƿ�Χ��ѯ�������������������
			if (pFlag)
			{
				if (dataItem.getRangeQueryFlag())
				{

					// ����Ƿ��зָ���-��
					value = value.replace("��", "-");
					if (value.indexOf("-") != -1)
					{
						String[] pValues = value.split("-");
						if (pValues.length > 2)
						{
							pFlag = false;
							pErrInfo.getContent().append("��Χ��ѯ������ֵ�зǷ����ֶ��'-'���š�");
						}

						// ���跶Χ��ѯ����С�����ֵ
						if (pFlag)
						{
							minValue = pValues[0];
							maxValue = pValues[1];
						}
					}
					else
					{
						// ���û�з�Χ����ôֱ���Ż�Ϊ��ȷ��ѯ
						operatorType = EnumOperatorType.����;
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

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
