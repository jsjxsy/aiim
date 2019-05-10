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
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesInfoSavedDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����鵵��Ϣ��DAOʵ���� ��SQL Serverƽ̨��
 *
 */
public class ArchivesInfoSavedDaoImpl extends JdbcDaoSupport implements IArchivesInfoSavedDao {
	
	/**
	 * ���캯��
	 */
	public ArchivesInfoSavedDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesInfoSavedDaoImpl(DataSource dataSource) {
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
			//���ü���ǰ�����������
			archivesInfo.setTotalYears(rs.getInt("TotalYears"));
			archivesInfo.setStoreAddressFullName(rs.getString("StoreAddressFullName"));
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
	 * ��������->��ټ��� 	��ҳ��ѯ���ڵ�����Ϣ��SQL���(���˾����ļ�)
	 * %1$s ������Ϣ�鵵����
	 * %2$s �����γɲ�������
	 */
	private final String SQL_SELECT_SaveDestroyAppraisal_WithPage = "SELECT * FROM( SELECT t.* ,rp.TotalYears, '' StoreAddressFullName,ROW_NUMBER() OVER(ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC, t.ArchivesID ASC) RowNumber FROM %1$s t,DD_RetentionPeriod rp WHERE t.ParentNBXH=0 AND DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s) t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC";

	/**
	 * ��������->��ټ���	 ��ѯ���й��ڵ�����Ϣ��SQL���(���˾����ļ�)
	 * %1$s ������Ϣ�鵵����
	 * %2$s �����γɲ�������
	 */
	private final String SQL_SELECT_SaveDestroyAppraisal_WithNoPage = "SELECT t.*,sai.StoreAddressFullName, 0 TotalYears FROM %1$s t,DD_RetentionPeriod rp,StoreroomArchivesInfo srai,StoreAddressInfo sai WHERE srai.ArchivesTypeID=t.ArchivesTypeID AND srai.NBXH=t.NBXH AND srai.ArchivesBoxBarcode=sai.ArchivesBoxBarcode AND DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s  ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC, t.ArchivesID ASC";
	
	/**
	 * ��������->��ټ���	��ѯ���ڵ���������SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s �����γɲ�������
	 */
	private final String SQL_SELECT_COUNT_SaveDestroyAppraisal = "SELECT COUNT(1) FROM %1$s t,DD_RetentionPeriod rp WHERE DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s ";
	
	/**
	 * ��������->��ټ���	�������µ�����Ϣ�ı������� ��SQL���
	 * %1$s ���µı���
	 */
	private final String SQL_BATCHUPDATE_RetentionPeriod = "UPDATE %1$s SET RetentionPeriodID=r.ID, RetentionPeriodText=r.Name FROM DD_RetentionPeriod r WHERE r.ID=? AND %1$s.NBXH=? AND %1$s.ArchivesTypeID=? ";
	
	/**
	 * ��������->��ټ���	�������ٵ��� ��SQL���
	 * %1$s ���µı���
	 */
	private final String SQL_BATCHUPDATE_DESTORY = "UPDATE %1$s SET DestroyFlag=1 WHERE NBXH=? AND ArchivesTypeID=?";
	

	/**
<<<<<<< .mine
	 * ��������->��ټ���	�����޸Ŀⷿ������Ϣ��Ĺݲ�״̬	��SQL���
	 */
	private final String SQL_BATCHUPDATE_StoreroomArchivesInfo = "UPDATE StoreroomArchivesInfo SET StoreStatus=4 WHERE NBXH=? AND ArchivesTypeID=?";
	
	/**
=======
	 * ��ѯָ���ڲ���ŵĵ�����Ϣ��SQL���
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?";

	/**
>>>>>>> .r815
	 * ��������->���ż��� 	��ҳ��ѯΪ���ŵĵ�����Ϣ��SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s �Ƿ���Ҫ�������ż�����ϸ��
	 * %3$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_PublicAppraisal_WithPage = "SELECT * FROM ( SELECT t.*,ROW_NUMBER() OVER(ORDER BY t.NBXH DESC) RowNumber FROM %1$s t %2$s %3$s )t2 WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY NBXH DESC";
	
	/**
	 * ��������->���ż���	��ѯ����������SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s �Ƿ���Ҫ�������ż�����ϸ��
	 * %3$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_COUNT_PublicAppraisal = "SELECT COUNT(DISTINCT t.NBXH) FROM %1$s t %2$s %3$s";
	
	/**
	 * ��������->���ż���	�������µ�����Ϣ�Ŀ��ű�־ ��SQL���
	 * %1$s ���µı���
	 * %2$s ���ű�־
	 */
	private final String SQL_BATCHUPDATE_PublicAppraisal = "UPDATE %1$s SET PublicFlag=%2$s WHERE NBXH=? ";
	
	/**
	 * ��������->�������� 	��ҳ��ѯΪ�����ĵ�����Ϣ��SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s �Ƿ���Ҫ�������ż�����ϸ��
	 * %3$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_OpenAppraisal_WithPage = "SELECT * FROM( SELECT t.*,ROW_NUMBER() OVER(ORDER BY t.NBXH DESC) RowNumber FROM %1$s t %2$s %3$s )t1 WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY NBXH DESC";
	
	/**
	 * ��������->��������	��ѯ����������SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s �Ƿ���Ҫ�������ż�����ϸ��
	 * %3$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_COUNT_OpenAppraisal = "select COUNT(DISTINCT t.NBXH) FROM %1$s t %2$s %3$s";
	
	/**
	 * UPDATE %1$s SET SecrecyID=%2$s WHERE NBXH=? 
	 * 	%2$s ȡ������ʱ��(select distinct OldSecrecyID from AppraisalPublicDetails p,%1$s t
			where p.NBXH=t.NBXH and p.OldSecrecyID>1 and p.NewSecrecyID=1)
	 	
	 * ��������->��������	������ԭ������Ϣ���ܼ���Ϣ ��SQL���
	 * %1$s ���µı���
	 * %2$s �����ܼ�
	 * %3$s �����ܼ��ı�
	 */
	private final String SQL_BATCHUPDATE_OpenAppraisal = "UPDATE %1$s SET SecrecyID=%2$s,SecrecyText='%3$s' WHERE NBXH=? ";
	
	/**
	 * ��������->���ؼ���	��ҳ��ѯΪ���ŵĵ�����Ϣ��SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_ControlArea_WithPage = "SELECT * FROM (SELECT *,ROW_NUMBER() OVER(ORDER BY NBXH DESC) RowNumber FROM %1$s WHERE PublicFlag=0 %2$s )t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY NBXH DESC";
	
	/**
	 * ��������->���ؼ���	��ѯ����������SQL���
	 * %1$s ������Ϣ�鵵����
	 * %2$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_COUNT_ControlArea = "select COUNT(DISTINCT NBXH) FROM %1$s WHERE PublicFlag=0 %2$s ";
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#batchDelArchives(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean batchDelArchives(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#batchUpdateParentFlag(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean batchUpdateParentFlag(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#batchUpdateParentNBXH(int, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean batchUpdateParentNBXH(int parentNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#delete(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#deleteParentAndChild(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteParentAndChild(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#findByNBXH(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */

	@Override	
	public boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfosavedTableName = "";
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
					// ���浱ǰ����ĵ����鵵��Ϣ��
					archivesInfosavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_ByNBXH, archivesInfosavedTableName);

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
	
