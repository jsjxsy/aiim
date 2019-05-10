package com.orifound.aiim.entity;

    
/**
 * �����ӡ���ñ�
 */
public class ReportPrintSetting
{
    /**
     * ���캯��
     */
    public ReportPrintSetting()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param titleFontSize ������������С
	* @param tableRowHeight ����и�
	* @param tableFontSize ��������С
	*/
	public ReportPrintSetting(int titleFontSize,int tableRowHeight,int tableFontSize)
	{
		// Table Name: ReportPrintSetting
		// Columns List,Can Used in SELECT SQL: TitleFontSize,TableRowHeight,TableFontSize
		// Columns List,Can Used in INSERT SQL: :TitleFontSize,:TableRowHeight,:TableFontSize
		// Columns List,Can Used in UPDATE SQL: TitleFontSize=:TitleFontSize,TableRowHeight=:TableRowHeight,TableFontSize=:TableFontSize

		this.titleFontSize = titleFontSize;
		this.tableRowHeight = tableRowHeight;
		this.tableFontSize = tableFontSize;
	}

	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * ������������С
	 */
	private int titleFontSize=0;

	/**
	 * ��ȡ����ֵ��������������С
	 * @return ������������С
	 */
	public int getTitleFontSize()
	{
		return titleFontSize;
	}

	/**
	 * ��������ֵ��������������С
	 * @param titleFontSize ������������С
	 */
	public void setTitleFontSize(int titleFontSize)
	{
		this.titleFontSize = titleFontSize;
	}

	/**
	 * ����и�
	 */
	private int tableRowHeight=0;

	/**
	 * ��ȡ����ֵ������и�
	 * @return ����и�
	 */
	public int getTableRowHeight()
	{
		return tableRowHeight;
	}

	/**
	 * ��������ֵ������и�
	 * @param tableRowHeight ����и�
	 */
	public void setTableRowHeight(int tableRowHeight)
	{
		this.tableRowHeight = tableRowHeight;
	}

	/**
	 * ��������С
	 */
	private int tableFontSize=0;

	/**
	 * ��ȡ����ֵ����������С
	 * @return ��������С
	 */
	public int getTableFontSize()
	{
		return tableFontSize;
	}

	/**
	 * ��������ֵ����������С
	 * @param tableFontSize ��������С
	 */
	public void setTableFontSize(int tableFontSize)
	{
		this.tableFontSize = tableFontSize;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportPrintSetting clone()
	{
		ReportPrintSetting item = new ReportPrintSetting(titleFontSize,tableRowHeight,tableFontSize);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportPrintSetting ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportPrintSetting reportPrintSetting)
	{
		this.titleFontSize = reportPrintSetting.getTitleFontSize();
		this.tableRowHeight = reportPrintSetting.getTableRowHeight();
		this.tableFontSize = reportPrintSetting.getTableFontSize();
		this.keyInCol = reportPrintSetting.getKeyInCol();
		this.tag = reportPrintSetting.getTag();
	}



    
}



