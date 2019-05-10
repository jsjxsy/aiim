/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesFondsDao;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ����ȫ���ֵ���DAOʵ���ࣨSQL Serverƽ̨��
 * 
 */
public class ArchivesFondsDaoImpl extends JdbcDaoSupport implements IArchivesFondsDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesFondsMapper implements RowMapper<ArchivesFonds>
	{
		@Override
		public ArchivesFonds mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String code = rs.getString("Code");
			String name = rs.getString("Name");
			boolean defaultFlag = rs.getBoolean("DefaultFlag");
			String remark = rs.getString("Remark");
			
			return new ArchivesFonds(iD,code,name,defaultFlag,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public ArchivesFondsDaoImpl()
	{
		
	}
	
	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesFondsDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	
	//SQL���
	
	/**
	 * ��������ȫ�ڼ�¼
	 */
	private final String SQL_INSERT="INSERT INTO DD_ArchivesFonds(Code,Name,DefaultFlag,Remark) VALUES(:Code,:Name,:DefaultFlag,:Remark)";
	
	/**
	 * ɾ��ָ���ĵ���ȫ�ڼ�¼
	 */
	private final String SQL_DELETE="DELETE FROM DD_ArchivesFonds WHERE ID=?";
	
	/**
	 * ����ָ���ĵ���ȫ�ڼ�¼
	 */
	private final String SQL_UPDATE="UPDATE DD_ArchivesFonds SET Name=:Name,Remark=:Remark WHERE ID=:ID";
	
	/**
	 * ��ѯ���е���ȫ�ڼ�¼
	 */
	private final String SQL_SELECT_ALL="SELECT * FROM DD_ArchivesFonds ORDER BY ID";
	
	/**
	 * ��ѯָ���ĵ���ȫ�ڼ�¼
	 */
	private final String SQL_SELECT_BYID="SELECT * FROM DD_ArchivesFonds WHERE ID=?";
	
	
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
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesFondsDao#save(com.orifound.aiim.entity
	 * .ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			// ���ȫ�ڱ���Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (pArchivesFonds.getCode() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�ںŷǷ�Ϊ�ա�");
				}
				else
				{
					if (pArchivesFonds.getCode().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ȫ�ںŷǷ�Ϊ�ա�");
					}
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos =2;
				if (pArchivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
				}
				else
				{
					if (pArchivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
					}
				}
			}

			// ���첢ִ�в��봦��
			if (pFlag)
			{
				pErrPos = 3;
				//"INSERT INTO DD_ArchivesFonds(Code,Name,DefaultFlag,Remark) VALUES(:Code,:Name,:DefaultFlag,:Remark)";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Code", pArchivesFonds.getCode(),Types.VARCHAR);
				paramSource.addValue("Name", pArchivesFonds.getName(),Types.VARCHAR);
				paramSource.addValue("DefaultFlag", pArchivesFonds.getDefaultFlag(),Types.BIT);
				paramSource.addValue("Remark", pArchivesFonds.getRemark(), Types.VARCHAR);
				
				pErrPos = 4;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesFondsDao#delete(com.orifound.aiim.
	 * entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			//���ȫ�ڱ���Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (pArchivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}

			//����SQLִ��֮
			if (pFlag)
			{
				pErrPos = 2;
				//SQL_DELETE="DELETE FROM DD_ArchivesFonds WHERE ID=?";
				
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE, pArchivesFonds.getID());
				
				//���پֲ�����
				jdbcTemplate=null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesFondsDao#update(com.orifound.aiim.
	 * entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			// ���ȫ�ڱ���Ƿ��и�ֵ
			{
				pErrPos = 1;
				if (pArchivesFonds.getID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos =2;
				if (pArchivesFonds.getName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
				}
				else
				{
					if (pArchivesFonds.getName().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ȫ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//����SQLִ��֮
			if (pFlag)
			{
				pErrPos = 3;
				//SQL_UPDATE="UPDATE DD_ArchivesFonds SET Name=:Name,Remark=:Remark WHERE ID=:ID";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Name", pArchivesFonds.getName());
				paramSource.addValue("Remark", pArchivesFonds.getRemark());
				paramSource.addValue("ID", pArchivesFonds.getID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				
				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate=null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IArchivesFondsDao#findAll(java.util.List,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<ArchivesFonds> pArchivesFondss, ErrInfo pErrInfo)
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
			
			//����SQLִ��֮
			//DoSomething
			if (pFlag)
			{
				pErrPos = 1;
				//SQL_SELECT_ALL="SELECT * FROM DD_ArchivesFonds ORDER BY ID";
	
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				List<ArchivesFonds> archivesFonds= jdbcTemplate.query(SQL_SELECT_ALL, new ArchivesFondsMapper());
				
				//���ؽ��
				pArchivesFondss.addAll(archivesFonds);
				
				//���پֲ�����
				archivesFonds=null;
				jdbcTemplate=null;
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
	 * @see com.orifound.aiim.dal.dao.IArchivesFondsDao#findByID(java.lang.String, com.orifound.aiim.entity.ArchivesFonds, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, ArchivesFonds pArchivesFonds, ErrInfo pErrInfo)
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
			
			//���ID�Ƿ�ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (pID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}

			//����SQLִ��֮
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				List<ArchivesFonds> archivesFondss=jdbcTemplate.query(SQL_SELECT_BYID, new ArchivesFondsMapper(), pID);
				if (archivesFondss.size()>0)
				{
					//���ز�ѯ���
					pArchivesFonds.cloneFrom(archivesFondss.get(0));
				}
				
				//���پֲ�����
				archivesFondss=null;
				jdbcTemplate=null;
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

}
