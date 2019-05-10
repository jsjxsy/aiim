package com.orifound.aiim.entity;

    
/**
 * 报表打印设置表
 */
public class ReportPrintSetting
{
    /**
     * 构造函数
     */
    public ReportPrintSetting()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param titleFontSize 报表标题字体大小
	* @param tableRowHeight 表格行高
	* @param tableFontSize 表格字体大小
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
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 报表标题字体大小
	 */
	private int titleFontSize=0;

	/**
	 * 获取属性值：报表标题字体大小
	 * @return 报表标题字体大小
	 */
	public int getTitleFontSize()
	{
		return titleFontSize;
	}

	/**
	 * 设置属性值：报表标题字体大小
	 * @param titleFontSize 报表标题字体大小
	 */
	public void setTitleFontSize(int titleFontSize)
	{
		this.titleFontSize = titleFontSize;
	}

	/**
	 * 表格行高
	 */
	private int tableRowHeight=0;

	/**
	 * 获取属性值：表格行高
	 * @return 表格行高
	 */
	public int getTableRowHeight()
	{
		return tableRowHeight;
	}

	/**
	 * 设置属性值：表格行高
	 * @param tableRowHeight 表格行高
	 */
	public void setTableRowHeight(int tableRowHeight)
	{
		this.tableRowHeight = tableRowHeight;
	}

	/**
	 * 表格字体大小
	 */
	private int tableFontSize=0;

	/**
	 * 获取属性值：表格字体大小
	 * @return 表格字体大小
	 */
	public int getTableFontSize()
	{
		return tableFontSize;
	}

	/**
	 * 设置属性值：表格字体大小
	 * @param tableFontSize 表格字体大小
	 */
	public void setTableFontSize(int tableFontSize)
	{
		this.tableFontSize = tableFontSize;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportPrintSetting clone()
	{
		ReportPrintSetting item = new ReportPrintSetting(titleFontSize,tableRowHeight,tableFontSize);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportPrintSetting 指定的对象源
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



