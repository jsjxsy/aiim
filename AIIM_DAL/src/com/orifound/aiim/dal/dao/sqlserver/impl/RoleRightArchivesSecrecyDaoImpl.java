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
 * ��ɫ�����ܼ���Ȩ��ϢDAOʵ����
 *
 */
public class RoleRightArchivesSecrecyDaoImpl extends JdbcDaoSupport implements IRoleRightArchivesSecrecyDao
{

	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ���캯��
	 */
	public RoleRightArchivesSecrecyDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public RoleRightArchivesSecrecyDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * ��ѯָ����ɫ�ĵ����ܼ���Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_By_RolesID = "SELECT A.ID,RolesID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives,Name,Remark FROM DDR_UserRoles_ArchivesSecrecy A,DD_ArchivesSecrecy B WHERE RolesID IN (:RolesID) AND A.SecrecyID=B.ID ORDER BY B.ID";
	
	
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//�������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
				
			}
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}
			
			//ת����ɫ�������
			if (pFlag)
			{
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				List<RoleRightArchivesSecrecy> pRoleRightArchivesSecrecys=namedParameterJdbcTemplate.query(SQL_SELECT_By_RolesID,paramSource,new RoleRightArchivesSecrecyMapper());

				//���ز�ѯ���
				if (pRoleRightArchivesSecrecys.size() > 0)
				{
					for (RoleRightArchivesSecrecy item : pRoleRightArchivesSecrecys)
					{
						item.setID(item.getSecrecyID());
						roleRightArchivesSecrecys.put(item.getSecrecyID(), item);
					}
				}

				//���پֲ�����
				pRoleRightArchivesSecrecys=null;
				namedParameterJdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
			pRoleID=null;
		}

		return pFlag;
	}

}
