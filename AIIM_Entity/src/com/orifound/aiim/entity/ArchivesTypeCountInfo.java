package com.orifound.aiim.entity;

/**
 * 档案分类的数量统计信息
 * @author Administrator
 *
 */
public class ArchivesTypeCountInfo {
	
	//档案分类信息
	ArchivesType archivesType = null;

	//查询条件
	String querySQL = "";
	
	//查询结果的数量
	int countNum = 0;

	//该数据项的附加对象，可以用来保存一些附加信息	 
	private Object tag=null;

	
	
	public Object getTag()
	{
		return tag;
	}

	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	
	public ArchivesType getArchivesType() {
		return archivesType;
	}

	public void setArchivesType(ArchivesType archivesType) {
		this.archivesType = archivesType;
	}

	public String getQuerySQL() {
		return querySQL;
	}

	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}

	public int getCountNum() {
		return countNum;
	}

	public void setCountNum(int countNum) {
		this.countNum = countNum;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesTypeCountInfo clone()
	{
		ArchivesTypeCountInfo archivesTypeCountInfo = new ArchivesTypeCountInfo();
		archivesTypeCountInfo.setArchivesType(archivesType);
		archivesTypeCountInfo.setCountNum(countNum);
		archivesTypeCountInfo.setQuerySQL(querySQL);
		archivesTypeCountInfo.setTag(tag);
		return archivesTypeCountInfo;
	}
	
	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesTypeCountInfo 指定的对象源
	*/
	public void cloneFrom(ArchivesTypeCountInfo archivesTypeCountInfo)
	{
		this.archivesType = archivesTypeCountInfo.getArchivesType();
		this.countNum = archivesTypeCountInfo.getCountNum();
		this.querySQL = archivesTypeCountInfo.getQuerySQL();
		this.tag = archivesTypeCountInfo.getTag();
	}
}
