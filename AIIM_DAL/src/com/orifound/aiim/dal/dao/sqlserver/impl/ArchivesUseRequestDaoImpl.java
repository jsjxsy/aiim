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

import com.orifound.aiim.dal.dao.IArchivesUseRequestDao;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

public class ArchivesUseRequestDaoImpl extends JdbcDaoSupport implements IArchivesUseRequestDao {
	
	/**
	 * 构造函数
	 */
	public ArchivesUseRequestDaoImpl() {

	}

	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class ArchivesUseRequestMapper implements RowMapper<ArchivesUseRequest>
	{		
		@Override
		public ArchivesUseRequest mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			Date requestTime = rs.getTimestamp("RequestTime");
			String requestReason = rs.getString("RequestReason");
			String userDepartment = rs.getString("UserDepartment");
			int userID = rs.getInt("UserID");
			
			return new ArchivesUseRequest(iD,requestTime,requestReason,userDepartment,userID);
		}
	}


	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class ArchivesUseRequestUserInfoMapper implements RowMapper<ArchivesUseRequest>
	{		
		@Override
		public ArchivesUseRequest mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			//===========Request===========
			int iD = rs.getInt("ID");
			Date requestTime = rs.getTimestamp("RequestTime");
			String requestReason = rs.getString("RequestReason");
			String userDepartment = rs.getString("UserDepartment");
			//int userID = rs.getInt("UserID");
			//===========userInfo==============
			int userID = rs.getInt("UserID");
			String userName = rs.getString("UserName");
			String userPWD = rs.getString("UserPWD");
			String realName = rs.getString("RealName");
			int departmentID = rs.getInt("DepartmentID");
			int dutyID = rs.getInt("DutyID");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String iDCardNo = rs.getString("IDCardNo");
			String email = rs.getString("Email");
			String tel = rs.getString("Tel");
			String address = rs.getString("Address");
			boolean anonymouseFlag = rs.getBoolean("AnonymouseFlag");
			String pKI_CA = rs.getString("PKI_CA");
			boolean frozenFlag = rs.getBoolean("FrozenFlag");
			
			UserInfo userInfo = new UserInfo(userID,userName,userPWD,realName,departmentID,dutyID,iDCardTypeID,iDCardNo,email,tel,address,anonymouseFlag,pKI_CA,frozenFlag);
			ArchivesUseRequest archivesUseRequest = new ArchivesUseRequest(iD,requestTime,requestReason,userDepartment,userID);
			archivesUseRequest.setUserInfo(userInfo);
			return archivesUseRequest;
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
	// Table Name: ArchivesUseRequest
	// Columns List,Can Used in SELECT SQL: ID,RequestTime,RequestReason,UserDepartment,UserID
	// Columns List,Can Used in INSERT SQL: :ID,:RequestTime,:RequestReason,:UserDepartment,:UserID
	// Columns List,Can Used in UPDATE SQL: ID=:ID,RequestTime=:RequestTime,RequestReason=:RequestReason,UserDepartment=:UserDepartment,UserID=:UserID

	/**
	 * 添加利用申请登记信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUseRequest(RequestTime,RequestReason,UserDepartment,UserID) VALUES(:RequestTime,:RequestReason,:UserDepartment,:UserID)";
	
	@Override
	public boolean add(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
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
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				//:ID,:RequestTime,:RequestReason,:UserDepartment,:UserID
				paramSource.addValue("ID",archivesUseRequest.getID());
				paramSource.addValue("RequestTime",archivesUseRequest.getRequestTime());
				paramSource.addValue("RequestReason", archivesUseRequest.getRequestReason());
				paramSource.addValue("UserDepartment",archivesUseRequest.getUserDepartment());
				paramSource.addValue("UserID",archivesUseRequest.getUserID());	
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				archivesUseRequest.setID(keyHolder.getKey().intValue());
				

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
	public boolean delete(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * 根据条件查询利用申请单信息SQL语句
	 *///参数定义    %1$s:查询条件
	private final String SQL_SELECT_findByQueryCondition_COUNT = " SELECT COUNT(A.ID) FROM ArchivesUseRequest A,UserInfo B WHERE A.UserID = B.UserID %1$s ";

	/**
	 * 根据条件查询利用申请单信息SQL语句
	 *///参数定义    %1$s:查询条件; 
//	private final String SQL_SELECT_findByQueryCondition1 = " SELECT TOP 5 A.ID ,A.RequestReason ,A.RequestTime,A.UserDepartment, B.* " +
//			" FROM ArchivesUseRequest A,UserInfo B WHERE A.UserID = B.UserID %1$s " +
//			" AND A.ID>  "+
//			" ORDER BY A.UserID ASC,A.RequestTime DESC";
	//参数定义    %1$s:查询条件; %2$s:每页显示记录数; %3$s:翻页前已经显示过的记录数;
	private final String SQL_SELECT_findByQueryCondition =
		" SELECT TOP %2$s A.ID ,A.RequestReason ,A.RequestTime,A.UserDepartment, B.* "+ 
		" FROM ArchivesUseRequest A,UserInfo B  "+
		" WHERE A.UserID = B.UserID %1$s "+ 
		" AND A.ID> "+
		" (  "+
		" SELECT ISNULL(MAX(ID),0) "+ 
		" FROM "+ 
			" ( "+
			" SELECT TOP %3$s A.ID from ArchivesUseRequest A,UserInfo B WHERE A.UserID = B.UserID %1$s ORDER BY A.ID ASC,A.UserID, A.RequestTime DESC "+
			" ) T "+
		" ) "+
		" ORDER BY A.ID ASC,A.UserID, A.RequestTime DESC ";

	@Override
	public boolean findByQueryCondition(String querySQL, DataPageInfo dataPageInfo, List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo) {
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
				//获取总记录数
				String localSQL = String.format(SQL_SELECT_findByQueryCondition_COUNT, querySQL);
				int pRowCount = jdbcTemplate.queryForInt(localSQL);
				dataPageInfo.setRowCount(pRowCount);
				
				//获取记录信息
				localSQL = String.format(SQL_SELECT_findByQueryCondition, querySQL,dataPageInfo.getPageSize(),(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				List<ArchivesUseRequest> pEntitys=jdbcTemplate.query(localSQL,new ArchivesUseRequestUserInfoMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {	
					archivesUseRequests.addAll(pEntitys);
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
	public boolean findByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
