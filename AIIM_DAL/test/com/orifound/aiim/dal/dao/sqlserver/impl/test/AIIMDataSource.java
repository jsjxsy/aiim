/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * AIIM项目的数据源（单例模式）
 *
 */
public class AIIMDataSource
{
	/**
	 * 构造函数
	 */
	public AIIMDataSource()
	{
		String jdbcURL="jdbc:sqlserver://yf:1433;databaseName=AIIM_DB;userName=sa;password=123456";
		this.dataSource=new DriverManagerDataSource(jdbcURL);
	}
	
	private static AIIMDataSource instance=new AIIMDataSource();
	
	public static AIIMDataSource getInstance()
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
