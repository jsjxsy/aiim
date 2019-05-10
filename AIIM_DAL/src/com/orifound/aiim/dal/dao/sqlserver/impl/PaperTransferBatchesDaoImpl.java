package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IPaperTransferBatchesDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.UserInfo;
/**
 * �ƽ�������Ϣ���DAOʵ����
 * @author Administrator
 *
 */
public class PaperTransferBatchesDaoImpl extends JdbcDaoSupport implements IPaperTransferBatchesDao {

	/**
	 * ����������Ϣ��SQL
	 */
	private static final String SQL_INSERT_PaperTransferBatch= "INSERT INTO PaperTransferBatches (BatNo, BatNoCreateUserID,InsideFlag,TransferDepartmentID) VALUES (?,?,?,?)";
	
	/**
	 * ����������Ϣ��SQL
	 */
	private static final String SQL_UPDATE_PaperTransferBatch= "UPDATE PaperTransferBatches SET BatNoCreateUserID=?,InsideFlag=? where BatNo=?";
	
	/**
	 * ��ѯ��ǰ�û�δ�ƽ���������Ϣ��SQL
	 */
	private static final String SQL_SELECT_FindPaperTransferBatchByConfirmFlag= "SELECT BatNo FROM PaperTransferBatches WHERE BatNoCreateUserID=? AND BatNoStatus=? AND InsideFlag=?";
	
	
	/**
	 * ��ǰ�û�ȷ���ƽ�δ�ƽ���������Ϣ��SQL
	 */
	private static final String SQL_UPDATE_updateConfirmFlag= "UPDATE PaperTransferBatches SET BatNoStatus=1,TransferUserID=?,TransferDepartmentID=?,TransferTime=?,TransferTotal=?  WHERE BatNo=?";
	
	/**
	 * ����������Ϣ��SQL
	 */
	private static final String SQL_UPDATE_UpdateForReceive= "UPDATE PaperTransferBatches SET  BatNoStatus=?,ReceiveDepartmentID=?,ReceiveUserID =?,ReceiveTime=? WHERE BatNo=?";
	
	/**
	 * �������κŲ���������Ϣ�����صĽ���а������εĴ����ˡ��������š������ˡ����ղ���
	 */
	private static final String SQL_SELECT_findByBatNO = "select * from " +
	"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
	"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
	"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and T9.BatNo=? order by T9.TransferTime";
	
	/**
	 * ��������������Ϣ�����صĽ���а������εĴ����ˡ��������š������ˡ����ղ���
	 */
	private static final  String SQL_SELECT_findAll="select * from " +
	"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
	"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
	"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and BatNoStatus=:BatNoStatus  AND TransferDepartmentID IN(:TransferDepartmentIDS) AND InsideFlag=:InsideFlag order by T9.TransferTime";
	
	
	/**
	 * ��Ч�����ѯ���������ҽ��յ��������SQL���
	 * %1$s ptd.ReceiveCheckResult<>2 ��������ptd.ReceiveCheckResult=1Ϊδ��������
	 */
	private final String SQL_SELECT_ReceiveArchives = "SELECT COUNT(1) FROM PaperTransferBatches pt,PaperTransferSubBatches pts,PaperTransferBatchesDetails ptd" +
			" WHERE pt.BatNo=pts.BatNo AND pts.SubBatNo=ptd.TransferBatNo " +
			" AND pt.InsideFlag=1 AND pt.BatNoStatus=2 AND pt.ReceiveCheckedFlag=0" +
			" AND %1$s";
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class PaperTransferBatchMapper implements RowMapper<PaperTransferBatch>
	{
		
		@Override
		public PaperTransferBatch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String batNo = rs.getString("BatNo");
			int batNoCreateUserID = rs.getInt("BatNoCreateUserID");
			int transferDepartmentID = rs.getInt("TransferDepartmentID");
			boolean insideFlag = rs.getBoolean("InsideFlag");
			Date transferTime = rs.getTimestamp("TransferTime");
			int transferTotal = rs.getInt("TransferTotal");
			int transferUserID = rs.getInt("TransferUserID");
			int batNoStatus = rs.getInt("BatNoStatus");
			int receiveDepartmentID = rs.getInt("ReceiveDepartmentID");
			int receiveUserID = rs.getInt("ReceiveUserID");
			Date receiveTime = rs.getTimestamp("ReceiveTime");
			boolean receiveCheckedFlag = rs.getBoolean("ReceiveCheckedFlag");
			String batNoCreateUserName = rs.getString("BatNoCreateUserName");
			String transferDepartmentName = rs.getString("TransferDepartmentName");
			String receiveUserName = rs.getString("ReceiveUserName");
			String receiveDepartmentName = rs.getString("ReceiveDepartmentName");
			
			return new PaperTransferBatch(batNo, batNoCreateUserID, transferDepartmentID,  batNoStatus,insideFlag, transferTime, transferTotal, transferUserID, receiveDepartmentID, receiveUserID, receiveTime, receiveCheckedFlag, batNoCreateUserName, transferDepartmentName, receiveUserName, receiveDepartmentName);
		}
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
	
