package com.orifound.aiim.entity;

    
/**
 * רְ��Ա����Ĳ�����Ϣ���ʵ����
 */
public class UserChargeDepartmentInfo extends DepartmentInfo
{
    /**
     * ���캯��
     */
    public UserChargeDepartmentInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param userID �û����
	* @param departmentID ���ű��
	*/
	public UserChargeDepartmentInfo(int iD,int userID,int departmentID)
	{
		// Table Name: UserChargeDepartment
		// Columns List,Can Used in SELECT SQL: ID,UserID,DepartmentID
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:DepartmentID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,DepartmentID=:DepartmentID

		this(userID, departmentID);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param userID �û����
	* @param departmentID ���ű��
	*/
	public UserChargeDepartmentInfo(int userID,int departmentID)
	{
		this.userID = userID;
		this.departmentID = departmentID;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param userID �û����
	* @param departmentID ���ű��
	* @param name ��������
	* @param remark ��ע
	*/
	public UserChargeDepartmentInfo(int iD,int userID,int departmentID,String name,String remark)
	{
		this(userID, departmentID);
		this.iD = iD;
		super.setName(name);
		super.setRemark(remark);
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param userID �û����
	* @param departmentID ���ű��
	* @param name ��������
	* @param remark ��ע
	*/
	public UserChargeDepartmentInfo(int departmentID,String name,String remark)
	{
		this.departmentID = departmentID;
		super.setName(name);
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
	 * ���ű��
	 */
	private int departmentID=0;

	/**
	 * ��ȡ����ֵ�����ű��
	 * @return ���ű��
	 */
	public int getDepartmentID()
	{
		return departmentID;
	}

	/**
	 * ��������ֵ�����ű��
	 * @param departmentID ���ű��
	 */
	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserChargeDepartmentInfo clone()
	{
		UserChargeDepartmentInfo item = new UserChargeDepartmentInfo(iD,userID,departmentID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userChargeDepartmentInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(UserChargeDepartmentInfo userChargeDepartmentInfo)
	{
		this.iD = userChargeDepartmentInfo.getID();
		this.userID = userChargeDepartmentInfo.getUserID();
		this.departmentID = userChargeDepartmentInfo.getDepartmentID();
	}



    
}



