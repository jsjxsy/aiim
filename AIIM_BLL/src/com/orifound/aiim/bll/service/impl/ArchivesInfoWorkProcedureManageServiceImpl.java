/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkProcedureDao;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案工作工程信息管理服务实现类
 *
 */
public class ArchivesInfoWorkProcedureManageServiceImpl implements IArchivesInfoWorkProcedureManageService
{
	/**
	 * 构造函数
	 */
	public ArchivesInfoWorkProcedureManageServiceImpl()
	{

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesInfoWorkProcedureManageServiceImpl(IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao)
	{
		this.archivesInfoWorkProcedureDao = archivesInfoWorkProcedureDao;
	}
	
	/**
	 * 档案工作过程信息表的数据访问对象
	 */
	private IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao = null;

	/**
	 * 获取属性值：档案工作过程信息表的数据访问对象
	 * @return 档案工作过程信息表的数据访问对象
	 */
	public IArchivesInfoWorkProcedureDao getArchivesInfoWorkProcedureDao()
	{
		return archivesInfoWorkProcedureDao;
	}

	/**
	 * 设置属性值：档案工作过程信息表的数据访问对象
	 * @param archiveInfoWorkProcedureDao 档案工作过程信息表的数据访问对象
	 */
	public void setArchivesInfoWorkProcedureDao(IArchivesInfoWorkProcedureDao archivesInfoWorkProcedureDao)
	{
		this.archivesInfoWorkProcedureDao = archivesInfoWorkProcedureDao;
	}
	
	
	
	/**
	 * 检查档案工作过程信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForArchivesInfoWorkProcedure(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try
		{
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesInfoWorkProcedureDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案工作过程信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService#saveArchivesInfoWorkProcedure(com.orifound.aiim.entity.ArchivesInfoWorkProcedure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesInfoWorkProcedure(ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesType archivesType=null;//所属档案分类信息

		try
		{
			pErrPos = 1;
			//检查是否有进行档案工作工程信息的BLL依赖注入
			if (checkDaoInjectionForArchivesInfoWorkProcedure(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案工作过程信息的完整性
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案工作过程信息非法为空。");
				}
				else
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的内部序号非法为0");
					}
					else if (archivesInfoWorkProcedure.getArchivesTypeID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的档案分类编号非法为0");
					}
					else if (archivesInfoWorkProcedure.getUserID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的用户编号非法为0");
					}
					else if (archivesInfoWorkProcedure.getWorkFlowStatus()==EnumWorkFlowStatus.NONE)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的工作流状态非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.checkArchivesType(archivesInfoWorkProcedure.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfoWorkProcedure.getArchivesTypeID());
				}
			}

			//调用DAO接口保存当前档案归档过程信息
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesInfoWorkProcedureDao.save(archivesType,archivesInfoWorkProcedure, pErrInfo)==false)
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService#findArchivesInfoWorkProcedureByNBXH(com.orifound.aiim.entity.ArchivesType,int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoWorkProcedureByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行档案工作工程信息的BLL依赖注入
			if (checkDaoInjectionForArchivesInfoWorkProcedure(pErrInfo) == false)
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
			}
			
			//检查内部序号是否为0
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案内部序号非法为0");
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.checkArchivesType(archivesType.getID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//调用DAO接口保存当前档案归档过程信息
			if (pFlag)
			{
				pErrPos = 3;
				if (archivesInfoWorkProcedureDao.findByNBXH(archivesType, pNBXH, archivesInfoWorkProcedures, pErrInfo)==false)
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

	@Override
	public boolean deleteArchivesInfoWorkProcedure(EnumArchivesInfoType enumArchivesInfoType,ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesType archivesType=null;//所属档案分类信息

		try
		{
			pErrPos = 1;
			//检查是否有进行档案工作工程信息的BLL依赖注入
			if (checkDaoInjectionForArchivesInfoWorkProcedure(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案信息分类是否为空
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息分类非法为空。");
				}
			}
			
			//检查档案工作过程信息的完整性
			if (pFlag)
			{
				if (archivesInfoWorkProcedure==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案工作过程信息非法为空。");
				}
				else
				{
					if (archivesInfoWorkProcedure.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的内部序号非法为0");
					}
					else if (archivesInfoWorkProcedure.getArchivesTypeID()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案工作过程信息的档案分类编号非法为0");
					}
				}
			}
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.checkArchivesType(archivesInfoWorkProcedure.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfoWorkProcedure.getArchivesTypeID());
				}
			}

			//调用DAO接口进行删除处理
			if (pFlag)
			{
				//如果是案卷级档案则调用案卷连同卷内文件工作过程信息一并删除的DAO接口
				pErrPos = 3;
				if (enumArchivesInfoType==EnumArchivesInfoType.案卷级档案)
				{
					if (archivesInfoWorkProcedureDao.deleteForParentArchives(archivesType, archivesInfoWorkProcedure, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "删除案卷档案的归档过程信息失败: ");
					}
				}
				//如果是文件级档案，则调用删除单一文件的工作过程信息的DAO接口
				else
				{
					if (archivesInfoWorkProcedureDao.deleteForSingleArchives(archivesType, archivesInfoWorkProcedure, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "删除文件档案的归档过程信息失败: ");
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
}