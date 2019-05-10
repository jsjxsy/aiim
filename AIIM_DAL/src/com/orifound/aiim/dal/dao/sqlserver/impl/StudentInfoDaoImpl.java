package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStudentInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ImportDataitemsMapping;
import com.orifound.aiim.entity.StudentInfo;

/**
 * 学生信息表DAO实现类
 * @author Administrator
 *
 */
public class StudentInfoDaoImpl extends JdbcDaoSupport implements IStudentInfoDao {

	private class StudentInfoMapper implements RowMapper<StudentInfo>
	{
		@Override
		public StudentInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int nBXH = rs.getInt("NBXH");
			Date saveDate = rs.getTimestamp("SaveDate");
			int workFlowStatus = rs.getInt("WorkFlowStatus");
			String barcode = rs.getString("Barcode");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			int moveOutRegID = rs.getInt("MoveOutRegID");
			String studentId = rs.getString("xh");
			String studentName = rs.getString("xm");
			String sex = rs.getString("xb");
			String startSchoolYear = rs.getString("rxnd");
			String finishSchoolYear = rs.getString("bynd");
			String college = rs.getString("xy");
			String specialty = rs.getString("zy");
			String grade = rs.getString("bj");
			String companyAddr = rs.getString("dwszd");
			String companyName = rs.getString("dwmc");
			String phone = rs.getString("lxdh");
			String mailingCompany = rs.getString("dayjbm");
			String receivePostcode = rs.getString("yzbm");
			String mailingAddr = rs.getString("dayjdz");
			String reMark = rs.getString("bz");
			
			//return new StudentInfo(nBXH,saveDate,workFlowStatus,barcode,archivesTypeID,archivesID,title,moveOutRegID,xh,xm,xb,rxnd,bynd,xy,zy,bj,dwszd,dwmc,lxdh,dayjbm,yzbm,dayjdz,bz);
		    return	new	StudentInfo( nBXH, saveDate, workFlowStatus, barcode, archivesTypeID, archivesID, title, moveOutRegID, studentId,studentName, sex, 
		    		startSchoolYear, finishSchoolYear, college, specialty, grade, companyAddr, companyName, phone, mailingCompany, receivePostcode, mailingAddr,
					 reMark);
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
	
