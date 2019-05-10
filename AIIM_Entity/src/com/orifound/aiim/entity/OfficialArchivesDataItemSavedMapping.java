package com.orifound.aiim.entity;

    
/**
 * ���ĵ���������Ĺ鵵ӳ���ϵ��ʵ����
 */
public class OfficialArchivesDataItemSavedMapping
{
    /**
     * ���캯��
     */
    public OfficialArchivesDataItemSavedMapping()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param archivesTypeSavedMappingID ��������鵵ӳ����
	* @param srcOfficialArchivesTypeDataItemID Դ���ĵ���������������
	* @param desArchivesTypeDataItemID ӳ���Ŀ��ʵ�嵵������������
	*/
	public OfficialArchivesDataItemSavedMapping(int iD,int archivesTypeSavedMappingID,int srcOfficialArchivesTypeDataItemID,int desArchivesTypeDataItemID,String columnName)
	{
		// Table Name: DD_OfficialArchivesDataItemSavedMapping
		// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID
		// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeSavedMappingID,:SrcOfficialArchivesTypeDataItemID,:DesArchivesTypeDataItemID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeSavedMappingID=:ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID=:SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID=:DesArchivesTypeDataItemID

		this.iD = iD;
		this.archivesTypeSavedMappingID = archivesTypeSavedMappingID;
		this.srcOfficialArchivesTypeDataItemID = srcOfficialArchivesTypeDataItemID;
		this.desArchivesTypeDataItemID = desArchivesTypeDataItemID;
		this.columnName=columnName;
	}
	
	

	/**
	* ���ֶβ����Ĺ��캯��
	* @param archivesTypeSavedMappingID ��������鵵ӳ����
	* @param srcOfficialArchivesTypeDataItemID Դ���ĵ���������������
	* @param desArchivesTypeDataItemID ӳ���Ŀ��ʵ�嵵������������
	*/
	public OfficialArchivesDataItemSavedMapping(int archivesTypeSavedMappingID,int srcOfficialArchivesTypeDataItemID,int desArchivesTypeDataItemID,String columnName)
	{
		// Table Name: DD_OfficialArchivesDataItemSavedMapping
		// Columns List,Can Used in SELECT SQL: ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID
		// Columns List,Can Used in INSERT SQL: :ArchivesTypeSavedMappingID,:SrcOfficialArchivesTypeDataItemID,:DesArchivesTypeDataItemID
		// Columns List,Can Used in UPDATE SQL: ID=:ArchivesTypeSavedMappingID=:ArchivesTypeSavedMappingID,SrcOfficialArchivesTypeDataItemID=:SrcOfficialArchivesTypeDataItemID,DesArchivesTypeDataItemID=:DesArchivesTypeDataItemID

		this.archivesTypeSavedMappingID = archivesTypeSavedMappingID;
		this.srcOfficialArchivesTypeDataItemID = srcOfficialArchivesTypeDataItemID;
		this.desArchivesTypeDataItemID = desArchivesTypeDataItemID;
		this.columnName=columnName;
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
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��������鵵ӳ����
	 */
	private int archivesTypeSavedMappingID=0;

	/**
	 * ��ȡ����ֵ����������鵵ӳ����
	 * @return ��������鵵ӳ����
	 */
	public int getArchivesTypeSavedMappingID()
	{
		return archivesTypeSavedMappingID;
	}

	/**
	 * ��������ֵ����������鵵ӳ����
	 * @param archivesTypeSavedMappingID ��������鵵ӳ����
	 */
	public void setArchivesTypeSavedMappingID(int archivesTypeSavedMappingID)
	{
		this.archivesTypeSavedMappingID = archivesTypeSavedMappingID;
	}

	/**
	 * Դ���ĵ���������������
	 */
	private int srcOfficialArchivesTypeDataItemID=0;

	/**
	 * ��ȡ����ֵ��Դ���ĵ���������������
	 * @return Դ���ĵ���������������
	 */
	public int getSrcOfficialArchivesTypeDataItemID()
	{
		return srcOfficialArchivesTypeDataItemID;
	}

	/**
	 * ��������ֵ��Դ���ĵ���������������
	 * @param srcOfficialArchivesTypeDataItemID Դ���ĵ���������������
	 */
	public void setSrcOfficialArchivesTypeDataItemID(int srcOfficialArchivesTypeDataItemID)
	{
		this.srcOfficialArchivesTypeDataItemID = srcOfficialArchivesTypeDataItemID;
	}

	/**
	 * ӳ���Ŀ��ʵ�嵵������������
	 */
	private int desArchivesTypeDataItemID=0;

	/**
	 * ��ȡ����ֵ��ӳ���Ŀ��ʵ�嵵������������
	 * @return ӳ���Ŀ��ʵ�嵵������������
	 */
	public int getDesArchivesTypeDataItemID()
	{
		return desArchivesTypeDataItemID;
	}

	/**
	 * ��������ֵ��ӳ���Ŀ��ʵ�嵵������������
	 * @param desArchivesTypeDataItemID ӳ���Ŀ��ʵ�嵵������������
	 */
	public void setDesArchivesTypeDataItemID(int desArchivesTypeDataItemID)
	{
		this.desArchivesTypeDataItemID = desArchivesTypeDataItemID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public OfficialArchivesDataItemSavedMapping clone()
	{
		OfficialArchivesDataItemSavedMapping item = new OfficialArchivesDataItemSavedMapping(iD,archivesTypeSavedMappingID,srcOfficialArchivesTypeDataItemID,desArchivesTypeDataItemID,columnName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param officialArchivesDataItemSavedMapping ָ���Ķ���Դ
	*/
	public void cloneFrom(OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping)
	{
		this.iD = officialArchivesDataItemSavedMapping.getID();
		this.archivesTypeSavedMappingID = officialArchivesDataItemSavedMapping.getArchivesTypeSavedMappingID();
		this.srcOfficialArchivesTypeDataItemID = officialArchivesDataItemSavedMapping.getSrcOfficialArchivesTypeDataItemID();
		this.desArchivesTypeDataItemID = officialArchivesDataItemSavedMapping.getDesArchivesTypeDataItemID();
		this.columnName=officialArchivesDataItemSavedMapping.getColumnName();
		this.keyInCol = officialArchivesDataItemSavedMapping.getKeyInCol();
		this.tag = officialArchivesDataItemSavedMapping.getTag();
	}
	
	private String columnName;//�����������

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

    
}



