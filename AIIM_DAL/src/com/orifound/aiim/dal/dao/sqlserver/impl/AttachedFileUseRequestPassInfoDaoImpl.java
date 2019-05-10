/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IAttachedFileUseRequestPassInfoDao;
import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
/**
 * 原文利用申请通过信息表的DAO实现类
 *
 */
public class AttachedFileUseRequestPassInfoDaoImpl extends JdbcDaoSupport implements IAttachedFileUseRequestPassInfoDao
{
	/**
	 * 构造函数
	 */
	public AttachedFileUseRequestPassInfoDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public AttachedFileUseRequestPassInfoDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	// Table Name: AttachedFileUseRequestPassInfo
	// Columns List,Can Used in SELECT SQL: ID,ArchivesTypeID,NBXH,ArchivesID,Title,UserID,ExpirationDate
	// Columns List,Can Used in INSERT SQL: :ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UserID,:ExpirationDate
	// Columns List,Can Used in UPDATE SQL: ID=:ID,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,UserID=:UserID,ExpirationDate=:ExpirationDate


	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	private class AttachedFileUseRequestPassInfoMapper implements RowMapper<AttachedFileUseRequestPassInfo>
	{
		
		@Override
		public AttachedFileUseRequestPassInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			int userID = rs.getInt("UserID");
			Date expirationDate = rs.getTimestamp("ExpirationDate");
			
			return new AttachedFileUseRequestPassInfo(iD,archivesTypeID,nBXH,archivesID,title,userID,expirationDate);
		}
	}
	
	/**
	 * 查找指定的用户档案原文申请通过信息存在的有效数量的SQL语句
	 */
	private final String SQL_SELECT_CountByRequestPassInfo = "SELECT COUNT(ID) FROM AttachedFileUseRequestPassInfo WHERE ArchivesTypeID=:ArchivesTypeID AND NBXH=:NBXH AND UserID=:UserID AND ExpirationDate>GETDATE()";
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.dal.dao.IAttachedFileUseRequestPassDao#findCountByRequestPassInfo(int, int, int, com.orifound.aiim.entity.IntegerEx, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCountByRequestPassInfo(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pCount, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag)
			{
				//SQL_SELECT_CountByRequestPassInfo = "SELECT COUNT(ID) FROM AttachedFileUseRequestPassInfo WHERE 
				//ArchivesTypeID=:ArchivesTypeID AND NBXH=:NBXH AND UserID=:UserID AND ExpirationDate>GETDATE()";
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);
				paramSource.addValue("NBXH", pNBXH, Types.INTEGER);
				paramSource.addValue("UserID", pUserID, Types.INTEGER);

				pErrPos = 3;
				int count= namedParameterJdbcTemplate.queryForInt(SQL_SELECT_CountByRequestPassInfo, paramSource);
				
				//返回查询结果
				pCount.setValue(count);

				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 添加原文利用申请通过信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO AttachedFileUseRequestPassInfo(ArchivesTypeID,NBXH,ArchivesID,Title,UserID,ExpirationDate) VALUES(:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UserID,:ExpirationDate) ";

	@Override
	public boolean add(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {

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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				//:ID,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UserID,:ExpirationDate
				paramSource.addValue("ArchivesTypeID",attachedFileUseRequestPassInfo.getArchivesTypeID());
				paramSource.addValue("NBXH", attachedFileUseRequestPassInfo.getNBXH());
				paramSource.addValue("ArchivesID",attachedFileUseRequestPassInfo.getArchivesID());
				paramSource.addValue("Title",attachedFileUseRequestPassInfo.getTitle());
				paramSource.addValue("UserID",attachedFileUseRequestPassInfo.getUserID());
				paramSource.addValue("ExpirationDate",attachedFileUseRequestPassInfo.getExpirationDate());
							
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				attachedFileUseRequestPassInfo.setID(keyHolder.getKey().intValue());
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
	public boolean delete(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByID(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	

}
