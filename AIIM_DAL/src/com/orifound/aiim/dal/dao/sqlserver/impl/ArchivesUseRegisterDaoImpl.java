/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import com.orifound.aiim.dal.dao.IArchivesUseRegisterDao;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用登记表的DAO接口的实现类
 * @author Administrator
 *
 */
public class ArchivesUseRegisterDaoImpl extends JdbcDaoSupport implements IArchivesUseRegisterDao {
	
	/**
	 * 构造函数
	 */
	public ArchivesUseRegisterDaoImpl() {

	}	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesUseRegisterMapper implements RowMapper<ArchivesUseRegister>
	{
		
		@Override
		public ArchivesUseRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int purposeID = rs.getInt("PurposeID");
			int usePersonsCount = rs.getInt("UsePersonsCount");
			int useArchivesCount = rs.getInt("UseArchivesCount");
			Date useDate = rs.getTimestamp("UseDate");
			int usePersonID = rs.getInt("UsePersonID");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			///usePersonInfo			
			String name = rs.getString("Name");
			String iDCardNo = rs.getString("IDCardNo");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String department = rs.getString("Department");
			String tel = rs.getString("Tel");
			String email = rs.getString("Email");
			int areaID = rs.getInt("AreaFlag");
			//构造利用人信息
			ArchivesUsePersonInfo archivesUsePersonInfo = new ArchivesUsePersonInfo(usePersonID, name, iDCardNo, iDCardTypeID, department, tel, email, areaID);
			
			ArchivesUseRegister archivesUseRegister  = new ArchivesUseRegister(iD,borrowFlag,purposeID,usePersonsCount,useArchivesCount,useDate,usePersonID,managerUserID,remark);
			archivesUseRegister.setArchivesUsePersonInfo(archivesUsePersonInfo);
			return archivesUseRegister;
		}
	}

	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中;注：没有映射利用人信息
	 * 
	 */
	@SuppressWarnings("unused")
	private class ArchivesUseRegisterMapperSmall implements RowMapper<ArchivesUseRegister>
	{
		
		@Override
		public ArchivesUseRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int purposeID = rs.getInt("PurposeID");
			int usePersonsCount = rs.getInt("UsePersonsCount");
			int useArchivesCount = rs.getInt("UseArchivesCount");
			Date useDate = rs.getTimestamp("UseDate");
			int usePersonID = rs.getInt("UsePersonID");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new ArchivesUseRegister(iD,borrowFlag,purposeID,usePersonsCount,useArchivesCount,useDate,usePersonID,managerUserID,remark);
		}
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
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseRegisterDao#delete(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * 根据条件查询利用登记信息的SQL语句
	 * select A.*,B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel  from ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID
	 */
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseRegisterDao#findByConditions(java.lang.String, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByConditions(String querySQL, DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//%1$s：查询条件
			String SQL_SELECT_COUNT_By_Conditions = "SELECT COUNT(*) FROM ArchivesUseRegister A,ArchivesUsePersonInfo B WHERE A.UsePersonID = B.ID %1$s AND EXISTS (SELECT UseRegID from ArchivesUseOutInfo C  where C.UseRegID = A.ID) ";
//select A.*,B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel  from ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID
			//执行SQL语句,统计结果数量
			if (pFlag) {
				pErrPos = 2;
				String localSql = String.format(SQL_SELECT_COUNT_By_Conditions,querySQL);	
				int rowCount = jdbcTemplate.queryForInt(localSql);
				dataPageInfo.setRowCount(rowCount);
System.out.println("localSql:"+localSql);
			}
			// %1$s：查询条件； %2$s:页大小
//String SQL_SELECT_findByConditions = "select A.*,B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel  from ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID  %1$s";
//			String SQL_SELECT_findByConditions = "SELECT TOP %2$s A.*,B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel  from ArchivesUseRegister A,ArchivesUsePersonInfo B " +
//					       "where A.UsePersonID = B.ID  %1$s ";
//SELECT TOP %3$s * FROM  %1$s WHERE ParentNBXH=0 AND NBXH IN 
//" AND NBXH > (SELECT ISNULL(MAX(NBXH),0) FROM (SELECT TOP %4$s NBXH FROM %1$s WHERE NBXH IN "
			// %1$s：查询条件； %2$s:页大小；%3$s：已经读取过的记录数量
