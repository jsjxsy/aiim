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
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRightArchivesType;

/**
 * �û�����������Ȩ��Ϣ��DAOʵ����
 *
 */
public class UserRightArchivesTypeDaoImpl extends JdbcDaoSupport implements IUserRightArchivesTypeDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class UserRightArchivesTypeMapper implements RowMapper<UserRightArchivesType>
	{
		
		@Override
		public UserRightArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
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
			return new UserRightArchivesType(iD, userID, archivesTypeID, rightFlag_ArchivesInfo, rightFlag_AttachedFile, rightFlag_PaperArchives, topFlag, name, code, parentID, topTypeID, fullCode, fullName, endFlag, contentIDFormatLength, subContentIDFormatLength, archivesIDExpressionPrefix,archivesIDExpression,subArchivesIDExpression, attachedFileSavePath, renewPeriod, remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public UserRightArchivesTypeDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserRightArchivesTypeDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯָ���û��ĵ���������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_By_UserID = "SELECT A.ID,UserID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives,TopFlag,Name,Code,ParentID,TopTypeID,FullCode,FullName,EndFlag,ContentIDFormatLength,SubContentIDFormatLength,ArchivesIDExpressionPrefix,ArchivesIDExpression,SubArchivesIDExpression,AttachedFileSavePath,RenewPeriod,Remark FROM UserRight_ArchivesType A,DD_ArchivesType B WHERE UserID=? AND A.ArchivesTypeID=B.ID ORDER BY B.ID";
	
	/**
	 * �����û���������Ȩ����Ȩ��Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO UserRight_ArchivesType(UserID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives) VALUES(:UserID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives)";
	
	/**
	 * ɾ���û���������Ȩ����Ȩ��Ϣ��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM UserRight_ArchivesType WHERE ID = ?";
	
	/**
	 * �����û����ɾ��SQL���
	 */
	private final String SQL_DELETE_By_UserID = "DELETE FROM UserRight_ArchivesType WHERE UserID = ?";
	
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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao#save(com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userRightArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (userRightArchivesType.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ���������û���ŷǷ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (userRightArchivesType.getArchivesTypeID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������ĵ��������ŷǷ�Ϊ��!");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//NSERT INTO UserRight_ArchivesType(UserID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives) VALUES(:UserID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userRightArchivesType.getUserID(), Types.INTEGER);
				sqlParameterSource.addValue("ArchivesTypeID", userRightArchivesType.getArchivesTypeID(), Types.INTEGER);
				sqlParameterSource.addValue("RightFlag_ArchivesInfo", userRightArchivesType.getRightFlag_ArchivesInfo(),Types.BIT);
				sqlParameterSource.addValue("RightFlag_AttachedFile", userRightArchivesType.getRightFlag_AttachedFile(),Types.BIT);
				sqlParameterSource.addValue("RightFlag_PaperArchives", userRightArchivesType.getRightFlag_PaperArchives(),Types.BIT);
				
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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao#delete(com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userRightArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (userRightArchivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������ı�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,userRightArchivesType.getID());

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
	
	@Override
	public boolean deleteByUserID(int pUserID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (pUserID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ����,�û���ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserID,pUserID);

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
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao#update(com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(UserRightArchivesType userRightArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRightArchivesTypeDao#findByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, LinkedHashMap<Integer, ArchivesType> userRightArchivesTypes, ErrInfo pErrInfo)
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
				List<UserRightArchivesType> pUserRightArchivesTypes=jdbcTemplate.query(SQL_SELECT_By_UserID,new UserRightArchivesTypeMapper(),pUserID);

				//���ز�ѯ���
				if (pUserRightArchivesTypes.size() > 0)
				{
					for (UserRightArchivesType item : pUserRightArchivesTypes)
					{   ArchivesType archivesType = item;
					    archivesType.setID(item.getArchivesTypeID());
						userRightArchivesTypes.put(item.getArchivesTypeID(), archivesType);
					}
				}

				//���پֲ�����
				pUserRightArchivesTypes=null;
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
