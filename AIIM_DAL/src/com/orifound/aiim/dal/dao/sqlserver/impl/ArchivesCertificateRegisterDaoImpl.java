package com.orifound.aiim.dal.dao.sqlserver.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IArchivesCertificateRegisterDao;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DateQuerycondition;
import com.orifound.aiim.entity.ErrInfo;

public class ArchivesCertificateRegisterDaoImpl extends JdbcDaoSupport  implements IArchivesCertificateRegisterDao{
	
	/**
	 * 构造函数
	 */
	public ArchivesCertificateRegisterDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesCertificateRegisterDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesCertificateRegisterMapper implements RowMapper<ArchivesCertificateRegister>
	{
		
		@Override
		public ArchivesCertificateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int personID = rs.getInt("PersonID");
			double shouldCharge = rs.getBigDecimal("ShouldCharge").doubleValue();
			double realCharge = rs.getBigDecimal("RealCharge").doubleValue();
			Date registerDate = rs.getTimestamp("RegisterDate");
			String invoiceSN = rs.getString("InvoiceSN");
			int managerUserID = rs.getInt("ManagerUserID");
			String realName = rs.getString("RealName");
			String remark = rs.getString("Remark");
			
			return new ArchivesCertificateRegister(iD,personID,shouldCharge,realCharge,registerDate,invoiceSN,managerUserID,realName,remark);
		}
	}
	
	private class ArchivesCertificateRegisterMapper2 implements RowMapper<ArchivesCertificateRegister>
	{
		
		@Override
		public ArchivesCertificateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			double shouldCharge = rs.getBigDecimal("ShouldCharge").doubleValue();
			double realCharge = rs.getBigDecimal("RealCharge").doubleValue();
			int managerUserID = rs.getInt("ManagerUserID");
			String realName = rs.getString("RealName");
			
			return new ArchivesCertificateRegister(shouldCharge,realCharge,managerUserID,realName);
		}
	}
	
	private class ArchivesCertificateRegisterMapper3 implements RowMapper<ArchivesCertificateRegister>
	{
		
		@Override
		public ArchivesCertificateRegister mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			double shouldCharge = rs.getBigDecimal("ShouldCharge").doubleValue();
			double realCharge = rs.getBigDecimal("RealCharge").doubleValue();
			
			return new ArchivesCertificateRegister(shouldCharge,realCharge);
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
	
	/**
	 * 查询符合时间段档案出证登记集合的的SQL语句
	 * 
	 */
	private final String SQL_SELECT_By_Condition = " SELECT  A.ManagerUserID,SUM(A.ShouldCharge) AS ShouldCharge,SUM(A.RealCharge) AS RealCharge,B.RealName FROM ArchivesCertificateRegister A,UserInfo B "
												+" WHERE A.ManagerUserID = B.UserID AND RegisterDate BETWEEN :beginTime AND :endTime "
												+" GROUP BY A.ManagerUserID,B.RealName ORDER BY A.ManagerUserID";
	/**
	 * 的SQL语句
	 */
	private final String SQL_SELECT_Count = "SELECT COUNT(*) FROM ArchivesCertificateRegister WHERE RegisterDate between  :beginTime and  :endTime AND ManagerUserID = :ManagerUserID";
	
	/**
	 * 查询指定利用人档案出证登记的SQL语句
	 */
	private final String SQL_SELECT_By_Condition_ManagerUserID = "SELECT * FROM ("+
		" SELECT  B.RealName,A.ID,A.PersonID,A.ShouldCharge,A.RealCharge,A.RegisterDate,A.InvoiceSN,A.ManagerUserID,A.Remark,ROW_NUMBER() OVER(ORDER BY ID) RowNum "
		+" FROM ArchivesCertificateRegister A,UserInfo B WHERE  A.PersonID = B.UserID  AND A.ManagerUserID = :ManagerUserID  AND RegisterDate BETWEEN  :beginTime AND  :endTime "
		+") AS P  WHERE P.RowNum BETWEEN :startPage AND :endPage "
		+" ORDER BY RegisterDate DESC";	
	
	/**															
	 * 															
	 * 根据时间段统计的SQL语句
	 */
	private final String SQL_SELECT_Total = "SELECT SUM(A.ShouldCharge) ShouldCharge,SUM(A.RealCharge) RealCharge FROM ArchivesCertificateRegister A "
											+" WHERE  A.RegisterDate BETWEEN :beginTime AND :endTime" ;
	@Override
	public boolean delete(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByID(int pID, ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ArchivesCertificateRegister pArchivesCertificateRegister, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findByCondition(DateQuerycondition dateQuerycondition, List<ArchivesCertificateRegister> pArchivesCertificateRegisters, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("beginTime", dateQuerycondition.getBeginTime(),Types.DATE);
				sqlParameterSource.addValue("endTime", dateQuerycondition.getEndTime(),Types.DATE);
				List<ArchivesCertificateRegister> pEntitys = namedParameterJdbcTemplate.query(SQL_SELECT_By_Condition,sqlParameterSource, new ArchivesCertificateRegisterMapper2());
					//返回查询结果
				if (pEntitys.size() > 0) {
						pArchivesCertificateRegisters.addAll(pEntitys);
					}	
				//查询总计结果
				List<ArchivesCertificateRegister> pEntitys2 = namedParameterJdbcTemplate.query(SQL_SELECT_Total,sqlParameterSource, new ArchivesCertificateRegisterMapper3());
				if (pEntitys2.size() >0) {
					pArchivesCertificateRegisters.add(pEntitys2.get(0));
				}
				//销毁局部变量
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
	public boolean findByCondition(int pManagerUserID,DateQuerycondition dateQuerycondition, DataPageInfo dataPageInfo, List<ArchivesCertificateRegister> pArchivesCertificateRegisters,
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
				sqlParameterSource.addValue("beginTime", dateQuerycondition.getBeginTime(),Types.DATE);
				sqlParameterSource.addValue("endTime", dateQuerycondition.getEndTime(),Types.DATE);
				sqlParameterSource.addValue("ManagerUserID", pManagerUserID,Types.INTEGER);
				
				int rowCount = namedParameterJdbcTemplate.queryForInt(SQL_SELECT_Count,sqlParameterSource);
				dataPageInfo.setRowCount(rowCount);
				
				MapSqlParameterSource sqlParameterSource2 = new MapSqlParameterSource();
				sqlParameterSource2.addValue("beginTime", dateQuerycondition.getBeginTime(),Types.DATE);
				sqlParameterSource2.addValue("endTime", dateQuerycondition.getEndTime(),Types.DATE);
				sqlParameterSource2.addValue("ManagerUserID", pManagerUserID,Types.INTEGER);
				sqlParameterSource2.addValue("startPage", 1+(dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize(),Types.INTEGER);
				sqlParameterSource2.addValue("endPage", dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize(),Types.INTEGER);
				List<ArchivesCertificateRegister> pEntitys = namedParameterJdbcTemplate.query(SQL_SELECT_By_Condition_ManagerUserID,sqlParameterSource2, new ArchivesCertificateRegisterMapper());
					//返回查询结果
				if (pEntitys.size() > 0) {
						pArchivesCertificateRegisters.addAll(pEntitys);
					}	
		
				//销毁局部变量
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
