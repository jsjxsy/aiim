package com.orifound.aiim.entity;

import java.util.LinkedHashMap;

    
/**
 * ϵͳ���������ֵ���ʵ����
 */
public class SystemFeature
{
    /**
     * ���캯��
     */
    public SystemFeature()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param title ���ܱ���
	* @param name ����
	* @param parentID �ϼ����
	* @param uclKey �û����ʿ����б��е�Keyֵ
	* @param menuFlag �˵���־
	* @param topFlag ���㹦�ܱ�־
	* @param menuURI �˵�����URI��ַ
	* @param pageSize ��ҳ��ʾ�Ĵ�С
	* @param remark ��ע
	*/
	public SystemFeature(int iD,String title,String name,int parentID,String uclKey,boolean menuFlag,boolean topFlag,String menuURI,int pageSize,String remark)
	{
		// Table Name: DD_SystemFeature
		// Columns List,Can Used in SELECT SQL: ID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,PageSize,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Title,:Name,:ParentID,:UclKey,:MenuFlag,:TopFlag,:MenuURI,:PageSize,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Title=:Title,Name=:Name,ParentID=:ParentID,UclKey=:UclKey,MenuFlag=:MenuFlag,TopFlag=:TopFlag,MenuURI=:MenuURI,PageSize=:PageSize,Remark=:Remark

		this(title, name, parentID, uclKey, menuFlag, topFlag, menuURI,pageSize, remark);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param title ���ܱ���
	* @param name ����
	* @param parentID �ϼ����
	* @param uclKey �û����ʿ����б��е�Keyֵ
	* @param menuFlag �˵���־
	* @param topFlag ���㹦�ܱ�־
	* @param menuURI �˵�����URI��ַ
	* @param pageSize ��ҳ��ʾ�Ĵ�С
	* @param remark ��ע
	*/
	public SystemFeature(String title,String name,int parentID,String uclKey,boolean menuFlag,boolean topFlag,String menuURI,int pageSize,String remark)
	{
		this.title = title;
		this.name = name;
		this.parentID = parentID;
		this.uclKey = uclKey;
		this.menuFlag = menuFlag;
		this.topFlag = topFlag;
		this.menuURI = menuURI;
		this.pageSize = pageSize;
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
	 * ���ܱ���
	 */
	private String title=null;

	/**
	 * ��ȡ����ֵ�����ܱ���
	 * @return 
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * ��������ֵ�����ܱ���
	 * @param title 
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * ����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ������
	 * @param name ����
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * �ϼ����
	 */
	private int parentID=0;

	/**
	 * ��ȡ����ֵ���ϼ����
	 * @return �ϼ����
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * ��������ֵ���ϼ����
	 * @param parentID �ϼ����
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * �û����ʿ����б��е�Keyֵ
	 */
	private String uclKey=null;

	/**
	 * ��ȡ����ֵ���û����ʿ����б��е�Keyֵ
	 * @return �û����ʿ����б��е�Keyֵ
	 */
	public String getUclKey()
	{
		return uclKey;
	}

	/**
	 * ��������ֵ���û����ʿ����б��е�Keyֵ
	 * @param uclKey �û����ʿ����б��е�Keyֵ
	 */
	public void setUclKey(String uclKey)
	{
		this.uclKey = uclKey;
	}

	/**
	 * �˵���־
	 */
	private boolean menuFlag=false;

	/**
	 * ��ȡ����ֵ���˵���־
	 * @return �˵���־
	 */
	public boolean getMenuFlag()
	{
		return menuFlag;
	}

	/**
	 * ��������ֵ���˵���־
	 * @param menuFlag �˵���־
	 */
	public void setMenuFlag(boolean menuFlag)
	{
		this.menuFlag = menuFlag;
	}

	/**
	 * ���㹦�ܱ�־
	 */
	private boolean topFlag=false;

	/**
	 * ��ȡ����ֵ�����㹦�ܱ�־
	 * @return ���㹦�ܱ�־
	 */
	public boolean getTopFlag()
	{
		return topFlag;
	}

	/**
	 * ��������ֵ�����㹦�ܱ�־
	 * @param topFlag ���㹦�ܱ�־
	 */
	public void setTopFlag(boolean topFlag)
	{
		this.topFlag = topFlag;
	}

	/**
	 * �˵�����URI��ַ
	 */
	private String menuURI=null;

	/**
	 * ��ȡ����ֵ���˵�����URI��ַ
	 * @return �˵�����URI��ַ
	 */
	public String getMenuURI()
	{
		return menuURI;
	}

	/**
	 * ��������ֵ���˵�����URI��ַ
	 * @param menuURI �˵�����URI��ַ
	 */
	public void setMenuURI(String menuURI)
	{
		this.menuURI = menuURI;
	}

	/**
	 * ��ҳ��ʾ�Ĵ�С
	 */
	private int pageSize=0;

	/**
	 * ��ȡ����ֵ����ҳ��ʾ�Ĵ�С
	 * @return ��ҳ��ʾ�Ĵ�С
	 */
	public int getPageSize()
	{
		return pageSize;
	}

	/**
	 * ��������ֵ����ҳ��ʾ�Ĵ�С
	 * @param pageSize ��ҳ��ʾ�Ĵ�С
	 */
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
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
	public SystemFeature clone()
	{
		SystemFeature item = new SystemFeature(iD,title,name,parentID,uclKey,menuFlag,topFlag,menuURI,pageSize,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param systemFeature ָ���Ķ���Դ
	*/
	public void cloneFrom(SystemFeature systemFeature)
	{
		this.iD = systemFeature.getID();
		this.title = systemFeature.getTitle();
		this.name = systemFeature.getName();
		this.parentID = systemFeature.getParentID();
		this.uclKey = systemFeature.getUclKey();
		this.menuFlag = systemFeature.getMenuFlag();
		this.topFlag = systemFeature.getTopFlag();
		this.menuURI = systemFeature.getMenuURI();
		this.pageSize = systemFeature.getPageSize();
		this.remark = systemFeature.getRemark();
	}
	
	/**
	 * �ӹ��ܼ���
	 */
	private LinkedHashMap<String, SystemFeature> childSystemFeatures = null;

	/**
	 * ��������ֵ���ӹ��ܼ���
	 * @param childSystemFeatures �ӹ��ܼ���
	 */
	public void setChildSystemFeatures(LinkedHashMap<String, SystemFeature> childSystemFeatures)
	{
		this.childSystemFeatures = childSystemFeatures;
	}

	/**
	 * ��ȡ����ֵ���ӹ��ܼ���
	 * @return �ӹ��ܼ���
	 */
	public LinkedHashMap<String, SystemFeature> getChildSystemFeatures()
	{
		return childSystemFeatures;
	}

}



