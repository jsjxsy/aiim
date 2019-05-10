package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IOfficialArchivesDataItemSavedMappingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;

/**
 * 公文档案数据项的映射表DAO实现类
 * 
 * @author xsy
 * 
 */
public class OfficialArchivesDataItemSavedMappingDaoImpl extends JdbcDaoSupport
		implements IOfficialArchivesDataItemSavedMappingDao {
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class OfficialArchivesDataItemSavedMappingMapper implements
			RowMapper<OfficialArchivesDataItemSavedMapping> {

		@Override
		public OfficialArchivesDataItemSavedMapping mapRow(ResultSet rs,
				int rowNum) throws SQLException {
			int iD = rs.getInt("ID");
			int archivesTypeSavedMappingID = rs
					.getInt("ArchivesTypeSavedMappingID");
			int srcOfficialArchivesTypeDataItemID = rs
					.getInt("SrcOfficialArchivesTypeDataItemID");
			int desArchivesTypeDataItemID = rs
					.getInt("DesArchivesTypeDataItemID");
			String columnName=rs.getString("columnName");

			return new OfficialArchivesDataItemSavedMapping(iD,
					archivesTypeSavedMappingID,
					srcOfficialArchivesTypeDataItemID,
					desArchivesTypeDataItemID,
					columnName);
		}
	}

	/**
	 * 构造函数
	 */
	public OfficialArchivesDataItemSavedMappingDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * 
	 * @param dataSource
	 *            数据源
	 */
	public OfficialArchivesDataItemSavedMappingDaoImpl(DataSource dataSource) {
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
	
	/**
	 * 查询所有的公文档案数据系那个保存映射的SQL语句
	 */
	private final String SQL_SELECT_By_ArchivesTypeSavedMappingID  = "SELECT a.ID,ArchivesTypeSavedMappingID, SrcOfficialArchivesTypeDataItemID, DesArchivesTypeDataItemID, b.ColumnName"
                      +" FROM  DD_OfficialArchivesDataItemSavedMapping AS a ,"
                      +" DD_DataItem AS b WHERE a.ArchivesTypeSavedMappingID=? AND a.SrcOfficialArchivesTypeDataItemID = b.ID ";
                      
	@Override
	public boolean delete(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean findByArchivesTypeSavedMappingID (Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo){
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
				List<OfficialArchivesDataItemSavedMapping> pOfficialArchivesDataItemSavedMappings=jdbcTemplate.query(SQL_SELECT_By_ArchivesTypeSavedMappingID,new OfficialArchivesDataItemSavedMappingMapper(),archivesTypeSavedMappingID);
				
				//返回查询结果
				if (pOfficialArchivesDataItemSavedMappings.size() > 0) {
					for (OfficialArchivesDataItemSavedMapping item : pOfficialArchivesDataItemSavedMappings) {
						officialArchivesDataItemSavedMappings.put(item.getDesArchivesTypeDataItemID(), item);
					}
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
	public boolean findByID(
			int pID,
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
