package com.orifound.aiim.entity;

    
/**
 * �û�������Ϣ���ʵ����
 */
public class UserChargeUserInfo
{
    /**
     * ���캯��
     */
    public UserChargeUserInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param userID �����û����
	* @param chargedUserID �������û����
	*/
	public UserChargeUserInfo(int iD,int userID,int chargedUserID,String chargedUserName)
	{
		// Table Name: UserChargeUser
		// Columns List,Can Used in SELECT SQL: ID,UserID,ChargedUserID
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:ChargedUserID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,ChargedUserID=:ChargedUserID

		this(userID, chargedUserID);
		this.iD = iD;
		this.chargedUserName = chargedUserName;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param userID �����û����
	* @param chargedUserID �������û����
	*/
	public UserChargeUserInfo(int userID,int chargedUserID)
	{
		this.userID = userID;
		this.chargedUserID = chargedUserID;
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
	 * �����û����
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ�������û����
	 * @return �����û����
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ�������û����
	 * @param userID �����û����
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * �������û����
	 */
	private int chargedUserID=0;

	/**
	 * ��ȡ����ֵ���������û����
	 * @return �������û����
	 */
	public int getChargedUserID()
	{
		return chargedUserID;
	}

	/**
	 * ��������ֵ���������û����
	 * @param chargedUserID �������û����
	 */
	public void setChargedUserID(int chargedUserID)
	{
		this.chargedUserID = chargedUserID;
	}

	private String chargedUserName;
	
	public String getChargedUserName() {
		return chargedUserName;
	}

	public void setChargedUserName(String chargedUserName) {
		this.chargedUserName = chargedUserName;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserChargeUserInfo clone()
	{
		UserChargeUserInfo item = new UserChargeUserInfo(iD,userID,chargedUserID,chargedUserName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userChargeUserInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(UserChargeUserInfo userChargeUserInfo)
	{
		this.iD = userChargeUserInfo.getID();
		this.userID = userChargeUserInfo.getUserID();
		this.chargedUserID = userChargeUserInfo.getChargedUserID();
		this.chargedUserName = userChargeUserInfo.getChargedUserName();
	}



    
}



