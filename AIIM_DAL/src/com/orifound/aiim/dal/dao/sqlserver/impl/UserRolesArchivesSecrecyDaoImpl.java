package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IUserRolesArchivesSecrecyDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesSecrecy;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserRolesArchivesSecrecyDaoImpl extends JdbcDaoSupport implements IUserRolesArchivesSecrecyDao {
	
	/**
	 * 构造函数
	 */
	public UserRolesArchivesSecrecyDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserRolesArchivesSecrecyDaoImpl(DataSource dataSource) {
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
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserRolesArchivesSecrecyMapper implements RowMapper<UserRolesArchivesSecrecy>
	{
		
		@Override
		public UserRolesArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int secrecyID = rs.getInt("SecrecyID");
			
			return new UserRolesArchivesSecrecy(iD,rolesID,secrecyID);
		}
	}
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DDR_UserRoles_ArchivesSecrecy";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_By_RoleID = "SELECT * FROM DDR_UserRoles_ArchivesSecrecy WHERE RolesID = ?";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM DDR_UserRoles_ArchivesSecrecy where ID = ?";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_DELETE_By_RolesID = "DELETE FROM DDR_UserRoles_ArchivesSecrecy where RolesID = ?";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO DDR_UserRoles_ArchivesSecrecy(RolesID,SecrecyID) VALUES(:RolesID,:SecrecyID)";
	
	@Override
	public boolean delete(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}

			if (pFlag) {
				pErrPos = 2;
				if ( pUserRolesArchivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (pUserRolesArchivesSecrecy.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案密级权限的编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,pUserRolesArchivesSecrecy.getID());

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

	@Override
	public boolean findAll(List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRolesArchivesSecrecy> userRolesArchivesSecrecy = jdbcTemplate.query(SQL_SELECT_ALL, new UserRolesArchivesSecrecyMapper());
				if(userRolesArchivesSecrecy.size() > 0){
					userRolesArchivesSecrecy.addAll(pUserRolesArchivesSecrecys);
				}
				//销毁局部变量
				jdbcTemplate = null;
				userRolesArchivesSecrecy = null;
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

	@Override
	public boolean findByID(int pID, UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			pErrPos = 1;
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户系统功能权限表的JDBC数据源是否正确依赖注入失败:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (pUserRolesArchivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案密级权限的实体类对象非法为空!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (pUserRolesArchivesSecrecy.getRolesID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案密级权限的用户编号非法为空!");
				}
			}
			if (pFlag) {
				pErrPos = 4;
				if (pUserRolesArchivesSecrecy.getSecrecyID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案密级权限的档案密级编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 5;
				//INSERT INTO DDR_UserRoles_ArchivesSecrecy(RolesID,SecrecyID) VALUES(:RolesID,:SecrecyID)";
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("RolesID", pUserRolesArchivesSecrecy.getRolesID(), Types.INTEGER);
				sqlParameterSource.addValue("SecrecyID", pUserRolesArchivesSecrecy.getSecrecyID(), Types.INTEGER);

				parameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource);
				//销毁局部变量
				parameterJdbcTemplate = null;
				sqlParameterSource = null;
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

	@Override
	public boolean update(UserRolesArchivesSecrecy pUserRolesArchivesSecrecy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean findByRoleID(int pRoleID, List<UserRolesArchivesSecrecy> pUserRolesArchivesSecrecys, ErrInfo pErrInfo) {
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
				List<UserRolesArchivesSecrecy> pEntitys=jdbcTemplate.query(SQL_SELECT_By_RoleID,new UserRolesArchivesSecrecyMapper(),pRoleID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					pUserRolesArchivesSecrecys.addAll(pEntitys);
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

	@Override
	public boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_DELETE_By_RolesID,pRoleID);

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
