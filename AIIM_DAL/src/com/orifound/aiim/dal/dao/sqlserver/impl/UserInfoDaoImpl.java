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
 * ϵͳ�û���Ϣ��DAOʵ���ࣨSQL Serverƽ̨��
 * 
 */
public class UserInfoDaoImpl extends JdbcDaoSupport implements IUserInfoDao {


	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * ���departmentName��dutyName������
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ���캯��
	 */
	public UserInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * 
	 * @param dataSource
	 *            ����Դ
	 */
	public UserInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * ��ѯ�û���������Ϣ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM UserInfo ORDER BY UserID DESC";

	/**
	 * Feature��SQL���
	 *   
	 */
	private final String SQL_SELECT_By_Condition = "SELECT * FROM (SELECT  A.UserID,A.UserName,A.UserPWD,A.RealName,A.DepartmentID,B.Name AS DepartmentName,A.IDCardTypeID,A.DutyID,D.Name AS DutyName,A.IDCardNo,A.Email,A.Tel,A.Address,A.AnonymouseFlag,A.PKI_CA,A.FrozenFlag, "+
                                                  " ROW_NUMBER() OVER(ORDER BY A.UserID) rownumber FROM UserInfo  A LEFT JOIN DD_DepartmentInfo  B ON A.DepartmentID = B.ID  ,UserInfo  C  LEFT JOIN DD_Duty  D  ON C.DutyID=D.ID WHERE A.UserID = C.UserID) AS p WHERE  p.rownumber BETWEEN %1$s AND %2$s %3$s";
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_By_Condition_WithoutPage = "SELECT * FROM UserInfo";
	/**
	 * ��ѯ�û����˵�SQL���
	 */
	private final String SQL_SELECT_COUNT_By_QueryConditions = "SELECT COUNT(UserID) FROM UserInfo";
	/**
	 * �����û��������û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_BYUSERNAME = "select u.UserID,u.UserName,u.UserPWD,u.RealName,u.DepartmentID,u.DutyID,u.IDCardTypeID,u.IDCardNo,u.Email,u.Tel,u.Address,u.AnonymouseFlag,u.PKI_CA,u.FrozenFlag,u.CreateDate,d.ID,d.Name as DepartmentName,d.SystemDepartmentFlag,d.Remark " +
			                                     "from UserInfo u,DD_DepartmentInfo d where d.ID=u.DepartmentID AND u.UserName=?";

	/**
	 * ���������û���SQL���
	 */
	private final String SQL_SELECT_Anonymouse = "SELECT * FROM UserInfo WHERE AnonymouseFlag=1";

	/**
	 * ��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��SQL���
	 */
	private final String SQL_SELECT_ALL_BUSINESSGUIDS = "SELECT u.UserID, u.RealName FROM DD_UserRoles r, UserRolesInfo ur, UserInfo u WHERE r.ID=ur.RolesID AND ur.UserID=u.UserID AND r.SystemRolesFlag=:SystemRolesFlag";

	/**
	 * ��ѯָ��ҵ��ָ����ָ�������е�����ְ��Ա��SQL���
	 */
	private final String SQL_SELECT_BY_BUSINESSGUIDIDS = "SELECT du.UserID FROM UserInfo u,UserChargeDepartment cd, UserInfo du,UserRolesInfo ur,DD_UserRoles dur "
			+ "WHERE u.UserID=cd.UserID AND cd.DepartmentID=du.DepartmentID AND du.UserID=ur.UserID AND ur.RolesID=dur.ID AND u.UserID in(:businessGuidIds) AND dur.SystemRolesFlag=:SystemRolesFlag ";

	/**
	 * ��ѯ���������ŵ���Ա����ȥ�ݳ������ݳ�����SQL���
	 */
	private final String SQL_SELECT_ARCHIVESMANAGERS = "SELECT u.* FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r "
			+ "WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " + "AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";

	/**
	 * ��ѯ��������˼��ϵ�SQL���
	 */
	private final String SQL_SELECT_TASKPERSONS = "SELECT u.* FROM UserInfo u,DD_DepartmentInfo d WHERE u.DepartmentID=d.ID AND d.SystemDepartmentFlag=1";

