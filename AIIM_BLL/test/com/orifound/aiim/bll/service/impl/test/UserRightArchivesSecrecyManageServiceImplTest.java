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

import com.orifound.aiim.bll.service.impl.UserRightArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 用户档案密级授权管理服务测试类
 *
 */
public class UserRightArchivesSecrecyManageServiceImplTest
{

	//业务逻辑实现类
	private static UserRightArchivesSecrecyManageServiceImpl ServiceImpl = new UserRightArchivesSecrecyManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setUserRightArchivesSecrecyDao(new UserRightArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightArchivesSecrecyManageServiceImpl#saveRightArchivesSecrecysForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightArchivesSecrecysForUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightArchivesSecrecyManageServiceImpl#deleteRightArchivesSecrecysForUser(com.orifound.aiim.entity.UserInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightArchivesSecrecysForUser()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRightArchivesSecrecyManageServiceImpl#findRightArchivesSecrecysByUserID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightArchivesSecrecysByUserID()
	{
		int pUserID=2;
		Map<Integer, ArchivesSecrecy> archivesSecrecys=new HashMap<Integer, ArchivesSecrecy>();
		if (ServiceImpl.findRightArchivesSecrecysByUserID(pUserID, archivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRightArchivesSecrecysByUserID 查询结果集数量: "+archivesSecrecys.size());
			for (ArchivesSecrecy item : archivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
	}

}
