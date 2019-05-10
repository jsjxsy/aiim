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

import com.orifound.aiim.dal.dao.IUserRoleDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;

/**
 * 角色信息字典表的DAO实现(SQL Server 平台实现)
 *
 */
public class UserRoleDaoimpl extends JdbcDaoSupport implements IUserRoleDao {
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserRoleMapper implements RowMapper<UserRole>
	{
		
		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			int systemRolesFlag = rs.getInt("SystemRolesFlag");
			String remark = rs.getString("Remark");
			
			return new UserRole(iD,name,systemRolesFlag,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public UserRoleDaoimpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserRoleDaoimpl(DataSource dataSource) {
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
	
	/**
	 * 查询所有角色信息的SQL语句
	 * 
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_UserRoles  ORDER BY ID ASC";

	
	/**
	 * 插入角色字典表记录的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO DD_UserRoles(Name,SystemRolesFlag,Remark) VALUES(:Name,:SystemRolesFlag,:Remark)";
	
	/**
	 * 删除DD_UserRoles表的SQL语句
	 */
	private final String SQL_DELETE = "DELETE a FROM DD_UserRoles  u , DDR_UserRoles_ArchivesSecrecy  a WHERE u.ID = ? AND a.RolesID = u.ID "+
									" DELETE  t FROM DD_UserRoles  u , DDR_UserRoles_ArchivesType  t WHERE u.ID = ? AND t.RolesID = u.ID "+
									" DELETE  s FROM DD_UserRoles  u , DDR_UserRoles_SystemFeature s WHERE u.ID = ? AND s.RolesID = u.ID"+
									" DELETE  us FROM DD_UserRoles  u , UserRolesInfo  us WHERE u.ID = ? AND us.RolesID = u.ID"+
	                                " DELETE FROM DD_UserRoles WHERE ID = ?";
	
	/**
	 * 更新DD_UserRoles的SQL语句
	 */
	private final String SQL_UPDATE= "UPDATE DD_UserRoles SET Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark WHERE ID = :ID";
	
	/**
	 * 更具Id号查找userRole对象的SQL语句
	 */
	private final String SQL_SELECT_By_ID = "SELECT * FROM DD_UserRoles where ID = ?";
	
	
	/**
	 *查询未加入到用户角色信息列表中的角色SQL语句
	 */ 
	private final String SQL_SELECT_Useres_By_UserID = "SELECT ID,Name,SystemRolesFlag,Remark FROM DD_UserRoles  WHERE  ID NOT IN  (SELECT A.RolesID FROM UserRolesInfo A WHERE A.UserID = ?  ) ORDER BY ID";
	
	@Override
	public boolean delete(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
             
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("角色对象非法为空!");
			}
			
			if (userRole.getID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("角色编号非法为空@");
			}
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE, userRole.getID(),userRole.getID(),userRole.getID(),userRole.getID(),userRole.getID());
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<UserRole> userRoles, ErrInfo pErrInfo) {
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
				List<UserRole> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new UserRoleMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					userRoles.addAll(pEntitys);
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
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#findByID(int, com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, UserRole userRole, ErrInfo pErrInfo) {
		// 
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
				//"SELECT * FROM DD_UserRoles where ID = ?";
				List<UserRole> pUserRoles=jdbcTemplate.query(SQL_SELECT_By_ID,new UserRoleMapper(),pID);
				if (pUserRoles.size()>0)
				{
					//返回查询结果
					userRole.cloneFrom(pUserRoles.get(0));
				}
				
				//销毁局部变量
				pUserRoles=null;
				jdbcTemplate=null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#save(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("userRole对象Dao注入非法为空!");
			}
            
			if (userRole == null) {
				pFlag=false;
				pErrInfo.getContent().append("userRole对象非法为空!");
			}
			if (userRole.getName() == null || userRole.getName().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("角色名称非法为空!");
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
			   //INSERT INTO DD_UserRoles(Name,SystemRolesFlag,Remark) VALUES(:Name,:SystemRolesFlag,:Remark)
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Name", userRole.getName(),Types.VARCHAR);
				mapSqlParameterSource.addValue("SystemRolesFlag", Types.INTEGER);
				mapSqlParameterSource.addValue("Remark", userRole.getRemark(),Types.VARCHAR);
				namedParameterJdbcTemplate.update(SQL_INSERT, mapSqlParameterSource);
				//销毁局部变量
				namedParameterJdbcTemplate = null;
				mapSqlParameterSource = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#update(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(UserRole userRole, ErrInfo pErrInfo) {
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("userRole的Dao注入非法为空!");
			}
			
			if (userRole == null) {
				pFlag=false;
				pErrInfo.getContent().append("userRole对象非法为空!");
			}
			if (userRole.getName() == null || userRole.getName().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("角色名称非法为空!");
			}
	
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//UPDATE DD_UserRoles SET (Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark) WHERE ID=:ID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Name", userRole.getName());
				mapSqlParameterSource.addValue("SystemRolesFlag", userRole.getSystemRolesFlag().getEnumValue());
				mapSqlParameterSource.addValue("Remark", userRole.getRemark());
				mapSqlParameterSource.addValue("ID", userRole.getID());
				namedParameterJdbcTemplate.update(SQL_UPDATE, mapSqlParameterSource);

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				mapSqlParameterSource = null;
				
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
	
	


	/**
	 * 根据系统固有角色查询角色信息的SQL语句
	 */
	private final String SQL_SELECT_findBySystemRolesFlag = "SELECT * FROM DD_UserRoles WHERE SystemRolesFlag=? ORDER BY ID ASC";;
	
	@Override
	public boolean findBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles, ErrInfo pErrInfo) {
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
				List<UserRole> pEntitys=jdbcTemplate.query(SQL_SELECT_findBySystemRolesFlag,new UserRoleMapper(),systemRolesFlag);

				//返回查询结果
				if (pEntitys.size() > 0) {
					userRoles.addAll(pEntitys);
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
	public boolean findRolesNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "角色信息，JDBC数据源是依赖注入失败：");
			}

			// 检查用户编号是否合法
			if (pFlag) {
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为0");
				}
			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRole> puserRoles = jdbcTemplate.query(SQL_SELECT_Useres_By_UserID, new UserRoleMapper(), pUserID);

				// 返回查询结果
				if (puserRoles.size() > 0) {
					userRoles.addAll(puserRoles);
				}

				// 销毁局部变量
				puserRoles = null;
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
