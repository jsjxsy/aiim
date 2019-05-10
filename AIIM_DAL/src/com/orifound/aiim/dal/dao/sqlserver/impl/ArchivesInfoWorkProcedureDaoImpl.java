/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 档案过程过程信息的DAO实现类
 *
 */
public class ArchivesInfoWorkProcedureDaoImpl extends JdbcDaoSupport implements IArchivesInfoWorkProcedureDao
{
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesInfoWorkProcedureMapper implements RowMapper<ArchivesInfoWorkProcedure>
	{
		
		@Override
		public ArchivesInfoWorkProcedure mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int nBXH = rs.getInt("NBXH");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int userID = rs.getInt("UserID");
			int workFlowStatus = rs.getInt("WorkFlowStatus");
			Date operateTime = rs.getTimestamp("OperateTime");
			String sendBackReason = rs.getString("SendBackReason");
			
			return new ArchivesInfoWorkProcedure(iD,nBXH,archivesTypeID,userID,workFlowStatus,operateTime,sendBackReason);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesInfoWorkProcedureDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesInfoWorkProcedureDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 插入新的工作过程信息的SQL语句<br>
	 * %1$s：具体档案分类对应的归档过程表名
	 */
	private final String SQL_INSERT = "INSERT INTO %1$s(NBXH,ArchivesTypeID,UserID,WorkFlowStatus,SendBackReason) VALUES(:NBXH,:ArchivesTypeID,:UserID,:WorkFlowStatus,:SendBackReason)";

	/**
	 * 查找指定内部序号的所有工作过程信息的SQL语句<br>
	 * %1$s：具体档案分类对应的归档过程表名
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?";
	
	/**
	 * 删除指定内部序号的工作过程信息的SQL语句<br>
	 * %1$s：具体档案分类对应的归档过程表名
	 */
	private final String SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=?";
	
	/**
	 * 删除指定案卷级档案内部序号及其卷内文件的工作过程信息的SQL语句<br>
	 * %1$s：具体档案分类对应的归档过程表名
	 * %2$s：具体档案分类对应的归档工作表名
	 */
	private final String SQL_DELETE_ByNBXH_ForParent = "DELETE FROM %1$s WHERE NBXH IN (SELECT NBXH FROM %2$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH)";
	
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
	 * 检查指定的档案分类信息下其档案归档工作表信息是否正确赋值了
	 * 
	 * @param archivesType
	 *            档案分类信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableNameForArchivesWorking(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表非法为空");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.档案归档工作表) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 检查指定的档案分类信息下其档案归档过程表信息是否正确赋值了
	 * @param archivesType 档案分类信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableNameForWorkProcedure(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表非法为空");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.档案归档过程表) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档过程表信息非法为空。");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档过程表信息非法为空。");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档过程表信息非法为空。");
						}
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
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao#save(com.orifound.aiim.entity.ArchivesType,com.orifound.aiim.entity.ArchivesInfoWorkProcedure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//档案归档过程表名
		String localSql="";//最终提交的SQL语句
		
		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}
			
			//检查档案工作过程信息是否为空
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案工作过程信息非法为空。");
				}
				else 
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的内部序号非法为空。");
					}
				}
			}
			
			//检查档案分类对应的工作过程表名称是否有值
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//保存当前分类的档案归档过程表名
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag)
			{
//				SQL_INSERT = "INSERT INTO %1$s(NBXH,ArchivesTypeID,UserID,WorkFlowStatus,SendBackReason) 
//				VALUES(:NBXH,:ArchivesTypeID,:UserID,:WorkFlowStatus,:SendBackReason)";
				
				pErrPos = 2;
				//将档案归档过程表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_INSERT, archivesInfoWorkProcedureTableName);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", archivesInfoWorkProcedure.getNBXH(), Types.INTEGER);
				paramSource.addValue("ArchivesTypeID", archivesInfoWorkProcedure.getArchivesTypeID(), Types.INTEGER);
				paramSource.addValue("UserID", archivesInfoWorkProcedure.getUserID(), Types.INTEGER);
				paramSource.addValue("WorkFlowStatus", archivesInfoWorkProcedure.getWorkFlowStatus().getEnumValue(), Types.INTEGER);
				paramSource.addValue("SendBackReason", archivesInfoWorkProcedure.getSendBackReason(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(localSql, paramSource);

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
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao#findByNBXH(com.orifound.aiim.entity.ArchivesType,int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//档案归档过程表名
		String localSql="";//最终提交的SQL语句
		
		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}
			
			//检查内部序号是否为0
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}
			
			//检查档案分类对应的工作过程表名称是否有值
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//保存当前分类的档案归档过程表名
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag)
			{
//				SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?"
				
				pErrPos = 2;
				//将档案归档过程表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_ByNBXH, archivesInfoWorkProcedureTableName);
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfoWorkProcedure> pArchivesInfoWorkProcedures=jdbcTemplate.query(localSql,new ArchivesInfoWorkProcedureMapper(),pNBXH);

				//返回查询结果
				if (pArchivesInfoWorkProcedures.size() > 0)
				{
					archivesInfoWorkProcedures.addAll(pArchivesInfoWorkProcedures);
				}

				//销毁局部变量
				pArchivesInfoWorkProcedures=null;
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
	public boolean deleteForSingleArchives(ArchivesType archivesType, ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//档案归档过程表名
		String localSql="";//最终提交的SQL语句
		
		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}
			
			//检查档案工作过程信息是否为空
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案工作过程信息非法为空。");
				}
				else 
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的内部序号非法为空。");
					}
				}
			}
			
			//检查档案分类对应的工作过程表名称是否有值
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//保存当前分类的档案归档过程表名
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag)
			{
//				SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=?";
				
				pErrPos = 2;
				//将档案归档过程表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_DELETE_ByNBXH, archivesInfoWorkProcedureTableName);
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(localSql,archivesInfoWorkProcedure.getNBXH());

				//销毁局部变量
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
	public boolean deleteForParentArchives(ArchivesType archivesType, ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//档案归档过程表名
		String archivesInfoWorkingTableName="";//档案归档工作表名
		String localSql="";//最终提交的SQL语句
		
		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}
			
			//检查档案工作过程信息是否为空
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案工作过程信息非法为空。");
				}
				else 
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的内部序号非法为空。");
					}
				}
			}
			
			//检查档案分类对应的工作过程表名称是否有值
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//保存当前分类的档案归档过程表名
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档过程表).getTableName();
				}
			}
			
			
			//检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableNameForArchivesWorking(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//保存当前分类的档案归档工作表名
					archivesInfoWorkingTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//执行SQL语句
			if (pFlag)
			{
//				SQL_DELETE_ByNBXH_ForParent = "DELETE FROM %1$s WHERE NBXH IN (SELECT NBXH FROM %2$s 
//				WHERE NBXH=:NBXH OR ParentNBXH=:NBXH)";
				
				pErrPos = 2;
				//将档案归档过程表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_DELETE_ByNBXH_ForParent, archivesInfoWorkProcedureTableName,archivesInfoWorkingTableName);
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", archivesInfoWorkProcedure.getNBXH(), Types.VARCHAR);

				namedParameterJdbcTemplate.update(localSql, paramSource);

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
	 * 监测档案形成部门、业务指导室的人员特定时间段内著录档案的数量<br>
	 * @param recordCondition map集合保存:人员真实姓名-著录数量 键值对
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间	
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean recordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			if (pFlag) {
				pErrPos = 1;
				if(getArchivesInfoWorkProcedureCount(recordCondition, userIds, new Integer[]{10}, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "getArchivesInfoWorkProcedureCount获取档案归档过程记录数执行失败。");
				}
			}
		} catch (Exception e) {
			//异常错误
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
	
	/**
	 * Dao接口定义：监测业务指导室的人员特定时间段内审核、待审核档案的数量<br>
	 * @param recordAudit 封装成Map<String, Integer[]>保存人员审核档案数量,String保存人员真实姓名,Integer[0]保存审核总数、Integer[1]待审核
	 * @param userIds 人员id集合
	 * @param beginTime 检测开始时间
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	 public boolean recordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
		 boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//人员接收档案著录总数量
			Map<String, Integer> totalCount = new LinkedHashMap<String, Integer>();
			//人员已审核通过档案著录的总数量
			Map<String, Integer> passCount = new LinkedHashMap<String, Integer>();
			
			//查询人员接收档案总数量
			if (pFlag) {
				pErrPos = 1;
				if(getArchivesInfoWorkProcedureCount(totalCount, userIds, new Integer[]{20}, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询人员接收档案著录审核总数量 失败：");
				}
			}
			
			//查询人员已完成著录审核的总数量
			if (pFlag) {
				pErrPos = 2;
				if(getArchivesInfoWorkProcedureCount(passCount, userIds, new Integer[]{40,30}, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询人员已完成著录审核数量 失败：");
				}
			}
			
			//组装生成待审核、审核数量
			if (pFlag) {
				pErrPos = 3;
				if(totalCount.keySet().size() >= 1 || passCount.keySet().size()>=1) {
					//使用Set集合获取所有业务指导室人员
					Set<String> nameSet = new HashSet<String>();
					nameSet.addAll(totalCount.keySet());
					nameSet.addAll(passCount.keySet());
					
					//数组记录已审核、待审核数量
					Integer[] nums = null;
					for(String name : nameSet) {
						//默认设置值为0
						nums = new Integer[]{0,0};
						
						//判断是否有审核档案数
						if(totalCount.get(name) != null) {
							
							//判断是否有未审核档案数
							if(passCount.get(name) != null) {
								//设置已审核的数量
								nums[0] = passCount.get(name);
							} 
							//设置待审核的数量
							nums[1] = totalCount.get(name)- nums[0];
						}
						//封装成Map<String, Integer[]>保存人员审核档案数量 
						recordAudit.put(name, nums);
					}
				}
			}
		} catch (Exception e) {
			//异常错误
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
	
	/**
	 * 获取档案归档过程表记录数量
	 * @param recordCondition map集合保存:人员真实姓名-著录数量 键值对
	 * @param userIds 人员id集合
	 * @param WorkFlowStatus 档案规程状态
	 * @param beginTime 检测开始时间	
	 * @param endTime	检测结束时间
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getArchivesInfoWorkProcedureCount(Map<String, Integer> recordCondition, List<Integer> userIds, Integer[] WorkFlowStatus, 
			String beginTime, String endTime, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查著录结果map集合是否为空
			if (pFlag) {
				if(recordCondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("输入参数:著录结果map集合非法为空。");
				}
			}
			
			//检查业务指导室人员id集合是否为空
			if (pFlag) {
				if(userIds==null || userIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("输入参数:人员id集合非法为空。");
				}
			}
			
			//获取档案归档过程表名集合
			Set<String> tableNames = null;
			if (pFlag) {
				tableNames = new HashSet<String>();
				if(getArchivesInfoTables(tableNames, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("循环遍历获取 档案归档过程表名集合非法为空。");
				}
				
				//判断获取表名集合是否为空
				if (pFlag) {
					if(tableNames.size() <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("档案归档过程表名集合中不存在表。");
					}
				}
			}
			
			StringBuffer nativeSQL = null;
			//拼接SQL语句
			/**
			 * 生成SQL语句
			 * select u.RealName,t1.num from(
				select t.UserID,count(t.NBXH),MAX(t.OperateTime) OperateTime num from
				(
				select wp.NBXH,u.UserID,wp.OperateTime from ArchivesInfoWorkProcedure_JX wp,UserInfo u 
					where wp.UserID=u.UserID and wp.WorkFlowStatus=?
				union all
				select wp.NBXH,u.UserID from ArchivesInfoWorkProcedure_JX wp,UserInfo u 
					where wp.UserID=u.UserID and wp.WorkFlowStatus=?
				) t
				group by t.UserID
				) as t1 right on UserInfo u where u.UserID=t1.UserID AND u.UserID in(:userIds)
				AND t1.OperateTime>=:beginTime AND t1.OperateTime<=:endTime
			 */
			if (pFlag) {
				pErrPos = 2;
				String orSQL = " OR ";
				nativeSQL = new StringBuffer("SELECT u.RealName,t1.num FROM ( SELECT t.UserID,count(DISTINCT t.NBXH) num,MAX(t.OperateTime) OperateTime FROM ( ");
				String union = " UNION ALL ";
				for(String tableName : tableNames) {
					nativeSQL.append("SELECT wp.NBXH,u.UserID,wp.OperateTime FROM ");
					nativeSQL.append(tableName).append(" wp,UserInfo u ");
					nativeSQL.append("WHERE wp.UserID=u.UserID ");
					nativeSQL.append(" AND (");
					for(Integer stau : WorkFlowStatus) {
						nativeSQL.append(" wp.WorkFlowStatus=").append(stau).append(orSQL);
					}
					//取消最后的OR
					nativeSQL.delete(nativeSQL.length()-orSQL.length(), nativeSQL.length());
					nativeSQL.append(") ").append(union);
				}
				//去掉最后的一个UNION ALL
				nativeSQL.delete(nativeSQL.length()-union.length(), nativeSQL.length());
				nativeSQL.append(") t GROUP BY t.UserID ) AS t1,UserInfo u WHERE u.UserID in(:userIds) AND u.UserID=t1.UserID ");
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("userIds", userIds);
				
				//判断开始时间是否为空
				if(StringTool.checkNull(beginTime)) {
					nativeSQL.append(" AND t1.OperateTime>=:beginTime");
					paramSource.addValue("beginTime", TimeTool.getMinTime(beginTime), Types.TIMESTAMP);
				}
				//判断结束时间是否为空
				if(StringTool.checkNull(endTime)) {
					nativeSQL.append(" AND t1.OperateTime<=:endTime");
					paramSource.addValue("endTime",TimeTool.getMaxTime(endTime) ,Types.TIMESTAMP);
				}
				
				pErrPos = 3;
				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(nativeSQL.toString(), paramSource);
				
//				for(Map<String, Object> result : results) {
//					for(String key : result.keySet()) {
//						System.out.println(key+" "+result.get(key));
//					}
//				}
				
				System.out.println("档案归档过程表记录数量统计="+nativeSQL.toString());
				//判断是否有统计结果
				if(results.size() <= 0) {
					//无统计结果设置默认值
					paramSource = new MapSqlParameterSource();
					paramSource.addValue("userIds", userIds);
					nativeSQL = new StringBuffer("SELECT u.RealName,0 num FROM UserInfo u WHERE u.UserID in(:userIds)");	
					results = namedParameterJdbcTemplate.queryForList(nativeSQL.toString(), paramSource);
				} 
				
				for(Map<String, Object> result : results) {
					recordCondition.put(result.get("RealName").toString(), Integer.valueOf(result.get("num").toString()));
				}
				
				//销毁局部变量
				nativeSQL = null;
				results = null;
				namedParameterJdbcTemplate = null;
			}
			
		} catch (NumberFormatException e) {
			// 数据类型转换异常错误
			pFlag = false;
			pErrInfo.getContent().append("著录情况的结果集著录数量Object转成Integer异常。"+e.toString());
			pErrInfo.setException(e);
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
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
	
	
	/**
	 * 循环遍历获取 档案归档过程表名集合
	 * @param tableNames 	档案归档过程表名集合	
	 * @param pErrInfo  	处理失败的错误原因描述	
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getArchivesInfoTables(Set<String> tableNames, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//检查平面结构的档案分类集合是否为空
			Map<Integer, ArchivesType> planeArchivesTypes = null;
			if (pFlag) {
				planeArchivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();
				if(planeArchivesTypes == null) {
					pFlag = false;
					pErrInfo.getContent().append("获取系统初始化器定义类下的->平面结构的档案分类集合 属性为空。");
				}
			}
			
			//检查是否存在档案分类
			Collection<ArchivesType> archivesTypes = null;
			if(pFlag) {
				archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes().values();
				if(archivesTypes == null) {
					pFlag = false;
					pErrInfo.getContent().append("获取系统初始化器定义类下的->平面结构的档案分类集合的->值对象集合档案分类集合为空。");
				}
			}
			
			if(pFlag) {
				EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> enumMap = null;
				ArchivesInfoTable archivesInfoTable = null;
				for(ArchivesType archivesType : archivesTypes) {
					enumMap = archivesType.getArchivesInfoTables();
					//判断档案主体的相关信息表集合	是否为空
					if(enumMap == null) {
						continue;
					}
					
					//判断档案信息相关表的字典表的实体类	是否为空
					archivesInfoTable = enumMap.get(EnumArchivesInfoTableType.档案归档过程表);
					if(archivesInfoTable == null) {
						continue;
					}
					tableNames.add(archivesInfoTable.getTableName());
				}
			}
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
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

	@Override
	public boolean findInStorageCondition(Map<String, Integer> inStorageCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
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
				//DoSomething
				if (pFlag) {
					getArchivesInfoWorkProcedureCount(inStorageCondition, userIds, new Integer[]{120}, beginTime, endTime, pErrInfo);
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
