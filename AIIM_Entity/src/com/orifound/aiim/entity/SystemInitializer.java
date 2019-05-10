/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统初始化器定义类
 * 
 */
public class SystemInitializer
{

	/**
	 * 构造函数
	 */
	private SystemInitializer()
	{

	}

	// 单例模式
	private static SystemInitializer instance = new SystemInitializer();

	/**
	 * 获取系统初始化器的实例（单例模式）	
	 * @return 系统初始化器的实例（单例模式）
	 */
	public static SystemInitializer getInstance()
	{
		return instance;
	}

	/**
	 * 原文电子文件的临时存储目录集合
	 */
	private List<Config> attachedFileTempSavePaths = null;

	/**
	 * 设置属性值：原文电子文件的临时存储目录集合
	 * 
	 * @param attachedFileTempSavePaths
	 *            原文电子文件的临时存储目录集合
	 */
	public void setAttachedFileTempSavePaths(List<Config> attachedFileTempSavePaths)
	{
		this.attachedFileTempSavePaths = attachedFileTempSavePaths;
	}

	/**
	 * 获取属性值：原文电子文件的临时存储目录集合
	 * 
	 * @return 原文电子文件的临时存储目录集合
	 */
	public List<Config> getAttachedFileTempSavePaths()
	{
		return attachedFileTempSavePaths;
	}
	
	/**
	 * 原文电子文件的永久存储目录集合
	 */
	private List<Config> attachedFileSavePaths = null;

	/**
	 * 设置属性值：原文电子文件的永久存储目录集合
	 * 
	 * @param attachedFileSavePaths
	 *            原文电子文件的永久存储目录集合
	 */
	public void setAttachedFileSavePaths(List<Config> attachedFileSavePaths)
	{
		this.attachedFileSavePaths = attachedFileSavePaths;
	}

	/**
	 * 获取属性值：原文电子文件的永久存储目录集合
	 * 
	 * @return 原文电子文件的永久存储目录集合
	 */
	public List<Config> getAttachedFileSavePaths()
	{
		return attachedFileSavePaths;
	}

	/**
	 * 原文电子文件PDF另存后的存储目录
	 */
	private List<Config> attachedFileReSavePath = null;

	/**
	 * 设置属性值：原文电子文件PDF另存后的存储目录集合
	 * 
	 * @param attachedFileReSavePath
	 *            原文电子文件PDF另存后的存储目录集合
	 */
	public void setAttachedFileReSavePath(List<Config> attachedFileReSavePath)
	{
		this.attachedFileReSavePath = attachedFileReSavePath;
	}

	/**
	 * 获取属性值：原文电子文件PDF另存后的存储目录集合
	 * 
	 * @return 原文电子文件PDF另存后的存储目录集合
	 */
	public List<Config> getAttachedFileReSavePath()
	{
		return attachedFileReSavePath;
	}

	/**
	 * 系统的Logo图片文件的名称
	 */
	private String systemLogoFileName = "";

	/**
	 * 设置属性值：系统的Logo图片文件的名称
	 * 
	 * @param systemLogoFileName
	 *            系统的Logo图片文件的名称
	 */
	public void setSystemLogoFileName(String systemLogoFileName)
	{
		this.systemLogoFileName = systemLogoFileName;
	}

	/**
	 * 获取属性值：系统的Logo图片文件的名称
	 * 
	 * @return 系统的Logo图片文件的名称
	 */
	public String getSystemLogoFileName()
	{
		return systemLogoFileName;
	}

	/**
	 * 每页显示的记录数量
	 */
	private int pageViewRecordsCount = 10;

	/**
	 * 设置属性值：每页显示的记录数量
	 * 
	 * @param pageViewRecordsCount
	 *            每页显示的记录数量
	 */
	public void setPageViewRecordsCount(int pageViewRecordsCount)
	{
		this.pageViewRecordsCount = pageViewRecordsCount;
	}

