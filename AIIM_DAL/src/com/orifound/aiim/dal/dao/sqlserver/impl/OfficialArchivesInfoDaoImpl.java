package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IOfficialArchivesInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * 
 *公文档案信息DAO实现类
 * 
 */
public class OfficialArchivesInfoDaoImpl extends JdbcDaoSupport implements
		IOfficialArchivesInfoDao {
	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */

	@SuppressWarnings("unused")
	private class OfficialArchivesInfoMapper implements
			RowMapper<OfficialArchivesInfo> {
		
		
		public OfficialArchivesInfoMapper() {
		}

		
		public OfficialArchivesInfoMapper(
				OfficialArchivesType offcialArchivesType) {
			this.offcialArchivesType = offcialArchivesType;
		}


		private OfficialArchivesType offcialArchivesType=null;

		public OfficialArchivesType getOffcialArchivesType() {
			return offcialArchivesType;
		}

		public void setOffcialArchivesType(OfficialArchivesType offcialArchivesType) {
			this.offcialArchivesType = offcialArchivesType;
		}

		@Override
		public OfficialArchivesInfo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			String value="";
			OfficialArchivesInfo officialArchivesInfo=new OfficialArchivesInfo(offcialArchivesType);
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues().values())
			{
				if (item.getColumnDataType() == EnumColumnDataType.字符串)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.实数)
				{
					float tempValue = rs.getFloat(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.布尔值)
				{
					boolean tempValue = rs.getBoolean(item.getColumnName());
					value = tempValue ? "1" : "0";
				}
				else if (item.getColumnDataType() == EnumColumnDataType.整数)
				{
					int tempValue = rs.getInt(item.getColumnName());
					value = String.valueOf(tempValue);
				}
				else if (item.getColumnDataType() == EnumColumnDataType.文本)
				{
					value = rs.getString(item.getColumnName());
				}
				else if (item.getColumnDataType() == EnumColumnDataType.日期时间)
				{
					Date tempValue = rs.getDate(item.getColumnName());
					SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					value = dateFormater.format(tempValue);
				}

				item.setValue(value);
			}

			return officialArchivesInfo;
		}
	}
	
	
	
	/**
	 * 构造函数
	 */
	public OfficialArchivesInfoDaoImpl() {

	}

	/**
	 * 带数据源的构造函数
	 * 
	 * @param dataSource
	 *            数据源
	 */
	public OfficialArchivesInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}

	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
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

	/**
	 * 插入公文档案登记信息记录的SQL语句
	 */
	private final String SQL_INSERT_OfficialArchivesInfo = "INSERT INTO %1$s (%2$s) VALUES(%3$s)";
     
	/**
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?;";
	
	/**
	 * 删除指定内部序号的档案信息记录的SQL
	 */
	private final String SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=?";
	

	/**
	 * 更新指定档案信息的SQL语句<br>
	 * 更新档案信息时仅能够修改档案的元数据属性字段值，对于档案工作流状态以及著录人员等信息不作修改
	 */
	private final String SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?";
	
	
	/**
	 * 
	 */
	private final String SQL_SELECT_COUNT_By_QueryConditions = "SELECT COUNT(NBXH) FROM %1$s"
		+ " WHERE 1 = 1 %2$s";
	

	/**
	 * 
	 */
	private final String SQL_DELETE_ParentAndChild_ByNBXH="DELETE FROM %1$s WHERE NBXH=:NBXH";
	
	/**
	 * 分页查询指定工作流状态下满足指定条件的文件级或案卷级档案信息的SQL语句（卷内文件记录要过滤掉）<br>
	 * %1$s：页大小<br>
	 * %2$s：已经读取过的记录数量<br>
	 * %3$s：具体档案分类对应的归档工作表名<br>
	 * %4$s：指定查询条件的SQL片段<br>
	 * 
	 */
	private final String SQL_SELECT_By_QueryConditions_WithPage = "SELECT TOP %1$s * FROM %3$s"
			+ " WHERE 1 = 1  %4$s AND NBXH < "
			+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %2$s NBXH FROM %3$s"
			+ " WHERE 1 = 1 %4$s ORDER BY NBXH DESC) AS T) "
			+ "ORDER BY NBXH DESC";

	/**
	 * 查询指定工作流状态下满足指定条件的文件级或案卷级档案信息的SQL语句（卷内文件记录要过滤掉），不分页<br>
	 * %1$s：具体档案分类对应的归档工作表名<br>
	 * %2$s：指定查询条件的SQL片段<br>
	 */
	private final String SQL_SELECT_By_QueryConditions_WithoutPage = "SELECT * FROM %1$s"
			+ " WHERE OfficialArchivesTypeID=:OfficialArchivesTypeID %2$s ORDER BY NBXH DESC";

	/**打印查询语句
	 * Feature的SQL语句
	 */
	private final String SQL_SELECT_ALL= "SELECT * FROM %1$s";
	
	@Override
	public boolean delete(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		
			boolean pFlag = true;
			int pErrPos = 0;
			Throwable throwable = new Throwable();

			// 具体档案分类对应的公文档案信息工作表名
			String officialArchivesInfoTableName = "";
			String localSql = "";// 最终提交的SQL语句

			try
			{
				// 检查JDBC数据源是否正确依赖注入
				if (CheckDataSourceInjection(pErrInfo) == false)
				{
					pFlag = false;
				}

				// 检查档案类型是否有赋值
				if (pFlag)
				{
					pErrPos = 1;
					if (officialArchivesType == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类信息非法为空。");
					}
				}
				
				// 检查公文档案信息对象是否赋值
				if (pFlag)
				{
					pErrPos = 2;
					if (officialArchivesInfo == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案信息对象非法为空");
					}
				}

				// 检查内部序号是否有值
				if (pFlag)
				{
					pErrPos = 3;
					if (officialArchivesInfo.getNBXH() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案信息对象属性（NBXH）没有赋值");
					}
				}

				// 检查档案分类对应的工作表名称是否有值
				if (pFlag)
				{
					pErrPos = 4;
					if (checkTableName(officialArchivesType, pErrInfo) == false)
					{
						pFlag = false;
					}
					else
					{
						// 保存当前分类的公文档案登记表
						officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
					}
				}

				// 执行SQL语句
				if (pFlag)
				{
					pErrPos = 5;

					// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
					// ?的动态构建，必须自行动态构建好SQL语句字符串
					localSql = String.format(SQL_DELETE_ByNBXH, officialArchivesInfoTableName);

					JdbcTemplate jdbcTemplate = getJdbcTemplate();
					jdbcTemplate.update(localSql, officialArchivesInfo.getNBXH());

					// 销毁局部变量
					jdbcTemplate = null;
				}
			}
			catch (BadSqlGrammarException e)
			{
				// SQL语句语法错误
				pFlag = false;
				pErrInfo.getContent().append(e.toString());
				pErrInfo.setException(e);
				pErrInfo.setBadSQL(e.getSql());
			}
			catch (Exception e)
			{
				// 其他异常错误
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

	/**
	 * 构造档案信息著录查询条件的SQL片段
	 * 
	 * @param archivesInfoQueryConditions
	 *            档案著录查询条件集合
	 * @param querySQL
	 *            返回构造好的查询条件SQL片段
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private static boolean getSqlForOfficialArchivesInfoInputQueryConditions(List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (OfficialArchivesInfoQueryCondition item : officialArchivesInfoQueryConditions)
			{
				// 如果是范围查询，则构造最小值和最大值条件
				if (item.getDataItem().getRangeQueryFlag())
				{
					// 无需单引号的情况
					if (item.getDataItem().getColumnDataType() == EnumColumnDataType.实数 || item.getDataItem().getColumnDataType() == EnumColumnDataType.整数
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.布尔值)
					{
						// 拼接逻辑运行符 AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// 拼接字段名
						querySQL.append(item.getDataItem().getColumnName());
						// 拼接条件范围
						querySQL.append(" BETWEEN ");
						querySQL.append(item.getMinValue());
						querySQL.append(" AND ");
						querySQL.append(item.getMaxValue());
					}
					// 需要单引号的情况
					else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间 || item.getDataItem().getColumnDataType() == EnumColumnDataType.字符串
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.文本)
					{
						// 拼接逻辑运行符 AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// 拼接字段名
						querySQL.append(item.getDataItem().getColumnName());
						// 拼接条件范围
						querySQL.append(" BETWEEN ");
						querySQL.append("'");
						querySQL.append(item.getMinValue());
						querySQL.append("'");
						querySQL.append(" AND ");
						querySQL.append("'");
						querySQL.append(item.getMaxValue());
						querySQL.append("'");
					}
				}
				// 如果不是范围查询，则构造值条件
				else
				{
					// 无需单引号的情况
					if (item.getDataItem().getColumnDataType() == EnumColumnDataType.实数 || item.getDataItem().getColumnDataType() == EnumColumnDataType.整数
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.布尔值)
					{
						// 拼接逻辑运行符 AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// 拼接字段名
						querySQL.append(item.getDataItem().getColumnName());
						// 拼接条件
						querySQL.append(" = "); // 数字类型缺省使用精确匹配
						querySQL.append(item.getValue());
					}
					// 需要单引号的情况
					else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间 || item.getDataItem().getColumnDataType() == EnumColumnDataType.字符串
							|| item.getDataItem().getColumnDataType() == EnumColumnDataType.文本)
					{
						// 拼接逻辑运行符 AND OR
						querySQL.append(item.getIsAND() ? " AND " : " OR ");
						// 拼接字段名
						querySQL.append(item.getDataItem().getColumnName());
						// 拼接条件
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间)
						{
							querySQL.append(" = "); // 日期类型缺省使用精确匹配
							querySQL.append("'");
							querySQL.append(item.getValue());
							querySQL.append("'");
						}
						else
						{
							querySQL.append(" LIKE "); // 字符类型缺省使用模糊匹配
							querySQL.append("'%");
							querySQL.append(item.getValue());
							querySQL.append("%'");
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
	

	public boolean find(EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType, OfficialArchivesType officialArchivesType,
			List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";

		StringBuilder querySQL = new StringBuilder();// 查询条件的SQL片段
		String localSql = "";// 最终提交的查询SQL语句

		MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL的参数源对象
		NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
				}
			}

			// 检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}


			// 构造查询条件的SQL片段
			if (pFlag)
			{
				if (officialArchivesInfoQueryConditions != null)
				{
					if (officialArchivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForOfficialArchivesInfoInputQueryConditions(officialArchivesInfoQueryConditions, querySQL, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "构造查询条件SQL片段失败; ");
						}
					}
				}
			}

			// 构造SQL的参数源
			if (pFlag)
			{
				paramSource.addValue("OfficialArchivesTypeID", officialArchivesType.getID(), Types.INTEGER);
			}

			// 查询总记录数
			if (pFlag)
			{
				pErrPos = 3;

				// 工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_COUNT_By_QueryConditions, officialArchivesInfoTableName, querySQL.toString());
				// 执行查询总记录数的SQL
				int pRowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);
				// 设置分页对象中的总记录数
				dataPageInfo.setRowCount(pRowCount);
			}

			// 构造查询档案信息的SQL语句
			if (pFlag)
			{
				pErrPos = 4;

				// 如果分页大小大于0表示要求进行分页查询
				if (dataPageInfo.getPageSize() > 0)
				{
					// 将分页大小、工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
					// ?的动态构建，必须自行动态构建好SQL语句字符串
					localSql = String.format(SQL_SELECT_By_QueryConditions_WithPage, dataPageInfo.getPageSize(), (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize(),
							officialArchivesInfoTableName, querySQL.toString());
					System.out.println(localSql);

				}
				// 如果分页大小=0表示不分页
				else
				{
					// 工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
					// ?的动态构建，必须自行动态构建好SQL语句字符串
					localSql = String.format(SQL_SELECT_By_QueryConditions_WithoutPage, officialArchivesInfoTableName, querySQL.toString());
				}
			}

			// 执行查询档案信息的SQL语句并返回结果
			if (pFlag)
			{
				pErrPos = 5;
				List<OfficialArchivesInfo> pOfficialArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new OfficialArchivesInfoMapper(officialArchivesType));
				// 返回查询结果
				if (pOfficialArchivesInfos.size() > 0)
				{
					officialArchivesInfos.addAll(pOfficialArchivesInfos);
				}

				// 销毁局部变量
				pOfficialArchivesInfos = null;
			}
			
		}
		catch (BadSqlGrammarException e)
		{
			
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
			
		}
		catch (Exception e)
		{
			try {
				throw e;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 其他异常错误
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
			paramSource = null;
			namedParameterJdbcTemplate = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByID(int pID, OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体公文档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName= "";
		// INSERT SQL语句
		String localSql = "";
	
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// 检查要保存的公文档案信息是否为空
			if (pFlag)
			{
				if (officialArchivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案信息非法为空。");
				}
				else
				{
					if (officialArchivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合非法为空。");
					}
					else if (officialArchivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合数量非法0。");
					}
				}
			}
			
			//判断文号是否为空
			if (pFlag) {
				pErrPos = 1;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
				}
			}
			
			// 构造SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				StringBuilder columnsListSQL = new StringBuilder();// 字段名列表串，逗号分隔
				StringBuilder valuesListSQL = new StringBuilder(); // 字段值列表串，逗号分隔
				if (getSqlForArchivesInfoInsert(officialArchivesInfo, columnsListSQL, valuesListSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "构造插入SQL语句片段失败: ");
				}
				else
				{
					// 动态构建INSERT SQL语句
					localSql = String.format(SQL_INSERT_OfficialArchivesInfo, officialArchivesInfoTableName, columnsListSQL.toString(), valuesListSQL.toString());
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 3;
				PreparedStatementCreatorFactory pscFactory = new PreparedStatementCreatorFactory(localSql);
				// 构造SQL执行成功后返回的主键
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				pscFactory.setGeneratedKeysColumnNames(new String[] { "NBXH" });

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(pscFactory.newPreparedStatementCreator(new Object[] {}), keyHolder);

				// 返回自动生成的内部序号
				officialArchivesInfo.setNBXH(keyHolder.getKey().intValue());

				// 销毁局部变量
				pscFactory = null;
				keyHolder = null;
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
	

	
	
	@Override
	public boolean update(OfficialArchivesType officialArchivesType,OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		// INSERT SQL语句
		String localSql = "";

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查档案信息是否为空
			if (pFlag)
			{
				if (officialArchivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息非法为空。");
				}
				else
				{
					if (officialArchivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合非法为空。");
					}
					else if (officialArchivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合数量非法0。");
					}
				}
			}

			// 检查内部序号是否有值
			if (pFlag)
			{
				if (officialArchivesInfo.getNBXH() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
				}
			}

			// 构造SET SQL语句
			if (pFlag)
			{
				StringBuilder setValueSQL = new StringBuilder();
				if (getSqlForOfficialArchivesInfoUpdateSet(true, officialArchivesInfo, setValueSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "构造SET SQL片段失败: ");
				}
				else
				{
					pErrPos = 1;
					// 动态构建UPDATE SQL语句
					// SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?"
					localSql = String.format(SQL_UPDATE_ByNBXH, officialArchivesInfoTableName, setValueSQL.toString());
				}

				setValueSQL = null;
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(localSql, officialArchivesInfo.getNBXH());

				// 销毁局部变量
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
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
	
	/**
	 * 检查指定的公文档案分类信息下其公文档案登记表信息是否正确赋值了
	 * 
	 * @param officialArchivesType
	 *            公文档案分类信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkTableName(OfficialArchivesType officialArchivesType, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			if (officialArchivesType.getOfficialArchivesInfoTables() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文档案相关信息表非法为空");
			}
			else
			{
				if (officialArchivesType.getOfficialArchivesInfoTables().containsKey(EnumOfficialArchivesInfoTableType.公文档案登记表) == false)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类（" + officialArchivesType.getCode() + "）的公文档案登记表信息非法为空。");
				}
				else
				{
					String tableName = "";
					tableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
					if (tableName == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类（" + officialArchivesType.getCode() + "）的公文档案登记表信息非法为空。");
					}
					else
					{
						if (tableName.length() == 0)
						{
							pFlag = false;
							pErrInfo.getContent().append("公文档案分类（" + officialArchivesType.getCode() + "）的公文档案登记表信息非法为空。");
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
	
	/**
	 * 构造插入公文档案信息的字段列表和值列表的SQL片段
	 * 
	 * @param archivesInfo
	 *            档案信息对象
	 * @param columnsListSQL
	 *            返回构造好的字段名列表SQL片段，逗号分隔
	 * @param valuesListSQL
	 *            返回构造好的字段值SQL片段，逗号分隔
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getSqlForArchivesInfoInsert(OfficialArchivesInfo officialArchivesInfo, StringBuilder columnsListSQL, StringBuilder valuesListSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 2;
			
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues().values())
			{
				// 处理系统固有数据项字段
				if (item.getSystemItemFlag())
				{
					// 内部序号字段属于自增1字段，在INSERT时无需处理
					if (item.getSystemDataItemType() == EnumSystemDataItem.内部序号)
					{
						continue;
					}
					
					// 备份盘号由备份恢复程序写入，在INSERT时无需处理
					if (item.getSystemDataItemType() == EnumSystemDataItem.备份盘号)
					{
						continue;
					}

					// 卷内文件数量字段值在添加卷内文件时进行维护，本身INSERT时无需处理
					if (item.getSystemDataItemType() == EnumSystemDataItem.卷内文件数量)
					{
						continue;
					}

				}

				// 构造字段名列表字符串，逗号分隔
				columnsListSQL.append(item.getColumnName());
				columnsListSQL.append(",");

				// 构造值列表字符串，逗号分隔
				if (item.getValue() == null)
				{
					valuesListSQL.append("NULL");
				}
				else
				{
					if (item.getColumnDataType() == EnumColumnDataType.字符串 || item.getColumnDataType() == EnumColumnDataType.文本
							|| item.getColumnDataType() == EnumColumnDataType.日期时间)
					{
						valuesListSQL.append("'");
						valuesListSQL.append(item.getValue());
						valuesListSQL.append("'");
					}
					else if (item.getColumnDataType() == EnumColumnDataType.实数)
					{
						valuesListSQL.append(item.getValue());
					}
					else if (item.getColumnDataType() == EnumColumnDataType.布尔值)
					{
						valuesListSQL.append(item.getValue());
					}
					else if (item.getColumnDataType() == EnumColumnDataType.整数)
					{
						valuesListSQL.append(item.getValue());
					}
				}
				valuesListSQL.append(",");// 添加逗号分隔符
			}

			// 去掉字段列表字符串末尾的逗号分隔符
			if (pFlag)
			{
				pErrPos = 3;
				if (columnsListSQL.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("字段列表非法为空。");
				}
				else
				{
					columnsListSQL.deleteCharAt(columnsListSQL.length() - 1);
				}
			}

			// 去掉值列表字符串末尾的逗号分隔符
			if (pFlag)
			{
				pErrPos = 4;
				if (valuesListSQL.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("值列表非法为空。");
				}
				else
				{
					valuesListSQL.deleteCharAt(valuesListSQL.length() - 1);
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

	@Override
	public boolean findByNBXH(int pNBXH,
			OfficialArchivesType officialArchivesType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		String 	officialArchivesInfoTableName ;
		String localSql = "";
		officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
		localSql = String.format(SQL_SELECT_ByNBXH, officialArchivesInfoTableName);
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		List<OfficialArchivesInfo> pOfficialArchivesInfos = jdbcTemplate.query(localSql, new OfficialArchivesInfoMapper(officialArchivesType), pNBXH);

		// 返回查询结果
		if (pOfficialArchivesInfos .size() > 0)
		{
			officialArchivesInfo.cloneFrom(pOfficialArchivesInfos.get(0));
		}

		// 销毁局部变量
		pOfficialArchivesInfos = null;
		jdbcTemplate = null;
		return pFlag;
	}
	
	private boolean getSqlForOfficialArchivesInfoUpdateSet(Boolean simpleUpdate, OfficialArchivesInfo officialArchivesInfo, StringBuilder setValueSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues().values())
			{
				pErrPos = 1;
			
				// 如果是归档日期，在UPDATE时需更新处理，其他系统固有数据项字段一律不更新
				if (item.getSystemDataItemType() == EnumSystemDataItem.归档日期)
				{
					// 构造SET值列表字符串，逗号分隔
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					setValueSQL.append("getdate()");
					setValueSQL.append(",");// 添加逗号分隔符
				}
				// 如果是归档日期，在UPDATE时需更新处理，其他系统固有数据项字段一律不更新
				if (item.getSystemDataItemType() == EnumSystemDataItem.归档标志)
				{
					// 构造SET值列表字符串，逗号分隔
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					setValueSQL.append(item.getValue());
					setValueSQL.append(",");// 添加逗号分隔符
				}
				// 著录数据项字段
				else if (item.getInputFlag())
				{
					pErrPos = 2;
					// 构造SET值列表字符串，逗号分隔
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					if (item.getValue() == null)
					{
						setValueSQL.append("NULL");
					}
					else
					{
						pErrPos = 3;
						if (item.getColumnDataType() == EnumColumnDataType.字符串 || item.getColumnDataType() == EnumColumnDataType.文本
								|| item.getColumnDataType() == EnumColumnDataType.日期时间)
						{
							setValueSQL.append("'");
							setValueSQL.append(item.getValue());
							setValueSQL.append("'");
						}
						else if (item.getColumnDataType() == EnumColumnDataType.实数)
						{
							setValueSQL.append(item.getValue());
						}
						else if (item.getColumnDataType() == EnumColumnDataType.布尔值)
						{
							setValueSQL.append(item.getValue());
						}
						else if (item.getColumnDataType() == EnumColumnDataType.整数)
						{
							setValueSQL.append(item.getValue());
						}
					}
					setValueSQL.append(",");// 添加逗号分隔符

					// 如果该数据项有关联字段，则进一步处理其关联字段的数据项
					if (item.getAssociateTextColumnName() != null)
					{
						if (item.getAssociateTextColumnName().length() > 0)
						{
							if (officialArchivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName()))
							{
								pErrPos = 4;
								// 获取关联的数据项字段值对象
								FieldValue associateFieldValue = officialArchivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName());

								// 构造SET值列表字符串，逗号分隔
								setValueSQL.append(associateFieldValue.getColumnName());
								setValueSQL.append("=");
								if (associateFieldValue.getValue() == null)
								{
									setValueSQL.append("NULL");
								}
								else
								{
									if (associateFieldValue.getColumnDataType() == EnumColumnDataType.字符串 || associateFieldValue.getColumnDataType() == EnumColumnDataType.文本
											|| associateFieldValue.getColumnDataType() == EnumColumnDataType.日期时间)
									{
										setValueSQL.append("'");
										setValueSQL.append(associateFieldValue.getValue());
										setValueSQL.append("'");
									}
									else if (associateFieldValue.getColumnDataType() == EnumColumnDataType.实数)
									{
										setValueSQL.append(associateFieldValue.getValue());
									}
									else if (associateFieldValue.getColumnDataType() == EnumColumnDataType.布尔值)
									{
										setValueSQL.append(associateFieldValue.getValue());
									}
									else if (associateFieldValue.getColumnDataType() == EnumColumnDataType.整数)
									{
										setValueSQL.append(associateFieldValue.getValue());
									}
								}
								setValueSQL.append(",");// 添加逗号分隔符
							}
						}
					}

				}
				// 其他数据项不予处理
				else
				{
					continue;
				}
			}

			// 去掉字段列表字符串末尾的逗号分隔符
			if (pFlag)
			{
				pErrPos = 5;
				if (setValueSQL.length() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("SET值列表非法为空。");
				}
				else
				{
					setValueSQL.deleteCharAt(setValueSQL.length() - 1);
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

	@Override
	public boolean update(OfficialArchivesInfo officialArchivesInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	
	@Override
	public boolean batchDelOfficialArchives(OfficialArchivesType officialArchivesType,final List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		String localSql = "";// 最终提交的SQL语句

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 1;
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 2;
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
				}
			}

			// 检查档案的内部序号是否为空
			if (pFlag)
			{
				pErrPos = 3;
				if (officialArchivesInfos == null || officialArchivesInfos.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入的内部序号非法为空！");
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 4;

				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_DELETE_ByNBXH, officialArchivesInfoTableName);
				// localSql = String.format(SQL_DELETE_BatchDleteArchives,
				// "ArchivesInfoWorking_JX14");
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.batchUpdate(localSql,
						new BatchPreparedStatementSetter() {
							public void setValues(PreparedStatement ps, int i)
									throws SQLException {
								ps.setInt(1, officialArchivesInfos.get(i).getNBXH());
							}

							public int getBatchSize() {
								return officialArchivesInfos.size();
							}
						});
				// 销毁局部变量
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
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

	@Override
	public boolean deleteParentAndChild(OfficialArchivesType officialArchivesType, OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String officialArchivesInfoTableName = "";
		String localSql = "";// 最终提交的SQL语句

		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 检查内部序号是否有值
			if (pFlag)
			{
				if (officialArchivesInfo.getNBXH()<= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";
				
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_DELETE_ParentAndChild_ByNBXH, officialArchivesInfoTableName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", officialArchivesInfo.getNBXH(),Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(localSql, paramSource);

				// 销毁局部变量
				paramSource = null;
				namedParameterJdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			// SQL语句语法错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			// 其他异常错误
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

	@Override
	public boolean findAll(OfficialArchivesType officialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String officialArchivesInfoTableName = "";
		String localSql = "";
		try {
			//检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag)
			{
				if (checkTableName(officialArchivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					officialArchivesInfoTableName = officialArchivesType.getOfficialArchivesInfoTables().get(EnumOfficialArchivesInfoTableType.公文档案登记表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				localSql = String.format(SQL_SELECT_ALL, officialArchivesInfoTableName);
				List<OfficialArchivesInfo> pOfficialArchivesInfos=jdbcTemplate.query(localSql,new OfficialArchivesInfoMapper(officialArchivesType));
				//返回查询结果
				if (pOfficialArchivesInfos.size() > 0) {
					officialArchivesInfos.addAll(pOfficialArchivesInfos);
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
