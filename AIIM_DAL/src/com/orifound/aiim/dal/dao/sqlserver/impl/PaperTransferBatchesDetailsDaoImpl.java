package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IPaperTransferBatchesDetailsDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * �����ƽ�������ϸ��DAOʵ����
 * @author Administrator
 *
 */
public class PaperTransferBatchesDetailsDaoImpl extends JdbcDaoSupport implements  IPaperTransferBatchesDetailsDao{

	/**
	 * ����ƽ�������Ϣ��ϸ��SQL
	 * %1$s : �������κ�
	 * %2$s : working����
	 */
	private static final String SQL_INSERT_PaperTransferBatchesDetails = "insert into PaperTransferBatchesDetails  (TransferBatNo,ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear)"+   
         "select '%1$s',ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear from %2$s where NBXH IN (:NBXHS)";

	/**
	 * �����κźͷ������������ϸ��Ϣ��SQL
	 */
	private static final String SQL_SELECT_findByBatNoAndArchivesType = "SELECT * FROM PaperTransferBatchesDetails WHERE (TransferBatNo=:TransferBatNo OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND ArchivesTypeID=:ArchivesTypeID AND ReceiveCheckResult IN (:ReceiveCheckResults) ORDER BY ReceiveCheckResult,TransferTime";
	
