package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IStoreAddressInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreAddressInfo;
/**
 * 档案上架位置信息表的DAO实现类
 * @author Administrator
 *
 */
public class StoreAddressInfoDaoImpl extends JdbcDaoSupport implements IStoreAddressInfoDao {
	/**
	 * 构造函数
	 */
	public StoreAddressInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public StoreAddressInfoDaoImpl(DataSource dataSource) {
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
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 */
	private class StoreAddressInfoMapper implements RowMapper<StoreAddressInfo>
	{
		
		@Override
		public StoreAddressInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int storeAddressID = rs.getInt("StoreAddressID");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			String storeAddressFullName = rs.getString("StoreAddressFullName");
			Date putTime = rs.getTimestamp("PutTime");
			
			return new StoreAddressInfo(storeAddressID,archivesBoxBarcode,storeAddressFullName,putTime);
		}
	}
	// Table Name: StoreAddressInfo
	// Columns List,Can Used in SELECT SQL: StoreAddressID,ArchivesBoxBarcode,StoreAddressFullName,PutTime
	// Columns List,Can Used in INSERT SQL: :StoreAddressID,:ArchivesBoxBarcode,:StoreAddressFullName,:PutTime
	// Columns List,Can Used in UPDATE SQL: StoreAddressID=:StoreAddressID,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName,PutTime=:PutTime

	
	/**
	 * 添加档案上架位置信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO StoreAddressInfo(StoreAddressID,ArchivesBoxBarcode,StoreAddressFullName,PutTime)" +
			" VALUES(:StoreAddressID,:ArchivesBoxBarcode,:StoreAddressFullName,:PutTime)";
	@Override
	public boolean add(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StoreAddressID", storeAddressInfo.getStoreAddressID());
				parameterSource.addValue("ArchivesBoxBarcode", storeAddressInfo.getArchivesBoxBarcode());
				parameterSource.addValue("StoreAddressFullName", storeAddressInfo.getStoreAddressFullName());
				parameterSource.addValue("PutTime", storeAddressInfo.getPutTime());
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
				System.out.println("error:"+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean delete(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	/**
	 * 根据盒条形码查询档案上架位置信息的SQL语句
	 */
	private final String SQL_SELECT_findByBoxBarcode = "SELECT * FROM StoreAddressInfo WHERE ArchivesBoxBarcode = ?";
	@Override
	public boolean findByBoxBarcode(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
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
				List<StoreAddressInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByBoxBarcode,new StoreAddressInfoMapper(),storeAddressInfo.getArchivesBoxBarcode());

				
				//返回查询结果
				if (pEntitys.size() > 0) {
					storeAddressInfo.cloneFrom(pEntitys.get(0));
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
	public boolean findByStoreAddressID(int storeAddressID, List<StoreAddressInfo> storeAddressInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据盒条形码更新档案上架位置信息的SQL语句
	 */
	private String SQL_UPDATE = "UPDATE StoreAddressInfo SET StoreAddressID=:StoreAddressID,StoreAddressFullName=:StoreAddressFullName,PutTime=:PutTime WHERE ArchivesBoxBarcode=:ArchivesBoxBarcode";
	@Override
	public boolean update(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StoreAddressID", storeAddressInfo.getStoreAddressID());
				parameterSource.addValue("ArchivesBoxBarcode", storeAddressInfo.getArchivesBoxBarcode());
				parameterSource.addValue("StoreAddressFullName", storeAddressInfo.getStoreAddressFullName());
				parameterSource.addValue("PutTime", storeAddressInfo.getPutTime());
				namedParameterJdbcTemplate.update(SQL_UPDATE, parameterSource);

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
	 * 检查条形码是否存在的SQL语句
	 */
	private final String SQL_SELECT_checkBoxBarcodeExist = "SELECT COUNT(ArchivesBoxBarcode) FROM dbo.StoreAddressInfo WHERE ArchivesBoxBarcode=?";
	@Override
	public boolean checkBoxBarcodeExist(String archivesBoxBarcode, ErrInfo pErrInfo) {
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
				int rowNum = jdbcTemplate.queryForInt(SQL_SELECT_checkBoxBarcodeExist,archivesBoxBarcode);
				if(rowNum>0){
					resultFlag = true;
				}else{
					resultFlag = false;
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

	
	
	
}
