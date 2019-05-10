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
 * 出证学生信息表的DAO实现（SQL Server 平台）
 *
 */
public class CertificateStudentDaoImpl extends JdbcDaoSupport implements ICertificateStudentDao {
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
	 * 构造函数
	 */
	public CertificateStudentDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public CertificateStudentDaoImpl(DataSource dataSource) {
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
	
	/**
	 * 插入学生信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO CertificateStudent(XH,NameCN,NameEN,MajorNameCN,CollegeNameCN,EntranceDate,GraduateDate) " +
			"VALUES(:XH,:NameCN,:NameEN,:MajorNameCN,:CollegeNameCN,:EntranceDate,:GraduateDate)";

	
	/**
	 * 根据学号查询学生信息的SQL语句
	 */
	private final String SQL_SELECT_byId = "SELECT * FROM CertificateStudent WHERE XH=?";
	
	/**
	 * 更新学生信息的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE CertificateStudent SET NameCN=:NameCN,NameEN=:NameEN,MajorNameCN=:MajorNameCN,CollegeNameCN=:CollegeNameCN,EntranceDate=:EntranceDate,GraduateDate=:GraduateDate WHERE XH=:XH";
	
	/**
	 * 查询所有专业信息的SQL语句
	 */
	private final String SQL_SELECT_MAJOR = "SELECT * FROM DD_Major";
	
	/**
	 * 查询所有学院信息的SQL语句
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
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<CertificateStudent> pEntitys=jdbcTemplate.query(SQL_SELECT_byId,new CertificateStudentMapper(),pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					certificateStudent.cloneFrom(pEntitys.get(0));
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#save(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
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
				paramSource.addValue("XH", certificateStudent.getXH(), Types.VARCHAR);
				paramSource.addValue("NameCN", certificateStudent.getNameCN(), Types.VARCHAR);
				paramSource.addValue("NameEN", certificateStudent.getNameEN(), Types.VARCHAR);
				paramSource.addValue("MajorNameCN", certificateStudent.getMajorNameCN(), Types.VARCHAR);
				paramSource.addValue("CollegeNameCN", certificateStudent.getCollegeNameCN(), Types.VARCHAR);
				paramSource.addValue("EntranceDate", certificateStudent.getEntranceDate(), Types.TIMESTAMP);
				paramSource.addValue("GraduateDate", certificateStudent.getGraduateDate(), Types.TIMESTAMP);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				System.out.println("新增学生信息="+SQL_INSERT);
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICertificateStudentDao#update(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
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
				paramSource.addValue("XH", certificateStudent.getXH(), Types.VARCHAR);
				paramSource.addValue("NameCN", certificateStudent.getNameCN(), Types.VARCHAR);
				paramSource.addValue("NameEN", certificateStudent.getNameEN(), Types.VARCHAR);
				paramSource.addValue("MajorNameCN", certificateStudent.getMajorNameCN(), Types.VARCHAR);
				paramSource.addValue("CollegeNameCN", certificateStudent.getCollegeNameCN(), Types.VARCHAR);
				paramSource.addValue("EntranceDate", certificateStudent.getEntranceDate(), Types.TIMESTAMP);
				paramSource.addValue("GraduateDate", certificateStudent.getGraduateDate(), Types.TIMESTAMP);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				System.out.println("更新学生信息："+SQL_UPDATE);
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
	public boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo) {
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
				List<College> pEntitys=jdbcTemplate.query(SQL_SELECT_COLLEGE, new CollegeMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					colleges.addAll(pEntitys);
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
	public boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo) {
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
				List<Major> pEntitys=jdbcTemplate.query(SQL_SELECT_MAJOR,new MajorMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					majors.addAll(pEntitys);
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
}