package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.orifound.aiim.dal.dao.IStockReportArchivesCountDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportArchivesCount;
/**
 * 库房盘点- 库房盘点档案数量信息表的DAO实现类
 * @author Administrator
 *
 */
public class StockReportArchivesCountDaoImpl extends JdbcDaoSupport implements IStockReportArchivesCountDao {

	/**
	 * 构造函数
	 */
	public StockReportArchivesCountDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StockReportArchivesCountDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StockReportArchivesCountMapper implements RowMapper<StockReportArchivesCount>
	{
		
		@Override
		public StockReportArchivesCount mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int paperVolumeCount = rs.getInt("PaperVolumeCount");
			int paperPieceCount = rs.getInt("PaperPieceCount");
			int systemVolumeCount = rs.getInt("SystemVolumeCount");
			int systemPieceCount = rs.getInt("SystemPieceCount");
			String fullName = rs.getString("FullName");
			StockReportArchivesCount stockReportArchivesCount =new StockReportArchivesCount(stocktakingID,archivesTypeID,paperVolumeCount,paperPieceCount,systemVolumeCount,systemPieceCount);
			stockReportArchivesCount.setTag(fullName);
			return  stockReportArchivesCount;
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

	// Table Name: StocktakingReport_ArchivesCount
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesTypeID,PaperVolumeCount,PaperPieceCount,SystemVolumeCount,SystemPieceCount
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesTypeID,:PaperVolumeCount,:PaperPieceCount,:SystemVolumeCount,:SystemPieceCount
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesTypeID=:ArchivesTypeID,PaperVolumeCount=:PaperVolumeCount,PaperPieceCount=:PaperPieceCount,SystemVolumeCount=:SystemVolumeCount,SystemPieceCount=:SystemPieceCount

	/**
	 * 添加库房盘点档案数量信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_ArchivesCount(StocktakingID,ArchivesTypeID,PaperVolumeCount,PaperPieceCount,SystemVolumeCount,SystemPieceCount)" +
						" VALUES(:StocktakingID,:ArchivesTypeID,:PaperVolumeCount,:PaperPieceCount,:SystemVolumeCount,:SystemPieceCount)";
	@Override
	public boolean add(StockReportArchivesCount stockReportArchivesCounty, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportArchivesCounty.getStocktakingID());
				parameterSource.addValue("ArchivesTypeID", stockReportArchivesCounty.getArchivesTypeID());
				parameterSource.addValue("PaperVolumeCount", stockReportArchivesCounty.getPaperVolumeCount());
				parameterSource.addValue("PaperPieceCount", stockReportArchivesCounty.getPaperPieceCount());
				parameterSource.addValue("SystemVolumeCount", stockReportArchivesCounty.getSystemVolumeCount());
				parameterSource.addValue("SystemPieceCount", stockReportArchivesCounty.getSystemPieceCount());
				
				namedParameterJdbcTemplate.update(SQL_INSERT,parameterSource);

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
	 * 执行库房档案数量信息盘点的SQL语句
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_ArchivesCount WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "insert into StocktakingReport_ArchivesCount " +
			" select %1$s as StocktakingID,AA.ArchivesTypeID ,BB.paper1,BB.paper0,AA.sys1,AA.sys0 "+
				" from "+
				" (select ArchivesTypeID, "+
				" count(case ParentFlag when 1 then 1 end) as sys1, "+
				" count(case ParentFlag when 0 then 1 end) as sys0 "+
				" from DD_StoreroomStructure A ,StoreAddressInfo B,StoreroomArchivesInfo C "+ 
				" where  RoomID=(SELECT StoreroomID FROM StocktakingInfo WHERE ID=%1$s)  "+
				" and EndFlag=1  and A.ID = B.StoreAddressID and B.ArchivesBoxBarcode = C.ArchivesBoxBarcode  and c.StoreStatus=1 "+ 
				" group by ArchivesTypeID )AA "+
				" left join  "+
				" ( "+
				" select ArchivesTypeID, "+
				" count(case ParentFlag when 1 then 1 end) as paper1, "+
				" count(case ParentFlag when 0 then 1 end) as paper0 "+
				" 	from StoreroomArchivesInfo A1,StocktakingArchivesDetails B1 "+ 
				" 	where A1.ArchivesBarcode = B1.ArchivesBarcode and StocktakingID=%1$s  and a1.StoreStatus =1 "+ 
				" 	group by A1.ArchivesTypeID "+ 
				" ) BB on AA.ArchivesTypeID=BB.ArchivesTypeID ";
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
				jdbcTemplate.update(SQL_UPDATE_deleteAll,stocktakingID);//删除上次统计的数量信息
				String sql = String.format(SQL_UPDATE_stocktaking, stocktakingID);
				System.out.println(sql);
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

	/**
	 * 查找所有的库房盘点档案数量信息的SQL语句
	 */
	private final String SQL_SELECT_findAll = "SELECT A.* ,B.FullName FROM StocktakingReport_ArchivesCount A,DD_ArchivesType B WHERE StocktakingID=?  AND A.ArchivesTypeID=B.ID ORDER BY FullName ";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportArchivesCount> stockReportArchivesCountys, ErrInfo pErrInfo) {
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
				List<StockReportArchivesCount> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportArchivesCountMapper(),stocktakingID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					stockReportArchivesCountys.addAll(pEntitys);
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

}
