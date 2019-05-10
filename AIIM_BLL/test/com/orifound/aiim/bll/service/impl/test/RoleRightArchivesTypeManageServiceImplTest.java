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

import com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.RoleRightArchivesTypeDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 角色档案资源授权管理服务测试类
 *
 */
public class RoleRightArchivesTypeManageServiceImplTest
{

	//业务逻辑实现类
	private static RoleRightArchivesTypeManageServiceImpl ServiceImpl = new RoleRightArchivesTypeManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setRoleRightArchivesTypeDao(new RoleRightArchivesTypeDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl#saveRightArchivesTypeForRole(com.orifound.aiim.entity.UserRole, com.orifound.aiim.entity.UserRightArchivesType, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightArchivesTypeForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl#saveRightArchivesTypesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveRightArchivesTypesForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl#deleteRightArchivesTypesForRole(com.orifound.aiim.entity.UserRole, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteRightArchivesTypesForRole()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.RoleRightArchivesTypeManageServiceImpl#findRightArchivesTypeByRolesID(int, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindRightArchivesTypeByRolesID()
	{
		int[] pRoleID=new int[]{1};
		LinkedHashMap<Integer, ArchivesType> archivestypes=new LinkedHashMap<Integer, ArchivesType>();
		if (ServiceImpl.findRightArchivesTypeByRolesID(pRoleID, archivestypes, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindByUserID 查询结果集数量: "+archivestypes.size());
			for (ArchivesType item : archivestypes.values())
			{
				System.out.println(item.getFullCode());
			}
		}
	}

}
