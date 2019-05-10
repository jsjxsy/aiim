package com.orifound.aiim.entity;

    
/**
 * 配置信息表的实体类
 */
public class Config
{
    /**
     * 构造函数
     */
    public Config()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param configType 配置类型
	* @param configValue 配置值
	* @param minValue 最小值
	* @param maxValue 最大值
	* @param ftpServer FTP服务器
	* @param ftpUser FTP登录用户
	* @param ftpPassword FTP登录密码
	* @param localPath 本地路径
	*/
	public Config(int iD,String configType,String configValue,String minValue,String maxValue,String ftpServer,String ftpUser,String ftpPassword,String localPath)
	{
		// Table Name: Config
		// Columns List,Can Used in SELECT SQL: ID,ConfigType,ConfigValue,MinValue,MaxValue,FtpServer,FtpUser,FtpPassword,LocalPath
		// Columns List,Can Used in INSERT SQL: :ID,:ConfigType,:ConfigValue,:MinValue,:MaxValue,:FtpServer,:FtpUser,:FtpPassword,:LocalPath
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ConfigType=:ConfigType,ConfigValue=:ConfigValue,MinValue=:MinValue,MaxValue=:MaxValue,FtpServer=:FtpServer,FtpUser=:FtpUser,FtpPassword=:FtpPassword,LocalPath=:LocalPath

		this(configType, configValue, minValue, maxValue, ftpServer, ftpUser, ftpPassword, localPath);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param configType 配置类型
	* @param configValue 配置值
	* @param minValue 最小值
	* @param maxValue 最大值
	* @param ftpServer FTP服务器
	* @param ftpUser FTP登录用户
	* @param ftpPassword FTP登录密码
	* @param localPath 本地路径
	*/
	public Config(String configType,String configValue,String minValue,String maxValue,String ftpServer,String ftpUser,String ftpPassword,String localPath)
	{
		this.configType = configType;
		this.configValue = configValue;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.ftpServer = ftpServer;
		this.ftpUser = ftpUser;
		this.ftpPassword = ftpPassword;
		this.localPath = localPath;
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
	 * 配置类型
	 */
	private String configType=null;

	/**
	 * 获取属性值：配置类型
	 * @return 配置类型
	 */
	public String getConfigType()
	{
		return configType;
	}

	/**
	 * 设置属性值：配置类型
	 * @param configType 配置类型
	 */
	public void setConfigType(String configType)
	{
		this.configType = configType;
	}

	/**
	 * 配置值
	 */
	private String configValue=null;

	/**
	 * 获取属性值：配置值
	 * @return 配置值
	 */
	public String getConfigValue()
	{
		return configValue;
	}

	/**
	 * 设置属性值：配置值
	 * @param configValue 配置值
	 */
	public void setConfigValue(String configValue)
	{
		this.configValue = configValue;
	}

	/**
	 * 最小值
	 */
	private String minValue=null;

	/**
	 * 获取属性值：最小值
	 * @return 最小值
	 */
	public String getMinValue()
	{
		return minValue;
	}

	/**
	 * 设置属性值：最小值
	 * @param minValue 最小值
	 */
	public void setMinValue(String minValue)
	{
		this.minValue = minValue;
	}

	/**
	 * 最大值
	 */
	private String maxValue=null;

	/**
	 * 获取属性值：最大值
	 * @return 最大值
	 */
	public String getMaxValue()
	{
		return maxValue;
	}

	/**
	 * 设置属性值：最大值
	 * @param maxValue 最大值
	 */
	public void setMaxValue(String maxValue)
	{
		this.maxValue = maxValue;
	}

	/**
	 * FTP服务器
	 */
	private String ftpServer=null;

	/**
	 * 获取属性值：FTP服务器
	 * @return FTP服务器
	 */
	public String getFtpServer()
	{
		return ftpServer;
	}

	/**
	 * 设置属性值：FTP服务器
	 * @param ftpServer FTP服务器
	 */
	public void setFtpServer(String ftpServer)
	{
		this.ftpServer = ftpServer;
	}

	/**
	 * FTP登录用户
	 */
	private String ftpUser=null;

	/**
	 * 获取属性值：FTP登录用户
	 * @return FTP登录用户
	 */
	public String getFtpUser()
	{
		return ftpUser;
	}

	/**
	 * 设置属性值：FTP登录用户
	 * @param ftpUser FTP登录用户
	 */
	public void setFtpUser(String ftpUser)
	{
		this.ftpUser = ftpUser;
	}

	/**
	 * FTP登录密码
	 */
	private String ftpPassword=null;

	/**
	 * 获取属性值：FTP登录密码
	 * @return FTP登录密码
	 */
	public String getFtpPassword()
	{
		return ftpPassword;
	}

	/**
	 * 设置属性值：FTP登录密码
	 * @param ftpPassword FTP登录密码
	 */
	public void setFtpPassword(String ftpPassword)
	{
		this.ftpPassword = ftpPassword;
	}

	/**
	 * 本地路径
	 */
	private String localPath=null;

	/**
	 * 获取属性值：本地路径
	 * @return 本地路径
	 */
	public String getLocalPath()
	{
		return localPath;
	}

	/**
	 * 设置属性值：本地路径
	 * @param localPath 本地路径
	 */
	public void setLocalPath(String localPath)
	{
		this.localPath = localPath;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public Config clone()
	{
		Config item = new Config(iD,configType,configValue,minValue,maxValue,ftpServer,ftpUser,ftpPassword,localPath);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param config 指定的对象源
	*/
	public void cloneFrom(Config config)
	{
		this.iD = config.getID();
		this.configType = config.getConfigType();
		this.configValue = config.getConfigValue();
		this.minValue = config.getMinValue();
		this.maxValue = config.getMaxValue();
		this.ftpServer = config.getFtpServer();
		this.ftpUser = config.getFtpUser();
		this.ftpPassword = config.getFtpPassword();
		this.localPath = config.getLocalPath();
		this.keyInCol = config.getKeyInCol();
		this.tag = config.getTag();
	}



    
}



