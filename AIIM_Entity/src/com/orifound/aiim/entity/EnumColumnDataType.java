package com.orifound.aiim.entity;

/**
 * �������ֶε���������ö��<br>
 * �ַ���������������ʱ�䡢����ֵ���ı���ʵ��
 * 
 */
public enum EnumColumnDataType
{

	�ַ���,	
	����,
	����ʱ��,
	����ֵ,
	�ı�,
	ʵ��;
	

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա<br>
	 * Ŀǰ֧��SQL Serverƽ̨���������Ҫ֧���������ݿ�ƽ̨����չ�޸ĸú����е��ж����
	 * @param ColumnDataTypeText �ֶ����������ı���
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
	 */
	public static EnumColumnDataType getEnumElement(String ColumnDataTypeText)
	{
		EnumColumnDataType dataType=EnumColumnDataType.�ַ���;
		ColumnDataTypeText=ColumnDataTypeText.toLowerCase();
		
		if (ColumnDataTypeText.equals("varchar"))
		{
			dataType=EnumColumnDataType.�ַ���;
		}
		else if (ColumnDataTypeText.equals("int")) 
		{
			dataType=EnumColumnDataType.����;
		}
		else if (ColumnDataTypeText.equals("datetime")) 
		{
			dataType=EnumColumnDataType.����ʱ��;
		}
		else if (ColumnDataTypeText.equals("bit")) 
		{
			dataType=EnumColumnDataType.����ֵ;
		}
		else if (ColumnDataTypeText.equals("text")) 
		{
			dataType=EnumColumnDataType.�ı�;
		}
		else if (ColumnDataTypeText.equals("float")) 
		{
			dataType=EnumColumnDataType.ʵ��;
		}

		return dataType;
	}
}

