/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * �������̹�����Ϣ��DAOʵ����
 *
 */
public class ArchivesInfoWorkProcedureDaoImpl extends JdbcDaoSupport implements IArchivesInfoWorkProcedureDao
{
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesInfoWorkProcedureMapper implements RowMapper<ArchivesInfoWorkProcedure>
	{
		
		@Override
		public ArchivesInfoWorkProcedure mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int nBXH = rs.getInt("NBXH");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int userID = rs.getInt("UserID");
			int workFlowStatus = rs.getInt("WorkFlowStatus");
			Date operateTime = rs.getTimestamp("OperateTime");
			String sendBackReason = rs.getString("SendBackReason");
			
			return new ArchivesInfoWorkProcedure(iD,nBXH,archivesTypeID,userID,workFlowStatus,operateTime,sendBackReason);
		}
	}
	
	/**
	 * ���캯��
	 */
	public ArchivesInfoWorkProcedureDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesInfoWorkProcedureDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * �����µĹ���������Ϣ��SQL���<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵���̱���
	 */
	private final String SQL_INSERT = "INSERT INTO %1$s(NBXH,ArchivesTypeID,UserID,WorkFlowStatus,SendBackReason) VALUES(:NBXH,:ArchivesTypeID,:UserID,:WorkFlowStatus,:SendBackReason)";

	/**
	 * ����ָ���ڲ���ŵ����й���������Ϣ��SQL���<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵���̱���
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?";
	
	/**
	 * ɾ��ָ���ڲ���ŵĹ���������Ϣ��SQL���<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵���̱���
	 */
	private final String SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=?";
	
	/**
	 * ɾ��ָ�����������ڲ���ż�������ļ��Ĺ���������Ϣ��SQL���<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵���̱���
	 * %2$s�����嵵�������Ӧ�Ĺ鵵��������
	 */
	private final String SQL_DELETE_ByNBXH_ForParent = "DELETE FROM %1$s WHERE NBXH IN (SELECT NBXH FROM %2$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH)";
	
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
	
	/**
	 * ���ָ���ĵ���������Ϣ���䵵���鵵��������Ϣ�Ƿ���ȷ��ֵ��
	 * 
	 * @param archivesType
	 *            ����������Ϣ
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkTableNameForArchivesWorking(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������Ϣ��Ƿ�Ϊ��");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.�����鵵������) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ����鵵��������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ����鵵��������Ϣ�Ƿ�Ϊ�ա�");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ����鵵��������Ϣ�Ƿ�Ϊ�ա�");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// �쳣����
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

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ���ָ���ĵ���������Ϣ���䵵���鵵���̱���Ϣ�Ƿ���ȷ��ֵ��
	 * @param archivesType ����������Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkTableNameForWorkProcedure(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���������Ϣ��Ƿ�Ϊ��");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.�����鵵���̱�) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ����鵵���̱���Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ����鵵���̱���Ϣ�Ƿ�Ϊ�ա�");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ����鵵���̱���Ϣ�Ƿ�Ϊ�ա�");
						}
					}
				}
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
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao#save(com.orifound.aiim.entity.ArchivesType,com.orifound.aiim.entity.ArchivesInfoWorkProcedure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//�����鵵���̱���
		String localSql="";//�����ύ��SQL���
		
		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵������������Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("��������������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ���ڲ���ŷǷ�Ϊ�ա�");
					}
				}
			}
			
			//��鵵�������Ӧ�Ĺ������̱������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//���浱ǰ����ĵ����鵵���̱���
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
//				SQL_INSERT = "INSERT INTO %1$s(NBXH,ArchivesTypeID,UserID,WorkFlowStatus,SendBackReason) 
//				VALUES(:NBXH,:ArchivesTypeID,:UserID,:WorkFlowStatus,:SendBackReason)";
				
				pErrPos = 2;
				//�������鵵���̱�����̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_INSERT, archivesInfoWorkProcedureTableName);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", archivesInfoWorkProcedure.getNBXH(), Types.INTEGER);
				paramSource.addValue("ArchivesTypeID", archivesInfoWorkProcedure.getArchivesTypeID(), Types.INTEGER);
				paramSource.addValue("UserID", archivesInfoWorkProcedure.getUserID(), Types.INTEGER);
				paramSource.addValue("WorkFlowStatus", archivesInfoWorkProcedure.getWorkFlowStatus().getEnumValue(), Types.INTEGER);
				paramSource.addValue("SendBackReason", archivesInfoWorkProcedure.getSendBackReason(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(localSql, paramSource);

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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao#findByNBXH(com.orifound.aiim.entity.ArchivesType,int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//�����鵵���̱���
		String localSql="";//�����ύ��SQL���
		
		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//����ڲ�����Ƿ�Ϊ0
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
			}
			
			//��鵵�������Ӧ�Ĺ������̱������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//���浱ǰ����ĵ����鵵���̱���
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
//				SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?"
				
				pErrPos = 2;
				//�������鵵���̱�����̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_ByNBXH, archivesInfoWorkProcedureTableName);
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfoWorkProcedure> pArchivesInfoWorkProcedures=jdbcTemplate.query(localSql,new ArchivesInfoWorkProcedureMapper(),pNBXH);

				//���ز�ѯ���
				if (pArchivesInfoWorkProcedures.size() > 0)
				{
					archivesInfoWorkProcedures.addAll(pArchivesInfoWorkProcedures);
				}

				//���پֲ�����
				pArchivesInfoWorkProcedures=null;
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

	@Override
	public boolean deleteForSingleArchives(ArchivesType archivesType, ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//�����鵵���̱���
		String localSql="";//�����ύ��SQL���
		
		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵������������Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("��������������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ���ڲ���ŷǷ�Ϊ�ա�");
					}
				}
			}
			
			//��鵵�������Ӧ�Ĺ������̱������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//���浱ǰ����ĵ����鵵���̱���
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
//				SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=?";
				
				pErrPos = 2;
				//�������鵵���̱�����̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_DELETE_ByNBXH, archivesInfoWorkProcedureTableName);
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(localSql,archivesInfoWorkProcedure.getNBXH());

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

	@Override
	public boolean deleteForParentArchives(ArchivesType archivesType, ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoWorkProcedureTableName="";//�����鵵���̱���
		String archivesInfoWorkingTableName="";//�����鵵��������
		String localSql="";//�����ύ��SQL���
		
		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵������������Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("��������������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��������������Ϣ���ڲ���ŷǷ�Ϊ�ա�");
					}
				}
			}
			
			//��鵵�������Ӧ�Ĺ������̱������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableNameForWorkProcedure(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//���浱ǰ����ĵ����鵵���̱���
					archivesInfoWorkProcedureTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
				}
			}
			
			
			//��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableNameForArchivesWorking(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else 
				{
					//���浱ǰ����ĵ����鵵��������
					archivesInfoWorkingTableName=archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
//				SQL_DELETE_ByNBXH_ForParent = "DELETE FROM %1$s WHERE NBXH IN (SELECT NBXH FROM %2$s 
//				WHERE NBXH=:NBXH OR ParentNBXH=:NBXH)";
				
				pErrPos = 2;
				//�������鵵���̱�����̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_DELETE_ByNBXH_ForParent, archivesInfoWorkProcedureTableName,archivesInfoWorkingTableName);
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", archivesInfoWorkProcedure.getNBXH(), Types.VARCHAR);

				namedParameterJdbcTemplate.update(localSql, paramSource);

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
	 * ��⵵���γɲ��š�ҵ��ָ���ҵ���Ա�ض�ʱ�������¼����������<br>
	 * @param recordCondition map���ϱ���:��Ա��ʵ����-��¼���� ��ֵ��
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��	
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean recordCondition(Map<String, Integer> recordCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			if (pFlag) {
				pErrPos = 1;
				if(getArchivesInfoWorkProcedureCount(recordCondition, userIds, new Integer[]{10}, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "getArchivesInfoWorkProcedureCount��ȡ�����鵵���̼�¼��ִ��ʧ�ܡ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	
	/**
	 * Dao�ӿڶ��壺���ҵ��ָ���ҵ���Ա�ض�ʱ�������ˡ�����˵���������<br>
	 * @param recordAudit ��װ��Map<String, Integer[]>������Ա��˵�������,String������Ա��ʵ����,Integer[0]�������������Integer[1]�����
	 * @param userIds ��Աid����
	 * @param beginTime ��⿪ʼʱ��
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	 public boolean recordAuditCondition(Map<String, Integer[]> recordAudit, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
		 boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//��Ա���յ�����¼������
			Map<String, Integer> totalCount = new LinkedHashMap<String, Integer>();
			//��Ա�����ͨ��������¼��������
			Map<String, Integer> passCount = new LinkedHashMap<String, Integer>();
			
			//��ѯ��Ա���յ���������
			if (pFlag) {
				pErrPos = 1;
				if(getArchivesInfoWorkProcedureCount(totalCount, userIds, new Integer[]{20}, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��Ա���յ�����¼��������� ʧ�ܣ�");
				}
			}
			
			//��ѯ��Ա�������¼��˵�������
			if (pFlag) {
				pErrPos = 2;
				if(getArchivesInfoWorkProcedureCount(passCount, userIds, new Integer[]{40,30}, beginTime, endTime, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��Ա�������¼������� ʧ�ܣ�");
				}
			}
			
			//��װ���ɴ���ˡ��������
			if (pFlag) {
				pErrPos = 3;
				if(totalCount.keySet().size() >= 1 || passCount.keySet().size()>=1) {
					//ʹ��Set���ϻ�ȡ����ҵ��ָ������Ա
					Set<String> nameSet = new HashSet<String>();
					nameSet.addAll(totalCount.keySet());
					nameSet.addAll(passCount.keySet());
					
					//�����¼����ˡ����������
					Integer[] nums = null;
					for(String name : nameSet) {
						//Ĭ������ֵΪ0
						nums = new Integer[]{0,0};
						
						//�ж��Ƿ�����˵�����
						if(totalCount.get(name) != null) {
							
							//�ж��Ƿ���δ��˵�����
							if(passCount.get(name) != null) {
								//��������˵�����
								nums[0] = passCount.get(name);
							} 
							//���ô���˵�����
							nums[1] = totalCount.get(name)- nums[0];
						}
						//��װ��Map<String, Integer[]>������Ա��˵������� 
						recordAudit.put(name, nums);
					}
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	
	/**
	 * ��ȡ�����鵵���̱��¼����
	 * @param recordCondition map���ϱ���:��Ա��ʵ����-��¼���� ��ֵ��
	 * @param userIds ��Աid����
	 * @param WorkFlowStatus �������״̬
	 * @param beginTime ��⿪ʼʱ��	
	 * @param endTime	������ʱ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getArchivesInfoWorkProcedureCount(Map<String, Integer> recordCondition, List<Integer> userIds, Integer[] WorkFlowStatus, 
			String beginTime, String endTime, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//�����¼���map�����Ƿ�Ϊ��
			if (pFlag) {
				if(recordCondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������:��¼���map���ϷǷ�Ϊ�ա�");
				}
			}
			
			//���ҵ��ָ������Աid�����Ƿ�Ϊ��
			if (pFlag) {
				if(userIds==null || userIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������:��Աid���ϷǷ�Ϊ�ա�");
				}
			}
			
			//��ȡ�����鵵���̱�������
			Set<String> tableNames = null;
			if (pFlag) {
				tableNames = new HashSet<String>();
				if(getArchivesInfoTables(tableNames, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ѭ��������ȡ �����鵵���̱������ϷǷ�Ϊ�ա�");
				}
				
				//�жϻ�ȡ���������Ƿ�Ϊ��
				if (pFlag) {
					if(tableNames.size() <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("�����鵵���̱��������в����ڱ�");
					}
				}
			}
			
			StringBuffer nativeSQL = null;
			//ƴ��SQL���
			/**
			 * ����SQL���
			 * select u.RealName,t1.num from(
				select t.UserID,count(t.NBXH),MAX(t.OperateTime) OperateTime num from
				(
				select wp.NBXH,u.UserID,wp.OperateTime from ArchivesInfoWorkProcedure_JX wp,UserInfo u 
					where wp.UserID=u.UserID and wp.WorkFlowStatus=?
				union all
				select wp.NBXH,u.UserID from ArchivesInfoWorkProcedure_JX wp,UserInfo u 
					where wp.UserID=u.UserID and wp.WorkFlowStatus=?
				) t
				group by t.UserID
				) as t1 right on UserInfo u where u.UserID=t1.UserID AND u.UserID in(:userIds)
				AND t1.OperateTime>=:beginTime AND t1.OperateTime<=:endTime
			 */
			if (pFlag) {
				pErrPos = 2;
				String orSQL = " OR ";
				nativeSQL = new StringBuffer("SELECT u.RealName,t1.num FROM ( SELECT t.UserID,count(DISTINCT t.NBXH) num,MAX(t.OperateTime) OperateTime FROM ( ");
				String union = " UNION ALL ";
				for(String tableName : tableNames) {
					nativeSQL.append("SELECT wp.NBXH,u.UserID,wp.OperateTime FROM ");
					nativeSQL.append(tableName).append(" wp,UserInfo u ");
					nativeSQL.append("WHERE wp.UserID=u.UserID ");
					nativeSQL.append(" AND (");
					for(Integer stau : WorkFlowStatus) {
						nativeSQL.append(" wp.WorkFlowStatus=").append(stau).append(orSQL);
					}
					//ȡ������OR
					nativeSQL.delete(nativeSQL.length()-orSQL.length(), nativeSQL.length());
					nativeSQL.append(") ").append(union);
				}
				//ȥ������һ��UNION ALL
				nativeSQL.delete(nativeSQL.length()-union.length(), nativeSQL.length());
				nativeSQL.append(") t GROUP BY t.UserID ) AS t1,UserInfo u WHERE u.UserID in(:userIds) AND u.UserID=t1.UserID ");
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("userIds", userIds);
				
				//�жϿ�ʼʱ���Ƿ�Ϊ��
				if(StringTool.checkNull(beginTime)) {
					nativeSQL.append(" AND t1.OperateTime>=:beginTime");
					paramSource.addValue("beginTime", TimeTool.getMinTime(beginTime), Types.TIMESTAMP);
				}
				//�жϽ���ʱ���Ƿ�Ϊ��
				if(StringTool.checkNull(endTime)) {
					nativeSQL.append(" AND t1.OperateTime<=:endTime");
					paramSource.addValue("endTime",TimeTool.getMaxTime(endTime) ,Types.TIMESTAMP);
				}
				
				pErrPos = 3;
				List<Map<String, Object>> results = namedParameterJdbcTemplate.queryForList(nativeSQL.toString(), paramSource);
				
//				for(Map<String, Object> result : results) {
//					for(String key : result.keySet()) {
//						System.out.println(key+" "+result.get(key));
//					}
//				}
				
				System.out.println("�����鵵���̱��¼����ͳ��="+nativeSQL.toString());
				//�ж��Ƿ���ͳ�ƽ��
				if(results.size() <= 0) {
					//��ͳ�ƽ������Ĭ��ֵ
					paramSource = new MapSqlParameterSource();
					paramSource.addValue("userIds", userIds);
					nativeSQL = new StringBuffer("SELECT u.RealName,0 num FROM UserInfo u WHERE u.UserID in(:userIds)");	
					results = namedParameterJdbcTemplate.queryForList(nativeSQL.toString(), paramSource);
				} 
				
				for(Map<String, Object> result : results) {
					recordCondition.put(result.get("RealName").toString(), Integer.valueOf(result.get("num").toString()));
				}
				
				//���پֲ�����
				nativeSQL = null;
				results = null;
				namedParameterJdbcTemplate = null;
			}
			
		} catch (NumberFormatException e) {
			// ��������ת���쳣����
			pFlag = false;
			pErrInfo.getContent().append("��¼����Ľ������¼����Objectת��Integer�쳣��"+e.toString());
			pErrInfo.setException(e);
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
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
	
	
	/**
	 * ѭ��������ȡ �����鵵���̱�������
	 * @param tableNames 	�����鵵���̱�������	
	 * @param pErrInfo  	����ʧ�ܵĴ���ԭ������	
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getArchivesInfoTables(Set<String> tableNames, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//���ƽ��ṹ�ĵ������༯���Ƿ�Ϊ��
			Map<Integer, ArchivesType> planeArchivesTypes = null;
			if (pFlag) {
				planeArchivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();
				if(planeArchivesTypes == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡϵͳ��ʼ�����������µ�->ƽ��ṹ�ĵ������༯�� ����Ϊ�ա�");
				}
			}
			
			//����Ƿ���ڵ�������
			Collection<ArchivesType> archivesTypes = null;
			if(pFlag) {
				archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes().values();
				if(archivesTypes == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡϵͳ��ʼ�����������µ�->ƽ��ṹ�ĵ������༯�ϵ�->ֵ���󼯺ϵ������༯��Ϊ�ա�");
				}
			}
			
			if(pFlag) {
				EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> enumMap = null;
				ArchivesInfoTable archivesInfoTable = null;
				for(ArchivesType archivesType : archivesTypes) {
					enumMap = archivesType.getArchivesInfoTables();
					//�жϵ�������������Ϣ����	�Ƿ�Ϊ��
					if(enumMap == null) {
						continue;
					}
					
					//�жϵ�����Ϣ��ر���ֵ���ʵ����	�Ƿ�Ϊ��
					archivesInfoTable = enumMap.get(EnumArchivesInfoTableType.�����鵵���̱�);
					if(archivesInfoTable == null) {
						continue;
					}
					tableNames.add(archivesInfoTable.getTableName());
				}
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
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

	@Override
	public boolean findInStorageCondition(Map<String, Integer> inStorageCondition, List<Integer> userIds, String beginTime, String endTime, ErrInfo pErrInfo) {
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
				//DoSomething
				if (pFlag) {
					getArchivesInfoWorkProcedureCount(inStorageCondition, userIds, new Integer[]{120}, beginTime, endTime, pErrInfo);
				}
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
}
