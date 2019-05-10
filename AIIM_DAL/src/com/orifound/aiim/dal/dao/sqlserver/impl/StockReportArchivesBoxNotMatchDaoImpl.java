package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStockReportArchivesBoxNotMatchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
/**
 * 库房盘点-档案装盒不匹配信息表的DAO实现类
 * @author Administrator
 *
 */
public class StockReportArchivesBoxNotMatchDaoImpl extends JdbcDaoSupport implements IStockReportArchivesBoxNotMatchDao {

	/**
	 * 构造函数
	 */
	public StockReportArchivesBoxNotMatchDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StockReportArchivesBoxNotMatchDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StockReportArchivesBoxNotMatchMapper implements RowMapper<StockReportArchivesBoxNotMatch>
	{
		
		@Override
		public StockReportArchivesBoxNotMatch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String paperBoxBarcode = rs.getString("PaperBoxBarcode");
			String systemBoxBarcode = rs.getString("SystemBoxBarcode");
			String fullName = rs.getString("FullName");
			StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch = new StockReportArchivesBoxNotMatch(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,paperBoxBarcode,systemBoxBarcode);
			stockReportArchivesBoxNotMatch.setTag(fullName);
			return stockReportArchivesBoxNotMatch;
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
	// Table Name: StocktakingReport_ArchivesBoxNotMatch
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,PaperBoxBarcode,SystemBoxBarcode
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:PaperBoxBarcode,:SystemBoxBarcode
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,PaperBoxBarcode=:PaperBoxBarcode,SystemBoxBarcode=:SystemBoxBarcode
	/**
	 * 添加档案装盒不匹配信息的SQL语句
	 */
	private final String SQL_SELECT = "INSERT INTO StocktakingReport_ArchivesBoxNotMatch(StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,PaperBoxBarcode,SystemBoxBarcode) " +
			" VALUES(:StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:PaperBoxBarcode,:SystemBoxBarcode)";
	@Override
	public boolean add(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo) {
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
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("StocktakingID", stockReportArchivesBoxNotMatch.getStocktakingID());
				parameterSource.addValue("ArchivesBarcode", stockReportArchivesBoxNotMatch.getArchivesBarcode());
				parameterSource.addValue("ArchivesTypeID", stockReportArchivesBoxNotMatch.getArchivesTypeID());
				parameterSource.addValue("NBXH", stockReportArchivesBoxNotMatch.getNBXH());
				parameterSource.addValue("ArchivesID", stockReportArchivesBoxNotMatch.getArchivesID());
				parameterSource.addValue("Title", stockReportArchivesBoxNotMatch.getTitle());
				parameterSource.addValue("PaperBoxBarcode", stockReportArchivesBoxNotMatch.getPaperBoxBarcode());
				parameterSource.addValue("SystemBoxBarcode", stockReportArchivesBoxNotMatch.getSystemBoxBarcode());
				
				namedParameterJdbcTemplate.update(SQL_SELECT, parameterSource);

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				parameterSource = null;
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

	/**
	 * 执行档案装盒不匹配信息情况盘点的SQL
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_ArchivesBoxNotMatch WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_ArchivesBoxNotMatch " + 
		" select A.StocktakingID,A.ArchivesBarcode ,B.ArchivesTypeID,B.NBXH,B.ArchivesID,B.Title,A.ArchivesBoxBarcode AS PaperBoxBarcode ,B.ArchivesBoxBarcode " +
		" FROM StocktakingArchivesDetails A,StoreroomArchivesInfo B  " +
		" WHERE A.StocktakingID=? AND A.ArchivesBarcode = B.ArchivesBarcode AND A.ArchivesBoxBarcode<>B.ArchivesBoxBarcode " ;
	@Override
	public boolean executeStocktakingByStocktakingID(int stocktakingID, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_UPDATE_deleteAll,stocktakingID);
				
				jdbcTemplate.update(SQL_UPDATE_stocktaking,stocktakingID);

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

	/**
	 * 查找所有的档案装盒不匹配信息的SQL语句
	 */
	private final String SQL_SELECT_findAll = "SELECT A.*,B.FullName FROM StocktakingReport_ArchivesBoxNotMatch A,DD_ArchivesType B WHERE StocktakingID=? AND A.ArchivesTypeID=B.ID";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs, ErrInfo pErrInfo) {
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
				List<StockReportArchivesBoxNotMatch> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportArchivesBoxNotMatchMapper(),stocktakingID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					stockReportArchivesBoxNotMatchs.addAll(pEntitys);
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
	public boolean update(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
