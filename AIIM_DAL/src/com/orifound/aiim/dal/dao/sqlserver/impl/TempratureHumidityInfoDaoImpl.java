package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.ITempratureHumidityInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
/**
 * 库房温湿度登记信息表的DAO实现
 * @author Administrator
 *
 */
public class TempratureHumidityInfoDaoImpl extends JdbcDaoSupport implements ITempratureHumidityInfoDao {
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TempratureHumidityInfoMapper implements RowMapper<TempratureHumidityInfo>
	{
		
		@Override
		public TempratureHumidityInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String storeroomID = rs.getString("StoreroomID");
			Date measureDate = rs.getTimestamp("MeasureDate");
			String measureTime = rs.getString("MeasureTime");
			double temperature = rs.getBigDecimal("Temperature").doubleValue();
			double humidity = rs.getBigDecimal("Humidity").doubleValue();
			Date recordTime = rs.getTimestamp("RecordTime");
			
			return new TempratureHumidityInfo(iD,storeroomID,measureDate,measureTime,temperature,humidity,recordTime);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TempratureHumidityInfoMapperExtendStoreroom implements RowMapper<TempratureHumidityInfo>
	{
		
		@Override
		public TempratureHumidityInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String storeroomID = rs.getString("StoreroomID");
			String storeroomName = rs.getString("StoreroomName");
			Date measureDate = rs.getTimestamp("MeasureDate");
			String measureTime = rs.getString("MeasureTime");
			double temperature = rs.getBigDecimal("Temperature").doubleValue();
			double humidity = rs.getBigDecimal("Humidity").doubleValue();
			Date recordTime = rs.getTimestamp("RecordTime");
			
			return new TempratureHumidityInfo(iD, storeroomID, storeroomName, measureDate, measureTime, temperature, humidity, recordTime);  
		}
	}

	/**
	 * 构造函数
	 */
	public TempratureHumidityInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public TempratureHumidityInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
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
	 * 添加库房温湿度登记信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO TempratureHumidityInfo(StoreroomID,MeasureDate,MeasureTime,Temperature,Humidity) VALUES(:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity)";
	@Override
	public boolean add(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			//:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("StoreroomID", tempratureHumidityInfo.getStoreroomID());
				paramSource.addValue("MeasureDate", tempratureHumidityInfo.getMeasureDate());
				paramSource.addValue("MeasureTime", tempratureHumidityInfo.getMeasureTime());
				paramSource.addValue("Temperature", tempratureHumidityInfo.getTemperature());
				paramSource.addValue("Humidity", tempratureHumidityInfo.getHumidity());
				
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				tempratureHumidityInfo.setID(keyHolder.getKey().intValue());

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
	 * 删除库房环境登记信息的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM TempratureHumidityInfo WHERE ID = ?";
	@Override
	public boolean delete(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_DELETE,tempratureHumidityInfo.getID());		

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
	 * 根据条件查询库房温度、湿度信息的SQL语句
	 */
	private final String SQL_SELECT_findByCondition_count = "select count(*) from TempratureHumidityInfo where 1=1  %1$s ";


	/**
	 * 根据条件查询库房温度、湿度信息的SQL语句
	 */
	private final String SQL_SELECT_findByCondition = "select * from( select A.* , B.Name AS StoreroomName ,ROW_NUMBER() over(order by MeasureDate desc ,A.ID desc) as RowNum from " +
					" TempratureHumidityInfo A,DD_StoreroomStructure B  where A.StoreroomID=B.ID  %1$s )TEMPTABLE where RowNum between %2$s and %3$s ";
	@Override
	public boolean findByCondition(String whereSQL, List<TempratureHumidityInfo> tempratureHumidityInfos, DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String sql = "";

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				sql = String.format(SQL_SELECT_findByCondition_count,whereSQL);
				int rowCount = jdbcTemplate.queryForInt(sql);
				dataPageInfo.setRowCount(rowCount);
				
				sql = String.format(SQL_SELECT_findByCondition,whereSQL,(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize() + 1, dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());
				List<TempratureHumidityInfo> pEntitys = jdbcTemplate.query(sql,new TempratureHumidityInfoMapperExtendStoreroom());
				
				//返回查询结果
				if (pEntitys.size() > 0) {
					tempratureHumidityInfos.addAll(pEntitys);	
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

	
	/**
	 * 通过编号查询库房环境信息的SQL语句
	 */
	private final String SQL_SELECT_findByID = "SELECT * FROM TempratureHumidityInfo WHERE ID =?";
	@Override
	public boolean findByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				List<TempratureHumidityInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByID,new TempratureHumidityInfoMapper(),tempratureHumidityInfo.getID());


				//返回查询结果
				if (pEntitys.size() > 0) {
					tempratureHumidityInfo.cloneFrom(pEntitys.get(0));
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

	// Table Name: TempratureHumidityInfo
	// Columns List,Can Used in SELECT SQL: ID,StoreroomID,MeasureDate,MeasureTime,Temperature,Humidity,RecordTime
	// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity,:RecordTime
	// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,MeasureDate=:MeasureDate,MeasureTime=:MeasureTime,Temperature=:Temperature,Humidity=:Humidity,RecordTime=:RecordTime

	/**
	 * 更新库房环境信息的SQL语句
	 */
	private final String SQL_UPDATE = " UPDATE TempratureHumidityInfo SET StoreroomID=:StoreroomID,MeasureDate=:MeasureDate,MeasureTime=:MeasureTime ,Temperature=:Temperature ,Humidity=:Humidity  WHERE ID=:ID ";
	@Override
	public boolean update(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL的参数源对象
				paramSource.addValue("ID", tempratureHumidityInfo.getID());
				paramSource.addValue("StoreroomID", tempratureHumidityInfo.getStoreroomID());
				paramSource.addValue("MeasureDate", tempratureHumidityInfo.getMeasureDate());
				paramSource.addValue("MeasureTime", tempratureHumidityInfo.getMeasureTime());
				paramSource.addValue("Temperature", tempratureHumidityInfo.getTemperature());
				paramSource.addValue("Humidity", tempratureHumidityInfo.getHumidity());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
