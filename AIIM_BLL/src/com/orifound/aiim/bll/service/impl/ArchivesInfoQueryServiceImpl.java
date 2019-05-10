/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesInfoQueryService;
import com.orifound.aiim.bll.service.IUserDefinedSearchManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IArchivesInfoSavedDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeCountInfo;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * 档案综合查询服务实现类
 *
 */
public class ArchivesInfoQueryServiceImpl implements IArchivesInfoQueryService
{
	
	/**
	 * 构造函数
	 */
	public ArchivesInfoQueryServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ArchivesInfoQueryServiceImpl(IArchivesInfoSavedDao archivesInfoSavedDao) {
		this.archivesInfoSavedDao = archivesInfoSavedDao;
	}
	
	/**
	 * 检查档案归档信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (archivesInfoSavedDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案归档信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}
	
	/**
	 * 档案归档信息表的数据访问对象
	 */
	private IArchivesInfoSavedDao archivesInfoSavedDao = null;

	/**
	 * 获取属性值：档案归档信息表的数据访问对象
	 * @return 档案归档信息表的数据访问对象
	 */
	public IArchivesInfoSavedDao getArchivesInfoSavedDao() {
		return archivesInfoSavedDao;
	}

	/**
	 * 设置属性值：档案归档信息表的数据访问对象
	 * @param archivesInfoSavedDao 档案归档信息表的数据访问对象
	 */
	public void setArchivesInfoSavedDao(IArchivesInfoSavedDao archivesInfoSavedDao) {
		this.archivesInfoSavedDao = archivesInfoSavedDao;
	}
	
	/**
	 * 用户自定义条件查询管理服务类
	 */
	private IUserDefinedSearchManageService userDefinedSearchManageService = null;
		
	public IUserDefinedSearchManageService getUserDefinedSearchManageService() {
		return userDefinedSearchManageService;
	}
	
