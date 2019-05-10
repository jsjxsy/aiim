/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IUserRoleDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;

/**
 * ��ɫ��Ϣ�ֵ���DAOʵ��(SQL Server ƽ̨ʵ��)
 *
 */
public class UserRoleDaoimpl extends JdbcDaoSupport implements IUserRoleDao {
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class UserRoleMapper implements RowMapper<UserRole>
	{
		
		@Override
		public UserRole mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			int systemRolesFlag = rs.getInt("SystemRolesFlag");
			String remark = rs.getString("Remark");
			
			return new UserRole(iD,name,systemRolesFlag,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public UserRoleDaoimpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserRoleDaoimpl(DataSource dataSource) {
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
	 * ��ѯ���н�ɫ��Ϣ��SQL���
	 * 
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_UserRoles  ORDER BY ID ASC";

	
	/**
	 * �����ɫ�ֵ���¼��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO DD_UserRoles(Name,SystemRolesFlag,Remark) VALUES(:Name,:SystemRolesFlag,:Remark)";
	
	/**
	 * ɾ��DD_UserRoles���SQL���
	 */
	private final String SQL_DELETE = "DELETE a FROM DD_UserRoles  u , DDR_UserRoles_ArchivesSecrecy  a WHERE u.ID = ? AND a.RolesID = u.ID "+
									" DELETE  t FROM DD_UserRoles  u , DDR_UserRoles_ArchivesType  t WHERE u.ID = ? AND t.RolesID = u.ID "+
									" DELETE  s FROM DD_UserRoles  u , DDR_UserRoles_SystemFeature s WHERE u.ID = ? AND s.RolesID = u.ID"+
									" DELETE  us FROM DD_UserRoles  u , UserRolesInfo  us WHERE u.ID = ? AND us.RolesID = u.ID"+
	                                " DELETE FROM DD_UserRoles WHERE ID = ?";
	
	/**
	 * ����DD_UserRoles��SQL���
	 */
	private final String SQL_UPDATE= "UPDATE DD_UserRoles SET Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark WHERE ID = :ID";
	
	/**
	 * ����Id�Ų���userRole�����SQL���
	 */
	private final String SQL_SELECT_By_ID = "SELECT * FROM DD_UserRoles where ID = ?";
	
	
	/**
	 *��ѯδ���뵽�û���ɫ��Ϣ�б��еĽ�ɫSQL���
	 */ 
	private final String SQL_SELECT_Useres_By_UserID = "SELECT ID,Name,SystemRolesFlag,Remark FROM DD_UserRoles  WHERE  ID NOT IN  (SELECT A.RolesID FROM UserRolesInfo A WHERE A.UserID = ?  ) ORDER BY ID";
	
	@Override
	public boolean delete(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
             
			if (userRole == null) {
				pFlag = false;
				pErrInfo.getContent().append("��ɫ����Ƿ�Ϊ��!");
			}
			
			if (userRole.getID() == 0) {
				pFlag = false;
				pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ��@");
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE, userRole.getID(),userRole.getID(),userRole.getID(),userRole.getID(),userRole.getID());
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
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<UserRole> userRoles, ErrInfo pErrInfo) {
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
				List<UserRole> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new UserRoleMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					userRoles.addAll(pEntitys);
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
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#findByID(int, com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, UserRole userRole, ErrInfo pErrInfo) {
		// 
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
				//"SELECT * FROM DD_UserRoles where ID = ?";
				List<UserRole> pUserRoles=jdbcTemplate.query(SQL_SELECT_By_ID,new UserRoleMapper(),pID);
				if (pUserRoles.size()>0)
				{
					//���ز�ѯ���
					userRole.cloneFrom(pUserRoles.get(0));
				}
				
				//���پֲ�����
				pUserRoles=null;
				jdbcTemplate=null;
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
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#save(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(UserRole userRole, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("userRole����Daoע��Ƿ�Ϊ��!");
			}
            
			if (userRole == null) {
				pFlag=false;
				pErrInfo.getContent().append("userRole����Ƿ�Ϊ��!");
			}
			if (userRole.getName() == null || userRole.getName().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("��ɫ���ƷǷ�Ϊ��!");
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
			   //INSERT INTO DD_UserRoles(Name,SystemRolesFlag,Remark) VALUES(:Name,:SystemRolesFlag,:Remark)
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Name", userRole.getName(),Types.VARCHAR);
				mapSqlParameterSource.addValue("SystemRolesFlag", Types.INTEGER);
				mapSqlParameterSource.addValue("Remark", userRole.getRemark(),Types.VARCHAR);
				namedParameterJdbcTemplate.update(SQL_INSERT, mapSqlParameterSource);
				//���پֲ�����
				namedParameterJdbcTemplate = null;
				mapSqlParameterSource = null;
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
	 * @see com.orifound.aiim.dal.dao.IUserRoleDao#update(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(UserRole userRole, ErrInfo pErrInfo) {
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("userRole��Daoע��Ƿ�Ϊ��!");
			}
			
			if (userRole == null) {
				pFlag=false;
				pErrInfo.getContent().append("userRole����Ƿ�Ϊ��!");
			}
			if (userRole.getName() == null || userRole.getName().equals("")) {
				pFlag=false;
				pErrInfo.getContent().append("��ɫ���ƷǷ�Ϊ��!");
			}
	
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//UPDATE DD_UserRoles SET (Name=:Name,SystemRolesFlag=:SystemRolesFlag,Remark=:Remark) WHERE ID=:ID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Name", userRole.getName());
				mapSqlParameterSource.addValue("SystemRolesFlag", userRole.getSystemRolesFlag().getEnumValue());
				mapSqlParameterSource.addValue("Remark", userRole.getRemark());
				mapSqlParameterSource.addValue("ID", userRole.getID());
				namedParameterJdbcTemplate.update(SQL_UPDATE, mapSqlParameterSource);

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				mapSqlParameterSource = null;
				
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
	
	


	/**
	 * ����ϵͳ���н�ɫ��ѯ��ɫ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_findBySystemRolesFlag = "SELECT * FROM DD_UserRoles WHERE SystemRolesFlag=? ORDER BY ID ASC";;
	
	@Override
	public boolean findBySystemRolesFlag(int systemRolesFlag, List<UserRole> userRoles, ErrInfo pErrInfo) {
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
				List<UserRole> pEntitys=jdbcTemplate.query(SQL_SELECT_findBySystemRolesFlag,new UserRoleMapper(),systemRolesFlag);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					userRoles.addAll(pEntitys);
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
	public boolean findRolesNotInUserID(int pUserID, List<UserRole> userRoles, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ɫ��Ϣ��JDBC����Դ������ע��ʧ�ܣ�");
			}

			// ����û�����Ƿ�Ϸ�
			if (pFlag) {
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ0");
				}
			}

			// ִ��SQL���
			if (pFlag) {
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRole> puserRoles = jdbcTemplate.query(SQL_SELECT_Useres_By_UserID, new UserRoleMapper(), pUserID);

				// ���ز�ѯ���
				if (puserRoles.size() > 0) {
					userRoles.addAll(puserRoles);
				}

				// ���پֲ�����
				puserRoles = null;
				jdbcTemplate = null;
			}
		} catch (BadSqlGrammarException e) {
			// SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			// �����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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

}
