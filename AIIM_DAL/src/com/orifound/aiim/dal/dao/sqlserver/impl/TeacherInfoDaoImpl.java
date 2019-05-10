package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ITeacherInfoDao;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ImportDataitemsMapping;
import com.orifound.aiim.entity.TeacherInfo;

public class TeacherInfoDaoImpl extends JdbcDaoSupport implements ITeacherInfoDao {
	
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	private class TeacherInfoMapper implements RowMapper<TeacherInfo>
	{
		@Override
		public TeacherInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int nBXH = rs.getInt("NBXH");
			Date saveDate = rs.getTimestamp("SaveDate");
			int workFlowStatus = rs.getInt("WorkFlowStatus");
			Date moveOutDate = rs.getTimestamp("MoveOutDate");
			String barcode = rs.getString("Barcode");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String xm = rs.getString("xm");
			String gzh = rs.getString("gzh");
			String xb = rs.getString("xb");
			String csrq = rs.getString("csrq");
			String jg = rs.getString("jg");
			String mz = rs.getString("mz");
			String sfzhm = rs.getString("sfzhm");
			String dp = rs.getString("dp");
			String lxqdw = rs.getString("lxqdw");
			String cjgzsj = rs.getString("cjgzsj");
			String lxgzsj = rs.getString("lxgzsj");
			String jdsj = rs.getString("jdsj");
			String ssxy = rs.getString("ssxy");
			String ssyx = rs.getString("ssyx");
			String zcxl = rs.getString("zcxl");
			String zc = rs.getString("zc");
			String pzcsj = rs.getString("pzcsj");
			String zwlx = rs.getString("zwlx");
			String zw = rs.getString("zw");
			String tzsj = rs.getString("tzsj");
			String whcd = rs.getString("whcd");
			String xw = rs.getString("xw");
			String bysj = rs.getString("bysj");
			String byyx = rs.getString("byyx");
			String dalx = rs.getString("dalx");
			String cllx = rs.getString("cllx");
			String clsj = rs.getString("clsj");
			String bz = rs.getString("bz");
			long oldXH = rs.getLong("oldXH");
			
			return new TeacherInfo(nBXH,saveDate,workFlowStatus,moveOutDate,barcode,archivesTypeID,archivesID,title,xm,gzh,xb,csrq,jg,mz,sfzhm,dp,lxqdw,cjgzsj,lxgzsj,jdsj,ssxy,ssyx,zcxl,zc,pzcsj,zwlx,zw,tzsj,whcd,xw,bysj,byyx,dalx,cllx,clsj,bz,oldXH);
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
	public boolean add(List<TeacherInfo> teacherInfos, ErrInfo pErrInfo) {
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
				
				String sql = "INSERT INTO TeacherInfo (WorkFlowStatus %1$s) VALUES (11 %2$s)";
				
				String SELECT_SQL = "SELECT * FROM TeacherInfo WHERE gzh=?";
				String fieldNames = "";
				String fieldValues = "";
				for (TeacherInfo teacherInfo : teacherInfos) {
					fieldNames = ",ArchivesTypeID,ArchivesID,Title,Barcode";
					fieldValues = "," + teacherInfo.getArchivesTypeID() + ",'" + teacherInfo.getArchivesID() + "','" + teacherInfo.getTitle() + "','" + teacherInfo.getBarcode()+ "'";
					Map<String, ImportDataitemsMapping> importDataitemsMappings = teacherInfo.getImportDataitemsMappings();
					for (Iterator<String> iterator = importDataitemsMappings.keySet().iterator(); iterator.hasNext();) {
						String key =  iterator.next();
						ImportDataitemsMapping importDataitemsMapping = importDataitemsMappings.get(key);
						fieldNames += "," + importDataitemsMapping.getDesTableFiedName();
						fieldValues += "," + "'" + importDataitemsMapping.getValue() + "'";
					}
					String localSql = String.format(sql, fieldNames, fieldValues);
					List<TeacherInfo> teacherInfos2 = getJdbcTemplate().query(SELECT_SQL, new Object[]{teacherInfo.getArchivesID()}, new TeacherInfoMapper());
					
					//如果没找到
					if(teacherInfos2.size() == 0){
						getJdbcTemplate().update(localSql);
						//getJdbcTemplate().update("INSERT INTO StudentDocsInfo (NBXH,DocID,ExistsFlag) SELECT T2.NBXH,T1.ID,0 FROM DD_StudentDoc T1 INNER JOIN StudentInfo T2 ON T2.xh='"+ teacherInfo.getArchivesID() +"'");
					}
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
	public boolean add(TeacherInfo teacherInfo, ErrInfo pErrInfo) {
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
				String sql = "INSERT INTO TeacherInfo (SaveDate,WorkFlowStatus,Barcode,ArchivesTypeID,ArchivesID,Title,xm," +
						"gzh,xb,csrq,jg,mz,sfzhm,dp,lxqdw,cjgzsj,lxgzsj,jdsj,ssxy,ssyx," +
						"zcxl,zc,pzcsj,zwlx,zw,tzsj,whcd,xw,bysj,byyx,dalx,cllx,clsj) VALUES (:SaveDate,:WorkFlowStatus,:Barcode,:ArchivesTypeID,:ArchivesID,:Title,:xm," +
						":gzh,:xb,:csrq,:jg,:mz,:sfzhm,:dp,:lxqdw,:cjgzsj,:lxgzsj,:jdsj,:ssxy,:ssyx," +
						":zcxl,:zc,:pzcsj,:zwlx,:zw,:tzsj,:whcd,:xw,:bysj,:byyx,:dalx,:cllx,:clsj)";
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("SaveDate", teacherInfo.getSaveDate());
				parameterSource.addValue("WorkFlowStatus", 11);
		       // parameterSource.addValue("MoveOutDate", teacherInfo.getMoveOutDate());
		        parameterSource.addValue("Barcode", teacherInfo.getGzh());
		        parameterSource.addValue("ArchivesTypeID", teacherInfo.getArchivesTypeID());
		        parameterSource.addValue("ArchivesID", teacherInfo.getGzh());
		        parameterSource.addValue("Title", teacherInfo.getXm());
		        parameterSource.addValue("xm", teacherInfo.getXm());
		        
		        parameterSource.addValue("gzh", teacherInfo.getGzh());
		        parameterSource.addValue("xb", teacherInfo.getXb());
		        parameterSource.addValue("csrq", teacherInfo.getCsrq());
		        parameterSource.addValue("jg", teacherInfo.getJg());
		        parameterSource.addValue("mz", teacherInfo.getMz());
		        parameterSource.addValue("sfzhm", teacherInfo.getSfzhm());
		        parameterSource.addValue("dp", teacherInfo.getDp());
		        parameterSource.addValue("lxqdw", teacherInfo.getLxqdw());
		        parameterSource.addValue("cjgzsj", teacherInfo.getCjgzsj());
		        parameterSource.addValue("lxgzsj", teacherInfo.getLxgzsj());
		        parameterSource.addValue("jdsj", teacherInfo.getJdsj());
		        parameterSource.addValue("ssxy", teacherInfo.getSsxy());
		        parameterSource.addValue("ssyx", teacherInfo.getSsyx());
		        
		        parameterSource.addValue("zcxl", teacherInfo.getZcxl());
		        parameterSource.addValue("zc", teacherInfo.getZc());
		        parameterSource.addValue("pzcsj", teacherInfo.getPzcsj());
		        parameterSource.addValue("zwlx", teacherInfo.getZwlx());
		        parameterSource.addValue("zw", teacherInfo.getZw());
		        parameterSource.addValue("tzsj", teacherInfo.getTzsj());
		        parameterSource.addValue("whcd", teacherInfo.getWhcd());
		        parameterSource.addValue("xw", teacherInfo.getXw());
		        parameterSource.addValue("bysj", teacherInfo.getBysj());
		        parameterSource.addValue("byyx", teacherInfo.getByyx());
		        parameterSource.addValue("dalx", teacherInfo.getDalx());
		        parameterSource.addValue("cllx", teacherInfo.getCllx());
		        parameterSource.addValue("clsj", teacherInfo.getClsj());
		        
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
	
	@Override
	public boolean findTeacherInfos(TeacherInfo teacherInfo,ArchivesType archivesType, DataPageInfo dataPageInfo,List<TeacherInfo> teacherInfos, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM(SELECT ROW_NUMBER() over(ORDER BY saveDate desc) AS rownum, *  FROM TeacherInfo WHERE ArchivesTypeID=:ArchivesTypeID %1$s) T1 WHERE T1.rownum>:startRow AND T1.rownum<=:endRow";
				String sql_count = "SELECT COUNT(*) FROM TeacherInfo WHERE ArchivesTypeID=:ArchivesTypeID %1$s";		
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesTypeID", archivesType.getID());
				
				String condition = "";
				if (teacherInfo.getGzh() != null && !"".equals(teacherInfo.getGzh()) ) {
					condition += " AND gzh=:gzh";
					parameterSource.addValue("gzh", teacherInfo.getGzh());
				}
				
				if (teacherInfo.getXm() != null && !"".equals(teacherInfo.getXm()) ) {
					condition += " AND xm=:xm";
					parameterSource.addValue("xm", teacherInfo.getXm());
				}
				
				sql = String.format(sql, condition);
				sql_count = String.format(sql_count, condition);

				dataPageInfo.setRowCount( new NamedParameterJdbcTemplate(getDataSource()).queryForInt(sql_count, parameterSource));
				
				parameterSource.addValue("startRow", (dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize());
				parameterSource.addValue("endRow", dataPageInfo.getCurrentPage()*dataPageInfo.getPageSize());
				List<TeacherInfo> pTeacherInfos = new NamedParameterJdbcTemplate(getDataSource()).query(sql, parameterSource, new TeacherInfoMapper());
				
				//如果没找到
				if(pTeacherInfos.size() > 0){
					teacherInfos.addAll(pTeacherInfos);
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


	
	/**
	 * 获取所有待归档的教职工信息的SQL语句
	 */
	private final String SQL_SELECT_For_Archiving_count = "SELECT COUNT(*) FROM TeacherInfo WHERE ArchivesTypeID=:ArchivesTypeID and WorkFlowStatus=12";
	private final String SQL_SELECT_For_Archiving = "SELECT * FROM(SELECT *, ROW_NUMBER() over(ORDER BY saveDate) AS rownum "+
			" FROM TeacherInfo WHERE ArchivesTypeID=:ArchivesTypeID and WorkFlowStatus=12 ) T1 WHERE T1.rownum between :startRow AND :endRow ";
	@Override
	public boolean findTeacherInfosForArchiving(ArchivesType archivesType, DataPageInfo dataPageInfo, List<TeacherInfo> teacherInfos, ErrInfo pErrInfo) {

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
				MapSqlParameterSource parameterSource  = new MapSqlParameterSource();
				parameterSource.addValue("ArchivesTypeID",archivesType.getID());				
				//获取总记录数
				int rowNum = namedParameterJdbcTemplate.queryForInt(SQL_SELECT_For_Archiving_count,parameterSource );
				dataPageInfo.setRowCount(rowNum);
				parameterSource.addValue("startRow",(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize()+1);
				System.out.println("dataPageInfo.getCurrentPage()4:"+dataPageInfo.getCurrentPage());
				parameterSource.addValue("endRow", dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());
				System.out.println("dataPageInfo.getCurrentPage()3:"+dataPageInfo.getCurrentPage());
				
				System.out.println("rowNum:"+rowNum);
				//获取当前页面数据
				List<TeacherInfo> pEntitys=namedParameterJdbcTemplate.query(SQL_SELECT_For_Archiving, parameterSource, new TeacherInfoMapper());
				
				
				//返回查询结果
				if (pEntitys.size() > 0) {
					teacherInfos.addAll(pEntitys);
				}

				//销毁局部变量
				namedParameterJdbcTemplate = null;
				parameterSource = null;
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
	 * 添加教职工信息到库房档案信息表中的SQL语句
	 */
	private final String SQL_INSERT_To_StoreroomArchivesInfo = "INSERT INTO StoreroomArchivesInfo "+
		" SELECT A.ArchivesTypeID,A.NBXH,A.ArchivesID,A.xm,A.Barcode,0 as BoxBarcode,1 as StoreStatus FROM TeacherInfo A where NBXH=? ";
	@Override
	public boolean addTeacherInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_INSERT_To_StoreroomArchivesInfo, nbxh);
				
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);

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
	 * 更新教职工档案信息的工作流状态的SQL语句
	 */
	private final String SQL_UPDATE_Status = "UPDATE TeacherInfo SET WorkFlowStatus=? WHERE NBXH=?";
	@Override
	public boolean updateTeacherInfoStatus(int nbxh, int workFlowStatus, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_UPDATE_Status,workFlowStatus,nbxh);
				
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
	public boolean findTeacherInfoByNBXH(TeacherInfo teacherInfo, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM TeacherInfo WHERE NBXH=?";
				List<TeacherInfo> teacherInfos = getJdbcTemplate().query(sql, new TeacherInfoMapper(), teacherInfo.getNBXH());
				
				if (teacherInfos.size() > 0) {
					teacherInfo.cloneFrom(teacherInfos.get(0));
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
	public boolean update(TeacherInfo teacherInfo, ErrInfo pErrInfo) {
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
				String sql = "UPDATE TeacherInfo SET MoveOutDate=:MoveOutDate,Barcode=:Barcode,ArchivesID=:ArchivesID,Title=:Title,xm=:xm,gzh=:gzh,xb=:xb,csrq=:csrq,jg=:jg,mz=:mz,sfzhm=:sfzhm,dp=:dp,lxqdw=:lxqdw,cjgzsj=:cjgzsj,lxgzsj=:lxgzsj,jdsj=:jdsj,ssxy=:ssxy,ssyx=:ssyx,zcxl=:zcxl,zc=:zc,pzcsj=:pzcsj,zwlx=:zwlx,zw=:zw,tzsj=:tzsj,whcd=:whcd,xw=:xw,bysj=:bysj,byyx=:byyx,dalx=:dalx,cllx=:cllx,clsj=:clsj WHERE NBXH=:NBXH";
				
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		        parameterSource.addValue("MoveOutDate", teacherInfo.getMoveOutDate());
		        parameterSource.addValue("Barcode", teacherInfo.getGzh());
		        parameterSource.addValue("ArchivesID", teacherInfo.getGzh());
		        parameterSource.addValue("Title", teacherInfo.getXm());
		        parameterSource.addValue("xm", teacherInfo.getXm());
		        parameterSource.addValue("gzh", teacherInfo.getGzh());
		        parameterSource.addValue("xb", teacherInfo.getXb());
		        parameterSource.addValue("csrq", teacherInfo.getCsrq());
		        parameterSource.addValue("jg", teacherInfo.getJg());
		        parameterSource.addValue("mz", teacherInfo.getMz());
		        parameterSource.addValue("sfzhm", teacherInfo.getSfzhm());
		        parameterSource.addValue("dp", teacherInfo.getDp());
		        parameterSource.addValue("lxqdw", teacherInfo.getLxqdw());
		        parameterSource.addValue("cjgzsj", teacherInfo.getCjgzsj());
		        parameterSource.addValue("lxgzsj", teacherInfo.getLxgzsj());
		        parameterSource.addValue("jdsj", teacherInfo.getJdsj());
		        parameterSource.addValue("ssxy", teacherInfo.getSsxy());
		        parameterSource.addValue("ssyx", teacherInfo.getSsyx());
		        parameterSource.addValue("zcxl", teacherInfo.getZcxl());
		        parameterSource.addValue("zc", teacherInfo.getZc());
		        parameterSource.addValue("pzcsj", teacherInfo.getPzcsj());
		        parameterSource.addValue("zwlx", teacherInfo.getZwlx());
		        parameterSource.addValue("zw", teacherInfo.getZw());
		        parameterSource.addValue("tzsj", teacherInfo.getTzsj());
		        parameterSource.addValue("whcd", teacherInfo.getWhcd());
		        parameterSource.addValue("xw", teacherInfo.getXw());
		        parameterSource.addValue("bysj", teacherInfo.getBysj());
		        parameterSource.addValue("byyx", teacherInfo.getByyx());
		        parameterSource.addValue("dalx", teacherInfo.getDalx());
		        parameterSource.addValue("cllx", teacherInfo.getCllx());
		        parameterSource.addValue("clsj", teacherInfo.getClsj());
		        parameterSource.addValue("NBXH", teacherInfo.getNBXH());
		        
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