	/**
	 * 获取属性值：每页显示的记录数量
	 * 
	 * @return 每页显示的记录数量
	 */
	public int getPageViewRecordsCount()
	{
		return pageViewRecordsCount;
	}

	/**
	 * 档案分类集合（仅一级分类），树状结构的档案分类集合，可以访问其下属档案分类
	 */
	private LinkedHashMap<Integer, ArchivesType> archivesTypes =null;

	/**
	 * 设置属性值：档案分类集合（仅一级分类），树状结构的档案分类集合，可以访问其下属档案分类
	 * 
	 * @param archivesTypes
	 *            档案分类集合（仅一级分类），树状结构的档案分类集合，可以访问其下属档案分类
	 */
	public void setArchivesTypes(LinkedHashMap<Integer, ArchivesType> archivesTypes)
	{
		this.archivesTypes = archivesTypes;
	}

	/**
	 * 获取属性值：档案分类集合（仅一级分类），树状结构的档案分类集合，可以访问其下属档案分类
	 * 
	 * @return 档案分类集合（仅一级分类），树状结构的档案分类集合，可以访问其下属档案分类
	 */
	public LinkedHashMap<Integer, ArchivesType> getArchivesTypes()
	{
		return archivesTypes;
	}
	
	/**
	 * 平面结构的档案分类集合，所有档案分类都无层次关系的直接存放在集合中
	 */
	private Map<Integer, ArchivesType> planeArchivesTypes = null;

	/**
	 * 设置属性值：平面结构的档案分类集合，所有档案分类都无层次关系的直接存放在集合中
	 * @param camelPropertyName 平面结构的档案分类集合，所有档案分类都无层次关系的直接存放在集合中
	 */
	public void setPlaneArchivesTypes(Map<Integer, ArchivesType> planeArchivesTypes)
	{
		this.planeArchivesTypes = planeArchivesTypes;
	}

	/**
	 * 获取属性值：平面结构的档案分类集合，所有档案分类都无层次关系的直接存放在集合中
	 * @return 平面结构的档案分类集合，所有档案分类都无层次关系的直接存放在集合中
	 */
	public Map<Integer, ArchivesType> getPlaneArchivesTypes()
	{
		return planeArchivesTypes;
	}

	/**
	 * 公文档案分类集合
	 */
	private LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = null;

	/**
	 * 设置属性值：公文档案分类集合
	 * @param officialArchivesTypes 公文档案分类集合
	 */
	public void setOfficialArchivesTypes(LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes)
	{
		this.officialArchivesTypes = officialArchivesTypes;
	}

	/**
	 * 获取属性值：公文档案分类集合
	 * @return 公文档案分类集合
	 */
	public LinkedHashMap<Integer, OfficialArchivesType> getOfficialArchivesTypes()
	{
		return officialArchivesTypes;
	}

	/**
	 * 公文文种字典集合
	 */
	private LinkedHashMap<Integer, OfficialDocType> officialDocTypes = null;

	/**
	 * 设置属性值：公文文种字典集合
	 * @param officialDocTypes 公文文种字典集合
	 */
	public void setOfficialDocTypes(LinkedHashMap<Integer, OfficialDocType> officialDocTypes)
	{
		this.officialDocTypes = officialDocTypes;
	}

	/**
	 * 获取属性值：公文文种字典集合
	 * @return 公文文种字典集合
	 */
	public LinkedHashMap<Integer, OfficialDocType> getOfficialDocTypes()
	{
		return officialDocTypes;
	}

	/**
	 * 档案全宗集合
	 */
	private List<ArchivesFonds> archivesFondss = null;

	/**
	 * 设置属性值：档案全宗集合
	 * 
	 * @param archivesFondss
	 *            档案全宗集合
	 */
	public void setArchivesFonds(List<ArchivesFonds> archivesFondss)
	{
		this.archivesFondss = archivesFondss;
	}

	/**
	 * 获取属性值：档案全宗集合
	 * 
	 * @return 档案全宗集合
	 */
	public List<ArchivesFonds> getArchivesFondss()
	{
		return archivesFondss;
	}
	
