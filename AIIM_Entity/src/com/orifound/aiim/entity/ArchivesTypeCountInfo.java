package com.orifound.aiim.entity;

/**
 * �������������ͳ����Ϣ
 * @author Administrator
 *
 */
public class ArchivesTypeCountInfo {
	
	//����������Ϣ
	ArchivesType archivesType = null;

	//��ѯ����
	String querySQL = "";
	
	//��ѯ���������
	int countNum = 0;

	//��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ	 
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
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
	* ��ָ�������¡��������������ֵ
	* @param archivesTypeCountInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesTypeCountInfo archivesTypeCountInfo)
	{
		this.archivesType = archivesTypeCountInfo.getArchivesType();
		this.countNum = archivesTypeCountInfo.getCountNum();
		this.querySQL = archivesTypeCountInfo.getQuerySQL();
		this.tag = archivesTypeCountInfo.getTag();
	}
}
