package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import com.orifound.aiim.dal.dao.IUserRolesArchivesTypeDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesType;

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
public class UserRolesArchivesTypeDaoImpl extends JdbcDaoSupport implements IUserRolesArchivesTypeDao {
	
	/**
	 * ���캯��
	 */
	public UserRolesArchivesTypeDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserRolesArchivesTypeDaoImpl(DataSource dataSource) {
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class UserRolesArchivesTypeMapper implements RowMapper<UserRolesArchivesType>
	{
		
		@Override
		public UserRolesArchivesType mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int rolesID = rs.getInt("RolesID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			boolean rightFlag_ArchivesInfo = rs.getBoolean("RightFlag_ArchivesInfo");
			boolean rightFlag_AttachedFile = rs.getBoolean("RightFlag_AttachedFile");
			boolean rightFlag_PaperArchives = rs.getBoolean("RightFlag_PaperArchives");
			
			return new UserRolesArchivesType(iD,rolesID,archivesTypeID,rightFlag_ArchivesInfo,rightFlag_AttachedFile,rightFlag_PaperArchives);
		}
	}
	/**
	 * ��ѯ�û���ɫ�ĵ�������Ȩ�ޱ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DDR_UserRoles_ArchivesType ";
	
	/**
	 * �����û���ɫ�ĵ�������Ȩ�ޱ���Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO DDR_UserRoles_ArchivesType(RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives) VALUES(:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives)";
	
	/**
	 * ɾ���û���ɫ�ĵ�������Ȩ�ޱ���Ϣ��SQL������ID
	 */
	private final String SQL_DELETE = "DELETE FROM DDR_UserRoles_ArchivesType WHERE ID = ?";
	
	/**
	 * ���ݽ�ɫ��Ų�ѯ��SQL���
	 */
	private final String SQL_SELECT_By_RoleID = "SELECT * FROM DDR_UserRoles_ArchivesType WHERE RolesID = ?;";
	
	/**
	 * ɾ��ָ����ɫ�����еĵ�������Ȩ�ޱ���Ϣ��SQL���
	 */
	private final String SQL_DELETE_By_RoleID = "DELETE FROM DDR_UserRoles_ArchivesType WHERE RolesID = ?";
	
	@Override
	public boolean delete(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�޵�ʵ�������Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�޵ı�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_DELETE,pUserRolesArchivesType.getID());

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
	public boolean findAll(List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<UserRolesArchivesType> userRolesArchivesTypes = jdbcTemplate.query(SQL_SELECT_ALL, new UserRolesArchivesTypeMapper());
				if(userRolesArchivesTypes.size() > 0){
					userRolesArchivesTypes.addAll(pUserRolesArchivesTypes);
				}
				//���پֲ�����
				jdbcTemplate = null;
				userRolesArchivesTypes = null;
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
	public boolean findByID(int pID, UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������Ƿ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType.getRolesID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������Ľ�ɫ��ŷǷ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (pUserRolesArchivesType.getArchivesTypeID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������ĵ��������ŷǷ�Ϊ��!");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//INSERT INTO DDR_UserRoles_ArchivesType(RolesID,ArchivesTypeID,RightFlag_ArchivesInfo,RightFlag_AttachedFile,RightFlag_PaperArchives) VALUES(:RolesID,:ArchivesTypeID,:RightFlag_ArchivesInfo,:RightFlag_AttachedFile,:RightFlag_PaperArchives)
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("RolesID", pUserRolesArchivesType.getRolesID(), Types.INTEGER);
				sqlParameterSource.addValue("ArchivesTypeID", pUserRolesArchivesType.getArchivesTypeID(), Types.INTEGER);
				sqlParameterSource.addValue("RightFlag_ArchivesInfo", pUserRolesArchivesType.getRightFlag_ArchivesInfo(),Types.BIT);
				sqlParameterSource.addValue("RightFlag_AttachedFile", pUserRolesArchivesType.getRightFlag_AttachedFile(),Types.BIT);
				sqlParameterSource.addValue("RightFlag_PaperArchives", pUserRolesArchivesType.getRightFlag_PaperArchives(),Types.BIT);
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

	@Override
	public boolean update(UserRolesArchivesType pUserRolesArchivesType, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByRoleID(int pRoleID, List<UserRolesArchivesType> pUserRolesArchivesTypes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���������Ȩ�ޱ��ʵ�������Ľ�ɫ��ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//INSERT INTO DDR_UserRoles_ArchivesType(RolesID,ArchivesTypeID,
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				 List<UserRolesArchivesType> userRolesArchivesTypes = jdbcTemplate.query(SQL_SELECT_By_RoleID, new UserRolesArchivesTypeMapper(), pRoleID);
				 if (userRolesArchivesTypes.size() > 0) {
					pUserRolesArchivesTypes.addAll(userRolesArchivesTypes);
				}
				userRolesArchivesTypes = null;
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
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (pRoleID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ�ĵ�������Ȩ�޵�ʵ����,��ɫ��ŷǷ�Ϊ��!");
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

	
}
