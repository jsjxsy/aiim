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

import com.orifound.aiim.dal.dao.IEvaluateLevelDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateLevel;

/**
 * ���˵ȼ��ֵ��(DD_EvaluateLevel)��DAOʵ���ࣨSQL Serverƽ̨��
 * @author tyb
 *
 */
public class EvaluateLevelDaoImpl extends JdbcDaoSupport implements IEvaluateLevelDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class EvaluateLevelMapper implements RowMapper<EvaluateLevel>
	{
		
		@Override
		public EvaluateLevel mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new EvaluateLevel(iD,name,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public EvaluateLevelDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public EvaluateLevelDaoImpl(DataSource dataSource) {
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
	
	// Table Name: DD_EvaluateLevel
	// Columns List,Can Used in SELECT SQL: ID,Name,Remark
	// Columns List,Can Used in INSERT SQL: :ID,:Name,:Remark
	// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Remark=:Remark
	
	/**
	 * ���뿼�˵ȼ���Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO DD_EvaluateLevel(Name,Remark) VALUES(:Name,:Remark)";
	
	/**
	 * ��ѯ���п��˵ȼ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_EvaluateLevel ORDER BY ID";
	
	/**
	 * ����������ѯ���˵ȼ��ֵ���SQL���
	 */
	private final String SQL_SELECT_ByID = "SELECT * FROM DD_EvaluateLevel WHERE ID=?";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#delete(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<EvaluateLevel> evaluateLevels, ErrInfo pErrInfo) {
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
				List<EvaluateLevel> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new EvaluateLevelMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					for(EvaluateLevel level : pEntitys) {
						evaluateLevels.add(level);
					}
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#findByID(int, com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵ȼ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateLevel == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˵ȼ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鿼�˵ȼ����� �Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateLevel.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���˵ȼ���Ϣ��ID �ֶηǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<EvaluateLevel> pEntitys=jdbcTemplate.query(SQL_SELECT_ByID,new EvaluateLevelMapper(),evaluateLevel.getID());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					evaluateLevel.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#save(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵ȼ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateLevel == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˵ȼ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鿼�˵ȼ����� �Ƿ�Ϊ��
			if (pFlag) {
				if(StringTool.checkNull(evaluateLevel.getName()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���˵ȼ���Ϣ�µȼ����� �ֶηǷ�Ϊ�ա�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Name", evaluateLevel.getName(), Types.VARCHAR);
				paramSource.addValue("Remark", evaluateLevel.getRemark(), Types.VARCHAR);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				//���ٱ���
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateLevelDao#update(com.orifound.aiim.entity.EvaluateLevel, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(EvaluateLevel evaluateLevel, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}


