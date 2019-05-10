package com.orifound.aiim.entity;

import java.util.List;

    
/**
 * �û���ɫ�ֵ���ʵ����
 */
public class UserRole
{
    /**
     * ���캯��
     */
    public UserRole()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��ɫ���
	* @param name ��ɫ����
	* @param systemRolesFlag ϵͳ���н�ɫ��־
	* @param remark ��ע
	*/
	public UserRole(int iD,String name,int systemRolesFlag,String remark)
	{
		// Table Name: DD_UserRoles
		// Columns List,Can Used in SELECT SQL: ID,Name,SystemRolesFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:SystemRolesFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.systemRolesFlag = EnumSystemRole.getEnumElement(systemRolesFlag);
		this.remark = remark;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��ɫ���
	* @param name ��ɫ����
	* @param systemRole ϵͳ���н�ɫö����
	* @param remark ��ע
	*/
	public UserRole(int iD,String name,EnumSystemRole systemRole,String remark)
	{
		// Table Name: DD_UserRoles
		// Columns List,Can Used in SELECT SQL: ID,Name,SystemRolesFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:SystemRolesFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.systemRolesFlag = systemRole;
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
	 * ��ɫ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����ɫ���
	 * @return ��ɫ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����ɫ���
	 * @param iD ��ɫ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��ɫ����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ����ɫ����
	 * @return ��ɫ����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ����ɫ����
	 * @param name ��ɫ����
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ϵͳ���н�ɫ��־
	 */
	private EnumSystemRole systemRolesFlag= EnumSystemRole.NONE;

	/**
	 * ��ȡ����ֵ��ϵͳ���н�ɫ��־
	 * @return ϵͳ���н�ɫ��־
	 */
	public EnumSystemRole getSystemRolesFlag()
	{
		return systemRolesFlag;
	}

	/**
	 * ��������ֵ��ϵͳ���н�ɫ��־
	 * @param systemRolesFlag ϵͳ���н�ɫ��־
	 */
	public void setSystemRolesFlag(EnumSystemRole systemRolesFlag)
	{
		this.systemRolesFlag = systemRolesFlag;
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
	public UserRole clone()
	{
		UserRole item = new UserRole(iD,name,systemRolesFlag,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRole ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRole userRole)
	{
		this.iD = userRole.getID();
		this.name = userRole.getName();
		this.systemRolesFlag = userRole.getSystemRolesFlag();
		this.remark = userRole.getRemark();
		this.keyInCol = userRole.getKeyInCol();
		this.tag = userRole.getTag();
	}
    
	/**
	 * ��ǰ��ɫӵ�е��û���Ϣ����
	 */
	private List<UserRolesInfo> hasUsers;

	/**
	 * ��������ֵ����ǰ��ɫӵ�е��û���Ϣ����
	 * @param hasUsers ��ǰ��ɫӵ�е��û���Ϣ����
	 */
	public void setHasUsers(List<UserRolesInfo> hasUsers) {
		this.hasUsers = hasUsers;
	}

	/**
	 * ��ȡ����ֵ����ǰ��ɫӵ�е��û���Ϣ����
	 * @return ��ǰ��ɫӵ�е��û���Ϣ����
	 */
	public List<UserRolesInfo> getHasUsers() {
		return hasUsers;
	}
	
	
}