	@Override
	public boolean add(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ��");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch.getBatNo() == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ��");
				}
			}
			
			if (pFlag) {
				if ("".equals(pPaperTransferBatch.getBatNo())) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���κŷǷ�Ϊ��");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch.getBatNoCreateUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������ε��û���ŷǷ�Ϊ0");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch.getTransferDepartmentID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������ε��û��������ű�ŷǷ�Ϊ0");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_INSERT_PaperTransferBatch, 
						pPaperTransferBatch.getBatNo(),
						pPaperTransferBatch.getBatNoCreateUserID(),
						pPaperTransferBatch.getInsideFlag(),
						pPaperTransferBatch.getTransferDepartmentID());

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
	public boolean delete(PaperTransferBatch pPaperTansferBatche, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(int [] deptIDs,EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> pPaperTransferBatches, boolean insideFlag,ErrInfo pErrInfo){
		//static final String SQL_SELECT_findAll="";
		List<Integer> depts = new ArrayList<Integer>();
		for (int i = 0; i < deptIDs.length; i++) {
			depts.add(deptIDs[i]);
		}
		 
		/*String SQL_SELECT_findAll="select * from " +
				"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
				"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
				"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and BatNoStatus=:BatNoStatus AND TransferDepartmentID IN(:TransferDepartmentIDS) order by T9.TransferTime";*/
		
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("BatNoStatus", enumPaperTransferBatchesDealStatus.getEnumValue());
		parameterSource.addValue("TransferDepartmentIDS", depts);
		parameterSource.addValue("InsideFlag", insideFlag);
		//parameterSource.addValue("ReceiveCheckedFlag", receiveCheckedFlag);
		
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
		List<PaperTransferBatch> paperTransferBatchs = jdbcTemplate.query(SQL_SELECT_findAll, parameterSource, new PaperTransferBatchMapper());
		pPaperTransferBatches.addAll(paperTransferBatchs);
		return true;
	}

	@Override
	public boolean findByBatNO(String batNO, PaperTransferBatch pPaperTransferBatche, ErrInfo pErrInfo) {
		
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
				/*String SQL_SELECT_findByBatNO = "select * from " +
				"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
				"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
				"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and T9.BatNo=? order by T9.TransferTime";*/
		        List<PaperTransferBatch> paperTransferBatchs= getJdbcTemplate().query(SQL_SELECT_findByBatNO, new PaperTransferBatchMapper(), batNO);

				//���ز�ѯ���
				if (paperTransferBatchs.size() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("û���������");
				}else{
					pPaperTransferBatche.cloneFrom(paperTransferBatchs.get(0));
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean update(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo) {
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
				
				//String SQL_UPDATE_PaperTransferBatch= "UPDATE PaperTransferBatches SET BatNo=?, BatNoCreateUserID=?,InsideFlag=?";

				getJdbcTemplate().update(SQL_UPDATE_PaperTransferBatch, 
										pPaperTransferBatch.getBatNoCreateUserID(),
										pPaperTransferBatch.getInsideFlag(),
										pPaperTransferBatch.getBatNo());
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
	public boolean findPaperTransferBatchByConfirmFlag(PaperTransferBatch pPaperTransferBatch,boolean insideFlag,  ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			pErrPos = 1;
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatch.getBatNoCreateUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣû�г�ʼ��");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				List<String> batNos = getJdbcTemplate().queryForList(SQL_SELECT_FindPaperTransferBatchByConfirmFlag,
																					java.lang.String.class, 
																					pPaperTransferBatch.getBatNoCreateUserID(),
																					pPaperTransferBatch.getBatNoStatus(),
																					insideFlag);
				
				if(batNos.size() > 0){
					pPaperTransferBatch.setBatNo(batNos.get(0));
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

	@Override
	public boolean updateConfirmFlag(String paperTransferBatNo,UserInfo userInfo,int srchivesSum ,ErrInfo pErrInfo) {
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
				getJdbcTemplate().update(SQL_UPDATE_updateConfirmFlag,userInfo.getUserID(),userInfo.getDepartmentID(),new Date(), srchivesSum,paperTransferBatNo);
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
			pErrInfo.getContent().append(e.getMessage());
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

	public boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if(counts==null) {
				pFlag = false;
				pErrInfo.getContent().append("�������->ͳ�ƽ�����ϷǷ���");
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				counts.add(getJdbcTemplate().queryForInt(String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult=1")));
				System.out.println("����������δ����������"+String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult=1"));
				
				counts.add(getJdbcTemplate().queryForInt(String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult<>2")));
				System.out.println("�����������ܽ���������"+String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult<>2"));
				
				

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
	public boolean updateForReceive(PaperTransferBatch pPaperTransferBatch, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,ErrInfo pErrInfo) {
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
				getJdbcTemplate().update(SQL_UPDATE_UpdateForReceive,
						enumPaperTransferBatchesDealStatus.getEnumValue(),
						pPaperTransferBatch.getReceiveDepartmentID(),
						pPaperTransferBatch.getReceiveUserID(),
						pPaperTransferBatch.getReceiveTime(),
						pPaperTransferBatch.getBatNo());
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateBatState(String paperTransferBatNo, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,ErrInfo pErrInfo) {
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
				String sql = "UPDATE PaperTransferBatches SET BatNoStatus=? WHERE BatNo=?";
				getJdbcTemplate().update(sql, enumPaperTransferBatchesDealStatus.getEnumValue(),paperTransferBatNo);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findByCondition(int[] deptIDs, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,
			PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches,DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			List<Integer> depts = new ArrayList<Integer>();
			for (int i = 0; i < deptIDs.length; i++) {
				depts.add(deptIDs[i]);
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM (SELECT ROW_NUMBER() over(ORDER BY TransferTime) as rownum, * FROM" +
						"(SELECT * FROM PaperTransferBatches T3,(SELECT T1.UserID AS SUserID ,T1.RealName AS BatNoCreateUserName,T2.ID AS SDepartmentID,T2.Name AS TransferDepartmentName FROM UserInfo T1,DD_DepartmentInfo T2 WHERE T1.DepartmentID=T2.ID) T4 WHERE T3.TransferUserID=T4.SUserID) AS T9," +
						
						"(SELECT T8.ReceiveUserName AS ReceiveUserName,T8.ReceiveDepartmentName AS ReceiveDepartmentName,T8.UserID AS RUserID,T7.BatNo AS BatNoT7 FROM PaperTransferBatches T7 LEFT JOIN " +
						"(SELECT T5.UserID AS UserID  ,T5.RealName AS ReceiveUserName ,T6.ID AS DepartmentID ,T6.Name AS ReceiveDepartmentName  FROM UserInfo T5,DD_DepartmentInfo T6 WHERE T5.DepartmentID=T6.ID) T8 ON T7.ReceiveUserID=T8.UserID) AS T10 " +
						
						"WHERE T9.BatNo = T10.BatNoT7 AND BatNoStatus>=:BatNoStatus AND TransferDepartmentID IN(:TransferDepartmentIDS) AND InsideFlag=0 %1$s) T11 WHERE T11.rownum>:startRow AND T11.rownum<=:endRow";
				
				String formatStr = "AND TransferTime BETWEEN :BeginDate AND :EndDate";
				
				String sql_count = "SELECT COUNT(*) FROM PaperTransferBatches WHERE BatNoStatus>=:BatNoStatus AND TransferDepartmentID IN(:TransferDepartmentIDS) AND InsideFlag=0 %1$s";
				
				MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
				parameterSource.addValue("BatNoStatus", enumPaperTransferBatchesDealStatus.getEnumValue());
				parameterSource.addValue("TransferDepartmentIDS", depts);

				if (paperTransferBatchesQueryCondition.getTransferDateBegin() != null && paperTransferBatchesQueryCondition.getTransferDateEnd() != null) {
					sql = String.format(sql, formatStr);
					sql_count = String.format(sql_count, formatStr);
					parameterSource.addValue("BeginDate", paperTransferBatchesQueryCondition.getTransferDateBegin());
					parameterSource.addValue("EndDate", paperTransferBatchesQueryCondition.getTransferDateEnd());
				}else{
					sql = String.format(sql, "");
					sql_count = String.format(sql_count, "");
				}
				
				dataPageInfo.setRowCount(new NamedParameterJdbcTemplate(getDataSource()).queryForInt(sql_count, parameterSource));
				
				parameterSource.addValue("startRow", (dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				parameterSource.addValue("endRow", dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize());
				
				List<PaperTransferBatch> paperTransferBatchs = new NamedParameterJdbcTemplate(getDataSource()).query(sql,parameterSource,new PaperTransferBatchMapper());
				
				if (paperTransferBatchs.size()>0) {
					paperTransferBatches.addAll(paperTransferBatchs);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findByCondition(EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, int[] userIDs,
			PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches, DataPageInfo dataPageInfo,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			List<Integer> users = new ArrayList<Integer>();
			for (int i = 0; i < userIDs.length; i++) {
				users.add(userIDs[i]);
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM (SELECT ROW_NUMBER() over(ORDER BY TransferTime) as rownum, * FROM" +
						"(SELECT * FROM PaperTransferBatches T3,(SELECT T1.UserID AS SUserID ,T1.RealName AS BatNoCreateUserName,T2.ID AS SDepartmentID,T2.Name AS TransferDepartmentName FROM UserInfo T1,DD_DepartmentInfo T2 WHERE T1.DepartmentID=T2.ID) T4 WHERE T3.TransferUserID=T4.SUserID) AS T9," +
						
						"(SELECT T8.ReceiveUserName AS ReceiveUserName,T8.ReceiveDepartmentName AS ReceiveDepartmentName,T8.UserID AS RUserID,T7.BatNo AS BatNoT7 FROM PaperTransferBatches T7 LEFT JOIN " +
						"(SELECT T5.UserID AS UserID  ,T5.RealName AS ReceiveUserName ,T6.ID AS DepartmentID ,T6.Name AS ReceiveDepartmentName  FROM UserInfo T5,DD_DepartmentInfo T6 WHERE T5.DepartmentID=T6.ID) T8 ON T7.ReceiveUserID=T8.UserID) AS T10 " +
						
						"WHERE T9.BatNo = T10.BatNoT7 AND TransferUserID IN(:TransferUserIDs) AND BatNoStatus>=:BatNoStatus AND InsideFlag=:InsideFlag %1$s) T11 WHERE T11.rownum>:startRow AND T11.rownum<=:endRow";
				
				String formatStr = "AND TransferTime BETWEEN :BeginDate AND :EndDate";
				
				String sql_count = "SELECT COUNT(*) FROM PaperTransferBatches WHERE TransferUserID IN(:TransferUserIDs) AND BatNoStatus>=:BatNoStatus AND InsideFlag=:InsideFlag %1$s";
				
				MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
				//parameterSource.addValue("BatNoStatus", enumPaperTransferBatchesDealStatus.getEnumValue());
				parameterSource.addValue("TransferUserIDs", users);
				parameterSource.addValue("BatNoStatus", enumPaperTransferBatchesDealStatus.getEnumValue());
				parameterSource.addValue("InsideFlag", insideFlag);
				
				if (paperTransferBatchesQueryCondition.getTransferDateBegin() != null && paperTransferBatchesQueryCondition.getTransferDateEnd() != null) {
					sql = String.format(sql, formatStr);
					sql_count = String.format(sql_count, formatStr);
					parameterSource.addValue("BeginDate", paperTransferBatchesQueryCondition.getTransferDateBegin());
					parameterSource.addValue("EndDate", paperTransferBatchesQueryCondition.getTransferDateEnd());
				}else{
					sql = String.format(sql, "");
					sql_count = String.format(sql_count, "");
				}
				
				dataPageInfo.setRowCount(new NamedParameterJdbcTemplate(getDataSource()).queryForInt(sql_count, parameterSource));
				
				parameterSource.addValue("startRow", (dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				parameterSource.addValue("endRow", dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize());
				
				List<PaperTransferBatch> paperTransferBatchs = new NamedParameterJdbcTemplate(getDataSource()).query(sql,parameterSource,new PaperTransferBatchMapper());
				
				if (paperTransferBatchs.size()>0) {
					paperTransferBatches.addAll(paperTransferBatchs);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findPaperTransferBatchsByTransferUser(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, List<PaperTransferBatch> pPaperTransferBatches, boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> userIDs = new ArrayList<Integer>();
		userIDs.add(userID);
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT * FROM " +
							"(SELECT * FROM PaperTransferBatches T3,(SELECT T1.UserID AS SUserID ,T1.RealName AS BatNoCreateUserName,T2.ID AS SDepartmentID,T2.Name AS TransferDepartmentName FROM UserInfo T1,DD_DepartmentInfo T2 WHERE T1.DepartmentID=T2.ID) T4 WHERE T3.TransferUserID=T4.SUserID) AS T9, " +
							"(SELECT T8.ReceiveUserName AS ReceiveUserName,T8.ReceiveDepartmentName AS ReceiveDepartmentName,T8.UserID AS RUserID,T7.BatNo AS BatNoT7 FROM PaperTransferBatches T7 LEFT JOIN (SELECT T5.UserID AS UserID  ,T5.RealName AS ReceiveUserName ,T6.ID AS DepartmentID ,T6.Name AS ReceiveDepartmentName  FROM UserInfo T5,DD_DepartmentInfo T6 " +
							"WHERE T5.DepartmentID=T6.ID) T8 ON T7.ReceiveUserID=T8.UserID) AS T10 WHERE T9.BatNo = T10.BatNoT7 AND BatNoStatus=:BatNoStatus  AND TransferUserID IN(:TransferUserIDs) AND InsideFlag=:InsideFlag order by T9.TransferTime";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("BatNoStatus", enumPaperTransferBatchesDealStatus.getEnumValue());
				parameterSource.addValue("TransferUserIDs", userIDs);
				parameterSource.addValue("InsideFlag", insideFlag);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				List<PaperTransferBatch> paperTransferBatchs = jdbcTemplate.query(sql, parameterSource, new PaperTransferBatchMapper());
				pPaperTransferBatches.addAll(paperTransferBatchs);
				
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateTransferTotal(String batNo, ErrInfo pErrInfo) {
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
				String sql = "UPDATE PaperTransferBatches SET TransferTotal=(SELECT COUNT(*) FROM PaperTransferBatchesDetails where (TransferBatNo=:TransferBatNo OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND ReceiveCheckResult IN (0,1,3)) WHERE BatNo=:TransferBatNo";
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNo", batNo);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				jdbcTemplate.update(sql, parameterSource);
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
			pErrInfo.getContent().append(e.getMessage());
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
