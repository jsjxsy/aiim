/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesFondsManageService;
import com.orifound.aiim.bll.service.IArchivesInfoTableManageService;
import com.orifound.aiim.bll.service.IArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IArchivesTypeDataItemManageService;
import com.orifound.aiim.bll.service.IArchivesTypeManageService;
import com.orifound.aiim.bll.service.ICatalogDataItemManageService;
import com.orifound.aiim.bll.service.ICheckRuleManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IDataSourceItemManageService;
import com.orifound.aiim.bll.service.IDataSourceManageService;
import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IDutyManageService;
import com.orifound.aiim.bll.service.IEvaluateItemManageService;
import com.orifound.aiim.bll.service.IEvaluateLevelManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesDataItemSavedMappingManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoTableManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesTypeManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesTypeSavedMappingManageService;
import com.orifound.aiim.bll.service.IOfficialDocTypeManageService;
import com.orifound.aiim.bll.service.IRetentionPeriodManageService;
import com.orifound.aiim.bll.service.ISystemInitializeService;
import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ArchivesTypeDataItemComparator;
import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.EnumArchivesInfoTableType;
import com.orifound.aiim.entity.EnumArchivesTypeDataItemComparatorType;
import com.orifound.aiim.entity.EnumDataSourceInherentType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.EvaluateItem;
import com.orifound.aiim.entity.EvaluateLevel;
import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;
import com.orifound.aiim.entity.OfficialArchivesInfoTable;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserRole;

/**
 * 系统初始化服务实现类
 * 
 */
public class SystemInitializeServiceImpl implements ISystemInitializeService
{

	/**
	 * 档案全宗管理服务对象
	 */
	private IArchivesFondsManageService archivesFondsManageService = null;

	/**
	 * 设置属性值：档案全宗管理服务对象
	 * 
	 * @param archivesFondsManageService
	 *            档案全宗管理服务对象
	 */
	public void setArchivesFondsManageService(IArchivesFondsManageService archivesFondsManageService)
	{
		this.archivesFondsManageService = archivesFondsManageService;
	}

	/**
	 * 获取属性值：档案全宗管理服务对象
	 * 
	 * @return 档案全宗管理服务对象
	 */
	public IArchivesFondsManageService getArchivesFondsManageService()
	{
		return archivesFondsManageService;
	}

	/**
	 * 档案相关信息表管理服务对象
	 */
	private IArchivesInfoTableManageService archivesInfoTableManageService = null;

	/**
	 * 设置属性值：档案相关信息表管理服务对象
	 * 
	 * @param archivesInfoTableManageService
	 *            档案相关信息表管理服务对象
	 */
	public void setArchivesInfoTableManageService(IArchivesInfoTableManageService archivesInfoTableManageService)
	{
		this.archivesInfoTableManageService = archivesInfoTableManageService;
	}

	/**
	 * 获取属性值：档案相关信息表管理服务对象
	 * 
	 * @return 档案相关信息表管理服务对象
	 */
	public IArchivesInfoTableManageService getArchivesInfoTableManageService()
	{
		return archivesInfoTableManageService;
	}

	/**
	 * 档案分类管理服务对象
	 */
	private IArchivesTypeManageService archivesTypeManageService = null;

	/**
	 * 设置属性值：档案分类管理服务对象
	 * 
	 * @param archivesTypeManageService
	 *            档案分类管理服务对象
	 */
	public void setArchivesTypeManageService(IArchivesTypeManageService archivesTypeManageService)
	{
		this.archivesTypeManageService = archivesTypeManageService;
	}

	/**
	 * 获取属性值：档案分类管理服务对象
	 * 
	 * @return 档案分类管理服务对象
	 */
	public IArchivesTypeManageService getArchivesTypeManageService()
	{
		return archivesTypeManageService;
	}

	/**
	 * 档案分类数据项管理服务对象
	 */
	private IArchivesTypeDataItemManageService archivesTypeDataItemManageService = null;

	/**
	 * 设置属性值：档案分类数据项管理服务对象
	 * 
	 * @param archivesTypeDataItemManageService
	 *            档案分类数据项管理服务对象
	 */
	public void setArchivesTypeDataItemManageService(IArchivesTypeDataItemManageService archivesTypeDataItemManageService)
	{
		this.archivesTypeDataItemManageService = archivesTypeDataItemManageService;
	}

	/**
	 * 获取属性值：档案分类数据项管理服务对象
	 * 
	 * @return 档案分类数据项管理服务对象
	 */
	public IArchivesTypeDataItemManageService getArchivesTypeDataItemManageService()
	{
		return archivesTypeDataItemManageService;
	}

	/**
	 * 数据源管理服务对象
	 */
	private IDataSourceManageService dataSourceManageService = null;

	/**
	 * 设置属性值：数据源管理服务对象
	 * 
	 * @param dataSourceManageService
	 *            数据源管理服务对象
	 */
	public void setDataSourceManageService(IDataSourceManageService dataSourceManageService)
	{
		this.dataSourceManageService = dataSourceManageService;
	}

	/**
	 * 获取属性值：数据源管理服务对象
	 * 
	 * @return 数据源管理服务对象
	 */
	public IDataSourceManageService getDataSourceManageService()
	{
		return dataSourceManageService;
	}

	/**
	 * 数据源的数据项管理服务对象
	 */
	private IDataSourceItemManageService dataSourceItemManageService = null;

	/**
	 * 设置属性值：数据源的数据项管理服务对象
	 * 
	 * @param dataSourceItemManageService
	 *            数据源的数据项管理服务对象
	 */
	public void setDataSourceItemManageService(IDataSourceItemManageService dataSourceItemManageService)
	{
		this.dataSourceItemManageService = dataSourceItemManageService;
	}

	/**
	 * 获取属性值：数据源的数据项管理服务对象
	 * 
	 * @return 数据源的数据项管理服务对象
	 */
	public IDataSourceItemManageService getDataSourceItemManageService()
	{
		return dataSourceItemManageService;
	}

	/**
	 * 校验规则管理服务对象
	 */
	private ICheckRuleManageService checkRuleManageService = null;

