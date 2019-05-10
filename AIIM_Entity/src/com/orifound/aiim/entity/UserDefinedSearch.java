package com.orifound.aiim.entity;
 
/**
 * �û��Զ���������ѯ��
 */
public class UserDefinedSearch
{
    /**
     * ���캯��
     */
    public UserDefinedSearch()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD 
	* @param userID 
	* @param name 
	* @param archivesIDString 
	* @param whereString 
	*/
	public UserDefinedSearch(int iD,int userID,String name,String archivesIDString,String whereString)
	{
		// Table Name: UserDefinedSearch
		// Columns List,Can Used in SELECT SQL: ID,UserID,Name,ArchivesIDString,WhereString
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:Name,:ArchivesIDString,:WhereString
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,Name=:Name,ArchivesIDString=:ArchivesIDString,WhereString=:WhereString

		this.iD = iD;
		this.userID = userID;
		this.name = name;
		this.archivesIDString = archivesIDString;
		this.whereString = whereString;
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
	 * 
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ��
	 * @return 
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ��
	 * @param iD 
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ��
	 * @return 
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ��
	 * @param userID 
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ��
	 * @return 
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ��
	 * @param name 
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 
	 */
	private String archivesIDString=null;

	/**
	 * ��ȡ����ֵ��
	 * @return 
	 */
	public String getArchivesIDString()
	{
		return archivesIDString;
	}

	/**
	 * ��������ֵ��
	 * @param archivesIDString 
	 */
	public void setArchivesIDString(String archivesIDString)
	{
		this.archivesIDString = archivesIDString;
	}

	/**
	 * 
	 */
	private String whereString=null;

	/**
	 * ��ȡ����ֵ��
	 * @return 
	 */
	public String getWhereString()
	{
		return whereString;
	}

	/**
	 * ��������ֵ��
	 * @param whereString 
	 */
	public void setWhereString(String whereString)
	{
		this.whereString = whereString;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserDefinedSearch clone()
	{
		UserDefinedSearch item = new UserDefinedSearch(iD,userID,name,archivesIDString,whereString);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userDefinedSearch ָ���Ķ���Դ
	*/
	public void cloneFrom(UserDefinedSearch userDefinedSearch)
	{
		this.iD = userDefinedSearch.getID();
		this.userID = userDefinedSearch.getUserID();
		this.name = userDefinedSearch.getName();
		this.archivesIDString = userDefinedSearch.getArchivesIDString();
		this.whereString = userDefinedSearch.getWhereString();
		this.keyInCol = userDefinedSearch.getKeyInCol();
		this.tag = userDefinedSearch.getTag();
	}


    
}



