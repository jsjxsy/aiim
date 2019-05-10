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
public class AIIMDataSourceTYB
{
	/**
	 * ���캯��
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
