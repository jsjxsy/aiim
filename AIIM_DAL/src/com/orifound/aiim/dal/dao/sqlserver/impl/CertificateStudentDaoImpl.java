/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ICertificateStudentDao;
import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * ��֤ѧ����Ϣ���DAOʵ�֣�SQL Server ƽ̨��
 *
 */
public class CertificateStudentDaoImpl extends JdbcDaoSupport implements ICertificateStudentDao {
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class CertificateStudentMapper implements RowMapper<CertificateStudent>
	{
		
		@Override
		public CertificateStudent mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String xH = rs.getString("XH");
			String nameCN = rs.getString("NameCN");
			String nameEN = rs.getString("NameEN");
			String majorNameCN = rs.getString("MajorNameCN");
			String collegeNameCN = rs.getString("CollegeNameCN");
			Date entranceDate = rs.getTimestamp("EntranceDate");
			Date graduateDate = rs.getTimestamp("GraduateDate");
			Date gradeRecordDate = rs.getTimestamp("GradeRecordDate");
			
			return new CertificateStudent(xH,nameCN,nameEN,majorNameCN,collegeNameCN,entranceDate,graduateDate,gradeRecordDate);
		}
	}
	

	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class MajorMapper implements RowMapper<Major>
	{
		
		@Override
		public Major mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String majorNameCN = rs.getString("MajorNameCN");
			String majorNameEN = rs.getString("MajorNameEN");
			String shortKey = rs.getString("ShortKey");
			String remark = rs.getString("Remark");
			
			return new Major(majorNameCN,majorNameEN,shortKey,remark);
		}
	}
	

	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class CollegeMapper implements RowMapper<College>
	{
		
		@Override
		public College mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String collegeNameCN = rs.getString("CollegeNameCN");
			String collegeNameEN = rs.getString("CollegeNameEN");
			String remark = rs.getString("Remark");
			
			return new College(collegeNameCN,collegeNameEN,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public CertificateStudentDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public CertificateStudentDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
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
	 * ����ѧ����Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO CertificateStudent(XH,NameCN,NameEN,MajorNameCN,CollegeNameCN,EntranceDate,GraduateDate) " +
			"VALUES(:XH,:NameCN,:NameEN,:MajorNameCN,:CollegeNameCN,:EntranceDate,:GraduateDate)";

	
	/**
	 * ����ѧ�Ų�ѯѧ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_byId = "SELECT * FROM CertificateStudent WHERE XH=?";
	
	/**
	 * ����ѧ����Ϣ��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE CertificateStudent SET NameCN=:NameCN,NameEN=:NameEN,MajorNameCN=:MajorNameCN,CollegeNameCN=:CollegeNameCN,EntranceDate=:EntranceDate,GraduateDate=:GraduateDate WHERE XH=:XH";
	
	/**
	 * ��ѯ����רҵ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_MAJOR = "SELECT * FROM DD_Major";
	
	/**
	 * ��ѯ����ѧԺ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_COLLEGE = "SELECT * FROM DD_College";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#delete(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(CertificateStudent certificateStudent,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<CertificateStudent> certificateStudents,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#findByID(int, com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(String pID, CertificateStudent certificateStudent,
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
				List<CertificateStudent> pEntitys=jdbcTemplate.query(SQL_SELECT_byId,new CertificateStudentMapper(),pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					certificateStudent.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#save(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
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
				paramSource.addValue("XH", certificateStudent.getXH(), Types.VARCHAR);
				paramSource.addValue("NameCN", certificateStudent.getNameCN(), Types.VARCHAR);
				paramSource.addValue("NameEN", certificateStudent.getNameEN(), Types.VARCHAR);
				paramSource.addValue("MajorNameCN", certificateStudent.getMajorNameCN(), Types.VARCHAR);
				paramSource.addValue("CollegeNameCN", certificateStudent.getCollegeNameCN(), Types.VARCHAR);
				paramSource.addValue("EntranceDate", certificateStudent.getEntranceDate(), Types.TIMESTAMP);
				paramSource.addValue("GraduateDate", certificateStudent.getGraduateDate(), Types.TIMESTAMP);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				System.out.println("����ѧ����Ϣ="+SQL_INSERT);
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
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#update(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
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
				paramSource.addValue("XH", certificateStudent.getXH(), Types.VARCHAR);
				paramSource.addValue("NameCN", certificateStudent.getNameCN(), Types.VARCHAR);
				paramSource.addValue("NameEN", certificateStudent.getNameEN(), Types.VARCHAR);
				paramSource.addValue("MajorNameCN", certificateStudent.getMajorNameCN(), Types.VARCHAR);
				paramSource.addValue("CollegeNameCN", certificateStudent.getCollegeNameCN(), Types.VARCHAR);
				paramSource.addValue("EntranceDate", certificateStudent.getEntranceDate(), Types.TIMESTAMP);
				paramSource.addValue("GraduateDate", certificateStudent.getGraduateDate(), Types.TIMESTAMP);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				System.out.println("����ѧ����Ϣ��"+SQL_UPDATE);
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
	public boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo) {
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
				List<College> pEntitys=jdbcTemplate.query(SQL_SELECT_COLLEGE, new CollegeMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					colleges.addAll(pEntitys);
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
	public boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo) {
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
				List<Major> pEntitys=jdbcTemplate.query(SQL_SELECT_MAJOR,new MajorMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					majors.addAll(pEntitys);
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
}