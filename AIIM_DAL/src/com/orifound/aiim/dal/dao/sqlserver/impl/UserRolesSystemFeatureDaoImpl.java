package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IUserRolesSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesSystemFeature;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserRolesSystemFeatureDaoImpl extends JdbcDaoSupport implements IUserRolesSystemFeatureDao {
	
	/**
	 * 构造函数
	 */
	public UserRolesSystemFeatureDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserRolesSystemFeatureDaoImpl(DataSource dataSource) {
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
	 * 查询所有用户角色的系统功能权限信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DDR_UserRoles_SystemFeature ";
	
	/**
	 * 删除用户角色的系统功能权限信息的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM DDR_UserRoles_SystemFeature WHERE ID = ?";
	
	/**
	 * 删除用户角色的系统功能权限信息的SQL语句
	 */
	private final String SQL_DELETE_By_RoleID = "DELETE FROM DDR_UserRoles_SystemFeature WHERE RolesID = ?";
	
	
	/**
	 * 插入用户角色的系统功能权限信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO DDR_UserRoles_SystemFeature(RolesID,FeatureID) VALUES(:RolesID,:FeatureID)";
	
	/**
	 * 根据角色ID查询用户角色的系统功能权限信息的SQL语句
	 */
	private final String SQL_SELECT_By_RoleID = "SELECT * FROM DDR_UserRoles_SystemFeature   WHERE RolesID = ?;";
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class UserRolesSystemFeatureMapper implements RowMapper<UserRolesSystemFeature>
	{
		
		@Override
		public UserRolesSystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int featureID = rs.getInt("FeatureID");
			
			return new UserRolesSystemFeature(iD,rolesID,featureID);
		}
	}
	@Override
	public boolean delete(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "JDBC数据源是否正确依赖注入失败，检查JDBC数据源是否正确依赖注入:");
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限的对象非法为空！");
				}
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限的编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,pUserRolesSystemFeature.getID());
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
				pErrInfo.getContent().insert(0, "JDBC数据源是否正确依赖注入失败，检查JDBC数据源是否正确依赖注入:");
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限的对象,角色编号非法为空！");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_RoleID,pRoleID);
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
	public boolean findAll(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
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
				List<UserRolesSystemFeature> userRolesSystemFeatures = jdbcTemplate.query(SQL_SELECT_ALL,new UserRolesSystemFeatureMapper());

				//返回查询结果
				if (userRolesSystemFeatures.size() > 0) {
					pUserRolesSystemFeatures.addAll(userRolesSystemFeatures);
				}

				//销毁局部变量
				jdbcTemplate = null;
				userRolesSystemFeatures = null;
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
	public boolean findByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "JDBC数据源是否正确依赖注入失败，检查JDBC数据源是否正确依赖注入:");
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限的对象非法为空！");
				}
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature.getFeatureID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能权限的系统功能编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//INSERT INTO DDR_UserRoles_SystemFeature(RolesID,FeatureID) VALUES(:RolesID,:FeatureID)
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlSource=new MapSqlParameterSource();
				sqlSource.addValue("RolesID", pUserRolesSystemFeature.getRolesID(),Types.INTEGER);
				sqlSource.addValue("FeatureID",  pUserRolesSystemFeature.getFeatureID(),Types.INTEGER);
				namedParameterJdbcTemplate.update(SQL_INSERT, sqlSource);
				//销毁局部变量
				namedParameterJdbcTemplate = null;
				sqlSource = null;

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
	public boolean update(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByRoleID(int pRoleID, List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("用户角色的系统功能，角色编号非法为0！");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRolesSystemFeature> userRolesSystemFeatures = jdbcTemplate.query(SQL_SELECT_By_RoleID,new UserRolesSystemFeatureMapper(),pRoleID);

				//返回查询结果
				if (userRolesSystemFeatures.size() > 0) {
					pUserRolesSystemFeatures.addAll(userRolesSystemFeatures);
				}

				//销毁局部变量
				jdbcTemplate = null;
				userRolesSystemFeatures = null;
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
