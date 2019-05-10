/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * AIIM��Ŀ������Դ������ģʽ��
 *
 */
public class AIIMDataSource
{
	/**
	 * ���캯��
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
	 * JDBC����Դ
	 */
	private DataSource dataSource = null;

	/**
	 * ��ȡ����ֵ��JDBC����Դ
	 * @return JDBC����Դ
	 */
	public DataSource getDataSource()
	{
		return dataSource;
	}

	
}
