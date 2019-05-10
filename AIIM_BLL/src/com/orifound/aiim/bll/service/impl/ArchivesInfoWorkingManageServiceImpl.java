/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.ICurrentContentIDManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.EnumWorkingUserIDType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.IntegerEx;

/**
 * 档案工作表的档案信息管理服务实现类
 *
 */
public class ArchivesInfoWorkingManageServiceImpl implements IArchivesInfoWorkingManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesInfoWorkingManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesInfoWorkingManageServiceImpl(IArchivesInfoWorkingDao archivesInfoWorkingDao)
	{
		this.archivesInfoWorkingDao = archivesInfoWorkingDao;
	}
	
	/**
	 * 档案归档工作表的数据访问对象
	 */
	private IArchivesInfoWorkingDao archivesInfoWorkingDao = null;

	/**
	 * 获取属性值：档案归档工作表的数据访问对象
	 * @return 档案归档工作表的数据访问对象
	 */
	public IArchivesInfoWorkingDao getArchivesInfoWorkingDao()
	{
		return archivesInfoWorkingDao;
	}

	/**
	 * 设置属性值：档案归档工作表的数据访问对象
	 * @param entityDao 档案归档工作表的数据访问对象
	 */
	public void setArchivesInfoWorkingDao(IArchivesInfoWorkingDao archivesInfoWorkingDao)
	{
		this.archivesInfoWorkingDao = archivesInfoWorkingDao;
	}
	
	/**
	 * 档案归档过程信息管理服务对象
	 */
	private IArchivesInfoWorkProcedureManageService archivesInfoWorkProcedureManageService = null;

	/**
	 * 设置属性值：档案归档过程信息管理服务对象
	 * @param archivesInfoWorkProcedureManageService 档案归档过程信息管理服务对象
	 */
	public void setArchivesInfoWorkProcedureManageService(IArchivesInfoWorkProcedureManageService archivesInfoWorkProcedureManageService)
	{
		this.archivesInfoWorkProcedureManageService = archivesInfoWorkProcedureManageService;
	}

	/**
	 * 获取属性值：档案归档过程信息管理服务对象
	 * @return 档案归档过程信息管理服务对象
	 */
	public IArchivesInfoWorkProcedureManageService getArchivesInfoWorkProcedureManageService()
	{
		return archivesInfoWorkProcedureManageService;
	}
	
	/**
	 * 当前案卷号信息管理服务对象
	 */
	private ICurrentContentIDManageService currentContentIDManageService = null;

	/**
	 * 设置属性值：当前案卷号信息管理服务对象
	 * @param currentContentIDManageService 当前案卷号信息管理服务对象
	 */
	public void setCurrentContentIDManageService(ICurrentContentIDManageService currentContentIDManageService)
	{
		this.currentContentIDManageService = currentContentIDManageService;
	}

	/**
	 * 获取属性值：当前案卷号信息管理服务对象
	 * @return 当前案卷号信息管理服务对象
	 */
	public ICurrentContentIDManageService getCurrentContentIDManageService()
	{
		return currentContentIDManageService;
	}
	
	/**
	 * 检查档案归档工作表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesInfoWorking(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesInfoWorkingDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案归档工作表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}
	
	/**
	 * 检查相关的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查是否有进行当前案卷号信息业务逻辑对象的依赖注入
			pErrPos = 1;
			if (currentContentIDManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("当前案卷号信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			//检查是否有进行档案归档过程信息业务逻辑对象的依赖注入
			pErrPos = 2;
			if (archivesInfoWorkProcedureManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案归档过程信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}
	
	/**
	 * 设置档案管理工作人员编号信息<br>
	 * 直接对指定的档案信息的对应的用户编号属性赋值
	 * @param userID 档案管理工作人员编号
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForUserID(int userID,ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查用户编号是否有值
			pErrPos = 1;
			if (userID<=0)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案管理的工作人员编号非法为空。");
			}

			//根据档案所处的工作流状态，确定如何赋值工作人员编号
			if (pFlag)
			{
				if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案的工作流状态信息非法为空。");
				}
				else if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.著录完成 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.提交送审完成 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.确认进行实物档案的馆外移交) 
				{
					archivesInfo.setUserID1(userID);
				}
				else if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.业务指导室著录审核打回 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.业务指导室著录审核通过 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.业务指导室接收审核打回 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.业务指导室接收审核通过 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.确认进行实物档案的馆内移交) 
				{
					archivesInfo.setUserID2(userID);
				}
				else if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案管理室接收审核打回 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案管理室接收审核通过 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案上架入库完成 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案被预约利用 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案被查档利用 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案被借阅利用 ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.档案已归还) 
				{
					archivesInfo.setUserID3(userID);
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 获取指定行字段值对象的数据源项编号对应的的文本值
	 * @param dataItemName 数据项名称
	 * @param dataSourceID 数据源编号
	 * @param dataSourceName 数据源名称
	 * @param dataSourceItemID 数据源项编号
	 * @param dataSourceItemText 返回获取成功的数据源项文本
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getDataSourceItemText(String dataItemName, int dataSourceID,String dataSourceName,int dataSourceItemID,StringBuilder dataSourceItemText, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 7;
			if (dataSourceID<=0)
			{
				//pFlag = false;
				//pErrInfo.getContent().append(dataItemName+" 数据项的数据源未设置。");
			}
			else 
			{
				if (CommonUtil.getSystemInitializer().getDataSources().containsKey(dataSourceID)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统数据源字典中不存在 "+dataSourceName+" 数据源。");
				}
			}
			
			//计算对应数据源项
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getDataSources().get(dataSourceID).getDataSourceItems()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append(dataSourceName+" 数据源的项成员集合非法为空。");
				}
				else 
				{
					if (CommonUtil.getSystemInitializer().getDataSources().get(dataSourceID).getDataSourceItems().containsKey(dataSourceItemID)==false)
					{
						pFlag = false;
						pErrInfo.getContent().append(dataSourceName+" 数据源的项成员中不存在编号为 "+dataSourceItemID+" 的项。");
					}
					else 
					{
						//返回数据源项编号对应的文本值
						dataSourceItemText.append(CommonUtil.getSystemInitializer().getDataSources().get(dataSourceID).getDataSourceItems().get(dataSourceItemID).getName());
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 设置保管期截止年度<br>
	 * 根据档案形成年度和保管期限自动计算并设置其保管期截止年度
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForRetentionEndYear(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int retentionYears=0;//保管期限编号对应的实际保管年限

		try
		{
			//检查行记录字段集合是否为空
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息的行记录字段集合非法为空。");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的行记录字段集合非法为空。");
				}
			}
			
			//检查档案形成年度是否为0
			if (pFlag)
			{
				if (archivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成年度非法为0");
				}
			}
			
			//检查保管期限字典信息
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getRetentionPeriods()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化器中的保管期限字典信息非法为空，请先初始化。");
				}
			}

			//根据保管期限编号计算保管年限
			if (pFlag)
			{
				if (archivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的保管期限编号非法为空。");
				}
				//计算保管期限编号对应的实际保管年限
				else
				{
					pErrPos = 2;
					if (CommonUtil.getSystemInitializer().getRetentionPeriods().containsKey(archivesInfo.getRetentionPeriodID())==false)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统不存在保管期限编号为 "+archivesInfo.getRetentionPeriodID()+" 的定义。");
					}
					else 
					{
						retentionYears=CommonUtil.getSystemInitializer().getRetentionPeriods().get(archivesInfo.getRetentionPeriodID()).getTotalYears();
					}
				}
			}
			
			//根据档案形成年度和保管年限自动计算并设置其保管期截止年度
			if (pFlag)
			{
				pErrPos = 3;
				int retentionEndYear=archivesInfo.getFormationYear()+retentionYears;
				archivesInfo.setRetentionEndYear(retentionEndYear);
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 设置部分数据项字段值对应的文本串字段值<br>
	 * 例如保管期限文本串、档案密级文本串、形成部门文本串等等<br>
	 * 注意：不包括案卷号格式化文本串、卷内文件序号格式化文本串<br>
	 * 调用该函数之前应该先对档案信息进行了完整性检查处理
	 * @param archivesType 档案所属的档案分类信息
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForAssociateDataItem(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//检查行记录字段集合是否为空
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
				}
			}
			
			//检查系统初始化器下面的数据源集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getDataSources()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的数据源集合非法为空，请先进行系统初始化操作。");
				}
			}
			
			//循环遍历处理有关联对应文本字段的数据项，计算其对应的文本字段值并能进行赋值处理
			if (pFlag)
			{
				for (FieldValue item : archivesInfo.getRowFieldsValues().values())
				{
					//设置档案分类编码字段值
					if (item.getSystemDataItemType()==EnumSystemDataItem.档案分类编号)
					{
						pErrPos = 6;
						archivesInfo.setArchivesTypeCode(archivesType.getFullCode());
					}
					
					//设置保管期限文本字段值
					else if (item.getSystemDataItemType()==EnumSystemDataItem.保管期限编号)
					{
						pErrPos = 7;
						StringBuilder dataSourceItemText=new StringBuilder();
						if (getDataSourceItemText("保管期限编号", item.getDataSourceID(), "保管期限", archivesInfo.getRetentionPeriodID(), dataSourceItemText, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"获取保管期限编号对应的文本失败: ");
						}
						else
						{
							//设置保管期限文本值
							archivesInfo.setRetentionPeriodText(dataSourceItemText.toString());
						}
					}
					
					//设置档案密级文本字段值
					else if (item.getSystemDataItemType()==EnumSystemDataItem.档案密级编号)
					{
						pErrPos = 8;
						StringBuilder dataSourceItemText=new StringBuilder();
						if (getDataSourceItemText("档案密级编号", item.getDataSourceID(), "档案密级", archivesInfo.getSecrecyID(), dataSourceItemText, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"获取档案密级编号对应的文本失败: ");
						}
						else 
						{
							//设置档案密级文本值
							archivesInfo.setSecrecyText(dataSourceItemText.toString());
						}
					}
					
					//设置档案形成部门文本字段值
					else if (item.getSystemDataItemType()==EnumSystemDataItem.档案形成部门编号)
					{
						pErrPos = 9;
						StringBuilder dataSourceItemText=new StringBuilder();
						if (getDataSourceItemText("档案形成部门编号", item.getDataSourceID(), "档案形成部门", archivesInfo.getFormationDepartmentID(), dataSourceItemText, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"获取档案形成部门编号对应的文本失败: ");
						}
						else 
						{
							//设置档案形成部门名称文本值
							archivesInfo.setFormationDepartment(dataSourceItemText.toString());
						}
					}
					
					//如果不是上述系统固有数据源字段，并且存在相关联的文本字段，则直接使用数据源获取文本值
					else if (item.getAssociateTextColumnName()!=null)
					{
						if (item.getAssociateTextColumnName().length()>0)
						{
							//如果该数据项有关联的文本字段，但该数据项的值为null，需要容错提示，无法计算其对应的文本字段值
							if (item.getValue()==null)
							{
								pFlag = false;
								pErrInfo.getContent().append("数据项（"+item.getDisplayText()+"）设置了关联的文本字段（"+item.getAssociateTextColumnName()+"），其值却非法为空，无法计算其对应的文本字段值，可能是未对其进行有效的赋值处理或在档案分类数据项字典中未对其设置缺省值。");
							}
							else 
							{
								pErrPos =10;
								StringBuilder dataSourceItemText=new StringBuilder();
								if (getDataSourceItemText(item.getDisplayText(), item.getDataSourceID(), item.getDisplayText(),Integer.valueOf(item.getValue()), dataSourceItemText, pErrInfo)==false)
								{
									pFlag = false;
									pErrInfo.getContent().insert(0,"获取"+item.getDisplayText()+"对应的文本失败: ");
								}
								else 
								{
									//设置对应文本值
									if (archivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName()))
									{
										archivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName()).setValue(dataSourceItemText.toString());
									}
								}
							}
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 设置案卷号对应的格式化文本串字段值<br>
	 * 生成档号时需要调用该函数进行处理
	 * @param archivesType 档案所属的档案分类信息
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	/*private boolean setValueForContentIDText(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//检查行记录字段集合是否为空
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
				}
			}
			
			//检查案卷号属性值是否为0
			if (pFlag)
			{
				if (archivesInfo.getContentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的案卷号非法为0");
				}
			}
			
			//计算案卷号对应的文本字段值并能进行赋值处理
			if (pFlag)
			{
				pErrPos = 2;
				String contentIDText= String.valueOf(archivesInfo.getContentID());
				if (archivesType.getContentIDFormatLength()>0)
				{
					contentIDText=String.format("%0"+String.valueOf(archivesType.getContentIDFormatLength()+"d"), archivesInfo.getContentID());
				}
				archivesInfo.setContentIDText(contentIDText);
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}*/
	
	/**
	 * 设置卷内文件序号对应的格式化文本串字段值<br>
	 * @param archivesType 档案所属的档案分类信息
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForSubContentIDText(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//检查行记录字段集合是否为空
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
				}
			}
			
			//检查卷内文件序号属性值是否为0
			if (pFlag)
			{
				if (archivesInfo.getSubContentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的卷内文件序号非法为0");
				}
			}
			
			//计算卷内文件序号对应的文本字段值并能进行赋值处理
			if (pFlag)
			{
				pErrPos = 2;
				String subContentIDText= String.valueOf(archivesInfo.getSubContentID());
				if (archivesType.getSubContentIDFormatLength()>0)
				{
					subContentIDText=String.format("%0"+String.valueOf(archivesType.getSubContentIDFormatLength()+"d"), archivesInfo.getSubContentID());
				}
				//设置卷内文件序号格式化文本字段值
				archivesInfo.setSubContentIDText(subContentIDText);
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * 新增档案信息时的档案固有数据项的完整性检查与缺省值处理
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkArchivesInfoSystemDataItemsForSave(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查档案信息是否为空
			pErrPos = 1;
			if (archivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息非法为空。");
			}
			
			//检查工作流状态是否为空
			if (pFlag)
			{
				if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的工作流状态非法为空。");
				}
			}
			
			//检查全宗数字编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getFondsID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的全宗数字编号非法为空。");
				}
			}
			
			//检查档案分类信息是否为空
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的档案分类编号非法为0");
				}
			}
			
			//检查保管期限编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的保管期限编号非法为0");
				}
			}

			//检查档案密级编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getSecrecyID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的密级编号非法为0");
				}
			}
			
			//检查档案形成年度是否为空
			if (pFlag)
			{
				if (archivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成年度非法为空。");
				}
			}
			
			//检查档案形成部门编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getFormationDepartmentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成部门编号非法为0");
				}
			}
			
			//所有缺省值都强制显示赋值，赋予缺省值（因为get具体字段值的时候有进行转换处理，其value值不一定真正有值）
			
			//如果案卷号是null，则使用缺省值0，则案卷号文本使用空串（案卷号文本字段不允许为NULL）
			if (pFlag)
			{
				if (archivesInfo.getContentID()==0)
				{
					archivesInfo.setContentID(0);
					archivesInfo.setContentIDText("");
				}
			}
			
			//如果案卷标志是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getParentFlag()==false)
				{
					archivesInfo.setParentFlag(false);
				}
			}
			
			//如果所属案卷的内部序号是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getParentNBXH()==0)
				{
					archivesInfo.setParentNBXH(0);
				}
			}
			
			//如果卷内文件数量是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getSubContentCount()==0)
				{
					archivesInfo.setSubContentCount(0);
				}
			}
			
			//档案页数为null时使用缺省值0替代
			if (pFlag)
			{
				if (archivesInfo.getPageSum()==0)
				{
					archivesInfo.setPageSum(0);
				}
			}

			//档案归档日期为null，则使用缺省值当前时间替代
			if (pFlag)
			{
				if (archivesInfo.getSaveDate()==null)
				{
					archivesInfo.setSaveDate(new Date());
				}
			}
			
			//如果修复标志是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getFixedFlag()==false)
				{
					archivesInfo.setFixedFlag(false);
				}
			}
			
			//如果删除标志是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getDeleteFlag()==false)
				{
					archivesInfo.setDeleteFlag(false);
				}
			}
			
			//如果开放标志是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getPublicFlag()==false)
				{
					archivesInfo.setPublicFlag(false);
				}
			}
			
			//如果UserID2是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getUserID2()==0)
				{
					archivesInfo.setUserID2(0);
				}
			}
			
			//如果UserID3是null，则使用缺省值0
			if (pFlag)
			{
				if (archivesInfo.getUserID3()==0)
				{
					archivesInfo.setUserID3(0);
				}
			}
			
			//检查数据项的值是否等于空串
			
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 更新档案信息时的档案固有数据项的完整性检查
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkArchivesInfoSystemDataItemsForUpdate(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查档案信息是否为空
			pErrPos = 1;
			if (archivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息非法为空。");
			}
			
			//检查全宗编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getFondsID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的全宗数字编号非法为空。");
				}
			}
			
			//检查档案分类信息是否为空
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的档案分类编号非法为0");
				}
			}
			
			//检查内部序号是否有值
			if (pFlag)
			{
				if (archivesInfo.getNBXH()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的内部序号非法为0");
				}
			}
			
			//检查保管期限编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的保管期限编号非法为0");
				}
			}

			//检查档案密级编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getSecrecyID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的密级编号非法为0");
				}
			}
			
			//检查档案形成年度是否为空
			if (pFlag)
			{
				if (archivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成年度非法为空。");
				}
			}
			
			//检查档案形成部门编号是否为空
			if (pFlag)
			{
				if (archivesInfo.getFormationDepartmentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成部门编号非法为0");
				}
			}
			
			//档案页数为null时使用缺省值0替代
			if (pFlag)
			{
				if (archivesInfo.getPageSum()==0)
				{
					archivesInfo.setPageSum(0);
				}
			}

			//档案归档日期为null，则使用缺省值当前时间替代
			if (pFlag)
			{
				if (archivesInfo.getSaveDate()==null)
				{
					archivesInfo.setSaveDate(new Date());
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 检查档案信息各数据项值类型的正确性
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkArchivesInfoDataItemValueType(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查档案信息是否为空
			pErrPos = 1;
			if (archivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息非法为空。");
			}

			//检查数据项字段值集合是否为空
			if (pFlag)
			{
				if (archivesInfo.getRowFieldsValues()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的字段值集合非法为空。");
				}
			}
			
			//遍历字段值集合逐一检查（字符串和文本类型可以不必检查，其他数字，日期类型必须检查）
			if (pFlag)
			{
				for (FieldValue item : archivesInfo.getRowFieldsValues().values())
				{
					//如果是空值则不检查
					if (item.getValue()==null)
					{
						continue;
					}
					
					//有几个特殊数据项不做检查（因为其著录界面表单设计了其隐藏字段，并且可能会赋予空串值，是合法的，程序会容错处理掉）
					if(item.getSystemDataItemType() == EnumSystemDataItem.内部序号  
							||  item.getSystemDataItemType() == EnumSystemDataItem.所属案卷的内部序号
							||  item.getSystemDataItemType() == EnumSystemDataItem.档案页数){
						
						continue;
					}

					//if (item.getColumnDataType() == EnumColumnDataType.字符串 || item.getColumnDataType() == EnumColumnDataType.文本
					if (item.getColumnDataType() == EnumColumnDataType.日期时间)
					{
						if (CommonUtil.isDate(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" 必须是日期值，当前值为: "+item.getValue());
						}
					}
					else if (item.getColumnDataType() == EnumColumnDataType.实数)
					{
						if (CommonUtil.isDouble(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" 必须是数值，当前值为: "+item.getValue());
						}
					}
					else if (item.getColumnDataType() == EnumColumnDataType.布尔值)
					{
						if (CommonUtil.isDouble(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" 必须是0或1，当前值为: "+item.getValue());
						}
					}
					else if (item.getColumnDataType() == EnumColumnDataType.整数)
					{
						if (CommonUtil.isDouble(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" 必须是整数，当前值为: "+item.getValue());
						}
					}
					
					//存在一个检查未通过就跳出循环
					if (pFlag==false)
					{
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 检查档案信息类别是否正确
	 * @param enumArchivesInfoType 档案信息类别
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkArchivesInfoType(EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos =1;
			if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息类别非法为空。");
			}
			else if (enumArchivesInfoType==EnumArchivesInfoType.案卷级档案)
			{
				pErrPos =2;
				archivesInfo.setParentFlag(true);
				if (archivesInfo.getParentNBXH()>0)
				{
					pFlag = false;
					pErrInfo.getContent().append("案卷级档案不应该存在所属案卷的内部序号信息。");
				}
			}
			else if (enumArchivesInfoType==EnumArchivesInfoType.文件级档案)
			{
				pErrPos =3;
				archivesInfo.setParentFlag(false);
				if (archivesInfo.getParentNBXH()>0)
				{
					pFlag = false;
					pErrInfo.getContent().append("文件级档案不应该存在所属案卷的内部序号信息。");
				}
			}
			else if (enumArchivesInfoType==EnumArchivesInfoType.卷内文件)
			{
				pErrPos =4;
				archivesInfo.setParentFlag(false);
				if (archivesInfo.getParentNBXH()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("卷内文件的所属案卷内部序号非法为0");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * 设置工作流状态标志：保存档案归档过程中的工作状态，标志档案归档过程中的指定环节已经处理完成<br>
	 * 业务逻辑：更新档案信息表中的档案工作流状态和工作人员信息，并保存档案归档过程信息
	 * 
	 * @param enumArchivesInfoType 档案信息分类，如文件、案卷
	 * @param pArchivesTypeID 档案分类编号
	 * @param pNBXH 内部序号
	 * @param userID 用户编号
	 * @param enumWorkFlowStatus 工作流状态
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean setFlagForWorkFlow(EnumArchivesInfoType enumArchivesInfoType, int pArchivesTypeID,  int[] pNBXHs, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		EnumWorkingUserIDType enumWorkingUserIDType=null;  //更新那个用户编号字段呢
		ArchivesType archivesType=null; //所属档案分类

		try
		{
			//检查档案信息分类是否为空
			pErrPos = 1;
			/*if (enumArchivesInfoType==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息分类非法为空。");
			}*/
	
			//检查档案分类是否为空
			if (pFlag)
			{
				if (pArchivesTypeID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类编号非法为0");
				}
			}
			
			//检查内部序号是否为空
			if (pFlag)
			{
				if (pNBXHs.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为空。");
				}
			}
			
			//检查用户编号是否为空
			if (pFlag)
			{
				if (userID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案归档工作人员的用户编号非法为0");
				}
			}

			//根据档案所处的工作流状态，确定如何赋值工作人员编号
			if (pFlag)
			{
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
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.checkArchivesType(pArchivesTypeID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(pArchivesTypeID);
				}
			}
			
			//调用DAO接口，更新档案的工作流状态和用户编号信息
			if (pFlag)
			{
				if (archivesInfoWorkingDao.updateWorkFlowUserIDByNBXH(archivesType, pNBXHs, enumWorkingUserIDType, userID, enumWorkFlowStatus, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新档案工作流状态和用户编号信息失败: ");
				}
			}
			
			//如果是案卷档案，则还需要对其卷内文件的工作流状态和用户编号进行同步更新处理
			if (pFlag)
			{
				if (enumArchivesInfoType != null)
				{
					if (enumArchivesInfoType==EnumArchivesInfoType.案卷级档案)
					{
						if (archivesInfoWorkingDao.updateWorkFlowUserIDForChild(archivesType, pNBXHs, enumWorkingUserIDType, userID, enumWorkFlowStatus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "更新卷内文件的档案工作流状态和用户编号信息失败: ");
						}
					}
				}
				else
				{
					if (archivesInfoWorkingDao.updateWorkFlowUserIDForChild(archivesType, pNBXHs, enumWorkingUserIDType, userID, enumWorkFlowStatus, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新卷内文件的档案工作流状态和用户编号信息失败: ");
					}
				}	
			}
			
			//调用档案归档过程信息业务逻辑接口，保存档案归档过程信息
			if (pFlag)
			{
				pErrPos = 9;
				for (int i = 0; i < pNBXHs.length; i++)
				{
					ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure(pNBXHs[i], pArchivesTypeID, userID, enumWorkFlowStatus.getEnumValue());
					if (archivesInfoWorkProcedureManageService.saveArchivesInfoWorkProcedure(archivesInfoWorkProcedure, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "保存档案归档过程信息失败: ");
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findArchivesInfos( int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType,com.orifound.aiim.entity.EnumWorkFlowStatus, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfos( int[] userID, ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType,EnumWorkFlowStatus enumWorkFlowStatus,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息非法为空。");
				}
			}
			
			//检查用户编号是否有值
			if (pFlag)
			{
				if (userID==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("用户编号信息非法为空。");
				}
				else
				{
					if (userID.length==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("用户编号信息非法为空。");
					}
				}
			}
			
			//检查查询条件并进行相关修复处理
			if (pFlag)
			{
				if (CommonUtil.checkArchivesInfoInputQueryConditions(archivesInfoQueryConditions, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "检查查询条件信息失败: ");
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (archivesInfoWorkingDao.find(enumArchivesInfoType, userID, archivesType, enumWorkFlowStatus, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定工作流状态的档案信息失败: ");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findArchivesInfoByNBXH(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查内部序号是否有值
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (archivesInfoWorkingDao.findByNBXH(pNBXH, archivesType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定档案信息失败: ");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#appendArchivesInfos(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean appendArchivesInfos(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#brokeArchivesInfo(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean brokeArchivesInfo(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, int userID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//设置档案分类编号信息
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息为空");
				}
			}
			
			//检查档案分类信ID是否赋值
			if (pFlag) {
				if (archivesType.getID() <=0) {
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息编号没有赋值");
				}
			}
			
			//检查传入的archivesInfos是否为空
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息集合对象没有赋值");
				}
			}
			
			if (pFlag) {
				for (int i = 0; i < archivesInfos.size(); i++) {
					archivesInfos.get(i).setParentNBXH(0);
					archivesInfos.get(i).setContentID(0);
					archivesInfos.get(i).setContentIDText("");
					archivesInfos.get(i).setSubContentID(0);
					archivesInfos.get(i).setSubContentIDText("");
				}
			}
			
			//调用dao更新卷内目录的parentNBXH字段值
			if (pFlag) {
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesType, archivesInfos,userID,EnumWorkFlowStatus.著录完成, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"调用DAO更新卷内文件parentNBXH字段值失败！");
				}
			}
			
			//调用dao删除案卷信息
//			if (pFlag) {
//				if (archivesInfoWorkingDao.batchDelArchives(archivesType, archivesInfos, pErrInfo) == false) {
//					pFlag = false;
//					pErrInfo.getContent().insert(0,"调用DAO删除案卷信息失败！");
//				}
//			}	
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#deleteArchivesInfo(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesInfo(ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			
			//检查档案信息类型是否为空
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息类型非法为空（必需明确删除的档案是案卷级、文件级还是卷内文件档案）。");
				}
			}
			
			//检查档案信息是否为空
			if (pFlag)
			{
				if (archivesInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息非法为空。");
				}
				else 
				{
					//检查内部序号是否有值
					if (archivesInfo.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息的内部序号非法为0");
					}
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//调用DAO接口删除档案信息，包括工作过程信息、原文电子文件信息，记录原文删除记录等，其中删除原文电子文件信息，记录原文删除记录功能尚未实现
			
			//先删除档案工作过程信息
			if (pFlag)
			{
				ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure();
				archivesInfoWorkProcedure.setArchivesTypeID(archivesType.getID());
				archivesInfoWorkProcedure.setNBXH(archivesInfo.getNBXH());
				if (archivesInfoWorkProcedureManageService.deleteArchivesInfoWorkProcedure(enumArchivesInfoType, archivesInfoWorkProcedure, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除档案工作过程信息失败: ");
				}
			}
			
			//TODO: 待实现：删除原文电子文件信息、插入原文电子文件删除记录（确保后台服务能够读取并能删除磁盘文件）
			
			//最后才能够删除档案归档工作表记录
			if (pFlag)
			{
				if (archivesInfoWorkingDao.deleteParentAndChild(archivesType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除档案信息失败: ");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#deleteArchivesInfo(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesInfos(ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查档案信息类型是否为空
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息类型非法为空（必需明确删除的档案是案卷级、文件级还是卷内文件档案）。");
				}
			}
			
			//检查档案信息是否为空
			if (pFlag)
			{
				if (archivesInfos==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息集合非法为空。");
				}
				else 
				{
					//检查内部序号是否有值
					if (archivesInfos.size()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息集合非法为空。");
					}
				}
			}
			
			//调用单个删除的接口方法，循环逐个删除
			if (pFlag)
			{
				for (ArchivesInfo item : archivesInfos)
				{
					if (deleteArchivesInfo(archivesType, enumArchivesInfoType, item, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "删除指定档案信息失败: ");
						break; //有一个出错就跳出循环
					}
				}
			}
			
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findInputCheckNeedArchivesInfos(int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findInputCheckNeedArchivesInfos(int[] deptIDs, ArchivesType archivesType, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//调用DAO查找待著录审核的档案信息
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkingDao.findArchivesInfosByEnumWorkFlowStatus(archivesType,deptIDs, EnumWorkFlowStatus.提交送审完成, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据工作流状态查找档案信息失败");
				}
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findChildArchivesInfosByNBXH(int, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findChildArchivesInfosByNBXH(int pNBXH,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查内部序号是否有值
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//调用DAO接口查找卷内文件
			if (pFlag)
			{
				if (archivesInfoWorkingDao.findChildrenByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定档案信息失败: ");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#combineArchivesInfos(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean combineArchivesInfos(int userID, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			if(archivesInfos == null || archivesInfos.size()<=0){
				pFlag = false;
				pErrInfo.getContent().append("传入的要组卷档案信息集合对象为空，请检查是否赋值");
			}
			
			//调用保存当前案卷信息
			if (pFlag)
			{
				pErrPos = 2;
				if (this.saveArchivesInfo(userID, archivesType, EnumArchivesInfoType.案卷级档案, archivesInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存档案信息失败: ");
				}
			}
			
			if (pFlag) {
				//ArchivesInfo cArchivesInfo = null;
				for (int i = 0; i < archivesInfos.size(); i++) {
					//cArchivesInfo =  archivesInfos.get(i);
					
					//设置parentNBXH
					archivesInfos.get(i).setParentNBXH(archivesInfo.getNBXH());
					archivesInfos.get(i).setContentID(0);
					archivesInfos.get(i).setContentIDText("");
					
					//计算文件号文本
					//IntegerEx maxSubContentID=new IntegerEx();
//					if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, archivesInfo.getNBXH(), maxSubContentID, pErrInfo)==false)
//					{
//						pFlag = false;
//						pErrInfo.getContent().insert(0, "获取最大卷内文件序号值失败: ");
//					}
//					else 
//					{
						//将当前最大卷内文件序号值+1，赋予卷内文件序号属性值
						archivesInfos.get(i).setSubContentID(i+1);
				//	}
					
					if (pFlag) {
						//设置卷内文件序号格式化文本字段值
						if (pFlag)
						{
							if (setValueForSubContentIDText(archivesType, archivesInfos.get(i), pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "设置卷内文件序号格式化文本字段值失败: ");
							}
						}
					}
				}
			}
			
			//更新文件号文本和所属案卷内部序号
			if (pFlag) {
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesType, archivesInfos, userID, EnumWorkFlowStatus.著录完成, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新文件号文本和所属案卷内部序号: ");
				}
			}
			//调用DAO组卷
		/*	if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesInfo.getNBXH(),archivesType, archivesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "组卷失败: ");
				}
			}*/
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#mergeArchivesInfos(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean mergeArchivesInfos(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#saveArchivesInfo(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesInfo(int userID, ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查是否有进行业务逻辑对象的依赖注入
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}
			
			//检查档案信息类别是否为空，并设置案卷标志
			if (pFlag)
			{
				if (checkArchivesInfoType(enumArchivesInfoType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息类别检查失败: ");
				}
			}
			
			//设置档案管理工作人员编号信息
			if (pFlag)
			{
				if (setValueForUserID(userID, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案管理工作人员编号信息失败: ");
				}
			}
			
			//设置档案分类编号信息
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					archivesInfo.setArchivesTypeID(archivesType.getID());
				}
			}
			
			//检查各数据项字段值的类型是否正确，例如整数字段不能够赋予字母字符串类型的值
			if (pFlag)
			{
				if (checkArchivesInfoDataItemValueType(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息数据项字段值类型检查未通过: ");
				}
			}
			
			//检查要保存的档案信息的系统固有数据项字段值的完整性
			if (pFlag)
			{
				pErrPos = 2;
				if (checkArchivesInfoSystemDataItemsForSave(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息不完整: ");
				}
			}
			
			//设置档案保管期截止年度
			if (pFlag)
			{
				if (setValueForRetentionEndYear(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案保管期截止年度失败: ");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.checkArchivesType(archivesInfo.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfo.getArchivesTypeID());
				}
			}
			
			//设置部分数据项关联字段的文本值（包括案卷号格式化文本、卷内文件序号格式化文本、实体分类号、保管期限文本、档案密级文本、档案形成部门名称、以及其他自定义的数据项关联文本字段值）
			if (pFlag)
			{
				pErrPos = 6;
				if (setValueForAssociateDataItem(archivesType,archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置关联数据项字段的文本值失败: ");
				}
			}
			
			//如果是卷内文件需要先计算其所属案卷的最大卷内文件序号，然后+1作为本文件记录的卷内文件序号字段值，并计算卷内文件序号的格式化文本
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.卷内文件)
				{
					pErrPos = 5;
					IntegerEx maxSubContentID=new IntegerEx();
					if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, archivesInfo.getParentNBXH(), maxSubContentID, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取最大卷内文件序号值失败: ");
					}
					else 
					{
						//将当前最大卷内文件序号值+1，赋予卷内文件序号属性值
						archivesInfo.setSubContentID(maxSubContentID.getValue()+1);
					}
				}
			}
			
			//设置卷内文件序号格式化文本字段值
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.卷内文件)
				{
					if (setValueForSubContentIDText(archivesType, archivesInfo, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "设置卷内文件序号格式化文本字段值失败: ");
					}
				}
			}
			
			//调用DAO接口保存当前档案信息
			if (pFlag)
			{
				pErrPos = 8;
				if (archivesInfoWorkingDao.save(archivesType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存档案信息失败: ");
				}
			}
			
			//如果当前档案是卷内文件，那么需要同步更新其所属案卷的卷内文件数量、档案页数字段值
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.卷内文件)
				{
					pErrPos = 9;
					if (archivesInfoWorkingDao.updateSubContentCountAndPageSum(archivesType, archivesInfo.getParentNBXH(), pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新所属案卷的卷内文件数量和总页数失败: ");
					}
				}
			}
			
			//调用档案归档过程信息业务逻辑接口，保存档案归档过程信息
			if (pFlag)
			{
				pErrPos = 10;
				ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure(archivesInfo.getNBXH(), archivesInfo.getArchivesTypeID(), archivesInfo.getUserID1(), archivesInfo.getWorkFlowStatus().getEnumValue());
				if (archivesInfoWorkProcedureManageService.saveArchivesInfoWorkProcedure(archivesInfoWorkProcedure, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存档案归档过程信息失败: ");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#updateArchivesInfo(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesInfo(ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查是否有进行业务逻辑对象的依赖注入
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}
			
			//检查档案信息类别是否为空，并设置案卷标志
			if (pFlag)
			{
				if (checkArchivesInfoType(enumArchivesInfoType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息类别检查失败: ");
				}
			}
			
			//设置档案分类编号信息
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					archivesInfo.setArchivesTypeID(archivesType.getID());
				}
			}
			
			//检查各数据项字段值的类型是否正确，例如整数字段不能够赋予字母字符串类型的值
			if (pFlag)
			{
				if (checkArchivesInfoDataItemValueType(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息数据项字段值类型检查未通过: ");
				}
			}
			
			//检查要保存的档案信息的系统固有数据项字段值的完整性
			if (pFlag)
			{
				pErrPos = 2;
				if (checkArchivesInfoSystemDataItemsForUpdate(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息不完整: ");
				}
			}
			
			//设置档案保管期截止年度
			if (pFlag)
			{
				if (setValueForRetentionEndYear(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案保管期截止年度失败: ");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.checkArchivesType(archivesInfo.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfo.getArchivesTypeID());
				}
			}
			
			//设置部分数据项关联字段的文本值（包括实体分类号、保管期限文本、档案密级文本、档案形成部门名称、以及其他自定义的数据项关联文本字段值）
			if (pFlag)
			{
				pErrPos = 5;
				if (setValueForAssociateDataItem(archivesType,archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置关联数据项字段的文本值失败: ");
				}
			}
			
			//调用DAO接口更新当前档案信息
			if (pFlag)
			{
				pErrPos = 7;
				if (archivesInfoWorkingDao.update(archivesType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存档案信息失败: ");
				}
			}
			
			//如果当前档案是卷内文件，那么需要同步更新其所属案卷的卷内文件数量、档案页数字段值
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.卷内文件)
				{
					pErrPos = 8;
					if (archivesInfoWorkingDao.updateSubContentCountAndPageSum(archivesType, archivesInfo.getParentNBXH(), pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "更新所属案卷的卷内文件数量和总页数失败: ");
					}
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#separateArchivesInfos(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean separateArchivesInfos(ArchivesType archivesType, ArchivesInfo parentArchivesInfo, List<ArchivesInfo> childArchivesInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#splitArchivesInfos(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean splitArchivesInfos(ArchivesType archivesType, ArchivesInfo parentArchivesInfo, List<ArchivesInfo> childArchivesInfos, ArchivesInfo desArchivesInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#submitToInputCheck(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean submitToInputCheck(int userID, ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int[] pArrayNBXHs=null;

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查是否有进行业务逻辑对象的依赖注入
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}
			
			//计算内部序号数组
			if (pFlag)
			{
				pArrayNBXHs=new int[archivesInfos.size()];
				int i=0;
				for (ArchivesInfo item : archivesInfos)
				{
					pArrayNBXHs[i]=item.getNBXH();
					i=i+1;
				}
			}
			
			//设置提交送审工作流状态
			if (pFlag)
			{
				pErrPos = 2;
				if (setFlagForWorkFlow(enumArchivesInfoType, archivesType.getID(), pArrayNBXHs, userID, EnumWorkFlowStatus.提交送审完成, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案工作流状态失败: ");
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#statInputCheckNeedCount(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean statInputCheckNeedCount(int[] userID, Map<Integer, ArchivesTypeEx> archivesTypeEx, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#inputCheckPass(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean inputCheckPass(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//设置档案信息工作流为著录审核
			if (pFlag) {	
				if (this.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()}, userID, EnumWorkFlowStatus.业务指导室著录审核通过, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流为著录审核完成失败");
				}
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#inputCheckBack(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, java.lang.String, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean inputCheckBack(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, String backReason, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;

			//写打回原因
			if (pFlag) {	
				if (archivesInfoWorkingDao.updateArchivesBackReason(archivesInfo.getNBXH(),archivesType,backReason,pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新档案信息打回原因失败");
				}
			}
			
			//设置档案信息工作流为著录审核
			if (pFlag) {	
				if (this.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()},userID, EnumWorkFlowStatus.业务指导室著录审核打回, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"设置档案工作流为著录审核完成失败");
				}
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#statInputCheckResult(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean statInputCheckResult(int[] userID, Map<Integer, ArchivesTypeEx> archivesTypeEx, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findInputCheckPassArchivesInfos(int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findInputCheckPassArchivesInfos(int[] userID, ArchivesType archivesType, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findInputCheckBackArchivesInfos(int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findInputCheckBackArchivesInfos(int[] userID, ArchivesType archivesType, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#fixArchivesInfoForInputCheckBack(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean fixArchivesInfoForInputCheckBack(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#fixArchivesInfoForPaperCheckBack(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean fixArchivesInfoForPaperCheckBack(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArchivesInfoFixedFlag(ArchivesType archivesType, int NBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;

			//写打回原因
			if (pFlag) {	
				if (archivesInfoWorkingDao.updateArchivesInfoFixedFlag(archivesType, NBXH, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新档案信息修复标志失败");
				}
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
	public boolean deleteParentAndChild(ArchivesType archivesType, int nBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
				archivesInfo.setNBXH(nBXH);
				if (archivesInfoWorkingDao.delete(archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"删除档案信息失败： ");
				}
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
	public boolean insertFileToArchives(int userID, ArchivesType archivesType, int parentNBXH, int[] nBXHS, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<ArchivesInfo> archivesInfos = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			
			IntegerEx maxSubContentID=new IntegerEx();
			if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, parentNBXH, maxSubContentID, pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "获取最大卷内文件序号值失败: ");
			}
			
			if (pFlag) {	
				archivesInfos = new ArrayList<ArchivesInfo>();
				ArchivesInfo archivesInfo = null;
				for (int i = 0; i < nBXHS.length; i++) {
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(nBXHS[i]);
					//设置parentNBXH
					archivesInfo.setParentNBXH(parentNBXH);
					archivesInfo.setContentID(0);
					archivesInfo.setContentIDText("");
					
					//计算文件号文本
//					IntegerEx maxSubContentID=new IntegerEx();
//					if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, parentNBXH, maxSubContentID, pErrInfo)==false)
//					{
//						pFlag = false;
//						pErrInfo.getContent().insert(0, "获取最大卷内文件序号值失败: ");
//					}
//					else 
//					{
//						//将当前最大卷内文件序号值+1，赋予卷内文件序号属性值
						archivesInfo.setSubContentID(maxSubContentID.getValue()+1);
//					}
					
					if (pFlag) {
						//设置卷内文件序号格式化文本字段值
						if (pFlag)
						{
							if (setValueForSubContentIDText(archivesType, archivesInfo, pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "设置卷内文件序号格式化文本字段值失败: ");
							}
						}
					}
					archivesInfos.add(archivesInfo);
					maxSubContentID.setValue(maxSubContentID.getValue()+1);
				}
			}
			
			//更新文件号文本和所属案卷内部序号
			if (pFlag) {
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesType, archivesInfos, userID, EnumWorkFlowStatus.著录完成, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新文件号文本和所属案卷内部序号: ");
				}
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
}