	/**
	 * 设置属性值：校验规则管理服务对象
	 * 
	 * @param checkRuleManageService
	 *            校验规则管理服务对象
	 */
	public void setCheckRuleManageService(ICheckRuleManageService checkRuleManageService)
	{
		this.checkRuleManageService = checkRuleManageService;
	}

	/**
	 * 获取属性值：校验规则管理服务对象
	 * 
	 * @return 校验规则管理服务对象
	 */
	public ICheckRuleManageService getCheckRuleManageService()
	{
		return checkRuleManageService;
	}

	/**
	 * 保管期限管理服务对象
	 */
	private IRetentionPeriodManageService retentionPeriodManageService = null;

	/**
	 * 设置属性值：保管期限管理服务对象
	 * 
	 * @param retentionPeriodManageService
	 *            保管期限管理服务对象
	 */
	public void setRetentionPeriodManageService(IRetentionPeriodManageService retentionPeriodManageService)
	{
		this.retentionPeriodManageService = retentionPeriodManageService;
	}

	/**
	 * 获取属性值：保管期限管理服务对象
	 * 
	 * @return 保管期限管理服务对象
	 */
	public IRetentionPeriodManageService getRetentionPeriodManageService()
	{
		return retentionPeriodManageService;
	}

	/**
	 * 档案密级管理服务对象
	 */
	private IArchivesSecrecyManageService archivesSecrecyManageService = null;

	/**
	 * 设置属性值：档案密级管理服务对象
	 * 
	 * @param archivesSecrecyManageService
	 *            档案密级管理服务对象
	 */
	public void setArchivesSecrecyManageService(IArchivesSecrecyManageService archivesSecrecyManageService)
	{
		this.archivesSecrecyManageService = archivesSecrecyManageService;
	}

	/**
	 * 获取属性值：档案密级管理服务对象
	 * 
	 * @return 档案密级管理服务对象
	 */
	public IArchivesSecrecyManageService getArchivesSecrecyManageService()
	{
		return archivesSecrecyManageService;
	}

	/**
	 * 部门信息管理服务对象
	 */
	private IDepartmentInfoManageService departmentInfoManageService = null;

	/**
	 * 设置属性值：部门信息管理服务对象
	 * 
	 * @param departmentInfoManageService
	 *            部门信息管理服务对象
	 */
	public void setDepartmentInfoManageService(IDepartmentInfoManageService departmentInfoManageService)
	{
		this.departmentInfoManageService = departmentInfoManageService;
	}

	/**
	 * 获取属性值：部门信息管理服务对象
	 * 
	 * @return 部门信息管理服务对象
	 */
	public IDepartmentInfoManageService getDepartmentInfoManageService()
	{
		return departmentInfoManageService;
	}

	/**
	 * 系统配置项服务对象
	 */
	@Autowired
	private IConfigManageService configManageService;
	
	/**
	 * 获取属性值：系统配置项服务对象
	 * 
	 * @return 系统配置项服务对象
	 */
	public IConfigManageService getConfigManageService()
	{
		return configManageService;
	}

	/**
	 * 设置属性值：系统配置项服务对象
	 * 
	 * @param configManageService
	 *            系统配置项服务对象
	 */
	public void setConfigManageService(IConfigManageService configManageService)
	{
		this.configManageService = configManageService;
	}
	
	/**
	 * 公文文种信息管理服务对象
	 */
	private IOfficialDocTypeManageService officialDocTypeManageService = null;

	/**
	 * 设置属性值：公文文种信息管理服务对象
	 * @param officialDocTypeManageService 公文文种信息管理服务对象
	 */
	public void setOfficialDocTypeManageService(IOfficialDocTypeManageService officialDocTypeManageService)
	{
		this.officialDocTypeManageService = officialDocTypeManageService;
	}

	/**
	 * 获取属性值：公文文种信息管理服务对象
	 * @return 公文文种信息管理服务对象
	 */
	public IOfficialDocTypeManageService getOfficialDocTypeManageService()
	{
		return officialDocTypeManageService;
	}
	
	/**
	 * 公文档案分类信息管理服务对象
	 */
	private IOfficialArchivesTypeManageService officialArchivesTypeManageService = null;

	/**
	 * 设置属性值：公文档案分类信息管理服务对象
	 * @param officialArchivesTypeManageService 公文档案分类信息管理服务对象
	 */
	public void setOfficialArchivesTypeManageService(IOfficialArchivesTypeManageService officialArchivesTypeManageService)
	{
		this.officialArchivesTypeManageService = officialArchivesTypeManageService;
	}

	/**
	 * 获取属性值：公文档案分类信息管理服务对象
	 * @return 公文档案分类信息管理服务对象
	 */
	public IOfficialArchivesTypeManageService getOfficialArchivesTypeManageService()
	{
		return officialArchivesTypeManageService;
	}

	/**
	 * 公文档案信息相关表管理服务对象
	 */
	private IOfficialArchivesInfoTableManageService officialArchivesInfoTableManageService = null;

	/**
	 * 设置属性值：公文档案信息相关表管理服务对象
	 * @param officialArchivesInfoTableManageService 公文档案信息相关表管理服务对象
	 */
	public void setOfficialArchivesInfoTableManageService(IOfficialArchivesInfoTableManageService officialArchivesInfoTableManageService)
	{
		this.officialArchivesInfoTableManageService = officialArchivesInfoTableManageService;
	}

	/**
	 * 获取属性值：公文档案信息相关表管理服务对象
	 * @return 公文档案信息相关表管理服务对象
	 */
	public IOfficialArchivesInfoTableManageService getOfficialArchivesInfoTableManageService()
	{
		return officialArchivesInfoTableManageService;
	}

	/**
	 * 考核项目字典管理服务类对象
	 */
	private IEvaluateItemManageService evaluateItemManageService = null;
	
	/**
	 * 获取属性值：考核项目字典服务类对象
	 * @return 考核项目服务类对象
	 */
	public IEvaluateItemManageService getEvaluateItemManageService() {
		return evaluateItemManageService;
	}
	/**
	 * 设置属性值：考核项目字典服务类对象
	 * @param EvaluateItemManageService 考核项目服务类对象
	 */
	public void setEvaluateItemManageService(
			IEvaluateItemManageService evaluateItemManageService) {
		this.evaluateItemManageService = evaluateItemManageService;
	}

