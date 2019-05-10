/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 实物档案利用出去明细Dao接口实现类
 * @author Administrator
 *
 */
public class ArchivesUseOutInfoDaoImpl extends JdbcDaoSupport implements IArchivesUseOutInfoDao {
	/**
	 * 构造函数
	 */
	public ArchivesUseOutInfoDaoImpl() {

	}	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesUseOutInfoMapper implements RowMapper<ArchivesUseOutInfo>
	{		
		@Override
		public ArchivesUseOutInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int useRegID = rs.getInt("UseRegID");
			boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String title = rs.getString("Title");
			int pageSum = rs.getInt("PageSum");
			Date shouldReturnDate = rs.getTimestamp("ShouldReturnDate");
			
			return new ArchivesUseOutInfo(iD,useRegID,borrowFlag,archivesTypeID,nBXH,archivesID,archivesBarcode,title,pageSum,shouldReturnDate);
		}
	}
	
	/**
	 * 全映射器：查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesUseOutInfoFullMapper implements RowMapper<ArchivesUseOutInfo>
	{		
		@Override
		public ArchivesUseOutInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
//outinfo	C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID,
//Person    B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel,
//Register	A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark  
			//=====构造ArchivesUseOutInfo=====
			int iD = rs.getInt("ID");
			int useRegID = rs.getInt("UseRegID");
			boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String title = rs.getString("Title");
			int pageSum = rs.getInt("PageSum");
			Date shouldReturnDate = rs.getTimestamp("ShouldReturnDate");
			Date returnDate = rs.getTimestamp("ReturnDate");
			ArchivesUseOutInfo archivesUseOutInfo =  new ArchivesUseOutInfo(iD,useRegID,borrowFlag,archivesTypeID,nBXH,archivesID,archivesBarcode,title,pageSum,shouldReturnDate,returnDate);
			//======构造ArchivesUseOutInfo=======
			
			int userID = rs.getInt("UsePersonID");
			String name = rs.getString("Name");
			String iDCardNo = rs.getString("IDCardNo");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String department = rs.getString("Department");
			String tel = rs.getString("Tel");
			String email = rs.getString("Email");
			int areaID = rs.getInt("AreaFlag");
			ArchivesUsePersonInfo archivesUsePersonInfo = new ArchivesUsePersonInfo(userID, name, iDCardNo, iDCardTypeID, department, tel, email, areaID);
			//======构造ArchivesUseOutInfo=======
			int registerID = rs.getInt("UseRegID");
			//boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int purposeID = rs.getInt("PurposeID");
			int usePersonsCount = rs.getInt("UsePersonsCount");
			int useArchivesCount = rs.getInt("UseArchivesCount");
			Date useDate = rs.getTimestamp("UseDate");
			int usePersonID = rs.getInt("UsePersonID");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");			
			String purposeText = rs.getString("Purpose");
			String managerUserName = "";
			ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister(registerID, borrowFlag, purposeID, usePersonsCount, useArchivesCount, useDate, usePersonID, managerUserID, remark, managerUserName, purposeText);
			//ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister(registerID, borrowFlag, purposeID, usePersonsCount, useArchivesCount, useDate, usePersonID, managerUserID, remark);
			//将利用人信息挂到利用登记信息上
			archivesUseRegister.setArchivesUsePersonInfo(archivesUsePersonInfo);
			//将利用登记信息挂到档案利用信息明细上
			archivesUseOutInfo.setArchivesUseRegister(archivesUseRegister);
			 return archivesUseOutInfo;
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
	
	


	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#delete(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据条件查询档案利用出去详细信息的SQL语句
	 */
