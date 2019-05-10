package com.orifound.aiim.entity;

import java.util.*;

import com.orifound.commons.acservice.IAccessControlService;
    
/**
 * �û���Ϣ���ʵ����
 */
public class UserInfo
{
    /**
     * ���캯��
     */
    public UserInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param userID �û����
	* @param userName �û���
	* @param userPWD �û�����
	* @param realName ��ʵ����
	* @param departmentID ���ű��
	* @param dutyID ְ����
	* @param iDCardTypeID ֤������
	* @param iDCardNo ֤������
	* @param email ��������
	* @param tel �绰
	* @param address ��ַ
	* @param anonymouseFlag �����˻���־
	* @param pKI_CA PKI-CA��Կ��
	* @param frozenFlag �����־
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
	* ���ֶβ����Ĺ��캯��
	* @param userName �û���
	* @param userPWD �û�����
	* @param realName ��ʵ����
	* @param departmentID ���ű��
	* @param dutyID ְ����
	* @param iDCardTypeID ֤������
	* @param iDCardNo ֤������
	* @param email ��������
	* @param tel �绰
	* @param address ��ַ
	* @param anonymouseFlag �����˻���־
	* @param pKI_CA PKI-CA��Կ��
	* @param frozenFlag �����־
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
	 * �û���
	 */
	private String userName=null;

	/**
	 * ��ȡ����ֵ���û���
	 * @return �û���
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * ��������ֵ���û���
	 * @param userName �û���
	 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	/**
	 * �û�����
	 */
	private String userPWD=null;

	/**
	 * ��ȡ����ֵ���û�����
	 * @return �û�����
	 */
	public String getUserPWD()
	{
		return userPWD;
	}

	/**
	 * ��������ֵ���û�����
	 * @param userPWD �û�����
	 */
	public void setUserPWD(String userPWD)
	{
		this.userPWD = userPWD;
	}

	/**
	 * ��ʵ����
	 */
	private String realName=null;

	/**
	 * ��ȡ����ֵ����ʵ����
	 * @return ��ʵ����
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * ��������ֵ����ʵ����
	 * @param realName ��ʵ����
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
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
	 * ְ����
	 */
	private int dutyID=0;

	/**
	 * ��ȡ����ֵ��ְ����
	 * @return ְ����
	 */
	public int getDutyID()
	{
		return dutyID;
	}