	public void setUserDefinedSearchManageService(IUserDefinedSearchManageService userDefinedSearchManageService) {
		this.userDefinedSearchManageService = userDefinedSearchManageService;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#findArchivesInfoByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoByBarcode(String archivesBarcode,
			ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#findArchivesInfoByNBXH(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoByNBXH(ArchivesType archivesType,
			int NBXH, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//调用DAO接口
			if (pFlag) {
				if (archivesInfoSavedDao.findByNBXH(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据内部序号查找档案信息失败: ");
				}
			}
			
			System.out.println("-->"+archivesInfo.getSecrecyID());
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#findQueryDataItems(java.util.List, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findQueryDataItems(List<ArchivesType> archivesTypes,LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub425044
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#queryClassified(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
/*
	@Override
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo)
	{
		//数据验证
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StringBuilder querySQL = new StringBuilder(); 
		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForArchivesInfoSavedDao(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypes == null || archivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}
			}
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息未初始化。");
				}
			}
			
			//检查查询条件并进行相关修复处理
			if (pFlag)
			{
				if (CommonUtil.checkArchivesInfoUseQueryConditions(archivesInfoQueryConditions, pErrInfo)==false)
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


			//验证档案分类信息是否在系统中存在
			if (pFlag)
			{
				pErrPos = 5;
				for (ArchivesType archivesType : archivesTypes) {
					if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
					}
				}				
			}
			
			getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo);
			
			//调用DAO接口
			if (pFlag)
			{
				//if (archivesInfoSavedDao.queryClassified(userInfo, archivesType, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo)==false)
				if (archivesInfoSavedDao.queryClassified(userInfo, archivesTypes, querySQL.toString(), dataPageInfo, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "分类查询失败: ");
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
	*/
	
	
	/**
	 * 根据whereSQL语句查询指定档案分类的档案集合<br>
	 * whereSQL格式：and a>b and c=23
	 */
	@Override
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,
			String querySQL,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo)
	{
		//数据验证
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//检查档案类型是否有赋值
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypes == null || archivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("档案分类信息未初始化。");
				}
			}
			
			
			//检查数据分页对象是否为空
			if (pFlag)
			{
				pErrPos =3;
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("数据分页信息未初始化。");
				}
			}		
			
			
			//检查系统初始化器下面的档案分类集合是否有值
			if (pFlag)
			{
				pErrPos = 4;
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

			
			//验证档案分类信息是否在系统中存在
			if (pFlag)
			{
				pErrPos = 5;
				for (ArchivesType archivesType : archivesTypes) {
					if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
					{
						pFlag = false;
						pErrInfo.getContent().append("系统中不存在编号为 "+archivesType.getID()+" 的档案分类");
					}
				}				
			}
			
			
			//调用DAO接口
			if (pFlag)
			{
				pErrPos = 6;
				if (archivesInfoSavedDao.queryClassified(userInfo, archivesTypes, querySQL, dataPageInfo, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "分类查询失败: ");
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
			if (checkInjection(pErrInfo) == false)
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
				if (archivesInfoSavedDao.findChildrenByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo)==false)
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#queryCrossClassified(java.util.List, java.util.List, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean queryCrossClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,List<ArchivesTypeCountInfo> archivesTypeCountInfos,StringBuilder querySQL,ErrInfo pErrInfo){
		boolean pFlag = true;
		IntegerEx countNum =new IntegerEx();		
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false){
				pFlag = false;
			}

			
			//构造SQL查询条件
			if (pFlag) {
				pErrPos = 2;
				 if(getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo)==false){
					 pFlag = false;
					 pErrInfo.getContent().append("构造档案信息著录查询条件的SQL片段失败。");			
				 }
			}
			
			
			//调用DAO数据访问
			if (pFlag) {
				pErrPos = 3;
				for (ArchivesType archivesType : archivesTypes) {
					if(archivesInfoSavedDao.queryCountByClassified(userInfo, archivesType,querySQL, countNum, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("根据查询条件统计" + archivesType.getFullName() + "类的档案数量失败。");
					}else{
						//档案分类的数量统计信息
						ArchivesTypeCountInfo archivesTypeCountInfo = new ArchivesTypeCountInfo();
						archivesTypeCountInfo.setArchivesType(archivesType);
						archivesTypeCountInfo.setCountNum(countNum.getValue());
						archivesTypeCountInfo.setQuerySQL(querySQL.toString());
						if(countNum.getValue()>0){//将有结果集的分类加入到列表中去
							archivesTypeCountInfos.add(archivesTypeCountInfo);
						}						
						System.out.println("跨门类查询：根据查询条件统计出指定分类的档案数量成功！");
						
					}
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
System.out.println("errrorInfo: "+ pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		return pFlag;
	}
	
	
	
	@Override
	public boolean queryCrossClassifiedByKeyWord(UserInfo userInfo, List<ArchivesType> archivesTypes, String keyWord, List<ArchivesTypeCountInfo> archivesTypeCountInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		IntegerEx countNum =new IntegerEx();		
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StringBuilder querySQL = null; 

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//调用DAO数据访问
			if (pFlag) {
				pErrPos = 3;
				for (ArchivesType archivesType : archivesTypes) {
					querySQL = new StringBuilder();
					//获取SQL语句
					getKeyQuerySQL(keyWord, archivesType.getDataItemsForUseQuery(), querySQL, pErrInfo);
					//根据查询条件统计出指定分类的档案数量
					if(archivesInfoSavedDao.queryCountByClassified(userInfo, archivesType, querySQL , countNum, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("根据查询条件统计" + archivesType.getFullName() + "类的档案数量失败。");
					}else{
						//档案分类的数量统计信息
						ArchivesTypeCountInfo archivesTypeCountInfo = new ArchivesTypeCountInfo();
						archivesTypeCountInfo.setArchivesType(archivesType);
						archivesTypeCountInfo.setCountNum(countNum.getValue());
						archivesTypeCountInfo.setQuerySQL(querySQL.toString());
						if(countNum.getValue()>0){//将有结果集的分类加入到列表中去
							archivesTypeCountInfos.add(archivesTypeCountInfo);
						}					
						System.out.println("关键字检索：根据查询条件统计出指定分类的档案数量成功！");
					}
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
System.out.println("errrorInfo: "+ pErrInfo.toString());
			}

			//销毁局部变量
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
			//如果没有数据就返回空串
			if(archivesInfoQueryConditions == null ){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else if(archivesInfoQueryConditions.size()==0){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else{
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
		System.out.println("getSqlForArchivesInfoInputQueryConditions: querySQL:" + querySQL);
		return pFlag;
	}

	
	/**
	 * 获取关键字检索的SQL语句
	 * @param keyWord 用户输入的关键字
	 * @param dataItemsForUseQuery 可用于查询的字段
	 * @param querySQL	返回SQL语句
	 * @param pErrInfo 返回错误信息
	 * @return
	 */
	private boolean getKeyQuerySQL(String keyWord,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery,StringBuilder querySQL, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			//如果输入为空，则查询所有档案，querySQL也为空串
			if("".equals(keyWord.trim())){
				return true;
			}
			
			pErrPos = 1;
			//检查各档案分类的数据项集合的集合是否已初始化
			if(dataItemsForUseQuery==null){
				pFlag = false;
				pErrInfo.getContent().append("各档案分类的数据项集合的集合未初始化");
			}
			
			if (pFlag) {
				Iterator<String> columnNames = dataItemsForUseQuery.keySet().iterator();			
				int index = 0;//记录是否是第一列
				//querySQL格式：AND (Title LIKE '%xx%' OR Ftm LIKE '%xx%')
				while(columnNames.hasNext()){
					String columnName = columnNames.next();
					index ++;
					if(index==1){
						querySQL.append( " AND ( " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}else{
						querySQL.append( " OR " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}
				}
				querySQL.append(")");
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
