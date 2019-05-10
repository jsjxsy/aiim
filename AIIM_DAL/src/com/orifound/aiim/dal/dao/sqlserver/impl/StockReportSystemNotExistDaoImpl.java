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

import com.orifound.aiim.dal.dao.IStockReportSystemNotExistDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumStoreStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportSystemNotExist;
/**
 * 盘点报告-系统中不在架档案信息表的DAO实现类
 * @author Administrator
 *
 */
public class StockReportSystemNotExistDaoImpl extends JdbcDaoSupport implements IStockReportSystemNotExistDao {
	/**
	 * 构造函数
	 */
	public StockReportSystemNotExistDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StockReportSystemNotExistDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StockReportSystemNotExistsMapper implements RowMapper<StockReportSystemNotExist>
	{
		
		@Override
		public StockReportSystemNotExist mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			String storeAddressFullName = rs.getString("StoreAddressFullName");
			EnumStoreStatus storeStatus =EnumStoreStatus.getEnumElement(rs.getInt("StoreStatus"));
			
			return new StockReportSystemNotExist(stocktakingID,archivesBarcode,archivesBoxBarcode,storeAddressFullName,storeStatus);
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
	// Table Name: StocktakingReport_SystemNotExists
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesBoxBarcode,StoreAddressFullName,StoreStatus
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreAddressFullName,:StoreStatus
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName,StoreStatus=:StoreStatus

	/**
	 * 添加系统中不在架档案信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_SystemNotExists(StocktakingID,ArchivesBarcode,ArchivesBoxBarcode,StoreAddressFullName,StoreStatus) VALUES(:StocktakingID,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreAddressFullName,:StoreStatus) ";
	@Override
	public boolean add(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportSystemNotExist.getStocktakingID());
				parameterSource.addValue("ArchivesBarcode", stockReportSystemNotExist.getArchivesBarcode());
				parameterSource.addValue("ArchivesBoxBarcode", stockReportSystemNotExist.getArchivesBoxBarcode());
				parameterSource.addValue("StoreAddressFullName", stockReportSystemNotExist.getStoreAddressFullName());
				parameterSource.addValue("StoreStatus", stockReportSystemNotExist.getStoreStatus().getEnumValue());
				
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

	@Override
	public boolean delete(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * 查询所有系统不在架的档案信息的SQL语句
	 */
	private final String SQL_SELECT_findAll = " select *  from StocktakingReport_SystemNotExists A WHERE StocktakingID=? ";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportSystemNotExist> stockReportSystemNotExists,ErrInfo pErrInfo) {
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
				List<StockReportSystemNotExist> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportSystemNotExistsMapper(),stocktakingID);
				
				if (pEntitys.size()>0) {
					stockReportSystemNotExists.addAll(pEntitys);
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
	public boolean findByID(int pID, StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(StockReportSystemNotExist stockReportSystemNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 执行系统中不在架的档案情况盘点的SQL语句
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_SystemNotExists WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_SystemNotExists "+
	" select A.StocktakingID,A.ArchivesBarcode,A.ArchivesBoxBarcode,C.StoreAddressFullName,B.StoreStatus "+
	" from StocktakingArchivesDetails A "+ 
	"  left join StoreroomArchivesInfo  B on A.ArchivesBarcode=B.ArchivesBarcode "+
	"   left join StoreAddressInfo  C on A.ArchivesBoxBarcode =C.ArchivesBoxBarcode "+
	"     WHERE A.StocktakingID=? AND B.StoreStatus<>1 ";
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
				
				jdbcTemplate.update(SQL_UPDATE_stocktaking, stocktakingID);
				
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