	/**
	 * 数据类型字典集合，以类型编号作为关键字
	 */
	private Map<String, DataType> dataTypes = null;

	/**
	 * 设置属性值：数据类型字典集合，以类型编号作为关键字
	 * @param dataTypes 数据类型字典，以类型编号作为关键字
	 */
	public void setDataTypes(Map<String, DataType> dataTypes)
	{
		this.dataTypes = dataTypes;
	}

	/**
	 * 获取属性值：数据类型字典集合，以类型编号作为关键字
	 * @return 数据类型字典，以类型编号作为关键字
	 */
	public Map<String, DataType> getDataTypes()
	{
		return dataTypes;
	}

	/**
	 * 数据源字典集合，以数据源编号作为关键字
	 */
	private Map<Integer, DataSource> dataSources = null;

	/**
	 * 设置属性值：数据源字典集合，以数据源编号作为关键字
	 * @param dataSources 数据源字典集合，以数据源编号作为关键字
	 */
	public void setDataSources(Map<Integer, DataSource> dataSources)
	{
		this.dataSources = dataSources;
	}

	/**
	 * 获取属性值：数据源字典集合，以数据源编号作为关键字
	 * @return 数据源字典集合，以数据源编号作为关键字
	 */
	public Map<Integer, DataSource> getDataSources()
	{
		return dataSources;
	}

	/**
	 * 档案密级字典集合，以密级编号作为集合关键字
	 */
	private LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys = null;

	/**
	 * 设置属性值：档案密级字典集合，以密级编号作为集合关键字
	 * @param archivesSecrecys 档案密级字典集合，以密级编号作为集合关键字
	 */
	public void setArchivesSecrecys(LinkedHashMap<Integer, ArchivesSecrecy> archivesSecrecys)
	{
		this.archivesSecrecys = archivesSecrecys;
	}

	/**
	 * 获取属性值：档案密级字典集合，以密级编号作为集合关键字
	 * @return 档案密级字典集合，以密级编号作为集合关键字
	 */
	public LinkedHashMap<Integer, ArchivesSecrecy> getArchivesSecrecys()
	{
		return archivesSecrecys;
	}
	
	/**
	 * 档案部门信息集合
	 */
	private List<DepartmentInfo> departmentInfos = null;

	/**
	 * 设置属性值：档案部门信息集合
	 * @param departmentInfos 档案部门信息集合
	 */
	public void setDepartmentInfos(List<DepartmentInfo> departmentInfos)
	{
		this.departmentInfos = departmentInfos;
	}

	/**
	 * 获取属性值：档案部门信息集合
	 * @return 档案部门信息集合
	 */
	public List<DepartmentInfo> getDepartmentInfos()
	{
		return departmentInfos;
	}

	/**
	 * 校验规则字典集合，以规则编号作为关键字
	 */
	private Map<Integer, CheckRule> checkRules = null;

	/**
	 * 设置属性值：校验规则字典集合，以规则编号作为关键字
	 * @param checkRules 校验规则字典集合，以规则编号作为关键字
	 */
	public void setCheckRules(Map<Integer, CheckRule> checkRules)
	{
		this.checkRules = checkRules;
	}

	/**
	 * 获取属性值：校验规则字典集合，以规则编号作为关键字
	 * @return 校验规则字典集合，以规则编号作为关键字
	 */
	public Map<Integer, CheckRule> getCheckRules()
	{
		return checkRules;
	}

	/**
	 * 保管期限字典集合
	 */
	private LinkedHashMap<Integer, RetentionPeriod> retentionPeriods = null;

	/**
	 * 设置属性值：保管期限字典集合
	 * @param retentionPeriod 保管期限字典集合
	 */
	public void setRetentionPeriods(LinkedHashMap<Integer, RetentionPeriod> retentionPeriods)
	{
		this.retentionPeriods = retentionPeriods;
	}