//			String SQL_SELECT_findByConditions = "SELECT TOP %2$s A.* , B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel " +
//					"FROM ArchivesUseRegister A ,ArchivesUsePersonInfo B WHERE A.UsePersonID = B.ID %1$s AND A.ID < " +
//					"(SELECT ISNULL(MIN(ID),2147483647) FROM " +
//					"( SELECT TOP %3$s C.ID FROM ArchivesUseRegister C,ArchivesUsePersonInfo D WHERE C.UsePersonID=D.ID %1$s  ORDER BY ID DESC) AS T " +
//					")ORDER BY ID DESC";
			String SQL_SELECT_findByConditions = "SELECT TOP %2$s A.* , B.AreaFlag,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel " +
			"FROM ArchivesUseRegister A ,ArchivesUsePersonInfo B WHERE A.UsePersonID = B.ID %1$s  AND EXISTS (SELECT UseRegID FROM ArchivesUseOutInfo C  where C.UseRegID = A.ID) AND A.ID < " +
			"(SELECT ISNULL(MIN(ID),2147483647) FROM " +
			"( SELECT TOP %3$s C.ID FROM ArchivesUseRegister C,ArchivesUsePersonInfo D WHERE C.UsePersonID=D.ID %1$s AND EXISTS (SELECT UseRegID FROM ArchivesUseOutInfo C  where C.UseRegID = A.ID)  ORDER BY ID DESC) AS T " +
			")ORDER BY ID DESC";
			//执行SQL语句,查询利用登记信息
			if (pFlag) {
				pErrPos = 3;
				String localSql = String.format(SQL_SELECT_findByConditions,querySQL,dataPageInfo.getPageSize(),(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());	
				List<ArchivesUseRegister> useRegisters=jdbcTemplate.query(localSql,new ArchivesUseRegisterMapper());

				//返回查询结果
				if (useRegisters.size() > 0) {
					archivesUseRegisters.addAll(useRegisters);
				}
			}
			//销毁局部变量
			jdbcTemplate = null;
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
	// Table Name: ArchivesUseRegister
	// Columns List,Can Used in SELECT SQL: ID,BorrowFlag,PurposeID,UsePersonsCount,UseArchivesCount,UseDate,UsePersonID,ManagerUserID,Remark
	// Columns List,Can Used in INSERT SQL: :ID,:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark
	// Columns List,Can Used in UPDATE SQL: ID=:ID,BorrowFlag=:BorrowFlag,PurposeID=:PurposeID,UsePersonsCount=:UsePersonsCount,UseArchivesCount=:UseArchivesCount,UseDate=:UseDate,UsePersonID=:UsePersonID,ManagerUserID=:ManagerUserID,Remark=:Remark
	/**
	 * 根据利用编号查询档案利用登记信息的SQL语句
	 */
	private final String SQL_SELECT_findByID = "SELECT  A.* , B.AreaFlag,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel " +
			"  FROM ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID AND A.ID = ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseRegisterDao#findByID(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo) {
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
				List<ArchivesUseRegister> useRegisters = jdbcTemplate.query(SQL_SELECT_findByID,new ArchivesUseRegisterMapper(),archivesUseRegister.getID());

				//返回查询结果
				if (useRegisters.size() > 0) {
					archivesUseRegister.cloneFrom(useRegisters.get(0));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("通过唯一标识没有找到指定的利用登记信息！");
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
	 * 添加档案利用登记信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUseRegister(BorrowFlag,PurposeID,UsePersonsCount,UseArchivesCount,UseDate,UsePersonID,ManagerUserID,Remark) " +
									"VALUES(:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark)";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseRegisterDao#save(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean add(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句,添加档案利用登记信息
			if (pFlag) {
				pErrPos = 2;
//"VALUES(:BorrowFlag,:PurposeID,:UsePersonsCount,:UseArchivesCount,:UseDate,:UsePersonID,:ManagerUserID,:Remark)";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				
				paramSource.addValue("BorrowFlag", archivesUseRegister.getBorrowFlag());
				paramSource.addValue("PurposeID", archivesUseRegister.getPurposeID());
				paramSource.addValue("UsePersonsCount", archivesUseRegister.getUsePersonsCount());
				paramSource.addValue("UseArchivesCount", archivesUseRegister.getUseArchivesCount());
				paramSource.addValue("UseDate", archivesUseRegister.getUseDate());
				paramSource.addValue("UsePersonID", archivesUseRegister.getUsePersonID());
				paramSource.addValue("ManagerUserID", archivesUseRegister.getManagerUserID());
				paramSource.addValue("Remark", archivesUseRegister.getRemark());
				
		//		namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);
				archivesUseRegister.setID(keyHolder.getKey().intValue());
				
				//销毁局部变量
				keyHolder = null;
				paramSource=null;
				namedParameterJdbcTemplate=null;				
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
	 * @see com.orifound.aiim.dal.dao.IArchivesUseRegisterDao#update(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * 查询所有过期未归还档案的登记信息的SQL语句
	 */
	//?1:每页显示的记录数;?2:当前已显示的记录数
//	private  String SQL_SELECT_findAllExpiringRegister = "SELECT TOP  %1$s    A.* , B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel "+
//	" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID AND A.ID in (SELECT useRegid FROM ArchivesUseOutInfo  WHERE GETDATE()>ShouldReturnDate )"+
//	"  AND A.ID > ( "+
//	" SELECT ISNULL(MAX(T.ID),0) FROM ( " +   
//	"    SELECT TOP %2$s  E.ID FROM ArchivesUseRegister E,ArchivesUsePersonInfo F where E.UsePersonID = F.ID AND E.ID IN (select useRegid from ArchivesUseOutInfo  WHERE GETDATE()>ShouldReturnDate ) ORDER BY E.ID ASC "+
//	" ) AS T )ORDER BY ID ASC";
	/**
	 * 查询所有过期未归还档案的登记信息的SQL语句
	 */
	//%1$s:每页显示的记录数;%2$s:当前已显示的记录数%3$s:到期预警提前的天数
	private  String SQL_SELECT_findAllExpiringRegister = "SELECT TOP  %1$s    A.* , B.AreaFlag,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel "+
		" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID AND A.ID in (SELECT useRegid FROM ArchivesUseOutInfo  WHERE ShouldReturnDate < DATEADD(day,%3$s,GETDATE()) AND ShouldReturnDate>GETDATE())"+
		"  AND A.ID > ( "+
		" SELECT ISNULL(MAX(T.ID),0) FROM (  "+  
		"    SELECT TOP %2$s  E.ID FROM ArchivesUseRegister E,ArchivesUsePersonInfo F where E.UsePersonID = F.ID AND E.ID IN (select useRegid from ArchivesUseOutInfo  WHERE ShouldReturnDate < DATEADD(day,%3$s,GETDATE()) AND ShouldReturnDate>GETDATE() ) ORDER BY E.ID ASC "+
		" ) AS T )ORDER BY ID ASC ";
	
	@Override
	public boolean findAllExpiringRegister(int daysNum,DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo){
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
				String localSql = String.format(SQL_SELECT_findAllExpiringRegister,dataPageInfo.getPageSize(),(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize(),daysNum);	
				List<ArchivesUseRegister> useRegisters = jdbcTemplate.query(localSql,new ArchivesUseRegisterMapper());

				dataPageInfo.setRowCount(useRegisters.size());
				//返回查询结果
				if (useRegisters.size() > 0) {
					archivesUseRegisters.addAll(useRegisters);
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
	 * 查询所有过期未归还档案的登记信息的SQL语句
	 */
	//?1:每页显示的记录数;?2:当前已显示的记录数
	private  String SQL_SELECT_findAllExpiredRegister = "SELECT TOP  %1$s    A.* , B.AreaFlag,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel "+
	" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B where A.UsePersonID = B.ID AND A.ID in (SELECT useRegid FROM ArchivesUseOutInfo  WHERE BorrowFlag=1 AND GETDATE()>ShouldReturnDate )"+
	"  AND A.ID > ( "+
	" SELECT ISNULL(MAX(T.ID),0) FROM ( " +   
	"    SELECT TOP %2$s  E.ID FROM ArchivesUseRegister E,ArchivesUsePersonInfo F where E.UsePersonID = F.ID AND E.ID IN (select useRegid from ArchivesUseOutInfo  WHERE  BorrowFlag=1 AND GETDATE()>ShouldReturnDate ) ORDER BY E.ID ASC "+
	" ) AS T )ORDER BY ID ASC";
	
	@Override
	public boolean findAllExpiredRegister(DataPageInfo dataPageInfo, List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo){
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
				String localSql = String.format(SQL_SELECT_findAllExpiredRegister,dataPageInfo.getPageSize(),(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());	
				List<ArchivesUseRegister> useRegisters = jdbcTemplate.query(localSql,new ArchivesUseRegisterMapper());

				dataPageInfo.setRowCount(useRegisters.size());
				//返回查询结果
				if (useRegisters.size() > 0) {
					archivesUseRegisters.addAll(useRegisters);
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
