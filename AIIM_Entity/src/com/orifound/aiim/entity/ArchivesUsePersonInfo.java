//package com.orifound.aiim.entity;
//
//    
///**
// * 档案利用人信息表的实体类
// */
//public class ArchivesUsePersonInfo
//{
//    /**
//     * 构造函数
//     */
//    public ArchivesUsePersonInfo()
//    {
//        
//    }
//    
//	/**
//	* 带字段参数的构造函数
//	* @param name 真实姓名
//	* @param iDCardNo 证件号码
//	* @param iDCardTypeID 证件类型
//	* @param department 单位
//	* @param tel 电话
//	* @param email 电子邮箱
//	* @param areaID 地域编号
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
//	* 带字段参数的构造函数
//	* @param iD 利用人编号
//	* @param name 真实姓名
//	* @param iDCardNo 证件号码
//	* @param iDCardTypeID 证件类型
//	* @param department 单位
//	* @param tel 电话
//	* @param email 电子邮箱
//	* @param areaID 地域编号
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
//	 * 成员对象在集合中的关键字
//	 */
//	private Object keyInCol=null;
//
//	/**
//	 * 获取属性值：成员对象在集合中的关键字
//	 * @return 成员对象在集合中的关键字
//	 */
//	public Object getKeyInCol()
//	{
//		return keyInCol;
//	}
//
//	/**
//	 * 设置属性值：成员对象在集合中的关键字
//	 * @param keyInCol 成员对象在集合中的关键字
//	 */
//	public void setKeyInCol(Object keyInCol)
//	{
//		this.keyInCol = keyInCol;
//	}
//
//	/**
//	 * 该数据项的附加对象，可以用来保存一些附加信息
//	 */
//	private Object tag=null;
//
//	/**
//	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
//	 * @return 该数据项的附加对象，可以用来保存一些附加信息
//	 */
//	public Object getTag()
//	{
//		return tag;
//	}
//
//	/**
//	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
//	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
//	 */
//	public void setTag(Object tag)
//	{
//		this.tag = tag;
//	}
//
//	/**
//	 * 利用人编号
//	 */
//	private int iD=0;
//
//	/**
//	 * 获取属性值：利用人编号
//	 * @return 利用人编号
//	 */
//	public int getID()
//	{
//		return iD;
//	}
//
//	/**
//	 * 设置属性值：利用人编号
//	 * @param iD 利用人编号
//	 */
//	public void setID(int iD)
//	{
//		this.iD = iD;
//	}
//
//	/**
//	 * 真实姓名
//	 */
//	private String name=null;
//
//	/**
//	 * 获取属性值：真实姓名
//	 * @return 真实姓名
//	 */
//	public String getName()
//	{
//		return name;
//	}
//
//	/**
//	 * 设置属性值：真实姓名
//	 * @param name 真实姓名
//	 */
//	public void setName(String name)
//	{
//		this.name = name;
//	}
//
//	/**
//	 * 证件号码
//	 */
//	private String iDCardNo=null;
//
//	/**
//	 * 获取属性值：证件号码
//	 * @return 证件号码
//	 */
//	public String getIDCardNo()
//	{
//		return iDCardNo;
//	}
//
//	/**
//	 * 设置属性值：证件号码
//	 * @param iDCardNo 证件号码
//	 */
//	public void setIDCardNo(String iDCardNo)
//	{
//		this.iDCardNo = iDCardNo;
//	}
//
//	/**
//	 * 证件类型
//	 */
//	private int iDCardTypeID=0;
//
//	/**
//	 * 获取属性值：证件类型
//	 * @return 证件类型
//	 */
//	public int getIDCardTypeID()
//	{
//		return iDCardTypeID;
//	}
//
//	/**
//	 * 设置属性值：证件类型
//	 * @param iDCardTypeID 证件类型
//	 */
//	public void setIDCardTypeID(int iDCardTypeID)
//	{
//		this.iDCardTypeID = iDCardTypeID;
//	}
//
//	/**
//	 * 单位
//	 */
//	private String department=null;
//
//	/**
//	 * 获取属性值：单位
//	 * @return 单位
//	 */
//	public String getDepartment()
//	{
//		return department;
//	}
//
//	/**
//	 * 设置属性值：单位
//	 * @param department 单位
//	 */
//	public void setDepartment(String department)
//	{
//		this.department = department;
//	}
//
//	/**
//	 * 电话
//	 */
//	private String tel=null;
//
//	/**
//	 * 获取属性值：电话
//	 * @return 电话
//	 */
//	public String getTel()
//	{
//		return tel;
//	}
//
//	/**
//	 * 设置属性值：电话
//	 * @param tel 电话
//	 */
//	public void setTel(String tel)
//	{
//		this.tel = tel;
//	}
//
//	/**
//	 * 电子邮箱
//	 */
//	private String email=null;
//
//	/**
//	 * 获取属性值：电子邮箱
//	 * @return 电子邮箱
//	 */
//	public String getEmail()
//	{
//		return email;
//	}
//
//	/**
//	 * 设置属性值：电子邮箱
//	 * @param email 电子邮箱
//	 */
//	public void setEmail(String email)
//	{
//		this.email = email;
//	}
//
//	/**
//	 * 地域编号
//	 */
//	private int areaID=0;
//
//	/**
//	 * 获取属性值：地域编号
//	 * @return 地域编号
//	 */
//	public int getAreaID()
//	{
//		return areaID;
//	}
//
//	/**
//	 * 设置属性值：地域编号
//	 * @param areaID 地域编号
//	 */
//	public void setAreaID(int areaID)
//	{
//		this.areaID = areaID;
//	}
//
//	/**
//	 * clone
//	 * @return 克隆当前对象实例后得到的新对象
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
 * 档案利用人信息
 */
