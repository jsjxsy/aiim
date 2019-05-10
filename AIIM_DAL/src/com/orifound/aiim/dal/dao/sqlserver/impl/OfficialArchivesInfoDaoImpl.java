package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IOfficialArchivesInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 
 *���ĵ�����ϢDAOʵ����
 * 
 */
public class OfficialArchivesInfoDaoImpl extends JdbcDaoSupport implements
		IOfficialArchivesInfoDao {
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */

	@SuppressWarnings("unused")
	private class OfficialArchivesInfoMapper implements
			RowMapper<OfficialArchivesInfo> {
		
		
		public OfficialArchivesInfoMapper() {
		}

		
		public OfficialArchivesInfoMapper(
				OfficialArchivesType offcialArchivesType) {
			this.offcialArchivesType = offcialArchivesType;
		}


		private OfficialArchivesType offcialArchivesType=null;

		public OfficialArchivesType getOffcialArchivesType() {
			return offcialArchivesType;
		}

		public void setOffcialArchivesType(OfficialArchivesType offcialArchivesType) {
			this.offcialArchivesType = offcialArchivesType;
		}

		@Override
		public OfficialArchivesInfo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			String value="";
			OfficialArchivesInfo officialArchivesInfo=new OfficialArchivesInfo(offcialArchivesType);
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues().values())
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

			return officialArchivesInfo;
		}
	}
	
	
	
	/**
	 * ���캯��
	 */
	public OfficialArchivesInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * 
	 * @param dataSource
	 *            ����Դ
	 */
	public OfficialArchivesInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// �������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
	 * ���빫�ĵ����Ǽ���Ϣ��¼��SQL���
	 */
	private final String SQL_INSERT_OfficialArchivesInfo = "INSERT INTO %1$s (%2$s) VALUES(%3$s)";
     
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?;";
	
	/**
	 * ɾ��ָ���ڲ���ŵĵ�����Ϣ��¼��SQL
	 */
	private final String SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=?";
	

	/**
	 * ����ָ��������Ϣ��SQL���<br>
	 * ���µ�����Ϣʱ���ܹ��޸ĵ�����Ԫ���������ֶ�ֵ�����ڵ���������״̬�Լ���¼��Ա����Ϣ�����޸�
	 */
	private final String SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?";
	
	
	/**
	 * 
	 */
	private final String SQL_SELECT_COUNT_By_QueryConditions = "SELECT COUNT(NBXH) FROM %1$s"
		+ " WHERE 1 = 1 %2$s";
	

	/**
	 * 
	 */
	private final String SQL_DELETE_ParentAndChild_ByNBXH="DELETE FROM %1$s WHERE NBXH=:NBXH";
	
	/**
	 * ��ҳ��ѯָ��������״̬������ָ���������ļ����򰸾�������Ϣ��SQL��䣨�����ļ���¼Ҫ���˵���<br>
	 * %1$s��ҳ��С<br>
	 * %2$s���Ѿ���ȡ���ļ�¼����<br>
	 * %3$s�����嵵�������Ӧ�Ĺ鵵��������<br>
	 * %4$s��ָ����ѯ������SQLƬ��<br>
	 * 
	 */
	private final String SQL_SELECT_By_QueryConditions_WithPage = "SELECT TOP %1$s * FROM %3$s"
			+ " WHERE 1 = 1  %4$s AND NBXH < "
			+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %2$s NBXH FROM %3$s"
			+ " WHERE 1 = 1 %4$s ORDER BY NBXH DESC) AS T) "
			+ "ORDER BY NBXH DESC";

	/**
	 * ��ѯָ��������״̬������ָ���������ļ����򰸾�������Ϣ��SQL��䣨�����ļ���¼Ҫ���˵���������ҳ<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵��������<br>
	 * %2$s��ָ����ѯ������SQLƬ��<br>
	 */
	private final String SQL_SELECT_By_QueryConditions_WithoutPage = "SELECT * FROM %1$s"
			+ " WHERE OfficialArchivesTypeID=:OfficialArchivesTypeID %2$s ORDER BY NBXH DESC";

	/**��ӡ��ѯ���
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_ALL= "SELECT * FROM %1$s";
	
	@Override
	public boolean delete(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		
			boolean pFlag = true;
			int pErrPos = 0;
			Throwable throwable = new Throwable();

			// ���嵵�������Ӧ�Ĺ��ĵ�����Ϣ��������
			String officialArchivesInfoTableName = "";
			String localSql = "";// �����ύ��SQL���

			try
			{
				// ���JDBC����Դ�Ƿ���ȷ����ע��
				if (CheckDataSourceInjection(pErrInfo) == false)
				{
					pFlag = false;
				}

				// ��鵵�������Ƿ��и�ֵ
				if (pFlag)
				{
					pErrPos = 1;
					if (officialArchivesType == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ���������Ϣ�Ƿ�Ϊ�ա�");
					}
				}
				
				// ��鹫�ĵ�����Ϣ�����Ƿ�ֵ
				if (pFlag)
				{
					pErrPos = 2;
					if (officialArchivesInfo == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ�����Ϣ����Ƿ�Ϊ��");
					}
				}

				// ����ڲ�����Ƿ���ֵ
				if (pFlag)
				{
					pErrPos = 3;
					if (officialArchivesInfo.getNBXH() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ�����Ϣ�������ԣ�NBXH��û�и�ֵ");
					}
				}

				// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
				if (pFlag)
				{
					pErrPos = 4;
					if (checkTableName(officialArchivesType, pErrInfo) == false)
					{
						pFlag = false;
					}
					else
					{
						// ���浱ǰ����Ĺ��ĵ����ǼǱ�
						officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
					}
				}

				// ִ��SQL���
				if (pFlag)
				{
					pErrPos = 5;

					// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
					// ?�Ķ�̬�������������ж�̬������SQL����ַ���
					localSql = String.format(SQL_DELETE_ByNBXH, officialArchivesInfoTableName);

					JdbcTemplate jdbcTemplate = getJdbcTemplate();
					jdbcTemplate.update(localSql, officialArchivesInfo.getNBXH());

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

	/**
	 * ���쵵����Ϣ��¼��ѯ������SQLƬ��
	 * 
	 * @param archivesInfoQueryConditions
	 *            ������¼��ѯ��������
	 * @param querySQL
	 *            ���ع���õĲ�ѯ����SQLƬ��
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private static boolean getSqlForOfficialArchivesInfoInputQueryConditions(List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (OfficialArchivesInfoQueryCondition item : officialArchivesInfoQueryConditions)
			{
				// ����Ƿ�Χ��ѯ��������Сֵ�����ֵ����
				if (item.getDataItem().getRangeQueryFlag())
				{
					// ���赥���ŵ����
					if (item.getDataItem().getColumnDataType() == EnumColumnDataType.ʵ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.����
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.����ֵ)
					{
						// ƴ���߼����з� AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// ƴ���ֶ���
						querySQL.append(item.getDataItem().getColumnName());
						// ƴ��������Χ
						querySQL.append(" BETWEEN ");
						querySQL.append(item.getMinValue());
						querySQL.append(" AND ");
						querySQL.append(item.getMaxValue());
					}
					// ��Ҫ�����ŵ����
					else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.�ַ���
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.�ı�)
					{
						// ƴ���߼����з� AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// ƴ���ֶ���
						querySQL.append(item.getDataItem().getColumnName());
						// ƴ��������Χ
						querySQL.append(" BETWEEN ");
						querySQL.append("'");
						querySQL.append(item.getMinValue());
						querySQL.append("'");
						querySQL.append(" AND ");
						querySQL.append("'");
						querySQL.append(item.getMaxValue());
						querySQL.append("'");
					}
				}
				// ������Ƿ�Χ��ѯ������ֵ����
				else
				{
					// ���赥���ŵ����
					if (item.getDataItem().getColumnDataType() == EnumColumnDataType.ʵ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.����
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.����ֵ)
					{
						// ƴ���߼����з� AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// ƴ���ֶ���
						querySQL.append(item.getDataItem().getColumnName());
						// ƴ������
						querySQL.append(" = "); // ��������ȱʡʹ�þ�ȷƥ��
						querySQL.append(item.getValue());
					}
					// ��Ҫ�����ŵ����
					else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.�ַ���
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.�ı�)
					{
						// ƴ���߼����з� AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// ƴ���ֶ���
						querySQL.append(item.getDataItem().getColumnName());
						// ƴ������
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ��)
						{
							querySQL.append(" = "); // ��������ȱʡʹ�þ�ȷƥ��
							querySQL.append("'");
							querySQL.append(item.getValue());
							querySQL.append("'");
						}
						else
						{
							querySQL.append(" LIKE "); // �ַ�����ȱʡʹ��ģ��ƥ��
							querySQL.append("'%");
							querySQL.append(item.getValue());
							querySQL.append("%'");
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
	

	public boolean find(EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType, OfficialArchivesType officialArchivesType,
			List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";

		StringBuilder querySQL = new StringBuilder();// ��ѯ������SQLƬ��
		String localSql = "";// �����ύ�Ĳ�ѯSQL���

		MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL�Ĳ���Դ����
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
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
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
				}
			}

			// ������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}


			// �����ѯ������SQLƬ��
			if (pFlag)
			{
				if (officialArchivesInfoQueryConditions != null)
				{
					if (officialArchivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForOfficialArchivesInfoInputQueryConditions(officialArchivesInfoQueryConditions, querySQL, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "�����ѯ����SQLƬ��ʧ��; ");
						}
					}
				}
			}

			// ����SQL�Ĳ���Դ
			if (pFlag)
			{
				paramSource.addValue("OfficialArchivesTypeID", officialArchivesType.getID(), Types.INTEGER);
			}

			// ��ѯ�ܼ�¼��
			if (pFlag)
			{
				pErrPos = 3;

				// ���������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_COUNT_By_QueryConditions, officialArchivesInfoTableName, querySQL.toString());
				// ִ�в�ѯ�ܼ�¼����SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
				// ���÷�ҳ�����е��ܼ�¼��
				dataPageInfo.setRowCount(pRowCount);
			}

			// �����ѯ������Ϣ��SQL���
			if (pFlag)
			{
				pErrPos = 4;

				// �����ҳ��С����0��ʾҪ����з�ҳ��ѯ
				if (dataPageInfo.getPageSize() > 0)
				{
					// ����ҳ��С�����������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
					// ?�Ķ�̬�������������ж�̬������SQL����ַ���
					localSql = String.format(SQL_SELECT_By_QueryConditions_WithPage, dataPageInfo.getPageSize(), (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize(),
							officialArchivesInfoTableName, querySQL.toString());
					System.out.println(localSql);

				}
				// �����ҳ��С=0��ʾ����ҳ
				else
				{
					// ���������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
					// ?�Ķ�̬�������������ж�̬������SQL����ַ���
					localSql = String.format(SQL_SELECT_By_QueryConditions_WithoutPage, officialArchivesInfoTableName, querySQL.toString());
				}
			}

			// ִ�в�ѯ������Ϣ��SQL��䲢���ؽ��
			if (pFlag)
			{
				pErrPos = 5;
				List<OfficialArchivesInfo> pOfficialArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new OfficialArchivesInfoMapper(officialArchivesType));
				// ���ز�ѯ���
				if (pOfficialArchivesInfos.size() > 0)
				{
					officialArchivesInfos.addAll(pOfficialArchivesInfos);
				}

				// ���پֲ�����
				pOfficialArchivesInfos = null;
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
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
			paramSource = null;
			namedParameterJdbcTemplate = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByID(int pID, OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���幫�ĵ��������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName= "";
		// INSERT SQL���
		String localSql = "";
	
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// ���Ҫ����Ĺ��ĵ�����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ�����Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (officialArchivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���ϷǷ�Ϊ�ա�");
					}
					else if (officialArchivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���������Ƿ�0��");
					}
				}
			}
			
			//�ж��ĺ��Ƿ�Ϊ��
			if (pFlag) {
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
				}
			}
			
			// ����SQL���
			if (pFlag)
			{
				pErrPos = 2;
				StringBuilder columnsListSQL = new StringBuilder();// �ֶ����б������ŷָ�
				StringBuilder valuesListSQL = new StringBuilder(); // �ֶ�ֵ�б������ŷָ�
				if (getSqlForArchivesInfoInsert(officialArchivesInfo, columnsListSQL, valuesListSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������SQL���Ƭ��ʧ��: ");
				}
				else
				{
					// ��̬����INSERT SQL���
					localSql = String.format(SQL_INSERT_OfficialArchivesInfo, officialArchivesInfoTableName, columnsListSQL.toString(), valuesListSQL.toString());
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 3;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(localSql);
				// ����SQLִ�гɹ��󷵻ص�����
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				pscFactory.setGeneratedKeysColumnNames(new String[] { "NBXH" });

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(pscFactory.newPreparedStatementCreator(new Object[] {}), keyHolder);

				// �����Զ����ɵ��ڲ����
				officialArchivesInfo.setNBXH(keyHolder.getKey().intValue());

				// ���پֲ�����
				pscFactory = null;
				keyHolder = null;
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
	public boolean update(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo,
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

			// ��鵵����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (officialArchivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���ϷǷ�Ϊ�ա�");
					}
					else if (officialArchivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���������Ƿ�0��");
					}
				}
			}

			// ����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (officialArchivesInfo.getNBXH() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
				}
			}

			// ����SET SQL���
			if (pFlag)
			{
				StringBuilder setValueSQL = new StringBuilder();
				if (getSqlForOfficialArchivesInfoUpdateSet(true, officialArchivesInfo, setValueSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����SET SQLƬ��ʧ��: ");
				}
				else
				{
					pErrPos = 1;
					// ��̬����UPDATE SQL���
					// SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?"
					localSql = String.format(SQL_UPDATE_ByNBXH, officialArchivesInfoTableName, setValueSQL.toString());
				}

				setValueSQL = null;
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(localSql, officialArchivesInfo.getNBXH());

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
	
	/**
	 * ���ָ���Ĺ��ĵ���������Ϣ���乫�ĵ����ǼǱ���Ϣ�Ƿ���ȷ��ֵ��
	 * 
	 * @param officialArchivesType
	 *            ���ĵ���������Ϣ
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkTableName(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (officialArchivesType.getOfficialArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ĵ��������Ϣ��Ƿ�Ϊ��");
			}
			else
			{
				if (officialArchivesType.getOfficialArchivesInfoTables().containsKey(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ������ࣨ" + officialArchivesType.getCode() + "���Ĺ��ĵ����ǼǱ���Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					String tableName = "";
					tableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ������ࣨ" + officialArchivesType.getCode() + "���Ĺ��ĵ����ǼǱ���Ϣ�Ƿ�Ϊ�ա�");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("���ĵ������ࣨ" + officialArchivesType.getCode() + "���Ĺ��ĵ����ǼǱ���Ϣ�Ƿ�Ϊ�ա�");
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
	 * ������빫�ĵ�����Ϣ���ֶ��б��ֵ�б��SQLƬ��
	 * 
	 * @param archivesInfo
	 *            ������Ϣ����
	 * @param columnsListSQL
	 *            ���ع���õ��ֶ����б�SQLƬ�Σ����ŷָ�
	 * @param valuesListSQL
	 *            ���ع���õ��ֶ�ֵSQLƬ�Σ����ŷָ�
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getSqlForArchivesInfoInsert(OfficialArchivesInfo officialArchivesInfo, StringBuilder columnsListSQL, StringBuilder valuesListSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 2;
			
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues().values())
			{
				// ����ϵͳ�����������ֶ�
				if (item.getSystemItemFlag())
				{
					// �ڲ�����ֶ���������1�ֶΣ���INSERTʱ���账��
					if (item.getSystemDataItemType() == EnumSystemDataItem.�ڲ����)
					{
						continue;
					}
					
					// �����̺��ɱ��ݻָ�����д�룬��INSERTʱ���账��
					if (item.getSystemDataItemType() == EnumSystemDataItem.�����̺�)
					{
						continue;
					}

					// �����ļ������ֶ�ֵ����Ӿ����ļ�ʱ����ά��������INSERTʱ���账��
					if (item.getSystemDataItemType() == EnumSystemDataItem.�����ļ�����)
					{
						continue;
					}

				}

				// �����ֶ����б��ַ��������ŷָ�
				columnsListSQL.append(item.getColumnName());
				columnsListSQL.append(",");

				// ����ֵ�б��ַ��������ŷָ�
				if (item.getValue() == null)
				{
					valuesListSQL.append("NULL");
				}
				else
				{
					if (item.getColumnDataType() == EnumColumnDataType.�ַ��� || item.getColumnDataType() == EnumColumnDataType.�ı�
							|| item.getColumnDataType() == EnumColumnDataType.����ʱ��)
					{
						valuesListSQL.append("'");
						valuesListSQL.append(item.getValue());
						valuesListSQL.append("'");
					}
					else if (item.getColumnDataType() == EnumColumnDataType.ʵ��)
					{
						valuesListSQL.append(item.getValue());
					}
					else if (item.getColumnDataType() == EnumColumnDataType.����ֵ)
					{
						valuesListSQL.append(item.getValue());
					}
					else if (item.getColumnDataType() == EnumColumnDataType.����)
					{
						valuesListSQL.append(item.getValue());
					}
				}
				valuesListSQL.append(",");// ��Ӷ��ŷָ���
			}

			// ȥ���ֶ��б��ַ���ĩβ�Ķ��ŷָ���
			if (pFlag)
			{
				pErrPos = 3;
				if (columnsListSQL.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�ֶ��б�Ƿ�Ϊ�ա�");
				}
				else
				{
					columnsListSQL.deleteCharAt(columnsListSQL.length() - 1);
				}
			}

			// ȥ��ֵ�б��ַ���ĩβ�Ķ��ŷָ���
			if (pFlag)
			{
				pErrPos = 4;
				if (valuesListSQL.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("ֵ�б�Ƿ�Ϊ�ա�");
				}
				else
				{
					valuesListSQL.deleteCharAt(valuesListSQL.length() - 1);
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
	public boolean findByNBXH(int pNBXH,
			OfficialArchivesType officialArchivesType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		String 	officialArchivesInfoTableName ;
		String localSql = "";
		officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
		localSql = String.format(SQL_SELECT_ByNBXH, officialArchivesInfoTableName);
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<OfficialArchivesInfo> pOfficialArchivesInfos = jdbcTemplate.query(localSql, new OfficialArchivesInfoMapper(officialArchivesType), pNBXH);

		// ���ز�ѯ���
		if (pOfficialArchivesInfos .size() > 0)
		{
			officialArchivesInfo.cloneFrom(pOfficialArchivesInfos.get(0));
		}

		// ���پֲ�����
		pOfficialArchivesInfos = null;
		jdbcTemplate = null;
		return pFlag;
	}
	
	private boolean getSqlForOfficialArchivesInfoUpdateSet(Boolean simpleUpdate, OfficialArchivesInfo officialArchivesInfo, StringBuilder setValueSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues().values())
			{
				pErrPos = 1;
			
				// ����ǹ鵵���ڣ���UPDATEʱ����´�������ϵͳ�����������ֶ�һ�ɲ�����
				if (item.getSystemDataItemType() == EnumSystemDataItem.�鵵����)
				{
					// ����SETֵ�б��ַ��������ŷָ�
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					setValueSQL.append("getdate()");
					setValueSQL.append(",");// ��Ӷ��ŷָ���
				}
				// ����ǹ鵵���ڣ���UPDATEʱ����´�������ϵͳ�����������ֶ�һ�ɲ�����
				if (item.getSystemDataItemType() == EnumSystemDataItem.�鵵��־)
				{
					// ����SETֵ�б��ַ��������ŷָ�
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					setValueSQL.append(item.getValue());
					setValueSQL.append(",");// ��Ӷ��ŷָ���
				}
				// ��¼�������ֶ�
				else if (item.getInputFlag())
				{
					pErrPos = 2;
					// ����SETֵ�б��ַ��������ŷָ�
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					if (item.getValue() == null)
					{
						setValueSQL.append("NULL");
					}
					else
					{
						pErrPos = 3;
						if (item.getColumnDataType() == EnumColumnDataType.�ַ��� || item.getColumnDataType() == EnumColumnDataType.�ı�
								|| item.getColumnDataType() == EnumColumnDataType.����ʱ��)
						{
							setValueSQL.append("'");
							setValueSQL.append(item.getValue());
							setValueSQL.append("'");
						}
						else if (item.getColumnDataType() == EnumColumnDataType.ʵ��)
						{
							setValueSQL.append(item.getValue());
						}
						else if (item.getColumnDataType() == EnumColumnDataType.����ֵ)
						{
							setValueSQL.append(item.getValue());
						}
						else if (item.getColumnDataType() == EnumColumnDataType.����)
						{
							setValueSQL.append(item.getValue());
						}
					}
					setValueSQL.append(",");// ��Ӷ��ŷָ���

					// ������������й����ֶΣ����һ������������ֶε�������
					if (item.getAssociateTextColumnName() != null)
					{
						if (item.getAssociateTextColumnName().length() > 0)
						{
							if (officialArchivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName()))
							{
								pErrPos = 4;
								// ��ȡ�������������ֶ�ֵ����
								FieldValue associateFieldValue = officialArchivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName());

								// ����SETֵ�б��ַ��������ŷָ�
								setValueSQL.append(associateFieldValue.getColumnName());
								setValueSQL.append("=");
								if (associateFieldValue.getValue() == null)
								{
									setValueSQL.append("NULL");
								}
								else
								{
									if (associateFieldValue.getColumnDataType() == EnumColumnDataType.�ַ��� || associateFieldValue.getColumnDataType() == EnumColumnDataType.�ı�
											|| associateFieldValue.getColumnDataType() == EnumColumnDataType.����ʱ��)
									{
										setValueSQL.append("'");
										setValueSQL.append(associateFieldValue.getValue());
										setValueSQL.append("'");
									}
									else if (associateFieldValue.getColumnDataType() == EnumColumnDataType.ʵ��)
									{
										setValueSQL.append(associateFieldValue.getValue());
									}
									else if (associateFieldValue.getColumnDataType() == EnumColumnDataType.����ֵ)
									{
										setValueSQL.append(associateFieldValue.getValue());
									}
									else if (associateFieldValue.getColumnDataType() == EnumColumnDataType.����)
									{
										setValueSQL.append(associateFieldValue.getValue());
									}
								}
								setValueSQL.append(",");// ��Ӷ��ŷָ���
							}
						}
					}

				}
				// ����������账��
				else
				{
					continue;
				}
			}

			// ȥ���ֶ��б��ַ���ĩβ�Ķ��ŷָ���
			if (pFlag)
			{
				pErrPos = 5;
				if (setValueSQL.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("SETֵ�б�Ƿ�Ϊ�ա�");
				}
				else
				{
					setValueSQL.deleteCharAt(setValueSQL.length() - 1);
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
	public boolean update(OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	
	@Override
	public boolean batchDelOfficialArchives(OfficialArchivesType officialArchivesType,final List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
		String localSql = "";// �����ύ��SQL���

		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
				}
			}

			// ��鵵�����ڲ�����Ƿ�Ϊ��
			if (pFlag)
			{
				pErrPos = 3;
				if (officialArchivesInfos == null || officialArchivesInfos.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������ڲ���ŷǷ�Ϊ�գ�");
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 4;

				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_DELETE_ByNBXH, officialArchivesInfoTableName);
				// localSql = String.format(SQL_DELETE_BatchDleteArchives,
				// "ArchivesInfoWorking_JX14");
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.batchUpdate(localSql,
						new BatchPreparedStatementSetter() {
							public void setValues(PreparedStatement ps, int i)
									throws SQLException {
								ps.setInt(1, officialArchivesInfos.get(i).getNBXH());
							}

							public int getBatchSize() {
								return officialArchivesInfos.size();
							}
						});
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
	public boolean deleteParentAndChild(OfficialArchivesType officialArchivesType, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String officialArchivesInfoTableName = "";
		String localSql = "";// �����ύ��SQL���

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
				if (officialArchivesInfo.getNBXH()<= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
				}
			}

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";
				
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_DELETE_ParentAndChild_ByNBXH, officialArchivesInfoTableName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", officialArchivesInfo.getNBXH(),Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(localSql, paramSource);

				// ���پֲ�����
				paramSource = null;
				namedParameterJdbcTemplate = null;
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
	public boolean findAll(OfficialArchivesType officialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String officialArchivesInfoTableName = "";
		String localSql = "";
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag)
			{
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				localSql = String.format(SQL_SELECT_ALL, officialArchivesInfoTableName);
				List<OfficialArchivesInfo> pOfficialArchivesInfos=jdbcTemplate.query(localSql,new OfficialArchivesInfoMapper(officialArchivesType));
				//���ز�ѯ���
				if (pOfficialArchivesInfos.size() > 0) {
					officialArchivesInfos.addAll(pOfficialArchivesInfos);
				}

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

}