	/**
	 * 获取属性值：保管期限字典集合
	 * @return 保管期限字典集合
	 */
	public LinkedHashMap<Integer, RetentionPeriod> getRetentionPeriods()
	{
		return retentionPeriods;
	}

	/**
	 * 绩效考评项目字典表集合
	 */
	private List<EvaluateItem> evaluateItems = null;

	/**
	 * 获取属性值：绩效考评项目字典表集合
	 * @return 绩效考评项目字典表集合
	 */
	public List<EvaluateItem> getEvaluateItems() {
		return evaluateItems;
	}

	/**
	 * 设置属性值：绩效考评项目字典表集合
	 * @param evaluateItems 绩效考评项目字典表集合
	 */
	public void setEvaluateItems(List<EvaluateItem> evaluateItems) {
		this.evaluateItems = evaluateItems;
	}

	/**
	 * 考核等级字典表 集合
	 */
	private List<EvaluateLevel> evaluateLevels = null;

	/**
	 * 设置属性值：考核等级字典表 集合
	 * @param evaluateLevels 考核等级字典表 集合
	 */
	public void setEvaluateLevels(List<EvaluateLevel> evaluateLevels) {
		this.evaluateLevels = evaluateLevels;
	}

	/**
	 * 获取属性值：考核等级字典表 集合
	 * @return 考核等级字典表 集合
	 */
	public List<EvaluateLevel> getEvaluateLevels() {
		return evaluateLevels;
	}

	/**
	 * 职务信息数据字典表 集合
	 */
	private List<Duty> duties = null;

	/**
	 * 获取属性值：职务信息数据字典表 集合
	 * @return 职务信息数据字典表 集合
	 */
	public List<Duty> getDuties() {
		return duties;
	}

	/**
	 * 设置属性值：职务信息数据字典表 集合
	 * @param duties 职务信息数据字典表 集合
	 */
	public void setDuties(List<Duty> duties) {
		this.duties = duties;
	}
	
	/**
	 * 档案公开密级数据字典信息
	 */
	private ArchivesSecrecy openArchivesSecrecy = null;

	/**
	 * 获取属性值：档案公开密级字典信息
	 * @return 档案公开密级字典信息
	 */
	public ArchivesSecrecy getOpenArchivesSecrecy() {
		return openArchivesSecrecy;
	}

	/**
	 * 设置属性值：档案公开密级字典信息
	 * @param archivesSecrecy 档案公开密级字典信息
	 */
	public void setOpenArchivesSecrecy(ArchivesSecrecy openArchivesSecrecy) {
		this.openArchivesSecrecy = openArchivesSecrecy;
	}
	
	/**
	 * 用户角色字典表的实体集合
	 */
	private List<UserRole> userRoles = null;

	/**
	 * 获取属性值：用户角色字典表的实体集合
	 * @return 用户角色字典表的实体集合
	 */
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * 设置属性值：用户角色字典表的实体集合
	 * @param userRoles 用户角色字典表的实体集合
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	/**
	 * 永久保管期限
	 */
	private RetentionPeriod foreverRetentionPeriod ;

	/**
	 * 获取属性值：永久保管期限
	 * @return 永久保管期限
	 */
	public RetentionPeriod getForeverRetentionPeriod() {
		return foreverRetentionPeriod;
	}

	/**
	 * 设置属性值：永久保管期限
	 * @param foreverRetentionPeriod 永久保管期限
	 */
	public void setForeverRetentionPeriod(RetentionPeriod foreverRetentionPeriod) {
		this.foreverRetentionPeriod = foreverRetentionPeriod;
	}
	
	/**
	 * 匿名用户角色编号
	 */
	private int anonymouseUserRoleID = 0;

	/**
	 * 获取匿名用户角色编号
	 */
	public int getAnonymouseUserRoleID() {
		return anonymouseUserRoleID;
	}

	/**
	 * 设置匿名用户角色编号
	 */
	public void setAnonymouseUserRoleID(int anonymouseUserRoleID) {
		this.anonymouseUserRoleID = anonymouseUserRoleID;
	}
	
	
	
}