	/**
	 * ɾ��������ϸ��Ϣ��SQL
	 */
	private static final String SQL_DELETE_PaperTransferBatchesDetails = "DELETE FROM PaperTransferBatchesDetails WHERE TransferBatNo=? AND ArchivesTypeID=? AND NBXH=?";
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class PaperTransferBatchesDetailMapper implements RowMapper<PaperTransferBatchesDetail>
	{
		
		@Override
		public PaperTransferBatchesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String transferBatNo = rs.getString("TransferBatNo");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			boolean parentFlag = rs.getBoolean("ParentFlag");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			int subContentCount = rs.getInt("SubContentCount");
			int pageSum = rs.getInt("PageSum");
			int retentionPeriodID = rs.getInt("RetentionPeriodID");
			int secrecyID = rs.getInt("SecrecyID");
			int formationYear = rs.getInt("FormationYear");
			Timestamp transferTime = rs.getTimestamp("TransferTime");
			int receiveCheckResult = rs.getInt("ReceiveCheckResult");
			
			return new PaperTransferBatchesDetail(iD,transferBatNo,archivesTypeID,nBXH,parentFlag,archivesID,title,subContentCount,pageSum,retentionPeriodID,secrecyID,formationYear,transferTime,EnumCheckResult.getEnumElement(receiveCheckResult));
		}
	}
	
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//�������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
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
	
	/**
	 * ���ָ���ĵ���������Ϣ���䵵���鵵��������Ϣ�Ƿ���ȷ��ֵ��
	 * 
	 * @param archivesType
	 *            ����������Ϣ
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkTableName(ArchivesType archivesType, ErrInfo pErrInfo)
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

	
	@Override
	public boolean addPaperTransferBatchesDetails(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}
			
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
				//private static final String SQL_INSERT_PaperTransferBatchesDetails = "insert into PaperTransferBatchesDetails  (TransferBatNo,ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear)"+   
		      //   "select '%1$s',ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,SecrecyID,FormationYear from %2$s where NBXH IN (:NBXHS)";
				String localSql = String.format(SQL_INSERT_PaperTransferBatchesDetails, paperTransferBatch.getBatNo(),archivesInfoWorkingTableName);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				List<Integer> NBXHS = new ArrayList<Integer>();
				for (ArchivesInfo archivesInfo : archivesInfos) {
					NBXHS.add(archivesInfo.getNBXH());
				}
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXHS", NBXHS);
				
				namedParameterJdbcTemplate.update(localSql, parameterSource);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean update(PaperTransferBatchesDetail pPaperTransferBatchesDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String paperTransferBatNo, int archivesTypeID,int nBXH, ErrInfo pErrInfo) {
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
				 //String SQL_DELETE_PaperTransferBatchesDetails = "DELETE FROM PaperTransferBatchesDetails WHERE TransferBatNo=?,ArchivesTypeID=?, NBXH=?";
				getJdbcTemplate().update(SQL_DELETE_PaperTransferBatchesDetails,paperTransferBatNo,archivesTypeID, nBXH);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean setFlagForWorkFlow(ArchivesType archivesType, PaperTransferBatch paperTransferBatch, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		String archivesInfoWorkingTableName = "";
		String archivesInfoWorkingProcedureTableName = "";
		try {
			pErrPos = 1;
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣû�г�ʼ��");
				}
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}
			
			archivesInfoWorkingProcedureTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
			String sql1 = "update %1$s  set WorkFlowStatus=? where NBXH in (select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? and p.ArchivesTypeID=?)";
			
			String sql2 = "insert into %1$s (ArchivesTypeID,NBXH,UserID,WorkFlowStatus) " +
					"select T1.ArchivesTypeID,T1.NBXH,T1.UserID1,T1.WorkFlowStatus from %2$s T1 where NBXH in (" +
					"select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? and p.ArchivesTypeID=?)";
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				getJdbcTemplate().update(String.format(sql1, archivesInfoWorkingTableName),enumWorkFlowStatus.getEnumValue(), paperTransferBatch.getBatNo(),archivesType.getID());
				getJdbcTemplate().update(String.format(sql2, archivesInfoWorkingProcedureTableName,archivesInfoWorkingTableName), paperTransferBatch.getBatNo(),archivesType.getID());
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
	public boolean updateReceiveCheckResult(int archivesTypeID, String batNo,int NBXH, EnumCheckResult enumCheckResult, ErrInfo pErrInfo) {
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
				 String SQL_UPDATE_updateReceiveCheckResult = "UPDATE PaperTransferBatchesDetails SET ReceiveCheckResult=? WHERE (TransferBatNo=? OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?)) AND ArchivesTypeID=? AND NBXH=?";
				getJdbcTemplate().update(SQL_UPDATE_updateReceiveCheckResult,enumCheckResult.getEnumValue(),batNo,batNo,archivesTypeID, NBXH);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findArchivesFondsByBatNoAndArchivesType(String batNo, ArchivesType archivesType, List<String> pArchivesFondIDs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT DISTINCT T1.ArchivesFondsID AS ArchivesFondsID FROM %1$S T1 ," +
						"(SELECT NBXH FROM PaperTransferBatchesDetails T2 WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=?) T3 WHERE T1.NBXH=T3.NBXH";
				sql = String.format(sql, archivesInfoWorkingTableName);
				List<String> archivesFondIDs =  getJdbcTemplate().queryForList(sql, String.class,batNo,archivesType.getID());
				pArchivesFondIDs.addAll(archivesFondIDs);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findNBXHSByArchivesFonds(String paperTransferBatNo, ArchivesType archivesType, String archivesFondID, List<Integer> pNBXHS, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT T1.NBXH AS NBXH FROM %1$s T1 , (SELECT NBXH FROM PaperTransferBatchesDetails T2 " +
						"WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=? AND T2.ReceiveCheckResult=1) T3 WHERE T1.NBXH=T3.NBXH AND T1.ArchivesFondsID=?";
				sql = String.format(sql, archivesInfoWorkingTableName);
				List<Integer> NBXHS =  getJdbcTemplate().queryForList(sql, Integer.class,paperTransferBatNo,archivesType.getID(),archivesFondID);
				pNBXHS.addAll(NBXHS);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateArchivesID(String paperTransferBatNo, ArchivesType archivesType,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//���°�����
				String sql = "UPDATE %1$s  SET ArchivesID=%2$s WHERE NBXH IN (SELECT T1.NBXH AS NBXH FROM %1$s T1 ," +
						"(SELECT NBXH FROM PaperTransferBatchesDetails T2 WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=? AND T2.ReceiveCheckResult=1) T3 " +
						"WHERE T1.NBXH=T3.NBXH)";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getArchivesIDExpression());
				getJdbcTemplate().update(sql,paperTransferBatNo,archivesType.getID());
				
				//���¾����ļ�����
				sql = "UPDATE %1$s  SET ArchivesID=%2$s WHERE ParentNBXH IN (SELECT T1.NBXH AS NBXH FROM %1$s T1 ," +
				"(SELECT NBXH FROM PaperTransferBatchesDetails T2 WHERE T2.TransferBatNo=? AND T2.ArchivesTypeID=? AND T2.ReceiveCheckResult=1) T3 " +
				"WHERE T1.NBXH=T3.NBXH)";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getSubArchivesIDExpression());
				getJdbcTemplate().update(sql,paperTransferBatNo,archivesType.getID());
				
				//����������ϸ��Ϣ������
				sql = "UPDATE PaperTransferBatchesDetails SET ArchivesID=(select T1.ArchivesID from %1$s T1 where PaperTransferBatchesDetails.ArchivesTypeID=T1.ArchivesTypeID and PaperTransferBatchesDetails.NBXH=T1.NBXH) WHERE ReceiveCheckResult=1 AND TransferBatNo=?";
				sql = String.format(sql, archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,paperTransferBatNo);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean addToPaperTransferBatchaddToPaperTransferBatch(final String[] pPaperTransferBatchNos, final PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		//List<String> paperTransferBatchNos = new ArrayList<String>();
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "INSERT INTO PaperTransferSubBatches VALUES(?,?)";
				getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, paperTransferBatch.getBatNo());
						ps.setString(2, pPaperTransferBatchNos[i]);
					}
					@Override
					public int getBatchSize() {
						return pPaperTransferBatchNos.length;
					}
				});
				
				
				/*for (String paperTransferBatchNo : pPaperTransferBatchNos) {
					paperTransferBatchNos.add(paperTransferBatchNo);
				}
				
				sql ="UPDATE PaperTransferBatchesDetails SET ReceiveCheckResult=0 WHERE TransferBatNo IN (:TransferBatNos) AND ReceiveCheckResult<>2";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNos", paperTransferBatchNos);
				
				namedParameterJdbcTemplate.update(sql, parameterSource);*/
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean staArchivesInfosSumByTransferBat(IntegerEx archivesInfosSum, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
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
				String sql = "SELECT COUNT(*) FROM PaperTransferBatchesDetails T1 WHERE T1.TransferBatNo=? OR T1.TransferBatNo IN(" +
						"SELECT T2.SubBatNo FROM PaperTransferSubBatches T2 WHERE T2.BatNo=?) AND (T1.ReceiveCheckResult=1 OR T1.ReceiveCheckResult=3)";
				archivesInfosSum.setValue(getJdbcTemplate().queryForInt(sql,paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo()));
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean staArchivesInfosSumByTransferBatArchvType(final PaperTransferBatch paperTransferBatch, List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT COUNT(*) AS TransferTotal, T2.ArchivesTypeID AS ArchivesTypeID FROM PaperTransferBatchesDetails T2 WHERE (T2.TransferBatNo=? OR T2.TransferBatNo IN (SELECT T1.SubBatNo AS SubBatNo FROM PaperTransferSubBatches T1 WHERE BatNo=?)) AND ReceiveCheckResult<>2 GROUP BY T2.ArchivesTypeID";
				
				//���ݿ��ѯ�ֶ�ӳ����
				RowMapper<PaperTransferBatchesArchvTypeDetail> mapper = new RowMapper<PaperTransferBatchesArchvTypeDetail>() {
					PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
					
					@Override
					public PaperTransferBatchesArchvTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
						paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
						
						paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatch.getBatNo());
						paperTransferBatchesArchvTypeDetail.setArchivesTypeID(rs.getInt("ArchivesTypeID"));
						paperTransferBatchesArchvTypeDetail.setTransferTotal(rs.getInt("TransferTotal"));
						
						return paperTransferBatchesArchvTypeDetail;
					}
				};
				
				//ִ��SQL
				List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = getJdbcTemplate().query(sql, new Object[]{paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo()}, mapper);
				if (paperTransferBatchesArchvTypeDetails.size()>0) {
					pPaperTransferBatchesArchvTypeDetails.addAll(paperTransferBatchesArchvTypeDetails);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean setArchivesFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}

			//ִ��SQL���
			if (pFlag) {
				String sql = "UPDATE %1$s SET WorkFlowStatus=? WHERE NBXH IN (" +
						"SELECT NBXH FROM PaperTransferBatchesDetails T1 WHERE T1.TransferBatNo=? OR T1.TransferBatNo IN(" +
						"SELECT T2.SubBatNo FROM PaperTransferSubBatches T2 WHERE T2.BatNo=?) AND T1.ReceiveCheckResult<>2 AND T1.ReceiveCheckResult<>4)";
				sql = String.format(sql, archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,enumWorkFlowStatus.getEnumValue(), paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo());
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findByBatNoAndArchivesType(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesDetail> paperTransferBatchesDetails, List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> receiveCheckResults = new ArrayList<Integer>();
		
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				for (EnumCheckResult enumCheckResult : enumCheckResults) {
					receiveCheckResults.add(enumCheckResult.getEnumValue());
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID AND ReceiveCheckResult IN (:ReceiveCheckResults)";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNo", paperTransferBatNo);
				parameterSource.addValue("ArchivesTypeID", archivesTypeID);
				parameterSource.addValue("ReceiveCheckResults", receiveCheckResults);
				
				List<PaperTransferBatchesDetail> pPaperTransferBatchesDetails = namedParameterJdbcTemplate.query(SQL_SELECT_findByBatNoAndArchivesType, parameterSource, new PaperTransferBatchesDetailMapper());
				
				paperTransferBatchesDetails.addAll(pPaperTransferBatchesDetails);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean setFlagForWorkFlow(PaperTransferBatch paperTransferBatch, ArchivesType archivesType, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		String archivesInfoWorkingTableName = "";
		String archivesInfoWorkingProcedureTableName = "";
		try {
			pErrPos = 1;
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣû�г�ʼ��");
				}
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}
			
			archivesInfoWorkingProcedureTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
			String sql1 = "update %1$s  set WorkFlowStatus=? where NBXH in (select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? OR p.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?)  and p.ArchivesTypeID=?)";
			
			String sql2 = "insert into %1$s (ArchivesTypeID,NBXH,UserID,WorkFlowStatus) " +
					"select T1.ArchivesTypeID,T1.NBXH,T1.UserID2,T1.WorkFlowStatus from %2$s T1 where NBXH in (" +
					"select p.NBXH from PaperTransferBatchesDetails p where p.TransferBatNo=? OR p.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?) and p.ArchivesTypeID=?)";
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				getJdbcTemplate().update(String.format(sql1, archivesInfoWorkingTableName),enumWorkFlowStatus.getEnumValue(),paperTransferBatch.getBatNo(), paperTransferBatch.getBatNo(),archivesType.getID());
				getJdbcTemplate().update(String.format(sql2, archivesInfoWorkingProcedureTableName,archivesInfoWorkingTableName), paperTransferBatch.getBatNo(),paperTransferBatch.getBatNo(),archivesType.getID());
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
	public boolean findArchivesIDPrefixWhitOutArchivesID(String paperTransferBatNo, ArchivesType archivesType, final Map<Integer, String> archivesIDPrefixs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT NBXH,%2$s AS ArchivesIDPrefix FROM %1$s WHERE NBXH IN " +
						"(SELECT NBXH FROM PaperTransferBatchesDetails WHERE (TransferBatNo=? OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=?)) AND ReceiveCheckResult=1)";
				
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getArchivesIDExpressionPrefix());
				
				//��¼���д�����
				RowCallbackHandler rowCallbackHandler = new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						archivesIDPrefixs.put(rs.getInt("NBXH"), rs.getString("ArchivesIDPrefix"));
					}
				};
				
				//����
				Object [] args = new Object[]{paperTransferBatNo,paperTransferBatNo};
				
				//ִ��sql
				getJdbcTemplate().query(sql, args, rowCallbackHandler);
				
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID,final ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		
		ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql= "SELECT TOP 1 * FROM"+
						  "(SELECT * FROM PaperTransferBatchesDetails P WHERE (P.TransferBatNo=:TransferBatNo OR P.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND P.ArchivesTypeID=:ArchivesTypeID) T1,"+
						  "(SELECT * FROM %1$s A WHERE A.Barcode IS NULL) T2 WHERE T1.NBXH=T2.NBXH ORDER BY T2.ArchivesID";
				sql = String.format(sql, archivesInfoSavedTableName);
				
				//�д�����
				RowCallbackHandler rowCallbackHandler = new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						archivesInfo.setNBXH(rs.getInt("NBXH"));
						archivesInfo.setArchivesID(rs.getString("ArchivesID"));
						archivesInfo.setTitle(rs.getString("Title"));
						archivesInfo.setBarcode(rs.getString("Barcode"));
					}
				};
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNo", batNo);
				parameterSource.addValue("ArchivesTypeID", archivesTypeID);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				jdbcTemplate.query(sql, parameterSource, rowCallbackHandler);
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
			pErrInfo.getContent().append(e.getMessage());
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
}
