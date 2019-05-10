/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumStoreStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;

/**
 * 库房档案信息表 的DAO的实现类
 * @author Administrator
 *
 */
public class StoreroomArchivesInfoDaoImpl extends JdbcDaoSupport implements IStoreroomArchivesInfoDao {

	/**
	 * 构造函数
	 */
	public StoreroomArchivesInfoDaoImpl() {

	}

	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StoreroomArchivesInfoMapper implements RowMapper<StoreroomArchivesInfo>
	{		
		@Override
		public StoreroomArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			EnumStoreStatus storeStatus = EnumStoreStatus.getEnumElement(rs.getInt("StoreStatus"));
			
			return new StoreroomArchivesInfo(archivesTypeID,nBXH,archivesID,title,archivesBarcode,archivesBoxBarcode,storeStatus);
		}
	}

	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class StoreroomArchivesInfoMapperExtendStoreAddressInfo implements RowMapper<StoreroomArchivesInfo>
	{		
		@Override
		public StoreroomArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			EnumStoreStatus storeStatus = EnumStoreStatus.getEnumElement(rs.getInt("StoreStatus"));
			String storeAddressFullName = rs.getString("StoreAddressFullName");
			StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo(archivesTypeID,nBXH,archivesID,title,archivesBarcode,archivesBoxBarcode,storeStatus);
			storeroomArchivesInfo.setTag(storeAddressFullName);	
			System.out.println(":"+storeroomArchivesInfo.getTag()+":");
			return storeroomArchivesInfo;
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
	 * 检查指定的档案分类信息下其档案归档工作表信息是否正确赋值了
	 * 
	 * @param archivesType
	 *            档案分类信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableName(ArchivesType archivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (archivesType.getArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表非法为空");
			}
			else
			{
				if (archivesType.getArchivesInfoTables().containsKey(EnumArchivesInfoTableType.档案归档工作表) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
				}
				else
				{
					String tableName = "";
					tableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("档案分类（" + archivesType.getFullCode() + "）的档案归档工作表信息非法为空。");
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao#delete(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	

	/**
	 * 根据条件查询库房档案信息的SQL语句
	 */
	//%1$s查询条件 格式：and aa='bb'
	private final String SQL_SELECT_findByCondition = "SELECT A.*,B.StoreAddressFullName  FROM StoreroomArchivesInfo A " +
						"	 LEFT JOIN StoreAddressInfo B ON  A.ArchivesBoxBarcode = B.ArchivesBoxBarcode WHERE 1=1 %1$s ";
	@Override
	public boolean findByCondition(String whereSQL, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String sql = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				sql = String.format(SQL_SELECT_findByCondition,whereSQL);
				List<StoreroomArchivesInfo> pEntitys=jdbcTemplate.query(sql,new StoreroomArchivesInfoMapperExtendStoreAddressInfo());

				//返回查询结果
				if (pEntitys.size() > 0) {
					storeroomArchivesInfos.addAll(pEntitys);	
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
System.out.println("sql--error:"+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}


	/**
	 * 根据档案条形码查询库房档案信息的SQL语句
	 */			
	private final String SQL_SELECT_findByBarcode = "SELECT * FROM StoreroomArchivesInfo WHERE ArchivesBarcode = ?";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao#findByNBXH(int, com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
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
				List<StoreroomArchivesInfo> storeroomArchivesInfos = jdbcTemplate.query(SQL_SELECT_findByBarcode,new StoreroomArchivesInfoMapper(),storeroomArchivesInfo.getArchivesBarcode());

				//返回查询结果
				if (storeroomArchivesInfos.size() > 0) {
					storeroomArchivesInfo.cloneFrom(storeroomArchivesInfos.get(0));
					System.out.println("title:"+storeroomArchivesInfo.getTitle());
					System.out.println("ArchivesBarcode:"+storeroomArchivesInfo.getArchivesBarcode());
				}else{
					storeroomArchivesInfo.setNBXH(0);//没有找到该条形码所对应的档案时，将库房档案信息对象的NBXH赋0；
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
	 * 根据NBXH查询库房档案信息的SQL语句
	 */			
	private final String SQL_SELECT_findByNBXH = "SELECT * FROM StoreroomArchivesInfo WHERE ArchivesTypeID=? AND NBXH = ?";

	@Override
	public boolean findByNBXH(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
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
				List<StoreroomArchivesInfo> storeroomArchivesInfos = jdbcTemplate.query(SQL_SELECT_findByNBXH,new StoreroomArchivesInfoMapper(),storeroomArchivesInfo.getArchivesTypeID(),storeroomArchivesInfo.getNBXH());

				//返回查询结果
				if (storeroomArchivesInfos.size() > 0) {
					storeroomArchivesInfo.cloneFrom(storeroomArchivesInfos.get(0));
				}else{
					pFlag = false;
					pErrInfo.getContent().append("分类编号为:"+storeroomArchivesInfo.getArchivesTypeID()+" NBXH："+storeroomArchivesInfo.getNBXH()+"的库房档案信息未找到！");
					//storeroomArchivesInfo.setNBXH(0);//没有找到该条形码所对应的档案时，将库房档案信息对象的NBXH赋0；
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


	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao#save(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao#update(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	// Table Name: 
	// Columns List,Can Used in SELECT SQL: ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBarcode,ArchivesBoxBarcode,StoreStatus
	// Columns List,Can Used in INSERT SQL: :ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBarcode,:ArchivesBoxBarcode,:StoreStatus
	// Columns List,Can Used in UPDATE SQL: ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBarcode=:ArchivesBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreStatus=:StoreStatus

	/**
	 * 更新指定NBXH档案的馆藏状态的SQL语句
	 */
	private final String SQL_UPDATE_updateStoreStatus = "UPDATE StoreroomArchivesInfo SET StoreStatus= ?  WHERE ArchivesBarcode= ? ";
	
	@Override
	public boolean updateStoreStatusByArchivesBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//数据验证
			if(storeroomArchivesInfo==null){
				pFlag = false;
				pErrInfo.getContent().append("库房档案信息未初始化。");
			}else{
				if ("".equals(storeroomArchivesInfo.getArchivesBarcode())) {
					pFlag = false;
					pErrInfo.getContent().append("档案条形码未赋值。");
				}
				
				if(storeroomArchivesInfo.getStoreStatus() == EnumStoreStatus.NONE){
					pFlag = false;
					pErrInfo.getContent().append("档案馆藏状态未赋值。");
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(SQL_UPDATE_updateStoreStatus , storeroomArchivesInfo.getStoreStatus().getEnumValue(),storeroomArchivesInfo.getArchivesBarcode());
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
	public boolean add(String barcode, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoSavedTableName = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag){
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false){
					pFlag = false;
				}else{
					// 保存当前分类的档案归档工作表
					archivesInfoSavedTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档信息表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				String sql = "INSERT INTO StoreroomArchivesInfo SELECT ArchivesTypeID,NBXH,ParentFlag,ArchivesID,Title,Barcode,'NULL',1 FROM %1$s WHERE Barcode=?";
				sql = String.format(sql,archivesInfoSavedTableName);
				getJdbcTemplate().update(sql,barcode);
				/*getJdbcTemplate().batchUpdate(sql,new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setString(1, barcodes.get(i));
					}
					@Override
					public int getBatchSize() {
						return barcodes.size();
					}
				});*/
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
	public boolean findByBoxBarcode(String archivesBoxBarcode, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM StoreroomArchivesInfo WHERE ArchivesBoxBarcode=? ORDER BY ArchivesID";
				List<StoreroomArchivesInfo> pStoreroomArchivesInfos = getJdbcTemplate().query(sql, new Object[]{archivesBoxBarcode},new StoreroomArchivesInfoMapper());
				if (pStoreroomArchivesInfos.size() > 0) {
					storeroomArchivesInfos.addAll(pStoreroomArchivesInfos);
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
	public boolean updateArchivesBoxBarcode(List<String> archivesBarcodes, String archivesBoxBarcode, ErrInfo pErrInfo) {
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
				String sql = "UPDATE StoreroomArchivesInfo SET ArchivesBoxBarcode=:ArchivesBoxBarcode WHERE ArchivesBarcode IN (:ArchivesBarcodes)";
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesBoxBarcode", archivesBoxBarcode);
				parameterSource.addValue("ArchivesBarcodes", archivesBarcodes);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				namedParameterJdbcTemplate.update(sql, parameterSource);
				
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
	public boolean find(int NBXH, int archivesTypeID, StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM StoreroomArchivesInfo WHERE NBXH=:NBXH AND ArchivesTypeID=:ArchivesTypeID";
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("NBXH", NBXH);
				parameterSource.addValue("ArchivesTypeID", archivesTypeID);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				List<StoreroomArchivesInfo> storeroomArchivesInfos = namedParameterJdbcTemplate.query(sql, parameterSource, new StoreroomArchivesInfoMapper());
				if (storeroomArchivesInfos.size() >0) {
					storeroomArchivesInfo.cloneFrom(storeroomArchivesInfos.get(0));
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
	public boolean updateBarCode(String barcode, int nbxh, ArchivesType archivesType, ErrInfo pErrInfo) {
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
				String sql = "UPDATE StoreroomArchivesInfo SET ArchivesBarcode=:ArchivesBarcode WHERE ArchivesTypeID=:ArchivesTypeID AND NBXH=:NBXH";
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesBarcode", barcode);
				parameterSource.addValue("ArchivesTypeID", archivesType.getID());
				parameterSource.addValue("NBXH", nbxh);
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				namedParameterJdbcTemplate.update(sql, parameterSource);
				
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
