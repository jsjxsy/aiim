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
 * ԭ����������ͨ����Ϣ���DAOʵ����
 *
 */
public class AttachedFileUseRequestPassInfoDaoImpl extends JdbcDaoSupport implements IAttachedFileUseRequestPassInfoDao
{
	/**
	 * ���캯��
	 */
	public AttachedFileUseRequestPassInfoDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ����ָ�����û�����ԭ������ͨ����Ϣ���ڵ���Ч������SQL���
	 */
	private final String SQL_SELECT_CountByRequestPassInfo = "SELECT COUNT(ID) FROM AttachedFileUseRequestPassInfo WHERE ArchivesTypeID=:ArchivesTypeID AND NBXH=:NBXH AND UserID=:UserID AND ExpirationDate>GETDATE()";
	
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
				if (pErrInfo.getException() != null)
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//ִ��SQL���
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
				
				//���ز�ѯ���
				pCount.setValue(count);

				//���پֲ�����
				paramSource=null;
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
				if (pErrInfo.getException() != null)
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
	
	/**
	 * ���ԭ����������ͨ����Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO AttachedFileUseRequestPassInfo(ArchivesTypeID,NBXH,ArchivesID,Title,UserID,ExpirationDate) VALUES(:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:UserID,:ExpirationDate) ";

	@Override
	public boolean add(AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo, ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
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
				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
