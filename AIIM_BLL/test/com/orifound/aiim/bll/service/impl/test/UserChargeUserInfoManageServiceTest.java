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

import com.orifound.aiim.bll.service.impl.UserChargeUserInfoManageServiceImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.UserChargeUserInfoDaoImpl;
import com.orifound.aiim.dal.dao.sqlserver.impl.test.AIIMDataSource;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;

/**
 * 用户代工信息管理服务测试类
 *
 */
public class UserChargeUserInfoManageServiceTest
{
	
	//业务逻辑实现类
	private static UserChargeUserInfoManageServiceImpl ServiceImpl = new UserChargeUserInfoManageServiceImpl();
	//错误信息对象
	private static ErrInfo pErrInfo = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		//DAO注入
		ServiceImpl.setUserChargeUserInfoDao(new UserChargeUserInfoDaoImpl(AIIMDataSource.getInstance().getDataSource()));
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
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeUserInfoManageServiceImpl#saveUserChargeUserInfo(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testSaveUserChargeUserInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeUserInfoManageServiceImpl#deleteUserChargeUserInfo(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testDeleteUserChargeUserInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeUserInfoManageServiceImpl#updateUserChargeUserInfo(com.orifound.aiim.entity.UserChargeUserInfo, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUpdateUserChargeUserInfo()
	{
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.orifound.aiim.bll.service.impl.UserChargeUserInfoManageServiceImpl#findUserChargeUserInfoByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)}.
	 */
	@Test
	public void testUserChargeUserInfosByUserID()
	{
		int pUserID=1;
		List<UserChargeUserInfo> userChargeUserInfos=new ArrayList<UserChargeUserInfo>();
		if (ServiceImpl.findUserChargeUserInfosByUserID(pUserID, userChargeUserInfos, pErrInfo)==false)
		{
			fail(pErrInfo.toShortString());
		}
		else
		{
			System.out.println("testFindUserChargeUserInfoByID 查询结果集数量: "+userChargeUserInfos.size());
		}
	}

}
