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

import com.orifound.aiim.dal.dao.IRoleRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightArchivesSecrecy;

/**
 * 角色档案密级授权信息DAO实现类
 *
 */
public class RoleRightArchivesSecrecyDaoImpl extends JdbcDaoSupport implements IRoleRightArchivesSecrecyDao
{

	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class RoleRightArchivesSecrecyMapper implements RowMapper<RoleRightArchivesSecrecy>
	{
		
		@Override
		public RoleRightArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int secrecyID = rs.getInt("SecrecyID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
	        String name = rs.getString("Name");
	        String remark = rs.getString("Remark");
			return new RoleRightArchivesSecrecy(iD,rolesID,secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives,name,remark);
		}
	}
	
	/**
	 * 构造函数
	 */
	public RoleRightArchivesSecrecyDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public RoleRightArchivesSecrecyDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * 查询指定角色的档案密级授权信息的SQL语句
	 */
	private final String SQL_SELECT_By_RolesID = "SELECT A.ID,RolesID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives,Name,Remark FROM DDR_UserRoles_ArchivesSecrecy A,DD_ArchivesSecrecy B WHERE RolesID IN (:RolesID) AND A.SecrecyID=B.ID ORDER BY B.ID";
	
	
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
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesSecrecyDao#save(com.orifound.aiim.entity.RoleRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(RoleRightArchivesSecrecy roleRightArchivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesSecrecyDao#delete(com.orifound.aiim.entity.RoleRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(RoleRightArchivesSecrecy roleRightArchivesSecrecy, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightArchivesSecrecyDao#findByRoleID(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByRoleID(int[] pRoleID, Map<Integer, ArchivesSecrecy> roleRightArchivesSecrecys, ErrInfo pErrInfo)
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
				List<RoleRightArchivesSecrecy> pRoleRightArchivesSecrecys=namedParameterJdbcTemplate.query(SQL_SELECT_By_RolesID,paramSource,new RoleRightArchivesSecrecyMapper());

				//返回查询结果
				if (pRoleRightArchivesSecrecys.size() > 0)
				{
					for (RoleRightArchivesSecrecy item : pRoleRightArchivesSecrecys)
					{
						item.setID(item.getSecrecyID());
						roleRightArchivesSecrecys.put(item.getSecrecyID(), item);
					}
				}

				//销毁局部变量
				pRoleRightArchivesSecrecys=null;
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
			pRoleID=null;
		}

		return pFlag;
	}

}
