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

import com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * 用户系统功能授权管理服务测试类
 *
 */
public class UserRightSystemFeatureManageServiceImplTest
{

	//业务逻辑实现类
	private static UserRightSystemFeatureManageServiceImpl ServiceImpl = new UserRightSystemFeatureManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setUserRightSystemFeatureDao(new UserRightSystemFeatureDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl#saveRightSystemFeatureForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightSystemFeatureForUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl#saveRightSystemFeaturesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightSystemFeaturesForUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl#deleteRightSystemFeatureForUser(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightSystemFeatureForUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl#deleteRightSystemFeaturesForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightSystemFeaturesForUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl#findRightSystemFeatureMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightSystemFeatureMenusByUserID()
	{
		int pUserID=2;
		LinkedHashMap<String, SystemFeature> systemFeatures=new LinkedHashMap<String, SystemFeature>();
		if (ServiceImpl.findRightSystemFeatureMenusByUserID(pUserID, systemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindRightSystemFeatureMenusByUserID 查询结果集合数量: "+systemFeatures.size());
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightSystemFeatureManageServiceImpl#findRightSystemFeaturesByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightSystemFeaturesByUserID()
	{
		int pUserID=3;
		Map<String, SystemFeature> systemFeatures=new HashMap<String, SystemFeature>();
		if (ServiceImpl.findRightSystemFeaturesByUserID(pUserID, systemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindRightSystemFeaturesByUserID 查询结果集合数量: "+systemFeatures.size());
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
