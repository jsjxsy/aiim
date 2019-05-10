/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 代向涛机器上的AIIM项目数据源（单例模式）
 *
 */
public class AIIMDataSourceDXT
{
	/**
	 * 构造函数
	 */
	public AIIMDataSourceDXT()
	{
		String jdbcURL="jdbc:sqlserver://dxt:1433;databaseName=AIIM_DB;userName=sa;password=123456";
		this.dataSource=new DriverManagerDataSource(jdbcURL);
	}
	
	private static AIIMDataSourceDXT instance=new AIIMDataSourceDXT();
	
	public static AIIMDataSourceDXT getInstance()
	{
		return instance;
	}
	
	/**
	 * JDBC数据源
	 */
	private DataSource dataSource = null;

	/**
	 * 获取属性值：JDBC数据源
	 * @return JDBC数据源
	 */
	public DataSource getDataSource()
	{
		return dataSource;
	}

	
}
