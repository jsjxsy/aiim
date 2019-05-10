package com.orifound.aiim.dal.dao.sqlserver.impl;

import javax.sql.DataSource;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IStocktakingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;
/**
 * 库房盘点Dao实现类
 * @author Administrator
 *
 */
public class StocktakingDaoImpl extends JdbcDaoSupport implements IStocktakingDao {
	/**
	 * 构造函数
	 */
	public StocktakingDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StocktakingDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StocktakingAddressBoxDetailsMapper implements RowMapper<StocktakingAddressBoxDetail>
	{
		
		@Override
		public StocktakingAddressBoxDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String storeAddressBarcode = rs.getString("StoreAddressBarcode");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			
			return new StocktakingAddressBoxDetail(stocktakingID,storeAddressBarcode,archivesBoxBarcode);
		}
	}
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StocktakingArchivesDetailsMapper implements RowMapper<StocktakingArchivesDetail>
	{
		
		@Override
		public StocktakingArchivesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			
			return new StocktakingArchivesDetail(stocktakingID,archivesBoxBarcode,archivesBarcode);
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
	
	
	
/////////////////////////////导入数据/////////////// ////////////////////

	/**
	 * 判断档案盒条形码是否已经存在的SQL语句
	 */
	private final String SQL_SELECT_checkAddressBoxDetailExist = "SELECT COUNT(*) FROM StocktakingAddressBoxDetails WHERE ArchivesBoxBarcode=? AND StocktakingID=?";

	@Override
	public boolean checkAddressBoxDetailExist(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		boolean resultFlag = false;
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
				int rowNum = jdbcTemplate.queryForInt(SQL_SELECT_checkAddressBoxDetailExist,stocktakingAddressBoxDetail.getArchivesBoxBarcode(),stocktakingAddressBoxDetail.getStocktakingID());
				System.out.println("rowNum1:"+rowNum);
				if (rowNum >0) {
					resultFlag = true;
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
		return resultFlag;
	}

	// Table Name: StocktakingAddressBoxDetails
	// Columns List,Can Used in SELECT SQL: StocktakingID,StoreAddressBarcode,StocktakingAddressBoxDetails
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:StoreAddressBarcode,:ArchivesBoxBarcode
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,StoreAddressBarcode=:StoreAddressBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode

	/**
	 *  更新库房盘点的设备位置与档案盒详细的SQL语句
	 */
	private final String SQL_UPDATE_AddressBoxDetail = " UPDATE StocktakingAddressBoxDetails SET StoreAddressBarcode=:StoreAddressBarcode WHERE ArchivesBoxBarcode=:ArchivesBoxBarcode AND StocktakingID=:StocktakingID ";
	@Override
	public boolean updateAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingAddressBoxDetail.getStocktakingID());
				parameterSource.addValue("StoreAddressBarcode", stocktakingAddressBoxDetail.getStoreAddressBarcode());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingAddressBoxDetail.getArchivesBoxBarcode());

				namedParameterJdbcTemplate.update(SQL_UPDATE_AddressBoxDetail, parameterSource);
				//销毁局部变量
				
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
	 * 添加库房盘点的设备位置与档案盒详细的SQL语句
	 */
	private final String SQL_INSERT_AddressBoxDetail = "INSERT INTO StocktakingAddressBoxDetails(StocktakingID,StoreAddressBarcode,ArchivesBoxBarcode) VALUES(:StocktakingID,:StoreAddressBarcode,:ArchivesBoxBarcode)";
	@Override
	public boolean addAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingAddressBoxDetail.getStocktakingID());
				parameterSource.addValue("StoreAddressBarcode",stocktakingAddressBoxDetail.getStoreAddressBarcode());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingAddressBoxDetail.getArchivesBoxBarcode());
				
				namedParameterJdbcTemplate.update(SQL_INSERT_AddressBoxDetail, parameterSource);

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
	 * 判断档案卷信息是否存在的SQL语句
	 */
	private final String SQL_SELECT_checkArchivesDetailExist = " SELECT COUNT(*) FROM StocktakingArchivesDetails WHERE ArchivesBarcode=? AND StocktakingID=? ";
	@Override
	public boolean checkArchivesDetailExist(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		boolean resultFlag = false;
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
				int rowNum = jdbcTemplate.queryForInt(SQL_SELECT_checkArchivesDetailExist,stocktakingArchivesDetail.getArchivesBarcode(),stocktakingArchivesDetail.getStocktakingID());
				System.out.println("rowNum:"+rowNum);
				if (rowNum>0) {
					resultFlag = true;
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
System.out.println("pErrInfo.toString():"+pErrInfo.toString());
		return resultFlag;
	}
	
	// Table Name: StocktakingArchivesDetails
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,ArchivesBarcode
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:ArchivesBarcode
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,ArchivesBarcode=:ArchivesBarcode
	/**
	 * 更新库房盘点的档案盒与档案卷详细的SQL语句
	 */
	private final String SQL_UPDATE_ArchivesDetail = " UPDATE StocktakingArchivesDetails SET ArchivesBoxBarcode=:ArchivesBoxBarcode WHERE StocktakingID=:StocktakingID AND ArchivesBarcode=:ArchivesBarcode ";
	@Override
	public boolean updateArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingArchivesDetail.getStocktakingID());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingArchivesDetail.getArchivesBoxBarcode());
				parameterSource.addValue("ArchivesBarcode", stocktakingArchivesDetail.getArchivesBarcode());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_ArchivesDetail, parameterSource);

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
	 * 添加库房盘点的档案盒与档案卷详细的SQL语句
	 */
	private final String SQL_INSERT_ArchivesDetail = "INSERT INTO StocktakingArchivesDetails(StocktakingID,ArchivesBoxBarcode,ArchivesBarcode) VALUES(:StocktakingID,:ArchivesBoxBarcode,:ArchivesBarcode)";
	@Override
	public boolean addArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingArchivesDetail.getStocktakingID());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingArchivesDetail.getArchivesBoxBarcode());
				parameterSource.addValue("ArchivesBarcode", stocktakingArchivesDetail.getArchivesBarcode());
				
				namedParameterJdbcTemplate.update(SQL_INSERT_ArchivesDetail, parameterSource);
				
				

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
	
	
	//////////////////////////////生成盘点报告////////////////////////////////
	

}
