/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoTableDao;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案相关信息表（DD_ArchivesInfoTable）的DAO实现类（SQL Server平台）
 *
 */
public class ArchivesInfoTableDaoImpl extends JdbcDaoSupport implements IArchivesInfoTableDao
{
	/**
	 * 查询结果集到实体类的映射器
	 *
	 */
	private class ArchivesInfoTableMapper implements RowMapper<ArchivesInfoTable>
	{

		@Override
		public ArchivesInfoTable mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD=rs.getInt("ID");
			int archivesTypeID=rs.getInt("ArchivesTypeID");
			int tableType=rs.getInt("TableType");
			String tableName=rs.getString("TableName");
			boolean createdFlag=rs.getBoolean("CreatedFlag");
			Date createdTime=rs.getTimestamp("CreatedTime");
			String remark=rs.getString("Remark");

			return new ArchivesInfoTable(iD, archivesTypeID, tableType, tableName, 
					createdFlag, createdTime, remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesInfoTableDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesInfoTableDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	//SQL语句
	private final String SQL_SELECT_BYTYPEID="SELECT * FROM DD_ArchivesInfoTable WHERE ArchivesTypeID=?";
	
	
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
			if (getDataSource()==null)
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
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#save(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#delete(int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteByArchivesTypeID(int pArchivesTypeID, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#setCreatedFlag(com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean setCreatedFlag(ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#findByArchivesTypeID(int, java.util.EnumMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByArchivesTypeID(int pArchivesTypeID, EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			//执行SQL语句
			if (pFlag)
			{
				pErrPos=1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfoTable> pArchivesInfoTables= jdbcTemplate.query(SQL_SELECT_BYTYPEID, new ArchivesInfoTableMapper(),pArchivesTypeID);
				
				//返回查询结果
				if (pArchivesInfoTables.size()>0)
				{
					for (ArchivesInfoTable item : pArchivesInfoTables)
					{
						archivesInfoTables.put(item.getTableType(), item);
					}
				}
				
				//销毁具备变量
				pArchivesInfoTables=null;
				jdbcTemplate=null;
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
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoTableDao#findByID(int, com.orifound.aiim.entity.ArchivesInfoTable, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesInfoTable archivesInfoTable, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
