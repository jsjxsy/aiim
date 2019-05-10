package com.orifound.aiim.entity;

import java.util.EnumMap;
import java.util.LinkedHashMap;
    
/**
 * ���������ֵ���ʵ����
 */
public class ArchivesType extends BaseRightArchivesResource
{
    /**
     * ���캯��
     */
    public ArchivesType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ����������
	* @param topFlag һ����Ŀ��־
	* @param name ������������
	* @param code �������
	* @param parentID �ϼ�����������
	* @param topTypeID ���������
	* @param fullCode ��������
	* @param fullName ��������
	* @param endFlag ��ײ��־
	* @param contentIDFormatLength ����ŵĸ�ʽ������
	* @param subContentIDFormatLength �����ļ���ŵĸ�ʽ������
	* @param archivesIDExpressionPrefix ����ǰ׺��ɱ��ʽ
	* @param archivesIDExpression ������ɱ��ʽ
	* @param subArchivesIDExpression �����ļ��ĵ�����ɱ��ʽ
	* @param attachedFileSavePath ԭ�Ĵ洢·��
	* @param renewPeriod ��������
	* @param personalArchivesFlag ���µ�����־
	* @param remark ��ע
	*/
	public ArchivesType(int iD,boolean topFlag,String name,String code,int parentID,int topTypeID,String fullCode,String fullName,boolean endFlag,int contentIDFormatLength,int subContentIDFormatLength,String archivesIDExpressionPrefix,String archivesIDExpression,String subArchivesIDExpression,String attachedFileSavePath,int renewPeriod,int personalArchivesFlag,String remark)
	{
		// Table Name: DD_ArchivesType
		// Columns List,Can Used in SELECT SQL: ID,TopFlag,Name,Code,ParentID,TopTypeID,FullCode,FullName,EndFlag,ContentIDFormatLength,SubContentIDFormatLength,ArchivesIDExpressionPrefix,ArchivesIDExpression,SubArchivesIDExpression,AttachedFileSavePath,RenewPeriod,PersonalArchivesFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:TopFlag,:Name,:Code,:ParentID,:TopTypeID,:FullCode,:FullName,:EndFlag,:ContentIDFormatLength,:SubContentIDFormatLength,:ArchivesIDExpressionPrefix,:ArchivesIDExpression,:SubArchivesIDExpression,:AttachedFileSavePath,:RenewPeriod,:PersonalArchivesFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TopFlag=:TopFlag,Name=:Name,Code=:Code,ParentID=:ParentID,TopTypeID=:TopTypeID,FullCode=:FullCode,FullName=:FullName,EndFlag=:EndFlag,ContentIDFormatLength=:ContentIDFormatLength,SubContentIDFormatLength=:SubContentIDFormatLength,ArchivesIDExpressionPrefix=:ArchivesIDExpressionPrefix,ArchivesIDExpression=:ArchivesIDExpression,SubArchivesIDExpression=:SubArchivesIDExpression,AttachedFileSavePath=:AttachedFileSavePath,RenewPeriod=:RenewPeriod,PersonalArchivesFlag=:PersonalArchivesFlag,Remark=:Remark

		this(topFlag, name, code, parentID, topTypeID, fullCode, fullName, endFlag, contentIDFormatLength, subContentIDFormatLength, archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression, attachedFileSavePath, renewPeriod,personalArchivesFlag, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param topFlag һ����Ŀ��־
	* @param name ������������
	* @param code �������
	* @param parentID �ϼ�����������
	* @param topTypeID ���������
	* @param fullCode ��������
	* @param fullName ��������
	* @param endFlag ��ײ��־
	* @param contentIDFormatLength ����ŵĸ�ʽ������
	* @param subContentIDFormatLength �����ļ���ŵĸ�ʽ������
	* @param archivesIDExpressionPrefix ����ǰ׺��ɱ��ʽ
	* @param archivesIDExpression ������ɱ��ʽ
	* @param subArchivesIDExpression �����ļ��ĵ�����ɱ��ʽ
	* @param attachedFileSavePath ԭ�Ĵ洢·��
	* @param renewPeriod ��������
	* @param personalArchivesFlag ���µ�����־
	* @param remark ��ע
	*/
	public ArchivesType(boolean topFlag,String name,String code,int parentID,int topTypeID,String fullCode,String fullName,boolean endFlag,int contentIDFormatLength,int subContentIDFormatLength,String archivesIDExpressionPrefix,String archivesIDExpression,String subArchivesIDExpression,String attachedFileSavePath,int renewPeriod,int personalArchivesFlag,String remark)
	{
		this.topFlag = topFlag;
		this.name = name;
		this.code = code;
		this.parentID = parentID;
		this.topTypeID = topTypeID;
		this.fullCode = fullCode;
		this.fullName = fullName;
		this.endFlag = endFlag;
		this.contentIDFormatLength = contentIDFormatLength;
		this.subContentIDFormatLength = subContentIDFormatLength;
		this.archivesIDExpressionPrefix = archivesIDExpressionPrefix;
		this.archivesIDExpression = archivesIDExpression;
		this.subArchivesIDExpression = subArchivesIDExpression;
		this.attachedFileSavePath = attachedFileSavePath;
		this.renewPeriod = renewPeriod;
		this.personalArchivesFlag =EnumPersonalArchivesType.getEnumElement(personalArchivesFlag);
		this.remark = remark;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ����������
	*/
	public ArchivesType(int iD)
	{
		this.iD = iD;
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
	 * һ����Ŀ��־
	 */
	private boolean topFlag=false;

	/**
	 * ��ȡ����ֵ��һ����Ŀ��־
	 * @return һ����Ŀ��־
	 */
	public boolean getTopFlag()
	{
		return topFlag;
	}

	/**
	 * ��������ֵ��һ����Ŀ��־
	 * @param topFlag һ����Ŀ��־
	 */
	public void setTopFlag(boolean topFlag)
	{
		this.topFlag = topFlag;
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
	 * �������
	 */
	private String code=null;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * ��������ֵ���������
	 * @param code �������
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * �ϼ�����������
	 */
	private int parentID=0;

	/**
	 * ��ȡ����ֵ���ϼ�����������
	 * @return �ϼ�����������
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * ��������ֵ���ϼ�����������
	 * @param parentID �ϼ�����������
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * ���������
	 */
	private int topTypeID=0;

	/**
	 * ��ȡ����ֵ�����������
	 * @return ���������
	 */
	public int getTopTypeID()
	{
		return topTypeID;
	}

	/**
	 * ��������ֵ�����������
	 * @param topTypeID ���������
	 */
	public void setTopTypeID(int topTypeID)
	{
		this.topTypeID = topTypeID;
	}

	/**
	 * ��������
	 */
	private String fullCode=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getFullCode()
	{
		return fullCode;
	}

	/**
	 * ��������ֵ����������
	 * @param fullCode ��������
	 */
	public void setFullCode(String fullCode)
	{
		this.fullCode = fullCode;
	}

	/**
	 * ��������
	 */
	private String fullName=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getFullName()
	{
		return fullName;
	}

	/**
	 * ��������ֵ����������
	 * @param fullName ��������
	 */
	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	/**
	 * ��ײ��־
	 */
	private boolean endFlag=false;

	/**
	 * ��ȡ����ֵ����ײ��־
	 * @return ��ײ��־
	 */
	public boolean getEndFlag()
	{
		return endFlag;
	}

	/**
	 * ��������ֵ����ײ��־
	 * @param endFlag ��ײ��־
	 */
	public void setEndFlag(boolean endFlag)
	{
		this.endFlag = endFlag;
	}

	/**
	 * ����ŵĸ�ʽ������
	 */
	private int contentIDFormatLength=0;

	/**
	 * ��ȡ����ֵ������ŵĸ�ʽ������
	 * @return ����ŵĸ�ʽ������
	 */
	public int getContentIDFormatLength()
	{
		return contentIDFormatLength;
	}

	/**
	 * ��������ֵ������ŵĸ�ʽ������
	 * @param contentIDFormatLength ����ŵĸ�ʽ������
	 */
	public void setContentIDFormatLength(int contentIDFormatLength)
	{
		this.contentIDFormatLength = contentIDFormatLength;
	}

	/**
	 * �����ļ���ŵĸ�ʽ������
	 */
	private int subContentIDFormatLength=0;

	/**
	 * ��ȡ����ֵ�������ļ���ŵĸ�ʽ������
	 * @return �����ļ���ŵĸ�ʽ������
	 */
	public int getSubContentIDFormatLength()
	{
		return subContentIDFormatLength;
	}

	/**
	 * ��������ֵ�������ļ���ŵĸ�ʽ������
	 * @param subContentIDFormatLength �����ļ���ŵĸ�ʽ������
	 */
	public void setSubContentIDFormatLength(int subContentIDFormatLength)
	{
		this.subContentIDFormatLength = subContentIDFormatLength;
	}
	
	/**
	 * ����ǰ׺��ɱ��ʽ
	 */
	private String archivesIDExpressionPrefix=null;

	/**
	 * ��ȡ����ֵ������ǰ׺��ɱ��ʽ
	 * @return ����ǰ׺��ɱ��ʽ
	 */
	public String getArchivesIDExpressionPrefix()
	{
		return archivesIDExpressionPrefix;
	}

	/**
	 * ��������ֵ������ǰ׺��ɱ��ʽ
	 * @param archivesIDExpressionPrefix ����ǰ׺��ɱ��ʽ
	 */
	public void setArchivesIDExpressionPrefix(String archivesIDExpressionPrefix)
	{
		this.archivesIDExpressionPrefix = archivesIDExpressionPrefix;
	}

	/**
	 * ������ɱ��ʽ
	 */
	private String archivesIDExpression=null;

	/**
	 * ��ȡ����ֵ��������ɱ��ʽ
	 * @return ������ɱ��ʽ
	 */
	public String getArchivesIDExpression()
	{
		return archivesIDExpression;
	}

	/**
	 * ��������ֵ��������ɱ��ʽ
	 * @param archivesIDExpression ������ɱ��ʽ
	 */
	public void setArchivesIDExpression(String archivesIDExpression)
	{
		this.archivesIDExpression = archivesIDExpression;
	}
	
	/**
	 * �����ļ��ĵ�����ɱ��ʽ
	 */
	private String subArchivesIDExpression=null;

	/**
	 * ��ȡ����ֵ�������ļ��ĵ�����ɱ��ʽ
	 * @return �����ļ��ĵ�����ɱ��ʽ
	 */
	public String getSubArchivesIDExpression()
	{
		return subArchivesIDExpression;
	}

	/**
	 * ��������ֵ�������ļ��ĵ�����ɱ��ʽ
	 * @param subArchivesIDExpression �����ļ��ĵ�����ɱ��ʽ
	 */
	public void setSubArchivesIDExpression(String subArchivesIDExpression)
	{
		this.subArchivesIDExpression = subArchivesIDExpression;
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
	 * ��������
	 */
	private int renewPeriod=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getRenewPeriod()
	{
		return renewPeriod;
	}

	/**
	 * ��������ֵ����������
	 * @param renewPeriod ��������
	 */
	public void setRenewPeriod(int renewPeriod)
	{
		this.renewPeriod = renewPeriod;
	}
	
	/**
	 * ���µ�����־
	 */
	private EnumPersonalArchivesType personalArchivesFlag=EnumPersonalArchivesType.NONE;

	/**
	 * ��ȡ����ֵ�����µ�����־
	 * @return ���µ�����־
	 */
	public EnumPersonalArchivesType getPersonalArchivesFlag()
	{
		return personalArchivesFlag;
	}

	/**
	 * ��������ֵ�����µ�����־
	 * @param personalArchivesFlag ���µ�����־
	 */
	public void setPersonalArchivesFlag(EnumPersonalArchivesType personalArchivesFlag)
	{
		this.personalArchivesFlag = personalArchivesFlag;
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
	public ArchivesType clone()
	{
		ArchivesType item = new ArchivesType(iD,topFlag,name,code,parentID,topTypeID,fullCode,fullName,endFlag,contentIDFormatLength,subContentIDFormatLength,archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression,attachedFileSavePath,renewPeriod,personalArchivesFlag.getEnumValue(),remark);
		item.setRightFlag_ArchivesInfo(getRightFlag_ArchivesInfo());
		item.setRightFlag_AttachedFile(getRightFlag_AttachedFile());
		item.setRightFlag_PaperArchives(getRightFlag_PaperArchives());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		
		//һЩֻ����;���ֵ����Կ��������ϸ��¡��ֱ�ӹҽ���������ü���
		item.setArchivesInfoTables(archivesInfoTables);
		item.setDataItemsForAll(dataItemsForAll);
		item.setDataItemsForInput(dataItemsForInput);
		item.setDataItemsForInputQuery(dataItemsForInputQuery);
		item.setDataItemsForUseQuery(dataItemsForUseQuery);
		item.setDataItemsForListDisplay(dataItemsForListDisplay);
		item.setCatalogPrintTemplates(catalogPrintTemplates);
		
		//���ӷ�����Ҫ�ϸ��¡��������Ϊ���ܻ�ı���
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				LinkedHashMap<Integer, ArchivesType> children=new LinkedHashMap<Integer, ArchivesType>();
				for (ArchivesType childItem : childArchivesTypes.values())
				{
					children.put(childItem.getID(), childItem.clone());
				}
				
				item.setChildArchivesTypes(children);
			}
		}

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesType ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesType archivesType)
	{
		super.cloneFrom(archivesType);
		this.iD = archivesType.getID();
		this.topFlag = archivesType.getTopFlag();
		this.name = archivesType.getName();
		this.code = archivesType.getCode();
		this.parentID = archivesType.getParentID();
		this.topTypeID = archivesType.getTopTypeID();
		this.fullCode = archivesType.getFullCode();
		this.fullName = archivesType.getFullName();
		this.endFlag = archivesType.getEndFlag();
		this.contentIDFormatLength = archivesType.getContentIDFormatLength();
		this.subContentIDFormatLength = archivesType.getSubContentIDFormatLength();
		this.archivesIDExpressionPrefix = archivesType.getArchivesIDExpressionPrefix();
		this.archivesIDExpression = archivesType.getArchivesIDExpression();
		this.subArchivesIDExpression = archivesType.getSubArchivesIDExpression();
		this.attachedFileSavePath = archivesType.getAttachedFileSavePath();
		this.renewPeriod = archivesType.getRenewPeriod();
		this.personalArchivesFlag = archivesType.getPersonalArchivesFlag();
		this.remark = archivesType.getRemark();
		this.tag=archivesType.getTag();
		this.keyInCol=archivesType.getKeyInCol();
		
		this.parentArchivesType=archivesType.getParentArchivesType();
		this.childArchivesTypes=archivesType.getChildArchivesTypes();
		this.archivesInfoTables=archivesType.getArchivesInfoTables();
		this.dataItemsForAll=archivesType.getDataItemsForAll();
		this.dataItemsForInput=archivesType.getDataItemsForInput();
		this.dataItemsForInputQuery=archivesType.getDataItemsForInputQuery();
		this.dataItemsForUseQuery=archivesType.getDataItemsForUseQuery();
		this.dataItemsForListDisplay=archivesType.getDataItemsForListDisplay();
		this.catalogPrintTemplates=archivesType.getCatalogPrintTemplates();
	}

	/**
	* ��ָ�������¡�����ƻ����ĵ�����ԴȨ������
	* @param baseRightArchivesResource ָ���Ķ���Դ
	*/
	public void cloneBaseRightArchivesResourceFrom(BaseRightArchivesResource baseRightArchivesResource)
	{
		this.setRightFlag_ArchivesInfo(baseRightArchivesResource.getRightFlag_ArchivesInfo());
		this.setRightFlag_AttachedFile(baseRightArchivesResource.getRightFlag_AttachedFile());
		this.setRightFlag_PaperArchives(baseRightArchivesResource.getRightFlag_PaperArchives());
	}



	/**
	 * �ϼ���������
	 */
	private ArchivesType parentArchivesType = null;

	/**
	 * ��ȡ����ֵ���ϼ���������
	 */
	public ArchivesType getParentArchivesType() {
		return parentArchivesType;
	}

	/**
	 * ��������ֵ���ϼ���������
	 */
	public void setParentArchivesType(ArchivesType parentArchivesType) {
		this.parentArchivesType = parentArchivesType;
	}

	/**
	 * �¼��������༯��
	 */
	private LinkedHashMap<Integer, ArchivesType> childArchivesTypes=null;

	/**
	 * ��������ֵ���¼��������༯��
	 * 
	 * @param childArchivesTypes
	 *            �¼��������༯��
	 */
	public void setChildArchivesTypes(
			LinkedHashMap<Integer, ArchivesType> childArchivesTypes) {
		this.childArchivesTypes = childArchivesTypes;
		
		//����������������ĸ������������Զ�����ͳһ��ֵ
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setParentArchivesType(this);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ���¼��������༯��
	 */
	public LinkedHashMap<Integer, ArchivesType> getChildArchivesTypes() {
		return childArchivesTypes;
	}


	/**
	 * ��������������Ϣ����
	 */
	private EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables =null;

	/**
	 * ��������ֵ����������������Ϣ����
	 * 
	 * @param archivesInfoTables
	 *            ��������������Ϣ����
	 */
	public void setArchivesInfoTables(
			EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables) {
		this.archivesInfoTables = archivesInfoTables;
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ����е��������Ϣ����ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setArchivesInfoTables(archivesInfoTables);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ����������������Ϣ����
	 * 
	 * @return ��������������Ϣ����
	 */
	public EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> getArchivesInfoTables() {
		return archivesInfoTables;
	}
	
	/**
	 * ��ǰ�������ඨ���������������ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll = null;

	/**
	 * ��������ֵ����ǰ�������ඨ���������������ֶ�����Ϊ�ؼ���
	 * @param dataItemsForAll ��ǰ�������ඨ���������������ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForAll(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll)
	{
		this.dataItemsForAll = dataItemsForAll;
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ����е����������������Ϣ����ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForAll(dataItemsForAll);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ����ǰ�������ඨ���������������ֶ�����Ϊ�ؼ���
	 * @return ��ǰ�������ඨ���������������ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForAll()
	{
		return dataItemsForAll;
	}

	/**
	 * ��ǰ����������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput = null;

	/**
	 * ��������ֵ����ǰ����������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForInput ��ǰ����������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForInput(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput)
	{
		this.dataItemsForInput = dataItemsForInput;
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ����е����������������Ϣ����ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForInput(dataItemsForInput);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ����ǰ����������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ����������Ҫ��¼��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInput()
	{
		return dataItemsForInput;
	}

	/**
	 * ��ǰ�����������Ϊ��¼��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery = null;

	/**
	 * ��������ֵ����ǰ�����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForQuery ��ǰ�����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForInputQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery)
	{
		this.dataItemsForInputQuery = dataItemsForInputQuery;
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ����е����������������Ϣ����ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForInputQuery(dataItemsForInputQuery);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ����ǰ�����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ�����������Ϊ��ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForInputQuery()
	{
		return dataItemsForInputQuery;
	}
	
	/**
	 * ��ǰ�����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery = null;

	/**
	 * ��������ֵ����ǰ�����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForUseQuery ��ǰ�����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForUseQuery(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery)
	{
		this.dataItemsForUseQuery = dataItemsForUseQuery;
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ����е����������������Ϣ����ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForUseQuery(dataItemsForUseQuery);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ����ǰ�����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ�����������Ϊ�������ò�ѯ������������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForUseQuery()
	{
		return dataItemsForUseQuery;
	}

	/**
	 * ��ǰ���������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	private LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay = null;

	/**
	 * ��������ֵ����ǰ���������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 * @param dataItemsForListDisplay ��ǰ���������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setDataItemsForListDisplay(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay)
	{
		this.dataItemsForListDisplay = dataItemsForListDisplay;
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ����е����������������Ϣ����ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setDataItemsForListDisplay(dataItemsForListDisplay);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ����ǰ���������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 * @return ��ǰ���������ѯ������б���ʾ��������ϣ����ֶ�����Ϊ�ؼ���
	 */
	public LinkedHashMap<String, ArchivesTypeDataItem> getDataItemsForListDisplay()
	{
		return dataItemsForListDisplay;
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
		
		//���������������඼����ͳһ��ֵ����Ϊͬ��һ����Ŀ�µ�����Ŀ¼��ӡģ�嶼��ȫһ��
		if (childArchivesTypes!=null)
		{
			if (childArchivesTypes.size()>0)
			{
				for (ArchivesType item : childArchivesTypes.values())
				{
					item.setCatalogPrintTemplates(catalogPrintTemplates);
				}
			}
		}
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
