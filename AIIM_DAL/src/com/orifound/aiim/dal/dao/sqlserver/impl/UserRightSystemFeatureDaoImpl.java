/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

/**
 * �û�ϵͳ����Ȩ�޵�DAOʵ����
 *
 */
public class UserRightSystemFeatureDaoImpl extends JdbcDaoSupport implements IUserRightSystemFeatureDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class UserRightSystemFeatureMapper implements RowMapper<UserRightSystemFeature>
	{
		
		@Override
		public UserRightSystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			int featureID = rs.getInt("FeatureID");
			String title = rs.getString("Title");
			String name = rs.getString("Name");
			int parentID = rs.getInt("ParentID");
			String uclKey = rs.getString("UclKey");
			boolean menuFlag = rs.getBoolean("MenuFlag");
			boolean topFlag = rs.getBoolean("TopFlag");
			String menuURI = rs.getString("MenuURI");
			String remark = rs.getString("Remark");
	
			return new UserRightSystemFeature(iD, userID, featureID, title, name, parentID, uclKey, menuFlag, topFlag, menuURI, remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public UserRightSystemFeatureDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserRightSystemFeatureDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯָ���û�������ϵͳ������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_SystemFeatures_BY_UserID = "SELECT A.ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B WHERE A.UserID=? AND A.FeatureID=B.ID  ORDER BY B.ID";
	
	/**
	 * ��ѯָ���û������ж���˵�������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_TopMenus_BY_UserID = "SELECT A.ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B WHERE A.UserID=? AND B.TopFlag=1 AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * ��ѯָ���û��߱���ָ���˵���������¼��˵�������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_ChildMenus_BY_UserIDAndMenuID = "SELECT A.ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B WHERE A.UserID=:UserID AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * �����û�ϵͳ����Ȩ�ޱ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)";
	
	/**
	 * Ϊָ�����û������û�ϵͳ����Ȩ�ޱ��SQL���
	 */
	private final String SQL_INSERT_By_UserID = "INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)";
	
	
	/**
	 * ɾ���û�ϵͳ����Ȩ�ޱ��SQL���
	 */
	private final String SQL_DELETE	= "DELETE FROM UserRight_SystemFeature WHERE ID = ?";
	
	/**
	 * ɾ��ָ���û��µ�ָ����ϵͳ����Ȩ�޵�SQL���
	 */
	private final String SQL_DELETE_By_UserIDAndFeatureID = "DELETE FROM UserRight_SystemFeature WHERE UserID = ? and FeatureID = ?";
	
	/**
	 * ɾ��ָ���û��µ����е�ϵͳ����Ȩ�޵�SQL���
	 */
	private final String SQL_DELETE_By_UserID = "DELETE FROM UserRight_SystemFeature where UserID = ?";
	
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
				if (pErrInfo.getException()!=null)
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
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#save(com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			pErrPos = 1;
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�޶���Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�ޱ���û���ŷǷ�Ϊ��!");
				}
			}
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getFeatureID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�ޱ��ϵͳ���ܱ�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 4;
				//INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userRightSystemFeature.getID(), Types.INTEGER);
				sqlParameterSource.addValue("FeatureID", userRightSystemFeature.getFeatureID(), Types.INTEGER);

				parameterJdbcTemplate.update(SQL_INSERT, sqlParameterSource);
				//���پֲ�����
				parameterJdbcTemplate = null;
				sqlParameterSource = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#delete(com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�޶���Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�޵ı�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,userRightSystemFeature.getID());

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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#findByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByUserID(int pUserID, Map<String, SystemFeature> userRightSystemFeatures, ErrInfo pErrInfo)
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
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightSystemFeature> pUserRightSystemFeatures=jdbcTemplate.query(SQL_SELECT_SystemFeatures_BY_UserID,new UserRightSystemFeatureMapper(),pUserID);

				//���ز�ѯ���
				if (pUserRightSystemFeatures.size() > 0)
				{
					for (UserRightSystemFeature item : pUserRightSystemFeatures)
					{
						item.setID(item.getFeatureID());
						userRightSystemFeatures.put(item.getUclKey(), item);
					}
				}

				//���پֲ�����
				pUserRightSystemFeatures=null;
				jdbcTemplate = null;
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
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
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
	 * @see com.orifound.aiim.dal.dao.IUserRightSystemFeatureDao#findMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findMenusByUserID(int pUserID, LinkedHashMap<String, SystemFeature> userRightMenus, ErrInfo pErrInfo)
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
			
			//����û�����Ƿ�Ƿ�
			if (pFlag)
			{
				if (pUserID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{

				//���ҵ�����˵���Ȩ
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRightSystemFeature> pUserRightSystemFeatures=jdbcTemplate.query(SQL_SELECT_TopMenus_BY_UserID,new UserRightSystemFeatureMapper(),pUserID);

				//���ز�ѯ���
				if (pUserRightSystemFeatures.size() > 0)
				{
					for (UserRightSystemFeature topMenuItem : pUserRightSystemFeatures)
					{
						SystemFeature pFeature= topMenuItem;
						pFeature.setID(topMenuItem.getFeatureID());
						userRightMenus.put(pFeature.getUclKey(), pFeature);
						
						//��������Ȩ���¼��˵�����
						LinkedHashMap<String, SystemFeature> childMenus = new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pUserID, topMenuItem.getFeatureID(), childMenus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"�����û������: "+pUserID+"���� "+ topMenuItem.getName() +" ���¼���Ȩ�˵�ʧ��: ");
						}
						else
						{
							//�����ȡ�ĸ��û���Ȩ���¼��˵�����
							if (childMenus.size()>0)
							{
								topMenuItem.setChildSystemFeatures(childMenus);
							}
						}
					}
				}

				//���پֲ�����
				pUserRightSystemFeatures=null;
				jdbcTemplate = null;
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
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
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
	
	/**
	 * ����ָ���û��߱���ָ���˵����¼��˵���Ȩ����
	 * @param pUserID ָ�����û����
	 * @param featureID ָ���Ĳ˵����ܱ��
	 * @param childMenus ���ز��ҳɹ����¼��˵�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean findForChildMenus(int pUserID, int featureID, LinkedHashMap<String, SystemFeature> childMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//ִ��SQL���
				//SQL_SELECT_ChildMenus_BY_UserIDAndMenuID = "SELECT ID,UserID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B 
				//WHERE A.UserID=:UserID AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
				pErrPos = 1;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("UserID", pUserID, Types.INTEGER);
				paramSource.addValue("ParentID", featureID, Types.INTEGER);

				pErrPos =2;
				List<UserRightSystemFeature> pUserRightSystemFeatures= namedParameterJdbcTemplate.query(SQL_SELECT_ChildMenus_BY_UserIDAndMenuID, paramSource, new UserRightSystemFeatureMapper());
				
				//���ز�ѯ���
				if (pUserRightSystemFeatures.size()>0)
				{
					for (UserRightSystemFeature item : pUserRightSystemFeatures)
					{
						//��Ϊ�¼��˵�����������з���
						SystemFeature pFeature=item;
						pFeature.setID(item.getFeatureID());
						childMenus.put(pFeature.getUclKey(), pFeature);
						
						pErrPos=2;
						//�����ݹ���Ҹ��¼��˵�������һ���˵���ֱ��û���¼��˵�Ϊֹ
						LinkedHashMap<String, SystemFeature> pChildren=new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pUserID,item.getFeatureID(), pChildren, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"�����û������: "+pUserID+"���� "+ item.getName() +" ���¼���Ȩ�˵�ʧ��: ");
							break;
						}
						else
						{
							if (pChildren.size()>0)
							{
								item.setChildSystemFeatures(pChildren);
							}
						}
					}
				}
				
				//���پֲ�����
				pUserRightSystemFeatures=null;
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
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����

		}

		return pFlag;
	}

	@Override
	public boolean deleteUserRightSystemFeatureByUserIDAndFeatureID(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ��û���Ϣ�Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ���û�ϵͳ����Ȩ�ޣ��û���ŷǷ�Ϊ0!");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�޶���Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û�ϵͳ����Ȩ�޵ı�ŷǷ�Ϊ��!");
				}
			}
			
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserIDAndFeatureID,userInfo.getUserID(),userRightSystemFeature.getID());

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

	@Override
	public boolean saveUserRightSystemFeatureByUserID(UserInfo userInfo, UserRightSystemFeature userRightSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			pErrPos = 1;
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}

			if (pFlag) {
				pErrPos = 2;
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ��,�û���Ϣ�Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (userRightSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�޶���Ƿ�Ϊ��!");
				}
			}
			
			
			if (pFlag) {
				pErrPos = 4;
				if (userRightSystemFeature.getUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޱ���û���ŷǷ�Ϊ0!");
				}
			}
			if (pFlag) {
				pErrPos = 5;
				if (userRightSystemFeature.getFeatureID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("����û�ϵͳ����Ȩ�ޱ��ϵͳ���ܱ�ŷǷ�Ϊ0!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 4;
				//INSERT INTO UserRight_SystemFeature(UserID,FeatureID) VALUES(:UserID,:FeatureID)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("UserID", userInfo.getUserID(), Types.INTEGER);
				sqlParameterSource.addValue("FeatureID", userRightSystemFeature.getFeatureID(), Types.INTEGER);
				parameterJdbcTemplate.update(SQL_INSERT_By_UserID, sqlParameterSource);
				//���پֲ�����
				parameterJdbcTemplate = null;
				sqlParameterSource = null;
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
	
	
	public boolean deleteUserRightSystemFeatureByUserID(int pUserID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "�û�ϵͳ����Ȩ�ޱ��JDBC����Դ�Ƿ���ȷ����ע��ʧ��:");
			}
			
			if (pUserID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("�û�ϵͳ����Ȩ�ޱ��û���ŷǷ�Ϊ��!");
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 3;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_UserID,pUserID);
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
