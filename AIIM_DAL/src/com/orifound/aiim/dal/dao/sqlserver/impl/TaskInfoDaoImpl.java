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

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.ITaskInfoDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TaskInfo;
import com.orifound.aiim.entity.TaskPerson;
import com.orifound.aiim.entity.TaskResponse;

/**
 * 工作任务信息表 (TaskInfo)的DAO实现类(SQL Server平台)
 * @author tyb
 *
 */
public class TaskInfoDaoImpl extends JdbcDaoSupport implements ITaskInfoDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TaskInfoMapper implements RowMapper<TaskInfo>
	{
		
		@Override
		public TaskInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String title = rs.getString("Title");
			String content = rs.getString("Content");
			int fromUserID = rs.getInt("FromUserID");
			int fromDutyID = rs.getInt("FromDutyID");
			boolean publishFlag = rs.getBoolean("PublishFlag");
			Date publishTime = rs.getTimestamp("PublishTime");
			Date lastModifyTime = rs.getTimestamp("LastModifyTime");
			String fromUserName = rs.getString("fromUserName");
			
			return new TaskInfo(iD,title,content,fromUserID,fromDutyID,publishFlag,publishTime,lastModifyTime, fromUserName);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TaskPersonMapper implements RowMapper<TaskPerson>
	{
		
		@Override
		public TaskPerson mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int taskID = rs.getInt("TaskID");
			int userID = rs.getInt("UserID");
			String userName = rs.getString("userName");
			return new TaskPerson(iD, taskID, userID, userName);
		}
	}
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TaskResponseMapper implements RowMapper<TaskResponse>
	{
		
		@Override
		public TaskResponse mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int taskID = rs.getInt("TaskID");
			Date responseTime = rs.getTimestamp("ResponseTime");
			int responseUserID = rs.getInt("ResponseUserID");
			String responseContent = rs.getString("ResponseContent");
			
			return new TaskResponse(iD,taskID,responseTime,responseUserID,responseContent);
		}
	}
	
	/**
	 * 构造函数
	 */
	public TaskInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public TaskInfoDaoImpl(DataSource dataSource) {
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
	
	/**
	 * 插入任务信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO TaskInfo(Title,Content,FromUserID,FromDutyID,PublishFlag,PublishTime,LastModifyTime) " +
			"VALUES(:Title,:Content,:FromUserID,:FromDutyID,:PublishFlag,:PublishTime,:LastModifyTime)";
	
	/**
	 * 插入任务接收人信息的SQL语句
	 */
	private final String SQL_INSERT_TASKPERSON = "INSERT INTO TaskPerson(TaskID,UserID) SELECT %1$s, u.UserID FROM UserInfo u WHERE u.UserID IN(:userIDs)";
	
	/**
	 * 分页查询任务信息的SQL语句
	 * %1$s AND u.RealName like '%' 发布人条件
	 * %2$s AND pu.RealName like '%' 接收人条件
	 * %3$s AND t.PublishTime>=beginTime 发布开始时间
	 * %4$s AND t.PublishTime<=endTime 发布结束时间
	 * %5$s AND t.PublishFlag=0  发布标志 AND u.UserID=p.UserID
	 * %6$s AND t.Title like % AND t.Content like %
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM (SELECT t.* ,ROW_NUMBER() OVER(ORDER BY t.LastModifyTime DESC) RowNumber FROM ( " +
			"SELECT DISTINCT t.*,u.RealName fromUserName " +
			"FROM TaskInfo t ,UserInfo u , TaskPerson p ,UserInfo pu " +
			"WHERE  t.FromUserID=u.UserID AND t.ID=p.TaskID AND p.UserID=pu.UserID %1$s %2$s %3$s %4$s %5$s %6$s" +
			") t ) temp WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY LastModifyTime DESC";
	
	/**
	 * 不分页查询任务信息的SQL语句
	 * %1$s AND u.RealName like '%' 发布人条件
	 * %2$s AND pu.RealName like '%' 接收人条件
	 * %3$s AND t.PublishTime>=beginTime 发布开始时间
	 * %4$s AND t.PublishTime<=endTime 发布结束时间
	 * %5$s AND t.PublishFlag=0  发布标志
	 * %6$s AND t.Title like % AND t.Content like %
	 */
	private final String SQL_SELECT_WITHNOPAGE = "SELECT DISTINCT t.*,u.RealName fromUserName " +
		"FROM TaskInfo t ,UserInfo u , TaskPerson p ,UserInfo pu " +
		"WHERE  t.FromUserID=u.UserID AND t.ID=p.TaskID AND p.UserID=pu.UserID %1$s %2$s %3$s %4$s %5$s %6$s ORDER BY LastModifyTime DESC";
	
	/**
	 * 查询任务数量的SQL语句
	 * %1$s AND u.RealName like '%' 发布人条件
	 * %2$s AND pu.RealName like '%' 接收人条件
	 * %3$s AND t.PublishTime>=beginTime 发布开始时间
	 * %4$s AND t.PublishTime<=endTime 发布结束时间
	 * %5$s AND t.PublishFlag=0  发布标志
	 * %6$s AND t.Title like % AND t.Content like %
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(DISTINCT t.id) FROM TaskInfo t ,UserInfo u , TaskPerson p ,UserInfo pu " +
	"WHERE  t.FromUserID=u.UserID AND t.ID=p.TaskID AND p.UserID=pu.UserID %1$s %2$s %3$s %4$s %5$s %6$s";
	
	/**
	 * 查询特定任务的回复数量的SQL语句
	 */
	private final String SQL_SELECT_TaskResponseCount = "SELECT COUNT(1) FROM TaskPerson WHERE TaskID=:TaskID";
	
	/**
	 * 批量删除任务信息的SQL语句
	 * 删除任务回复、任务接收人、任务
	 */
	private final String SQL_DELETE_BATCH = "DELETE FROM TaskPerson WHERE TaskID IN(:taskInfoIds);DELETE FROM TaskResponse WHERE TaskID IN(:taskInfoIds);DELETE FROM TaskInfo WHERE ID IN(:taskInfoIds);";
	
	/**
	 * 根据id查询任务信息的SQL语句
	 */
	private final String SQL_SELECT_BYID = "SELECT *,u.RealName fromUserName FROM TaskInfo t,UserInfo u WHERE t.FromUserID=u.UserID AND t.ID=?";
	
	/**
	 * 根据任务id查询接收人姓名以及id集合的SQL语句
	 */
	private final String SQL_SELECT_TASKPERSON_BYTASKINFOID = "SELECT u.UserID,u.RealName FROM TaskPerson tp,UserInfo u WHERE tp.UserID=u.UserID AND tp.TaskID=?";
	
	/**
	 * 更新任务信息的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE TaskInfo SET Title=:Title,Content=:Content,PublishFlag=:PublishFlag,PublishTime=:PublishTime,LastModifyTime=:LastModifyTime WHERE ID=:ID";
	
	/**
	 * 删除任务的介绍人信息以及回复的SQL语句
	 */
	private final String SQL_DELETE_TASKPERSON = "DELETE FROM TaskResponse WHERE TaskID=:TaskID AND ResponseUserID IN(:UserID);DELETE FROM TaskPerson WHERE TaskID=:TaskID AND UserID IN(:UserID);";
	
	/**
	 * 查询任务详细信息的SQL语句
	 */
	private final String SQL_SELECT_DETAIL_BYID = "SELECT *,u.RealName userName FROM TaskPerson tp,UserInfo u WHERE u.UserID=tp.UserID AND TaskID=?";
	
	/**
	 * 查询特定接收人特定任务的回复信息的SQL语句
	 */
	private final String SQL_SELECT_TASKRESPONSE = "SELECT * FROM TaskResponse WHERE TaskID=? AND ResponseUserID=?";
	
	/**
	 * 查询任务发布人的回复的SQL语句
	 */
	private final String SQL_SELECT_OWN_TASKRESPONSE = "SELECT tr.* FROM TaskInfo t,TaskResponse tr WHERE t.FromUserID=tr.ResponseUserID AND t.ID=tr.TaskID AND t.ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#findWithPage(java.util.Map, com.orifound.aiim.entity.DataPageInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo, List<TaskInfo> taskInfos, ErrInfo pErrInfo) {
		/**
		 * select * from (
				select t.*,u.UserName fromUserName,ROW_NUMBER() over(order by t.LastModifyTime desc) RowNumber from TaskInfo t
				left join UserInfo u on t.FromUserID=u.UserID and u.UserName like '%'
				left join TaskPerson p on t.ID=p.TaskID
				left join UserInfo pu on p.UserID=pu.UserID and pu.UserName like '%'
				where t.PublishTime between 1 and 2
				) temp
				WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY PublishTime DESC
		 */
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务信息集合是否为空
			if (pFlag) {
				if (taskInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务信息集合非法为空。");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
//				任务发布人fromUserName、接收人receiveName、发布日期开始beginTime、结束endTime
				//根据查询条件拼接SQL语句
				String fromUserName = "";
				String receiveName = "";
				String beginTime = "";
				String endTime = "";
				String publish = "";
				String content = "";
				String title = "";
				if (params!=null && params.keySet().size() >= 1) {
					//是否发布
					publish = params.get("publishFlag"); 
					if(StringTool.checkNull(publish) && publish.equals("n")) {
						publish = "AND t.PublishFlag=0 ";
					} else {
						publish = "AND t.PublishFlag=1 ";
					}
					
					//判断是否存在发布人 条件
					fromUserName = params.get("fromUserName");
					if(StringTool.checkNull(fromUserName)) {
						fromUserName = "AND u.RealName like '%"+fromUserName+"%'";
					} else {
						fromUserName = "";
					}
					
					//判断是否存在接收人条件
					receiveName = params.get("receiveName");
					if(StringTool.checkNull(receiveName)) {
						receiveName = "AND pu.RealName like '%"+receiveName+"%'";
					} else {
						receiveName = "";
					}
					
					//判断是否存在关键词搜索条件
					title = params.get("title");
					content = params.get("content");
					if(StringTool.checkNull(title) && StringTool.checkNull(content)) {
						title = "AND( t.Title like '%"+title+"%' OR t.Content like '%"+content+"%')";
					} else if(StringTool.checkNull(title)) {
						title = "AND t.Title like '%"+title+"%'";
					} else if(StringTool.checkNull(content)) {
						title = "AND t.Content like '%"+content+"%'";
					} else {
						title = "";
					}
					
//					//判断是否存在发布开始日期
//					beginTime = params.get("beginTime");
//					if (StringTool.checkNull(beginTime)) {
//						beginTime = " AND t.PublishTime>='"+beginTime+" 00:00:01'";
//					} else {
//						beginTime = "";
//					}
//					
//					//判断是否存在发布结束日期
//					endTime = params.get("endTime");
//					if (StringTool.checkNull(endTime)) {
//						endTime = " AND t.PublishTime<='"+endTime+" 23:59:59'";
//					} else {
//						endTime = "";
//					}
					
					
				}
				
				//最后生成的SQL语句
				String localSQL = null;
				String countSQL = null;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				
				//判断是否支持分页查询
				if (dataPageInfo == null || dataPageInfo.getPageSize() <= 0) {	//不支持分页查询
					localSQL = String.format(SQL_SELECT_WITHNOPAGE, fromUserName, receiveName, beginTime, endTime, publish, title);
					
				} else { //支持分页查询
					
					//设置任务的总记录数
					countSQL = String.format(SQL_SELECT_COUNT, fromUserName, receiveName, beginTime, endTime, publish, title);
					System.out.println("任务分页查询countSQL="+countSQL);
					dataPageInfo.setRowCount(namedParameterJdbcTemplate.queryForInt(countSQL, parameterSource));
					
					//分页查询SQL构建
					parameterSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
					parameterSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
					localSQL = String.format(SQL_SELECT_WITHPAGE, fromUserName, receiveName, beginTime, endTime, publish, title);
				}
				
				System.out.println("任务分页查询localSQL="+localSQL);
				
				List<TaskInfo> results = namedParameterJdbcTemplate.query(localSQL, parameterSource, new TaskInfoMapper());
				
				if (results.size() >= 1) {
					taskInfos.addAll(results);
					
					//循环设置每个任务的回复数量
					for(TaskInfo taskInfo : taskInfos) {
						parameterSource = new MapSqlParameterSource();
						parameterSource.addValue("TaskID", taskInfo.getID());
						taskInfo.setTaskResponseCount(namedParameterJdbcTemplate.queryForInt(SQL_SELECT_TaskResponseCount, parameterSource));
						
						if (findDetailTaskPersons(taskInfo.getID(), taskInfo, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "查询特定任务的接收人集合 失败：");
						}
						
						//判断是否存在接收人
						if(StringTool.checkNull(taskInfo.getReceiveNames())) {
							//任务接收人字符串使用","分隔
							taskInfo.setReceiveNames(taskInfo.getReceiveNames().replace(';', ','));
						}
					}
				}
				
				//销毁局部变量
				localSQL = null;
				parameterSource = null;
				namedParameterJdbcTemplate = null;
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
		System.out.println(pErrInfo.toString());
		return pFlag;
	}
	
	/**
	 * 查询特定任务的接收人集合并将姓名、以及id 拼接成字符串（";"分隔）
	 * @param taskId 任务id
	 * @param taskInfo	返回任务信息
	 * pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean findDetailTaskPersons(int taskId,TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				//查询任务的接收人详细信息
				List<Map<String, Object>> results = jdbcTemplate.queryForList(SQL_SELECT_TASKPERSON_BYTASKINFOID, taskId);
				if(results.size() >= 1) {
					//任务接收人id
					Object receiveId = null;
					//任务接收人姓名
					Object receiveName = null;
					StringBuffer nameBuffer = new StringBuffer();
					StringBuffer idBuffer = new StringBuffer();
					//封装任务接收人信息
					for(Map<String, Object> result : results) {
						receiveId = result.get("UserID");
						receiveName = result.get("RealName");
						
						if(receiveId != null && receiveName!=null) {
							idBuffer.append(receiveId).append(";");
							nameBuffer.append(receiveName).append(";");
						}
					}
					//取出最后的分号
					if (nameBuffer.length()>=1 && idBuffer.length()>=1) {
						idBuffer.deleteCharAt(idBuffer.length()-1);
						nameBuffer.deleteCharAt(nameBuffer.length()-1);
						
						taskInfo.setReceiveIds(idBuffer.toString());
						taskInfo.setReceiveNames(nameBuffer.toString());
					}
				}
				//销毁局部变量
				jdbcTemplate = null;
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
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#delete(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(TaskInfo taskInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<TaskInfo> taskInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#findByID(int, com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务id是否为零
			if (pFlag) {
				if (pID <=0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务id非法为零。");
				}
			}
			
			//检查任务对象是否为空
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务对象非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<TaskInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID,new TaskInfoMapper(),pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					taskInfo.cloneFrom(pEntitys.get(0));
					
					if (findDetailTaskPersons(taskInfo.getID(), taskInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "查询特定任务的接收人集合 失败：");
					}
				}
				//销毁局部变量
				jdbcTemplate = null;
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
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#save(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务信息是否为空
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务信息非法为空。");
				}
			}
			
			//检查任务接收人是否为空
			if (pFlag && taskInfo.getPublishFlag()) {
				if (taskInfo.getTaskPersonIds()==null || taskInfo.getTaskPersonIds().size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务接收人非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 1;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT);
				//构造SQL执行成功后返回的主键
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				//设置主键字段名
				pscFactory.setGeneratedKeysColumnNames(new String[] { "ID" });
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("Title", taskInfo.getTitle(), Types.VARCHAR);
				paramSource.addValue("Content", taskInfo.getContent(), Types.VARCHAR);
				paramSource.addValue("FromUserID", taskInfo.getFromUserID(), Types.INTEGER);
				paramSource.addValue("FromDutyID", taskInfo.getFromDutyID(), Types.INTEGER);
				paramSource.addValue("PublishFlag", taskInfo.getPublishFlag(), Types.BOOLEAN);
				paramSource.addValue("PublishTime", taskInfo.getPublishTime(), Types.TIMESTAMP);
				paramSource.addValue("LastModifyTime", taskInfo.getLastModifyTime(), Types.TIMESTAMP);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				
				System.out.println("插入任务信息："+SQL_INSERT);

				//返回自动生成的内部序号
				taskInfo.setID(keyHolder.getKey().intValue());
				
				//插入 任务接收人信息
				if (taskInfo.getTaskPersonIds()!=null && taskInfo.getTaskPersonIds().size() >= 1) {
					paramSource = new MapSqlParameterSource();
					paramSource.addValue("userIDs", taskInfo.getTaskPersonIds());
					namedParameterJdbcTemplate.update(String.format(SQL_INSERT_TASKPERSON, taskInfo.getID()), paramSource);
					
					System.out.println("插入任务接受人信息="+String.format(SQL_INSERT_TASKPERSON, taskInfo.getID()));
				}
				
				//销毁局部变量
				namedParameterJdbcTemplate = null;
				paramSource = null;
				pscFactory = null;
				keyHolder = null;
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
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#update(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查任务信息是否为空
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务信息非法为空。");
				}
			}
			
			//检查任务主题是否为空
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getTitle()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务主题非法为空。");
				}
			}
			
			//检查任务最后修改时间是否为空
			if (pFlag) {
				if (taskInfo.getLastModifyTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务最后修改时间非法为空。");
				}
			}
			
			//检查任务内容是否为空
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务内容非法为空。");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ID", taskInfo.getID(),Types.INTEGER);
				paramSource.addValue("Title", taskInfo.getTitle(),Types.VARCHAR);
				paramSource.addValue("Content", taskInfo.getContent(),Types.VARCHAR);
				paramSource.addValue("PublishFlag", taskInfo.getPublishFlag(),Types.BOOLEAN);
				paramSource.addValue("PublishTime", taskInfo.getPublishTime(),Types.TIMESTAMP);
				paramSource.addValue("LastModifyTime", taskInfo.getLastModifyTime(),Types.TIMESTAMP);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				
				//删除任务接收人信息
				if (pFlag) {
					//需要删除的任务接收人id
					List<Integer> delTaskPersonIds = taskInfo.getDelTaskPersonIds();
					if(delTaskPersonIds!=null && delTaskPersonIds.size()>=1) {
						paramSource = new MapSqlParameterSource();
						paramSource.addValue("TaskID", taskInfo.getID(),Types.INTEGER);
						paramSource.addValue("UserID", delTaskPersonIds);
						
						namedParameterJdbcTemplate.update(SQL_DELETE_TASKPERSON, paramSource);
					}
				}
				
				//新增任务接收人信息
				if (pFlag) {
					//需要新增的任务接收人id
					List<Integer> insertTaskPersonIds = taskInfo.getTaskPersonIds();
					if (insertTaskPersonIds!=null && insertTaskPersonIds.size()>=1) {
						paramSource = new MapSqlParameterSource();
						paramSource.addValue("userIDs", insertTaskPersonIds);
						String local = String.format(SQL_INSERT_TASKPERSON, taskInfo.getID());
						
						namedParameterJdbcTemplate.update(local, paramSource);
					}
				}
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

	@Override
	public boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//判断任务id集合是否为空
			if (pFlag) {
				if (taskInfoIds==null || taskInfoIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("任务id集合非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("taskInfoIds", taskInfoIds);
				
				System.out.println("SQL_DELETE_BATCH="+SQL_DELETE_BATCH);

				namedParameterJdbcTemplate.update(SQL_DELETE_BATCH, paramSource);
				
				//销毁变量
				paramSource = null;
				namedParameterJdbcTemplate = null;
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

	@Override
	public boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_SELECT_TASKPERSON_BYTASKINFOID,pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					TaskPerson taskPerson = null;
					Object receiveId = null;
					Object receiveName = null;
					for(Map<String, Object> result : pEntitys) {
						receiveId = result.get("UserID");
						receiveName = result.get("RealName");
						if(receiveId != null && receiveName!=null) {
							taskPerson = new TaskPerson();
							taskPerson.setUserID(Integer.valueOf(receiveId.toString()));
							taskPerson.setUserName(receiveName.toString());
							taskPersons.add(taskPerson);
						}
					}
				}
				//销毁局部变量
				jdbcTemplate = null;
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

	@Override
	public boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查主键是否为空
			if (pFlag) {
				if (pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入参数->任务id非法为零。");
				}
			}
			

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<TaskInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID,new TaskInfoMapper(),pID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					taskInfo.cloneFrom(pEntitys.get(0));
					
					//查询任务发布人	回复信息
					List<TaskResponse> ownTaskResponses=jdbcTemplate.query(SQL_SELECT_OWN_TASKRESPONSE,new TaskResponseMapper(),pID);
					if(ownTaskResponses.size() >= 1) {
						taskInfo.setOwnTaskResponses(ownTaskResponses);
					}
					
					//查询任务的接收人集合
					List<TaskPerson> results=jdbcTemplate.query(SQL_SELECT_DETAIL_BYID,new TaskPersonMapper(),pID);
					
					TaskPerson person = null;
					List<TaskPerson> taskPersons = new ArrayList<TaskPerson>();
					//循环任务接收人 查询对任务的回复
					for(TaskPerson result : results) {
						person = new TaskPerson();
						person.cloneFrom(result);
						
						//查询每个接收人的回复信息
						List<TaskResponse> taskResponses=jdbcTemplate.query(SQL_SELECT_TASKRESPONSE,new TaskResponseMapper(),pID,person.getUserID());
						
						if(taskResponses.size() >= 1) {
							person.setTaskResponses(taskResponses);
						}
						
						//加入到任务接收人信息
						taskPersons.add(person);
					}
					taskInfo.setTaskPersons(taskPersons);
				}

				//销毁局部变量
				jdbcTemplate = null;
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


}
