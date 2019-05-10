package com.orifound.aiim.entity;

    
/**
 * ������Ϣ���ʵ����
 */
public class Config
{
    /**
     * ���캯��
     */
    public Config()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param configType ��������
	* @param configValue ����ֵ
	* @param minValue ��Сֵ
	* @param maxValue ���ֵ
	* @param ftpServer FTP������
	* @param ftpUser FTP��¼�û�
	* @param ftpPassword FTP��¼����
	* @param localPath ����·��
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
	* ���ֶβ����Ĺ��캯��
	* @param configType ��������
	* @param configValue ����ֵ
	* @param minValue ��Сֵ
	* @param maxValue ���ֵ
	* @param ftpServer FTP������
	* @param ftpUser FTP��¼�û�
	* @param ftpPassword FTP��¼����
	* @param localPath ����·��
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
	 * ��������
	 */
	private String configType=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getConfigType()
	{
		return configType;
	}

	/**
	 * ��������ֵ����������
	 * @param configType ��������
	 */
	public void setConfigType(String configType)
	{
		this.configType = configType;
	}

	/**
	 * ����ֵ
	 */
	private String configValue=null;

	/**
	 * ��ȡ����ֵ������ֵ
	 * @return ����ֵ
	 */
	public String getConfigValue()
	{
		return configValue;
	}

	/**
	 * ��������ֵ������ֵ
	 * @param configValue ����ֵ
	 */
	public void setConfigValue(String configValue)
	{
		this.configValue = configValue;
	}

	/**
	 * ��Сֵ
	 */
	private String minValue=null;

	/**
	 * ��ȡ����ֵ����Сֵ
	 * @return ��Сֵ
	 */
	public String getMinValue()
	{
		return minValue;
	}

	/**
	 * ��������ֵ����Сֵ
	 * @param minValue ��Сֵ
	 */
	public void setMinValue(String minValue)
	{
		this.minValue = minValue;
	}

	/**
	 * ���ֵ
	 */
	private String maxValue=null;

	/**
	 * ��ȡ����ֵ�����ֵ
	 * @return ���ֵ
	 */
	public String getMaxValue()
	{
		return maxValue;
	}

	/**
	 * ��������ֵ�����ֵ
	 * @param maxValue ���ֵ
	 */
	public void setMaxValue(String maxValue)
	{
		this.maxValue = maxValue;
	}

	/**
	 * FTP������
	 */
	private String ftpServer=null;

	/**
	 * ��ȡ����ֵ��FTP������
	 * @return FTP������
	 */
	public String getFtpServer()
	{
		return ftpServer;
	}

	/**
	 * ��������ֵ��FTP������
	 * @param ftpServer FTP������
	 */
	public void setFtpServer(String ftpServer)
	{
		this.ftpServer = ftpServer;
	}

	/**
	 * FTP��¼�û�
	 */
	private String ftpUser=null;

	/**
	 * ��ȡ����ֵ��FTP��¼�û�
	 * @return FTP��¼�û�
	 */
	public String getFtpUser()
	{
		return ftpUser;
	}

	/**
	 * ��������ֵ��FTP��¼�û�
	 * @param ftpUser FTP��¼�û�
	 */
	public void setFtpUser(String ftpUser)
	{
		this.ftpUser = ftpUser;
	}

	/**
	 * FTP��¼����
	 */
	private String ftpPassword=null;

	/**
	 * ��ȡ����ֵ��FTP��¼����
	 * @return FTP��¼����
	 */
	public String getFtpPassword()
	{
		return ftpPassword;
	}

	/**
	 * ��������ֵ��FTP��¼����
	 * @param ftpPassword FTP��¼����
	 */
	public void setFtpPassword(String ftpPassword)
	{
		this.ftpPassword = ftpPassword;
	}

	/**
	 * ����·��
	 */
	private String localPath=null;

	/**
	 * ��ȡ����ֵ������·��
	 * @return ����·��
	 */
	public String getLocalPath()
	{
		return localPath;
	}

	/**
	 * ��������ֵ������·��
	 * @param localPath ����·��
	 */
	public void setLocalPath(String localPath)
	{
		this.localPath = localPath;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public Config clone()
	{
		Config item = new Config(iD,configType,configValue,minValue,maxValue,ftpServer,ftpUser,ftpPassword,localPath);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param config ָ���Ķ���Դ
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