	@Override
	public boolean add(List<StudentInfo> studentInfos, ErrInfo pErrInfo) {
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
				
				String sql = "INSERT INTO StudentInfo (WorkFlowStatus %1$s) VALUES (11 %2$s)";
				
				String SELECT_SQL = "SELECT * FROM StudentInfo WHERE xh=?";
				String fieldNames = "";
				String fieldValues = "";
				for (StudentInfo studentInfo : studentInfos) {
					fieldNames = ",ArchivesTypeID,ArchivesID,Title,Barcode";
					fieldValues = "," + studentInfo.getArchivesTypeID() + ",'" + studentInfo.getArchivesID() + "','" + studentInfo.getTitle() + "','" + studentInfo.getBarcode()+ "'";
					Map<String, ImportDataitemsMapping> importDataitemsMappings = studentInfo.getImportDataitemsMappings();
					for (Iterator<String> iterator = importDataitemsMappings.keySet().iterator(); iterator.hasNext();) {
						String key =  iterator.next();
						ImportDataitemsMapping importDataitemsMapping = importDataitemsMappings.get(key);
						fieldNames += "," + importDataitemsMapping.getDesTableFiedName();
						fieldValues += "," + "'" + importDataitemsMapping.getValue() + "'";
					}
					String localSql = String.format(sql, fieldNames, fieldValues);
					List<StudentInfo> studentInfos2 = getJdbcTemplate().query(SELECT_SQL, new Object[]{studentInfo.getArchivesID()}, new StudentInfoMapper());
					
					//若够没找到
					if(studentInfos2.size() == 0){
						getJdbcTemplate().update(localSql);
						getJdbcTemplate().update("INSERT INTO StudentDocsInfo (NBXH,DocID,ExistsFlag) SELECT T2.NBXH,T1.ID,0 FROM DD_StudentDoc T1 INNER JOIN StudentInfo T2 ON T2.xh='"+ studentInfo.getArchivesID() +"'");
					}
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean find(String xyName, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<StudentInfo> studentInfos, ErrInfo pErrInfo) {
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
				
				MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
				parameterSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				parameterSource.addValue("xy", xyName);
				
				String sql_count = "SELECT COUNT(*) FROM StudentInfo WHERE WorkFlowStatus=:WorkFlowStatus AND xy=:xy";		
				dataPageInfo.setRowCount( new NamedParameterJdbcTemplate(getDataSource()).queryForInt(sql_count, parameterSource));
				
				String sql = "SELECT * FROM (SELECT ROW_NUMBER() over(ORDER BY saveDate) AS rownum, * FROM StudentInfo WHERE WorkFlowStatus=:WorkFlowStatus AND xy=:xy) T1 WHERE T1.rownum>:startRow AND T1.rownum<=:endRow";
				parameterSource.addValue("startRow", (dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				parameterSource.addValue("endRow", dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize());
				List<StudentInfo> pStudentInfos = new NamedParameterJdbcTemplate(getDataSource()).query(sql, parameterSource,new StudentInfoMapper());
				
				if (pStudentInfos.size()>0) {
					studentInfos.addAll(pStudentInfos);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM StudentInfo WHERE NBXH=?";
				List<StudentInfo> studentInfos = getJdbcTemplate().query(sql, new Object[]{nbxh}, new StudentInfoMapper());
				if (studentInfos.size() > 0) {
					studentInfo.cloneFrom(studentInfos.get(0));
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateWorkFlowStatus(int nbxh, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
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
				String sql = "UPDATE StudentInfo SET WorkFlowStatus=? WHERE NBXH=?";
				getJdbcTemplate().update(sql, enumWorkFlowStatus.getEnumValue(),nbxh);
				
				//将归档后的学生信息写到库房档案信息表中
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
			pErrInfo.getContent().append(e.getMessage());
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

	
	/**
	 * 将学生信息添加到库房档案信息表中的SQL语句
	 */
	private final String SQL_INSERT_TO_StoreroomArchivesInfo = "INSERT INTO StoreroomArchivesInfo "+
		" SELECT A.ArchivesTypeID,A.NBXH,A.ArchivesID,A.xm,A.Barcode ,0 as BoxBarcode,1 as StoreStatus  " +
		" FROM StudentInfo A WHERE NBXH=?";
	
	@Override
	public boolean addStudentInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo) {
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
				
				jdbcTemplate.update(SQL_INSERT_TO_StoreroomArchivesInfo,nbxh);

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
	public boolean findCollege(List<String> collegeNames, ErrInfo pErrInfo) {
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
				String sql = "SELECT xy FROM StudentInfo WHERE WorkFlowStatus=11 GROUP BY xy";
				List<String> pCollegeNames = getJdbcTemplate().queryForList(sql,String.class);
				if (pCollegeNames.size()>0) {
					collegeNames.addAll(pCollegeNames);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateMoveOutInfo(StudentInfo studentInfo, ErrInfo pErrInfo) {
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
				String sql = "UPDATE StudentInfo SET MoveOutRegID=:MoveOutRegID,dwszd=:dwszd,dwmc=:dwmc,lxdh=:lxdh,dayjbm=:dayjbm,yzbm=:yzbm,dayjdz=:dayjdz WHERE xh=:xh";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("MoveOutRegID", studentInfo.getMoveOutRegID());
				parameterSource.addValue("dwszd", studentInfo.getCompanyAddr());
				parameterSource.addValue("dwmc", studentInfo.getCompanyName());
				parameterSource.addValue("lxdh", studentInfo.getPhone());
				parameterSource.addValue("dayjbm", studentInfo.getMailingCompany());
				parameterSource.addValue("yzbm", studentInfo.getReceivePostcode());
				parameterSource.addValue("dayjdz", studentInfo.getMailingAddr());
				parameterSource.addValue("xh", studentInfo.getStudentId());
				
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findStudentInfoByMoveOutId(int moveOutId, List<StudentInfo> studentInfos, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM StudentInfo WHERE MoveOutRegID=? AND WorkFlowStatus <> 11";
				List<StudentInfo> pStudentInfos = getJdbcTemplate().query(sql, new Object[]{moveOutId}, new StudentInfoMapper());
				if (pStudentInfos.size() > 0) {
					studentInfos.addAll(pStudentInfos);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateWorkFlowStatusByMoveOutRegID(int moveOutRegID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
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
				String sql = "UPDATE StudentInfo SET WorkFlowStatus=? WHERE MoveOutRegID=?";
				getJdbcTemplate().update(sql,enumWorkFlowStatus.getEnumValue(),moveOutRegID);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findStudentInfos(StudentInfo studentInfo, List<EnumWorkFlowStatus> enumWorkFlowStatus,List<StudentInfo> studentInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> enumWorkFlowStatues = new ArrayList<Integer>();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				for (EnumWorkFlowStatus enumWorkFlowStatu : enumWorkFlowStatus) {
					enumWorkFlowStatues.add(enumWorkFlowStatu.getEnumValue());
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM StudentInfo S,MoveOutRegister M WHERE S.MoveOutRegID=M.ID AND S.WorkFlowStatus IN (:WorkFlowStatus) %1$s ORDER BY NBXH";
				String condition = "";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("WorkFlowStatus", enumWorkFlowStatues);
				
				if (studentInfo.getStudentId() != null && !"".equals(studentInfo.getStudentId())) {
					condition += " AND S.xh=:xh";
					parameterSource.addValue("xh", studentInfo.getStudentId());
				}
				
				if (studentInfo.getStudentName() != null && !"".equals(studentInfo.getStudentName())) {
					condition += " AND S.xm=:xm";
					parameterSource.addValue("xm", studentInfo.getStudentName());
				}
				
				if (studentInfo.getSN() != null && !"".equals(studentInfo.getSN())) {
					condition += " AND M.SN=:SN";
					parameterSource.addValue("SN", studentInfo.getSN());
				}
				
				sql = String.format(sql, condition);
				
				RowMapper<StudentInfo> rowMapper = new RowMapper<StudentInfo>() {
					@Override
					public StudentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
						int nBXH = rs.getInt("NBXH");
						Date saveDate = rs.getTimestamp("SaveDate");
						int workFlowStatus = rs.getInt("WorkFlowStatus");
						String barcode = rs.getString("Barcode");
						int archivesTypeID = rs.getInt("ArchivesTypeID");
						String archivesID = rs.getString("ArchivesID");
						String title = rs.getString("Title");
						int moveOutRegID = rs.getInt("MoveOutRegID");
						String studentId = rs.getString("xh");
						String studentName = rs.getString("xm");
						String sex = rs.getString("xb");
						String startSchoolYear = rs.getString("rxnd");
						String finishSchoolYear = rs.getString("bynd");
						String college = rs.getString("xy");
						String specialty = rs.getString("zy");
						String grade = rs.getString("bj");
						String companyAddr = rs.getString("dwszd");
						String companyName = rs.getString("dwmc");
						String phone = rs.getString("lxdh");
						String mailingCompany = rs.getString("dayjbm");
						String receivePostcode = rs.getString("yzbm");
						String mailingAddr = rs.getString("dayjdz");
						String reMark = rs.getString("bz");
						String SN = rs.getString("SN");
						int moveOutWay = rs.getInt("MoveOutWay");
						boolean moveOutedFlag = rs.getBoolean("MoveOutedFlag");
						
					    return	new	StudentInfo( nBXH, saveDate, workFlowStatus, barcode, archivesTypeID, archivesID, title, moveOutRegID, studentId,studentName, sex, 
					    		startSchoolYear, finishSchoolYear, college, specialty, grade, companyAddr, companyName, phone, mailingCompany, receivePostcode, mailingAddr,
								 reMark,SN,moveOutWay,moveOutedFlag);
					}
				};
				
				List<StudentInfo> pStudentInfos = new NamedParameterJdbcTemplate(getDataSource()).query(sql, parameterSource, rowMapper);
				if (pStudentInfos.size() > 0) {
					studentInfos.addAll(pStudentInfos);
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
			pErrInfo.getContent().append(e.getMessage());
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
}
