/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStudentGradeInfoDao;
import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentGradeInfo;

/**
 * 学生成绩信息表的DAO接口实现类(SQL Server平台实现)
 *
 */
public class StudentGradeInfoDaoImpl extends JdbcDaoSupport implements IStudentGradeInfoDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class CourseMapper implements RowMapper<Course>
	{
		
		@Override
		public Course mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String courseNameCN = rs.getString("CourseNameCN");
			String courseNameEN = rs.getString("CourseNameEN");
			boolean electivesFlag = rs.getBoolean("ElectivesFlag");
			String shortKey = rs.getString("ShortKey");
			String remark = rs.getString("Remark");
			
			return new Course(courseNameCN,courseNameEN,electivesFlag,shortKey,remark);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 */
	private class StudentGradeInfoMapper implements RowMapper<StudentGradeInfo>
	{
		@Override
		public StudentGradeInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String xH = rs.getString("XH");
			String courseNameCN = rs.getString("CourseNameCN");
			String term = rs.getString("Term");
			String totalHour = rs.getString("TotalHour");
			String credit = rs.getString("Credit");
			String grade = rs.getString("Grade");
			
			return new StudentGradeInfo(iD,xH,courseNameCN,term,totalHour,credit,grade);
		}
	}

	/**
	 * 构造函数
	 */
	public StudentGradeInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StudentGradeInfoDaoImpl(DataSource dataSource) {
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
	 * 根据id查询学生成绩信息的SQL语句
	 */
	private final String SQL_SELECT_byId = "SELECT * FROM StudentGradeInfo WHERE ID=?";
	
	/**
	 * 插入学生成信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StudentGradeInfo(XH,CourseNameCN,Term,TotalHour,Credit,Grade) VALUES(:XH,:CourseNameCN,:Term,:TotalHour,:Credit,:Grade)";
	
	/**
	 * 插入学生的所有课程信息的SQL语句
	 */
	private final String SQL_INSERT_StudentAllGrade = "INSERT INTO StudentGradeInfo(XH,CourseNameCN,Term,TotalHour,Credit)" +
			" SELECT cs.XH,c.CourseNameCN,mcs.Term,mcs.TotalHour,mcs.Credit FROM MajorSetting ms,MajorCourseSetting mcs,DD_Course c,CertificateStudent cs" +
			" WHERE ms.ID=mcs.MajorSettingID AND mcs.CourseNameCN=c.CourseNameCN AND cs.MajorNameCN=ms.MajorNameCN AND DATEPART(year,cs.EntranceDate)=ms.EntranceYear AND cs.XH=?";
	
	/**
	 * 查询指定学生的所有课程成绩信息的SQL语句
	 */
	private final String SQL_SELECT_StudentGradeInfo = "select g.* from StudentGradeInfo g" +
			" LEFT JOIN MajorCourseSetting mcs ON g.CourseNameCN=mcs.CourseNameCN" +
			" WHERE g.XH=? ORDER BY CASE WHEN mcs.OrderID IS NULL THEN 1 ELSE 0 END ASC";
	
	/**
	 * 批量更新学生成绩信息的SQL语句
	 */
	private final String SQL_BatchUpdate_StudentGradeInfo = "UPDATE StudentGradeInfo SET Term=?,TotalHour=?,Credit=?,Grade=? WHERE ID=?";
	
	/**
	 * 查询课程信息集合的SQL语句
	 * 过滤已经选项的公选课
	 */
	private final String SQL_SELECT_AllElectivesCourse = "SELECT * FROM DD_Course WHERE CourseNameCN LIKE ? AND electivesFlag=? AND CourseNameCN NOT IN(SELECT CourseNameCN FROM StudentGradeInfo WHERE XH=?) ORDER BY CourseNameCN ASC";
	
	/**
	 * 批量插入公选课程数组的SQL语句
	 * %1$s 学号
	 */
	private final String SQL_INSERT_ElectivesCourse = "INSERT INTO StudentGradeInfo(XH,CourseNameCN,Term,TotalHour,Credit,Grade) SELECT %1$s,CourseNameCN,NULL,NULL,NULL,NULL FROM DD_Course WHERE CourseNameCN IN (:CourseNameCNS)";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStudentGradeInfoDao#delete(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStudentGradeInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<StudentGradeInfo> studentGradeInfos,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStudentGradeInfoDao#findByID(int, com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, StudentGradeInfo studentGradeInfo,
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
				List<StudentGradeInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_byId, new StudentGradeInfoMapper(),pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					studentGradeInfo.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.IStudentGradeInfoDao#save(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo) {
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
				paramSource.addValue("XH", studentGradeInfo.getXH(),Types.VARCHAR);
				paramSource.addValue("CourseNameCN", studentGradeInfo.getCourseNameCN(),Types.VARCHAR);
				paramSource.addValue("Term", studentGradeInfo.getTerm(),Types.VARCHAR);
				paramSource.addValue("TotalHour", studentGradeInfo.getTotalHour(),Types.VARCHAR);
				paramSource.addValue("Credit", studentGradeInfo.getCredit(),Types.VARCHAR);
				paramSource.addValue("Grade", studentGradeInfo.getGrade(),Types.VARCHAR);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
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
	 * @see com.orifound.aiim.dal.dao.IStudentGradeInfoDao#update(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean saveStudentAllGrade(String XH, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_INSERT_StudentAllGrade, XH);
				
				System.out.println("插入学生成绩:"+SQL_INSERT_StudentAllGrade);

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
	public boolean findStudentAllGrade(String XH, List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
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
				List<StudentGradeInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_StudentGradeInfo, new StudentGradeInfoMapper(), XH);

				//返回查询结果
				if (pEntitys.size() > 0) {
					studentGradeInfos.addAll(pEntitys);
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
	public boolean updateStudentAllGrade(String XH, final List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
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
				//UPDATE StudentGradeInfo SET Term=?,TotalHour=?,Credit=?,Grade=? WHERE ID=?
				BatchPreparedStatementSetter preparedStatementSetter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)throws SQLException {
						ps.setString(1, studentGradeInfos.get(i).getTerm());
						ps.setString(2, studentGradeInfos.get(i).getTotalHour());
						ps.setString(3, studentGradeInfos.get(i).getCredit());
						ps.setString(4, studentGradeInfos.get(i).getGrade());
						ps.setInt(5, studentGradeInfos.get(i).getID());
					}
					//返回执行次数
					public int getBatchSize() {
						return studentGradeInfos.size();
					}
				};
				getJdbcTemplate().batchUpdate(SQL_BatchUpdate_StudentGradeInfo,preparedStatementSetter);
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
	public boolean findAllElectivesCourseByName(String name, String XH, String ElectivesFlag, List<Course> courses, ErrInfo pErrInfo) {
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
				List<Course> pEntitys=jdbcTemplate.query(SQL_SELECT_AllElectivesCourse, new CourseMapper(), "%"+name+"%",ElectivesFlag, XH);

				//返回查询结果
				if (pEntitys.size() > 0) {
					courses.addAll(pEntitys);
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
	public boolean saveElectivesCourse(String XH, String[] courseNames, List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
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
				List<String> courseNameList = Arrays.asList(courseNames);
				paramSource.addValue("CourseNameCNS", courseNameList);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(String.format(SQL_INSERT_ElectivesCourse, "'"+XH+"'"), paramSource);
				
				if (findStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询指定学生的所有课程成绩信息 失败：");
				}
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
