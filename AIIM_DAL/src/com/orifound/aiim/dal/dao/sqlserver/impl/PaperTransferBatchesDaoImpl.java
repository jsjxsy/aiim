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
 * 移交批次信息表的DAO实现类
 * @author Administrator
 *
 */
public class PaperTransferBatchesDaoImpl extends JdbcDaoSupport implements IPaperTransferBatchesDao {

	/**
	 * 新增批次信息的SQL
	 */
	private static final String SQL_INSERT_PaperTransferBatch= "INSERT INTO PaperTransferBatches (BatNo, BatNoCreateUserID,InsideFlag,TransferDepartmentID) VALUES (?,?,?,?)";
	
	/**
	 * 新增批次信息的SQL
	 */
	private static final String SQL_UPDATE_PaperTransferBatch= "UPDATE PaperTransferBatches SET BatNoCreateUserID=?,InsideFlag=? where BatNo=?";
	
	/**
	 * 查询当前用户未移交的批次信息的SQL
	 */
	private static final String SQL_SELECT_FindPaperTransferBatchByConfirmFlag= "SELECT BatNo FROM PaperTransferBatches WHERE BatNoCreateUserID=? AND BatNoStatus=? AND InsideFlag=?";
	
	
	/**
	 * 当前用户确认移交未移交的批次信息的SQL
	 */
	private static final String SQL_UPDATE_updateConfirmFlag= "UPDATE PaperTransferBatches SET BatNoStatus=1,TransferUserID=?,TransferDepartmentID=?,TransferTime=?,TransferTotal=?  WHERE BatNo=?";
	
	/**
	 * 新增批次信息的SQL
	 */
	private static final String SQL_UPDATE_UpdateForReceive= "UPDATE PaperTransferBatches SET  BatNoStatus=?,ReceiveDepartmentID=?,ReceiveUserID =?,ReceiveTime=? WHERE BatNo=?";
	
	/**
	 * 根据批次号查找批次信息，返回的结果中包含批次的创建人、创建部门、接收人、接收部门
	 */
	private static final String SQL_SELECT_findByBatNO = "select * from " +
	"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
	"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
	"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and T9.BatNo=? order by T9.TransferTime";
	
