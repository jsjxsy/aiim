/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import com.orifound.aiim.bll.service.impl.ArchivesFondsManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesTypeDataItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.CatalogDataItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.CheckRuleManageServiceImpl;
import com.orifound.aiim.bll.service.impl.ConfigManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DutyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.EvaluateItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.EvaluateLevelManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesDataItemSavedMappingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesInfoTableManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialArchivesTypeSavedMappingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.OfficialDocTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl;
import com.orifound.aiim.bll.service.impl.SystemInitializeServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRoleManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CatalogDataItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CheckRuleDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ConfigDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DepartmentInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DutyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.EvaluateLevelDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesDataItemSavedMappingDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialArchivesTypeSavedMappingDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.OfficialDocTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRoleDaoimpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 系统初始化处理类
 *
 */
public class SystemInitializePrepare
{
	/**
	 * 获取系统初始化器
	 * @return 系统初始化器
	 */
	public static SystemInitializer getSystemInitializer()
	{
		return SystemInitializer.getInstance();
	}
	
	private static void setBLL(SystemInitializeServiceImpl ServiceImpl)
	{
		//业务逻辑对象注入
		
		//档案全宗
		ArchivesFondsManageServiceImpl archivesFondsManageServiceImpl=new ArchivesFondsManageServiceImpl();
		archivesFondsManageServiceImpl.setArchivesFondsDao(new ArchivesFondsDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesFondsManageService(archivesFondsManageServiceImpl);
		
		//档案相关信息表管理服务对象
		ArchivesInfoTableManageServiceImpl archivesInfoTableManageServiceImpl=new ArchivesInfoTableManageServiceImpl();
		archivesInfoTableManageServiceImpl.setArchivesInfoTableDao(new ArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesInfoTableManageService(archivesInfoTableManageServiceImpl);
		
		//档案分类管理服务对象
		ArchivesTypeManageServiceImpl archivesTypeManageServiceImpl=new ArchivesTypeManageServiceImpl();
		archivesTypeManageServiceImpl.setArchivesTypeDao(new ArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeManageService(archivesTypeManageServiceImpl);
		
		//档案分类数据项管理服务对象
		ArchivesTypeDataItemManageServiceImpl archivesTypeDataItemManageServiceImpl=new ArchivesTypeDataItemManageServiceImpl();
		archivesTypeDataItemManageServiceImpl.setArchivesTypeDataItemDao(new ArchivesTypeDataItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeDataItemManageService(archivesTypeDataItemManageServiceImpl);
		
		//数据源管理服务对象
		DataSourceManageServiceImpl dataSourceManageServiceImpl=new DataSourceManageServiceImpl();
		dataSourceManageServiceImpl.setDataSourceDao(new DataSourceDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDataSourceManageService(dataSourceManageServiceImpl);
		
		//数据源的数据项管理服务对象
		DataSourceItemManageServiceImpl dataSourceItemManageServiceImpl=new DataSourceItemManageServiceImpl();
		dataSourceItemManageServiceImpl.setDataSourceItemDao(new DataSourceItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDataSourceItemManageService(dataSourceItemManageServiceImpl);
		
		//校验规则管理服务对象
		CheckRuleManageServiceImpl checkRuleManageServiceImpl=new CheckRuleManageServiceImpl();
		checkRuleManageServiceImpl.setCheckRuleDao(new CheckRuleDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setCheckRuleManageService(checkRuleManageServiceImpl);
		
		//保管期限管理服务对象
		RetentionPeriodManageServiceImpl retentionPeriodManageServiceImpl=new RetentionPeriodManageServiceImpl(new RetentionPeriodDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setRetentionPeriodManageService(retentionPeriodManageServiceImpl);
		
		//档案密级管理服务对象
		ArchivesSecrecyManageServiceImpl archivesSecrecyManageServiceImpl=new ArchivesSecrecyManageServiceImpl(new ArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesSecrecyManageService(archivesSecrecyManageServiceImpl);
		
		//部门信息管理服务对象
		DepartmentInfoManageServiceImpl departmentInfoManageServiceImpl=new DepartmentInfoManageServiceImpl(new DepartmentInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDepartmentInfoManageService(departmentInfoManageServiceImpl);
		
		//配置信息管理服务对象
		ConfigManageServiceImpl configManageServiceImpl=new ConfigManageServiceImpl(new ConfigDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setConfigManageService(configManageServiceImpl);
		
		//公文文种信息管理服务对象
		OfficialDocTypeManageServiceImpl officialDocTypeManageServiceImpl=new OfficialDocTypeManageServiceImpl(new OfficialDocTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialDocTypeManageService(officialDocTypeManageServiceImpl);
		
		//公文档案分类信息管理服务对象
		OfficialArchivesTypeManageServiceImpl officialArchivesTypeManageServiceImpl=new OfficialArchivesTypeManageServiceImpl(new OfficialArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesTypeManageService(officialArchivesTypeManageServiceImpl);
		
		//公文档案分类相关信息表管理服务对象
		OfficialArchivesInfoTableManageServiceImpl officialArchivesInfoTableManageServiceImpl=new OfficialArchivesInfoTableManageServiceImpl(new OfficialArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesInfoTableManageService(officialArchivesInfoTableManageServiceImpl);
		
		//考核项目字典管理服务类对象的依赖注入
		EvaluateItemManageServiceImpl evaluateItemManageServiceImpl=new EvaluateItemManageServiceImpl(new EvaluateItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setEvaluateItemManageService(evaluateItemManageServiceImpl);
		
		//考核等级字典表管理服务对象的依赖注入
		EvaluateLevelManageServiceImpl evaluateLevelManageServiceImpl=new EvaluateLevelManageServiceImpl(new EvaluateLevelDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setEvaluateLevelManageService(evaluateLevelManageServiceImpl);
		
		//职务信息数据字典服务对象的依赖注入
		DutyManageServiceImpl dutyManageServiceImpl=new DutyManageServiceImpl(new DutyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDutyManageService(dutyManageServiceImpl);
		
		//公文档案类型保存映射服务对象的依赖注入
		OfficialArchivesTypeSavedMappingManageServiceImpl officialArchivesTypeSavedMappingManageServiceImpl=new OfficialArchivesTypeSavedMappingManageServiceImpl(new OfficialArchivesTypeSavedMappingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesTypeSavedMappingManageService(officialArchivesTypeSavedMappingManageServiceImpl);
		
		//公文档案数据项归档映射管理服务对象的依赖注入
		OfficialArchivesDataItemSavedMappingManageServiceImpl officialArchivesDataItemSavedMappingManageServiceImpl=new OfficialArchivesDataItemSavedMappingManageServiceImpl(new OfficialArchivesDataItemSavedMappingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesDataItemSavedMappingManageService(officialArchivesDataItemSavedMappingManageServiceImpl);
		
		//用户角色信息字典服务对象的依赖注入
		UserRoleManageServiceImpl userRolesInfoManageServiceImpl=new UserRoleManageServiceImpl(new UserRoleDaoimpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setUserRoleManageService(userRolesInfoManageServiceImpl);
		
		//目录打印模板数据项定义管理服务对象的依赖注入
		CatalogDataItemManageServiceImpl catalogDataItemManageServiceImpl=new CatalogDataItemManageServiceImpl(new CatalogDataItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setCatalogDataItemManageService(catalogDataItemManageServiceImpl);
	}
	
	/**
	 * 系统初始化
	 * @param pErrInfo
	 * @return
	 */
	public static boolean initialize(ErrInfo pErrInfo)
	{
		boolean pFlag=true;
		
		SystemInitializeServiceImpl ServiceImpl = new SystemInitializeServiceImpl();
		
		setBLL(ServiceImpl);
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			pFlag= false;
		}
		
		return pFlag;
	}
}
