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
import com.orifound.aiim.bll.service.impl.CheckRuleManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceItemManageServiceImpl;
import com.orifound.aiim.bll.service.impl.DataSourceManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RetentionPeriodManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RoleRightArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl;
import com.orifound.aiim.bll.service.impl.SystemInitializeServiceImpl;
import com.orifound.aiim.bll.service.impl.SystemUserRightInitializeServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRightArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRightArchivesTypeManageServiceImpl;
import com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesFondsDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoTableDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesTypeDataItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.CheckRuleDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.DataSourceItemDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RetentionPeriodDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.SystemInitializer;

/**
 * ϵͳ�û�Ȩ�޳�ʼ�����������
 *
 */
public class SystemUserRightInitializeServiceImplTest
{

	//ҵ���߼�ʵ����
	private static SystemUserRightInitializeServiceImpl ServiceImpl = new SystemUserRightInitializeServiceImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;
	
	//ϵͳ��ʼ��ҵ���߼�ʵ����
	private static SystemInitializeServiceImpl SystemInitializeServiceImpl = new SystemInitializeServiceImpl();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//BLL����ע��
		
		//�û�ϵͳ������Ȩ����������
		ServiceImpl.setUserRightSystemFeatureManageService(new UserRightSystemFeatureManageServiceImpl(new UserRightSystemFeatureDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//��ɫϵͳ������Ȩ����������
		ServiceImpl.setRoleRightSystemFeatureManageService(new RoleRightSystemFeatureManageServiceImpl(new RoleRightSystemFeatureDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//�û�������Դ��Ȩ����������
		ServiceImpl.setUserRightArchivesTypeManageService(new UserRightArchivesTypeManageServiceImpl(new UserRightArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//��ɫ������Դ��Ȩ����������
		ServiceImpl.setRoleRightArchivesTypeManageService(new RoleRightArchivesTypeManageServiceImpl(new RoleRightArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//�û������ܼ���Ȩ����������
		ServiceImpl.setUserRightArchivesSecrecyManageService(new UserRightArchivesSecrecyManageServiceImpl(new UserRightArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		//��ɫ�����ܼ���Ȩ����������
		ServiceImpl.setRoleRightArchivesSecrecyManageService(new RoleRightArchivesSecrecyManageServiceImpl(new RoleRightArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource())));
		
		
		//ϵͳ��ʼ�������ҵ���߼�����ע��
		
		//����ȫ��
		ArchivesFondsManageServiceImpl archivesFondsManageServiceImpl=new ArchivesFondsManageServiceImpl();
		archivesFondsManageServiceImpl.setArchivesFondsDao(new ArchivesFondsDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setArchivesFondsManageService(archivesFondsManageServiceImpl);
		
		//���������Ϣ�����������
		ArchivesInfoTableManageServiceImpl archivesInfoTableManageServiceImpl=new ArchivesInfoTableManageServiceImpl();
		archivesInfoTableManageServiceImpl.setArchivesInfoTableDao(new ArchivesInfoTableDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setArchivesInfoTableManageService(archivesInfoTableManageServiceImpl);
		
		//�����������������
		ArchivesTypeManageServiceImpl archivesTypeManageServiceImpl=new ArchivesTypeManageServiceImpl();
		archivesTypeManageServiceImpl.setArchivesTypeDao(new ArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setArchivesTypeManageService(archivesTypeManageServiceImpl);
		
		//�����������������������
		ArchivesTypeDataItemManageServiceImpl archivesTypeDataItemManageServiceImpl=new ArchivesTypeDataItemManageServiceImpl();
		archivesTypeDataItemManageServiceImpl.setArchivesTypeDataItemDao(new ArchivesTypeDataItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setArchivesTypeDataItemManageService(archivesTypeDataItemManageServiceImpl);
		
		//����Դ����������
		DataSourceManageServiceImpl dataSourceManageServiceImpl=new DataSourceManageServiceImpl();
		dataSourceManageServiceImpl.setDataSourceDao(new DataSourceDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setDataSourceManageService(dataSourceManageServiceImpl);
		
		//����Դ�����������������
		DataSourceItemManageServiceImpl dataSourceItemManageServiceImpl=new DataSourceItemManageServiceImpl();
		dataSourceItemManageServiceImpl.setDataSourceItemDao(new DataSourceItemDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setDataSourceItemManageService(dataSourceItemManageServiceImpl);
		
		//У��������������
		CheckRuleManageServiceImpl checkRuleManageServiceImpl=new CheckRuleManageServiceImpl();
		checkRuleManageServiceImpl.setCheckRuleDao(new CheckRuleDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setCheckRuleManageService(checkRuleManageServiceImpl);
		
		//�������޹���������
		RetentionPeriodManageServiceImpl retentionPeriodManageServiceImpl=new RetentionPeriodManageServiceImpl(new RetentionPeriodDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setRetentionPeriodManageService(retentionPeriodManageServiceImpl);
		
		//�����ܼ�����������
		ArchivesSecrecyManageServiceImpl archivesSecrecyManageServiceImpl=new ArchivesSecrecyManageServiceImpl(new ArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
		SystemInitializeServiceImpl.setArchivesSecrecyManageService(archivesSecrecyManageServiceImpl);
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.SystemUserRightInitializeServiceImpl#findRightSystemFeatureMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightSystemFeatureMenusByUserID()
	{
		int pUserID=2;
		int[] pRolesID=new int[]{1};
		LinkedHashMap<String, SystemFeature> systemFeatureMenus=new LinkedHashMap<String, SystemFeature>();
		if (ServiceImpl.findRightSystemFeatureMenusByUserID(pUserID,pRolesID, systemFeatureMenus, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRightSystemFeatureMenusByUserID ��ѯ�����������: "+systemFeatureMenus.size());
			if (systemFeatureMenus.size()>0)
			{
				for (SystemFeature item : systemFeatureMenus.values())
				{
					System.out.println(item.getName());
					
					if (item.getChildSystemFeatures()!=null)
					{
						for (SystemFeature childMenu : item.getChildSystemFeatures().values())
						{
							System.out.println("--"+childMenu.getName());
							
							if (childMenu.getChildSystemFeatures()!=null)
							{
								for (SystemFeature endMenu : childMenu.getChildSystemFeatures().values())
								{
									System.out.println("----"+endMenu.getName());
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.SystemUserRightInitializeServiceImpl#findRightSystemFeaturesByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightSystemFeaturesByUserID()
	{
		int pUserID=2;
		int[] pRolesID=new int[]{1};
		Map<String, SystemFeature> systemFeatures=new HashMap<String, SystemFeature>();
		if (ServiceImpl.findRightSystemFeaturesByUserID(pUserID, pRolesID, systemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRightSystemFeaturesByUserID ��ѯ���������: "+systemFeatures.size());
			for (SystemFeature item : systemFeatures.values())
			{
				System.out.println(item.getName());
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.SystemUserRightInitializeServiceImpl#findRightArchivesTypesByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightArchivesTypesByUserID()
	{
		//ϵͳ��ʼ��
		SystemInitializer systemInitializer=SystemInitializer.getInstance();
		if (SystemInitializeServiceImpl.initialize(systemInitializer, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			int pUserID=2;
			int[] pRolesID=new int[]{1};
			LinkedHashMap<Integer, ArchivesType> archivesTypes=new LinkedHashMap<Integer, ArchivesType>();
			if (ServiceImpl.findRightArchivesTypesByUserID(pUserID, pRolesID, archivesTypes, pErrInfo)==false)
			{
				fail(pErrInfo.toShortString());
			}
			else
			{
				System.out.println("testFindRightArchivesTypesByUserID ��ѯ���������: "+archivesTypes.size());
				for (ArchivesType item : archivesTypes.values())
				{
					System.out.println("--"+item.getFullCode()+","+item.getFullName()+",����������:"+item.getDataItemsForAll().size());
					
					if (item.getChildArchivesTypes()!=null)
					{
						for (ArchivesType item1 : item.getChildArchivesTypes().values())
						{
							System.out.println("----"+item1.getFullCode()+","+item1.getFullName()+",����������:"+item1.getDataItemsForAll().size());
							
							if (item1.getChildArchivesTypes()!=null)
							{
								for (ArchivesType item2 : item1.getChildArchivesTypes().values())
								{
									System.out.println("------"+item2.getFullCode()+","+item2.getFullName()+",����������:"+item2.getDataItemsForAll().size());
									
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.SystemUserRightInitializeServiceImpl#findRightArchivesSecrecysByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightArchivesSecrecysByUserID()
	{
		int pUserID=2;
		int[] pRolesID=new int[]{1};
		Map<Integer, ArchivesSecrecy> archivesSecrecys=new HashMap<Integer, ArchivesSecrecy>();
		if (ServiceImpl.findRightArchivesSecrecysByUserID(pUserID, pRolesID, archivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRightArchivesSecrecysByUserID ��ѯ���������: "+archivesSecrecys.size());
			for (ArchivesSecrecy item : archivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
	}

}
