package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ��־��Ϣ��ʵ����
 */
public class LogInfo
{
    /**
     * ���캯��
     */
    public LogInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param userID �û����
	* @param userName �û���
	* @param realName ��ʵ����
	* @param featureName ��������
	* @param operateContent ��������
	* @param operateTime ����ʱ��
	* @param iP �����ն�IP
	* @param uRI ����ĵ�ַ
	*/
	public LogInfo(int iD,int userID,String userName,String realName,String featureName,String operateContent,Date operateTime,String iP,String uRI)
	{
		// Table Name: LogInfo
		// Columns List,Can Used in SELECT SQL: ID,UserID,UserName,RealName,FeatureName,OperateContent,OperateTime,IP,URI
		// Columns List,Can Used in INSERT SQL: :ID,:UserID,:UserName,:RealName,:FeatureName,:OperateContent,:OperateTime,:IP,:URI
		// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,UserName=:UserName,RealName=:RealName,FeatureName=:FeatureName,OperateContent=:OperateContent,OperateTime=:OperateTime,IP=:IP,URI=:URI

		this.iD = iD;
		this.userID = userID;
		this.userName = userName;
		this.realName = realName;
		this.featureName = featureName;
		this.operateContent = operateContent;
		this.operateTime = operateTime;
		this.iP = iP;
		this.uRI = uRI;
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
	 * ��������
	 */
	private String featureName=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getFeatureName()
	{
		return featureName;
	}

	/**
	 * ��������ֵ����������
	 * @param featureName ��������
	 */
	public void setFeatureName(String featureName)
	{
		this.featureName = featureName;
	}

	/**
	 * ��������
	 */
	private String operateContent=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getOperateContent()
	{
		return operateContent;
	}

	/**
	 * ��������ֵ����������
	 * @param operateContent ��������
	 */
	public void setOperateContent(String operateContent)
	{
		this.operateContent = operateContent;
	}

	/**
	 * ����ʱ��
	 */
	private Date operateTime;

	/**
	 * ��ȡ����ֵ������ʱ��
	 * @return ����ʱ��
	 */
	public Date getOperateTime()
	{
		return operateTime;
	}

	/**
	 * ��������ֵ������ʱ��
	 * @param operateTime ����ʱ��
	 */
	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}

	/**
	 * �����ն�IP
	 */
	private String iP=null;

	/**
	 * ��ȡ����ֵ�������ն�IP
	 * @return �����ն�IP
	 */
	public String getIP()
	{
		return iP;
	}

	/**
	 * ��������ֵ�������ն�IP
	 * @param iP �����ն�IP
	 */
	public void setIP(String iP)
	{
		this.iP = iP;
	}

	/**
	 * ����ĵ�ַ
	 */
	private String uRI=null;

	/**
	 * ��ȡ����ֵ������ĵ�ַ
	 * @return ����ĵ�ַ
	 */
	public String getURI()
	{
		return uRI;
	}

	/**
	 * ��������ֵ������ĵ�ַ
	 * @param uRI ����ĵ�ַ
	 */
	public void setURI(String uRI)
	{
		this.uRI = uRI;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public LogInfo clone()
	{
		LogInfo item = new LogInfo(iD,userID,userName,realName,featureName,operateContent,operateTime,iP,uRI);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param logInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(LogInfo logInfo)
	{
		this.iD = logInfo.getID();
		this.userID = logInfo.getUserID();
		this.userName = logInfo.getUserName();
		this.realName = logInfo.getRealName();
		this.featureName = logInfo.getFeatureName();
		this.operateContent = logInfo.getOperateContent();
		this.operateTime = logInfo.getOperateTime();
		this.iP = logInfo.getIP();
		this.uRI = logInfo.getURI();
		this.keyInCol = logInfo.getKeyInCol();
		this.tag = logInfo.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class LogInfoMapper implements RowMapper<LogInfo>
//	{
//		
//		@Override
//		public LogInfo mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int userID = rs.getInt("UserID");
//			String userName = rs.getString("UserName");
//			String realName = rs.getString("RealName");
//			String featureName = rs.getString("FeatureName");
//			String operateContent = rs.getString("OperateContent");
//			Date operateTime = rs.getTimestamp("OperateTime");
//			String iP = rs.getString("IP");
//			String uRI = rs.getString("URI");
//			
//			return new LogInfo(iD,userID,userName,realName,featureName,operateContent,operateTime,iP,uRI);
//		}
//	}

    
}



