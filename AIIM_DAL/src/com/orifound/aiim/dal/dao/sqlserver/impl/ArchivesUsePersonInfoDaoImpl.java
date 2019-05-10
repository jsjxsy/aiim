/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案利用人信息DAO实现类
 * @author Administrator
 *
 */
public class ArchivesUsePersonInfoDaoImpl extends JdbcDaoSupport implements IArchivesUsePersonInfoDao {
	
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class ArchivesUsePersonInfoMapper implements RowMapper<ArchivesUsePersonInfo>
	{
		
		@Override
		public ArchivesUsePersonInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String iDCardNo = rs.getString("IDCardNo");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String department = rs.getString("Department");
			String tel = rs.getString("Tel");
			String email = rs.getString("Email");
			int areaID = rs.getInt("AreaFlag");
			
			return new ArchivesUsePersonInfo(iD,name,iDCardNo,iDCardTypeID,department,tel,email,areaID);
		}
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesUsePersonInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * @param dataSource 数据源
	 */
	public ArchivesUsePersonInfoDaoImpl(DataSource dataSource) {
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
	 * 添加档案利用人信息的SQL语句
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUsePersonInfo(Name,IDCardNo,IDCardTypeID,Department,Tel,Email,AreaFlag) VALUES(:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaFlag)";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#add(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean add(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
			
				//返回查询结果
//VALUES(:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaID)";
				//执行SQL语句
				if (pFlag) {
					pErrPos = 2;				
					NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
					//绑定参数
					MapSqlParameterSource paramSource=new MapSqlParameterSource();
					paramSource.addValue("Name", archivesUsePersonInfo.getName());
					paramSource.addValue("IDCardNo", archivesUsePersonInfo.getIDCardNo());	
					paramSource.addValue("IDCardTypeID", archivesUsePersonInfo.getIDCardTypeID());
					paramSource.addValue("Department", archivesUsePersonInfo.getDepartment());
					paramSource.addValue("Tel", archivesUsePersonInfo.getTel());
					paramSource.addValue("Email", archivesUsePersonInfo.getEmail());
					paramSource.addValue("AreaFlag", archivesUsePersonInfo.getAreaID());
					
					pErrPos = 3;	
					//执行SQL
					GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
					namedParameterJdbcTemplate.update(SQL_INSERT, paramSource, keyHolder);
					//将执行SQL返回的主键返回
					archivesUsePersonInfo.setID(keyHolder.getKey().intValue());

					//销毁局部变量
					paramSource=null;
					namedParameterJdbcTemplate=null;
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
				System.out.println(pErrInfo.toString());
			}
			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#delete(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<ArchivesUsePersonInfo> archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	// Table Name: ArchivesUsePersonInfo
	// Columns List,Can Used in SELECT SQL: ID,Name,IDCardNo,IDCardTypeID,Department,Tel,Email,AreaID
	// Columns List,Can Used in INSERT SQL: :ID,:Name,:IDCardNo,:IDCardTypeID,:Department,:Tel,:Email,:AreaID
	// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,IDCardNo=:IDCardNo,IDCardTypeID=:IDCardTypeID,Department=:Department,Tel=:Tel,Email=:Email,AreaID=:AreaID

	
	/**
	 * 根据唯一编号查询利用人信息的SQL语句
	 */
	private final String SQL_SELECT_findByID = "SELECT * FROM ArchivesUsePersonInfo WHERE ID= ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findByID(int, com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
				
			// 检查条码信息是否初始化	
		

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
			
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesUsePersonInfo> archivesUsePersonInfos=jdbcTemplate.query(SQL_SELECT_findByID,new ArchivesUsePersonInfoMapper(),archivesUsePersonInfo.getID());
				
				//返回查询结果
				if (archivesUsePersonInfos.size() > 0) {
					archivesUsePersonInfo.cloneFrom(archivesUsePersonInfos.get(0));					
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

	/**
	 * 根据利用人姓名查询用户信息的SQL语句
	 */
	private final String SQL_SELECT_findByIDCardNo = "SELECT * FROM ArchivesUsePersonInfo WHERE IDCardNo = ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findByName(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByIDCardNo(String IDCardNo, List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo) {
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
				List<ArchivesUsePersonInfo> infos = jdbcTemplate.query(SQL_SELECT_findByIDCardNo,new ArchivesUsePersonInfoMapper(),IDCardNo);
				
				//返回查询结果
				if (infos.size() > 0) {
					System.out.println("infos.size(): "+ infos.size());
					archivesUsePersonInfos.addAll(infos);					
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
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#update(com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据利用人姓名查询用户信息的SQL语句
	 */
	private final String SQL_SELECT_findByName = "SELECT * FROM ArchivesUsePersonInfo WHERE Name like ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUsePersonInfoDao#findByName(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByName(String name, List<ArchivesUsePersonInfo> archivesUsePersonInfos, ErrInfo pErrInfo) {
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
				List<ArchivesUsePersonInfo> infos = jdbcTemplate.query(SQL_SELECT_findByName,new ArchivesUsePersonInfoMapper(),"%"+name+"%");
				
				//返回查询结果
				if (infos.size() > 0) {
					archivesUsePersonInfos.addAll(infos);					
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
