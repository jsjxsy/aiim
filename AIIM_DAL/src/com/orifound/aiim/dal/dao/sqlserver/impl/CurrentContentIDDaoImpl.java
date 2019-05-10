/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.ICurrentContentIDDao;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 当前案卷号信息表的DAO实现类（SQL SERVER平台）
 *
 */
public class CurrentContentIDDaoImpl extends JdbcDaoSupport implements ICurrentContentIDDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class CurrentContentIDMapper implements RowMapper<CurrentContentID>
	{
		
		@Override
		public CurrentContentID mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String archivesIDPrefix = rs.getString("ArchivesIDPrefix");
			int contentID = rs.getInt("ContentID");
			
			return new CurrentContentID(archivesIDPrefix,contentID);
		}
	}
	
	/**
	 * 构造函数
	 */
	public CurrentContentIDDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public CurrentContentIDDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * 插入保存新增的当前案卷号信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO CurrentContentID(ArchivesIDPrefix,ContentID) VALUES(:ArchivesIDPrefix,1)";
	
	/**
	 * 更新指定的当前案卷号信息（累加1）的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE CurrentContentID SET ContentID=ContentID+1 WHERE ArchivesIDPrefix=:ArchivesIDPrefix";
	
	/**
	 * 查询指定的当前案卷号信息的SQL语句
	 */
	private final String SQL_SELECT = "SELECT * FROM CurrentContentID WHERE ArchivesIDPrefix=:ArchivesIDPrefix";

	/**
	 * 查询指定的当前案卷号信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM CurrentContentID";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICurrentContentIDDao#save(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(CurrentContentID currentContentID, ErrInfo pErrInfo)
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
			
			//检查当前案卷号信息的完整性
			if (pFlag)
			{
				pErrPos = 1;
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "当前案卷号信息不完整: ");
				}
			}

			//执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesIDPrefix", currentContentID.getArchivesIDPrefix(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);

				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate = null;
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.dal.dao.ICurrentContentIDDao#update(com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(CurrentContentID currentContentID, ErrInfo pErrInfo)
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
			
			//检查当前案卷号信息的完整性
			if (pFlag)
			{
				if (checkCurrentContentID(currentContentID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "当前案卷号信息不完整: ");
				}
			}

			//执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesIDPrefix", currentContentID.getArchivesIDPrefix(), Types.VARCHAR);

				pErrPos = 4;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);

				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate = null;
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * 检查当前案卷号信息的完整性
	 * @param currentContentID 当前案卷号信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkCurrentContentID(CurrentContentID currentContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查当前案卷号信息是否为空
			pErrPos = 1;
			if (currentContentID==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("当前案卷号信息非法为空。");
			}

			//检查档案全宗编号是否为空
			if (pFlag)
			{
				if (currentContentID.getArchivesIDPrefix()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案全宗编号非法为空。");
				}
				else 
				{
					if ("".equals(currentContentID.getArchivesIDPrefix()))
					{
						pFlag = false;
						pErrInfo.getContent().append("档案全宗编号非法为空。");
					}
				}
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
				if (pErrInfo.getException() != null)
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
	 * @see com.orifound.aiim.dal.dao.ICurrentContentIDDao#findByID(java.lang.String, int, com.orifound.aiim.entity.CurrentContentID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByPrefix(String archivesIDPrefix,CurrentContentID currentContentID, ErrInfo pErrInfo)
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
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesIDPrefix", archivesIDPrefix, Types.VARCHAR);

				pErrPos = 4;
				List<CurrentContentID> pCurrentContentIDs=namedParameterJdbcTemplate.query(SQL_SELECT, paramSource,new CurrentContentIDMapper());

				//返回查询结果
				if (pCurrentContentIDs.size()>0)
				{
					currentContentID.cloneFrom(pCurrentContentIDs.get(0));
				}
				
				//销毁局部变量
				pCurrentContentIDs=null;
				paramSource=null;
				namedParameterJdbcTemplate = null;
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
				if (pErrInfo.getException() != null)
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

	@Override
	public boolean findAll(List<CurrentContentID> pCurrentContentIDs, ErrInfo pErrInfo) {
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
				List<CurrentContentID> currentContentIDs = getJdbcTemplate().query(SQL_SELECT_ALL,new CurrentContentIDMapper());

				//返回查询结果
				if (currentContentIDs.size() > 0) {
					pCurrentContentIDs.addAll(currentContentIDs);
				}
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
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
