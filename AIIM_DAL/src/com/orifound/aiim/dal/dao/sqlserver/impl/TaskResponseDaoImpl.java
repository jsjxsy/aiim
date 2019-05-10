/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ITaskResponseDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskResponse;

/**
 * ��������㱨��Ϣ�� (TaskResponse)��DAOʵ�֣�SQL Server ƽ̨��
 * @author tyb
 *
 */
public class TaskResponseDaoImpl extends JdbcDaoSupport implements ITaskResponseDao {
	/**
	 * ���캯��
	 */
	public TaskResponseDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public TaskResponseDaoImpl(DataSource dataSource) {
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

	// Table Name: TaskResponse
	// Columns List,Can Used in SELECT SQL: ID,TaskID,ResponseTime,ResponseUserID,ResponseContent
	// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ResponseTime,:ResponseUserID,:ResponseContent
	// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ResponseTime=:ResponseTime,ResponseUserID=:ResponseUserID,ResponseContent=:ResponseContent

	/**
	 * ��������ظ���Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO TaskResponse(TaskID,ResponseTime,ResponseUserID,ResponseContent) VALUES(:TaskID,:ResponseTime,:ResponseUserID,:ResponseContent)";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskResponseDao#delete(com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(TaskResponse taskResponse, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskResponseDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<TaskResponse> taskResponses, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskResponseDao#findByID(int, com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, TaskResponse taskResponse, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskResponseDao#save(com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(TaskResponse taskResponse, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�������ظ���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (taskResponse == null) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�������ظ���Ϣ������id
			if (pFlag) {
				if (taskResponse.getTaskID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ������id�Ƿ���");
				}
			}
			
			//�������ظ���Ϣ�Ļظ�ʱ��
			if (pFlag) {
				if (taskResponse.getResponseTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ�Ļظ�ʱ��Ƿ�Ϊ�ա�");
				}
			}
			
			//��� ����ظ���Ϣ�Ļظ�����
			if (pFlag) {
				if (StringTool.checkNull(taskResponse.getResponseContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("����ظ���Ϣ�Ļظ����ݷǷ�Ϊ�ա�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("TaskID", taskResponse.getTaskID(), Types.INTEGER);
				paramSource.addValue("ResponseTime", taskResponse.getResponseTime(), Types.TIMESTAMP);
				paramSource.addValue("ResponseUserID", taskResponse.getResponseUserID(), Types.INTEGER);
				paramSource.addValue("ResponseContent", taskResponse.getResponseContent(), Types.VARCHAR);

				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				System.out.println("��������ظ�="+SQL_INSERT);
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
	 * @see com.orifound.aiim.dal.dao.ITaskResponseDao#update(com.orifound.aiim.entity.TaskResponse, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(TaskResponse taskResponse, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
