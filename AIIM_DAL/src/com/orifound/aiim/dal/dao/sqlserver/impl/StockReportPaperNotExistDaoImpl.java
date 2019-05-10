package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStockReportPaperNotExistDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/**
 * 库房盘点-实物档案不在架信息表的DAO实现类
 * @author Administrator
 *
 */
public class StockReportPaperNotExistDaoImpl extends JdbcDaoSupport implements IStockReportPaperNotExistDao {

	/**
	 * 构造函数
	 */
	public StockReportPaperNotExistDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StockReportPaperNotExistDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StockReportPaperNotExistsMapper implements RowMapper<StockReportPaperNotExist>
	{
		
		@Override
		public StockReportPaperNotExist mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			String storeAddressFullName = rs.getString("StoreAddressFullName");
			String fullName = rs.getString("FullName");
			StockReportPaperNotExist stockReportPaperNotExist =new StockReportPaperNotExist(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,archivesBoxBarcode,storeAddressFullName);
			stockReportPaperNotExist.setTag(fullName);
			return stockReportPaperNotExist;
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
	// Table Name: StocktakingReport_PaperNotExists
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBoxBarcode,StoreAddressFullName
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBoxBarcode,:StoreAddressFullName
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName
	/**
	 * 添加实物档案不在架信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_PaperNotExists(StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBoxBarcode,StoreAddressFullName)" +
			" VALUES(:StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBoxBarcode,:StoreAddressFullName) ";
	@Override
	public boolean add(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportPaperNotExist.getStocktakingID());
				parameterSource.addValue("ArchivesBarcode", stockReportPaperNotExist.getStocktakingID());
				parameterSource.addValue("ArchivesTypeID", stockReportPaperNotExist.getArchivesTypeID());
				parameterSource.addValue("NBXH", stockReportPaperNotExist.getNBXH());
				parameterSource.addValue("ArchivesID", stockReportPaperNotExist.getArchivesID());
				parameterSource.addValue("Title", stockReportPaperNotExist.getTitle());
				parameterSource.addValue("ArchivesBoxBarcode", stockReportPaperNotExist.getArchivesBoxBarcode());
				parameterSource.addValue("StoreAddressFullName", stockReportPaperNotExist.getStoreAddressFullName());
				
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
	public boolean delete(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 查找所有的实物档案不在架信息的SQL语句
	 */
	private final String SQL_SELECT_findAll = "SELECT A.* ,B.FullName  FROM StocktakingReport_PaperNotExists A,DD_ArchivesType B WHERE StocktakingID=?  AND A.ArchivesTypeID=B.ID ";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportPaperNotExist> stockReportPaperNotExists, ErrInfo pErrInfo) {
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
				List<StockReportPaperNotExist> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportPaperNotExistsMapper(),stocktakingID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					stockReportPaperNotExists.addAll(pEntitys);
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
	public boolean findByID(int pID, StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 实物档案不在架的情况盘点的SQL语句
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_PaperNotExists WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_PaperNotExists "+
		"  select E.*  from( "+
		"  select %1$s as Stocktaking,C.ArchivesBarcode,C.ArchivesTypeID,C.NBXH,C.ArchivesID,C.Title,C.ArchivesBoxBarcode,B.StoreAddressFullName from DD_StoreroomStructure A ,StoreAddressInfo B,StoreroomArchivesInfo C "+ 
		"  where  RoomID=(SELECT StoreroomID FROM StocktakingInfo WHERE ID= %1$s ) and EndFlag=1  and A.ID = B.StoreAddressID and B.ArchivesBoxBarcode = C.ArchivesBoxBarcode  and c.StoreStatus=1) E "+
		"  left join StocktakingArchivesDetails F on E.ArchivesBarcode =f.ArchivesBarcode and f.StocktakingID = %1$s "+
		"  where StocktakingID is NULL ";
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
				
				String sql = String.format(SQL_UPDATE_stocktaking, stocktakingID);
				System.out.println("sql:"+sql);
				jdbcTemplate.update(sql);

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
