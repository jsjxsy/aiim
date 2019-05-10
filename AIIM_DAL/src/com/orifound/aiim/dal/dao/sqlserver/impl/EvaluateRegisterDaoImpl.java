/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IEvaluateDetailsDao;
import com.orifound.aiim.dal.dao.IEvaluateRegisterDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateDetails;
import com.orifound.aiim.entity.EvaluateRegister;
import com.orifound.aiim.entity.EvaluateRegisterVO;

/**
 * ���˵ǼǱ� (EvaluateRegister)��DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class EvaluateRegisterDaoImpl extends JdbcDaoSupport implements IEvaluateRegisterDao {
	
	@Autowired
	private IEvaluateDetailsDao evaluateDetailsDao;
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class EvaluateRegisterMapper implements RowMapper<EvaluateRegister>
	{
		public EvaluateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String years = rs.getString("Years");
			int userID = rs.getInt("UserID");
			int dutyID = rs.getInt("DutyID");
			int score = rs.getInt("Score");
			int levelID = rs.getInt("LevelID");
			String content = rs.getString("Content");
			Date registerDate = rs.getTimestamp("RegisterDate");
			int checkerUserID = rs.getInt("CheckerUserID");
			int checkerDutyID = rs.getInt("CheckerDutyID");
			
			return new EvaluateRegister(iD,years,userID,dutyID,score,levelID,content,registerDate,checkerUserID,checkerDutyID);
		}
	}
	
	/**
	 * ��ѯ�������ʵ����ʾ���ӳ���������������DAOʵ������
	 */
	private class EvaluateRegisterVOMapper implements RowMapper<EvaluateRegisterVO>
	{
		public EvaluateRegisterVO mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String realName = rs.getString("RealName");
			String dutyName = rs.getString("DutyName");
			Integer score = rs.getInt("Score");
			String evaluateLevelName = rs.getString("EvaluateLevelName");
			String years = rs.getString("years");
			return new EvaluateRegisterVO(iD, realName, dutyName, score, evaluateLevelName, years);
		}
	}
	
	/**
	 * ���캯��
	 */
	public EvaluateRegisterDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public EvaluateRegisterDaoImpl(DataSource dataSource) {
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
	
	// Table Name: EvaluateRegister
	// Columns List,Can Used in SELECT SQL: ID,Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID
	// Columns List,Can Used in INSERT SQL: :ID,:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID
	// Columns List,Can Used in UPDATE SQL: ID=:ID,Years=:Years,UserID=:UserID,DutyID=:DutyID,Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID

	/**
	 * ���뱣���µĿ��˵ȼ���Ϣ��SQL���
	 */
//	private final String SQL_INSERT = "INSERT INTO EvaluateRegister(Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID) VALUES(:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID)";
	private final String SQL_INSERT = "INSERT INTO EvaluateRegister(Years,UserID,DutyID) VALUES(:Years,:UserID,:DutyID)";
	
	/**
	 * ͨ��������ѯ���˵Ǽ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_ByID = "SELECT * FROM EvaluateRegister WHERE ID=?";
	
	/**
	 * ���¿��˵Ǽ���Ϣ��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE EvaluateRegister SET Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID WHERE ID=:ID";
	
	/**
	 * ��ѯ�Ѿ�ͨ�����˵������ȵĿ��˼�¼��SQL���
	 */
	private final String SQL_SELECT_BYMAXYEAR = "SELECT MAX(Years) maxYear FROM EvaluateRegister WHERE RegisterDate IS NOT NULL";
	
	/**
	 * ��ѯ���˼�¼�����ȵ�SQL���
	 */
	private final String SQL_SELECT_MAXYEAR = "SELECT MAX(Years) maxYear FROM EvaluateRegister";
	
//	/**
//	 * ��ҳ��ѯ�ض���ȵĿ��˼�¼��ʾ���SQL���
//	 */
//	private final String SQL_SELECT_BYYEAR_WITHPAGE = "SELECT ID,years, RealName, DutyName, Score, EvaluateLevelName FROM ( SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName,ROW_NUMBER() OVER (ORDER BY e.id ASC) RowNumber FROM EvaluateRegister e " +
//										" LEFT JOIN UserInfo u ON  e.UserID=u.UserID " +
//										" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
//										" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID WHERE e.Years=:years)t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY id ASC";
//	
	/**
	 * ��ѯ�ض���ȵĿ��˼�¼��ʾ���SQL���
	 */
	private final String SQL_SELECT_BYYEAR_WITHNOTPAGE = "SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName FROM EvaluateRegister e " +
										" LEFT JOIN UserInfo u ON  e.UserID=u.UserID " +
										" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
										" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID WHERE e.Years=:years ORDER BY id ASC";
	
	
	/**
	 * ��ȡ���ݿ��д��ڵĿ��˼�¼��ȵ�SQL���
	 */
	private final String SQL_SELECT_EvaluatedYears = "SELECT Years FROM EvaluateRegister GROUP BY Years";
	
	/**
	 * ��ѯ�ض���ȵĿ��˼�¼��������SQL���
	 */
	private final String SQL_SELECT_COUNT_BYYEAR = "SELECT COUNT(1) FROM EvaluateRegister WHERE Years=:years";
	
	/**
	 * ��ѯ�ض���ȵĿ��˼�¼��ʾ���SQL���
	 */
	private final String SQL_SELECT_BYREGID = "SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName FROM EvaluateRegister e " +
										" LEFT JOIN UserInfo u ON  e.UserID=u.UserID " +
										" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
										" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID WHERE e.ID=:evaluateRegID";
	
	
	/**
	 * �����ض���ȵĿ��˼�¼��SQL���
	 * %1$s ��Ȳ���
	 */
	private final String SQL_INSERT_BYYEAR = "INSERT INTO EvaluateRegister(Years,UserID,DutyID) " +
					"SELECT %1$s,u.UserID,u.DutyID FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
					"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
					"AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";
	
	/**
	 * ��ѯ�ض���Ȳ��ڿ��˼�¼����Աid��SQL���
	 */
	private final String SQL_SELECT_NOTIN = "SELECT u.UserID FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
						"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
						"AND u.UserID not in(SELECT UserID FROM EvaluateRegister WHERE Years=%1$s) "+
						"AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";
	
	/**
	 * ׷���ض���ȵĿ��˼�¼��SQL���
	 */
	private final String SQL_INSERT_APPEND_BYYEAR = "INSERT INTO EvaluateRegister(Years,UserID,DutyID) " +
					"SELECT %1$s,u.UserID,u.DutyID FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
					"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
					"AND u.UserID IN(:userIds) AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1";
	
	/**
	 * ׷���ض���ȵĿ��˼���ϸ��SQL���
	 */
	private final String SQL_INSERT_DETAILS_APPEND_BYYEAR = "INSERT INTO EvaluateDetails(EvaluateRegID,EvaluateItemID) SELECT e.ID,i.ID FROM EvaluateRegister e,DD_EvaluateItem i WHERE e.UserID IN(:userIds) AND e.Years=:Years";
	
	/**
	 * ��ҳ�������˵Ǽ���Ϣ��SQL���
	 * %1$s ����������
	 * %2$s ��������ƴ��
	 */
	private final String SQL_SEARCH_EVALUATE_WITHPAGE = "SELECT ID,years, RealName, DutyName, Score, EvaluateLevelName FROM ( " +
			"SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName,ROW_NUMBER() OVER (ORDER BY e.id ASC) RowNumber FROM EvaluateRegister e " +
			" LEFT JOIN UserInfo u ON  e.UserID=u.UserID AND u.UserName like '%1$s'" +
			" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
			" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID %2$s)t WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY id ASC";
	
	/**
	 * ����ҳ�������˵Ǽ���Ϣ��SQL���
	 * %1$s ����������
	 * %2$s ��������ƴ��
	 */
//	private final String SQL_SEARCH_EVALUATE_WITHNOPAGE1 = "SELECT e.ID,e.years,u.RealName RealName,d.Name DutyName,e.Score Score,el.Name EvaluateLevelName FROM EvaluateRegister e " +
//						" LEFT JOIN UserInfo u ON  e.UserID=u.UserID AND u.RealName like '%1$s'" +
//						" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
//						" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID %2$s ORDER BY id ASC";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SEARCH_EVALUATE_WITHNOPAGE = "SELECT t.*,el.Name EvaluateLevelName FROM DD_EvaluateLevel el"
				+" RIGHT JOIN (SELECT e.ID,e.years,e.LevelID,u.RealName RealName,d.Name DutyName,e.Score Score"
				+" FROM EvaluateRegister e, UserInfo u, DD_Duty d"
				+" WHERE e.UserID=u.UserID AND e.DutyID=d.ID  AND e.years=?) t ON t.LevelID=el.ID ORDER BY t.id ASC";
			
	/**
	 * ��ѯ�ܼ�¼����SQL���
	 */
	private final String SQL_SELECT_COUNT = "SELECT count(1) EvaluateLevelName FROM EvaluateRegister e " +
			" LEFT JOIN UserInfo u ON  e.UserID=u.UserID AND u.UserName like '%1$s'" +
			" LEFT JOIN DD_Duty d ON e.DutyID=d.ID " +
			" LEFT JOIN DD_EvaluateLevel el ON e.LevelID=el.ID %2$s";
	
	/**
	 * ����ɾ�����˵Ǽ���Ϣ��SQL���
	 */
	private final String SQL_DELETE_BATCH = "DELETE FROM EvaluateDetails WHERE EvaluateRegID IN(:EvaluateRegIDS);DELETE FROM EvaluateRegister WHERE ID IN(:EvaluateRegIDS)";
	
	/**
	 * �ж��Ƿ���Ҫ׷����Ա
	 * %1$s ��Ȳ���
	 */
	private final String SQL_isAppend = "SELECT COUNT(1) FROM DD_DepartmentInfo dd,UserInfo u,UserRolesInfo ur,DD_UserRoles r " +
					"WHERE dd.ID=u.DepartmentID AND u.UserID=ur.UserID AND ur.RolesID=r.ID " +
					"AND r.SystemRolesFlag<>5 AND r.SystemRolesFlag<>6 AND dd.SystemDepartmentFlag=1 AND u.UserID NOT IN(SELECT DISTINCT UserID FROM EvaluateRegister WHERE Years=?)";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#delete(com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean delete(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findAll(List<EvaluateRegister> evaluateRegisters, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#findByID(int, com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean findByID(int pID, EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鿼�˵Ǽ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��������Ƿ�Ϊ��
			if (pFlag) {
				if(pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�����Ƿ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<EvaluateRegister> pEntitys=jdbcTemplate.query(SQL_SELECT_ByID,new EvaluateRegisterMapper(),pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					evaluateRegister.cloneFrom(pEntitys.get(0));
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#save(com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean save(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鿼�˵Ǽ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�������Ƿ�Ϊ��
			if (pFlag) {
				if(StringTool.checkNull(evaluateRegister.getYears()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�µĿ�������ֶηǷ�Ϊ�ա�");
				}
			}
			
			//��鱻�����˱�� �Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateRegister.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�µı������˱���ֶηǷ�Ϊ�ա�");
				}
			}
			
			//��鱻������ְ���� �Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateRegister.getDutyID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�µı�������ְ�����ֶηǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Years", evaluateRegister.getYears(), Types.VARCHAR);
				paramSource.addValue("UserID", evaluateRegister.getUserID(), Types.INTEGER);
				paramSource.addValue("DutyID", evaluateRegister.getDutyID(), Types.INTEGER);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);

				//���پֲ�����
				paramSource=null;
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
	 * @see com.orifound.aiim.dal.dao.IEvaluateRegisterDao#update(com.orifound.aiim.entity.EvaluateRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	public boolean update(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鿼����Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateRegister == null) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鿼����ϢID�Ƿ�Ϊ��
			if (pFlag) {
				if(evaluateRegister.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("���˵Ǽ���Ϣ��ID�ֶηǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate( getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Score", evaluateRegister.getScore(), Types.INTEGER);
				paramSource.addValue("LevelID", evaluateRegister.getLevelID(), Types.INTEGER);
				paramSource.addValue("Content", evaluateRegister.getContent(), Types.VARCHAR);
				paramSource.addValue("RegisterDate", evaluateRegister.getRegisterDate(), Types.TIMESTAMP);
				paramSource.addValue("CheckerUserID", evaluateRegister.getCheckerUserID(), Types.INTEGER);
				paramSource.addValue("CheckerDutyID", evaluateRegister.getCheckerDutyID(), Types.INTEGER);
				paramSource.addValue("ID", evaluateRegister.getID(), Types.INTEGER);
				
				pErrPos = 3;
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
	
	public boolean findByMaxYear(DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵Ǽ���Ϣ��ʾ�༯�� �Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣ��ʾ�༯�ϷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> resutls = jdbcTemplate.queryForList(SQL_SELECT_BYMAXYEAR);
				
				if(resutls!=null && resutls.size() >= 1) {
					Object maxYearObj = resutls.get(0).get("maxYear");
					if (maxYearObj != null) {
						System.out.println("��ͨ�����˵�������="+maxYearObj);
						
						if (findByYear(maxYearObj.toString(), dataPageInfo, evaluateRegisterVOs, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ҳ��ѯ�ض���ȵĿ��˼�¼��ʾ�� ʧ�ܣ�");
						}
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
	
	@Override
	public boolean findByYear(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		return findByYear(year, null, evaluateRegisterVOs, pErrInfo);
	}
	
	public boolean findByYear(String year, DataPageInfo dataPageInfo, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
			}
			
			//��� ����Ƿ�Ϊ������
			if (pFlag) {
				if (year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ȳ��Ǵ����ַǷ���");
				}
			}
			
			//��� ���˵Ǽ���Ϣ��ʾ�༯�� �Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateRegisterVOs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣ��ʾ�༯�ϷǷ�Ϊ�㡣");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("years", year, Types.VARCHAR);
				
				List<EvaluateRegisterVO> pEntitys = null;
				pEntitys=namedParameterJdbcTemplate.query(SQL_SELECT_BYYEAR_WITHNOTPAGE, mapSqlParameterSource, new EvaluateRegisterVOMapper());	

				if (pEntitys.size() >= 1) {
					evaluateRegisterVOs.addAll(pEntitys);
				}
				
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
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
			}
			
			//�ж�year�����Ƿ�Ϊ����
			if (pFlag) {
				if(year.matches("[0-9]*") == false) {
					pFlag = false;
					pErrInfo.getContent().append("������ݲ����������ַǷ���");
				}
			}
			
			//�ж��Ƿ��Ѿ����ڼ�¼
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("years", year, Types.VARCHAR);
				int count = namedParameterJdbcTemplate.queryForInt(SQL_SELECT_COUNT_BYYEAR, mapSqlParameterSource);
				if(count >= 1) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�Ѿ����ڿ��˵Ǽ���Ϣ ����ʧ�ܣ�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				pErrPos = 3;
				jdbcTemplate.update(String.format(SQL_INSERT_BYYEAR, year));
				
				System.out.println("�����ض���ȵĿ��˼�¼��"+String.format(SQL_INSERT_BYYEAR, year));

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
	public boolean findByEvaluateRegID(int evaluateRegID, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("evaluateRegID", evaluateRegID, Types.INTEGER);

				List<EvaluateRegisterVO> evaluateRegisterVOs = namedParameterJdbcTemplate.query(SQL_SELECT_BYREGID, 
							paramSource, new EvaluateRegisterVOMapper());
				
				//�ж��Ƿ���ڽ��
				if (evaluateRegisterVOs.size() >= 1) {
					evaluateRegisterVO.cloneFrom(evaluateRegisterVOs.get(0));
					
					//��ѯ���˵Ǽ���Ϣ
					EvaluateRegister evaluateRegister = new EvaluateRegister();
					if (findByID(evaluateRegID, evaluateRegister , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ѯ���˵Ǽ���Ϣ ʧ�ܣ�");
					}
					
					//��� ������Ϣ�Ƿ�Ϊ��
					if (evaluateRegister.getID() >= 0) {
						evaluateRegisterVO.setEvaluateRegister(evaluateRegister);
						
						//��ѯ������ϸ��Ϣ
						List<EvaluateDetails> evaluateDetailss = new ArrayList<EvaluateDetails>();
						if (evaluateDetailsDao.findByEvaluateRegID(evaluateRegID, evaluateDetailss , pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ѯ������ϸ��Ϣ ʧ�ܣ�");
						}
						
						//���뿼����ϸ��Ϣ
						if (evaluateDetailss.size() >= 1) {
							evaluateRegisterVO.setDetails(evaluateDetailss);
						}
					}
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
	public boolean insertAppendByYear(String year, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ����Ƿ�Ϊ��
			if (pFlag) {
				if (year==null || "".equals(year.trim())) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������ȷǷ�Ϊ�㡣");
				}
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
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> results = jdbcTemplate.queryForList(String.format(SQL_SELECT_NOTIN, year));
				List<Integer> userIds = null;
				if(results.size() >= 1) {
					userIds = new ArrayList<Integer>();
					for(Map<String, Object> map : results) {
						if(map.get("UserID") != null) {
							userIds.add(Integer.valueOf(map.get("UserID").toString()));
						}
					}
				}
				
				if(userIds!=null && userIds.size()>=1) {
					NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
					MapSqlParameterSource parameterSource = new MapSqlParameterSource();
					parameterSource.addValue("userIds", userIds);
					//׷���ض���ȵĿ��˼�¼
					namedParameterJdbcTemplate.update(String.format(SQL_INSERT_APPEND_BYYEAR, year),parameterSource);
					
					//׷���ض���ȵĿ�����ϸ
					parameterSource.addValue("Years", year);
					namedParameterJdbcTemplate.update(SQL_INSERT_DETAILS_APPEND_BYYEAR,parameterSource);
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
	public boolean search(String evaluateName, int dutyId, String registerDate,
			int minScore, int maxScore, DataPageInfo dataPageInfo,
			List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�ж���С�����Ƿ����������
			if (minScore>=1 && maxScore>=1 && minScore > maxScore) {
				pFlag = false;
				pErrInfo.getContent().append("�������->��С�����Ƿ������������");
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
				String searchName = "%";
				//ƴ��SQL���
				StringBuffer bufferSQL = new StringBuffer(" WHERE e.UserID>=1 ");
				
				//�жϿ����������Ƿ�Ϊ��
				if (StringTool.checkNull(evaluateName)) {
					searchName = searchName+evaluateName+searchName;
				}
				
				//�ж� ְ��Ϊλ��
				if (dutyId >= 1) {
					bufferSQL.append(" AND e.DutyID=").append(dutyId);
				}
				
				//�ж� ��������Ƿ�Ϊ��
				if (StringTool.checkNull(registerDate) && registerDate.length()>=4) {
					bufferSQL.append(" AND e.Years='").append(registerDate.substring(0, 4)).append("'");
				}
				
				//�ж���С�����Ƿ�Ϊ0
				if (minScore >= 1) {
					bufferSQL.append(" AND ISNULL(e.Score,0)>=").append(minScore);
				}
				
				//�ж��������Ƿ�Ϊ0
				if (maxScore >= 1) {
					bufferSQL.append(" AND ISNULL(e.Score,0)<=").append(maxScore);
				}
				
				String localSQL = null;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				
				List<EvaluateRegisterVO> pEntitys = null;
				if(dataPageInfo == null || dataPageInfo.getPageSize() <= 0) {	//��֧�ַ�ҳ��ѯ
					localSQL = String.format(SQL_SEARCH_EVALUATE_WITHNOPAGE, searchName, bufferSQL.toString());
					pEntitys=namedParameterJdbcTemplate.query(localSQL, mapSqlParameterSource, new EvaluateRegisterVOMapper());	
				} else {	//֧�ַ�ҳ��ѯ
					localSQL = String.format(SQL_SEARCH_EVALUATE_WITHPAGE, searchName, bufferSQL.toString());
					mapSqlParameterSource.addValue("beginRow", dataPageInfo.getBeginRow(), Types.INTEGER);
					mapSqlParameterSource.addValue("endRow", dataPageInfo.getEndRow(), Types.INTEGER);
					pEntitys=namedParameterJdbcTemplate.query(localSQL, mapSqlParameterSource, new EvaluateRegisterVOMapper());
				}
				
				System.out.println("localSQL="+localSQL);
				
				if(pEntitys!=null && pEntitys.size() >= 1) {
					evaluateRegisterVOs.addAll(pEntitys);
					
					//��ѯ�ܼ�¼��
					mapSqlParameterSource = new MapSqlParameterSource();
					localSQL = String.format(SQL_SELECT_COUNT, searchName, bufferSQL.toString());
					dataPageInfo.setRowCount(namedParameterJdbcTemplate.queryForInt(localSQL, mapSqlParameterSource));
				}
				
				//���پֲ�����
				pEntitys = null;
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

	@Override
	public boolean deleteBatch(List<Integer> evaluateIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��� ���˵Ǽ���Ϣid�����Ƿ�Ϊ��
			if (pFlag) {
				if (evaluateIds==null || evaluateIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->���˵Ǽ���Ϣid���ϷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("EvaluateRegIDS", evaluateIds);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_DELETE_BATCH, paramSource);
				

				//���پֲ�����
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

	@Override
	public boolean findCountByYear(String year, EvaluateRegisterVO evaluateRegisterVO, ErrInfo pErrInfo) {
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
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("years", year);
				evaluateRegisterVO.setEvaluateCount(namedParameterJdbcTemplate.queryForInt(SQL_SELECT_COUNT_BYYEAR, parameterSource));
				//���پֲ�����
				parameterSource = null;
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

	@Override
	public boolean findEvaluatedYears(List<String> evaluatedYears, ErrInfo pErrInfo) {
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
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_SELECT_EvaluatedYears);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					Object years = null;
					for(Map<String, Object> entity : pEntitys) {
						years = entity.get("Years");
						if(years != null) {
							evaluatedYears.add(years.toString());
						}
					}
				}
				//���پֲ�����
				pEntitys = null;
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
	public boolean search(String year, List<EvaluateRegisterVO> evaluateRegisterVOs, ErrInfo pErrInfo) {
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
				List<EvaluateRegisterVO> pEntitys=jdbcTemplate.query(SQL_SEARCH_EVALUATE_WITHNOPAGE,new EvaluateRegisterVOMapper(),year);
				
				System.out.println("���ݿ�����ȼ������˵Ǽ���Ϣ="+SQL_SEARCH_EVALUATE_WITHNOPAGE);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					evaluateRegisterVOs.addAll(pEntitys);
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
	public boolean findNeedAppend(String currentYear, Integer[] count, ErrInfo pErrInfo) {
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
				count[0] = jdbcTemplate.queryForInt(SQL_isAppend, currentYear);
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
	public boolean findMaxYear(EvaluateRegister evaluateRegister, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> resutls = jdbcTemplate.queryForList(SQL_SELECT_MAXYEAR);
				
				if(resutls!=null && resutls.size() >= 1) {
					Object maxYearObj = resutls.get(0).get("maxYear");
					if (maxYearObj != null) {
						evaluateRegister.setYears(maxYearObj.toString());
					}
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