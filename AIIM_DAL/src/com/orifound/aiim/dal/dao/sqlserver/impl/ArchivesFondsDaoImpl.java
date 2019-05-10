/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesFondsDao;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案全宗字典表的DAO实现类（SQL Server平台）
 * 
 */
public class ArchivesFondsDaoImpl extends JdbcDaoSupport implements IArchivesFondsDao
{
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesFondsMapper implements RowMapper<ArchivesFonds>
	{
		@Override
		public ArchivesFonds mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			boolean defaultFlag = rs.getBoolean("DefaultFlag");
			String remark = rs.getString("Remark");
			
			return new ArchivesFonds(iD,code,name,defaultFlag,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesFondsDaoImpl()
	{
		
	}
	
	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesFondsDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	
	//SQL语句
	
	/**
	 * 新增档案全宗记录
	 */
	private final String SQL_INSERT="INSERT INTO DD_ArchivesFonds(Code,Name,DefaultFlag,Remark) VALUES(:Code,:Name,:DefaultFlag,:Remark)";
	
	/**
	 * 删除指定的档案全宗记录
	 */
	private final String SQL_DELETE="DELETE FROM DD_ArchivesFonds WHERE ID=?";
	
	/**
	 * 更新指定的档案全宗记录
	 */
	private final String SQL_UPDATE="UPDATE DD_ArchivesFonds SET Name=:Name,Remark=:Remark WHERE ID=:ID";
	
	/**
	 * 查询所有档案全宗记录
	 */
	private final String SQL_SELECT_ALL="SELECT * FROM DD_ArchivesFonds ORDER BY ID";
	
	/**
	 * 查询指定的档案全宗记录
	 */
	private final String SQL_SELECT_BYID="SELECT * FROM DD_ArchivesFonds WHERE ID=?";
	
	
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
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesFondsDao#save(com.orifound.aiim.entity
	 * .ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			// 检查全宗编号是否有赋值
			if (pFlag)
			{
				pErrPos = 1;
				if (pArchivesFonds.getCode() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗号非法为空。");
				}
				else
				{
					if (pArchivesFonds.getCode().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("全宗号非法为空。");
					}
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos =2;
				if (pArchivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗名称非法为空。");
				}
				else
				{
					if (pArchivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("全宗名称非法为空。");
					}
				}
			}

			// 构造并执行插入处理
			if (pFlag)
			{
				pErrPos = 3;
				//"INSERT INTO DD_ArchivesFonds(Code,Name,DefaultFlag,Remark) VALUES(:Code,:Name,:DefaultFlag,:Remark)";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Code", pArchivesFonds.getCode(),Types.VARCHAR);
				paramSource.addValue("Name", pArchivesFonds.getName(),Types.VARCHAR);
				paramSource.addValue("DefaultFlag", pArchivesFonds.getDefaultFlag(),Types.BIT);
				paramSource.addValue("Remark", pArchivesFonds.getRemark(), Types.VARCHAR);
				
				pErrPos = 4;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesFondsDao#delete(com.orifound.aiim.
	 * entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			//检查全宗编号是否有赋值
			if (pFlag)
			{
				pErrPos = 1;
				if (pArchivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗数字编号非法为空。");
				}
			}

			//构造SQL执行之
			if (pFlag)
			{
				pErrPos = 2;
				//SQL_DELETE="DELETE FROM DD_ArchivesFonds WHERE ID=?";
				
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE, pArchivesFonds.getID());
				
				//销毁局部变量
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesFondsDao#update(com.orifound.aiim.
	 * entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			// 检查全宗编号是否有赋值
			{
				pErrPos = 1;
				if (pArchivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗数字编号非法为空。");
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos =2;
				if (pArchivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗名称非法为空。");
				}
				else
				{
					if (pArchivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("全宗名称非法为空。");
					}
				}
			}
			
			//构造SQL执行之
			if (pFlag)
			{
				pErrPos = 3;
				//SQL_UPDATE="UPDATE DD_ArchivesFonds SET Name=:Name,Remark=:Remark WHERE ID=:ID";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Name", pArchivesFonds.getName());
				paramSource.addValue("Remark", pArchivesFonds.getRemark());
				paramSource.addValue("ID", pArchivesFonds.getID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				
				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate=null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IArchivesFondsDao#findAll(java.util.List,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<ArchivesFonds> pArchivesFondss, ErrInfo pErrInfo)
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
			
			//构造SQL执行之
			//DoSomething
			if (pFlag)
			{
				pErrPos = 1;
				//SQL_SELECT_ALL="SELECT * FROM DD_ArchivesFonds ORDER BY ID";
	
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				List<ArchivesFonds> archivesFonds= jdbcTemplate.query(SQL_SELECT_ALL, new ArchivesFondsMapper());
				
				//返回结果
				pArchivesFondss.addAll(archivesFonds);
				
				//销毁局部变量
				archivesFonds=null;
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
	 * @see com.orifound.aiim.dal.dao.IArchivesFondsDao#findByID(java.lang.String, com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			//检查ID是否赋值
			if (pFlag)
			{
				pErrPos = 1;
				if (pID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("全宗数字编号非法为空。");
				}
			}

			//构造SQL执行之
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				List<ArchivesFonds> archivesFondss=jdbcTemplate.query(SQL_SELECT_BYID, new ArchivesFondsMapper(), pID);
				if (archivesFondss.size()>0)
				{
					//返回查询结果
					pArchivesFonds.cloneFrom(archivesFondss.get(0));
				}
				
				//销毁局部变量
				archivesFondss=null;
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

}