	/**
	 * ɾ��userInfo��SQL���
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
	 * ����userInfo��SQL���
	 */
	private final String SQL_UPDATE_By_UserID = "UPDATE UserInfo SET UserName=:UserName,UserPWD=:UserPWD,RealName=:RealName,DepartmentID=:DepartmentID,DutyID=:DutyID,IDCardTypeID=:IDCardTypeID,IDCardNo=:IDCardNo,Email=:Email,Tel=:Tel,Address=:Address,AnonymouseFlag=:AnonymouseFlag,PKI_CA=:PKI_CA,FrozenFlag=:FrozenFlag WHERE UserID = :UserID";
	
	/**
	 * ���������SQL���
	 */
	private final String SQL_UPDATE_Password = "UPDATE UserInfo SET UserPWD=:UserPWD WHERE UserID = :UserID";
	
	/**
	 * ����userInfo��SQL���
	 */
	private final String SQL_SELECT_By_UserID = "SELECT * FROM  UserInfo WHERE UserID = ?";


	/**
	 * �����¼��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO UserInfo(UserName,UserPWD,RealName,DepartmentID,DutyID,IDCardTypeID,IDCardNo,Email,Tel,Address) VALUES(:UserName,:UserPWD,:RealName,:DepartmentID,:DutyID,:IDCardTypeID,:IDCardNo,:Email,:Tel,:Address)";
	
	/**
	 * ��ѯ�������û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_UserChargeUserInfo = "SELECT B.ID ,A.* FROM UserInfo A,UserChargeUser B  WHERE A.UserID IN (SELECT ChargedUserID "+
                                        " FROM UserChargeUser WHERE UserID = ? ) and A.UserID = B.ChargedUserID;";
	
	/**
	 * ��ѯ���е��û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_UserUnchargeUserInfo_WithoutPage = "SELECT u.* FROM UserInfo u WHERE u.UserID NOT IN ( SELECT UserID AS userid FROM UserChargeUser UNION SELECT ChargedUserID AS userid FROM UserChargeUser)";
	
	/**
	 * ��ѯ���е��û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_UserUnchargeUserInfo_Count = "SELECT COUNT(u.UserID) FROM UserInfo u WHERE u.UserID NOT IN ( SELECT UserID AS userid FROM UserChargeUser UNION SELECT ChargedUserID AS userid FROM UserChargeUser)";
	
	/**
	 * ��ѯ���е��û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_UserUnchargeUserInfo = "SELECT * FROM (SELECT u.*,ROW_NUMBER() OVER(ORDER BY UserID) rownumber  FROM UserInfo u WHERE u.UserID NOT IN ( SELECT UserID AS userid FROM UserChargeUser UNION SELECT ChargedUserID AS userid FROM UserChargeUser)) AS P WHERE p.rownumber BETWEEN %1$s AND %2$s %3$s";
	
	/**
	 * Feature��SQL���
	 *   
	 */
	private final String SQL_SELECT_Proxy_By_Condition = "SELECT * FROM (SELECT  UserID,UserName,UserPWD,RealName,DepartmentID,D.Name AS DepartmentName,IDCardTypeID,DutyID,duty.Name AS DutyName,IDCardNo,Email,Tel,Address,AnonymouseFlag,PKI_CA,FrozenFlag ,"+
														" ROW_NUMBER() OVER(ORDER BY UserID) rownumber FROM UserInfo u1,DD_DepartmentInfo  D,DD_Duty AS duty WHERE  u1.DepartmentID = D.ID AND DutyID = duty.ID) AS p WHERE  p.rownumber BETWEEN %1$s AND %2$s AND DutyID = 2 OR DutyID = 3 %3$s";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_Proxy_By_Condition_WithoutPage = "SELECT * FROM UserInfo WHERE DutyID = 2 OR DutyID = 3";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_Proxy_COUNT_By_QueryConditions = "SELECT COUNT(UserID) FROM UserInfo WHERE DutyID = 2 OR DutyID = 3";
	
	/**
	 * ��ѯ�Ƿ������ͬ���û�����SQL���
	 */
	private final String SQL_SELECT_UserName = "SELECT COUNT(UserID) FROM UserInfo WHERE UserName = ?";
	
	/**
	 *��ѯ�Ƿ��Ƿ������ͬ��֤���ŵĵ�SQL���
	 */
	private final String SQL_SELECT_IDCardNo = "SELECT COUNT(UserID) FROM UserInfo WHERE IDCardNo = ?";
	
