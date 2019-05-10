/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ICatalogPrintDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;

/**
 * Ŀ¼��ӡdao�ӿ�ʵ���ࣨSQL Server ƽ̨ʵ�֣�
 */
public class CatalogPrintDaoImpl extends JdbcDaoSupport implements ICatalogPrintDao {
	
	/**
	 * ���캯��
	 */
	public CatalogPrintDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public CatalogPrintDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	public class ArchivesInfoMapper implements RowMapper<ArchivesInfo>
	{

		/**
		 * �������Ĺ��캯��
		 * 
		 * @param archivesType
		 *            ����������Ϣ
		 */
		public ArchivesInfoMapper(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * ����������Ϣ
		 */
		private ArchivesType archivesType = null;

		/**
		 * ��������ֵ������������Ϣ
		 * 
		 * @param archivesType
		 *            ����������Ϣ
		 */
		public void setArchivesType(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * ��ȡ����ֵ������������Ϣ
		 * 
		 * @return ����������Ϣ
		 */
		public ArchivesType getArchivesType()
		{
			return archivesType;
		}
		
		@Override
		public ArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String value = "";
			ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				if (item.getColumnDataType() == EnumColumnDataType.�ַ���)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.ʵ��)
				{
					float tempValue = rs.getFloat(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.����ֵ)
				{
					boolean tempValue = rs.getBoolean(item.getColumnName());
					value = tempValue ? "1" : "0";
				}
				else if (item.getColumnDataType() == EnumColumnDataType.����)
				{
					int tempValue = rs.getInt(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.�ı�)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.����ʱ��)
				{
					Date tempValue = rs.getDate(item.getColumnName());
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormater.format(tempValue);
				}
				item.setValue(value);
			}
			
			return archivesInfo;
		}
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	public class ArchivesInfoMapperOther implements RowMapper<ArchivesInfo>
	{

		/**
		 * �������Ĺ��캯��
		 * 
		 * @param archivesType
		 *            ����������Ϣ
		 */
		public ArchivesInfoMapperOther(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * ����������Ϣ
		 */
		private ArchivesType archivesType = null;

		/**
		 * ��������ֵ������������Ϣ
		 * 
		 * @param archivesType
		 *            ����������Ϣ
		 */
		public void setArchivesType(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * ��ȡ����ֵ������������Ϣ
		 * 
		 * @return ����������Ϣ
		 */
		public ArchivesType getArchivesType()
		{
			return archivesType;
		}
		
		@Override
		public ArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String value = "";
			ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				if (item.getColumnDataType() == EnumColumnDataType.�ַ���)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.ʵ��)
				{
					float tempValue = rs.getFloat(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.����ֵ)
				{
					boolean tempValue = rs.getBoolean(item.getColumnName());
					value = tempValue ? "1" : "0";
				}
				else if (item.getColumnDataType() == EnumColumnDataType.����)
				{
					int tempValue = rs.getInt(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.�ı�)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.����ʱ��)
				{
					Date tempValue = rs.getDate(item.getColumnName());
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormater.format(tempValue);
				}
				item.setValue(value);
			}
			
			//���÷�Ƥ ���Ƶ�λ
			String departmentName = rs.getString("departmentName");
			if(departmentName != null) {
				archivesInfo.setDepartmentName(departmentName);
			}
			return archivesInfo;
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
	 * �ƽ�Ŀ¼ ��ӡ��ѯ
	 * UNION ALL��ѯ�������ȡ������Ϣ
	 * %1$s �����鵵��
	 * %2$s ����������
	 * %3$s ������˽������
	 */
	private static final String SQL_SELECT_TransferCatalog = "SELECT * FROM(SELECT s.* FROM PaperTransferBatchesDetails pd,%1$s s" +
			" WHERE (pd.TransferBatNo=:BatNo OR pd.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:BatNo)) " +
			" AND pd.ArchivesTypeID=s.ArchivesTypeID AND pd.NBXH=s.NBXH AND s.ParentNBXH=0 AND s.ArchivesTypeID=:archivesTypeID %3$s" +
			" UNION ALL" +
			" SELECT s.* FROM PaperTransferBatchesDetails pd,%2$s s" +
			" WHERE (pd.TransferBatNo=:BatNo OR pd.TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:BatNo)) " +
			" AND pd.ArchivesTypeID=s.ArchivesTypeID AND pd.NBXH=s.NBXH AND s.ParentNBXH=0 AND s.ArchivesTypeID=:archivesTypeID %3$s) t ORDER BY NBXH ASC";

	/**
	 * ����Ŀ¼ ��ӡ��ѯ
	 * %1$s �����鵵��
	 */
	private final String SQL_SELECT_BoxCatalog = "SELECT a.* FROM %1$s a,StoreroomArchivesInfo s " +
			"  WHERE s.ArchivesBoxBarcode=:boxBarcode AND s.StoreStatus<>:StoreStatus AND s.ArchivesTypeID=:archivesTypeID" +
			" AND a.ArchivesTypeID=s.ArchivesTypeID AND a.NBXH=s.NBXH AND a.ParentNBXH=0 ORDER BY a.ArchivesID ASC";
	
	
	/**
	 * ��Ƥ��ӡ ��ѯָ���ڲ���ŵĵ�����Ϣ��SQL���
	 */
	private final String SQL_SELECT_ENVELOPE_ByNBXH = "SELECT w.*,d.Name departmentName FROM %1$s w,%2$s wp,UserInfo u,DD_DepartmentInfo d " +
			" WHERE w.ArchivesTypeID=wp.ArchivesTypeID AND w.NBXH=wp.NBXH AND wp.UserID=u.UserID AND u.DepartmentID=d.ID AND w.NBXH=?";
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICatalogPrintDao#findArchivesinfoForTransferCatalog(com.orifound.aiim.entity.ArchivesType, int, boolean, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesinfoForTransferCatalog(String depaermentName, String paperTransferBatNo,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
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
				//�����鵵��Ϣ����
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				String archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("BatNo", paperTransferBatNo, Types.VARCHAR);
				paramSource.addValue("archivesTypeID", archivesType.getID(), Types.INTEGER);
				
				pErrPos = 3;
				String receiveCheckResult = " AND ";
				//�����γɲ��Ŵ�ӡ ����Ŀ¼
				if("DXBM".equals(depaermentName.toUpperCase())) {
					receiveCheckResult += "pd.ReceiveCheckResult in(1,3,4)";
				} else if("YWZD".equals(depaermentName.toUpperCase()) || "DAGL".equals(depaermentName.toUpperCase())){
					receiveCheckResult += "pd.ReceiveCheckResult=3";
				}
				List<ArchivesInfo> reaults = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_TransferCatalog, archivesInfoSavedTableName, archivesInfoWorkingTableName, receiveCheckResult), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("�ƽ�Ŀ¼��ӡ ��ѯ������ϢSQL��"+String.format(SQL_SELECT_TransferCatalog, archivesInfoSavedTableName, archivesInfoWorkingTableName, receiveCheckResult));
				if(reaults.size() >= 1) {
					archivesInfos.addAll(reaults);
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

	@Override
	public boolean findArchivesinfoForBoxCatalog(String boxBarcode, int storeStatus, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
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
				//�����鵵��Ϣ����
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("boxBarcode", boxBarcode, Types.VARCHAR);
				paramSource.addValue("StoreStatus", storeStatus, Types.INTEGER);
				paramSource.addValue("archivesTypeID", archivesType.getID(), Types.INTEGER);
				

				pErrPos = 3;
				List<ArchivesInfo> pEntitys = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_BoxCatalog, archivesInfoSavedTableName), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("����Ŀ¼��ӡ��ѯSQL="+String.format(SQL_SELECT_BoxCatalog, archivesInfoSavedTableName));
				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesInfos.addAll(pEntitys);
				}

				//���پֲ�����
				paramSource = null;
				namedParameterJdbcTemplate = null;
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

	@Override
	public boolean findArchivesinfoForEnelope(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (pNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				String archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				String archivesInfoWorkProcedureTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵���̱�).getTableName();
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				String localSql = String.format(SQL_SELECT_ENVELOPE_ByNBXH, archivesInfoWorkingTableName, archivesInfoWorkProcedureTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapperOther(archivesType), pNBXH);
				
				System.out.println("��Ƥ��ӡ��ѯSQL="+localSql);

				// ���ز�ѯ���
				if (pArchivesInfos.size() > 0)
				{
					archivesInfo.cloneFrom(pArchivesInfos.get(0));
					archivesInfo.setDepartmentName(pArchivesInfos.get(0).getDepartmentName());
				}

				// ���پֲ�����
				pArchivesInfos = null;
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
}
