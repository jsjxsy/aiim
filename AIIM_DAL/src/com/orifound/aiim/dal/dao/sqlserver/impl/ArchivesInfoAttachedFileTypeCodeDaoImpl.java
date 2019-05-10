package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoAttachedFileTypeCodeDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案信息原文表的Dao实现类
 * @author Administrator
 *
 */
public class ArchivesInfoAttachedFileTypeCodeDaoImpl extends JdbcDaoSupport implements IArchivesInfoAttachedFileTypeCodeDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesInfoAttachedFileMapper implements RowMapper<ArchivesInfoAttachedFile>
	{
		@Override
		public ArchivesInfoAttachedFile mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			int orderID = rs.getInt("OrderID");
			String title = rs.getString("Title");
			String oriFileName = rs.getString("OriFileName");
			double originalSize = rs.getDouble("OriginalSize");
			String originalType = rs.getString("OriginalType");
			Date attachedTime = rs.getTimestamp("AttachedTime");
			int fetchFullTextRequest = rs.getInt("FetchFullTextRequest");
			String archivingFileName = rs.getString("ArchivingFileName");
			String resaveFileName = rs.getString("ResaveFileName");
			double resaveSize = rs.getDouble("ResaveSize");
			String resaveType = rs.getString("ResaveType");
			Date resaveTime = rs.getTimestamp("ResaveTime");
			boolean deleteFlag = rs.getBoolean("DeleteFlag");
			String remark = rs.getString("Remark");
			
			return new ArchivesInfoAttachedFile(iD,archivesTypeID,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
		}
	}
	
	/**
	 * 插入档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_INSERT_AttachedFile = "INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,FetchFullTextRequest,ArchivingFileName ) VALUES" +
			                                                                 "(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:FetchFullTextRequest,:ArchivingFileName)";
	
	/**
	 * 根据原文电子文件名查找档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_SELECT_AttachedFileIDByName = "SELECT ID FROM %1$s WHERE OriFileName=?";
	
	/**
	 * 根据原文电子序号查找档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_SELECT_AttachedFileById = "SELECT * FROM %1$s WHERE ID=?";
	
	
	/**
	 *更新档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_UPDATE_AttachedFile = "UPDATE %1$s SET ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,OrderID=:OrderID,Title=:Title,OriFileName=:OriFileName,OriginalSize=:OriginalSize,OriginalType=:OriginalType,FetchFullTextRequest=:FetchFullTextRequest WHERE ID=:ID";
	
	/**
	 *查找档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_SELECT_FindAttachedFiles = "SELECT * FROM %1$s WHERE NBXH=?";
	
	/**
	 *查找档案原文电子文件信息记录的SQL语句
	 */
	private final String SQL_DELETE_DeleteAttachedFile = "DELETE FROM %1$s WHERE ID=?";
	
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
	 * 检查指定的档案分类信息下其档案归档工作表信息是否正确赋值了
	 * 
	 * @param archivesType
	 *            档案分类信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableName(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表非法为空");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.档案原文信息表) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案原文信息表非法为空。");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案原文信息表非法为空。");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案原文信息表非法为空。");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
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
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
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
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息对象为空");
			}else if(archivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息所属的内部序号非法为0");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_INSERT_AttachedFile, archivesInfoWorkingTableName);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesTypeID", archivesInfoAttachedFile.getArchivesTypeID());
				parameterSource.addValue("NBXH", archivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", archivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", archivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", archivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", archivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", archivesInfoAttachedFile.getOriginalType()); 
				parameterSource.addValue("ArchivingFileName", archivesInfoAttachedFile.getArchivingFileName());
				parameterSource.addValue("FetchFullTextRequest", archivesInfoAttachedFile.getFetchFullTextRequest());
				
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
	public boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType, int archivesInfoAttachedFileID, ErrInfo pErrInfo) {
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
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}

			if (pFlag) {
				if (archivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("原文信息编号非法为0");
				}
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				localSql = String.format(SQL_DELETE_DeleteAttachedFile, archivesInfoWorkingTableName);
				
				getJdbcTemplate().update(localSql,archivesInfoAttachedFileID);
	
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
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		System.out.println("查找成功");
		return true;
	}

	@Override
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType, int pNBXH, List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo) {
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
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
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
				if (archivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("要返回的原文电子档案信息集合对象没有赋值");
				}
			}
			
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_FindAttachedFiles, archivesInfoWorkingTableName);
				
				List<ArchivesInfoAttachedFile> pArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new ArchivesInfoAttachedFileMapper(),pNBXH);
				
				if (pArchivesInfoAttachedFiles.size()>0) {
					archivesInfoAttachedFiles.addAll(pArchivesInfoAttachedFiles);
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
	public boolean findArchivesInfoAttachedFileByName(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
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
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要查询的原文电子文件信息对象为空");
			}else if(archivesInfoAttachedFile.getOriFileName() == null){
				pFlag = false;
				pErrInfo.getContent().append("要查询的原文电子文件信息的文件名非法为空");
			}else if("".equals(archivesInfoAttachedFile.getOriFileName())){
				pFlag = false;
				pErrInfo.getContent().append("要查询的原文电子文件信息的文件名非法为空");
			}
			
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileIDByName, archivesInfoWorkingTableName);
				
				List<Integer> ids = getJdbcTemplate().queryForList(localSql, Integer.class,archivesInfoAttachedFile.getOriFileName());
				
				if (ids.size()>0) {
					archivesInfoAttachedFile.setID(ids.get(0));
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
	public boolean updateArchivesInfoAttachedFile(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
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
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息对象为空");
			}else if(archivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要新增的原文电子文件信息所属的内部序号非法为0");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;

				localSql = String.format(SQL_UPDATE_AttachedFile, archivesInfoWorkingTableName);
				
                NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesTypeID", archivesInfoAttachedFile.getArchivesTypeID());
				parameterSource.addValue("NBXH", archivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", archivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", archivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", archivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", archivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", archivesInfoAttachedFile.getOriginalType());
				parameterSource.addValue("FetchFullTextRequest", archivesInfoAttachedFile.getFetchFullTextRequest());
				parameterSource.addValue("ID", archivesInfoAttachedFile.getID());
				
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

	@Override
	public boolean findArchivesInfoAttachedFileById(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
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
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象为空");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("档案分类信息对象编号非法为0");
			}
			
			//检查原文电子文件信息
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对象为空");
			}else if(archivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("要查找的原文电子文件信息对编号非法为0");
			}
			
			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案原文信息表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileById, archivesInfoWorkingTableName);
				
                List<ArchivesInfoAttachedFile> pArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new ArchivesInfoAttachedFileMapper(),archivesInfoAttachedFile.getID());
				
				if (pArchivesInfoAttachedFiles.size()>0) {
					archivesInfoAttachedFile.cloneFrom(pArchivesInfoAttachedFiles.get(0));
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

}
