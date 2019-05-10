/**
 * 
 */
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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.EnumWorkingUserIDType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ������Ϣ������DAOʵ����
 * 
 */
public class ArchivesInfoWorkingDaoImpl extends JdbcDaoSupport implements IArchivesInfoWorkingDao
{

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
	 * ���캯��
	 */
	public ArchivesInfoWorkingDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * 
	 * @param dataSource
	 *            ����Դ
	 */
	public ArchivesInfoWorkingDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}

	/**
	 * ��ѯָ��������״̬������ָ���������ļ����򰸾�������Ϣ��������SQL��䣨�����ļ���¼Ҫ���˵���<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵��������<br>
	 * %2$s��ָ����ѯ������SQLƬ��<br>
	 * %3$s��parentFlag<br>
	 * %4$s��userType<br>
	 */
	private final String SQL_SELECT_COUNT_By_QueryConditions = "SELECT COUNT(NBXH) FROM %1$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %4$s %3$s  AND ParentNBXH=0 %2$s";

	/**
	 * ��ҳ��ѯָ��������״̬������ָ���������ļ����򰸾�������Ϣ��SQL��䣨�����ļ���¼Ҫ���˵���<br>
	 * %1$s��ҳ��С<br>
	 * %2$s���Ѿ���ȡ���ļ�¼����<br>
	 * %3$s�����嵵�������Ӧ�Ĺ鵵��������<br>
	 * %4$s��ָ����ѯ������SQLƬ��<br>
	 * %5$s���Ƿ�ƴ��parentFlag��־
	 */
	private final String SQL_SELECT_By_QueryConditions_WithPage = "SELECT TOP %1$s * FROM %3$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %6$s %5$s AND ParentNBXH=0 %4$s AND NBXH < "
			+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %2$s NBXH FROM %3$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %6$s %5$s AND ParentNBXH=0 %4$s ORDER BY NBXH DESC) AS T) "
			+ "ORDER BY NBXH DESC";

	/**
	 * ��ѯָ��������״̬������ָ���������ļ����򰸾�������Ϣ��SQL��䣨�����ļ���¼Ҫ���˵���������ҳ<br>
	 * %1$s�����嵵�������Ӧ�Ĺ鵵��������<br>
	 * %2$s��ָ����ѯ������SQLƬ��<br>
	 */
	private final String SQL_SELECT_By_QueryConditions_WithoutPage = "SELECT * FROM %1$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %4$s %3$s AND ParentNBXH=0 %2$s ORDER BY NBXH DESC";

	/**
	 * ��ѯָ���ڲ���ŵĵ�����Ϣ��SQL���
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?";

	/**
	 * ����ָ��������Ϣ��SQL���<br>
	 * ���µ�����Ϣʱ���ܹ��޸ĵ�����Ԫ���������ֶ�ֵ�����ڵ���������״̬�Լ���¼��Ա����Ϣ�����޸�
	 */
	private final String SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?";

	/**
	 * ��������������Ϣ��¼��SQL���
	 */
	private final String SQL_INSERT_ArchivesInfo = "INSERT INTO %1$s (%2$s) VALUES(%3$s)";

	/**
	 * ��ѯָ��������������ļ����ֵ��SQL���
	 */
	private final String SQL_SELECT_MaxSubContentID = "SELECT MAX(SubContentID) FROM %1$s WHERE ParentNBXH=?";

	/**
	 * ����ָ������ľ����ļ���������ҳ����SQL���
	 */
	private final String SQL_UPDATE_SubContentCountAndPageSum = "UPDATE %1$s SET " + "SubContentCount=(SELECT COUNT(NBXH) FROM %1$s WHERE ParentNBXH=:ParentNBXH),"
			+ "PageSum=(SELECT SUM(PageSum) FROM %1$s WHERE ParentNBXH=:ParentNBXH) " + "WHERE NBXH=:ParentNBXH";

	/**
	 * ���ݰ�����ڲ���Ų��������о����ļ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_ChildArchivesInfo_ByParentNBXH = "SELECT * FROM %1$s WHERE ParentNBXH=? ORDER BY SubContentID desc";

	/**
	 * ��������ָ��������Ϣ�İ����־��SQL
	 */
	private final String SQL_UPDATE_BatchUpdateParentFlag = "UPDATE %1$s SET ParentNBXH=?,UserID1=?,WorkFlowStatus=?,ContentID=?,ContentIDText=?,SubContentID=?,SubContentIDText=?,ArchivesID=? WHERE NBXH=?";
	
	/**
	 * ��������ָ��������Ϣ�������������ڲ���ŵ�SQL
	 */
	private final String SQL_UPDATE_BatchUpdateParentNBXH = "UPDATE %1$s SET ParentNBXH=:parentNBXH WHERE NBXH IN (:NBXHS)";

	/**
	 * ɾ��ָ���ڲ���ŵĵ�����Ϣ��¼��SQL
	 */
	private final String SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=? OR ParentNBXH=?";
	
	/**
	 * ɾ��ָ�������ڲ���ż�������ļ��ĵ�����Ϣ��¼��SQL
	 */
	private final String SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";

	/**
	 * ����ָ���ڲ���ŵ�����Ϣ��¼�Ĺ�����״̬���û������Ϣ��SQL���
	 */
	private final String SQL_UPDATE_WorkFlowStatusUserID_ByNBXH = "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE NBXH IN (:NBXH)";

	// /**
	// * ɾ��ָ��������Ϣ�İ����־��SQL
	// */
	// private final String SQL_DELETE_ByNBXH =
	// "delete from %1$s  where NBXH=?";

	/**
	 * ����ָ���ڲ���ŵ��������о����ļ��Ĺ�����״̬���û������Ϣ��SQL���
	 */
	private final String SQL_UPDATE_WorkFlowStatusUserID_ByParentNBXH = "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE ParentNBXH IN (:ParentNBXH)";

	/**
	 * ���ݹ�����״̬ͳ�Ƶ���������SQL
	 * %1$s������
	 * %2$s���û�ID
	 */
	private final String SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser = "SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus =:WorkFlowStatus AND %2$s IN (:userIDs) AND ParentNBXH=0";
	
	/**
	 * ���ݹ�����״̬ͳ�Ƶ���������SQL
	 * %1$s������
	 * %2$s���û�ID
	 */
	private final String SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndDept = "SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus =:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) AND ParentNBXH=0";
	
	/**
	 * ���ݹ���״̬���ҵ�����Ϣ��SQL
	 */
	private final String SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndUser_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND %4$s IN (:userIDs) AND  ParentNBXH=0 AND NBXH < "
		+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND %4$s IN (:userIDs)  AND ParentNBXH=0 ORDER BY NBXH DESC) AS T) "
		+ "ORDER BY NBXH DESC";
	
	/**
	 * ���ݹ���״̬���ҵ�����Ϣ��SQL
	 */
	private final String SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndDept_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) AND ParentNBXH=0 AND NBXH < "
		+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) AND ParentNBXH=0  ORDER BY NBXH DESC) AS T) "
		+ "ORDER BY NBXH DESC";
	
	/**
	 * ���õ�����Ϣ�޸���־��SQL
	 */
	private static final String SQL_UPDATE_updateArchivesInfoFixedFlag = "UPDATE %1$s SET FixedFlag=? WHERE NBXH=?";
	
	
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// �������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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

	/**
	 * ���쵵�����µ�SET���SQLƬ��<br>
	 * ע�⣺��SQLƬ����ֻ��SET�ؼ���֮��ĸ�ֵ��䣬������SET�ؼ���
	 * 
	 * @param simpleUpdate
	 *            �Ƿ�Ϊ�򵥸��´���<br>
	 *            �򵥸��������ڽ����Ե�������¼�ĵ���Ԫ������Ϣ�����޸ĸ��£����ڵ���������״̬�Լ���¼��Ա����Ϣ�����޸�<br>
	 *            �������³��˰�����������¼��Ԫ������ϢҪ���£���Ҫ���µ����Ĺ鵵������״̬��������Ա��ŵ���Ϣ
	 * @param archivesInfo
	 *            ������Ϣ����
	 * @param setValueSQL
	 *            ���ع���õ�SET���
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getSqlForArchivesInfoUpdateSet(Boolean simpleUpdate, ArchivesInfo archivesInfo, StringBuilder setValueSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				pErrPos = 1;
				// �������µ�ʱ����Ҫ������ֶ�
				if (item.getSystemDataItemType() == EnumSystemDataItem.������������״̬ || item.getSystemDataItemType() == EnumSystemDataItem.������Ա1
						|| item.getSystemDataItemType() == EnumSystemDataItem.������Ա2 || item.getSystemDataItemType() == EnumSystemDataItem.������Ա3)
				{
					if (simpleUpdate == false)
					{
						// ����SETֵ�б��ַ��������ŷָ�
						setValueSQL.append(item.getColumnName());
						setValueSQL.append("=");
						if (item.getValue() == null)
						{
							setValueSQL.append("NULL");
						}
						else
						{
							setValueSQL.append(item.getValue());
						}
						setValueSQL.append(",");// ��Ӷ��ŷָ���
					}
				}
				// ����ǹ鵵���ڣ���UPDATEʱ����´�������ϵͳ�����������ֶ�һ�ɲ�����
				if (item.getSystemDataItemType() == EnumSystemDataItem.�鵵����)
				{
					// ����SETֵ�б��ַ��������ŷָ�
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					setValueSQL.append("getdate()");
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
							if (archivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName()))
							{
								pErrPos = 4;
								// ��ȡ�������������ֶ�ֵ����
								FieldValue associateFieldValue = archivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName());

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

	/**
	 * ������뵵����Ϣ���ֶ��б��ֵ�б��SQLƬ��
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
	private boolean getSqlForArchivesInfoInsert(ArchivesInfo archivesInfo, StringBuilder columnsListSQL, StringBuilder valuesListSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 2;
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
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

					// �鵵���ڡ��޸���־��ɾ����־�����ű�־����ȱʡֵ����INSERTʱ���账��
					if (item.getSystemDataItemType() == EnumSystemDataItem.�鵵���� || item.getSystemDataItemType() == EnumSystemDataItem.�޸���־
							|| item.getSystemDataItemType() == EnumSystemDataItem.ɾ����־ || item.getSystemDataItemType() == EnumSystemDataItem.���ű�־)
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
	private static boolean getSqlForArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#save(com.orifound.aiim
	 * .entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
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

			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ���Ҫ����ĵ�����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (archivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���ϷǷ�Ϊ�ա�");
					}
					else if (archivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���������Ƿ�0��");
					}
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

			// ����SQL���
			if (pFlag)
			{
				pErrPos = 2;
				StringBuilder columnsListSQL = new StringBuilder();// �ֶ����б������ŷָ�
				StringBuilder valuesListSQL = new StringBuilder(); // �ֶ�ֵ�б������ŷָ�
				if (getSqlForArchivesInfoInsert(archivesInfo, columnsListSQL, valuesListSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������SQL���Ƭ��ʧ��: ");
				}
				else
				{
					// ��̬����INSERT SQL���
					localSql = String.format(SQL_INSERT_ArchivesInfo, archivesInfoWorkingTableName, columnsListSQL.toString(), valuesListSQL.toString());
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
				archivesInfo.setNBXH(keyHolder.getKey().intValue());

				// ���پֲ�����
				pscFactory = null;
				keyHolder = null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#delete(com.orifound
	 * .aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵����Ϣ�����Ƿ�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ��");
				}
			}

			// ����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfo.getNBXH() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�������ԣ�NBXH��û�и�ֵ");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 4;
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

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 5;
				localSql = String.format(SQL_DELETE_ByNBXH, archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, archivesInfo.getNBXH(),archivesInfo.getNBXH());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#update(com.orifound
	 * .aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
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

			// ��鵵����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (archivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���ϷǷ�Ϊ�ա�");
					}
					else if (archivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ�µ��������ֶ�ֵ���������Ƿ�0��");
					}
				}
			}

			// ����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (archivesInfo.getNBXH() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
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

			// ����SET SQL���
			if (pFlag)
			{
				StringBuilder setValueSQL = new StringBuilder();
				if (getSqlForArchivesInfoUpdateSet(true, archivesInfo, setValueSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����SET SQLƬ��ʧ��: ");
				}
				else
				{
					pErrPos = 1;
					// ��̬����UPDATE SQL���
					// SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?"
					localSql = String.format(SQL_UPDATE_ByNBXH, archivesInfoWorkingTableName, setValueSQL.toString());
				}

				setValueSQL = null;
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(localSql, archivesInfo.getNBXH());

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#find(com.orifound.aiim
	 * .entity.EnumArchivesInfoType, int[],
	 * com.orifound.aiim.entity.ArchivesType,
	 * com.orifound.aiim.entity.EnumWorkFlowStatus,java.util.List,
	 * com.orifound.aiim.entity.DataPageInfo, java.util.List,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean find(EnumArchivesInfoType enumArchivesInfoType, int[] userID, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		List<Integer> pUserIDs = new ArrayList<Integer>();

		StringBuilder querySQL = new StringBuilder();// ��ѯ������SQLƬ��
		String localSql = "";// �����ύ�Ĳ�ѯSQL���
		String ParentFlag = "";

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

			// ������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ת������Ϊlist�ṹ
			if (pFlag)
			{
				for (int i = 0; i < userID.length; i++)
				{
					pUserIDs.add(userID[i]);
				}

				if (pUserIDs.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ�ա�");
				}
			}

			// �����ѯ������SQLƬ��
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "�����ѯ����SQLƬ��ʧ��; ");
						}
					}
				}
			}

			EnumWorkingUserIDType enumWorkingUserIDType = null;
			String userType = "";
			if (pFlag) {
				if (enumWorkFlowStatus==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����Ĺ�����״̬��Ϣ�Ƿ�Ϊ�ա�");
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.��¼��� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�ύ������� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.����������ƽ��嵥) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID1;
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ������¼��˴�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ������¼���ͨ�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ���ҽ������ͨ�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�鵵��Ϣ����޸�) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID2;
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.���������ҽ�����˴�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.���������ҽ������ͨ�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�����ϼ������� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.������ԤԼ���� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�������鵵���� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�������������� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�����ѹ黹) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID3;
				}else if(enumWorkFlowStatus == EnumWorkFlowStatus.�γɲ��Ž��������嵥���Ƴ�){
					   enumWorkFlowStatus = EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��;
					   enumWorkingUserIDType=EnumWorkingUserIDType.UserID1;
				}
				userType = " AND " + enumWorkingUserIDType.toString() + " IN (:UserIDs)";
			}
			
			// ����SQL�Ĳ���Դ
			if (pFlag)
			{
				paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue(), Types.INTEGER);
				paramSource.addValue("UserIDs", pUserIDs);
				//int parentFlag = 0;
				
				if (enumArchivesInfoType == EnumArchivesInfoType.�ļ�������)
				{
					ParentFlag = "AND ParentFlag=:ParentFlag";
					paramSource.addValue("ParentFlag", 0, Types.INTEGER);
				}
				else if(enumArchivesInfoType == EnumArchivesInfoType.��������)
				{
					ParentFlag = "AND ParentFlag=:ParentFlag";
					paramSource.addValue("ParentFlag", 1, Types.INTEGER);
				}
//				else if(enumArchivesInfoType == EnumArchivesInfoType.NONE){
//					ParentFlag = "AND ParentFlag<>:ParentFlag";
//					paramSource.addValue("ParentFlag", 2, Types.INTEGER);
//				}
			}

			// ��ѯ�ܼ�¼��
			if (pFlag)
			{
				pErrPos = 3;

				// ���������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_COUNT_By_QueryConditions, archivesInfoWorkingTableName, querySQL.toString(),ParentFlag,userType);
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
							archivesInfoWorkingTableName, querySQL.toString(),ParentFlag,userType);

				}
				// �����ҳ��С=0��ʾ����ҳ
				else
				{
					// ���������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
					// ?�Ķ�̬�������������ж�̬������SQL����ַ���
					localSql = String.format(SQL_SELECT_By_QueryConditions_WithoutPage, archivesInfoWorkingTableName, querySQL.toString(),ParentFlag,userType);
				}
			}

			// ִ�в�ѯ������Ϣ��SQL��䲢���ؽ��
			if (pFlag)
			{
				pErrPos = 5;
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesType));

				// ���ز�ѯ���
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
				}

				// ���پֲ�����
				pArchivesInfos = null;
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
			paramSource = null;
			namedParameterJdbcTemplate = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#findByNBXH(int,
	 * com.orifound.aiim.entity.ArchivesType,
	 * com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (pNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
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

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_ByNBXH, archivesInfoWorkingTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapper(archivesType), pNBXH);

				// ���ز�ѯ���
				if (pArchivesInfos.size() > 0)
				{
					archivesInfo.cloneFrom(pArchivesInfos.get(0));
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

	@Override
	public boolean findMaxSubContentID(ArchivesType archivesType, int parentNBXH, IntegerEx maxSubContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
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

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_MaxSubContentID, archivesInfoWorkingTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				int pMaxContentID = jdbcTemplate.queryForInt(localSql, parentNBXH);

				// ���ز�ѯ���
				maxSubContentID.setValue(pMaxContentID);

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
	public boolean updateSubContentCountAndPageSum(ArchivesType archivesType, int parentNBXH, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_UPDATE_SubContentCountAndPageSum, archivesInfoWorkingTableName);

				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ParentNBXH", parentNBXH, Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(localSql, paramSource);

				// ���پֲ�����
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
	public boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (pNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
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

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_ChildArchivesInfo_ByParentNBXH, archivesInfoWorkingTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapper(archivesType), pNBXH);

				// ���ز�ѯ���
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
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

	@Override
	public boolean batchUpdateParentNBXH(ArchivesType archivesType,final List<ArchivesInfo> archivesInfos,final int userID,final EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 2;
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

			//��鴫��ĸ��µĵ�����Ϣ����
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfos == null || archivesInfos.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����Ҫ���µĵ�����Ϣ���ϷǷ�Ϊ��");
				}

			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 4;
				//UPDATE %1$s SET ParentNBXH=?,UserID1=?,WorkFlowStatus=?,ContentID=?,ContentIDText=?,SubContentID=?,SubContentIDText=?,ArchivesID=? WHERE NBXH=?
				localSql = String.format(SQL_UPDATE_BatchUpdateParentFlag, archivesInfoWorkingTableName);
				BatchPreparedStatementSetter preparedStatementSetter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)throws SQLException {
						ps.setInt(1, archivesInfos.get(i).getParentNBXH());
						ps.setInt(2, userID);
						ps.setInt(3, enumWorkFlowStatus.getEnumValue());
						ps.setInt(4, archivesInfos.get(i).getContentID());
						ps.setString(5, archivesInfos.get(i).getContentIDText());
						ps.setInt(6, archivesInfos.get(i).getSubContentID());
						ps.setString(7, archivesInfos.get(i).getSubContentIDText());
						ps.setString(8, archivesInfos.get(i).getArchivesID());
						ps.setInt(9, archivesInfos.get(i).getNBXH());
					}
					//����ִ�д���
					public int getBatchSize() {
						return archivesInfos.size();
					}
				};
				
				getJdbcTemplate().batchUpdate(localSql,preparedStatementSetter);
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
	public boolean batchDelArchives(ArchivesType archivesType,final List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 2;
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

			// ��鵵�����ڲ�����Ƿ�Ϊ��
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfos == null || archivesInfos.size() <= 0) {
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
				localSql = String.format(SQL_DELETE_ByNBXH, archivesInfoWorkingTableName);
				// localSql = String.format(SQL_DELETE_BatchDleteArchives,
				// "ArchivesInfoWorking_JX14");
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.batchUpdate(localSql,
						new BatchPreparedStatementSetter() {
							public void setValues(PreparedStatement ps, int i)
									throws SQLException {
								ps.setInt(1, archivesInfos.get(i).getNBXH());
							}

							public int getBatchSize() {
								return archivesInfos.size();
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
	public boolean updateWorkFlowUserIDByNBXH(ArchivesType archivesType, int[] pNBXHs, EnumWorkingUserIDType enumWorkingUserIDType, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		String userIDFieldName = "";// �û���ŵľ����ֶ���
		List<Integer> localNBXHs = null;// �ڲ���ż���
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
				if (pNBXHs.length == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ��");
				}
			}

			// ����û�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (userID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����鵵������Ա���û���ŷǷ�Ϊ0");
				}
			}

			// ��鹤����״̬��Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumWorkFlowStatus == EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����Ĺ�����״̬��Ϣ�Ƿ�Ϊ�ա�");
				}
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

			// �����û���ŵľ����ֶ���
			if (pFlag)
			{
				if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID1)
				{
					userIDFieldName = "UserID1";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID2)
				{
					userIDFieldName = "UserID2";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID3)
				{
					userIDFieldName = "UserID3";
				}
			}

			// ת���ڲ���ż���
			if (pFlag)
			{
				// ת������Ϊlist�ṹ
				if (pFlag)
				{
					localNBXHs = new ArrayList<Integer>();
					for (int i = 0; i < pNBXHs.length; i++)
					{
						if (pNBXHs[i] > 0)
						{
							localNBXHs.add(pNBXHs[i]);
						}
					}

					if (localNBXHs.size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��Ч���ڲ������Ϣ�Ƿ�Ϊ�ա�");
					}
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_UPDATE_WorkFlowStatusUserID_ByNBXH =
				// "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE NBXH IN (:NBXH)";
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_UPDATE_WorkFlowStatusUserID_ByNBXH, archivesInfoWorkingTableName, userIDFieldName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue(), Types.INTEGER);
				paramSource.addValue("UserID", userID, Types.INTEGER);
				paramSource.addValue("NBXH", localNBXHs);

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
	public boolean updateWorkFlowUserIDForChild(ArchivesType archivesType, int[] parentNBXHs, EnumWorkingUserIDType enumWorkingUserIDType, int userID,
			EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		String userIDFieldName = "";// �û���ŵľ����ֶ���
		ArrayList<Integer> localParentNBXHs = null;// ���������ڲ���ż���
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
				if (parentNBXHs.length <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��������������ڲ���ŷǷ�Ϊ0");
				}
			}

			// ����û�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (userID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����鵵������Ա���û���ŷǷ�Ϊ0");
				}
			}

			// ��鹤����״̬��Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumWorkFlowStatus == EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����Ĺ�����״̬��Ϣ�Ƿ�Ϊ�ա�");
				}
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

			// �����û���ŵľ����ֶ���
			if (pFlag)
			{
				if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID1)
				{
					userIDFieldName = "UserID1";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID2)
				{
					userIDFieldName = "UserID2";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID3)
				{
					userIDFieldName = "UserID3";
				}
			}

			// ת���ڲ���ż���
			if (pFlag)
			{
				// ת������Ϊlist�ṹ
				if (pFlag)
				{
					localParentNBXHs = new ArrayList<Integer>();
					for (int i = 0; i < parentNBXHs.length; i++)
					{
						if (parentNBXHs[i] > 0)
						{
							localParentNBXHs.add(parentNBXHs[i]);
						}
					}

					if (localParentNBXHs.size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("��Ч�ĵ������������ڲ������Ϣ�Ƿ�Ϊ�ա�");
					}
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_UPDATE_WorkFlowStatusUserID_ByParentNBXH =
				// "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE ParentNBXH=:ParentNBXH";
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_UPDATE_WorkFlowStatusUserID_ByParentNBXH, archivesInfoWorkingTableName, userIDFieldName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue(), Types.INTEGER);
				paramSource.addValue("UserID", userID, Types.INTEGER);
				paramSource.addValue("ParentNBXH", localParentNBXHs);

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
	public boolean deleteParentAndChild(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesInfo.getNBXH()<= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
				}
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

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";
				
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_DELETE_ParentAndChild_ByNBXH, archivesInfoWorkingTableName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", archivesInfo.getNBXH(),Types.INTEGER);

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
	public boolean batchUpdateParentNBXH(int parentNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		String localSql = "";// �����ύ��SQL���

		List<Integer> NBXHS = new ArrayList<Integer>();
		
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
				if (parentNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
				}
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

			//��鴫��ĸ��µĵ�����Ϣ����
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfos == null || archivesInfos.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����Ҫ���µĵ�����Ϣ���ϷǷ�Ϊ��");
				}else{
					for (ArchivesInfo archivesInfo2 : archivesInfos) {
						NBXHS.add(archivesInfo2.getNBXH());
					}
				}

			}
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";
				
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_UPDATE_BatchUpdateParentNBXH, archivesInfoWorkingTableName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("parentNBXH", parentNBXH);
				paramSource.addValue("NBXHS", NBXHS);

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
	public boolean statArchivesInfoCountByWorkFlowStatus(int [] userIDs, ArchivesType pArchivesType,EnumWorkFlowStatus enumWorkFlowStatus,String userType,IntegerEx count, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// �����ύ��SQL���
		
		ArchivesType archivesType = null;
		List<Integer> users = new ArrayList<Integer>();
		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else if(userIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else{
					for (Integer userId : userIDs) {
						users.add(userId);
					}
				}
			}
			
			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (pArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}else{
					archivesType = pArchivesType;
				}
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			/*if (pFlag)
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
			}*/
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatus = "SELECT COUNT(*) FROM ArchivesInfoWorking_JX14 WHERE WorkFlowStatus =? AND UserID1 IN (?)"
				//String sql = "SELECT COUNT(*) FROM ArchivesInfoWorking_JX14 WHERE WorkFlowStatus =:WorkFlowStatus AND UserID1 IN (:userIDs)";
				// ������������̬������SQL�����ȥ
				//localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser, archivesInfoWorkingTableName);
				localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser, "ArchivesInfoWorking_JX",userType);
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				paramSource.addValue("userIDs", users);

				count.setValue(namedParameterJdbcTemplate.queryForInt(localSql, paramSource));
				
				// ���پֲ�����
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
	public boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,DataPageInfo dataPageInfo,String userType, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// �����ύ��SQL���

		List<Integer> users = new ArrayList<Integer>();
		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else if(userIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else{
					for (Integer userId : userIDs) {
						users.add(userId);
					}
				}
			}
			
			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}else if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣ���δ��ֵ");
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
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				/*String SQL_SELECT_ArchivesInfos_By_WorkFlowStatus_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) AND NBXH < "
				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) ORDER BY NBXH DESC) AS T) "
				+ "ORDER BY NBXH DESC";*/
				//"SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs)";
				
				// ������������̬������SQL�����ȥ
				localSql = String.format(SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndUser_WithPage,dataPageInfo.getPageSize(), archivesInfoWorkingTableName,(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize(),userType);
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				paramSource.addValue("userIDs", users);

				pErrPos = 3;
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesType));
				
				//ͳ��������
				localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser, archivesInfoWorkingTableName,userType);
				int rowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);

				archivesInfos.addAll(pArchivesInfos);
				dataPageInfo.setRowCount(rowCount);
				// ���پֲ�����
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
	public boolean findArchivesInfosByEnumWorkFlowStatus(ArchivesType archivesType, int[] deptIDs, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// �����ύ��SQL���

		List<Integer> depts = new ArrayList<Integer>();
		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (deptIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else if(deptIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else{
					for (Integer userId : deptIDs) {
						depts.add(userId);
					}
				}
			}
			
			// ��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}else if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣ���δ��ֵ");
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
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				/*String SQL_SELECT_ArchivesInfos_By_WorkFlowStatus_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) AND NBXH < "
				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) ORDER BY NBXH DESC) AS T) "
				+ "ORDER BY NBXH DESC";*/
				//"SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs)";
				
				// ������������̬������SQL�����ȥ
				localSql = String.format(SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndDept_WithPage,dataPageInfo.getPageSize(), archivesInfoWorkingTableName,(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize());
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				paramSource.addValue("deptIDs", depts);

				pErrPos = 3;
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesType));
				
				//ͳ��������
				localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndDept, archivesInfoWorkingTableName);
				int rowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);

				archivesInfos.addAll(pArchivesInfos);
				dataPageInfo.setRowCount(rowCount);
				// ���پֲ�����
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
	public boolean updateArchivesBackReason(int nbxh,ArchivesType archivesType, String backReason, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// �����ύ��SQL���

		List<Integer> depts = new ArrayList<Integer>();
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}else if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣ���δ��ֵ");
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
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				/*String SQL_SELECT_ArchivesInfos_By_WorkFlowStatus_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) AND NBXH < "
				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) ORDER BY NBXH DESC) AS T) "
				+ "ORDER BY NBXH DESC";*/
				//"SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs)";
				
				String sql = "UPDATE %1$s SET SendBackReason=? WHERE NBXH=?";

				// ������������̬������SQL�����ȥ
				localSql = String.format(sql,archivesInfoWorkingTableName);
				
				getJdbcTemplate().update(localSql,backReason,nbxh);
				// ���پֲ�����
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
	public boolean updateArchivesInfoFixedFlag(ArchivesType archivesType, int NBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
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

			if (pFlag) {
				if (NBXH == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("Ҫ�޸��ĵ����ڲ���ŷǷ�Ϊ0");
				}
			}
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				//private static final String SQL_UPDATE_updateArchivesInfoFixedFlag = "UPDATE %1$s SET FixedFlag=? WHERE NBXH=?";
				
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_UPDATE_updateArchivesInfoFixedFlag, archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, true,NBXH);
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
	public boolean updateContentIDText(int contentID, String contentIDText, int NBXH, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
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

			if (pFlag) {
				if (NBXH == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("Ҫ�޸��ĵ����ڲ���ŷǷ�Ϊ0");
				}
			}
			
			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				//private static final String SQL_UPDATE_updateArchivesInfoFixedFlag = "UPDATE %1$s SET FixedFlag=? WHERE NBXH=?";
				
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from ?�Ķ�̬�������������ж�̬������SQL����ַ���
				String sql = "UPDATE %1$s SET ContentID=?,ContentIDText=? WHERE NBXH=?";
				localSql = String.format(sql , archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, contentID,contentIDText,NBXH);
				
				sql = "UPDATE %1$s SET ContentID=?,ContentIDText=? WHERE ParentNBXH=?";
				localSql = String.format(sql , archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, contentID,contentIDText,NBXH);
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
	public boolean updateSubArchivesID(int parentNBXH, ArchivesType archivesType, ErrInfo pErrInfo) {
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
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// ���浱ǰ����ĵ����鵵������
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵������).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate nJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				SqlParameterSource parameterSource = new MapSqlParameterSource()
				                                         .addValue("NBXH", parentNBXH);
				
				String sql = "UPDATE T1 SET ContentID=T2.ContentID,ContentIDText=T2.ContentIDText FROM %1$s T1,%1$s T2 WHERE T1.ParentNBXH=:NBXH AND T2.NBXH=:NBXH AND T1.ArchivesID IS NULL";
				sql = String.format(sql, archivesInfoWorkingTableName);
				nJdbcTemplate.update(sql, parameterSource);
				
				sql = "UPDATE %1$s SET ArchivesID=%2$s WHERE ParentNBXH=:NBXH AND ArchivesID IS NULL";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getSubArchivesIDExpression());
				nJdbcTemplate.update(sql, parameterSource);
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
