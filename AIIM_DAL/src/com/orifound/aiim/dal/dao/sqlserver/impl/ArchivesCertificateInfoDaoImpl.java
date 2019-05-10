/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������֤��Ϣ����DAOʵ���ࣨSQL Server ƽ̨��
 *
 */
public class ArchivesCertificateInfoDaoImpl extends JdbcDaoSupport implements IArchivesCertificateInfoDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesCertificateInfoMapper implements RowMapper<ArchivesCertificateInfo>
	{
		
		@Override
		public ArchivesCertificateInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");

			int certificateRegID = rs.getInt("CertificateRegID");
			String name = rs.getString("Name");

			int certificateTypeID = rs.getInt("CertificateTypeID");
			int total = rs.getInt("Total");
			boolean expressFlag = rs.getBoolean("ExpressFlag");

			String certificateSN = rs.getString("CertificateSN");
			String xH = rs.getString("XH");
			boolean dealedFlag = rs.getBoolean("DealedFlag");
			Date fileUploadDate = rs.getTimestamp("FileUploadDate");
			String certificateFileName = rs.getString("CertificateFileName");
			
			return new ArchivesCertificateInfo(iD,certificateRegID,name,certificateTypeID,total,expressFlag,certificateSN,xH,dealedFlag,fileUploadDate,certificateFileName);
		}
	}
	
	private class ArchivesCertificateInfoMapperOther implements RowMapper<ArchivesCertificateInfo>
	{
		
		@Override
		public ArchivesCertificateInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int certificateRegID = rs.getInt("CertificateRegID");
			int certificateTypeID = rs.getInt("CertificateTypeID");
			int total = rs.getInt("Total");
			boolean expressFlag = rs.getBoolean("ExpressFlag");
			String certificateSN = rs.getString("CertificateSN");
			String xH = rs.getString("XH");
			boolean dealedFlag = rs.getBoolean("DealedFlag");
			Date fileUploadDate = rs.getTimestamp("FileUploadDate");
			String certificateFileName = rs.getString("CertificateFileName");
			ArchivesCertificateInfo archivesCertificateInfo = new ArchivesCertificateInfo(iD,certificateRegID,certificateTypeID,total,expressFlag,certificateSN,xH,dealedFlag,fileUploadDate,certificateFileName);
			String certificateTypeName = rs.getString("certificateTypeName");
			archivesCertificateInfo.setCertificateTypeName(certificateTypeName);
			String templateFileName = rs.getString("templateFileName");
			archivesCertificateInfo.setTemplateFileName(templateFileName);
			boolean gradeFlag = rs.getBoolean("GradeFlag");
			archivesCertificateInfo.setGradeFlag(gradeFlag);
			return archivesCertificateInfo;

		}
	}

	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class CertificateTypeMapper implements RowMapper<CertificateType>
	{
		
		@Override
		public CertificateType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			double generalPrice = rs.getBigDecimal("GeneralPrice").doubleValue();
			double expressPrice = rs.getBigDecimal("ExpressPrice").doubleValue();
			boolean gradeFlag = rs.getBoolean("GradeFlag");
			String templateFileName = rs.getString("TemplateFileName");
			String remark = rs.getString("Remark");
			
			return new CertificateType(iD,name,generalPrice,expressPrice,gradeFlag,templateFileName,remark);
		}
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
			String remark = rs.getString("Remark");
			ArchivesCertificateRegister certificateRegister =  new ArchivesCertificateRegister(iD,personID,shouldCharge,realCharge,registerDate,invoiceSN,managerUserID,remark);
			String personName = rs.getString("personName");
			String managerUserName = rs.getString("managerUserName");
			String cardNo = rs.getString("cardNo");
			certificateRegister.setPersonName(personName);
			certificateRegister.setManagerUserName(managerUserName);
			certificateRegister.setCardNo(cardNo);
			return certificateRegister;
		}
	}
	
	/**
	 * ���캯��
	 */
	public ArchivesCertificateInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesCertificateInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return �����ɹ�����true�����򷵻�false
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
	
	/**
	 * ��ѯ������֤������Ϣ��SQL���
	 */
	private final String SQL_SELECT_CertificateTypes = "SELECT * FROM DD_CertificateType ORDER BY ID ASC";
	
	/**
	 * ���ݳ�֤�ǼǱ�Ų�ѯ������֤��ϸ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_By_CertificateRegID  = "SELECT A.*, B.Name FROM ArchivesCertificateInfo A,DD_CertificateType B WHERE A.CertificateTypeID = B.ID AND A.CertificateRegID = ? ORDER BY A.ID";
	/**
	 * ���뵵���Ǽ���Ϣ��SQL���
	 */
	private final String SQL_INSERT_ArchivesCertificateRegister = "INSERT INTO ArchivesCertificateRegister(PersonID,ShouldCharge,RealCharge,RegisterDate,InvoiceSN,ManagerUserID,Remark) " +
				"VALUES(:PersonID,:ShouldCharge,:RealCharge,:RegisterDate,:InvoiceSN,:ManagerUserID,:Remark)";
	
	/**
	 * ���뵵����֤��Ϣ��SQL���
	 */
	private final String SQL_INSERT_ArchivesCertificateInfo = "INSERT INTO ArchivesCertificateInfo(CertificateRegID,CertificateTypeID,Total,ExpressFlag) VALUES(?,?,?,?)";
	
	/**
	 * ����������ѯ������֤�Ǽ���Ϣ��SQL���
	 * %1$s ��ѯ����
	 * and u.IDCardNo like '%1%' and u.Name like '%1%'
	 * 
	 */
	private final String SQL_SELECT_Register_ByQuery = "SELECT r.*,u.Name personName,ui.RealName managerUserName,u.IDCardNo cardNo FROM ArchivesCertificateRegister r,ArchivesUsePersonInfo u,UserInfo ui" +
			" WHERE r.ManagerUserID=ui.UserID AND r.PersonID=u.ID %1$s ORDER BY r.RegisterDate DESC";
	
	/**
	 * ���ݵǼ�id��ѯ�Ǽ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_by_RegisterId = "SELECT c.*, t.Name certificateTypeName, t.templateFileName, t.GradeFlag  FROM ArchivesCertificateInfo c,DD_CertificateType t " +
			"WHERE c.CertificateTypeID=t.ID AND c.CertificateRegID=? ORDER BY FileUploadDate ASC";
	
	/**
	 * ����id��ѯ������SQL���
	 */
	private final String SQL_SELECT_ArchivesCertificateInfo_byId = "SELECT * FROM ArchivesCertificateInfo WHERE ID=?";
	
	/**
	 * ���³�֤��ϸ��Ϣ��SQL���
	 */
	private final String SQL_UPDATE_ArchivesCertificateInfo = "UPDATE ArchivesCertificateInfo SET CertificateFileName=:certificateFileName,FileUploadDate=:fileUploadDate,DealedFlag=:dealedFlag WHERE ID=:ID";
	
	/**
	 * ���³�֤��Ϣ����ѧ�ŵ�SQL���
	 */
	private final String SQL_UPDATE_XH = "UPDATE ArchivesCertificateInfo SET XH=? WHERE ID=?";
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao#delete(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesCertificateInfo archivesCertificateInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao#findByID(int, com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
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
				List<ArchivesCertificateInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_ArchivesCertificateInfo_byId,new ArchivesCertificateInfoMapper(),pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesCertificateInfo.cloneFrom(pEntitys.get(0));
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
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao#save(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesCertificateRegister certificateRegister, final List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo) {boolean pFlag = true;
	System.out.println("dao save");		
			int pErrPos = 0;
			Throwable throwable = new Throwable();
	
			try {
				//���JDBC����Դ�Ƿ���ȷ����ע��
				if (CheckDataSourceInjection(pErrInfo) == false) {
					pFlag = false;
				}
	
				//ִ��SQL���
				if (pFlag) {
					pErrPos = 1;
					PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT_ArchivesCertificateRegister);
					//����SQLִ�гɹ��󷵻ص�����
					GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
					//���������ֶ���
					pscFactory.setGeneratedKeysColumnNames(new String[] { "ID" });
					pErrPos = 2;
					NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
					MapSqlParameterSource paramSource = new MapSqlParameterSource();
					paramSource.addValue("PersonID",certificateRegister.getPersonID(), Types.INTEGER);
					paramSource.addValue("ShouldCharge",certificateRegister.getShouldCharge(), Types.DOUBLE);
					paramSource.addValue("RealCharge",certificateRegister.getRealCharge(), Types.DOUBLE);
					paramSource.addValue("RegisterDate",certificateRegister.getRegisterDate(), Types.TIMESTAMP);
					paramSource.addValue("InvoiceSN",certificateRegister.getInvoiceSN(), Types.VARCHAR);
					paramSource.addValue("ManagerUserID",certificateRegister.getManagerUserID(), Types.INTEGER);
					paramSource.addValue("Remark",certificateRegister.getRemark(), Types.VARCHAR);
					pErrPos = 3;
					namedParameterJdbcTemplate.update(SQL_INSERT_ArchivesCertificateRegister, paramSource, keyHolder);
	
					//�����Զ����ɵ��ڲ����
					certificateRegister.setID(keyHolder.getKey().intValue());
					final int certificateRegisterId = keyHolder.getKey().intValue();
					System.out.println("���뵵���Ǽ���ϢSQL="+SQL_INSERT_ArchivesCertificateRegister);
					System.out.println("certificateRegisterId="+certificateRegisterId);
					
					
	//				INSERT INTO ArchivesCertificateInfo(CertificateRegID,CertificateTypeID,Total,ExpressFlag) VALUES(?,?,?,?)
					//���������֤��ϸ��Ϣ
					pErrPos = 3;
					BatchPreparedStatementSetter preparedStatementSetter = new BatchPreparedStatementSetter() {
						public void setValues(PreparedStatement ps, int i)throws SQLException {
							ps.setInt(1, certificateRegisterId);
							ps.setInt(2, archivesCertificateInfos.get(i).getCertificateTypeID());
							ps.setInt(3, archivesCertificateInfos.get(i).getTotal());
							ps.setBoolean(4, archivesCertificateInfos.get(i).getExpressFlag());
						}
						//����ִ�д���
						public int getBatchSize() {
							return archivesCertificateInfos.size();
						}
					};
					
					getJdbcTemplate().batchUpdate(SQL_INSERT_ArchivesCertificateInfo,preparedStatementSetter);
					
					//���پֲ�����
					namedParameterJdbcTemplate = null;
					paramSource = null;
					pscFactory = null;
					keyHolder = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesCertificateInfoDao#update(com.orifound.aiim.entity.ArchivesCertificateInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
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
				//UPDATE ArchivesCertificateInfo SET CertificateFileName=:certificateFileName,FileUploadDate=:fileUploadDate,DealedFlag=:dealedFlag WHERE ID=:ID
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("certificateFileName", archivesCertificateInfo.getCertificateFileName(), Types.VARCHAR);
				paramSource.addValue("fileUploadDate", archivesCertificateInfo.getFileUploadDate(), Types.TIMESTAMP);
				paramSource.addValue("dealedFlag", archivesCertificateInfo.getDealedFlag(), Types.BOOLEAN);
				paramSource.addValue("ID", archivesCertificateInfo.getID(), Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE_ArchivesCertificateInfo, paramSource);

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
	public boolean findCertificateTypes(List<CertificateType> certificateTypes, ErrInfo pErrInfo) {
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
				List<CertificateType> pEntitys=jdbcTemplate.query(SQL_SELECT_CertificateTypes,new CertificateTypeMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					certificateTypes.addAll(pEntitys);
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
	public boolean findByCertificateRegID(int pCertificateRegID,ArchivesCertificateInfo archivesCertificateInfo, ErrInfo pErrInfo) {
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
				List<ArchivesCertificateInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_By_CertificateRegID,new ArchivesCertificateInfoMapper(),pCertificateRegID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesCertificateInfo.cloneFrom(pEntitys.get(0));
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
	public boolean findArchivesCertificateRegistersByQuery(
			Map<String, String> queryString,
			List<ArchivesCertificateRegister> archivesCertificateRegisters,
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
				StringBuffer buffer = new StringBuffer();
				//����������personName
				String personName = queryString.get("personName");
				if(StringTool.checkNull(personName)) {
					paramSource.addValue("personName", "%"+personName+"%", Types.VARCHAR);
					buffer.append("AND u.Name like :personName ");
				}
				//������֤����cardId
				String cardId = queryString.get("cardId");
				if(StringTool.checkNull(cardId)) {
					paramSource.addValue("cardId", "%"+cardId+"%", Types.VARCHAR);
					buffer.append("AND u.IDCardNo like :cardId ");
				}

				pErrPos = 3;
				List<ArchivesCertificateRegister> pList = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_Register_ByQuery, buffer.toString()), paramSource, new ArchivesCertificateRegisterMapper());
				System.out.println("��ѯ������֤��Ϣ��"+String.format(SQL_SELECT_Register_ByQuery, buffer.toString()));	
				
				if (pList.size() >= 1) {
					archivesCertificateRegisters.addAll(pList);
				}
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
	public boolean findByRegisterId(int certificateRegID, List<ArchivesCertificateInfo> archivesCertificateInfos, ErrInfo pErrInfo) {
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
				List<ArchivesCertificateInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_by_RegisterId,new ArchivesCertificateInfoMapperOther(), certificateRegID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesCertificateInfos.addAll(pEntitys);
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
	public boolean updateXH(ArchivesCertificateInfo archivesCertificateInfo,
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_UPDATE_XH, archivesCertificateInfo.getXH(), archivesCertificateInfo.getID());
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

}