package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.object.MappingSqlQueryWithParameters;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesUseRequestDetailDao;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumStoreStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����������뵥��ϸ���DAOʵ����
 * @author Administrator
 *
 */
public class ArchivesUseRequestDetailDaoImpl extends JdbcDaoSupport implements IArchivesUseRequestDetailDao {
	/**
	 * ���캯��
	 */
	public ArchivesUseRequestDetailDaoImpl() {

	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */

	private class ArchivesUseRequestDetailMapper implements RowMapper<ArchivesUseRequestDetail>
	{
		
		@Override
		public ArchivesUseRequestDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			//=======Detail======
			int iD = rs.getInt("ID");
			int requestID = rs.getInt("RequestID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			String archivesTypeText = rs.getString("ArchivesTypeText");
			int nBXH = rs.getInt("NBXH");
			int secrecyID = rs.getInt("SecrecyID");
			String secrecyText = rs.getString("secrecyText");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			int useWayID = rs.getInt("UseWayID");
			String useWayText = "";
			if (useWayID==1) {
				useWayText = "�赵";
			} else if(useWayID==2){
				useWayText = "�鵵";
			} else if(useWayID==3){
				useWayText = "�鿴ԭ��";
			}
			int checkResult = rs.getInt("CheckResult");
			String backReason = rs.getString("BackReason");
			Date checkTime = rs.getTimestamp("CheckTime");
			int checkUserID = rs.getInt("CheckUserID");
			String remark = rs.getString("Remark");
			ArchivesUseRequestDetail archivesUseRequestDetail = new ArchivesUseRequestDetail(iD, requestID, archivesTypeID,archivesTypeText, nBXH, secrecyID, secrecyText, archivesID, title, useWayID,useWayText, checkResult, backReason, checkTime, checkUserID, remark);
			//==========request===========
		//	String iD = rs.getString("RequestID");
			Date requestTime = rs.getTimestamp("RequestTime");
			String requestReason = rs.getString("RequestReason");
			String userDepartment = rs.getString("UserDepartment");
			int userID = rs.getInt("UserID");
			ArchivesUseRequest archivesUseRequest = new ArchivesUseRequest(requestID, requestTime, requestReason, userDepartment, userID);
			
		    //=========UserInfo============
		//	int userID = rs.getInt("UserID");
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
			UserInfo userInfo = new UserInfo(userID, userName, userPWD, realName, departmentID, dutyID, iDCardTypeID, iDCardNo, email, tel, address, anonymouseFlag, pKI_CA, frozenFlag);
			
			archivesUseRequest.setUserInfo(userInfo);
			archivesUseRequestDetail.setArchivesUseRequest(archivesUseRequest);
			return archivesUseRequestDetail; 
		}
	}

	private class ArchivesUseRequestDetailStoreStatusMapper implements RowMapper<ArchivesUseRequestDetail>
	{
		
		@Override
		public ArchivesUseRequestDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int requestID = rs.getInt("RequestID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			String archivesTypeText = rs.getString("ArchivesTypeText");
			int nBXH = rs.getInt("NBXH");
			int secrecyID = rs.getInt("SecrecyID");
			String secrecyText = rs.getString("secrecyText");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			int useWayID = rs.getInt("UseWayID");
			String useWayText = rs.getString("useWayText");
			int checkResult = rs.getInt("CheckResult");
			String backReason = rs.getString("BackReason");
			Date checkTime = rs.getTimestamp("CheckTime");
			int checkUserID = rs.getInt("CheckUserID");
			String remark = rs.getString("Remark");
			int storeStatus = rs.getInt("StoreStatus");
			
			
			ArchivesUseRequestDetail archivesUseRequestDetail = new ArchivesUseRequestDetail(iD, requestID, archivesTypeID, archivesTypeText, nBXH, secrecyID, secrecyText, archivesID, title, useWayID, useWayText, checkResult, backReason, checkTime, checkUserID, remark);
			archivesUseRequestDetail.setTag(EnumStoreStatus.getEnumElement(storeStatus));
			return archivesUseRequestDetail;
			
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

	// Table Name: ArchivesUseRequestDetails
	// Columns List,Can Used in SELECT SQL: ID,RequestID,ArchivesTypeID,NBXH,ArchivesID,Title,UseWayID,CheckResult,BackReason,CheckTime,CheckUserID,Remark
	// Columns List,Can Used in INSERT SQL: :ID,:RequestID,:ArchivesTypeID,:NBXH,:secrecyID:ArchivesID,:Title,:UseWayID,:CheckResult,:BackReason,:CheckTime,:CheckUserID,:Remark
	// Columns List,Can Used in UPDATE SQL: ID=:ID,RequestID=:RequestID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,UseWayID=:UseWayID,CheckResult=:CheckResult,BackReason=:BackReason,CheckTime=:CheckTime,CheckUserID=:CheckUserID,Remark=:Remark

	
	/**
	 * ��ӵ����������뵥��ϸ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUseRequestDetails(RequestID,ArchivesTypeID,NBXH,SecrecyID,ArchivesID,Title,UseWayID) VALUES(:RequestID,:ArchivesTypeID,:NBXH,:SecrecyID,:ArchivesID,:Title,:UseWayID) ";
	
	@Override
	public boolean add(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo) {
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
				//RequestID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UseWayID
				paramSource.addValue("RequestID",archivesUseRequestDetail.getRequestID());
				paramSource.addValue("ArchivesTypeID", archivesUseRequestDetail.getArchivesTypeID());
				paramSource.addValue("NBXH",archivesUseRequestDetail.getNBXH());				
				paramSource.addValue("SecrecyID",archivesUseRequestDetail.getSecrecyID());
				paramSource.addValue("ArchivesID",archivesUseRequestDetail.getArchivesID());
				paramSource.addValue("Title",archivesUseRequestDetail.getTitle());
				paramSource.addValue("UseWayID",archivesUseRequestDetail.getUseWayID());
							
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				archivesUseRequestDetail.setID(keyHolder.getKey().intValue());

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
	public boolean delete(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * ��ѯ���д���˵����������SQL���
	 */
	private final String SQL_SELECT_findAllNotExamine_COUNT = "select COUNT(A.ID) "+
		" from ArchivesUseRequestDetails A,ArchivesUseRequest B,DD_ArchivesSecrecy C ,UserInfo D  "+
		" where A.RequestID = B.ID AND A.SecrecyID=C.ID AND B.UserID = D.UserID  AND A.CheckResult =0 ";

	/**
	 * ��ѯ���д���˵����������SQL���
	 *///������%1$s�� ÿҳ��ʾ�ļ�¼����%2$s����ǰ����ʾҳǰ����ʾ�ļ�¼��
	private final String SQL_SELECT_findAllNotExamine = 
		" select TOP %1$s A.*  "+
		" ,B.RequestReason,B.RequestTime,B.UserDepartment,B.UserID  "+ 
		" ,C.Name as SecrecyText  "+
		" ,D.*  "+
		" ,E.Name as useWayText  "+
		" ,F.FullName AS ArchivesTypeText  "+
		" from ArchivesUseRequestDetails A,ArchivesUseRequest B,DD_ArchivesSecrecy C ,UserInfo D,DD_ArchivesUseWay E ,DD_ArchivesType F "+
		" where A.RequestID = B.ID AND A.SecrecyID=C.ID AND B.UserID = D.UserID AND a.UseWayID=E.ID AND A.ArchivesTypeID=F.ID AND A.CheckResult =0  "+
		" AND A.ID >   "+
		" (  "+
		" SELECT ISNULL(MAX(ID),0) FROM  "+ 
		" (SELECT TOP %2$s A.ID FROM ArchivesUseRequestDetails A,ArchivesUseRequest B,DD_ArchivesSecrecy C ,UserInfo D,DD_ArchivesUseWay E  "+
		" where A.RequestID = B.ID AND A.SecrecyID=C.ID AND B.UserID = D.UserID AND a.UseWayID=E.ID AND A.CheckResult =0   "+
		" ORDER BY A.ID ASC ) AS T "+		
		" ) ORDER BY A.ID ASC"; 
	
	@Override
	public boolean findAllNotExamine(DataPageInfo dataPageInfo,List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo) {
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
				int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_findAllNotExamine_COUNT);
				dataPageInfo.setRowCount(pRowCount);
				String localSQL = String.format(SQL_SELECT_findAllNotExamine, dataPageInfo.getPageSize(),(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				List<ArchivesUseRequestDetail> pEntitys = jdbcTemplate.query(localSQL , new ArchivesUseRequestDetailMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesUseRequestDetails.addAll(pEntitys);
					System.out.println("Text1:"+pEntitys.get(0).getArchivesTypeText());
					System.out.println("Text2:"+archivesUseRequestDetails.get(0).getArchivesTypeText());
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

//	private final String SQL_SELECT_findOneNotExamine = " SELECT TOP 1 A.*  "+
//	" ,B.RequestReason,B.RequestTime,B.UserDepartment,B.UserID  "+ 
//	" ,C.Name as SecrecyText  "+
//	" ,D.*  "+
//	" ,E.Name AS useWayText  "+
//	" ,F.FullName AS ArchivesTypeText  "+
//	" FROM ArchivesUseRequestDetails A,ArchivesUseRequest B,DD_ArchivesSecrecy C ,UserInfo D,DD_ArchivesUseWay E ,DD_ArchivesType F "+
//	" WHERE A.RequestID = B.ID AND A.SecrecyID=C.ID AND B.UserID = D.UserID AND a.UseWayID=E.ID AND A.ArchivesTypeID=F.ID AND A.CheckResult =0 ORDER BY ID ";
//	
	
	/**
	 * ���ҵ�һ��δ�����ĵ����������뵥��ϸ��SQL���
	 */
	private final String SQL_SELECT_findOneNotExamine = " SELECT TOP 1 A.*  "+
		" ,B.RequestReason,B.RequestTime,B.UserDepartment,B.UserID  "+ 
		" ,C.Name as SecrecyText  "+
		" ,D.*  "+		
		" ,F.FullName AS ArchivesTypeText  "+
		" FROM ArchivesUseRequestDetails A,ArchivesUseRequest B,DD_ArchivesSecrecy C ,UserInfo D,DD_ArchivesType F "+
		" WHERE A.RequestID = B.ID AND A.SecrecyID=C.ID AND B.UserID = D.UserID  AND A.ArchivesTypeID=F.ID AND A.CheckResult =0 ORDER BY ID ";
	@Override
	public boolean findOneNotExamine(IntegerEx recordsNum, ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo) {
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
				int pRowCount = jdbcTemplate.queryForInt(SQL_SELECT_findAllNotExamine_COUNT);
				recordsNum.setValue(pRowCount);
				List<ArchivesUseRequestDetail> pEntitys = jdbcTemplate.query(SQL_SELECT_findOneNotExamine , new ArchivesUseRequestDetailMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesUseRequestDetail.cloneFrom(pEntitys.get(0));
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

	/**
	 * ����Ψһ��Ż�ȡ������ϸ��ϢSQL���
	 */
	private final String SQL_SELECT_findByID = " select A.*  "+
		" ,B.RequestReason,B.RequestTime,B.UserDepartment,B.UserID  "+ 
		" ,C.Name as SecrecyText  "+
		" ,D.*  "+
		" ,E.Name as useWayText  "+
		" ,F.FullName AS ArchivesTypeText  "+
		" from ArchivesUseRequestDetails A,ArchivesUseRequest B,DD_ArchivesSecrecy C ,UserInfo D,DD_ArchivesUseWay E ,DD_ArchivesType F  "+
		" where A.RequestID = B.ID AND A.SecrecyID=C.ID AND B.UserID = D.UserID AND a.UseWayID=E.ID AND A.ID = ?";
	@Override
	public boolean findByID(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo) {
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
				List<ArchivesUseRequestDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_findByID , new ArchivesUseRequestDetailMapper(),archivesUseRequestDetail.getID());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesUseRequestDetail.cloneFrom(pEntitys.get(0));
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
				System.out.println(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	// Table Name: ArchivesUseRequestDetails
	// Columns List,Can Used in SELECT SQL: ID,RequestID,ArchivesTypeID,NBXH,ArchivesID,Title,UseWayID,CheckResult,BackReason,CheckTime,CheckUserID,Remark
	// Columns List,Can Used in INSERT SQL: :ID,:RequestID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UseWayID,:CheckResult,:BackReason,:CheckTime,:CheckUserID,:Remark
	// Columns List,Can Used in UPDATE SQL: ID=:ID,RequestID=:RequestID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,UseWayID=:UseWayID,CheckResult=:CheckResult,BackReason=:BackReason,CheckTime=:CheckTime,CheckUserID=:CheckUserID,Remark=:Remark

	/**
	 * �������������ָ���ĵ����������뵥��ϸ��SQL���
	 */
	private final String SQL_UPDATE_updateCheckResult = "UPDATE ArchivesUseRequestDetails SET CheckResult=:CheckResult,BackReason=:BackReason,CheckTime=:CheckTime,CheckUserID=:CheckUserID WHERE ID=:ID";
	@Override
	public boolean updateCheckResult(ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo) {
		System.out.println("DAO:updateCheckResult");
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
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL�Ĳ���Դ����
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

				paramSource.addValue("CheckResult", archivesUseRequestDetail.getCheckResult());
				paramSource.addValue("BackReason", archivesUseRequestDetail.getBackReason());
				paramSource.addValue("CheckTime", archivesUseRequestDetail.getCheckTime());
				paramSource.addValue("CheckUserID", archivesUseRequestDetail.getCheckUserID());
				paramSource.addValue("ID", archivesUseRequestDetail.getID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_updateCheckResult, paramSource);
			
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

	/**
	 * �������뵥��Ų������е����������뵥��ϸ��SQL���
	 */
	private final String SQL_SELECT_findByRequestID = 
		" select "+
		" A.* "+
		" ,B.StoreStatus "+
		" ,C.Name AS SecrecyText "+ 
		" ,D.FullName AS ArchivesTypeText"+
		" ,E.Name AS UseWayText "+
		" from ArchivesUseRequestDetails A ,StoreroomArchivesInfo B, DD_ArchivesSecrecy C ,DD_ArchivesType D ,DD_ArchivesUseWay E "+
		" WHERE A.NBXH = B.NBXH AND A.SecrecyID =C.ID AND A.ArchivesTypeID = D.ID AND A.UseWayID = E.ID and A.RequestID = ? ";
	
	@Override
	public boolean findByRequestID(String requestID, List<ArchivesUseRequestDetail> archivesUseRequestDetails, ErrInfo pErrInfo) {
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
				System.out.println("SQL_SELECT_findByRequestID:"+SQL_SELECT_findByRequestID);
				List<ArchivesUseRequestDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_findByRequestID , new ArchivesUseRequestDetailStoreStatusMapper() , requestID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesUseRequestDetails.addAll(pEntitys);
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

	
	
	
	

}
//private class ArchivesUseRequestDetailMapper implements RowMapper<ArchivesUseRequestDetail>
//{
//	
//	@Override
//	public ArchivesUseRequestDetail mapRow(ResultSet rs, int rowNum) throws SQLException
//	{
//		int iD = rs.getInt("ID");
//		String requestID = rs.getString("RequestID");
//		int archivesTypeID = rs.getInt("ArchivesTypeID");
//		int nBXH = rs.getInt("NBXH");
//		int secrecyID = rs.getInt("SecrecyID");
//		String archivesID = rs.getString("ArchivesID");
//		String title = rs.getString("Title");
//		int useWayID = rs.getInt("UseWayID");
//		int checkResult = rs.getInt("CheckResult");
//		String backReason = rs.getString("BackReason");
//		Date checkTime = rs.getTimestamp("CheckTime");
//		int checkUserID = rs.getInt("CheckUserID");
//		String remark = rs.getString("Remark");
//		
//		return new ArchivesUseRequestDetail(iD,requestID,archivesTypeID,nBXH,secrecyID,archivesID,title,useWayID,checkResult,backReason,checkTime,checkUserID,remark);
//	}
//}