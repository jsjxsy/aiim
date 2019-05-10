package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IOfficialTemplateDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OfficialTemplateDaoImpl extends JdbcDaoSupport implements IOfficialTemplateDao{
	
	
	/**
	 * 构造函数
	 */
	public OfficialTemplateDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public OfficialTemplateDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	 /**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 *
	 */

	 private class OfficialTemplateMapper implements
	 RowMapper<OfficialTemplate>
	 {
				
	 @Override
	 public OfficialTemplate mapRow(ResultSet rs, int rowNum) throws
	 SQLException
	 {
	 int iD = rs.getInt("ID");
	 String title = rs.getString("Title");
	 int docType = rs.getInt("DocType");
	 String provider = rs.getString("Provider");
	 Date createDate = rs.getTimestamp("CreateDate");
	 String fileName = rs.getString("FileName");
	 String remark = rs.getString("Remark");
					
	 return new OfficialTemplate(iD,title,docType,provider,createDate,fileName,remark);
	 }
	 }
	 /**
	 * Feature的SQL语句
	 */
	private final String SQL_DELETE = "DELETE FROM OfficialTemplate  where ID = ? ";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_BY_NAME = "SELECT * FROM OfficialTemplate WHERE DocType = ? and Title LIKE ?";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM OfficialTemplate where DocType = ?";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_UPDATE = "UPDATE OfficialTemplate SET Title=:Title,DocType=:DocType,Provider=:Provider,createDate=:createDate,FileName=:FileName,Remark=:Remark WHERE ID=:ID";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO OfficialTemplate(Title,DocType,Provider,createDate,FileName,Remark) VALUES(:Title,:DocType,:Provider,:createDate,:FileName,:Remark);";
	
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_By_ID = "select * from OfficialTemplate where ID = ?";
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
	public boolean delete(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			JdbcTemplate jdbcTemplate = getJdbcTemplate();
			if (pFlag)
			{
				pErrPos = 1;
				if (officialTemplate.getID() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板号非法为空。");
				}else{
	
					jdbcTemplate.update(SQL_DELETE,officialTemplate.getID());
				}
			}
			jdbcTemplate = null;
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
	public boolean findAll(OfficialDocType officialDocType, List<OfficialTemplate> pOfficialTemplates, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
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
				List<OfficialTemplate> officialTemplates = jdbcTemplate.query(SQL_SELECT_ALL,new OfficialTemplateMapper(),officialDocType.getID());
				if (officialTemplates.size() >0) {
					pOfficialTemplates.addAll(officialTemplates);
				}
				//销毁局部变量
				officialTemplates = null;
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
	public boolean findByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
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
				List<OfficialTemplate> pOfficialTemplats=jdbcTemplate.query(SQL_SELECT_By_ID,new OfficialTemplateMapper(),pID);

				//返回查询结果
				if (pOfficialTemplats.size() > 0) {
					officialTemplate.cloneFrom(pOfficialTemplats.get(0)) ;
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

	@Override
	public boolean save(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
	
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos = 1;
				if (officialTemplate.getTitle() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板名称非法为空。");
				}else{
					if (officialTemplate.getTitle().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文模板名称非法为空。");
					}
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (officialTemplate.getDocType()== 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板文种非法为空。");
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos = 3;
				if (officialTemplate.getProvider()== null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板提供者非法为空。");
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos = 4;
				if (officialTemplate.getCreateDate() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板名称非法为空。");
				}
			}
			
			//检查全宗名称是否有赋值
			if (pFlag)
			{
				pErrPos = 5;
				if (officialTemplate.getFileName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板名称非法为空。");
				}else{
					if(officialTemplate.getFileName().trim().length()==0){
						pFlag = false;
						pErrInfo.getContent().append("公文模板名称非法为空。");
					}
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 6;
				//SQL_INSERT = "INSERT INTO OfficialTemplate(Title,DocType,Provider,createDate,FileName,Remark) VALUES(:Title,:DocType,:Provider,:createDate,:FileName,:Remark);";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Title", officialTemplate.getTitle(),Types.VARCHAR);
				paramSource.addValue("DocType", officialTemplate.getDocType(), Types.INTEGER);
				paramSource.addValue("Provider", officialTemplate.getProvider(), Types.VARCHAR);
				paramSource.addValue("createDate", officialTemplate.getCreateDate(), Types.DATE);
				paramSource.addValue("FileName", officialTemplate.getFileName(), Types.VARCHAR);
				paramSource.addValue("Remark", officialTemplate.getRemark(), Types.VARCHAR);
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
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

	@Override
	public boolean update(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			
			if (pFlag)
			{
				pErrPos = 1;
				if (officialTemplate.getID() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板号非法为空。");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 2;
				if (officialTemplate.getTitle() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板名称非法为空。");
				}else{
					if (officialTemplate.getTitle().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文模板名称非法为空。");
					}
				}
			}
			
			if (pFlag)
			{
				pErrPos = 3;
				if (officialTemplate.getDocType()== 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板文种非法为空。");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 4;
				if (officialTemplate.getProvider()== null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板提供者非法为空。");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 5;
				if (officialTemplate.getCreateDate() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板名称非法为空。");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 6;
				if (officialTemplate.getFileName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文模板名称非法为空。");
				}else{
					if(officialTemplate.getFileName().trim().length()==0){
						pFlag = false;
						pErrInfo.getContent().append("公文模板名称非法为空。");
					}
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//SQL_UPDATE = "UPDATE OfficialTemplate SET Title=:Title,DocType=:DocType,Provider=:Provider,createDate=:createDate,FileName=:FileName,Remark=:Remark WHERE ID=:ID";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Title", officialTemplate.getTitle(),Types.VARCHAR);
				paramSource.addValue("DocType", officialTemplate.getDocType(), Types.INTEGER);
				paramSource.addValue("Provider", officialTemplate.getProvider(), Types.VARBINARY);
				paramSource.addValue("createDate", officialTemplate.getDocType(), Types.DATE);
				paramSource.addValue("FileName", officialTemplate.getFileName(), Types.VARBINARY);
				paramSource.addValue("Remark", officialTemplate.getRemark(), Types.VARBINARY);
				paramSource.addValue("ID", officialTemplate.getID(), Types.INTEGER);
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				
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

	@Override
	public boolean findByName(OfficialDocType officialDocType,String title, List<OfficialTemplate> pOfficialTemplates, ErrInfo pErrInfo) {
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
				pErrPos = 6;
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				List<OfficialTemplate> officialTemplates=jdbcTemplate.query(SQL_SELECT_BY_NAME, new OfficialTemplateMapper(), new Object[]{officialDocType.getID(),"%"+title+"%"});
				pOfficialTemplates.addAll(officialTemplates);
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
