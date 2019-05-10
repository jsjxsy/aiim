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
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��ټ�����ϸ������DAOʵ���� (SQL Serverƽ̨ʵ��)
 * @author tyb
 *
 */
public class AppraisalKeepDestroyDetailDaoImpl extends JdbcDaoSupport implements IAppraisalKeepDestroyDetailDao {
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class AppraisalKeepDestroyDetailMapper implements RowMapper<AppraisalKeepDestroyDetail>
	{
		
		@Override
		public AppraisalKeepDestroyDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			boolean appraisalDeletedFlag = rs.getBoolean("AppraisalDeletedFlag");
			int oldRetentionPeriodID = rs.getInt("OldRetentionPeriodID");
			int newRetentionPeriodID = rs.getInt("NewRetentionPeriodID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			String FormationDepartment = rs.getString("FormationDepartment");
			String archivesTypeName = rs.getString("archivesTypeName");
			String retentionPeriodName = rs.getString("retentionPeriodName");
			String oldRetentionPeriodName = rs.getString("oldRetentionPeriodName");
			
			return new AppraisalKeepDestroyDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,appraisalDeletedFlag,oldRetentionPeriodID,newRetentionPeriodID,registerDate,
					managerUserID,remark,FormationDepartment,archivesTypeName, retentionPeriodName, oldRetentionPeriodName);
		}
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class AppraisalKeepDestroyDetailMapperOther implements RowMapper<AppraisalKeepDestroyDetail>
	{
		
		@Override
		public AppraisalKeepDestroyDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			boolean appraisalDeletedFlag = rs.getBoolean("AppraisalDeletedFlag");
			int oldRetentionPeriodID = rs.getInt("OldRetentionPeriodID");
			int newRetentionPeriodID = rs.getInt("NewRetentionPeriodID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new AppraisalKeepDestroyDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,appraisalDeletedFlag,oldRetentionPeriodID,newRetentionPeriodID,registerDate,
					managerUserID,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public AppraisalKeepDestroyDetailDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public AppraisalKeepDestroyDetailDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
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
	 * ��������->��ټ���	�����ټ�����ϸ�����SQL���
	 * %1$s ��������
	 * %2$s	����ԭ��
	 * %3$s ������
	 * %4$s �Ƿ�����
	 * %5$s �±�������ID
	 * %6$s �����˱��
	 * %7$s �����鵵���� 
	 */
	private final String SQL_INSERT = "INSERT INTO AppraisalKeepDestroyDetails(ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalDate," +
			" AppraisalReason, AppraisalPersion,AppraisalDeletedFlag,OldRetentionPeriodID,NewRetentionPeriodID,ManagerUserID,Remark,FormationDepartmentID)" +
			" SELECT ArchivesTypeID,NBXH,ArchivesID,Title,'%1$s', " +
			" '%2$s', '%3$s', %4$s , RetentionPeriodID, %5$s, %6$s, NULL,FormationDepartmentID" +
			" from %7$s where NBXH=?";
	
	/**
	 * ��������->�����Ǽ�  ��ټ���  ��ҳ��ѯ������Ϣ��SQL���
	 * %1$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM(SELECT kd.*,d.Name FormationDepartment,at.FullName archivesTypeName, rp.name retentionPeriodName, oldrp.Name oldRetentionPeriodName, ROW_NUMBER() OVER(ORDER BY kd.FormationDepartmentID ASC, kd.RegisterDate ASC) RowNumber " +
			" FROM AppraisalKeepDestroyDetails kd,DD_DepartmentInfo d, DD_ArchivesType at,DD_RetentionPeriod rp,DD_RetentionPeriod oldrp " +
			" WHERE kd.FormationDepartmentID=d.ID AND kd.ArchivesTypeID=at.ID AND rp.id=kd.NewRetentionPeriodID AND oldrp.id=kd.OldRetentionPeriodID %1$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow";
	
	/**
	 * ��������->�����Ǽ�  ��ټ���  ��ѯ�Ǽ���Ϣ��¼������SQL���
	 * %1$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(ID) FROM AppraisalKeepDestroyDetails WHERE ID>=1 %1$s";
	
	/**
	 * ����������ѯ��ټ�����Ϣ��SQL���
	 */
	private final String SQL_SELECT_BYID = "SELECT * FROM AppraisalKeepDestroyDetails WHERE ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#delete(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(
			AppraisalKeepDestroyDetail appraisalKeepDestoryDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(
			List<AppraisalKeepDestroyDetail> appraisalKeepDestoryDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#findByID(int, com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestoryDetail, ErrInfo pErrInfo) {
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
				List<AppraisalKeepDestroyDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID,new AppraisalKeepDestroyDetailMapperOther(),pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					appraisalKeepDestoryDetail.cloneFrom(pEntitys.get(0));
				}

				//���پֲ�����
				pEntitys = null;
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
	
	/**
	 * ���ڱ��湹���õĲ����ټ�����ϸ���SQL���
	 */
	private String localSQL ;

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#save(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
					
			//��⾭�����Ƿ�Ϊ��
			if (userInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("�������->�����˷Ƿ�Ϊ�ա�");
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				
				//�����鵵��Ϣ����
				String archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				
				int userId = userInfo.getUserID();
				
				//��������
				String AppraisalDate = opinion.get("AppraisalDate");
				if(StringTool.checkNull(AppraisalDate) == false) {
					AppraisalDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
				}
				
				//�������(��Ϊ��)
				String AppraisalReason = opinion.get("AppraisalReason"); 
				if(StringTool.checkNull(AppraisalReason) == false) {
					AppraisalReason = "";
				}
				//������(��Ϊ��)
				String AppraisalPersion = opinion.get("AppraisalPersion");
				if(StringTool.checkNull(AppraisalPersion) == false) {
					AppraisalPersion = "";
				}
				
				//ѭ��	�����ټ�����ϸ
				for(Integer NBXH: batchArchives.keySet()) {
					Map<String, String> params = batchArchives.get(NBXH);
					
					//�Ƿ����ٱ�־
					String AppraisalDeletedFlag = params.get("AppraisalDeletedFlag");
					if(StringTool.checkNull(AppraisalDeletedFlag) == false) {
						AppraisalDeletedFlag = "0";
					}
					
					//�±�������ID
					String NewRetentionPeriodID = params.get("NewRetentionPeriodID");
					//���ٵ���
					if(NewRetentionPeriodID.equals("0")) {
						NewRetentionPeriodID = "RetentionPeriodID";
					}
					
					/**
					 * ��������->��ټ���	�����ټ�����ϸ�����SQL���
					 * %1$s ��������
					 * %2$s	����ԭ��
					 * %3$s ������
					 * %4$s �Ƿ�����
					 * %5$s �±�������ID
					 * %6$s �����˱��
					 * %7$s �����鵵���� 
					 */
					localSQL = String.format(SQL_INSERT, AppraisalDate, AppraisalReason, AppraisalPersion, AppraisalDeletedFlag, 
									NewRetentionPeriodID, userId, archivesInfoWorkingTableName);
					
					jdbcTemplate.update(localSQL, NBXH);
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
			System.out.println(e.getMessage());
			
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao#update(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(
			AppraisalKeepDestroyDetail appraisalKeepDestoryDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findWithPage(List<Integer> archivesTypeIds, Map<String, String> params, DataPageInfo dataPageInfo, List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
				ErrInfo pErrInfo) {
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
				
				
//				params �����б� ����������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
//				 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd
				//�����ѯ����
				StringBuffer buffer = new StringBuffer();
				
				//�ж��Ƿ��е�������
				String archivesID = params.get("archivesID");
				if(StringTool.checkNull(archivesID)) {
					buffer.append(" AND archivesID LIKE '%").append(archivesID).append("%' ");
				}
				
				//�ж��Ƿ�����������
				String title = params.get("title");
				if(StringTool.checkNull(title)) {
					buffer.append(" AND title LIKE '%").append(title).append("%' ");
				}
				
				//�ж��Ƿ��е�����������	��ѯ��һ�����������µ����е���
				String archivesTypeId = params.get("archivesTypeId");
				if(StringTool.checkNull(archivesTypeId) && archivesTypeIds!=null && archivesTypeIds.size()>=1) {
					System.out.println("�������ͣ�");
					for(Integer i : archivesTypeIds) {
						System.out.print(i+"-");
					}
					System.out.println("");
					
					buffer.append(" AND archivesTypeId IN(:archivesTypeId)");
					paramSource.addValue("archivesTypeId", archivesTypeIds);
				}
				
				//�ж��Ƿ��е����γɲ�������
				String formationDepartmentID = params.get("formationDepartmentID");
				if(StringTool.checkNull(formationDepartmentID)) {
					buffer.append(" AND formationDepartmentID=:formationDepartmentID");
					paramSource.addValue("formationDepartmentID", formationDepartmentID, Types.INTEGER);
				}
				
				//�ж��Ƿ��м�����ʼ��������
				String AppraisalDate = params.get("AppraisalDate");
				if(StringTool.checkNull(AppraisalDate)) {
					buffer.append(" AND AppraisalDate>=:AppraisalDate");
					paramSource.addValue("AppraisalDate", TimeTool.getMinTime(AppraisalDate),Types.TIMESTAMP);
				}
				
				//�ж��Ƿ��м�����ֹ��������
				String AppraisalDateEnd = params.get("AppraisalDateEnd");
				if(StringTool.checkNull(AppraisalDateEnd)) {
					buffer.append(" AND AppraisalDate<=:AppraisalDateEnd");
					paramSource.addValue("AppraisalDateEnd", TimeTool.getMaxTime(AppraisalDateEnd),Types.TIMESTAMP);
				}

				//��ѯ�������ܼ�¼��
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT, buffer.toString()), paramSource);
				dataPageInfo.setRowCount(count);
				
				//��ҳ����
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//��ҳ��ѯ����
				pErrPos = 3;
				List<AppraisalKeepDestroyDetail> results = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_WITHPAGE, buffer.toString()), paramSource, new AppraisalKeepDestroyDetailMapper());
			
				System.out.println("��ټ����Ǽ���Ϣ��ѯSQL��"+String.format(SQL_SELECT_WITHPAGE, buffer.toString()));
				
				//�ж��Ƿ���ڼ�¼
				if(results.size() >= 0) {
					appraisalKeepDestroyDetails.addAll(results);
				}
				
				//���پֲ�����
				results = null;
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
}