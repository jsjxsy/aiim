package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStatReportDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StatReport;

public class StatReportDaoImpl extends JdbcDaoSupport implements IStatReportDao {
	
	/**
	 * 构造函数
	 */
	public StatReportDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StatReportDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StatReportMapper implements RowMapper<StatReport>
	{
		
		@Override
		public StatReport mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String reportName = rs.getString("ReportName");
			String reportTitle = rs.getString("ReportTitle");
			int statTemplateType = rs.getInt("StatTemplateType");
			int parentID = rs.getInt("ParentID");
			boolean parentFlag = rs.getBoolean("ParentFlag");
			boolean templateFlag = rs.getBoolean("TemplateFlag");
			boolean reportFlag = rs.getBoolean("ReportFlag");
			String statYear = rs.getString("StatYear");
			Date statBeginDate = rs.getTimestamp("StatBeginDate");
			Date statEndDate = rs.getTimestamp("StatEndDate");
			String remark = rs.getString("Remark");
			
			return new StatReport(iD,reportName,reportTitle,statTemplateType,parentID,parentFlag,templateFlag,reportFlag,statYear,statBeginDate,statEndDate,remark);
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
	
	
	/**
	 * 查询所有统计报表信息的SQL语句
	 */
	private final String SQL_SELECT = "SELECT * FROM DD_StatReport";
	@Override
	public boolean delete(StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<StatReport> pStatReports, ErrInfo pErrInfo) {
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
				List<StatReport> pEntitys=jdbcTemplate.query(SQL_SELECT,new StatReportMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					pStatReports.addAll(pEntitys);
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
	public boolean findByID(int pID, StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
