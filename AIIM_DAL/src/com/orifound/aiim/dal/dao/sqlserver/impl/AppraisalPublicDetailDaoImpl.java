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

import com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * AppraisalPublicDetails(��������/����������)���DAOʵ����(SQL Serverƽ̨ʵ��)
 *
 */
public class AppraisalPublicDetailDaoImpl extends JdbcDaoSupport implements IAppraisalPublicDetailDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class AppraisalPublicDetailMapper implements RowMapper<AppraisalPublicDetail>
	{
		
		@Override
		public AppraisalPublicDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean publicFlag = rs.getBoolean("PublicFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			int oldSecrecyID = rs.getInt("OldSecrecyID");
			int newSecrecyID = rs.getInt("NewSecrecyID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			String archivesTypeName = rs.getString("archivesTypeName");
			String formationDepartment = rs.getString("FormationDepartment");
			String oldSecrecyName = rs.getString("OldSecrecyName");
			
			return new AppraisalPublicDetail(iD,publicFlag,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,oldSecrecyID,newSecrecyID,registerDate,managerUserID, remark,archivesTypeName, formationDepartment, oldSecrecyName);
		}
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class AppraisalPublicDetailMapperOther implements RowMapper<AppraisalPublicDetail>
	{
		
		@Override
		public AppraisalPublicDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			boolean publicFlag = rs.getBoolean("PublicFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String appraisalReason = rs.getString("AppraisalReason");
			Date appraisalDate = rs.getTimestamp("AppraisalDate");
			String appraisalOpinion = rs.getString("AppraisalOpinion");
			String appraisalPersion = rs.getString("AppraisalPersion");
			int oldSecrecyID = rs.getInt("OldSecrecyID");
			int newSecrecyID = rs.getInt("NewSecrecyID");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new AppraisalPublicDetail(iD,publicFlag,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,oldSecrecyID,newSecrecyID,registerDate,managerUserID, remark);
		}
	}
	
	
	/**
	 * ���캯��
	 */
	public AppraisalPublicDetailDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public AppraisalPublicDetailDaoImpl(DataSource dataSource) {
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
	 * ��������->���ż���	���뿪�ż�����ϸ�����SQL���
	 * %1$s	���ű�־
	 * %2$s	��������
	 * %3$s ��������
	 * %4$s ������
	 * %5$s �����˱�� 
	 * %6$s ��ע�����ż�����ȡ�����ż���
	 * %7$s ָ�������涨��
	 */
	private final String SQL_INSERT_PUBLIC = "INSERT INTO AppraisalPublicDetails(PublicFlag,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason," +
			"AppraisalDate,AppraisalPersion,OldSecrecyID,NewSecrecyID,ManagerUserID,Remark,FormationDepartmentID)" +
			"SELECT %1$s, ArchivesTypeID,NBXH,ArchivesID,Title, '%2$s', '%3$s', '%4$s', SecrecyID,SecrecyID, %5$s,'%6$s',FormationDepartmentID FROM %7$s WHERE NBXH=?";
	
	/**
	 * ��������->ȡ������/��������		ɾ�����ż�����ϸ�����SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM AppraisalPublicDetails WHERE PublicFlag=:PublicFlag AND ArchivesTypeID=:ArchivesTypeID AND NBXH=:NBXH";
	
	/**
	 * ��ԭ�����Ŀ��ű�־��SQL���
	 * 
	 */
	private final String SQL_UPDATE_PUBLICFLAG = "UPDATE %1$s SET PublicFlag=0 WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID";
	
	/**
	 * ��ԭ�������ܼ���SQL���
	 * update ArchivesInfoSaved_JX set SecrecyID=p.OldSecrecyID from AppraisalPublicDetails p where ArchivesInfoSaved_JX.NBXH=p.NBXH and p.ArchivesTypeID=?
	 */
	private final String SQL_UPDATE_SECRECY = "UPDATE %1$s SET SecrecyID=p.OldSecrecyID,SecrecyText=s.Name FROM AppraisalPublicDetails p,DD_ArchivesSecrecy s WHERE %1$s.NBXH=p.NBXH AND p.NewSecrecyID=:NewSecrecyID AND p.OldSecrecyID=s.ID AND p.ArchivesTypeID=:ArchivesTypeID AND p.NBXH=:NBXH";
	
	/**
	 * ��������->��������	���빫��������ϸ�����SQL���
	 * %1$s	��������
	 * %2$s ��������
	 * %3$s ������
	 * 
	 * %4$s	�ɵ��ܼ�
	 * %5$s	�µ��ܼ�
	 * 
	 * %6$s �����˱�� 
	 * %7$s ��ע������������ȡ����������
	 * %8$s ָ�������涨��
	 */
	private final String SQL_INSERT_OPEN = "INSERT INTO AppraisalPublicDetails(PublicFlag,ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason," +
			"AppraisalDate,AppraisalPersion,OldSecrecyID,NewSecrecyID,ManagerUserID,Remark,FormationDepartmentID)" +
			"SELECT 0, ArchivesTypeID,NBXH,ArchivesID,Title, '%1$s', '%2$s', '%3$s', %4$s, %5$s, %6$s,'%7$s',FormationDepartmentID FROM %8$s WHERE NBXH=?";
	
	/**
	 * ��������->�����Ǽ�  ����/���ż���  ��ҳ��ѯ������Ϣ��SQL���
	 * %1$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM(SELECT kd.*,d.Name FormationDepartment,at.FullName archivesTypeName,da.name OldSecrecyName, ROW_NUMBER() OVER(ORDER BY kd.FormationDepartmentID ASC, kd.RegisterDate ASC) RowNumber " +
			" FROM AppraisalPublicDetails kd,DD_DepartmentInfo d, DD_ArchivesType at,DD_ArchivesSecrecy da " +
			" WHERE kd.FormationDepartmentID=d.ID AND kd.ArchivesTypeID=at.ID AND kd.OldSecrecyID=da.ID %1$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow";
	
	/**
	 * ��������->�����Ǽ�   ����/���ż���  ��ѯ�Ǽ���Ϣ��¼������SQL���
	 * %1$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(ID) FROM AppraisalPublicDetails WHERE ID>=1 %1$s";
	
	/**
	 * ����������ѯ����/������ϸ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_BYID = "SELECT * FROM AppraisalPublicDetails WHERE PublicFlag=? AND ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#delete(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(AppraisalPublicDetail appraisalPublicDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<AppraisalPublicDetail> appraisalPublicDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#findByID(int, com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
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
				List<AppraisalPublicDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID, new AppraisalPublicDetailMapperOther(), publicFlag, pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					appraisalPublicDetail.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#save(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(AppraisalPublicDetail appraisalPublicDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao#update(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(AppraisalPublicDetail appraisalPublicDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, Map<String, String> opinion, ErrInfo pErrInfo) {
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
				
				String remark = "���ż���";
				int PublicFlag = 1;
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				String localSQL = null;
				//ѭ��	���뿪��������ϸ
				for(Integer NBXH: archivesNBXHs) {
					/**
					 * ��������->���ż���	���뿪�ż�����ϸ�����SQL���
					 * %1$s	���ű�־
					 * %2$s	����ԭ��
					 * %3$s ��������
					 * %4$s ������
					 * %5$s �����˱�� 
					 * %6$s ��ע�����ż���
					 * %7$s ָ�������鵵��
					 */
					localSQL = String.format(SQL_INSERT_PUBLIC, PublicFlag, AppraisalReason, AppraisalDate, AppraisalPersion, 
										userInfo.getUserID(), remark, archivesInfoSavedTableName);
					
					System.out.println("��������->���ż��� ������ӵ������ż�����Ϣ localSQL ="+localSQL);
					
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
	public boolean saveBatchForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs,
									Map<String, String> opinion, ErrInfo pErrInfo) {
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
				
				String OldSecrecyID = "SecrecyID";
				ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
				String NewSecrecyID = ""+openArchivesSecrecy.getID();
				String remark = "��������";
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				String localSQL = null;
				//ѭ��	���빫��������ϸ
				for(Integer NBXH: archivesNBXHs) {
					/**
					 * ��������->��������	���빫��������ϸ�����SQL���
					 * %1$s	��������
					 * %2$s ��������
					 * %3$s ������
					 * 
					 * %4$s	�ɵ��ܼ�
					 * %5$s	�µ��ܼ�
					 * 
					 * %6$s �����˱�� 
					 * %7$s ��ע����������
					 * %8$s ָ�������鵵��
					 */
					localSQL = String.format(SQL_INSERT_OPEN, AppraisalReason, AppraisalDate, AppraisalPersion, 
										OldSecrecyID,NewSecrecyID,userInfo.getUserID(), remark, archivesInfoSavedTableName);
					
					System.out.println("��������->�������� ������ӵ������ż�����Ϣ localSQL ="+localSQL);
					
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
	public boolean delete(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			
			//��鵵���ڲ����
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ�գ�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("PublicFlag", publicFlag, Types.INTEGER);
				paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
				paramSource.addValue("NBXH", NBXH, Types.INTEGER);

				pErrPos = 3;
				//DELETE FROM AppraisalPublicDetails WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID AND PublicFlag=:PublicFlag
				namedParameterJdbcTemplate.update(SQL_DELETE, paramSource);
				
				//�����鵵��Ϣ����
				String archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.�����鵵��Ϣ��).getTableName();
				
				//�����ܼ��ֵ�
				ArchivesSecrecy openArchivesSecrecy = SystemInitializer.getInstance().getOpenArchivesSecrecy();
				
				paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
				paramSource.addValue("NBXH", NBXH, Types.INTEGER);
				//ȡ�����ż���
				if(publicFlag == 1) {
					pErrPos = 4;
					//UPDATE %1$s SET PublicFlag=0 WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID
					namedParameterJdbcTemplate.update(String.format(SQL_UPDATE_PUBLICFLAG, archivesInfoSavedTableName), paramSource);
				}
				
				//ȡ����������
				if(publicFlag == 0) {
					pErrPos = 5;
					paramSource.addValue("NewSecrecyID", openArchivesSecrecy.getID(), Types.INTEGER);
					//UPDATE %1$s SET SecrecyID=p.OldSecrecyID FROM AppraisalPublicDetails p WHERE %1$s.NBXH=p.NBXH AND p.NewSecrecyID=:NewSecrecyID AND p.ArchivesTypeID=:ArchivesTypeID AND p.NBXH=:NBXH
					namedParameterJdbcTemplate.update(String.format(SQL_UPDATE_SECRECY, archivesInfoSavedTableName), paramSource);
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
	public boolean findWithPage(List<Integer> archivesTypeIds,
			Map<String, String> params, DataPageInfo dataPageInfo,
			List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo) {
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
//				 								������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd��������־PublicFlag
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
				
				String PublicFlag = params.get("PublicFlag");
				if (StringTool.checkNull(PublicFlag)) {
					buffer.append(" AND PublicFlag=:PublicFlag");
					paramSource.addValue("PublicFlag", PublicFlag, Types.INTEGER);
				}
				
				//��ѯ�������ܼ�¼��
				int count = namedParameterJdbcTemplate.queryForInt(String.format(SQL_SELECT_COUNT, buffer.toString()), paramSource);
				dataPageInfo.setRowCount(count);
				
				//��ҳ����
				paramSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
				paramSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
				//��ҳ��ѯ����
				pErrPos = 3;
				List<AppraisalPublicDetail> results = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_WITHPAGE, buffer.toString()), paramSource, new AppraisalPublicDetailMapper());
			
				System.out.println("���ż����Ǽ���Ϣ��ѯSQL��"+String.format(SQL_SELECT_WITHPAGE, buffer.toString()));
				
				//�ж��Ƿ���ڼ�¼
				if(results.size() >= 0) {
					appraisalPublicDetails.addAll(results);
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
