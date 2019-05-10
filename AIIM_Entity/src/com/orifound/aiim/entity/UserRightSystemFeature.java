package com.orifound.aiim.entity;

    
/**
 * �û�ϵͳ����Ȩ�ޱ��ʵ����
 */
public class UserRightSystemFeature extends SystemFeature
{
    /**
     * ���캯��
     */
    public UserRightSystemFeature()
    {
    	
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ���
	* @param userID �û����
	* @param featureID ϵͳ���ܱ��
	*/
	public UserRightSystemFeature(int iD,int userID,int featureID)
	{
		// Table Name: UserRight_SystemFeature
		// Columns List,Can Used in SELECT SQL: ID,UserID,FeatureID
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:FeatureID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,FeatureID=:FeatureID
		
		this(userID, featureID);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param userID �û����
	* @param featureID ϵͳ���ܱ��
	*/
	public UserRightSystemFeature(int userID,int featureID)
	{
		this.userID = userID;
		this.featureID = featureID;
	}
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��Ȩ���
	* @param userID �û����
	* @param featureID ϵͳ���ܱ��
	* @param title ���ܱ���
	* @param name ����
	* @param parentID �ϼ����
	* @param uclKey �û����ʿ����б��е�Keyֵ
	* @param menuFlag �˵���־
	* @param topFlag ���㹦�ܱ�־
	* @param menuURI �˵�����URI��ַ
	* @param remark ��ע
	*/
	public UserRightSystemFeature(int iD,int userID,int featureID,String title,String name,int parentID,
			String uclKey,boolean menuFlag,boolean topFlag,String menuURI,String remark)
	{
		this(iD,userID, featureID);
		super.setTitle(title);
		super.setName(name);
		super.setParentID(parentID);
		super.setUclKey(uclKey);
		super.setMenuFlag(menuFlag);
		super.setTopFlag(topFlag);
		super.setMenuURI(menuURI);
		super.setRemark(remark);
	}
	
	public UserRightSystemFeature(int iD,String title,String name,int parentID,
			String uclKey,boolean menuFlag,boolean topFlag,String menuURI,String remark)
	{
		super.setID(iD);
		super.setTitle(title);
		super.setName(name);
		super.setParentID(parentID);
		super.setUclKey(uclKey);
		super.setMenuFlag(menuFlag);
		super.setTopFlag(topFlag);
		super.setMenuURI(menuURI);
		super.setRemark(remark);
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
	 * �û����
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ���û����
	 * @return �û����
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ���û����
	 * @param userID �û����
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * ϵͳ���ܱ��
	 */
	private int featureID=0;

	/**
	 * ��ȡ����ֵ��ϵͳ���ܱ��
	 * @return ϵͳ���ܱ��
	 */
	public int getFeatureID()
	{
		return featureID;
	}

	/**
	 * ��������ֵ��ϵͳ���ܱ��
	 * @param featureID ϵͳ���ܱ��
	 */
	public void setFeatureID(int featureID)
	{
		this.featureID = featureID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserRightSystemFeature clone()
	{
		UserRightSystemFeature item = new UserRightSystemFeature(iD,userID,featureID,getTitle(), getName(), getParentID(), getUclKey(), getMenuFlag(), getTopFlag(), getMenuURI(), getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRightSystemFeature ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRightSystemFeature userRightSystemFeature)
	{
		super.cloneFrom(userRightSystemFeature);
		this.iD = userRightSystemFeature.getID();
		this.userID = userRightSystemFeature.getUserID();
		this.featureID = userRightSystemFeature.getFeatureID();
	}



    
}



