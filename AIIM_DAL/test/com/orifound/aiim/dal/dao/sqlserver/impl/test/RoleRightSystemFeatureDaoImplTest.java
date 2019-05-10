/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl.test;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * 角色系统功能授权信息DAO实现的测试类
 *
 */
public class RoleRightSystemFeatureDaoImplTest
{

	//DAO实现类
	private static RoleRightSystemFeatureDaoImpl DaoImpl = new RoleRightSystemFeatureDaoImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//数据源注入
		DaoImpl.setDataSource(AIIMDataSource.getInstance().getDataSource());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		DaoImpl = null;
		pErrInfo = null;
	}

	@Before
	public void setUp() throws Exception
	{
		pErrInfo = new ErrInfo();
	}

	@After
	public void tearDown() throws Exception
	{
		pErrInfo = null;
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl#save(com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl#delete(com.orifound.aiim.entity.RoleRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl#findByRoleID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByRoleID()
	{
		int[] pRoleID=new int[]{1,2};
		Map<String, SystemFeature> roleRightSystemFeatures=new HashMap<String, SystemFeature>();
		if (DaoImpl.findByRoleID(pRoleID, roleRightSystemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindByRoleID 查询结果集合数量: "+roleRightSystemFeatures.size());
			if (roleRightSystemFeatures.size()>0)
			{
				for (SystemFeature item : roleRightSystemFeatures.values())
				{
					System.out.println(item.getName());
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightSystemFeatureDaoImpl#findMenusByRoleID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindMenusByRoleID()
	{
		int[] pRoleID=new int[]{1,2};
		LinkedHashMap<String, SystemFeature> roleRightMenus=new LinkedHashMap<String, SystemFeature>();
		if (DaoImpl.findMenusByRoleID(pRoleID, roleRightMenus, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindMenusByRoleID 查询结果集合数量: "+roleRightMenus.size());
			if (roleRightMenus.size()>0)
			{
				for (SystemFeature item : roleRightMenus.values())
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

}
