package com.orifound.aiim.entity;

    
/**
 * �û���ɫ��ϵͳ�����ֵ��ϵ���ʵ����
 */
public class RoleRightSystemFeature extends SystemFeature
{
    /**
     * ���캯��
     */
    public RoleRightSystemFeature()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param rolesID �û���ɫ���
	* @param featureID ϵͳ���ܱ��
	*/
	public RoleRightSystemFeature(int iD,int rolesID,int featureID)
	{
		// Table Name: DDR_UserRoles_SystemFeature
		// Columns List,Can Used in SELECT SQL: ID,RolesID,FeatureID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:FeatureID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,FeatureID=:FeatureID

		this(rolesID, featureID);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param rolesID �û���ɫ���
	* @param featureID ϵͳ���ܱ��
	*/
	public RoleRightSystemFeature(int rolesID,int featureID)
	{
		this.rolesID = rolesID;
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
	public RoleRightSystemFeature(int iD,int rolesID,int featureID,String title,String name,int parentID,
			String uclKey,boolean menuFlag,boolean topFlag,String menuURI,String remark)
	{
		this(iD,rolesID, featureID);
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
	 * �û���ɫ���
	 */
	private int rolesID=0;

	/**
	 * ��ȡ����ֵ���û���ɫ���
	 * @return �û���ɫ���
	 */
	public int getRolesID()
	{
		return rolesID;
	}

	/**
	 * ��������ֵ���û���ɫ���
	 * @param rolesID �û���ɫ���
	 */
	public void setRolesID(int rolesID)
	{
		this.rolesID = rolesID;
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
	public RoleRightSystemFeature clone()
	{
		RoleRightSystemFeature item = new RoleRightSystemFeature(featureID, rolesID, featureID, getTitle(), getName(), getParentID(), getUclKey(), getMenuFlag(), getTopFlag(), getMenuURI(), getRemark());
		item.setKeyInCol(keyInCol);
		item.setTag(tag);
		
		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param roleRightSystemFeature ָ���Ķ���Դ
	*/
	public void cloneFrom(RoleRightSystemFeature roleRightSystemFeature)
	{
		super.cloneFrom(roleRightSystemFeature);
		this.iD = roleRightSystemFeature.getID();
		this.rolesID = roleRightSystemFeature.getRolesID();
		this.featureID = roleRightSystemFeature.getFeatureID();
	}

    
}



