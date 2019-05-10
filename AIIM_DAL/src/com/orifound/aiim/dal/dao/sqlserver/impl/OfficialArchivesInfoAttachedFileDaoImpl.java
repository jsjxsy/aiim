package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IOfficialArchivesInfoAttachedFileDao;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.OfficialArchivesType;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 * 
 * 公文档案原文信息表的DAO实现类
 *
 */
public class OfficialArchivesInfoAttachedFileDaoImpl extends JdbcDaoSupport implements
		IOfficialArchivesInfoAttachedFileDao {
	
	/**
	 * 构造函数
	 */
	public OfficialArchivesInfoAttachedFileDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public OfficialArchivesInfoAttachedFileDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class OfficialArchivesInfoAttachedFileMapper implements RowMapper<OfficialArchivesInfoAttachedFile>
	{
		
		@Override
		public OfficialArchivesInfoAttachedFile mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int nBXH = rs.getInt("NBXH");
			int orderID = rs.getInt("OrderID");
			String title = rs.getString("Title");
			String oriFileName = rs.getString("OriFileName");
			long originalSize = rs.getLong("OriginalSize");
			String originalType = rs.getString("OriginalType");
			Date attachedTime = rs.getTimestamp("AttachedTime");
			int fetchFullTextRequest = rs.getInt("FetchFullTextRequest");
			String archivingFileName = rs.getString("ArchivingFileName");
			String resaveFileName = rs.getString("ResaveFileName");
			long resaveSize = rs.getLong("ResaveSize");
			String resaveType = rs.getString("ResaveType");
			Date resaveTime = rs.getTimestamp("ResaveTime");
			boolean deleteFlag = rs.getBoolean("DeleteFlag");
			String remark = rs.getString("Remark");
			
			return new OfficialArchivesInfoAttachedFile(iD,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
		}
	}
	
	/**
	 * 检查指定的档案分类信息下其TableType信息是否正确赋值了
	 * @param archivesType 档案分类信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableName(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			if (officialArchivesType.getOfficialArchivesInfoTables() == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表非法为空");
			} else {
				if (officialArchivesType.getOfficialArchivesInfoTables().containsKey(EnumOfficialArchivesInfoTableType.公文档案原文信息表) == false) {
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类（" + officialArchivesType.getCode() + "）的TableType信息非法为空。");
				} else {
					String tableName = "";
					tableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案原文信息表).getTableName();
					if (tableName == null) {
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类（" + officialArchivesType.getCode() + "）的TableType信息非法为空。");
					} else {
						if (tableName.length() == 0) {
							pFlag = false;
							pErrInfo.getContent().append("公文档案分类（" + officialArchivesType.getCode() + "）的TableType信息非法为空。");
						}
					}
				}
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
	 * 插入值的SQL语句
	 */
	private final String SQL_INSERT_AttachedFile = "INSERT INTO %1$s(NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,FetchFullTextRequest,ArchivingFileName) VALUES(:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:FetchFullTextRequest,:ArchivingFileName)";

	private final String SQL_SELECT_FindAttachedFiles="SELECT * FROM %1$s WHERE NBXH=?";
	
	private final String SQL_DELETE_DeleteAttachedFile="DELETE FROM %1$s WHERE ID=?";
	
	private final String SQL_UPDATE_AttachedFile="UPDATE %1$s SET NBXH=:NBXH,OrderID=:OrderID,Title=:Title,OriFileName=:OriFileName,OriginalSize=:OriginalSize,OriginalType=:OriginalType,FetchFullTextRequest=:FetchFullTextRequest WHERE ID=:ID";
	
	private final String SQL_SELECT_AttachedFileById = "SELECT * FROM %1$s WHERE ID=?";
	
	/**
	 * 根据原文电子文件名查找档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_SELECT_AttachedFileIDByName = "SELECT ID FROM %1$s WHERE OriFileName=?";
	
	@Override
	public boolean delete(OfficialArchivesType officialArchivesType,
			int officialArchivesInfoAttachedFileID,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}

			if (pFlag) {
				if (officialArchivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("原文信息编号非法为0");
				}
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				localSql = String.format(SQL_DELETE_DeleteAttachedFile, officialArchivesInfoTableName);
				
				getJdbcTemplate().update(localSql,officialArchivesInfoAttachedFileID);
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	/**
	 * 查找所有的公文档案信息
	 * @param officialArchivesInfoAttachedFiles
	 * @param pErrInfo
	 * @return
	 */
	public boolean findAll(
			OfficialArchivesType officialArchivesType,int pNBXH,
			List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类信息
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			if (pFlag) {
				if (pNBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("原文所属档案的内部序号非法为0");
				}
			}
			
			if (pFlag) {
				if (officialArchivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("要返回的原文电子档案信息集合对象没有赋值");
				}
			}
			
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_FindAttachedFiles, officialArchivesInfoTableName);
				
				List<OfficialArchivesInfoAttachedFile> pOfficialArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new OfficialArchivesInfoAttachedFileMapper() ,pNBXH);
				
				if (pOfficialArchivesInfoAttachedFiles.size()>0) {
					officialArchivesInfoAttachedFiles.addAll(pOfficialArchivesInfoAttachedFiles);
				}
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	

	@Override
	public boolean findOfficialArchivesInfoAttachedFileByName(OfficialArchivesType officialArchivesType, OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要查询的原文电子文件信息对象为空");
			}else if(officialArchivesInfoAttachedFile.getOriFileName() == null){
				pFlag = false;
				pErrInfo.getContent().append("要查询的原文电子文件信息的文件名非法为空");
			}else if("".equals(officialArchivesInfoAttachedFile.getOriFileName())){
				pFlag = false;
				pErrInfo.getContent().append("要查询的原文电子文件信息的文件名非法为空");
			}
			
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileIDByName, officialArchivesInfoTableName);
				
				List<Integer> ids = getJdbcTemplate().queryForList(localSql, Integer.class,officialArchivesInfoAttachedFile.getOriFileName());
				
				if (ids.size()>0) {
					officialArchivesInfoAttachedFile.setID(ids.get(0));
				}
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	@Override
	public boolean findByID(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对象为空");
			}else if(officialArchivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对编号非法为0");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileById, officialArchivesInfoTableName);
				
                List<OfficialArchivesInfoAttachedFile> pOfficialArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new OfficialArchivesInfoAttachedFileMapper(),officialArchivesInfoAttachedFile.getID());
				
				if (pOfficialArchivesInfoAttachedFiles.size()>0) {
					officialArchivesInfoAttachedFile.cloneFrom(pOfficialArchivesInfoAttachedFiles.get(0));
				}
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean save(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息对象为空");
			}else if(officialArchivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息所属的内部序号非法为0");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s(ID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) value(:ID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_INSERT_AttachedFile, officialArchivesInfoTableName);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXH", officialArchivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", officialArchivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", officialArchivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", officialArchivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", officialArchivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", officialArchivesInfoAttachedFile.getOriginalType());
				parameterSource.addValue("FetchFullTextRequest", officialArchivesInfoAttachedFile.getFetchFullTextRequest());
				parameterSource.addValue("ArchivingFileName", officialArchivesInfoAttachedFile.getArchivingFileName());
				jdbcTemplate.update(localSql, parameterSource);
				
				// 销毁局部变量
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean update(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案分类信息
			pErrPos = 1;
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息对象为空");
			}else if(officialArchivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息所属的内部序号非法为0");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;

				localSql = String.format(SQL_UPDATE_AttachedFile, archivesInfoWorkingTableName);
				
                NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXH", officialArchivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", officialArchivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", officialArchivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", officialArchivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", officialArchivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", officialArchivesInfoAttachedFile.getOriginalType());
				parameterSource.addValue("FetchFullTextRequest", officialArchivesInfoAttachedFile.getFetchFullTextRequest());
				parameterSource.addValue("ID", officialArchivesInfoAttachedFile.getID());
				
				jdbcTemplate.update(localSql, parameterSource);

			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return pFlag;
	}


}