	/**
	 * ���ݰ�����ڲ���Ų��������о����ļ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_ChildArchivesInfo_ByParentNBXH = "SELECT * FROM %1$s WHERE ParentNBXH=? ORDER BY SubContentID ASC";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#findChildrenByNBXH(int, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String archivesInfoSavedTableName = "";
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
					// ���浱ǰ����ĵ����鵵��Ϣ��
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}

			// ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				// ������������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_ChildArchivesInfo_ByParentNBXH, archivesInfoSavedTableName);

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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#findMaxSubContentID(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.IntegerEx, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findMaxSubContentID(ArchivesType archivesType, int parentNBXH, IntegerEx maxSubContentID, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#queryClassified(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
/*
	@Override
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes, List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = "";	// ���嵵�������Ӧ�ĵ�����Ϣ��������
		StringBuilder querySQL = new StringBuilder();// ��ѯ������SQLƬ��
		String localSql = "";	// �����ύ�Ĳ�ѯSQL���
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL�Ĳ���Դ����
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		List<Integer> secrecyIDs = new ArrayList<Integer>(); //�û��ɷ��ʵ��ܼ�
		List<Integer> rolesIDs = new ArrayList<Integer>();//�û�������ɫ		
		
		try
		{
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//���userInfo�����Ƿ�ֵ
			if(userInfo == null){
				pFlag = false;
				pErrInfo.getContent().append("�û�δ��ʼ����");
			}else{
				secrecyIDs = new ArrayList<Integer>(userInfo.getArchivesSecrecys().keySet());
				if(secrecyIDs.size()==0){//���û��ֵʱ�����б����һ��0���Ա���SQL����
					secrecyIDs.add(new Integer(0));
				}
System.out.println("secrecyIDs" + userInfo.getArchivesSecrecys().keySet().toString());
				
				//��ȡ��ɫ��Ϣ
				for(int i=0;i<userInfo.getRolesIDs().length;i++){
					rolesIDs.add(userInfo.getRolesIDs()[i]);
				}
				//��û�н�ɫ��Ϣ����Ĭ��ֵ0����ֹSQL�쳣
				if(rolesIDs.size()==0){
					rolesIDs.add(0);
				}
				
			}

			// ��鵵�������Ƿ��и�ֵ			
			if (archivesTypes.get(0) == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣδ��ʼ����");
			}
			

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesTypes.get(0), pErrInfo) == false){
					pFlag = false;
				}else{
					// ���浱ǰ����ĵ����鵵��Ϣ��
<<<<<<< .mine
					archivesInfoWorkingTableName = archivesTypes.get(0).getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
=======
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
>>>>>>> .r765
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
			
			// ����SQL�Ĳ���Դ
			if (pFlag)
			{
				paramSource.addValue("ArchivesTypeID", archivesTypes.get(0).getID(), Types.INTEGER);
				paramSource.addValue("SecrecyID", secrecyIDs);
				paramSource.addValue("RolesID",rolesIDs );
			}
		
			
//			 String SQL_SELECT_COUNT_By_Classified1 = "SELECT COUNT(NBXH) FROM %1$s"
//				+ " WHERE ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) AND  ParentNBXH=0 %2$s";
			 
				//%1$s:���ݿ����;  %2$s����ѯ����
				String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM  " +
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) ) AS BaseTable ";

			// ��ѯ�ܼ�¼��
			if (pFlag)
			{
				pErrPos = 3;

				// ���������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, querySQL.toString());
				
				long beginTime = new Date().getTime();	
				// ִ�в�ѯ�ܼ�¼����SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
				
				System.out.println("count_SQL use Time:" + (new Date().getTime()-beginTime)+"ms");
				// ���÷�ҳ�����е��ܼ�¼��
				dataPageInfo.setRowCount(pRowCount);
			}


//			String SQL_SELECT_By_Classified_WithPage1 = "SELECT TOP %1$s * FROM %3$s"
//				+ " WHERE ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) AND ParentNBXH=0 %4$s AND NBXH < "
//				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %2$s NBXH FROM %3$s"
//				+ " WHERE ArchivesTypeID=:ArchivesTypeID  AND SecrecyID IN (:SecrecyID) AND ParentNBXH=0 %4$s ORDER BY NBXH DESC) AS T) "
//				+ "ORDER BY NBXH DESC";
//			

			//%1$s:���ݿ����;  %2$s:��ѯ����(��and��ͷ )��%3$s��ҳ��С��<br>%4$s���Ѿ���ȡ���ļ�¼����<br> 
			String SQL_SELECT_By_Classified_WithPage =  "SELECT TOP %3$s * FROM  %1$s WHERE ParentNBXH=0 AND NBXH IN "
				+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
				+
				" AND NBXH > (SELECT ISNULL(MAX(NBXH),0) FROM (SELECT TOP %4$s NBXH FROM %1$s WHERE NBXH IN "
					+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
					+
				" ORDER BY NBXH ASC) AS T) "
				+ "ORDER BY NBXH ASC";			

			// �����ѯ������Ϣ��SQL���
			if (pFlag){
				pErrPos = 4;
				// ����ҳ��С�����������Լ���ѯ������̬������SQL�����ȥ��ע��jdbc��֧��select top ? from
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_By_Classified_WithPage,archivesInfoSavedTableName, querySQL.toString(), dataPageInfo.getPageSize(), (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize());
System.out.println("localSql:"+ localSql);
			}

			// ִ�в�ѯ������Ϣ��SQL��䲢���ؽ��
			if (pFlag){
				pErrPos = 5;
				
long beginTime = new Date().getTime();				
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesTypes.get(0)));
System.out.println("find_SQL use Time:" + (new Date().getTime()-beginTime)+"ms");
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
	*/
	
	
	/**
	 * �����ѯ<br>
	 * ���ݵ�������+�û��ɷ��ʵ��ܼ�+��ѯ����+�Ѽ���ʹ�÷�Χ
	 * @param archivesType
	 * @param archivesInfoQueryConditions_SQL �����ѯ����
	 * @param dataPageInfo
	 * @param archivesInfos
	 * @param pErrInfo
	 * @return
	 */
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes, String archivesInfoQueryConditions_SQL, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = "";	// ���嵵�������Ӧ�ĵ�����Ϣ��������
		String localSql = "";	// �����ύ�Ĳ�ѯSQL���
		MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL�Ĳ���Դ����
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		List<Integer> secrecyIDs = new ArrayList<Integer>(); 
		List<Integer> rolesIDs = new ArrayList<Integer>();
		
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
				if (archivesTypes.get(0) == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}
			}
			
			//���userInfo�����Ƿ�ֵ
			if(userInfo == null){
				pFlag = false;
				pErrInfo.getContent().append("�û�δ��ʼ��");
			}else{
				//��ȡ�ܼ��ͽ�ɫ��Ϣ
				//�ܼ�
				secrecyIDs = new ArrayList<Integer>(userInfo.getArchivesSecrecys().keySet());
				if(secrecyIDs.size()==0){//���û��ֵʱ�����б����һ��0���Ա���SQL����
						secrecyIDs.add(0);
				}
				//��ȡ��ɫ��Ϣ
				for(int i=0;i<userInfo.getRolesIDs().length;i++){
					rolesIDs.add(userInfo.getRolesIDs()[i]);
				}
				//��û�н�ɫ��Ϣ����Ĭ��ֵ0����ֹSQL�쳣
				if(rolesIDs.size()==0){
					rolesIDs.add(0);
				}				
			}

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesTypes.get(0), pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// ���浱ǰ����ĵ����鵵������

					archivesInfoSavedTableName = archivesTypes.get(0).getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();


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

				
			// ����SQL�Ĳ���Դ
			if (pFlag)
			{
				List<Integer> types = new ArrayList<Integer>();
				for (ArchivesType item : archivesTypes) {
					types.add(item.getID());
				}
				
				paramSource.addValue("ArchivesTypeID", types);
				paramSource.addValue("SecrecyID", secrecyIDs);
				paramSource.addValue("RolesID",rolesIDs);
			}

			
	
			//%1$s:���ݿ����;  %2$s����ѯ����
			String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM  " +
				"(" +
					"SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID IN (:ArchivesTypeID) AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID IN(:RolesID) AND D.ID=B.ID))" +
				" ) AS BaseTable ";
			

			//%1$s:���ݿ����;  %2$s:��ѯ����(��and��ͷ )��%3$s����ʼֵ��<br>%4$s������ֵ<br> 
			String SQL_SELECT_By_Classified_WithPage =" SELECT * FROM ("+
						"SELECT * ,ROW_NUMBER() over(order by NBXH) AS RowNum FROM %1$s WHERE NBXH IN  "+
							"(" +
								"SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s" +
								" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
								" UNION " +
								"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) AND SecrecyID IN (:SecrecyID) %2$s" +
								" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
								" UNION " +
								"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID IN (:ArchivesTypeID) %2$s AND EXISTS  (" +
								"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID IN (:ArchivesTypeID) AND A.NBXH=B.NBXH " +
								"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID IN(:RolesID) AND D.ID=B.ID))" +
							")"+
						"  )TEMPTABLE WHERE RowNum BETWEEN %3$s AND %4$s ";
			
			
			
		/*	
=======
			// ��ѯ�ܼ�¼��
			if (pFlag)
			{
				pErrPos = 3;
				// ?�Ķ�̬�������������ж�̬������SQL����ַ���
				localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, archivesInfoQueryConditions_SQL);

long beginTime = new Date().getTime();				
				// ִ�в�ѯ�ܼ�¼����SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
System.out.println("find_SQL use Time:" + (new Date().getTime()-beginTime)+"ms");	

				// ���÷�ҳ�����е��ܼ�¼��
				dataPageInfo.setRowCount(pRowCount);
			}

>>>>>>> .r765
			//%1$s:���ݿ����;  %2$s:��ѯ����(��and��ͷ )��%3$s��ҳ��С��<br>%4$s���Ѿ���ȡ���ļ�¼����<br> 
			String SQL_SELECT_By_Classified_WithPage_TOP =  "SELECT TOP %3$s * FROM  %1$s WHERE ParentNBXH=0 AND NBXH IN "
				+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
				+
				" AND NBXH > (SELECT ISNULL(MAX(NBXH),0) FROM (SELECT TOP %4$s NBXH FROM %1$s WHERE NBXH IN "
					+
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) )  "
					+
				" ORDER BY NBXH ASC) AS T) "
				+ "ORDER BY NBXH ASC";
					
			*/
			
			// ��ѯ�ܼ�¼��
			if (pFlag)
			{
				pErrPos = 3;			
				localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, archivesInfoQueryConditions_SQL);
