/**
 * 
 */
package com.orifound.aiim.bll.service.impl.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
import com.orifound.aiim.bll.service.impl.UserRolesInfoManageServiceImpl;
import com.orifound.aiim.bll.util.CommonUtil;
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
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ArchivesInfoTable;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.CatalogDataItem;
import com.orifound.aiim.entity.CheckRule;
import com.orifound.aiim.entity.DataItem;
import com.orifound.aiim.entity.DataSource;
import com.orifound.aiim.entity.DataSourceItem;
import com.orifound.aiim.entity.EnumCatalogType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * 系统初始化服务测试类
 *
 */
public class SystemInitializeServiceImplTest
{

	//业务逻辑实现类
	private static SystemInitializeServiceImpl ServiceImpl = new SystemInitializeServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
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
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		ServiceImpl = null;
		pErrInfo = null;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		pErrInfo = new ErrInfo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		pErrInfo = null;
	}
	
	/**
	 * 测试克隆档案分类树
	 */
	@Test
	public void testCloneArchivesTypes()
	{
		LinkedHashMap<Integer, ArchivesType> clonedArchivesTypes=new LinkedHashMap<Integer, ArchivesType>();
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("==========原始树状结构的档案分类信息=================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("==========克隆后的树状结构档案分类信息=================");
			
			if (systemInitializer.getArchivesTypes()!=null)
			{
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					clonedArchivesTypes.put(item.getID(), item.clone());
				}
				
				System.out.println("树状档案分类信息: " +clonedArchivesTypes.size()+" 个");
				for (ArchivesType item : clonedArchivesTypes.values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("==========克隆后的树状结构档案分类信息移除1个1级分类后=================");
			if (clonedArchivesTypes!=null)
			{
				clonedArchivesTypes.remove(1);
				System.out.println("树状档案分类信息: " +clonedArchivesTypes.size()+" 个");
				for (ArchivesType item : clonedArchivesTypes.values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("==========原始树状结构的档案分类信息=================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
		}
	}
	
	
	/**
	 * 测试平面底层档案分类节点集合转换为树状档案分类集合
	 */
	@Test
	public void testConvertPlaneArchivesTypesToTree()
	{
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("==========原始树状结构的档案分类信息=================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			if (systemInitializer.getArchivesTypes()!=null)
			{
				Map<Integer, ArchivesType> planeArchivesTypes=new HashMap<Integer, ArchivesType>();
				planeArchivesTypes.put(11, new ArchivesType(11));
				planeArchivesTypes.put(3, new ArchivesType(3));
				planeArchivesTypes.put(5, new ArchivesType(5));
				planeArchivesTypes.put(6, new ArchivesType(6));
				
				LinkedHashMap<Integer, ArchivesType> treeArchivesTypes=new LinkedHashMap<Integer, ArchivesType>();
				if (CommonUtil.convertPlaneArchivesTypesToTree(planeArchivesTypes, treeArchivesTypes, pErrInfo)==false)
				{
					fail(pErrInfo.toString());
				}
				else 
				{
					System.out.println("==========转换后的树状结构档案分类信息=================");
					System.out.println("转换得到的树状档案分类信息: " +treeArchivesTypes.size()+" 个");
					for (ArchivesType item : treeArchivesTypes.values())
					{
						System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
						
						if (item.getChildArchivesTypes()!=null)
						{
							for (ArchivesType item1 : item.getChildArchivesTypes().values())
							{
								System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
								
								if (item1.getChildArchivesTypes()!=null)
								{
									for (ArchivesType item2 : item1.getChildArchivesTypes().values())
									{
										System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
										
									}
								}
							}
						}
						
						System.out.println("");
					}
				}
			}
		}
	}
	
	/**
	 * 测试克隆档案分类树
	 */
	@Test
	public void testCloneArchivesTypesToEx()
	{
		LinkedHashMap<Integer, ArchivesTypeEx> clonedArchivesTypeExs=new LinkedHashMap<Integer, ArchivesTypeEx>();
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("==========原始树状结构的档案分类信息=================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("==========克隆后的树状结构档案分类信息=================");
			
			if (systemInitializer.getArchivesTypes()!=null)
			{
				if (CommonUtil.cloneSystemArchivesTypesToEx(clonedArchivesTypeExs, pErrInfo)==false)
				{
					fail(pErrInfo.toString());
				}
				
				System.out.println("扩展树状档案分类信息: " +clonedArchivesTypeExs.size()+" 个");
				for (ArchivesTypeEx item : clonedArchivesTypeExs.values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypeExs()!=null)
					{
						for (ArchivesTypeEx item1 : item.getChildArchivesTypeExs().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypeExs()!=null)
							{
								for (ArchivesTypeEx item2 : item1.getChildArchivesTypeExs().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("==========克隆后的树状结构档案分类信息移除1个1级分类后=================");
			if (clonedArchivesTypeExs!=null)
			{
				clonedArchivesTypeExs.remove(1);
				System.out.println("树状档案分类信息: " +clonedArchivesTypeExs.size()+" 个");
				for (ArchivesTypeEx item : clonedArchivesTypeExs.values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypeExs()!=null)
					{
						for (ArchivesTypeEx item1 : item.getChildArchivesTypeExs().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesTypeEx item2 : item1.getChildArchivesTypeExs().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("==========原始树状结构的档案分类信息=================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
		}
	}
	
	/**
	 * 测试平面底层扩展档案分类节点集合转换为树状扩展档案分类集合
	 */
	@Test
	public void testConvertPlaneArchivesTypeExsToTree()
	{
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("==========原始树状结构的档案分类信息=================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			if (systemInitializer.getArchivesTypes()!=null)
			{
				Map<Integer, ArchivesTypeEx> planeArchivesTypeExs=new HashMap<Integer, ArchivesTypeEx>();
				planeArchivesTypeExs.put(11, new ArchivesTypeEx(11));
				planeArchivesTypeExs.put(3, new ArchivesTypeEx(3));
				planeArchivesTypeExs.put(5, new ArchivesTypeEx(5));
				planeArchivesTypeExs.put(6, new ArchivesTypeEx(6));
				planeArchivesTypeExs.get(11).setQueryCrossClassifiedResultRecordCount(110);
				planeArchivesTypeExs.get(3).setQueryCrossClassifiedResultRecordCount(30);
				planeArchivesTypeExs.get(5).setQueryCrossClassifiedResultRecordCount(50);
				planeArchivesTypeExs.get(6).setQueryCrossClassifiedResultRecordCount(60);
				
				LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs=new LinkedHashMap<Integer, ArchivesTypeEx>();
				if (CommonUtil.convertPlaneArchivesTypeExsToTree(planeArchivesTypeExs, treeArchivesTypeExs, pErrInfo)==false)
				{
					fail(pErrInfo.toString());
				}
				else 
				{
					System.out.println("==========转换后的树状结构扩展档案分类信息=================");
					System.out.println("转换得到的树状扩展档案分类信息: " +treeArchivesTypeExs.size()+" 个");
					for (ArchivesTypeEx item : treeArchivesTypeExs.values())
					{
						System.out.println("--"+item.getFullCode()+","+item.getFullName()+",ID: "+item.getID()+",QueryCrossClassifiedResultRecordCount: "+item.getQueryCrossClassifiedResultRecordCount());
						
						if (item.getChildArchivesTypeExs()!=null)
						{
							for (ArchivesTypeEx item1 : item.getChildArchivesTypeExs().values())
							{
								System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",ID: "+item1.getID()+",QueryCrossClassifiedResultRecordCount: "+item1.getQueryCrossClassifiedResultRecordCount());
								
								if (item1.getChildArchivesTypeExs()!=null)
								{
									for (ArchivesTypeEx item2 : item1.getChildArchivesTypeExs().values())
									{
										System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",ID: "+item2.getID()+",QueryCrossClassifiedResultRecordCount: "+item2.getQueryCrossClassifiedResultRecordCount());
										
									}
								}
							}
						}
						
						System.out.println("");
					}
				}
			}
		}
	}

	/**
	 * 测试系统初始化
	 * Test method for {@link com.orifound.aiim.bll.service.impl.SystemInitializeServiceImpl#initialize(com.orifound.aiim.entity.SystemInitializer, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testInitialize()
	{
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (ServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			//显示全宗信息
			if (systemInitializer.getArchivesFondss()!=null)
			{
				System.out.println("全宗信息: "+systemInitializer.getArchivesFondss().size()+" 个");
				for (ArchivesFonds item : systemInitializer.getArchivesFondss())
				{
					System.out.println("--"+item.getName());
				}
			}
			
			System.out.println("======================================================");
			
			//显示树状结构的档案分类信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("树状档案分类信息: " +systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName());
									
								}
							}
						}
					}
					
					System.out.println("");
				}
			}
			
			System.out.println("======================================================");
			
			//显示平面结构的档案分类信息
			if (systemInitializer.getPlaneArchivesTypes()!=null)
			{
				System.out.println("平面档案分类信息: " +systemInitializer.getPlaneArchivesTypes().size()+" 个");
				for (ArchivesType item : systemInitializer.getPlaneArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName());
				}
			}
			
			System.out.println("======================================================");
			
			//显示档案分类的相关表信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("档案分类主表信息:");
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					if (archivesType.getArchivesInfoTables()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的档案分类主表信息:");
						for (ArchivesInfoTable item :archivesType.getArchivesInfoTables().values())
						{
							System.out.println("----"+item.getTableType().toString()+": "+item.getTableName());
						}
						System.out.println("");
					}
				}
			}
			
			System.out.println("======================================================");
			
			//显示档案分类的数据项信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("档案分类的数据项信息: "+systemInitializer.getArchivesTypes().size()+" 个");
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					//所有数据项
					if (archivesType.getDataItemsForAll()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的档案分类所有数据项信息:"+archivesType.getDataItemsForAll().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForAll().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//著录数据项
					if (archivesType.getDataItemsForInput()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的档案分类著录数据项信息:"+archivesType.getDataItemsForInput().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInput().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//著录查询数据项
					if (archivesType.getDataItemsForInputQuery()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的档案分类著录查询数据项信息:"+archivesType.getDataItemsForInputQuery().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInputQuery().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//利用查询数据项
					if (archivesType.getDataItemsForUseQuery()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的档案分类利用查询数据项信息:"+archivesType.getDataItemsForUseQuery().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//结果集列表显示数据项
					if (archivesType.getDataItemsForListDisplay()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的档案分类结果集列表显示数据项信息:"+archivesType.getDataItemsForUseQuery().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					if (archivesType.getChildArchivesTypes()!=null)
					{
						if (archivesType.getChildArchivesTypes().size()>0)
						{
							ArrayList<ArchivesType> templist=new ArrayList<ArchivesType>(archivesType.getChildArchivesTypes().values());
							if (templist.get(0).getDataItemsForAll()!=null)
							{
								System.out.println("++ " +templist.get(0).getFullCode()+"  所有项数量: "+templist.get(0).getDataItemsForAll().size());
							}
							
							if (templist.get(0).getDataItemsForInput()!=null)
								System.out.println("++ " +templist.get(0).getFullCode()+"  著录项数量: "+templist.get(0).getDataItemsForInput().size());
							
							if (templist.get(0).getDataItemsForListDisplay()!=null)
								System.out.println("++ " +templist.get(0).getFullCode()+"  列表显示项数量: "+templist.get(0).getDataItemsForListDisplay().size());
							
							
						}
					}
				}
				
				
			}
			

			System.out.println("======================================================");
			
			//显示档案分类的目录打印模板的数据项信息
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("各档案分类的目录打印模板信息: ");
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					if (archivesType.getCatalogPrintTemplates()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"的目录打印模板信息:");
						for (EnumCatalogType catalogType : EnumCatalogType.values())
						{
							if (archivesType.getCatalogPrintTemplates().containsKey(catalogType))
							{
								System.out.println("--"+catalogType.toString()+" 的数据项信息：");
								for (CatalogDataItem item : archivesType.getCatalogPrintTemplates().get(catalogType).values())
								{
									System.out.println("----"+item.getColumnName()+","+item.getDisplayText()+","+item.getDisplayAlias());
								}
							}
						}
						System.out.println("");
					}
				}
			}
			
			System.out.println("======================================================");
			
			//显示公文档案分类的数据项信息
			if (systemInitializer.getOfficialArchivesTypes()!=null)
			{
				System.out.println("公文档案分类的数据项信息: "+systemInitializer.getOfficialArchivesTypes().size()+" 个");
				for (OfficialArchivesType archivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					//所有数据项
					if (archivesType.getDataItemsForAll()!=null)
					{
						System.out.println("--"+archivesType.getName()+"的档案分类所有数据项信息:"+archivesType.getDataItemsForAll().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForAll().values())
						{
							System.out.println("----"+item.getID()+","+ item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//著录数据项
					if (archivesType.getDataItemsForInput()!=null)
					{
						System.out.println("--"+archivesType.getName()+"的档案分类著录数据项信息:"+archivesType.getDataItemsForInput().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInput().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//著录查询数据项
					if (archivesType.getDataItemsForInputQuery()!=null)
					{
						System.out.println("--"+archivesType.getName()+"的档案分类著录查询数据项信息:"+archivesType.getDataItemsForInputQuery().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInputQuery().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//利用查询数据项
					if (archivesType.getDataItemsForUseQuery()!=null)
					{
						System.out.println("--"+archivesType.getName()+"的档案分类利用查询数据项信息:"+archivesType.getDataItemsForUseQuery().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//结果集列表显示数据项
					if (archivesType.getDataItemsForListDisplay()!=null)
					{
						System.out.println("--"+archivesType.getName()+"的档案分类结果集列表显示数据项信息:"+archivesType.getDataItemsForUseQuery().size()+" 个");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
				}
				
				
			}
			
			System.out.println("======================================================");
			
			//显示保管期限信息
			if (systemInitializer.getRetentionPeriods()!=null)
			{
				System.out.println("保管期限字典信息: "+ systemInitializer.getRetentionPeriods().size() +" 个");
				for (RetentionPeriod item : systemInitializer.getRetentionPeriods().values())
				{
					System.out.println("--"+item.getName());
				}
			}
			
			System.out.println("======================================================");
			
			//显示档案密级信息
			if (systemInitializer.getArchivesSecrecys()!=null)
			{
				System.out.println("档案密级字典信息: " +systemInitializer.getArchivesSecrecys().size() +" 个");
				for (ArchivesSecrecy item : systemInitializer.getArchivesSecrecys().values())
				{
					System.out.println("--"+item.getName());
				}
			}
			
			System.out.println("======================================================");
			
			//显示数据源字典信息
			if (systemInitializer.getDataSources()!=null)
			{
				System.out.println("数据源字典信息: "+systemInitializer.getDataSources().size()+" 个");
				for (DataSource dataSource : systemInitializer.getDataSources().values())
				{
					if (dataSource.getDataSourceItems()!=null)
					{
						System.out.println("--"+dataSource.getName()+"的数据项信息: "+dataSource.getDataSourceItems().size() +" 个");
						for (DataSourceItem item : dataSource.getDataSourceItems().values())
						{
							System.out.println("----"+item.getOrderID()+":"+item.getName());
						}
						System.out.println("");
					}
				}
			}
			
			System.out.println("======================================================");
			
			//显示校验规则字典信息
			if (systemInitializer.getCheckRules()!=null)
			{
				System.out.println("校验规则字典信息: " +systemInitializer.getCheckRules().size()+" 个");
				for (CheckRule item : systemInitializer.getCheckRules().values())
				{
					System.out.println("--"+item.getName());
				}
			}
			
		}
	}

}
