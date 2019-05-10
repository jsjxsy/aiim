/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 档案盒标签对象类<br>
 * 描述了盒内档号起止范围的信息
 */
public class ArchivesBoxLabel {
	
	/**
	 * 构造函数
	 */
	public ArchivesBoxLabel() {
		
	}
	
	/**
	 * 带参数的构造函数
	 * @param minArchivesID 起始档号
	 * @param maxArchivesID 截止档号
	 */
	public ArchivesBoxLabel(String minArchivesID,String maxArchivesID) {
		this.minArchivesID=minArchivesID;
		this.maxArchivesID=maxArchivesID;
	}
	
	/**
	 * 盒内起始档号
	 */
	private String minArchivesID = "";

	/**
	 * 设置属性值：盒内起始档号
	 * @param minArchivesID 盒内起始档号
	 */
	public void setMinArchivesID(String minArchivesID) {
		this.minArchivesID = minArchivesID;
	}

	/**
	 * 获取属性值：盒内起始档号
	 * @return 盒内起始档号
	 */
	public String getMinArchivesID() {
		return minArchivesID;
	}

	/**
	 * 盒内截止档号
	 */
	private String maxArchivesID = "";

	/**
	 * 设置属性值：盒内截止档号
	 * @param maxArchivesID 盒内截止档号
	 */
	public void setMaxArchivesID(String maxArchivesID) {
		this.maxArchivesID = maxArchivesID;
	}

	/**
	 * 获取属性值：盒内截止档号
	 * @return 盒内截止档号
	 */
	public String getMaxArchivesID() {
		return maxArchivesID;
	}
	
	/**
	 * 盒内档案数量
	 */
	private int archivesCount = 0;

	/**
	 * 设置属性值：盒内档案数量
	 * @param archivesCount 盒内档案数量
	 */
	public void setArchivesCount(int archivesCount)
	{
		this.archivesCount = archivesCount;
	}

	/**
	 * 获取属性值：盒内档案数量
	 * @return 盒内档案数量
	 */
	public int getArchivesCount()
	{
		return archivesCount;
	}

	

	
}