	/**
	 * 查找所有批次信息，返回的结果中包含批次的创建人、创建部门、接收人、接收部门
	 */
	private static final  String SQL_SELECT_findAll="select * from " +
	"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
	"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
	"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and BatNoStatus=:BatNoStatus  AND TransferDepartmentID IN(:TransferDepartmentIDS) AND InsideFlag=:InsideFlag order by T9.TransferTime";
	
	
	/**
	 * 绩效管理查询档案管理室接收档案情况的SQL语句
	 * %1$s ptd.ReceiveCheckResult<>2 总数量、ptd.ReceiveCheckResult=1为未接收数量
	 */
	private final String SQL_SELECT_ReceiveArchives = "SELECT COUNT(1) FROM PaperTransferBatches pt,PaperTransferSubBatches pts,PaperTransferBatchesDetails ptd" +
			" WHERE pt.BatNo=pts.BatNo AND pts.SubBatNo=ptd.TransferBatNo " +
			" AND pt.InsideFlag=1 AND pt.BatNoStatus=2 AND pt.ReceiveCheckedFlag=0" +
			" AND %1$s";
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean add(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息对象非法为空");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch.getBatNo() == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息对象非法为空");
				}
			}
			
			if (pFlag) {
				if ("".equals(pPaperTransferBatch.getBatNo())) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息批次号非法为空");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch.getBatNoCreateUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("创建批次的用户编号非法为0");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch.getTransferDepartmentID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("创建批次的用户所属部门编号非法为0");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_INSERT_PaperTransferBatch, 
						pPaperTransferBatch.getBatNo(),
						pPaperTransferBatch.getBatNoCreateUserID(),
						pPaperTransferBatch.getInsideFlag(),
						pPaperTransferBatch.getTransferDepartmentID());

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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				/*String SQL_SELECT_findByBatNO = "select * from " +
				"(select * from PaperTransferBatches T3,(select T1.UserID as SUserID ,T1.RealName as BatNoCreateUserName,T2.ID as SDepartmentID,T2.Name as TransferDepartmentName from UserInfo T1,DD_DepartmentInfo T2 where T1.DepartmentID=T2.ID) T4 where T3.TransferUserID=T4.SUserID) as T9, " +
				"(select T8.ReceiveUserName as ReceiveUserName,T8.ReceiveDepartmentName as ReceiveDepartmentName,T8.UserID as RUserID,T7.BatNo as BatNoT7 from PaperTransferBatches T7 left join (select T5.UserID as UserID  ,T5.RealName as ReceiveUserName ,T6.ID as DepartmentID ,T6.Name as ReceiveDepartmentName  from UserInfo T5,DD_DepartmentInfo T6 " +
				"where T5.DepartmentID=T6.ID) T8 on T7.ReceiveUserID=T8.UserID) as T10 where T9.BatNo = T10.BatNoT7 and T9.BatNo=? order by T9.TransferTime";*/
		        List<PaperTransferBatch> paperTransferBatchs= getJdbcTemplate().query(SQL_SELECT_findByBatNO, new PaperTransferBatchMapper(), batNO);

				//返回查询结果
				if (paperTransferBatchs.size() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("没有这个批次");
				}else{
					pPaperTransferBatche.cloneFrom(paperTransferBatchs.get(0));
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
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean update(PaperTransferBatch pPaperTransferBatch, ErrInfo pErrInfo) {
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
				
				//String SQL_UPDATE_PaperTransferBatch= "UPDATE PaperTransferBatches SET BatNo=?, BatNoCreateUserID=?,InsideFlag=?";

				getJdbcTemplate().update(SQL_UPDATE_PaperTransferBatch, 
										pPaperTransferBatch.getBatNoCreateUserID(),
										pPaperTransferBatch.getInsideFlag(),
										pPaperTransferBatch.getBatNo());
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean findPaperTransferBatchByConfirmFlag(PaperTransferBatch pPaperTransferBatch,boolean insideFlag,  ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatch.getBatNoCreateUserID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为0");
				}
			}
			
			if (pFlag) {
				if (pPaperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("批次信息没有初始化");
				}
			}
			//执行SQL语句
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
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean updateConfirmFlag(String paperTransferBatNo,UserInfo userInfo,int srchivesSum ,ErrInfo pErrInfo) {
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
				getJdbcTemplate().update(SQL_UPDATE_updateConfirmFlag,userInfo.getUserID(),userInfo.getDepartmentID(),new Date(), srchivesSum,paperTransferBatNo);
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
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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

	public boolean receiverCondition(List<Integer> counts, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if(counts==null) {
				pFlag = false;
				pErrInfo.getContent().append("传入参数->统计结果集合非法。");
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				counts.add(getJdbcTemplate().queryForInt(String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult=1")));
				System.out.println("档案管理室未接收数量："+String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult=1"));
				
				counts.add(getJdbcTemplate().queryForInt(String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult<>2")));
				System.out.println("档案管理室总接收数量："+String.format(SQL_SELECT_ReceiveArchives, "ptd.ReceiveCheckResult<>2"));
				
				

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
	public boolean updateForReceive(PaperTransferBatch pPaperTransferBatch, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,ErrInfo pErrInfo) {
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
				getJdbcTemplate().update(SQL_UPDATE_UpdateForReceive,
						enumPaperTransferBatchesDealStatus.getEnumValue(),
						pPaperTransferBatch.getReceiveDepartmentID(),
						pPaperTransferBatch.getReceiveUserID(),
						pPaperTransferBatch.getReceiveTime(),
						pPaperTransferBatch.getBatNo());
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
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean updateBatState(String paperTransferBatNo, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,ErrInfo pErrInfo) {
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
				String sql = "UPDATE PaperTransferBatches SET BatNoStatus=? WHERE BatNo=?";
				getJdbcTemplate().update(sql, enumPaperTransferBatchesDealStatus.getEnumValue(),paperTransferBatNo);
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
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean findByCondition(int[] deptIDs, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,
			PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches,DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			List<Integer> depts = new ArrayList<Integer>();
			for (int i = 0; i < deptIDs.length; i++) {
				depts.add(deptIDs[i]);
			}
			
			//执行SQL语句
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
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean findByCondition(EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, int[] userIDs,
			PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, List<PaperTransferBatch> paperTransferBatches, DataPageInfo dataPageInfo,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			List<Integer> users = new ArrayList<Integer>();
			for (int i = 0; i < userIDs.length; i++) {
				users.add(userIDs[i]);
			}
			
			//执行SQL语句
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
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean findPaperTransferBatchsByTransferUser(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, List<PaperTransferBatch> pPaperTransferBatches, boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> userIDs = new ArrayList<Integer>();
		userIDs.add(userID);
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
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
			//SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		} catch (Exception e) {
			//其他异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	public boolean updateTransferTotal(String batNo, ErrInfo pErrInfo) {
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
				String sql = "UPDATE PaperTransferBatches SET TransferTotal=(SELECT COUNT(*) FROM PaperTransferBatchesDetails where (TransferBatNo=:TransferBatNo OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND ReceiveCheckResult IN (0,1,3)) WHERE BatNo=:TransferBatNo";
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("TransferBatNo", batNo);
				
				NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				jdbcTemplate.update(sql, parameterSource);
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
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {

			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
