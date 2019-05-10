/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.LinkedHashMap;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 公文档案分类信息的DAO实现类（SQL Server平台）
 *
 */
public class OfficialArchivesTypeDaoImpl extends JdbcDaoSupport implements IOfficialArchivesTypeDao
{
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class OfficialArchivesTypeMapper implements RowMapper<OfficialArchivesType>
	{
		
		@Override
		public OfficialArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String code = rs.getString("Code");
			String attachedFileSavePath = rs.getString("AttachedFileSavePath");
			String remark = rs.getString("Remark");
			
			return new OfficialArchivesType(iD,name,code,attachedFileSavePath,remark);
		}
	}
	
	
	/**
	 * 构造函数
	 */
	public OfficialArchivesTypeDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public OfficialArchivesTypeDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * 查询所有公文档案分类信息的SQL语句
	 */
	private final String SQL_SELECT = "SELECT * FROM DD_OfficialArchivesType ORDER BY ID ASC";
	
	/**
	 * 根据公文档案分类编号的查询其详细信息的SQL语句
	 */
	private final String SQL_SELECT_ByID = "SELECT * FROM DD_OfficialArchivesType WHERE ID=?";
	
	/**
	 * 插入一条公文档案分类记录的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO DD_OfficialArchivesType(Name,Code,AttachedFileSavePath,Remark) VALUES(:Name,:Code,:AttachedFileSavePath,:Remark) ";
	
	

	
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
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#save(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
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
			
			//检查公文档案名称是否为空
			if (pFlag)
			{
				if (officialArchivesType.getName()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案名称非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag)
			{
				//SQL_INSERT = "INSERT INTO DD_OfficialArchivesType(Name,Code,AttachedFileSavePath,Remark) 
				//VALUES(:Name,:Code,:AttachedFileSavePath,:Remark) ";
				
				pErrPos = 1;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT);
				//构造SQL执行成功后返回的主键
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				//设置主键字段名
				pscFactory.setGeneratedKeysColumnNames(new String[] { "ID" });
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Name", officialArchivesType.getName(), Types.VARCHAR);
				paramSource.addValue("Code", officialArchivesType.getCode(), Types.VARCHAR);
				paramSource.addValue("AttachedFileSavePath", officialArchivesType.getAttachedFileSavePath(), Types.VARCHAR);
				paramSource.addValue("Remark", officialArchivesType.getRemark(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);
				
				//返回自动生成的内部序号
				officialArchivesType.setID(keyHolder.getKey().intValue());
				
				//销毁局部变量
				namedParameterJdbcTemplate = null;
				paramSource=null;
				pscFactory = null;
				keyHolder = null;
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
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#delete(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#update(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#findAll(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(LinkedHashMap<Integer,OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo)
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<OfficialArchivesType> pOfficialArchivesTypes=jdbcTemplate.query(SQL_SELECT,new OfficialArchivesTypeMapper());

				//返回查询结果
				if (pOfficialArchivesTypes.size() > 0)
				{
					for (OfficialArchivesType item : pOfficialArchivesTypes)
					{
						officialArchivesTypes.put(item.getID(), item);
//System.out.println("OfficialArchivesType.ID:"+item.getID());
					}
				}
//System.out.println("officialArchivesTypes.size()="+officialArchivesTypes.size());
				//销毁局部变量
				pOfficialArchivesTypes=null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#findByID(int, com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
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
				//SQL_SELECT_ByID = "SELECT * FROM DD_OfficialArchivesType WHERE ID=?";
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<OfficialArchivesType> pOfficialArchivesTypes=jdbcTemplate.query(SQL_SELECT_ByID,new OfficialArchivesTypeMapper(),pOfficialArchivesTypeID);

				//返回查询结果
				if (pOfficialArchivesTypes.size() > 0)
				{
					officialArchivesType.cloneFrom(pOfficialArchivesTypes.get(0));
				}
				
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

	/* (non-Javadoc)
	 * @see org.springframework.dao.support.DaoSupport#checkDaoConfig()
	 */
	@Override
	protected void checkDaoConfig() throws IllegalArgumentException
	{
		// TODO Auto-generated method stub

	}

}
