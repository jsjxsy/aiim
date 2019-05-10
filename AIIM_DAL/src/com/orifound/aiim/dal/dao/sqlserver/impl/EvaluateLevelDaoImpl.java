/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IEvaluateLevelDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateLevel;

/**
 * 考核等级字典表(DD_EvaluateLevel)的DAO实现类（SQL Server平台）
 * @author tyb
 *
 */
public class EvaluateLevelDaoImpl extends JdbcDaoSupport implements IEvaluateLevelDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class EvaluateLevelMapper implements RowMapper<EvaluateLevel>
	{
		
		@Override
		public EvaluateLevel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new EvaluateLevel(iD,name,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public EvaluateLevelDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public EvaluateLevelDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
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
	
	// Table Name: DD_EvaluateLevel
	// Columns List,Can Used in SELECT SQL: ID,Name,Remark
	// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
	// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark
	
	/**
	 * 插入考核等级信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO DD_EvaluateLevel(Name,Remark) VALUES(:Name,:Remark)";
	
	/**
	 * 查询所有考核等级信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_EvaluateLevel ORDER BY ID";
	
	/**
	 * 根据主键查询考核等级字典表的SQL语句
	 */
	private final String SQL_SELECT_ByID = "SELECT * FROM DD_EvaluateLevel WHERE ID=?";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#delete(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<EvaluateLevel> evaluateLevels, ErrInfo pErrInfo) {
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
				List<EvaluateLevel> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new EvaluateLevelMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					for(EvaluateLevel level : pEntitys) {
						evaluateLevels.add(level);
					}
				}

				//销毁局部变量
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#findByID(int, com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核等级信息是否为空
			if (pFlag) {
				if(evaluateLevel == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核等级信息非法为空。");
				}
			}
			
			//检查考核等级名称 是否为空
			if (pFlag) {
				if(evaluateLevel.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考核等级信息下ID 字段非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<EvaluateLevel> pEntitys=jdbcTemplate.query(SQL_SELECT_ByID,new EvaluateLevelMapper(),evaluateLevel.getID());

				//返回查询结果
				if (pEntitys.size() > 0) {
					evaluateLevel.cloneFrom(pEntitys.get(0));
				}

				//销毁局部变量
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#save(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核等级信息是否为空
			if (pFlag) {
				if(evaluateLevel == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核等级信息非法为空。");
				}
			}
			
			//检查考核等级名称 是否为空
			if (pFlag) {
				if(StringTool.checkNull(evaluateLevel.getName()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("考核等级信息下等级名称 字段非法为空。");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Name", evaluateLevel.getName(), Types.VARCHAR);
				paramSource.addValue("Remark", evaluateLevel.getRemark(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				//销毁变量
				paramSource = null;
				namedParameterJdbcTemplate = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#update(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}


