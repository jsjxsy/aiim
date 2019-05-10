package com.orifound.aiim.entity;

import java.util.*;

import com.orifound.commons.acservice.IAccessControlService;
    
/**
 * 用户信息表的实体类
 */
public class UserInfo
{
    /**
     * 构造函数
     */
    public UserInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param userID 用户编号
	* @param userName 用户名
	* @param userPWD 用户密码
	* @param realName 真实姓名
	* @param departmentID 部门编号
	* @param dutyID 职务编号
	* @param iDCardTypeID 证件类型
	* @param iDCardNo 证件号码
	* @param email 电子邮箱
	* @param tel 电话
	* @param address 地址
	* @param anonymouseFlag 匿名账户标志
	* @param pKI_CA PKI-CA密钥串
	* @param frozenFlag 冻结标志
	*/
	public UserInfo(int userID,String userName,String userPWD,String realName,int departmentID,int dutyID,int iDCardTypeID,String iDCardNo,String email,String tel,String address,boolean anonymouseFlag,String pKI_CA,boolean frozenFlag)
	{
		// Table Name: UserInfo
		// Columns List,Can Used in SELECT SQL: UserID,UserName,UserPWD,RealName,DepartmentID,DutyID,IDCardTypeID,IDCardNo,Email,Tel,Address,AnonymouseFlag,PKI_CA,FrozenFlag
		// Columns List,Can Used in INSERT SQL: :UserID,:UserName,:UserPWD,:RealName,:DepartmentID,:DutyID,:IDCardTypeID,:IDCardNo,:Email,:Tel,:Address,:AnonymouseFlag,:PKI_CA,:FrozenFlag
		// Columns List,Can Used in UPDATE SQL: UserID=:UserID,UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag

		this(userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		this.userID = userID;
		
	}
	
	public UserInfo(int userID,String userName,String userPWD,String realName,int departmentID,int dutyID,int iDCardTypeID,String iDCardNo,String email,String tel,String address,boolean anonymouseFlag,String pKI_CA,boolean frozenFlag,String departmentName,String dutyName)
	{
		// Table Name: UserInfo
		// Columns List,Can Used in SELECT SQL: UserID,UserName,UserPWD,RealName,DepartmentID,DutyID,IDCardTypeID,IDCardNo,Email,Tel,Address,AnonymouseFlag,PKI_CA,FrozenFlag
		// Columns List,Can Used in INSERT SQL: :UserID,:UserName,:UserPWD,:RealName,:DepartmentID,:DutyID,:IDCardTypeID,:IDCardNo,:Email,:Tel,:Address,:AnonymouseFlag,:PKI_CA,:FrozenFlag
		// Columns List,Can Used in UPDATE SQL: UserID=:UserID,UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag

		this(userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		this.userID = userID;
		this.departmentName =departmentName;
		this.dutyName = dutyName;
		
		
	}
	/**
	* 带字段参数的构造函数
	* @param userName 用户名
	* @param userPWD 用户密码
	* @param realName 真实姓名
	* @param departmentID 部门编号
	* @param dutyID 职务编号
	* @param iDCardTypeID 证件类型
	* @param iDCardNo 证件号码
	* @param email 电子邮箱
	* @param tel 电话
	* @param address 地址
	* @param anonymouseFlag 匿名账户标志
	* @param pKI_CA PKI-CA密钥串
	* @param frozenFlag 冻结标志
	*/
	public UserInfo(String userName,String userPWD,String realName,int departmentID,int dutyID,int iDCardTypeID,String iDCardNo,String email,String tel,String address,boolean anonymouseFlag,String pKI_CA,boolean frozenFlag)
	{
		this.userName = userName;
		this.userPWD = userPWD;
		this.realName = realName;
		this.departmentID = departmentID;
		this.dutyID = dutyID;
		this.iDCardTypeID = iDCardTypeID;
		this.iDCardNo = iDCardNo;
		this.email = email;
		this.tel = tel;
		this.address = address;
		this.anonymouseFlag = anonymouseFlag;
		this.pKI_CA = pKI_CA;
		this.frozenFlag = frozenFlag;
	}
	
	public UserInfo(int iD,int userID,String userName,String userPWD,String realName,int departmentID,int dutyID,int iDCardTypeID,String iDCardNo,String email,String tel,String address,boolean anonymouseFlag,String pKI_CA,boolean frozenFlag)
	{
		// Table Name: UserInfo
		// Columns List,Can Used in SELECT SQL: UserID,UserName,UserPWD,RealName,DepartmentID,DutyID,IDCardTypeID,IDCardNo,Email,Tel,Address,AnonymouseFlag,PKI_CA,FrozenFlag
		// Columns List,Can Used in INSERT SQL: :UserID,:UserName,:UserPWD,:RealName,:DepartmentID,:DutyID,:IDCardTypeID,:IDCardNo,:Email,:Tel,:Address,:AnonymouseFlag,:PKI_CA,:FrozenFlag
		// Columns List,Can Used in UPDATE SQL: UserID=:UserID,UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag

		this(userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		this.userID = userID;
		this.iD=iD;
	}
	
	public UserInfo(int userID,String userName,String userPWD,String realName,int departmentID,String departmentName,int dutyID,int iDCardTypeID,String iDCardNo,String email,String tel,String address,boolean anonymouseFlag,String pKI_CA,boolean frozenFlag) {
		this(userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		this.userID = userID;
		this.departmentName = departmentName;
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
	 * 用户编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：用户编号
	 * @return 用户编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：用户编号
	 * @param userID 用户编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 用户名
	 */
	private String userName=null;

	/**
	 * 获取属性值：用户名
	 * @return 用户名
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * 设置属性值：用户名
	 * @param userName 用户名
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * 用户密码
	 */
	private String userPWD=null;

	/**
	 * 获取属性值：用户密码
	 * @return 用户密码
	 */
	public String getUserPWD()
	{
		return userPWD;
	}

	/**
	 * 设置属性值：用户密码
	 * @param userPWD 用户密码
	 */
	public void setUserPWD(String userPWD)
	{
		this.userPWD = userPWD;
	}

	/**
	 * 真实姓名
	 */
	private String realName=null;

	/**
	 * 获取属性值：真实姓名
	 * @return 真实姓名
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * 设置属性值：真实姓名
	 * @param realName 真实姓名
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * 部门编号
	 */
	private int departmentID=0;

	/**
	 * 获取属性值：部门编号
	 * @return 部门编号
	 */
	public int getDepartmentID()
	{
		return departmentID;
	}

	/**
	 * 设置属性值：部门编号
	 * @param departmentID 部门编号
	 */
	public void setDepartmentID(int departmentID)
	{
		this.departmentID = departmentID;
	}

	/**
	 * 职务编号
	 */
	private int dutyID=0;

	/**
	 * 获取属性值：职务编号
	 * @return 职务编号
	 */
	public int getDutyID()
	{
		return dutyID;
	}

	/**
	 * 设置属性值：职务编号
	 * @param dutyID 职务编号
	 */
	public void setDutyID(int dutyID)
	{
		this.dutyID = dutyID;
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
	 * 地址
	 */
	private String address=null;

	/**
	 * 获取属性值：地址
	 * @return 地址
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * 设置属性值：地址
	 * @param address 地址
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * 匿名账户标志
	 */
	private boolean anonymouseFlag=false;

	/**
	 * 获取属性值：匿名账户标志
	 * @return 匿名账户标志
	 */
	public boolean getAnonymouseFlag()
	{
		return anonymouseFlag;
	}

	/**
	 * 设置属性值：匿名账户标志
	 * @param anonymouseFlag 匿名账户标志
	 */
	public void setAnonymouseFlag(boolean anonymouseFlag)
	{
		this.anonymouseFlag = anonymouseFlag;
	}

	/**
	 * PKI-CA密钥串
	 */
	private String pKI_CA=null;

	/**
	 * 获取属性值：PKI-CA密钥串
	 * @return PKI-CA密钥串
	 */
	public String getPKI_CA()
	{
		return pKI_CA;
	}

	/**
	 * 设置属性值：PKI-CA密钥串
	 * @param pKI_CA PKI-CA密钥串
	 */
	public void setPKI_CA(String pKI_CA)
	{
		this.pKI_CA = pKI_CA;
	}

	/**
	 * 冻结标志
	 */
	private boolean frozenFlag=false;

	/**
	 * 获取属性值：冻结标志
	 * @return 冻结标志
	 */
	public boolean getFrozenFlag()
	{
		return frozenFlag;
	}

	/**
	 * 设置属性值：冻结标志
	 * @param frozenFlag 冻结标志
	 */
	public void setFrozenFlag(boolean frozenFlag)
	{
		this.frozenFlag = frozenFlag;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public UserInfo clone()
	{
		UserInfo item = new UserInfo(userID,userName,userPWD,realName,departmentID,dutyID,iDCardTypeID,iDCardNo,email,tel,address,anonymouseFlag,pKI_CA,frozenFlag,departmentName,dutyName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param userInfo 指定的对象源
	*/
	public void cloneFrom(UserInfo userInfo)
	{
		this.userID = userInfo.getUserID();
		this.userName = userInfo.getUserName();
		this.userPWD = userInfo.getUserPWD();
		this.realName = userInfo.getRealName();
		this.departmentID = userInfo.getDepartmentID();
		this.departmentName = userInfo.getDepartmentName();
		this.dutyID = userInfo.getDutyID();
		this.iDCardTypeID = userInfo.getIDCardTypeID();
		this.iDCardNo = userInfo.getIDCardNo();
		this.email = userInfo.getEmail();
		this.tel = userInfo.getTel();
		this.address = userInfo.getAddress();
		this.anonymouseFlag = userInfo.getAnonymouseFlag();
		this.pKI_CA = userInfo.getPKI_CA();
		this.frozenFlag = userInfo.getFrozenFlag();
	}







	/**
	 * 获取当前用户所顶替的用户编号数组
	 * @return 当前用户所顶替的用户编号数组
	 */
	public int[] getChargeUserIDs() {
		int[] chargeUserIDs=null;
		
		if (chargeUserInfos!=null)
		{
			if (chargeUserInfos.size()>0)
			{
				chargeUserIDs=new int[chargeUserInfos.size()+1];
				//添加自己的用户编号至数组中
				chargeUserIDs[0]=userID;
				
				//添加代工用户信息
				for (int i = 0; i < chargeUserInfos.size()-1; i++)
				{
					chargeUserIDs[i+1]=chargeUserInfos.get(i).getChargedUserID();
				}
			}
		}
		
		//如果没有指定代工信息，添加自己的用户编号至数组中
		if (chargeUserIDs==null)
		{
			chargeUserIDs=new int[1];
			chargeUserIDs[0]=userID;
		}
		
		return chargeUserIDs;
	}
	
	/**
	 * 当前用户所顶替代工的用户集合
	 */
	private List<UserChargeUserInfo> chargeUserInfos = null;

	/**
	 * 设置属性值：当前用户所顶替代工的用户集合
	 * @param chargeUserInfos 当前用户所顶替代工的用户集合
	 */
	public void setChargeUserInfos(List<UserChargeUserInfo> chargeUserInfos)
	{
		this.chargeUserInfos = chargeUserInfos;
	}

	/**
	 * 获取属性值：当前用户所顶替代工的用户集合
	 * @return 当前用户所顶替代工的用户集合
	 */
	public List<UserChargeUserInfo> getChargeUserInfos()
	{
		return chargeUserInfos;
	}

	

	/**
	 * 当前用户所负责的部门集合（针对业务指导室专员），以部门编号作为集合关键字
	 */
	private LinkedHashMap<Integer,UserChargeDepartmentInfo> chargeDepartmentInfos = null;

	/**
	 * 设置属性值：当前用户所负责的部门集合（针对业务指导室专员），以部门编号作为集合关键字
	 * @param chargeDepartmentInfos 当前用户所负责的部门集合（针对业务指导室专员），以部门编号作为集合关键字
	 */
	public void setChargeDepartmentInfos(LinkedHashMap<Integer,UserChargeDepartmentInfo> chargeDepartmentInfos)
	{
		this.chargeDepartmentInfos = chargeDepartmentInfos;
	}

	/**
	 * 获取属性值：当前用户所负责的部门集合（针对业务指导室专员），以部门编号作为集合关键字
	 * @return 当前用户所负责的部门集合（针对业务指导室专员），以部门编号作为集合关键字
	 */
	public LinkedHashMap<Integer,UserChargeDepartmentInfo> getChargeDepartmentInfos()
	{
		return chargeDepartmentInfos;
	}
	
	/**
	 * 获取当前用户所负责的部门编号数组
	 * @return 获取当前用户所负责的部门编号数组
	 */
	public int[] getChargeDepartmentIDs()
	{
		//当前用户所负责的部门编号数组
		int[] chargeDepartmentIDs = null;
		
		if (chargeDepartmentInfos!=null)
		{
			if (chargeDepartmentInfos.size()>0)
			{
				chargeDepartmentIDs=new int[chargeDepartmentInfos.size()];
				
				//添加负责的部门信息
				int i=0;
				for (UserChargeDepartmentInfo item : chargeDepartmentInfos.values())
				{
					chargeDepartmentIDs[i]=item.getDepartmentID();
					i=i+1;
				}
			}
		}
		
		return chargeDepartmentIDs;
	}

	

	/**
	 * 当前用户能够访问的系统功能菜单，使用UclKey作为集合关键字
	 */
	private LinkedHashMap<String, SystemFeature> systemMenus = null;

	/**
	 * 设置属性值：当前用户能够访问的系统功能菜单，使用UclKey作为集合关键字
	 * @param systemMenus 当前用户能够访问的系统功能菜单，使用UclKey作为集合关键字
	 */
	public void setSystemMenus(LinkedHashMap<String, SystemFeature> systemMenus)
	{
		this.systemMenus = systemMenus;
	}

	/**
	 * 获取属性值：当前用户能够访问的系统功能菜单，使用UclKey作为集合关键字
	 * @return 当前用户能够访问的系统功能菜单，使用UclKey作为集合关键字
	 */
	public LinkedHashMap<String, SystemFeature> getSystemMenus()
	{
		return systemMenus;
	}

	/**
	 * UCL：当前用户能够访问的系统功能列表，使用UclKey作为集合关键字
	 */
	private Map<String, SystemFeature> uCL = null;

	/**
	 * 设置属性值：UCL：当前用户能够访问的系统功能列表，使用UclKey作为集合关键字
	 * @param uCL UCL：当前用户能够访问的系统功能列表，使用UclKey作为集合关键字
	 */
	public void setUCL(Map<String, SystemFeature> uCL)
	{
		this.uCL = uCL;
	}

	/**
	 * 获取属性值：UCL：当前用户能够访问的系统功能列表，使用UclKey作为集合关键字
	 * @return UCL：当前用户能够访问的系统功能列表，使用UclKey作为集合关键字
	 */
	public Map<String, SystemFeature> getUCL()
	{
		return uCL;
	}

	/**
	 * 当前用户能够访问的档案分类集合（一级类目），以分类编号为做集合关键字
	 */
	private LinkedHashMap<Integer, ArchivesType> archivesTypes = null;

	/**
	 * 设置属性值：当前用户能够访问的档案分类集合（一级类目），以分类编号为做集合关键字
	 * @param archivesTypes 当前用户能够访问的档案分类集合（一级类目），以分类编号为做集合关键字
	 */
	public void setArchivesTypes(LinkedHashMap<Integer, ArchivesType> archivesTypes)
	{
		this.archivesTypes = archivesTypes;
	}

	/**
	 * 获取属性值：当前用户能够访问的档案分类集合（一级类目），以分类编号为做集合关键字
	 * @return 当前用户能够访问的档案分类集合（一级类目），以分类编号为做集合关键字
	 */
	public LinkedHashMap<Integer, ArchivesType> getArchivesTypes()
	{
		return archivesTypes;
	}
	
//	/**
//	 * 当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 */
//	private LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = null;
//	
//	/**
//	 * 当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 * @param officialArchivesTypes 公文档案分类集合
//	 */
//	public void setOfficialArchivesTypes(
//			LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes) {
//		this.officialArchivesTypes = officialArchivesTypes;
//	}
//
//	/**
//	 * 获取属性值：当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 * @return 当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 */
//	public LinkedHashMap<Integer, OfficialArchivesType> getOfficialArchivesTypes()
//	{
//		return officialArchivesTypes;
//	}
//	
//	/**
//	 * 当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 */
//	private LinkedHashMap<Integer, OfficialDocType> officialDocTypes = null;
//	
//	/**
//	 * 当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 * @param officialArchivesTypes 公文档案分类集合
//	 */
//	public void setOfficialDocTypes(
//			LinkedHashMap<Integer, OfficialDocType> officialDocTypes) {
//		this.officialDocTypes = officialDocTypes;
//	}
//
//	/**
//	 * 获取属性值：当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 * @return 当前用户能够访问的公文档案分类集合（一级类目），以分类编号为做集合关键字
//	 */
//	public LinkedHashMap<Integer, OfficialDocType> getOfficialDocTypes()
//	{
//		return officialDocTypes;
//	}
	
	/**
	 * 当前用户能够访问的档案密级集合，以密级编号作为集合关键字
	 */
	private Map<Integer, ArchivesSecrecy> archivesSecrecys = null;

	/**
	 * 设置属性值：当前用户能够访问的档案密级集合，以密级编号作为集合关键字
	 * @param archivesSecrecys 当前用户能够访问的档案密级集合，以密级编号作为集合关键字
	 */
	public void setArchivesSecrecys(Map<Integer, ArchivesSecrecy> archivesSecrecys)
	{
		this.archivesSecrecys = archivesSecrecys;
	}

	/**
	 * 获取属性值：当前用户能够访问的档案密级集合，以密级编号作为集合关键字
	 * @return 当前用户能够访问的档案密级集合，以密级编号作为集合关键字
	 */
	public Map<Integer, ArchivesSecrecy> getArchivesSecrecys()
	{
		return archivesSecrecys;
	}
	
	/**
	 * 当前用户所属的角色编号数组
	 */
	private int[] rolesIDs = {};

	/**
	 * 获取当前用户所属角色ID数组
	 * @return
	 */
	public int[] getRolesIDs() {
		return rolesIDs;
	}

	/**
	 * 设置当前用户所属角色ID数组
	 * @param rolesIDs
	 */
	public void setRolesIDs(int[] rolesIDs) {
		this.rolesIDs = rolesIDs;
	}
	

	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	private String dutyName;

	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}
	
	
	private int iD = 0;//用用代理信息的ID
	
	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * 访问控制服务
	 */
	private IAccessControlService accessControlService = null;

	/**
	 * 获取访问控制服务对象
	 * @return
	 */
	public IAccessControlService getAccessControlService() {
		return accessControlService;
	}

	/**
	 * 设置访问控制服务
	 * @param accessControlService
	 */
	public void setAccessControlService(IAccessControlService accessControlService) {
		this.accessControlService = accessControlService;
	}
	
	

}
