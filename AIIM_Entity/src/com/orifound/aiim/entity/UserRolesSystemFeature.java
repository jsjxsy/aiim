package com.orifound.aiim.entity;

    
/**
 * �û���ɫ��ϵͳ����Ȩ�ޱ�ʵ����
 */
public class UserRolesSystemFeature
{
    /**
     * ���캯��
     */
    public UserRolesSystemFeature()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param rolesID �û���ɫ���
	* @param featureID ϵͳ���ܱ��
	*/
	public UserRolesSystemFeature(int iD,int rolesID,int featureID)
	{
		// Table Name: DDR_UserRoles_SystemFeature
		// Columns List,Can Used in SELECT SQL: ID,RolesID,FeatureID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:FeatureID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,FeatureID=:FeatureID

		this.iD = iD;
		this.rolesID = rolesID;
		this.featureID = featureID;
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
	public UserRolesSystemFeature clone()
	{
		UserRolesSystemFeature item = new UserRolesSystemFeature(iD,rolesID,featureID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRolesSystemFeature ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRolesSystemFeature userRolesSystemFeature)
	{
		this.iD = userRolesSystemFeature.getID();
		this.rolesID = userRolesSystemFeature.getRolesID();
		this.featureID = userRolesSystemFeature.getFeatureID();
		this.keyInCol = userRolesSystemFeature.getKeyInCol();
		this.tag = userRolesSystemFeature.getTag();
	}



    
}


