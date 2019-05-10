/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 库房档案信息查询条件对象类
 *
 */
public class StoreroomArchivesInfoQueryCondition {

	/**
	 * 档号
	 */
	private String archivesID=null;

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		return archivesID;
	}

	/**
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		this.archivesID = archivesID;
	}

	/**
	 * 档案条码
	 */
	private String archivesBarcode=null;

	/**
	 * 获取属性值：档案条码
	 * @return 档案条码
	 */
	public String getArchivesBarcode()
	{
		return archivesBarcode;
	}

	/**
	 * 设置属性值：档案条码
	 * @param archivesBarcode 档案条码
	 */
	public void setArchivesBarcode(String archivesBarcode)
	{
		this.archivesBarcode = archivesBarcode;
	}
	
	/**
	 * 题名
	 */
	private String title=null;

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
}
