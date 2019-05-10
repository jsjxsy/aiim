/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类字典表的DAO实现类（SQL Server平台）
 *
 */
public class ArchivesTypeDaoImpl extends JdbcDaoSupport implements IArchivesTypeDao
{
	/**
	 * 查询结果集到实体类的映射器	，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesTypeMapper implements RowMapper<ArchivesType>
	{
		
		@Override
		public ArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean topFlag = rs.getBoolean("TopFlag");
			String name = rs.getString("Name");
			String code = rs.getString("Code");
			int parentID = rs.getInt("ParentID");
			int topTypeID = rs.getInt("TopTypeID");
			String fullCode = rs.getString("FullCode");
			String fullName = rs.getString("FullName");
			boolean endFlag = rs.getBoolean("EndFlag");
			int contentIDFormatLength = rs.getInt("ContentIDFormatLength");
			int subContentIDFormatLength = rs.getInt("SubContentIDFormatLength");
			String archivesIDExpressionPrefix = rs.getString("ArchivesIDExpressionPrefix");
			String archivesIDExpression = rs.getString("ArchivesIDExpression");
			String subArchivesIDExpression = rs.getString("SubArchivesIDExpression");
			String attachedFileSavePath = rs.getString("AttachedFileSavePath");
			int renewPeriod = rs.getInt("RenewPeriod");
			int personalArchivesFlag = rs.getInt("PersonalArchivesFlag");
			String remark = rs.getString("Remark");
			
			return new ArchivesType(iD,topFlag,name,code,parentID,topTypeID,fullCode,fullName,endFlag,contentIDFormatLength,subContentIDFormatLength,archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression,attachedFileSavePath,renewPeriod,personalArchivesFlag,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesTypeDaoImpl()
	{

	}
	
	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesTypeDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	//SQL语句
	
	/**
	 * 查找顶层档案分类（一级类目）的SQL语句
	 */
	private final String SQL_SELECT_FORLEVEL1="SELECT * FROM DD_ArchivesType WHERE TopFlag=1 ORDER BY FullCode";
	/**
	 * 查找下一级档案分类的SQL语句
	 */
	private final String SQL_SELECT_FORCHILD = "SELECT * FROM DD_ArchivesType WHERE ParentID=? ORDER BY FullCode";
	

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
				if (pErrInfo.getException()!=null)
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
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDao#save(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDao#delete(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDao#update(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDao#findForLevel1(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findForLevel1(LinkedHashMap<Integer, ArchivesType> archivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			//执行SQL语句
			if (pFlag)
			{
				pErrPos=1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesType> pArchivesTypes= jdbcTemplate.query(SQL_SELECT_FORLEVEL1, new ArchivesTypeMapper());
	
				//返回查询结果
				if (pArchivesTypes.size()>0)
				{
					for (ArchivesType item : pArchivesTypes)
					{
						archivesTypes.put(item.getID(), item);
					}
				}
				
				//销毁局部变量
				pArchivesTypes=null;
				jdbcTemplate = null;
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
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesTypeDao#findForChild(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findForChild(int archivesTypeID, LinkedHashMap<Integer, ArchivesType> childArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//执行SQL语句
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesType> pArchivesTypes= jdbcTemplate.query(SQL_SELECT_FORCHILD, new ArchivesTypeMapper(), archivesTypeID);
	
				//返回查询结果
				if (pArchivesTypes.size()>0)
				{
					for (ArchivesType item : pArchivesTypes)
					{
						//作为下级分类添加至集合中返回
						childArchivesTypes.put(item.getID(), item);
						
						pErrPos=2;
						//继续递归查找该下级分类的再下一级档案分类，直到没有下级分类为止
						LinkedHashMap<Integer, ArchivesType> pChildren=new LinkedHashMap<Integer, ArchivesType>();
						if (findForChild(item.getID(), pChildren, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"查找 "+ item.getFullCode() +" 的下级分类失败: ");
							break;
						}
						else
						{
							if (pChildren.size()>0)
							{
								item.setChildArchivesTypes(pChildren);
							}
						}
					}
				}
				
				//销毁局部变量
				pArchivesTypes=null;
				jdbcTemplate = null;
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
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量

		}

		return pFlag;
	}

	@Override
	public boolean findByID(int pID, ArchivesType archivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
