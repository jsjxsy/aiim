package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ITeacherDocsInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherDocsInfo;

public class TeacherDocsInfoDaoImpl extends JdbcDaoSupport implements ITeacherDocsInfoDao {

	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TeacherDocsInfoMapper implements RowMapper<TeacherDocsInfo>
	{
		
		@Override
		public TeacherDocsInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int nBXH = rs.getInt("NBXH");
			int docTypeID = rs.getInt("DocTypeID");
			int orderID = rs.getInt("OrderID");
			String docName = rs.getString("DocName");
			String formationDate = rs.getString("FormationDate");
			int copys = rs.getInt("Copys");
			int pages = rs.getInt("Pages");
			String remark = rs.getString("Remark");
			
			return new TeacherDocsInfo(iD,nBXH,docTypeID,orderID,docName,formationDate,copys,pages,remark);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TeacherDocsInfoMapper2 implements RowMapper<TeacherDocsInfo>
	{
		
		@Override
		public TeacherDocsInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String gzh = rs.getString("gzh");
			String xm =  rs.getString("xm");
			int docTypeID = rs.getInt("DocTypeID");
			int orderID =  rs.getInt("OrderID");
			String docName = rs.getString("DocName");
			String formationDate = rs.getString("FormationDate");
			int copys = rs.getInt("Copys");
			int pages = rs.getInt("Pages");
			String remark = rs.getString("Remark");
			
			return new TeacherDocsInfo(gzh,xm,docTypeID,orderID,docName,formationDate,copys,pages,remark);
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
	public boolean add(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
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
				String sql = "INSERT INTO TeacherDocsInfo VALUES (:NBXH,:DocTypeID,:OrderID,:DocName,:FormationDate,:Copys,:Pages,:Remark)";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXH", teacherDocsInfo.getNBXH());
				parameterSource.addValue("DocTypeID", teacherDocsInfo.getDocTypeID());
				parameterSource.addValue("OrderID", teacherDocsInfo.getOrderID());
				parameterSource.addValue("DocName", teacherDocsInfo.getDocName());
				parameterSource.addValue("FormationDate", teacherDocsInfo.getFormationDate());
				parameterSource.addValue("Copys", teacherDocsInfo.getCopys());
				parameterSource.addValue("Pages", teacherDocsInfo.getPages());
				parameterSource.addValue("Remark", teacherDocsInfo.getRemark());
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				namedParameterJdbcTemplate.update(sql, parameterSource);
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
	public boolean findByNBXH(int nbxh, List<TeacherDocsInfo> pTeacherDocsInfos, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM TeacherDocsInfo WHERE NBXH=? ORDER BY DocTypeID,OrderID";
				List<TeacherDocsInfo> teacherDocsInfos = getJdbcTemplate().query(sql, new TeacherDocsInfoMapper(), nbxh);
				if (teacherDocsInfos.size() > 0) {
					pTeacherDocsInfos.addAll(teacherDocsInfos);
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
	public boolean findByID(int id, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM TeacherDocsInfo WHERE ID=?";
				List<TeacherDocsInfo> teacherDocsInfos = getJdbcTemplate().query(sql, new TeacherDocsInfoMapper(), id);
				if (teacherDocsInfos.size() > 0) {
					teacherDocsInfo.cloneFrom(teacherDocsInfos.get(0));
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
	public boolean update(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo) {
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
				String sql = "UPDATE TeacherDocsInfo SET DocTypeID=:DocTypeID,OrderID=:OrderID,DocName=:DocName,FormationDate=:FormationDate,Copys=:Copys,Pages=:Pages WHERE ID=:ID";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("DocTypeID", teacherDocsInfo.getDocTypeID());
				parameterSource.addValue("OrderID", teacherDocsInfo.getOrderID());
				parameterSource.addValue("DocName", teacherDocsInfo.getDocName());
				parameterSource.addValue("FormationDate", teacherDocsInfo.getFormationDate());
				parameterSource.addValue("Copys", teacherDocsInfo.getCopys());
				parameterSource.addValue("Pages", teacherDocsInfo.getPages());
				parameterSource.addValue("ID", teacherDocsInfo.getID());
				
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				parameterJdbcTemplate.update(sql, parameterSource);
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
	public boolean delDocs(int[] docIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> teacherDocIds = new ArrayList<Integer>();
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if(pFlag) {
				for (int i = 0; i < docIds.length; i++) {
					teacherDocIds.add(docIds[i]);
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				String sql = "DELETE TeacherDocsInfo WHERE ID IN (:TeacherDocIds)";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TeacherDocIds", teacherDocIds);
				
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
	public boolean findByNBXHForPrint(int nbxh, List<TeacherDocsInfo> pTeacherDocsInfos, ErrInfo pErrInfo) {
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
				String sql = " SELECT B.gzh,B.xm,A.DocTypeID,A.OrderID,A.FormationDate,A.DocName,A.Pages,A.Copys,A.Remark FROM TeacherDocsInfo A,TeacherInfo B,DD_TeacherDocsType C WHERE A.NBXH = ? AND A.NBXH=B.NBXH AND A.DocTypeID = C.ID";
				List<TeacherDocsInfo> teacherDocsInfos = getJdbcTemplate().query(sql, new TeacherDocsInfoMapper2(), nbxh);
				if (teacherDocsInfos.size() > 0) {
					pTeacherDocsInfos.addAll(teacherDocsInfos);
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
	public boolean add(final List<String> gzhList, final TeacherDocsInfo teacherDocsInfo,ErrInfo pErrInfo) {
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
				String sql = "INSERT INTO TeacherDocsInfo VALUES ((SELECT NBXH FROM TeacherInfo WHERE gzh=?),?,?,?,?,?,?,?)";
				BatchPreparedStatementSetter preparedStatementSetter = new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, gzhList.get(i));
						ps.setInt(2, teacherDocsInfo.getDocTypeID());
						ps.setInt(3, teacherDocsInfo.getOrderID());
						ps.setString(4, teacherDocsInfo.getDocName());
						ps.setString(5, teacherDocsInfo.getFormationDate());
						ps.setInt(6, teacherDocsInfo.getCopys());
						ps.setInt(7, teacherDocsInfo.getPages());
						ps.setString(8, teacherDocsInfo.getRemark());
					}
					@Override
					public int getBatchSize() {
						// TODO Auto-generated method stub
						return gzhList.size();
					}
				};
				getJdbcTemplate().batchUpdate(sql, preparedStatementSetter);
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