long beginTime = new Date().getTime();				
				// ִ�в�ѯ�ܼ�¼����SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
System.out.println("queryForInt use Time:" + (new Date().getTime()-beginTime)+"ms");	

				// ���÷�ҳ�����е��ܼ�¼��
				dataPageInfo.setRowCount(pRowCount);
			}

			
			
			
			// �����ѯ������Ϣ��SQL���
			if (pFlag)
			{
				pErrPos = 4;			

				// ?�Ķ�̬�������������ж�̬������SQL����ַ���(1-1)*5+1 AND 1*5
				localSql = String.format(SQL_SELECT_By_Classified_WithPage,archivesInfoSavedTableName, archivesInfoQueryConditions_SQL, (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize()+1, dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());

			}

			// ִ�в�ѯ������Ϣ��SQL��䲢���ؽ��
			if (pFlag)
			{
				pErrPos = 5;
				System.out.println("localSql-2: " + localSql);
long beginTime1 = new Date().getTime();	
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesTypes.get(0)));
System.out.println("query Info use Time:" + (new Date().getTime()-beginTime1)+"ms");	
				// ���ز�ѯ���
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
				}
				// ���پֲ�����
				pArchivesInfos = null;
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
	
	@Override
	public boolean queryCountByClassified(UserInfo userInfo,ArchivesType archivesType,StringBuilder querySQL,IntegerEx countNum,ErrInfo pErrInfo){
	
			boolean pFlag = true;
			int pErrPos = 0;
			Throwable throwable = new Throwable();
			String archivesInfoSavedTableName = "";	// ���嵵�������Ӧ�ĵ�����Ϣ��������			
			String localSql = "";	// �����ύ�Ĳ�ѯSQL���
			MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL�Ĳ���Դ����
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			List<Integer> secrecyIDs = new ArrayList<Integer>(); 
			List<Integer> rolesIDs = new ArrayList<Integer>();//�û�������ɫ�б� 
					
			try
			{
				pErrPos = 1;
				// ���JDBC����Դ�Ƿ���ȷ����ע��
				if (CheckDataSourceInjection(pErrInfo) == false)
				{
					pFlag = false;
				}

				
				// ��鵵�������Ƿ��и�ֵ
				if (pFlag)
				{pErrPos = 2;
					if (archivesType == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("����������Ϣδ��ʼ����");
					}
				}
				
				if (pFlag) {
					pErrPos = 3;
					//���userInfo�����Ƿ�ֵ
					if(userInfo == null){
						pFlag = false;
						pErrInfo.getContent().append("�û�δ��ʼ��");
					}
				}
				
				if (pFlag) {
					pErrPos = 4;
					//��ȡ�û��ɷ��ʵ��ܼ���Ϣ
					secrecyIDs = new ArrayList<Integer>(userInfo.getArchivesSecrecys().keySet());
					if(secrecyIDs.size()==0){//���û��ֵʱ�����б����һ��0���Ա���SQL����
						secrecyIDs.add(new Integer(0));
					}
					
					//��ȡ�û���ɫ��Ϣ
					rolesIDs = new ArrayList<Integer>();
					rolesIDs.add(0);//Ĭ�ϼ�һ��0����֤���û������ڽ�ɫʱ�����SQL�쳣
					for(int i = 0;i<userInfo.getRolesIDs().length;i++){
						rolesIDs.add(userInfo.getRolesIDs()[i]);
					}
				}

				
				// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
				if (pFlag){
					pErrPos = 4;
					if (checkTableName(archivesType, pErrInfo) == false){
						pFlag = false;
					}else{
						// ���浱ǰ����ĵ����鵵������
						archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
					}
				}
					
				pErrPos = 5;
				// ����SQL�Ĳ���Դ
				if (pFlag)
				{
					paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
					paramSource.addValue("SecrecyID", secrecyIDs);	
					paramSource.addValue("RolesID", rolesIDs);
				}
				

			   
//			   String SQL_SELECT_COUNT_By_Classified1 = "SELECT COUNT(NBXH) FROM (SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
//				" UNION  " +
//				"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
//				" UNION  " +
//				"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
//				"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE A.NBXH=B.NBXH" +
//				"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
//				"   ) ) AS BaseTable ";
			 //%1$s:���ݿ����;  %2$s:��ѯ����(��and��ͷ )
//				String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM (SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
//				" UNION  " +
//				"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
//				" UNION  " +
//				"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
//				"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
//				"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
//				"   ) ) AS BaseTable ";
			   
				//%1$s:���ݿ����;  %2$s����ѯ����
				String SQL_SELECT_COUNT_By_Classified = "SELECT COUNT(NBXH) FROM  " +
					"(SELECT NBXH FROM %1$s WHERE PublicFlag=1 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"SELECT NBXH FROM %1$s  WHERE PublicFlag=0 AND ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID AND SecrecyID IN (:SecrecyID) %2$s" +
					" AND NBXH NOT IN (SELECT NBXH FROM AppraisalUseScopesDetails) "+
					" UNION " +
					"  SELECT NBXH FROM %1$s A where ParentNBXH=0 AND ArchivesTypeID=:ArchivesTypeID %2$s AND EXISTS  (" +
					"  SELECT NBXH FROM AppraisalUseScopesDetails B WHERE  ArchivesTypeID=:ArchivesTypeID AND A.NBXH=B.NBXH " +
					"  AND EXISTS ( SELECT * FROM AppraisalUseScopes_Roles D WHERE D.RolesID in(:RolesID) and D.ID=B.ID)" +
					"   ) ) AS BaseTable ";
			   
				// ��ѯ�ܼ�¼��
				if (pFlag){
					pErrPos = 6;					
					// ?�Ķ�̬�������������ж�̬������SQL����ַ���
					localSql = String.format(SQL_SELECT_COUNT_By_Classified, archivesInfoSavedTableName, querySQL);
System.out.println("localSql:" + localSql);
					// ִ�в�ѯ�ܼ�¼����SQL
					int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);	
System.out.println("ͨ��NBXH��ȡ�����"+ archivesType.getName()+"��������"+pRowCount);
					countNum.setValue(pRowCount);					
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
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#save(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType, int NBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
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
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "INSERT INTO %1$s SELECT * FROM %2$s T1 WHERE T1.NBXH=?";
				sql = String.format(sql, archivesInfoSavedTableName,archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,NBXH);
				
				sql = "INSERT INTO %1$s SELECT * FROM %2$s T1 WHERE T1.ParentNBXH=?";
				sql = String.format(sql, archivesInfoSavedTableName,archivesInfoWorkingTableName);
				getJdbcTemplate().update(sql,NBXH);
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#update(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoSavedDao#updateSubContentCountAndPageSum(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateSubContentCountAndPageSum(ArchivesType archivesType, int parentNBXH, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
////////////////// ���߷���   ///////////////////////////	
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
			//���û�����ݾͷ��ؿմ�
			if(archivesInfoQueryConditions== null ){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else if(archivesInfoQueryConditions.size()==0){
				System.out.println("archivesInfoQueryConditions--size=0;");
				return true;
			}else{
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
	public boolean queryClassifiedForSaveDestroyAppraisal(ArchivesType archivesType, int formationDepartmentID,
										DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				//�����鵵��Ϣ����
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				
				String formationDepartmentIDParam = "";
				if(formationDepartmentID >= 1) {
					formationDepartmentIDParam = "AND t.FormationDepartmentID=:FormationDepartmentID";
					paramSource.addValue("FormationDepartmentID", formationDepartmentID);
				}
				
				//�������ñ������޲�����
				paramSource.addValue("RetentionPeriodID", SystemInitializer.getInstance().getForeverRetentionPeriod().getID(),Types.INTEGER);
				paramSource.addValue("ArchivesTypeID", archivesType.getID(),Types.INTEGER);
				
//				SELECT t.* FROM %1$s t,DD_RetentionPeriod rp WHERE DATEDIFF(YEAR,t.SaveDate,GETDATE())>rp.TotalYears AND t.RetentionPeriodID=rp.ID AND t.RetentionPeriodID<>:RetentionPeriodID AND t.ArchivesTypeID=:ArchivesTypeID AND t.DestroyFlag=0 %2$s  ORDER BY t.FormationDepartmentID ASC, t.SaveDate ASC
				
				List<ArchivesInfo> pArchivesInfos = null;
				if(dataPageInfo == null || dataPageInfo.getPageSize()<=0) {	//��֧�ַ�ҳ��ѯ
					
					pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_SaveDestroyAppraisal_WithNoPage, archivesInfoSavedTableName, formationDepartmentIDParam), paramSource, new ArchivesInfoMapperOther(archivesType));
					
					System.out.println("��ټ�����ѯ��"+String.format(SQL_SELECT_SaveDestroyAppraisal_WithNoPage, archivesInfoSavedTableName, formationDepartmentIDParam));
				
				} else {	//֧�ַ�ҳ��ѯ
					
					//��ѯ���ڵ���������
					int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_SaveDestroyAppraisal, archivesInfoSavedTableName, formationDepartmentIDParam), paramSource);
					dataPageInfo.setRowCount(count);
					
					//��ҳ����
					paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
					paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
					//��ҳ��ѯ���ڵ���
					pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_SaveDestroyAppraisal_WithPage, archivesInfoSavedTableName, formationDepartmentIDParam), paramSource, new ArchivesInfoMapperOther(archivesType));
				
					System.out.println("��ټ���->��ѯ���ڵ�����"+String.format(SQL_SELECT_SaveDestroyAppraisal_WithPage, archivesInfoSavedTableName, formationDepartmentIDParam));
				}
				
				//�ж��Ƿ��м�¼��
				if (pArchivesInfos.size() >= 1) {
					archivesInfos.addAll(pArchivesInfos);
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
	public boolean updateBatchRetentionPeriod(ArchivesType archivesType, final Map<Integer[], Integer> saveArchives, final List<Integer[]> destoryArchives, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = null;
		
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�����鵵��Ϣ����
			archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				BatchPreparedStatementSetter setter = null;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//�ж��Ƿ�����Ҫ�ӳ��������޵ĵ���
				if(saveArchives !=null && saveArchives.keySet().size()>=1 && saveArchives.values().size()>=1) {
					
					setter = new BatchPreparedStatementSetter() {
						List<Integer[]> listInteger = new ArrayList<Integer[]>(saveArchives.keySet());
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, saveArchives.get(listInteger.get(i)));
							ps.setInt(2, listInteger.get(i)[1]);
							ps.setInt(3, listInteger.get(i)[0]);
						}

						@Override
						public int getBatchSize() {
							return saveArchives.keySet().size();
						}
					};
					//�������� �����ı�������
					jdbcTemplate.batchUpdate(String.format(SQL_BATCHUPDATE_RetentionPeriod, archivesInfoSavedTableName), setter);
				}
				
				//�ж��Ƿ���� ������������
				if(destoryArchives!=null && destoryArchives.size()>=1) {
					setter = new BatchPreparedStatementSetter() {
						
						@Override
						public void setValues(PreparedStatement ps, int i) throws SQLException {
							ps.setInt(1, destoryArchives.get(i)[1]);
							ps.setInt(2, destoryArchives.get(i)[0]);
						}

						@Override
						public int getBatchSize() {
							return destoryArchives.size();
						}
					};
					//�������µ������ٱ�־
					//UPDATE %1$s SET DestroyFlag=1 WHERE NBXH=? AND ArchivesTypeID=?
					jdbcTemplate.batchUpdate(String.format(SQL_BATCHUPDATE_DESTORY, archivesInfoSavedTableName), setter);
					
					System.out.println("�������µ������ٱ�־="+String.format(SQL_BATCHUPDATE_DESTORY, archivesInfoSavedTableName));
					
					//�������¿ⷿ������Ϣ�ݲ�״̬ 
					//UPDATE StoreroomArchivesInfo SET StoreStatus=4 WHERE NBXH=? AND ArchivesTypeID=?
					jdbcTemplate.batchUpdate(SQL_BATCHUPDATE_StoreroomArchivesInfo, setter);
					
					System.out.println("�������¿ⷿ������Ϣ�ݲ�״̬ ="+SQL_BATCHUPDATE_StoreroomArchivesInfo);
				}

				//���پֲ�����
				setter = null;
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
	public boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
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
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM %1$s WHERE Barcode=?";
				sql = String.format(sql, archivesInfoSavedTableName);
				List<ArchivesInfo> archivesInfos = getJdbcTemplate().query(sql, new ArchivesInfoMapper(archivesType),barcode);
				if(archivesInfos.size()>0){
					archivesInfo.cloneFrom(archivesInfos.get(0));
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
	public boolean updateArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
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
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "UPDATE %1$s SET Barcode=? WHERE ArchivesID=?";
				sql = String.format(sql, archivesInfoSavedTableName);
				int rowCount = getJdbcTemplate().update(sql,barcode,archivesID);
				if(rowCount == 0){
					pFlag = false;
					pErrInfo.getContent().append("û�������");
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
	public boolean findByArchivesID(String archivesID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
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
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM %1$s WHERE ArchivesID=?";
				sql = String.format(sql, archivesInfoSavedTableName);
				List<ArchivesInfo> archivesInfos = getJdbcTemplate().query(sql,new Object[]{archivesID},new ArchivesInfoMapper(archivesType));
				if(archivesInfos.size() > 0){
					archivesInfo.cloneFrom(archivesInfos.get(0));
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
	public boolean queryClassifiedForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isPublic,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = null;
		StringBuilder querySQL = new StringBuilder();
		
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// ��鵵�������Ƿ��и�ֵ			
			if (archivesType == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣδ��ʼ����");
			}
			

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// ���浱ǰ����ĵ����鵵��Ϣ��
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
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
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL , pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "�����ѯ����SQLƬ��ʧ�ܣ�");
						}
					}
				}
			}
			
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			//���� �����ܼ�Ϊ��������
			ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
			
			//��������������ϸ�� �ַ���
			String associateTable = "";
			//�ж��Ƿ��������������ϸ��
			if("0".equals(isPublic)) {
				//��ѯ�ܼ��ǹ�����δ���ŵĵ���
				associateTable = " WHERE SecrecyID=:SecrecyID AND PublicFlag=0 ";
				paramSource.addValue("SecrecyID", openArchivesSecrecy.getID());
			} else {
				//��ѯ��ǰ�û����ż������µ��ܼ��ǹ����ġ��Ѿ����ŵ���
				associateTable = " ,AppraisalPublicDetails p WHERE p.NBXH=t.NBXH  AND p.NewSecrecyID=:NewSecrecyID AND p.PublicFlag=1 AND t.PublicFlag=1 ";
				paramSource.addValue("NewSecrecyID", openArchivesSecrecy.getID());
			}
			
			//ִ�в�ѯ
			if (pFlag) {
				List<ArchivesInfo> pArchivesInfos = null;
					
				//��ѯ����������
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_PublicAppraisal, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource);
				
				System.out.println("COUNT="+String.format(SQL_SELECT_COUNT_PublicAppraisal, archivesInfoSavedTableName, associateTable, querySQL.toString()));
				
				dataPageInfo.setRowCount(count);
				
				//��ҳ����
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//��ҳ��ѯ����
				pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_PublicAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("list="+String.format(SQL_SELECT_PublicAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()));
				
				//�ж��Ƿ��м�¼��
				if (pArchivesInfos.size() >= 1) {
					archivesInfos.addAll(pArchivesInfos);
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
	public boolean queryClassifiedForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			String isOpen, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String archivesInfoSavedTableName = null;
		StringBuilder querySQL = new StringBuilder();
		
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// ��鵵�������Ƿ��и�ֵ			
			if (archivesType == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣδ��ʼ����");
			}
			

			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// ���浱ǰ����ĵ����鵵��Ϣ��
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
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
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL , pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "�����ѯ����SQLƬ��ʧ��; ");
						}
					}
				}
			}
			
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			
			//���� �����ܼ�Ϊ��������
			ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
			
			//��������������ϸ�� �ַ���
			String associateTable = "";
			//�ж��Ƿ��������������ϸ��
			if("0".equals(isOpen)) {
				//�ܼ��������ϡ�δ���ŵĵ���
				associateTable = " WHERE SecrecyID>:SecrecyID AND PublicFlag=0 ";
				paramSource.addValue("SecrecyID", openArchivesSecrecy.getID());
			} else {
				//��ѯ��ǰ�û������������µ��ܼ�Ϊ�����ġ�δ���ŵĵ���
				associateTable = " ,AppraisalPublicDetails p WHERE p.NBXH=t.NBXH AND p.NewSecrecyID=:NewSecrecyID AND p.OldSecrecyID>:NewSecrecyID AND p.PublicFlag=0 AND t.SecrecyID=:NewSecrecyID ";
				paramSource.addValue("ManagerUserID", userInfo.getUserID());
				paramSource.addValue("NewSecrecyID", openArchivesSecrecy.getID());
			}
			
			//ִ�в�ѯ
			if (pFlag) {
				List<ArchivesInfo> pArchivesInfos = null;
				//��ѯ����������
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_OpenAppraisal, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource);
				dataPageInfo.setRowCount(count);
				
				//��ҳ����
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//��ҳ��ѯ����
				pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_OpenAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()), paramSource, new ArchivesInfoMapper(archivesType));
				
				System.out.println("SQL_SELECT_OpenAppraisal_WithPage="+String.format(SQL_SELECT_OpenAppraisal_WithPage, archivesInfoSavedTableName, associateTable, querySQL.toString()));
				
				//�ж��Ƿ��м�¼��
				if (pArchivesInfos.size() >= 1) {
					archivesInfos.addAll(pArchivesInfos);
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
	public boolean updateBatchForPublicAppraisal(UserInfo userInfo,
			ArchivesType archivesType, final List<Integer> archivesNBXHs,
			String isPublic, ErrInfo pErrInfo) {
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//������������
				BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, archivesNBXHs.get(i));
					}

					@Override
					public int getBatchSize() {
						return archivesNBXHs.size();
					}
				};
				
				//�����鵵��Ϣ����
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				
				int publicFlag = 1;
				if("1".equals(isPublic)) {
					publicFlag = 0;
				}
				
				
				/**
				 * ��������->���ż���	�������µ�����Ϣ�Ŀ��ű�־ ��SQL���
				 * %1$s ���µı���
				 * %2$s ���ű�־
				 */
				//�������� �����Ŀ�����־
				jdbcTemplate.batchUpdate(String.format(SQL_BATCHUPDATE_PublicAppraisal, archivesInfoSavedTableName, publicFlag), setter);
				
				System.out.println("SQL_BATCHUPDATE_PublicAppraisal="+String.format(SQL_BATCHUPDATE_PublicAppraisal, archivesInfoSavedTableName, publicFlag));

				//���پֲ�����
				setter = null;
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
	public boolean updateBatchForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType, final List<Integer> archivesNBXHs,
			String isPublic, ErrInfo pErrInfo) {
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//������������
				BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setInt(1, archivesNBXHs.get(i));
					}

					@Override
					public int getBatchSize() {
						return archivesNBXHs.size();
					}
				};
				
				//�����鵵��Ϣ����
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				//�����ܼ��ֵ�
				ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
				
				String localSQL = String.format(SQL_BATCHUPDATE_OpenAppraisal, archivesInfoSavedTableName, openArchivesSecrecy.getID(), openArchivesSecrecy.getName());
				
				//�������� �����Ŀ�����־
				jdbcTemplate.batchUpdate(localSQL, setter);
				
				System.out.println("SQL_BATCHUPDATE_OpenAppraisal="+localSQL);

				//���پֲ�����
				setter = null;
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
	public boolean queryClassifiedForControlAreaAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// ��鵵�������Ƿ��и�ֵ			
			if (archivesType == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����������Ϣδ��ʼ����");
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			String archivesInfoSavedTableName = null;
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// ���浱ǰ����ĵ����鵵��Ϣ��
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
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
			StringBuilder querySQL = new StringBuilder();
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL , pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "�����ѯ����SQLƬ��ʧ�ܣ�");
						}
					}
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				
				//ִ�в�ѯ
				if (pFlag) {
					List<ArchivesInfo> pArchivesInfos = null;
						
					//��ѯ����������
					int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT_ControlArea, archivesInfoSavedTableName, querySQL.toString()), paramSource);
					dataPageInfo.setRowCount(count);
					
					System.out.println("SQL_SELECT_COUNT_ControlArea="+String.format(SQL_SELECT_COUNT_ControlArea, archivesInfoSavedTableName, querySQL.toString()));
					
					//��ҳ����
					paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
					paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
					
					//��ҳ��ѯ������Ϣ
					pArchivesInfos = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_ControlArea_WithPage, archivesInfoSavedTableName, querySQL.toString()), paramSource, new ArchivesInfoMapper(archivesType));
					
					System.out.println("SQL_SELECT_ControlArea_WithPage="+String.format(SQL_SELECT_ControlArea_WithPage, archivesInfoSavedTableName, querySQL.toString()));
					
					//�ж��Ƿ��м�¼��
					if (pArchivesInfos.size() >= 1) {
						archivesInfos.addAll(pArchivesInfos);
					}
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
	public boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> NBXHS = new ArrayList<Integer>();
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				for (int i = 0; i < nbxhs.length; i++) {
					NBXHS.add(nbxhs[i]);
				}
			}
			
			// ��鵵�������Ӧ�Ĺ����������Ƿ���ֵ
			String archivesInfoSavedTableName = null;
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// ���浱ǰ����ĵ����鵵��Ϣ��
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("nbxhs", NBXHS);
				
				String sql = "DELETE FROM %1$s WHERE NBXH IN (:nbxhs)";
				sql = String.format(sql, archivesInfoSavedTableName);
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
				
				sql = "DELETE FROM %1$s WHERE ParentNBXH IN (:nbxhs)";
                sql = String.format(sql, archivesInfoSavedTableName);
                new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
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
	public boolean checkOutArchivesInfo(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		String archivesInfoWorkingTableName = "";
		String strCondition = "";
		List<Integer> NBXHS = new ArrayList<Integer>();
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
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				}
			}
			
			if (pFlag) {
				Map<String, ArchivesTypeDataItem> dataItems =  archivesType.getDataItemsForAll();
				ArchivesTypeDataItem dataItem = null;
				List<ArchivesTypeDataItem> dataItemList = new ArrayList<ArchivesTypeDataItem>(dataItems.values());
				for (int i = 0;i<dataItemList.size();i++) {
					dataItem = dataItemList.get(i);
				    if(i == dataItemList.size()-1){
					   strCondition += dataItem.getColumnName();
					}else{
					   strCondition += dataItem.getColumnName()+",";
					}
				}
			}
			
			if (pFlag) {
				for (Integer NBXH : nbxhs) {
					NBXHS.add(NBXH);
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("nbxhs", NBXHS);
				
				String sql = "SET IDENTITY_INSERT %1$s ON INSERT INTO %1$s (%3$s) SELECT %3$s FROM %2$s T1 WHERE T1.NBXH IN (:nbxhs) SET IDENTITY_INSERT %1$s off";
				//String sql = "SET IDENTITY_INSERT %1$s ON INSERT INTO %1$s (%3$s) SELECT %3$s FROM %2$s T1 WHERE T1.NBXH IN (5) SET IDENTITY_INSERT %1$s off";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesInfoSavedTableName,strCondition);
				System.out.println(sql);
				//new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
				
				sql = "SET IDENTITY_INSERT %1$s ON INSERT INTO %1$s (%3$s) SELECT %3$s FROM %2$s T1 WHERE T1.ParentNBXH IN (:nbxhs) SET IDENTITY_INSERT %1$s off";
				sql = String.format(sql, archivesInfoWorkingTableName, archivesInfoSavedTableName,strCondition);
				System.out.println(sql);
				new NamedParameterJdbcTemplate(getDataSource()).update(sql, parameterSource);
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
