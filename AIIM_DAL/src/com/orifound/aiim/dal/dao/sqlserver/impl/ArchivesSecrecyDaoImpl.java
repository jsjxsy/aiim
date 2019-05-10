/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案密级字典表（DD_ArchivesSecrecy）的DAO实现类（SQL Server平台）
 *
 */
public class ArchivesSecrecyDaoImpl extends JdbcDaoSupport implements IArchivesSecrecyDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesSecrecyMapper implements RowMapper<ArchivesSecrecy>
	{
		
		@Override
		public ArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new ArchivesSecrecy(iD,name,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesSecrecyDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesSecrecyDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询所有档案密级信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_ArchivesSecrecy ORDER BY ID";
	
	/**
	 * 查询公开密级信息的SQL语句
	 */
	private final String SQL_SELECT_PUBLIC = "SELECT * FROM DD_ArchivesSecrecy WHERE SystemSecrecyFlag=1";
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesSecrecyDao#save(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesSecrecyDao#delete(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesSecrecyDao#update(com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesSecrecyDao#findAll(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
				
			}

			//执行SQL语句
			if (pFlag)
			{

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesSecrecy> pArchivesSecrecys=jdbcTemplate.query(SQL_SELECT_ALL,new ArchivesSecrecyMapper());

				//返回查询结果
				if (pArchivesSecrecys.size() > 0)
				{
					for (ArchivesSecrecy item : pArchivesSecrecys)
					{
						archivesSecrecys.put(item.getID(), item);
					}
				}

				//销毁局部变量
				pArchivesSecrecys=null;
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesSecrecyDao#findByID(int, com.orifound.aiim.entity.ArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesSecrecy archivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByOpenSecrecy(ArchivesSecrecy archivesSecrecy,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesSecrecy> pEntitys=jdbcTemplate.query(SQL_SELECT_PUBLIC, new ArchivesSecrecyMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					archivesSecrecy.cloneFrom(pEntitys.get(0));
				}

				//销毁局部变量
				pEntitys = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

}
