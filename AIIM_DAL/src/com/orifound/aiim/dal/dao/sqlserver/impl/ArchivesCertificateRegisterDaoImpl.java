package com.orifound.aiim.dal.dao.sqlserver.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesCertificateRegisterDao;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DateQuerycondition;
import com.orifound.aiim.entity.ErrInfo;

public class ArchivesCertificateRegisterDaoImpl extends JdbcDaoSupport  implements IArchivesCertificateRegisterDao{
	
	/**
	 * ���캯��
	 */
	public ArchivesCertificateRegisterDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesCertificateRegisterDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesCertificateRegisterMapper implements RowMapper<ArchivesCertificateRegister>
	{
		
		@Override
		public ArchivesCertificateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int personID = rs.getInt("PersonID");
			double shouldCharge = rs.getBigDecimal("ShouldCharge").doubleValue();
			double realCharge = rs.getBigDecimal("RealCharge").doubleValue();
			Date registerDate = rs.getTimestamp("RegisterDate");
			String invoiceSN = rs.getString("InvoiceSN");
			int managerUserID = rs.getInt("ManagerUserID");
			String realName = rs.getString("RealName");
			String remark = rs.getString("Remark");
			
			return new ArchivesCertificateRegister(iD,personID,shouldCharge,realCharge,registerDate,invoiceSN,managerUserID,realName,remark);
		}
	}
	
	private class ArchivesCertificateRegisterMapper2 implements RowMapper<ArchivesCertificateRegister>
	{
		
		@Override
		public ArchivesCertificateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			double shouldCharge = rs.getBigDecimal("ShouldCharge").doubleValue();
			double realCharge = rs.getBigDecimal("RealCharge").doubleValue();
			int managerUserID = rs.getInt("ManagerUserID");
			String realName = rs.getString("RealName");
			
			return new ArchivesCertificateRegister(shouldCharge,realCharge,managerUserID,realName);
		}
	}
	
	private class ArchivesCertificateRegisterMapper3 implements RowMapper<ArchivesCertificateRegister>
	{
		
		@Override
		public ArchivesCertificateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			double shouldCharge = rs.getBigDecimal("ShouldCharge").doubleValue();
			double realCharge = rs.getBigDecimal("RealCharge").doubleValue();
			
			return new ArchivesCertificateRegister(shouldCharge,realCharge);
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
	
	/**
	 * ��ѯ����ʱ��ε�����֤�ǼǼ��ϵĵ�SQL���
	 * 
	 */
	private final String SQL_SELECT_By_Condition = " SELECT  A.ManagerUserID,SUM(A.ShouldCharge) AS ShouldCharge,SUM(A.RealCharge) AS RealCharge,B.RealName FROM ArchivesCertificateRegister A,UserInfo B "
												+" WHERE A.ManagerUserID = B.UserID AND RegisterDate BETWEEN :beginTime AND :endTime "
												+" GROUP BY A.ManagerUserID,B.RealName ORDER BY A.ManagerUserID";
	/**
	 * ��SQL���
	 */
	private final String SQL_SELECT_Count = "SELECT COUNT(*) FROM ArchivesCertificateRegister WHERE RegisterDate between  :beginTime and  :endTime AND ManagerUserID = :ManagerUserID";
	
	/**
	 * ��ѯָ�������˵�����֤�Ǽǵ�SQL���
	 */
	private final String SQL_SELECT_By_Condition_ManagerUserID = "SELECT * FROM ("+
		" SELECT  B.RealName,A.ID,A.PersonID,A.ShouldCharge,A.RealCharge,A.RegisterDate,A.InvoiceSN,A.ManagerUserID,A.Remark,ROW_NUMBER() OVER(ORDER BY ID) RowNum "
		+" FROM ArchivesCertificateRegister A,UserInfo B WHERE  A.PersonID = B.UserID  AND A.ManagerUserID = :ManagerUserID  AND RegisterDate BETWEEN  :beginTime AND  :endTime "
		+") AS P  WHERE P.RowNum BETWEEN :startPage AND :endPage "
		+" ORDER BY RegisterDate DESC";	
	
	/**															
	 * 															
	 * ����ʱ���ͳ�Ƶ�SQL���
	 */
	private final String SQL_SELECT_Total = "SELECT SUM(A.ShouldCharge) ShouldCharge,SUM(A.RealCharge) RealCharge FROM ArchivesCertificateRegister A "
											+" WHERE  A.RegisterDate BETWEEN :beginTime AND :endTime" ;
	@Override
	public boolean delete(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByCondition(DateQuerycondition dateQuerycondition, List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
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
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("beginTime", dateQuerycondition.getBeginTime(),Types.DATE);
				sqlParameterSource.addValue("endTime", dateQuerycondition.getEndTime(),Types.DATE);
				List<ArchivesCertificateRegister> pEntitys = namedParameterJdbcTemplate.query(SQL_SELECT_By_Condition,sqlParameterSource, new ArchivesCertificateRegisterMapper2());
					//���ز�ѯ���
				if (pEntitys.size() > 0) {
						pArchivesCertificateRegisters.addAll(pEntitys);
					}	
				//��ѯ�ܼƽ��
				List<ArchivesCertificateRegister> pEntitys2 = namedParameterJdbcTemplate.query(SQL_SELECT_Total,sqlParameterSource, new ArchivesCertificateRegisterMapper3());
				if (pEntitys2.size() >0) {
					pArchivesCertificateRegisters.add(pEntitys2.get(0));
				}
				//���پֲ�����
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
	public boolean findByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition, DataPageInfo dataPageInfo, List<ArchivesCertificateRegister> pArchivesCertificateRegisters,
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
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("beginTime", dateQuerycondition.getBeginTime(),Types.DATE);
				sqlParameterSource.addValue("endTime", dateQuerycondition.getEndTime(),Types.DATE);
				sqlParameterSource.addValue("ManagerUserID", pManagerUserID,Types.INTEGER);
				
				int rowCount = namedParameterJdbcTemplate.queryForInt(SQL_SELECT_Count,sqlParameterSource);
				dataPageInfo.setRowCount(rowCount);
				
				MapSqlParameterSource sqlParameterSource2 = new MapSqlParameterSource();
				sqlParameterSource2.addValue("beginTime", dateQuerycondition.getBeginTime(),Types.DATE);
				sqlParameterSource2.addValue("endTime", dateQuerycondition.getEndTime(),Types.DATE);
				sqlParameterSource2.addValue("ManagerUserID", pManagerUserID,Types.INTEGER);
				sqlParameterSource2.addValue("startPage", 1+(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize(),Types.INTEGER);
				sqlParameterSource2.addValue("endPage", dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(),Types.INTEGER);
				List<ArchivesCertificateRegister> pEntitys = namedParameterJdbcTemplate.query(SQL_SELECT_By_Condition_ManagerUserID,sqlParameterSource2, new ArchivesCertificateRegisterMapper());
					//���ز�ѯ���
				if (pEntitys.size() > 0) {
						pArchivesCertificateRegisters.addAll(pEntitys);
					}	
		
				//���پֲ�����
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

}
