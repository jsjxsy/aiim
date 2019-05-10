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
 * ����������Ϣ�� (TaskInfo)��DAOʵ����(SQL Serverƽ̨)
 * @author tyb
 *
 */
public class TaskInfoDaoImpl extends JdbcDaoSupport implements ITaskInfoDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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
	 * ���캯��
	 */
	public TaskInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public TaskInfoDaoImpl(DataSource dataSource) {
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
	 * ����������Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO TaskInfo(Title,Content,FromUserID,FromDutyID,PublishFlag,PublishTime,LastModifyTime) " +
			"VALUES(:Title,:Content,:FromUserID,:FromDutyID,:PublishFlag,:PublishTime,:LastModifyTime)";
	
	/**
	 * ���������������Ϣ��SQL���
	 */
	private final String SQL_INSERT_TASKPERSON = "INSERT INTO TaskPerson(TaskID,UserID) SELECT %1$s, u.UserID FROM UserInfo u WHERE u.UserID IN(:userIDs)";
	
	/**
	 * ��ҳ��ѯ������Ϣ��SQL���
	 * %1$s AND u.RealName like '%' ����������
	 * %2$s AND pu.RealName like '%' ����������
	 * %3$s AND t.PublishTime>=beginTime ������ʼʱ��
	 * %4$s AND t.PublishTime<=endTime ��������ʱ��
	 * %5$s AND t.PublishFlag=0  ������־ AND u.UserID=p.UserID
	 * %6$s AND t.Title like % AND t.Content like %
	 */
	private final String SQL_SELECT_WITHPAGE = "SELECT * FROM (SELECT t.* ,ROW_NUMBER() OVER(ORDER BY t.LastModifyTime DESC) RowNumber FROM ( " +
			"SELECT DISTINCT t.*,u.RealName fromUserName " +
			"FROM TaskInfo t ,UserInfo u , TaskPerson p ,UserInfo pu " +
			"WHERE  t.FromUserID=u.UserID AND t.ID=p.TaskID AND p.UserID=pu.UserID %1$s %2$s %3$s %4$s %5$s %6$s" +
			") t ) temp WHERE RowNumber BETWEEN :beginRow AND :endRow ORDER BY LastModifyTime DESC";
	
	/**
	 * ����ҳ��ѯ������Ϣ��SQL���
	 * %1$s AND u.RealName like '%' ����������
	 * %2$s AND pu.RealName like '%' ����������
	 * %3$s AND t.PublishTime>=beginTime ������ʼʱ��
	 * %4$s AND t.PublishTime<=endTime ��������ʱ��
	 * %5$s AND t.PublishFlag=0  ������־
	 * %6$s AND t.Title like % AND t.Content like %
	 */
	private final String SQL_SELECT_WITHNOPAGE = "SELECT DISTINCT t.*,u.RealName fromUserName " +
		"FROM TaskInfo t ,UserInfo u , TaskPerson p ,UserInfo pu " +
		"WHERE  t.FromUserID=u.UserID AND t.ID=p.TaskID AND p.UserID=pu.UserID %1$s %2$s %3$s %4$s %5$s %6$s ORDER BY LastModifyTime DESC";
	
	/**
	 * ��ѯ����������SQL���
	 * %1$s AND u.RealName like '%' ����������
	 * %2$s AND pu.RealName like '%' ����������
	 * %3$s AND t.PublishTime>=beginTime ������ʼʱ��
	 * %4$s AND t.PublishTime<=endTime ��������ʱ��
	 * %5$s AND t.PublishFlag=0  ������־
	 * %6$s AND t.Title like % AND t.Content like %
	 */
	private final String SQL_SELECT_COUNT = "SELECT COUNT(DISTINCT t.id) FROM TaskInfo t ,UserInfo u , TaskPerson p ,UserInfo pu " +
	"WHERE  t.FromUserID=u.UserID AND t.ID=p.TaskID AND p.UserID=pu.UserID %1$s %2$s %3$s %4$s %5$s %6$s";
	
	/**
	 * ��ѯ�ض�����Ļظ�������SQL���
	 */
	private final String SQL_SELECT_TaskResponseCount = "SELECT COUNT(1) FROM TaskPerson WHERE TaskID=:TaskID";
	
	/**
	 * ����ɾ��������Ϣ��SQL���
	 * ɾ������ظ�����������ˡ�����
	 */
	private final String SQL_DELETE_BATCH = "DELETE FROM TaskPerson WHERE TaskID IN(:taskInfoIds);DELETE FROM TaskResponse WHERE TaskID IN(:taskInfoIds);DELETE FROM TaskInfo WHERE ID IN(:taskInfoIds);";
	
	/**
	 * ����id��ѯ������Ϣ��SQL���
	 */
	private final String SQL_SELECT_BYID = "SELECT *,u.RealName fromUserName FROM TaskInfo t,UserInfo u WHERE t.FromUserID=u.UserID AND t.ID=?";
	
	/**
	 * ��������id��ѯ�����������Լ�id���ϵ�SQL���
	 */
	private final String SQL_SELECT_TASKPERSON_BYTASKINFOID = "SELECT u.UserID,u.RealName FROM TaskPerson tp,UserInfo u WHERE tp.UserID=u.UserID AND tp.TaskID=?";
	
	/**
	 * ����������Ϣ��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE TaskInfo SET Title=:Title,Content=:Content,PublishFlag=:PublishFlag,PublishTime=:PublishTime,LastModifyTime=:LastModifyTime WHERE ID=:ID";
	
	/**
	 * ɾ������Ľ�������Ϣ�Լ��ظ���SQL���
	 */
	private final String SQL_DELETE_TASKPERSON = "DELETE FROM TaskResponse WHERE TaskID=:TaskID AND ResponseUserID IN(:UserID);DELETE FROM TaskPerson WHERE TaskID=:TaskID AND UserID IN(:UserID);";
	
	/**
	 * ��ѯ������ϸ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_DETAIL_BYID = "SELECT *,u.RealName userName FROM TaskPerson tp,UserInfo u WHERE u.UserID=tp.UserID AND TaskID=?";
	
	/**
	 * ��ѯ�ض��������ض�����Ļظ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_TASKRESPONSE = "SELECT * FROM TaskResponse WHERE TaskID=? AND ResponseUserID=?";
	
	/**
	 * ��ѯ���񷢲��˵Ļظ���SQL���
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���������Ϣ�����Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ϣ���ϷǷ�Ϊ�ա�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				
//				���񷢲���fromUserName��������receiveName���������ڿ�ʼbeginTime������endTime
				//���ݲ�ѯ����ƴ��SQL���
				String fromUserName = "";
				String receiveName = "";
				String beginTime = "";
				String endTime = "";
				String publish = "";
				String content = "";
				String title = "";
				if (params!=null && params.keySet().size() >= 1) {
					//�Ƿ񷢲�
					publish = params.get("publishFlag"); 
					if(StringTool.checkNull(publish) && publish.equals("n")) {
						publish = "AND t.PublishFlag=0 ";
					} else {
						publish = "AND t.PublishFlag=1 ";
					}
					
					//�ж��Ƿ���ڷ����� ����
					fromUserName = params.get("fromUserName");
					if(StringTool.checkNull(fromUserName)) {
						fromUserName = "AND u.RealName like '%"+fromUserName+"%'";
					} else {
						fromUserName = "";
					}
					
					//�ж��Ƿ���ڽ���������
					receiveName = params.get("receiveName");
					if(StringTool.checkNull(receiveName)) {
						receiveName = "AND pu.RealName like '%"+receiveName+"%'";
					} else {
						receiveName = "";
					}
					
					//�ж��Ƿ���ڹؼ�����������
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
					
//					//�ж��Ƿ���ڷ�����ʼ����
//					beginTime = params.get("beginTime");
//					if (StringTool.checkNull(beginTime)) {
//						beginTime = " AND t.PublishTime>='"+beginTime+" 00:00:01'";
//					} else {
//						beginTime = "";
//					}
//					
//					//�ж��Ƿ���ڷ�����������
//					endTime = params.get("endTime");
//					if (StringTool.checkNull(endTime)) {
//						endTime = " AND t.PublishTime<='"+endTime+" 23:59:59'";
//					} else {
//						endTime = "";
//					}
					
					
				}
				
				//������ɵ�SQL���
				String localSQL = null;
				String countSQL = null;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				
				//�ж��Ƿ�֧�ַ�ҳ��ѯ
				if (dataPageInfo == null || dataPageInfo.getPageSize() <= 0) {	//��֧�ַ�ҳ��ѯ
					localSQL = String.format(SQL_SELECT_WITHNOPAGE, fromUserName, receiveName, beginTime, endTime, publish, title);
					
				} else { //֧�ַ�ҳ��ѯ
					
					//����������ܼ�¼��
					countSQL = String.format(SQL_SELECT_COUNT, fromUserName, receiveName, beginTime, endTime, publish, title);
					System.out.println("�����ҳ��ѯcountSQL="+countSQL);
					dataPageInfo.setRowCount(namedParameterJdbcTemplate.queryForInt(countSQL, parameterSource));
					
					//��ҳ��ѯSQL����
					parameterSource.addValue("beginRow", dataPageInfo.getBeginRow() ,Types.INTEGER);
					parameterSource.addValue("endRow", dataPageInfo.getEndRow() ,Types.INTEGER);
					localSQL = String.format(SQL_SELECT_WITHPAGE, fromUserName, receiveName, beginTime, endTime, publish, title);
				}
				
				System.out.println("�����ҳ��ѯlocalSQL="+localSQL);
				
				List<TaskInfo> results = namedParameterJdbcTemplate.query(localSQL, parameterSource, new TaskInfoMapper());
				
				if (results.size() >= 1) {
					taskInfos.addAll(results);
					
					//ѭ������ÿ������Ļظ�����
					for(TaskInfo taskInfo : taskInfos) {
						parameterSource = new MapSqlParameterSource();
						parameterSource.addValue("TaskID", taskInfo.getID());
						taskInfo.setTaskResponseCount(namedParameterJdbcTemplate.queryForInt(SQL_SELECT_TaskResponseCount, parameterSource));
						
						if (findDetailTaskPersons(taskInfo.getID(), taskInfo, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ѯ�ض�����Ľ����˼��� ʧ�ܣ�");
						}
						
						//�ж��Ƿ���ڽ�����
						if(StringTool.checkNull(taskInfo.getReceiveNames())) {
							//����������ַ���ʹ��","�ָ�
							taskInfo.setReceiveNames(taskInfo.getReceiveNames().replace(';', ','));
						}
					}
				}
				
				//���پֲ�����
				localSQL = null;
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
		System.out.println(pErrInfo.toString());
		return pFlag;
	}
	
	/**
	 * ��ѯ�ض�����Ľ����˼��ϲ����������Լ�id ƴ�ӳ��ַ�����";"�ָ���
	 * @param taskId ����id
	 * @param taskInfo	����������Ϣ
	 * pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean findDetailTaskPersons(int taskId,TaskInfo taskInfo, ErrInfo pErrInfo) {
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
				//��ѯ����Ľ�������ϸ��Ϣ
				List<Map<String, Object>> results = jdbcTemplate.queryForList(SQL_SELECT_TASKPERSON_BYTASKINFOID, taskId);
				if(results.size() >= 1) {
					//���������id
					Object receiveId = null;
					//�������������
					Object receiveName = null;
					StringBuffer nameBuffer = new StringBuffer();
					StringBuffer idBuffer = new StringBuffer();
					//��װ�����������Ϣ
					for(Map<String, Object> result : results) {
						receiveId = result.get("UserID");
						receiveName = result.get("RealName");
						
						if(receiveId != null && receiveName!=null) {
							idBuffer.append(receiveId).append(";");
							nameBuffer.append(receiveName).append(";");
						}
					}
					//ȡ�����ķֺ�
					if (nameBuffer.length()>=1 && idBuffer.length()>=1) {
						idBuffer.deleteCharAt(idBuffer.length()-1);
						nameBuffer.deleteCharAt(nameBuffer.length()-1);
						
						taskInfo.setReceiveIds(idBuffer.toString());
						taskInfo.setReceiveNames(nameBuffer.toString());
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
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�������id�Ƿ�Ϊ��
			if (pFlag) {
				if (pID <=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->����id�Ƿ�Ϊ�㡣");
				}
			}
			
			//�����������Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�������Ƿ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<TaskInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID,new TaskInfoMapper(),pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					taskInfo.cloneFrom(pEntitys.get(0));
					
					if (findDetailTaskPersons(taskInfo.getID(), taskInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ѯ�ض�����Ľ����˼��� ʧ�ܣ�");
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
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#save(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//�������������Ƿ�Ϊ��
			if (pFlag && taskInfo.getPublishFlag()) {
				if (taskInfo.getTaskPersonIds()==null || taskInfo.getTaskPersonIds().size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������˷Ƿ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 1;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(SQL_INSERT);
				//����SQLִ�гɹ��󷵻ص�����
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				//���������ֶ���
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
				
				System.out.println("����������Ϣ��"+SQL_INSERT);

				//�����Զ����ɵ��ڲ����
				taskInfo.setID(keyHolder.getKey().intValue());
				
				//���� �����������Ϣ
				if (taskInfo.getTaskPersonIds()!=null && taskInfo.getTaskPersonIds().size() >= 1) {
					paramSource = new MapSqlParameterSource();
					paramSource.addValue("userIDs", taskInfo.getTaskPersonIds());
					namedParameterJdbcTemplate.update(String.format(SQL_INSERT_TASKPERSON, taskInfo.getID()), paramSource);
					
					System.out.println("���������������Ϣ="+String.format(SQL_INSERT_TASKPERSON, taskInfo.getID()));
				}
				
				//���پֲ�����
				namedParameterJdbcTemplate = null;
				paramSource = null;
				pscFactory = null;
				keyHolder = null;
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
	 * @see com.orifound.aiim.dal.dao.ITaskInfoDao#update(com.orifound.aiim.entity.TaskInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//������������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getTitle()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������Ƿ�Ϊ�ա�");
				}
			}
			
			//�����������޸�ʱ���Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfo.getLastModifyTime() == null) {
					pFlag = false;
					pErrInfo.getContent().append("�������->��������޸�ʱ��Ƿ�Ϊ�ա�");
				}
			}
			
			//������������Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(taskInfo.getContent()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�������->�������ݷǷ�Ϊ�ա�");
				}
			}
			
			//ִ��SQL���
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
				
				//ɾ�������������Ϣ
				if (pFlag) {
					//��Ҫɾ�������������id
					List<Integer> delTaskPersonIds = taskInfo.getDelTaskPersonIds();
					if(delTaskPersonIds!=null && delTaskPersonIds.size()>=1) {
						paramSource = new MapSqlParameterSource();
						paramSource.addValue("TaskID", taskInfo.getID(),Types.INTEGER);
						paramSource.addValue("UserID", delTaskPersonIds);
						
						namedParameterJdbcTemplate.update(SQL_DELETE_TASKPERSON, paramSource);
					}
				}
				
				//���������������Ϣ
				if (pFlag) {
					//��Ҫ���������������id
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
	public boolean deleteBatch(List<Integer> taskInfoIds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�ж�����id�����Ƿ�Ϊ��
			if (pFlag) {
				if (taskInfoIds==null || taskInfoIds.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����id���ϷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("taskInfoIds", taskInfoIds);
				
				System.out.println("SQL_DELETE_BATCH="+SQL_DELETE_BATCH);

				namedParameterJdbcTemplate.update(SQL_DELETE_BATCH, paramSource);
				
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

	@Override
	public boolean findTaskPersonByID(int pID, List<TaskPerson> taskPersons,
			ErrInfo pErrInfo) {
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
				List<Map<String, Object>> pEntitys=jdbcTemplate.queryForList(SQL_SELECT_TASKPERSON_BYTASKINFOID,pID);

				//���ز�ѯ���
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
	public boolean findDetailByID(int pID, TaskInfo taskInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��������Ƿ�Ϊ��
			if (pFlag) {
				if (pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������->����id�Ƿ�Ϊ�㡣");
				}
			}
			

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<TaskInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_BYID,new TaskInfoMapper(),pID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					taskInfo.cloneFrom(pEntitys.get(0));
					
					//��ѯ���񷢲���	�ظ���Ϣ
					List<TaskResponse> ownTaskResponses=jdbcTemplate.query(SQL_SELECT_OWN_TASKRESPONSE,new TaskResponseMapper(),pID);
					if(ownTaskResponses.size() >= 1) {
						taskInfo.setOwnTaskResponses(ownTaskResponses);
					}
					
					//��ѯ����Ľ����˼���
					List<TaskPerson> results=jdbcTemplate.query(SQL_SELECT_DETAIL_BYID,new TaskPersonMapper(),pID);
					
					TaskPerson person = null;
					List<TaskPerson> taskPersons = new ArrayList<TaskPerson>();
					//ѭ����������� ��ѯ������Ļظ�
					for(TaskPerson result : results) {
						person = new TaskPerson();
						person.cloneFrom(result);
						
						//��ѯÿ�������˵Ļظ���Ϣ
						List<TaskResponse> taskResponses=jdbcTemplate.query(SQL_SELECT_TASKRESPONSE,new TaskResponseMapper(),pID,person.getUserID());
						
						if(taskResponses.size() >= 1) {
							person.setTaskResponses(taskResponses);
						}
						
						//���뵽�����������Ϣ
						taskPersons.add(person);
					}
					taskInfo.setTaskPersons(taskPersons);
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