	/**
	 *��ѯδ���뵽�û���ɫ��Ϣ�б��е��û�SQL���
	 */
	private final String SQL_SELECT_Useres_By_ROleID = "SELECT P.* FROM (SELECT  *,ROW_NUMBER() OVER(ORDER BY UserID) RowNumber  FROM   UserInfo WHERE  UserID NOT IN ( SELECT UserID  FROM UserRolesInfo WHERE RolesID = ?)) AS P WHERE P.rownumber  BETWEEN %1$s AND %2$s %3$s ORDER BY P.UserID";
	
	/**
	 *��ѯδ���뵽�û���ɫ��Ϣ�б��е��û�SQL���
	 */
	private final String SQL_SELECT_Useres_Count_By_ROleID = "SELECT COUNT(UserID) FROM   UserInfo WHERE  UserID NOT IN ( SELECT UserID  FROM UserRolesInfo WHERE RolesID = ?) ";
	
	/**
	 * ��ѯδ���뵽�û���ɫ��Ϣ�б��е��û�,����ҳ��SQL���
	 */
	private final String SQL_SELECT_Useres_By_Condition_WithoutPage = "SELECT  *  FROM   UserInfo WHERE  UserID NOT IN ( SELECT UserID  FROM UserRolesInfo WHERE RolesID = ?) ";
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// �������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo�Ƿ�Ϊ�գ�");
			}
			if (userInfo.getUserID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(sQL_DELETE_By_UserID, userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID(),userInfo.getUserID());
				
				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_ALL, new UserInfoMapper());

				// ���ز�ѯ���
				if (pUserInfos.size() > 0) {
					userInfos.addAll(pUserInfos);
				}

				// ���پֲ�����
				pUserInfos = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// ִ��SQL���
			if (pFlag) {

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_Anonymouse, new UserInfoMapper());

				// ���ز�ѯ���
				if (pUserInfos.size() > 0) {
					userInfo.cloneFrom(pUserInfos.get(0));
				}

				// ���پֲ�����
				pUserInfos = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;

			}

			// ����û����Ƿ�Ϊ��
			if (pFlag) {
				if (userName == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����Ƿ�Ϊ�ա�");
				} else {
					if (userName.trim().length() == 0) {
						pFlag = false;
						pErrInfo.getContent().append("�û����Ƿ�Ϊ�ա�");
					}
				}
			}

			// ִ��SQL���
			if (pFlag) {

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_BYUSERNAME, new UserInfoMapper4(), userName);

				// ���ز�ѯ���
				if (pUserInfos.size() > 0) {
					userInfo.cloneFrom(pUserInfos.get(0));
				}

				// ���پֲ�����
				pUserInfos = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ�գ�");
			}
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û����Ƿ�Ϊ��!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ʵ�����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û�����Ƿ�Ϊ��!");
			}
			// ִ��SQL���
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
				
				// ���پֲ�����
				namedParameterJdbcTemplate = null;
				sqlSource = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
			}
			if (userInfo.getUserName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û����Ƿ�Ϊ��!");
			}
			if (userInfo.getRealName() == null || userInfo.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ʵ�����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û�����Ƿ�Ϊ��!");
			}
			// ִ��SQL���
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

				// ���پֲ�����
				namedParameterJdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("userInfo����Ƿ�Ϊ��!");
			}
			if (userInfo.getUserID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ��!");
			}
			if (userInfo.getUserPWD() == null || userInfo.getUserPWD().equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û�����Ƿ�Ϊ��!");
			}
			// ִ��SQL���
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

				// ���پֲ�����
				namedParameterJdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;

	}
	
	/**
	 * DAO�ӿڶ��壺��ѯ���о���ҵ��ָ���ҽ�ɫ����Ա��Ϣ����
	 * 
	 * @param userInfos
	 *            ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo
	 *            ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */

	public boolean findBusinessGuids(List<UserInfo> userInfos, EnumSystemRole systemRole, ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("SystemRolesFlag", systemRole.getEnumValue(), Types.INTEGER);

				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(SQL_SELECT_ALL_BUSINESSGUIDS, paramSource);
				// ���ز�ѯ���
				if (results.size() > 0) {
					UserInfo userInfo = null;
					for (Map<String, Object> result : results) {
						userInfo = new UserInfo();
						userInfo.setUserID(Integer.valueOf(result.get("USERID").toString()));
						userInfo.setRealName(result.get("REALNAME").toString());
						userInfos.add(userInfo);
					}
				}

				// ���پֲ�����
				results = null;
				namedParameterJdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * DAO�ӿڶ��壺��ѯҵ��ָ����ָ�������е�����ְ��Ա
	 * 
	 * @param businessGuidIds
	 *            ҵ��ָ������Աid����
	 * @param userIds
	 *            ������ְ��ԱId����
	 * @param pErrInfo
	 *            ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findPartTimePersons(List<Integer> businessGuidIds, List<Integer> userIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ���ҵ��ָ������Աid�����Ƿ�Ϊ��
			if (pFlag) {
				if (businessGuidIds == null || businessGuidIds.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������ҵ��ָ������Աid���ϷǷ�Ϊ�ա�");
				}
			}

			// ����û������Ƿ�Ϊ��
			if (pFlag) {
				if (businessGuidIds == null) {
					pFlag = false;
					pErrInfo.getContent().append("��������û����ϷǷ�Ϊ�ա�");
				}
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("businessGuidIds", businessGuidIds);
				paramSource.addValue("SystemRolesFlag", EnumSystemRole.������ְ��Ա��ɫ.getEnumValue(), Types.INTEGER);
				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(SQL_SELECT_BY_BUSINESSGUIDIDS, paramSource);
				if (results.size() >= 1) {
					for (Map<String, Object> result : results) {
						userIds.add(Integer.valueOf(result.get("USERID").toString()));
					}
				}

			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * ��ѯ���������ŵ���Ա����ȥ�ݳ������ݳ���
	 * 
	 * @param userInfos
	 *            ���ҳɹ��󷵻ص��û���Ϣ
	 * @param pErrInfo
	 *            ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findAllArchivesManagers(List<UserInfo> userInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pEntitys = jdbcTemplate.query(SQL_SELECT_ARCHIVESMANAGERS, new UserInfoMapper());

				// ���ز�ѯ���
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

				// ���پֲ�����
				pEntitys = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pEntitys = jdbcTemplate.query(SQL_SELECT_TASKPERSONS, new UserInfoMapper());

				// ���ز�ѯ���
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			// ������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag) {
				if (dataPageInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			StringBuilder querySQL = new StringBuilder();
			Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
			// ��ȡ��ѯ�ַ���
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

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_COUNT_By_QueryConditions);
				// ���÷�ҳ�����е��ܼ�¼��
				dataPageInfo.setRowCount(pRowCount);
				if (dataPageInfo.getPageSize() > 0) {
					localQuery = String.format(SQL_SELECT_By_Condition, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
				} else {
					localQuery = SQL_SELECT_By_Condition_WithoutPage;
				}
				System.out.println("sql----->"+localQuery);
				List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper2());
				// ���ز�ѯ���
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				// ���÷�ҳ�����е��ܼ�¼��
				List<UserInfo> pEntitys = jdbcTemplate.query(SQL_SELECT_By_UserID, new UserInfoMapper(),pID);
				// ���ز�ѯ���
				if (pEntitys.size() > 0) {
					userInfo.cloneFrom(pEntitys.get(0));
				}

				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("SystemRolesFlag", EnumSystemRole.����������רԱ��ɫ.getEnumValue(), Types.INTEGER);
				
				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(SQL_SELECT_ALL_BUSINESSGUIDS, paramSource);
				//���ز�ѯ���
				if (results.size() > 0) {
					UserInfo userInfo = null;
					for(Map<String, Object> result : results) {
						userInfo = new UserInfo();
						userInfo.setUserID(Integer.valueOf(result.get("USERID").toString()));
						userInfo.setRealName(result.get("REALNAME").toString());
						userInfos.add(userInfo);
					}
				}

				//���پֲ�����
				results = null;
				namedParameterJdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserInfo> pUserInfos = jdbcTemplate.query(SQL_SELECT_UserChargeUserInfo, new UserInfoMapper3(),pID);

				// ���ز�ѯ���
				if (pUserInfos.size() > 0) {
					userInfos.addAll(pUserInfos);
				}

				// ���پֲ�����
				pUserInfos = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			

		// ���JDBC����Դ�Ƿ���ȷ����ע��
		if (CheckDataSourceInjection(pErrInfo) == false) {
			pFlag = false;
		}
		// ������ݷ�ҳ�����Ƿ�Ϊ��
		if (pFlag) {
			if (dataPageInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
			}
		}

		StringBuilder querySQL = new StringBuilder();
		Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
		// ��ȡ��ѯ�ַ���
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

				// ִ��SQL���
				if (pFlag) {
					pErrPos = 2;

					JdbcTemplate jdbcTemplate = getJdbcTemplate();
					int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_UserUnchargeUserInfo_Count);
					// ���÷�ҳ�����е��ܼ�¼��
					dataPageInfo.setRowCount(pRowCount);
					if (dataPageInfo.getPageSize() > 0) {
						localQuery = String.format(SQL_SELECT_UserUnchargeUserInfo, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
					} else {
						localQuery = SQL_SELECT_UserUnchargeUserInfo_WithoutPage;
					}
					List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper());
					// ���ز�ѯ���
					if (pEntitys.size() > 0) {
						userInfos.addAll(pEntitys);
					}
					// ���پֲ�����
					jdbcTemplate = null;
				}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			// ������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag) {
				if (dataPageInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			StringBuilder querySQL = new StringBuilder();
			Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
			// ��ȡ��ѯ�ַ���
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

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_Proxy_COUNT_By_QueryConditions);
				// ���÷�ҳ�����е��ܼ�¼��
				dataPageInfo.setRowCount(pRowCount);
				if (dataPageInfo.getPageSize() > 0) {
					localQuery = String.format(SQL_SELECT_Proxy_By_Condition, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
				} else {
					localQuery = SQL_SELECT_Proxy_By_Condition_WithoutPage;
				}
				System.out.println("Proxy--->Sql---->"+localQuery);
				List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper2());
				// ���ز�ѯ���
				if (pEntitys.size() > 0) {
					userInfos.addAll(pEntitys);
				}

				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢDao��JDBC����Դ����ע��ʧ�ܣ�");
			}

			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ����Ƿ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserName() == null || userInfo.getUserName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("�û����Ƿ�Ϊ�գ�");
				}
			}
			
			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
			    int recordCount = jdbcTemplate.queryForInt(SQL_SELECT_UserName, userInfo.getUserName());//��¼��
			    exists.setValue(recordCount);

				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢDao��JDBC����Դ����ע��ʧ�ܣ�");
			}

			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ����Ƿ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				if (userInfo.getIDCardNo() == null || userInfo.getIDCardNo().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("�û���֤���ŷǷ�Ϊ�գ�");
				}
			}
			
			// ִ��SQL���
			if (pFlag) {
				pErrPos = 2;

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
			    int recordCount = jdbcTemplate.queryForInt(SQL_SELECT_IDCardNo, userInfo.getIDCardNo());//��¼��
			    exists.setValue(recordCount);

				// ���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
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
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û���ϢJDBC����Դ����ע��ʧ�ܣ�");
			}

			// ����û�����Ƿ�Ϸ�
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ0");
				}
			}
			
			StringBuilder querySQL = new StringBuilder();
			Iterator<String> iterator = userInfoQueryCondition.keySet().iterator();
			// ��ȡ��ѯ�ַ���
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
				
				
				// ִ��SQL���
				if (pFlag) {
					pErrPos = 2;

					JdbcTemplate jdbcTemplate = getJdbcTemplate();
					int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_Useres_Count_By_ROleID,pRoleID);
					// ���÷�ҳ�����е��ܼ�¼��
					dataPageInfo.setRowCount(pRowCount);
					if (dataPageInfo.getPageSize() > 0) {
						localQuery = String.format(SQL_SELECT_Useres_By_ROleID, 1+dataPageInfo.getPageSize()*(dataPageInfo.getCurrentPage()-1), dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(), querySQL.toString());
					} else {
						localQuery = SQL_SELECT_Useres_By_Condition_WithoutPage;
					}
					List<UserInfo> pEntitys = jdbcTemplate.query(localQuery, new UserInfoMapper(),pRoleID);
					// ���ز�ѯ���
					if (pEntitys.size() > 0) {
						userInfos.addAll(pEntitys);
					}
					
				// ���پֲ�����
				pEntitys = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
}