public class ArchivesUsePersonInfo
{
    /**
     * 构造函数
     */
    public ArchivesUsePersonInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 利用人编号
	* @param name 真实姓名
	* @param iDCardNo 证件号码
	* @param iDCardTypeID 证件类型
	* @param department 单位
	* @param tel 电话
	* @param email 电子邮箱
	* @param areaID 地域编号
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
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 利用人编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：利用人编号
	 * @return 利用人编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：利用人编号
	 * @param iD 利用人编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 真实姓名
	 */
	private String name=null;

	/**
	 * 获取属性值：真实姓名
	 * @return 真实姓名
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：真实姓名
	 * @param name 真实姓名
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 证件号码
	 */
	private String iDCardNo=null;

	/**
	 * 获取属性值：证件号码
	 * @return 证件号码
	 */
	public String getIDCardNo()
	{
		return iDCardNo;
	}

	/**
	 * 设置属性值：证件号码
	 * @param iDCardNo 证件号码
	 */
	public void setIDCardNo(String iDCardNo)
	{
		this.iDCardNo = iDCardNo;
	}

	/**
	 * 证件类型
	 */
	private int iDCardTypeID=0;

	/**
	 * 获取属性值：证件类型
	 * @return 证件类型
	 */
	public int getIDCardTypeID()
	{
		return iDCardTypeID;
	}

	/**
	 * 设置属性值：证件类型
	 * @param iDCardTypeID 证件类型
	 */
	public void setIDCardTypeID(int iDCardTypeID)
	{
		this.iDCardTypeID = iDCardTypeID;
	}

	/**
	 * 单位
	 */
	private String department=null;

	/**
	 * 获取属性值：单位
	 * @return 单位
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * 设置属性值：单位
	 * @param department 单位
	 */
	public void setDepartment(String department)
	{
		this.department = department;
	}

	/**
	 * 电话
	 */
	private String tel=null;

	/**
	 * 获取属性值：电话
	 * @return 电话
	 */
	public String getTel()
	{
		return tel;
	}

	/**
	 * 设置属性值：电话
	 * @param tel 电话
	 */
	public void setTel(String tel)
	{
		this.tel = tel;
	}

	/**
	 * 电子邮箱
	 */
	private String email=null;

	/**
	 * 获取属性值：电子邮箱
	 * @return 电子邮箱
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * 设置属性值：电子邮箱
	 * @param email 电子邮箱
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * 地域编号
	 */
	private int areaID=0;

	/**
	 * 获取属性值：地域编号
	 * @return 地域编号
	 */
	public int getAreaID()
	{
		return areaID;
	}

	/**
	 * 设置属性值：地域编号
	 * @param areaID 地域编号
	 */
	public void setAreaID(int areaID)
	{
		this.areaID = areaID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesUsePersonInfo clone()
	{
		ArchivesUsePersonInfo item = new ArchivesUsePersonInfo(iD,name,iDCardNo,iDCardTypeID,department,tel,email,areaID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesUsePersonInfo 指定的对象源
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




