/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.LinkedHashMap;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ���������Ϣ��DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class OfficialArchivesTypeDaoImpl extends JdbcDaoSupport implements IOfficialArchivesTypeDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class OfficialArchivesTypeMapper implements RowMapper<OfficialArchivesType>
	{
		
		@Override
		public OfficialArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String code = rs.getString("Code");
			String attachedFileSavePath = rs.getString("AttachedFileSavePath");
			String remark = rs.getString("Remark");
			
			return new OfficialArchivesType(iD,name,code,attachedFileSavePath,remark);
		}
	}
	
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesTypeDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public OfficialArchivesTypeDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ���й��ĵ���������Ϣ��SQL���
	 */
	private final String SQL_SELECT = "SELECT * FROM DD_OfficialArchivesType ORDER BY ID ASC";
	
	/**
	 * ���ݹ��ĵ��������ŵĲ�ѯ����ϸ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_ByID = "SELECT * FROM DD_OfficialArchivesType WHERE ID=?";
	
	/**
	 * ����һ�����ĵ��������¼��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO DD_OfficialArchivesType(Name,Code,AttachedFileSavePath,Remark) VALUES(:Name,:Code,:AttachedFileSavePath,:Remark) ";
	
	

	
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
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#save(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
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
			
			//��鹫�ĵ��������Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesType.getName()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ������ƷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				//SQL_INSERT = "INSERT INTO DD_OfficialArchivesType(Name,Code,AttachedFileSavePath,Remark) 
				//VALUES(:Name,:Code,:AttachedFileSavePath,:Remark) ";
				
				pErrPos = 1;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT);
				//����SQLִ�гɹ��󷵻ص�����
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				//���������ֶ���
				pscFactory.setGeneratedKeysColumnNames(new String[] { "ID" });
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Name", officialArchivesType.getName(), Types.VARCHAR);
				paramSource.addValue("Code", officialArchivesType.getCode(), Types.VARCHAR);
				paramSource.addValue("AttachedFileSavePath", officialArchivesType.getAttachedFileSavePath(), Types.VARCHAR);
				paramSource.addValue("Remark", officialArchivesType.getRemark(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);
				
				//�����Զ����ɵ��ڲ����
				officialArchivesType.setID(keyHolder.getKey().intValue());
				
				//���پֲ�����
				namedParameterJdbcTemplate = null;
				paramSource=null;
				pscFactory = null;
				keyHolder = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#delete(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#update(com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#findAll(java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(LinkedHashMap<Integer,OfficialArchivesType> officialArchivesTypes, ErrInfo pErrInfo)
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<OfficialArchivesType> pOfficialArchivesTypes=jdbcTemplate.query(SQL_SELECT,new OfficialArchivesTypeMapper());

				//���ز�ѯ���
				if (pOfficialArchivesTypes.size() > 0)
				{
					for (OfficialArchivesType item : pOfficialArchivesTypes)
					{
						officialArchivesTypes.put(item.getID(), item);
//System.out.println("OfficialArchivesType.ID:"+item.getID());
					}
				}
//System.out.println("officialArchivesTypes.size()="+officialArchivesTypes.size());
				//���پֲ�����
				pOfficialArchivesTypes=null;
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
	 * @see com.orifound.aiim.dal.dao.IOfficialArchivesTypeDao#findByID(int, com.orifound.aiim.entity.OfficialArchivesType, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pOfficialArchivesTypeID, OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
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
				//SQL_SELECT_ByID = "SELECT * FROM DD_OfficialArchivesType WHERE ID=?";
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<OfficialArchivesType> pOfficialArchivesTypes=jdbcTemplate.query(SQL_SELECT_ByID,new OfficialArchivesTypeMapper(),pOfficialArchivesTypeID);

				//���ز�ѯ���
				if (pOfficialArchivesTypes.size() > 0)
				{
					officialArchivesType.cloneFrom(pOfficialArchivesTypes.get(0));
				}
				
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
	 * @see org.springframework.dao.support.DaoSupport#checkDaoConfig()
	 */
	@Override
	protected void checkDaoConfig() throws IllegalArgumentException
	{
		// TODO Auto-generated method stub

	}

}
