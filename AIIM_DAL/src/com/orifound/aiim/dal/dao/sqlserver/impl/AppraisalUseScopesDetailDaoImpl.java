/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.dal.util.TimeTool;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ���ؼ����ĵ�����ϸ��ϢDAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class AppraisalUseScopesDetailDaoImpl extends JdbcDaoSupport implements IAppraisalUseScopesDetailDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class AppraisalUseScopesDetailMapper implements RowMapper<AppraisalUseScopesDetail>
	{
		
		@Override
		public AppraisalUseScopesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
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
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			String archivesTypeName = rs.getString("archivesTypeName");
			String formationDepartment = rs.getString("formationDepartment");
			
			return new AppraisalUseScopesDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,registerDate,managerUserID,remark, archivesTypeName, formationDepartment);
		}
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class AppraisalUseScopesDetailMapperOther implements RowMapper<AppraisalUseScopesDetail>
	{
		
		@Override
		public AppraisalUseScopesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
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
			Date registerDate = rs.getTimestamp("RegisterDate");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");
			
			return new AppraisalUseScopesDetail(iD,archivesTypeID,nBXH,archivesID,title,appraisalReason,appraisalDate,appraisalOpinion,
					appraisalPersion,registerDate,managerUserID,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public AppraisalUseScopesDetailDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public AppraisalUseScopesDetailDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ����ָ���ĵ�����ָ���û���Ȩ���ʵĻ��ص����б��г��ֵĴ�����SQL���
	 */
	private final String SQL_SELECT_CountArchivesInfoNotInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
			" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
			" AND NOT EXISTS (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
	
	/**
	 * ����ָ���ĵ�����ָ���û��Ļ��ص����б��г��ֵĴ�����SQL���
	 */
	private final String SQL_SELECT_CountArchivesInfoInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
			" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
			" AND EXISTS (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
	
	/**
	 * ���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ��SQL���
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM AppraisalUseScopesDetails WHERE ArchivesTypeID=? AND NBXH=?";
	
	/**
	 * ͨ�������ڲ���Ų�ѯ���ֿ��Ƽ����Ľ�ɫid��Χ��SQL���
	 */
	private final String SQL_SELECT_RoleIds_ByNBXH = "SELECT r.ID RolesID,r.Name FROM AppraisalUseScopes_Roles us, DD_UserRoles r where us.RolesID=r.ID AND us.ID=? ORDER BY us.RolesID";
	
	
	/**
	 * ���滮����ϸ��Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO AppraisalUseScopesDetails(ArchivesTypeID,NBXH,ArchivesID,Title,AppraisalReason,AppraisalDate,AppraisalPersion,ManagerUserID,Remark,FormationDepartmentID) VALUES(:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:AppraisalReason,:AppraisalDate,:AppraisalPersion,:ManagerUserID,:Remark,:FormationDepartmentID)";
	
	/**
	 * ���»�����ϸ��Ϣ��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE AppraisalUseScopesDetails SET AppraisalReason=:AppraisalReason,AppraisalDate=:AppraisalDate,AppraisalPersion=:AppraisalPersion,RegisterDate=DEFAULT,ManagerUserID=:ManagerUserID WHERE ID=:ID";
	
	/**
	 * ���� �������ؼ�����ɫ��SQL���
	 */
	private final String SQL_INSERT_Roles = "INSERT INTO AppraisalUseScopes_Roles(ID, RolesID) SELECT %1$s,ID FROM DD_UserRoles WHERE ID IN(:RolesID)";
	
	/**
	 * ɾ�� �������ؼ�����ɫ��SQL���
	 */
	private final String SQL_DELETE_Roles = "DELETE FROM AppraisalUseScopes_Roles WHERE RolesID IN(:RolesID) AND ID=:ID";
	
	/**
	 * ��������->�����Ǽ�  ���ؼ���  ��ҳ��ѯ������Ϣ��SQL���
	 * %1$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM(SELECT kd.*,d.Name FormationDepartment,at.FullName archivesTypeName, ROW_NUMBER() OVER(ORDER BY kd.FormationDepartmentID ASC, kd.RegisterDate ASC) RowNumber " +
			" FROM AppraisalUseScopesDetails kd,DD_DepartmentInfo d, DD_ArchivesType at" +
			" WHERE kd.FormationDepartmentID=d.ID AND kd.ArchivesTypeID=at.ID %1$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow";
	
	/**
	 * ��������->�����Ǽ�   ���ؼ���  ��ѯ�Ǽ���Ϣ��¼������SQL���
	 * %1$s ҳ���ѯ����
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(ID) FROM AppraisalUseScopesDetails WHERE ID>=1 %1$s";
	
	/**
	 * �����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ���Ƶ�SQL���
	 */
	private final String SQL_SELECT_RoleNames_ByID = "SELECT r.Name FROM AppraisalUseScopes_Roles us, DD_UserRoles r WHERE us.RolesID=r.ID AND us.ID=?";
	
	/**
	 * ���ؼ�����ɾ��������ϸ��Ϣ��ͬʱɾ�����Ƽ����Ľ�ɫ��Χ�� ��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM AppraisalUseScopes_Roles WHERE ID=%1$s;DELETE FROM AppraisalUseScopesDetails WHERE ID=%1$s;";
	
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao#findCountArchivesInfoNotInUseScopesACL(int, int, int, com.orifound.aiim.entity.IntegerEx, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCountArchivesInfoNotInUseScopesACL(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pACLCount, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;

//				String SQL_SELECT_CountArchivesInfoNotInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
//				" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
//				" AND NOT EXISTS (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
		
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);
				paramSource.addValue("NBXH", pNBXH, Types.INTEGER);
				paramSource.addValue("UserID", pUserID, Types.INTEGER);

				pErrPos = 3;
				int pCount= namedParameterJdbcTemplate.queryForInt(SQL_SELECT_CountArchivesInfoNotInUseScopesACL, paramSource);
				
				//���ز�ѯ���
				pACLCount.setValue(pCount);

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
				tempBuilder.append("."+stackTraceElements[0].getMethodName()+"-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
					tempBuilder.append(" ErrPos: "+pErrPos+", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findCountArchivesInfoInUseScopesACL(int pUserID, int pArchivesTypeID, int pNBXH, IntegerEx pACLCount, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;

//				String SQL_SELECT_CountArchivesInfoInUseScopesACL = "SELECT COUNT(A.ID) FROM AppraisalUseScopesDetails A,AppraisalUseScopes_Roles B" +
//				" WHERE A.ArchivesTypeID=:ArchivesTypeID AND A.NBXH=:NBXH AND A.ID=B.ID" +
//				" AND NOT (SELECT C.RolesID FROM UserRolesInfo C WHERE C.UserID=:UserID AND B.RolesID=C.RolesID)";
		
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ArchivesTypeID", pArchivesTypeID, Types.INTEGER);
				paramSource.addValue("NBXH", pNBXH, Types.INTEGER);
				paramSource.addValue("UserID", pUserID, Types.INTEGER);

				pErrPos = 3;
				int pCount= namedParameterJdbcTemplate.queryForInt(SQL_SELECT_CountArchivesInfoInUseScopesACL, paramSource);
				
				//���ز�ѯ���
				pACLCount.setValue(pCount);

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
				tempBuilder.append("."+stackTraceElements[0].getMethodName()+"-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
					tempBuilder.append(" ErrPos: "+pErrPos+", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}


	@Override
	public boolean findByByNBXH(int archivesTypeID, int NBXH, AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
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
				List<AppraisalUseScopesDetail> pEntitys=jdbcTemplate.query(SQL_SELECT_ByNBXH,new AppraisalUseScopesDetailMapperOther(), archivesTypeID, NBXH);
				
				//��ѯ�������ֿ��Ƽ�����ϸ���
				if (pEntitys.size() > 0) {
					appraisalUseScopesDetail.cloneFrom(pEntitys.get(0));
					
					List<Map<String, Object>> results = jdbcTemplate.queryForList(SQL_SELECT_RoleIds_ByNBXH, appraisalUseScopesDetail.getID());
					
					//��ѯ�����Ļ��ط�Χ��ɫid����
					List<Integer> roleIds = new ArrayList<Integer>();
					List<String> roleNames = new ArrayList<String>();
					Object roleId = null;
					Object roleName = null;
					//ѭ����ȡ��ɫid����ɫ����
					for(Map<String, Object> result : results) {
						roleId = result.get("RolesID");
						roleName = result.get("Name");
						if(roleId!=null && roleName!=null) {
							roleIds.add(Integer.valueOf(roleId.toString()));
							roleNames.add(roleName.toString());
						}
					}
					appraisalUseScopesDetail.setRoleIds(roleIds);
					appraisalUseScopesDetail.setRoleNames(roleNames);
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
	public boolean save(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
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
				
				paramSource.addValue("ArchivesTypeID", appraisalUseScopesDetail.getArchivesTypeID(), Types.INTEGER);
				paramSource.addValue("NBXH", appraisalUseScopesDetail.getNBXH(), Types.INTEGER);
				paramSource.addValue("ArchivesID", appraisalUseScopesDetail.getArchivesID(), Types.VARCHAR);
				paramSource.addValue("Title", appraisalUseScopesDetail.getTitle(), Types.VARCHAR);
				paramSource.addValue("AppraisalReason", appraisalUseScopesDetail.getAppraisalReason(), Types.VARCHAR);
				paramSource.addValue("AppraisalDate", appraisalUseScopesDetail.getAppraisalDate(), Types.TIMESTAMP);
				paramSource.addValue("AppraisalPersion", appraisalUseScopesDetail.getAppraisalPersion(), Types.VARCHAR);
				paramSource.addValue("ManagerUserID", appraisalUseScopesDetail.getManagerUserID(), Types.INTEGER);
				paramSource.addValue("Remark", appraisalUseScopesDetail.getRemark(), Types.VARCHAR);
				paramSource.addValue("FormationDepartmentID", appraisalUseScopesDetail.getFormationDepartmentID(), Types.INTEGER);
				
				
				pErrPos = 3;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT);
				//����SQLִ�гɹ��󷵻ص�����
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				//���������ֶ���
				pscFactory.setGeneratedKeysColumnNames(new String[] { "ID" });

				pErrPos = 4;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);

				//�����Զ����ɵ��ڲ����
				appraisalUseScopesDetail.setID(keyHolder.getKey().intValue());

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				paramSource = null;
				pscFactory = null;
				keyHolder = null;
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
	public boolean update(AppraisalUseScopesDetail appraisalUseScopesDetail,
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
				paramSource.addValue("ID", appraisalUseScopesDetail.getID(), Types.INTEGER);
				paramSource.addValue("AppraisalReason", appraisalUseScopesDetail.getAppraisalReason(), Types.VARCHAR);
				paramSource.addValue("AppraisalDate", appraisalUseScopesDetail.getAppraisalDate(), Types.TIMESTAMP);
				paramSource.addValue("AppraisalPersion", appraisalUseScopesDetail.getAppraisalPersion(), Types.VARCHAR);
				paramSource.addValue("ManagerUserID", appraisalUseScopesDetail.getManagerUserID(), Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
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
	public boolean deleteRoles(int appraisalUseScopesDetailId,
			List<Integer> roleIds, ErrInfo pErrInfo) {
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
				
//				"DELETE FROM AppraisalUseScopes_Roles WHERE RolesID IN(:RolesID) AND ID=:ID"
				paramSource.addValue("RolesID", roleIds);
				paramSource.addValue("ID", appraisalUseScopesDetailId, Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_DELETE_Roles, paramSource);
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
	public boolean saveRoles(int appraisalUseScopesDetailId,
			List<Integer> roleIds, ErrInfo pErrInfo) {
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
				
//				"INSERT INTO AppraisalUseScopes_Roles(ID, RolesID) SELECT %1$s,ID FROM DD_UserRoles WHERE ID IN(:RolesID)";
				paramSource.addValue("RolesID", roleIds);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(String.format(SQL_INSERT_Roles, appraisalUseScopesDetailId), paramSource);
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
			List<AppraisalUseScopesDetail> appraisalUseScopesDetails,
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
				List<AppraisalUseScopesDetail> results = namedParameterJdbcTemplate.query(String.format(SQL_SELECT_WITHPAGE, buffer.toString()), 
							paramSource, new AppraisalUseScopesDetailMapper());
			
				System.out.println("���ؼ����Ǽ���Ϣ��ѯSQL��"+String.format(SQL_SELECT_WITHPAGE, buffer.toString()));
				
				//�ж��Ƿ���ڼ�¼
				if(results.size() >= 0) {
					appraisalUseScopesDetails.addAll(results);
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

	@Override
	public boolean findRoleNamesById(int pId, List<String> roleNames, ErrInfo pErrInfo) {
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
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_SELECT_RoleNames_ByID, pId);
				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					Object roleName = null;
					for(Map<String, Object> pEntity : pEntitys) {
						roleName = pEntity.get("Name");
						//�ж��Ƿ���ڽ�ɫ����
						if(roleName != null) {
							roleNames.add(roleName.toString());
						}
					}
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

	@Override
	public boolean delete(AppraisalUseScopesDetail appraisalUseScopesDetail, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(String.format(SQL_DELETE, appraisalUseScopesDetail.getID()));

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
System.out.println(pErrInfo.toString());
		return pFlag;
	}
}