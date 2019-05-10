/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ����ҳ��Ϣ������
 * 
 */
public class DataPageInfo
{

	/**
	 * ���캯��
	 */
	public DataPageInfo()
	{

	}

	/**
	 * ���캯��
	 * 
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
	 * @param currentPage
	 *            ��ǰҳ���
	 */
	public DataPageInfo(int pageSize, int currentPage)
	{
		this.pageSize = pageSize;
		setCurrentPage(currentPage);
	}

	/**
	 * ���ݼ�¼����
	 */
	private int rowCount;
	/**
	 * ÿҳ��¼��
	 */
	private int pageSize = 0;
	/**
	 * ��ҳ��
	 */
	private int pageCount = 0;
	/**
	 * ��ǰҳ��
	 */
	private int currentPage = 1;

	/**
	 * ��һҳ����һҳ��ť�Ƿ���� disable������ ��enale����
	 */
	private String previousState;
	/**
	 * ���һҳ����һҳ��ť״̬ disable�����ã� enale����
	 */
	private String nextState;

	/**
	 * ��ȡ����ֵ���ܼ�¼��
	 * 
	 * @return �ܼ�¼��
	 */
	public int getRowCount()
	{
		return rowCount;
	}

	/**
	 * ��������ֵ���ܼ�¼��
	 * 
	 * @param rowCount
	 *            �ܼ�¼��
	 */
	public void setRowCount(int rowCount)
	{
		this.rowCount = rowCount;
	}

	/**
	 * ��ȡ����ֵ��ÿҳ��ʾ�ļ�¼��
	 * 
	 * @return ÿҳ��ʾ�ļ�¼��
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * ��������ֵ��ÿҳ��ʾ�ļ�¼��
	 * 
	 * @param pageSize
	 *            ÿҳ��ʾ�ļ�¼��
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
	 * ��ȡ����ֵ����ҳ��
	 * 
	 * @return ��ҳ��
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
			// ҳ��СΪ0��ʾ����ҳ���ܹ�1ҳ��
			pageCount = 1;
		}

		return pageCount;
	}

	/**
	 * ��ȡ����ֵ����ǰҳ,������ִ��ͳ��������������rowCountֵ����Ȼ���ǲ�ĵ�һҳ����
	 * 
	 * @return ��ǰҳ
	 */
	public int getCurrentPage()
	{
		if(pageSize != 0 && currentPage > getPageCount()){
			currentPage = getPageCount();
		}
		return currentPage;
	}

	/**
	 * ��������ֵ����ǰҳ
	 * 
	 * @param currentPage
	 *            ��ǰҳ
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
	 * ��һҳ����һҳ��ť�Ƿ���� disable������ ��enale����
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
	 * ���һҳ����һҳ��ť״̬ disable�����ã� enale����
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
	 * ���ص�ǰҳ����ʼ����
	 * Ĭ������Ϊ1
	 * @return int
	 */
	public int getBeginRow() {
		return (getCurrentPage()-1) * pageSize + 1;
	}
	
	/**
	 * ���ص�ǰҳ�Ľ�����
	 * Ĭ������Ϊ��һҳ��¼��
	 * @return int
	 */
	public int getEndRow() {
		return getCurrentPage() * pageSize;
	}
}
