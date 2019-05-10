/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * 杨丰机器上的AIIM项目数据源（单例模式）
 *
 */
public class AIIMDataSourceYF
{
	/**
	 * 构造函数
	 */
	public AIIMDataSourceYF()
	{
		String jdbcURL="jdbc:sqlserver://yf:1433;databaseName=AIIM_DB;userName=sa;password=123456";
		this.dataSource=new DriverManagerDataSource(jdbcURL);
	}
	
	private static AIIMDataSourceYF instance=new AIIMDataSourceYF();
	
	public static AIIMDataSourceYF getInstance()
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