	/**
	 * 考核等级字典表管理服务对象
	 */
	private IEvaluateLevelManageService evaluateLevelManageService = null;

	/**
	 * 获取属性值：考核等级字典表 管理服务对象
	 * @return 考核等级字典表 管理服务对象
	 */
	public IEvaluateLevelManageService getEvaluateLevelManageService() {
		return evaluateLevelManageService;
	}
	
	/**
	 * 设置属性值：考核等级字典表 管理服务对象
	 * @param evaluateLeevlManageService 考核等级字典表 管理服务对象
	 */
	public void setEvaluateLevelManageService(IEvaluateLevelManageService evaluateLevelManageService) {
		this.evaluateLevelManageService = evaluateLevelManageService;
	}
	
	/**
	 * 职务信息数据字典服务对象
	 */
	private IDutyManageService dutyManageService = null;

	/**
	 * 设置属性值：职务信息数据字典服务类
	 * @param dutyManageService 职务信息数据字典服务类
	 */
	public void setDutyManageService(IDutyManageService dutyManageService) {
		this.dutyManageService = dutyManageService;
	}

	/**
	 * 获取属性值：职务信息数据字典服务类
	 * @return 职务信息数据字典服务类
	 */
	public IDutyManageService getDutyManageService() {
		return dutyManageService;
	}

	/**
	 * 公文档案分类的归档映射关系表管理对象
	 */
	private IOfficialArchivesTypeSavedMappingManageService officialArchivesTypeSavedMappingManageService;

	/**
	 * 获取公文档案分类的归档映射关系表管理对象
	 * @return
	 */
	public IOfficialArchivesTypeSavedMappingManageService getOfficialArchivesTypeSavedMappingManageService() {
		return officialArchivesTypeSavedMappingManageService;
	}
	
	/**
	 * 设置公文档案分类的归档映射关系表管理对象
	 * @param archivesTypeSavedMappingManageService
	 */
	public void setOfficialArchivesTypeSavedMappingManageService(IOfficialArchivesTypeSavedMappingManageService officialArchivesTypeSavedMappingManageService) {
		this.officialArchivesTypeSavedMappingManageService = officialArchivesTypeSavedMappingManageService;
	}
	
	/**
	 * 公文档案数据项归档映射管理服务对象
	 */
	private IOfficialArchivesDataItemSavedMappingManageService officialArchivesDataItemSavedMappingManageService;

	/**
	 * 获取公文档案数据项归档映射管理服务对象
	 * @return
	 */
	public IOfficialArchivesDataItemSavedMappingManageService getOfficialArchivesDataItemSavedMappingManageService() {
		return officialArchivesDataItemSavedMappingManageService;
	}
	
	/**
	 * 设置公文档案数据项归档映射管理服务对象
	 * @param officialArchivesDataItemSavedMappingManageService
	 */
	public void setOfficialArchivesDataItemSavedMappingManageService(IOfficialArchivesDataItemSavedMappingManageService officialArchivesDataItemSavedMappingManageService) {
		this.officialArchivesDataItemSavedMappingManageService = officialArchivesDataItemSavedMappingManageService;
	}

	/**
	 * 用户角色信息字典服务对象
	 */
	private IUserRoleManageService userRoleManageService = null;

	/**
	 * 设置属性值：用户角色信息字典服务对象
	 * @param userRoleManageService 用户角色信息字典服务对象
	 */
	public void setUserRoleManageService(IUserRoleManageService userRoleManageService) {
		this.userRoleManageService = userRoleManageService;
	}

	/**
	 * 获取属性值：用户角色信息字典服务对象
	 * @return 用户角色信息字典服务对象
	 */
	public IUserRoleManageService getUserRoleManageService() {
		return userRoleManageService;
	}
	
	/**
	 * 目录打印模板数据项定义管理服务对象
	 */
	private ICatalogDataItemManageService catalogDataItemManageService = null;

	/**
	 * 设置属性值：目录打印模板数据项定义管理服务对象
	 * @param catalogDataItemManageService 目录打印模板数据项定义管理服务对象
	 */
	public void setCatalogDataItemManageService(ICatalogDataItemManageService catalogDataItemManageService)
	{
		this.catalogDataItemManageService = catalogDataItemManageService;
	}

	/**
	 * 获取属性值：目录打印模板数据项定义管理服务对象
	 * @return 目录打印模板数据项定义管理服务对象
	 */
	public ICatalogDataItemManageService getCatalogDataItemManageService()
	{
		return catalogDataItemManageService;
	}

	
	
