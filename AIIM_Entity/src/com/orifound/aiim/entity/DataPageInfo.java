/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 数据页信息对象类
 * 
 */
public class DataPageInfo
{

	/**
	 * 构造函数
	 */
	public DataPageInfo()
	{

	}

	/**
	 * 构造函数
	 * 
	 * @param pageSize
	 *            每页显示的记录数
	 * @param currentPage
	 *            当前页序号
	 */
	public DataPageInfo(int pageSize, int currentPage)
	{
		this.pageSize = pageSize;
		setCurrentPage(currentPage);
	}

	/**
	 * 数据记录总数
	 */
	private int rowCount;
	/**
	 * 每页记录数
	 */
	private int pageSize = 0;
	/**
	 * 总页数
	 */
	private int pageCount = 0;
	/**
	 * 当前页码
	 */
	private int currentPage = 1;

	/**
	 * 第一页和上一页按钮是否可用 disable不可用 ，enale可用
	 */
	private String previousState;
	/**
	 * 最后一页和下一页按钮状态 disable不可用， enale可用
	 */
	private String nextState;

	/**
	 * 获取属性值：总记录数
	 * 
	 * @return 总记录数
	 */
	public int getRowCount()
	{
		return rowCount;
	}

	/**
	 * 设置属性值：总记录数
	 * 
	 * @param rowCount
	 *            总记录数
	 */
	public void setRowCount(int rowCount)
	{
		this.rowCount = rowCount;
	}

	/**
	 * 获取属性值：每页显示的记录数
	 * 
	 * @return 每页显示的记录数
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * 设置属性值：每页显示的记录数
	 * 
	 * @param pageSize
	 *            每页显示的记录数
	 */
	public void setPageSize(int pageSize)
	{
		if (pageSize <= 0)
		{
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}

	/**
	 * 获取属性值：总页数
	 * 
	 * @return 总页数
	 */
	public int getPageCount()
	{
		if (pageSize > 0)
		{
			if (rowCount == 0)
			{
				pageCount = 1;
			}
			else
			{
				if (rowCount % pageSize == 0)
				{
					pageCount = rowCount / pageSize;
				}
				else
				{
					pageCount = rowCount / pageSize + 1;
				}
			}
		}
		else
		{
			// 页大小为0表示不分页，总共1页。
			pageCount = 1;
		}

		return pageCount;
	}

	/**
	 * 获取属性值：当前页,必须先执行统计总条数，设置rowCount值，不然总是查的第一页数据
	 * 
	 * @return 当前页
	 */
	public int getCurrentPage()
	{
		if(pageSize != 0 && currentPage > getPageCount()){
			currentPage = getPageCount();
		}
		return currentPage;
	}

	/**
	 * 设置属性值：当前页
	 * 
	 * @param currentPage
	 *            当前页
	 */
	public void setCurrentPage(int currentPage)
	{
		if (currentPage <= 0)
		{
			currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	/**
	 * 第一页和上一页按钮是否可用 disable不可用 ，enale可用
	 */
	public String getPreviousState()
	{
		if (currentPage <= 1)
		{
			previousState = "disable";
		}
		else
		{
			previousState = "enable";
		}
		return previousState;
	}

	/**
	 * 最后一页和下一页按钮状态 disable不可用， enale可用
	 */
	public String getNextState()
	{
		if (currentPage >= getPageCount())
		{
			nextState = "disable";
		}
		else
		{
			nextState = "enable";
		}
		return nextState;
	}
	
	/**
	 * 返回当前页的起始行数
	 * 默认设置为1
	 * @return int
	 */
	public int getBeginRow() {
		return (getCurrentPage()-1) * pageSize + 1;
	}
	
	/**
	 * 返回当前页的结束行
	 * 默认设置为第一页记录数
	 * @return int
	 */
	public int getEndRow() {
		return getCurrentPage() * pageSize;
	}
}
