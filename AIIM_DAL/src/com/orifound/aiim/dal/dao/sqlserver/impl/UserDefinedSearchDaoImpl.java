/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
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

import com.orifound.aiim.dal.dao.IUserDefinedSearchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserDefinedSearch;

/**
 * �û��Զ���������ѯ���ݷ���ʵ����
 * @author Administrator
 *
 */
public class UserDefinedSearchDaoImpl extends JdbcDaoSupport implements IUserDefinedSearchDao {
	


	/**
	 * ��ѯ�������ʵ�����ӳ����
	 * 
	 */
	private class UserDefinedSearchMapper implements RowMapper<UserDefinedSearch>
	{
		
		@Override
		public UserDefinedSearch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int userID = rs.getInt("UserID");
			String name = rs.getString("Name");
			String archivesIDString = rs.getString("ArchivesIDString");
			String whereString = rs.getString("WhereString");			
			return new UserDefinedSearch(iD,userID,name,archivesIDString,whereString);
		}
	}
	
	/**
	 * ���캯��
	 */
	public UserDefinedSearchDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public UserDefinedSearchDaoImpl(DataSource dataSource) {
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
	 * ɾ���û��Զ���������ѯ��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM UserDefinedSearch WHERE ID = ?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#delete(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {	
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
			
				if (pFlag) {
					jdbcTemplate.update(SQL_DELETE,userDefinedSearch.getID());
				}

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

	/**
	 * �����Զ���������Ψһ��ʶID��ѯ�Զ������������SQL���
	 */
	private final String SQL_SELECT_By_ID = "SELECT * FROM UserDefinedSearch WHERE ID = ?";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#findUserDefinedSearchByID(int, com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchByID(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
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
				List<UserDefinedSearch> userDefinedSearchs = jdbcTemplate.query(SQL_SELECT_By_ID,new UserDefinedSearchMapper(),userDefinedSearch.getID());

				//���ز�ѯ���
				if (userDefinedSearchs.size() > 0) {
					userDefinedSearch.cloneFrom(userDefinedSearchs.get(0));
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

	/**
	 * ͨ��userID��ѯ�Զ���������ѯ��SQL���
	 */
	private final String SQL_SELECT_By_userID = "SELECT * FROM UserDefinedSearch WHERE UserID = ?";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#findUserDefinedSearchsByUserID(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findUserDefinedSearchsByUserID(int userID,List<UserDefinedSearch> userDefinedSearchs, ErrInfo pErrInfo) {

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
				List<UserDefinedSearch> searchs =jdbcTemplate.query(SQL_SELECT_By_userID,new UserDefinedSearchMapper(),userID);
System.out.println("DAL--->searchs:-->" + searchs.size());

				//���ز�ѯ���
				if (searchs.size() > 0) {
					userDefinedSearchs.addAll(searchs);	
					
				}
System.out.println("DAL--->userDefinedSearchs:-->" + userDefinedSearchs.size());
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
	
	/**
	 * �����û��Զ���������ѯ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO UserDefinedSearch(UserID,Name,ArchivesIDString,WhereString) VALUES(:UserID,:Name,:ArchivesIDString,:WhereString)";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IUserDefinedSearchDao#save(com.orifound.aiim.entity.UserDefinedSearch, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addUserDefinedSearchs(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo) {

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
				//List<UserDefinedSearch> userDefinedSearchs = jdbcTemplate.query(SQL_DELETE,new UserDefinedSearchMapper(),userDefinedSeach.getID());

				// Table Name: UserDefinedSearch
				// Columns List,Can Used in SELECT SQL: ID,UserID,Name,ArchivesIDString,WhereString
				// Columns List,Can Used in INSERT SQL: :ID,:UserID,:Name,:ArchivesIDString,:WhereString
				// Columns List,Can Used in UPDATE SQL: ID=:ID,UserID=:UserID,Name=:Name,ArchivesIDString=:ArchivesIDString,WhereString=:WhereString

				pErrPos = 2;	
				//ִ��SQL���
				if (pFlag) {								
					NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
					MapSqlParameterSource paramSource=new MapSqlParameterSource();
					paramSource.addValue("UserID", userDefinedSearch.getUserID(),Types.INTEGER);
					paramSource.addValue("Name", userDefinedSearch.getName(),Types.VARCHAR);	
					paramSource.addValue("ArchivesIDString", userDefinedSearch.getArchivesIDString(),Types.VARCHAR);
					paramSource.addValue("WhereString", userDefinedSearch.getWhereString(),Types.VARCHAR);
					
				
					namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);					
					//���پֲ�����
					paramSource=null;
					namedParameterJdbcTemplate = null;
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
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	
	
	/**
	 * ����Զ������������Ƿ����
	 */
	private final String SQL_QUERY_CheckNameExist = "SELECT * FROM UserDefinedSearch WHERE Name = ? AND UserID = ? ";

	
	@Override
	public boolean checkQueryNameExist(UserDefinedSearch userDefinedSearch, ErrInfo pErrInfo){
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
				List<UserDefinedSearch> searchs =jdbcTemplate.query(SQL_QUERY_CheckNameExist,new UserDefinedSearchMapper(),userDefinedSearch.getName(),userDefinedSearch.getUserID());
System.out.println("DAL--->searchs:-->" + searchs.size());

				if(searchs.size()==0){//��û�м�¼ʱ����ID��0
					userDefinedSearch.setID(0);
				}
				if(searchs.size()>0){
					userDefinedSearch.cloneFrom(searchs.get(0));
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
	
	
	
}
