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
	 * ���캯��
	 */
	public ArchivesUseRequestDaoImpl() {

	}

	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//�������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	// Table Name: ArchivesUseRequest
	// Columns List,Can Used in SELECT SQL: ID,RequestTime,RequestReason,UserDepartment,UserID
	// Columns List,Can Used in INSERT SQL: :ID,:RequestTime,:RequestReason,:UserDepartment,:UserID
	// Columns List,Can Used in UPDATE SQL: ID=:ID,RequestTime=:RequestTime,RequestReason=:RequestReason,UserDepartment=:UserDepartment,UserID=:UserID

	/**
	 * �����������Ǽ���Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUseRequest(RequestTime,RequestReason,UserDepartment,UserID) VALUES(:RequestTime,:RequestReason,:UserDepartment,:UserID)";
	
	@Override
	public boolean add(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
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
				

				//���پֲ�����
				paramSource = null;
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean delete(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	/**
	 * ����������ѯ�������뵥��ϢSQL���
	 *///��������    %1$s:��ѯ����
	private final String SQL_SELECT_findByQueryCondition_COUNT = " SELECT COUNT(A.ID) FROM ArchivesUseRequest A,UserInfo B WHERE A.UserID = B.UserID %1$s ";

	/**
	 * ����������ѯ�������뵥��ϢSQL���
	 *///��������    %1$s:��ѯ����; 
//	private final String SQL_SELECT_findByQueryCondition1 = " SELECT TOP 5 A.ID ,A.RequestReason ,A.RequestTime,A.UserDepartment, B.* " +
//			" FROM ArchivesUseRequest A,UserInfo B WHERE A.UserID = B.UserID %1$s " +
//			" AND A.ID>  "+
//			" ORDER BY A.UserID ASC,A.RequestTime DESC";
	//��������    %1$s:��ѯ����; %2$s:ÿҳ��ʾ��¼��; %3$s:��ҳǰ�Ѿ���ʾ���ļ�¼��;
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				//��ȡ�ܼ�¼��
				String localSQL = String.format(SQL_SELECT_findByQueryCondition_COUNT, querySQL);
				int pRowCount = jdbcTemplate.queryForInt(localSQL);
				dataPageInfo.setRowCount(pRowCount);
				
				//��ȡ��¼��Ϣ
				localSQL = String.format(SQL_SELECT_findByQueryCondition, querySQL,dataPageInfo.getPageSize(),(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				List<ArchivesUseRequest> pEntitys=jdbcTemplate.query(localSQL,new ArchivesUseRequestUserInfoMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {	
					archivesUseRequests.addAll(pEntitys);
				}

				//���پֲ�����
				jdbcTemplate = null;
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean findByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