	/**
	 * ��������ֵ��ְ����
	 * @param dutyID ְ����
	 */
	public void setDutyID(int dutyID)
	{
		this.dutyID = dutyID;
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
	 * ��ַ
	 */
	private String address=null;

	/**
	 * ��ȡ����ֵ����ַ
	 * @return ��ַ
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * ��������ֵ����ַ
	 * @param address ��ַ
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * �����˻���־
	 */
	private boolean anonymouseFlag=false;

	/**
	 * ��ȡ����ֵ�������˻���־
	 * @return �����˻���־
	 */
	public boolean getAnonymouseFlag()
	{
		return anonymouseFlag;
	}

	/**
	 * ��������ֵ�������˻���־
	 * @param anonymouseFlag �����˻���־
	 */
	public void setAnonymouseFlag(boolean anonymouseFlag)
	{
		this.anonymouseFlag = anonymouseFlag;
	}

	/**
	 * PKI-CA��Կ��
	 */
	private String pKI_CA=null;

	/**
	 * ��ȡ����ֵ��PKI-CA��Կ��
	 * @return PKI-CA��Կ��
	 */
	public String getPKI_CA()
	{
		return pKI_CA;
	}

	/**
	 * ��������ֵ��PKI-CA��Կ��
	 * @param pKI_CA PKI-CA��Կ��
	 */
	public void setPKI_CA(String pKI_CA)
	{
		this.pKI_CA = pKI_CA;
	}

	/**
	 * �����־
	 */
	private boolean frozenFlag=false;

	/**
	 * ��ȡ����ֵ�������־
	 * @return �����־
	 */
	public boolean getFrozenFlag()
	{
		return frozenFlag;
	}

	/**
	 * ��������ֵ�������־
	 * @param frozenFlag �����־
	 */
	public void setFrozenFlag(boolean frozenFlag)
	{
		this.frozenFlag = frozenFlag;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public UserInfo clone()
	{
		UserInfo item = new UserInfo(userID,userName,userPWD,realName,departmentID,dutyID,iDCardTypeID,iDCardNo,email,tel,address,anonymouseFlag,pKI_CA,frozenFlag,departmentName,dutyName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param userInfo ָ���Ķ���Դ
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
	 * ��ȡ��ǰ�û���������û��������
	 * @return ��ǰ�û���������û��������
	 */
	public int[] getChargeUserIDs() {
		int[] chargeUserIDs=null;
		
		if (chargeUserInfos!=null)
		{
			if (chargeUserInfos.size()>0)
			{
				chargeUserIDs=new int[chargeUserInfos.size()+1];
				//����Լ����û������������
				chargeUserIDs[0]=userID;
				
				//��Ӵ����û���Ϣ
				for (int i = 0; i < chargeUserInfos.size()-1; i++)
				{
					chargeUserIDs[i+1]=chargeUserInfos.get(i).getChargedUserID();
				}
			}
		}
		
		//���û��ָ��������Ϣ������Լ����û������������
		if (chargeUserIDs==null)
		{
			chargeUserIDs=new int[1];
			chargeUserIDs[0]=userID;
		}
		
		return chargeUserIDs;
	}
	
	/**
	 * ��ǰ�û�������������û�����
	 */
	private List<UserChargeUserInfo> chargeUserInfos = null;

	/**
	 * ��������ֵ����ǰ�û�������������û�����
	 * @param chargeUserInfos ��ǰ�û�������������û�����
	 */
	public void setChargeUserInfos(List<UserChargeUserInfo> chargeUserInfos)
	{
		this.chargeUserInfos = chargeUserInfos;
	}

	/**
	 * ��ȡ����ֵ����ǰ�û�������������û�����
	 * @return ��ǰ�û�������������û�����
	 */
	public List<UserChargeUserInfo> getChargeUserInfos()
	{
		return chargeUserInfos;
	}

	

	/**
	 * ��ǰ�û�������Ĳ��ż��ϣ����ҵ��ָ����רԱ�����Բ��ű����Ϊ���Ϲؼ���
	 */
	private LinkedHashMap<Integer,UserChargeDepartmentInfo> chargeDepartmentInfos = null;

	/**
	 * ��������ֵ����ǰ�û�������Ĳ��ż��ϣ����ҵ��ָ����רԱ�����Բ��ű����Ϊ���Ϲؼ���
	 * @param chargeDepartmentInfos ��ǰ�û�������Ĳ��ż��ϣ����ҵ��ָ����רԱ�����Բ��ű����Ϊ���Ϲؼ���
	 */
	public void setChargeDepartmentInfos(LinkedHashMap<Integer,UserChargeDepartmentInfo> chargeDepartmentInfos)
	{
		this.chargeDepartmentInfos = chargeDepartmentInfos;
	}

	/**
	 * ��ȡ����ֵ����ǰ�û�������Ĳ��ż��ϣ����ҵ��ָ����רԱ�����Բ��ű����Ϊ���Ϲؼ���
	 * @return ��ǰ�û�������Ĳ��ż��ϣ����ҵ��ָ����רԱ�����Բ��ű����Ϊ���Ϲؼ���
	 */
	public LinkedHashMap<Integer,UserChargeDepartmentInfo> getChargeDepartmentInfos()
	{
		return chargeDepartmentInfos;
	}
	
	/**
	 * ��ȡ��ǰ�û�������Ĳ��ű������
	 * @return ��ȡ��ǰ�û�������Ĳ��ű������
	 */
	public int[] getChargeDepartmentIDs()
	{
		//��ǰ�û�������Ĳ��ű������
		int[] chargeDepartmentIDs = null;
		
		if (chargeDepartmentInfos!=null)
		{
			if (chargeDepartmentInfos.size()>0)
			{
				chargeDepartmentIDs=new int[chargeDepartmentInfos.size()];
				
				//��Ӹ���Ĳ�����Ϣ
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
	 * ��ǰ�û��ܹ����ʵ�ϵͳ���ܲ˵���ʹ��UclKey��Ϊ���Ϲؼ���
	 */
	private LinkedHashMap<String, SystemFeature> systemMenus = null;

	/**
	 * ��������ֵ����ǰ�û��ܹ����ʵ�ϵͳ���ܲ˵���ʹ��UclKey��Ϊ���Ϲؼ���
	 * @param systemMenus ��ǰ�û��ܹ����ʵ�ϵͳ���ܲ˵���ʹ��UclKey��Ϊ���Ϲؼ���
	 */
	public void setSystemMenus(LinkedHashMap<String, SystemFeature> systemMenus)
	{
		this.systemMenus = systemMenus;
	}

	/**
	 * ��ȡ����ֵ����ǰ�û��ܹ����ʵ�ϵͳ���ܲ˵���ʹ��UclKey��Ϊ���Ϲؼ���
	 * @return ��ǰ�û��ܹ����ʵ�ϵͳ���ܲ˵���ʹ��UclKey��Ϊ���Ϲؼ���
	 */
	public LinkedHashMap<String, SystemFeature> getSystemMenus()
	{
		return systemMenus;
	}

	/**
	 * UCL����ǰ�û��ܹ����ʵ�ϵͳ�����б�ʹ��UclKey��Ϊ���Ϲؼ���
	 */
	private Map<String, SystemFeature> uCL = null;

	/**
	 * ��������ֵ��UCL����ǰ�û��ܹ����ʵ�ϵͳ�����б�ʹ��UclKey��Ϊ���Ϲؼ���
	 * @param uCL UCL����ǰ�û��ܹ����ʵ�ϵͳ�����б�ʹ��UclKey��Ϊ���Ϲؼ���
	 */
	public void setUCL(Map<String, SystemFeature> uCL)
	{
		this.uCL = uCL;
	}

	/**
	 * ��ȡ����ֵ��UCL����ǰ�û��ܹ����ʵ�ϵͳ�����б�ʹ��UclKey��Ϊ���Ϲؼ���
	 * @return UCL����ǰ�û��ܹ����ʵ�ϵͳ�����б�ʹ��UclKey��Ϊ���Ϲؼ���
	 */
	public Map<String, SystemFeature> getUCL()
	{
		return uCL;
	}

	/**
	 * ��ǰ�û��ܹ����ʵĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
	 */
	private LinkedHashMap<Integer, ArchivesType> archivesTypes = null;

	/**
	 * ��������ֵ����ǰ�û��ܹ����ʵĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
	 * @param archivesTypes ��ǰ�û��ܹ����ʵĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
	 */
	public void setArchivesTypes(LinkedHashMap<Integer, ArchivesType> archivesTypes)
	{
		this.archivesTypes = archivesTypes;
	}

	/**
	 * ��ȡ����ֵ����ǰ�û��ܹ����ʵĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
	 * @return ��ǰ�û��ܹ����ʵĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
	 */
	public LinkedHashMap<Integer, ArchivesType> getArchivesTypes()
	{
		return archivesTypes;
	}
	
//	/**
//	 * ��ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 */
//	private LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = null;
//	
//	/**
//	 * ��ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 * @param officialArchivesTypes ���ĵ������༯��
//	 */
//	public void setOfficialArchivesTypes(
//			LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes) {
//		this.officialArchivesTypes = officialArchivesTypes;
//	}
//
//	/**
//	 * ��ȡ����ֵ����ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 * @return ��ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 */
//	public LinkedHashMap<Integer, OfficialArchivesType> getOfficialArchivesTypes()
//	{
//		return officialArchivesTypes;
//	}
//	
//	/**
//	 * ��ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 */
//	private LinkedHashMap<Integer, OfficialDocType> officialDocTypes = null;
//	
//	/**
//	 * ��ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 * @param officialArchivesTypes ���ĵ������༯��
//	 */
//	public void setOfficialDocTypes(
//			LinkedHashMap<Integer, OfficialDocType> officialDocTypes) {
//		this.officialDocTypes = officialDocTypes;
//	}
//
//	/**
//	 * ��ȡ����ֵ����ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 * @return ��ǰ�û��ܹ����ʵĹ��ĵ������༯�ϣ�һ����Ŀ�����Է�����Ϊ�����Ϲؼ���
//	 */
//	public LinkedHashMap<Integer, OfficialDocType> getOfficialDocTypes()
//	{
//		return officialDocTypes;
//	}
	
	/**
	 * ��ǰ�û��ܹ����ʵĵ����ܼ����ϣ����ܼ������Ϊ���Ϲؼ���
	 */
	private Map<Integer, ArchivesSecrecy> archivesSecrecys = null;

	/**
	 * ��������ֵ����ǰ�û��ܹ����ʵĵ����ܼ����ϣ����ܼ������Ϊ���Ϲؼ���
	 * @param archivesSecrecys ��ǰ�û��ܹ����ʵĵ����ܼ����ϣ����ܼ������Ϊ���Ϲؼ���
	 */
	public void setArchivesSecrecys(Map<Integer, ArchivesSecrecy> archivesSecrecys)
	{
		this.archivesSecrecys = archivesSecrecys;
	}

	/**
	 * ��ȡ����ֵ����ǰ�û��ܹ����ʵĵ����ܼ����ϣ����ܼ������Ϊ���Ϲؼ���
	 * @return ��ǰ�û��ܹ����ʵĵ����ܼ����ϣ����ܼ������Ϊ���Ϲؼ���
	 */
	public Map<Integer, ArchivesSecrecy> getArchivesSecrecys()
	{
		return archivesSecrecys;
	}
	
	/**
	 * ��ǰ�û������Ľ�ɫ�������
	 */
	private int[] rolesIDs = {};

	/**
	 * ��ȡ��ǰ�û�������ɫID����
	 * @return
	 */
	public int[] getRolesIDs() {
		return rolesIDs;
	}

	/**
	 * ���õ�ǰ�û�������ɫID����
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
	
	
	private int iD = 0;//���ô�����Ϣ��ID
	
	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * ���ʿ��Ʒ���
	 */
	private IAccessControlService accessControlService = null;

	/**
	 * ��ȡ���ʿ��Ʒ������
	 * @return
	 */
	public IAccessControlService getAccessControlService() {
		return accessControlService;
	}

	/**
	 * ���÷��ʿ��Ʒ���
	 * @param accessControlService
	 */
	public void setAccessControlService(IAccessControlService accessControlService) {
		this.accessControlService = accessControlService;
	}
	
	

}
