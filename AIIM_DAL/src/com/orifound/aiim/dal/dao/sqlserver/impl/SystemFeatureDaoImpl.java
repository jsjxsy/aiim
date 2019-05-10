package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ISystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class SystemFeatureDaoImpl extends JdbcDaoSupport implements ISystemFeatureDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class SystemFeatureMapper implements RowMapper<SystemFeature>
	{
		
		@Override
		public SystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String title = rs.getString("Title");
			String name = rs.getString("Name");
			int parentID = rs.getInt("ParentID");
			String uclKey = rs.getString("UclKey");
			boolean menuFlag = rs.getBoolean("MenuFlag");
			boolean topFlag = rs.getBoolean("TopFlag");
			String menuURI = rs.getString("MenuURI");
			int pageSize = rs.getInt("PageSize");
			String remark = rs.getString("Remark");
			
			return new SystemFeature(iD,title,name,parentID,uclKey,menuFlag,topFlag,menuURI,pageSize,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public SystemFeatureDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public SystemFeatureDaoImpl(DataSource dataSource) {
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
	 * ��ѯ���е�SystemFeature��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_SystemFeature";
	
	/**
	 * ��ѯ���ж���ϵͳ������Ϣ��SQL���
	 */
	private final String SQL_SELECT_TopSystemFeatures = "SELECT ID,ParentID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,PageSize,Remark FROM  DD_SystemFeature  WHERE TopFlag=1  ORDER BY ID ";
	
	/**
	 * ��ѯָ�����������ϵͳ������Ϣ��SQL���
	 */
	private final String SQL_SELECT_ChildSystemFeatures = "SELECT ID,ParentID,Title,Name,ParentID,UclKey,MenuFlag,TopFlag,MenuURI,PageSize,Remark FROM DD_SystemFeature WHERE ParentID = ? ORDER BY ID ";
	
	@Override
	public boolean delete(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<SystemFeature> pSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "SystemFeature��JDBC����Դ�Ƿ���ȷ����ע��");
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<SystemFeature> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new SystemFeatureMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					pSystemFeatures.addAll(pEntitys);
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

	@Override
	public boolean findByID(int pID, SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(SystemFeature pSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public boolean findAllSystemFeature(Map<String, SystemFeature> systemFeatures, ErrInfo pErrInfo) {
		
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

				//���ҵ�����˵���Ȩ
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<SystemFeature> pSystemFeatures=jdbcTemplate.query(SQL_SELECT_TopSystemFeatures,new SystemFeatureMapper());

				//���ز�ѯ���
				if (pSystemFeatures.size() > 0)
				{
					for (SystemFeature systemFeature : pSystemFeatures)
					{
						systemFeatures.put(systemFeature.getUclKey(), systemFeature);
						
						//��������Ȩ���¼��˵�����
						LinkedHashMap<String, SystemFeature> childMenus = new LinkedHashMap<String, SystemFeature>();
						if (findForChildSystemFeature(systemFeature.getID(), childMenus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"���ҹ���ģ��"+systemFeature.getID()+"�¼��ӹ���ģ��ʧ��: ");
						}
						else
						{
							//�����ȡ�ĸ��û���Ȩ���¼��˵�����
							if (childMenus.size()>0)
							{
								systemFeature.setChildSystemFeatures(childMenus);
							}
						}
					}
				}

				//���پֲ�����
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
	 * ����ָ������ģ����¼��˵���Ȩ����
	 * @param parentID ָ���Ĳ˵����ܱ��
	 * @param childMenus ���ز��ҳɹ����¼��˵�����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean findForChildSystemFeature(int parentID, LinkedHashMap<String, SystemFeature> childMenus, ErrInfo pErrInfo)
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
				

				pErrPos =2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<SystemFeature> pSystemFeatures = jdbcTemplate.query(SQL_SELECT_ChildSystemFeatures,new SystemFeatureMapper(),parentID);
				
				//���ز�ѯ���
				if (pSystemFeatures.size()>0)
				{
					for (SystemFeature item : pSystemFeatures)
					{
						childMenus.put(item.getUclKey(), item);
						
						pErrPos=2;
						//�����ݹ���Ҹ��¼��˵�������һ���˵���ֱ��û���¼��˵�Ϊֹ
						LinkedHashMap<String, SystemFeature> pChildren=new LinkedHashMap<String, SystemFeature>();
						if (findForChildSystemFeature(item.getID(), pChildren, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"����ģ�� "+item.getParentID()+"���� "+ item.getName() +" ���¼���Ȩ�˵�ʧ��: ");
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
				pSystemFeatures=null;
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
	public boolean findByUCLKey(SystemFeature systemFeature, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM  DD_SystemFeature  WHERE UclKey=?";
				List<SystemFeature> systemFeatures = getJdbcTemplate().query(sql, new SystemFeatureMapper(), systemFeature.getUclKey());
				if (systemFeatures.size() > 0) {
					systemFeature.cloneFrom(systemFeatures.get(0));
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
}
