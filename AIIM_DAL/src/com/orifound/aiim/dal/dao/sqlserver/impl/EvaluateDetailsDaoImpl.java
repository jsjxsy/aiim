/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IEvaluateDetailsDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;

/**
 * ������ϸ��(EvaluateDetails)��DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class EvaluateDetailsDaoImpl extends JdbcDaoSupport implements IEvaluateDetailsDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class EvaluateDetailsMapper implements RowMapper<EvaluateDetails>
	{
		public EvaluateDetails mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int evaluateRegID = rs.getInt("EvaluateRegID");
			int evaluateItemID = rs.getInt("EvaluateItemID");
			int score = rs.getInt("Score");
			
			return new EvaluateDetails(evaluateRegID,evaluateItemID,score);
		}
	}
	
	/**
	 * ���캯��
	 */
	public EvaluateDetailsDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public EvaluateDetailsDaoImpl(DataSource dataSource) {
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
	// Table Name: EvaluateDetails
	// Columns List,Can Used in SELECT SQL: EvaluateRegID,EvaluateItemID,Score
	// Columns List,Can Used in INSERT SQL: :EvaluateRegID,:EvaluateItemID,:Score
	// Columns List,Can Used in UPDATE SQL: EvaluateRegID=:EvaluateRegID,EvaluateItemID=:EvaluateItemID,Score=:Score
	
	/**
	 * ���뿼����ϸ�����Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO EvaluateDetails(EvaluateRegID,EvaluateItemID) VALUES(:EvaluateRegID,:EvaluateItemID)";
	
	/**
	 * ���뵱ǰ��ȵĿ�����ϸ�����Ϣ��SQL���
	 */
	private final String SQL_INSERT_BYYEAR = "INSERT INTO EvaluateDetails(EvaluateRegID,EvaluateItemID) SELECT e.ID,i.ID FROM EvaluateRegister e,DD_EvaluateItem i WHERE e.Years=:Years";
	
	/**
	 * ͨ�����˼�¼��Ų�ѯ������ϸ��Ϣ��SQL���
	 */
	private final String SQL_FIND_ByEvaluateRegID = "SELECT ed.EvaluateRegID,ed.EvaluateItemID,de.Name,ed.Score,de.Score maxScore FROM EvaluateDetails ed,DD_EvaluateItem de WHERE ed.EvaluateItemID=de.ID AND EvaluateRegID=?";
	
	/**
	 * ���¿�����ϸ�����Ϣ��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE EvaluateDetails SET Score=:Score WHERE EvaluateRegID=:EvaluateRegID AND EvaluateItemID=:EvaluateItemID";
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#delete(com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean delete(EvaluateDetails EvaluateDetails, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findAll(List<EvaluateDetails> evaluateDetails, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#findByID(int, com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findByEvaluateRegID(int evaluateRegID, List<EvaluateDetails> evaluateDetailss, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				if(evaluateDetailss == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ���ϷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				if(evaluateRegID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�����Ƿ�Ϊ�ա�");
				}
			}
			

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_FIND_ByEvaluateRegID,evaluateRegID);

				//���ز�ѯ���
				EvaluateDetails details = null;
				if (pEntitys.size() > 0) {
					for(Map<String, Object> pEntity : pEntitys) {
						int evaluateID = Integer.valueOf(pEntity.get("EvaluateRegID").toString());
						int evaluateItemID = Integer.valueOf(pEntity.get("EvaluateItemID").toString());
						int maxScore = Integer.valueOf(pEntity.get("maxScore").toString());
						String name = pEntity.get("Name").toString();
						int score = 0;
						Object scoreObj = pEntity.get("Score");
						//�ж��Ƿ��з���
						if(scoreObj != null) {
							score = Integer.valueOf(scoreObj.toString());
						}
						details = new EvaluateDetails(evaluateID, evaluateItemID, score);
						details.setTag(name);
						details.setMaxScore(maxScore);
						evaluateDetailss.add(details);
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#save(com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean save(EvaluateDetails evaluateDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鿼�˼�¼����Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateDetails.getEvaluateRegID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ����µĿ��˼�¼����ֶηǷ�Ϊ�ա�");
				}
			}
			
			//��鿼�������Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateDetails.getEvaluateItemID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ����µĿ��������ֶηǷ�Ϊ�ա�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("EvaluateRegID", evaluateDetails.getEvaluateRegID(), Types.INTEGER);
				paramSource.addValue("EvaluateItemID", evaluateDetails.getEvaluateItemID(), Types.INTEGER);

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
	 * @see com.orifound.aiim.dal.dao.IEvaluateDetailsDao#update(com.orifound.aiim.entity.EvaluateDetails, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean update(EvaluateDetails evaluateDetails, ErrInfo pErrInfo) {
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
				paramSource.addValue("Score", evaluateDetails.getScore(), Types.INTEGER);
				paramSource.addValue("EvaluateRegID", evaluateDetails.getEvaluateRegID(), Types.INTEGER);
				paramSource.addValue("EvaluateItemID", evaluateDetails.getEvaluateItemID(), Types.INTEGER);

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

	/**
	 * �����ض���ȵĿ�����Ϣ ����
	 */
	@Override
	public boolean insertByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�ж�year�����Ƿ�Ϊ����
			if (pFlag) {
				if(year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("������ݲ����������ַǷ���");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Years", year, Types.VARCHAR);
				
				namedParameterJdbcTemplate.update(SQL_INSERT_BYYEAR, mapSqlParameterSource);
				
				System.out.println("���뿼����ϸ="+SQL_INSERT_BYYEAR);

				//���پֲ�����
				mapSqlParameterSource = null;
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
}
