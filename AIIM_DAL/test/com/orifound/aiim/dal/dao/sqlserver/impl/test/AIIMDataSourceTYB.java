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
public class AIIMDataSourceTYB
{
	/**
	 * 构造函数
	 */
	private AIIMDataSourceTYB()
	{
		String jdbcURL="jdbc:sqlserver://tyb:1433;databaseName=AIIM_DD;userName=sa;password=123";
		this.dataSource=new DriverManagerDataSource(jdbcURL);
	}
	
	private static AIIMDataSourceTYB instance=new AIIMDataSourceTYB();
	
	public static AIIMDataSourceTYB getInstance()
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
