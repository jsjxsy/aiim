/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������������ϢDAOʵ����
 * @author Administrator
 *
 */
public class ArchivesUsePersonInfoDaoImpl extends JdbcDaoSupport implements IArchivesUsePersonInfoDao {
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesUsePersonInfoMapper implements RowMapper<ArchivesUsePersonInfo>
	{
		
		@Override
		public ArchivesUsePersonInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String iDCardNo = rs.getString("IDCardNo");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String department = rs.getString("Department");
			String tel = rs.getString("Tel");
			String email = rs.getString("Email");
			int areaID = rs.getInt("AreaFlag");
			
			return new ArchivesUsePersonInfo(iD,name,iDCardNo,iDCardTypeID,department,tel,email,areaID);
		}
	}
	
	/**
	 * ���캯��
	 */
	public ArchivesUsePersonInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ArchivesUsePersonInfoDaoImpl(DataSource dataSource) {
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
	 * ��ӵ�����������Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUsePersonInfo(Name,IDCardNo,IDCardTypeID,Department,Tel,Email,AreaFlag) VALUES(:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaFlag)";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#add(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
			
				//���ز�ѯ���
//VALUES(:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaID)";
				//ִ��SQL���
				if (pFlag) {
					pErrPos = 2;				
					NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
					//�󶨲���
					MapSqlParameterSource paramSource=new MapSqlParameterSource();
					paramSource.addValue("Name", archivesUsePersonInfo.getName());
					paramSource.addValue("IDCardNo", archivesUsePersonInfo.getIDCardNo());	
					paramSource.addValue("IDCardTypeID", archivesUsePersonInfo.getIDCardTypeID());
					paramSource.addValue("Department", archivesUsePersonInfo.getDepartment());
					paramSource.addValue("Tel", archivesUsePersonInfo.getTel());
					paramSource.addValue("Email", archivesUsePersonInfo.getEmail());
					paramSource.addValue("AreaFlag", archivesUsePersonInfo.getAreaID());
					
					pErrPos = 3;	
					//ִ��SQL
					GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
					namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);
					//��ִ��SQL���ص���������
					archivesUsePersonInfo.setID(keyHolder.getKey().intValue());

					//���پֲ�����
					paramSource=null;
					namedParameterJdbcTemplate=null;
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
				System.out.println(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#delete(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	// Table Name: ArchivesUsePersonInfo
	// Columns List,Can Used in SELECT SQL: ID,Name,IDCardNo,IDCardTypeID,Department,Tel,Email,AreaID
	// Columns List,Can Used in INSERT SQL: :ID,:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaID
	// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,IDCardNo=:IDCardNo,IDCardTypeID=:IDCardTypeID,Department=:Department,Tel=:Tel,Email=:Email,AreaID=:AreaID

	
	/**
	 * ����Ψһ��Ų�ѯ��������Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByID = "SELECT * FROM ArchivesUsePersonInfo WHERE ID= ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findByID(int, com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
				
			// ���������Ϣ�Ƿ��ʼ��	
		

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
			
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesUsePersonInfo> archivesUsePersonInfos=jdbcTemplate.query(SQL_SELECT_findByID,new ArchivesUsePersonInfoMapper(),archivesUsePersonInfo.getID());
				
				//���ز�ѯ���
				if (archivesUsePersonInfos.size() > 0) {
					archivesUsePersonInfo.cloneFrom(archivesUsePersonInfos.get(0));					
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

	/**
	 * ����������������ѯ�û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByIDCardNo = "SELECT * FROM ArchivesUsePersonInfo WHERE IDCardNo = ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findByName(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByIDCardNo(String IDCardNo, List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo) {
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
				List<ArchivesUsePersonInfo> infos = jdbcTemplate.query(SQL_SELECT_findByIDCardNo,new ArchivesUsePersonInfoMapper(),IDCardNo);
				
				//���ز�ѯ���
				if (infos.size() > 0) {
					System.out.println("infos.size(): "+ infos.size());
					archivesUsePersonInfos.addAll(infos);					
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
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#update(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ����������������ѯ�û���Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByName = "SELECT * FROM ArchivesUsePersonInfo WHERE Name like ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findByName(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByName(String name, List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo) {
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
				List<ArchivesUsePersonInfo> infos = jdbcTemplate.query(SQL_SELECT_findByName,new ArchivesUsePersonInfoMapper(),"%"+name+"%");
				
				//���ز�ѯ���
				if (infos.size() > 0) {
					archivesUsePersonInfos.addAll(infos);					
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
}
