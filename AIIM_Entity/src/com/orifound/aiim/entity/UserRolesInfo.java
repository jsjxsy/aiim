package com.orifound.aiim.entity;


    
/**
 * �û���ɫ��Ϣ���ʵ����
 */
public class UserRolesInfo extends UserRole
{
    /**
     * ���캯��
     */
    public UserRolesInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param rolesID �û���ɫ���
	* @param userID �û����
	*/
	public UserRolesInfo(int iD,int rolesID,int userID)
	{
		// Table Name: UserRolesInfo
		// Columns List,Can Used in SELECT SQL: ID,RolesID,UserID
		// Columns List,Can Used in INSERT SQL: :ID,:RolesID,:UserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,RolesID=:RolesID,UserID=:UserID

		this(rolesID, userID);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param rolesID �û���ɫ���
	* @param userID �û����
	*/
	public UserRolesInfo(int rolesID,int userID)
	{
		this.rolesID = rolesID;
		this.userID = userID;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param rolesID �û���ɫ���
	* @param userID �û����
	* @param name ��ɫ����
	* @param remark ��ע
	*/
	public UserRolesInfo(int iD,int rolesID,int userID,String name,String remark)
	{
		this(iD,rolesID, userID);
		super.setName(name);
		super.setRemark(remark);
	}
	
	/**
	 * 
	 * @param iD
	 * @param rolesID
	 * @param userID
	 * @param UserName
	 * @param RealName
	 * @param iDCardNo
	 */
	public UserRolesInfo(int iD,int rolesID,int userID,String UserName,String RealName,String iDCardNo)
	{
		this(iD,rolesID, userID);
		this.setUserName(UserName);
		this.setRealName(RealName);
		this.setIDCardNo(iDCardNo);
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
	 * ��ʵ����
	 */
	private String realName;
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	/**
	 * �û���
	 */
	private String userName;
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * ֤������
	 */
	private String iDCardNo; 
	
	

	public String getIDCardNo() {
		return iDCardNo;
	}

	public void setIDCardNo(String iDCardNo) {
		this.iDCardNo = iDCardNo;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserRolesInfo clone()
	{
		UserRolesInfo item = new UserRolesInfo(iD,rolesID,userID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userRolesInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(UserRolesInfo userRolesInfo)
	{
		super.cloneFrom(userRolesInfo);
		this.iD = userRolesInfo.getID();
		this.rolesID = userRolesInfo.getRolesID();
		this.userID = userRolesInfo.getUserID();
	}



	
    
}



