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

import com.orifound.aiim.dal.dao.IStockReportAddressNotMatchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
/**
 * 库房盘点-上架位置不匹配信息表的DAO实现类
 * @author Administrator
 *
 */
public class StockReportAddressNotMatchDaoImpl extends JdbcDaoSupport implements IStockReportAddressNotMatchDao {
	/**
	 * 构造函数
	 */
	public StockReportAddressNotMatchDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StockReportAddressNotMatchDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StockReportAddressNotMatchMapper implements RowMapper<StockReportAddressNotMatch>
	{
		
		@Override
		public StockReportAddressNotMatch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			int paperAddressID = rs.getInt("PaperAddressID");
			String paperAddressFullName = rs.getString("PaperAddressFullName");
			int systemAddressID = rs.getInt("SystemAddressID");
			String systemAddressFullName = rs.getString("SystemAddressFullName");
			
			return new StockReportAddressNotMatch(stocktakingID,archivesBoxBarcode,paperAddressID,paperAddressFullName,systemAddressID,systemAddressFullName);
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
	// Table Name: StocktakingReport_AddressNotMatch
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,PaperAddressID,PaperAddressFullName,SystemAddressID,SystemAddressFullName
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:PaperAddressID,:PaperAddressFullName,:SystemAddressID,:SystemAddressFullName
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,PaperAddressID=:PaperAddressID,PaperAddressFullName=:PaperAddressFullName,SystemAddressID=:SystemAddressID,SystemAddressFullName=:SystemAddressFullName
	/**
	 * 添加上架位置不匹配信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_AddressNotMatch(StocktakingID,ArchivesBoxBarcode,PaperAddressID,PaperAddressFullName,SystemAddressID,SystemAddressFullName)" +
			" VALUSE(:StocktakingID,:ArchivesBoxBarcode,:PaperAddressID,:PaperAddressFullName,:SystemAddressID,:SystemAddressFullName)";
	@Override
	public boolean add(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportAddressNotMatch.getStocktakingID());
				parameterSource.addValue("ArchivesBoxBarcode", stockReportAddressNotMatch.getArchivesBoxBarcode());
				parameterSource.addValue("PaperAddressID", stockReportAddressNotMatch.getPaperAddressID());
				parameterSource.addValue("PaperAddressFullName", stockReportAddressNotMatch.getPaperAddressFullName());
				parameterSource.addValue("SystemAddressID", stockReportAddressNotMatch.getSystemAddressID());
				parameterSource.addValue("SystemAddressFullName",stockReportAddressNotMatch.getSystemAddressFullName());

				namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource);
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
	 * 查找所有的上架位置不匹配信息的SQL语句
	 */
	private final String SQL_SELECT_findAll = "SELECT * FROM StocktakingReport_AddressNotMatch WHERE StocktakingID = ?";
	@Override
	public boolean findAll(int stocktakingID, List<StockReportAddressNotMatch> stockReportAddressNotMatchs, ErrInfo pErrInfo) {
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
				List<StockReportAddressNotMatch> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportAddressNotMatchMapper(),stocktakingID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					stockReportAddressNotMatchs.addAll(pEntitys);
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
	public boolean update(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 执行上架位置不匹配信息情况盘点的SQL语句
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_AddressNotMatch WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_AddressNotMatch "+
			"  SELECT A.StocktakingID,A.ArchivesBoxBarcode , "+
			"  	B.ID as PaperAddressID,B.FullName AS PaperAddressFullName , "+
			"  	C.StoreAddressID ,C.StoreAddressFullName  "+
			"  	FROM StocktakingAddressBoxDetails A ,DD_StoreroomStructure B ,StoreAddressInfo C "+  
			"  		WHERE StocktakingID=? and A.StoreAddressBarcode =B.Barcode AND A.ArchivesBoxBarcode = C.ArchivesBoxBarcode AND B.ID <> C.StoreAddressID ";
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

	
	
}
