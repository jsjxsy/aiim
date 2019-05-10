/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IUserInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * 系统用户信息的DAO实现类（SQL Server平台）
 * 
 */
public class UserInfoDaoImpl extends JdbcDaoSupport implements IUserInfoDao {


	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserInfoMapper implements RowMapper<UserInfo> {

		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
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
			return new UserInfo(userID, userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 添加departmentName和dutyName的属性
	 */
	private class UserInfoMapper2 implements RowMapper<UserInfo> {

		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
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
			String departmentName = rs.getString("DepartmentName");
			String dutyName = rs.getString("DutyName");
			return new UserInfo(userID, userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag,departmentName,dutyName);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserInfoMapper3 implements RowMapper<UserInfo> {

		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			int ID = rs.getInt("ID");
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
			return new UserInfo(ID,userID, userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class UserInfoMapper4 implements RowMapper<UserInfo> {

		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			int userID = rs.getInt("UserID");
			String userName = rs.getString("UserName");
			String userPWD = rs.getString("UserPWD");
			String realName = rs.getString("RealName");
			int departmentID = rs.getInt("DepartmentID");
			String departmentName = rs.getString("DepartmentName");
			int dutyID = rs.getInt("DutyID");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String iDCardNo = rs.getString("IDCardNo");
			String email = rs.getString("Email");
			String tel = rs.getString("Tel");
			String address = rs.getString("Address");
			boolean anonymouseFlag = rs.getBoolean("AnonymouseFlag");
			String pKI_CA = rs.getString("PKI_CA");
			boolean frozenFlag = rs.getBoolean("FrozenFlag");
			return new UserInfo(userID, userName, userPWD, realName, departmentID,departmentName, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
		}
	}
	
	/**
	 * 构造函数
	 */
	public UserInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * 
	 * @param dataSource
	 *            数据源
	 */
	public UserInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * 查询用户的所有信息的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM UserInfo ORDER BY UserID DESC";

	/**
	 * Feature的SQL语句
	 *   
	 */
	private final String SQL_SELECT_By_Condition = "SELECT * FROM (SELECT  A.UserID,A.UserName,A.UserPWD,A.RealName,A.DepartmentID,B.Name AS DepartmentName,A.IDCardTypeID,A.DutyID,D.Name AS DutyName,A.IDCardNo,A.Email,A.Tel,A.Address,A.AnonymouseFlag,A.PKI_CA,A.FrozenFlag, "+
                                                  " ROW_NUMBER() OVER(ORDER BY A.UserID) rownumber FROM UserInfo  A LEFT JOIN DD_DepartmentInfo  B ON A.DepartmentID = B.ID  ,UserInfo  C  LEFT JOIN DD_Duty  D  ON C.DutyID=D.ID WHERE A.UserID = C.UserID) AS p WHERE  p.rownumber BETWEEN %1$s AND %2$s %3$s";
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_By_Condition_WithoutPage = "SELECT * FROM UserInfo";
	/**
	 * 查询用户个人的SQL语句
	 */
	private final String SQL_SELECT_COUNT_By_QueryConditions = "SELECT COUNT(UserID) FROM UserInfo";
	/**
	 * 根据用户名查找用户信息的SQL语句
	 */
	private final String SQL_SELECT_BYUSERNAME = "select u.UserID,u.UserName,u.UserPWD,u.RealName,u.DepartmentID,u.DutyID,u.IDCardTypeID,u.IDCardNo,u.Email,u.Tel,u.Address,u.AnonymouseFlag,u.PKI_CA,u.FrozenFlag,u.CreateDate,d.ID,d.Name as DepartmentName,d.SystemDepartmentFlag,d.Remark " +
			                                     "from UserInfo u,DD_DepartmentInfo d where d.ID=u.DepartmentID AND u.UserName=?";

	/**
	 * 查找匿名用户的SQL语句
	 */
	private final String SQL_SELECT_Anonymouse = "SELECT * FROM UserInfo WHERE AnonymouseFlag=1";

	/**
	 * 查询所有具有业务指导室角色的人员的SQL语句
	 */
	private final String SQL_SELECT_ALL_BUSINESSGUIDS = "SELECT u.UserID, u.RealName FROM DD_UserRoles r, UserRolesInfo ur, UserInfo u WHERE r.ID=ur.RolesID AND ur.UserID=u.UserID AND r.SystemRolesFlag=:SystemRolesFlag";

	/**
	 * 查询指定业务指导室指导的所有档案兼职人员的SQL语句
	 */
	private final String SQL_SELECT_BY_BUSINESSGUIDIDS = "SELECT du.UserID FROM UserInfo u,UserChargeDepartment cd, UserInfo du,UserRolesInfo ur,DD_UserRoles dur "
			+ "WHERE u.UserID=cd.UserID AND cd.DepartmentID=du.DepartmentID AND du.UserID=ur.UserID AND ur.RolesID=dur.ID AND u.UserID in(:businessGuidIds) AND dur.SystemRolesFlag=:SystemRolesFlag ";

	/**
	 * 查询档案管理部门的人员（除去馆长、副馆长）的SQL语句
	 */
	private final String SQL_SELECT_ARCHIVESMANAGERS = "SELECT u.* FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r "
			+ "WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " + "AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";

	/**
	 * 查询任务接收人集合的SQL语句
	 */
	private final String SQL_SELECT_TASKPERSONS = "SELECT u.* FROM UserInfo u,DD_DepartmentInfo d WHERE u.DepartmentID=d.ID AND d.SystemDepartmentFlag=1";

	/**
	 * 删除userInfo的SQL语句
	 */
	private final String sQL_DELETE_By_UserID = " DELETE  a FROM UserInfo u , UserRight_ArchivesSecrecy a WHERE u.UserID=? AND a.UserID = u.UserID "+
	                                           " DELETE  t FROM UserInfo u , UserRight_ArchivesType t WHERE u.UserID=? AND t.UserID = u.UserID"+
	                                           " DELETE  s FROM UserInfo u , UserRight_SystemFeature s WHERE u.UserID=? AND s.UserID = u.UserID"+
	                                           " DELETE l FROM UserInfo u ,LogInfo l WHERE u.UserID = ? AND l.UserID = u.UserID "+
	                                           " DELETE  ur FROM UserInfo u,UserRolesInfo ur WHERE u.UserID = ? AND u.UserID=ur.UserID"+
	                                           " DELETE  ucu  FROM UserInfo u,UserChargeUser ucu  WHERE u.UserID = ? AND ucu.UserID=u.UserID;"+
	                                           " DELETE  d  FROM UserInfo u,UserChargeDepartment  d  WHERE u.UserID = ? AND d.UserID=u.UserID;"+
	                                           " DELETE FROM UserInfo WHERE UserID = ? ";

	/**
	 * 更新userInfo的SQL语句
	 */
	private final String SQL_UPDATE_By_UserID = "UPDATE UserInfo SET UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag WHERE UserID = :UserID";
	
	/**
	 * 更新密码的SQL语句
	 */
	private final String SQL_UPDATE_Password = "UPDATE UserInfo SET UserPWD=:UserPWD WHERE UserID = :UserID";
	
	/**
	 * 更新userInfo的SQL语句
	 */
	private final String SQL_SELECT_By_UserID = "SELECT * FROM  UserInfo WHERE UserID = ?";


	/**
	 * 插入记录的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO UserInfo(UserName,UserPWD,RealName,DepartmentID,DutyID,IDCardTypeID,IDCardNo,Email,Tel,Address) VALUES(:UserName,:UserPWD,:RealName,:DepartmentID,:DutyID,:IDCardTypeID,:IDCardNo,:Email,:Tel,:Address)";
	
	/**
	 * 查询被代工用户信息的SQL语句
	 */
	private final String SQL_SELECT_UserChargeUserInfo = "SELECT B.ID ,A.* FROM UserInfo A,UserChargeUser B  WHERE A.UserID IN (SELECT ChargedUserID "+
                                        " FROM UserChargeUser WHERE UserID = ? ) and A.UserID = B.ChargedUserID;";
	
	/**
	 * 查询所有的用户信息的SQL语句
	 */
	private final String SQL_SELECT_UserUnchargeUserInfo_WithoutPage = "SELECT u.* FROM UserInfo u WHERE u.UserID NOT IN ( SELECT UserID AS userid FROM UserChargeUser UNION SELECT ChargedUserID AS userid FROM UserChargeUser)";
	
	/**
	 * 查询所有的用户信息的SQL语句
	 */
	private final String SQL_SELECT_UserUnchargeUserInfo_Count = "SELECT COUNT(u.UserID) FROM UserInfo u WHERE u.UserID NOT IN ( SELECT UserID AS userid FROM UserChargeUser UNION SELECT ChargedUserID AS userid FROM UserChargeUser)";
	
	/**
	 * 查询所有的用户信息的SQL语句
	 */
	private final String SQL_SELECT_UserUnchargeUserInfo = "SELECT * FROM (SELECT u.*,ROW_NUMBER() OVER(ORDER BY UserID) rownumber  FROM UserInfo u WHERE u.UserID NOT IN ( SELECT UserID AS userid FROM UserChargeUser UNION SELECT ChargedUserID AS userid FROM UserChargeUser)) AS P WHERE p.rownumber BETWEEN %1$s AND %2$s %3$s";
	
	/**
	 * Feature的SQL语句
	 *   
	 */
	private final String SQL_SELECT_Proxy_By_Condition = "SELECT * FROM (SELECT  UserID,UserName,UserPWD,RealName,DepartmentID,D.Name AS DepartmentName,IDCardTypeID,DutyID,duty.Name AS DutyName,IDCardNo,Email,Tel,Address,AnonymouseFlag,PKI_CA,FrozenFlag ,"+
														" ROW_NUMBER() OVER(ORDER BY UserID) rownumber FROM UserInfo u1,DD_DepartmentInfo  D,DD_Duty AS duty WHERE  u1.DepartmentID = D.ID AND DutyID = duty.ID) AS p WHERE  p.rownumber BETWEEN %1$s AND %2$s AND DutyID = 2 OR DutyID = 3 %3$s";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_Proxy_By_Condition_WithoutPage = "SELECT * FROM UserInfo WHERE DutyID = 2 OR DutyID = 3";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_Proxy_COUNT_By_QueryConditions = "SELECT COUNT(UserID) FROM UserInfo WHERE DutyID = 2 OR DutyID = 3";
	
	/**
	 * 查询是否存在相同的用户名的SQL语句
	 */
	private final String SQL_SELECT_UserName = "SELECT COUNT(UserID) FROM UserInfo WHERE UserName = ?";
	
	/**
	 *查询是否是否存在相同的证件号的的SQL语句
	 */
	private final String SQL_SELECT_IDCardNo = "SELECT COUNT(UserID) FROM UserInfo WHERE IDCardNo = ?";
	
	/**
	 *查询未加入到用户角色信息列表中的用户SQL语句
	 */
	private final String SQL_SELECT_Useres_By_ROleID = "SELECT P.* FROM (SELECT  *,ROW_NUMBER() OVER(ORDER BY UserID) RowNumber  FROM   UserInfo WHERE  UserID NOT IN ( SELECT UserID  FROM UserRolesInfo WHERE RolesID = ?)) AS P WHERE P.rownumber  BETWEEN %1$s AND %2$s %3$s ORDER BY P.UserID";
	
	/**
	 *查询未加入到用户角色信息列表中的用户SQL语句
	 */
	private final String SQL_SELECT_Useres_Count_By_ROleID = "SELECT COUNT(UserID) FROM   UserInfo WHERE  UserID NOT IN ( SELECT UserID  FROM UserRolesInfo WHERE RolesID = ?) ";
	
	/**
	 * 查询未加入到用户角色信息列表中的用户,不分页的SQL语句
	 */
	private final String SQL_SELECT_Useres_By_Condition_WithoutPage = "SELECT  *  FROM   UserInfo WHERE  UserID NOT IN ( SELECT UserID  FROM UserRolesInfo WHERE RolesID = ?) ";
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IUserInfoDAO#delete(com.orifound.aiim.entity
	 * .UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo非法为空！");
			}
			if (userInfo.getUserID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("用户编号非法为空！");
			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(sQL_DELETE_By_UserID, userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID());
				
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
	 * @see com.orifound.aiim.dal.dao.IUserInfoDAO#findAll(java.util.List,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<UserInfo> userInfos, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_ALL, new UserInfoMapper());

				// 返回查询结果
				if (pUserInfos.size() > 0) {
					userInfos.addAll(pUserInfos);
				}

				// 销毁局部变量
				pUserInfos = null;
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
	 * @see
	 * com.orifound.aiim.dal.dao.IUserInfoDAO#findAnonymous(com.orifound.aiim
	 * .entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAnonymous(UserInfo userInfo, ErrInfo pErrInfo) {
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

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_Anonymouse, new UserInfoMapper());

				// 返回查询结果
				if (pUserInfos.size() > 0) {
					userInfo.cloneFrom(pUserInfos.get(0));
				}

				// 销毁局部变量
				pUserInfos = null;
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
	 * @see com.orifound.aiim.dal.dao.IUserInfoDAO#findByDepartmentID(int,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByDepartmentID(int departmentID, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IUserInfoDAO#findByIDCard(int,
	 * java.lang.String, com.orifound.aiim.entity.UserInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByIDCard(int iDCardTypeID, String iDCardNo, UserInfo userInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IUserInfoDAO#findByRealName(java.lang.String,
	 * java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByRealName(String realName, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IUserInfoDAO#findByUserName(java.lang.String,
	 * com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserName(String userName, UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// 检查用户名是否为空
			if (pFlag) {
				if (userName == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户名非法为空。");
				} else {
					if (userName.trim().length() == 0) {
						pFlag = false;
						pErrInfo.getContent().append("用户名非法为空。");
					}
				}
			}

			// 执行SQL语句
			if (pFlag) {

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_BYUSERNAME, new UserInfoMapper4(), userName);

				// 返回查询结果
				if (pUserInfos.size() > 0) {
					userInfo.cloneFrom(pUserInfos.get(0));
				}

				// 销毁局部变量
				pUserInfos = null;
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
	 * @see
	 * com.orifound.aiim.dal.dao.IUserInfoDAO#save(com.orifound.aiim.entity.
	 * UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo对象非法为空！");
			}
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户名非法为空!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户真实姓名非法为空!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户密码非法为空!");
			}
			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				// INSERT INTO
				// UserInfo(UserName,UserPWD,RealName,DepartmentID,DutyID,IDCardTypeID,IDCardNo,Email,Tel,Address,AnonymouseFlag,PKI_CA,FrozenFlag)
				// VALUES(:UserName,:UserPWD,:RealName,:DepartmentID,:DutyID,:IDCardTypeID,:IDCardNo,:Email,:Tel,:Address,:AnonymouseFlag,:PKI_CA,:FrozenFlag)";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlSource = new MapSqlParameterSource();
				sqlSource.addValue("UserName", userInfo.getUserName(), Types.VARCHAR);
				sqlSource.addValue("UserPWD", userInfo.getUserPWD(), Types.VARCHAR);
				sqlSource.addValue("RealName", userInfo.getRealName(), Types.VARCHAR);
				sqlSource.addValue("DepartmentID", userInfo.getDepartmentID(), Types.INTEGER);
				sqlSource.addValue("DutyID", userInfo.getDutyID(), Types.INTEGER);
				sqlSource.addValue("IDCardTypeID", userInfo.getIDCardTypeID(), Types.INTEGER);
				sqlSource.addValue("IDCardNo", userInfo.getIDCardNo(), Types.VARCHAR);
				sqlSource.addValue("Email", userInfo.getEmail(), Types.VARCHAR);
				sqlSource.addValue("Tel", userInfo.getTel(), Types.VARCHAR);
				sqlSource.addValue("Address", userInfo.getAddress(), Types.VARCHAR);
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				
				namedParameterJdbcTemplate.update(SQL_INSERT, sqlSource,keyHolder);				
				userInfo.setUserID(keyHolder.getKey().intValue());
				
				// 销毁局部变量
				namedParameterJdbcTemplate = null;
				sqlSource = null;
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
	 * com.orifound.aiim.dal.dao.IUserInfoDAO#update(com.orifound.aiim.entity
	 * .UserInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo对象非法为空!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("用户编号非法为空!");
			}
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户名非法为空!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户真实姓名非法为空!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户密码非法为空!");
			}
			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				// UPDATE UserInfo SET
				// (UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag)
				// WHERE UserID = :UserID
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserName", userInfo.getUserName());
				sqlParameterSource.addValue("UserPWD", userInfo.getUserPWD());
				sqlParameterSource.addValue("RealName", userInfo.getRealName());
				sqlParameterSource.addValue("DepartmentID", userInfo.getDepartmentID());
				sqlParameterSource.addValue("DutyID", userInfo.getDutyID());
				sqlParameterSource.addValue("IDCardTypeID", userInfo.getIDCardTypeID());
				sqlParameterSource.addValue("IDCardNo", userInfo.getIDCardNo());
				sqlParameterSource.addValue("Email", userInfo.getEmail());
				sqlParameterSource.addValue("Tel", userInfo.getTel());
				sqlParameterSource.addValue("Address", userInfo.getAddress());
				sqlParameterSource.addValue("AnonymouseFlag", userInfo.getAnonymouseFlag());
				sqlParameterSource.addValue("PKI_CA", userInfo.getPKI_CA());
				sqlParameterSource.addValue("FrozenFlag", userInfo.getFrozenFlag());
				sqlParameterSource.addValue("UserID", userInfo.getUserID());
				namedParameterJdbcTemplate.update(SQL_UPDATE_By_UserID, sqlParameterSource);

				// 销毁局部变量
				namedParameterJdbcTemplate = null;
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
	public boolean updatePassword(UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo对象非法为空!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("用户编号非法为空!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("用户密码非法为空!");
			}
			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				// UPDATE UserInfo SET
				// (UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag)
				// WHERE UserID = :UserID
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserPWD", userInfo.getUserPWD());
				sqlParameterSource.addValue("UserID", userInfo.getUserID());
				namedParameterJdbcTemplate.update(SQL_UPDATE_Password, sqlParameterSource);

				// 销毁局部变量
				namedParameterJdbcTemplate = null;
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
	
	/**
	 * DAO接口定义：查询所有具有业务指导室角色的人员信息集合
	 * 
	 * @param userInfos
	 *            查找成功后返回的用户信息
	 * @param pErrInfo
	 *            返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */

	public boolean findBusinessGuids(List<UserInfo> userInfos, EnumSystemRole systemRole, ErrInfo pErrInfo) {

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
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("SystemRolesFlag", systemRole.getEnumValue(), Types.INTEGER);

				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(SQL_SELECT_ALL_BUSINESSGUIDS, paramSource);
				// 返回查询结果
				if (results.size() > 0) {
					UserInfo userInfo = null;
					for (Map<String, Object> result : results) {
						userInfo = new UserInfo();
						userInfo.setUserID(Integer.valueOf(result.get("USERID").toString()));
						userInfo.setRealName(result.get("REALNAME").toString());
						userInfos.add(userInfo);
					}
				}

				// 销毁局部变量
				results = null;
				namedParameterJdbcTemplate = null;
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

	/**
	 * DAO接口定义：查询业务指导室指导的所有档案兼职人员
	 * 
	 * @param businessGuidIds
	 *            业务指导室人员id集合
	 * @param userIds
	 *            档案兼职人员Id集合
	 * @param pErrInfo
	 *            返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查业务指导室人员id集合是否为空
			if (pFlag) {
				if (businessGuidIds == null || businessGuidIds.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数业务指导室人员id集合非法为空。");
				}
			}

			// 检查用户集合是否为空
			if (pFlag) {
				if (businessGuidIds == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数用户集合非法为空。");
				}
			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("businessGuidIds", businessGuidIds);
				paramSource.addValue("SystemRolesFlag", EnumSystemRole.档案兼职人员角色.getEnumValue(), Types.INTEGER);
				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(SQL_SELECT_BY_BUSINESSGUIDIDS, paramSource);
				if (results.size() >= 1) {
					for (Map<String, Object> result : results) {
						userIds.add(Integer.valueOf(result.get("USERID").toString()));
					}
				}

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

	/**
	 * 查询档案管理部门的人员（除去馆长、副馆长）
	 * 
	 * @param userInfos
	 *            查找成功后返回的用户信息
	 * @param pErrInfo
	 *            返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findAllArchivesManagers(List<UserInfo> userInfos, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pEntitys = jdbcTemplate.query(SQL_SELECT_ARCHIVESMANAGERS, new UserInfoMapper());

				// 返回查询结果
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

				// 销毁局部变量
				pEntitys = null;
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
	public boolean findTaskPersons(List<UserInfo> userInfos, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pEntitys = jdbcTemplate.query(SQL_SELECT_TASKPERSONS, new UserInfoMapper());

				// 返回查询结果
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

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


	@Override
	public boolean findUserInfosByCondition(Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String localQuery = "";
		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			// 检查数据分页对象是否为空
			if (pFlag) {
				if (dataPageInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

			StringBuilder querySQL = new StringBuilder();
			Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
			// 获取查询字符串
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = userInfoQueryCondition.get(key);

				if (key.equals("RealName")) {
					querySQL.append("AND ");
					querySQL.append(key + " LIKE '%" + value + "%' ");

				}

				if (key.equals("DepartmentID")) {
					querySQL.append("AND ");
					querySQL.append(key + " = " + value + " ");

				}

				if (key.equals("DutyID")) {
					querySQL.append("AND ");
					querySQL.append(key + " = " + value + " ");

				}

				if (key.equals("IDCardNo")) {
					querySQL.append("AND ");
					querySQL.append(key + " like '%" + value + "%' ");
				}

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_COUNT_By_QueryConditions);
				// 设置分页对象中的总记录数
				dataPageInfo.setRowCount(pRowCount);
				if (dataPageInfo.getPageSize() > 0) {
					localQuery = String.format(SQL_SELECT_By_Condition, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
				} else {
					localQuery = SQL_SELECT_By_Condition_WithoutPage;
				}
				System.out.println("sql----->"+localQuery);
				List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper2());
				// 返回查询结果
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

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

	@Override
	public boolean findUserInfoByUserID(int pID, UserInfo userInfo, ErrInfo pErrInfo) {
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
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				// 设置分页对象中的总记录数
				List<UserInfo> pEntitys = jdbcTemplate.query(SQL_SELECT_By_UserID, new UserInfoMapper(),pID);
				// 返回查询结果
				if (pEntitys.size() > 0) {
					userInfo.cloneFrom(pEntitys.get(0));
				}

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



	@Override
	public boolean findArchivesinfoManagers(List<UserInfo> userInfos,
			ErrInfo pErrInfo) {
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
				paramSource.addValue("SystemRolesFlag", EnumSystemRole.档案管理室专员角色.getEnumValue(), Types.INTEGER);
				
				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(SQL_SELECT_ALL_BUSINESSGUIDS, paramSource);
				//返回查询结果
				if (results.size() > 0) {
					UserInfo userInfo = null;
					for(Map<String, Object> result : results) {
						userInfo = new UserInfo();
						userInfo.setUserID(Integer.valueOf(result.get("USERID").toString()));
						userInfo.setRealName(result.get("REALNAME").toString());
						userInfos.add(userInfo);
					}
				}

				//销毁局部变量
				results = null;
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
	public boolean findUserChargeUserInfoByUserID(int pID, List<UserInfo> userInfos, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_UserChargeUserInfo, new UserInfoMapper3(),pID);

				// 返回查询结果
				if (pUserInfos.size() > 0) {
					userInfos.addAll(pUserInfos);
				}

				// 销毁局部变量
				pUserInfos = null;
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
	public boolean findAllUserUnchargeUserInfo(Map<String, Object> userInfoQueryCondition,DataPageInfo dataPageInfo,List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String localQuery = "";
		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			

		// 检查JDBC数据源是否正确依赖注入
		if (CheckDataSourceInjection(pErrInfo) == false) {
			pFlag = false;
		}
		// 检查数据分页对象是否为空
		if (pFlag) {
			if (dataPageInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("数据分页信息非法为空。");
			}
		}

		StringBuilder querySQL = new StringBuilder();
		Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
		// 获取查询字符串
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = userInfoQueryCondition.get(key);

			if (key.equals("RealName")) {
				querySQL.append("AND ");
				querySQL.append(key + " LIKE '%" + value + "%' ");

			}

			if (key.equals("IDCardNo")) {
				querySQL.append("AND ");
				querySQL.append(key + " like '%" + value + "%' ");
			}
		}

				// 执行SQL语句
				if (pFlag) {
					pErrPos = 2;

					JdbcTemplate jdbcTemplate = getJdbcTemplate();
					int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_UserUnchargeUserInfo_Count);
					// 设置分页对象中的总记录数
					dataPageInfo.setRowCount(pRowCount);
					if (dataPageInfo.getPageSize() > 0) {
						localQuery = String.format(SQL_SELECT_UserUnchargeUserInfo, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
					} else {
						localQuery = SQL_SELECT_UserUnchargeUserInfo_WithoutPage;
					}
					List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper());
					// 返回查询结果
					if (pEntitys.size() > 0) {
						userInfos.addAll(pEntitys);
					}
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
	
	@Override
	public boolean findUserProxyInfosByCondition(Map<String, Object> userInfoQueryCondition, DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String localQuery = "";
		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			// 检查数据分页对象是否为空
			if (pFlag) {
				if (dataPageInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}

			StringBuilder querySQL = new StringBuilder();
			Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
			// 获取查询字符串
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = userInfoQueryCondition.get(key);

				if (key.equals("RealName")) {
					querySQL.append("AND ");
					querySQL.append(key + " LIKE '%" + value + "%' ");

				}

				if (key.equals("DepartmentID")) {
					querySQL.append("AND ");
					querySQL.append(key + " = " + value + " ");

				}

				if (key.equals("IDCardNo")) {
					querySQL.append("AND ");
					querySQL.append(key + " like '%" + value + "%' ");
				}

			}

			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_Proxy_COUNT_By_QueryConditions);
				// 设置分页对象中的总记录数
				dataPageInfo.setRowCount(pRowCount);
				if (dataPageInfo.getPageSize() > 0) {
					localQuery = String.format(SQL_SELECT_Proxy_By_Condition, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
				} else {
					localQuery = SQL_SELECT_Proxy_By_Condition_WithoutPage;
				}
				System.out.println("Proxy--->Sql---->"+localQuery);
				List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper2());
				// 返回查询结果
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

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
	
	@Override
	public boolean checkUserNameExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息Dao层JDBC数据源依赖注入失败：");
			}

			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息对象非法为空！");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserName() == null || userInfo.getUserName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户名非法为空！");
				}
			}
			
			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
			    int recordCount = jdbcTemplate.queryForInt(SQL_SELECT_UserName, userInfo.getUserName());//记录数
			    exists.setValue(recordCount);

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
	
	@Override
	public boolean checkIDCardNoExists(UserInfo userInfo,IntegerEx exists, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息Dao层JDBC数据源依赖注入失败：");
			}

			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户信息对象非法为空！");
				}
			}
			
			if (pFlag) {
				if (userInfo.getIDCardNo() == null || userInfo.getIDCardNo().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户将证件号非法为空！");
				}
			}
			
			// 执行SQL语句
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
			    int recordCount = jdbcTemplate.queryForInt(SQL_SELECT_IDCardNo, userInfo.getIDCardNo());//记录数
			    exists.setValue(recordCount);

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
	
	public boolean findUseresNotInRoleID(int pRoleID,Map<String,Object> userInfoQueryCondition,DataPageInfo dataPageInfo, List<UserInfo> userInfos, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		String localQuery = "";
		Throwable throwable = new Throwable();

		try {
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "用户信息JDBC数据源依赖注入失败：");
			}

			// 检查用户编号是否合法
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为0");
				}
			}
			
			StringBuilder querySQL = new StringBuilder();
			Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
			// 获取查询字符串
			while (iterator.hasNext()) {
				String key = iterator.next();
				Object value = userInfoQueryCondition.get(key);
	
				if (key.equals("RealName")) {
					querySQL.append("AND ");
					querySQL.append(key + " LIKE '%" + value + "%' ");
	
				}
	
				if (key.equals("IDCardNo")) {
					querySQL.append("AND ");
					querySQL.append(key + " like '%" + value + "%' ");
				}
	
			}
				
				
				// 执行SQL语句
				if (pFlag) {
					pErrPos = 2;

					JdbcTemplate jdbcTemplate = getJdbcTemplate();
					int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_Useres_Count_By_ROleID,pRoleID);
					// 设置分页对象中的总记录数
					dataPageInfo.setRowCount(pRowCount);
					if (dataPageInfo.getPageSize() > 0) {
						localQuery = String.format(SQL_SELECT_Useres_By_ROleID, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
					} else {
						localQuery = SQL_SELECT_Useres_By_Condition_WithoutPage;
					}
					List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper(),pRoleID);
					// 返回查询结果
					if (pEntitys.size() > 0) {
						userInfos.addAll(pEntitys);
					}
					
				// 销毁局部变量
				pEntitys = null;
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