	/**
	 * 检查相关的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查是否有进行档案全宗业务逻辑对象的依赖注入
			pErrPos = 1;
			if (archivesFondsManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案全宗的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行档案相关信息表的业务逻辑对象的依赖注入
			pErrPos = 2;
			if (archivesInfoTableManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案相关信息表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行档案分类业务逻辑对象的依赖注入
			pErrPos = 3;
			if (archivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行档案分类数据项业务逻辑对象的依赖注入
			pErrPos = 4;
			if (archivesTypeDataItemManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类数据项的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行数据源业务逻辑对象的依赖注入
			pErrPos = 5;
			if (dataSourceManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("数据源的的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行数据源的数据项业务逻辑对象的依赖注入
			pErrPos = 6;
			if (dataSourceItemManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("数据源的数据项的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行校验规则业务逻辑对象的依赖注入
			pErrPos = 7;
			if (checkRuleManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("校验规则的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行保管期限业务逻辑对象的依赖注入
			pErrPos = 8;
			if (retentionPeriodManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("保管期限的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}

			// 检查是否有进行部门信息业务逻辑对象的依赖注入
			pErrPos = 9;
			if (departmentInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("部门信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行部门信息业务逻辑对象的依赖注入
			pErrPos = 10;
			if (departmentInfoManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("部门信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行配置信息业务逻辑对象的依赖注入
			pErrPos = 11;
			if (configManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("配置信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行公文文种信息业务逻辑对象的依赖注入
			pErrPos = 12;
			if (officialDocTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文文种信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行公文档案分类信息业务逻辑对象的依赖注入
			pErrPos = 13;
			if (officialArchivesTypeManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文档案分类信息的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行公文档案信息相关表业务逻辑对象的依赖注入
			pErrPos = 14;
			if (officialArchivesInfoTableManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文档案信息相关表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行考核项目字典管理服务类对象的依赖注入
			pErrPos = 15;
			if (evaluateItemManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("考核项目字典信息相关表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			// 检查是否有进行考核等级字典表管理服务对象的依赖注入
			pErrPos = 16;
			if (evaluateLevelManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("考核等级字典信息相关表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 17;
			//检查是否进行职务信息数据字典服务对象的依赖注入
			if (dutyManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("职务信息数据字典信息相关表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 18;
			//检查是否进行公文档案类型归档映射服务对象的依赖注入
			if (officialArchivesTypeSavedMappingManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("公文档案类型归档映射表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 19;
			//检查是否进行公文档案数据项归档映射管理服务对象的依赖注入
			if (officialArchivesDataItemSavedMappingManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("公文档案数据项归档映射表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 20;
			//检查是否进行用户角色信息字典服务对象的依赖注入
			if (userRoleManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("用户角色信息字典表的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			pErrPos = 21;
			//检查是否进行用户角色信息字典服务对象的依赖注入
			if (catalogDataItemManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("目录打印模板数据项定义的ManageService对象（业务逻辑对象）非法为空，请检查是否有进行依赖注入或赋值。");
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
		}

		return pFlag;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.orifound.aiim.bll.service.ISystemInitializeService#initialize(com
	 * .orifound.aiim.entity.SystemInitializer,
	 * com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean initialize(SystemInitializer systemInitializer, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			// 检查是否有进行相关业务逻辑对象的依赖注入
			if (checkBllInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			// 初始化档案全宗信息
			if (pFlag)
			{
				pErrPos = 2;
				List<ArchivesFonds> archivesFondss = new ArrayList<ArchivesFonds>();
				if (archivesFondsManageService.findArchivesFondss(archivesFondss, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化档案全宗信息失败: ");
				}
				else
				{
					// 保存初始化的档案全宗信息
					systemInitializer.setArchivesFonds(archivesFondss);
				}
			}

			// 初始化实体档案分类信息得到树状结构的档案分类
			if (pFlag)
			{
				pErrPos = 3;
				LinkedHashMap<Integer, ArchivesType> archivesTypes = new LinkedHashMap<Integer, ArchivesType>();
				if (archivesTypeManageService.findArchivesTypes(archivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化档案分类信息失败: ");
				}
				else
				{
					// 保存初始化的树状结构的档案分类信息
					systemInitializer.setArchivesTypes(archivesTypes);
				}
			}

			// 转换获取平面结构的实体档案分类集合
			if (pFlag)
			{
				pErrPos = 4;
				Map<Integer, ArchivesType> planeArchivesTypes = new HashMap<Integer, ArchivesType>();
				if (CommonUtil.convertTreeArchivesTypesToPlane(systemInitializer.getArchivesTypes(), planeArchivesTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化平面结构的档案分类信息失败: ");
				}
				else
				{
					// 保存初始化的平面结构的实体档案分类信息
					systemInitializer.setPlaneArchivesTypes(planeArchivesTypes);
				}
			}

			// 初始化每个实体档案分类的相关表信息（例如：工作表、归档表、工作过程表、原文信息表等等）
			if (pFlag)
			{
				pErrPos = 5;
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable> archivesInfoTables = new EnumMap<EnumArchivesInfoTableType, ArchivesInfoTable>(
							EnumArchivesInfoTableType.class);
					if (archivesInfoTableManageService.findArchivesTypeTables(archivesType.getID(), archivesInfoTables, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化档案分类（" + archivesType.getFullCode() + "）的相关表信息失败: ");
					}
					else
					{
						// 保存档案分类的相关表信息
						archivesType.setArchivesInfoTables(archivesInfoTables);
					}
				}
			}

			// 初始化实体档案分类中的每个一级档案分类的数据项信息
			if (pFlag)
			{
				pErrPos = 6;
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems = new LinkedHashMap<String, ArchivesTypeDataItem>();
					if (archivesTypeDataItemManageService.findByArchivesTypeID(false,archivesType.getID(), archivesTypeDataItems, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化档案分类（" + archivesType.getFullCode() + "）的数据项信息失败: ");
					}
					else
					{
						// 保存档案分类的数据项信息
						archivesType.setDataItemsForAll(archivesTypeDataItems);

						// 分离解析档案分类的数据项，分别得到著录项集合、著录查询项集合、利用查询项集合、查询结果列表显示项集合，上述集合均以字段名作为关键字
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay=new LinkedHashMap<String, ArchivesTypeDataItem>();
						if (splitArchivesTypeDataItems(archivesType.getDataItemsForAll(),dataItemsForInput,dataItemsForInputQuery,dataItemsForUseQuery,dataItemsForListDisplay, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "分离解析档案分类（" + archivesType.getFullCode() + "）的数据项失败: ");
						}
						else 
						{
							//保存分离结果
							archivesType.setDataItemsForInput(dataItemsForInput);
							archivesType.setDataItemsForInputQuery(dataItemsForInputQuery);
							archivesType.setDataItemsForUseQuery(dataItemsForUseQuery);
							archivesType.setDataItemsForListDisplay(dataItemsForListDisplay);
						}
					}

					// 处理失败则立即跳出循环
					if (pFlag == false)
					{
						break;
					}
				}
			}
			
			// 初始化实体档案分类中的每个一级档案分类的目录打印模板信息（各目录打印模板关联的数据项集合）
			if (pFlag)
			{
				pErrPos = 61;
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					if (catalogDataItemManageService.findCatalogDataItems(archivesType, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化档案分类（" + archivesType.getFullCode() + "）的目录打印模板数据项信息失败: ");
					}

					// 处理失败则立即跳出循环
					if (pFlag==false)
					{
						break;
					}
				}
			}
			
			// 初始化公文档案分类信息
			if (pFlag)
			{
				pErrPos = 7;
				LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes=new LinkedHashMap<Integer, OfficialArchivesType>();
				if (officialArchivesTypeManageService.findOfficialArchivesTypes(officialArchivesTypes, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化公文档案分类信息失败: ");
				}
				else 
				{
					//保存公文档案分类信息
					systemInitializer.setOfficialArchivesTypes(officialArchivesTypes);
				}
			}
			
			// 初始化每个公文档案分类的相关表信息（例如：公文登记表、原文信息表）
			if (pFlag)
			{
				pErrPos = 8;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable> officialArchivesInfoTables=new EnumMap<EnumOfficialArchivesInfoTableType, OfficialArchivesInfoTable>(EnumOfficialArchivesInfoTableType.class);
			
					if (officialArchivesInfoTableManageService.findOfficialArchivesInfoTables(officialArchivesType.getID(), officialArchivesInfoTables, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化公文档案分类（" + officialArchivesType.getName() + "）的相关表信息失败: ");
					}
					else
					{
						// 保存公文档案分类对应的相关表信息
						officialArchivesType.setOfficialArchivesInfoTables(officialArchivesInfoTables);
					}
				}
			}
			
			// 初始化每个公文档案分类的数据项信息
			if (pFlag)
			{
				pErrPos = 9;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems = new LinkedHashMap<String, ArchivesTypeDataItem>();
					if (archivesTypeDataItemManageService.findByArchivesTypeID(true,officialArchivesType.getID(), archivesTypeDataItems, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化公文档案分类（" + officialArchivesType.getName() + "）的数据项信息失败: ");
					}
					else
					{
						// 保存公文档案分类的数据项信息
						officialArchivesType.setDataItemsForAll(archivesTypeDataItems);

						// 分离解析公文档案分类的数据项，分别得到著录项集合、著录查询项集合、利用查询项集合、查询结果列表显示项集合，上述集合均以字段名作为关键字
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery=new LinkedHashMap<String, ArchivesTypeDataItem>();
						LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay=new LinkedHashMap<String, ArchivesTypeDataItem>();
						if (splitArchivesTypeDataItems(officialArchivesType.getDataItemsForAll(),dataItemsForInput,dataItemsForInputQuery,dataItemsForUseQuery,dataItemsForListDisplay, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "分离解析公文档案分类（" + officialArchivesType.getName() + "）的数据项失败: ");
						}
						else 
						{
							//保存分离结果
							officialArchivesType.setDataItemsForInput(dataItemsForInput);
							officialArchivesType.setDataItemsForInputQuery(dataItemsForInputQuery);
							officialArchivesType.setDataItemsForUseQuery(dataItemsForUseQuery);
							officialArchivesType.setDataItemsForListDisplay(dataItemsForListDisplay);
						}
					}

					// 处理失败则立即跳出循环
					if (pFlag == false)
					{
						break;
					}
				}
			}
			
			//初始化每个公文档案分类归档存映射关系表
			if (pFlag) {
				pErrPos = 10;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					Map<Integer,OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings = new HashMap<Integer,OfficialArchivesTypeSavedMapping>();
					if (officialArchivesTypeSavedMappingManageService.findArchivesTypesByOfficialArchivesTypeID(officialArchivesType.getID(), officialArchivesTypeSavedMappings, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化公文档案分类归档存映射信息失败: ");
					} else {
						// 公文档案归档映射关系信息
						officialArchivesType.setOfficialArchivesTypeSavedMappings(officialArchivesTypeSavedMappings);
					}
				}
			}
			
			//初始化每一个公文档案分类归档映射关系对应的数据项归档映射信息
			if (pFlag) {
				pErrPos = 11;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					if (officialArchivesType.getOfficialArchivesTypeSavedMappings()!=null) {
						
						for (OfficialArchivesTypeSavedMapping item : officialArchivesType.getOfficialArchivesTypeSavedMappings().values()) {
							Map<Integer, OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings=new HashMap<Integer, OfficialArchivesDataItemSavedMapping>();
							if (officialArchivesDataItemSavedMappingManageService.findByArchivesTypeSavedMappingID(item.getID(), officialArchivesDataItemSavedMappings, pErrInfo)==false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "获取指定公文档案分类归档映射关系对应的数据项归档映射信息失败: ");
							}
							else {
								//将数据项归档映射关系保存至公文档案分类映射信息对象中
								item.setOfficialArchivesDataItemSavedMapping(officialArchivesDataItemSavedMappings);
							}
						}
					}
				}
			}
			
			// 初始化公文档案分类的目录打印模板信息（各目录打印模板关联的数据项集合）
			if (pFlag)
			{
				pErrPos = 61;
				for (OfficialArchivesType officialArchivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					if (catalogDataItemManageService.findCatalogDataItemsForOfficial(officialArchivesType, pErrInfo) == false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "初始化公文档案分类（" + officialArchivesType.getName() + "）的目录打印模板数据项信息失败: ");
					}
					
					// 处理失败则立即跳出循环
					if (pFlag==false)
					{
						break;
					}
				}
			}

			// 初始化保管期限
			if (pFlag)
			{
				pErrPos = 12;
				LinkedHashMap<Integer, RetentionPeriod> retentionPeriods = new LinkedHashMap<Integer, RetentionPeriod>();
				if (retentionPeriodManageService.findRetentionPeriods(retentionPeriods, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化保管期限信息失败: ");
				}
				else
				{
					// 保存保管期限信息
					systemInitializer.setRetentionPeriods(retentionPeriods);
					
					RetentionPeriod period = null;
					
					////初始化永久保管期限
					for(Integer key : retentionPeriods.keySet()) {
						period = retentionPeriods.get(key);
						//判断是否存在永久保存期限
						if(period!=null && StringTool.checkNull(period.getName()) && period.getName().equals("永久")) {
							systemInitializer.setForeverRetentionPeriod(period);
							break;
						} else {
							pFlag = false;
							pErrInfo.getContent().insert(0, "初始化永久保存期限信息 失败: ");
						}
					}
				}
			}

			// 初始化档案密级
			if (pFlag)
			{
				pErrPos = 13;
				LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys = new LinkedHashMap<Integer, ArchivesSecrecy>();
				if (archivesSecrecyManageService.findArchivesSecrecys(archivesSecrecys, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化档案密级信息失败: ");
				}
				else
				{
					// 保存保管期限信息
					systemInitializer.setArchivesSecrecys(archivesSecrecys);
				}
			}
			
			// 初始化公文文种
			if (pFlag)
			{
				pErrPos = 14;
				LinkedHashMap<Integer, OfficialDocType> officialDocTypes = new LinkedHashMap<Integer, OfficialDocType>();
				if (officialDocTypeManageService.findOfficialDocTypes(officialDocTypes, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化档案密级信息失败: ");
				}
				else
				{
					// 保存公文文种信息
					systemInitializer.setOfficialDocTypes(officialDocTypes);
				}
			}

			// 初始化部门信息
			if (pFlag)
			{
				pErrPos = 15;
				List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findDepartmentInfos(departmentInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化部门信息失败: ");
				}
				else
				{
					// 保存部门信息
					systemInitializer.setDepartmentInfos(departmentInfos);
				}
			}

			// 初始化数据源信息（例如：密级、保管期限、形成部门、全宗、公文呢文种以及其他非固有数据源信息）
			if (pFlag)
			{
				pErrPos = 16;
				Map<Integer, DataSource> dataSources = new HashMap<Integer, DataSource>();
				if (dataSourceManageService.findDataSources(dataSources, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化数据源信息失败: ");
				}
				else
				{
					// 保存数据源信息
					systemInitializer.setDataSources(dataSources);
				}
			}

			// 初始化每个数据源的数据项信息（例如：密级数据源包括公开、开放和内部等选择项）
			if (pFlag)
			{
				pErrPos = 17;
				if (initializeDataSourceItems(systemInitializer, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化数据源项信息失败: ");
				}
			}

			// 初始化原文临时存储目录信息
			if (pFlag)
			{
				pErrPos = 18;
				List<Config> attachedFileTempSavePaths = new ArrayList<Config>();
				if (configManageService.findConfigByConfigType("AttachedFileWorkingPath", attachedFileTempSavePaths, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化原文临时存储目录信息失败: ");
				}
				else
				{
					// 保存原文临时存储目录信息
					systemInitializer.setAttachedFileTempSavePaths(attachedFileTempSavePaths);
				}
			}
			
			// 初始化校验规则字典信息
			if (pFlag)
			{
				pErrPos = 19;
				Map<Integer, CheckRule> checkRules = new HashMap<Integer, CheckRule>();
				if (checkRuleManageService.findCheckRules(checkRules, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化校验规则信息失败: ");
				}
				else
				{
					// 保存校验规则字典信息
					systemInitializer.setCheckRules(checkRules);
				}
			}
			
			//初始化考核项目字典信息
			if (pFlag) {
				pErrPos = 20;
				List<EvaluateItem> evaluateItems = new ArrayList<EvaluateItem>();
				if (evaluateItemManageService.findEvaluateItems(evaluateItems , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化考核项目字典信息失败: ");
				} else {
					// 保存考核项目字典信息
					systemInitializer.setEvaluateItems(evaluateItems);
				}
			}
			
			//初始化考核评分等级字典信息
			if (pFlag) {
				pErrPos = 21;
				List<EvaluateLevel> evaluateLevels = new ArrayList<EvaluateLevel>();
				if (evaluateLevelManageService.findEvaluateLevels(evaluateLevels , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化考核评分等级字典信息失败: ");
				} else {
					// 保存考核评分等级字典信息
					systemInitializer.setEvaluateLevels(evaluateLevels);
				}
			}
			
			//初始化职务信息数据字典信息
			if (pFlag) {
				pErrPos = 22;
				List<Duty> duties = new ArrayList<Duty>();
				if (dutyManageService.findDutys(duties, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化职务信息数据字典信息失败: ");
				} else {
					// 保存职务信息数据字典信息
					systemInitializer.setDuties(duties);
				}
			}

			//初始化公开档案密级数据字典信息
			if (pFlag) {
				pErrPos = 23;
				ArchivesSecrecy archivesSecrecy = new ArchivesSecrecy();
				if (archivesSecrecyManageService.findByOpenSecrecy(archivesSecrecy , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化公开档案密级数据字典信息 失败: ");
				} else {
					// 保存公开档案密级数据字典信息
					systemInitializer.setOpenArchivesSecrecy(archivesSecrecy);
				}
			}
			
			//初始化用户角色信息字典集合
			if (pFlag) {
				pErrPos = 24;
				List<UserRole> userRoles = new ArrayList<UserRole>();
				if (userRoleManageService.findUserRoles(userRoles , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化用户角色信息字典集合 失败: ");
				} else {
					// 保存用户角色信息字典集合
					systemInitializer.setUserRoles(userRoles);
				}
			}
			
			//初始化匿名用户角色编号
			if (pFlag) {
				pErrPos = 25;
				int anonymouseUserRoleID = 0;
				List<UserRole> userRoles = new ArrayList<UserRole>();
				if(userRoleManageService.findUserRolesBySystemRolesFlag(1, userRoles, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "初始化匿名用户角色编号失败: ");
				}else{
					if (userRoles.size()!=1) {
						pFlag = false;
						pErrInfo.getContent().append("用户角色信息字典中匿名角色信息配置错误！");
					}else{
						anonymouseUserRoleID = userRoles.get(0).getID();
						systemInitializer.setAnonymouseUserRoleID(anonymouseUserRoleID);
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

		}

		return pFlag;
	}
	
	/**
	 * 初始化每个数据源的数据源项成员
	 * @param systemInitializer 系统初始化器对象
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean initializeDataSourceItems(SystemInitializer systemInitializer,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//检查系统初始化器是否为空
			pErrPos = 1;
			if (systemInitializer==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("系统初始化器对象非法为空。");
			}
			

			//先转换系统固有的字典信息至数据源字典集合中，例如：档案密级、保管期限、档案形成部门、公文文种、档案全宗，然后处理其他非固有数据源
			if (pFlag)
			{
				for (DataSource item : systemInitializer.getDataSources().values())
				{
					pErrPos = 2;
					LinkedHashMap<Integer, DataSourceItem> dataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();

					// 处理数据源固有类别：保管期限
					if (item.getInherentType() == EnumDataSourceInherentType.保管期限)
					{
						LinkedHashMap<Integer, DataSourceItem> retentionPeriodDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertRetentionPeriods(systemInitializer.getRetentionPeriods(), item.getID(), retentionPeriodDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "转换虚拟的保管期限数据源的数据项信息失败: ");
						}
						else
						{
							// 保存转换后的结果至数据源对象中
							item.setDataSourceItems(retentionPeriodDataSourceItems);
						}
					}
					// 处理数据源固有类别：档案密级
					else if (item.getInherentType() == EnumDataSourceInherentType.档案密级)
					{
						pErrPos = 3;
						LinkedHashMap<Integer, DataSourceItem> archivesSecrecyDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertArchivesSecrecys(systemInitializer.getArchivesSecrecys(), item.getID(), archivesSecrecyDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "转换虚拟的档案密级数据源的数据项信息失败: ");
						}
						else
						{
							// 保存转换后的结果至数据源对象中
							item.setDataSourceItems(archivesSecrecyDataSourceItems);
						}
					}
					// 处理数据源固有类别：部门信息
					else if (item.getInherentType() == EnumDataSourceInherentType.档案形成部门)
					{
						pErrPos = 4;
						LinkedHashMap<Integer, DataSourceItem> departmentInfoDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertDepartmentInfos(systemInitializer.getDepartmentInfos(), item.getID(), departmentInfoDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "转换虚拟的档案密级数据源的数据项信息失败: ");
						}
						else
						{
							// 保存转换后的结果至数据源对象中
							item.setDataSourceItems(departmentInfoDataSourceItems);
						}
					}
					// 处理数据源固有类别：公文文种
					else if (item.getInherentType() == EnumDataSourceInherentType.公文文种)
					{
						pErrPos = 5;
						LinkedHashMap<Integer, DataSourceItem> officialDocTypeDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertOfficalDocTypes(systemInitializer.getOfficialDocTypes(), item.getID(), officialDocTypeDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "转换虚拟的档案密级数据源的数据项信息失败: ");
						}
						else
						{
							// 保存转换后的结果至数据源对象中
							item.setDataSourceItems(officialDocTypeDataSourceItems);
						}
					}
					// 处理数据源固有类别：档案全宗
					else if (item.getInherentType() == EnumDataSourceInherentType.档案全宗)
					{
						pErrPos = 6;
						LinkedHashMap<Integer, DataSourceItem> archivesFondsDataSourceItems = new LinkedHashMap<Integer, DataSourceItem>();
						if (convertArchivesFonds(systemInitializer.getArchivesFondss(), item.getID(), archivesFondsDataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "转换虚拟的档案全宗数据源的数据项信息失败: ");
						}
						else
						{
							// 保存转换后的结果至数据源对象中
							item.setDataSourceItems(archivesFondsDataSourceItems);
						}
					}
					// 处理其他非固有数据源
					else
					{
						pErrPos = 6;
						if (dataSourceItemManageService.findDataSourceItemsByDataSourceID(item.getID(), dataSourceItems, pErrInfo) == false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "获取数据源（编号：" + item.getID() + "," + item.getName() + "）的数据项信息失败: ");
							break;
						}
						else
						{
							item.setDataSourceItems(dataSourceItems);
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
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * 分离解析档案分类数据项<br>
	 * 分别得到著录项集合、著录查询项集合、利用查询项集合、查询结果列表显示项集合<br>
	 * 上述集合均以字段名作为关键字
	 * 
	 * @param dataItemsForAll 指定档案分类下定义的所有数据项集合，以字段名作为关键字
	 * @param dataItemsForInput 返回档案分类需要著录的数据项集合，以字段名作为关键字
	 * @param dataItemsForInputQuery 返回档案分类可作为著录查询条件的数据项集合，以字段名作为关键字
	 * @param dataItemsForUseQuery 返回档案分类可作为档案利用查询条件的数据项集合，以字段名作为关键字
	 * @param dataItemsForListDisplay 返回档案分类查询结果集列表显示的数据项集合，以字段名作为关键字
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean splitArchivesTypeDataItems(LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForAll,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInput,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForInputQuery,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery, LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForListDisplay,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查档案分类下定义的所有数据项集合是否为空
			pErrPos = 1;
			if (dataItemsForAll == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案分类下定义的所有数据项集合非法为空。");
			}

			// 如果有所有数据项信息，则进行解析
			if (pFlag)
			{
				//档案分类需要著录的数据项集合
				ArrayList<ArchivesTypeDataItem> pDataItemsForInput=new ArrayList<ArchivesTypeDataItem>();
				//档案分类可作为著录查询条件的数据项集合
				ArrayList<ArchivesTypeDataItem> pDataItemsForInputQuery=new ArrayList<ArchivesTypeDataItem>();
				//档案分类可作为档案利用查询条件的数据项集合
				ArrayList<ArchivesTypeDataItem> pDataItemsForUseQuery=new ArrayList<ArchivesTypeDataItem>();
				//档案分类查询结果集列表显示的数据项集合
				ArrayList<ArchivesTypeDataItem> pDataItemsForListDisplay=new ArrayList<ArchivesTypeDataItem>();
				
				// 逐个遍历分离
				for (ArchivesTypeDataItem dataItem : dataItemsForAll.values())
				{
					// 著录标志
					if (dataItem.getInputFlag() == true)
					{
						pDataItemsForInput.add(dataItem);
					}

					// 著录查询标志
					if (dataItem.getInputQueryFlag() == true)
					{
						pDataItemsForInputQuery.add(dataItem);
					}

					// 利用查询标志
					if (dataItem.getUseQueryFlag() == true)
					{
						pDataItemsForUseQuery.add(dataItem);
					}

					// 查询结果列表显示标志
					if (dataItem.getListDisplayFlag() == true)
					{
						pDataItemsForListDisplay.add(dataItem);
					}
				}

				// 著录项集合排序后添加至档案分类的对应属性中
				if (pDataItemsForInput.size() > 0)
				{
					Collections.sort(pDataItemsForInput, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.著录次序));

					for (ArchivesTypeDataItem item : pDataItemsForInput)
					{
						dataItemsForInput.put(item.getColumnName(), item);
					}
				}

				// 著录查询条件项集合排序后添加至档案分类的对应属性中
				if (pDataItemsForInputQuery.size() > 0)
				{
					Collections.sort(pDataItemsForInputQuery, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.著录查询次序));

					for (ArchivesTypeDataItem item : pDataItemsForInputQuery)
					{
						dataItemsForInputQuery.put(item.getColumnName(), item);
					}
				}

				// 利用查询条件项集合排序后添加至档案分类的对应属性中
				if (pDataItemsForUseQuery.size() > 0)
				{
					Collections.sort(pDataItemsForUseQuery, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.利用查询次序));

					for (ArchivesTypeDataItem item : pDataItemsForUseQuery)
					{
						dataItemsForUseQuery.put(item.getColumnName(), item);
					}
				}

				// 查询结果集列表显示项集合排序
				if (pDataItemsForListDisplay.size() > 0)
				{
					Collections.sort(pDataItemsForListDisplay, new ArchivesTypeDataItemComparator(EnumArchivesTypeDataItemComparatorType.列表显示次序));

					for (ArchivesTypeDataItem item : pDataItemsForListDisplay)
					{
						dataItemsForListDisplay.put(item.getColumnName(), item);
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
	 * 将保管期限字典信息转换为数据源的数据项集合，用于虚拟放置在全局数据源集合中的保管期限数据源中
	 * 
	 * @param retentionPeriods
	 *            保管期限字典信息
	 * @param retentionPeriodDataSourceID
	 *            保管期限的数据源编号
	 * @param retentionPeriodDataSourceItems
	 *            返回转换后的保管期限数据源的数据项集合
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean convertRetentionPeriods(LinkedHashMap<Integer, RetentionPeriod> retentionPeriods, int retentionPeriodDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> retentionPeriodDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查保管期限信息是否为空
			pErrPos = 1;
			if (retentionPeriods == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("保管期限信息非法为空。");
			}

			// 遍历逐个转换并添加至返回结果中
			if (pFlag)
			{
				if (retentionPeriods.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (RetentionPeriod item : retentionPeriods.values())
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), retentionPeriodDataSourceID, orderID, item.getName(), item.getRemark());
						retentionPeriodDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
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
	 * 将档案密级字典信息转换为数据源的数据项集合，用于虚拟放置在全局数据源集合中的档案密级数据源中
	 * 
	 * @param archivesSecrecys
	 *            档案密级字典信息
	 * @param archivesSecrecyDataSourceID
	 *            档案密级的数据源编号
	 * @param archivesSecrecyDataSourceItems
	 *            返回转换后的档案密级数据源的数据项集合
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean convertArchivesSecrecys(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys, int archivesSecrecyDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> archivesSecrecyDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查档案密级信息是否为空
			pErrPos = 1;
			if (archivesSecrecys == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案密级信息非法为空。");
			}

			// 遍历逐个转换并添加至返回结果中
			if (pFlag)
			{
				if (archivesSecrecys.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (ArchivesSecrecy item : archivesSecrecys.values())
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), archivesSecrecyDataSourceID, orderID, item.getName(), item.getRemark());
						archivesSecrecyDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
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
	 * 将部门字典信息转换为数据源的数据项集合，用于虚拟放置在全局数据源集合中的部门信息数据源中
	 * 
	 * @param departmentInfos
	 *            部门字典信息
	 * @param departmentInfoDataSourceID
	 *            档案密级的数据源编号
	 * @param departmentInfoDataSourceItems
	 *            返回转换后的档案密级数据源的数据项集合
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean convertDepartmentInfos(List<DepartmentInfo> departmentInfos, int departmentInfoDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> departmentInfoDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查部门字典信息是否为空
			pErrPos = 1;
			if (departmentInfos == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("部门字典信息非法为空。");
			}

			// 遍历逐个转换并添加至返回结果中
			if (pFlag)
			{
				if (departmentInfos.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (DepartmentInfo item : departmentInfos)
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), departmentInfoDataSourceID, orderID, item.getName(), item.getRemark());
						departmentInfoDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
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
	 * 将公文文种字典信息转换为数据源的数据项集合，用于虚拟放置在全局数据源集合中的公文文种数据源中
	 * 
	 * @param officialDocTypes
	 *            公文文种字典信息
	 * @param officalDocTypeDataSourceID
	 *            公文文种的数据源编号
	 * @param officialDocTypeDataSourceItems
	 *            返回转换后的公文文种数据源的数据项集合
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean convertOfficalDocTypes(LinkedHashMap<Integer, OfficialDocType> officialDocTypes, int officalDocTypeDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> officialDocTypeDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查公文文种字典信息是否为空
			pErrPos = 1;
			if (officialDocTypes == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("公文文种字典信息非法为空。");
			}

			// 遍历逐个转换并添加至返回结果中
			if (pFlag)
			{
				if (officialDocTypes.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (OfficialDocType item : officialDocTypes.values())
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), officalDocTypeDataSourceID, orderID, item.getName(), item.getRemark());
						officialDocTypeDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
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
	 * 将档案全宗字典信息转换为数据源的数据项集合，用于虚拟放置在全局数据源集合中的档案全宗数据源中
	 * 
	 * @param archivesFonds
	 *            档案全宗字典信息
	 * @param archivesFondsDataSourceID
	 *            档案全宗的数据源编号
	 * @param archivesFondsDataSourceItems
	 *            返回转换后的档案全宗数据源的数据项集合
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean convertArchivesFonds(List<ArchivesFonds> archivesFonds, int archivesFondsDataSourceID,
			LinkedHashMap<Integer, DataSourceItem> archivesFondsDataSourceItems, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			// 检查档案全宗字典信息是否为空
			pErrPos = 1;
			if (archivesFonds == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("档案全宗字典信息非法为空。");
			}

			// 遍历逐个转换并添加至返回结果中
			if (pFlag)
			{
				if (archivesFonds.size() > 0)
				{
					pErrPos = 2;
					int orderID = 0;
					for (ArchivesFonds item : archivesFonds)
					{
						orderID = orderID + 1;
						DataSourceItem dataSourceItem = new DataSourceItem(item.getID(), archivesFondsDataSourceID, orderID, item.getCode(), item.getName());
						archivesFondsDataSourceItems.put(dataSourceItem.getID(), dataSourceItem);
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
	
}
