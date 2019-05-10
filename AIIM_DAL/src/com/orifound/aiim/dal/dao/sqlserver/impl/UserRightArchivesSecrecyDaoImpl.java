/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

/**
 * �û������ܼ���Ȩ��ϢDAOʵ����
 *
 */
public class UserRightArchivesSecrecyDaoImpl extends JdbcDaoSupport implements IUserRightArchivesSecrecyDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class UserRightArchivesSecrecyMapper implements RowMapper<UserRightArchivesSecrecy>
	{
		
		@Override
		public UserRightArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int secrecyID = rs.getInt("SecrecyID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
			String name = rs.getString("Name");
	        String remark = rs.getString("Remark");
			return new UserRightArchivesSecrecy(iD, userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives,name, remark);
		}
	}
	
	private class UserRightArchivesSecrecyMapper2 implements RowMapper<UserRightArchivesSecrecy>
	{
		
		@Override
		public UserRightArchivesSecrecy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int secrecyID = rs.getInt("SecrecyID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
			return new UserRightArchivesSecrecy(iD, userID, secrecyID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		}
	}
	
	/**
	 * ���캯��
	 */
	public UserRightArchivesSecrecyDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserRightArchivesSecrecyDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * ��ѯָ���û��ĵ����ܼ���Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_By_UserID = "SELECT A.ID,UserID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives,Name,Remark FROM UserRight_ArchivesSecrecy A,DD_ArchivesSecrecy B WHERE UserID=? AND A.SecrecyID=B.ID ORDER BY B.ID";
	
	/**
	 * ��ѯָ���û��ĵ����ܼ���Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_UserRight_By_UserID = "SELECT ID,UserID,SecrecyID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives FROM UserRight_ArchivesSecrecy WHERE UserID = ?  ORDER BY ID";
	
	
	/**
	 * ���Ӷ�ָ���û��ĵ����ܼ���Ȩ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO UserRight_ArchivesSecrecy(UserID,SecrecyID) VALUES(:UserID,:SecrecyID)";
	
	/**
	 * ɾ��ָ���û��ĵ����ܼ���Ȩ��Ϣ�ĵ�SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM UserRight_ArchivesSecrecy where ID = ?";
	
	
	/**
	 * ɾ��ָ���û��ĵ����ܼ���Ȩ��Ϣ�ĵ�SQL���
	 */
	private final String SQL_DELETE_By_UserID = "DELETE FROM UserRight_ArchivesSecrecy WHERE UserID = ?";
	
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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao#save(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			pErrPos = 1;
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userRightArchivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û������ܼ�Ȩ�ޱ��ʵ�������Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightArchivesSecrecy.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û������ܼ�Ȩ�޵��û���ŷǷ�Ϊ��!");
				}
			}
			if (pFlag) {
				pErrPos = 3;
				if (userRightArchivesSecrecy.getSecrecyID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û������ܼ�Ȩ�޵ĵ����ܼ���ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 4;
				//INSERT INTO UserRight_ArchivesSecrecy(ID,UserID,SecrecyID) VALUES(:ID,:UserID,:SecrecyID)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userRightArchivesSecrecy.getUserID(), Types.INTEGER);
				sqlParameterSource.addValue("SecrecyID", userRightArchivesSecrecy.getSecrecyID(), Types.INTEGER);

				parameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource);
				//���پֲ�����
				parameterJdbcTemplate = null;
				sqlParameterSource = null;
			}
		} catch (BadSqlGrammarException e) {
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao#delete(com.orifound.aiim.entity.UserRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserRightArchivesSecrecy userRightArchivesSecrecy, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}

			if (pFlag) {
				pErrPos = 2;
				if ( userRightArchivesSecrecy == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û������ܼ�Ȩ�޶���Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightArchivesSecrecy.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û������ܼ�Ȩ�޵ı�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,userRightArchivesSecrecy.getID());

				//���پֲ�����
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesSecrecyDao#findByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, Map<Integer, ArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

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
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightArchivesSecrecy> pUserRightArchivesSecrecys=jdbcTemplate.query(SQL_SELECT_By_UserID,new UserRightArchivesSecrecyMapper(),pUserID);
				
				//���ز�ѯ���
				if (pUserRightArchivesSecrecys.size() > 0)
				{
					for (UserRightArchivesSecrecy item : pUserRightArchivesSecrecys)
					{
						item.setID(item.getSecrecyID());
						userRightArchivesSecrecys.put(item.getSecrecyID(), item);
					}
				}

				//���پֲ�����
				pUserRightArchivesSecrecys=null;
				jdbcTemplate = null;
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
		}

		return pFlag;
	}

	public boolean findByUserID(int pUserID, List<UserRightArchivesSecrecy> userRightArchivesSecrecys, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

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
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightArchivesSecrecy> pUserRightArchivesSecrecys=jdbcTemplate.query(SQL_SELECT_UserRight_By_UserID,new UserRightArchivesSecrecyMapper2(),pUserID);
				
				//���ز�ѯ���
				if (pUserRightArchivesSecrecys.size() > 0)
				{
					userRightArchivesSecrecys.addAll(pUserRightArchivesSecrecys);
				}

				//���پֲ�����
				pUserRightArchivesSecrecys=null;
				jdbcTemplate = null;
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
		}

		return pFlag;
	}

	@Override
	public boolean deleteByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

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
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserID,pUserID);
				//���پֲ�����
				jdbcTemplate = null;
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
		}

		return pFlag;
	}
}
