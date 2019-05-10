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
 * ���������ֵ���DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class ArchivesTypeDaoImpl extends JdbcDaoSupport implements IArchivesTypeDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ����	�����������DAOʵ������
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
	 * ���캯��
	 */
	public ArchivesTypeDaoImpl()
	{

	}
	
	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesTypeDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	//SQL���
	
	/**
	 * ���Ҷ��㵵�����ࣨһ����Ŀ����SQL���
	 */
	private final String SQL_SELECT_FORLEVEL1="SELECT * FROM DD_ArchivesType WHERE TopFlag=1 ORDER BY FullCode";
	/**
	 * ������һ�����������SQL���
	 */
	private final String SQL_SELECT_FORCHILD = "SELECT * FROM DD_ArchivesType WHERE ParentID=? ORDER BY FullCode";
	

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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			//ִ��SQL���
			if (pFlag)
			{
				pErrPos=1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesType> pArchivesTypes= jdbcTemplate.query(SQL_SELECT_FORLEVEL1, new ArchivesTypeMapper());
	
				//���ز�ѯ���
				if (pArchivesTypes.size()>0)
				{
					for (ArchivesType item : pArchivesTypes)
					{
						archivesTypes.put(item.getID(), item);
					}
				}
				
				//���پֲ�����
				pArchivesTypes=null;
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//ִ��SQL���
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesType> pArchivesTypes= jdbcTemplate.query(SQL_SELECT_FORCHILD, new ArchivesTypeMapper(), archivesTypeID);
	
				//���ز�ѯ���
				if (pArchivesTypes.size()>0)
				{
					for (ArchivesType item : pArchivesTypes)
					{
						//��Ϊ�¼���������������з���
						childArchivesTypes.put(item.getID(), item);
						
						pErrPos=2;
						//�����ݹ���Ҹ��¼����������һ���������ֱ࣬��û���¼�����Ϊֹ
						LinkedHashMap<Integer, ArchivesType> pChildren=new LinkedHashMap<Integer, ArchivesType>();
						if (findForChild(item.getID(), pChildren, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"���� "+ item.getFullCode() +" ���¼�����ʧ��: ");
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
				
				//���پֲ�����
				pArchivesTypes=null;
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
