package com.orifound.aiim.dal.dao.sqlserver.impl;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ISystemLogInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.LogInfo;

public class SystemLogInfoDaoImpl extends JdbcDaoSupport implements ISystemLogInfoDao {

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//	private class LogInfoMapper implements RowMapper<LogInfo>
//	{
//		
//		@Override
//		public LogInfo mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int userID = rs.getInt("UserID");
//			String userName = rs.getString("UserName");
//			String realName = rs.getString("RealName");
//			int featureID = rs.getInt("FeatureID");
//			String featureName = rs.getString("FeatureName");
//			String operateContent = rs.getString("OperateContent");
//			Date operateTime = rs.getTimestamp("OperateTime");
//			String iP = rs.getString("IP");
//			String remark = rs.getString("Remark");
//			
//			return new LogInfo(iD,userID,userName,realName,featureID,featureName,operateContent,operateTime,iP,remark);
//		}
//	}
	
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
	public boolean add(LogInfo logInfo, ErrInfo pErrInfo) {
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
				String sql = "INSERT INTO LogInfo VALUES(:UserID,:UserName,:RealName,:FeatureName,:OperateContent,:OperateTime,:IP,:URI)";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("UserID", logInfo.getUserID());
				parameterSource.addValue("UserName", logInfo.getUserName());
				parameterSource.addValue("RealName", logInfo.getRealName());
				parameterSource.addValue("FeatureName", logInfo.getFeatureName());
				parameterSource.addValue("OperateContent", logInfo.getOperateContent());
				parameterSource.addValue("OperateTime", logInfo.getOperateTime());
				parameterSource.addValue("IP", logInfo.getIP());
				parameterSource.addValue("URI", logInfo.getURI());
				
				NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				parameterJdbcTemplate.update(sql, parameterSource);
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
