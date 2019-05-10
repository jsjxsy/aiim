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
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IUserRolesInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * 用户所属角色定义的DAO实现类
 * 
 */
public class UserRolesInfoDaoImpl extends JdbcDaoSupport implements IUserRolesInfoDao {

	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserRolesInfoMapper implements RowMapper<UserRolesInfo> {

		@Override
		public UserRolesInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int userID = rs.getInt("UserID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");

			return new UserRolesInfo(iD, rolesID, userID, name, remark);
		}
	}

	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserRolesInfoMapper2 implements RowMapper<UserRolesInfo> {

		@Override
		public UserRolesInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int userID = rs.getInt("UserID");
			String UserName = rs.getString("UserName");
			String RealName = rs.getString("RealName");
			String IDCardNo = rs.getString("IDCardNo");
			return new UserRolesInfo(iD, rolesID, userID, UserName, RealName, IDCardNo);
		}
	}

	/**
	 * 构造函数
	 */
	public UserRolesInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * 
	 * @param dataSource
	 *            数据源
	 */
	public UserRolesInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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
	 * 查询指定角色所属用户信息的SQL语句
	 */
	private final String SQL_SELECT_By_UserID = "SELECT A.ID,RolesID,UserID,Name,Remark FROM UserRolesInfo A,DD_UserRoles B WHERE UserID=? AND A.RolesID=B.ID ORDER BY B.ID";

	/**
	 * 查询指定用户所属角色信息的SQL语句
	 *
	 */
	private final String SQL_SELECT_By_RoleID = "SELECT ID,RolesID,B.UserID,UserName,RealName,IDCardNo FROM UserRolesInfo A,UserInfo B WHERE A.RolesID=? AND A.UserID=B.UserID ORDER BY B.UserID";

	/**
	 * 删除角色信息失败的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM UserRolesInfo WHERE ID = ?";

	
	/**
	 * 插入用户角色信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO UserRolesInfo(RolesID,UserID) VALUES(:RolesID,:UserID)";
	

	/**
	 * 查询角色用户信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT A.ID,RolesID,UserID,Name,Remark FROM UserRolesInfo A,DD_UserRoles B WHERE A.RolesID=B.ID ORDER BY B.ID";
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IUserRolesInfoDao#save(com.orifound.aiim.entity
	 * .UserRolesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRolesInfo userRolesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;


				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();

				sqlParameterSource.addValue("RolesID",userRolesInfo.getRolesID());
				sqlParameterSource.addValue("UserID",userRolesInfo.getUserID());
				namedParameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource);
				// 销毁局部变量
				namedParameterJdbcTemplate = null;

				sqlParameterSource = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IUserRolesInfoDao#delete(com.orifound.aiim.
	 * entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserRolesInfo userRolesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE, userRolesInfo.getID());
				// 销毁局部变量
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IUserRolesInfoDao#findByUserID(int,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 检查用户编号是否合法
			if (pFlag) {
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRolesInfo> pUserRolesInfos = jdbcTemplate.query(SQL_SELECT_By_UserID, new UserRolesInfoMapper(), pUserID);

				// 返回查询结果
				if (pUserRolesInfos.size() > 0) {
					userRolesInfos.addAll(pUserRolesInfos);
				}

				// 销毁局部变量
				pUserRolesInfos = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	@Override
	public boolean findAll(List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				 List<UserRolesInfo>
				 pUserRolesInfos=jdbcTemplate.query(SQL_SELECT_ALL,new
				 UserRolesInfoMapper());

				 //返回查询结果
				 if (pUserRolesInfos.size() > 0)
				 {
				 userRolesInfos.addAll(pUserRolesInfos);
				 }
				
				 //销毁局部变量
				 pUserRolesInfos=null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	@Override
	public boolean updateUserRole(UserRolesInfo userRolesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;
		
			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	@Override
	public boolean findByID(int pID, UserRolesInfo userRolesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;

			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	@Override
	public boolean findByRoleID(int pRoleID, List<UserRolesInfo> userRolesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 检查用户编号是否合法
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为0");
				}
			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRolesInfo> pUserRolesInfos = jdbcTemplate.query(SQL_SELECT_By_RoleID, new UserRolesInfoMapper2(), pRoleID);

				// 返回查询结果
				if (pUserRolesInfos.size() > 0) {
					userRolesInfos.addAll(pUserRolesInfos);
				}

				// 销毁局部变量
				pUserRolesInfos = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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


}