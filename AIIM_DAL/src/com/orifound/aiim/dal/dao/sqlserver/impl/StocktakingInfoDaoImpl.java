package com.orifound.aiim.dal.dao.sqlserver.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IStocktakingInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StocktakingInfo;
/**
 * 库房盘点工作信息表的DAO实现类
 * @author Administrator
 *
 */
public class StocktakingInfoDaoImpl extends JdbcDaoSupport implements IStocktakingInfoDao {
	
	/**
	 * 构造函数
	 */
	public StocktakingInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StocktakingInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StocktakingInfoMapperExtendStoreroomName implements RowMapper<StocktakingInfo>
	{
		
		@Override
		public StocktakingInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int storeroomID = rs.getInt("StoreroomID");
			Date stocktakingDate = rs.getTimestamp("StocktakingDate");
			boolean stocktakedFlag = rs.getBoolean("StocktakedFlag");
			String storeroomName = rs.getString("StoreroomName");
			
			return new StocktakingInfo(iD,storeroomID,storeroomName,stocktakingDate,stocktakedFlag);
		}
	}
	
	private class StocktakingInfoMapper implements RowMapper<StocktakingInfo>
	{
		
		@Override
		public StocktakingInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int storeroomID = rs.getInt("StoreroomID");
			Date stocktakingDate = rs.getTimestamp("StocktakingDate");
			boolean stocktakedFlag = rs.getBoolean("StocktakedFlag");
			
			return new StocktakingInfo(iD,storeroomID,stocktakingDate,stocktakedFlag);
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
	 * 添加库房盘点工作信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingInfo(StoreroomID,StocktakingDate,StocktakedFlag)" +
			" VALUES(:StoreroomID,:StocktakingDate,:StocktakedFlag)";

	@Override
	public boolean add(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StoreroomID",stocktakingInfo.getStoreroomID());
				parameterSource.addValue("StocktakingDate", stocktakingInfo.getStocktakingDate());
				parameterSource.addValue("StocktakedFlag", stocktakingInfo.getStocktakedFlag());
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				
				namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource,keyHolder);
				stocktakingInfo.setID(keyHolder.getKey().intValue());
				
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);

				

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
	 * 删除库房盘点工作信息的SQL语句
	 */
	private final String SQL_DELETE = "DELETE StocktakingInfo WHERE ID=?";
	@Override
	public boolean delete(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				int effectRomNum = jdbcTemplate.update(SQL_DELETE, stocktakingInfo.getID());				
				System.out.println(effectRomNum);
				if(effectRomNum>0){
					System.out.println("删除成功！");
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
	// Table Name: StocktakingInfo
	// Columns List,Can Used in SELECT SQL: ID,StoreroomID,StocktakingDate,StocktakedFlag
	// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:StocktakingDate,:StocktakedFlag
	// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,StocktakingDate=:StocktakingDate,StocktakedFlag=:StocktakedFlag

	/**
	 * 查找所有的库房盘点工作信息的SQL语句
	 */ //%1$s:起始记录数；%2$s：结尾记录数
	private final String SQL_SELECT_findAll = "SELECT * FROM " +
			" ( SELECT  A.*,B.Name AS StoreroomName ,ROW_NUMBER() over(order by StocktakingDate desc ,A.ID desc) as RowNum " +
			" FROM StocktakingInfo A ,DD_StoreroomStructure B  WHERE A.StoreroomID= B.ID)TEMPTABLE " +
			" WHERE ROWNUM BETWEEN %1$s and %2$s ";
	//查询所有库房盘点信息的总数
	private final String SQL_SELECT_findAll_count = "SELECT  COUNT(*) FROM StocktakingInfo ";
	
	@Override
	public boolean findAll(List<StocktakingInfo> stocktakingInfos, DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
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
				int rowCount = jdbcTemplate.queryForInt(SQL_SELECT_findAll_count);
				dataPageInfo.setRowCount(rowCount);
				String sql = String.format(SQL_SELECT_findAll, (dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize()+1,dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());
				List<StocktakingInfo> pEntitys=jdbcTemplate.query(sql,new StocktakingInfoMapperExtendStoreroomName());

				//返回查询结果
				if (pEntitys.size() > 0) {
					stocktakingInfos.addAll(pEntitys);
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
	 * 根据唯一编号获取库房盘点工作信息的SQL语句
	 */
	private final String SQL_SELECT_findByID = "SELECT A.* ,B.Name AS StoreroomName FROM StocktakingInfo A,DD_StoreroomStructure B  WHERE A.StoreroomID = B.ID AND A.ID = ? ";
	@Override
	public boolean findByID( StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				List<StocktakingInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByID,new StocktakingInfoMapperExtendStoreroomName(),stocktakingInfo.getID());

				//返回查询结果
				if (pEntitys.size() > 0) {
					stocktakingInfo.cloneFrom(pEntitys.get(0));
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
	 * 更新指定的库房盘点状态的SQL语句
	 */
	private final String SQL_UPDATE_stocktakedFlag = "UPDATE StocktakingInfo SET StocktakedFlag = 1 WHERE ID=?";
	@Override
	public boolean update(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_UPDATE_stocktakedFlag,stocktakingInfo.getID());

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