//	private final String SQL_SELECT_findByQueryCondition1 = "SELECT "+
//		" C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID, "+
//		" B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel, "+
//		" A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark "+  
//		" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID "+
//		" AND ArchivesID LIKE 'G01%' AND A.BorrowFlag=1 order by ID ASC ";
	//%1$s:查询条件 
	private  String SQL_SELECT_findByQueryCondition_COUNT = " SELECT COUNT(C.ID) "+ 
	" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID = B.ID AND C.UseRegID = A.ID  %1$s ";
	
	
	//%1$s:查询条件(以and开头 )；%2$s：页大小；<br>%3$s：已经读取过的记录数量<br>
	private  String SQL_SELECT_findByQueryCondition =	 
	"SELECT TOP %2$s "+
	" C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID, "+
	" B.AreaFlag ,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel, "+
	" A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark, "+  
	" D.Purpose "+
	" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C,DD_ArchivesUsePurpose D WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID AND A.PurposeID =D.ID"+
	//分页条件
	" AND C.ID IN ( "+
	" SELECT  C.ID FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID %1$s "+ 
	" AND C.ID > "+
	" ( "+
	" SELECT ISNULL(MAX(ID),0) "+
	"  FROM  "+
	"  ( "+
	"  SELECT TOP %3$s C.ID "+
	"   FROM "+ 
	"   ArchivesUseRegister A, "+
	"   ArchivesUsePersonInfo B, "+
	"   (SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C "+ 
	"   WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID %1$s  ORDER BY ID ASC "+
	" ) AS T "+
	" ))ORDER BY C.ID ASC ";
	
	//未修改borrowFlag
//	private  String SQL_SELECT_findByQueryCondition =	 
//		"SELECT TOP %2$s "+
//		" C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID, "+
//		" B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel, "+
//		" A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark "+  
//		" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID "+
//		//分页条件
//		" AND C.ID IN ( "+
//		" SELECT  C1.ID FROM ArchivesUseRegister A1,ArchivesUsePersonInfo B1,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C1 WHERE A1.UsePersonID =B1.ID AND C1.UseRegID = A1.ID %1$s "+ 
//		" AND C.ID > "+
//		" ( "+
//		" SELECT ISNULL(MAX(ID),0) "+
//		"  FROM  "+
//		"  ( "+
//		"  SELECT TOP %3$s C2.ID "+
//		"   FROM "+ 
//		"   ArchivesUseRegister A2, "+
//		"   ArchivesUsePersonInfo B2, "+
//		"   (SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C2 "+ 
//		"   WHERE A2.UsePersonID =B2.ID AND C2.UseRegID = A2.ID %1$s  ORDER BY ID ASC "+
//		" ) AS T "+
//		" ))ORDER BY C.ID ASC ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByQueryCondition(String querySQL,DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		String localSql = "";
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
				localSql = String.format(SQL_SELECT_findByQueryCondition_COUNT,querySQL);
System.out.println("localSql1:"+ localSql);
				//获取总记录数
				int pRowCount = jdbcTemplate.queryForInt(localSql);
				dataPageInfo.setRowCount(pRowCount);
