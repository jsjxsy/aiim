/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStoreroomStructureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * 库房结构信息表（DD_StoreroomStructure）的DAO实现类（SQL Server平台）
 *
 */
public class StoreroomStructureDaoImpl  extends JdbcDaoSupport implements IStoreroomStructureDao {
	
	
	
		private class StoreroomStructureMapper implements RowMapper<StoreroomStructure>
		{
			
			@Override
			public StoreroomStructure mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				int iD = rs.getInt("ID");
				String name = rs.getString("Name");
				int parentID = rs.getInt("ParentID");
				String barcode = rs.getString("Barcode");
				String fullName = rs.getString("FullName");
				int totalSize = rs.getInt("TotalSize");
				int usedSize = rs.getInt("UsedSize");
				boolean roomFlag = rs.getBoolean("RoomFlag");
				boolean endFlag = rs.getBoolean("EndFlag");
				int roomID = rs.getInt("RoomID");//
				String remark = rs.getString("Remark");
				
				return new StoreroomStructure(iD,name,parentID,barcode,fullName,totalSize,usedSize,roomFlag,roomID,endFlag,remark);
			}
		}
	
		
		/**
		 * 构造函数
		 */
		public  StoreroomStructureDaoImpl() {

		}

		/**
		 * 带数据源的构造函数
		 * @param dataSource 数据源
		 */
		public  StoreroomStructureDaoImpl(DataSource dataSource) {
			setDataSource(dataSource);
		}
		
	
		
		//SQL
		// Table Name: DD_StoreroomStructure
		// Columns List,Can Used in SELECT SQL: ID,Name,ParentID,Barcode,FullName,TotalSize,UsedSize,RoomFlag,RoomID,EndFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:ParentID,:Barcode,:FullName,:TotalSize,:UsedSize,:RoomFlag,:RoomID,:EndFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,ParentID=:ParentID,Barcode=:Barcode,FullName=:FullName,TotalSize=:TotalSize,UsedSize=:UsedSize,RoomFlag=:RoomFlag,RoomID=:RoomID,EndFlag=:EndFlag,Remark=:Remark

		/**
		 * 插入库房结构信息的SQL语句
		 */
		private final String SQL_INSERT = "INSERT INTO DD_StoreroomStructure(Name,ParentID,Barcode,FullName,TotalSize,UsedSize,RoomFlag,EndFlag,Remark) VALUES(:Name,:ParentID,:Barcode,:FullName,:TotalSize,:UsedSize,:RoomFlag,:EndFlag,:Remark)";
		
		/**
		 * 删除库房结构信息的SQL语句
		 */
		private final String SQL_DELETE = "DELETE FROM DD_StoreroomStructure WHERE ID=?";
		
		
		
		
		/**
		 * 更新库房结构信息的SQL语句
		 */
		private final String SQL_UPDATE = "UPDATE DD_StoreroomStructure SET Name=:Name,FullName=:FullName,TotalSize=:TotalSize,RoomFlag=:RoomFlag,EndFlag=:EndFlag,Remark=:Remark WHERE ID=:ID";
		
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
					pErrInfo.getContent()
							.append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
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
		
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#addStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addStoreroomStructure(StoreroomStructure storeroomStructure,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if(storeroomStructure==null){
				pFlag = false;
				pErrInfo.getContent().append("库房结构信息未初始化。");				
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;				
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Name", storeroomStructure.getName(),Types.VARCHAR);
				paramSource.addValue("ParentID", storeroomStructure.getParentID(),Types.INTEGER);
				paramSource.addValue("Barcode", storeroomStructure.getBarcode(),Types.VARCHAR);
				paramSource.addValue("FullName", storeroomStructure.getFullName(),Types.VARCHAR);	
				paramSource.addValue("TotalSize", storeroomStructure.getTotalSize(),Types.INTEGER);
				paramSource.addValue("UsedSize", storeroomStructure.getUsedSize(),Types.INTEGER);
				paramSource.addValue("RoomFlag", storeroomStructure.getRoomFlag(),Types.BOOLEAN);
				paramSource.addValue("EndFlag", storeroomStructure.getEndFlag(),Types.BOOLEAN);
				paramSource.addValue("Remark", storeroomStructure.getRemark(),Types.VARCHAR);				
				
				pErrPos = 3;
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
	 * 根据设备条形码查询设备信息的SQL语句
	 */
	private final String SQL_SELECT_findByBarcode = " SELECT * FROM DD_StoreroomStructure WHERE Barcode = ?";

	@Override
	public boolean findByBarcode(StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {
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
				List<StoreroomStructure> pEntitys=jdbcTemplate.query(SQL_SELECT_findByBarcode,new StoreroomStructureMapper(),storeroomStructure.getBarcode());

				//返回查询结果
				if (pEntitys.size() > 0) {
					storeroomStructure.cloneFrom(pEntitys.get(0));
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
	 * 根据库房结构ID查询库房结构信息的SQL语句
	 */
	private final String SQL_SELECT_findStoreroomStructureByID = "SELECT * FROM DD_StoreroomStructure WHERE ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#findStoreroomStructureByID(int, com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检查库房设备编号为非0
			if(storeroomStructure==null){
				pFlag = false;
				pErrInfo.getContent().append("库房结构信息未初始化。");
			}
			else{
				if(storeroomStructure.getID()==0){
					pFlag = false;
					pErrInfo.getContent().append("库房设备编号非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<StoreroomStructure> storeroomStructures = jdbcTemplate.query(SQL_SELECT_findStoreroomStructureByID,new StoreroomStructureMapper(),storeroomStructure.getID());

				//返回查询结果
				if (storeroomStructures.size() > 0) {
					storeroomStructure.cloneFrom(storeroomStructures.get(0));
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
	 * 查询指定编号下级库房结构信息集合的SQL语句
	 */
	private final String SQL_SELECT_findChildrenByID = "SELECT * FROM DD_StoreroomStructure WHERE ParentID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#findStoreroomStructureChildrenByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findChildrenByID(int storeroomStructureID,
			List<StoreroomStructure> children, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<StoreroomStructure> storeroomStructures=jdbcTemplate.query(SQL_SELECT_findChildrenByID,new StoreroomStructureMapper(),storeroomStructureID);

				//返回查询结果
				if (storeroomStructures.size() > 0) {
					children.addAll(storeroomStructures);
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
	 * 查询所有库房信息的SQL语句
	 */
	private final String SQL_SELECT_findStorerooms = " SELECT * FROM DD_StoreroomStructure WHERE RoomFlag = 1";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#findStorerooms(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStorerooms(List<StoreroomStructure> storeRooms,
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
				List<StoreroomStructure> storeroomStructures=jdbcTemplate.query(SQL_SELECT_findStorerooms,new StoreroomStructureMapper());

				//返回查询结果
				if (storeroomStructures.size() > 0) {
					storeRooms.addAll(storeroomStructures);
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
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#updateStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStoreroomStructure(
			StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if(storeroomStructure ==null){
				pFlag = false;
				pErrInfo.getContent().append("库房结构信息未初始化。");
			}else{
				if(storeroomStructure.getID()==0){
					pFlag = false;
					pErrInfo.getContent().append("库房结构ID非法为空。");
				}
			}

			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				//JdbcTemplate jdbcTemplate = getJdbcTemplate();
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);
			//	"UPDATE DD_StoreroomStructure SET Name=:Name,FullName=:FullName,TotalSize=:TotalSize,RoomFlag=:RoomFlag,EndFlag=:EndFlag,Remark=:Remark WHERE ID=:ID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Name", storeroomStructure.getName(),Types.VARCHAR);
				paramSource.addValue("FullName", storeroomStructure.getFullName(),Types.VARCHAR);
				paramSource.addValue("TotalSize", storeroomStructure.getTotalSize(),Types.INTEGER);
				paramSource.addValue("RoomFlag", storeroomStructure.getRoomFlag(),Types.BOOLEAN);
				paramSource.addValue("EndFlag", storeroomStructure.getEndFlag(),Types.BOOLEAN);
				paramSource.addValue("Remark", storeroomStructure.getRemark(),Types.VARCHAR);
				paramSource.addValue("ID", storeroomStructure.getID(),Types.INTEGER);
				
				pErrPos = 3;
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
	 * 通过条形码更新最底层设备的已用空间的SQL语句
	 */
	private final String SQL_UPDATE_usedSize_ByBarcode = " UPDATE DD_StoreroomStructure "+
		"  SET UsedSize=(SELECT COUNT(*) FROM StoreAddressInfo A,DD_StoreroomStructure B WHERE B.Barcode='%1$s' AND a.StoreAddressID=B.ID) "+
		"  WHERE Barcode='%1$s'";
	@Override
	public boolean updateUsedSizeByBarcode(String storeAddressBarcode, ErrInfo pErrInfo) {
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
				String sql = String.format(SQL_UPDATE_usedSize_ByBarcode, storeAddressBarcode);
System.out.println("通过条形码更新最底层设备的已用空间的SQL语句:"+sql);
				jdbcTemplate.update(sql);

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
	 * 通过设备编号更新已用空间的SQL语句
	 */
	private final String SQL_UPDATE_usedSize_ByID = "UPDATE DD_StoreroomStructure "+
			" SET UsedSize =( "+
			" SELECT SUM(UsedSize) FROM DD_StoreroomStructure WHERE ParentID= %1$s "+
			" ) WHERE ID=%1$s";
	@Override
	public boolean updateUsedSizeByID(int storeAddressID, ErrInfo pErrInfo) {
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
				String sql = String.format(SQL_UPDATE_usedSize_ByID,storeAddressID);
				System.out.println("通过条形码更新最底层设备的已用空间的SQL语句:"+sql);
				jdbcTemplate.update(sql);

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
