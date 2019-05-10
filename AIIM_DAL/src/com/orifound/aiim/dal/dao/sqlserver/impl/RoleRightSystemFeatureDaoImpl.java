/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RoleRightSystemFeature;
import com.orifound.aiim.entity.SystemFeature;


/**
 * ��ɫϵͳ������Ȩ��Ϣ��DAOʵ����
 *
 */
public class RoleRightSystemFeatureDaoImpl extends JdbcDaoSupport implements IRoleRightSystemFeatureDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class RoleRightSystemFeatureMapper implements RowMapper<RoleRightSystemFeature>
	{
		
		@Override
		public RoleRightSystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int featureID = rs.getInt("FeatureID");
			String title = rs.getString("Title");
			String name = rs.getString("Name");
			int parentID = rs.getInt("ParentID");
			String uclKey = rs.getString("UclKey");
			boolean menuFlag = rs.getBoolean("MenuFlag");
			boolean topFlag = rs.getBoolean("TopFlag");
			String menuURI = rs.getString("MenuURI");
			String remark = rs.getString("Remark");
			
			return new RoleRightSystemFeature(iD, rolesID, featureID, title, name, parentID, uclKey, menuFlag, topFlag, menuURI, remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public RoleRightSystemFeatureDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public RoleRightSystemFeatureDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	
	/**
	 * ��ѯָ����ɫ������ϵͳ������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_SystemFeatures_BY_RoleID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM DDR_UserRoles_SystemFeature A,DD_SystemFeature B WHERE A.RolesID IN (:RolesID) AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * ��ѯָ����ɫ�����ж���˵�������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_TopMenus_BY_RoleID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM DDR_UserRoles_SystemFeature A,DD_SystemFeature B WHERE A.RolesID IN (:RolesID) AND B.TopFlag=1 AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	/**
	 * ��ѯָ����ɫ�߱���ָ���˵���������¼��˵�������Ȩ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_ChildMenus_BY_RoleIDAndMenuID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM DDR_UserRoles_SystemFeature A,DD_SystemFeature B WHERE A.RolesID IN (:RolesID) AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID";
	
	
	
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
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#save(com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#delete(com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(RoleRightSystemFeature roleRightSystemFeature, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#findByRoleID(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByRoleID(int[] pRoleID, Map<String, SystemFeature> roleRightSystemFeatures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
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
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}
			
			//ת����ɫ�������Ϊlist�ṹ���Ա�SQL��ѯʹ��
			if (pFlag)
			{
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				List<RoleRightSystemFeature> pRoleRightSystemFeatures=namedParameterJdbcTemplate.query(SQL_SELECT_SystemFeatures_BY_RoleID,paramSource,new RoleRightSystemFeatureMapper());

				//���ز�ѯ���
				if (pRoleRightSystemFeatures.size() > 0)
				{
					for (RoleRightSystemFeature item : pRoleRightSystemFeatures)
					{
						roleRightSystemFeatures.put(item.getUclKey(), item);
					}
				}

				//���پֲ�����
				pRoleRightSystemFeatures=null;
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
			pRoleIDs=null;
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IRoleRightSystemFeatureDao#findMenusByRoleID(int[], java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findMenusByRoleID(int[] pRoleID, LinkedHashMap<String, SystemFeature> roleRightMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
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
				if (pRoleID.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}
			
			//ת����ɫ�������
			if (pFlag)
			{
				
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag)
			{

				//���ҵ�����˵���Ȩ
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				List<RoleRightSystemFeature> pRoleRightSystemFeatures=namedParameterJdbcTemplate.query(SQL_SELECT_TopMenus_BY_RoleID,paramSource,new RoleRightSystemFeatureMapper());

				//���ز�ѯ���
				if (pRoleRightSystemFeatures.size() > 0)
				{
					for (RoleRightSystemFeature topMenuItem : pRoleRightSystemFeatures)
					{
						roleRightMenus.put(topMenuItem.getUclKey(), topMenuItem);
						
						//��������Ȩ���¼��˵�����
						LinkedHashMap<String, SystemFeature> childMenus = new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pRoleID, topMenuItem.getFeatureID(), childMenus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"���ҽ�ɫ�� "+ topMenuItem.getName() +" ���¼���Ȩ�˵�ʧ��: ");
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
				pRoleRightSystemFeatures=null;
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
			pRoleIDs=null;
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * ����ָ���û��߱���ָ���˵����¼��˵���Ȩ����
	 * @param pRoleID ָ���Ľ�ɫ�������
	 * @param featureID ָ���Ĳ˵����ܱ��
	 * @param childMenus ���ز��ҳɹ����¼��˵�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean findForChildMenus(int[] pRoleID, int featureID, LinkedHashMap<String, SystemFeature> childMenus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		List<Integer> pRoleIDs=new ArrayList<Integer>();
		
		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo)==false)
			{
				pFlag = false;
				
			}
			
			//ת����ɫ�������
			if (pFlag)
			{
				for (int i = 0; i < pRoleID.length; i++)
				{
					pRoleIDs.add(pRoleID[i]);
				}
				
				if (pRoleIDs.size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag)
			{
				pErrPos=1;
				//ִ��SQL���
				//SQL_SELECT_ChildMenus_BY_RoleIDAndMenuID = "SELECT A.ID,RolesID,FeatureID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,Remark FROM UserRight_SystemFeature A,DD_SystemFeature B 
				//WHERE A.RolesID IN (:RolesID) AND B.ParentID=:ParentID AND B.MenuFlag=1 AND A.FeatureID=B.ID ORDER BY B.ID"; 

				pErrPos = 1;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("RolesID", pRoleIDs);
				paramSource.addValue("ParentID", featureID, Types.INTEGER);

				pErrPos =2;
				List<RoleRightSystemFeature> pRoleRightSystemFeatures= namedParameterJdbcTemplate.query(SQL_SELECT_ChildMenus_BY_RoleIDAndMenuID, paramSource, new RoleRightSystemFeatureMapper());
				
				//���ز�ѯ���
				if (pRoleRightSystemFeatures.size()>0)
				{
					for (RoleRightSystemFeature item : pRoleRightSystemFeatures)
					{
						//��Ϊ�¼��˵�����������з���
						childMenus.put(item.getUclKey(), item);
						
						pErrPos=2;
						//�����ݹ���Ҹ��¼��˵�������һ���˵���ֱ��û���¼��˵�Ϊֹ
						LinkedHashMap<String, SystemFeature> pChildren=new LinkedHashMap<String, SystemFeature>();
						if (findForChildMenus(pRoleID,item.getFeatureID(), pChildren, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"���ҽ�ɫ�� "+ item.getName() +" ���¼���Ȩ�˵�ʧ��: ");
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
				pRoleRightSystemFeatures=null;
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
			pRoleIDs=null;
			throwable=null;
		}

		return pFlag;
	}
	
}
