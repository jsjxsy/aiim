/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightArchivesType;


/**
 * 角色档案分类授权信息的DAO实现类
 *
 */
public class RoleRightArchivesTypeDaoImpl extends JdbcDaoSupport implements IRoleRightArchivesTypeDao
{
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class RoleRightArchivesTypeMapper implements RowMapper<RoleRightArchivesType>
	{
		
		@Override
		public RoleRightArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
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
			String remark = rs.getString("Remark");
			return new RoleRightArchivesType(iD, rolesID, archivesTypeID, rightFlag_ArchivesInfo, rightFlag_AttachedFile, rightFlag_PaperArchives, topFlag, name, code, parentID, topTypeID, fullCode, fullName, endFlag, contentIDFormatLength, subContentIDFormatLength, archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression, attachedFileSavePath, renewPeriod, remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public RoleRightArchivesTypeDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public RoleRightArchivesTypeDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * 查询指定角色的档案分类授权信息的SQL语句
	 */
	private final String SQL_SELECT_By_UserID = "SELECT A.ID,RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives,TopFlag,Name,Code,ParentID,TopTypeID,FullCode,FullName,EndFlag,ContentIDFormatLength,SubContentIDFormatLength,ArchivesIDExpressionPrefix,ArchivesIDExpression,SubArchivesIDExpression,AttachedFileSavePath,RenewPeriod,Remark FROM DDR_UserRoles_ArchivesType A,DD_ArchivesType B WHERE RolesID IN (:RolesID) AND A.ArchivesTypeID=B.ID ORDER BY B.ID";
	
	
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
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao#save(com.orifound.aiim.entity.RoleRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao#delete(com.orifound.aiim.entity.RoleRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao#update(com.orifound.aiim.entity.RoleRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(RoleRightArchivesType roleRightArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesTypeDao#findByUserID(int[], java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByRoleID(int[] pRoleID, LinkedHashMap<Integer, ArchivesType> roleRightArchivesTypes, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
		try
		{
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
				
			}
			
			//检查用户编号是否非法
			if (pFlag)
			{
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}
			
			//转换角色编号数组
			if (pFlag)
			{
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				List<RoleRightArchivesType> pRoleRightArchivesTypes=namedParameterJdbcTemplate.query(SQL_SELECT_By_UserID,paramSource,new RoleRightArchivesTypeMapper());
				
				//返回查询结果
				if (pRoleRightArchivesTypes.size() > 0)
				{
					for (RoleRightArchivesType item : pRoleRightArchivesTypes)
					{    ArchivesType archivesType = item;
				         archivesType.setID(item.getArchivesTypeID());
						 roleRightArchivesTypes.put(item.getArchivesTypeID(), archivesType);
					}
				}

				//销毁局部变量
				pRoleRightArchivesTypes=null;
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
			pRoleIDs=null;
		}

		return pFlag;
	}

}
