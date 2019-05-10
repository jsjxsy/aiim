package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import com.orifound.aiim.dal.dao.IUserRolesArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;


import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
public class UserRolesArchivesTypeDaoImpl extends JdbcDaoSupport implements IUserRolesArchivesTypeDao {
	
	/**
	 * 构造函数
	 */
	public UserRolesArchivesTypeDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserRolesArchivesTypeDaoImpl(DataSource dataSource) {
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
	private class UserRolesArchivesTypeMapper implements RowMapper<UserRolesArchivesType>
	{
		
		@Override
		public UserRolesArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
			
			return new UserRolesArchivesType(iD,rolesID,archivesTypeID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		}
	}
	/**
	 * 查询用户角色的档案类型权限表信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DDR_UserRoles_ArchivesType ";
	
	/**
	 * 插入用户角色的档案类型权限表信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO DDR_UserRoles_ArchivesType(RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives) VALUES(:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives)";
	
	/**
	 * 删除用户角色的档案类型权限表信息的SQL语句根据ID
	 */
	private final String SQL_DELETE = "DELETE FROM DDR_UserRoles_ArchivesType WHERE ID = ?";
	
	/**
	 * 根据角色编号查询的SQL语句
	 */
	private final String SQL_SELECT_By_RoleID = "SELECT * FROM DDR_UserRoles_ArchivesType WHERE RolesID = ?;";
	
	/**
	 * 删除指定角色下所有的档案类型权限表信息的SQL语句
	 */
	private final String SQL_DELETE_By_RoleID = "DELETE FROM DDR_UserRoles_ArchivesType WHERE RolesID = ?";
	
	@Override
	public boolean delete(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限的实体类对象非法为空!");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限的编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,pUserRolesArchivesType.getID());

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
	public boolean findAll(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
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
				List<UserRolesArchivesType> userRolesArchivesTypes = jdbcTemplate.query(SQL_SELECT_ALL, new UserRolesArchivesTypeMapper());
				if(userRolesArchivesTypes.size() > 0){
					userRolesArchivesTypes.addAll(pUserRolesArchivesTypes);
				}
				//销毁局部变量
				jdbcTemplate = null;
				userRolesArchivesTypes = null;
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
	public boolean findByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案分类权限表的实体类对象非法为空!");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType.getRolesID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案分类权限表的实体类对象的角色编号非法为空!");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType.getArchivesTypeID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案分类权限表的实体类对象的档案分类编号非法为空!");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//INSERT INTO DDR_UserRoles_ArchivesType(RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives) VALUES(:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("RolesID", pUserRolesArchivesType.getRolesID(), Types.INTEGER);
				sqlParameterSource.addValue("ArchivesTypeID", pUserRolesArchivesType.getArchivesTypeID(), Types.INTEGER);
				sqlParameterSource.addValue("RightFlag_ArchivesInfo", pUserRolesArchivesType.getRightFlag_ArchivesInfo(),Types.BIT);
				sqlParameterSource.addValue("RightFlag_AttachedFile", pUserRolesArchivesType.getRightFlag_AttachedFile(),Types.BIT);
				sqlParameterSource.addValue("RightFlag_PaperArchives", pUserRolesArchivesType.getRightFlag_PaperArchives(),Types.BIT);
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
	public boolean update(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByRoleID(int pRoleID, List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户档案分类权限表的实体类对象的角色编号非法为空!");
				}
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//INSERT INTO DDR_UserRoles_ArchivesType(RolesID,ArchivesTypeID,
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				 List<UserRolesArchivesType> userRolesArchivesTypes = jdbcTemplate.query(SQL_SELECT_By_RoleID, new UserRolesArchivesTypeMapper(), pRoleID);
				 if (userRolesArchivesTypes.size() > 0) {
					pUserRolesArchivesTypes.addAll(userRolesArchivesTypes);
				}
				userRolesArchivesTypes = null;
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
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色的档案类型权限的实体类,角色编号非法为空!");
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

	
}
