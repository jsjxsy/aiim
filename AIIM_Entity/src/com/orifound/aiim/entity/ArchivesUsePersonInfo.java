//package com.orifound.aiim.entity;
//
//    
///**
// * ������������Ϣ���ʵ����
// */
//public class ArchivesUsePersonInfo
//{
//    /**
//     * ���캯��
//     */
//    public ArchivesUsePersonInfo()
//    {
//        
//    }
//    
//	/**
//	* ���ֶβ����Ĺ��캯��
//	* @param name ��ʵ����
//	* @param iDCardNo ֤������
//	* @param iDCardTypeID ֤������
//	* @param department ��λ
//	* @param tel �绰
//	* @param email ��������
//	* @param areaID ������
//	*/
//	public ArchivesUsePersonInfo(String name,String iDCardNo,int iDCardTypeID,String department,String tel,String email,int areaID)
//	{
//		this.name = name;
//		this.iDCardNo = iDCardNo;
//		this.iDCardTypeID = iDCardTypeID;
//		this.department = department;
//		this.tel = tel;
//		this.email = email;
//		this.areaID = areaID;
//	}
//    
//	/**
//	* ���ֶβ����Ĺ��캯��
//	* @param iD �����˱��
//	* @param name ��ʵ����
//	* @param iDCardNo ֤������
//	* @param iDCardTypeID ֤������
//	* @param department ��λ
//	* @param tel �绰
//	* @param email ��������
//	* @param areaID ������
//	*/
//	public ArchivesUsePersonInfo(int iD,String name,String iDCardNo,int iDCardTypeID,String department,String tel,String email,int areaID)
//	{
//		// Columns List,Can Used in SELECT SQL: ID,Name,IDCardNo,IDCardTypeID,Department,Tel,Email,AreaID
//		// Columns List,Can Used in INSERT SQL: pID,pName,pIDCardNo,pIDCardTypeID,pDepartment,pTel,pEmail,pAreaID
//		// Columns List,Can Used in UPDATE SQL: ID=pID,Name=pName,IDCardNo=pIDCardNo,IDCardTypeID=pIDCardTypeID,Department=pDepartment,Tel=pTel,Email=pEmail,AreaID=pAreaID
//
//		this(name, iDCardNo, iDCardTypeID, department, tel, email, areaID);
//		this.iD = iD;
//	}
//
//	/**
//	 * ��Ա�����ڼ����еĹؼ���
//	 */
//	private Object keyInCol=null;
//
//	/**
//	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
//	 * @return ��Ա�����ڼ����еĹؼ���
//	 */
//	public Object getKeyInCol()
//	{
//		return keyInCol;
//	}
//
//	/**
//	 * ��������ֵ����Ա�����ڼ����еĹؼ���
//	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
//	 */
//	public void setKeyInCol(Object keyInCol)
//	{
//		this.keyInCol = keyInCol;
//	}
//
//	/**
//	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 */
//	private Object tag=null;
//
//	/**
//	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 */
//	public Object getTag()
//	{
//		return tag;
//	}
//
//	/**
//	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
//	 */
//	public void setTag(Object tag)
//	{
//		this.tag = tag;
//	}
//
//	/**
//	 * �����˱��
//	 */
//	private int iD=0;
//
//	/**
//	 * ��ȡ����ֵ�������˱��
//	 * @return �����˱��
//	 */
//	public int getID()
//	{
//		return iD;
//	}
//
//	/**
//	 * ��������ֵ�������˱��
//	 * @param iD �����˱��
//	 */
//	public void setID(int iD)
//	{
//		this.iD = iD;
//	}
//
//	/**
//	 * ��ʵ����
//	 */
//	private String name=null;
//
//	/**
//	 * ��ȡ����ֵ����ʵ����
//	 * @return ��ʵ����
//	 */
//	public String getName()
//	{
//		return name;
//	}
//
//	/**
//	 * ��������ֵ����ʵ����
//	 * @param name ��ʵ����
//	 */
//	public void setName(String name)
//	{
//		this.name = name;
//	}
//
//	/**
//	 * ֤������
//	 */
//	private String iDCardNo=null;
//
//	/**
//	 * ��ȡ����ֵ��֤������
//	 * @return ֤������
//	 */
//	public String getIDCardNo()
//	{
//		return iDCardNo;
//	}
//
//	/**
//	 * ��������ֵ��֤������
//	 * @param iDCardNo ֤������
//	 */
//	public void setIDCardNo(String iDCardNo)
//	{
//		this.iDCardNo = iDCardNo;
//	}
//
//	/**
//	 * ֤������
//	 */
//	private int iDCardTypeID=0;
//
//	/**
//	 * ��ȡ����ֵ��֤������
//	 * @return ֤������
//	 */
//	public int getIDCardTypeID()
//	{
//		return iDCardTypeID;
//	}
//
//	/**
//	 * ��������ֵ��֤������
//	 * @param iDCardTypeID ֤������
//	 */
//	public void setIDCardTypeID(int iDCardTypeID)
//	{
//		this.iDCardTypeID = iDCardTypeID;
//	}
//
//	/**
//	 * ��λ
//	 */
//	private String department=null;
//
//	/**
//	 * ��ȡ����ֵ����λ
//	 * @return ��λ
//	 */
//	public String getDepartment()
//	{
//		return department;
//	}
//
//	/**
//	 * ��������ֵ����λ
//	 * @param department ��λ
//	 */
//	public void setDepartment(String department)
//	{
//		this.department = department;
//	}
//
//	/**
//	 * �绰
//	 */
//	private String tel=null;
//
//	/**
//	 * ��ȡ����ֵ���绰
//	 * @return �绰
//	 */
//	public String getTel()
//	{
//		return tel;
//	}
//
//	/**
//	 * ��������ֵ���绰
//	 * @param tel �绰
//	 */
//	public void setTel(String tel)
//	{
//		this.tel = tel;
//	}
//
//	/**
//	 * ��������
//	 */
//	private String email=null;
//
//	/**
//	 * ��ȡ����ֵ����������
//	 * @return ��������
//	 */
//	public String getEmail()
//	{
//		return email;
//	}
//
//	/**
//	 * ��������ֵ����������
//	 * @param email ��������
//	 */
//	public void setEmail(String email)
//	{
//		this.email = email;
//	}
//
//	/**
//	 * ������
//	 */
//	private int areaID=0;
//
//	/**
//	 * ��ȡ����ֵ��������
//	 * @return ������
//	 */
//	public int getAreaID()
//	{
//		return areaID;
//	}
//
//	/**
//	 * ��������ֵ��������
//	 * @param areaID ������
//	 */
//	public void setAreaID(int areaID)
//	{
//		this.areaID = areaID;
//	}
//
//	/**
//	 * clone
//	 * @return ��¡��ǰ����ʵ����õ����¶���
//	 */
//	public ArchivesUsePersonInfo clone()
//	{
//		ArchivesUsePersonInfo item = new ArchivesUsePersonInfo(iD,name,iDCardNo,iDCardTypeID,department,tel,email,areaID);
//		item.setKeyInCol(keyInCol);
//		item.setTag(tag);
//
//		return item;
//	}
//
//
//    
//}

