/**
 * 
 */
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
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.EnumWorkingUserIDType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 档案信息工作表DAO实现类
 * 
 */
public class ArchivesInfoWorkingDaoImpl extends JdbcDaoSupport implements IArchivesInfoWorkingDao
{

	/**
	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
	 * 
	 */
	public class ArchivesInfoMapper implements RowMapper<ArchivesInfo>
	{

		/**
		 * 带参数的构造函数
		 * 
		 * @param archivesType
		 *            档案分类信息
		 */
		public ArchivesInfoMapper(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * 档案分类信息
		 */
		private ArchivesType archivesType = null;

		/**
		 * 设置属性值：档案分类信息
		 * 
		 * @param archivesType
		 *            档案分类信息
		 */
		public void setArchivesType(ArchivesType archivesType)
		{
			this.archivesType = archivesType;
		}

		/**
		 * 获取属性值：档案分类信息
		 * 
		 * @return 档案分类信息
		 */
		public ArchivesType getArchivesType()
		{
			return archivesType;
		}

		@Override
		public ArchivesInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			String value = "";
			ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
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

			return archivesInfo;
		}
	}

	/**
	 * 构造函数
	 */
	public ArchivesInfoWorkingDaoImpl()
	{

	}

	/**
	 * 带数据源的构造函数
	 * 
	 * @param dataSource
	 *            数据源
	 */
	public ArchivesInfoWorkingDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}

	/**
	 * 查询指定工作流状态下满足指定条件的文件级或案卷级档案信息总数量的SQL语句（卷内文件记录要过滤掉）<br>
	 * %1$s：具体档案分类对应的归档工作表名<br>
	 * %2$s：指定查询条件的SQL片段<br>
	 * %3$s：parentFlag<br>
	 * %4$s：userType<br>
	 */
	private final String SQL_SELECT_COUNT_By_QueryConditions = "SELECT COUNT(NBXH) FROM %1$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %4$s %3$s  AND ParentNBXH=0 %2$s";

	/**
	 * 分页查询指定工作流状态下满足指定条件的文件级或案卷级档案信息的SQL语句（卷内文件记录要过滤掉）<br>
	 * %1$s：页大小<br>
	 * %2$s：已经读取过的记录数量<br>
	 * %3$s：具体档案分类对应的归档工作表名<br>
	 * %4$s：指定查询条件的SQL片段<br>
	 * %5$s：是否拼上parentFlag标志
	 */
	private final String SQL_SELECT_By_QueryConditions_WithPage = "SELECT TOP %1$s * FROM %3$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %6$s %5$s AND ParentNBXH=0 %4$s AND NBXH < "
			+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %2$s NBXH FROM %3$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %6$s %5$s AND ParentNBXH=0 %4$s ORDER BY NBXH DESC) AS T) "
			+ "ORDER BY NBXH DESC";

	/**
	 * 查询指定工作流状态下满足指定条件的文件级或案卷级档案信息的SQL语句（卷内文件记录要过滤掉），不分页<br>
	 * %1$s：具体档案分类对应的归档工作表名<br>
	 * %2$s：指定查询条件的SQL片段<br>
	 */
	private final String SQL_SELECT_By_QueryConditions_WithoutPage = "SELECT * FROM %1$s"
			+ " WHERE ArchivesTypeID=:ArchivesTypeID AND WorkFlowStatus=:WorkFlowStatus %4$s %3$s AND ParentNBXH=0 %2$s ORDER BY NBXH DESC";

	/**
	 * 查询指定内部序号的档案信息的SQL语句
	 */
	private final String SQL_SELECT_ByNBXH = "SELECT * FROM %1$s WHERE NBXH=?";

	/**
	 * 更新指定档案信息的SQL语句<br>
	 * 更新档案信息时仅能够修改档案的元数据属性字段值，对于档案工作流状态以及著录人员等信息不作修改
	 */
	private final String SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?";

	/**
	 * 插入新增档案信息记录的SQL语句
	 */
	private final String SQL_INSERT_ArchivesInfo = "INSERT INTO %1$s (%2$s) VALUES(%3$s)";

	/**
	 * 查询指定案卷的最大卷内文件序号值的SQL语句
	 */
	private final String SQL_SELECT_MaxSubContentID = "SELECT MAX(SubContentID) FROM %1$s WHERE ParentNBXH=?";

	/**
	 * 更新指定案卷的卷内文件数量和总页数的SQL语句
	 */
	private final String SQL_UPDATE_SubContentCountAndPageSum = "UPDATE %1$s SET " + "SubContentCount=(SELECT COUNT(NBXH) FROM %1$s WHERE ParentNBXH=:ParentNBXH),"
			+ "PageSum=(SELECT SUM(PageSum) FROM %1$s WHERE ParentNBXH=:ParentNBXH) " + "WHERE NBXH=:ParentNBXH";

	/**
	 * 根据案卷的内部序号查找其所有卷内文件信息的SQL语句
	 */
	private final String SQL_SELECT_ChildArchivesInfo_ByParentNBXH = "SELECT * FROM %1$s WHERE ParentNBXH=? ORDER BY SubContentID desc";

	/**
	 * 批量更新指定档案信息的案卷标志的SQL
	 */
	private final String SQL_UPDATE_BatchUpdateParentFlag = "UPDATE %1$s SET ParentNBXH=?,UserID1=?,WorkFlowStatus=?,ContentID=?,ContentIDText=?,SubContentID=?,SubContentIDText=?,ArchivesID=? WHERE NBXH=?";
	
	/**
	 * 批量更新指定档案信息的所属档案的内部序号的SQL
	 */
	private final String SQL_UPDATE_BatchUpdateParentNBXH = "UPDATE %1$s SET ParentNBXH=:parentNBXH WHERE NBXH IN (:NBXHS)";

	/**
	 * 删除指定内部序号的档案信息记录的SQL
	 */
	private final String SQL_DELETE_ByNBXH = "DELETE FROM %1$s WHERE NBXH=? OR ParentNBXH=?";
	
	/**
	 * 删除指定案卷内部序号及其卷内文件的档案信息记录的SQL
	 */
	private final String SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";

	/**
	 * 更新指定内部序号档案信息记录的工作流状态和用户编号信息的SQL语句
	 */
	private final String SQL_UPDATE_WorkFlowStatusUserID_ByNBXH = "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE NBXH IN (:NBXH)";

	// /**
	// * 删除指定档案信息的案卷标志的SQL
	// */
	// private final String SQL_DELETE_ByNBXH =
	// "delete from %1$s  where NBXH=?";

	/**
	 * 更新指定内部序号档案的所有卷内文件的工作流状态和用户编号信息的SQL语句
	 */
	private final String SQL_UPDATE_WorkFlowStatusUserID_ByParentNBXH = "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE ParentNBXH IN (:ParentNBXH)";

	/**
	 * 根据工作流状态统计档案数量的SQL
	 * %1$s：表名
	 * %2$s：用户ID
	 */
	private final String SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser = "SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus =:WorkFlowStatus AND %2$s IN (:userIDs) AND ParentNBXH=0";
	
	/**
	 * 根据工作流状态统计档案数量的SQL
	 * %1$s：表名
	 * %2$s：用户ID
	 */
	private final String SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndDept = "SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus =:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) AND ParentNBXH=0";
	
	/**
	 * 根据工作状态查找档案信息的SQL
	 */
	private final String SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndUser_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND %4$s IN (:userIDs) AND  ParentNBXH=0 AND NBXH < "
		+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND %4$s IN (:userIDs)  AND ParentNBXH=0 ORDER BY NBXH DESC) AS T) "
		+ "ORDER BY NBXH DESC";
	
	/**
	 * 根据工作状态查找档案信息的SQL
	 */
	private final String SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndDept_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) AND ParentNBXH=0 AND NBXH < "
		+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) AND ParentNBXH=0  ORDER BY NBXH DESC) AS T) "
		+ "ORDER BY NBXH DESC";
	
	/**
	 * 设置档案信息修复标志的SQL
	 */
	private static final String SQL_UPDATE_updateArchivesInfoFixedFlag = "UPDATE %1$s SET FixedFlag=? WHERE NBXH=?";
	
	
	/**
	 * 检查JDBC数据源的依赖注入（JDBC DataSource Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查数据源是否为空
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC数据源非法为空，请检查是否有进行依赖注入或赋值。");
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

	/**
	 * 构造档案更新的SET语句SQL片段<br>
	 * 注意：该SQL片段中只有SET关键字之后的赋值语句，不包含SET关键字
	 * 
	 * @param simpleUpdate
	 *            是否为简单更新处理<br>
	 *            简单更新适用于仅仅对档案可著录的档案元数据信息进行修改更新，对于档案工作流状态以及著录人员等信息不作修改<br>
	 *            完整更新除了包括档案可著录的元数据信息要更新，还要更新档案的归档工作流状态、工作人员编号等信息
	 * @param archivesInfo
	 *            档案信息对象
	 * @param setValueSQL
	 *            返回构造好的SET语句
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getSqlForArchivesInfoUpdateSet(Boolean simpleUpdate, ArchivesInfo archivesInfo, StringBuilder setValueSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				pErrPos = 1;
				// 完整更新的时候需要处理的字段
				if (item.getSystemDataItemType() == EnumSystemDataItem.档案管理工作流状态 || item.getSystemDataItemType() == EnumSystemDataItem.工作人员1
						|| item.getSystemDataItemType() == EnumSystemDataItem.工作人员2 || item.getSystemDataItemType() == EnumSystemDataItem.工作人员3)
				{
					if (simpleUpdate == false)
					{
						// 构造SET值列表字符串，逗号分隔
						setValueSQL.append(item.getColumnName());
						setValueSQL.append("=");
						if (item.getValue() == null)
						{
							setValueSQL.append("NULL");
						}
						else
						{
							setValueSQL.append(item.getValue());
						}
						setValueSQL.append(",");// 添加逗号分隔符
					}
				}
				// 如果是归档日期，在UPDATE时需更新处理，其他系统固有数据项字段一律不更新
				if (item.getSystemDataItemType() == EnumSystemDataItem.归档日期)
				{
					// 构造SET值列表字符串，逗号分隔
					setValueSQL.append(item.getColumnName());
					setValueSQL.append("=");
					setValueSQL.append("getdate()");
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
							if (archivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName()))
							{
								pErrPos = 4;
								// 获取关联的数据项字段值对象
								FieldValue associateFieldValue = archivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName());

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

	/**
	 * 构造插入档案信息的字段列表和值列表的SQL片段
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
	private boolean getSqlForArchivesInfoInsert(ArchivesInfo archivesInfo, StringBuilder columnsListSQL, StringBuilder valuesListSQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 2;
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
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

					// 归档日期、修复标志、删除标志、开放标志都有缺省值，在INSERT时无需处理
					if (item.getSystemDataItemType() == EnumSystemDataItem.归档日期 || item.getSystemDataItemType() == EnumSystemDataItem.修复标志
							|| item.getSystemDataItemType() == EnumSystemDataItem.删除标志 || item.getSystemDataItemType() == EnumSystemDataItem.开放标志)
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
	private static boolean getSqlForArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#save(com.orifound.aiim
	 * .entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		// INSERT SQL语句
		String localSql = "";

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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查要保存的档案信息是否为空
			if (pFlag)
			{
				if (archivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息非法为空。");
				}
				else
				{
					if (archivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合非法为空。");
					}
					else if (archivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合数量非法0。");
					}
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 构造SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				StringBuilder columnsListSQL = new StringBuilder();// 字段名列表串，逗号分隔
				StringBuilder valuesListSQL = new StringBuilder(); // 字段值列表串，逗号分隔
				if (getSqlForArchivesInfoInsert(archivesInfo, columnsListSQL, valuesListSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "构造插入SQL语句片段失败: ");
				}
				else
				{
					// 动态构建INSERT SQL语句
					localSql = String.format(SQL_INSERT_ArchivesInfo, archivesInfoWorkingTableName, columnsListSQL.toString(), valuesListSQL.toString());
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
				archivesInfo.setNBXH(keyHolder.getKey().intValue());

				// 销毁局部变量
				pscFactory = null;
				keyHolder = null;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#delete(com.orifound
	 * .aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案信息对象是否赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息对象非法为空");
				}
			}

			// 检查内部序号是否有值
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfo.getNBXH() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息对象属性（NBXH）没有赋值");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 4;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 5;
				localSql = String.format(SQL_DELETE_ByNBXH, archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, archivesInfo.getNBXH(),archivesInfo.getNBXH());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#update(com.orifound
	 * .aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesInfo == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息非法为空。");
				}
				else
				{
					if (archivesInfo.getRowFieldsValues() == null)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合非法为空。");
					}
					else if (archivesInfo.getRowFieldsValues().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息下的数据项字段值集合数量非法0。");
					}
				}
			}

			// 检查内部序号是否有值
			if (pFlag)
			{
				if (archivesInfo.getNBXH() <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 构造SET SQL语句
			if (pFlag)
			{
				StringBuilder setValueSQL = new StringBuilder();
				if (getSqlForArchivesInfoUpdateSet(true, archivesInfo, setValueSQL, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "构造SET SQL片段失败: ");
				}
				else
				{
					pErrPos = 1;
					// 动态构建UPDATE SQL语句
					// SQL_UPDATE_ByNBXH = "UPDATE %1$s SET %2$s WHERE NBXH=?"
					localSql = String.format(SQL_UPDATE_ByNBXH, archivesInfoWorkingTableName, setValueSQL.toString());
				}

				setValueSQL = null;
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.update(localSql, archivesInfo.getNBXH());

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#find(com.orifound.aiim
	 * .entity.EnumArchivesInfoType, int[],
	 * com.orifound.aiim.entity.ArchivesType,
	 * com.orifound.aiim.entity.EnumWorkFlowStatus,java.util.List,
	 * com.orifound.aiim.entity.DataPageInfo, java.util.List,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean find(EnumArchivesInfoType enumArchivesInfoType, int[] userID, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		List<Integer> pUserIDs = new ArrayList<Integer>();

		StringBuilder querySQL = new StringBuilder();// 查询条件的SQL片段
		String localSql = "";// 最终提交的查询SQL语句
		String ParentFlag = "";

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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
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

			// 转换数组为list结构
			if (pFlag)
			{
				for (int i = 0; i < userID.length; i++)
				{
					pUserIDs.add(userID[i]);
				}

				if (pUserIDs.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空。");
				}
			}

			// 构造查询条件的SQL片段
			if (pFlag)
			{
				if (archivesInfoQueryConditions != null)
				{
					if (archivesInfoQueryConditions.size() > 0)
					{
						pErrPos = 2;
						if (getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "构造查询条件SQL片段失败; ");
						}
					}
				}
			}

			EnumWorkingUserIDType enumWorkingUserIDType = null;
			String userType = "";
			if (pFlag) {
				if (enumWorkFlowStatus==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案的工作流状态信息非法为空。");
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.著录完成 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.提交送审完成 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.确认进行实物档案的馆外移交 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.添加至馆外移交清单) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID1;
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.业务指导室著录审核打回 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.业务指导室著录审核通过 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.业务指导室接收审核打回 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.业务指导室接收审核通过 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.确认进行实物档案的馆内移交 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.归档信息打回修改) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID2;
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.档案管理室接收审核打回 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.档案管理室接收审核通过 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.档案上架入库完成 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.档案被预约利用 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.档案被查档利用 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.档案被借阅利用 ||
						enumWorkFlowStatus==EnumWorkFlowStatus.档案已归还) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID3;
				}else if(enumWorkFlowStatus == EnumWorkFlowStatus.形成部门将档案从清单中移除){
					   enumWorkFlowStatus = EnumWorkFlowStatus.业务指导室著录审核通过;
					   enumWorkingUserIDType=EnumWorkingUserIDType.UserID1;
				}
				userType = " AND " + enumWorkingUserIDType.toString() + " IN (:UserIDs)";
			}
			
			// 构造SQL的参数源
			if (pFlag)
			{
				paramSource.addValue("ArchivesTypeID", archivesType.getID(), Types.INTEGER);
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue(), Types.INTEGER);
				paramSource.addValue("UserIDs", pUserIDs);
				//int parentFlag = 0;
				
				if (enumArchivesInfoType == EnumArchivesInfoType.文件级档案)
				{
					ParentFlag = "AND ParentFlag=:ParentFlag";
					paramSource.addValue("ParentFlag", 0, Types.INTEGER);
				}
				else if(enumArchivesInfoType == EnumArchivesInfoType.案卷级档案)
				{
					ParentFlag = "AND ParentFlag=:ParentFlag";
					paramSource.addValue("ParentFlag", 1, Types.INTEGER);
				}
//				else if(enumArchivesInfoType == EnumArchivesInfoType.NONE){
//					ParentFlag = "AND ParentFlag<>:ParentFlag";
//					paramSource.addValue("ParentFlag", 2, Types.INTEGER);
//				}
			}

			// 查询总记录数
			if (pFlag)
			{
				pErrPos = 3;

				// 工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_COUNT_By_QueryConditions, archivesInfoWorkingTableName, querySQL.toString(),ParentFlag,userType);
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
							archivesInfoWorkingTableName, querySQL.toString(),ParentFlag,userType);

				}
				// 如果分页大小=0表示不分页
				else
				{
					// 工作表名以及查询条件动态构建到SQL语句中去，注意jdbc不支持select top ? from
					// ?的动态构建，必须自行动态构建好SQL语句字符串
					localSql = String.format(SQL_SELECT_By_QueryConditions_WithoutPage, archivesInfoWorkingTableName, querySQL.toString(),ParentFlag,userType);
				}
			}

			// 执行查询档案信息的SQL语句并返回结果
			if (pFlag)
			{
				pErrPos = 5;
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesType));

				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
				}

				// 销毁局部变量
				pArchivesInfos = null;
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
			paramSource = null;
			namedParameterJdbcTemplate = null;
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao#findByNBXH(int,
	 * com.orifound.aiim.entity.ArchivesType,
	 * com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (pNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_ByNBXH, archivesInfoWorkingTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapper(archivesType), pNBXH);

				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfo.cloneFrom(pArchivesInfos.get(0));
				}

				// 销毁局部变量
				pArchivesInfos = null;
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
	public boolean findMaxSubContentID(ArchivesType archivesType, int parentNBXH, IntegerEx maxSubContentID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_MaxSubContentID, archivesInfoWorkingTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				int pMaxContentID = jdbcTemplate.queryForInt(localSql, parentNBXH);

				// 返回查询结果
				maxSubContentID.setValue(pMaxContentID);

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
	public boolean updateSubContentCountAndPageSum(ArchivesType archivesType, int parentNBXH, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 1;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_UPDATE_SubContentCountAndPageSum, archivesInfoWorkingTableName);

				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("ParentNBXH", parentNBXH, Types.INTEGER);

				pErrPos = 3;
				namedParameterJdbcTemplate.update(localSql, paramSource);

				// 销毁局部变量
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
	public boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (pNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from
				// ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_SELECT_ChildArchivesInfo_ByParentNBXH, archivesInfoWorkingTableName);

				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<ArchivesInfo> pArchivesInfos = jdbcTemplate.query(localSql, new ArchivesInfoMapper(archivesType), pNBXH);

				// 返回查询结果
				if (pArchivesInfos.size() > 0)
				{
					archivesInfos.addAll(pArchivesInfos);
				}

				// 销毁局部变量
				pArchivesInfos = null;
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
	public boolean batchUpdateParentNBXH(ArchivesType archivesType,final List<ArchivesInfo> archivesInfos,final int userID,final EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 2;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//检查传入的更新的档案信息集合
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfos == null || archivesInfos.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入的要更新的档案信息集合非法为空");
				}

			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 4;
				//UPDATE %1$s SET ParentNBXH=?,UserID1=?,WorkFlowStatus=?,ContentID=?,ContentIDText=?,SubContentID=?,SubContentIDText=?,ArchivesID=? WHERE NBXH=?
				localSql = String.format(SQL_UPDATE_BatchUpdateParentFlag, archivesInfoWorkingTableName);
				BatchPreparedStatementSetter preparedStatementSetter = new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)throws SQLException {
						ps.setInt(1, archivesInfos.get(i).getParentNBXH());
						ps.setInt(2, userID);
						ps.setInt(3, enumWorkFlowStatus.getEnumValue());
						ps.setInt(4, archivesInfos.get(i).getContentID());
						ps.setString(5, archivesInfos.get(i).getContentIDText());
						ps.setInt(6, archivesInfos.get(i).getSubContentID());
						ps.setString(7, archivesInfos.get(i).getSubContentIDText());
						ps.setString(8, archivesInfos.get(i).getArchivesID());
						ps.setInt(9, archivesInfos.get(i).getNBXH());
					}
					//返回执行次数
					public int getBatchSize() {
						return archivesInfos.size();
					}
				};
				
				getJdbcTemplate().batchUpdate(localSql,preparedStatementSetter);
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
	public boolean batchDelArchives(ArchivesType archivesType,final List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				pErrPos = 2;
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 检查档案的内部序号是否为空
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfos == null || archivesInfos.size() <= 0) {
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
				localSql = String.format(SQL_DELETE_ByNBXH, archivesInfoWorkingTableName);
				// localSql = String.format(SQL_DELETE_BatchDleteArchives,
				// "ArchivesInfoWorking_JX14");
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				jdbcTemplate.batchUpdate(localSql,
						new BatchPreparedStatementSetter() {
							public void setValues(PreparedStatement ps, int i)
									throws SQLException {
								ps.setInt(1, archivesInfos.get(i).getNBXH());
							}

							public int getBatchSize() {
								return archivesInfos.size();
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
	public boolean updateWorkFlowUserIDByNBXH(ArchivesType archivesType, int[] pNBXHs, EnumWorkingUserIDType enumWorkingUserIDType, int userID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		String userIDFieldName = "";// 用户编号的具体字段名
		List<Integer> localNBXHs = null;// 内部序号集合
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
				if (pNBXHs.length == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为空");
				}
			}

			// 检查用户编号是否为空
			if (pFlag)
			{
				if (userID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案归档工作人员的用户编号非法为0");
				}
			}

			// 检查工作流状态信息是否为空
			if (pFlag)
			{
				if (enumWorkFlowStatus == EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案的工作流状态信息非法为空。");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 计算用户编号的具体字段名
			if (pFlag)
			{
				if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID1)
				{
					userIDFieldName = "UserID1";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID2)
				{
					userIDFieldName = "UserID2";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID3)
				{
					userIDFieldName = "UserID3";
				}
			}

			// 转换内部序号集合
			if (pFlag)
			{
				// 转换数组为list结构
				if (pFlag)
				{
					localNBXHs = new ArrayList<Integer>();
					for (int i = 0; i < pNBXHs.length; i++)
					{
						if (pNBXHs[i] > 0)
						{
							localNBXHs.add(pNBXHs[i]);
						}
					}

					if (localNBXHs.size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("有效的内部序号信息非法为空。");
					}
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_UPDATE_WorkFlowStatusUserID_ByNBXH =
				// "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE NBXH IN (:NBXH)";
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_UPDATE_WorkFlowStatusUserID_ByNBXH, archivesInfoWorkingTableName, userIDFieldName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue(), Types.INTEGER);
				paramSource.addValue("UserID", userID, Types.INTEGER);
				paramSource.addValue("NBXH", localNBXHs);

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
	public boolean updateWorkFlowUserIDForChild(ArchivesType archivesType, int[] parentNBXHs, EnumWorkingUserIDType enumWorkingUserIDType, int userID,
			EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		String userIDFieldName = "";// 用户编号的具体字段名
		ArrayList<Integer> localParentNBXHs = null;// 所属案卷内部序号集合
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
				if (parentNBXHs.length <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案所属案卷的内部序号非法为0");
				}
			}

			// 检查用户编号是否为空
			if (pFlag)
			{
				if (userID <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案归档工作人员的用户编号非法为0");
				}
			}

			// 检查工作流状态信息是否为空
			if (pFlag)
			{
				if (enumWorkFlowStatus == EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案的工作流状态信息非法为空。");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 计算用户编号的具体字段名
			if (pFlag)
			{
				if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID1)
				{
					userIDFieldName = "UserID1";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID2)
				{
					userIDFieldName = "UserID2";
				}
				else if (enumWorkingUserIDType == EnumWorkingUserIDType.UserID3)
				{
					userIDFieldName = "UserID3";
				}
			}

			// 转换内部序号集合
			if (pFlag)
			{
				// 转换数组为list结构
				if (pFlag)
				{
					localParentNBXHs = new ArrayList<Integer>();
					for (int i = 0; i < parentNBXHs.length; i++)
					{
						if (parentNBXHs[i] > 0)
						{
							localParentNBXHs.add(parentNBXHs[i]);
						}
					}

					if (localParentNBXHs.size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("有效的档案所属案卷内部序号信息非法为空。");
					}
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_UPDATE_WorkFlowStatusUserID_ByParentNBXH =
				// "UPDATE %1$s SET WorkFlowStatus=:WorkFlowStatus,%2$s=:UserID WHERE ParentNBXH=:ParentNBXH";
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_UPDATE_WorkFlowStatusUserID_ByParentNBXH, archivesInfoWorkingTableName, userIDFieldName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue(), Types.INTEGER);
				paramSource.addValue("UserID", userID, Types.INTEGER);
				paramSource.addValue("ParentNBXH", localParentNBXHs);

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
	public boolean deleteParentAndChild(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesInfo.getNBXH()<= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";
				
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_DELETE_ParentAndChild_ByNBXH, archivesInfoWorkingTableName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("NBXH", archivesInfo.getNBXH(),Types.INTEGER);

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
	public boolean batchUpdateParentNBXH(int parentNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		String localSql = "";// 最终提交的SQL语句

		List<Integer> NBXHS = new ArrayList<Integer>();
		
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
				if (parentNBXH <= 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的内部序号非法为0");
				}
			}

			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			//检查传入的更新的档案信息集合
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfos == null || archivesInfos.size() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("传入的要更新的档案信息集合非法为空");
				}else{
					for (ArchivesInfo archivesInfo2 : archivesInfos) {
						NBXHS.add(archivesInfo2.getNBXH());
					}
				}

			}
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_DELETE_ParentAndChild_ByNBXH = "DELETE FROM %1$s WHERE NBXH=:NBXH OR ParentNBXH=:NBXH";
				
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_UPDATE_BatchUpdateParentNBXH, archivesInfoWorkingTableName);

				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("parentNBXH", parentNBXH);
				paramSource.addValue("NBXHS", NBXHS);

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
	public boolean statArchivesInfoCountByWorkFlowStatus(int [] userIDs, ArchivesType pArchivesType,EnumWorkFlowStatus enumWorkFlowStatus,String userType,IntegerEx count, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// 最终提交的SQL语句
		
		ArchivesType archivesType = null;
		List<Integer> users = new ArrayList<Integer>();
		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else if(userIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else{
					for (Integer userId : userIDs) {
						users.add(userId);
					}
				}
			}
			
			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (pArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}else{
					archivesType = pArchivesType;
				}
			}
			
			// 检查档案分类对应的工作表名称是否有值
			/*if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}*/
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				// SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatus = "SELECT COUNT(*) FROM ArchivesInfoWorking_JX14 WHERE WorkFlowStatus =? AND UserID1 IN (?)"
				//String sql = "SELECT COUNT(*) FROM ArchivesInfoWorking_JX14 WHERE WorkFlowStatus =:WorkFlowStatus AND UserID1 IN (:userIDs)";
				// 将工作表名动态构建到SQL语句中去
				//localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser, archivesInfoWorkingTableName);
				localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser, "ArchivesInfoWorking_JX",userType);
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				paramSource.addValue("userIDs", users);

				count.setValue(namedParameterJdbcTemplate.queryForInt(localSql, paramSource));
				
				// 销毁局部变量
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
	public boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus,DataPageInfo dataPageInfo,String userType, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// 最终提交的SQL语句

		List<Integer> users = new ArrayList<Integer>();
		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else if(userIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else{
					for (Integer userId : userIDs) {
						users.add(userId);
					}
				}
			}
			
			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}else if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息编号未赋值");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				/*String SQL_SELECT_ArchivesInfos_By_WorkFlowStatus_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) AND NBXH < "
				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) ORDER BY NBXH DESC) AS T) "
				+ "ORDER BY NBXH DESC";*/
				//"SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs)";
				
				// 将工作表名动态构建到SQL语句中去
				localSql = String.format(SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndUser_WithPage,dataPageInfo.getPageSize(), archivesInfoWorkingTableName,(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize(),userType);
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				paramSource.addValue("userIDs", users);

				pErrPos = 3;
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesType));
				
				//统计总数量
				localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndUser, archivesInfoWorkingTableName,userType);
				int rowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);

				archivesInfos.addAll(pArchivesInfos);
				dataPageInfo.setRowCount(rowCount);
				// 销毁局部变量
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
	public boolean findArchivesInfosByEnumWorkFlowStatus(ArchivesType archivesType, int[] deptIDs, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// 最终提交的SQL语句

		List<Integer> depts = new ArrayList<Integer>();
		try
		{
			// 检查JDBC数据源是否正确依赖注入
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			if (pFlag) {
				if (deptIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else if(deptIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("用户编号未赋值");
				}else{
					for (Integer userId : deptIDs) {
						depts.add(userId);
					}
				}
			}
			
			// 检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}else if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息编号未赋值");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				/*String SQL_SELECT_ArchivesInfos_By_WorkFlowStatus_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) AND NBXH < "
				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) ORDER BY NBXH DESC) AS T) "
				+ "ORDER BY NBXH DESC";*/
				//"SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs)";
				
				// 将工作表名动态构建到SQL语句中去
				localSql = String.format(SQL_SELECT_ArchivesInfos_By_WorkFlowStatusAndDept_WithPage,dataPageInfo.getPageSize(), archivesInfoWorkingTableName,(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize());
				
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				
				paramSource.addValue("WorkFlowStatus", enumWorkFlowStatus.getEnumValue());
				paramSource.addValue("deptIDs", depts);

				pErrPos = 3;
				List<ArchivesInfo> pArchivesInfos = namedParameterJdbcTemplate.query(localSql, paramSource, new ArchivesInfoMapper(archivesType));
				
				//统计总数量
				localSql = String.format(SQL_SELECT_statArchivesInfoCount_By_WorkFlowStatusAndDept, archivesInfoWorkingTableName);
				int rowCount = namedParameterJdbcTemplate.queryForInt(localSql, paramSource);

				archivesInfos.addAll(pArchivesInfos);
				dataPageInfo.setRowCount(rowCount);
				// 销毁局部变量
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
	public boolean updateArchivesBackReason(int nbxh,ArchivesType archivesType, String backReason, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
		
		String localSql = "";// 最终提交的SQL语句

		List<Integer> depts = new ArrayList<Integer>();
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}else if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("要统计的档案分类信息编号未赋值");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				/*String SQL_SELECT_ArchivesInfos_By_WorkFlowStatus_WithPage = "SELECT TOP %1$s * FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND UserID1 IN (:userIDs) AND NBXH < "
				+ "(SELECT ISNULL(MIN(NBXH),2147483647) FROM (SELECT TOP %3$s NBXH FROM %2$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs) ORDER BY NBXH DESC) AS T) "
				+ "ORDER BY NBXH DESC";*/
				//"SELECT COUNT(*) FROM %1$s WHERE WorkFlowStatus=:WorkFlowStatus AND FormationDepartmentID IN (:deptIDs)";
				
				String sql = "UPDATE %1$s SET SendBackReason=? WHERE NBXH=?";

				// 将工作表名动态构建到SQL语句中去
				localSql = String.format(sql,archivesInfoWorkingTableName);
				
				getJdbcTemplate().update(localSql,backReason,nbxh);
				// 销毁局部变量
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
	public boolean updateArchivesInfoFixedFlag(ArchivesType archivesType, int NBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			if (pFlag) {
				if (NBXH == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("要修复的档案内部序号非法为0");
				}
			}
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				//private static final String SQL_UPDATE_updateArchivesInfoFixedFlag = "UPDATE %1$s SET FixedFlag=? WHERE NBXH=?";
				
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				localSql = String.format(SQL_UPDATE_updateArchivesInfoFixedFlag, archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, true,NBXH);
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
	public boolean updateContentIDText(int contentID, String contentIDText, int NBXH, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		// 具体档案分类对应的档案信息工作表名
		String archivesInfoWorkingTableName = "";
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
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
			}

			// 检查档案分类对应的工作表名称是否有值
			if (pFlag)
			{
				if (checkTableName(archivesType, pErrInfo) == false)
				{
					pFlag = false;
				}
				else
				{
					// 保存当前分类的档案归档工作表
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}

			if (pFlag) {
				if (NBXH == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("要修复的档案内部序号非法为0");
				}
			}
			
			// 执行SQL语句
			if (pFlag)
			{
				pErrPos = 2;
				//private static final String SQL_UPDATE_updateArchivesInfoFixedFlag = "UPDATE %1$s SET FixedFlag=? WHERE NBXH=?";
				
				// 将工作表名动态构建到SQL语句中去，注意jdbc不支持select top ? from ?的动态构建，必须自行动态构建好SQL语句字符串
				String sql = "UPDATE %1$s SET ContentID=?,ContentIDText=? WHERE NBXH=?";
				localSql = String.format(sql , archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, contentID,contentIDText,NBXH);
				
				sql = "UPDATE %1$s SET ContentID=?,ContentIDText=? WHERE ParentNBXH=?";
				localSql = String.format(sql , archivesInfoWorkingTableName);
				getJdbcTemplate().update(localSql, contentID,contentIDText,NBXH);
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
	public boolean updateSubArchivesID(int parentNBXH, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String archivesInfoWorkingTableName = "";
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
					archivesInfoWorkingTableName = archivesType.getArchivesInfoTables().get(EnumArchivesInfoTableType.档案归档工作表).getTableName();
				}
			}
			
			//执行SQL语句
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate nJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				SqlParameterSource parameterSource = new MapSqlParameterSource()
				                                         .addValue("NBXH", parentNBXH);
				
				String sql = "UPDATE T1 SET ContentID=T2.ContentID,ContentIDText=T2.ContentIDText FROM %1$s T1,%1$s T2 WHERE T1.ParentNBXH=:NBXH AND T2.NBXH=:NBXH AND T1.ArchivesID IS NULL";
				sql = String.format(sql, archivesInfoWorkingTableName);
				nJdbcTemplate.update(sql, parameterSource);
				
				sql = "UPDATE %1$s SET ArchivesID=%2$s WHERE ParentNBXH=:NBXH AND ArchivesID IS NULL";
				sql = String.format(sql, archivesInfoWorkingTableName,archivesType.getSubArchivesIDExpression());
				nJdbcTemplate.update(sql, parameterSource);
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
