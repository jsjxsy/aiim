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

import com.orifound.aiim.bll.service.impl.RoleRightArchivesSecrecyManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色档案密级授权管理服务测试类
 *
 */
public class RoleRightArchivesSecrecyManageServiceImplTest
{

	//业务逻辑实现类
	private static RoleRightArchivesSecrecyManageServiceImpl ServiceImpl = new RoleRightArchivesSecrecyManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setRoleRightArchivesSecrecyDao(new RoleRightArchivesSecrecyDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesSecrecyManageServiceImpl#saveRightArchivesSecrecysForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightArchivesSecrecysForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesSecrecyManageServiceImpl#deleteRightArchivesSecrecysForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightArchivesSecrecysForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesSecrecyManageServiceImpl#findRightArchivesSecrecysByRolesID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightArchivesSecrecysByRolesID()
	{
		int[] pRoleID=new int[]{1};
		Map<Integer, ArchivesSecrecy> archivesSecrecys=new HashMap<Integer, ArchivesSecrecy>();
		if (ServiceImpl.findRightArchivesSecrecysByRolesID(pRoleID, archivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindRightArchivesSecrecysByRolesID 查询结果集数量: "+archivesSecrecys.size());
			for (ArchivesSecrecy item : archivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
	}

}
