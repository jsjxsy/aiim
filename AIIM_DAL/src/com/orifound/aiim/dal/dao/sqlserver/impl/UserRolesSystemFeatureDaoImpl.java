package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IUserRolesSystemFeatureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesSystemFeature;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class UserRolesSystemFeatureDaoImpl extends JdbcDaoSupport implements IUserRolesSystemFeatureDao {
	
	/**
	 * ���캯��
	 */
	public UserRolesSystemFeatureDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserRolesSystemFeatureDaoImpl(DataSource dataSource) {
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
	 * ��ѯ�����û���ɫ��ϵͳ����Ȩ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DDR_UserRoles_SystemFeature ";
	
	/**
	 * ɾ���û���ɫ��ϵͳ����Ȩ����Ϣ��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM DDR_UserRoles_SystemFeature WHERE ID = ?";
	
	/**
	 * ɾ���û���ɫ��ϵͳ����Ȩ����Ϣ��SQL���
	 */
	private final String SQL_DELETE_By_RoleID = "DELETE FROM DDR_UserRoles_SystemFeature WHERE RolesID = ?";
	
	
	/**
	 * �����û���ɫ��ϵͳ����Ȩ����Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO DDR_UserRoles_SystemFeature(RolesID,FeatureID) VALUES(:RolesID,:FeatureID)";
	
	/**
	 * ���ݽ�ɫID��ѯ�û���ɫ��ϵͳ����Ȩ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_By_RoleID = "SELECT * FROM DDR_UserRoles_SystemFeature   WHERE RolesID = ?;";
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */

	private class UserRolesSystemFeatureMapper implements RowMapper<UserRolesSystemFeature>
	{
		
		@Override
		public UserRolesSystemFeature mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int featureID = rs.getInt("FeatureID");
			
			return new UserRolesSystemFeature(iD,rolesID,featureID);
		}
	}
	@Override
	public boolean delete(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "JDBC����Դ�Ƿ���ȷ����ע��ʧ�ܣ����JDBC����Դ�Ƿ���ȷ����ע��:");
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�޵Ķ���Ƿ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�޵ı�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,pUserRolesSystemFeature.getID());
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
	public boolean deleteByRoleID(int pRoleID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "JDBC����Դ�Ƿ���ȷ����ע��ʧ�ܣ����JDBC����Դ�Ƿ���ȷ����ע��:");
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�޵Ķ���,��ɫ��ŷǷ�Ϊ�գ�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE_By_RoleID,pRoleID);
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
	public boolean findAll(List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
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
				List<UserRolesSystemFeature> userRolesSystemFeatures = jdbcTemplate.query(SQL_SELECT_ALL,new UserRolesSystemFeatureMapper());

				//���ز�ѯ���
				if (userRolesSystemFeatures.size() > 0) {
					pUserRolesSystemFeatures.addAll(userRolesSystemFeatures);
				}

				//���پֲ�����
				jdbcTemplate = null;
				userRolesSystemFeatures = null;
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
	public boolean findByID(int pID, UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "JDBC����Դ�Ƿ���ȷ����ע��ʧ�ܣ����JDBC����Դ�Ƿ���ȷ����ע��:");
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�޵Ķ���Ƿ�Ϊ�գ�");
				}
			}
			
			if (pFlag) {
				if (pUserRolesSystemFeature.getFeatureID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ����Ȩ�޵�ϵͳ���ܱ�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//INSERT INTO DDR_UserRoles_SystemFeature(RolesID,FeatureID) VALUES(:RolesID,:FeatureID)
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlSource=new MapSqlParameterSource();
				sqlSource.addValue("RolesID", pUserRolesSystemFeature.getRolesID(),Types.INTEGER);
				sqlSource.addValue("FeatureID",  pUserRolesSystemFeature.getFeatureID(),Types.INTEGER);
				namedParameterJdbcTemplate.update(SQL_INSERT, sqlSource);
				//���پֲ�����
				namedParameterJdbcTemplate = null;
				sqlSource = null;

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
	public boolean update(UserRolesSystemFeature pUserRolesSystemFeature, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByRoleID(int pRoleID, List<UserRolesSystemFeature> pUserRolesSystemFeatures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			if (pFlag) {
				if(pRoleID <= 0){
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��ϵͳ���ܣ���ɫ��ŷǷ�Ϊ0��");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRolesSystemFeature> userRolesSystemFeatures = jdbcTemplate.query(SQL_SELECT_By_RoleID,new UserRolesSystemFeatureMapper(),pRoleID);

				//���ز�ѯ���
				if (userRolesSystemFeatures.size() > 0) {
					pUserRolesSystemFeatures.addAll(userRolesSystemFeatures);
				}

				//���پֲ�����
				jdbcTemplate = null;
				userRolesSystemFeatures = null;
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
