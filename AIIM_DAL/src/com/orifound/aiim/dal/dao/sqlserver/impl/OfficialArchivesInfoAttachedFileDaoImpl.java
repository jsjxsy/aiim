package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IOfficialArchivesInfoAttachedFileDao;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.OfficialArchivesType;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
/**
 * 
 * ���ĵ���ԭ����Ϣ���DAOʵ����
 *
 */
public class OfficialArchivesInfoAttachedFileDaoImpl extends JdbcDaoSupport implements
		IOfficialArchivesInfoAttachedFileDao {
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesInfoAttachedFileDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public OfficialArchivesInfoAttachedFileDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */

	private class OfficialArchivesInfoAttachedFileMapper implements RowMapper<OfficialArchivesInfoAttachedFile>
	{
		
		@Override
		public OfficialArchivesInfoAttachedFile mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int nBXH = rs.getInt("NBXH");
			int orderID = rs.getInt("OrderID");
			String title = rs.getString("Title");
			String oriFileName = rs.getString("OriFileName");
			long originalSize = rs.getLong("OriginalSize");
			String originalType = rs.getString("OriginalType");
			Date attachedTime = rs.getTimestamp("AttachedTime");
			int fetchFullTextRequest = rs.getInt("FetchFullTextRequest");
			String archivingFileName = rs.getString("ArchivingFileName");
			String resaveFileName = rs.getString("ResaveFileName");
			long resaveSize = rs.getLong("ResaveSize");
			String resaveType = rs.getString("ResaveType");
			Date resaveTime = rs.getTimestamp("ResaveTime");
			boolean deleteFlag = rs.getBoolean("DeleteFlag");
			String remark = rs.getString("Remark");
			
			return new OfficialArchivesInfoAttachedFile(iD,nBXH,orderID,title,oriFileName,originalSize,originalType,attachedTime,fetchFullTextRequest,archivingFileName,resaveFileName,resaveSize,resaveType,resaveTime,deleteFlag,remark);
		}
	}
	
	/**
	 * ���ָ���ĵ���������Ϣ����TableType��Ϣ�Ƿ���ȷ��ֵ��
	 * @param archivesType ����������Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkTableName(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			if (officialArchivesType.getOfficialArchivesInfoTables() == null) {
				pFlag = false;
				pErrInfo.getContent().append("���������Ϣ��Ƿ�Ϊ��");
			} else {
				if (officialArchivesType.getOfficialArchivesInfoTables().containsKey(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���ĵ������ࣨ" + officialArchivesType.getCode() + "����TableType��Ϣ�Ƿ�Ϊ�ա�");
				} else {
					String tableName = "";
					tableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��).getTableName();
					if (tableName == null) {
						pFlag = false;
						pErrInfo.getContent().append("���ĵ������ࣨ" + officialArchivesType.getCode() + "����TableType��Ϣ�Ƿ�Ϊ�ա�");
					} else {
						if (tableName.length() == 0) {
							pFlag = false;
							pErrInfo.getContent().append("���ĵ������ࣨ" + officialArchivesType.getCode() + "����TableType��Ϣ�Ƿ�Ϊ�ա�");
						}
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
	 * ����ֵ��SQL���
	 */
	private final String SQL_INSERT_AttachedFile = "INSERT INTO %1$s(NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,FetchFullTextRequest,ArchivingFileName) VALUES(:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:FetchFullTextRequest,:ArchivingFileName)";

	private final String SQL_SELECT_FindAttachedFiles="SELECT * FROM %1$s WHERE NBXH=?";
	
	private final String SQL_DELETE_DeleteAttachedFile="DELETE FROM %1$s WHERE ID=?";
	
	private final String SQL_UPDATE_AttachedFile="UPDATE %1$s SET NBXH=:NBXH,OrderID=:OrderID,Title=:Title,OriFileName=:OriFileName,OriginalSize=:OriginalSize,OriginalType=:OriginalType,FetchFullTextRequest=:FetchFullTextRequest WHERE ID=:ID";
	
	private final String SQL_SELECT_AttachedFileById = "SELECT * FROM %1$s WHERE ID=?";
	
	/**
	 * ����ԭ�ĵ����ļ������ҵ���ԭ�ĵ����ļ���Ϣ��¼��SQL���
	 */
	private final String SQL_SELECT_AttachedFileIDByName = "SELECT ID FROM %1$s WHERE OriFileName=?";
	
	@Override
	public boolean delete(OfficialArchivesType officialArchivesType,
			int officialArchivesInfoAttachedFileID,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
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
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}

			if (pFlag) {
				if (officialArchivesInfoAttachedFileID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("ԭ����Ϣ��ŷǷ�Ϊ0");
				}
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				localSql = String.format(SQL_DELETE_DeleteAttachedFile, officialArchivesInfoTableName);
				
				getJdbcTemplate().update(localSql,officialArchivesInfoAttachedFileID);
	
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

	/**
	 * �������еĹ��ĵ�����Ϣ
	 * @param officialArchivesInfoAttachedFiles
	 * @param pErrInfo
	 * @return
	 */
	public boolean findAll(
			OfficialArchivesType officialArchivesType,int pNBXH,
			List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
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
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
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
				if (officialArchivesInfoAttachedFiles == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫ���ص�ԭ�ĵ��ӵ�����Ϣ���϶���û�и�ֵ");
				}
			}
			
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_FindAttachedFiles, officialArchivesInfoTableName);
				
				List<OfficialArchivesInfoAttachedFile> pOfficialArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new OfficialArchivesInfoAttachedFileMapper() ,pNBXH);
				
				if (pOfficialArchivesInfoAttachedFiles.size()>0) {
					officialArchivesInfoAttachedFiles.addAll(pOfficialArchivesInfoAttachedFiles);
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
	public boolean findOfficialArchivesInfoAttachedFileByName(OfficialArchivesType officialArchivesType, OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
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
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ��ѯ��ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(officialArchivesInfoAttachedFile.getOriFileName() == null){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ��ѯ��ԭ�ĵ����ļ���Ϣ���ļ����Ƿ�Ϊ��");
			}else if("".equals(officialArchivesInfoAttachedFile.getOriFileName())){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ��ѯ��ԭ�ĵ����ļ���Ϣ���ļ����Ƿ�Ϊ��");
			}
			
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileIDByName, officialArchivesInfoTableName);
				
				List<Integer> ids = getJdbcTemplate().queryForList(localSql, Integer.class,officialArchivesInfoAttachedFile.getOriFileName());
				
				if (ids.size()>0) {
					officialArchivesInfoAttachedFile.setID(ids.get(0));
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
	public boolean findByID(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
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
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(officialArchivesInfoAttachedFile.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ���ҵ�ԭ�ĵ����ļ���Ϣ�Ա�ŷǷ�Ϊ0");
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s (ArchivesTypeID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) VALUES(:ArchivesTypeID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_SELECT_AttachedFileById, officialArchivesInfoTableName);
				
                List<OfficialArchivesInfoAttachedFile> pOfficialArchivesInfoAttachedFiles = getJdbcTemplate().query(localSql, new OfficialArchivesInfoAttachedFileMapper(),officialArchivesInfoAttachedFile.getID());
				
				if (pOfficialArchivesInfoAttachedFiles.size()>0) {
					officialArchivesInfoAttachedFile.cloneFrom(pOfficialArchivesInfoAttachedFiles.get(0));
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
	public boolean save(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
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
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(officialArchivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ�������ڲ���ŷǷ�Ϊ0");
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				
				//INSERT INTO %1$s(ID,NBXH,OrderID,Title,OriFileName,OriginalSize,OriginalType,AttachedTime,FetchFullTextRequest) value(:ID,:NBXH,:OrderID,:Title,:OriFileName,:OriginalSize,:OriginalType,:AttachedTime,:FetchFullTextRequest)

				localSql = String.format(SQL_INSERT_AttachedFile, officialArchivesInfoTableName);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXH", officialArchivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", officialArchivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", officialArchivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", officialArchivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", officialArchivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", officialArchivesInfoAttachedFile.getOriginalType());
				parameterSource.addValue("FetchFullTextRequest", officialArchivesInfoAttachedFile.getFetchFullTextRequest());
				parameterSource.addValue("ArchivingFileName", officialArchivesInfoAttachedFile.getArchivingFileName());
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
	public boolean update(OfficialArchivesType officialArchivesType,
			OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile,
			ErrInfo pErrInfo) {
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
			if (officialArchivesType == null ) {
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ����Ϊ��");
			}else if(officialArchivesType.getID() == 0){
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣ�����ŷǷ�Ϊ0");
			}
			
			//���ԭ�ĵ����ļ���Ϣ
			if (officialArchivesInfoAttachedFile == null ) {
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ����Ϊ��");
			}else if(officialArchivesInfoAttachedFile.getNBXH() == 0){
				pFlag = false;
				pErrInfo.getContent().append("Ҫ������ԭ�ĵ����ļ���Ϣ�������ڲ���ŷǷ�Ϊ0");
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumArchivesInfoTableType.����ԭ����Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;

				localSql = String.format(SQL_UPDATE_AttachedFile, archivesInfoWorkingTableName);
				
                NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXH", officialArchivesInfoAttachedFile.getNBXH());
				parameterSource.addValue("OrderID", officialArchivesInfoAttachedFile.getOrderID());
				parameterSource.addValue("Title", officialArchivesInfoAttachedFile.getTitle());
				parameterSource.addValue("OriFileName", officialArchivesInfoAttachedFile.getOriFileName());
				parameterSource.addValue("OriginalSize", officialArchivesInfoAttachedFile.getOriginalSize());
				parameterSource.addValue("OriginalType", officialArchivesInfoAttachedFile.getOriginalType());
				parameterSource.addValue("FetchFullTextRequest", officialArchivesInfoAttachedFile.getFetchFullTextRequest());
				parameterSource.addValue("ID", officialArchivesInfoAttachedFile.getID());
				
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


}
