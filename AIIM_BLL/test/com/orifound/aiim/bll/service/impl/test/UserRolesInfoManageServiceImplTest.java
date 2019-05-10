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

import com.orifound.aiim.bll.service.impl.UserRolesInfoManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserRolesInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

/**
 * 用户角色信息管理服务实现类的测试类
 *
 */
public class UserRolesInfoManageServiceImplTest
{

	//业务逻辑实现类
	private static UserRolesInfoManageServiceImpl ServiceImpl = new UserRolesInfoManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setUserRolesInfoDao(new UserRolesInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRolesInfoManageServiceImpl#saveUserRolesInfo(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveUserRolesInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRolesInfoManageServiceImpl#deleteUserRolesInfo(com.orifound.aiim.entity.UserRolesInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteUserRolesInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserRolesInfoManageServiceImpl#findUserRolesInfosByUserID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testFindUserRolesInfosByUserID()
	{
		int pUserID=2;
		List<UserRolesInfo> userRolesInfos=new ArrayList<UserRolesInfo>();
		if (ServiceImpl.findUserRolesInfosByUserID(pUserID, userRolesInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println(pUserID+"所属角色：");
			for (UserRolesInfo item : userRolesInfos)
			{
				System.out.println("--角色: "+item.getName());
			}
			
		}
	}

}
