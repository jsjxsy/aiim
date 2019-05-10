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

import com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * ��ɫϵͳ������Ȩ������������
 *
 */
public class RoleRightSystemFeatureManageServiceImplTest
{

	//ҵ���߼�ʵ����
	private static RoleRightSystemFeatureManageServiceImpl ServiceImpl = new RoleRightSystemFeatureManageServiceImpl();
	//������Ϣ����
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAOע��
		ServiceImpl.setRoleRightSystemFeatureDao(new RoleRightSystemFeatureDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl#saveRightSystemFeatureForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightSystemFeatureForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl#saveRightSystemFeaturesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightSystemFeaturesForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl#deleteRightSystemFeatureForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightSystemFeatureForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl#deleteRightSystemFeaturesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightSystemFeaturesForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl#findRightSystemFeatureMenusByRolesID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightSystemFeatureMenusByRolesID()
	{
		int[] pRoleID=new int[]{1,2};
		LinkedHashMap<String, SystemFeature> systemFeatures=new LinkedHashMap<String, SystemFeature>();
		if (ServiceImpl.findRightSystemFeatureMenusByRolesID(pRoleID, systemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRightSystemFeatureMenusByRolesID ��ѯ�����������: "+systemFeatures.size());
			if (systemFeatures.size()>0)
			{
				for (SystemFeature item : systemFeatures.values())
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightSystemFeatureManageServiceImpl#findRightSystemFeaturesByRolesID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightSystemFeaturesByRolesID()
	{
		int[] pRoleID=new int[]{1,2};
		Map<String, SystemFeature> systemFeatures=new HashMap<String, SystemFeature>();
		if (ServiceImpl.findRightSystemFeaturesByRolesID(pRoleID, systemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindRightSystemFeaturesByRolesID ��ѯ�����������: "+systemFeatures.size());
			if (systemFeatures.size()>0)
			{
				for (SystemFeature item : systemFeatures.values())
				{
					System.out.println(item.getName());
				}
			}
		}
	}

}