System.out.println("pRowCount:"+pRowCount);
				
				localSql = String.format(SQL_SELECT_findByQueryCondition,querySQL, dataPageInfo.getPageSize(), (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize());
System.out.println("localSql2:"+ localSql);
				List<ArchivesUseOutInfo> pEntitys=jdbcTemplate.query(localSql,new ArchivesUseOutInfoFullMapper());

				//返回查询结果
				if (pEntitys.size() > 0) {					
					archivesUseOutInfos.addAll(pEntitys);
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
System.out.println("error:"+ pErrInfo.toString());
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#findByID(int, com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
		/**
	 * 根据档案条形码查询档实物档案利用出去明细的SQL语句
	 */
	private final String SQL_SELECT_findByArchivesBarcode = "SELECT * FROM ArchivesUseOutInfo WHERE ArchivesBarcode = ?";
	
	@Override
	public boolean findByArchivesBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
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
				List<ArchivesUseOutInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByArchivesBarcode,new ArchivesUseOutInfoMapper(),archivesUseOutInfo.getArchivesBarcode());

				//返回查询结果
				if (pEntitys.size() > 0) {
					archivesUseOutInfo.cloneFrom(pEntitys.get(0));
				}else{
					pFlag = false;
					pErrInfo.getContent().insert(0,"没有找到条形码‘" + archivesUseOutInfo.getArchivesBarcode() + "’所指定的档案利用出去明细  记录！");
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
	
	/**
	 * 根据利用登记编号查询所有实物档案利用出去明细的SQL语句
	 */
	private final String SQL_SELECT_findByRegisterID = "SELECT * FROM ArchivesUseOutInfo WHERE UseRegID = ?";
	
	@Override
	public boolean findByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
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
				List<ArchivesUseOutInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByRegisterID,new ArchivesUseOutInfoMapper(),registerID);

				//返回查询结果
				if (pEntitys.size() > 0) {
					archivesUseOutInfos.addAll(pEntitys);
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

	
	
	
	/**
	 * 添加实物档案利用出去明细的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUseOutInfo(UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate) " +
			"VALUES(:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate)";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#save(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean add(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
	
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				paramSource.addValue("UseRegID", archivesUseOutInfo.getUseRegID());
				paramSource.addValue("BorrowFlag", archivesUseOutInfo.getBorrowFlag());
				paramSource.addValue("ArchivesTypeID", archivesUseOutInfo.getArchivesTypeID());
				paramSource.addValue("NBXH", archivesUseOutInfo.getNBXH());
				paramSource.addValue("ArchivesID", archivesUseOutInfo.getArchivesID());
				paramSource.addValue("ArchivesBarcode", archivesUseOutInfo.getArchivesBarcode());
				paramSource.addValue("Title", archivesUseOutInfo.getTitle());
				paramSource.addValue("PageSum", archivesUseOutInfo.getPageSum());
				paramSource.addValue("ShouldReturnDate", archivesUseOutInfo.getShouldReturnDate());
				pErrPos = 3;
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				archivesUseOutInfo.setID(keyHolder.getKey().intValue());
			
				
				//销毁局部变量
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
	// Table Name: ArchivesUseOutInfo
	// Columns List,Can Used in SELECT SQL: ID,UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate
	// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate
	// Columns List,Can Used in UPDATE SQL: ID=:ID,UseRegID=:UseRegID,BorrowFlag=:BorrowFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,ArchivesBarcode=:ArchivesBarcode,Title=:Title,PageSum=:PageSum,ShouldReturnDate=:ShouldReturnDate
	
	/**
	 * 归还档案：将记录从利用出去表复制到归还利用档案表中的SQL语句
	 */
	private final String SQL_INSERT_returnArchivesByBarcode = "INSERT INTO ArchivesUseReturnInfo SELECT *,GETDATE() FROM ArchivesUseOutInfo WHERE ArchivesBarcode = ?";
	
	/**
	 * 归还档案：将利用出去表中的记录删除的SQL语句
	 */
	private final String SQL_DELETE_returnArchivesByBarcode = "DELETE FROM ArchivesUseOutInfo WHERE ArchivesBarcode = ?";
	
	@Override
	public boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
				int rowsNum = jdbcTemplate.update(SQL_INSERT_returnArchivesByBarcode, archivesUseOutInfo.getArchivesBarcode());
				pErrPos = 3;
				if (rowsNum > 0) {
					//插入成功后删除利用出去明细信息
					jdbcTemplate.update(SQL_DELETE_returnArchivesByBarcode, archivesUseOutInfo.getArchivesBarcode());
					
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
	
	
	/**
	 * 根据档案条形码更新档案应归还时间的SQL语句
	 */
	private final String SQL_UPDATE_updateShouldReturnDate = "UPDATE ArchivesUseOutInfo SET ShouldReturnDate = ? WHERE ArchivesBarcode = ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#update(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
				int rowsNum = jdbcTemplate.update(SQL_UPDATE_updateShouldReturnDate, archivesUseOutInfo.getShouldReturnDate(),archivesUseOutInfo.getArchivesBarcode());
				
				//返回查询结果
				if (rowsNum == 0) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行SQL，没有找到指定条形码所对应的档案可以去更新归还日期");
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
