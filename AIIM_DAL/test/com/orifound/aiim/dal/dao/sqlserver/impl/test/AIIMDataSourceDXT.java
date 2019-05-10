/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * �����λ����ϵ�AIIM��Ŀ����Դ������ģʽ��
 *
 */
public class AIIMDataSourceDXT
{
	/**
	 * ���캯��
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
