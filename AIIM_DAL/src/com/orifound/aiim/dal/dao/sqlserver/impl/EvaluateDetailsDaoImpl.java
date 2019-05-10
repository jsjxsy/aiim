/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IEvaluateDetailsDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;

/**
 * 考核明细表(EvaluateDetails)的DAO实现类（SQL Server平台）
 *
 */
public class EvaluateDetailsDaoImpl extends JdbcDaoSupport implements IEvaluateDetailsDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class EvaluateDetailsMapper implements RowMapper<EvaluateDetails>
	{
		public EvaluateDetails mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int evaluateRegID = rs.getInt("EvaluateRegID");
			int evaluateItemID = rs.getInt("EvaluateItemID");
			int score = rs.getInt("Score");
			
			return new EvaluateDetails(evaluateRegID,evaluateItemID,score);
		}
	}
	
	/**
	 * 构造函数
	 */
	public EvaluateDetailsDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public EvaluateDetailsDaoImpl(DataSource dataSource) {
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
	// Table Name: EvaluateDetails
	// Columns List,Can Used in SELECT SQL: EvaluateRegID,EvaluateItemID,Score
	// Columns List,Can Used in INSERT SQL: :EvaluateRegID,:EvaluateItemID,:Score
	// Columns List,Can Used in UPDATE SQL: EvaluateRegID=:EvaluateRegID,EvaluateItemID=:EvaluateItemID,Score=:Score
	
	/**
	 * 插入考核明细情况信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO EvaluateDetails(EvaluateRegID,EvaluateItemID) VALUES(:EvaluateRegID,:EvaluateItemID)";
	
	/**
	 * 插入当前年度的考核明细情况信息的SQL语句
	 */
	private final String SQL_INSERT_BYYEAR = "INSERT INTO EvaluateDetails(EvaluateRegID,EvaluateItemID) SELECT e.ID,i.ID FROM EvaluateRegister e,DD_EvaluateItem i WHERE e.Years=:Years";
	
	/**
	 * 通过考核记录编号查询考核明细信息的SQL语句
	 */
	private final String SQL_FIND_ByEvaluateRegID = "SELECT ed.EvaluateRegID,ed.EvaluateItemID,de.Name,ed.Score,de.Score maxScore FROM EvaluateDetails ed,DD_EvaluateItem de WHERE ed.EvaluateItemID=de.ID AND EvaluateRegID=?";
	
	/**
	 * 跟新考评明细情况信息的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE EvaluateDetails SET Score=:Score WHERE EvaluateRegID=:EvaluateRegID AND EvaluateItemID=:EvaluateItemID";
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#delete(com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean delete(EvaluateDetails EvaluateDetails, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findAll(List<EvaluateDetails> evaluateDetails, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#findByID(int, com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findByEvaluateRegID(int evaluateRegID, List<EvaluateDetails> evaluateDetailss, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				if(evaluateDetailss == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核详细信息集合非法为空。");
				}
			}
			
			if (pFlag) {
				if(evaluateRegID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息主键非法为空。");
				}
			}
			

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_FIND_ByEvaluateRegID,evaluateRegID);

				//返回查询结果
				EvaluateDetails details = null;
				if (pEntitys.size() > 0) {
					for(Map<String, Object> pEntity : pEntitys) {
						int evaluateID = Integer.valueOf(pEntity.get("EvaluateRegID").toString());
						int evaluateItemID = Integer.valueOf(pEntity.get("EvaluateItemID").toString());
						int maxScore = Integer.valueOf(pEntity.get("maxScore").toString());
						String name = pEntity.get("Name").toString();
						int score = 0;
						Object scoreObj = pEntity.get("Score");
						//判断是否有分数
						if(scoreObj != null) {
							score = Integer.valueOf(scoreObj.toString());
						}
						details = new EvaluateDetails(evaluateID, evaluateItemID, score);
						details.setTag(name);
						details.setMaxScore(maxScore);
						evaluateDetailss.add(details);
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#save(com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean save(EvaluateDetails evaluateDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查考核记录编号是否为空
			if (pFlag) {
				if(evaluateDetails.getEvaluateRegID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考评明细情况下的考核记录编号字段非法为空。");
				}
			}
			
			//检查考核项编号是否为空
			if (pFlag) {
				if(evaluateDetails.getEvaluateItemID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考评明细情况下的考核项编号字段非法为空。");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("EvaluateRegID", evaluateDetails.getEvaluateRegID(), Types.INTEGER);
				paramSource.addValue("EvaluateItemID", evaluateDetails.getEvaluateItemID(), Types.INTEGER);

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
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#update(com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean update(EvaluateDetails evaluateDetails, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Score", evaluateDetails.getScore(), Types.INTEGER);
				paramSource.addValue("EvaluateRegID", evaluateDetails.getEvaluateRegID(), Types.INTEGER);
				paramSource.addValue("EvaluateItemID", evaluateDetails.getEvaluateItemID(), Types.INTEGER);

				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
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

	/**
	 * 插入特定年度的考核信息 测试
	 */
	@Override
	public boolean insertByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//判断year参数是否为数字
			if (pFlag) {
				if(year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入年份参数不是数字非法。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Years", year, Types.VARCHAR);
				
				namedParameterJdbcTemplate.update(SQL_INSERT_BYYEAR, mapSqlParameterSource);
				
				System.out.println("插入考核明细="+SQL_INSERT_BYYEAR);

				//销毁局部变量
				mapSqlParameterSource = null;
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
}
