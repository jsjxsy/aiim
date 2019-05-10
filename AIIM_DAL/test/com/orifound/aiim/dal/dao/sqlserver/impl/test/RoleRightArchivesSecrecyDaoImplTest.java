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

import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色档案密级权限DAO实现类的测试类
 *
 */
public class RoleRightArchivesSecrecyDaoImplTest
{

	//DAO实现类
	private static RoleRightArchivesSecrecyDaoImpl DaoImpl = new RoleRightArchivesSecrecyDaoImpl();
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
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl#save(com.orifound.aiim.entity.RoleRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSave()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl#delete(com.orifound.aiim.entity.RoleRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDelete()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl#update(com.orifound.aiim.entity.RoleRightArchivesSecrecy, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesSecrecyDaoImpl#findByRoleID(int, java.util.Map, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindByRoleID()
	{
		int[] pRoleID=new int[]{2};
		Map<Integer, ArchivesSecrecy> roleRightArchivesSecrecys=new HashMap<Integer, ArchivesSecrecy>();
		if (DaoImpl.findByRoleID(pRoleID, roleRightArchivesSecrecys, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByRoleID 查询结果集数量: "+roleRightArchivesSecrecys.size());
			for (ArchivesSecrecy item : roleRightArchivesSecrecys.values())
			{
				System.out.println(item.getName());
			}
		}
	}

}
