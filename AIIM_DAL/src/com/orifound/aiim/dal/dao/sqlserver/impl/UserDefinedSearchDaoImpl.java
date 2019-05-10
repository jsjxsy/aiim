/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
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

import com.orifound.aiim.dal.dao.IUserDefinedSearchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserDefinedSearch;

/**
 * 用户自定义条件查询数据访问实现类
 * @author Administrator
 *
 */
public class UserDefinedSearchDaoImpl extends JdbcDaoSupport implements IUserDefinedSearchDao {
	


	/**
	 * 查询结果集到实体类的映射器
	 * 
	 */
	private class UserDefinedSearchMapper implements RowMapper<UserDefinedSearch>
	{
		
		@Override
		public UserDefinedSearch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			String name = rs.getString("Name");
			String archivesIDString = rs.getString("ArchivesIDString");
			String whereString = rs.getString("WhereString");			
			return new UserDefinedSearch(iD,userID,name,archivesIDString,whereString);
		}
	}
	
	/**
	 * 构造函数
	 */
	public UserDefinedSearchDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public UserDefinedSearchDaoImpl(DataSource dataSource) {
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
	 * 删除用户自定义条件查询的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM UserDefinedSearch WHERE ID = ?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#delete(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {	
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
			
				if (pFlag) {
					jdbcTemplate.update(SQL_DELETE,userDefinedSearch.getID());
				}

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

	/**
	 * 根据自定义条件的唯一标识ID查询自定义条件对象的SQL语句
	 */
	private final String SQL_SELECT_By_ID = "SELECT * FROM UserDefinedSearch WHERE ID = ?";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#findUserDefinedSearchByID(int, com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
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
				List<UserDefinedSearch> userDefinedSearchs = jdbcTemplate.query(SQL_SELECT_By_ID,new UserDefinedSearchMapper(),userDefinedSearch.getID());

				//返回查询结果
				if (userDefinedSearchs.size() > 0) {
					userDefinedSearch.cloneFrom(userDefinedSearchs.get(0));
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

	/**
	 * 通过userID查询自定义条件查询的SQL语句
	 */
	private final String SQL_SELECT_By_userID = "SELECT * FROM UserDefinedSearch WHERE UserID = ?";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#findUserDefinedSearchsByUserID(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo) {

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
				List<UserDefinedSearch> searchs =jdbcTemplate.query(SQL_SELECT_By_userID,new UserDefinedSearchMapper(),userID);
System.out.println("DAL--->searchs:-->" + searchs.size());

				//返回查询结果
				if (searchs.size() > 0) {
					userDefinedSearchs.addAll(searchs);	
					
				}
System.out.println("DAL--->userDefinedSearchs:-->" + userDefinedSearchs.size());
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
	
	/**
	 * 新增用户自定义条件查询的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO UserDefinedSearch(UserID,Name,ArchivesIDString,WhereString) VALUES(:UserID,:Name,:ArchivesIDString,:WhereString)";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#save(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUserDefinedSearchs(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {

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
				//List<UserDefinedSearch> userDefinedSearchs = jdbcTemplate.query(SQL_DELETE,new UserDefinedSearchMapper(),userDefinedSeach.getID());

				// Table Name: UserDefinedSearch
				// Columns List,Can Used in SELECT SQL: ID,UserID,Name,ArchivesIDString,WhereString
				// Columns List,Can Used in INSERT SQL: :ID,:UserID,:Name,:ArchivesIDString,:WhereString
				// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,Name=:Name,ArchivesIDString=:ArchivesIDString,WhereString=:WhereString

				pErrPos = 2;	
				//执行SQL语句
				if (pFlag) {								
					NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
					MapSqlParameterSource paramSource=new MapSqlParameterSource();
					paramSource.addValue("UserID", userDefinedSearch.getUserID(),Types.INTEGER);
					paramSource.addValue("Name", userDefinedSearch.getName(),Types.VARCHAR);	
					paramSource.addValue("ArchivesIDString", userDefinedSearch.getArchivesIDString(),Types.VARCHAR);
					paramSource.addValue("WhereString", userDefinedSearch.getWhereString(),Types.VARCHAR);
					
				
					namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);					
					//销毁局部变量
					paramSource=null;
					namedParameterJdbcTemplate = null;
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
	 * 检查自定义条件名称是否存在
	 */
	private final String SQL_QUERY_CheckNameExist = "SELECT * FROM UserDefinedSearch WHERE Name = ? AND UserID = ? ";

	
	@Override
	public boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo){
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
				List<UserDefinedSearch> searchs =jdbcTemplate.query(SQL_QUERY_CheckNameExist,new UserDefinedSearchMapper(),userDefinedSearch.getName(),userDefinedSearch.getUserID());
System.out.println("DAL--->searchs:-->" + searchs.size());

				if(searchs.size()==0){//当没有记录时，给ID赋0
					userDefinedSearch.setID(0);
				}
				if(searchs.size()>0){
					userDefinedSearch.cloneFrom(searchs.get(0));
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
	
	
	
}
