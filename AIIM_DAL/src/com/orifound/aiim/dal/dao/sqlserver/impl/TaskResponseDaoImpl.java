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
 * 工作任务汇报信息表 (TaskResponse)的DAO实现（SQL Server 平台）
 * @author tyb
 *
 */
public class TaskResponseDaoImpl extends JdbcDaoSupport implements ITaskResponseDao {
	/**
	 * 构造函数
	 */
	public TaskResponseDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public TaskResponseDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}

	// Table Name: TaskResponse
	// Columns List,Can Used in SELECT SQL: ID,TaskID,ResponseTime,ResponseUserID,ResponseContent
	// Columns List,Can Used in INSERT SQL: :ID,:TaskID,:ResponseTime,:ResponseUserID,:ResponseContent
	// Columns List,Can Used in UPDATE SQL: ID=:ID,TaskID=:TaskID,ResponseTime=:ResponseTime,ResponseUserID=:ResponseUserID,ResponseContent=:ResponseContent

	/**
	 * 保存任务回复信息的SQL语句
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
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务回复信息是否为空
			if (pFlag) {
				if (taskResponse == null) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息非法为空。");
				}
			}
			
			//检查任务回复信息的任务id
			if (pFlag) {
				if (taskResponse.getTaskID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息的任务id非法。");
				}
			}
			
			//检查任务回复信息的回复时间
			if (pFlag) {
				if (taskResponse.getResponseTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息的回复时间非法为空。");
				}
			}
			
			//检查 任务回复信息的回复内容
			if (pFlag) {
				if (StringTool.checkNull(taskResponse.getResponseContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("任务回复信息的回复内容非法为空。");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("TaskID", taskResponse.getTaskID(), Types.INTEGER);
				paramSource.addValue("ResponseTime", taskResponse.getResponseTime(), Types.TIMESTAMP);
				paramSource.addValue("ResponseUserID", taskResponse.getResponseUserID(), Types.INTEGER);
				paramSource.addValue("ResponseContent", taskResponse.getResponseContent(), Types.VARCHAR);

				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				System.out.println("插入任务回复="+SQL_INSERT);
			}
		} catch (BadSqlGrammarException e) {
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
