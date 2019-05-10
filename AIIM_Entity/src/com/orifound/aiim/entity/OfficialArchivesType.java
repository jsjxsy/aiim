package com.orifound.aiim.entity;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

    
/**
 * ���ĵ��������ֵ���ʵ����
 */
public class OfficialArchivesType
{
    /**
     * ���캯��
     */
    public OfficialArchivesType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ����������
	* @param name ������������
	* @param code ����
	* @param attachedFileSavePath ԭ�Ĵ洢·��
	* @param remark ��ע
	*/
	public OfficialArchivesType(int iD,String name,String code,String attachedFileSavePath,String remark)
	{
		// Table Name: DD_OfficialArchivesType
		// Columns List,Can Used in SELECT SQL: ID,Name,Code,AttachedFileSavePath,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Code,:AttachedFileSavePath,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Code=:Code,AttachedFileSavePath=:AttachedFileSavePath,Remark=:Remark

		this(name, code, attachedFileSavePath, remark);
		this.iD = iD;
	}
	
    /**
     * ���캯��
     */
    public OfficialArchivesType(int iD)
    {
     this.iD=iD;   
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name ������������
	* @param code ����
	* @param attachedFileSavePath ԭ�Ĵ洢·��
	* @param remark ��ע
	*/
	public OfficialArchivesType(String name,String code,String attachedFileSavePath,String remark)
	{
		this.name = name;
		this.code = code;
		this.attachedFileSavePath = attachedFileSavePath;
		this.remark = remark;
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
	 * ����������
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ������������
	 * @param iD ����������
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ������������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ��������������
	 * @return ������������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ��������������
	 * @param name ������������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ����
	 */
	private String code=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * ��������ֵ������
	 * @param code ����
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * ԭ�Ĵ洢·��
	 */
	private String attachedFileSavePath=null;

	/**
	 * ��ȡ����ֵ��ԭ�Ĵ洢·��
	 * @return ԭ�Ĵ洢·��
	 */
	public String getAttachedFileSavePath()
	{
		return attachedFileSavePath;
	}

	/**
	 * ��������ֵ��ԭ�Ĵ洢·��
	 * @param attachedFileSavePath ԭ�Ĵ洢·��
	 */
	public void setAttachedFileSavePath(String attachedFileSavePath)
	{
		this.attachedFileSavePath = attachedFileSavePath;
	}

	/**
	 * ��ע
	 */
	private String remark=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * @param remark ��ע
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public OfficialArchivesType clone()
	{
		OfficialArchivesType item = new OfficialArchivesType(iD,name,code,attachedFileSavePath,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		//һЩֻ����;���ֵ����Կ��������ϸ��¡��ֱ�ӹҽ���������ü���
		item.setOfficialArchivesInfoTables(officialArchivesInfoTables);
		item.setOfficialArchivesTypeSavedMappings(officialArchivesTypeSavedMappings);
		item.setDataItemsForAll(dataItemsForAll);
		item.setDataItemsForInput(dataItemsForInput);
		item.setDataItemsForInputQuery(dataItemsForInputQuery);
		item.setDataItemsForUseQuery(dataItemsForUseQuery);
		item.setDataItemsForListDisplay(dataItemsForListDisplay);
		item.setCatalogPrintTemplates(catalogPrintTemplates);
		
		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param officialArchivesType ָ���Ķ���Դ
	*/
	public void cloneFrom(OfficialArchivesType officialArchivesType)
	{
		this.iD = officialArchivesType.getID();
		this.name = officialArchivesType.getName();
		this.code = officialArchivesType.getCode();
		this.attachedFileSavePath = officialArchivesType.getAttachedFileSavePath();
		this.remark = officialArchivesType.getRemark();
		this.keyInCol = officialArchivesType.getKeyInCol();
		this.tag = officialArchivesType.getTag();
		
		this.officialArchivesInfoTables=officialArchivesType.getOfficialArchivesInfoTables();
		this.officialArchivesTypeSavedMappings=officialArchivesType.getOfficialArchivesTypeSavedMappings();
		this.dataItemsForAll=officialArchivesType.getDataItemsForAll();
		this.dataItemsForInput=officialArchivesType.getDataItemsForInput();
		this.dataItemsForInputQuery=officialArchivesType.getDataItemsForInputQuery();
		this.dataItemsForUseQuery=officialArchivesType.getDataItemsForUseQuery();
		this.dataItemsForListDisplay=officialArchivesType.getDataItemsForListDisplay();
		this.catalogPrintTemplates=officialArchivesType.getCatalogPrintTemplates();
	}



	/**
	 * ���ĵ����������Ϣ����
	 */
	private EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables =null;

	/**
	 * ��������ֵ�����ĵ����������Ϣ����
	 * 
	 * @param archivesInfoTables
	 *            ���ĵ����������Ϣ����
	 */
	public void setOfficialArchivesInfoTables(
			EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables) {
		this.officialArchivesInfoTables = officialArchivesInfoTables;
	}

	/**
	 * ��ȡ����ֵ�����ĵ����������Ϣ����
	 * 
	 * @return ���ĵ����������Ϣ����
	 */
	public EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> getOfficialArchivesInfoTables() {
		return officialArchivesInfoTables;
	}
	
	/**
	 * ��ǰ���ĵ������ඨ���������������ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll = null;

	/**
	 * ��������ֵ����ǰ���ĵ������ඨ���������������ֶ�����Ϊ�ؼ���
	 * @param dataItemsForAll ��ǰ���ĵ������ඨ���������������ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForAll(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll)
	{
		this.dataItemsForAll = dataItemsForAll;
	}

	/**
	 * ��ȡ����ֵ����ǰ���ĵ������ඨ���������������ֶ�����Ϊ�ؼ���
	 * @return ��ǰ���ĵ������ඨ���������������ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForAll()
	{
		return dataItemsForAll;
	}

	/**
	 * ��ǰ���ĵ���������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput = null;

	/**
	 * ��������ֵ����ǰ���ĵ���������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForInput ��ǰ���ĵ���������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForInput(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput)
	{
		this.dataItemsForInput = dataItemsForInput;
	}

	/**
	 * ��ȡ����ֵ����ǰ���ĵ���������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ���ĵ���������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInput()
	{
		return dataItemsForInput;
	}

	/**
	 * ��ǰ���ĵ����������Ϊ��¼��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery = null;

	/**
	 * ��������ֵ����ǰ���ĵ����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForQuery ��ǰ���ĵ����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForInputQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery)
	{
		this.dataItemsForInputQuery = dataItemsForInputQuery;
	}

	/**
	 * ��ȡ����ֵ����ǰ���ĵ����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ���ĵ����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInputQuery()
	{
		return dataItemsForInputQuery;
	}
	
	/**
	 * ��ǰ���ĵ����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery = null;

	/**
	 * ��������ֵ����ǰ���ĵ����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForUseQuery ��ǰ���ĵ����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForUseQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery)
	{
		this.dataItemsForUseQuery = dataItemsForUseQuery;
	}

	/**
	 * ��ȡ����ֵ����ǰ���ĵ����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ���ĵ����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForUseQuery()
	{
		return dataItemsForUseQuery;
	}

	/**
	 * ��ǰ���ĵ��������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay = null;

	/**
	 * ��������ֵ����ǰ���ĵ��������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForListDisplay ��ǰ���ĵ��������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForListDisplay(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay)
	{
		this.dataItemsForListDisplay = dataItemsForListDisplay;
	}

	/**
	 * ��ȡ����ֵ����ǰ���ĵ��������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ���ĵ��������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForListDisplay()
	{
		return dataItemsForListDisplay;
	}

	/**
	 * ���ĵ������ͱ���ӳ�伯��
	 */
	private Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings=null;

	/**
	 * ��ȡ���ĵ������ͱ���ӳ�伯��
	 * @return
	 */
	public Map<Integer, OfficialArchivesTypeSavedMapping> getOfficialArchivesTypeSavedMappings() {
		return officialArchivesTypeSavedMappings;
	}
	
	/**
	 * ���ù��ĵ������ͱ���ӳ�伯��
	 * @param oSavedMappingfficialArchivesTypeSavedMappings
	 */
	public void setOfficialArchivesTypeSavedMappings(Map<Integer, OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings) {
		this.officialArchivesTypeSavedMappings = officialArchivesTypeSavedMappings;
	}
	
	/**
	 * ��ǰ���������µĸ���Ŀ¼��ӡģ�弯��
	 */
	private EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> catalogPrintTemplates = null;

	/**
	 * ��������ֵ����ǰ���������µĸ���Ŀ¼��ӡģ�弯��
	 * @param catalogPrintTemplates ��ǰ���������µĸ���Ŀ¼��ӡģ�弯��
	 */
	public void setCatalogPrintTemplates(EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> catalogPrintTemplates)
	{
		this.catalogPrintTemplates = catalogPrintTemplates;
	}

	/**
	 * ��ȡ����ֵ����ǰ���������µĸ���Ŀ¼��ӡģ�弯��
	 * @return ��ǰ���������µĸ���Ŀ¼��ӡģ�弯��
	 */
	public EnumMap<EnumCatalogType, LinkedHashMap<String, CatalogDataItem>> getCatalogPrintTemplates()
	{
		return catalogPrintTemplates;
	}
    
}