package com.orifound.aiim.entity;
    
/**
 * ������������Ϣ
 */
public class ArchivesUsePersonInfo
{
    /**
     * ���캯��
     */
    public ArchivesUsePersonInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD �����˱��
	* @param name ��ʵ����
	* @param iDCardNo ֤������
	* @param iDCardTypeID ֤������
	* @param department ��λ
	* @param tel �绰
	* @param email ��������
	* @param areaID ������
	*/
	public ArchivesUsePersonInfo(int iD,String name,String iDCardNo,int iDCardTypeID,String department,String tel,String email,int areaID)
	{
		// Table Name: ArchivesUsePersonInfo
		// Columns List,Can Used in SELECT SQL: ID,Name,IDCardNo,IDCardTypeID,Department,Tel,Email,AreaID
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,IDCardNo=:IDCardNo,IDCardTypeID=:IDCardTypeID,Department=:Department,Tel=:Tel,Email=:Email,AreaID=:AreaID

		this.iD = iD;
		this.name = name;
		this.iDCardNo = iDCardNo;
		this.iDCardTypeID = iDCardTypeID;
		this.department = department;
		this.tel = tel;
		this.email = email;
		this.areaID = areaID;
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
	 * �����˱��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param iD �����˱��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��ʵ����
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ����ʵ����
	 * @return ��ʵ����
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ����ʵ����
	 * @param name ��ʵ����
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ֤������
	 */
	private String iDCardNo=null;

	/**
	 * ��ȡ����ֵ��֤������
	 * @return ֤������
	 */
	public String getIDCardNo()
	{
		return iDCardNo;
	}

	/**
	 * ��������ֵ��֤������
	 * @param iDCardNo ֤������
	 */
	public void setIDCardNo(String iDCardNo)
	{
		this.iDCardNo = iDCardNo;
	}

	/**
	 * ֤������
	 */
	private int iDCardTypeID=0;

	/**
	 * ��ȡ����ֵ��֤������
	 * @return ֤������
	 */
	public int getIDCardTypeID()
	{
		return iDCardTypeID;
	}

	/**
	 * ��������ֵ��֤������
	 * @param iDCardTypeID ֤������
	 */
	public void setIDCardTypeID(int iDCardTypeID)
	{
		this.iDCardTypeID = iDCardTypeID;
	}

	/**
	 * ��λ
	 */
	private String department=null;

	/**
	 * ��ȡ����ֵ����λ
	 * @return ��λ
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * ��������ֵ����λ
	 * @param department ��λ
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * �绰
	 */
	private String tel=null;

	/**
	 * ��ȡ����ֵ���绰
	 * @return �绰
	 */
	public String getTel()
	{
		return tel;
	}

	/**
	 * ��������ֵ���绰
	 * @param tel �绰
	 */
	public void setTel(String tel)
	{
		this.tel = tel;
	}

	/**
	 * ��������
	 */
	private String email=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * ��������ֵ����������
	 * @param email ��������
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * ������
	 */
	private int areaID=0;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public int getAreaID()
	{
		return areaID;
	}

	/**
	 * ��������ֵ��������
	 * @param areaID ������
	 */
	public void setAreaID(int areaID)
	{
		this.areaID = areaID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesUsePersonInfo clone()
	{
		ArchivesUsePersonInfo item = new ArchivesUsePersonInfo(iD,name,iDCardNo,iDCardTypeID,department,tel,email,areaID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesUsePersonInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesUsePersonInfo archivesUsePersonInfo)
	{
		this.iD = archivesUsePersonInfo.getID();
		this.name = archivesUsePersonInfo.getName();
		this.iDCardNo = archivesUsePersonInfo.getIDCardNo();
		this.iDCardTypeID = archivesUsePersonInfo.getIDCardTypeID();
		this.department = archivesUsePersonInfo.getDepartment();
		this.tel = archivesUsePersonInfo.getTel();
		this.email = archivesUsePersonInfo.getEmail();
		this.areaID = archivesUsePersonInfo.getAreaID();
		this.keyInCol = archivesUsePersonInfo.getKeyInCol();
		this.tag = archivesUsePersonInfo.getTag();
	}


    
}




