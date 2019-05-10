/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IEvaluateDetailsDao;
import com.orifound.aiim.dal.dao.IEvaluateRegisterDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * 考核登记表 (EvaluateRegister)的DAO实现类（SQL Server平台）
 *
 */
public class EvaluateRegisterDaoImpl extends JdbcDaoSupport implements IEvaluateRegisterDao {
	
	@Autowired
	private IEvaluateDetailsDao evaluateDetailsDao;
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class EvaluateRegisterMapper implements RowMapper<EvaluateRegister>
	{
		public EvaluateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String years = rs.getString("Years");
			int userID = rs.getInt("UserID");
			int dutyID = rs.getInt("DutyID");
			int score = rs.getInt("Score");
			int levelID = rs.getInt("LevelID");
			String content = rs.getString("Content");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int checkerUserID = rs.getInt("CheckerUserID");
			int checkerDutyID = rs.getInt("CheckerDutyID");
			
			return new EvaluateRegister(iD,years,userID,dutyID,score,levelID,content,registerDate,checkerUserID,checkerDutyID);
		}
	}
	
	/**
	 * 查询结果集到实体显示类的映射器，该类可用于DAO实现类中
	 */
	private class EvaluateRegisterVOMapper implements RowMapper<EvaluateRegisterVO>
	{
		public EvaluateRegisterVO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String realName = rs.getString("RealName");
			String dutyName = rs.getString("DutyName");
			Integer score = rs.getInt("Score");
			String evaluateLevelName = rs.getString("EvaluateLevelName");
			String years = rs.getString("years");
			return new EvaluateRegisterVO(iD, realName, dutyName, score, evaluateLevelName, years);
		}
	}
	
	/**
	 * 构造函数
	 */
	public EvaluateRegisterDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public EvaluateRegisterDaoImpl(DataSource dataSource) {
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
	
	// Table Name: EvaluateRegister
	// Columns List,Can Used in SELECT SQL: ID,Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID
	// Columns List,Can Used in INSERT SQL: :ID,:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID
	// Columns List,Can Used in UPDATE SQL: ID=:ID,Years=:Years,UserID=:UserID,DutyID=:DutyID,Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID

	/**
	 * 插入保存新的考核等级信息的SQL语句
	 */
//	private final String SQL_INSERT = "INSERT INTO EvaluateRegister(Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID) VALUES(:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID)";
	private final String SQL_INSERT = "INSERT INTO EvaluateRegister(Years,UserID,DutyID) VALUES(:Years,:UserID,:DutyID)";
	
	/**
	 * 通过主键查询考核登记信息的SQL语句
	 */
	private final String SQL_SELECT_ByID = "SELECT * FROM EvaluateRegister WHERE ID=?";
	
	/**
	 * 更新考核登记信息的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE EvaluateRegister SET Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID WHERE ID=:ID";
	
	/**
	 * 查询已经通过考核的最大年度的考核记录的SQL语句
	 */
	private final String SQL_SELECT_BYMAXYEAR = "SELECT MAX(Years) maxYear FROM EvaluateRegister WHERE RegisterDate IS NOT NULL";
	
	/**
	 * 查询考核记录最大年度的SQL语句
	 */
	private final String SQL_SELECT_MAXYEAR = "SELECT MAX(Years) maxYear FROM EvaluateRegister";
	
//	/**
//	 * 分页查询特定年度的考核记录显示类的SQL语句
//	 */
//	private final String SQL_SELECT_BYYEAR_WITHPAGE = "SELECT ID,years, RealName, DutyName, Score, EvaluateLevelName FROM ( SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName,ROW_NUMBER() OVER (ORDER BY e.id ASC) RowNumber FROM EvaluateRegister e " +
//										" LEFT JOIN UserInfo u ON  e.UserID=u.UserID " +
//										" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
//										" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID WHERE e.Years=:years)t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY id ASC";
//	
	/**
	 * 查询特定年度的考核记录显示类的SQL语句
	 */
	private final String SQL_SELECT_BYYEAR_WITHNOTPAGE = "SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName FROM EvaluateRegister e " +
										" LEFT JOIN UserInfo u ON  e.UserID=u.UserID " +
										" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
										" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID WHERE e.Years=:years ORDER BY id ASC";
	
	
	/**
	 * 获取数据库中存在的考核记录年度的SQL语句
	 */
	private final String SQL_SELECT_EvaluatedYears = "SELECT Years FROM EvaluateRegister GROUP BY Years";
	
	/**
	 * 查询特定年度的考核记录总行数的SQL语句
	 */
	private final String SQL_SELECT_COUNT_BYYEAR = "SELECT COUNT(1) FROM EvaluateRegister WHERE Years=:years";
	
	/**
	 * 查询特定年度的考核记录显示类的SQL语句
	 */
	private final String SQL_SELECT_BYREGID = "SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName FROM EvaluateRegister e " +
										" LEFT JOIN UserInfo u ON  e.UserID=u.UserID " +
										" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
										" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID WHERE e.ID=:evaluateRegID";
	
	
	/**
	 * 插入特定年度的考核记录的SQL语句
	 * %1$s 年度参数
	 */
	private final String SQL_INSERT_BYYEAR = "INSERT INTO EvaluateRegister(Years,UserID,DutyID) " +
					"SELECT %1$s,u.UserID,u.DutyID FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
					"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
					"AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";
	
	/**
	 * 查询特定年度不在考核记录的人员id的SQL语句
	 */
	private final String SQL_SELECT_NOTIN = "SELECT u.UserID FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
						"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
						"AND u.UserID not in(SELECT UserID FROM EvaluateRegister WHERE Years=%1$s) "+
						"AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";
	
	/**
	 * 追加特定年度的考核记录的SQL语句
	 */
	private final String SQL_INSERT_APPEND_BYYEAR = "INSERT INTO EvaluateRegister(Years,UserID,DutyID) " +
					"SELECT %1$s,u.UserID,u.DutyID FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
					"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
					"AND u.UserID IN(:userIds) AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";
	
	/**
	 * 追加特定年度的考核记明细的SQL语句
	 */
	private final String SQL_INSERT_DETAILS_APPEND_BYYEAR = "INSERT INTO EvaluateDetails(EvaluateRegID,EvaluateItemID) SELECT e.ID,i.ID FROM EvaluateRegister e,DD_EvaluateItem i WHERE e.UserID IN(:userIds) AND e.Years=:Years";
	
	/**
	 * 分页检索考核登记信息的SQL语句
	 * %1$s 考核人姓名
	 * %2$s 检索条件拼接
	 */
	private final String SQL_SEARCH_EVALUATE_WITHPAGE = "SELECT ID,years, RealName, DutyName, Score, EvaluateLevelName FROM ( " +
			"SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName,ROW_NUMBER() OVER (ORDER BY e.id ASC) RowNumber FROM EvaluateRegister e " +
			" LEFT JOIN UserInfo u ON  e.UserID=u.UserID AND u.UserName like '%1$s'" +
			" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
			" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID %2$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY id ASC";
	
	/**
	 * 不分页检索考核登记信息的SQL语句
	 * %1$s 考核人姓名
	 * %2$s 检索条件拼接
	 */
//	private final String SQL_SEARCH_EVALUATE_WITHNOPAGE1 = "SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName FROM EvaluateRegister e " +
//						" LEFT JOIN UserInfo u ON  e.UserID=u.UserID AND u.RealName like '%1$s'" +
//						" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
//						" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID %2$s ORDER BY id ASC";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SEARCH_EVALUATE_WITHNOPAGE = "SELECT t.*,el.Name EvaluateLevelName FROM DD_EvaluateLevel el"
				+" RIGHT JOIN (SELECT e.ID,e.years,e.LevelID,u.RealName RealName,d.Name DutyName,e.Score Score"
				+" FROM EvaluateRegister e, UserInfo u, DD_Duty d"
				+" WHERE e.UserID=u.UserID AND e.DutyID=d.ID  AND e.years=?) t ON t.LevelID=el.ID ORDER BY t.id ASC";
			
	/**
	 * 查询总记录数的SQL语句
	 */
	private final String SQL_SELECT_COUNT = "SELECT count(1) EvaluateLevelName FROM EvaluateRegister e " +
			" LEFT JOIN UserInfo u ON  e.UserID=u.UserID AND u.UserName like '%1$s'" +
			" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
			" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID %2$s";
	
	/**
	 * 批量删除考核登记信息的SQL语句
	 */
	private final String SQL_DELETE_BATCH = "DELETE FROM EvaluateDetails WHERE EvaluateRegID IN(:EvaluateRegIDS);DELETE FROM EvaluateRegister WHERE ID IN(:EvaluateRegIDS)";
	
	/**
	 * 判断是否需要追加人员
	 * %1$s 年度参数
	 */
	private final String SQL_isAppend = "SELECT COUNT(1) FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
					"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
					"AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1 AND u.UserID NOT IN(SELECT DISTINCT UserID FROM EvaluateRegister WHERE Years=?)";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#delete(com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean delete(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findAll(List<EvaluateRegister> evaluateRegisters, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#findByID(int, com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findByID(int pID, EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查考核登记信息是否为空
			if (pFlag) {
				if(evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息非法为空。");
				}
			}
			
			//检查主键是否为空
			if (pFlag) {
				if(pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息主键非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<EvaluateRegister> pEntitys=jdbcTemplate.query(SQL_SELECT_ByID,new EvaluateRegisterMapper(),pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					evaluateRegister.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#save(com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean save(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查考核登记信息是否为空
			if (pFlag) {
				if(evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息非法为空。");
				}
			}
			
			//检查年度是否为空
			if (pFlag) {
				if(StringTool.checkNull(evaluateRegister.getYears()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息下的考核年度字段非法为空。");
				}
			}
			
			//检查被考核人编号 是否为空
			if (pFlag) {
				if(evaluateRegister.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息下的被考核人编号字段非法为空。");
				}
			}
			
			//检查被考核人职务编号 是否为空
			if (pFlag) {
				if(evaluateRegister.getDutyID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息下的被考核人职务编号字段非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Years", evaluateRegister.getYears(), Types.VARCHAR);
				paramSource.addValue("UserID", evaluateRegister.getUserID(), Types.INTEGER);
				paramSource.addValue("DutyID", evaluateRegister.getDutyID(), Types.INTEGER);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);

				//销毁局部变量
				paramSource=null;
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#update(com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean update(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查考核信息是否为空
			if (pFlag) {
				if(evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息非法为空。");
				}
			}
			
			//检查考核信息ID是否为空
			if (pFlag) {
				if(evaluateRegister.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("考核登记信息下ID字段非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Score", evaluateRegister.getScore(), Types.INTEGER);
				paramSource.addValue("LevelID", evaluateRegister.getLevelID(), Types.INTEGER);
				paramSource.addValue("Content", evaluateRegister.getContent(), Types.VARCHAR);
				paramSource.addValue("RegisterDate", evaluateRegister.getRegisterDate(), Types.TIMESTAMP);
				paramSource.addValue("CheckerUserID", evaluateRegister.getCheckerUserID(), Types.INTEGER);
				paramSource.addValue("CheckerDutyID", evaluateRegister.getCheckerDutyID(), Types.INTEGER);
				paramSource.addValue("ID", evaluateRegister.getID(), Types.INTEGER);
				
				pErrPos = 3;
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
	
	public boolean findByMaxYear(DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核登记信息显示类集合 是否为空
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息显示类集合非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> resutls = jdbcTemplate.queryForList(SQL_SELECT_BYMAXYEAR);
				
				if(resutls!=null && resutls.size() >= 1) {
					Object maxYearObj = resutls.get(0).get("maxYear");
					if (maxYearObj != null) {
						System.out.println("经通过考核的最大年度="+maxYearObj);
						
						if (findByYear(maxYearObj.toString(), dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "分页查询特定年度的考核记录显示类 失败：");
						}
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
	
	@Override
	public boolean findByYear(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		return findByYear(year, null, evaluateRegisterVOs, pErrInfo);
	}
	
	public boolean findByYear(String year, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
			}
			
			//检查 年度是否为纯数字
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度不是纯数字非法。");
				}
			}
			
			//检查 考核登记信息显示类集合 是否为空
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息显示类集合非法为零。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("years", year, Types.VARCHAR);
				
				List<EvaluateRegisterVO> pEntitys = null;
				pEntitys=namedParameterJdbcTemplate.query(SQL_SELECT_BYYEAR_WITHNOTPAGE, mapSqlParameterSource, new EvaluateRegisterVOMapper());	

				if (pEntitys.size() >= 1) {
					evaluateRegisterVOs.addAll(pEntitys);
				}
				
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
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
			}
			
			//判断year参数是否为数字
			if (pFlag) {
				if(year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入年份参数不是数字非法。");
				}
			}
			
			//判断是否已经存在记录
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("years", year, Types.VARCHAR);
				int count = namedParameterJdbcTemplate.queryForInt(SQL_SELECT_COUNT_BYYEAR, mapSqlParameterSource);
				if(count >= 1) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "已经存在考核登记信息 插入失败：");
				}
			}

			//执行SQL语句
			if (pFlag) {
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				pErrPos = 3;
				jdbcTemplate.update(String.format(SQL_INSERT_BYYEAR, year));
				
				System.out.println("插入特定年度的考核记录："+String.format(SQL_INSERT_BYYEAR, year));

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
	
	@Override
	public boolean findByEvaluateRegID(int evaluateRegID, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("evaluateRegID", evaluateRegID, Types.INTEGER);

				List<EvaluateRegisterVO> evaluateRegisterVOs = namedParameterJdbcTemplate.query(SQL_SELECT_BYREGID, 
							paramSource, new EvaluateRegisterVOMapper());
				
				//判断是否存在结果
				if (evaluateRegisterVOs.size() >= 1) {
					evaluateRegisterVO.cloneFrom(evaluateRegisterVOs.get(0));
					
					//查询考核登记信息
					EvaluateRegister evaluateRegister = new EvaluateRegister();
					if (findByID(evaluateRegID, evaluateRegister , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "查询考核登记信息 失败：");
					}
					
					//检查 考核信息是否为空
					if (evaluateRegister.getID() >= 0) {
						evaluateRegisterVO.setEvaluateRegister(evaluateRegister);
						
						//查询考核明细信息
						List<EvaluateDetails> evaluateDetailss = new ArrayList<EvaluateDetails>();
						if (evaluateDetailsDao.findByEvaluateRegID(evaluateRegID, evaluateDetailss , pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "查询考核明细信息 失败：");
						}
						
						//插入考核详细信息
						if (evaluateDetailss.size() >= 1) {
							evaluateRegisterVO.setDetails(evaluateDetailss);
						}
					}
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

	@Override
	public boolean insertAppendByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 年度是否为空
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核年度非法为零。");
				}
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> results = jdbcTemplate.queryForList(String.format(SQL_SELECT_NOTIN, year));
				List<Integer> userIds = null;
				if(results.size() >= 1) {
					userIds = new ArrayList<Integer>();
					for(Map<String, Object> map : results) {
						if(map.get("UserID") != null) {
							userIds.add(Integer.valueOf(map.get("UserID").toString()));
						}
					}
				}
				
				if(userIds!=null && userIds.size()>=1) {
					NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
					MapSqlParameterSource parameterSource = new MapSqlParameterSource();
					parameterSource.addValue("userIds", userIds);
					//追加特定年度的考核记录
					namedParameterJdbcTemplate.update(String.format(SQL_INSERT_APPEND_BYYEAR, year),parameterSource);
					
					//追加特定年度的考核明细
					parameterSource.addValue("Years", year);
					namedParameterJdbcTemplate.update(SQL_INSERT_DETAILS_APPEND_BYYEAR,parameterSource);
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

	@Override
	public boolean search(String evaluateName, int dutyId, String registerDate,
			int minScore, int maxScore, DataPageInfo dataPageInfo,
			List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//判断最小分数是否大于最大分数
			if (minScore>=1 && maxScore>=1 && minScore > maxScore) {
				pFlag = false;
				pErrInfo.getContent().append("传入参数->最小分数是否大于最大分数。");
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				String searchName = "%";
				//拼接SQL语句
				StringBuffer bufferSQL = new StringBuffer(" WHERE e.UserID>=1 ");
				
				//判断考核人姓名是否为空
				if (StringTool.checkNull(evaluateName)) {
					searchName = searchName+evaluateName+searchName;
				}
				
				//判断 职务为位空
				if (dutyId >= 1) {
					bufferSQL.append(" AND e.DutyID=").append(dutyId);
				}
				
				//判断 考核年度是否为空
				if (StringTool.checkNull(registerDate) && registerDate.length()>=4) {
					bufferSQL.append(" AND e.Years='").append(registerDate.substring(0, 4)).append("'");
				}
				
				//判断最小分数是否为0
				if (minScore >= 1) {
					bufferSQL.append(" AND ISNULL(e.Score,0)>=").append(minScore);
				}
				
				//判断最大分数是否为0
				if (maxScore >= 1) {
					bufferSQL.append(" AND ISNULL(e.Score,0)<=").append(maxScore);
				}
				
				String localSQL = null;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				
				List<EvaluateRegisterVO> pEntitys = null;
				if(dataPageInfo == null || dataPageInfo.getPageSize() <= 0) {	//不支持分页查询
					localSQL = String.format(SQL_SEARCH_EVALUATE_WITHNOPAGE, searchName, bufferSQL.toString());
					pEntitys=namedParameterJdbcTemplate.query(localSQL, mapSqlParameterSource, new EvaluateRegisterVOMapper());	
				} else {	//支持分页查询
					localSQL = String.format(SQL_SEARCH_EVALUATE_WITHPAGE, searchName, bufferSQL.toString());
					mapSqlParameterSource.addValue("beginRow", dataPageInfo.getBeginRow(), Types.INTEGER);
					mapSqlParameterSource.addValue("endRow", dataPageInfo.getEndRow(), Types.INTEGER);
					pEntitys=namedParameterJdbcTemplate.query(localSQL, mapSqlParameterSource, new EvaluateRegisterVOMapper());
				}
				
				System.out.println("localSQL="+localSQL);
				
				if(pEntitys!=null && pEntitys.size() >= 1) {
					evaluateRegisterVOs.addAll(pEntitys);
					
					//查询总记录数
					mapSqlParameterSource = new MapSqlParameterSource();
					localSQL = String.format(SQL_SELECT_COUNT, searchName, bufferSQL.toString());
					dataPageInfo.setRowCount(namedParameterJdbcTemplate.queryForInt(localSQL, mapSqlParameterSource));
				}
				
				//销毁局部变量
				pEntitys = null;
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

	@Override
	public boolean deleteBatch(List<Integer> evaluateIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查 考核登记信息id集合是否为空
			if (pFlag) {
				if (evaluateIds==null || evaluateIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->考核登记信息id集合非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("EvaluateRegIDS", evaluateIds);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_DELETE_BATCH, paramSource);
				

				//销毁局部变量
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

	@Override
	public boolean findCountByYear(String year, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
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
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("years", year);
				evaluateRegisterVO.setEvaluateCount(namedParameterJdbcTemplate.queryForInt(SQL_SELECT_COUNT_BYYEAR, parameterSource));
				//销毁局部变量
				parameterSource = null;
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

	@Override
	public boolean findEvaluatedYears(List<String> evaluatedYears, ErrInfo pErrInfo) {
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
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_SELECT_EvaluatedYears);

				//返回查询结果
				if (pEntitys.size() > 0) {
					Object years = null;
					for(Map<String, Object> entity : pEntitys) {
						years = entity.get("Years");
						if(years != null) {
							evaluatedYears.add(years.toString());
						}
					}
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

	@Override
	public boolean search(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
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
				List<EvaluateRegisterVO> pEntitys=jdbcTemplate.query(SQL_SEARCH_EVALUATE_WITHNOPAGE,new EvaluateRegisterVOMapper(),year);
				
				System.out.println("根据考核年度检索考核登记信息="+SQL_SEARCH_EVALUATE_WITHNOPAGE);

				//返回查询结果
				if (pEntitys.size() > 0) {
					evaluateRegisterVOs.addAll(pEntitys);
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

	@Override
	public boolean findNeedAppend(String currentYear, Integer[] count, ErrInfo pErrInfo) {
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
				count[0] = jdbcTemplate.queryForInt(SQL_isAppend, currentYear);
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

	@Override
	public boolean findMaxYear(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> resutls = jdbcTemplate.queryForList(SQL_SELECT_MAXYEAR);
				
				if(resutls!=null && resutls.size() >= 1) {
					Object maxYearObj = resutls.get(0).get("maxYear");
					if (maxYearObj != null) {
						evaluateRegister.setYears(maxYearObj.toString());
					}
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