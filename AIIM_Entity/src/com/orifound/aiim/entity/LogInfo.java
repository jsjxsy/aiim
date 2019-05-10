package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 日志信息表实体类
 */
public class LogInfo
{
    /**
     * 构造函数
     */
    public LogInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param userID 用户编号
	* @param userName 用户名
	* @param realName 真实姓名
	* @param featureName 功能名称
	* @param operateContent 操作内容
	* @param operateTime 操作时间
	* @param iP 操作终端IP
	* @param uRI 请求的地址
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
	 * 编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
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
	 * 功能名称
	 */
	private String featureName=null;

	/**
	 * 获取属性值：功能名称
	 * @return 功能名称
	 */
	public String getFeatureName()
	{
		return featureName;
	}

	/**
	 * 设置属性值：功能名称
	 * @param featureName 功能名称
	 */
	public void setFeatureName(String featureName)
	{
		this.featureName = featureName;
	}

	/**
	 * 操作内容
	 */
	private String operateContent=null;

	/**
	 * 获取属性值：操作内容
	 * @return 操作内容
	 */
	public String getOperateContent()
	{
		return operateContent;
	}

	/**
	 * 设置属性值：操作内容
	 * @param operateContent 操作内容
	 */
	public void setOperateContent(String operateContent)
	{
		this.operateContent = operateContent;
	}

	/**
	 * 操作时间
	 */
	private Date operateTime;

	/**
	 * 获取属性值：操作时间
	 * @return 操作时间
	 */
	public Date getOperateTime()
	{
		return operateTime;
	}

	/**
	 * 设置属性值：操作时间
	 * @param operateTime 操作时间
	 */
	public void setOperateTime(Date operateTime)
	{
		this.operateTime = operateTime;
	}

	/**
	 * 操作终端IP
	 */
	private String iP=null;

	/**
	 * 获取属性值：操作终端IP
	 * @return 操作终端IP
	 */
	public String getIP()
	{
		return iP;
	}

	/**
	 * 设置属性值：操作终端IP
	 * @param iP 操作终端IP
	 */
	public void setIP(String iP)
	{
		this.iP = iP;
	}

	/**
	 * 请求的地址
	 */
	private String uRI=null;

	/**
	 * 获取属性值：请求的地址
	 * @return 请求的地址
	 */
	public String getURI()
	{
		return uRI;
	}

	/**
	 * 设置属性值：请求的地址
	 * @param uRI 请求的地址
	 */
	public void setURI(String uRI)
	{
		this.uRI = uRI;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public LogInfo clone()
	{
		LogInfo item = new LogInfo(iD,userID,userName,realName,featureName,operateContent,operateTime,iP,uRI);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param logInfo 指定的对象源
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
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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



