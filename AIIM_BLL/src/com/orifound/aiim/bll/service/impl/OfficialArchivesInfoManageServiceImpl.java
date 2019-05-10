package com.orifound.aiim.bll.service.impl;

import java.util.Date;
import java.util.List;


import com.orifound.aiim.bll.service.IOfficialArchivesInfoManageService;
import com.orifound.aiim.bll.util.CommonUtil;
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

public class OfficialArchivesInfoManageServiceImpl
		implements IOfficialArchivesInfoManageService {
	/**
	 * 公文档案登记表 的数据访问对象
	 */
	private IOfficialArchivesInfoDao officialArchivesInfoDao = null;

	/**
	 * 获取属性值：公文档案登记表 的数据访问对象
	 * 
	 * @return 公文档案登记表 的数据访问对象
	 */
	public IOfficialArchivesInfoDao getOfficialArchivesInfoDao() {
		return officialArchivesInfoDao;
	}

	/**
	 * 设置属性值：公文档案登记表 的数据访问对象
	 * 
	 * @param officialArchivesInfoDao
	 *            公文档案登记表 的数据访问对象
	 */
	public void setOfficialArchivesInfoDao(
			IOfficialArchivesInfoDao officialArchivesInfoDao) {
		this.officialArchivesInfoDao = officialArchivesInfoDao;
	}

	/**
	 * 构造函数
	 */
	public OfficialArchivesInfoManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public OfficialArchivesInfoManageServiceImpl(
			IOfficialArchivesInfoDao officialArchivesInfoDao) {
		this.officialArchivesInfoDao = officialArchivesInfoDao;
	}

	/**
	 * 检查OfficialArchivesInfo的DAO依赖注入（DAO Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForOfficialArchivesInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (officialArchivesInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"OfficialArchivesInfo的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	/**
	 * 检查OfficialArchivesInfo的DAO依赖注入（DAO Depandency Injection）
	 * @officialArchivesType 公文档案类型
	 * @enumOfficialArchivesInfoTableType 公文档案主表信息的类型枚举
	 * @officialArchivesInfo 公文档案登记表实体类
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean deleteOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			
			//检查档案信息类型是否为空
			if (pFlag)
			{
				if (enumOfficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息类型非法为空（必需明确删除的档案是案卷级、文件级还是卷内文件档案）。");
				}
			}
			
			//检查档案信息是否为空
			if (pFlag)
			{
				if (officialArchivesInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息非法为空。");
				}
				else 
				{
					//检查内部序号是否有值
					if (officialArchivesInfo.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息的内部序号非法为0");
					}
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().size() == 0)
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
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+officialArchivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			
			//TODO: 待实现：删除原文电子文件信息、插入原文电子文件删除记录（确保后台服务能够读取并能删除磁盘文件）
			
			//最后才能够删除档案归档工作表记录
			if (pFlag)
			{
				if (officialArchivesInfoDao.deleteParentAndChild(officialArchivesType, officialArchivesInfo, pErrInfo)==false)
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

	@Override
	public boolean findOfficialArchivesInfoByID(int pID,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findOfficialArchivesInfos(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo,List<OfficialArchivesInfo> officialArchivesInfos,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
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
			
			
			//检查查询条件并进行相关修复处理
			if (pFlag)
			{
				if (CommonUtil.checkOfficialArchivesInfoInputQueryConditions(officialArchivesInfoQueryConditions, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "检查查询条件信息失败: ");
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的树状档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().size() == 0)
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
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+officialArchivesType.getID()+" 的档案分类");
				}
				else
				{
					//更新档案分类的引用
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (officialArchivesInfoDao.find(enumOfficialArchivesInfoTableType, officialArchivesType, officialArchivesInfoQueryConditions, dataPageInfo, officialArchivesInfos, pErrInfo)==false)
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

	/**
	 *@userID 用近的ID号
     *@officialArchivesType 公文文档类型
	 *@enumOfficialArchivesInfoType 公文档案主表信息的类型枚举
	 *@officialArchivesInfo 公文档案信息
	 *@ErrInfo pErrInfo 错误处理信息
	 */
	public boolean saveOfficialArchivesInfo(int userID,
			OfficialArchivesType officialArchivesType,
			EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			// 检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialArchivesInfoDAO依赖注入失败:");
			}

			// 设置公文档案管理工作人员编号信息
			if (pFlag) {
				if (setValueForUserID(userID, officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置公文档案管理工作人员编号信息失败: ");
				}
			}

			// 设置公文档案分类编号信息
			if (pFlag) {
				if (officialArchivesInfo.getDocTypeID() <= 0) {
					officialArchivesInfo.setDocTypeID(officialArchivesType
							.getID());
				}
			}

			// 检查各数据项字段值的类型是否正确，例如整数字段不能够赋予字母字符串类型的值
			if (pFlag) {
				if (checkOfficialArchivesInfoDataItemValueType(
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息数据项字段值类型检查未通过: ");
				}
			}

			// 检查要保存的档案信息的系统固有数据项字段值的完整性
			if (pFlag) {
				pErrPos = 2;
				if (checkOfficialArchivesInfoSystemDataItemsForSave(
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案信息不完整: ");
				}
			}

			// 设置档案保管期截止年度
			if (pFlag) {
				pErrPos = 3;
				if (setValueForRetentionEndYear(officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案保管期截止年度失败: ");
				}
			}
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.checkOfficialArchivesType(officialArchivesType.getID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案分类编号合法性检查失败: ");
				}
				else
				{
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}

			// 设置部分数据项关联字段的文本值（包括案卷号格式化文本、卷内文件序号格式化文本、实体分类号、保管期限文本、档案密级文本、档案形成部门名称、以及其他自定义的数据项关联文本字段值）
			if (pFlag) {
				pErrPos = 5;
				if (setValueForAssociateDataItem(officialArchivesType,
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置关联数据项字段的文本值失败: ");
				}
			}

			// 调用DAO接口保存当前档案信息
			if (pFlag) {
				pErrPos = 5;
				
				if (officialArchivesInfoDao.save(officialArchivesType,
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存档案信息失败: ");
				}
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
	 * 检查档案信息类别是否正确
	 * @param enumArchivesInfoType 档案信息类别
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkOfficialArchivesInfoType(EnumOfficialArchivesInfoTableType enumOffficialArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos =1;
			if (enumOffficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.NONE)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文档案信息类别非法为空。");
			}else if (enumOffficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.公文档案登记表)
			{
				pErrPos =3;
//				officialArchivesInfo.setParentFlag(false);
//				if (officialArchivesInfo.getParentNBXH()>0)
//				{
//					pFlag = false;
//					pErrInfo.getContent().append("文件级档案不应该存在所属案卷的内部序号信息。");
//				}
			}
			else if (enumOffficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.公文档案原文信息表)
			{
				pErrPos =4;
//				officialArchivesInfo.setParentFlag(false);
//				if (officialArchivesInfo.getParentNBXH()<=0)
//				{
//					pFlag = false;
//					pErrInfo.getContent().append("卷内文件的所属案卷内部序号非法为0");
//				}
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
	 * 更新公文档案信息时的档案固有数据项的完整性检查
	 * @param archivesInfo 档案信息对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkOfficialArchivesInfoSystemDataItemsForUpdate(OfficialArchivesInfo officialArchivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查档案信息是否为空
			pErrPos = 1;
			if (officialArchivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案信息非法为空。");
			}
			
			
			
			//检查内部序号是否有值
			if (pFlag)
			{
				if (officialArchivesInfo.getNBXH()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的内部序号非法为0");
				}
			}
			
			//检查保管期限编号是否为空
			if (pFlag)
			{
				if (officialArchivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的保管期限编号非法为0");
				}
			}

			//检查档案密级编号是否为空
			if (pFlag)
			{
				if (officialArchivesInfo.getSecrecyID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的密级编号非法为0");
				}
			}
			
			//检查档案形成年度是否为空
			if (pFlag)
			{
				if (officialArchivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成年度非法为空。");
				}
			}
			
			//检查档案形成部门编号是否为空
			if (pFlag)
			{
				if (officialArchivesInfo.getFormationDepartmentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成部门编号非法为0");
				}
			}
			
			//档案页数为null时使用缺省值0替代
			if (pFlag)
			{
				if (officialArchivesInfo.getPageSum()==0)
				{
					officialArchivesInfo.setPageSum(0);
				}
			}

			//档案归档日期为null，则使用缺省值当前时间替代
			if (pFlag)
			{
				if (officialArchivesInfo.getRegDate()==null)
				{
					officialArchivesInfo.setRegDate(new Date());
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
	 * 更新公文档案信息时的档案固有数据项的完整性检查
	 * @param officialArchivesType 公文档案信息对象
	 * @param officialArchivesInfo 公文档案信息对对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean updateOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficalArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//检查公文档案信息类别是否为空，并设置案卷标志
			if (pFlag)
			{
				pErrPos = 2;
				if (checkOfficialArchivesInfoType(enumOfficalArchivesInfoTableType, officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文档案信息类别检查失败: ");
				}
			}
			
			
			//检查各数据项字段值的类型是否正确，例如整数字段不能够赋予字母字符串类型的值
			if (pFlag)
			{
				pErrPos = 3;
				if (checkOfficialArchivesInfoDataItemValueType(officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文档案信息数据项字段值类型检查未通过: ");
				}
			}
			
			//检查要保存的档案信息的系统固有数据项字段值的完整性
			if (pFlag)
			{
				pErrPos = 4;
				if (checkOfficialArchivesInfoSystemDataItemsForUpdate(officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文档案信息不完整: ");
				}
			}
			
			//设置档案保管期截止年度
			if (pFlag)
			{
				pErrPos = 5;
				if (setValueForRetentionEndYear(officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置公文档案保管期截止年度失败: ");
				}
			}
			
			
			//获取档案分类信息
			if (pFlag)
			{
				pErrPos = 6;
				if (CommonUtil.checkOfficialArchivesType(officialArchivesType.getID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "公文档案分类编号合法性检查失败: ");
				}
				else
				{
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			//设置部分数据项关联字段的文本值（包括实体分类号、保管期限文本、档案密级文本、档案形成部门名称、以及其他自定义的数据项关联文本字段值）
			if (pFlag)
			{
				pErrPos = 7;
				if (setValueForAssociateDataItem(officialArchivesType,officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置关联数据项字段的文本值失败: ");
				}
			}
			
			//调用DAO接口更新当前档案信息
			if (pFlag)
			{
				pErrPos = 8;
				if (officialArchivesInfoDao.update(officialArchivesType, officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "保存档案信息失败: ");
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
	 * 检查公文档案信息各数据项值类型的正确性
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkOfficialArchivesInfoDataItemValueType(
			OfficialArchivesInfo officialarchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查公文档案信息是否为空
			pErrPos = 1;
			if (officialarchivesInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("公文档案信息非法为空。");
			}

			// 检查数据项字段值集合是否为空
			if (pFlag) {
				if (officialarchivesInfo.getRowFieldsValues() == null) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的字段值集合非法为空。");
				}
			}

			// 遍历字段值集合逐一检查（字符串和文本类型可以不必检查，其他数字，日期类型必须检查）
			if (pFlag) {
				for (FieldValue item : officialarchivesInfo
						.getRowFieldsValues().values()) {
					// 如果是空值则不检查
					if (item.getValue() == null) {
						continue;
					}

					// 有几个特殊数据项不做检查（因为其著录界面表单设计了其隐藏字段，并且可能会赋予空串值，是合法的，程序会容错处理掉）
					if (item.getSystemDataItemType() == EnumSystemDataItem.内部序号
							|| item.getSystemDataItemType() == EnumSystemDataItem.档案页数) {

						continue;
					}

					// if (item.getColumnDataType() == EnumColumnDataType.字符串 ||
					// item.getColumnDataType() == EnumColumnDataType.文本
					if (item.getColumnDataType() == EnumColumnDataType.日期时间) {
						if (CommonUtil.isDate(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " 必须是日期值，当前值为: "
											+ item.getValue());
						}
					} else if (item.getColumnDataType() == EnumColumnDataType.实数) {
						if (CommonUtil.isDouble(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " 必须是数值，当前值为: "
											+ item.getValue());
						}
					} else if (item.getColumnDataType() == EnumColumnDataType.布尔值) {
						if (CommonUtil.isDouble(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " 必须是0或1，当前值为: "
											+ item.getValue());
						}
					} else if (item.getColumnDataType() == EnumColumnDataType.整数) {
						if (CommonUtil.isDouble(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " 必须是整数，当前值为: "
											+ item.getValue());
						}
					}

					// 存在一个检查未通过就跳出循环
					if (pFlag == false) {
						break;
					}
				}
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
	 * 新增公文档案信息时的档案固有数据项的完整性检查与缺省值处理
	 * 
	 * @param archivesInfo
	 *            档案信息对象
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkOfficialArchivesInfoSystemDataItemsForSave(
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查公文档案信息是否为空
			pErrPos = 1;
			if (officialArchivesInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("公文档案信息非法为空。");
			}
			if (pFlag) {
				if (officialArchivesInfo.getDocNo() == null) {
					pFlag = false;
					pErrInfo.getContent().append("公文档案信息的文号非法为空!");
				}
			}

			// 检查档案分类信息是否为空
			if (pFlag) {
				if (officialArchivesInfo.getDocTypeID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的档案分类编号非法为0");
				}
			}

			// 检查保管期限编号是否为空
			if (pFlag) {
				if (officialArchivesInfo.getRetentionPeriodID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的保管期限编号非法为0");
				}
			}

			// 检查档案密级编号是否为空
			if (pFlag) {
				if (officialArchivesInfo.getSecrecyID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的密级编号非法为0");
				}
			}

			// 检查档案形成年度是否为空
			if (pFlag) {
				if (officialArchivesInfo.getFormationYear() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成年度非法为空。");
				}
			}

			// 检查档案形成部门编号是否为空
			if (pFlag) {
				if (officialArchivesInfo.getFormationDepartmentID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成部门编号非法为0");
				}
			}

			// 所有缺省值都强制显示赋值，赋予缺省值（因为get具体字段值的时候有进行转换处理，其value值不一定真正有值）

			// 档案页数为null时使用缺省值0替代
			if (pFlag) {
				if (officialArchivesInfo.getPageSum() == 0) {
					officialArchivesInfo.setPageSum(0);
				}
			}
			// 档案归档日期为null，则使用缺省值当前时间替代
			if (pFlag) {
				if (officialArchivesInfo.getRegDate() == null) {
					officialArchivesInfo.setRegDate(new Date());
				}
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
	 * 设置部分数据项字段值对应的文本串字段值<br>
	 * 例如保管期限文本串、档案密级文本串、形成部门文本串等等<br>
	 * 注意：不包括案卷号格式化文本串、卷内文件序号格式化文本串<br>
	 * 调用该函数之前应该先对档案信息进行了完整性检查处理
	 * 
	 * @param archivesType
	 *            档案所属的档案分类信息
	 * @param archivesInfo
	 *            档案信息对象
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForAssociateDataItem(
			OfficialArchivesType officialArchivesType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查行记录字段集合是否为空
			pErrPos = 1;
			if (officialArchivesInfo.getRowFieldsValues() == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
			} else {
				if (officialArchivesInfo.getRowFieldsValues().size() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的行记录字段集合非法为空");
				}
			}

			// 检查系统初始化器下面的数据源集合是否有值
			if (pFlag) {
				if (CommonUtil.getSystemInitializer().getDataSources() == null) {
					pFlag = false;
					pErrInfo.getContent().append(
							"系统初始化对象的数据源集合非法为空，请先进行系统初始化操作。");
				}
			}
			// 循环遍历处理有关联对应文本字段的数据项，计算其对应的文本字段值并能进行赋值处理
			if (pFlag) {
				for (FieldValue item : officialArchivesInfo
						.getRowFieldsValues().values()) {
					// 设置档案分类编码字段值
					if (item.getSystemDataItemType() == EnumSystemDataItem.档案分类编号) {
						pErrPos = 6;
						// officialArchivesInfo.setArchivesTypeCode(officialArchivesType.getFullCode());
					}

					// 设置保管期限文本字段值
					else if (item.getSystemDataItemType() == EnumSystemDataItem.保管期限编号) {
						pErrPos = 7;
						StringBuilder dataSourceItemText = new StringBuilder();
						if (getDataSourceItemText("保管期限编号", item
								.getDataSourceID(), "保管期限",
								officialArchivesInfo.getRetentionPeriodID(),
								dataSourceItemText, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent()
									.insert(0, "获取保管期限编号对应的文本失败: ");
						} else {
							// 设置保管期限文本值
							officialArchivesInfo
									.setRetentionPeriodText(dataSourceItemText
											.toString());
						}
					}

					// 设置档案密级文本字段值
					else if (item.getSystemDataItemType() == EnumSystemDataItem.档案密级编号) {
						pErrPos = 8;
						StringBuilder dataSourceItemText = new StringBuilder();
						if (getDataSourceItemText("档案密级编号", item
								.getDataSourceID(), "档案密级",
								officialArchivesInfo.getSecrecyID(),
								dataSourceItemText, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent()
									.insert(0, "获取档案密级编号对应的文本失败: ");
						} else {
							// 设置档案密级文本值
							officialArchivesInfo
									.setSecrecyText(dataSourceItemText
											.toString());
						}// 设置档案形成部门文本字段值
					}else if (item.getSystemDataItemType() == EnumSystemDataItem.档案形成部门编号) {
						pErrPos = 9;
						StringBuilder dataSourceItemText = new StringBuilder();
						if (getDataSourceItemText(
								"档案形成部门编号",
								item.getDataSourceID(),
								"档案形成部门",
								officialArchivesInfo.getFormationDepartmentID(),
								dataSourceItemText, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0,
									"获取档案形成部门编号对应的文本失败: ");
						} else {
							// 设置档案形成部门名称文本值
							officialArchivesInfo
									.setFormationDepartment(dataSourceItemText
											.toString());
						}
					}

					// 如果不是上述系统固有数据源字段，并且存在相关联的文本字段，则直接使用数据源获取文本值
					else if (item.getAssociateTextColumnName() != null) {
						if (item.getAssociateTextColumnName().length() > 0) {
							// 如果该数据项有关联的文本字段，但该数据项的值为null，需要容错提示，无法计算其对应的文本字段值
							if (item.getValue() == null) {
								pFlag = false;
								pErrInfo.getContent().append(
										"数据项（" + item.getDisplayText() + "）设置了关联的文本字段（" + item.getAssociateTextColumnName()
												+ "），其值却非法为空，无法计算其对应的文本字段值，可能是未对其进行有效的赋值处理或在档案分类数据项字典中未对其设置缺省值。");
							} else {
								pErrPos = 10;
								StringBuilder dataSourceItemText = new StringBuilder();
								if (getDataSourceItemText(item.getDisplayText(), item.getDataSourceID(), item.getDisplayText(), Integer.valueOf(item.getValue()),
										dataSourceItemText, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "获取" + item.getDisplayText() + "对应的文本失败: ");
								} else {
									// 设置对应文本值
									if (officialArchivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName())) {
										officialArchivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName()).setValue(dataSourceItemText.toString());
									}
								}
							}
						}
					}
				}
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
			officialArchivesType = null;
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * 设置保管期截止年度<br>
	 * 根据档案形成年度和保管期限自动计算并设置其保管期截止年度
	 * 
	 * @param archivesInfo
	 *            档案信息对象
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForRetentionEndYear(
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int retentionYears = 0;// 保管期限编号对应的实际保管年限

		try {
			// 检查行记录字段集合是否为空
			pErrPos = 1;
			if (officialArchivesInfo.getRowFieldsValues() == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案信息的行记录字段集合非法为空。");
			} else {
				if (officialArchivesInfo.getRowFieldsValues().size() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的行记录字段集合非法为空。");
				}
			}

			// 检查档案形成年度是否为0
			if (pFlag) {
				if (officialArchivesInfo.getFormationYear() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的形成年度非法为0");
				}
			}

			// 检查保管期限字典信息
			if (pFlag) {
				if (CommonUtil.getSystemInitializer().getRetentionPeriods() == null) {
					pFlag = false;
					pErrInfo.getContent().append("系统初始化器中的保管期限字典信息非法为空，请先初始化。");
				}
			}

			// 根据保管期限编号计算保管年限
			if (pFlag) {
				if (officialArchivesInfo.getRetentionPeriodID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("档案信息的保管期限编号非法为空。");
				}
				// 计算保管期限编号对应的实际保管年限
				else {
					pErrPos = 2;
					if (CommonUtil.getSystemInitializer().getRetentionPeriods().containsKey(officialArchivesInfo.getRetentionPeriodID()) == false) {
						pFlag = false;
						pErrInfo.getContent().append("系统不存在保管期限编号为 " + officialArchivesInfo.getRetentionPeriodID() + " 的定义。");
					} else {
						retentionYears = CommonUtil.getSystemInitializer().getRetentionPeriods().get(officialArchivesInfo.getRetentionPeriodID()).getTotalYears();
					}
				}
			}

			// 根据档案形成年度和保管年限自动计算并设置其保管期截止年度
			if (pFlag) {
				pErrPos = 3;
				int retentionEndYear = officialArchivesInfo.getFormationYear()
						+ retentionYears;
				officialArchivesInfo.setRetentionEndYear(retentionEndYear);
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
	 * 获取指定行字段值对象的数据源项编号对应的的文本值
	 * 
	 * @param dataItemName
	 *            数据项名称
	 * @param dataSourceID
	 *            数据源编号
	 * @param dataSourceName
	 *            数据源名称
	 * @param dataSourceItemID
	 *            数据源项编号
	 * @param dataSourceItemText
	 *            返回获取成功的数据源项文本
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean getDataSourceItemText(String dataItemName,
			int dataSourceID, String dataSourceName, int dataSourceItemID,
			StringBuilder dataSourceItemText, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 7;
			if (dataSourceID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append(dataItemName + " 数据项的数据源未设置。");
			} else {
				if (CommonUtil.getSystemInitializer().getDataSources()
						.containsKey(dataSourceID) == false) {
					pFlag = false;
					pErrInfo.getContent().append(
							"系统数据源字典中不存在 " + dataSourceName + " 数据源。");
				}
			}

			// 计算对应数据源项
			if (pFlag) {
				if (CommonUtil.getSystemInitializer().getDataSources().get(
						dataSourceID).getDataSourceItems() == null) {
					pFlag = false;
					pErrInfo.getContent().append(
							dataSourceName + " 数据源的项成员集合非法为空。");
				} else {
					if (CommonUtil.getSystemInitializer().getDataSources().get(
							dataSourceID).getDataSourceItems().containsKey(
							dataSourceItemID) == false) {
						pFlag = false;
						pErrInfo.getContent().append(
								dataSourceName + " 数据源的项成员中不存在编号为 "
										+ dataSourceItemID + " 的项。");
					} else {
						// 返回数据源项编号对应的文本值
						dataSourceItemText.append(CommonUtil
								.getSystemInitializer().getDataSources().get(
										dataSourceID).getDataSourceItems().get(
										dataSourceItemID).getName());
					}
				}
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
	 * 设置公文档案管理工作人员编号信息<br>
	 * 直接对指定的档案信息的对应的用户编号属性赋值
	 * 
	 * @param userID
	 *            公文档案管理工作人员编号
	 * @param officialArchivesInfo
	 *            公文档案信息对象
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setValueForUserID(int userID,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// 检查用户编号是否有值
			pErrPos = 1;
			if (userID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("档案管理的工作人员编号非法为空。");
			}else {
				officialArchivesInfo.setRegUserID(userID);
				System.out.println("user-->id-->"+officialArchivesInfo.getRegUserID());
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
	 * 设置公文档案管理工作人员编号信息<br>
	 * 直接对指定的档案信息的对应的用户编号属性赋值
	 * 
	 * @param pNBXH
	 *            公文档案的内部序号
	 * @param officialArchivesType
	 *            公文档案的类型
	 * @param  officialArchivesInfo
	 *            公文档案信息
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findOfficialArchivesInfoByNBXH(int pNBXH,
			OfficialArchivesType officialArchivesType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查公文档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案分类信息非法为空。");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("公文档案分类编号非法为0");
					}
				}
			}
			
			//检查内部序号是否有值
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("公文档案内部序号非法为0");
				}
			}
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统初始化对象的公文档案分类集合非法为空，请先进行系统初始化操作。");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统初始化对象的公文档案分类集合非法为空，请确定系统数据库中存在档案分类字典信息。");
					}
				}
			}

			//获取公文档案分类信息
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(officialArchivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("系统中不存在编号为 "+officialArchivesType.getID()+" 的公文档案分类");
				}
				else
				{
					//更新档案分类的引用
					officialArchivesType = CommonUtil.getSystemInitializer()
					.getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			//调用DAO接口
			if (pFlag)
			{
				if (officialArchivesInfoDao.findByNBXH(pNBXH, officialArchivesType,
						officialArchivesInfo, pErrInfo)==false)
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

	@Override
	public boolean deleteOfficialArchivesInfos(OfficialArchivesType officialArchivesType, EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,
			List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息非法为空。");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案分类编号非法为0");
					}
				}
			}
			
			//检查档案信息类型是否为空
			if (pFlag)
			{
				if (enumOfficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息类型非法为空（必需明确删除的档案是案卷级、文件级还是卷内文件档案）。");
				}
			}
			
			//检查档案信息是否为空
			if (pFlag)
			{
				if (officialArchivesInfos==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案信息集合非法为空。");
				}
				else 
				{
					//检查内部序号是否有值
					if (officialArchivesInfos.size()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("档案信息集合非法为空。");
					}
				}
			}
			
			//调用单个删除的接口方法，循环逐个删除
			if (pFlag)
			{
				for (OfficialArchivesInfo item : officialArchivesInfos)
				{
					if (deleteOfficialArchivesInfo(officialArchivesType, enumOfficialArchivesInfoTableType, item, pErrInfo)==false)
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

	@Override
	public boolean findAll(OfficialArchivesType offcialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialArchivesInfo管理业务层dao注入失败!");
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (offcialArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("offcialArchivesType非法为空!");
				}
			}
			if (pFlag) {
				if (officialArchivesInfoDao.findAll(offcialArchivesType, officialArchivesInfos, pErrInfo)==false) {
					pFlag=false;
					pErrInfo.getContent().insert(0,"officialArchivesInfoDao查询所有记录失败");
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
