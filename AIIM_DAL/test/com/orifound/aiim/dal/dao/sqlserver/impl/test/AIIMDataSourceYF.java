/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * �������ϵ�AIIM��Ŀ����Դ������ģʽ��
 *
 */
public class AIIMDataSourceYF
{
	/**
	 * ���캯��
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
