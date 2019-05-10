package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoAttachedFileTypeCodeDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������Ϣԭ�ı��Daoʵ����
 * @author Administrator
 *
 */
public class ArchivesInfoAttachedFileTypeCodeDaoImpl extends JdbcDaoSupport implements IArchivesInfoAttachedFileTypeCodeDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesInfoAttachedFileMapper implements RowMapper<ArchivesInfoAttachedFile>
	{
		@Override
		public ArchivesInfoAttachedFile mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			int orderID = rs.getInt("OrderID");
			String title = rs.getString("Title");
			String oriFileName = rs.getString("OriFileName");
			double originalSize = rs.getDouble("OriginalSize");
			String originalType = rs.getString("OriginalType");
			Date attachedTime = rs.getTimestamp("AttachedTime");
			int fetchFullTextRequest = rs.getInt("FetchFullTextRequest");
			String archivingFileName = rs.getString("ArchivingFileName");
			String resaveFileName = rs.getString("ResaveFileName");
			double resaveSize = rs.getDouble("ResaveSize");
			String resaveType = rs.getString("ResaveType");
			Date resaveTime = rs.getTimestamp("ResaveTime");
			boolean deleteFlag = rs.getBoolean("DeleteFlag");
			String remark = rs.getString("Remark");
			
			return new ArchivesInfoAttachedFile(iD,archivesTypeID,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
		}
	}
	
	/**
	 * ���뵵��ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_INSERT_AttachedFile = "INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,FetchFullTextRequest,ArchivingFileName ) VALUES" +
			                                                                 "(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:FetchFullTextRequest,:ArchivingFileName)";
	
	/**
	 * ����ԭ�ĵ����ļ������ҵ���ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_SELECT_AttachedFileIDByName = "SELECT ID FROM %1$s WHERE OriFileName=?";
	
	/**
	 * ����ԭ�ĵ�����Ų��ҵ���ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_SELECT_AttachedFileById = "SELECT * FROM %1$s WHERE ID=?";
	
	
	/**
	 *���µ���ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_UPDATE_AttachedFile = "UPDATE %1$s SET ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,OrderID=:OrderID,Title=:Title,OriFileName=:OriFileName,OriginalSize=:OriginalSize,OriginalType=:OriginalType,FetchFullTextRequest=:FetchFullTextRequest WHERE ID=:ID";
	
	/**
	 *���ҵ���ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_SELECT_FindAttachedFiles = "SELECT * FROM %1$s WHERE NBXH=?";
	
	/**
	 *���ҵ���ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_DELETE_DeleteAttachedFile = "DELETE FROM %1$s WHERE ID=?";
	
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
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.����ԭ����Ϣ��) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ���ԭ����Ϣ��Ƿ�Ϊ�ա�");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ���ԭ����Ϣ��Ƿ�Ϊ�ա�");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("�������ࣨ" + archivesType.getFullCode() + "���ĵ���ԭ����Ϣ��Ƿ�Ϊ�ա�");
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
	public boolean addArchivesInfoAttachedFile(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		// INSERT SQL���
		String localSql = "";

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(archivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ�������ڲ���ŷǷ�Ϊ0");
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_INSERT_AttachedFile, archivesInfoWorkingTableName);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesTypeID", archivesInfoAttachedFile.getArchivesTypeID());
				parameterSource.addValue("NBXH", archivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", archivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", archivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", archivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", archivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", archivesInfoAttachedFile.getOriginalType()); 
				parameterSource.addValue("ArchivingFileName", archivesInfoAttachedFile.getArchivingFileName());
				parameterSource.addValue("FetchFullTextRequest", archivesInfoAttachedFile.getFetchFullTextRequest());
				
				jdbcTemplate.update(localSql, parameterSource);
				
				// ���پֲ�����
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// �����쳣����
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
	public boolean deleteArchivesInfoAttachedFile(ArchivesType archivesType, int archivesInfoAttachedFileID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		// INSERT SQL���
		String localSql = "";

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}

			if (pFlag) {
				if (archivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ����Ϣ��ŷǷ�Ϊ0");
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				localSql = String.format(SQL_DELETE_DeleteAttachedFile, archivesInfoWorkingTableName);
				
				getJdbcTemplate().update(localSql,archivesInfoAttachedFileID);
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// �����쳣����
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
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		System.out.println("���ҳɹ�");
		return true;
	}

	@Override
	public boolean findArchivesInfoAttachedFiles(ArchivesType archivesType, int pNBXH, List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		// INSERT SQL���
		String localSql = "";

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵��������Ϣ
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			if (pFlag) {
				if (pNBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ�������������ڲ���ŷǷ�Ϊ0");
				}
			}
			
			if (pFlag) {
				if (archivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫ���ص�ԭ�ĵ��ӵ�����Ϣ���϶���û�и�ֵ");
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_FindAttachedFiles, archivesInfoWorkingTableName);
				
				List<ArchivesInfoAttachedFile> pArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new ArchivesInfoAttachedFileMapper(),pNBXH);
				
				if (pArchivesInfoAttachedFiles.size()>0) {
					archivesInfoAttachedFiles.addAll(pArchivesInfoAttachedFiles);
				}
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// �����쳣����
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
	public boolean findArchivesInfoAttachedFileByName(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		// INSERT SQL���
		String localSql = "";

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ��ѯ��ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(archivesInfoAttachedFile.getOriFileName() == null){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ��ѯ��ԭ�ĵ����ļ���Ϣ���ļ����Ƿ�Ϊ��");
			}else if("".equals(archivesInfoAttachedFile.getOriFileName())){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ��ѯ��ԭ�ĵ����ļ���Ϣ���ļ����Ƿ�Ϊ��");
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileIDByName, archivesInfoWorkingTableName);
				
				List<Integer> ids = getJdbcTemplate().queryForList(localSql, Integer.class,archivesInfoAttachedFile.getOriFileName());
				
				if (ids.size()>0) {
					archivesInfoAttachedFile.setID(ids.get(0));
				}
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// �����쳣����
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
	public boolean updateArchivesInfoAttachedFile(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		// INSERT SQL���
		String localSql = "";

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(archivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ�������ڲ���ŷǷ�Ϊ0");
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;

				localSql = String.format(SQL_UPDATE_AttachedFile, archivesInfoWorkingTableName);
				
                NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesTypeID", archivesInfoAttachedFile.getArchivesTypeID());
				parameterSource.addValue("NBXH", archivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", archivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", archivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", archivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", archivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", archivesInfoAttachedFile.getOriginalType());
				parameterSource.addValue("FetchFullTextRequest", archivesInfoAttachedFile.getFetchFullTextRequest());
				parameterSource.addValue("ID", archivesInfoAttachedFile.getID());
				
				jdbcTemplate.update(localSql, parameterSource);

			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// �����쳣����
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
	public boolean findArchivesInfoAttachedFileById(ArchivesType archivesType, ArchivesInfoAttachedFile archivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		// INSERT SQL���
		String localSql = "";

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵��������Ϣ
			pErrPos = 1;
			if (archivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(archivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (archivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(archivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ�Ա�ŷǷ�Ϊ0");
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileById, archivesInfoWorkingTableName);
				
                List<ArchivesInfoAttachedFile> pArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new ArchivesInfoAttachedFileMapper(),archivesInfoAttachedFile.getID());
				
				if (pArchivesInfoAttachedFiles.size()>0) {
					archivesInfoAttachedFile.cloneFrom(pArchivesInfoAttachedFiles.get(0));
				}
	
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// �����쳣����
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

}
