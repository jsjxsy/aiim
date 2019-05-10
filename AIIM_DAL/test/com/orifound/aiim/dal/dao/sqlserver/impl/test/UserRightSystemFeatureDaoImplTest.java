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

import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;

/**
 * 用户系统功能授权DAO实现的测试类
 *
 */
public class UserRightSystemFeatureDaoImplTest
{

	//DAO实现类
	private static UserRightSystemFeatureDaoImpl DaoImpl = new UserRightSystemFeatureDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl#save(com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl#delete(com.orifound.aiim.entity.UserRightSystemFeature, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl#findByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByUserID()
	{
		int pUserID=2;
		Map<String, SystemFeature> userRightSystemFeatures=new HashMap<String, SystemFeature>();
		if (DaoImpl.findByUserID(pUserID, userRightSystemFeatures, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindByUserID 查询结果集合数量: "+userRightSystemFeatures.values());
			if (userRightSystemFeatures.size()>0)
			{
				for (SystemFeature item : userRightSystemFeatures.values())
				{
					System.out.println(item.getName());
				}
			}
		}
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.UserRightSystemFeatureDaoImpl#findMenusByUserID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindMenusByUserID()
	{
		int pUserID=2;
		LinkedHashMap<String, SystemFeature> userRightMenus=new LinkedHashMap<String, SystemFeature>();
		if (DaoImpl.findMenusByUserID(pUserID, userRightMenus, pErrInfo)==false)
		{
			fail(pErrInfo.toString());
		}
		else
		{
			System.out.println("testFindMenusByUserID 查询结果集合数量: "+userRightMenus.size());
			if (userRightMenus.size()>0)
			{
				for (SystemFeature item : userRightMenus.values())
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
