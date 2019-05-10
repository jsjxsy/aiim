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
 * ϵͳ��ʼ�����������
 *
 */
public class SystemInitializeServiceImplTest
{

	//ҵ���߼�ʵ����
	private static SystemInitializeServiceImpl ServiceImpl = new SystemInitializeServiceImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//ҵ���߼�����ע��
		
		//����ȫ��
		ArchivesFondsManageServiceImpl archivesFondsManageServiceImpl=new ArchivesFondsManageServiceImpl();
		archivesFondsManageServiceImpl.setArchivesFondsDao(new ArchivesFondsDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesFondsManageService(archivesFondsManageServiceImpl);
		
		//���������Ϣ�����������
		ArchivesInfoTableManageServiceImpl archivesInfoTableManageServiceImpl=new ArchivesInfoTableManageServiceImpl();
		archivesInfoTableManageServiceImpl.setArchivesInfoTableDao(new ArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesInfoTableManageService(archivesInfoTableManageServiceImpl);
		
		//�����������������
		ArchivesTypeManageServiceImpl archivesTypeManageServiceImpl=new ArchivesTypeManageServiceImpl();
		archivesTypeManageServiceImpl.setArchivesTypeDao(new ArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeManageService(archivesTypeManageServiceImpl);
		
		//�����������������������
		ArchivesTypeDataItemManageServiceImpl archivesTypeDataItemManageServiceImpl=new ArchivesTypeDataItemManageServiceImpl();
		archivesTypeDataItemManageServiceImpl.setArchivesTypeDataItemDao(new ArchivesTypeDataItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesTypeDataItemManageService(archivesTypeDataItemManageServiceImpl);
		
		//����Դ����������
		DataSourceManageServiceImpl dataSourceManageServiceImpl=new DataSourceManageServiceImpl();
		dataSourceManageServiceImpl.setDataSourceDao(new DataSourceDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDataSourceManageService(dataSourceManageServiceImpl);
		
		//����Դ�����������������
		DataSourceItemManageServiceImpl dataSourceItemManageServiceImpl=new DataSourceItemManageServiceImpl();
		dataSourceItemManageServiceImpl.setDataSourceItemDao(new DataSourceItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDataSourceItemManageService(dataSourceItemManageServiceImpl);
		
		//У��������������
		CheckRuleManageServiceImpl checkRuleManageServiceImpl=new CheckRuleManageServiceImpl();
		checkRuleManageServiceImpl.setCheckRuleDao(new CheckRuleDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setCheckRuleManageService(checkRuleManageServiceImpl);
		
		//�������޹���������
		RetentionPeriodManageServiceImpl retentionPeriodManageServiceImpl=new RetentionPeriodManageServiceImpl(new RetentionPeriodDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setRetentionPeriodManageService(retentionPeriodManageServiceImpl);
		
		//�����ܼ�����������
		ArchivesSecrecyManageServiceImpl archivesSecrecyManageServiceImpl=new ArchivesSecrecyManageServiceImpl(new ArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setArchivesSecrecyManageService(archivesSecrecyManageServiceImpl);
		
		//������Ϣ����������
		DepartmentInfoManageServiceImpl departmentInfoManageServiceImpl=new DepartmentInfoManageServiceImpl(new DepartmentInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDepartmentInfoManageService(departmentInfoManageServiceImpl);
		
		//������Ϣ����������
		ConfigManageServiceImpl configManageServiceImpl=new ConfigManageServiceImpl(new ConfigDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setConfigManageService(configManageServiceImpl);
		
		//����������Ϣ����������
		OfficialDocTypeManageServiceImpl officialDocTypeManageServiceImpl=new OfficialDocTypeManageServiceImpl(new OfficialDocTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialDocTypeManageService(officialDocTypeManageServiceImpl);
		
		//���ĵ���������Ϣ����������
		OfficialArchivesTypeManageServiceImpl officialArchivesTypeManageServiceImpl=new OfficialArchivesTypeManageServiceImpl(new OfficialArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesTypeManageService(officialArchivesTypeManageServiceImpl);
		
		//���ĵ������������Ϣ�����������
		OfficialArchivesInfoTableManageServiceImpl officialArchivesInfoTableManageServiceImpl=new OfficialArchivesInfoTableManageServiceImpl(new OfficialArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesInfoTableManageService(officialArchivesInfoTableManageServiceImpl);
		
		//������Ŀ�ֵ�����������������ע��
		EvaluateItemManageServiceImpl evaluateItemManageServiceImpl=new EvaluateItemManageServiceImpl(new EvaluateItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setEvaluateItemManageService(evaluateItemManageServiceImpl);
		
		//���˵ȼ��ֵ����������������ע��
		EvaluateLevelManageServiceImpl evaluateLevelManageServiceImpl=new EvaluateLevelManageServiceImpl(new EvaluateLevelDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setEvaluateLevelManageService(evaluateLevelManageServiceImpl);
		
		//ְ����Ϣ�����ֵ������������ע��
		DutyManageServiceImpl dutyManageServiceImpl=new DutyManageServiceImpl(new DutyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setDutyManageService(dutyManageServiceImpl);
		
		//���ĵ������ͱ���ӳ�������������ע��
		OfficialArchivesTypeSavedMappingManageServiceImpl officialArchivesTypeSavedMappingManageServiceImpl=new OfficialArchivesTypeSavedMappingManageServiceImpl(new OfficialArchivesTypeSavedMappingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesTypeSavedMappingManageService(officialArchivesTypeSavedMappingManageServiceImpl);
		
		//���ĵ���������鵵ӳ����������������ע��
		OfficialArchivesDataItemSavedMappingManageServiceImpl officialArchivesDataItemSavedMappingManageServiceImpl=new OfficialArchivesDataItemSavedMappingManageServiceImpl(new OfficialArchivesDataItemSavedMappingDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setOfficialArchivesDataItemSavedMappingManageService(officialArchivesDataItemSavedMappingManageServiceImpl);
		
		//�û���ɫ��Ϣ�ֵ������������ע��
		UserRoleManageServiceImpl userRolesInfoManageServiceImpl=new UserRoleManageServiceImpl(new UserRoleDaoimpl(AIIMDataSource.getInstance().getDataSource()));
		ServiceImpl.setUserRoleManageService(userRolesInfoManageServiceImpl);
		
		//Ŀ¼��ӡģ�����������������������ע��
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
	 * ���Կ�¡����������
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
			System.out.println("==========ԭʼ��״�ṹ�ĵ���������Ϣ=================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
			
			System.out.println("==========��¡�����״�ṹ����������Ϣ=================");
			
			if (systemInitializer.getArchivesTypes()!=null)
			{
				for (ArchivesType item : systemInitializer.getArchivesTypes().values())
				{
					clonedArchivesTypes.put(item.getID(), item.clone());
				}
				
				System.out.println("��״����������Ϣ: " +clonedArchivesTypes.size()+" ��");
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
			
			System.out.println("==========��¡�����״�ṹ����������Ϣ�Ƴ�1��1�������=================");
			if (clonedArchivesTypes!=null)
			{
				clonedArchivesTypes.remove(1);
				System.out.println("��״����������Ϣ: " +clonedArchivesTypes.size()+" ��");
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
			
			System.out.println("==========ԭʼ��״�ṹ�ĵ���������Ϣ=================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
	 * ����ƽ��ײ㵵������ڵ㼯��ת��Ϊ��״�������༯��
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
			System.out.println("==========ԭʼ��״�ṹ�ĵ���������Ϣ=================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
					System.out.println("==========ת�������״�ṹ����������Ϣ=================");
					System.out.println("ת���õ�����״����������Ϣ: " +treeArchivesTypes.size()+" ��");
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
	 * ���Կ�¡����������
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
			System.out.println("==========ԭʼ��״�ṹ�ĵ���������Ϣ=================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
			
			System.out.println("==========��¡�����״�ṹ����������Ϣ=================");
			
			if (systemInitializer.getArchivesTypes()!=null)
			{
				if (CommonUtil.cloneSystemArchivesTypesToEx(clonedArchivesTypeExs, pErrInfo)==false)
				{
					fail(pErrInfo.toString());
				}
				
				System.out.println("��չ��״����������Ϣ: " +clonedArchivesTypeExs.size()+" ��");
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
			
			System.out.println("==========��¡�����״�ṹ����������Ϣ�Ƴ�1��1�������=================");
			if (clonedArchivesTypeExs!=null)
			{
				clonedArchivesTypeExs.remove(1);
				System.out.println("��״����������Ϣ: " +clonedArchivesTypeExs.size()+" ��");
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
			
			System.out.println("==========ԭʼ��״�ṹ�ĵ���������Ϣ=================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
	 * ����ƽ��ײ���չ��������ڵ㼯��ת��Ϊ��״��չ�������༯��
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
			System.out.println("==========ԭʼ��״�ṹ�ĵ���������Ϣ=================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
					System.out.println("==========ת�������״�ṹ��չ����������Ϣ=================");
					System.out.println("ת���õ�����״��չ����������Ϣ: " +treeArchivesTypeExs.size()+" ��");
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
	 * ����ϵͳ��ʼ��
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
			//��ʾȫ����Ϣ
			if (systemInitializer.getArchivesFondss()!=null)
			{
				System.out.println("ȫ����Ϣ: "+systemInitializer.getArchivesFondss().size()+" ��");
				for (ArchivesFonds item : systemInitializer.getArchivesFondss())
				{
					System.out.println("--"+item.getName());
				}
			}
			
			System.out.println("======================================================");
			
			//��ʾ��״�ṹ�ĵ���������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��״����������Ϣ: " +systemInitializer.getArchivesTypes().size()+" ��");
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
			
			//��ʾƽ��ṹ�ĵ���������Ϣ
			if (systemInitializer.getPlaneArchivesTypes()!=null)
			{
				System.out.println("ƽ�浵��������Ϣ: " +systemInitializer.getPlaneArchivesTypes().size()+" ��");
				for (ArchivesType item : systemInitializer.getPlaneArchivesTypes().values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName());
				}
			}
			
			System.out.println("======================================================");
			
			//��ʾ�����������ر���Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("��������������Ϣ:");
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					if (archivesType.getArchivesInfoTables()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"�ĵ�������������Ϣ:");
						for (ArchivesInfoTable item :archivesType.getArchivesInfoTables().values())
						{
							System.out.println("----"+item.getTableType().toString()+": "+item.getTableName());
						}
						System.out.println("");
					}
				}
			}
			
			System.out.println("======================================================");
			
			//��ʾ�����������������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("�����������������Ϣ: "+systemInitializer.getArchivesTypes().size()+" ��");
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					//����������
					if (archivesType.getDataItemsForAll()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"�ĵ�������������������Ϣ:"+archivesType.getDataItemsForAll().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForAll().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//��¼������
					if (archivesType.getDataItemsForInput()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"�ĵ���������¼��������Ϣ:"+archivesType.getDataItemsForInput().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInput().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//��¼��ѯ������
					if (archivesType.getDataItemsForInputQuery()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"�ĵ���������¼��ѯ��������Ϣ:"+archivesType.getDataItemsForInputQuery().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInputQuery().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//���ò�ѯ������
					if (archivesType.getDataItemsForUseQuery()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"�ĵ����������ò�ѯ��������Ϣ:"+archivesType.getDataItemsForUseQuery().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//������б���ʾ������
					if (archivesType.getDataItemsForListDisplay()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"�ĵ������������б���ʾ��������Ϣ:"+archivesType.getDataItemsForUseQuery().size()+" ��");
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
								System.out.println("++ " +templist.get(0).getFullCode()+"  ����������: "+templist.get(0).getDataItemsForAll().size());
							}
							
							if (templist.get(0).getDataItemsForInput()!=null)
								System.out.println("++ " +templist.get(0).getFullCode()+"  ��¼������: "+templist.get(0).getDataItemsForInput().size());
							
							if (templist.get(0).getDataItemsForListDisplay()!=null)
								System.out.println("++ " +templist.get(0).getFullCode()+"  �б���ʾ������: "+templist.get(0).getDataItemsForListDisplay().size());
							
							
						}
					}
				}
				
				
			}
			

			System.out.println("======================================================");
			
			//��ʾ���������Ŀ¼��ӡģ�����������Ϣ
			if (systemInitializer.getArchivesTypes()!=null)
			{
				System.out.println("�����������Ŀ¼��ӡģ����Ϣ: ");
				for (ArchivesType archivesType : systemInitializer.getArchivesTypes().values())
				{
					if (archivesType.getCatalogPrintTemplates()!=null)
					{
						System.out.println("--"+archivesType.getFullCode()+"��Ŀ¼��ӡģ����Ϣ:");
						for (EnumCatalogType catalogType : EnumCatalogType.values())
						{
							if (archivesType.getCatalogPrintTemplates().containsKey(catalogType))
							{
								System.out.println("--"+catalogType.toString()+" ����������Ϣ��");
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
			
			//��ʾ���ĵ����������������Ϣ
			if (systemInitializer.getOfficialArchivesTypes()!=null)
			{
				System.out.println("���ĵ����������������Ϣ: "+systemInitializer.getOfficialArchivesTypes().size()+" ��");
				for (OfficialArchivesType archivesType : systemInitializer.getOfficialArchivesTypes().values())
				{
					//����������
					if (archivesType.getDataItemsForAll()!=null)
					{
						System.out.println("--"+archivesType.getName()+"�ĵ�������������������Ϣ:"+archivesType.getDataItemsForAll().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForAll().values())
						{
							System.out.println("----"+item.getID()+","+ item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//��¼������
					if (archivesType.getDataItemsForInput()!=null)
					{
						System.out.println("--"+archivesType.getName()+"�ĵ���������¼��������Ϣ:"+archivesType.getDataItemsForInput().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInput().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//��¼��ѯ������
					if (archivesType.getDataItemsForInputQuery()!=null)
					{
						System.out.println("--"+archivesType.getName()+"�ĵ���������¼��ѯ��������Ϣ:"+archivesType.getDataItemsForInputQuery().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForInputQuery().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//���ò�ѯ������
					if (archivesType.getDataItemsForUseQuery()!=null)
					{
						System.out.println("--"+archivesType.getName()+"�ĵ����������ò�ѯ��������Ϣ:"+archivesType.getDataItemsForUseQuery().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
					
					//������б���ʾ������
					if (archivesType.getDataItemsForListDisplay()!=null)
					{
						System.out.println("--"+archivesType.getName()+"�ĵ������������б���ʾ��������Ϣ:"+archivesType.getDataItemsForUseQuery().size()+" ��");
						for (ArchivesTypeDataItem item :archivesType.getDataItemsForUseQuery().values())
						{
							System.out.println("----"+item.getID()+","+item.getColumnName()+","+item.getDisplayText());
						}
						System.out.println("");
					}
				}
				
				
			}
			
			System.out.println("======================================================");
			
			//��ʾ����������Ϣ
			if (systemInitializer.getRetentionPeriods()!=null)
			{
				System.out.println("���������ֵ���Ϣ: "+ systemInitializer.getRetentionPeriods().size() +" ��");
				for (RetentionPeriod item : systemInitializer.getRetentionPeriods().values())
				{
					System.out.println("--"+item.getName());
				}
			}
			
			System.out.println("======================================================");
			
			//��ʾ�����ܼ���Ϣ
			if (systemInitializer.getArchivesSecrecys()!=null)
			{
				System.out.println("�����ܼ��ֵ���Ϣ: " +systemInitializer.getArchivesSecrecys().size() +" ��");
				for (ArchivesSecrecy item : systemInitializer.getArchivesSecrecys().values())
				{
					System.out.println("--"+item.getName());
				}
			}
			
			System.out.println("======================================================");
			
			//��ʾ����Դ�ֵ���Ϣ
			if (systemInitializer.getDataSources()!=null)
			{
				System.out.println("����Դ�ֵ���Ϣ: "+systemInitializer.getDataSources().size()+" ��");
				for (DataSource dataSource : systemInitializer.getDataSources().values())
				{
					if (dataSource.getDataSourceItems()!=null)
					{
						System.out.println("--"+dataSource.getName()+"����������Ϣ: "+dataSource.getDataSourceItems().size() +" ��");
						for (DataSourceItem item : dataSource.getDataSourceItems().values())
						{
							System.out.println("----"+item.getOrderID()+":"+item.getName());
						}
						System.out.println("");
					}
				}
			}
			
			System.out.println("======================================================");
			
			//��ʾУ������ֵ���Ϣ
			if (systemInitializer.getCheckRules()!=null)
			{
				System.out.println("У������ֵ���Ϣ: " +systemInitializer.getCheckRules().size()+" ��");
				for (CheckRule item : systemInitializer.getCheckRules().values())
				{
					System.out.println("--"+item.getName());
				}
			}
			
		}
	}

}
