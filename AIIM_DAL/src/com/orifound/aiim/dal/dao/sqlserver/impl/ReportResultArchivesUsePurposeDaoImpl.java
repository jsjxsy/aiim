package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IReportResultArchivesUsePurposeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportResultArchivesUsePurpose;

public class ReportResultArchivesUsePurposeDaoImpl extends JdbcDaoSupport implements IReportResultArchivesUsePurposeDao {
	/**
	 * 构造函数
	 */
	public ReportResultArchivesUsePurposeDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ReportResultArchivesUsePurposeDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ReportResultArchivesUsePurposeMapper implements RowMapper<ReportResultArchivesUsePurpose>
	{
		
		@Override
		public ReportResultArchivesUsePurpose mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int reportID = rs.getInt("ReportID");
			String reportTitle = rs.getString("ReportTitle");
			int purposeID = rs.getInt("PurposeID");
			String purposeName = rs.getString("PurposeName");
			int columeCount = rs.getInt("ColumeCount");
			int pieceCount = rs.getInt("PieceCount");
			
			return new ReportResultArchivesUsePurpose(iD,reportID,reportTitle,purposeID,purposeName,columeCount,pieceCount);
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
	 * 查询所有统计结果档案利用意图情况的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT A.*,B.ReportTitle FROM ReportResult_ArchivesUsePurpose  A , DD_StatReport B where A.ReportID = B.ID";
	@Override
	public boolean delete(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<ReportResultArchivesUsePurpose> pReportResultArchivesUsePurposes, ErrInfo pErrInfo) {
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
				List<ReportResultArchivesUsePurpose> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new ReportResultArchivesUsePurposeMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {
					pReportResultArchivesUsePurposes.addAll(pEntitys);
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
	public boolean findByID(int pID, ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ReportResultArchivesUsePurpose pReportResultArchivesUsePurpose, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
