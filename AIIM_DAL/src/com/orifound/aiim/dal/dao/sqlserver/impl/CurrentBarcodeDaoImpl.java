/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ICurrentBarcodeDao;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * 条形码信息表的DAO实现类（SQL Server平台）
 *
 */
public class CurrentBarcodeDaoImpl extends JdbcDaoSupport implements
		ICurrentBarcodeDao {
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class CurrentBarcodeMapper implements RowMapper<CurrentBarcode>
	{
		
		@Override
		public CurrentBarcode mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int barcodeType = rs.getInt("BarcodeType");
			int currentBarcodeNo = rs.getInt("CurrentBarcodeNo");
			
			return new CurrentBarcode(EnumBarcodeType.getEnumElement(barcodeType),currentBarcodeNo);
		}
	}
	
	/**
	 * 构造函数
	 */
	public CurrentBarcodeDaoImpl() {
	
	}
	
	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public CurrentBarcodeDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	
	//SQL语句
	/**
	 * 更新指定条码类型的条码信息的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE CurrentBarcode SET CurrentBarcodeNo=:CurrentBarcodeNo WHERE BarcodeType=:BarcodeType";
	/**
	 * 查询指定条码类型的条码信息的SQL语句
	 */
	private final String SQL_SELECT = "SELECT * FROM CurrentBarcode WHERE BarcodeType=? ";
	
	/**
	 * 插入指定条码类型的条码信息的SQL语句
	 */
	private final String SQL_INSERT="INSERT INTO CurrentBarcode(BarcodeType,CurrentBarcodeNo) VALUES(:BarcodeType,:CurrentBarcodeNo)";
	
	//"UPDATE Config SET ConfigValue=:ConfigValue WHERE ID=:ID";
	// Table Name: CurrentBarcode
	// Columns List,Can Used in SELECT SQL: BarcodeType,CurrentBarcodeNo
	// Columns List,Can Used in INSERT SQL: :BarcodeType,:CurrentBarcodeNo
	// Columns List,Can Used in UPDATE SQL: BarcodeType=:BarcodeType,CurrentBarcodeNo=:CurrentBarcodeNo

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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICurrentBarcodeDao#findByBarcodeType(com.orifound.aiim.entity.EnumBarcodeType, com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override	
	public boolean findByBarcodeType(CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
				
			// 检查条码信息是否初始化	
			pErrPos = 1;
			if(currentBarcode==null){
				pFlag = false;
				pErrInfo.getContent().append("条码信息对象未初始化。");
			}else{
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("条形码类型未赋值。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<CurrentBarcode> currentBarcodes=jdbcTemplate.query(SQL_SELECT,new CurrentBarcodeMapper(),currentBarcode.getBarcodeType().getEnumValue());
				
				//返回查询结果
				if (currentBarcodes.size() > 0) {
					currentBarcode.cloneFrom(currentBarcodes.get(0));					
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICurrentBarcodeDao#update(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// 检查条码信息是否赋值	
			pErrPos = 1;
			if(currentBarcode==null){
				pFlag = false;
				pErrInfo.getContent().append("条码信息对象未初始化。");
			}else{
				if(currentBarcode.getBarcodeType()== EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("条码类型非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//JdbcTemplate jdbcTemplate = getJdbcTemplate();
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);

				//返回查询结果
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				paramSource.addValue("CurrentBarcodeNo", currentBarcode.getCurrentBarcodeNo(), Types.INTEGER);
				paramSource.addValue("BarcodeType", currentBarcode.getBarcodeType().getEnumValue(), Types.INTEGER);
				 //CurrentBarcodeNo WHERE BarcodeType=:BarcodeType
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				
				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICurrentBarcodeDao#update(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查条码信息是否赋值			
				pErrPos = 1;
				if(currentBarcode==null){
					pFlag = false;
					pErrInfo.getContent().append("条码信息对象未初始化。");
				}else{
					if(currentBarcode.getBarcodeType()== EnumBarcodeType.NONE){
						pFlag = false;
						pErrInfo.getContent().append("条码类型未赋值。");
					}
				}
			
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("BarcodeType", currentBarcode.getBarcodeType().getEnumValue(),Types.INTEGER);
				paramSource.addValue("CurrentBarcodeNo", currentBarcode.getCurrentBarcodeNo(),Types.INTEGER);				
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
